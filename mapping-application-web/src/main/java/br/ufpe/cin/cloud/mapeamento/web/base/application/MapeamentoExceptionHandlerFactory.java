/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.base.application;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Factory para o tratamento de exceções da aplicação.
 * 
 * @author helaine.lins
 * @created 21/04/2014 - 21:21:45
 */
public class MapeamentoExceptionHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory factory;
    
    public MapeamentoExceptionHandlerFactory(ExceptionHandlerFactory factory) {
        this.factory = factory;
    }
    
    /**
     * {@inheritDoc}.
     *
     * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
     */
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new MapeamentoExceptionHandler(this.factory.getExceptionHandler());
    }
}
