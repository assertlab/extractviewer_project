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
 * A enumeração TipoErro representa a natureza de um erro no Sistema Mapping.
 * 
 * @author helaine.lins
 */
public enum TipoErro {

    /**
     * Representa um erro ocasionado por validação de atributos.
     */
    VALIDACAO,

    /**
     * Representam os erros levantados através do hibernate valdator.
     */
    HIBERNATE_VALIDATOR,

    /**
     * Representao um erro ocasionado por validação de regras de negócio do sistema.
     */
    NEGOCIO,

    /**
     * Representa um tipo de erro não esperado na aplicação.
     */
    INESPERADO,

    /**
     * Representa um tipo de erro de acesso a dados no sistema.
     */
    ACESSO_DADOS;

}
