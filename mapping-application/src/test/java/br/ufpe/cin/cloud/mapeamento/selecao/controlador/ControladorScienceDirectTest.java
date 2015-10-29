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
 * Representa a implementação de testes unitários da classe
 * {@link ControladorScienceDirect}.
 * 
 * @author helaine.lins
 * @created 04/08/2014 - 10:48:59
 */
public class ControladorScienceDirectTest {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger
			.getLogger(ControladorIEEEXplorerTest.class.getName());

	private ControladorScienceDirect ctrl = new ControladorScienceDirect();

	/**
	 * Realiza o teste do método executarDiagnosticoBibTexArquivosSelecionados.
	 */
	@Test
	public void testeExecutarDiagnosticoBibTexArquivosSelecionados() {
		try {
			ctrl.executarDiagnosticoBibTexArquivosSelecionados();
		} catch (Exception e) {
			LOG.error(
					"Deveria ter executado o teste testeExecutarDiagnosticoBibTexArquivosSelecionados sem falhas",
					e);
			fail("Falhou ao executar o teste testeExecutarDiagnosticoBibTexArquivosSelecionados");
		}
	}
	
	/**
	 * Realiza o teste do método tratarBibTexArquivosSelecionados.
	 */
	@Test
	public void testeTratarBibTexArquivosSelecionados() {
		try {
			ctrl.tratarBibTexArquivosSelecionados();
		} catch (Exception e) {
			LOG.error(
					"Deveria ter executado o teste tratarBibTexArquivosSelecionados sem falhas",
					e);
			fail("Falhou ao executar o teste tratarBibTexArquivosSelecionados");
		}
	}

}
