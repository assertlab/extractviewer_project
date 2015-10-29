/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EstagioExecucao;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.SelecaoEstudo;

/**
 * Representa os dados de uma seleção de estudo.
 * 
 * @author helaine.lins
 * @created 28/04/2015 - 18:15:38
 */
public class SelecaoEstudoBean extends BaseBean {

    private static final long serialVersionUID = -5898078862370740098L;

    private Long id;
    private List<EtapaAnaliseBean> etapasAnalise;
    private TipoAnaliseEstudoEnumBean tipoAnalise;
    private List<RevisorBean> participantes;
    private Integer ordem;
    private Integer qtdConflitos;
    private Integer qtdSelecionados;
    private RevisorBean revisor;
    private Boolean concluida;
    private Boolean selecaoFinal;
    private SelecaoEstudoBean matriz;
    private LocalDateTime dataInicio;
    private LocalDateTime dataConclusao;
    private String descricao;
    private String observacoes;
    private EstagioExecucaoBean estagioExecucao;

    
    /**
     * Cria uma nova instância da classe.
     */
    public SelecaoEstudoBean() {
        super();
    }
    
    /**
     * Cria uma nova instância da classe.
     * 
     * @param id O identificador da classe.
     */
    public SelecaoEstudoBean(Long id) {
        this();
        this.id = id;
    }
    
    /**
     * Obtém o valor do atributo id.
     * 
     * @return Uma instância de {@link Long} contendo o valor do atributo id.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Atualiza a instância de id com o valor de id.
     * 
     * @param id Uma instância de Long contendo o valor a ser atualizado.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o valor do atributo etapasAnalise.
     * 
     * @return Uma instância de {@link List<EtapaAnaliseBean>} contendo o valor do atributo etapasAnalise.
     */
    public List<EtapaAnaliseBean> getEtapasAnalise() {
        return this.etapasAnalise;
    }

    /**
     * Atualiza a instância de etapasAnalise com o valor de etapasAnalise.
     * 
     * @param etapasAnalise Uma instância de List<EtapaAnaliseBean> contendo o valor a ser atualizado.
     */
    public void setEtapasAnalise(List<EtapaAnaliseBean> etapasAnalise) {
        this.etapasAnalise = etapasAnalise;
    }

    /**
     * Obtém o valor do atributo tipoAnalise.
     * 
     * @return Uma instância de {@link TipoAnaliseEstudoEnumBean} contendo o valor do atributo tipoAnalise.
     */
    public TipoAnaliseEstudoEnumBean getTipoAnalise() {
        return this.tipoAnalise;
    }

    /**
     * Atualiza a instância de tipoAnalise com o valor de tipoAnalise.
     * 
     * @param tipoAnalise Uma instância de TipoAnaliseEstudoEnumBean contendo o valor a ser atualizado.
     */
    public void setTipoAnalise(TipoAnaliseEstudoEnumBean tipoAnalise) {
        this.tipoAnalise = tipoAnalise;
    }

    /**
     * Obtém o valor do atributo participantes.
     * 
     * @return Uma instância de {@link List<RevisorBean>} contendo o valor do atributo participantes.
     */
    public List<RevisorBean> getParticipantes() {
        return this.participantes;
    }

    /**
     * Atualiza a instância de participantes com o valor de participantes.
     * 
     * @param participantes Uma instância de List<RevisorBean> contendo o valor a ser atualizado.
     */
    public void setParticipantes(List<RevisorBean> participantes) {
        this.participantes = participantes;
    }

    /**
     * Obtém o valor do atributo ordem.
     * 
     * @return Uma instância de {@link Integer} contendo o valor do atributo ordem.
     */
    public Integer getOrdem() {
        return this.ordem;
    }

    /**
     * Atualiza a instância de ordem com o valor de ordem.
     * 
     * @param ordem Uma instância de Integer contendo o valor a ser atualizado.
     */
    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    /**
     * Obtém o valor do atributo qtdConflitos.
     * 
     * @return Uma instância de {@link Integer} contendo o valor do atributo qtdConflitos.
     */
    public Integer getQtdConflitos() {
        return this.qtdConflitos;
    }

    /**
     * Atualiza a instância de qtdConflitos com o valor de qtdConflitos.
     * 
     * @param qtdConflitos Uma instância de Integer contendo o valor a ser atualizado.
     */
    public void setQtdConflitos(Integer qtdConflitos) {
        this.qtdConflitos = qtdConflitos;
    }

    /**
     * Obtém o valor do atributo qtdSelecionados.
     * 
     * @return Uma instância de {@link Integer} contendo o valor do atributo qtdSelecionados.
     */
    public Integer getQtdSelecionados() {
        return this.qtdSelecionados;
    }

    /**
     * Atualiza a instância de qtdSelecionados com o valor de qtdSelecionados.
     * 
     * @param qtdSelecionados Uma instância de Integer contendo o valor a ser atualizado.
     */
    public void setQtdSelecionados(Integer qtdSelecionados) {
        this.qtdSelecionados = qtdSelecionados;
    }

    /**
     * Obtém o valor do atributo revisor.
     * 
     * @return Uma instância de {@link RevisorBean} contendo o valor do atributo revisor.
     */
    public RevisorBean getRevisor() {
        return this.revisor;
    }

    /**
     * Atualiza a instância de revisor com o valor de revisor.
     * 
     * @param revisor Uma instância de RevisorBean contendo o valor a ser atualizado.
     */
    public void setRevisor(RevisorBean revisor) {
        this.revisor = revisor;
    }

    /**
     * Obtém o valor do atributo concluida.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo concluida.
     */
    public Boolean getConcluida() {
        return this.concluida;
    }

    /**
     * Atualiza a instância de concluida com o valor de concluida.
     * 
     * @param concluida Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    /**
     * Obtém o valor do atributo selecaoFinal.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo selecaoFinal.
     */
    public Boolean getSelecaoFinal() {
        return this.selecaoFinal;
    }

    /**
     * Atualiza a instância de selecaoFinal com o valor de selecaoFinal.
     * 
     * @param selecaoFinal Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setSelecaoFinal(Boolean selecaoFinal) {
        this.selecaoFinal = selecaoFinal;
    }

    /**
     * Obtém o valor do atributo matriz.
     * 
     * @return Uma instância de {@link SelecaoEstudoBean} contendo o valor do atributo matriz.
     */
    public SelecaoEstudoBean getMatriz() {
        return this.matriz;
    }

    /**
     * Atualiza a instância de matriz com o valor de matriz.
     * 
     * @param matriz Uma instância de SelecaoEstudoBean contendo o valor a ser atualizado.
     */
    public void setMatriz(SelecaoEstudoBean matriz) {
        this.matriz = matriz;
    }

    /**
     * Obtém o valor do atributo dataInicio.
     * 
     * @return Uma instância de {@link LocalDateTime} contendo o valor do atributo dataInicio.
     */
    public LocalDateTime getDataInicio() {
        return this.dataInicio;
    }

    /**
     * Atualiza a instância de dataInicio com o valor de dataInicio.
     * 
     * @param dataInicio Uma instância de LocalDateTime contendo o valor a ser atualizado.
     */
    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Obtém o valor do atributo dataConclusao.
     * 
     * @return Uma instância de {@link LocalDateTime} contendo o valor do atributo dataConclusao.
     */
    public LocalDateTime getDataConclusao() {
        return this.dataConclusao;
    }

    /**
     * Atualiza a instância de dataConclusao com o valor de dataConclusao.
     * 
     * @param dataConclusao Uma instância de LocalDateTime contendo o valor a ser atualizado.
     */
    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    /**
     * Obtém o valor do atributo descricao.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo descricao.
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Atualiza a instância de descricao com o valor de descricao.
     * 
     * @param descricao Uma instância de String contendo o valor a ser atualizado.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o valor do atributo observacoes.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo observacoes.
     */
    public String getObservacoes() {
        return this.observacoes;
    }

    /**
     * Atualiza a instância de observacoes com o valor de observacoes.
     * 
     * @param observacoes Uma instância de String contendo o valor a ser atualizado.
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * Obtém o valor do atributo estagioExecucao.
     * 
     * @return Uma instância de {@link EstagioExecucaoBean} contendo o valor do atributo estagioExecucao.
     */
    public EstagioExecucaoBean getEstagioExecucao() {
        return this.estagioExecucao;
    }

    /**
     * Atualiza a instância de estagioExecucao com o valor de estagioExecucao.
     * 
     * @param estagioExecucao Uma instância de EstagioExecucaoBean contendo o valor a ser atualizado.
     */
    public void setEstagioExecucao(EstagioExecucaoBean estagioExecucao) {
        this.estagioExecucao = estagioExecucao;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
     */
    @SuppressWarnings("unchecked")
    @Override
    public SelecaoEstudo getEntidade() {
        SelecaoEstudo entidade = new SelecaoEstudo();

        entidade.setId(this.getId());
        entidade.setConcluida(this.getConcluida());
        entidade.setDataConclusao(this.getDataConclusao());
        entidade.setDataInclusao(this.getDataInclusao());
        entidade.setDataInicio(this.getDataInicio());
        entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());
        entidade.setDescricao(this.getDescricao());
        
        if (this.getEstagioExecucao() != null) {
            entidade.setEstagioExecucao(new EstagioExecucao(this.getEstagioExecucao().getId()));
        }

        if (this.getMatriz() != null) {
            entidade.setMatriz(new SelecaoEstudo(this.getMatriz().getId()));
        }
        
        entidade.setObservacoes(this.getObservacoes());
        entidade.setOrdem(this.getOrdem());
        entidade.setQtdConflitos(this.getQtdConflitos());
        entidade.setQtdSelecionados(this.getQtdSelecionados());
        
        if (this.getRevisor() != null) {
            entidade.setRevisor(new Revisor(this.getRevisor().getId()));
        }
        
        entidade.setSelecaoFinal(this.getSelecaoFinal());
        
        if (this.getTipoAnalise() != null) {
            entidade.setTipoAnalise(this.getTipoAnalise().getEnumeracao());
        }
        
        if (this.participantes != null && !this.participantes.isEmpty()) {
            List<Revisor> entParticipantes = new ArrayList<Revisor>();
            
            for (RevisorBean participante : this.participantes) {
                if (participante != null) {
                    entParticipantes.add(new Revisor(participante.getId()));
                }
            }
            
            entidade.setParticipantes(entParticipantes);
        }
        
        return entidade;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getBeanID()
     */
    @Override
    public Long getBeanID() {
        return this.id;
    }

}
