/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import org.apache.commons.lang.builder.EqualsBuilder;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Criterio;

/**
 * Representa o encapsulamento dos dados de um critério de inclusao/exclusao.
 * 
 * @author helaine.lins
 * @created 27/08/2014 - 19:54:04
 */
public class CriterioBean extends BaseBean {

	private static final long serialVersionUID = -4443373330902685168L;
	private Long id;
	private String descricao;
	private Boolean inclusao;
	
	/**
	 * Cria uma nova instância da classe. 
	 */
	public CriterioBean() {
		super();
	}
	
	/**
	 * Cria uma nova instância da classe inicializando o seu identificador.
	 */
	public CriterioBean(Long id) {
		this();
		this.id = id;
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
	 * Obtém o valor do atributo inclusao.
	 * 
	 * @return Uma instância de {@link Boolean} contendo o valor do atributo inclusao.
	 */
	public Boolean getInclusao() {
		return this.inclusao;
	}
	/**
	 * Atualiza a instância de inclusao com o valor de inclusao.
	 *
	 * @param inclusao Uma instância de Boolean contendo o valor a ser atualizado.
	 */
	public void setInclusao(Boolean inclusao) {
		this.inclusao = inclusao;
	}
	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Criterio getEntidade() {
		Criterio entidade = new Criterio();
		
		entidade.setId(this.getId());
		entidade.setDataInclusao(this.getDataInclusao());
		entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());
		
		entidade.setDescricao(this.getDescricao());
		entidade.setInclusao(this.getInclusao());
		
		return entidade;
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
		} else if (obj instanceof CriterioBean) {
			CriterioBean objeto = (CriterioBean) obj;
			iguais = new EqualsBuilder().append(descricao, objeto.descricao)
					.isEquals();
		}

		return iguais;
	}
	
}
