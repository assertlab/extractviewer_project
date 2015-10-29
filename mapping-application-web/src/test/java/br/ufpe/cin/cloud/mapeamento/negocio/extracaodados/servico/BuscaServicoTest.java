/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpe.cin.cloud.mapeamento.base.AbstractServicoTestHelper;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstudoBean;

/**
 * Representa a implementação dos testes unitários da classe
 * {@link BuscaServico}.
 * 
 * @author helaine.lins
 * @created 19/08/2014 - 10:35:55
 */
// @TransactionConfiguration(defaultRollback = true)
public class BuscaServicoTest extends AbstractServicoTestHelper {

	/**
	 * Representa a instância da camada de serviços que está sendo testada.
	 */
	@Autowired
	private IBuscaServico servBusca;

	/**
	 * Representa a instância da camada de serviços {@link IBaseDeDadosServico}.
	 */
	@Autowired
	private IBaseDeDadosServico servBase;

	/**
	 * Realiza os testes de impotação dos dados da busca manual do EASE do ano de 2006.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualEASE2006() {

		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ease_2006.bib");
			busca.setConferencia("International Conference on Evaluation and Assessment in Software Engineering - EASE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2006);
			busca.setFim(2006);
			busca.setManual(true);
			busca.setPrefixo("EASE");
			busca.setString("N/A");
			busca.setQtdEstudos(2);

			Long id = this.servBusca.incluir(busca);
			busca.setId(id);

			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 2 estudos no arquivo bib!",
					2, busca.getEstudos().size());

			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);


		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualEASE2006",
					e);

			super.logarDetalheExcecoes(e);

			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualEASE2006.");
		}

	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do EASE do ano de 2007.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualEASE2007() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ease_2007.bib");
			busca.setConferencia("International Conference on Evaluation and Assessment in Software Engineering - EASE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2007);
			busca.setFim(2007);
			busca.setManual(true);
			busca.setPrefixo("EASE");
			busca.setString("N/A");
			busca.setQtdEstudos(1);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 1 estudos no arquivo bib!",
					1, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualEASE2007",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualEASE2007.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do EASE do ano de 2008.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualEASE2008() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ease_2008.bib");
			busca.setConferencia("International Conference on Evaluation and Assessment in Software Engineering - EASE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2008);
			busca.setFim(2008);
			busca.setManual(true);
			busca.setPrefixo("EASE");
			busca.setString("N/A");
			busca.setQtdEstudos(6);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 6 estudos no arquivo bib!",
					6, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualEASE2008",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualEASE2008.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do EASE do ano de 2009.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualEASE2009() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ease_2009.bib");
			busca.setConferencia("International Conference on Evaluation and Assessment in Software Engineering - EASE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2009);
			busca.setFim(2009);
			busca.setManual(true);
			busca.setPrefixo("EASE");
			busca.setString("N/A");
			busca.setQtdEstudos(4);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 4 estudos no arquivo bib!",
					4, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualEASE2009",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualEASE2009.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do EASE do ano de 2010.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualEASE2010() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ease_2010.bib");
			busca.setConferencia("International Conference on Evaluation and Assessment in Software Engineering - EASE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2010);
			busca.setFim(2010);
			busca.setManual(true);
			busca.setPrefixo("EASE");
			busca.setString("N/A");
			busca.setQtdEstudos(4);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 4 estudos no arquivo bib!",
					4, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualEASE2010",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualEASE2010.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do EASE do ano de 2011.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualEASE2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ease_2011.bib");
			busca.setConferencia("International Conference on Evaluation and Assessment in Software Engineering - EASE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("EASE");
			busca.setString("N/A");
			busca.setQtdEstudos(3);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 3 estudos no arquivo bib!",
					3, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualEASE2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualEASE2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do EASE do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualEASE2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ease_2012.bib");
			busca.setConferencia("International Conference on Evaluation and Assessment in Software Engineering - EASE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("EASE");
			busca.setString("N/A");
			busca.setQtdEstudos(4);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 4 estudos no arquivo bib!",
					4, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualEASE2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualEASE2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do EASE do ano de 2013.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualEASE2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ease_2013.bib");
			busca.setConferencia("International Conference on Evaluation and Assessment in Software Engineering - EASE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("EASE");
			busca.setString("N/A");
			busca.setQtdEstudos(2);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 2 estudos no arquivo bib!",
					2, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualEASE2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualEASE2013.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do EASE do ano de 2014.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualEASE2014() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ease_2014.bib");
			busca.setConferencia("International Conference on Evaluation and Assessment in Software Engineering - EASE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2014);
			busca.setFim(2014);
			busca.setManual(true);
			busca.setPrefixo("EASE");
			busca.setString("N/A");
			busca.setQtdEstudos(59);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 59 estudos no arquivo bib!",
					59, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualEASE2014",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualEASE2014.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESE do ano de 2006.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESE2006() {

		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ese_2006.bib");
			busca.setConferencia("Empirical Software Engineering - ESE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2006);
			busca.setFim(2006);
			busca.setManual(true);
			busca.setPrefixo("ESE");
			busca.setString("N/A");
			busca.setQtdEstudos(8);

			Long id = this.servBusca.incluir(busca);
			busca.setId(id);

			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 8 estudos no arquivo bib!",
					8, busca.getEstudos().size());

			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);


		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESE2006",
					e);

			super.logarDetalheExcecoes(e);

			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESE2006.");
		}

	}
	

	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESE do ano de 2007.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESE2007() {

		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ese_2007.bib");
			busca.setConferencia("Empirical Software Engineering - ESE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2007);
			busca.setFim(2007);
			busca.setManual(true);
			busca.setPrefixo("ESE");
			busca.setString("N/A");
			busca.setQtdEstudos(11);

			Long id = this.servBusca.incluir(busca);
			busca.setId(id);

			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 11 estudos no arquivo bib!",
					11, busca.getEstudos().size());

			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);


		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESE2006",
					e);

			super.logarDetalheExcecoes(e);

			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESE2006.");
		}

	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESE do ano de 2008.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESE2008() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ese_2008.bib");
			busca.setConferencia("Empirical Software Engineering - ESE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2008);
			busca.setFim(2008);
			busca.setManual(true);
			busca.setPrefixo("ESE");
			busca.setString("N/A");
			busca.setQtdEstudos(10);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 10 estudos no arquivo bib!",
					10, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESE2008",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESE2008.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESE do ano de 2009.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESE2009() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ese_2009.bib");
			busca.setConferencia("Empirical Software Engineering - ESE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2009);
			busca.setFim(2009);
			busca.setManual(true);
			busca.setPrefixo("ESE");
			busca.setString("N/A");
			busca.setQtdEstudos(9);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 9 estudos no arquivo bib!",
					9, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESE2009",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESE2009.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESE do ano de 2010.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESE2010() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ese_2010.bib");
			busca.setConferencia("Empirical Software Engineering - ESE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2010);
			busca.setFim(2010);
			busca.setManual(true);
			busca.setPrefixo("ESE");
			busca.setString("N/A");
			busca.setQtdEstudos(6);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 6 estudos no arquivo bib!",
					6, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESE2010",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESE2010.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESE do ano de 2011.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESE2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ese_2011.bib");
			busca.setConferencia("Empirical Software Engineering - ESE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("ESE");
			busca.setString("N/A");
			busca.setQtdEstudos(4);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 4 estudos no arquivo bib!",
					4, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESE2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESE2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESE do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESE2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ese_2012.bib");
			busca.setConferencia("Empirical Software Engineering - ESE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("ESE");
			busca.setString("N/A");
			busca.setQtdEstudos(10);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 10 estudos no arquivo bib!",
					10, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESE2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESE2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESE do ano de 2013.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESE2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ese_2013.bib");
			busca.setConferencia("Empirical Software Engineering - ESE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("ESE");
			busca.setString("N/A");
			busca.setQtdEstudos(1);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 1 estudos no arquivo bib!",
					1, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESE2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESE2013.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESE do ano de 2014.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESE2014() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ese_2014.bib");
			busca.setConferencia("Empirical Software Engineering - ESE");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2014);
			busca.setFim(2014);
			busca.setManual(true);
			busca.setPrefixo("ESE");
			busca.setString("N/A");
			busca.setQtdEstudos(16);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 16 estudos no arquivo bib!",
					16, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESE2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESE2013.");
		}
		
	}
	

	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESEM do ano de 2006.
	 */
	@Test
	//@Ignore
	public void testeExecutarImportacaoBuscaManualESEM2006() {

		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("esem_2006.bib");
			busca.setConferencia("International Symposium on Empirical Software Engineering and Measurement - ESEM");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2006);
			busca.setFim(2006);
			busca.setManual(true);
			busca.setPrefixo("ESEM");
			busca.setString("N/A");
			busca.setQtdEstudos(15);

			Long id = this.servBusca.incluir(busca);
			busca.setId(id);

			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 15 estudos no arquivo bib!",
					15, busca.getEstudos().size());

			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);


		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESEM2006",
					e);

			super.logarDetalheExcecoes(e);

			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESEM2006.");
		}

	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESEM do ano de 2007.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESEM2007() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("esem_2007.bib");
			busca.setConferencia("International Symposium on Empirical Software Engineering and Measurement - ESEM");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2007);
			busca.setFim(2007);
			busca.setManual(true);
			busca.setPrefixo("ESEM");
			busca.setString("N/A");
			busca.setQtdEstudos(22);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 22 estudos no arquivo bib!",
					22, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESEM2007",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESEM2007.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESEM do ano de 2008.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESEM2008() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("esem_2008.bib");
			busca.setConferencia("International Symposium on Empirical Software Engineering and Measurement - ESEM");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2008);
			busca.setFim(2008);
			busca.setManual(true);
			busca.setPrefixo("ESEM");
			busca.setString("N/A");
			busca.setQtdEstudos(6);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 6 estudos no arquivo bib!",
					6, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESEM2008",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESEM2008.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESEM do ano de 2009.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESEM2009() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("esem_2009.bib");
			busca.setConferencia("International Symposium on Empirical Software Engineering and Measurement - ESEM");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2009);
			busca.setFim(2009);
			busca.setManual(true);
			busca.setPrefixo("ESEM");
			busca.setString("N/A");
			busca.setQtdEstudos(9);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 9 estudos no arquivo bib!",
					9, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESEM2009",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESEM2009.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESEM do ano de 2010.
	 */
	@Test
	//@Ignore
	public void testeExecutarImportacaoBuscaManualESEM2010() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("esem_2010.bib");
			busca.setConferencia("International Symposium on Empirical Software Engineering and Measurement - ESEM");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2010);
			busca.setFim(2010);
			busca.setManual(true);
			busca.setPrefixo("ESEM");
			busca.setString("N/A");
			busca.setQtdEstudos(11);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 11 estudos no arquivo bib!",
					11, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESEM2010",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESEM2010.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESEM do ano de 2011.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESEM2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("esem_2011.bib");
			busca.setConferencia("International Symposium on Empirical Software Engineering and Measurement - ESEM");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("ESEM");
			busca.setString("N/A");
			busca.setQtdEstudos(10);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 10 estudos no arquivo bib!",
					10, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESEM2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESEM2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESEM do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESEM2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("esem_2012.bib");
			busca.setConferencia("International Symposium on Empirical Software Engineering and Measurement - ESEM");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("ESEM");
			busca.setString("N/A");
			busca.setQtdEstudos(8);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 8 estudos no arquivo bib!",
					8, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESEM2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESEM2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ESEM do ano de 2013.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualESEM2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("esem_2013.bib");
			busca.setConferencia("International Symposium on Empirical Software Engineering and Measurement - ESEM");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ESE");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("ESEM");
			busca.setString("N/A");
			busca.setQtdEstudos(10);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 10 estudos no arquivo bib!",
					10, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualESEM2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualESEM2013.");
		}
		
	}
	
    /**
	 * Realiza os testes de impotação dos dados da busca manual do CCGRID do ano de 2006.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCCGRID2006() {

		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ccgrid_2006.bib");
			busca.setConferencia("IEEE International Symposium on Cluster Computing and the Grid - CCGRID");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2006);
			busca.setFim(2006);
			busca.setManual(true);
			busca.setPrefixo("CCGRID");
			busca.setString("N/A");
			busca.setQtdEstudos(162);

			Long id = this.servBusca.incluir(busca);
			busca.setId(id);

			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			
			int i = 0;
			for (EstudoBean est : busca.getEstudos()) {
				LOG.info("Estudo "+ ++i + ": "+ est.toString() );
			}
			
			Assert.assertEquals(
					"Deveria ter encontrado 162 estudos no arquivo bib!",
					162, busca.getEstudos().size());

			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);


		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCCGRID2006",
					e);

			super.logarDetalheExcecoes(e);

			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCCGRID2006.");
		}

	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CCGRID do ano de 2007.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCCGRID2007() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ccgrid_2007.bib");
			busca.setConferencia("IEEE International Symposium on Cluster Computing and the Grid - CCGRID");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2007);
			busca.setFim(2007);
			busca.setManual(true);
			busca.setPrefixo("CCGRID");
			busca.setString("N/A");
			busca.setQtdEstudos(111);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 111 estudos no arquivo bib!",
					111, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCCGRID2007",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCCGRID2007.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CCGRID do ano de 2008.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCCGRID2008() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ccgrid_2008.bib");
			busca.setConferencia("IEEE International Symposium on Cluster Computing and the Grid - CCGRID");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2008);
			busca.setFim(2008);
			busca.setManual(true);
			busca.setPrefixo("CCGRID");
			busca.setString("N/A");
			busca.setQtdEstudos(118);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 118 estudos no arquivo bib!",
					118, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCCGRID2008",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCCGRID2008.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CCGRID do ano de 2009.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCCGRID2009() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ccgrid_2009.bib");
			busca.setConferencia("IEEE International Symposium on Cluster Computing and the Grid - CCGRID");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2009);
			busca.setFim(2009);
			busca.setManual(true);
			busca.setPrefixo("CCGRID");
			busca.setString("N/A");
			busca.setQtdEstudos(85);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 85 estudos no arquivo bib!",
					85, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCCGRID2009",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCCGRID2009.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CCGRID do ano de 2010.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCCGRID2010() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ccgrid_2010.bib");
			busca.setConferencia("IEEE International Symposium on Cluster Computing and the Grid - CCGRID");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2010);
			busca.setFim(2010);
			busca.setManual(true);
			busca.setPrefixo("CCGRID");
			busca.setString("N/A");
			busca.setQtdEstudos(124);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 124 estudos no arquivo bib!",
					124, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCCGRID2010",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCCGRID2010.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CCGRID do ano de 2011.
	 */
	@Test
	//@Ignore
	public void testeExecutarImportacaoBuscaManualCCGRID2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ccgrid_2011.bib");
			busca.setConferencia("IEEE International Symposium on Cluster Computing and the Grid - CCGRID");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("CCGRID");
			busca.setString("N/A");
			busca.setQtdEstudos(72);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 72 estudos no arquivo bib!",
					72, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCCGRID2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCCGRID2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CCGRID do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCCGRID2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ccgrid_2012.bib");
			busca.setConferencia("IEEE International Symposium on Cluster Computing and the Grid - CCGRID");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("CCGRID");
			busca.setString("N/A");
			busca.setQtdEstudos(137);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 137 estudos no arquivo bib!",
					137, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCCGRID2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCCGRID2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CCGRID do ano de 2013.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCCGRID2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ccgrid_2013.bib");
			busca.setConferencia("IEEE International Symposium on Cluster Computing and the Grid - CCGRID");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("CCGRID");
			busca.setString("N/A");
			busca.setQtdEstudos(105);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 105 estudos no arquivo bib!",
					105, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCCGRID2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCCGRID2013.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CCGRID do ano de 2014.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCCGRID2014() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ccgrid_2014.bib");
			busca.setConferencia("IEEE International Symposium on Cluster Computing and the Grid - CCGRID");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2014);
			busca.setFim(2014);
			busca.setManual(true);
			busca.setPrefixo("CCGRID");
			busca.setString("N/A");
			busca.setQtdEstudos(119);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 119 estudos no arquivo bib!",
					119, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCCGRID2014",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCCGRID2014.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CCIS do ano de 2011.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCCIS2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ccis_2011.bib");
			busca.setConferencia("IEEE International Conference on Cloud Computing and Intelligence Systems - CCIS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("CCIS");
			busca.setString("N/A");
			busca.setQtdEstudos(129);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 129 estudos no arquivo bib!",
					129, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCCIS2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCCIS2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CCIS do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCCIS2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ccis_2012.bib");
			busca.setConferencia("IEEE International Conference on Cloud Computing and Intelligence Systems - CCIS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("CCIS");
			busca.setString("N/A");
			busca.setQtdEstudos(111);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 111 estudos no arquivo bib!",
					111, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCCIS2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCCIS2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CCIS do ano de 2013.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCCIS2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ccis_2013.bib");
			busca.setConferencia("IEEE International Conference on Cloud Computing and Intelligence Systems - CCIS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("CCIS");
			busca.setString("N/A");
			busca.setQtdEstudos(197);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 197 estudos no arquivo bib!",
					197, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCCIS2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCCIS2013.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CLOUD do ano de 2009.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCLOUD2009() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("cloud_2009.bib");
			busca.setConferencia("IEEE International Conference on Cloud Computing - CLOUD");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2009);
			busca.setFim(2009);
			busca.setManual(true);
			busca.setPrefixo("CLOUD");
			busca.setString("N/A");
			busca.setQtdEstudos(32);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 32 estudos no arquivo bib!",
					32, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCLOUD2009",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCLOUD2009.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CLOUD do ano de 2010.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCLOUD2010() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("cloud_2010.bib");
			busca.setConferencia("IEEE International Conference on Cloud Computing - CLOUD");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2010);
			busca.setFim(2010);
			busca.setManual(true);
			busca.setPrefixo("CLOUD");
			busca.setString("N/A");
			busca.setQtdEstudos(77);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 77 estudos no arquivo bib!",
					77, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCLOUD2010",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCLOUD2010.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CLOUD do ano de 2011.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCLOUD2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("cloud_2011.bib");
			busca.setConferencia("IEEE International Conference on Cloud Computing - CLOUD");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("CLOUD");
			busca.setString("N/A");
			busca.setQtdEstudos(109);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 109 estudos no arquivo bib!",
					109, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCLOUD2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCLOUD2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CLOUD do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCLOUD2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("cloud_2012.bib");
			busca.setConferencia("IEEE International Conference on Cloud Computing - CLOUD");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("CLOUD");
			busca.setString("N/A");
			busca.setQtdEstudos(139);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 139 estudos no arquivo bib!",
					139, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCLOUD2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCLOUD2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CLOUD do ano de 2013.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCLOUD2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("cloud_2013.bib");
			busca.setConferencia("IEEE International Conference on Cloud Computing - CLOUD");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("CLOUD");
			busca.setString("N/A");
			busca.setQtdEstudos(97);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 97 estudos no arquivo bib!",
					97, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCLOUD2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCLOUD2013.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CLOUDCOM do ano de 2010.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCLOUDCOM2010() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("cloudcom_2010.bib");
			busca.setConferencia("IEEE International Conference on Cloud Computing Technology and Science - CLOUDCOM");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2010);
			busca.setFim(2010);
			busca.setManual(true);
			busca.setPrefixo("CLOUDCOM");
			busca.setString("N/A");
			busca.setQtdEstudos(105);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 105 estudos no arquivo bib!",
					105, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCLOUDCOM2010",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCLOUDCOM2010.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CLOUDCOM do ano de 2011.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCLOUDCOM2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("cloudcom_2011.bib");
			busca.setConferencia("IEEE International Conference on Cloud Computing Technology and Science - CLOUDCOM");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("CLOUDCOM");
			busca.setString("N/A");
			busca.setQtdEstudos(113);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 113 estudos no arquivo bib!",
					113, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCLOUDCOM2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCLOUDCOM2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CLOUDCOM do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualCLOUDCOM2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("cloudcom_2012.bib");
			busca.setConferencia("IEEE International Conference on Cloud Computing Technology and Science - CLOUDCOM");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("CLOUDCOM");
			busca.setString("N/A");
			busca.setQtdEstudos(134);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 134 estudos no arquivo bib!",
					134, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCLOUDCOM2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCLOUDCOM2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do CLOUDCOM do ano de 2013.
	 */
	@Test
	//@Ignore
	public void testeExecutarImportacaoBuscaManualCLOUDCOM2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("cloudcom_2013.bib");
			busca.setConferencia("IEEE International Conference on Cloud Computing Technology and Science - CLOUDCOM");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("CLOUDCOM");
			busca.setString("N/A");
			busca.setQtdEstudos(165);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 165 estudos no arquivo bib!",
					165, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualCLOUDCOM2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualCLOUDCOM2013.");
		}
		
	}
	
    /**
	 * Realiza os testes de impotação dos dados da busca manual do FGCS do ano de 2006.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualFGCS2006() {

		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("fgcs_2006.bib");
			busca.setConferencia("Future Generation Computer Systems - FGCS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2006);
			busca.setFim(2006);
			busca.setManual(true);
			busca.setPrefixo("FGCS");
			busca.setString("N/A");
			busca.setQtdEstudos(106);

			Long id = this.servBusca.incluir(busca);
			busca.setId(id);

			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 106 estudos no arquivo bib!",
					106, busca.getEstudos().size());

			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);


		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualFGCS2006",
					e);

			super.logarDetalheExcecoes(e);

			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualFGCS2006.");
		}

	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do FGCS do ano de 2007.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualFGCS2007() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("fgcs_2007.bib");
			busca.setConferencia("Future Generation Computer Systems - FGCS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2007);
			busca.setFim(2007);
			busca.setManual(true);
			busca.setPrefixo("FGCS");
			busca.setString("N/A");
			busca.setQtdEstudos(106);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 106 estudos no arquivo bib!",
					106, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualFGCS2007",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualFGCS2007.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do FGCS do ano de 2008.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualFGCS2008() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("fgcs_2008.bib");
			busca.setConferencia("Future Generation Computer Systems - FGCS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2008);
			busca.setFim(2008);
			busca.setManual(true);
			busca.setPrefixo("FGCS");
			busca.setString("N/A");
			busca.setQtdEstudos(82);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 82 estudos no arquivo bib!",
					82, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualFGCS2008",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualFGCS2008.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do FGCS do ano de 2009.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualFGCS2009() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("fgcs_2009.bib");
			busca.setConferencia("Future Generation Computer Systems - FGCS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2009);
			busca.setFim(2009);
			busca.setManual(true);
			busca.setPrefixo("FGCS");
			busca.setString("N/A");
			busca.setQtdEstudos(104);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 104 estudos no arquivo bib!",
					104, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualFGCS2009",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualFGCS2009.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do FGCS do ano de 2010.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualFGCS2010() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("fgcs_2010.bib");
			busca.setConferencia("Future Generation Computer Systems - FGCS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2010);
			busca.setFim(2010);
			busca.setManual(true);
			busca.setPrefixo("FGCS");
			busca.setString("N/A");
			busca.setQtdEstudos(148);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 148 estudos no arquivo bib!",
					148, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualFGCS2010",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualFGCS2010.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do FGCS do ano de 2011.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualFGCS2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("fgcs_2011.bib");
			busca.setConferencia("Future Generation Computer Systems - FGCS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("FGCS");
			busca.setString("N/A");
			busca.setQtdEstudos(123);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 123 estudos no arquivo bib!",
					123, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualFGCS2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualFGCS2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do FGCS do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualFGCS2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("fgcs_2012.bib");
			busca.setConferencia("Future Generation Computer Systems - FGCS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("FGCS");
			busca.setString("N/A");
			busca.setQtdEstudos(149);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 149 estudos no arquivo bib!",
					149, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualFGCS2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualFGCS2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do FGCS do ano de 2013.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualFGCS2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("fgcs_2013.bib");
			busca.setConferencia("Future Generation Computer Systems - FGCS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("FGCS");
			busca.setString("N/A");
			busca.setQtdEstudos(187);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 187 estudos no arquivo bib!",
					187, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualFGCS2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualFGCS2013.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do FGCS do ano de 2014.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualFGCS2014() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("fgcs_2014.bib");
			busca.setConferencia("Future Generation Computer Systems - FGCS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2014);
			busca.setFim(2014);
			busca.setManual(true);
			busca.setPrefixo("FGCS");
			busca.setString("N/A");
			busca.setQtdEstudos(4);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 4 estudos no arquivo bib!",
					4, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualFGCS2014",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualFGCS2014.");
		}
		
	}
	
    /**
	 * Realiza os testes de impotação dos dados da busca manual do ICDCSW do ano de 2006.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualICDCSW2006() {

		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("icdcsw_2006.bib");
			busca.setConferencia("International Conference on Distributed Computing Systems - ICDCSW");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2006);
			busca.setFim(2006);
			busca.setManual(true);
			busca.setPrefixo("ICDCSW");
			busca.setString("N/A");
			busca.setQtdEstudos(167);

			Long id = this.servBusca.incluir(busca);
			busca.setId(id);

			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 167 estudos no arquivo bib!",
					167, busca.getEstudos().size());

			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);


		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualICDCSW2006",
					e);

			super.logarDetalheExcecoes(e);

			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualICDCSW2006.");
		}

	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ICDCSW do ano de 2007.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualICDCSW2007() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("icdcsw_2007.bib");
			busca.setConferencia("International Conference on Distributed Computing Systems - ICDCSW");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2007);
			busca.setFim(2007);
			busca.setManual(true);
			busca.setPrefixo("ICDCSW");
			busca.setString("N/A");
			busca.setQtdEstudos(156);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 156 estudos no arquivo bib!",
					156, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualICDCSW2007",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualICDCSW2007.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ICDCSW do ano de 2008.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualICDCSW2008() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("icdcsw_2008.bib");
			busca.setConferencia("International Conference on Distributed Computing Systems - ICDCSW");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2008);
			busca.setFim(2008);
			busca.setManual(true);
			busca.setPrefixo("ICDCSW");
			busca.setString("N/A");
			busca.setQtdEstudos(102);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 102 estudos no arquivo bib!",
					102, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualICDCSW2008",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualICDCSW2008.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ICDCSW do ano de 2009.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualICDCSW2009() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("icdcsw_2009.bib");
			busca.setConferencia("International Conference on Distributed Computing Systems - ICDCSW");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2009);
			busca.setFim(2009);
			busca.setManual(true);
			busca.setPrefixo("ICDCSW");
			busca.setString("N/A");
			busca.setQtdEstudos(79);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 79 estudos no arquivo bib!",
					79, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualICDCSW2009",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualICDCSW2009.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ICDCSW do ano de 2010.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualICDCSW2010() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("icdcsw_2010.bib");
			busca.setConferencia("International Conference on Distributed Computing Systems - ICDCSW");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2010);
			busca.setFim(2010);
			busca.setManual(true);
			busca.setPrefixo("ICDCSW");
			busca.setString("N/A");
			busca.setQtdEstudos(132);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 132 estudos no arquivo bib!",
					132, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualICDCSW2010",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualICDCSW2010.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ICDCSW do ano de 2011.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualICDCSW2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("icdcsw_2011.bib");
			busca.setConferencia("International Conference on Distributed Computing Systems - ICDCSW");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("ICDCSW");
			busca.setString("N/A");
			busca.setQtdEstudos(136);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 136 estudos no arquivo bib!",
					136, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualICDCSW2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualICDCSW2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ICDCSW do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualICDCSW2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("icdcsw_2012.bib");
			busca.setConferencia("International Conference on Distributed Computing Systems - ICDCSW");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("ICDCSW");
			busca.setString("N/A");
			busca.setQtdEstudos(91);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 91 estudos no arquivo bib!",
					91, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualICDCSW2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualICDCSW2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do ICDCSW do ano de 2013.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualICDCSW2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("icdcsw_2013.bib");
			busca.setConferencia("International Conference on Distributed Computing Systems - ICDCSW");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("ICDCSW");
			busca.setString("N/A");
			busca.setQtdEstudos(131);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 131 estudos no arquivo bib!",
					131, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualICDCSW2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualICDCSW2013.");
		}
		
	}
	
    /**
	 * Realiza os testes de impotação dos dados da busca manual do IPDPS do ano de 2006.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualIPDPS2006() {

		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ipdps_2006.bib");
			busca.setConferencia("International Parallel and Distributed Processing Symposium - IPDPS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2006);
			busca.setFim(2006);
			busca.setManual(true);
			busca.setPrefixo("IPDPS");
			busca.setString("N/A");
			busca.setQtdEstudos(468);

			Long id = this.servBusca.incluir(busca);
			busca.setId(id);

			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 468 estudos no arquivo bib!",
					468, busca.getEstudos().size());

			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);


		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualIPDPS2006",
					e);

			super.logarDetalheExcecoes(e);

			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualIPDPS2006.");
		}

	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do IPDPS do ano de 2007.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualIPDPS2007() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ipdps_2007.bib");
			busca.setConferencia("International Parallel and Distributed Processing Symposium - IPDPS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2007);
			busca.setFim(2007);
			busca.setManual(true);
			busca.setPrefixo("IPDPS");
			busca.setString("N/A");
			busca.setQtdEstudos(447);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 447 estudos no arquivo bib!",
					447, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualIPDPS2007",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualIPDPS2007.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do IPDPS do ano de 2008.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualIPDPS2008() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ipdps_2008.bib");
			busca.setConferencia("International Parallel and Distributed Processing Symposium - IPDPS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2008);
			busca.setFim(2008);
			busca.setManual(true);
			busca.setPrefixo("IPDPS");
			busca.setString("N/A");
			busca.setQtdEstudos(486);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 486 estudos no arquivo bib!",
					486, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualIPDPS2008",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualIPDPS2008.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do IPDPS do ano de 2009.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualIPDPS2009() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ipdps_2009.bib");
			busca.setConferencia("International Parallel and Distributed Processing Symposium - IPDPS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2009);
			busca.setFim(2009);
			busca.setManual(true);
			busca.setPrefixo("IPDPS");
			busca.setString("N/A");
			busca.setQtdEstudos(386);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 186 estudos no arquivo bib!",
					386, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualIPDPS2009",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualIPDPS2009.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do IPDPS do ano de 2010.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualIPDPS2010() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ipdps_2010.bib");
			busca.setConferencia("International Parallel and Distributed Processing Symposium - IPDPS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2010);
			busca.setFim(2010);
			busca.setManual(true);
			busca.setPrefixo("IPDPS");
			busca.setString("N/A");
			busca.setQtdEstudos(133);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 133 estudos no arquivo bib!",
					133, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualIPDPS2010",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualIPDPS2010.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do IPDPS do ano de 2011.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualIPDPS2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ipdps_2011.bib");
			busca.setConferencia("International Parallel and Distributed Processing Symposium - IPDPS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("IPDPS");
			busca.setString("N/A");
			busca.setQtdEstudos(112);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 112 estudos no arquivo bib!",
					112, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualIPDPS2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualIPDPS2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do IPDPS do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualIPDPS2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ipdps_2012.bib");
			busca.setConferencia("International Parallel and Distributed Processing Symposium - IPDPS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("IPDPS");
			busca.setString("N/A");
			busca.setQtdEstudos(119);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 119 estudos no arquivo bib!",
					119, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualIPDPS2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualIPDPS2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do IPDPS do ano de 2013.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualIPDPS2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ipdps_2013.bib");
			busca.setConferencia("International Parallel and Distributed Processing Symposium - IPDPS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("IPDPS");
			busca.setString("N/A");
			busca.setQtdEstudos(267);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 267 estudos no arquivo bib!",
					267, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualIPDPS2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualIPDPS2013.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do IPDPS do ano de 2014.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualIPDPS2014() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ipdps_2014.bib");
			busca.setConferencia("International Parallel and Distributed Processing Symposium - IPDPS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2014);
			busca.setFim(2014);
			busca.setManual(true);
			busca.setPrefixo("IPDPS");
			busca.setString("N/A");
			busca.setQtdEstudos(117);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 117 estudos no arquivo bib!",
					117, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualIPDPS2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualIPDPS2013.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do SOCC do ano de 2010.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualSOCC2010() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("socc_2010.bib");
			busca.setConferencia("ACM Symposium on Cloud Computing - SOCC");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2010);
			busca.setFim(2010);
			busca.setManual(true);
			busca.setPrefixo("SOCC");
			busca.setString("N/A");
			busca.setQtdEstudos(24);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 24 estudos no arquivo bib!",
					24, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualSOCC2010",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualSOCC2010.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do SOCC do ano de 2011.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualSOCC2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("socc_2011.bib");
			busca.setConferencia("ACM Symposium on Cloud Computing - SOCC");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("SOCC");
			busca.setString("N/A");
			busca.setQtdEstudos(29);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 29 estudos no arquivo bib!",
					29, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualSOCC2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualSOCC2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do SOCC do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualSOCC2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("socc_2012.bib");
			busca.setConferencia("ACM Symposium on Cloud Computing - SOCC");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("SOCC");
			busca.setString("N/A");
			busca.setQtdEstudos(28);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 28 estudos no arquivo bib!",
					28, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualSOCC2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualSOCC2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do SOCC do ano de 2013.
	 */
	@Test
	//@Ignore
	public void testeExecutarImportacaoBuscaManualSOCC2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("socc_2013.bib");
			busca.setConferencia("ACM Symposium on Cloud Computing - SOCC");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("SOCC");
			busca.setString("N/A");
			busca.setQtdEstudos(58);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 58 estudos no arquivo bib!",
					58, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualSOCC2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualSOCC2013.");
		}
		
	}
	
    /**
	 * Realiza os testes de impotação dos dados da busca manual do TPDS do ano de 2006.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualTPDS2006() {

		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("tpds_2006.bib");
			busca.setConferencia("IEEE Transactions on Parallel and Distributed Systems - TPDS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2006);
			busca.setFim(2006);
			busca.setManual(true);
			busca.setPrefixo("TPDS");
			busca.setString("N/A");
			busca.setQtdEstudos(123);

			Long id = this.servBusca.incluir(busca);
			busca.setId(id);

			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 123 estudos no arquivo bib!",
					123, busca.getEstudos().size());

			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);


		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualTPDS2006",
					e);

			super.logarDetalheExcecoes(e);

			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualTPDS2006.");
		}

	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do TPDS do ano de 2007.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualTPDS2007() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("tpds_2007.bib");
			busca.setConferencia("IEEE Transactions on Parallel and Distributed Systems - TPDS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2007);
			busca.setFim(2007);
			busca.setManual(true);
			busca.setPrefixo("TPDS");
			busca.setString("N/A");
			busca.setQtdEstudos(137);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 137 estudos no arquivo bib!",
					137, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualTPDS2007",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualTPDS2007.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do TPDS do ano de 2008.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualTPDS2008() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("tpds_2008.bib");
			busca.setConferencia("IEEE Transactions on Parallel and Distributed Systems - TPDS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2008);
			busca.setFim(2008);
			busca.setManual(true);
			busca.setPrefixo("TPDS");
			busca.setString("N/A");
			busca.setQtdEstudos(137);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 137 estudos no arquivo bib!",
					137, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualTPDS2008",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualTPDS2008.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do TPDS do ano de 2009.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualTPDS2009() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("tpds_2009.bib");
			busca.setConferencia("IEEE Transactions on Parallel and Distributed Systems - TPDS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2009);
			busca.setFim(2009);
			busca.setManual(true);
			busca.setPrefixo("TPDS");
			busca.setString("N/A");
			busca.setQtdEstudos(127);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 127 estudos no arquivo bib!",
					127, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualTPDS2009",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualTPDS2009.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do TPDS do ano de 2010.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualTPDS2010() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("tpds_2010.bib");
			busca.setConferencia("IEEE Transactions on Parallel and Distributed Systems - TPDS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2010);
			busca.setFim(2010);
			busca.setManual(true);
			busca.setPrefixo("TPDS");
			busca.setString("N/A");
			busca.setQtdEstudos(141);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 141 estudos no arquivo bib!",
					141, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualTPDS2010",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualTPDS2010.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do TPDS do ano de 2011.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualTPDS2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("tpds_2011.bib");
			busca.setConferencia("IEEE Transactions on Parallel and Distributed Systems - TPDS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("TPDS");
			busca.setString("N/A");
			busca.setQtdEstudos(186);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 186 estudos no arquivo bib!",
					186, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualTPDS2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualTPDS2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do TPDS do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualTPDS2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("tpds_2012.bib");
			busca.setConferencia("IEEE Transactions on Parallel and Distributed Systems - TPDS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("TPDS");
			busca.setString("N/A");
			busca.setQtdEstudos(231);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 231 estudos no arquivo bib!",
					231, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualTPDS2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualTPDS2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do TPDS do ano de 2013.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualTPDS2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("tpds_2013.bib");
			busca.setConferencia("IEEE Transactions on Parallel and Distributed Systems - TPDS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("TPDS");
			busca.setString("N/A");
			busca.setQtdEstudos(226);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 226 estudos no arquivo bib!",
					226, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualTPDS2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualTPDS2013.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do TPDS do ano de 2014.
	 */
	@Test
	//@Ignore
	public void testeExecutarImportacaoBuscaManualTPDS2014() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("tpds_2014.bib");
			busca.setConferencia("IEEE Transactions on Parallel and Distributed Systems - TPDS");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2014);
			busca.setFim(2014);
			busca.setManual(true);
			busca.setPrefixo("TPDS");
			busca.setString("N/A");
			busca.setQtdEstudos(7);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 7 estudos no arquivo bib!",
					7, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualTPDS2014",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualTPDS2014.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do UCC do ano de 2011.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualUCC2011() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ucc_2011.bib");
			busca.setConferencia("IEEE International Conference on Utility and Cloud Computing - UCC");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2011);
			busca.setFim(2011);
			busca.setManual(true);
			busca.setPrefixo("UCC");
			busca.setString("N/A");
			busca.setQtdEstudos(65);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 65 estudos no arquivo bib!",
					65, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualUCC2011",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualUCC2011.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do UCC do ano de 2012.
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaManualUCC2012() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ucc_2012.bib");
			busca.setConferencia("IEEE International Conference on Utility and Cloud Computing - UCC");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2012);
			busca.setFim(2012);
			busca.setManual(true);
			busca.setPrefixo("UCC");
			busca.setString("N/A");
			busca.setQtdEstudos(56);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 56 estudos no arquivo bib!",
					56, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualUCC2012",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualUCC2012.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca manual do UCC do ano de 2013.
	 */
	@Test
	//@Ignore
	public void testeExecutarImportacaoBuscaManualUCC2013() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Múltiplo"));
			busca.setArquivoBib("ucc_2013.bib");
			busca.setConferencia("IEEE International Conference on Utility and Cloud Computing - UCC");
			busca.setArquivoExcel("");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/CLOUD");
			busca.setInicio(2013);
			busca.setFim(2013);
			busca.setManual(true);
			busca.setPrefixo("UCC");
			busca.setString("N/A");
			busca.setQtdEstudos(90);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo bib
			this.servBusca.tratarEstudosContidosArquivoBib(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 90 estudos no arquivo bib!",
					90, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaManualUCC2013",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaManualUCC2013.");
		}
		
	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca do Scopus
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaScopus() {

		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Scopus"));
			busca.setArquivoBib("");
			busca.setArquivoExcel("experimentos-cloud-scopus-tratado.xlsx");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/SCOPUS");
			busca.setInicio(2006);
			busca.setFim(2014);
			busca.setManual(false);
			busca.setPrefixo("SCP");
			busca.setString("(TITLE-ABS-KEY('cloud computing' OR 'cloud platform' OR 'cloud service' OR 'cloud provider' OR 'PaaS' OR 'IaaS' OR 'SaaS') AND TITLE-ABS-KEY(experiment))");
			busca.setQtdEstudos(2553);

			Long id = this.servBusca.incluir(busca);
			busca.setId(id);

			// Passo 2 - realizar a importação dos dados do arquivo excel
			this.servBusca.tratarEstudosContidosArquivoExcel(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 2553 estudos no arquivo excel!",
					2553, busca.getEstudos().size());

			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);

			// Passo 3 - gerar planilha do excel

		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaScopus",
					e);

			super.logarDetalheExcecoes(e);

			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaScopus.");
		}

	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca do ScienceDirect
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaScienceDirect() {

		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("Science Direct"));
			busca.setArquivoBib("");
			busca.setArquivoExcel("experimentos-cloud-science-direct-tratado.xls");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/SCIENCE_DIRECT");
			busca.setInicio(2006);
			busca.setFim(2014);
			busca.setManual(false);
			busca.setPrefixo("SD");
			busca.setString("tak((experiment) and ('cloud computing' or 'cloud platform' or 'cloud service' or 'cloud provider' or 'PaaS' or 'IaaS' or 'SaaS'))");
			busca.setQtdEstudos(128);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);

			// Passo 2 - realizar a importação dos dados do arquivo excel
			this.servBusca.tratarEstudosContidosArquivoExcel(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 128 estudos no arquivo excel!",
					128, busca.getEstudos().size());

			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);

			// Passo 3 - gerar planilha do excel

		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaScienceDirect",
					e);

			super.logarDetalheExcecoes(e);

			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaScienceDirect.");
		}

	}
	
	/**
	 * Realiza os testes de impotação dos dados da busca do ACM
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaACM() {

		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("ACM Digital Library"));
			busca.setArquivoBib("");
			busca.setArquivoExcel("experimentos-cloud-acm-tratado.xls");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/ACM");
			busca.setInicio(2006);
			busca.setFim(2014);
			busca.setManual(false);
			busca.setPrefixo("ACM");
			busca.setString("(experiment) and ('cloud computing' or 'cloud platform' or 'cloud service' or 'cloud provider' or 'PaaS' or 'IaaS' or 'SaaS')");
			busca.setQtdEstudos(3420);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);

			// Passo 2 - realizar a importação dos dados do arquivo excel
			this.servBusca.tratarEstudosContidosArquivoExcel(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 3420 estudos no arquivo excel!",
					3420, busca.getEstudos().size());

			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);

			// Passo 3 - gerar planilha do excel

		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaACM",
					e);

			super.logarDetalheExcecoes(e);

			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaACM.");
		}

	}

	/**
	 * Realiza os testes de impotação dos dados da busca do IEEExplorer
	 */
	@Test
	@Ignore
	public void testeExecutarImportacaoBuscaIEEEXplorer() {
		
		try {
			// Passo 1 - criar a instância de Busca
			BuscaBean busca = new BuscaBean();
			busca.setBase(servBase.buscar("IEEE Xplorer Digital Library"));
			busca.setArquivoBib("");
			busca.setArquivoExcel("experimentos-cloud-ieee-tratado.xls");
			busca.setData(new LocalDate(2014, 07, 31));
			busca.setDiretorio("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/final/IEEE");
			busca.setInicio(2006);
			busca.setFim(2014);
			busca.setManual(false);
			busca.setPrefixo("IEEEX");
			busca.setString("(('cloud computing' OR 'cloud platform' OR 'cloud service' OR 'cloud provider' OR 'PaaS' OR 'IaaS' OR 'SaaS') AND (experiment))");
			busca.setQtdEstudos(1580);
			
			Long id = this.servBusca.incluir(busca);
			busca.setId(id);
			
			// Passo 2 - realizar a importação dos dados do arquivo excel
			this.servBusca.tratarEstudosContidosArquivoExcel(busca);
			Assert.assertEquals(
					"Deveria ter encontrado 1580 estudos no arquivo excel!",
					1580, busca.getEstudos().size());
			
			// Passo 3 - importar para a base de dados
			this.servBusca.importarEstudosParaBaseDados(busca);
			
			// Passo 3 - gerar planilha do excel
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeExecutarImportacaoBuscaIEEEXplorer",
					e);
			
			super.logarDetalheExcecoes(e);
			
			fail("Nao deveria ter levantado excecao ao executar testeExecutarImportacaoBuscaIEEEXplorer.");
		}
		
	}
}
