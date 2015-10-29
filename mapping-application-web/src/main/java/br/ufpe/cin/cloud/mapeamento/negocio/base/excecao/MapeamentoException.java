/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.excecao;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe MapeamentoException representa um encapsulamento de exceção no sistema Mapping.
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
    private List<Erro> erros;

    /**
     * Cria uma nova instância inicializando o erro relacionado.
     * 
     * @param erro a instância que representa as informações do erro.
     */
    public MapeamentoException(Erro erro) {
        super();

        this.erros = new ArrayList<Erro>();
        erros.add(erro);
    }

    /**
     * Cria uma nova instância inicializando o erro relacionado.
     * 
     * @param erro a instância que representa as informações do erro.
     * @param causa A origem do erro a ser encapsulada.
     */
    public MapeamentoException(Erro erro, Throwable causa) {
        super(causa);

        this.erros = new ArrayList<Erro>();
        erros.add(erro);
    }

    /**
     * Cria uma nova instância inicializando o erro relacionado com o código padrão e a mensagem informada.
     * 
     * @param codigo A string que contém o código do erro.
     * @param tipoErro A instância que representa o tipo de erro gerado.
     * @param causa A origem do erro a ser encapsulada.
     */
    public <ErroEnum extends IEnumErro> MapeamentoException(String codigo, TipoErro tipoErro) {
        super();

        this.erros = new ArrayList<Erro>();

        Erro erro = new Erro();
        erro.setCodigo(codigo);
        erro.setErro(codigo);
        erro.setTipoErro(tipoErro);

        erros.add(erro);
    }

    /**
     * Cria uma nova instância inicializando o erro relacionado com o código padrão e a mensagem informada.
     * 
     * @param codigo A string que contém o código do erro.
     * @param tipoErro A instância que representa o tipo de erro gerado.
     * @param causa A origem do erro a ser encapsulada.
     */
    public <ErroEnum extends IEnumErro> MapeamentoException(String codigo, TipoErro tipoErro, Throwable causa) {
        super(causa);

        this.erros = new ArrayList<Erro>();

        Erro erro = new Erro();
        erro.setCodigo(codigo);
        erro.setTipoErro(tipoErro);

        erros.add(erro);
    }

    /**
     * Cria uma nova instância inicializando o erro relacionado com o código padrão e a mensagem informada.
     * 
     * @param codigo A enumeração que contém o código do erro.
     * @param tipoErro A instância que representa o tipo de erro gerado.
     * @param causa A origem do erro a ser encapsulada.
     */
    public <ErroEnum extends IEnumErro> MapeamentoException(ErroEnum codigo, TipoErro tipoErro, Throwable causa) {
        super(causa);

        this.erros = new ArrayList<Erro>();

        Erro erro = new Erro();
        erro.setCodigo(codigo.getChave());
        erro.setTipoErro(tipoErro);

        erros.add(erro);
    }

    /**
     * Cria uma nova instância inicializando o erro relacionado com o código padrão e a mensagem informada.
     * 
     * @param codigo A enumeração que contém o código do erro.
     * @param tipoErro A instância que representa o tipo de erro gerado.
     */
    public <ErroEnum extends IEnumErro> MapeamentoException(ErroEnum codigo, TipoErro tipoErro) {
        super();

        this.erros = new ArrayList<Erro>();

        Erro erro = new Erro();
        erro.setCodigo(codigo.getChave());
        erro.setTipoErro(tipoErro);

        erros.add(erro);
    }

    /**
     * Cria uma nova instância inicializado a lista de erros relacionado.
     * 
     * @param erros as instâncias que representam as falhas geradas.
     */
    public MapeamentoException(List<Erro> erros) {
        super();
        this.erros = erros;
    }

    /**
     * Cria uma nova instância inicializando a origem do problema e a lista de erros relacionados.
     * 
     * @param erros as instâncias que representam as informações de erro.
     * @param origem a instância que representa a origem do problema a ser encapsulado.
     */
    public MapeamentoException(List<Erro> erros, Throwable causa) {
        super(causa);
        this.erros = erros;
    }

    /**
     * Obtém o valor do atributo erros.
     * 
     * @return Uma instância de {@link List<Erro>} contendo o valor do atributo erros.
     */
    public List<Erro> getErros() {
        return this.erros;
    }

}
