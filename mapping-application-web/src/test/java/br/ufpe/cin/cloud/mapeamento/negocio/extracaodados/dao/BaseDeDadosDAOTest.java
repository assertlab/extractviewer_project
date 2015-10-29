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

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpe.cin.cloud.mapeamento.base.AbstractDAOTestHelper;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BaseDeDadosBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IBaseDeDadosServico;

/**
 * Representa a implementação dos testes unitários da classe
 * {@link BaseDeDadosDAO}
 * 
 * @author helaine.lins
 * @created 19/08/2014 - 10:58:13
 */
public class BaseDeDadosDAOTest extends AbstractDAOTestHelper {

	/**
	 * Representa a instância da camada de acesso à dados que está sendo
	 * testada.
	 */
	@Autowired
	private IBaseDeDadosServico baseDadosDAO;

	/**
	 * Realiza os testes de inclusão da base de dados Mista
	 */
	@Test
	@Ignore
	public void testeIncluirBaseDadosMista() {

		try {
			BaseDeDadosBean base = new BaseDeDadosBean();

			base.setNome("Mista");
			base.setUrl("http://www.google.com/");

			this.baseDadosDAO.incluir(base);
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeIncluirBaseDadosMista",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeIncluirBaseDadosMista.");
		}

	}
	
	/**
	 * Realiza os testes de inclusão da base de dados Scopus
	 */
	@Test
	@Ignore
	public void testeIncluirBaseDadosScopus() {
		
		try {
			BaseDeDadosBean base = new BaseDeDadosBean();
			
			base.setNome("Scopus");
			base.setUrl("http://www.scopus.com/");
			
			this.baseDadosDAO.incluir(base);
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeIncluirBaseDadosScopus",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeIncluirBaseDadosScopus.");
		}
		
	}

	/**
	 * Realiza os testes de inclusão da base de dados ScienceDirect
	 */
	@Test
	@Ignore
	public void testeIncluirBaseDadosScienceDirect() {

		try {
			BaseDeDadosBean base = new BaseDeDadosBean();

			base.setNome("Science Direct");
			base.setUrl("http://www.sciencedirect.com/");

			this.baseDadosDAO.incluir(base);
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeIncluirBaseDadosScienceDirect",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeIncluirBaseDadosScienceDirect.");
		}

	}

	/**
	 * Realiza os testes de inclusão da base de dados ACM.
	 */
	@Test
	@Ignore
	public void testeIncluirBaseDadosACM() {

		try {
			BaseDeDadosBean base = new BaseDeDadosBean();

			base.setNome("ACM Digital Library");
			base.setUrl("http://dl.acm.org/");

			this.baseDadosDAO.incluir(base);
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeIncluirBaseDadosACM",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeIncluirBaseDadosACM.");
		}

	}

	/**
	 * Realiza os testes de inclusão da base de dados IEEExplorer
	 */
	@Test
	@Ignore
	public void testeIncluirBaseDadosIEEEXplorer() {

		try {
			BaseDeDadosBean base = new BaseDeDadosBean();

			base.setNome("IEEE Xplorer Digital Library");
			base.setUrl("http://ieeexplore.ieee.org/Xplore/home.jsp");

			this.baseDadosDAO.incluir(base);
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeIncluirBaseDadosIEEEXplorer",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeIncluirBaseDadosIEEEXplorer.");
		}

	}
}
