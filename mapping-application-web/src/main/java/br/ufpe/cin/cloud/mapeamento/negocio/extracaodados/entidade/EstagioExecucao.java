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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstagioExecucaoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.SelecaoEstudoBean;

/**
 * Representa o estágio de execução de um estudo secundário.
 * 
 * @author helaine.lins
 * @created 28/04/2015 - 13:42:19
 */
@Entity
@Table(name = "estagio_execucao", schema = "mapeamento")
@SequenceGenerator(name = "seq_estagio_execucao", sequenceName = "mapeamento.seq_estagio_execucao")
public class EstagioExecucao extends EstagioRevisao {

    private static final long serialVersionUID = -1742334533297575859L;

    @Id
    @GeneratedValue(generator = "seq_estagio_execucao")
    @Column(name = "id_estagio_execucao")
    private Long id;

    @OneToMany(mappedBy = "estagioExecucao")
    private List<Busca> buscas;

    @OneToMany(mappedBy = "estagioExecucao", fetch = FetchType.LAZY)
    private List<SelecaoEstudo> selecaoEstudos;

    @OneToOne(mappedBy = "execucao")
    private EstudoSecundario estudoSecundario;

    //TODO: proxima fase
    //private List<AvaliacaoQualidade> estudosSelecionados;
    //private List<ExtracaoDados> extracoesDadosEstudos;
    //private List<SinteseDados> sinteseDadosEstudos;

    @NotNull
    @Column(name = "data_inicio", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dataInicio;

    @NotNull
    @Column(name = "data_conclusao", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dataConclusao;

    @Size(max = 400)
    @Column(name = "observacoes", nullable = true)
    private String observacoes;

    /**
     * Cria uma nova instância da classe;
     */
    public EstagioExecucao() {
        super();
    }

    /**
     * Cria uma nova instância da classe inicializando o identificador;
     * 
     * @param id O identificador da classe.
     */
    public EstagioExecucao(Long id) {
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
     * Obtém o valor do atributo buscas.
     * 
     * @return Uma instância de {@link List<Busca>} contendo o valor do atributo buscas.
     */
    public List<Busca> getBuscas() {
        return this.buscas;
    }

    /**
     * Atualiza a instância de buscas com o valor de buscas.
     * 
     * @param buscas Uma instância de List<Busca> contendo o valor a ser atualizado.
     */
    public void setBuscas(List<Busca> buscas) {
        this.buscas = buscas;
    }

    /**
     * Obtém o valor do atributo selecaoEstudos.
     * 
     * @return Uma instância de {@link List<SelecaoEstudo>} contendo o valor do atributo selecaoEstudos.
     */
    public List<SelecaoEstudo> getSelecaoEstudos() {
        return this.selecaoEstudos;
    }

    /**
     * Atualiza a instância de selecaoEstudos com o valor de selecaoEstudos.
     * 
     * @param selecaoEstudos Uma instância de List<SelecaoEstudo> contendo o valor a ser atualizado.
     */
    public void setSelecaoEstudos(List<SelecaoEstudo> selecaoEstudos) {
        this.selecaoEstudos = selecaoEstudos;
    }

    /**
     * Obtém o valor do atributo estudoSecundario.
     * 
     * @return Uma instância de {@link EstudoSecundario} contendo o valor do atributo estudoSecundario.
     */
    public EstudoSecundario getEstudoSecundario() {
        return this.estudoSecundario;
    }

    /**
     * Atualiza a instância de estudoSecundario com o valor de estudoSecundario.
     * 
     * @param estudoSecundario Uma instância de EstudoSecundario contendo o valor a ser atualizado.
     */
    public void setEstudoSecundario(EstudoSecundario estudoSecundario) {
        this.estudoSecundario = estudoSecundario;
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
    public EstagioExecucaoBean getBean() {
        EstagioExecucaoBean bean = new EstagioExecucaoBean();

        bean.setId(this.getId());
        bean.setDataInclusao(this.getDataInclusao());
        bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

        if (this.buscas != null && !this.buscas.isEmpty()) {
            List<BuscaBean> buscas = new ArrayList<BuscaBean>();

            for (Busca busca : this.buscas) {
                if (busca != null) {
                    buscas.add(busca.getBean());
                }
            }

            bean.setBuscas(buscas);
        }

        bean.setDataConclusao(this.getDataConclusao());
        bean.setDataInicio(this.getDataInicio());

//        if (this.estudoSecundario != null) {
//            bean.setEstudoSecundario(this.estudoSecundario.getBean());
//        }

        bean.setObservacoes(this.getObservacoes());

        if (this.selecaoEstudos != null && !this.selecaoEstudos.isEmpty()) {
            List<SelecaoEstudoBean> beans = new ArrayList<SelecaoEstudoBean>();

            for (SelecaoEstudo selEst : this.selecaoEstudos) {
                if (selEst != null) {
                    beans.add(selEst.getBean());
                }

                bean.setSelecaoEstudos(beans);
            }

        }

        return bean;
    }

}
