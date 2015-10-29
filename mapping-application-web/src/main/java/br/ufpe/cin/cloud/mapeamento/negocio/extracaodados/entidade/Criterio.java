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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.CriterioBean;

/**
 * Representa um criterio de Inclusao/Exclusão de um estudo.
 * 
 * @author helaine.lins
 * @created 27/08/2014 - 19:52:13
 */
@Entity
@Table(name = "criterio", schema = "mapeamento")
@SequenceGenerator(name = "seq_criterio", sequenceName = "mapeamento.seq_criterio")
public class Criterio extends Entidade {

	private static final long serialVersionUID = 6216423394002592641L;
	
	@Id
	@GeneratedValue(generator = "seq_criterio")
	@Column(name = "id_criterio")
	private Long id;
	
	@NotNull
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@NotNull
	@Column(name="inclusao", nullable=false)
	private Boolean inclusao;
	
	/**
	 * Cria uma nova instância da classe.
	 */
	public Criterio() {
		super();
	}
	
	/**
	 * Cria uma nova instância da classe inicializando seu identificador.
	 * 
	 * @param id O identificador da classe.
	 */
	public Criterio(Long id) {
		this();
		this.id = id;
	}
	
	/**
	 * Cria uma nova instância de critério inicializando sua descrição e se o mesmo representa inclusão.
	 * 
	 * @param descricao A descrição do critério.
	 * @param inclusao O indicador de inclusão.
	 */
	public Criterio(String descricao, Boolean inclusao) {
		this();
		this.descricao = descricao;
		this.inclusao = inclusao;
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
	public CriterioBean getBean() {
		CriterioBean bean = new CriterioBean();
		
		bean.setId(this.getId());
		bean.setDataInclusao(this.getDataInclusao());
		bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());
		
		bean.setDescricao(this.getDescricao());
		bean.setInclusao(this.getInclusao());
		
		return bean;
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
		} else if (obj instanceof Criterio) {
			Criterio objeto = (Criterio) obj;
			
			if (this.getId() != null && objeto.getId() != null) {
				iguais = new EqualsBuilder().append(id, objeto.id)
						.isEquals();
			} else {
				iguais = new EqualsBuilder().append(descricao, objeto.descricao)
						.isEquals();
			}
		}

		return iguais;
	}
	
	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#toString()
	 */
	@Override
	public String toString() {
		return "Criterio:" + this.getId() + "-" + this.getDescricao();
	}
}
