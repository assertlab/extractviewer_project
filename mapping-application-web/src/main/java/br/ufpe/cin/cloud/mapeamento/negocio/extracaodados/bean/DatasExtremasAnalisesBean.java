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

import org.joda.time.LocalDateTime;

/**
 * Representa as datas e horas extremas de início e fim de trabalho de análises
 * em um determinado dia.
 * 
 * @author helaine.lins
 * @created 12/11/2014 - 14:44:34
 */
public class DatasExtremasAnalisesBean implements Serializable {

	private static final long serialVersionUID = 5829780788716773632L;

	private Long idEtapa;
	private LocalDateTime dia;
	private LocalDateTime menor;
	private LocalDateTime maior;

	/**
	 * Obtém o valor do atributo idEtapa.
	 * 
	 * @return Uma instância de {@link Long} contendo o valor do atributo
	 *         idEtapa.
	 */
	public Long getIdEtapa() {
		return this.idEtapa;
	}

	/**
	 * Atualiza a instância de idEtapa com o valor de idEtapa.
	 * 
	 * @param idEtapa
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setIdEtapa(Long idEtapa) {
		this.idEtapa = idEtapa;
	}

	/**
	 * Obtém o valor do atributo dia.
	 * 
	 * @return Uma instância de {@link LocalDateTime} contendo o valor do
	 *         atributo dia.
	 */
	public LocalDateTime getDia() {
		return this.dia;
	}

	/**
	 * Atualiza a instância de dia com o valor de dia.
	 * 
	 * @param dia
	 *            Uma instância de LocalDateTime contendo o valor a ser
	 *            atualizado.
	 */
	public void setDia(LocalDateTime dia) {
		this.dia = dia;
	}

	/**
	 * Obtém o valor do atributo menor.
	 * 
	 * @return Uma instância de {@link LocalDateTime} contendo o valor do
	 *         atributo menor.
	 */
	public LocalDateTime getMenor() {
		return this.menor;
	}

	/**
	 * Atualiza a instância de menor com o valor de menor.
	 * 
	 * @param menor
	 *            Uma instância de LocalDateTime contendo o valor a ser
	 *            atualizado.
	 */
	public void setMenor(LocalDateTime menor) {
		this.menor = menor;
	}

	/**
	 * Obtém o valor do atributo maior.
	 * 
	 * @return Uma instância de {@link LocalDateTime} contendo o valor do
	 *         atributo maior.
	 */
	public LocalDateTime getMaior() {
		return this.maior;
	}

	/**
	 * Atualiza a instância de maior com o valor de maior.
	 * 
	 * @param maior
	 *            Uma instância de LocalDateTime contendo o valor a ser
	 *            atualizado.
	 */
	public void setMaior(LocalDateTime maior) {
		this.maior = maior;
	}

}
