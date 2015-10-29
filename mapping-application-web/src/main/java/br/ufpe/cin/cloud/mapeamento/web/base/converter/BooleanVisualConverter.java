/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.base.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

/**
 * Converter para os valores representativos de um campo boleano.
 * 
 * @author helaine.lins
 * @created 17/04/2014 - 21:10:40
 */
@FacesConverter(value = "boleanoVisualConverter")
public class BooleanVisualConverter implements Converter {

    /**
     * {@inheritDoc}.
     * 
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Boolean valor = null;

        if (StringUtils.isNotBlank(value)) {
            valor = Boolean.valueOf(value);
        }

        return valor;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        String valor = null;

        if (value != null) {
            if (Boolean.TRUE.equals(value)) {
                valor = "X";
            } else {
                valor = "";
            }
        }

        return valor;
    }

}
