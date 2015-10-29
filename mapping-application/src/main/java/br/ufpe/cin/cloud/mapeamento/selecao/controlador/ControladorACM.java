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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import br.ufpe.cin.cloud.mapeamento.base.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.base.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.selecao.modelo.Estudo;

/**
 * Representa o controlador responsável pelo tratamento dos arquivos coletados
 * do engenho de busca ACM Digital Library.
 * 
 * @author helaine.lins
 * @created 02/08/2014 - 16:21:50
 */
public class ControladorACM {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger
			.getLogger(ControladorACM.class.getName());

	/**
	 * Inicializa o cliente para navegação pois o mesmo deve ser utilizado em
	 * todas as requisições para não configurar um ataque.
	 */
	public ControladorACM() {
		super();
	}

	/**
	 * Executa o diagnóstico da leitura da planilha de estudos encontrados pelo
	 * REviewER no engenho de busca IEEEXplorer.
	 */
	public void executarDiagnosticoPlanilhaArquivosSelecionados() {
		try {
			List<Estudo> estudos = this.recuperarEstudosACM(true);

			int qtdEstudosSemArquivo = 0;
			List<String> arquivosNaoEncontrados = new ArrayList<String>();
			for (Estudo estudo : estudos) {
				if (estudo != null) {
					if (MapeamentoUtil.isEmpty(estudo.getArquivo())) {
						qtdEstudosSemArquivo++;
						arquivosNaoEncontrados.add(estudo.getTitulo());
					}
				}
			}

			LOG.info("Estudos:" + estudos.size());
			LOG.info("Estudos sem arquivos:" + qtdEstudosSemArquivo);
			for (String arquivo : arquivosNaoEncontrados) {
				LOG.info(arquivo);
			}
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar executar o diagnóstico do arquivo experimentos-cloud-acm.xls",
					e);
		}
	}

	/**
	 * Executa o tratamento da planilha de estudos encontrados pelo REviewER no
	 * engenho de busca ACM Digital Library.
	 */
	public void tratarArquivosSelecionados() {
		try {
			List<Estudo> estudos = this.recuperarEstudosACM(false);

			int tentativa = 1;
			int qtd = 1;
			for (Estudo estudo : estudos) {
				if (estudo != null
						&& StringUtils.isNotEmpty(estudo.getArquivo())) {

					// tenta tratar a url do arquivo até 3 vezes.
					do {
						try {
							LOG.info("Recuperando URL do estudo:" + qtd
									+ " de " + 3420);
							this.recuperarUrlArquivoPdf(estudo, false);
							break;
						} catch (Exception e) {
							LOG.error(
									"Erro ao tratar url do estudo:]"
											+ estudo.getTitulo()
											+ "] tentativa:" + tentativa, e);
							tentativa++;
						}
					} while (tentativa < 4);

					LOG.info("URL do Estudo:[" + estudo.getCodigo()
							+ "] recuperada! ");

					qtd++;
				}
			}

			this.preencherPlanilha(estudos);

		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar realizar o tratamento do arquivo experimentos-cloud-acm.xls",
					e);
		}
	}

	/**
	 * Realiza a leitura dos estudos existentes na planilha
	 * experimentos-cloud-acm.xls. Realiza o processamento dos arquivos em lotes
	 * para que não ocorra problemas com o bloqueamento do serviço de busca.
	 * 
	 * @param diagnostico
	 *            Indica se as informações de diagnóstico devem ser
	 *            apresentadas.
	 * @return Um {@link List} de {@link Estudo}
	 */
	private List<Estudo> recuperarEstudosACM(boolean diagnostico) {

		List<Estudo> estudos = new ArrayList<Estudo>();

		try {

			final InputStream template = new FileInputStream(
					MapeamentoUtil
							.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_ACM)
							+ "/experimentos-cloud-acm.xls");

			Workbook workbook = WorkbookFactory.create(template);
			final Sheet planilha = workbook.getSheet("experimentos-cloud-acm");

			Row linhaTabela = null;
			boolean existeProximo = true;
			Cell celula = null;
			Estudo estudo = null;
			int linha = 1;
			do {
				linhaTabela = planilha.getRow(linha);

				if (linhaTabela == null) {
					existeProximo = false;
				} else {
					estudo = new Estudo();

					celula = linhaTabela.getCell(0);
					estudo.setCodigo("ACM_" + linha);

					celula = linhaTabela.getCell(3);
					estudo.setTitulo(celula.getStringCellValue());

					celula = linhaTabela.getCell(4);
					estudo.setAutores(celula.getStringCellValue());

					celula = linhaTabela.getCell(6);
					estudo.setArquivo(celula.getStringCellValue());

					estudos.add(estudo);
				}

				linha++;
			} while (existeProximo);

			LOG.debug(estudos.size() + " Estudos ACM Digital Library");

		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao obter os estudos do arquivo experimentos-cloud-acm.xls",
					e);

			if (!diagnostico) {
				throw new MapeamentoException(
						"Ocorreu um erro ao obter os estudos do arquivo experimentos-cloud-acm.xls",
						e);
			}
		}

		return estudos;
	}

	/**
	 * Preenche a planilha que contém o resultado do processamento dos estudos
	 * da ese.
	 * 
	 * @param inicio
	 *            A posição do registro inicial na planilha original
	 * @param fim
	 *            A posição do registro final na planilha original
	 * @param estudos
	 *            A lista que contém os dados dos estudos.
	 */
	public void preencherPlanilha(List<Estudo> estudos) {
		try {

			LOG.info("Exportando os resultados do tratamento dos estudos ACM Digital Library");

			if (!estudos.isEmpty()) {

				final InputStream template = Thread.currentThread()
						.getContextClassLoader()
						.getResourceAsStream("experimentos-cloud.xls");

				Workbook workbook = WorkbookFactory.create(template);
				final Sheet planilha = workbook.getSheet("experimentos-cloud");

				workbook.setSheetName(
						workbook.getSheetIndex("experimentos-cloud"),
						"experimentos-cloud-acm");

				CellStyle style = workbook.createCellStyle();
				style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT
						.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				style.setAlignment(CellStyle.ALIGN_CENTER);

				Font font = workbook.createFont();
				font.setColor(HSSFColor.RED.index);
				style.setFont(font);

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

						if (estudo.isUrlNaoTratada()) {
							celCodigo.setCellStyle(style);
						}

						// STATUS -> B2
						Cell celSituacao = linhaResult.createCell(1,
								Cell.CELL_TYPE_STRING);
						celSituacao.setCellValue("NOT_EVALUATED");

						if (estudo.isUrlNaoTratada()) {
							celSituacao.setCellStyle(style);
						}

						// SOURCE -> C2
						Cell celFonte = linhaResult.createCell(2,
								Cell.CELL_TYPE_STRING);
						celFonte.setCellValue("N/A");

						if (estudo.isUrlNaoTratada()) {
							celFonte.setCellStyle(style);
						}

						// TITLE -> D2
						Cell celTitulo = linhaResult.createCell(3,
								Cell.CELL_TYPE_STRING);
						celTitulo.setCellValue(estudo.getTitulo());

						if (estudo.isUrlNaoTratada()) {
							celTitulo.setCellStyle(style);
						}

						// AUTHORS -> E2
						Cell celAutores = linhaResult.createCell(4,
								Cell.CELL_TYPE_STRING);
						celAutores.setCellValue(estudo.getAutores());

						if (estudo.isUrlNaoTratada()) {
							celAutores.setCellStyle(style);
						}

						// ABSTRACT -> F2
						Cell celResumo = linhaResult.createCell(5,
								Cell.CELL_TYPE_STRING);
						celResumo.setCellValue("");

						if (estudo.isUrlNaoTratada()) {
							celResumo.setCellStyle(style);
						}

						// URL -> G2
						Cell cellUrl = linhaResult.createCell(6);
						String local = estudo.getArquivo();
						cellUrl.setCellValue(local);

						if (estudo.isUrlNaoTratada()) {
							cellUrl.setCellStyle(style);
						} else {
							Hyperlink link = workbook.getCreationHelper()
									.createHyperlink(Hyperlink.LINK_URL);

							link.setAddress(local);
							cellUrl.setHyperlink(link);
						}

						linha++;
					}

				}

				FileOutputStream fileOut = new FileOutputStream(
						MapeamentoUtil
								.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_DESTINO_ACM)
								+ "/experimentos-cloud-acm-tratado.xls");

				workbook.write(fileOut);
				fileOut.close();
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		LOG.info("Exportação concluída!");
	}

	/**
	 * Recupera a url que representa o arquivo pdf no servidor do engenho ACM
	 * Digital Library. A URL será extraída da tag meta com o nome
	 * citation_pdf_url.
	 * 
	 * <pre>
	 * Exemplo url de arquivo na página da acm: <br>
	 * <meta name="citation_pdf_url" content="http://dl.acm.org/ft_gateway.cfm?id=2499981&type=pdf">
	 * <br>
	 * 
	 * @param estudo
	 *            A instância que contém os dados do estudo.
	 * @param diagnostico
	 *            Indica se a execução é em modo diagnóstico. Em caso afirmativo
	 *            as mensagens de diagnóstico serão exibidas e exceções não
	 * serão levantadas.
	 */
	public void recuperarUrlArquivoPdf(Estudo estudo, boolean diagnostico) {
		try {
			String[] splitPonto = StringUtils.split(estudo.getArquivo(), ".");
			String[] splitComercial = StringUtils.split(splitPonto[4], "&");

			String urlTratada = "http://dl.acm.org/ft_gateway.cfm?id="
					+ splitComercial[0] + "&type=pdf";

			estudo.setArquivo(urlTratada);
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao recuperar a url do arquivo pdf do estudo:["
							+ estudo.getTitulo() + "] no ACM Digital Library.",
					e);

			if (!diagnostico) {
				throw new MapeamentoException(
						"Ocorreu um erro ao recuperar a url do arquivo pdf do estudo:["
								+ estudo.getTitulo()
								+ "] no ACM Digital Library.", e);
			}

			estudo.setUrlNaoTratada(true);
		}

	}

}
