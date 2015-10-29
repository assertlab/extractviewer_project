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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BaseDeDadosBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IBaseDeDadosDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.BaseDeDados;

/**
 * Representa à implementação da camada de negócios da entidade
 * {@link BaseDeDados}.
 * 
 * @author helaine.lins
 * @created 19/08/2014 - 10:49:12
 */
@Service(value = "baseDeDadosServico")
public class BaseDeDadosServico extends
		AbstractServico<BaseDeDados, BaseDeDadosBean> implements
		IBaseDeDadosServico {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 3548498707604989474L;

	/**
	 * Representa o mecanismo de log da classe.
	 */
	protected static Log LOG = LogFactory.getLog(BaseDeDadosServico.class);

	/**
	 * Representa a instância da camada de acesso a dados da entidade
	 * {@link BaseDeDados}.
	 */
	@Autowired
	private IBaseDeDadosDAO dao;

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getDao()
	 */
	@Override
	public IBaseDeDadosDAO getDao() {
		return dao;
	}

	/**
	 * Atualiza a instância de dao com o valor de dao.
	 * 
	 * @param dao
	 *            Uma instância de IBaseDeDadosDAO contendo o valor a ser
	 *            atualizado.
	 */
	public void setDao(IBaseDeDadosDAO dao) {
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
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IBaseDeDadosServico#buscar(java.lang.String)
	 */
	@Override
	public BaseDeDadosBean buscar(String nome) {
		BaseDeDadosBean bean = null;
		
		if (MapeamentoUtil.isEmpty(nome)) {
			throw new MapeamentoException(new Erro(
					BaseDeDadosErroEnum.ERRO_BUSCA_NOME_OBRIGATORIO, TipoErro.VALIDACAO));
		}
		
		try {
			BaseDeDados base = this.dao.buscar(nome);
			
			if (base != null) {
				bean = base.getBean();
			}
		} catch (Exception e) {
			LOG.error("Ocorreu um erro ao buscar base de dados com o nome:["
					+ nome + "]", e);

			throw new MapeamentoException(new Erro(
					BaseDeDadosErroEnum.ERRO_BUSCA_NOME, TipoErro.INESPERADO),
					e);
		}
		
		return bean;
	}

	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#copiarDadosParaAlterarEntidade(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean, br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	protected void copiarDadosParaAlterarEntidade(BaseDeDadosBean bean,
			BaseDeDados entidade) {
		
		entidade.setNome(bean.getNome());
		entidade.setUrl(bean.getUrl());
		
	}
}
