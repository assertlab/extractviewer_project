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

import org.joda.time.LocalDate;

/**
 * Representa a estatistica de velocidade de análises concluídas em um
 * determinado dia.
 * 
 * @author helaine.lins
 * @created 12/11/2014 - 15:45:35
 */
public class VelocidadeDiaBean implements Serializable {
	
	private static final long serialVersionUID = -5197034501694391629L;

	private LocalDate data;
	private Long concluidos;
	private Double velocidade;

	/**
	 * Obtém o valor do atributo data.
	 * 
	 * @return Uma instância de {@link LocalDate} contendo o valor do atributo
	 *         data.
	 */
	public LocalDate getData() {
		return this.data;
	}

	/**
	 * Atualiza a instância de data com o valor de data.
	 * 
	 * @param data
	 *            Uma instância de LocalDate contendo o valor a ser atualizado.
	 */
	public void setData(LocalDate data) {
		this.data = data;
	}

	/**
	 * Obtém o valor do atributo concluidos.
	 * 
	 * @return Uma instância de {@link Long} contendo o valor do atributo
	 *         concluidos.
	 */
	public Long getConcluidos() {
		return this.concluidos;
	}

	/**
	 * Atualiza a instância de concluidos com o valor de concluidos.
	 * 
	 * @param concluidos
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setConcluidos(Long concluidos) {
		this.concluidos = concluidos;
	}

	/**
	 * Obtém o valor do atributo velocidade.
	 * 
	 * @return Uma instância de {@link Double} contendo o valor do atributo
	 *         velocidade.
	 */
	public Double getVelocidade() {
		return this.velocidade;
	}

	/**
	 * Atualiza a instância de velocidade com o valor de velocidade.
	 * 
	 * @param velocidade
	 *            Uma instância de Double contendo o valor a ser atualizado.
	 */
	public void setVelocidade(Double velocidade) {
		this.velocidade = velocidade;
	}

}
