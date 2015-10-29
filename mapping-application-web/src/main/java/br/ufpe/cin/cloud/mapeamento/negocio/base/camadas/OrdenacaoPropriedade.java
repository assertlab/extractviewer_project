/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.camadas;

import java.io.Serializable;

/**
 * Representa o encapsulamento dos dados para aplicação de ordenação de consultas.
 * 
 * @author helaine.lins
 * @created 05/09/2014 - 17:18:15
 */
public class OrdenacaoPropriedade implements Serializable {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 1334691178375821269L;

	private String propriedade;
	private boolean descendente;

	
	/**
	 * Cria uma nova instância da classe.
	 */
	public OrdenacaoPropriedade(String propriedade, boolean descendente) {
		this.propriedade = propriedade;
		this.descendente = descendente;
	}
	
	/**
	 * Obtém o valor do atributo propriedade.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         propriedade.
	 */
	public String getPropriedade() {
		return this.propriedade;
	}

	/**
	 * Atualiza a instância de propriedade com o valor de propriedade.
	 * 
	 * @param propriedade
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}

	/**
	 * Obtém o valor do atributo descendente.
	 * 
	 * @return Uma instância de {@link boolean} contendo o valor do atributo
	 *         descendente.
	 */
	public boolean isDescendente() {
		return this.descendente;
	}

	/**
	 * Atualiza a instância de descendente com o valor de descendente.
	 * 
	 * @param descendente
	 *            Uma instância de boolean contendo o valor a ser atualizado.
	 */
	public void setDescendente(boolean descendente) {
		this.descendente = descendente;
	}

}
