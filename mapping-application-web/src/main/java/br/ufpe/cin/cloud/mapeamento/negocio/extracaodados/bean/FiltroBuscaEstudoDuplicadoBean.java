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
 * Representa o encapsulamento dos dados da busca por estudos duplicados.
 * 
 * @author helaine.lins
 * @created 27/10/2014 - 12:39:18
 */
public class FiltroBuscaEstudoDuplicadoBean extends BaseBean {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -6003137954100411718L;

	private Long idEtapaRevisao;
	private Long idAnaliseEstudoDuplicada;
	private Long idAnaliseEstudoEmEdicao;
	private String tituloEstudo;
	private String codEstudo;

	/**
	 * Obtém o valor do atributo idAnaliseEstudoDuplicada.
	 * 
	 * @return Uma instância de {@link Long} contendo o valor do atributo
	 *         idAnaliseEstudoDuplicada.
	 */
	public Long getIdAnaliseEstudoDuplicada() {
		return this.idAnaliseEstudoDuplicada;
	}

	/**
	 * Atualiza a instância de idAnaliseEstudoDuplicada com o valor de
	 * idAnaliseEstudoDuplicada.
	 * 
	 * @param idAnaliseEstudoDuplicada
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setIdAnaliseEstudoDuplicada(Long idAnaliseEstudoDuplicada) {
		this.idAnaliseEstudoDuplicada = idAnaliseEstudoDuplicada;
	}

	/**
	 * Obtém o valor do atributo idAnaliseEstudoEmEdicao.
	 * 
	 * @return Uma instância de {@link Long} contendo o valor do atributo
	 *         idAnaliseEstudoEmEdicao.
	 */
	public Long getIdAnaliseEstudoEmEdicao() {
		return this.idAnaliseEstudoEmEdicao;
	}

	/**
	 * Atualiza a instância de idAnaliseEstudoEmEdicao com o valor de
	 * idAnaliseEstudoEmEdicao.
	 * 
	 * @param idAnaliseEstudoEmEdicao
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setIdAnaliseEstudoEmEdicao(Long idAnaliseEstudoEmEdicao) {
		this.idAnaliseEstudoEmEdicao = idAnaliseEstudoEmEdicao;
	}

	/**
	 * Obtém o valor do atributo tituloEstudo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         tituloEstudo.
	 */
	public String getTituloEstudo() {
		return this.tituloEstudo;
	}

	/**
	 * Atualiza a instância de tituloEstudo com o valor de tituloEstudo.
	 * 
	 * @param tituloEstudo
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setTituloEstudo(String tituloEstudo) {
		this.tituloEstudo = tituloEstudo;
	}

	/**
	 * Obtém o valor do atributo codEstudo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         codEstudo.
	 */
	public String getCodEstudo() {
		return this.codEstudo;
	}

	/**
	 * Atualiza a instância de codEstudo com o valor de codEstudo.
	 * 
	 * @param codEstudo
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setCodEstudo(String codEstudo) {
		this.codEstudo = codEstudo;
	}

	/**
	 * Obtém o valor do atributo idEtapaRevisao.
	 * 
	 * @return Uma instância de {@link Long} contendo o valor do atributo
	 *         idEtapaRevisao.
	 */
	public Long getIdEtapaRevisao() {
		return this.idEtapaRevisao;
	}

	/**
	 * Atualiza a instância de idEtapaRevisao com o valor de idEtapaRevisao.
	 * 
	 * @param idEtapaRevisao
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setIdEtapaRevisao(Long idEtapaRevisao) {
		this.idEtapaRevisao = idEtapaRevisao;
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
