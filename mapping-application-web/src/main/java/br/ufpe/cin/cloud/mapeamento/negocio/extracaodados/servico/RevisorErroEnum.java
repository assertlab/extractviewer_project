/**
 * SiVest - Sistema de Inscrição do Vestibular da UPE
 * 
 * Copyright (c) Universidade Federal de Pernambuco.
 * 
 * Este software é confidencial e propriedade da UPE. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do UPE. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.IEnumErro;

/**
 * Representa as chaves de mensagens de erro nas realizações de casos de uso da entidade {@link Revisor}.
 * 
 * @author helaine.lins
 * @created 05/05/2014 - 18:18:23
 */
public enum RevisorErroEnum implements IEnumErro {

    ERRO_LOGIN_OBRIGATORIO("Revisor.erro.loginObrigatorio");

    /**
     * Representa o código da enumeração.
     */
    private String codigo;

    /**
     * Cria uma nova instância da enumeração inicializando o seu código e descrição correspondente.
     * 
     * @param codigo A instância que representa o código da enumeração.
     */
    private RevisorErroEnum(String codigo) {
        this.codigo = codigo;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.IEnumErro#getChave()
     */
    @Override
    public String getChave() {
        return this.codigo;
    }

}
