/**
 * SiVest - Sistema de Inscrição do Vestibular da UPE
 * 
 * Copyright (c) Universidade Federal de Pernambuco.
 * 
 * Este software é confidencial e propriedade da UPE. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do UPE. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.controleacesso;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.SexoEnumBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.IUsuarioServico;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.UsuarioServico;
import br.ufpe.cin.cloud.mapeamento.web.base.camadas.AbstractMB;
import br.ufpe.cin.cloud.mapeamento.web.base.util.MapeamentoWebUtil;

/**
 * Bean Gerenciável correspondente ao cadastro de novos usuarios.
 * 
 * @author helaine.lins
 * @created 17/04/2012 - 13:24:50
 */
@ViewScoped
@ManagedBean(name = "cadastroMB")
public class CadastroMB extends AbstractMB {

	/**
	 * Identificador serialVersionUID da classe.
	 */
	private static final long serialVersionUID = -4280527789971885492L;

	/**
	 * Representa a instância da camada de servico {@link UsuarioServico}.
	 */
	@ManagedProperty(value = "#{usuarioServico}")
	private IUsuarioServico usuarioServico;

	/**
	 * Representa o container dos dados do candidato, preenchido em tela.
	 */
	private UsuarioBean containerDados;

	/**
	 * Representa os possíveis sexos adotados pela aplicação.
	 */
	private List<SelectItem> itensSexos;

	private Boolean alterar;

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.upe.sivest.web.base.camadas.AbstractMB#carregarPagina()
	 */
	@Override
	public void carregarPagina() {

		if (!FacesContext.getCurrentInstance().isPostback()) {

			// BUG DO MOJORRARA https://gist.github.com/1847155
			this.alterar = MapeamentoWebUtil.obterAtributoSessao("alterar",
					Boolean.class);

			if (alterar != null && alterar) {
				this.containerDados = MapeamentoWebUtil.obterAtributoSessao(
						"containerDados", UsuarioBean.class);
			} else {
				// parâmetro de outra página
				this.containerDados = MapeamentoWebUtil.getAtributoFlash(
						"dados", UsuarioBean.class);
			}

			// se nao veio parametro de nenhuma outra página é inclusão.
			if (this.containerDados == null) {
				this.containerDados = new UsuarioBean();
			}

			this.itensSexos = MapeamentoWebUtil
					.montarSelectItems(SexoEnumBean.MASCULINO);
		}

	}

	public String cancelar() {
		// REMOVE IMEDIATAMENTE OS ATRIBUTOS DA SESSAO, ISTO ESTAH ASSIM POR
		// CONTA DO BUG DO MOJORRARA.
		MapeamentoWebUtil.removerAtributoSessao("alterar");
		MapeamentoWebUtil.removerAtributoSessao("containerDados");

		return "resumoCandidato";
	}

	/**
	 * Armazena os dados do candidato e redireciona para a página de confirmação
	 * dos dados.
	 * 
	 * @return {@link String} contendo o nome da view que representa a tela de
	 *         confirmação dos dados.
	 */
	public String avancarQuestionario() {
		String retorno = null;
		boolean valido = true;

		if (!this.containerDados.getEmail().equalsIgnoreCase(
				this.containerDados.getEmailConfirmacao())) {
			valido = false;
			MapeamentoWebUtil
					.exibirMensagemErro("cadastro_ativacao_email_divegentes");
		}

		if (valido) {
			UsuarioBean usuario = this.containerDados;

			if (alterar != null && alterar) {
				this.usuarioServico.validarAlteracaoUsuario(usuario);
			} else {
				this.usuarioServico.validarInclusaoUsuario(usuario);
			}

			MapeamentoWebUtil.addAtributoFlash("dados", this.containerDados);
			MapeamentoWebUtil.addAtributoFlash("usuario", usuario);
			MapeamentoWebUtil.addAtributoFlash("alteracao", alterar);

			retorno = "confirmacao";
		}

		return retorno;
	}

	/**
	 * Obtém o valor do atributo alterar.
	 * 
	 * @return Uma instância de {@link Boolean} contendo o valor do atributo
	 *         alterar.
	 */
	public Boolean getAlterar() {
		return this.alterar;
	}

	/**
	 * Atualiza a instância de alterar com o valor de alterar.
	 * 
	 * @param alterar
	 *            Uma instância de Boolean contendo o valor a ser atualizado.
	 */
	public void setAlterar(Boolean alterar) {
		this.alterar = alterar;
	}

	/**
	 * Obtém o valor do atributo containerDados.
	 * 
	 * @return Uma instância de {@link UsuarioBean} contendo o valor do atributo
	 *         containerDados.
	 */
	public UsuarioBean getContainerDados() {
		return this.containerDados;
	}

	/**
	 * Atualiza a instância de containerDados com o valor de containerDados.
	 * 
	 * @param containerDados
	 *            Uma instância de UsuarioBean contendo o valor a ser
	 *            atualizado.
	 */
	public void setContainerDados(UsuarioBean containerDados) {
		this.containerDados = containerDados;
	}

	/**
	 * Obtém o valor do atributo itensSexos.
	 * 
	 * @return Uma instância de {@link List<SelectItem>} contendo o valor do
	 *         atributo itensSexos.
	 */
	public List<SelectItem> getItensSexos() {
		return this.itensSexos;
	}

	/**
	 * Atualiza a instância de itensSexos com o valor de itensSexos.
	 * 
	 * @param itensSexos
	 *            Uma instância de List<SelectItem> contendo o valor a ser
	 *            atualizado.
	 */
	public void setItensSexos(List<SelectItem> itensSexos) {
		this.itensSexos = itensSexos;
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

}
