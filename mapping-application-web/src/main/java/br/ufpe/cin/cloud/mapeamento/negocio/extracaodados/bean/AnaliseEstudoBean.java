/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.AnaliseEstudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Criterio;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EtapaAnalise;

/**
 * Representa o encapsulamento dos dados de uma analise de estudo.
 * 
 * @author helaine.lins
 * @created 27/08/2014 - 20:00:44
 */
public class AnaliseEstudoBean extends BaseBean {

    private static final long serialVersionUID = 7905905268154999549L;

    private Long id;
    private EstudoBean estudo;
    private RevisorBean revisor;
    private Boolean incluido;
    private CriterioBean criterio;
    private String comentario;
    private AnaliseEstudoBean matriz;
    private Boolean concluido;
    private EtapaAnaliseBean etapa;
    private Boolean duplicado;
    private Boolean existeConflito;
    private CriterioBean criterioAnterior;

    /**
     * Cria uma nova instância da classe inicializando os instâncias dependentes.
     */
    public AnaliseEstudoBean() {
        this.estudo = new EstudoBean();
        this.criterio = new CriterioBean();
        this.etapa = new EtapaAnaliseBean();
        this.criterioAnterior = new CriterioBean();
    }

    /**
     * Cria uma nova instância da classe inicializando os instâncias dependentes.
     */
    public AnaliseEstudoBean(EtapaAnaliseBean etapa) {
        this();
        this.etapa = etapa;
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
     * @return Uma instância de {@link EstudoBean} contendo o valor do atributo estudo.
     */
    public EstudoBean getEstudo() {
        return this.estudo;
    }

    /**
     * Atualiza a instância de estudo com o valor de estudo.
     * 
     * @param estudo Uma instância de EstudoBean contendo o valor a ser atualizado.
     */
    public void setEstudo(EstudoBean estudo) {
        this.estudo = estudo;
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
     * @return Uma instância de {@link CriterioBean} contendo o valor do atributo criterio.
     */
    public CriterioBean getCriterio() {
        return this.criterio;
    }

    /**
     * Atualiza a instância de criterio com o valor de criterio.
     * 
     * @param criterio Uma instância de CriterioBean contendo o valor a ser atualizado.
     */
    public void setCriterio(CriterioBean criterio) {
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
     * @return Uma instância de {@link AnaliseEstudoBean} contendo o valor do atributo matriz.
     */
    public AnaliseEstudoBean getMatriz() {
        return this.matriz;
    }

    /**
     * Atualiza a instância de matriz com o valor de matriz.
     * 
     * @param matriz Uma instância de AnaliseEstudoBean contendo o valor a ser atualizado.
     */
    public void setMatriz(AnaliseEstudoBean matriz) {
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
     * Obtém o valor do atributo etapa.
     * 
     * @return Uma instância de {@link EtapaAnaliseBean} contendo o valor do atributo etapa.
     */
    public EtapaAnaliseBean getEtapa() {
        return this.etapa;
    }

    /**
     * Atualiza a instância de etapa com o valor de etapa.
     * 
     * @param etapa Uma instância de EtapaAnaliseBean contendo o valor a ser atualizado.
     */
    public void setEtapa(EtapaAnaliseBean etapa) {
        this.etapa = etapa;
    }

    /**
     * Obtém o valor do atributo duplicado.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo duplicado.
     */
    public Boolean getDuplicado() {
        return this.duplicado;
    }

    /**
     * Atualiza a instância de duplicado com o valor de duplicado.
     * 
     * @param duplicado Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setDuplicado(Boolean duplicado) {
        this.duplicado = duplicado;
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
     * @param existeConflito Uma instância de boolean contendo o valor a ser atualizado.
     */
    public void setExisteConflito(Boolean existeConflito) {
        this.existeConflito = existeConflito;
    }

    /**
     * Obtém o valor do atributo criterioAnterior.
     * 
     * @return Uma instância de {@link CriterioBean} contendo o valor do atributo criterioAnterior.
     */
    public CriterioBean getCriterioAnterior() {
        return this.criterioAnterior;
    }

    /**
     * Atualiza a instância de criterioAnterior com o valor de criterioAnterior.
     * 
     * @param criterioAnterior Uma instância de CriterioBean contendo o valor a ser atualizado.
     */
    public void setCriterioAnterior(CriterioBean criterioAnterior) {
        this.criterioAnterior = criterioAnterior;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
     */
    @SuppressWarnings("unchecked")
    @Override
    public AnaliseEstudo getEntidade() {
        AnaliseEstudo entidade = new AnaliseEstudo();

        entidade.setId(this.getId());
        entidade.setDataInclusao(this.getDataInclusao());
        entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

        entidade.setComentario(this.getComentario());
        entidade.setIncluido(this.getIncluido());
        entidade.setConcluido(this.getConcluido());

        if (this.getEstudo() != null) {
            entidade.setEstudo(new Estudo(this.getEstudo().getId()));
        }

        if (this.getCriterio() != null) {
            entidade.setCriterio(new Criterio(this.getCriterio().getId()));
        }

        if (this.getRevisor() != null) {
            entidade.setRevisor(this.getRevisor().getEntidade());
        }

        if (this.matriz != null) {
            entidade.setMatriz(new AnaliseEstudo(this.matriz.getId()));
        }

        if (this.etapa != null) {
            EtapaAnalise etapa = new EtapaAnalise(this.etapa.getId());
            etapa.setDescricao(this.getEtapa().getDescricao());

            entidade.setEtapa(etapa);
        }

        if (this.getCriterioAnterior() != null) {
            entidade.setCriterioAnterior(new Criterio(this.getCriterioAnterior().getId()));
        }
        
        entidade.setExisteConflito(this.existeConflito);
        
        return entidade;
    }

}
