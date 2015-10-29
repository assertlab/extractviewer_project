/**
 * SiVest - Sistema de Inscrição do Vestibular da UPE
 * 
 * Copyright (c) Universidade Federal de Pernambuco.
 * 
 * Este software é confidencial e propriedade da UPE. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do UPE. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.controleacesso;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.IUsuarioServico;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.UsuarioServico;
import br.ufpe.cin.cloud.mapeamento.web.base.camadas.AbstractMB;
import br.ufpe.cin.cloud.mapeamento.web.base.util.MapeamentoWebUtil;

/**
 * Representa o bean gerenciável correspondente à tela de confirmação dos dados
 * cadastrais a partir do código de ativação enviado por email.
 * 
 * @author Cleber Alberto
 * @created 19/04/2012 - 18:19:49
 */
@ViewScoped
@ManagedBean(name = "codigoAtivacaoMB")
public class CodigoAtivacaoMB extends AbstractMB {

	/**
	 * Indetificador serialVersionUID da classe.
	 */
	private static final long serialVersionUID = 5406033832267456341L;

	/**
	 * Representa o código de ativação enviado ao usuário para confirmação dos
	 * dados.
	 */
	private String codigoAtivacao;

	/**
	 * Usuario que está cadastrando-se;
	 */
	private UsuarioBean usuario;

	/**
	 * Representa a instância da camada de servico {@link UsuarioServico}.
	 */
	@ManagedProperty(value = "#{usuarioServico}")
	private IUsuarioServico usuarioServico;

	/**
	 * Novo email.
	 */
	private String novoEmail;

	/**
	 * Repetição do novo email.
	 */
	private String repeticaoNovoEmail;

	/**
	 * Representa o email atual do usuário.
	 */
	private String email;

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.upe.sivest.web.base.camadas.AbstractMB#carregarPagina()
	 */
	@Override
	public void carregarPagina() {
		if (!MapeamentoWebUtil.isPostBack()) {
			this.usuario = MapeamentoWebUtil.getAtributoFlash("usuario",
					UsuarioBean.class);

			if (this.usuario == null) {
				this.usuario = MapeamentoWebUtil.obterUsuarioLogado();
			}

			if (this.usuario != null) {
				this.email = usuario.getEmail();
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				NavigationHandler navigator = context.getApplication()
						.getNavigationHandler();
				navigator.handleNavigation(context, null, "sessaoExpirada");
			}
		}
	}

	/**
	 * Executa o fluxo de ativação do código de confirmação.
	 * 
	 * @return
	 */
	public void ativarCodigoAtivacao(ActionEvent actionEvent) {
		this.usuarioServico.ativarCadastro(this.usuario.getLogin(),
				this.codigoAtivacao);
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("ativado", true);
	}

	/**
	 * Envia um novo email de ativação de cadastro para o usuário.
	 */
	public void reenviarEmailAtivacao(ActionEvent actionEvent) {
		this.usuarioServico.enviarEmailConfirmacao(this.usuario.getLogin());
		MapeamentoWebUtil.exibirMensagemSucesso("str_atencao_reenviar_email");
	}

	/**
	 * Altera o email cadastrado para o usuário e envia um novo email de
	 * confirmação.
	 */
	public void recadastrarEmail(ActionEvent actionEvent) {
		if (this.novoEmail.equalsIgnoreCase(this.repeticaoNovoEmail)) {

			if (this.usuario.getEmail().equalsIgnoreCase(this.novoEmail)) {
				MapeamentoWebUtil
						.exibirMensagemErro("cadastro_ativacao_email_jaCadastrado");
			} else {
				this.usuario.setEmail(this.novoEmail.toUpperCase());

				this.usuarioServico.alterarEmail(this.usuario.getLogin(),
						usuario.getEmail());

				this.email = this.novoEmail.toUpperCase();

				// limpa os dados do formulario depois do preenchimento
				this.novoEmail = "";
				this.repeticaoNovoEmail = "";

				MapeamentoWebUtil.exibirMensagemSucesso(
						"cadastro_ativacao_email_alterado", repeticaoNovoEmail);

				RequestContext context = RequestContext.getCurrentInstance();
				context.addCallbackParam("alterado", true);
			}

		} else {
			MapeamentoWebUtil
					.exibirMensagemErro("cadastro_ativacao_email_divegentes");
		}

	}

	/**
	 * Obtém o valor do atributo codigoAtivacao.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         codigoAtivacao.
	 */
	public String getCodigoAtivacao() {
		return this.codigoAtivacao;
	}

	/**
	 * Atualiza a instância de codigoAtivacao com o valor de codigoAtivacao.
	 * 
	 * @param codigoAtivacao
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setCodigoAtivacao(String codigoAtivacao) {
		this.codigoAtivacao = codigoAtivacao;
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
	 * Obtém o valor do atributo usuario.
	 * 
	 * @return Uma instância de {@link UsuarioBean} contendo o valor do atributo
	 *         usuario.
	 */
	public UsuarioBean getUsuario() {
		return this.usuario;
	}

	/**
	 * Atualiza a instância de usuario com o valor de usuario.
	 * 
	 * @param usuario
	 *            Uma instância de UsuarioBean contendo o valor a ser
	 *            atualizado.
	 */
	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	/**
	 * Obtém o valor do atributo novoEmail.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         novoEmail.
	 */
	public String getNovoEmail() {
		return this.novoEmail;
	}

	/**
	 * Atualiza a instância de novoEmail com o valor de novoEmail.
	 * 
	 * @param novoEmail
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setNovoEmail(String novoEmail) {
		this.novoEmail = novoEmail;
	}

	/**
	 * Obtém o valor do atributo repeticaoNovoEmail.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         repeticaoNovoEmail.
	 */
	public String getRepeticaoNovoEmail() {
		return this.repeticaoNovoEmail;
	}

	/**
	 * Atualiza a instância de repeticaoNovoEmail com o valor de
	 * repeticaoNovoEmail.
	 * 
	 * @param repeticaoNovoEmail
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setRepeticaoNovoEmail(String repeticaoNovoEmail) {
		this.repeticaoNovoEmail = repeticaoNovoEmail;
	}

	/**
	 * Obtém o valor do atributo email.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         email.
	 */
	public String getEmail() {
		return this.email;
	}

}
