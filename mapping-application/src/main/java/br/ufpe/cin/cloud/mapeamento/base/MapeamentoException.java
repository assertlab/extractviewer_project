/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.base;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe MapeamentoException representa um encapsulamento de exceção no sistema de mapeamento.
 * 
 * @author helaine.lins
 */
public class MapeamentoException extends RuntimeException {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = -3040071695000407830L;

    /**
     * Representa a lista de erros a serem tratados.
     */
    private List<String> erros;

    /**
     * Cria uma nova instância inicializando o erro relacionado.
     * 
     * @param erros a instância que representa as informações do erro.
     */
    public MapeamentoException(List<String> erros) {
        super();
        this.erros = new ArrayList<String>();
        this.erros.addAll(erros);
    }

    /**
     * Cria uma nova instância inicializando o erro relacionado.
     * 
     * @param erros a instância que representa as informações do erro.
     * @param causa A origem do erro a ser encapsulada.
     */
    public MapeamentoException(List<String> erros, Throwable causa) {
        super(causa);

        this.erros = new ArrayList<String>();
        this.erros.addAll(erros);
    }

    /**
     * Cria uma nova instância inicializando o erro relacionado com o código padrão e a mensagem informada.
     * 
     * @param erro a instância que representa a informação do erro.
     */
    public MapeamentoException(String erro) {
    	super();
    	this.erros = new ArrayList<String>();
        erros.add(erro);
    }
    
    /**
     * Cria uma nova instância inicializando o erro relacionado com o código padrão e a mensagem informada.
     * 
     * @param erro a instância que representa a informação do erro.
     * @param causa A origem do erro a ser encapsulada.
     */
    public MapeamentoException(String erro, Throwable causa) {
    	super(causa);
    	this.erros = new ArrayList<String>();
    	erros.add(erro);
    }

    /**
     * Obtém o valor do atributo erros.
     * 
     * @return Uma instância de {@link List<String>} contendo o valor do atributo erros.
     */
    public List<String> getErros() {
        return this.erros;
    }

}
