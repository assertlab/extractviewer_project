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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.joda.time.LocalDate;

/**
 * Representa o encapsulamento da quantidade de esforços de análises em uma
 * determinada etapa de revisão.
 * 
 * @author helaine.lins
 * @created 03/09/2014 - 18:16:02
 */
public class EstatisticaEsforcoBean implements Serializable,
		Comparable<EstatisticaEsforcoBean> {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -8733331539310383480L;

	private LocalDate data;
	private Long qtdPlanejado;
	private Long qtdRealizado;
	private boolean marcador;

	/**
	 * Cria uma instância padrão da classe.
	 */
	public EstatisticaEsforcoBean() {
		super();
	}

	/**
	 * Cria uma instância da classe inicializando os valores de coleta.
	 * 
	 * @param data
	 *            A data da coleta
	 * @param qtdPlanejado
	 *            A quantidade de estudos a serem analisados no dia.
	 * @param qtdRealizado
	 *            A quantidade de estudos que de fato foram analisados no dia.
	 */
	public EstatisticaEsforcoBean(LocalDate data, Long qtdPlanejado,
			Long qtdRealizado) {
		this();
		this.data = data;
		this.qtdPlanejado = qtdPlanejado;
		this.qtdRealizado = qtdRealizado;
		this.marcador = false;
	}

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
	 * Obtém o valor do atributo qtdPlanejado.
	 * 
	 * @return Uma instância de {@link Long} contendo o valor do atributo
	 *         qtdPlanejado.
	 */
	public Long getQtdPlanejado() {
		return this.qtdPlanejado;
	}

	/**
	 * Atualiza a instância de qtdPlanejado com o valor de qtdPlanejado.
	 * 
	 * @param qtdPlanejado
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setQtdPlanejado(Long qtdPlanejado) {
		this.qtdPlanejado = qtdPlanejado;
	}

	/**
	 * Obtém o valor do atributo qtdRealizado.
	 * 
	 * @return Uma instância de {@link Long} contendo o valor do atributo
	 *         qtdRealizado.
	 */
	public Long getQtdRealizado() {
		return this.qtdRealizado;
	}

	/**
	 * Atualiza a instância de qtdRealizado com o valor de qtdRealizado.
	 * 
	 * @param qtdRealizado
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setQtdRealizado(Long qtdRealizado) {
		this.qtdRealizado = qtdRealizado;
	}

	
	
	/**
	 * Obtém o valor do atributo marcador.
	 * 
	 * @return Uma instância de {@link boolean} contendo o valor do atributo marcador.
	 */
	public boolean isMarcador() {
		return this.marcador;
	}

	/**
	 * Atualiza a instância de marcador com o valor de marcador.
	 *
	 * @param marcador Uma instância de boolean contendo o valor a ser atualizado.
	 */
	public void setMarcador(boolean marcador) {
		this.marcador = marcador;
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
		} else if (obj instanceof EstatisticaEsforcoBean) {
			EstatisticaEsforcoBean objeto = (EstatisticaEsforcoBean) obj;

			if (this.data == null && objeto.getData() == null) {
				iguais = true;
			} else if (this.data != null && objeto.getData() != null) {
				iguais = new EqualsBuilder().append(data, objeto.data)
						.isEquals();
			}
		}

		return iguais;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(EstatisticaEsforcoBean objeto) {
		int comp = -1;

		if (this.data == null && objeto.getData() != null) {
			comp = 1;
		} else 	if (this.data != null && objeto.getData() != null) {
			comp = this.data.compareTo(objeto.getData());
		}

		return comp;
	}
}
