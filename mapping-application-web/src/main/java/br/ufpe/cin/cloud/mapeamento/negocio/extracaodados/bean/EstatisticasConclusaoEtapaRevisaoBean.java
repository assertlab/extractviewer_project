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
 * Representa o encapsulamento dos dados para a criação do relatório de
 * conclusão da estapa de revisão.
 * 
 * @author helaine.lins
 * @created 03/09/2014 - 18:12:29
 */
public class EstatisticasConclusaoEtapaRevisaoBean implements Serializable {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -5235111073504967998L;

	private Integer qtdTotal;
	private Integer qtdConcluidos;
	private Integer qtdNaoConcluidos;
	private Integer qtdIncluidos;
	private Integer qtdExcluidos;
	private Integer qtdDuplicados;
	private List<EstatisticaConclusaoBean> detalheCriterios;

	/**
	 * Obtém o valor do atributo qtdTotal.
	 * 
	 * @return Uma instância de {@link Integer} contendo o valor do atributo
	 *         qtdTotal.
	 */
	public Integer getQtdTotal() {
		return this.qtdTotal;
	}

	/**
	 * Atualiza a instância de qtdTotal com o valor de qtdTotal.
	 * 
	 * @param qtdTotal
	 *            Uma instância de Integer contendo o valor a ser atualizado.
	 */
	public void setQtdTotal(Integer qtdTotal) {
		this.qtdTotal = qtdTotal;
	}

	/**
	 * Obtém o valor do atributo qtdIncluidos.
	 * 
	 * @return Uma instância de {@link Integer} contendo o valor do atributo
	 *         qtdIncluidos.
	 */
	public Integer getQtdIncluidos() {
		return this.qtdIncluidos;
	}

	/**
	 * Atualiza a instância de qtdIncluidos com o valor de qtdIncluidos.
	 * 
	 * @param qtdIncluidos
	 *            Uma instância de Integer contendo o valor a ser atualizado.
	 */
	public void setQtdIncluidos(Integer qtdIncluidos) {
		this.qtdIncluidos = qtdIncluidos;
	}

	/**
	 * Obtém o valor do atributo qtdExcluidos.
	 * 
	 * @return Uma instância de {@link Integer} contendo o valor do atributo
	 *         qtdExcluidos.
	 */
	public Integer getQtdExcluidos() {
		return this.qtdExcluidos;
	}

	/**
	 * Atualiza a instância de qtdExcluidos com o valor de qtdExcluidos.
	 * 
	 * @param qtdExcluidos
	 *            Uma instância de Integer contendo o valor a ser atualizado.
	 */
	public void setQtdExcluidos(Integer qtdExcluidos) {
		this.qtdExcluidos = qtdExcluidos;
	}

	/**
	 * Obtém o valor do atributo detalheCriterios.
	 * 
	 * @return Uma instância de {@link List<EstatisticaConclusaoBean>} contendo o
	 *         valor do atributo detalheCriterios.
	 */
	public List<EstatisticaConclusaoBean> getDetalheCriterios() {
		return this.detalheCriterios;
	}

	/**
	 * Atualiza a instância de detalheCriterios com o valor de detalheCriterios.
	 * 
	 * @param detalheCriterios
	 *            Uma instância de List<EstatisticaConclusaoBean> contendo o
	 *            valor a ser atualizado.
	 */
	public void setDetalheCriterios(
			List<EstatisticaConclusaoBean> detalheCriterios) {
		this.detalheCriterios = detalheCriterios;
	}

	/**
	 * Obtém o valor do atributo qtdConcluidos.
	 * 
	 * @return Uma instância de {@link Integer} contendo o valor do atributo
	 *         qtdConcluidos.
	 */
	public Integer getQtdConcluidos() {
		return this.qtdConcluidos;
	}

	/**
	 * Atualiza a instância de qtdConcluidos com o valor de qtdConcluidos.
	 * 
	 * @param qtdConcluidos
	 *            Uma instância de Integer contendo o valor a ser atualizado.
	 */
	public void setQtdConcluidos(Integer qtdConcluidos) {
		this.qtdConcluidos = qtdConcluidos;
	}

	/**
	 * Obtém o valor do atributo qtdNaoConcluidos.
	 * 
	 * @return Uma instância de {@link Integer} contendo o valor do atributo
	 *         qtdNaoConcluidos.
	 */
	public Integer getQtdNaoConcluidos() {
		return this.qtdNaoConcluidos;
	}

	/**
	 * Atualiza a instância de qtdNaoConcluidos com o valor de qtdNaoConcluidos.
	 * 
	 * @param qtdNaoConcluidos
	 *            Uma instância de Integer contendo o valor a ser atualizado.
	 */
	public void setQtdNaoConcluidos(Integer qtdNaoConcluidos) {
		this.qtdNaoConcluidos = qtdNaoConcluidos;
	}

	/**
	 * Obtém o valor do atributo qtdDuplicados.
	 * 
	 * @return Uma instância de {@link Integer} contendo o valor do atributo
	 *         qtdDuplicados.
	 */
	public Integer getQtdDuplicados() {
		return this.qtdDuplicados;
	}

	/**
	 * Atualiza a instância de qtdDuplicados com o valor de qtdDuplicados.
	 * 
	 * @param qtdDuplicados
	 *            Uma instância de Integer contendo o valor a ser atualizado.
	 */
	public void setQtdDuplicados(Integer qtdDuplicados) {
		this.qtdDuplicados = qtdDuplicados;
	}

}
