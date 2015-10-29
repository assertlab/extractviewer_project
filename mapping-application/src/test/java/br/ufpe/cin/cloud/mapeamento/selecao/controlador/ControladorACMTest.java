/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.selecao.controlador;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Test;

import br.ufpe.cin.cloud.mapeamento.selecao.modelo.Estudo;

/**
 * Representa a implementação dos testes unitários da classe {@link ControladorACM}.
 * 
 * @author helaine.lins
 * @created 31/07/2014 - 13:57:07
 */
public class ControladorACMTest {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger
			.getLogger(ControladorACMTest.class.getName());
	
	/**
	 * Representa a instância do controlador que está sendo testado.
	 */
	private ControladorACM ctrl = new ControladorACM();
	
	
	/**
	 * Realiza o teste do método recuperarUrlArquivoPdf.
	 */
	@Test
	public void testeRecuperarUrlArquivoPdf() {
		try {
			Estudo estudo = this.montarEstudo();
			ctrl.recuperarUrlArquivoPdf(estudo, true);
			
			assertEquals("http://dl.acm.org/ft_gateway.cfm?id=2499981&type=pdf", estudo.getArquivo());
		} catch (Exception e) {
			LOG.error("Deveria ter executado o teste testeRecuperarUrlArquivoPdf sem falhas", e);
			fail("Falhou ao executar o teste testeRecuperarUrlArquivoPdf");
		}
	}
	
	/**
	 * Realiza o teste do método executarDiagnosticoBibTexArquivosSelecionados.
	 */
	@Test
	public void testeExecutarDiagnosticoPlanilhaArquivosSelecionados() {
		try {
			ctrl.executarDiagnosticoPlanilhaArquivosSelecionados();
		} catch (Exception e) {
			LOG.error("Deveria ter executado o teste testeExecutarDiagnosticoPlanilhaArquivosSelecionados sem falhas", e);
			fail("Falhou ao executar o teste testeExecutarDiagnosticoPlanilhaArquivosSelecionados");
		}
	}
	
	/**
	 * Realiza o teste do método preencherPlanilha.
	 */
	@Test
	public void testePreencherPlanilha() {
		try {
			ArrayList<Estudo> estudos = new ArrayList<Estudo>();
			estudos.add(this.montarEstudo());
			estudos.add(this.montarEstudo());
			
			ctrl.preencherPlanilha(estudos);
		} catch (Exception e) {
			LOG.error("Deveria ter executado o teste preencherPlanilha sem falhas", e);
			fail("Falhou ao executar o teste preencherPlanilha");
		}
	}
	
	/**
	 * Realiza o teste do método tratarArquivosSelecionados.
	 */
	@Test
	public void testeTratarArquivosSelecionados() {
		try {
			
			ctrl.tratarArquivosSelecionados();
//			ctrl.tratarArquivosSelecionados(51,100);
//			ctrl.tratarArquivosSelecionados(101,150);
//			ctrl.tratarArquivosSelecionados(151,200);
//			ctrl.tratarArquivosSelecionados(201,250);
//			ctrl.tratarArquivosSelecionados(251,300);
//			ctrl.tratarArquivosSelecionados(301,350);
//			ctrl.tratarArquivosSelecionados(351,400);
//			ctrl.tratarArquivosSelecionados(401,450);
//			ctrl.tratarArquivosSelecionados(451,500);
//			
//			ctrl.tratarArquivosSelecionados(501,550);
//			ctrl.tratarArquivosSelecionados(551,600);
//			ctrl.tratarArquivosSelecionados(601,650);
//			ctrl.tratarArquivosSelecionados(651,700);
//			ctrl.tratarArquivosSelecionados(701,750);
//			ctrl.tratarArquivosSelecionados(751,800);
//			ctrl.tratarArquivosSelecionados(801,850);
//			ctrl.tratarArquivosSelecionados(851,900);
//			ctrl.tratarArquivosSelecionados(901,950);
//			ctrl.tratarArquivosSelecionados(951,1000);
//			
//			ctrl.tratarArquivosSelecionados(1001,1050);
//			ctrl.tratarArquivosSelecionados(1051,1100);
//			ctrl.tratarArquivosSelecionados(1101,1150);
//			ctrl.tratarArquivosSelecionados(1151,1200);
//			ctrl.tratarArquivosSelecionados(1201,1250);
//			ctrl.tratarArquivosSelecionados(1251,1300);
//			ctrl.tratarArquivosSelecionados(1301,1350);
//			ctrl.tratarArquivosSelecionados(1351,1400);
//			ctrl.tratarArquivosSelecionados(1401,1450);
//			ctrl.tratarArquivosSelecionados(1451,1500);
//			
//			ctrl.tratarArquivosSelecionados(1501,1550);
//			ctrl.tratarArquivosSelecionados(1551,1600);
//			ctrl.tratarArquivosSelecionados(1601,1650);
//			ctrl.tratarArquivosSelecionados(1651,1700);
//			ctrl.tratarArquivosSelecionados(1701,1750);
//			ctrl.tratarArquivosSelecionados(1751,1800);
//			ctrl.tratarArquivosSelecionados(1801,1850);
//			ctrl.tratarArquivosSelecionados(1851,1900);
//			ctrl.tratarArquivosSelecionados(1901,1950);
//			ctrl.tratarArquivosSelecionados(1951,2000);
			
//			ctrl.tratarArquivosSelecionados(2001,2050);
//			ctrl.tratarArquivosSelecionados(2051,2100);
//			ctrl.tratarArquivosSelecionados(2101,2150);
//			ctrl.tratarArquivosSelecionados(2151,2200);
//			ctrl.tratarArquivosSelecionados(2201,2250);
//			ctrl.tratarArquivosSelecionados(2251,2300);
//			ctrl.tratarArquivosSelecionados(2301,2350);
//			ctrl.tratarArquivosSelecionados(2351,2400);
//			ctrl.tratarArquivosSelecionados(2401,2450);
//			ctrl.tratarArquivosSelecionados(2451,2500);
			
//			ctrl.tratarArquivosSelecionados(2501,2550);
//			ctrl.tratarArquivosSelecionados(2551,2600);	
//			ctrl.tratarArquivosSelecionados(2601,2650);
//			ctrl.tratarArquivosSelecionados(2651,2700);
//			ctrl.tratarArquivosSelecionados(2701,2750);
//			ctrl.tratarArquivosSelecionados(2751,2800);
//			ctrl.tratarArquivosSelecionados(2801,2850);
//			ctrl.tratarArquivosSelecionados(2851,2900);
//			ctrl.tratarArquivosSelecionados(2901,2950);
//			ctrl.tratarArquivosSelecionados(2951,3000);
			
		} catch (Exception e) {
			LOG.error("Deveria ter executado o teste tratarArquivosSelecionados sem falhas", e);
			fail("Falhou ao executar o teste tratarArquivosSelecionados");
		}
	}
	
	private Estudo montarEstudo() {
		Estudo estudo = new Estudo();
		
		estudo.setCodigo("ACM_01");
		estudo.setTitulo("Multiple objective scheduling of HPC workloads through dynamic prioritization");
		estudo.setAutores("Tyler A. Simon; Phuong Nguyen; Milton Halem");
		estudo.setArquivo("http://dl.acm.org/citation.cfm?id=2499968.2499981&coll=DL&dl=ACM");
		
		return estudo;
	}
}
