/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.controleacesso;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

import br.ufpe.cin.cloud.mapeamento.web.base.util.MapeamentoWebUtil;

/**
 * Captura as exceções de autenticação no sistema.
 * 
 * @author helaine.lins
 * @created 09/04/2014 - 00:39:12
 */
public class LoginErrorPhaseListener implements PhaseListener {

    /**
     * Representa o mecanismo de log da classe.
     */
    private Log LOG = LogFactory.getLog(LoginErrorPhaseListener.class);

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}.
     * 
     * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
     */
    @Override
    public void afterPhase(PhaseEvent arg0) {
        
    }

    /**
     * {@inheritDoc}.
     * 
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     */
    @Override
    public void beforePhase(PhaseEvent arg0) {

        Exception dadosIncorretosException =
                (Exception) MapeamentoWebUtil.getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);

        if (dadosIncorretosException instanceof BadCredentialsException) {
            LOG.info("Erro de autenticação de usuário.");

            MapeamentoWebUtil.getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
            MapeamentoWebUtil.exibirMensagemErro("str_login_erro");
        }

    }

    /**
     * {@inheritDoc}.
     * 
     * @see javax.faces.event.PhaseListener#getPhaseId()
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

}
