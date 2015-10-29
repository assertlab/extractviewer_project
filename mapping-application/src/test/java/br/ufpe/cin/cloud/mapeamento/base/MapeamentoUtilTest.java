/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.base;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Representa a implementação dos testes unitários da classe
 * {@link MapeamentoUtil}.
 * 
 * @author helaine.lins
 * @created 29/07/2014 - 11:12:21
 */
public class MapeamentoUtilTest {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger.getLogger(MapeamentoUtilTest.class
			.getName());

	/**
	 * Realiza o teste unitário do método de recuperar mensagens do arquivo de properties.
	 */
	@Test
	public void testRecuperarMensagemProperties() {
		try {
			String valor = MapeamentoUtil.recuperarMensagemProperties("config.diretorio.destino.ese");
			assertEquals("Deveria ter encontrado o valor da propriedade config.diretorio.destino.ese no arquivo de properties", "C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/ESE/selecionados", valor);
		} catch (Exception e) {
			LOG.error(
					"Deveria ter executado o teste testCopiarArquivo sem falhas",
					e);
			fail("Falhou ao executar o teste testCopiarArquivo");
		}
	}
	
	/**
	 * Realiza o teste unitário do método copiarArquivo.
	 */
	@Test
	public void testCopiarArquivo() {
		try {
			File arqOrigem = new File(
					"C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/artigos/Runeson, Wiberg/Runeson, Wiberg - 2005 - … Research and Practice in Sweden, 2005.pdf");
			File dirDest = new File(
					"C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/ESE/selecionados");
			boolean copiado = MapeamentoUtil.copiarArquivo(true, arqOrigem,
					dirDest);

			assertTrue(
					"deveria ter copiado o arquivo para o diretorio de destino.",
					copiado);
		} catch (Exception e) {
			LOG.error(
					"Deveria ter executado o teste testCopiarArquivo sem falhas",
					e);
			fail("Falhou ao executar o teste testCopiarArquivo");
		}
	}
	
	/**
	 * Realiza o teste unitário do método de renomear arquivo.
	 */
	@Test
	public void testRenomearArquivo() {
		try {
			File arqOrigem = new File("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/ESE/selecionados/Runeson, Wiberg - 2005 - … Research and Practice in Sweden, 2005.pdf");
			boolean renomeado = MapeamentoUtil.renomearArquivo(true, arqOrigem, "MESE_01.pdf");
			
			assertTrue(
					"deveria ter renomeado o arquivo para MESE_01.pdf",
					renomeado);
		} catch (Exception e) {
			LOG.error(
					"Deveria ter executado o teste testRenomearArquivo sem falhas",
					e);
			fail("Falhou ao executar o teste testRenomearArquivo");
		}
	}
	
	
}
