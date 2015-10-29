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
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Representa a implementação de validadores de selecao de escolas.
 * 
 * @author helaine.lins
 * @created 26/06/2014 - 01:15:23
 */
@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

    private static final String REGEX = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";

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
            String email = (String) value;

            if (!email.matches(REGEX)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        componente.getValidatorMessage(), componente.getValidatorMessage()));
            }
        }
    }

}
