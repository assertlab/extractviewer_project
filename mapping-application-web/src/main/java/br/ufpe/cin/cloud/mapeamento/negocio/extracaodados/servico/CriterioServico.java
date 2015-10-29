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
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.CriterioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.ICriterioDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Criterio;

/**
 * Representa à implementação da camada de negócios da entidade {@link Criterio}
 * .
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:28:13
 */
@Service(value = "criterioServico")
public class CriterioServico extends AbstractServico<Criterio, CriterioBean>
		implements ICriterioServico {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 3548498707604989474L;

	/**
	 * Representa o mecanismo de log da classe.
	 */
	protected static Log LOG = LogFactory.getLog(CriterioServico.class);

	/**
	 * Representa a instância da camada de acesso a dados da entidade
	 * {@link Criterio}.
	 */
	@Autowired
	private ICriterioDAO dao;

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getDao()
	 */
	@Override
	public ICriterioDAO getDao() {
		return dao;
	}

	/**
	 * Atualiza a instância de dao com o valor de dao.
	 * 
	 * @param dao
	 *            Uma instância de ICriterioDAO contendo o valor a ser
	 *            atualizado.
	 */
	public void setDao(ICriterioDAO dao) {
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
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#listar()
	 */
	@Override
	public List<CriterioBean> listar() {
		List<CriterioBean> critBean = null;
		
		List<Criterio> criterios =  this.dao.listar("descricao", true);
		
		if (criterios != null && !criterios.isEmpty()) {
			critBean = new ArrayList<CriterioBean>();
			
			for (Criterio criterio : criterios) {
				if (criterio != null) {
					critBean.add(criterio.getBean());
				}
			}
		}
		return critBean;
	}
	
	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.ICriterioServico#buscarCriterio(java.lang.String)
	 */
	@Override
	public CriterioBean buscarCriterio(String descricao) {
		CriterioBean bean = null;
		
		if (MapeamentoUtil.isEmpty(descricao)) {
			throw new MapeamentoException(new Erro(
					CriterioErroEnum.ERRO_DESCRICAO_OBRIGATORIA,
					TipoErro.VALIDACAO));
		}
		
		Criterio criterio =  this.dao.buscarCriterio(descricao);
		
		if (criterio != null) {
			bean = criterio.getBean();
		}
		
		return bean;
	}
	
	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#copiarDadosParaAlterarEntidade(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean, br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	protected void copiarDadosParaAlterarEntidade(CriterioBean bean,
			Criterio entidade) {
		
		entidade.setDescricao(bean.getDescricao());
		entidade.setInclusao(bean.getInclusao());
		entidade.setDataUltimaAlteracao(LocalDateTime.now());
	}
}
