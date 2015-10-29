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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.jfree.util.Log;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AnaliseEstudoBean;

/**
 * Representa a análise de um {@link Estudo} no sistema para aplicação dos critérios de inclusão e exclusão.
 * 
 * @author helaine.lins
 * @created 27/08/2014 - 18:55:12
 */
@Entity
@Table(name = "analise_estudo", schema = "mapeamento")
@SequenceGenerator(name = "seq_analise_estudo", sequenceName = "mapeamento.seq_analise_estudo")
public class AnaliseEstudo extends Entidade {

    private static final long serialVersionUID = 1805602057921097153L;

    @Id
    @GeneratedValue(generator = "seq_analise_estudo")
    @Column(name = "id_analise_estudo")
    private Long id;

    @Column(name = "comentario", nullable = true)
    private String comentario;

    @NotNull
    @Column(name = "incluido", nullable = false)
    private Boolean incluido;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_estudo", nullable = false)
    private Estudo estudo;

    @ManyToOne
    @JoinColumn(name = "id_revisor")
    private Revisor revisor;

    @ManyToOne
    @JoinColumn(name = "id_criterio", nullable = true)
    private Criterio criterio;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_matriz")
    private AnaliseEstudo matriz;

    @NotNull
    @Column(name = "concluido", nullable = false)
    private Boolean concluido;

    @OneToMany(mappedBy = "analise", targetEntity = HistoricoAnaliseEstudo.class)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @BatchSize(size = 1)
    private List<HistoricoAnaliseEstudo> alteracoes;

    @Column(name = "existe_conflito", nullable = true)
    private Boolean existeConflito;

    @ManyToOne
    @JoinColumn(name = "id_criterio_anterior", nullable = true)
    private Criterio criterioAnterior;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_etapa_analise", nullable = true)
    private EtapaAnalise etapa;

    /**
     * Cria uma nova instância da classe.
     */
    public AnaliseEstudo() {
        super();
    }

    /**
     * Cria uma nova instância da classe inicializando seu identificador.
     * 
     * @param id O identificador.
     */
    public AnaliseEstudo(Long id) {
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
     * Obtém o valor do atributo estudo.
     * 
     * @return Uma instância de {@link Estudo} contendo o valor do atributo estudo.
     */
    public Estudo getEstudo() {
        return this.estudo;
    }

    /**
     * Atualiza a instância de estudo com o valor de estudo.
     * 
     * @param estudo Uma instância de Estudo contendo o valor a ser atualizado.
     */
    public void setEstudo(Estudo estudo) {
        this.estudo = estudo;
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
     * Obtém o valor do atributo incluido.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo incluido.
     */
    public Boolean getIncluido() {
        return this.incluido;
    }

    /**
     * Atualiza a instância de incluido com o valor de incluido.
     * 
     * @param incluido Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setIncluido(Boolean incluido) {
        this.incluido = incluido;
    }

    /**
     * Obtém o valor do atributo criterio.
     * 
     * @return Uma instância de {@link Criterio} contendo o valor do atributo criterio.
     */
    public Criterio getCriterio() {
        return this.criterio;
    }

    /**
     * Atualiza a instância de criterio com o valor de criterio.
     * 
     * @param criterio Uma instância de Criterio contendo o valor a ser atualizado.
     */
    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }

    /**
     * Obtém o valor do atributo comentario.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo comentario.
     */
    public String getComentario() {
        return this.comentario;
    }

    /**
     * Atualiza a instância de comentario com o valor de comentario.
     * 
     * @param comentario Uma instância de String contendo o valor a ser atualizado.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Obtém o valor do atributo matriz.
     * 
     * @return Uma instância de {@link AnaliseEstudo} contendo o valor do atributo matriz.
     */
    public AnaliseEstudo getMatriz() {
        return this.matriz;
    }

    /**
     * Atualiza a instância de matriz com o valor de matriz.
     * 
     * @param matriz Uma instância de AnaliseEstudo contendo o valor a ser atualizado.
     */
    public void setMatriz(AnaliseEstudo matriz) {
        this.matriz = matriz;
    }

    /**
     * Obtém o valor do atributo concluido.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo concluido.
     */
    public Boolean getConcluido() {
        return this.concluido;
    }

    /**
     * Atualiza a instância de concluido com o valor de concluido.
     * 
     * @param concluido Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    /**
     * Obtém o valor do atributo alteracoes.
     * 
     * @return Uma instância de {@link List<HistoricoAnaliseEstudoBean>} contendo o valor do atributo alteracoes.
     */
    public List<HistoricoAnaliseEstudo> getAlteracoes() {
        return this.alteracoes;
    }

    /**
     * Atualiza a instância de alteracoes com o valor de alteracoes.
     * 
     * @param alteracoes Uma instância de List<HistoricoAnaliseEstudoBean> contendo o valor a ser atualizado.
     */
    public void setAlteracoes(List<HistoricoAnaliseEstudo> alteracoes) {
        this.alteracoes = alteracoes;
    }

    /**
     * Obtém o valor do atributo existeConflito.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo existeConflito.
     */
    public Boolean getExisteConflito() {
        return this.existeConflito;
    }

    /**
     * Atualiza a instância de existeConflito com o valor de existeConflito.
     * 
     * @param existeConflito Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setExisteConflito(Boolean existeConflito) {
        this.existeConflito = existeConflito;
    }

    /**
     * Obtém o valor do atributo criterioAnterior.
     * 
     * @return Uma instância de {@link Criterio} contendo o valor do atributo criterioAnterior.
     */
    public Criterio getCriterioAnterior() {
        return this.criterioAnterior;
    }

    /**
     * Atualiza a instância de criterioAnterior com o valor de criterioAnterior.
     * 
     * @param criterioAnterior Uma instância de Criterio contendo o valor a ser atualizado.
     */
    public void setCriterioAnterior(Criterio criterioAnterior) {
        this.criterioAnterior = criterioAnterior;
    }

    /**
     * Obtém o valor do atributo etapa.
     * 
     * @return Uma instância de {@link EtapaAnalise} contendo o valor do atributo etapa.
     */
    public EtapaAnalise getEtapa() {
        return this.etapa;
    }

    /**
     * Atualiza a instância de etapa com o valor de etapa.
     * 
     * @param etapa Uma instância de EtapaAnalise contendo o valor a ser atualizado.
     */
    public void setEtapa(EtapaAnalise etapa) {
        this.etapa = etapa;
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
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#getBean()
     */
    @SuppressWarnings("unchecked")
    @Override
    public AnaliseEstudoBean getBean() {
        AnaliseEstudoBean bean = new AnaliseEstudoBean();

        bean.setId(this.getId());
        bean.setDataInclusao(this.getDataInclusao());
        bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

        bean.setComentario(this.getComentario());
        bean.setIncluido(this.getIncluido());
        bean.setConcluido(this.getConcluido());

        if (this.getEstudo() != null) {
            bean.setEstudo(estudo.getBean());
        }

        if (this.getCriterio() != null) {
            bean.setCriterio(criterio.getBean());
        }

        if (this.getRevisor() != null) {
            bean.setRevisor(revisor.getBean());
        }

        if (this.matriz != null) {
            AnaliseEstudoBean matriz = new AnaliseEstudoBean();

            matriz.setId(this.matriz.getId());
            matriz.setDataInclusao(this.matriz.getDataInclusao());
            matriz.setDataUltimaAlteracao(this.matriz.getDataUltimaAlteracao());

            matriz.setComentario(this.matriz.getComentario());
            matriz.setIncluido(this.matriz.getIncluido());
            matriz.setConcluido(this.matriz.getConcluido());

            matriz.setEstudo(this.matriz.getEstudo().getBean());

            bean.setMatriz(matriz);
        }

        if (this.getCriterioAnterior() != null) {
            bean.setCriterioAnterior(criterioAnterior.getBean());
        }

        this.setExisteConflito(this.existeConflito);

        return bean;
    }

    /**
     * Recupera as diferenças de valores entre duas entidades.
     * 
     * @param inscricao A instância que contém os novos valores
     * @param propIgnorar As propriedades a serem ignoradas.
     * 
     * @return Uma {@link String} contendo as alterações realizadas.
     */
    public String getDiferencas(AnaliseEstudo novaAnalise, String... propIgnorar) {

        StringBuilder diferencas = new StringBuilder();

        try {
            if (novaAnalise != null) {

                if (novaAnalise.getConcluido() == null && this.getConcluido() != null) {
                    diferencas.append(" Concluido:null");
                } else if (!novaAnalise.getConcluido().equals(this.getConcluido())) {
                    diferencas.append(" Concluido:");
                    diferencas.append(novaAnalise.getConcluido());
                }

                if (novaAnalise.getCriterio() == null && this.getCriterio() != null) {
                    diferencas.append(" Criterio:null");
                } else if (novaAnalise.getCriterio() != null && !novaAnalise.getCriterio().equals(this.getCriterio())) {
                    diferencas.append(novaAnalise.getCriterio().toString());
                }

                if (novaAnalise.getComentario() == null && this.comentario != null) {
                    diferencas.append(" Comentario:null");
                } else if (novaAnalise.getComentario() != null
                        && !novaAnalise.getComentario().equalsIgnoreCase(this.comentario)) {
                    diferencas.append(" Comentario:");
                    diferencas.append(novaAnalise.getComentario());
                }

                if (novaAnalise.getIncluido() == null && this.getIncluido() != null) {
                    diferencas.append(" Incluido: null");
                } else if (!novaAnalise.getIncluido().equals(this.getIncluido())) {
                    diferencas.append(novaAnalise.getIncluido());
                }

                if (novaAnalise.getRevisor() == null && this.revisor == null) {
                    diferencas.append(" Revisor:null");
                } else if (!novaAnalise.getRevisor().equals(this.getRevisor())) {
                    diferencas.append(novaAnalise.getRevisor().toString());
                }

                if (novaAnalise.getMatriz() == null && this.getMatriz() != null) {
                    diferencas.append(" Matriz: null");
                } else if (novaAnalise.getMatriz() != null && !novaAnalise.getMatriz().equals(this.getMatriz())) {
                    diferencas.append(" Matriz:");
                    diferencas.append(novaAnalise.getMatriz().getId());
                    diferencas.append("-");
                    diferencas.append(novaAnalise.getMatriz().getEstudo().getTitulo());
                }

                if (novaAnalise.getCriterioAnterior() == null && this.getCriterioAnterior() != null) {
                    diferencas.append(" CriterioAnterior:null");
                } else if (novaAnalise.getCriterioAnterior() != null
                        && !novaAnalise.getCriterioAnterior().equals(this.getCriterioAnterior())) {
                    diferencas.append(novaAnalise.getCriterioAnterior().toString());
                }

                if (novaAnalise.getExisteConflito() == null && this.getExisteConflito() != null) {
                    diferencas.append(" ExisteConflito: null");
                } else if (!novaAnalise.getExisteConflito().equals(this.getExisteConflito())) {
                    diferencas.append(novaAnalise.getExisteConflito());
                }

            }
        } catch (Exception e) {
            Log.error("Ocorreu um erro ao gerar a diferença das alterações da análise de estudo", e);
        }

        return diferencas.toString();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#toString()
     */
    @Override
    public String toString() {
        String valor = "Analise:" + this.getId() + " Estudo:";

        if (this.getEstudo() != null) {
            valor += this.getEstudo().getId();
        }

        valor += "- ";

        if (this.criterio != null) {
            this.criterio.getDescricao();
        } else {
            valor += "Criterio:null";
        }

        return valor;
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
        } else if (obj instanceof AnaliseEstudo) {
            AnaliseEstudo objeto = (AnaliseEstudo) obj;

            if (this.getId() != null && objeto.getId() != null) {
                iguais = new EqualsBuilder().append(id, objeto.id).isEquals();
            } else {
                iguais = new EqualsBuilder().append(etapa, objeto.etapa).append(estudo, objeto.estudo).isEquals();
            }
        }

        return iguais;
    }
}
