/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.selecao.controlador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXParser;
import org.jbibtex.Value;

import br.ufpe.cin.cloud.mapeamento.base.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.base.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.selecao.modelo.Estudo;

/**
 * Representa o controlador responsável pelo tratamento dos arquivos coletados
 * das conferências de Computação em nuvem.
 * 
 * @author helaine.lins
 * @created 04/08/2014 - 18:14:38
 */
public class ControladorComputacaoEmNuvem {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger
			.getLogger(ControladorComputacaoEmNuvem.class.getName());

	/**
	 * Executa o diagnóstico da leitura dos arquivos bibitex da busca manual do
	 * journal TPDS de 2006 a 2013.
	 */
	public void executarDiagnosticoBuscaManualTPDS() {
		try {
			List<Estudo> estudosColetados = new ArrayList<Estudo>();

			// tpds_2006
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2006.bib", false, 1));

			// tpds_2007
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2007.bib", false,
							estudosColetados.size() + 1));

			// tpds_2008
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2008.bib", false,
							estudosColetados.size() + 1));

			// tpds_2009
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2009.bib", false,
							estudosColetados.size() + 1));

			// tpds_2010
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2010.bib", false,
							estudosColetados.size() + 1));

			// tpds_2011
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2011.bib", false,
							estudosColetados.size() + 1));

			// tpds_2012
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2012.bib", false,
							estudosColetados.size() + 1));

			// tpds_2013
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2013.bib", false,
							estudosColetados.size() + 1));

			int qtdEstudosSemArquivo = 0;
			List<String> arquivosNaoEncontrados = new ArrayList<String>();
			for (Estudo estudo : estudosColetados) {
				if (estudo != null) {
					if (MapeamentoUtil.isEmpty(estudo.getArquivo())) {
						qtdEstudosSemArquivo++;
					}
				}
			}

			LOG.info("Estudos:" + estudosColetados.size());
			LOG.info("Estudos sem arquivos:" + qtdEstudosSemArquivo);

			for (String arquivo : arquivosNaoEncontrados) {
				LOG.info(arquivo);
			}
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar executar o diagnóstico dos arquivos de busca manual do journal TPDS",
					e);
		}
	}

	/**
	 * Realiza o tratamento dos arquivos do journal IEEE Transactions on
	 * Parallel and Distributed Systems-TPDS dos anos de 2006 a 2013.
	 */
	public void tratarBuscaManualTPDS() {
		try {

			List<Estudo> estudosColetados = new ArrayList<Estudo>();

			// tpds_2006
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2006.bib", false, 1));

			// tpds_2007
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2007.bib", false,
							estudosColetados.size() + 1));

			// tpds_2008
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2008.bib", false,
							estudosColetados.size() + 1));

			// tpds_2009
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2009.bib", false,
							estudosColetados.size() + 1));

			// tpds_2010
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2010.bib", false,
							estudosColetados.size() + 1));

			// tpds_2011
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2011.bib", false,
							estudosColetados.size() + 1));

			// tpds_2012
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2012.bib", false,
							estudosColetados.size() + 1));

			// tpds_2013
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "tpds_2013.bib", false,
							estudosColetados.size() + 1));

			// gerar planilha igual a do reviewer, para análise posterior de
			// arquivos duplicados.
			//this.preencherPlanilha(estudosColetados, "experimentos-cloud-tpds");
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar tratar os arquivos do journal TPDS.",
					e);
		}
	}

	/**
	 * Executa o diagnóstico da leitura dos arquivos bibitex da busca manual do
	 * journal Future Generation COmputer Systems-FGCS de 2006 a 2013.
	 */
	public void executarDiagnosticoBuscaManualFGCS() {
		try {
			List<Estudo> estudosColetados = new ArrayList<Estudo>();

			// fgcs_2006
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2006.bib", false, 1));

			// fgcs_2007
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2007.bib", false,
							estudosColetados.size() + 1));

			// fgcs_2008
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2008.bib", false,
							estudosColetados.size() + 1));

			// fgcs_2009
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2009.bib", false,
							estudosColetados.size() + 1));

			// fgcs_2010
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2010.bib", false,
							estudosColetados.size() + 1));

			// fgcs_2011
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2011.bib", false,
							estudosColetados.size() + 1));

			// fgcs_2012
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2012.bib", false,
							estudosColetados.size() + 1));

			// fgcs_2013
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2013.bib", false,
							estudosColetados.size() + 1));

			int qtdEstudosSemArquivo = 0;
			List<String> arquivosNaoEncontrados = new ArrayList<String>();
			for (Estudo estudo : estudosColetados) {
				if (estudo != null) {
					if (MapeamentoUtil.isEmpty(estudo.getArquivo())) {
						qtdEstudosSemArquivo++;
					}
				}
			}

			LOG.info("Estudos:" + estudosColetados.size());
			LOG.info("Estudos sem arquivos:" + qtdEstudosSemArquivo);

			for (String arquivo : arquivosNaoEncontrados) {
				LOG.info(arquivo);
			}
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar executar o diagnóstico dos arquivos de busca manual do journal FGCS",
					e);
		}
	}

	/**
	 * Realiza o tratamento dos arquivos do journal Future Generation Computer
	 * Systems-FGCS dos anos de 2006 a 2013.
	 */
	public void tratarBuscaManualFGCS() {
		try {

			List<Estudo> estudosColetados = new ArrayList<Estudo>();

			// fgcs_2006
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2006.bib", false, 1));

			// fgcs_2007
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2007.bib", false,
							estudosColetados.size() + 1));

			// fgcs_2008
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2008.bib", false,
							estudosColetados.size() + 1));

			// fgcs_2009
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2009.bib", false,
							estudosColetados.size() + 1));

			// fgcs_2010
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2010.bib", false,
							estudosColetados.size() + 1));

			// fgcs_2011
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2011.bib", false,
							estudosColetados.size() + 1));

			// fgcs_2012
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2012.bib", false,
							estudosColetados.size() + 1));

			// fgcs_2013
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "fgcs_2013.bib", false,
							estudosColetados.size() + 1));

			//this.preencherPlanilha(estudosColetados, "experimentos-cloud-fgcs");
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar tratar os arquivos do journal TPDS.",
					e);
		}
	}

	/**
	 * Executa o diagnóstico da leitura dos arquivos bibitex da busca manual da
	 * conferência International Symposium on Parallel and Distributed
	 * Processing-IPDPS de 2004 a 2013.
	 */
	public void executarDiagnosticoBuscaManualIPDPS() {
		try {
			List<Estudo> estudosColetados = new ArrayList<Estudo>();

			// ipdps_2006
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2006.bib", false, 1));

			// ipdps_2007
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2007.bib", false,
							estudosColetados.size() + 1));

			// ipdps_2008
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2008.bib", false,
							estudosColetados.size() + 1));

			// ipdps_2009
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2009.bib", false,
							estudosColetados.size() + 1));

			// ipdps_2010
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2010.bib", false,
							estudosColetados.size() + 1));

			// ipdps_2011
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2011.bib", false,
							estudosColetados.size() + 1));

			// ipdps_2012
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2012.bib", false,
							estudosColetados.size() + 1));

			// ipdps_2013
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2013.bib", false,
							estudosColetados.size() + 1));

			int qtdEstudosSemArquivo = 0;
			List<String> arquivosNaoEncontrados = new ArrayList<String>();
			for (Estudo estudo : estudosColetados) {
				if (estudo != null) {
					if (MapeamentoUtil.isEmpty(estudo.getArquivo())) {
						qtdEstudosSemArquivo++;
					}
				}
			}

			LOG.info("Estudos:" + estudosColetados.size());
			LOG.info("Estudos sem arquivos:" + qtdEstudosSemArquivo);

			for (String arquivo : arquivosNaoEncontrados) {
				LOG.info(arquivo);
			}
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar executar o diagnóstico dos arquivos de busca manual da conferência IPDPS",
					e);
		}
	}

	/**
	 * Realiza o tratamento dos arquivos da conferência IEEE Internatonal Symposium on Parallel and Distributed Processing-IPDPS de 2006 a 2013.
	 */
	public void tratarBuscaManualIPDPS() {
		try {

			List<Estudo> estudosColetados = new ArrayList<Estudo>();

			// ipdps_2006
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2006.bib", false, 1));

			// ipdps_2007
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2007.bib", false,
							estudosColetados.size() + 1));

			// ipdps_2008
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2008.bib", false,
							estudosColetados.size() + 1));

			// ipdps_2009
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2009.bib", false,
							estudosColetados.size() + 1));

			// ipdps_2010
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2010.bib", false,
							estudosColetados.size() + 1));

			// ipdps_2011
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2011.bib", false,
							estudosColetados.size() + 1));

			// ipdps_2012
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2012.bib", false,
							estudosColetados.size() + 1));

			// ipdps_2013
			estudosColetados
					.addAll(this.recuperarEstudos(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ORIGEM_CN)
									+ "/" + "ipdps_2013.bib", false,
							estudosColetados.size() + 1));

			//this.preencherPlanilha(estudosColetados, "experimentos-cloud-ipdps");
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar tratar os arquivos da conferência IEEE International Symposium on Parallel and Distributed Processing-IPDPS.",
					e);
		}
	}
	
	/**
	 * Realiza a leitura dos estudos do arquivo mapeamento-ese-manual.bib. No
	 * modo diagnóstico verifica a existência dos arquivos e dos diretórios de
	 * destino. Fora do modo diagnóstico realiza a cópia do arquivo de origem
	 * para o de destino e o renomeia para o código do estudo.
	 * 
	 * @param bibTex
	 *            O nome do arquivo do qual os estudos serão recuperados.
	 * @param diagnostico
	 *            Indica se as informações de diagnóstico devem ser logadas.
	 * @param cod
	 *            O início do contador da lista para geração do código do
	 *            estudo.
	 * @return Um {@link List} de {@link Estudo} contendo os dados dos estudos
	 *         que constam no arquivo mapeamento-ese-manual.bib.
	 */
	private List<Estudo> recuperarEstudos(String bibTex, boolean diagnostico,
			int cod) {
		List<Estudo> estudos = new ArrayList<Estudo>();

		Collection<BibTeXEntry> registros = this
				.recuperarRegistrosArquivoBibTex(bibTex);

		try {
			Estudo estudo = null;
			Value valor;
			String titulo = null;

			for (BibTeXEntry registro : registros) {
				estudo = new Estudo();

				estudo.setCodigo("MCC_" + cod);

				valor = registro.getField(org.jbibtex.BibTeXEntry.KEY_TITLE);
				if (valor != null) {
					titulo = StringUtils.replace(valor.toUserString(), "}", "");
					titulo = StringUtils.replace(titulo, "{", "");
					estudo.setTitulo(titulo);
				}

				valor = registro.getField(org.jbibtex.BibTeXEntry.KEY_AUTHOR);
				if (valor != null) {
					estudo.setAutores(valor.toUserString());
				}

				valor = registro.getField(MapeamentoUtil.TYPE_ABSTRACT);
				if (valor != null) {
					estudo.setResumo(valor.toUserString());
				}

				valor = registro.getField(org.jbibtex.BibTeXEntry.KEY_YEAR);
				if (valor != null) {
					estudo.setAno(Integer.valueOf(valor.toUserString()));
				}

				valor = registro.getField(MapeamentoUtil.TYPE_FILE);
				if (valor != null) {
					estudo.setArquivo(valor.toUserString());
				} else {
					valor = registro.getField(MapeamentoUtil.TYPE_URL);
					if (valor != null) {
						estudo.setArquivo(valor.toUserString());
					}
				}

				estudos.add(estudo);
				cod++;
			}

			LOG.debug(estudos.size() + " Estudos encontrados.");
		} catch (Exception e) {
			LOG.error("Ocorreu um erro ao obter os estudos do arquivo "
					+ bibTex, e);

			if (!diagnostico) {
				throw new MapeamentoException(
						"Ocorreu um erro ao obter os estudos do arquivo "
								+ bibTex, e);
			}
		}

		return estudos;
	}

	/**
	 * Recupera o arquivo bibtex correspondente a coleção de artigos presentes
	 * no arquivo .bib informado.
	 * 
	 * @param bibiTex
	 *            A instância que contém o caminho completo do arquivo bib a ser
	 *            tratado.
	 * @return Uma {@link Collection} contendo a lista de registros contidos no
	 *         bibtex.
	 * @throws MapeamentoException
	 *             Caso ocorra algum erro durante a obtenção dos dados do
	 *             arquivo;
	 */
	private Collection<BibTeXEntry> recuperarRegistrosArquivoBibTex(
			String bibiTex) {
		Collection<BibTeXEntry> registros = null;

		try {

			Reader reader = new InputStreamReader(new FileInputStream(bibiTex));

			BibTeXParser bibtexParser = new org.jbibtex.BibTeXParser();
			BibTeXDatabase database = bibtexParser.parse(reader);

			Map<org.jbibtex.Key, org.jbibtex.BibTeXEntry> mapaRegistros = database
					.getEntries();

			registros = mapaRegistros.values();
		} catch (Exception e) {
			LOG.error("Ocorre um erro ao tentar abrir o arquivo " + bibiTex
					+ " na pasta resources do sistema.", e);
			throw new MapeamentoException("Ocorreu um erro ao abrir o arquivo "
					+ bibiTex, e);
		}

		return registros;
	}

	

}
