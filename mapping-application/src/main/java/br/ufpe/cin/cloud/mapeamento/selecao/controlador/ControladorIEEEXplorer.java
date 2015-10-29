/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.selecao.controlador;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
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
import br.ufpe.cin.cloud.mapeamento.base.ResponseString;
import br.ufpe.cin.cloud.mapeamento.selecao.modelo.Estudo;
import br.ufpe.cin.cloud.mapeamento.selecao.modelo.EstudoComparator;

/**
 * Representa o controlador responsável pelo tratamento dos arquivos coletados
 * do engenho de busca IEEEXplorer.
 * 
 * @author helaine.lins
 * @created 30/07/2014 - 14:22:49
 */
public class ControladorIEEEXplorer {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger.getLogger(ControladorIEEEXplorer.class
			.getName());

	private final HttpClient client;
	private final RequestConfig requestConfig;
	private final Random geraRandomico;

	/**
	 * Inicializa o cliente para navegação pois o mesmo deve ser utilizado em
	 * todas as requisições para não configurar um ataque.
	 */
	public ControladorIEEEXplorer() {
		this.requestConfig = RequestConfig.custom().setSocketTimeout(5000)
				.setConnectTimeout(5000).setConnectionRequestTimeout(5000)
				.setStaleConnectionCheckEnabled(true).build();

		this.client = HttpClients.custom()
				.setDefaultRequestConfig(requestConfig).build();

		this.geraRandomico = new Random();
	}

	/**
	 * Executa o diagnóstico da leitura da planilha de estudos encontrados pelo
	 * REviewER no engenho de busca IEEEXplorer.
	 */
	public void executarDiagnosticoBibTexArquivosSelecionados() {
		try {
			List<Estudo> estudos = this.recuperarEstudosIEEE(1, 1580, true);

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
					"Ocorreu um erro ao tentar executar o diagnóstico do arquivo experimentos-cloud-ieee.xls",
					e);
		}
	}

	/**
	 * Executa o tratamento da planilha de estudos encontrados pelo REviewER no
	 * engenho de busca IEEEXplorer.
	 */
	public void tratarArquivosSelecionados(int inicio, int fim) {
		try {
			List<Estudo> estudos = this
					.recuperarEstudosIEEE(inicio, fim, false);

			int tentativa = 1;
			int qtd = inicio;
			for (Estudo estudo : estudos) {
				if (estudo != null
						&& StringUtils.isNotEmpty(estudo.getArquivo())) {

					// tenta tratar a url do arquivo até 3 vezes.
					do {
						try {
							LOG.info("Recuperando URL do estudo:" + qtd
									+ " de " + fim);
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

					this.aguardaIntervalo();
					qtd++;
				}
			}

			this.preencherPlanilha(inicio, fim, estudos);

		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar realizar o tratamento do arquivo experimentos-cloud-ieee.xls",
					e);
		}
	}
	
	/**
	 * Realiza o diagnóstico dos arquivos de resultado de processamento.
	 */
	public void executarDiagnosticoResultadosProcessamento() {
		try {
			List<Estudo> estudos = new ArrayList<Estudo>();

			File diretorio = new File(
					MapeamentoUtil
							.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_DESTINO_EEE));

			if (!diretorio.exists()) {
				LOG.error("Ocorreu um erro ao tentar unificar o resultado do tratamento dos arquivos ieee pois o diretorio:["
						+ diretorio.getPath() + "] não existe.");
			} else if (!diretorio.isDirectory()) {
				LOG.error("Ocorreu um erro ao tentar unificar o resultado do tratamento dos arquivos ieee pois o caminho:["
						+ diretorio.getPath()
						+ "] não representa um diretório.");
			} else if (!diretorio.canRead()) {
				LOG.error("Ocorreu um erro ao tentar unificar o resultado do tratamento dos arquivos ieee pois o diretorio:["
						+ diretorio.getPath() + "] não permite leitura.");
			}

			for (File planilha : diretorio.listFiles()) {
				if (!planilha.getName().equals(
						"experimentos-cloud-eee-tratado.xls")) {
					
					estudos = this.recuperarEstudosPlanilhaTemporaria(planilha);
					
					LOG.info("Planilha:[" + planilha.getName() + "] Estudos:[" + estudos.size() + "]");
					
				}
			}

		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar executar diagnóstico do resultado do tratamento dos arquivos ieee",
					e);

			throw new MapeamentoException(
					"Ocorreu um erro ao tentar executar diagnóstico do resultado do tratamento dos arquivos ieee",
					e);
		}
	}

	/**
	 * Unifica as planilhas temporárias de resultados em uma única planilha
	 * final.
	 */
	public void unificarResultadosProcessamento() {
		try {
			List<Estudo> estudos = new ArrayList<Estudo>();

			// iterar sob as planilhas do diretorio

			File diretorio = new File(
					MapeamentoUtil
							.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_DESTINO_EEE));

			if (!diretorio.exists()) {
				LOG.error("Ocorreu um erro ao tentar unificar o resultado do tratamento dos arquivos ieee pois o diretorio:["
						+ diretorio.getPath() + "] não existe.");
			} else if (!diretorio.isDirectory()) {
				LOG.error("Ocorreu um erro ao tentar unificar o resultado do tratamento dos arquivos ieee pois o caminho:["
						+ diretorio.getPath()
						+ "] não representa um diretório.");
			} else if (!diretorio.canRead()) {
				LOG.error("Ocorreu um erro ao tentar unificar o resultado do tratamento dos arquivos ieee pois o diretorio:["
						+ diretorio.getPath() + "] não permite leitura.");
			}

			for (File planilha : diretorio.listFiles()) {
				if (!planilha.getName().equals(
						"experimentos-cloud-eee-tratado.xls")) {
					estudos.addAll(this.recuperarEstudosPlanilhaTemporaria(planilha));
				}
			}

			LOG.debug(estudos.size() + " Estudos IEEEXplorer");

			//ordenar os resultados pelo codigo do estudo
			Collections.sort(estudos, new EstudoComparator());
			
			this.preencherPlanilha(0, 0, estudos);

		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar unificar o resultado do tratamento dos arquivos ieee",
					e);

			throw new MapeamentoException(
					"Ocorreu um erro ao tentar unificar o resultado do tratamento dos arquivos ieee",
					e);
		}
	}

	/**
	 * Recupera os estudos de uma determinada planilha temporária de resultado.
	 * 
	 * @param planilha
	 *            O arquivo que representa a planilha temporária.
	 * @return Um {@link List} de {@link Estudo} encontrados na planilha
	 *         temporária.
	 */
	private List<Estudo> recuperarEstudosPlanilhaTemporaria(File arqPlanilha) {

		List<Estudo> estudos = new ArrayList<Estudo>();

		try {

			Workbook workbook = WorkbookFactory.create(arqPlanilha);
			final Sheet planilha = workbook.getSheet("experimentos-cloud-ieee");

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
					estudo.setCodigo(celula.getStringCellValue());

					celula = linhaTabela.getCell(3);
					estudo.setTitulo(celula.getStringCellValue());

					celula = linhaTabela.getCell(4);
					estudo.setAutores(celula.getStringCellValue());

					celula = linhaTabela.getCell(6);
					estudo.setArquivo(celula.getStringCellValue());

					if (StringUtils.isNotEmpty(estudo.getCodigo())) {
						estudos.add(estudo);
					}
				}

				linha++;
			} while (existeProximo);

		} catch (Exception e) {
			LOG.error("Ocorreu um erro ao obter os estudos do arquivo:["
					+ arqPlanilha.getName() + "].", e);

			throw new MapeamentoException(
					"Ocorreu um erro ao obter os estudos do arquivo:["
							+ arqPlanilha.getName() + "].", e);
		}

		return estudos;
	}

	/**
	 * Força um delay aleatório entre 10 e 20 segundos antes de tratar a url do
	 * próximo estudo.
	 */
	private void aguardaIntervalo() {
		long espera = 10000;

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

	/**
	 * Realiza a leitura dos estudos existentes na planilha
	 * experimentos-cloud-ieee.xls. Realiza o processamento dos arquivos em
	 * lotes para que não ocorra problemas com o bloqueamento do serviço de
	 * busca.
	 * 
	 * @param inicio
	 *            A posição do registro inicial na planilha original
	 * @param fim
	 *            A posição do registro final na planilha original
	 * @param diagnostico
	 *            Indica se as informações de diagnóstico devem ser
	 *            apresentadas.
	 * @return Um {@link List} de {@link Estudo}
	 */
	private List<Estudo> recuperarEstudosIEEE(int inicio, int fim,
			boolean diagnostico) {
		List<Estudo> estudos = new ArrayList<Estudo>();

		try {

			final InputStream template = new FileInputStream(
					MapeamentoUtil
							.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_EEE)
							+ "/experimentos-cloud-ieee.xls");

			Workbook workbook = WorkbookFactory.create(template);
			final Sheet planilha = workbook.getSheet("experimentos-cloud-ieee");

			Row linhaTabela = null;
			boolean existeProximo = true;
			Cell celula = null;
			Estudo estudo = null;
			int linha = inicio;
			do {
				linhaTabela = planilha.getRow(linha);

				if (linhaTabela == null || linha > fim) {
					existeProximo = false;
				} else {
					estudo = new Estudo();

					celula = linhaTabela.getCell(0);
					estudo.setCodigo("IEEEX_" + linha);

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

			LOG.debug(estudos.size() + " Estudos IEEEXplorer");

		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao obter os estudos do arquivo experimentos-cloud-ieee.xls",
					e);

			if (!diagnostico) {
				throw new MapeamentoException(
						"Ocorreu um erro ao obter os estudos do arquivo experimentos-cloud-ieee.xls",
						e);
			}
		}

		return estudos;
	}

	/**
	 * Recupera a url que representa o arquivo pdf no servidor do engenho
	 * IEEEXplorer. A url contida na planilha irá ser navegada, será extraída a
	 * url da página pdf e depois será convertida para a url do pdf em si.
	 * 
	 * <pre>
	 * Exemplo url de arquivo com o cabecalho: <br>
	 * http://ieeexplore.ieee.org/iel5/5634605/5635480/05635890.pdf?arnumber=5635890 <br> 
	 * <br>
	 * Exemplo de url de arquivo sem o cabecalho <br>
	 * http://ieeexplore.ieee.org/ielx5/5634605/5635480/05635890.pdf?tp=&arnumber=5635890&isnumber=5635480
	 * </pre>
	 * 
	 * @param estudo
	 *            A instância que contém os dados do estudo.
	 * @param diagnostico
	 *            Indica se a execução é em modo diagnóstico. Em caso afirmativo
	 *            as mensagens de diagnóstico serão exibidas e exceções não
	 *            serão levantadas.
	 */
	public void recuperarUrlArquivoPdf(Estudo estudo, boolean diagnostico) {
		try {
			HttpGet get = new HttpGet(estudo.getArquivo());
			get.setConfig(this.requestConfig);

			String resultado = this.client.execute(get, new ResponseString());

			// read it with BufferedReader
			InputStream is = new ByteArrayInputStream(resultado.getBytes());
			BufferedReader bufferResultado = new BufferedReader(
					new InputStreamReader(is));

			String linha = "";
			String url = null;

			while ((linha = bufferResultado.readLine()) != null) {
				if (StringUtils.isNotEmpty(linha)
						&& StringUtils.contains(linha, "citation_pdf_url")) {
					url = StringUtils.replace(linha,
							"<meta name=\"citation_pdf_url\" content=\"", "");
					url = StringUtils.replace(url, "\">", "");
					url = StringUtils.trimToEmpty(url);
					break;
				}
			}

			String urlTratada = null;
			if (StringUtils.isNotBlank(url)) {
				String[] partes = StringUtils.split(url, "/");
				String[] codigos = StringUtils.split(partes[5], ".pdf");

				urlTratada = "http://ieeexplore.ieee.org/iel7/" + partes[3]
						+ "/" + partes[4] + "/" + codigos[0]
						+ ".pdf?tp=&arnumber="
						+ Integer.valueOf(codigos[0]).toString() + "&isnumber="
						+ partes[4];

				estudo.setArquivo(urlTratada);
			} else {
				if (diagnostico) {
					LOG.error("Não foi possível recuperar a url do pdf para o estudo:["
							+ estudo.getTitulo() + "].");
				}
			}

		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao recuperar a url do arquivo pdf do estudo:["
							+ estudo.getTitulo() + "] no IEEEXplorer.", e);

			if (!diagnostico) {
				throw new MapeamentoException(
						"Ocorreu um erro ao recuperar a url do arquivo pdf do estudo:["
								+ estudo.getTitulo() + "] no IEEEXplorer.", e);
			}

			estudo.setUrlNaoTratada(true);
		}

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
	public void preencherPlanilha(int inicio, int fim, List<Estudo> estudos) {
		try {

			LOG.info("Exportando os resultados do tratamento dos estudos IEEEXplorer");

			if (!estudos.isEmpty()) {

				final InputStream template = Thread.currentThread()
						.getContextClassLoader()
						.getResourceAsStream("experimentos-cloud.xls");

				Workbook workbook = WorkbookFactory.create(template);
				final Sheet planilha = workbook.getSheet("experimentos-cloud");

				workbook.setSheetName(
						workbook.getSheetIndex("experimentos-cloud"),
						"experimentos-cloud-ieee");

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

				String nomeArquivo = "/experimentos-cloud-eee-tratado";

				if (inicio != 0 && fim != 0) {
					nomeArquivo += "-" + inicio + "-" + fim;
				}

				FileOutputStream fileOut = new FileOutputStream(
						MapeamentoUtil
								.recuperarMensagemProperties(MapeamentoUtil.CHAVE_DIRETORIO_DESTINO_EEE)
								+ nomeArquivo + ".xls");

				workbook.write(fileOut);
				fileOut.close();
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		LOG.info("Exportação concluída!");
	}

}
