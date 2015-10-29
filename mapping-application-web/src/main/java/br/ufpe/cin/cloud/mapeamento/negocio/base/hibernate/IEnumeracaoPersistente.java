/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate;

import java.io.Serializable;

/**
 * A interface IEnumeracaoPersistente representa o contrato para enumerações persistentes pelo hibernate.
 *
 *  @author helaine.lins
 * @created 15/04/2014 - 18:30:25
 */
public interface IEnumeracaoPersistente extends Serializable {

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
    
}
