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
 * Representa os dados estatísticos da busca realizada em uma determinada
 * conferência.
 * 
 * @author helaine.lins
 * @created 04/09/2014 - 19:20:48
 */
public class EstatisticaBuscaEstudosPorConferencia implements Serializable {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -2752608287677221391L;

	private String nome;
	private String sigla;
	private String comunidade;
	private String tipo;
	private Long qtdEstudos;
	private Integer inicio;
	private Integer fim;

	/**
	 * Obtém o valor do atributo nome.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         nome.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Atualiza a instância de nome com o valor de nome.
	 * 
	 * @param nome
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Obtém o valor do atributo sigla.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         sigla.
	 */
	public String getSigla() {
		return this.sigla;
	}

	/**
	 * Atualiza a instância de sigla com o valor de sigla.
	 * 
	 * @param sigla
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * Obtém o valor do atributo comunidade.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         comunidade.
	 */
	public String getComunidade() {
		return this.comunidade;
	}

	/**
	 * Atualiza a instância de comunidade com o valor de comunidade.
	 * 
	 * @param comunidade
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setComunidade(String comunidade) {
		this.comunidade = comunidade;
	}

	/**
	 * Obtém o valor do atributo tipo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         tipo.
	 */
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * Atualiza a instância de tipo com o valor de tipo.
	 * 
	 * @param tipo
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtém o valor do atributo qtdEstudos.
	 * 
	 * @return Uma instância de {@link Long} contendo o valor do atributo
	 *         qtdEstudos.
	 */
	public Long getQtdEstudos() {
		return this.qtdEstudos;
	}

	/**
	 * Atualiza a instância de qtdEstudos com o valor de qtdEstudos.
	 * 
	 * @param qtdEstudos
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setQtdEstudos(Long qtdEstudos) {
		this.qtdEstudos = qtdEstudos;
	}

	/**
	 * Obtém o valor do atributo inicio.
	 * 
	 * @return Uma instância de {@link Integer} contendo o valor do atributo
	 *         inicio.
	 */
	public Integer getInicio() {
		return this.inicio;
	}

	/**
	 * Atualiza a instância de inicio com o valor de inicio.
	 * 
	 * @param inicio
	 *            Uma instância de Integer contendo o valor a ser atualizado.
	 */
	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}

	/**
	 * Obtém o valor do atributo fim.
	 * 
	 * @return Uma instância de {@link Integer} contendo o valor do atributo
	 *         fim.
	 */
	public Integer getFim() {
		return this.fim;
	}

	/**
	 * Atualiza a instância de fim com o valor de fim.
	 * 
	 * @param fim
	 *            Uma instância de Integer contendo o valor a ser atualizado.
	 */
	public void setFim(Integer fim) {
		this.fim = fim;
	}

	/**
	 * {@inheritDoc}.
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[Nome:" + nome + " Sigla:" + sigla + " Comunidade:" + comunidade + " Tipo:" + tipo + " Qtd:" + qtdEstudos + " Inicio:" + inicio + " Fim:" + fim + "]";
	}
}
