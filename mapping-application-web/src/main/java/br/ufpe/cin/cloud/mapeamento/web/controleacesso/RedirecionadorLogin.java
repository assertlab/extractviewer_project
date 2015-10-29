/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.controleacesso;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.PerfilBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.web.base.util.MapeamentoWebUtil;

/**
 * Classe responsável por extender o mecanismo de autenticação do spring
 * security para após o login realizado com sucesso redirecionar o usuário para
 * a página correspondente ao seu estado de cadastro.
 * 
 * @author helaine.lins
 * @created 21/04/2014 - 01:22:31
 */
@Controller
public class RedirecionadorLogin implements AuthenticationSuccessHandler {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private Log LOG = LogFactory.getLog(RedirecionadorLogin.class);

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String pageRedirect = null;

		UsuarioBean usuario = MapeamentoWebUtil.obterUsuarioLogado();

		if (usuario != null) {
			if (isAdministrador(usuario)) {
				pageRedirect = "admin";
			} else {
				if (usuario.getEmailConfirmado() == null
						|| !usuario.getEmailConfirmado()) {
					pageRedirect = "ativacao";
				} else {
					pageRedirect = "revisor";
				}
			}
		}

		LOG.info(LocalDateTime.now().toString(DateTimeFormat.forPattern("dd/MM/yyyy hh:mm:ss")) + " - Usuario " + usuario.getLogin() + " redirecionado para "
				+ pageRedirect);

		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getApplication().getNavigationHandler()
				.handleNavigation(fc, null, pageRedirect);

	}

	private boolean isAdministrador(UsuarioBean usuario) {
		for (PerfilBean perfil : usuario.getPerfis()) {
			if (perfil.getAuthority().equalsIgnoreCase("ROLE_ADMINISTRADOR")) {
				return true;
			}
		}

		return false;
	}

}
