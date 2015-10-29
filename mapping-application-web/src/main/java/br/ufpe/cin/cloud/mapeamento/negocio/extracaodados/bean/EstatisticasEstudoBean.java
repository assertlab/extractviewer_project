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
 * Representa as estatísticas de quantitativos de estudos.
 * 
 * @author helaine.lins
 * @created 14/11/2014 - 20:07:17
 */
public class EstatisticasEstudoBean implements Serializable {

	private static final long serialVersionUID = -67243897057956761L;
	private Integer ano;
	private Long qtd;

	/**
	 * Obtém o valor do atributo ano.
	 * 
	 * @return Uma instância de {@link Integer} contendo o valor do atributo
	 *         ano.
	 */
	public Integer getAno() {
		return this.ano;
	}

	/**
	 * Atualiza a instância de ano com o valor de ano.
	 * 
	 * @param ano
	 *            Uma instância de Integer contendo o valor a ser atualizado.
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
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
