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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;

/**
 * Representa a implementação genérica e utilitária para implementação da camada
 * de negócio da aplicação.
 * 
 * @author helaine.lins
 * @created 01/04/2014 - 17:13:33
 */
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public abstract class AbstractServico<E extends Entidade, B extends BaseBean>
		implements IServico<E, B> {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 1233580687337898440L;

	/**
	 * Representa a classe da entidade persistente que está sendo manipulada no
	 * dao.
	 */
	private Class<E> classePersistente;

	/**
	 * Cria uma nova instância do serviço. A classe da entidade e do bean não
	 * precisa ser informada explicitamente no construtor, pois a mesma será
	 * obtida de acordo com a parametrização das classes filhas.
	 */
	@SuppressWarnings("unchecked")
	public AbstractServico() {
		this.classePersistente = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

	}

	/**
	 * Recupera a instância do mecanismo de persistência da classe.
	 * 
	 * @return Uma instância de {@link IDao}.
	 */
	public abstract IDao<E> getDao();

	/**
	 * Recupera a instância do mecanismo de log da classe.
	 * 
	 * @return Uma instância de {@link Log} da classe.
	 */
	public abstract Log getLog();

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico#incluir(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean)
	 */
	@Override
	@Transactional(readOnly = false)
	public Long incluir(B bean) {
		this.getLog().trace(
				"iniciando inclusao de "
						+ this.classePersistente.getSimpleName());

		if (bean == null) {
			throw new MapeamentoException("Os dados do bean encontram-se nulos",
					TipoErro.VALIDACAO);
		}

		E entidade = bean.getEntidade();

		if (entidade == null) {
			throw new MapeamentoException("Os dados do bean encontram-se nulos",
					TipoErro.VALIDACAO);
		}

		this.getLog().info("::parametros:: " + entidade);

		this.validar(entidade);
		return this.getDao().incluir(entidade);
	}

	/**
	 * Copia os dados de um bean para a entidade.
	 * 
	 * @param bean A instância que contém os dados a serem copiados
	 * @param entidade A instância que receberá os dados.
	 */
	protected abstract void copiarDadosParaAlterarEntidade(B bean, E entidade);
	
	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico#alterar(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	@Transactional(readOnly = false)
	public void alterar(B bean) {
		this.getLog().trace(
				"iniciando alteracao de "
						+ this.classePersistente.getSimpleName());

		if (bean == null) {
			throw new MapeamentoException("Os dados do bean encontram-se nulos",
					TipoErro.VALIDACAO);
		}

		E entidade = this.getDao().recuperar(bean.getBeanID());

		if (entidade == null) {
			throw new MapeamentoException("Os dados do bean encontram-se nulos",
					TipoErro.VALIDACAO);
		}

		this.copiarDadosParaAlterarEntidade(bean, entidade);
		
		this.validar(entidade);
		this.getDao().alterar(entidade);
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico#excluir(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	@Transactional(readOnly = false)
	public void excluir(B bean) {
		this.getLog().trace(
				"iniciando exclusao de "
						+ this.classePersistente.getSimpleName());

		if (bean == null) {
			throw new MapeamentoException("Os dados do bean encontram-se nulos",
					TipoErro.VALIDACAO);
		}

		E entidade = bean.getEntidade();

		if (entidade == null) {
			throw new MapeamentoException("Os dados do bean encontram-se nulos",
					TipoErro.VALIDACAO);
		}

		this.getDao().excluir(entidade);
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico#recuperar(java.lang.Long)
	 */
	@Override
	public B recuperar(Long identificador) {
		B bean = null;

		E entidade = this.getDao().recuperar(identificador);

		if (entidade != null) {
			bean = entidade.getBean();
		}

		return bean;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico#listar()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<B> listar() {
		List<B> beans = null;

		List<E> registros = this.getDao().listar();

		if (registros != null && !registros.isEmpty()) {
			beans = new ArrayList<B>();

			for (E entidade : registros) {
				beans.add((B) entidade.getBean());
			}
		}

		return beans;
	}

	/**
	 * Realiza as validações do HibernateValidator mapeadas na entidade.
	 * 
	 * @param entidade
	 *            a instancia que vai ser validada
	 * @param propIgnorar
	 *            as propriedades que devem ter a validacao ignorada
	 */
	protected void validar(Entidade entidade, String... propIgnorar) {
		if (entidade == null) {
			String chave = this.classePersistente.getSimpleName()
					+ ".obrigatorio";

			throw new MapeamentoException(chave, TipoErro.VALIDACAO);
		} else {
			List<Erro> erros = entidade.validar(propIgnorar);

			if (erros != null && !erros.isEmpty()) {
				throw new MapeamentoException(erros);
			}
		}
	}

	/**
	 * Lança erros caso a lista contenha elementos incializados.
	 * 
	 * @param erros
	 *            A lista que contém os erros a serem validados.
	 */
	protected void lancarErros(List<Erro> erros) {
		if (erros != null && !erros.isEmpty()) {
			throw new MapeamentoException(erros);
		}
	}

}
