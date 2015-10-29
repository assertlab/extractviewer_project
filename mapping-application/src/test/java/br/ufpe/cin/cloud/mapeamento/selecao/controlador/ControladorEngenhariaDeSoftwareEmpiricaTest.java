/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.selecao.controlador;

import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Representa a implementação dos testes unitários da classe {@link ControladorEngenhariaDeSoftwareEmpirica}.
 * 
 * @author emanoel.barreiros
 * @created 24/07/2014 - 18:29:30
 */
public class ControladorEngenhariaDeSoftwareEmpiricaTest {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger
			.getLogger(ControladorEngenhariaDeSoftwareEmpiricaTest.class.getName());
	
	private ControladorEngenhariaDeSoftwareEmpirica ctrl = new ControladorEngenhariaDeSoftwareEmpirica();
	
	/**
	 * Representa a implementação de testes unitários do método que realiza o parser do arquivo bibtex das referencias.
	 */
	@Test
	public void testExecutarDiagnosticoBibTexArquivosSelecionados() {
		try {
			ctrl.executarDiagnosticoBibTexArquivosSelecionados();
		} catch (Exception e) {
			LOG.error("Deveria ter executado o teste testExecutarDiagnosticoBibTexArquivosSelecionados sem falhas", e);
			fail("Falhou ao executar o teste testExecutarDiagnosticoBibTexArquivosSelecionados");
		}
	}
	
	/**
	 * Representa a implementação de testes unitários do método que realiza o parser do arquivo bibtex das referencias.
	 */
	@Test
	public void testTratarBibTexArquivosSelecionados() {
		try {
			ctrl.tratarBibTexArquivosSelecionados();
		} catch (Exception e) {
			LOG.error("Deveria ter executado o teste testTratarBibTexArquivosSelecionados sem falhas", e);
			fail("Falhou ao executar o teste testTratarBibTexArquivosSelecionados");
		}
	}
	
}
