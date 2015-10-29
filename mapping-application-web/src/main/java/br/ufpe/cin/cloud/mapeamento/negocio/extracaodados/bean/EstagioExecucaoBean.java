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

import org.joda.time.LocalDateTime;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EstagioExecucao;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EstudoSecundario;

/**
 * Representa o encapsulamento dos dados de um estágio de execução da revisão.
 * 
 * @author helaine.lins
 * @created 28/04/2015 - 17:50:56
 */
public class EstagioExecucaoBean extends BaseBean {

    private static final long serialVersionUID = 7682179982855925027L;

    private Long id;
    private List<BuscaBean> buscas;
    private List<SelecaoEstudoBean> selecaoEstudos;
    private EstudoSecundarioBean estudoSecundario;
    private LocalDateTime dataInicio;
    private LocalDateTime dataConclusao;
    private String observacoes;

    /**
     * Cria uma nova instância da classe.
     */
    public EstagioExecucaoBean() {
        super();
    }
    
    /**
     * Cria uma nova instância da classe inicializando seu identificador.
     * 
     * @param id O identificador do estágio de execução.
     */
    public EstagioExecucaoBean(Long id) {
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
     * @return Uma instância de {@link List<BuscaBean>} contendo o valor do atributo buscas.
     */
    public List<BuscaBean> getBuscas() {
        return this.buscas;
    }

    /**
     * Atualiza a instância de buscas com o valor de buscas.
     * 
     * @param buscas Uma instância de List<BuscaBean> contendo o valor a ser atualizado.
     */
    public void setBuscas(List<BuscaBean> buscas) {
        this.buscas = buscas;
    }

    /**
     * Obtém o valor do atributo selecaoEstudos.
     * 
     * @return Uma instância de {@link List<SelecaoEstudoBean>} contendo o valor do atributo selecaoEstudos.
     */
    public List<SelecaoEstudoBean> getSelecaoEstudos() {
        return this.selecaoEstudos;
    }

    /**
     * Atualiza a instância de selecaoEstudos com o valor de selecaoEstudos.
     * 
     * @param selecaoEstudos Uma instância de List<SelecaoEstudoBean> contendo o valor a ser atualizado.
     */
    public void setSelecaoEstudos(List<SelecaoEstudoBean> selecaoEstudos) {
        this.selecaoEstudos = selecaoEstudos;
    }

    /**
     * Obtém o valor do atributo estudoSecundario.
     * 
     * @return Uma instância de {@link EstudoSecundarioBean} contendo o valor do atributo estudoSecundario.
     */
    public EstudoSecundarioBean getEstudoSecundario() {
        return this.estudoSecundario;
    }

    /**
     * Atualiza a instância de estudoSecundario com o valor de estudoSecundario.
     * 
     * @param estudoSecundario Uma instância de EstudoSecundarioBean contendo o valor a ser atualizado.
     */
    public void setEstudoSecundario(EstudoSecundarioBean estudoSecundario) {
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
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
     */
    @SuppressWarnings("unchecked")
    @Override
    public EstagioExecucao getEntidade() {
        EstagioExecucao entidade = new EstagioExecucao();

        entidade.setId(this.id);
        entidade.setDataInclusao(this.getDataInclusao());
        entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());
        entidade.setDataConclusao(this.getDataConclusao());
        entidade.setDataInclusao(this.getDataInclusao());
        entidade.setObservacoes(this.getObservacoes());

        if (this.estudoSecundario != null) {
            entidade.setEstudoSecundario(new EstudoSecundario(this.getEstudoSecundario().getId()));
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
