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
import java.util.List;

/**
 * Representa o encapsulamento dos dados para a criação do relatório de esforço
 * na execução da etapa de revisão.
 * 
 * @author helaine.lins
 * @created 03/09/2014 - 18:12:29
 */
public class EstatisticasEsforcoEtapaRevisaoBean implements Serializable {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -5235111073504666666L;

	private List<EstatisticaEsforcoBean> detalheEsforcos;

	/**
	 * Obtém o valor do atributo detalheEsforcos.
	 * 
	 * @return Uma instância de {@link List<EstatisticaEsforcoBean>} contendo o
	 *         valor do atributo detalheEsforcos.
	 */
	public List<EstatisticaEsforcoBean> getDetalheEsforcos() {
		return this.detalheEsforcos;
	}

	/**
	 * Atualiza a instância de detalheEsforcos com o valor de detalheEsforcos.
	 * 
	 * @param detalheEsforcos
	 *            Uma instância de List<EstatisticaEsforcoBean> contendo o valor
	 *            a ser atualizado.
	 */
	public void setDetalheEsforcos(List<EstatisticaEsforcoBean> detalheEsforcos) {
		this.detalheEsforcos = detalheEsforcos;
	}

}
