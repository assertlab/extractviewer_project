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

/**
 * Representa à implementação de testes unitários da classe {@link PerfilDAO}.
 * 
 * @author helaine.lins
 * @created 29/08/2014 - 11:03:41
 */
public class PerfilDAOTest extends AbstractDAOTestHelper {

	/**
	 * Representa a instância da camada de acesso à dados que está sendo
	 * testada.
	 */
	@Autowired
	private IPerfilDAO perfilDAO;
	
	/**
	 * Realiza os testes de inclusão de perfil.
	 */
	@Test
	@Ignore
	public void testeIncluirPerfil() {

		try {
			
			List<Perfil> perfis = new ArrayList<Perfil>();
			
			perfis.add(new Perfil("ROLE_USUARIO"));
			perfis.add(new Perfil("ROLE_ADMINISTRADOR"));
			
			this.perfilDAO.incluir(perfis);
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeIncluirPerfil",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeIncluirPerfil.");
		}

	}
	
}
