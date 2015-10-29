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
import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Test;

import br.ufpe.cin.cloud.mapeamento.selecao.modelo.Estudo;

/**
 * Representa a implementação dos testes unitários da classe
 * {@link ControladorIEEEXplorer}.
 * 
 * @author helaine.lins
 * @created 31/07/2014 - 13:57:07
 */
public class ControladorIEEEXplorerTest {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger
			.getLogger(ControladorIEEEXplorerTest.class.getName());

	/**
	 * Representa a instância do controlador que está sendo testado.
	 */
	private ControladorIEEEXplorer ctrl = new ControladorIEEEXplorer();
	private final Random geraRandomico = new Random();

	/**
	 * Realiza o teste do método recuperarUrlArquivoPdf.
	 */
	@Test
	public void testeRecuperarUrlArquivoPdf() {
		try {
			Estudo estudo = this.montarEstudo();
			ctrl.recuperarUrlArquivoPdf(estudo, true);

			assertEquals(
					"http://ieeexplore.ieee.org/ielx5/6253102/6253471/06253560.pdf?tp=&arnumber=6253560&isnumber=6253471",
					estudo.getArquivo());
		} catch (Exception e) {
			LOG.error(
					"Deveria ter executado o teste testExecutarDiagnosticoBibTexArquivosSelecionados sem falhas",
					e);
			fail("Falhou ao executar o teste testExecutarDiagnosticoBibTexArquivosSelecionados");
		}
	}

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
	 * Realiza o teste do método preencherPlanilha.
	 */
	@Test
	public void testePreencherPlanilha() {
		try {
			ArrayList<Estudo> estudos = new ArrayList<Estudo>();
			estudos.add(this.montarEstudo());
			estudos.add(this.montarEstudo());

			ctrl.preencherPlanilha(1, 10, estudos);
		} catch (Exception e) {
			LOG.error(
					"Deveria ter executado o teste preencherPlanilha sem falhas",
					e);
			fail("Falhou ao executar o teste preencherPlanilha");
		}
	}

	/**
	 * Realiza o teste do método tratarArquivosSelecionados.
	 */
	@Test
	// @Ignore
	public void testeTratarArquivosSelecionados() {
		try {
			// OK
			ctrl.tratarArquivosSelecionados(1, 50);
			// OK
			// ctrl.tratarArquivosSelecionados(51,100);
			// OK
			// ctrl.tratarArquivosSelecionados(101,150);
			// OK
			// ctrl.tratarArquivosSelecionados(151,200);
			// OK
			// ctrl.tratarArquivosSelecionados(201,250);
			// OK
			// ctrl.tratarArquivosSelecionados(251,300);
			// OK
			// ctrl.tratarArquivosSelecionados(301,350);
			// OK
			// ctrl.tratarArquivosSelecionados(351,400);
			// OK
			// ctrl.tratarArquivosSelecionados(401,450);
			// OK
			// ctrl.tratarArquivosSelecionados(451,500);
			//
			// OK
			// ctrl.tratarArquivosSelecionados(501,550);
			// OK
			// ctrl.tratarArquivosSelecionados(551,600);
			// OK
			// ctrl.tratarArquivosSelecionados(601,650);
			// OK
			// ctrl.tratarArquivosSelecionados(651,700);
			// aguardaIntervalo();
			// OK
			// ctrl.tratarArquivosSelecionados(701,750);
			// aguardaIntervalo();
			// OK
			// ctrl.tratarArquivosSelecionados(751,800);
			// aguardaIntervalo();
			// OK
			// ctrl.tratarArquivosSelecionados(801,850);
			// aguardaIntervalo();
			// OK
			// ctrl.tratarArquivosSelecionados(851,900);
			// aguardaIntervalo();
			// OK
			// ctrl.tratarArquivosSelecionados(901,950);
			// aguardaIntervalo();
			// OK
			// ctrl.tratarArquivosSelecionados(951,1000);
			//
			// ctrl.tratarArquivosSelecionados(1001,1050);
			// aguardaIntervalo();
			// ctrl.tratarArquivosSelecionados(1051,1100);
			// aguardaIntervalo();
			// ctrl.tratarArquivosSelecionados(1101,1150);
			// aguardaIntervalo();
			// ctrl.tratarArquivosSelecionados(1151,1200);
			// aguardaIntervalo();
			// ctrl.tratarArquivosSelecionados(1201,1250);
			// aguardaIntervalo();
			// ctrl.tratarArquivosSelecionados(1251,1300);
			// aguardaIntervalo();
			// ctrl.tratarArquivosSelecionados(1301,1350);
			// aguardaIntervalo();
			// ctrl.tratarArquivosSelecionados(1351,1400);
			// aguardaIntervalo();
			// ctrl.tratarArquivosSelecionados(1401,1450);
			// aguardaIntervalo();
			// ctrl.tratarArquivosSelecionados(1451,1500);
			// aguardaIntervalo();

			// ctrl.tratarArquivosSelecionados(1501,1550);
			aguardaIntervalo();
			// ctrl.tratarArquivosSelecionados(1551,1580);

		} catch (Exception e) {
			LOG.error(
					"Deveria ter executado o teste tratarArquivosSelecionados sem falhas",
					e);
			fail("Falhou ao executar o teste tratarArquivosSelecionados");
		}
	}

	/**
	 * Executa o teste de unificação de resultados de processamento.
	 */
	@Test
	public void testExecutarDiagnosticoResultadosProcessamento() {
		try {
			ctrl.executarDiagnosticoResultadosProcessamento();
		} catch (Exception e) {
			LOG.error(
					"Deveria ter executado o teste testExecutarDiagnosticoResultadosProcessamento sem falhas",
					e);
			fail("Falhou ao executar o teste testExecutarDiagnosticoResultadosProcessamento");
		}
	}

	/**
	 * Executa o teste de unificação de resultados de processamento.
	 */
	@Test
	public void testUnificarResultadosProcessamento() {
		try {
			ctrl.unificarResultadosProcessamento();
		} catch (Exception e) {
			LOG.error(
					"Deveria ter executado o teste testUnificarResultadosProcessamento sem falhas",
					e);
			fail("Falhou ao executar o teste testUnificarResultadosProcessamento");
		}
	}

	private Estudo montarEstudo() {
		Estudo estudo = new Estudo();

		estudo.setCodigo("EEEX_04");
		estudo.setTitulo("A Latency-Aware Co-deployment Mechanism for Cloud-Based Services");
		estudo.setAutores("Yu Kang;  Zibin Zheng;  Lyu, M.R.");
		estudo.setArquivo("http://ieeexplore.ieee.org/xpl/articleDetails.jsp?tp=&arnumber=6253560&contentType=Conference+Publications");

		return estudo;
	}

	/**
	 * Força um delay aleatório entre 10 e 20 segundos antes de tratar a url do
	 * próximo estudo.
	 */
	private void aguardaIntervalo() {
		long espera = 180000;

		try {
			espera += this.geraRandomico.nextInt(10000);
			LOG.info("Aguardando " + espera / 1000
					+ " segs antes de tratar a próxima URL");
			Thread.sleep(espera);
		} catch (InterruptedException e) {
			LOG.error(
					"Ocorreu um erro ao tentar realizar uma pausa para tratar a próxima url.",
					e);

			Thread.currentThread().interrupt();
		}

	}
}
