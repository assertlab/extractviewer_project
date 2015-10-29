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
 * Representa uma coletânia de estatistícas da etapa de revisão.
 * 
 * @author helaine.lins
 * @created 12/11/2014 - 14:26:42
 */
public class EstatisticaEtapaRevisao implements Serializable {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 8237922935628080004L;

	private Integer qtdTotal;
	private Integer qtdIncluidos;
	private Integer qtdExcluidos;
	private Integer qtdConcluidos;
	private Double percConclusao;
	private VelocidadeDiaBean velocidadeDia;
	private List<VelocidadeDiaBean> historicoVelocidade;

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
	 * Obtém o valor do atributo percConclusao.
	 * 
	 * @return Uma instância de {@link Double} contendo o valor do atributo
	 *         percConclusao.
	 */
	public Double getPercConclusao() {
		return this.percConclusao;
	}

	/**
	 * Atualiza a instância de percConclusao com o valor de percConclusao.
	 * 
	 * @param percConclusao
	 *            Uma instância de Double contendo o valor a ser atualizado.
	 */
	public void setPercConclusao(Double percConclusao) {
		this.percConclusao = percConclusao;
	}

	/**
	 * Obtém o valor do atributo velocidadeDia.
	 * 
	 * @return Uma instância de {@link VelocidadeDiaBean} contendo o valor do
	 *         atributo velocidadeDia.
	 */
	public VelocidadeDiaBean getVelocidadeDia() {
		return this.velocidadeDia;
	}

	/**
	 * Atualiza a instância de velocidadeDia com o valor de velocidadeDia.
	 * 
	 * @param velocidadeDia
	 *            Uma instância de VelocidadeDiaBean contendo o valor a ser
	 *            atualizado.
	 */
	public void setVelocidadeDia(VelocidadeDiaBean velocidadeDia) {
		this.velocidadeDia = velocidadeDia;
	}

	/**
	 * Obtém o valor do atributo historicoVelocidade.
	 * 
	 * @return Uma instância de {@link List<VelocidadeDiaBean>} contendo o valor
	 *         do atributo historicoVelocidade.
	 */
	public List<VelocidadeDiaBean> getHistoricoVelocidade() {
		return this.historicoVelocidade;
	}

	/**
	 * Atualiza a instância de historicoVelocidade com o valor de
	 * historicoVelocidade.
	 * 
	 * @param historicoVelocidade
	 *            Uma instância de List<VelocidadeDiaBean> contendo o valor a
	 *            ser atualizado.
	 */
	public void setHistoricoVelocidade(
			List<VelocidadeDiaBean> historicoVelocidade) {
		this.historicoVelocidade = historicoVelocidade;
	}

}
