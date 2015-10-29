/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpe.cin.cloud.mapeamento.base.AbstractDAOTestHelper;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.dao.IUsuarioDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;

/**
 * Representa a implementação unitária de testes unitários da classe {@link RevisorDAO}.
 * 
 * @author helaine.lins
 * @created 29/08/2014 - 10:47:14
 */
public class RevisorDAOTest extends AbstractDAOTestHelper {

	/**
	 * Representa a instância da camada de acesso à dados que está sendo
	 * testada.
	 */
	@Autowired
	private IRevisorDAO revisorDAO;
	
	/**
	 * Representa a instância da camada de acesso à dados da entidade {@link Usuario}.
	 */
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	/**
	 * Realiza os testes de inclusão de revisor.
	 */
	@Test
	@Ignore
	public void testeIncluirRevisor() {

		try {
			List<Revisor> revisores = new ArrayList<Revisor>();
			
			revisores.add(new Revisor(this.usuarioDAO.buscarUsuarioLoginSistema("helainelins")));
			
			this.revisorDAO.incluir(revisores);
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeIncluirRevisor",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeIncluirRevisor.");
		}

	}
	
}
