/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.base.application;

import java.util.Map;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 * Representa a implementação de um view handler para identificação de postback.
 * 
 * @author helaine.lins
 * @created 04/06/2014 - 14:37:13
 */
public class CustomViewHandler extends ViewHandlerWrapper {

    protected ViewHandler baseViewHandler;

    public CustomViewHandler(ViewHandler viewHandler) {
        super();
        this.baseViewHandler = viewHandler;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see javax.faces.application.ViewHandlerWrapper#getWrapped()
     */
    @Override
    public ViewHandler getWrapped() {
        return this.baseViewHandler;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see javax.faces.application.ViewHandlerWrapper#createView(javax.faces.context.FacesContext,
     *      java.lang.String)
     */
    @Override
    public UIViewRoot createView(FacesContext context, String viewId) {
        return super.createView(context, viewId);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see javax.faces.application.ViewHandlerWrapper#restoreView(javax.faces.context.FacesContext,
     *      java.lang.String)
     */
    @Override
    public UIViewRoot restoreView(FacesContext context, String viewId) {
        return super.restoreView(context, viewId);
    }

    @SuppressWarnings({"rawtypes", "deprecation"})
    public Map getRequestScope(FacesContext facesContext) {
        return (Map) facesContext.getApplication().createValueBinding("#{requestScope}").getValue(facesContext);
    }

    @SuppressWarnings("unchecked")
    public void setPostback(FacesContext facesContext, boolean value) {
        getRequestScope(facesContext).put("ispostback", new Boolean(value));
    }

}
