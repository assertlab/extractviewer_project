/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstagioExecucaoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EtapaAnaliseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.RevisorBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.SelecaoEstudoBean;

/**
 * Representa os dados de selecao de estudos na etapa de execução.
 * 
 * @author helaine.lins
 * @created 28/04/2015 - 13:49:34
 */
@Entity
@Table(name = "selecao_estudo", schema = "mapeamento")
@SequenceGenerator(name = "seq_selecao_estudo", sequenceName = "mapeamento.seq_selecao_estudo")
public class SelecaoEstudo extends Entidade {

    private static final long serialVersionUID = -3136279853026053236L;

    @Id
    @GeneratedValue(generator = "seq_selecao_estudo")
    @Column(name = "id_selecao_estudo")
    private Long id;

    @OneToMany(mappedBy = "selecaoEstudo", fetch = FetchType.LAZY)
    private List<EtapaAnalise> etapasAnalise;

    @NotNull
    @Type(type = USER_TYPE_ENUMERACAO, parameters = {@Parameter(name = USER_TYPE_ENUMERACAO_PARAMETRO_CLASS, value = TipoAnaliseEstudoEnum.USER_TYPE_ENUMERACAO_CLASS)})
    @Column(name = "tipo_analise", nullable = false)
    private TipoAnaliseEstudoEnum tipoAnalise;

    @NotNull
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Revisor.class)
    @JoinTable(name = "mapeamento.participante_selecao_estudo", joinColumns = @JoinColumn(name = "id_revisor"), inverseJoinColumns = @JoinColumn(name = "id_selecao_estudo"))
    private List<Revisor> participantes;

    @Column(name = "ordem", nullable = true)
    private Integer ordem;

    @Column(name = "qtd_conflitos", nullable = true)
    private Integer qtdConflitos;

    @Column(name = "qtd_selecionados", nullable = true)
    private Integer qtdSelecionados;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_revisor", nullable = false)
    private Revisor revisor;

    @NotNull
    @Column(name = "concluida", nullable = false)
    private Boolean concluida;

    @NotNull
    @Column(name = "selecao_final", nullable = false)
    private Boolean selecaoFinal;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_matriz")
    private SelecaoEstudo matriz;

    @NotNull
    @Column(name = "data_inicio")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dataInicio;

    @NotNull
    @Column(name = "data_conclusao")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dataConclusao;

    @NotNull
    @Size(max = 200)
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Size(max = 400)
    @Column(name = "observacoes", nullable = true)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_estagio_execucao", nullable = false)
    private EstagioExecucao estagioExecucao;

    /**
     * Cria uma nova instância da classe.
     */
    public SelecaoEstudo() {
        super();
    }

    /**
     * Cria uma nova instância da classe.
     * 
     * @param id O identificador da classe.
     */
    public SelecaoEstudo(Long id) {
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
     * @return Uma instância de {@link List<EtapaAnalise>} contendo o valor do atributo etapasAnalise.
     */
    public List<EtapaAnalise> getEtapasAnalise() {
        return this.etapasAnalise;
    }

    /**
     * Atualiza a instância de etapasAnalise com o valor de etapasAnalise.
     * 
     * @param etapasAnalise Uma instância de List<EtapaAnalise> contendo o valor a ser atualizado.
     */
    public void setEtapasAnalise(List<EtapaAnalise> etapasAnalise) {
        this.etapasAnalise = etapasAnalise;
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
     * Obtém o valor do atributo participantes.
     * 
     * @return Uma instância de {@link List<Revisor>} contendo o valor do atributo participantes.
     */
    public List<Revisor> getParticipantes() {
        return this.participantes;
    }

    /**
     * Atualiza a instância de participantes com o valor de participantes.
     * 
     * @param participantes Uma instância de List<Revisor> contendo o valor a ser atualizado.
     */
    public void setParticipantes(List<Revisor> participantes) {
        this.participantes = participantes;
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
     * Obtém o valor do atributo estagioExecucao.
     * 
     * @return Uma instância de {@link EstagioExecucao} contendo o valor do atributo estagioExecucao.
     */
    public EstagioExecucao getEstagioExecucao() {
        return this.estagioExecucao;
    }

    /**
     * Atualiza a instância de estagioExecucao com o valor de estagioExecucao.
     * 
     * @param estagioExecucao Uma instância de EstagioExecucao contendo o valor a ser atualizado.
     */
    public void setEstagioExecucao(EstagioExecucao estagioExecucao) {
        this.estagioExecucao = estagioExecucao;
    }

    /**
     * Obtém o valor do atributo tipoAnalise.
     * 
     * @return Uma instância de {@link TipoAnaliseEstudoEnum} contendo o valor do atributo tipoAnalise.
     */
    public TipoAnaliseEstudoEnum getTipoAnalise() {
        return this.tipoAnalise;
    }

    /**
     * Atualiza a instância de tipoAnalise com o valor de tipoAnalise.
     * 
     * @param tipoAnalise Uma instância de TipoAnaliseEstudoEnum contendo o valor a ser atualizado.
     */
    public void setTipoAnalise(TipoAnaliseEstudoEnum tipoAnalise) {
        this.tipoAnalise = tipoAnalise;
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
     * @return Uma instância de {@link SelecaoEstudo} contendo o valor do atributo matriz.
     */
    public SelecaoEstudo getMatriz() {
        return this.matriz;
    }

    /**
     * Atualiza a instância de matriz com o valor de matriz.
     * 
     * @param matriz Uma instância de SelecaoEstudo contendo o valor a ser atualizado.
     */
    public void setMatriz(SelecaoEstudo matriz) {
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
    public SelecaoEstudoBean getBean() {
        SelecaoEstudoBean bean = new SelecaoEstudoBean();

        bean.setId(this.getId());
        bean.setDataInclusao(this.getDataInclusao());
        bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

        bean.setConcluida(this.getConcluida());
        bean.setDataConclusao(this.getDataConclusao());
        bean.setDataInicio(this.getDataInicio());
        bean.setDescricao(this.getDescricao());

        if (this.estagioExecucao != null) {
            bean.setEstagioExecucao(new EstagioExecucaoBean(this.estagioExecucao.getId()));
        }

        if (this.etapasAnalise != null && !this.etapasAnalise.isEmpty()) {
            List<EtapaAnaliseBean> etapaBeans = new ArrayList<EtapaAnaliseBean>();

            for (EtapaAnalise etapa : this.etapasAnalise) {
                if (etapa != null) {
                    etapaBeans.add(etapa.getBean());
                }
            }

            bean.setEtapasAnalise(etapaBeans);
        }

        if (this.matriz != null) {
            bean.setMatriz(this.getMatriz().getBean());
        }

        bean.setObservacoes(this.getObservacoes());
        bean.setOrdem(this.getOrdem());

        if (this.participantes != null && !this.participantes.isEmpty()) {
            List<RevisorBean> partBeans = new ArrayList<RevisorBean>();

            for (Revisor participante : this.participantes) {
                if (participante != null) {
                    partBeans.add(participante.getBean());
                }
            }

            bean.setParticipantes(partBeans);
        }

        bean.setQtdConflitos(this.getQtdConflitos());
        bean.setQtdSelecionados(this.getQtdSelecionados());

        if (this.revisor != null) {
            bean.setRevisor(this.revisor.getBean());
        }

        bean.setSelecaoFinal(this.getSelecaoFinal());

        if (this.getTipoAnalise() != null) {
            bean.setTipoAnalise(tipoAnalise.getBean());
        }

        return bean;
    }

}
