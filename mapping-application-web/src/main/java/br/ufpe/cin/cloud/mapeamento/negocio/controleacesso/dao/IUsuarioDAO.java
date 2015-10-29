/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.dao;

import java.util.List;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;

/**
 * Representa a definição dos serviços da camada de acesso à dados da entidade
 * {@link UsuarioBean} .
 * 
 * @author helaine.lins
 * @created 15/04/2014 - 17:08:53
 */
public interface IUsuarioDAO extends IDao<Usuario> {

	/**
	 * Realiza uma busca por login na tabela de usuários.
	 * 
	 * @param login
	 *            O login que deseja aplicar como filtro.
	 * @param id O identificador para os casos de alteração.
	 * @return Retorna <code>true</code> caso exista um usuário com o login, caso
	 *         contrário retorna <code>false</code>.
	 */
	boolean existeUsuarioLogin(String login, Long id);

	/**
	 * Realiza a busca de um usuário do sistema utilizando o login informado.
	 * 
	 * @param login
	 *            O login do usuário.
	 * @return Uma instância de {@link UsuarioBean} que corresponde ao login informado
	 *         ou <code>null</code> caso o mesmo não seja encontrado.
	 */
	Usuario buscarUsuarioLoginSistema(String login);
	
	/**
	 * Realiza a busca de um usuário do sistema utilizando o nome informado.
	 * 
	 * @param nome
	 *            O nome do usuário.
	 * @return Uma {@link List} de {@link UsuarioBean} que corresponde ao nome informado
	 *         ou <code>null</code> caso o mesmo não seja encontrado.
	 */
	List<Usuario> buscarUsuarioNome(String nome);
	
}
