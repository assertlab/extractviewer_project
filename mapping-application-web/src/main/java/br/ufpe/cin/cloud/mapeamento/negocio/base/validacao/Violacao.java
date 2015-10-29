/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.validacao;

import java.util.HashMap;
import java.util.Map;

/**
 * A classe Violacao representa os detalhes de uma violação de validação atraves do Hibernate Validator.
 * 
 * @author helaine.lins
 * @version 1.0
 * @created Mar 31, 2012 - 18:30:52 PM
 */
public class Violacao {

    /**
     * Representa o código da mensagem de erro.
     */
    private String codigo;

    /**
     * Representa a classe da entidade validada.
     */
    private Class<?> classe;

    /**
     * Representa os parâmetros das mensagens de validação.
     */
    private Map<String, String> parametros = new HashMap<String, String>();

    /**
     * Cria uma nova instância da classe inicializando o código de erro a a classe de validação correspondente.
     * 
     * @param codigo O código de erro de validação.
     * @param classe A classe de validação.
     */
    public Violacao(String codigo, Class<?> classe) {
        if (codigo == null) {
            throw new IllegalArgumentException("O codigo do erro não pode ser nulo");
        }

        if (classe == null) {
            throw new IllegalArgumentException("A classe de validação não pode ser nula.");
        }

        this.codigo = codigo;
        this.classe = classe;
    }

    /**
     * Retorna o valor do atributo codigo.
     * 
     * @return codigo
     */
    public final String getCodigo() {
        return this.codigo;
    }

    /**
     * Retorna o valor do atributo classe.
     * 
     * @return classe
     */
    public final Class<?> getClasse() {
        return this.classe;
    }

    /**
     * Retorna o valor do atributo parametros.
     * 
     * @return parametros
     */
    public final Map<String, String> getParametros() {
        return this.parametros;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Violacao:[codigo=" + this.codigo + ",classe=" + this.classe.getName() + ",parametros="
                + this.parametros + "]";
    }

}
