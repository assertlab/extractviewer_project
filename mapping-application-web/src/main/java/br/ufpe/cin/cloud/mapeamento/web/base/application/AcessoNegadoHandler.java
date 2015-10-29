/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.base.application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Representa a implementação de acesso negado na aplicação.
 * 
 * @author helaine.lins
 * @created Jun 1, 2014 - 4:39:50 PM
 */
public class AcessoNegadoHandler implements AccessDeniedHandler {

    /**
     * Representa a url de acesso negado.
     */
    private String acessoNegadoUrl;
    
    /**
     * {@inheritDoc}.
     *
     * @see org.springframework.security.web.access.AccessDeniedHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.access.AccessDeniedException)
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
        request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.sendRedirect(this.acessoNegadoUrl);
    }

    /**
     * Obtém o valor do atributo acessoNegadoUrl.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo acessoNegadoUrl.
     */
    public String getAcessoNegadoUrl() {
        return this.acessoNegadoUrl;
    }

    /**
     * Atualiza a instância de acessoNegadoUrl com o valor de acessoNegadoUrl.
     *
     * @param acessoNegadoUrl Uma instância de String contendo o valor a ser atualizado.
     */
    public void setAcessoNegadoUrl(String acessoNegadoUrl) {
        this.acessoNegadoUrl = acessoNegadoUrl;
    }

}
