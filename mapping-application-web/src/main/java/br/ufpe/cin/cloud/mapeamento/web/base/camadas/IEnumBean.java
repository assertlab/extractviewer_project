/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.base.camadas;

/**
 * Representa o contrato entre os Enums que representam lista de valores para as telas.
 * 
 * @author helaine.lins
 * @created 16/04/2014 - 18:36:38
 */
public interface IEnumBean {
    
    /**
     * Retorna o código do ítem da enumeração.
     * 
     * @return Uma {@link String} que representa o código do item da enumeração.
     */
    String getCodigo();
    
    /**
     * Retorna a descrição do ítem da enumeração.
     * 
     * @return Uma {@link String} contendo a descrição do ítem da enumeração.
     */
    String getDescricao();
    
    /**
     * Responsável por rertornar todos os valores dos enum que implementa este contrato.
     * 
     * @return Array de {@link IEnumBean} correspondente aos valores do enum que implementa o contrado.
     */
    IEnumBean[] getValores();
    
}
