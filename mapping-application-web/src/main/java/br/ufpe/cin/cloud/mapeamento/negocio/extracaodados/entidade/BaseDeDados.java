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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BaseDeDadosBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean;

/**
 * Representa o encapsulamento dos dados de uma base de dados no sistema.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 13:02:57
 */
@Entity
@Table(name = "base_dados", schema = "mapeamento")
@SequenceGenerator(name = "seq_basedados", sequenceName = "mapeamento.seq_basedados")
public class BaseDeDados extends Entidade {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 6754306349112610022L;

	@Id
    @GeneratedValue(generator = "seq_basedados")
	private Long id;
	
	@NotNull
	@Length(max = 100)
    @Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "url", nullable = true)
	private String url;

	@OneToMany(mappedBy = "base")
	private List<Busca> buscas;

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
	 * @param id
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtém o valor do atributo nome.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         nome.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Atualiza a instância de nome com o valor de nome.
	 * 
	 * @param nome
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Obtém o valor do atributo url.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo url.
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Atualiza a instância de url com o valor de url.
	 * 
	 * @param url
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Obtém o valor do atributo buscas.
	 * 
	 * @return Uma instância de {@link List<Busca>} contendo o valor do atributo
	 *         buscas.
	 */
	public List<Busca> getBuscas() {
		return this.buscas;
	}

	/**
	 * Atualiza a instância de buscas com o valor de buscas.
	 * 
	 * @param buscas
	 *            Uma instância de List<Busca> contendo o valor a ser
	 *            atualizado.
	 */
	public void setBuscas(List<Busca> buscas) {
		this.buscas = buscas;
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
	public BaseDeDadosBean getBean() {
		BaseDeDadosBean bean = new BaseDeDadosBean();

		bean.setId(this.getId());
		bean.setDataInclusao(this.getDataInclusao());
		bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

		bean.setNome(this.getNome());
		bean.setUrl(this.getUrl());

		if (this.buscas != null) {
			List<BuscaBean> buscaBeans = new ArrayList<BuscaBean>();

			for (Busca busca : buscas) {
				if (busca != null) {
					buscaBeans.add(busca.getBean());
				}
			}

			bean.setBuscas(buscaBeans);
		}

		return bean;
	}

}
