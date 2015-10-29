/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EtapaAnaliseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.SelecaoEstudoBean;

/**
 * Representa os dados de uma etapa de revisão realizada na revisão sistemática.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 14:38:28
 */
@Entity
@Table(name = "etapa_analise", schema = "mapeamento")
@SequenceGenerator(name = "seq_etapa_analise", sequenceName = "mapeamento.seq_etapa_analise")
public class EtapaAnalise extends Entidade {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 2642918408376969786L;

    @Id
    @GeneratedValue(generator = "seq_etapa_analise")
    @Column(name = "id_etapa_analise")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "inicial", nullable = false)
    private boolean inicial;

    @NotNull
    @Column(name = "qtd_estudos", nullable = false)
    private Integer qtdEstudos;

    @Column(name = "qtd_incluidos", nullable = true)
    private Integer qtdIncluidos;

    @Column(name = "qtd_excluidos", nullable = true)
    private Integer qtdExcluidos;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_revisor", nullable = true)
    private Revisor revisor;

    @OneToMany(mappedBy = "etapa", fetch = FetchType.LAZY)
    private List<AnaliseEstudo> analises;

    @Column(name = "data_inicio")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dataInicio;

    @Column(name = "data_conclusao")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dataConclusao;

    @NotNull
    @Column(name = "concluida", nullable = false)
    private Boolean concluida;

    @Size(max = 400)
    @Column(name = "observacoes", nullable = true)
    private String observacoes;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_selecao_estudo", nullable = true)
    private SelecaoEstudo selecaoEstudo;

    /**
     * Cria uma nova instância da classe.
     */
    public EtapaAnalise() {
        super();
    }

    /**
     * Cria uma nova instância da classe inicializando o identificador da classe.
     * 
     * @param id O identificador da classe.
     */
    public EtapaAnalise(Long id) {
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
     * @return Uma instância de {@link Revisor} contendo o valor do atributo revisor.
     */
    public Revisor getRevisor() {
        return this.revisor;
    }

    /**
     * Atualiza a instância de revisor com o valor de revisor.
     * 
     * @param revisor Uma instância de Revisor contendo o valor a ser atualizado.
     */
    public void setRevisor(Revisor revisor) {
        this.revisor = revisor;
    }

    /**
     * Obtém o valor do atributo analises.
     * 
     * @return Uma instância de {@link List<AnaliseEstudo>} contendo o valor do atributo analises.
     */
    public List<AnaliseEstudo> getAnalises() {
        return this.analises;
    }

    /**
     * Atualiza a instância de analises com o valor de analises.
     * 
     * @param analises Uma instância de List<AnaliseEstudo> contendo o valor a ser atualizado.
     */
    public void setAnalises(List<AnaliseEstudo> analises) {
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
     * @return Uma instância de {@link SelecaoEstudo} contendo o valor do atributo selecaoEstudo.
     */
    public SelecaoEstudo getSelecaoEstudo() {
        return this.selecaoEstudo;
    }

    /**
     * Atualiza a instância de selecaoEstudo com o valor de selecaoEstudo.
     * 
     * @param selecaoEstudo Uma instância de SelecaoEstudo contendo o valor a ser atualizado.
     */
    public void setSelecaoEstudo(SelecaoEstudo selecaoEstudo) {
        this.selecaoEstudo = selecaoEstudo;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#getEntidadeID()
     */
    @Override
    public Long getEntidadeID() {
        return this.id;
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
        } else if (obj instanceof EtapaAnalise) {
            EtapaAnalise objeto = (EtapaAnalise) obj;
            iguais =
                    new EqualsBuilder().append(revisor, objeto.revisor).append(selecaoEstudo, objeto.selecaoEstudo)
                            .isEquals();
        }

        return iguais;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#getBean()
     */
    @SuppressWarnings("unchecked")
    @Override
    public EtapaAnaliseBean getBean() {
        EtapaAnaliseBean bean = new EtapaAnaliseBean();

        bean.setId(this.getId());
        bean.setDataInclusao(this.getDataInclusao());
        bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

        bean.setDescricao(this.getDescricao());
        bean.setInicial(this.isInicial());
        bean.setQtdEstudos(this.getQtdEstudos());
        bean.setQtdIncluidos(this.getQtdIncluidos());
        bean.setQtdExcluidos(this.getQtdExcluidos());
        bean.setDataInicio(this.getDataInicio());
        bean.setDataConclusao(this.getDataConclusao());
        bean.setConcluida(this.getConcluida());
        bean.setObservacoes(this.getObservacoes());

        if (this.getSelecaoEstudo() != null) {
            bean.setSelecaoEstudo(new SelecaoEstudoBean(this.getSelecaoEstudo().getId()));
        }

//        if (this.getAnalises() != null) {
//            List<AnaliseEstudoBean> beans = new ArrayList<AnaliseEstudoBean>();
//
//            for (AnaliseEstudo aEstBean : this.getAnalises()) {
//                if (aEstBean != null) {
//                    beans.add(aEstBean.getBean());
//                }
//            }
//
//            bean.setAnalises(beans);
//        }

        return bean;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#toString()
     */
    @Override
    public String toString() {
        return "Etapa:" + this.getId() + "-" + this.getDescricao();
    }

}
