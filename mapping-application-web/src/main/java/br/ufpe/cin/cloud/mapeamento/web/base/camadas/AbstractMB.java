/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.base.camadas;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.RevisorBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IRevisorServico;
import br.ufpe.cin.cloud.mapeamento.web.base.util.MapeamentoWebUtil;

/**
 * Representa a hierarquia principal das classes controllers da camada web da
 * aplicação.
 * 
 * @author helaine.lins
 * @created 09/04/2014 - 00:32:35
 */
public abstract class AbstractMB implements Serializable {

	/**
	 * Representa a mnensagem de erro da aplicação ao realizar o acesso através
	 * de url explicitamente digitada no navegador.
	 */
	protected static final String MSG_LOGIN_ERRO_ACESSO_POR_URL = "str_login_erro";

	/**
	 * Representa a chave do parametro de dados do usuário logado.
	 */
	protected static final String PARAM_USUARIO = "usuario";

	/**
	 * Representa expressão regular para remover caracteres que não são dígitos.
	 */
	protected static final String REGEX_NAO_DIGITOS = "\\D";

	/**
	 * Representa a ação do spring que realiza a autenticação de usuário no
	 * sistema.
	 */
	private static final String J_SPRING_SECURITY_LOGOUT = "/j_spring_security_logout?faces-redirect=true";

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static final Log LOG = LogFactory.getLog(AbstractMB.class);

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 3151186258354640294L;

	@ManagedProperty(value = "#{revisorServico}")
	private IRevisorServico revisorServico;

	/**
	 * Contrato de implementação entre os ManagedBeans, para que a implementação
	 * do método seja chamada logo na inicialização da view correspondente ao
	 * MB.
	 */
	public abstract void carregarPagina();

	/**
	 * Recupera o identificador do usuário logado no sistema.
	 * 
	 * @return Um {@link Long} contendo o identificador do usuário, caso não
	 *         exista usuário autenticado retorna <code>null</code>.
	 */
	public Long getIdUsuarioLogado() {
		Long id = null;

		UsuarioBean usuario = MapeamentoWebUtil.obterUsuarioLogado();
		if (usuario != null) {
			id = usuario.getId();
		}

		return id;
	}

	/**
	 * Recupera o login do usuário logado no sistema.
	 * 
	 * @return Uma {@link String} contendo o login do usuário, caso não exista
	 *         usuário autenticado retorna <code>null</code>.
	 */
	public String getLoginUsuarioLogado() {
		String login = null;

		UsuarioBean usuario = MapeamentoWebUtil.obterUsuarioLogado();
		if (usuario != null) {
			login = usuario.getLogin();
		}

		return login;
	}

	/**
	 * Faz a navegação da página para o handle definido no faces config para
	 * esta página.
	 * 
	 * @param navigationHandle
	 *            o navigation case para o qual a página será redirecionada.
	 */
	public void redirecionarPagina(String navigationHandle) {
		if (!StringUtils.isEmpty(navigationHandle)) {
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getApplication().getNavigationHandler()
					.handleNavigation(fc, null, navigationHandle);
		}
	}

	/**
	 * Recupera os dados do usuário logado no sistema.
	 * 
	 * @return A instância contendo os dados do {@link UsuarioBean} logado no
	 *         sistema.
	 */
	public UsuarioBean getUsuarioLogado() {
		return MapeamentoWebUtil.obterUsuarioLogado();
	}

	/**
	 * Recupera os dados do usuário logado no sistema.
	 * 
	 * @return A instância contendo os dados do {@link RevisorBean} logado no
	 *         sistema.
	 */
	public RevisorBean getRevisorLogado() {
		UsuarioBean usuario = MapeamentoWebUtil.obterUsuarioLogado();
		return this.revisorServico.recuperarRevisor(usuario.getLogin());
	}

	/**
	 * Redireciona a requisição para o Spring security realize o logout do
	 * usuário finalizando a sessão e redirecionando para a página de login do
	 * sistema. Os botões que realizarem chamadas a estas ações devem ter a ação
	 * do tipo action e o ajax=false.
	 */
	public void realizarLogout() {

		try {

			RequestDispatcher dispatcher = MapeamentoWebUtil
					.getServletRequest().getRequestDispatcher(
							J_SPRING_SECURITY_LOGOUT);

			dispatcher.forward(MapeamentoWebUtil.getServletRequest(),
					MapeamentoWebUtil.getServletResponse());
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			LOG.error("Ocorreu um erro ao finalizar sessao de usuario:"
					+ getIdUsuarioLogado(), e);
			MapeamentoWebUtil.exibirMensagemErro("str_logout_erro");
		}

	}

	/**
	 * Obtém o valor do atributo revisorServico.
	 * 
	 * @return Uma instância de {@link IRevisorServico} contendo o valor do
	 *         atributo revisorServico.
	 */
	public IRevisorServico getRevisorServico() {
		return this.revisorServico;
	}

	/**
	 * Atualiza a instância de revisorServico com o valor de revisorServico.
	 * 
	 * @param revisorServico
	 *            Uma instância de IRevisorServico contendo o valor a ser
	 *            atualizado.
	 */
	public void setRevisorServico(IRevisorServico revisorServico) {
		this.revisorServico = revisorServico;
	}

}
