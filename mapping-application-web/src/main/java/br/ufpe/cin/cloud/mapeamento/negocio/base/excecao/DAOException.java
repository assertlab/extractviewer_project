/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.excecao;

import java.util.List;

/**
 * Representa as exceções da camada de acesso a dados da aplicação.
 * 
 * @author helaine.lins
 * @created 01/04/2014 - 17:18:34
 */
public class DAOException extends MapeamentoException {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = -1530638123627954591L;

    /**
     * Cria uma nova instância inicializando o erro relacionado.
     * 
     * @param erro a instância que representa as informações do erro.
     */
    public DAOException(Erro erro) {
        super(erro);
    }

    /**
     * Cria uma nova instância inicializando o erro relacionado.
     * 
     * @param erro a instância que representa as informações do erro.
     * @param causa A origem do erro a ser encapsulada.
     */
    public DAOException(Erro erro, Throwable causa) {
        super(erro, causa);
    }

    /**
     * Cria uma nova instância inicializando o erro relacionado com o código padrão e a mensagem informada.
     * 
     * @param codigo A enumeração que contém o código do erro.
     * @param tipoErro A instância que representa o tipo de erro gerado.
     * @param causa A origem do erro a ser encapsulada.
     */
    public <ErroEnum extends IEnumErro> DAOException(ErroEnum codigo, TipoErro tipoErro, Throwable causa) {
        super(codigo, tipoErro, causa);
    }

    /**
     * Cria uma nova instância inicializado a lista de erros relacionado.
     * 
     * @param erros as instâncias que representam as falhas geradas.
     */
    public DAOException(List<Erro> erros) {
        super(erros);
    }

    /**
     * Cria uma nova instância inicializando a origem do problema e a lista de erros relacionados.
     * 
     * @param erros as instâncias que representam as informações de erro.
     * @param origem a instância que representa a origem do problema a ser encapsulado.
     */
    public DAOException(List<Erro> erros, Throwable causa) {
        super(erros, causa);
    }
    
}
