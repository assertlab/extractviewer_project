/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.revisor;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.FilterEvent;

import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EtapaAnaliseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.EtapaAnaliseServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IEtapaAnaliseServico;
import br.ufpe.cin.cloud.mapeamento.web.base.camadas.AbstractMB;
import br.ufpe.cin.cloud.mapeamento.web.base.util.MapeamentoWebUtil;

/**
 * Representa o bean gerenciável da tela de um revisor no sistema.
 * 
 * @author helaine.lins
 * @created 01/09/2014 - 09:40:21
 */
@ViewScoped
@ManagedBean(name = "revisorMB")
public class RevisorMB extends AbstractMB {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 7535239321426405379L;

	/**
	 * Representa a instância da camada de servico {@link EtapaAnaliseServico}.
	 */
	@ManagedProperty(value = "#{etapaAnaliseServico}")
	private IEtapaAnaliseServico etapaRevisaoServico;

	private EtapaAnaliseBean containerDados;
	private List<EtapaAnaliseBean> etapas;
	private Long idRevisao;
	private Boolean botaoAnalisarAtivado;

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.web.base.camadas.AbstractMB#carregarPagina()
	 */
	@Override
	public void carregarPagina() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			this.listarEtapas();
			this.botaoAnalisarAtivado = false;
		}
	}

	/**
	 * Lista as etapas de revisões alocadas para o revisor logado no sistema.
	 */
	private void listarEtapas() {
		this.etapas = this.etapaRevisaoServico.listarEtapasAnalise(getLoginUsuarioLogado());
	}
	
	public void filterListener(FilterEvent filterEvent) {
		this.botaoAnalisarAtivado = true;
	}
	
	public void onRowSelect(SelectEvent event) {
		this.botaoAnalisarAtivado = true;
//	    if (event != null && event.getObject() != null) {
//	    	idRevisao = ((EtapaAnaliseBean) event.getObject()).getId();
//	    }
	}
	
	public String exibirEstudosAnalise() {
	    MapeamentoWebUtil.removerAtributoSessao("revisao");
		MapeamentoWebUtil.adicionarAtributoSessao("revisao", this.containerDados);
		return "exibirEstudosAnalise";
	}
	
	/**
	 * Atualiza a instância de etapaRevisaoServico com o valor de
	 * etapaRevisaoServico.
	 * 
	 * @param etapaRevisaoServico
	 *            Uma instância de IEtapaAnaliseServico contendo o valor a ser
	 *            atualizado.
	 */
	public void setEtapaRevisaoServico(IEtapaAnaliseServico etapaRevisaoServico) {
		this.etapaRevisaoServico = etapaRevisaoServico;
	}

	/**
	 * Obtém o valor do atributo containerDados.
	 * 
	 * @return Uma instância de {@link EtapaAnaliseBean} contendo o valor do
	 *         atributo containerDados.
	 */
	public EtapaAnaliseBean getContainerDados() {
		return this.containerDados;
	}

	/**
	 * Atualiza a instância de containerDados com o valor de containerDados.
	 * 
	 * @param containerDados
	 *            Uma instância de EtapaAnaliseBean contendo o valor a ser
	 *            atualizado.
	 */
	public void setContainerDados(EtapaAnaliseBean containerDados) {
		this.containerDados = containerDados;
	}

	/**
	 * Obtém o valor do atributo etapas.
	 * 
	 * @return Uma instância de {@link List<EtapaAnaliseBean>} contendo o valor
	 *         do atributo etapas.
	 */
	public List<EtapaAnaliseBean> getEtapas() {
		return this.etapas;
	}

	/**
	 * Atualiza a instância de etapas com o valor de etapas.
	 * 
	 * @param etapas
	 *            Uma instância de List<EtapaAnaliseBean> contendo o valor a ser
	 *            atualizado.
	 */
	public void setEtapas(List<EtapaAnaliseBean> etapas) {
		this.etapas = etapas;
	}

	/**
	 * Obtém o valor do atributo idRevisao.
	 * 
	 * @return Uma instância de {@link Long} contendo o valor do atributo idRevisao.
	 */
	public Long getIdRevisao() {
		return this.idRevisao;
	}

	/**
	 * Atualiza a instância de idRevisao com o valor de idRevisao.
	 *
	 * @param idRevisao Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setIdRevisao(Long idRevisao) {
		this.idRevisao = idRevisao;
	}

	/**
	 * Obtém o valor do atributo botaoAnalisarAtivado.
	 * 
	 * @return Uma instância de {@link Boolean} contendo o valor do atributo botaoAnalisarAtivado.
	 */
	public Boolean getBotaoAnalisarAtivado() {
		return this.botaoAnalisarAtivado;
	}

	/**
	 * Atualiza a instância de botaoAnalisarAtivado com o valor de botaoAnalisarAtivado.
	 *
	 * @param botaoAnalisarAtivado Uma instância de Boolean contendo o valor a ser atualizado.
	 */
	public void setBotaoAnalisarAtivado(Boolean botaoAnalisarAtivado) {
		this.botaoAnalisarAtivado = botaoAnalisarAtivado;
	}

	
}
