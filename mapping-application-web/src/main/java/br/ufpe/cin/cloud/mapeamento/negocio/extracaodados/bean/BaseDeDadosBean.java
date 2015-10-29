/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.BaseDeDados;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Busca;

/**
 * Representa os dados de uma BasedeDados no sistema.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 12:47:33
 */
public class BaseDeDadosBean extends BaseBean implements
		Comparable<BaseDeDadosBean> {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -4274794780432798677L;

	private Long id;
	private String nome;
	private String url;
	private List<BuscaBean> buscas;

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
	 * @return Uma instância de {@link List<BuscaBean>} contendo o valor do
	 *         atributo buscas.
	 */
	public List<BuscaBean> getBuscas() {
		return this.buscas;
	}

	/**
	 * Atualiza a instância de buscas com o valor de buscas.
	 * 
	 * @param buscas
	 *            Uma instância de List<BuscaBean> contendo o valor a ser
	 *            atualizado.
	 */
	public void setBuscas(List<BuscaBean> buscas) {
		this.buscas = buscas;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public BaseDeDados getEntidade() {
		BaseDeDados entidade = new BaseDeDados();

		entidade.setId(this.getId());
		entidade.setDataInclusao(this.getDataInclusao());
		entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

		entidade.setNome(this.getNome());
		entidade.setUrl(this.getUrl());

		if (this.buscas != null) {
			List<Busca> listaBuscas = new ArrayList<Busca>();

			for (BuscaBean busca : this.buscas) {
				if (busca != null) {
					listaBuscas.add(busca.getEntidade());
				}
			}

			entidade.setBuscas(listaBuscas);
		}

		return entidade;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(BaseDeDadosBean base) {
		int retorno = 0;

		if (this != null && base == null) {
			retorno = -1;
		} else if (this == null && base != null) {
			retorno = 1;
		} else if (this != null && base != null) {
			if (StringUtils.isNotBlank(this.getNome())
					&& StringUtils.isBlank(base.getNome())) {
				retorno = -1;
			} else if (StringUtils.isBlank(this.getNome())
					&& StringUtils.isNotBlank(base.getNome())) {
				retorno = 1;
			}

			retorno = this.nome.compareToIgnoreCase(base.getNome());
		}

		return retorno;

	}

}
