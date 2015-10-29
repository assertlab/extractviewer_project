/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.selecao.modelo;

/**
 * Representa um estudo no sistema.
 * 
 * @author helaine.lins
 * @created 24/07/2014 - 17:39:59
 */
public class Estudo {

	private Long id;
	private String codigo;
	private String titulo;
	private Integer ano;
	private String autores;
	private String resumo;
	private String arquivo;
	private boolean urlNaoTratada;

	/**
	 * Obtém o valor do atributo id.
	 * 
	 * @return Uma instância de {@link Long} contendo o valor do atributo id.
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Atualiza a instância de id com o valor de id.
	 * 
	 * @param id
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtém o valor do atributo codigo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         codigo.
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Atualiza a instância de codigo com o valor de codigo.
	 * 
	 * @param codigo
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtém o valor do atributo titulo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         titulo.
	 */
	public String getTitulo() {
		return this.titulo;
	}

	/**
	 * Atualiza a instância de titulo com o valor de titulo.
	 * 
	 * @param titulo
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

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
	 * Obtém o valor do atributo autores.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         autores.
	 */
	public String getAutores() {
		return this.autores;
	}

	/**
	 * Atualiza a instância de autores com o valor de autores.
	 * 
	 * @param autores
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setAutores(String autores) {
		this.autores = autores;
	}

	/**
	 * Obtém o valor do atributo arquivo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         arquivo.
	 */
	public String getArquivo() {
		return this.arquivo;
	}

	/**
	 * Atualiza a instância de arquivo com o valor de arquivo.
	 * 
	 * @param arquivo
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	/**
	 * Obtém o valor do atributo urlNaoTratada.
	 * 
	 * @return Uma instância de {@link boolean} contendo o valor do atributo
	 *         urlNaoTratada.
	 */
	public boolean isUrlNaoTratada() {
		return this.urlNaoTratada;
	}

	/**
	 * Atualiza a instância de urlNaoTratada com o valor de urlNaoTratada.
	 * 
	 * @param urlNaoTratada
	 *            Uma instância de boolean contendo o valor a ser atualizado.
	 */
	public void setUrlNaoTratada(boolean urlNaoTratada) {
		this.urlNaoTratada = urlNaoTratada;
	}

	/**
	 * Obtém o valor do atributo resumo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         resumo.
	 */
	public String getResumo() {
		return this.resumo;
	}

	/**
	 * Atualiza a instância de resumo com o valor de resumo.
	 * 
	 * @param resumo
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "titulo:" + this.titulo + " ano:" + this.ano + " autores:"
				+ this.autores + " arquivo:" + this.arquivo;
	}

}
