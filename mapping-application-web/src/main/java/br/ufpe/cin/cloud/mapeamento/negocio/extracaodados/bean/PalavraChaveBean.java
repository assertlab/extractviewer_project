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
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;

/**
 * Representa o agrupamento dos dados de uma palavra-chave de estudo registrado
 * no sistema.
 * 
 * @author helaine.lins
 * @created 12/11/2014 - 12:46:19
 */
public class PalavraChaveBean extends BaseBean {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 7746020762450886247L;

	private String palavra;
	private Integer quantidade;

	/**
	 * Obtém o valor do atributo palavra.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         palavra.
	 */
	public String getPalavra() {
		return this.palavra;
	}

	/**
	 * Atualiza a instância de palavra com o valor de palavra.
	 * 
	 * @param palavra
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	/**
	 * Obtém o valor do atributo quantidade.
	 * 
	 * @return Uma instância de {@link Integer} contendo o valor do atributo
	 *         quantidade.
	 */
	public Integer getQuantidade() {
		return this.quantidade;
	}

	/**
	 * Atualiza a instância de quantidade com o valor de quantidade.
	 * 
	 * @param quantidade
	 *            Uma instância de Integer contendo o valor a ser atualizado.
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
	 */
	@Override
	public <E extends Entidade> E getEntidade() {
		return null;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getBeanID()
	 */
	@Override
	public Long getBeanID() {
		return null;
	}

}
