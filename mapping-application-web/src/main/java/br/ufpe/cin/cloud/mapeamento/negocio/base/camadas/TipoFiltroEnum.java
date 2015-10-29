/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.camadas;

/**
 * Representa as opções de tipo de filtro em uma consulta com filtros.
 * 
 * @author helaine.lins
 * @created 15/04/2014 - 18:30:25
 */
public enum TipoFiltroEnum {

	/**
	 * Representa o tipo de operador like.
	 */
	LIKE("like"),
	
	/**
	 * Representa o tipo de operador equals.
	 */
	EQUALS("="),
	
	/**
	 * Representa o tipo de operador equals.
	 */
	DIFFERENT("<>"),
	
	/**
	 * Representa o tipo de operador NULL.
	 */
	NULL("IS NULL");

	/**
	 * Representa o código da enumeração.
	 */
	private String operador;

	/**
	 * Cria uma nova instância da enumeração inicializando o seu código e
	 * descrição correspondente.
	 * 
	 * @param operador
	 *            A instância que representa o operador da enumeração.
	 */
	private TipoFiltroEnum(String operador) {
		this.operador = operador;
	}

	/**
	 * Obtém o valor do atributo operador.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         operador.
	 */
	public String getOperador() {
		return this.operador;
	}

}
