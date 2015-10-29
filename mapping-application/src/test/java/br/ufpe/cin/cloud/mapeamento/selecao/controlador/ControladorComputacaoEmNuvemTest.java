/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.selecao.controlador;

import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Representa FIXME: completar javadoc
 * 
 * @author helaine.lins
 * @created 15/08/2014 - 14:48:23
 */
public class ControladorComputacaoEmNuvemTest {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger
			.getLogger(ControladorComputacaoEmNuvemTest.class.getName());
	
	private ControladorComputacaoEmNuvem ctrl = new ControladorComputacaoEmNuvem();
	
	/**
	 * Representa a implementação de testes unitários do método que realiza o parser dos arquivos da busca manual do TPDS.
	 */
	@Test
	public void testExecutarDiagnosticoBuscaManualTPDS() {
		try {
			ctrl.executarDiagnosticoBuscaManualTPDS();
		} catch (Exception e) {
			LOG.error("Deveria ter executado o teste testExecutarDiagnosticoBuscaManualTPDS sem falhas", e);
			fail("Falhou ao executar o teste testExecutarDiagnosticoBuscaManualTPDS");
		}
	}
	
	/**
	 * Representa a implementação de testes unitários do método que realiza o parser dos arquivos da busca manual do FGCS.
	 */
	@Test
	public void testExecutarDiagnosticoBuscaManualFGCS() {
		try {
			ctrl.executarDiagnosticoBuscaManualFGCS();
		} catch (Exception e) {
			LOG.error("Deveria ter executado o teste testExecutarDiagnosticoBuscaManualFGCS sem falhas", e);
			fail("Falhou ao executar o teste testExecutarDiagnosticoBuscaManualFGCS");
		}
	}
	
	/**
	 * Representa a implementação de testes unitários do método que realiza o parser dos arquivos da busca manual do IPDPS.
	 */
	@Test
	public void testExecutarDiagnosticoBuscaManualIPDPS() {
		try {
			ctrl.executarDiagnosticoBuscaManualIPDPS();
		} catch (Exception e) {
			LOG.error("Deveria ter executado o teste testExecutarDiagnosticoBuscaManualIPDPS sem falhas", e);
			fail("Falhou ao executar o teste testExecutarDiagnosticoBuscaManualIPDPS");
		}
	}
	
	/**
	 * Representa a implementação de testes unitários do método que realiza o parser dos arquivos da busca manual do TPDS.
	 */
	@Test
	public void testTratarBuscaManualTPDS() {
		try {
			ctrl.tratarBuscaManualTPDS();
		} catch (Exception e) {
			LOG.error("Deveria ter executado o teste testTratarBuscaManualTPDS sem falhas", e);
			fail("Falhou ao executar o teste testTratarBuscaManualTPDS");
		}
	}
	
	/**
	 * Representa a implementação de testes unitários do método que realiza o parser dos arquivos da busca manual do FGCS.
	 */
	@Test
	public void testTratarBuscaManualFGCS() {
		try {
			ctrl.tratarBuscaManualFGCS();
		} catch (Exception e) {
			LOG.error("Deveria ter executado o teste testTratarBuscaManualFGCS sem falhas", e);
			fail("Falhou ao executar o teste testTratarBuscaManualFGCS");
		}
	}
	
	/**
	 * Representa a implementação de testes unitários do método que realiza o parser dos arquivos da busca manual do IPPDS.
	 */
	@Test
	public void testTratarBuscaManualIPPDS() {
		try {
			ctrl.tratarBuscaManualIPDPS();
		} catch (Exception e) {
			LOG.error("Deveria ter executado o teste testTratarBuscaManualIPPDS sem falhas", e);
			fail("Falhou ao executar o teste testTratarBuscaManualIPPDS");
		}
	}
	
}
