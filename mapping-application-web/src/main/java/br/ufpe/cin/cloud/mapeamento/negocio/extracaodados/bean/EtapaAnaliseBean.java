/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.joda.time.LocalDateTime;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EtapaAnalise;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.SelecaoEstudo;

/**
 * Representa o encapsulamento dos dados de uma etapa da revisao.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 14:44:39
 */
public class EtapaAnaliseBean extends BaseBean {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 7135599388427645069L;

    private Long id;
    private String descricao;
    private RevisorBean revisor;
    private List<AnaliseEstudoBean> analises;
    private boolean inicial;
    private Integer qtdEstudos;
    private Integer qtdIncluidos;
    private Integer qtdExcluidos;
    private LocalDateTime dataInicio;
    private LocalDateTime dataConclusao;
    private Boolean concluida;
    private String observacoes;
    private Double percentualConclusao;
    private SelecaoEstudoBean selecaoEstudo;

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getBeanID()
     */
    @Override
    public Long getBeanID() {
        return this.id;
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
     * Obtém o valor do atributo analises.
     * 
     * @return Uma instância de {@link List<AnaliseEstudoBean>} contendo o valor do atributo analises.
     */
    public List<AnaliseEstudoBean> getAnalises() {
        return this.analises;
    }

    /**
     * Atualiza a instância de analises com o valor de analises.
     * 
     * @param analises Uma instância de List<AnaliseEstudoBean> contendo o valor a ser atualizado.
     */
    public void setAnalises(List<AnaliseEstudoBean> analises) {
        this.analises = analises;
    }

    /**
     * Obtém o valor do atributo inicial.
     * 
     * @return Uma instância de {@link boolean} contendo o valor do atributo inicial.
     */
    public boolean isInicial() {
        return this.inicial;
    }

    /**
     * Atualiza a instância de inicial com o valor de inicial.
     * 
     * @param inicial Uma instância de boolean contendo o valor a ser atualizado.
     */
    public void setInicial(boolean inicial) {
        this.inicial = inicial;
    }

    /**
     * Obtém o valor do atributo qtdEstudos.
     * 
     * @return Uma instância de {@link Integer} contendo o valor do atributo qtdEstudos.
     */
    public Integer getQtdEstudos() {
        return this.qtdEstudos;
    }

    /**
     * Atualiza a instância de qtdEstudos com o valor de qtdEstudos.
     * 
     * @param qtdEstudos Uma instância de Integer contendo o valor a ser atualizado.
     */
    public void setQtdEstudos(Integer qtdEstudos) {
        this.qtdEstudos = qtdEstudos;
    }

    /**
     * Obtém o valor do atributo qtdIncluidos.
     * 
     * @return Uma instância de {@link Integer} contendo o valor do atributo qtdIncluidos.
     */
    public Integer getQtdIncluidos() {
        return this.qtdIncluidos;
    }

    /**
     * Atualiza a instância de qtdIncluidos com o valor de qtdIncluidos.
     * 
     * @param qtdIncluidos Uma instância de Integer contendo o valor a ser atualizado.
     */
    public void setQtdIncluidos(Integer qtdIncluidos) {
        this.qtdIncluidos = qtdIncluidos;
    }

    /**
     * Obtém o valor do atributo qtdExcluidos.
     * 
     * @return Uma instância de {@link Integer} contendo o valor do atributo qtdExcluidos.
     */
    public Integer getQtdExcluidos() {
        return this.qtdExcluidos;
    }

    /**
     * Atualiza a instância de qtdExcluidos com o valor de qtdExcluidos.
     * 
     * @param qtdExcluidos Uma instância de Integer contendo o valor a ser atualizado.
     */
    public void setQtdExcluidos(Integer qtdExcluidos) {
        this.qtdExcluidos = qtdExcluidos;
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
     * Obtém o valor do atributo percentualConclusao.
     * 
     * @return Uma instância de {@link Double} contendo o valor do atributo percentualConclusao.
     */
    public Double getPercentualConclusao() {
        return this.percentualConclusao;
    }

    /**
     * Atualiza a instância de percentualConclusao com o valor de percentualConclusao.
     * 
     * @param percentualConclusao Uma instância de Double contendo o valor a ser atualizado.
     */
    public void setPercentualConclusao(Double percentualConclusao) {
        this.percentualConclusao = percentualConclusao;
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
     * Obtém o valor do atributo selecaoEstudo.
     * 
     * @return Uma instância de {@link SelecaoEstudoBean} contendo o valor do atributo selecaoEstudo.
     */
    public SelecaoEstudoBean getSelecaoEstudo() {
        return this.selecaoEstudo;
    }

    /**
     * Atualiza a instância de selecaoEstudo com o valor de selecaoEstudo.
     * 
     * @param selecaoEstudo Uma instância de SelecaoEstudoBean contendo o valor a ser atualizado.
     */
    public void setSelecaoEstudo(SelecaoEstudoBean selecaoEstudo) {
        this.selecaoEstudo = selecaoEstudo;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean iguais = false;

        if (obj == this) {
            iguais = true;
        } else if (obj instanceof EtapaAnaliseBean) {
            EtapaAnaliseBean objeto = (EtapaAnaliseBean) obj;
            iguais = new EqualsBuilder().append(revisor, objeto.revisor).isEquals();
        }

        return iguais;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
     */
    @SuppressWarnings("unchecked")
    @Override
    public EtapaAnalise getEntidade() {
        EtapaAnalise entidade = new EtapaAnalise();

        entidade.setId(this.getId());
        entidade.setDataInclusao(this.getDataInclusao());
        entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

        entidade.setDescricao(this.getDescricao());
        entidade.setInicial(this.isInicial());
        entidade.setQtdEstudos(this.getQtdEstudos());
        entidade.setQtdIncluidos(this.getQtdIncluidos());
        entidade.setQtdExcluidos(this.getQtdExcluidos());
        entidade.setDataInicio(this.getDataInicio());
        entidade.setDataConclusao(this.getDataConclusao());
        entidade.setConcluida(this.getConcluida());
        entidade.setObservacoes(this.getObservacoes());

        if (this.selecaoEstudo != null) {
            entidade.setSelecaoEstudo(new SelecaoEstudo(this.selecaoEstudo.getId()));
        }

        if (this.getRevisor() != null) {
            entidade.setRevisor(new Revisor(this.getRevisor().getId()));
        }
        
        if (this.selecaoEstudo != null) {
            entidade.setSelecaoEstudo(new SelecaoEstudo(this.selecaoEstudo.getId()));
        }

        return entidade;
    }

}
