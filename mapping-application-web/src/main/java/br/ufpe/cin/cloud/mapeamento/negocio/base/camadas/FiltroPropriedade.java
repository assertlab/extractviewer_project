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
 * Representa o encapsulamento dos dados que permitem uma consulta filtrada no
 * sistema.
 * 
 * @author helaine.lins
 * @created 05/09/2014 - 17:11:39
 */
public class FiltroPropriedade implements Serializable {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 5470115006161957471L;

	private String propriedade;
	private Object valor;
	private Class<?> tipo;
	private TipoFiltroEnum tipoFiltro;

	/**
	 * Cria uma nova instância da classe inicializando os valores padrão.
	 * 
	 * @param propriedade A instância que contém a propriedade a ser ordenada.
	 * @param valor A instância que contém o valor do filtro.
	 * @param tipo A instância que contem o class do tipo;
	 * @param tipoFiltro O tipo do filtro a ser aplicado no valor.
	 */
	public FiltroPropriedade(String propriedade, Object valor, Class<?> tipo, TipoFiltroEnum tipoFiltro) {
		this.propriedade = propriedade;
		this.valor = valor;
		this.tipo = tipo;
		this.tipoFiltro = tipoFiltro;
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
	 * Obtém o valor do atributo valor.
	 * 
	 * @return Uma instância de {@link Object} contendo o valor do atributo
	 *         valor.
	 */
	public Object getValor() {
		return this.valor;
	}

	/**
	 * Atualiza a instância de valor com o valor de valor.
	 * 
	 * @param valor
	 *            Uma instância de Object contendo o valor a ser atualizado.
	 */
	public void setValor(Object valor) {
		this.valor = valor;
	}

	/**
	 * Obtém o valor do atributo tipo.
	 * 
	 * @return Uma instância de {@link Class} contendo o valor do atributo tipo.
	 */
	public Class<?> getTipo() {
		return this.tipo;
	}

	/**
	 * Atualiza a instância de tipo com o valor de tipo.
	 * 
	 * @param tipo
	 *            Uma instância de Class contendo o valor a ser atualizado.
	 */
	public void setTipo(Class<?> tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtém o valor do atributo tipoFiltro.
	 * 
	 * @return Uma instância de {@link TipoFiltroEnum} contendo o valor do
	 *         atributo tipoFiltro.
	 */
	public TipoFiltroEnum getTipoFiltro() {
		return this.tipoFiltro;
	}

	/**
	 * Atualiza a instância de tipoFiltro com o valor de tipoFiltro.
	 * 
	 * @param tipoFiltro
	 *            Uma instância de TipoFiltroEnum contendo o valor a ser
	 *            atualizado.
	 */
	public void setTipoFiltro(TipoFiltroEnum tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

}
