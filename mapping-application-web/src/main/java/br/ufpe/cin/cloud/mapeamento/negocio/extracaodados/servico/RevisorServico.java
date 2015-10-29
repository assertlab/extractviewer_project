/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.RevisorBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IRevisorDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;

/**
 * Representa à implementação da camada de negócios da entidade {@link Revisor}
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:32:33
 */
@Service(value = "revisorServico")
public class RevisorServico extends AbstractServico<Revisor, RevisorBean>
		implements IRevisorServico {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 3548498707604989474L;

	/**
	 * Representa o mecanismo de log da classe.
	 */
	protected static Log LOG = LogFactory.getLog(RevisorServico.class);

	/**
	 * Representa a instância da camada de acesso a dados da entidade
	 * {@link Revisor}.
	 */
	@Autowired
	private IRevisorDAO dao;

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getDao()
	 */
	@Override
	public IRevisorDAO getDao() {
		return dao;
	}

	/**
	 * Atualiza a instância de dao com o valor de dao.
	 * 
	 * @param dao
	 *            Uma instância de IBaseDeDadosDAO contendo o valor a ser
	 *            atualizado.
	 */
	public void setDao(IRevisorDAO dao) {
		this.dao = dao;
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
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#copiarDadosParaAlterarEntidade(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean, br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	protected void copiarDadosParaAlterarEntidade(RevisorBean bean,
			Revisor entidade) {
		
		entidade.setDataUltimaAlteracao(LocalDateTime.now());
	}

	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IRevisorServico#recuperarRevisor(java.lang.Long)
	 */
	@Override
	public RevisorBean recuperarRevisor(String login) {
		RevisorBean revisor = null;
		
		if (MapeamentoUtil.isEmpty(login)) {
			throw new MapeamentoException(new Erro(
					RevisorErroEnum.ERRO_LOGIN_OBRIGATORIO,
					TipoErro.VALIDACAO));
		}
		
		Revisor entRevisor = this.dao.buscarRevisor(login);
		
		
		if (entRevisor != null) {
			revisor = entRevisor.getBean();
		}
		
		return revisor;
	}
}
