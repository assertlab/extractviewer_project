/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpe.cin.cloud.mapeamento.base.AbstractDAOTestHelper;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Perfil;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.SexoEnum;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;

/**
 * Representa a implementação de testes unitários da classe {@link UsuarioDAO}.
 * 
 * @author helaine.lins
 * @created 29/08/2014 - 10:49:58
 */
public class UsuarioDAOTest extends AbstractDAOTestHelper {

	/**
	 * Representa a instância da camada de acesso à dados que está sendo
	 * testada.
	 */
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	/**
	 * Representa a instância da camada de acesso à dados  da entidade {@link Perfil}.
	 */
	@Autowired
	private IPerfilDAO perfilDAO;
	
	/**
	 * Realiza os testes de inclusão de usuários.
	 */
	@Test
	@Ignore
	public void testeIncluirUsuario() {

		try {
			
			List<Perfil> perfis = new ArrayList<Perfil>();
			
			Perfil perfilUsuario = this.perfilDAO.obterPerfilUsuario();
			perfis.add(perfilUsuario);
			
			List<Usuario> usuarios = new ArrayList<Usuario>();
			
			Usuario usu1 = new Usuario();
			usu1.setAtivo(true);
			usu1.setCodigoAtivacaoEmail("99999999");
			usu1.setEmail("helainelins@gmail.com");
			usu1.setEmailConfirmado(true);
			usu1.setLogin("helainelins");
			usu1.setNacionalidade("Brasileira");
			usu1.setNome("Helaine Barreiros");
			usu1.setSenha("643ac45d85d785e4c9eed400f809768a");
			usu1.setSexo(SexoEnum.FEMININO);
			usu1.setPerfis(perfis);
			
			usuarios.add(usu1);
			
			this.usuarioDAO.incluir(usuarios);
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeIncluirUsuario",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeIncluirUsuario.");
		}

	}
	
}
