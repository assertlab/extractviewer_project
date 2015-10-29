/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.base.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Representa a implementação de validadores de selecao de escolas.
 * 
 * @author helaine.lins
 * @created 26/06/2014 - 01:15:23
 */
@FacesValidator(value = "localDateValidator")
public class LocalDateValidator implements Validator {

    /**
     * Indica se a data pode ser maior que a data de hoje.
     */
    private String maiorHoje;

    /**
     * Representa a mensagem para a data inválida.
     */
    private String msgInvalida;

    /**
     * Representa a mensagem para quando a data for maior que a atual;
     */
    private String msgMaiorAtual;

    /**
     * {@inheritDoc}.
     * 
     * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput componente = (UIInput) component;
        if (UIInput.isEmpty(value)) {

            if (componente.getRequiredMessage() != null) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        componente.getRequiredMessage(), componente.getRequiredMessage()));
            }

        } else {
            String strData = (String) value;

            DateTimeFormatter pattern = DateTimeFormat.forPattern("dd/MM/yyyy");
            LocalDate data = null;

            try {
                data = pattern.parseLocalDate(strData);
            } catch (Exception e) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", msgInvalida);
                throw new ConverterException(facesMessage, e);
            }

            if ("false".equals(this.maiorHoje)) {
                LocalDate dataAtual = LocalDate.now();

                if (data.isEqual(dataAtual) || data.isAfter(dataAtual)) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msgMaiorAtual,
                            msgMaiorAtual));
                }
            }
        }
    }

    /**
     * Obtém o valor do atributo maiorHoje.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo maiorHoje.
     */
    public String getMaiorHoje() {
        return this.maiorHoje;
    }

    /**
     * Atualiza a instância de maiorHoje com o valor de maiorHoje.
     * 
     * @param maiorHoje Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setMaiorHoje(String maiorHoje) {
        this.maiorHoje = maiorHoje;
    }

    /**
     * Obtém o valor do atributo msgInvalida.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo msgInvalida.
     */
    public String getMsgInvalida() {
        return this.msgInvalida;
    }

    /**
     * Atualiza a instância de msgInvalida com o valor de msgInvalida.
     * 
     * @param msgInvalida Uma instância de String contendo o valor a ser atualizado.
     */
    public void setMsgInvalida(String msgInvalida) {
        this.msgInvalida = msgInvalida;
    }

    /**
     * Obtém o valor do atributo msgMaiorAtual.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo msgMaiorAtual.
     */
    public String getMsgMaiorAtual() {
        return this.msgMaiorAtual;
    }

    /**
     * Atualiza a instância de msgMaiorAtual com o valor de msgMaiorAtual.
     * 
     * @param msgMaiorAtual Uma instância de String contendo o valor a ser atualizado.
     */
    public void setMsgMaiorAtual(String msgMaiorAtual) {
        this.msgMaiorAtual = msgMaiorAtual;
    }

}
