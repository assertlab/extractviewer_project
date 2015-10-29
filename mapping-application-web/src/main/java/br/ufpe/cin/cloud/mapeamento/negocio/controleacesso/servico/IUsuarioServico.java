/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;

/**
 * Interface para o servico de {@link Usuario}.
 * 
 * @author helaine.lins
 * @created 22/04/2014 - 19:52:22
 */
public interface IUsuarioServico extends IServico<Usuario, UsuarioBean> {

	/**
     * Realiza a alteração de email para usuário cadastrado gerando um novo código de ativação.
     * 
     * @param login O número do login do usuário para o qual se deseja alterar o email.
     * @param novoEmail O novo email do usuário.
     */
    void alterarEmail(String login, String novoEmail);
	
	void enviarEmailConfirmacao(String login);
	
	/**
     * Realiza a busca de usuário por login
     * 
     * @param login O login para buscar.
     * @param codigo O código de ativação.
     */
    void ativarCadastro(String login, String codigo);
	
	UsuarioBean buscarUsuarioLogin(String login);
	
	/**
	 * Valida os dados para inclusão de usuário no sistema.
	 * 
	 * @param bean
	 *            A instância que contém os dados do usuário a ser incluído no
	 *            sistema.
	 */
	void validarInclusaoUsuario(UsuarioBean bean);

	/**
	 * Valida os dados para alteracao de usuário no sistema.
	 * 
	 * @param bean
	 *            A instância que contém os dados do usuário a ser alterado no
	 *            sistema.
	 */
	void validarAlteracaoUsuario(UsuarioBean bean);

	/**
	 * Realiza a alteração dos dados do usuário no sistema.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico#alterar(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean)
	 * 
	 * @param bean A instância que contém os dados a serem alterados no sistema.
	 * @param admin
	 *            Indica se a alteração está sendo feita pelo administrador do
	 *            sistema.
	 */
	void alterar(UsuarioBean bean, boolean admin);
}
