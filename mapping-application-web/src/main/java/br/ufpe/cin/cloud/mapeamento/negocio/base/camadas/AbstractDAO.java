/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.camadas;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.CodErrosGeraisEnum;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.DAOException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;

/**
 * Representa a implementação genérica e utilitária para implementação da camada
 * de acesso a dados da aplicação.
 * 
 * @author helaine.lins
 * @created 01/04/2014 - 17:13:18
 */
public abstract class AbstractDAO<E extends Entidade> implements IDao<E> {

	/**
	 * Representa a classe da entidade persistente que está sendo manipulada no
	 * dao.
	 */
	protected Class<E> classePersistente;

	/**
	 * Representa a session factory do hibernate.
	 */
	@Autowired(required = true)
	private SessionFactory sessionFactory;

	/**
	 * Cria uma nova instância do dao. A classe da entidade não precisa ser
	 * informada explicitamente, pois a mesma será obtida de acordo com a
	 * parametrização das classes filhas.
	 */
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		Type superClasse = getClass().getGenericSuperclass();

		if (superClasse instanceof ParameterizedType) {
			this.classePersistente = (Class<E>) ((ParameterizedType) superClasse)
					.getActualTypeArguments()[0];

		}
	}

	/**
	 * Atualiza a instância de sessionFactory com o valor de sessionFactory
	 * inicializado pelo Spring.
	 * 
	 * @param sessionFactory
	 *            Uma instância de SessionFactory contendo o valor a ser
	 *            atualizado.
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Obtém o valor do atributo sessionFactory.
	 * 
	 * @return Uma instância de {@link SessionFactory} contendo o valor do
	 *         atributo sessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	/**
	 * Obtém a {@link Session} corrente da {@link SessionFactory}.
	 * 
	 * @return Uma instância de {@link Session}.
	 */
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * Recupera a instância do mecanismo de log da classe.
	 * 
	 * @return {@link Log} a referência do mecanismo de log.
	 */
	public abstract Log getLog();

	/**
	 * 
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao#incluir(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	public Long incluir(E entidade) {
		this.getLog().trace("iniciando incluir");

		Long identificador = null;
		Object chave = null;

		try {
			entidade.setDataInclusao(LocalDateTime.now());
			entidade.setDataUltimaAlteracao(LocalDateTime.now());

			chave = this.getSession().save(entidade);

			if (chave instanceof Long) {
				identificador = (Long) chave;
			} else {
				identificador = ((Entidade) chave).getEntidadeID();
			}

			this.getLog().debug(
					this.classePersistente.getSimpleName().toLowerCase()
							+ " inserido(a) id:" + identificador);

		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "incluir");
		}

		this.getLog().trace("finalizando incluir");

		return identificador;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao#incluir(java.util.List)
	 */
	@Override
	public void incluir(List<E> entidades) {
		try {
			int tamBuffer = 0;

			for (tamBuffer = 0; tamBuffer < entidades.size(); tamBuffer++) {
				Entidade entidade = entidades.get(tamBuffer);

				entidade.setDataInclusao(LocalDateTime.now());
				entidade.setDataUltimaAlteracao(LocalDateTime.now());

				this.getSession().save(entidade);

				if (tamBuffer % 50 == 0) {
					this.getSession().flush();
					this.getSession().clear();
				}
			}

			// comita o restinho se sobrar!
			if (tamBuffer % 50 != 0) {
				this.getSession().flush();
				this.getSession().clear();
			}

		} catch (Throwable e) {
			Erro erro = new Erro();

			erro.setCodigo(CodErrosGeraisEnum.GERAL_ERRO_PERSISTENCIA_COODIGO
					.getChave());
			erro.setErro("Ocorreu um erro ao atualizar os dados da entidade.");
			erro.setTipoErro(TipoErro.ACESSO_DADOS);

			throw new DAOException(erro, e);
		}
	}

	/**
	 * 
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao#alterar(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	public void alterar(E entidade) {
		this.getLog().trace("iniciando alterar");

		try {
			entidade.setDataUltimaAlteracao(LocalDateTime.now());
			this.getSession().update(entidade);
		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "alterar");
		}

		this.getLog().trace("finalizando alterar");
	}

	/**
	 * {@inheritdoc}
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao#alterar(java.util.List)
	 */
	@Override
	public void alterar(List<E> entidades) {
		try {
			int tamBuffer = 0;

			for (tamBuffer = 0; tamBuffer < entidades.size(); tamBuffer++) {
				Entidade alocacao = entidades.get(tamBuffer);
				this.getSession().update(alocacao);

				if (tamBuffer % 50 == 0) {
					this.getSession().flush();
					this.getSession().clear();
				}

			}

			// comita o restinho se sobrar!
			if (tamBuffer % 50 != 0) {
				this.getSession().flush();
				this.getSession().clear();
			}

		} catch (Throwable e) {
			Erro erro = new Erro();

			erro.setCodigo(CodErrosGeraisEnum.GERAL_ERRO_PERSISTENCIA_COODIGO
					.getChave());
			erro.setErro("Ocorreu um erro ao atualizar os dados da entidade.");
			erro.setTipoErro(TipoErro.ACESSO_DADOS);

			throw new DAOException(erro, e);
		}

	}

	/**
	 * 
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao#excluir(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	public void excluir(E entidade) {
		this.getLog().trace("iniciando excluir");

		try {
			this.getSession().delete(entidade);
		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "excluir");
		}

		this.getLog().trace("finalizando excluir");
	}

	/**
	 * 
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao#recuperar(java.lang.Long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E recuperar(Long identificador) {
		this.getLog().trace("iniciando recuperar");

		E instancia = null;

		try {
			instancia = (E) this.getSession().get(this.classePersistente,
					identificador);
		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "recuperar");
		}

		this.getLog().trace("finalizando recuperar");

		return instancia;
	}

	/**
	 * 
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao#listar()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<E> listar() {
		this.getLog().trace("iniciando listar");

		List<E> instancias = null;

		try {

			Criteria criteria = this.getSession().createCriteria(
					this.classePersistente);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			instancias = criteria.list();
		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "listar");
		}

		this.getLog().trace("finalizando listar");

		return instancias;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao#listar(java.lang.String,
	 *      boolean)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<E> listar(String propOrdenacao, boolean ascendente) {
		this.getLog().trace("iniciando listar");

		List<E> encontrados = null;

		try {

			Criteria criteria = this.getSession().createCriteria(
					this.classePersistente);

			if (ascendente) {
				criteria.addOrder(Order.asc(propOrdenacao));
			} else {
				criteria.addOrder(Order.desc(propOrdenacao));
			}

			encontrados = criteria.list();
		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "incluir");
		}

		this.getLog().trace("finalizando listar");

		return encontrados;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao#listar(int,
	 *      int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<E> listar(int inicio, int tamanho) {
		this.getLog().trace("iniciando listar");

		List<E> encontrados = null;

		try {
			Criteria criterio = this.getSession().createCriteria(
					this.classePersistente);
			criterio.setFirstResult(inicio);
			criterio.setMaxResults(tamanho);

			encontrados = criterio.list();

		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "listar");
		}

		this.getLog().trace("finalizando listar");

		return encontrados;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao#atualizarDataUltimaAlteracao(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	public final void atualizarDataUltimaAlteracao(E objeto) {
		this.getLog().trace("iniciando atualizarDataUltimaAlteracao");

		try {

			objeto.setDataUltimaAlteracao(new LocalDateTime());

			this.getSession().saveOrUpdate(objeto);
		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "atualizar data ultima alteracao");
		}

		this.getLog().trace("finalizando atualizarDataUltimaAlteracao");
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao#procurar(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade,
	 *      int, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<E> procurar(final E exemplo, final int inicio, final int tamanho) {
		this.getLog().trace("iniciando procurar");

		List<E> encontrados = null;

		try {
			final Criteria criterio = this.getSession().createCriteria(
					this.classePersistente);

			Example exemploConfig = Example.create(exemplo);
			exemploConfig.enableLike(MatchMode.ANYWHERE);
			exemploConfig.ignoreCase();

			criterio.add(exemploConfig);
			if (inicio != 0) {
				criterio.setFirstResult(inicio);
			}
			if (tamanho != 0) {
				criterio.setMaxResults(tamanho);
			}

			encontrados = criterio.list();
		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "procurar por exemplo paginado");
		}

		this.getLog().trace("finalizando procurar");

		return encontrados;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao#procurar(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<E> procurar(final E exemplo) {
		this.getLog().trace("iniciando procurar");

		List<E> encontrados = null;

		try {
			final Criteria criterio = this.getSession().createCriteria(
					this.classePersistente);

			Example exemploConfig = Example.create(exemplo);
			exemploConfig.enableLike(MatchMode.ANYWHERE);
			exemploConfig.ignoreCase();

			criterio.add(exemploConfig);

			encontrados = criterio.list();
		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "procurar por exemplo");
		}

		this.getLog().trace("finalizando procurar");

		return encontrados;
	}

	/**
	 * Realiza o tratamento das exceções levantadas na camada de acesso a dados
	 * do sistema.
	 * 
	 * @param e
	 *            A instância da exceção causada.
	 */
	protected void tratarErroPersistencia(Throwable e, String operacao) {
		this.getLog().trace("iniciando tratarErroPersistencia");

		Erro erro = this.getMensagemErro(operacao);
		this.getLog().debug(erro.getErro(), e);

		this.getLog().trace("finalizando tratarErroPersistencia");

		throw new DAOException(erro, e);
	}

	/**
	 * Recupera uma mensagem de falha padrão com o nome da tabela a qual a
	 * {@link Entidade} foi mapeada.
	 * 
	 * @return Uma instância de {@link Erro} contendo a mensagem: Falha de
	 *         acesso a dados na tabela + 'nome da tabela' ao + 'operacao'
	 */
	protected Erro getMensagemErro(String operacao) {
		this.getLog().trace("iniciando getMensagemErro");

		Table anotacaoTabela = this.classePersistente
				.getAnnotation(Table.class);

		Object argumentos = new Object[] { anotacaoTabela.name(),
				this.classePersistente.getSimpleName(), operacao };

		Erro erro = new Erro();

		erro.setCodigo(CodErrosGeraisEnum.GERAL_ERRO_PERSISTENCIA_COODIGO
				.getChave());

		erro.setErro(MapeamentoUtil.recuperarMensagemProperties(
				CodErrosGeraisEnum.GERAL_ERRO_PERSISTENCIA_MENSAGEM.getChave(),
				argumentos));

		erro.setTipoErro(TipoErro.ACESSO_DADOS);

		this.getLog().trace("finalizando getMensagemErro");

		return erro;
	}

	/**
	 * Aplica um conjunto de filtros a uma determinada query.
	 * 
	 * @param hql
	 *            A instância que receberá os filtros a serem aplicados.
	 * @param filtros
	 *            A instância que contém os filtros a serem aplicados.
	 */
	protected String aplicarFiltros(String hql, List<FiltroPropriedade> filtros, boolean append) {
		FiltroPropriedade filtro;
		
		String query = new String(hql);
		
		if (filtros != null && !filtros.isEmpty()) {
			
			if (!append) {
				query += " WHERE ";
			}
			
			for (int i = 0; i < filtros.size(); i++) {
				filtro = filtros.get(i);
				if (filtro != null) {
					
					if (filtro.getTipoFiltro().equals(TipoFiltroEnum.NULL)) {

						if (!append && i == 0) {
							query += filtro.getPropriedade();
						} else {
							query += " AND " + filtro.getPropriedade();
						}

						if (Boolean.TRUE.equals(Boolean.class.cast(filtro
								.getValor()))) {
							query += " is null ";
						} else {
							query += " is not null ";
						}

					} else if (filtro.getTipoFiltro().equals(
							TipoFiltroEnum.LIKE)) {
						
						if (!append && i == 0) {
							query += " upper("; 
						} else {
							query += " AND upper(";
						}
						
						query +=  filtro.getPropriedade() + ") "
								+ filtro.getTipoFiltro().getOperador()
								+ " upper(:Valor" + i + ")";

					} else {
						
						if (!append && i == 0) {
							query += " "; 
						} else {
							query += " AND ";
						}
						
						query +=  filtro.getPropriedade() + " "
								+ filtro.getTipoFiltro().getOperador()
								+ " :Valor" + i;
					}
				}
			}
		}
		
		return query;
	}

	/**
	 * Aplica um conjunto de valores de filtros a uma determinada query.
	 * 
	 * @param query
	 *            A instância que receberá os valores dos filtros.
	 * @param filtros
	 *            A instância que contém os valores dos filtros a serem
	 *            aplicados.
	 */
	protected void aplicarValoresFiltros(Query query,
			List<FiltroPropriedade> filtros) {

		FiltroPropriedade filtro;

		if (filtros != null && !filtros.isEmpty()) {
			for (int i = 0; i < filtros.size(); i++) {

				filtro = filtros.get(i);

				if (filtro != null
						&& !(filtro.getTipoFiltro().equals(TipoFiltroEnum.NULL))) {

					if (filtro.getTipo().equals(Long.class)) {
						query.setParameter("Valor" + i,
								(Long) filtro.getValor());
					} else if (filtro.getTipo().equals(Integer.class)) {
						query.setParameter("Valor" + i,
								(Integer) filtro.getValor());
					} else if (filtro.getTipo().equals(Double.class)) {
						query.setParameter("Valor" + i,
								(Double) filtro.getValor());
					} else if (filtro.getTipo().equals(Boolean.class)) {
						query.setParameter("Valor" + i,
								(Boolean) filtro.getValor());
					} else if (filtro.getTipo().equals(String.class)) {
						if (filtro.getTipoFiltro().equals(TipoFiltroEnum.LIKE)) {
							query.setParameter("Valor" + i, "%"
									+ (String) filtro.getValor() + "%");
						} else {
							query.setParameter("Valor" + i,
									(String) filtro.getValor());
						}
					}
				}
			}
		}
	}

	protected String aplicarOrdenacoes(String hql,
			List<OrdenacaoPropriedade> ordenacoes, String propOrdenacaoPadrao) {
		
		String query = new String(hql);
		
		if (ordenacoes != null && !ordenacoes.isEmpty()) {
			query += " ORDER BY ";
			for (OrdenacaoPropriedade ordenacao : ordenacoes) {
				if (ordenacao != null) {
					query += ordenacao.getPropriedade();
					if (ordenacao.isDescendente()) {
						query += " DESC ";
					}
				}
			}
		} else {
			query += " ORDER BY " + propOrdenacaoPadrao;
		}
		
		return query;
	}
}
