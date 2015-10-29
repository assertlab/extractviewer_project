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
 * A classe Erro representa o detalhamento de um Erro no Sistema SiVest.
 * 
 * @author helaine.lins
 */
public class Erro {

    /**
     * Representa o código do erro gerado.
     */
    private String codigo;

    /**
     * Representa a descrição do erro gerado.
     */
    private String erro;

    /**
     * Representa os argumentos a serem interpolados na mensagem.
     */
    private Object[] parametros;

    /**
     * Representa o tipo de erro gerado.
     */
    private TipoErro tipoErro;

    /**
     * Cria uma nova instância da classe.
     */
    public Erro() {
        super();
    }

    /**
     * Cria uma nova instância do erro inicializando o código da mensagem do erro.
     * 
     * @param codigo código do erro
     * @param erro mensagem do erro
     * @param parametros representa os parâmetros a serem substituidos na mensagem.
     */
    public Erro(String codigo, String erro, Object... parametros) {
        this();
        this.codigo = codigo;
        this.erro = erro;
        this.parametros = parametros;
    }

    /**
     * Cria uma nova instância do erro inicializando o código da mensagem do erro.
     * 
     * @param codigo a mensagem do erro.
     * @param tipoErro tipo do erro.
     * @param parametros representa os parâmetros a serem substituidos na mensagem.
     */
    public Erro(String codigo, TipoErro tipoErro, Object... parametros) {
        this();
        this.codigo = codigo;
        this.tipoErro = tipoErro;
    }
    
    /**
     * Cria uma nova instância do erro inicializando o código da mensagem do erro.
     * 
     * @param erro a mensagem do erro.
     * @param tipoErro tipo do erro.
     * @param parametros representa os parâmetros a serem substituidos na mensagem.
     */
    public Erro(IEnumErro erro, TipoErro tipoErro, Object... parametros) {
    	this();
    	this.codigo = erro.getChave();
    	this.tipoErro = tipoErro;
    	this.parametros = parametros;
    }

    /**
     * Cria uma nova instância do erro inicializando o código da mensagem, a mensagem e o tipo do erro.
     * 
     * @param codigo código do erro
     * @param erro mensagem do erro
     * @param tipoErro tipo do erro
     * @param parametros representa os parâmetros a serem substituidos na mensagem.
     */
    public Erro(String codigo, String erro, TipoErro tipoErro, Object... parametros) {
        this();
        this.codigo = codigo;
        this.erro = erro;
        this.tipoErro = tipoErro;
        this.parametros = parametros;
    }

    /**
     * Obtém o valor do atributo codigo.
     * 
     * @return Uma instância de String contendo o valor do atributo codigo.
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Atualiza a instância de codigo com o valor de codigo.
     * 
     * @param codigo Uma instância de String contendo o valor a ser atualizado.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtém o valor do atributo erro.
     * 
     * @return Uma instância de String contendo o valor do atributo erro.
     */
    public String getErro() {
        return this.erro;
    }

    /**
     * Atualiza a instância de erro com o valor de erro.
     * 
     * @param erro Uma instância de String contendo o valor a ser atualizado.
     */
    public void setErro(String erro) {
        this.erro = erro;
    }

    /**
     * Obtém o valor do atributo tipoErro.
     * 
     * @return Uma instância de TipoErro contendo o valor do atributo tipoErro.
     */
    public TipoErro getTipoErro() {
        return this.tipoErro;
    }

    /**
     * Atualiza a instância de tipoErro com o valor de tipoErro.
     * 
     * @param tipoErro Uma instância de TipoErro contendo o valor a ser atualizado.
     */
    public void setTipoErro(TipoErro tipoErro) {
        this.tipoErro = tipoErro;
    }

    /**
     * Obtém o valor do atributo parametros.
     * 
     * @return Uma instância de {@link Object[]} contendo o valor do atributo parametros.
     */
    public Object[] getParametros() {
        return this.parametros;
    }

    /**
     * Atualiza a instância de parametros com o valor de parametros.
     * 
     * @param parametros Uma instância de Object[] contendo o valor a ser atualizado.
     */
    public void setParametros(Object[] parametros) {
        this.parametros = parametros;
    }

}
