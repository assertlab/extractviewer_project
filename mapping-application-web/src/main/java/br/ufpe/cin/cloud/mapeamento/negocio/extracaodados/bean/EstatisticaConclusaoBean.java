/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import java.io.Serializable;

/**
 * Representa o encapsulamento da quantidade de estudos classificados em um
 * determinado criterio.
 * 
 * @author helaine.lins
 * @created 03/09/2014 - 18:16:02
 */
public class EstatisticaConclusaoBean implements Serializable {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -8739881539310383480L;
	private String descricao;
	private Long qtd;

	/**
	 * Obtém o valor do atributo descricao.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         descricao.
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Atualiza a instância de descricao com o valor de descricao.
	 * 
	 * @param descricao
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Obtém o valor do atributo qtd.
	 * 
	 * @return Uma instância de {@link Long} contendo o valor do atributo qtd.
	 */
	public Long getQtd() {
		return this.qtd;
	}

	/**
	 * Atualiza a instância de qtd com o valor de qtd.
	 * 
	 * @param qtd
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}

}
