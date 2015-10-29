/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.base.application;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.ufpe.cin.cloud.mapeamento.web.base.util.MapeamentoWebUtil;

/**
 * Representa um {@link PhaseListener} para tratamento de exceções ajax.
 * 
 * @author helaine.lins
 * @created 27/06/2014 - 00:37:31
 */
public class TratamentoErroAjaxPhaseListener implements PhaseListener {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = -1576170503836595379L;

    /**
     * Remove o atributo de exceção, evitando assim que antes da página de erro seja exibida não corra exceções
     * IllegalStateException "response alread commited". Estas exceções ocorrem comumente nos servidores JBoss e no
     * Tomcat. Este tratamento faz com que a página de erro seja exibida.
     * 
     * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
     */
    @Override
    public void afterPhase(PhaseEvent event) {
        MapeamentoWebUtil.getServletRequest().removeAttribute("javax.servlet.error.exception");
    }

    /**
     * {@inheritDoc}.
     * 
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     */
    @Override
    public void beforePhase(PhaseEvent event) {

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
