/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.DuplaAnaliseBean;

/**
 * Representa um criterio de Inclusao/Exclusão de um estudo.
 * 
 * @author helaine.lins
 * @created 27/08/2014 - 19:52:13
 */
@Entity
@Table(name = "dupla_analise", schema = "mapeamento")
@SequenceGenerator(name = "seq_dupla_analise", sequenceName = "mapeamento.seq_dupla_analise")
public class DuplaAnalise extends Entidade {

	private static final long serialVersionUID = 6216423394002333641L;
	
	@Id
	@GeneratedValue(generator = "seq_dupla_analise")
	@Column(name = "id_dupla_analise")
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_revisor_1", nullable = false)
	private Revisor revisor1;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_revisor_2", nullable = false)
	private Revisor revisor2;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_selecao_estudo", nullable = false)
	private SelecaoEstudo selecaoEstudo;

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
     * @return Uma instância de {@link Revisor} contendo o valor do atributo revisor1.
     */
    public Revisor getRevisor1() {
        return this.revisor1;
    }

    /**
     * Atualiza a instância de revisor1 com o valor de revisor1.
     *
     * @param revisor1 Uma instância de Revisor contendo o valor a ser atualizado.
     */
    public void setRevisor1(Revisor revisor1) {
        this.revisor1 = revisor1;
    }

    /**
     * Obtém o valor do atributo revisor2.
     * 
     * @return Uma instância de {@link Revisor} contendo o valor do atributo revisor2.
     */
    public Revisor getRevisor2() {
        return this.revisor2;
    }

    /**
     * Atualiza a instância de revisor2 com o valor de revisor2.
     *
     * @param revisor2 Uma instância de Revisor contendo o valor a ser atualizado.
     */
    public void setRevisor2(Revisor revisor2) {
        this.revisor2 = revisor2;
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
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#getBean()
     */
    @SuppressWarnings("unchecked")
    @Override
    public DuplaAnaliseBean getBean() {
        DuplaAnaliseBean bean = new DuplaAnaliseBean();

        bean.setId(this.getId());
        bean.setDataInclusao(this.getDataInclusao());
        bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

        bean.setRevisor1(this.getRevisor1().getBean());
        bean.setRevisor2(this.getRevisor2().getBean());

        bean.setSelecaoEstudo(this.getSelecaoEstudo().getBean());

        return bean;
    }
	
	
}
