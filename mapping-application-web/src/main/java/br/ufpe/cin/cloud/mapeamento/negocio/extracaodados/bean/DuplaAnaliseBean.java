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
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.DuplaAnalise;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.SelecaoEstudo;

/**
 * Representa o encapsulamento dos dados de uma dupla de análise no sistema.
 * 
 * @author helaine.lins
 * @created 30/04/2015 - 14:49:30
 */
public class DuplaAnaliseBean extends BaseBean {

    private static final long serialVersionUID = 3248206817917726711L;
    
    private Long id;
    private RevisorBean revisor1;
    private RevisorBean revisor2;
    private SelecaoEstudoBean selecaoEstudo;

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
     * Obtém o valor do atributo revisor1.
     * 
     * @return Uma instância de {@link RevisorBean} contendo o valor do atributo revisor1.
     */
    public RevisorBean getRevisor1() {
        return this.revisor1;
    }

    /**
     * Atualiza a instância de revisor1 com o valor de revisor1.
     * 
     * @param revisor1 Uma instância de RevisorBean contendo o valor a ser atualizado.
     */
    public void setRevisor1(RevisorBean revisor1) {
        this.revisor1 = revisor1;
    }

    /**
     * Obtém o valor do atributo revisor2.
     * 
     * @return Uma instância de {@link RevisorBean} contendo o valor do atributo revisor2.
     */
    public RevisorBean getRevisor2() {
        return this.revisor2;
    }

    /**
     * Atualiza a instância de revisor2 com o valor de revisor2.
     * 
     * @param revisor2 Uma instância de RevisorBean contendo o valor a ser atualizado.
     */
    public void setRevisor2(RevisorBean revisor2) {
        this.revisor2 = revisor2;
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
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
     */
    @SuppressWarnings("unchecked")
    @Override
    public DuplaAnalise getEntidade() {
        DuplaAnalise entidade = new DuplaAnalise();

        entidade.setId(this.getId());
        entidade.setDataInclusao(this.getDataInclusao());
        entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

        entidade.setRevisor1(this.getRevisor1().getEntidade());
        entidade.setRevisor2(this.getRevisor2().getEntidade());

        entidade.setSelecaoEstudo(new SelecaoEstudo(this.getSelecaoEstudo().getId()));

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
