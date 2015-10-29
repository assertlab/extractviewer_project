/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.RevisorBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.BuscaDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IBuscaDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEstudoDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IHistoricoEstudoDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IRevisorDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Busca;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.HistoricoEstudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;

/**
 * Representa a implementação da camada de negócios da entidade Estudo.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 18:36:40
 */
@Service(value = "estudoServico")
public class EstudoServico extends AbstractServico<Estudo, EstudoBean>
		implements IEstudoServico {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -4478944801602269986L;

	/**
	 * Representa o mecanismo de log da classe.
	 */
	protected static Log LOG = LogFactory.getLog(BuscaDAO.class);

	/**
	 * A instância da camada de acesso à dados da entidade {@link Estudo}
	 */
	@Autowired
	private IEstudoDAO dao;

	/**
	 * Representa a instância da camada de acesso à dados da entidade Busca.
	 */
	@Autowired
	private IBuscaDAO daoBusca;

	/**
	 * Representa a instância da camada de acesso à dados da entidade Revisor.
	 */
	@Autowired
	private IRevisorDAO daoRevisor;

	/**
	 * Representa a instância da camada de acesso à dados da entidade
	 * HistorioEstudo.
	 */
	@Autowired
	private IHistoricoEstudoDAO daoHistoricoEstudo;

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getDao()
	 */
	@Override
	public IEstudoDAO getDao() {
		return this.dao;
	}

	/**
	 * Atualiza a instância de dao com o valor de dao.
	 * 
	 * @param dao
	 *            Uma instância de IEstudoDAO contendo o valor a ser atualizado.
	 */
	public void setDao(IEstudoDAO dao) {
		this.dao = dao;
	}

	/**
	 * Atualiza a instância de daoRevisor com o valor de daoRevisor.
	 * 
	 * @param daoRevisor
	 *            Uma instância de IRevisorDAO contendo o valor a ser
	 *            atualizado.
	 */
	public void setDaoRevisor(IRevisorDAO daoRevisor) {
		this.daoRevisor = daoRevisor;
	}

	/**
	 * Atualiza a instância de daoHistoricoEstudo com o valor de
	 * daoHistoricoEstudo.
	 * 
	 * @param daoHistoricoEstudo
	 *            Uma instância de IHistoricoEstudoDAO contendo o valor a ser
	 *            atualizado.
	 */
	public void setDaoHistoricoEstudo(IHistoricoEstudoDAO daoHistoricoEstudo) {
		this.daoHistoricoEstudo = daoHistoricoEstudo;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getLog()
	 */
	@Override
	public Log getLog() {
		return LOG;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IEstudoServico#incluir(java.util.List)
	 */
	@Override
	@Transactional(readOnly = false)
	public List<Estudo> incluir(BuscaBean buscaBean, List<EstudoBean> beans) {

		Busca entidadeBusca = this.daoBusca.recuperar(buscaBean.getId());

		if (entidadeBusca == null) {
			throw new MapeamentoException(new Erro(
					EstudoErroEnum.ERRO_INCLUIR_ESTUDOS_BUSCA_NAO_ENCONTRADA,
					TipoErro.VALIDACAO));
		}

		List<Estudo> estudos = new ArrayList<Estudo>();

		try {
			Estudo estudo = null;

			List<Erro> errosInstancia = new ArrayList<Erro>();
			List<Erro> errosConsolidado = new ArrayList<Erro>();
			StringBuilder erros = null;

			for (EstudoBean bean : beans) {
				if (bean != null) {
					estudo = bean.getEntidade();
					estudo.setBusca(entidadeBusca);
					errosInstancia = estudo.validar();

					if (errosInstancia != null && !errosInstancia.isEmpty()) {
						erros = new StringBuilder();

						for (Erro erro : errosInstancia) {
							LOG.error("Falha ao incluir em lote:["
									+ erro.getErro() + "]");
							erros.append(erro.getErro());
							erros.append(" | ");
							LOG.error(" Estudo: " + estudo.toString());
						}

						errosConsolidado
								.add(new Erro(
										BuscaErroEnum.ERRO_INCLUIR_ESTUDOS_ARQUIVO_BIB,
										TipoErro.HIBERNATE_VALIDATOR, erros
												.toString()));
					}

					estudos.add(estudo);
				}
			}

			if (!errosConsolidado.isEmpty()) {
				throw new MapeamentoException(errosConsolidado);
			}

			this.dao.incluir(estudos);
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar salvar os estudos na base de dados.",
					e);

			throw new MapeamentoException(new Erro(
					EstudoErroEnum.ERRO_INCLUIR_ESTUDOS, TipoErro.INESPERADO),
					e);
		}

		return estudos;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IEstudoServico#alterar(br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.RevisorBean,
	 *      br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstudoBean)
	 */
	@Override
	@Transactional(readOnly = false)
	public void alterar(RevisorBean revisorBean, EstudoBean estudoBean) {

		if (estudoBean == null) {
			throw new MapeamentoException(
					"Os dados do bean encontram-se nulos", TipoErro.VALIDACAO);
		}

		Estudo estudo = this.getDao().recuperar(estudoBean.getBeanID());

		if (estudo == null) {
			throw new MapeamentoException(
					"Os dados do estudo não foram encontrados",
					TipoErro.VALIDACAO);
		}

		// gerar o historico de alteracao
		Revisor revisor = this.daoRevisor.recuperar(revisorBean.getId());

		if (revisor == null) {
			throw new MapeamentoException(new Erro(
					EstudoErroEnum.ERRO_ALTERAR_ESTUDOS_REVISOR_NAO_ENCONTRADO,
					TipoErro.VALIDACAO));
		}

		estudoBean.setPalavrasChave(StringUtils.replace(estudoBean.getPalavrasChave(), ",", ";"));
		
		LocalDateTime now = LocalDateTime.now();
		HistoricoEstudo alteracao = new HistoricoEstudo();

		alteracao.setDataInclusao(now);
		alteracao.setDataUltimaAlteracao(now);
		alteracao.setEstudo(estudo);

		alteracao.setRevisor(revisor);
		alteracao.setDadosAnteriores(estudo.toString() + "-"
				+ estudo.getResumo());
		alteracao.setDiferenca(estudo.getDiferencas(estudoBean.getEntidade(),
				"alteracoes", "busca") + "-" + estudo.getResumo());

		// altera os dados
		this.copiarDadosParaAlterarEntidade(estudoBean, estudo);

		this.validar(estudo);
		this.getDao().alterar(estudo);

		// finaliza o histórico de alteracao - desconsidera os campos abaixo
		// pois os mesmos não são alterados por essência.
		alteracao.setDadosAtuais(estudo.toString() + "-" + estudo.getResumo());
		
		this.daoHistoricoEstudo.incluir(alteracao);
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#copiarDadosParaAlterarEntidade(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean,
	 *      br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	protected void copiarDadosParaAlterarEntidade(EstudoBean bean,
			Estudo entidade) {

		entidade.setAno(bean.getAno());
		entidade.setArquivo(bean.getArquivo());
		entidade.setAutores(bean.getAutores());
		entidade.setCodigo(bean.getCodigo());
		entidade.setDataUltimaAlteracao(LocalDateTime.now());
		entidade.setResumo(bean.getResumo());
		entidade.setTitulo(bean.getTitulo());
		entidade.setTituloLimpo(MapeamentoUtil.getStringLimpaUppercase(bean.getTitulo()));
		entidade.setUrlNaoTratada(bean.isUrlNaoTratada());
		entidade.setPalavrasChave(bean.getPalavrasChave());

	}

	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IEstudoServico#estatisticaQuantidadeEstudosPorAno()
	 */
	@Override
	public List<EstatisticasEstudoBean> estatisticaQuantidadeEstudosPorAno() {
		return this.dao.estatisticaQuantidadeEstudosPorAno();
	}
	
}
