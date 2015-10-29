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
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXParser;
import org.jbibtex.Value;

import br.ufpe.cin.cloud.mapeamento.base.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.base.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.selecao.modelo.Estudo;

/**
 * Representa o controlador responsável pelo tratamento dos arquivos coletados
 * do engenho de busca Science Direct.
 * 
 * @author helaine.lins
 * @created 04/08/2014 - 10:06:21
 */
public class ControladorScienceDirect {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger.getLogger(ControladorScienceDirect.class
			.getName());

	/**
	 * Inicializa as informações necessárias para o tratamento dos arquivos do
	 * science direct.
	 */
	public ControladorScienceDirect() {
		super();
	}

	/**
	 * Executa o diagnóstico da leitura do arqvuivo de bibitex
	 * mapeamento-ese-manual.bib.
	 */
	public void executarDiagnosticoBibTexArquivosSelecionados() {
		try {
			List<Estudo> estudos = this.recuperarEstudosScienceDirect(true);

			int qtdEstudosSemArquivo = 0;
			int qtdEstudosArquivosInexistente = 0;
			List<String> arquivosNaoEncontrados = new ArrayList<String>();
			for (Estudo estudo : estudos) {
				if (estudo != null) {
					if (MapeamentoUtil.isEmpty(estudo.getArquivo())) {
						qtdEstudosSemArquivo++;
					} else {
						if (!MapeamentoUtil.existeArquivo(estudo.getArquivo())) {
							qtdEstudosArquivosInexistente++;
							arquivosNaoEncontrados.add(estudo.getArquivo());
						}
					}
				}
			}

			LOG.info("Estudos:" + estudos.size());
			LOG.info("Estudos sem arquivos:" + qtdEstudosSemArquivo);
			LOG.info("Arquivos não encontrados:"
					+ qtdEstudosArquivosInexistente);

			for (String arquivo : arquivosNaoEncontrados) {
				LOG.info(arquivo);
			}
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar executar o diagnóstico do arquivo science-direct.bib",
					e);
		}
	}

	/**
	 * Realiza a leitura do arquivo mapeamento-ese-manual.bib para a montagem da
	 * planilha de estudos selecionados manualmente nas conferências importantes
	 * da engenharia de software empírica.
	 */
	public void tratarBibTexArquivosSelecionados() {

		// carregar o bibtex
		List<Estudo> estudos = this.recuperarEstudosScienceDirect(false);

		// gerar planilha igual a do reviewer, para análise posterior de
		// arquivos duplicados.
		this.preencherPlanilha(estudos);
		
		LOG.info("O tratamento do arquivo science-direct.bib foi concluído!");
	}

	/**
	 * Realiza a leitura dos estudos do arquivo science-direct.bib. No modo
	 * diagnóstico verifica a existência dos arquivos e dos diretórios de
	 * destino. Fora do modo diagnóstico realiza a cópia do arquivo de origem
	 * para o de destino e o renomeia para o código do estudo.
	 * 
	 * @return Um {@link List} de {@link Estudo} contendo os dados dos estudos
	 *         que constam no arquivo mapeamento-ese-manual.bib.
	 */
	private List<Estudo> recuperarEstudosScienceDirect(boolean diagnostico) {
		List<Estudo> estudos = new ArrayList<Estudo>();

		Collection<BibTeXEntry> registros = this
				.recuperarRegistrosArquivoBibTex();

		try {
			int cod = 1;

			Estudo estudo = null;
			Value valor;
			String titulo = null;

			for (BibTeXEntry registro : registros) {
				estudo = new Estudo();

				estudo.setCodigo("SD_" + cod);

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

				valor = registro.getField(org.jbibtex.BibTeXEntry.KEY_YEAR);
				if (valor != null) {
					estudo.setAno(Integer.valueOf(valor.toUserString()));
				}

				valor = registro.getField(org.jbibtex.BibTeXEntry.KEY_URL);
				if (valor != null) {
					estudo.setArquivo(valor.toUserString());
				} else {
					estudo.setUrlNaoTratada(true);
				}

				estudos.add(estudo);
				cod++;
			}

			LOG.debug(estudos.size() + " Estudos encontrados.");
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao obter os estudos do arquivo mapeamento-ese-manual.bib",
					e);

			if (!diagnostico) {
				throw new MapeamentoException(
						"Ocorreu um erro ao obter os estudos do arquivo mapeamento-ese-manual.bib",
						e);
			}
		}

		return estudos;
	}

	/**
	 * Recupera o arquivo bibtex correspondente a coleção de artigos recuperados
	 * no science direct.
	 * 
	 * @return Uma {@link Collection} contendo a lista de registros contidos no
	 *         bibtex.
	 * @throws MapeamentoException
	 *             Caso ocorra algum erro durante a obtenção dos dados do
	 *             arquivo;
	 */
	private Collection<BibTeXEntry> recuperarRegistrosArquivoBibTex() {
		Collection<BibTeXEntry> registros = null;

		try {

			Reader reader = new InputStreamReader(
					new FileInputStream(
							MapeamentoUtil
									.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_SCIENCE_DIRECT)
									+ "/mapeamento-science-direct.bib"));

			BibTeXParser bibtexParser = new org.jbibtex.BibTeXParser();
			BibTeXDatabase database = bibtexParser.parse(reader);

			// realiza a leitura do diretório que contém os arquivos dos estudos
			// que serão analisados.
			Map<org.jbibtex.Key, org.jbibtex.BibTeXEntry> mapaRegistros = database
					.getEntries();

			registros = mapaRegistros.values();
		} catch (Exception e) {
			LOG.error(
					"Ocorre um erro ao tentar abrir o arquivo mapeamento-ese-manual.bib na pasta resources do sistema.",
					e);
			throw new MapeamentoException(
					"Ocorreu um erro ao abrir o arquivo mapeamento-ese-manual.bib",
					e);
		}

		return registros;
	}

	/**
	 * Preenche a planilha que contém o resultado do processamento dos estudos
	 * da Science Direct.
	 * 
	 * @param estudos
	 *            A lista que contém os dados dos estudos.
	 */
	private void preencherPlanilha(List<Estudo> estudos) {
		try {

			if (!estudos.isEmpty()) {

				final InputStream template = Thread.currentThread()
						.getContextClassLoader()
						.getResourceAsStream("experimentos-cloud.xls");

				Workbook workbook = WorkbookFactory.create(template);
				final Sheet planilha = workbook.getSheet("experimentos-cloud");
				workbook.setSheetName(workbook.getSheetIndex("experimentos-cloud"),
						"experimentos-cloud-sd");

				Row linhaResult = null;
				int linha = 1;
				for (Estudo estudo : estudos) {

					linhaResult = planilha.getRow(linha);

					if (linhaResult == null) {
						linhaResult = planilha.createRow(linha);
					}

					if (linhaResult != null) {
						// CODE -> A2
						Cell celCodigo = linhaResult.createCell(0,
								Cell.CELL_TYPE_STRING);
						celCodigo.setCellValue(estudo.getCodigo());

						// STATUS -> B2
						Cell celSituacao = linhaResult.createCell(1,
								Cell.CELL_TYPE_STRING);
						celSituacao.setCellValue("NOT_EVALUATED");

						// SOURCE -> C2
						Cell celFonte = linhaResult.createCell(2,
								Cell.CELL_TYPE_STRING);
						celFonte.setCellValue("N/A");

						// TITLE -> D2
						Cell celTitulo = linhaResult.createCell(3,
								Cell.CELL_TYPE_STRING);
						celTitulo.setCellValue(estudo.getTitulo());

						// AUTHORS -> E2
						Cell celAutores = linhaResult.createCell(4,
								Cell.CELL_TYPE_STRING);
						celAutores.setCellValue(estudo.getAutores());

						// ABSTRACT -> F2
						Cell celResumo = linhaResult.createCell(5,
								Cell.CELL_TYPE_STRING);
						celResumo.setCellValue("");

						// URL -> G2
						Cell cellUrl = linhaResult.createCell(6);

						Hyperlink link = workbook.getCreationHelper()
								.createHyperlink(Hyperlink.LINK_URL);

						link.setAddress(estudo.getArquivo());
						cellUrl.setCellValue(estudo.getArquivo());

						cellUrl.setHyperlink(link);

						linha++;
					}

				}

				FileOutputStream fileOut = new FileOutputStream(
						MapeamentoUtil
								.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_DESTINO_SCIENCE_DIRECT)
								+ "/experimentos-cloud-science-direct-tratado.xls");

				workbook.write(fileOut);
				fileOut.close();
			}

		} catch (Exception e) {
			LOG.error(e);
		}
	}

}
