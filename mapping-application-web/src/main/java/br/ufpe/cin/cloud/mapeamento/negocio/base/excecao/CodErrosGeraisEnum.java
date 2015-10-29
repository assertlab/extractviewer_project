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
 * Representa o encapsulamento de erros genéricos da aplicação.
 * 
 * @author helaine.lins
 * @created 01/04/2014 - 17:28:32
 */
public enum CodErrosGeraisEnum implements IEnumErro {

    /**
     * Representa a chave do erro 'Falha de acesso aos dados da tabela {0} ao {1}.' no arquivo de propriedades.
     */
    GERAL_ERRO_PERSISTENCIA_MENSAGEM("mapping.erro.persistencia.mensagem"),
    
    /**
     * Representa a chave do código de erro 'MAPPINGERRGER01' no arquivo de propriedades.
     */
    GERAL_ERRO_PERSISTENCIA_COODIGO("mapping.erro.persistencia.codigo");

    /**
     * Representa a chave da mensagem do erro no arquivo de propriedades.
     */
    private String chave;

    /**
     * Cria uma nova instância da enumeração inicializando a chave do erro no arquivo de propriedades.
     * 
     * @param chave representa a chave da mensagem do erro no arquivo de propriedades.
     */
    private CodErrosGeraisEnum(String chave) {
        this.chave = chave;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.IEnumErro#getChave()
     */
    @Override
    public String getChave() {
        return this.chave;
    }

}
