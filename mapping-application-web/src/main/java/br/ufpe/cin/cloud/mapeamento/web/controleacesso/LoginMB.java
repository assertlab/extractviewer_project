/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.controleacesso;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.IUsuarioServico;
import br.ufpe.cin.cloud.mapeamento.web.base.camadas.AbstractMB;
import br.ufpe.cin.cloud.mapeamento.web.base.util.MapeamentoWebUtil;

/**
 * Representa a implementação padrão do managed bean de controle de acesso do
 * sistema.
 * 
 * @author helaine.lins
 * @created 09/04/2014 - 00:34:01
 */
@ViewScoped
@ManagedBean(name = "loginMB")
public class LoginMB extends AbstractMB {

	/**
	 * Representa o identificador serialVersionUID da classe.
	 */
	private static final long serialVersionUID = -8089668948685128273L;

	/**
	 * Representa a chave da mensagem de erro para ativação de cadastro de
	 * usuário inexistente.
	 */
	private static final String MSG_LOGIN_ATIVACAO_USUARIO_INEXISTENTE = "login_ativacao_usuario_inexistente";

	/**
	 * Representa a chave da mensagem de erro para recuperação de senha sem
	 * informar login.
	 */
	private static final String MSG_RECUPERACAO_SENHA_LOGIN_OBRIGATORIO = "str_recuperacao_senha_login_obrigatorio";

	/**
	 * Representa a mensagem de erro ao realizar logout da aplicação.
	 */
	private static final String MSG_LOGOUT_ERRO = "str_logout_erro";

	/**
	 * Representa a ação do spring que realiza a autenticação de usuário no
	 * sistema.
	 */
	private static final String J_SPRING_SECURITY_LOGOUT = "/j_spring_security_logout";

	/**
	 * Representa a mensagem de logout realizado com sucesso.
	 */
	private static final String MSG_LOGOUT_SUCESSO = "str_logout_sucesso";

	/**
	 * Representa a mensagem de erro ao realizar o login.
	 */
	private static final String MSG_LOGIN_FALHA = "str_login_falha";

	/**
	 * Representa a ação do spring que realiza o processo de logout do usuário
	 * no sistema.
	 */
	private static final String J_SPRING_SECURITY_CHECK = "/j_spring_security_check";

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private Log LOG = LogFactory.getLog(LoginMB.class);

	/**
	 * Representa o atributo de usuario informado ao spring security.
	 */
	private String j_username;

	/**
	 * Representa o serviço de UsuarioBean.
	 */
	@ManagedProperty(value = "#{usuarioServico}")
	private IUsuarioServico usuarioServico;

	private Usuario usuario;

	/**
	 * Identifica a origem da solicitação de login no sistema e redireciona a
	 * solicitação para o Spring Security realiza a autenticação.
	 */
	public void logar() {
		try {

			RequestDispatcher dispatcher = MapeamentoWebUtil
					.getServletRequest().getRequestDispatcher(
							J_SPRING_SECURITY_CHECK);

			dispatcher.forward(MapeamentoWebUtil.getServletRequest(),
					MapeamentoWebUtil.getServletResponse());
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {
			LOG.error(
					"Erro ao enviar autenticacao de login para o spring security",
					e);
			MapeamentoWebUtil.exibirMensagemErro(MSG_LOGIN_FALHA);
		}

	}

	/**
	 * Realiza o logout do usuário no sistema.
	 * 
	 * @return A chave do redirecionamento para a página de login.
	 */
	public String logout() {
		String paginaDestino = null;

		MapeamentoWebUtil.exibirMensagemSucesso(MSG_LOGOUT_SUCESSO);

		try {

			RequestDispatcher dispatcher = MapeamentoWebUtil
					.getServletRequest().getRequestDispatcher(
							J_SPRING_SECURITY_LOGOUT);

			dispatcher.forward(MapeamentoWebUtil.getServletRequest(),
					MapeamentoWebUtil.getServletResponse());
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {
			LOG.error(
					"Erro ao enviar processo de logout para o spring security",
					e);
			MapeamentoWebUtil.exibirMensagemErro(MSG_LOGOUT_ERRO);
		}

		LOG.info(LocalDateTime.now().toString(DateTimeFormat.forPattern("dd/MM/yyyy hh:mm:ss")) + " - Usuario " + usuario.getLogin() + " deslogou do sistema.");
		
		return paginaDestino;
	}

	/**
	 * Redireciona o usuário para a tela de cadastramento no sistema.
	 * 
	 * @return A chave da navegação para a página cadastro.xhtml
	 */
	public String efetuarCadastro() {
		return "cadastro";
	}

	/**
	 * Inicializa o processo de recuperação de senhas.
	 * 
	 * @return "recuperar-senha" mapeamento para a página de recuperação de
	 *         senhas.
	 */
	public String recuperacaoSenha() {
		String retorno = null;

		String login = MapeamentoWebUtil.getRequestParameter("j_username");

		if (StringUtils.isBlank(login)) {
			MapeamentoWebUtil
					.exibirMensagemErro(MSG_RECUPERACAO_SENHA_LOGIN_OBRIGATORIO);
		} else {
			UsuarioBean usuario = this.usuarioServico.buscarUsuarioLogin(login);

			if (usuario == null) {
				MapeamentoWebUtil
						.exibirMensagemErro(MSG_LOGIN_ATIVACAO_USUARIO_INEXISTENTE);
			} else {
				MapeamentoWebUtil.adicionarAtributoSessao(PARAM_USUARIO,
						usuario);
				retorno = "recuperar-senha";
			}

		}

		return retorno;
	}

	public Usuario getUsuario() {
		// String cpf = MapeamentoWebUtil.getRequestParameter("j_username");

		// FIXME: hsl - Adicionar implementação do método.

		// if (StringUtils.isBlank(cpf)) {
		// MapeamentoWebUtil.exibirMensagemErro(MSG_RECUPERACAO_SENHA_CPF_OBRIGATORIO);
		// } else {
		// usuario =
		// this.usuarioServico.buscarUsuarioCpf(cpf.replaceAll(REGEX_NAO_DIGITOS,
		// ""));
		//
		// if (usuario == null) {
		// MapeamentoWebUtil.exibirMensagemErro(MSG_LOGIN_ATIVACAO_USUARIO_INEXISTENTE);
		// } else {
		// MapeamentoWebUtil.adicionarAtributoSessao("usuario", usuario);
		// }
		// }

		return usuario;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.web.base.camadas.AbstractMB#carregarPagina()
	 */
	@Override
	public void carregarPagina() {

	}

	/**
	 * Atualiza a instância de usuarioServico com o valor de usuarioServico.
	 * 
	 * @param usuarioServico
	 *            Uma instância de IUsuarioServico contendo o valor a ser
	 *            atualizado.
	 */
	public void setUsuarioServico(IUsuarioServico usuarioServico) {
		this.usuarioServico = usuarioServico;
	}

	/**
	 * Obtém o valor do atributo j_username.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         j_username.
	 */
	public String getJ_username() {
		return this.j_username;
	}

	/**
	 * Atualiza a instância de j_username com o valor de j_username.
	 * 
	 * @param j_username
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	/**
	 * Obtém o valor do atributo usuarioServico.
	 * 
	 * @return Uma instância de {@link IUsuarioServico} contendo o valor do
	 *         atributo usuarioServico.
	 */
	public IUsuarioServico getUsuarioServico() {
		return this.usuarioServico;
	}

}
