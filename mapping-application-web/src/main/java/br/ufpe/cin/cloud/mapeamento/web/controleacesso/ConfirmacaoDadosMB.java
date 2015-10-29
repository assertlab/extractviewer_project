/**
 * SiVest - Sistema de Inscrição do Vestibular da UPE
 * 
 * Copyright (c) Universidade Federal de Pernambuco.
 * 
 * Este software é confidencial e propriedade da UPE. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do UPE. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.controleacesso;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.IUsuarioServico;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.UsuarioServico;
import br.ufpe.cin.cloud.mapeamento.web.base.camadas.AbstractMB;
import br.ufpe.cin.cloud.mapeamento.web.base.util.MapeamentoWebUtil;

/**
 * Bean Gerenciável correspondente a tela de confirmação dos dados cadastrais do candidato.
 * 
 * @author Cleber Alberto
 * @created 17/04/2012 - 13:24:50
 */
@ViewScoped
@ManagedBean(name = "confirmacaoDadosMB")
public class ConfirmacaoDadosMB extends AbstractMB {

    /**
     * Identificador serialVersionUID da classe.
     */
    private static final long serialVersionUID = -4280527789971885492L;

    /**
     * Representa o container dos dados do candidato, preenchido em tela.
     */
    private UsuarioBean containerDados;

    /**
     * Representa a entidade com os dados preenchidos.
     */
    private Usuario usuario;

    /**
     * Representa a instância da camada de servico {@link UsuarioServico}.
     */
    @ManagedProperty(value = "#{usuarioServico}")
    private IUsuarioServico usuarioServico;


    private Boolean alterar;

    /**
     * {@inheritDoc}.
     * 
     * @see br.upe.sivest.web.base.camadas.AbstractMB#carregarPagina()
     */
    @Override
    public void carregarPagina() {
        if (!MapeamentoWebUtil.isPostBack()) {
            //ESTE ATRIBUTO VEM DA SESSAO POR CONTA DE BUG DO MOJORRARA AO ALTERAR DE PASTA https://gist.github.com/1847155
            this.alterar = MapeamentoWebUtil.obterAtributoSessao("alterar", Boolean.class);
            this.containerDados = MapeamentoWebUtil.getAtributoFlash("dados", UsuarioBean.class);
            this.usuario = MapeamentoWebUtil.getAtributoFlash("usuario", Usuario.class);

        }

        if (this.containerDados == null) {
            redirecionarPagina("alterarDados");
        }
    }

    /**
     * Redireciona para a view correspondente a edição dos dados de confirmação.
     * 
     * @return {@link String} correspondente ao nome da view correspondente a edição dos dados de confirmação.
     */
    public String alterarDados() {
        MapeamentoWebUtil.addAtributoFlash("dados", this.containerDados);
        return "alterarDados";
    }

    /**
     * Redireciona para a view correspondente à página de confirmação do cadastro por código de ativação.
     * 
     * @return
     */
    public String confirmarDados() {
        String retorno = null;

        if (alterar != null && alterar) {
            this.usuarioServico.alterar(this.usuario.getBean());

            //REMOVE IMEDIATAMENTE OS ATRIBUTOS DA SESSAO, ISTO ESTAH ASSIM POR CONTA DO BUG DO MOJORRARA.
            MapeamentoWebUtil.removerAtributoSessao("alterar");
            MapeamentoWebUtil.removerAtributoSessao("containerDados");

            retorno = "resumoCandidato";
        } else {
        	this.usuario.setSenha(containerDados.getSenha());
            this.usuarioServico.incluir(this.usuario.getBean());

            MapeamentoWebUtil.addAtributoFlash("usuario", this.usuario);
            retorno = "confirmacaoCodigoAtivacao";
        }

        MapeamentoWebUtil.addAtributoFlash("dados", this.containerDados);
        return retorno;
    }

    public String cancelar() {
        //REMOVE IMEDIATAMENTE OS ATRIBUTOS DA SESSAO, ISTO ESTAH ASSIM POR CONTA DO BUG DO MOJORRARA.
        MapeamentoWebUtil.removerAtributoSessao("alterar");
        MapeamentoWebUtil.removerAtributoSessao("containerDados");

        return "resumoCandidato";
    }

    

    /**
     * Obtém o valor do atributo containerDados.
     * 
     * @return Uma instância de {@link UsuarioBean} contendo o valor do atributo containerDados.
     */
    public UsuarioBean getContainerDados() {
        return this.containerDados;
    }

    /**
     * Obtém o valor do atributo usuarioServico.
     * 
     * @return Uma instância de {@link IUsuarioServico} contendo o valor do atributo usuarioServico.
     */
    public IUsuarioServico getUsuarioServico() {
        return this.usuarioServico;
    }

    /**
     * Atualiza a instância de usuarioServico com o valor de usuarioServico.
     * 
     * @param usuarioServico Uma instância de IUsuarioServico contendo o valor a ser atualizado.
     */
    public void setUsuarioServico(IUsuarioServico usuarioServico) {
        this.usuarioServico = usuarioServico;
    }

    /**
     * Obtém o valor do atributo alterar.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo alterar.
     */
    public Boolean getAlterar() {
        return this.alterar;
    }

    /**
     * Atualiza a instância de alterar com o valor de alterar.
     * 
     * @param alterar Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setAlterar(Boolean alterar) {
        this.alterar = alterar;
    }

}
