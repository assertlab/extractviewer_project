/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.excecao;

/**
 * Representa o contrato das enumerações de tratamento de erros no sistema SiVest.
 * 
 * @author helaine.lins
 * @created 31/03/2014 - 18:55:01
 */
public interface IEnumErro {

    /**
     * Obtém o valor do atributo chave.
     * 
     * @return Uma instância de String contendo o valor do atributo chave.
     */
    public abstract String getChave();
    
}
