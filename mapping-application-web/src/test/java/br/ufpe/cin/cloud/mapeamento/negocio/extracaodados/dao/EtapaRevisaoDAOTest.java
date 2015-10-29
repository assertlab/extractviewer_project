/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpe.cin.cloud.mapeamento.base.AbstractDAOTestHelper;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaBuscaEstudosPorConferencia;

/**
 * Representa a implementação de testes unitários da classe {@link EtapaAnaliseDAO}
 * 
 * @author helaine.lins
 * @created 04/09/2014 - 20:10:58
 */
public class EtapaRevisaoDAOTest extends AbstractDAOTestHelper {

	/**
	 * Representa a instância da camada de acesso à dados que está sendo
	 * testada.
	 */
	@Autowired
	private IEtapaAnaliseDAO etapaRevisaoDAO;
	
	/**
	 * Realiza os testes da consulta de estudos por origem e categoria.
	 */
	@Test
	public void testeListarBuscasPorOrigemECategoria() {

		try {
			List<EstatisticaBuscaEstudosPorConferencia> resultado = this.etapaRevisaoDAO.listarBuscasPorOrigemECategoria(2l);
			
			assertNotNull("Deveria ter encontrado resultados", resultado);
			
			for (EstatisticaBuscaEstudosPorConferencia estatistica : resultado) {
				if (estatistica != null) {
					LOG.info(estatistica);
				}
			}
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeColetarCriteriosAplicados",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeColetarCriteriosAplicados.");
		}

	}
	
	/**
	 * Realiza os testes da consulta de estudos por categoria.
	 */
	@Test
	public void testeListarBuscasPorCategoria() {
		
		try {
			List<EstatisticaBuscaEstudosPorConferencia> resultado = this.etapaRevisaoDAO.listarBuscasPorCategoria(2l);
			
			assertNotNull("Deveria ter encontrado resultados", resultado);
			
			for (EstatisticaBuscaEstudosPorConferencia estatistica : resultado) {
				if (estatistica != null) {
					LOG.info(estatistica);
				}
			}
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeListarBuscasPorCategoria",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeListarBuscasPorCategoria.");
		}
		
	}
	
	/**
	 * Realiza os testes da consulta de etudos por engenho.
	 */
	@Test
	public void testeListarBuscasPorEngenho() {
		
		try {
			List<EstatisticaBuscaEstudosPorConferencia> resultado = this.etapaRevisaoDAO.listarBuscasPorEngenho(2l);
			
			assertNotNull("Deveria ter encontrado resultados", resultado);
			
			for (EstatisticaBuscaEstudosPorConferencia estatistica : resultado) {
				if (estatistica != null) {
					LOG.info(estatistica);
				}
			}
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeListarBuscasPorEngenho",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeListarBuscasPorEngenho.");
		}
		
	}
	
}
