/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.base.converter;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Converte os valores {@link Date} em {@link LocalDate}.
 * 
 * @author helaine.lins
 * @created 12/05/2014 - 23:31:22
 */
@FacesConverter(value = "localDateTimeConverter")
public class LocalDateTimeConverter implements Converter {

    /**
     * {@inheritDoc}.
     * 
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LocalDateTime data = null;

        if (StringUtils.isNotBlank(value)) {
            DateTimeFormatter pattern = DateTimeFormat.forPattern("dd/MM/yyyy");
            UIInput componente = (UIInput) component;

            try {
                data = pattern.parseLocalDateTime(value);
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        componente.getValidatorMessage(), componente.getValidatorMessage()));

            }
        }

        return data;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String data = null;

        if (value != null) {
            LocalDateTime dataObj = (LocalDateTime) value;
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
            data = dataObj.toString(fmt);
        }

        return data;
    }

}
