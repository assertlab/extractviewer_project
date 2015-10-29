/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import java.io.File;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IBuscaDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Busca;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;

/**
 * Representa a implementação da camada de serviços da entidade {@link Busca} no
 * sistema.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 14:46:35
 */
@Service(value = "buscaServico")
public class BuscaServico extends AbstractServico<Busca, BuscaBean> implements
		IBuscaServico {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -2481491710710234912L;

	/**
	 * Representa o mecanismo de log da classe.
	 */
	protected static Log LOG = LogFactory.getLog(BuscaServico.class);

	/**
	 * Representa a instância da camada de acesso a dados da entidade
	 * {@link Busca}.
	 */
	@Autowired
	private IBuscaDAO dao;

	/**
	 * Representa a instância da camada de negócios da entidade {@link Estudo}.
	 */
	@Autowired
	private IEstudoServico estudoServ;

	/**
	 * Cria uma nova instância do serviço inicializando os mecanismos do crawler
	 * de navegação.
	 */
	public BuscaServico() {
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getDao()
	 */
	@Override
	public IBuscaDAO getDao() {
		return this.dao;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getLog()
	 */
	@Override
	public Log getLog() {
		return LOG;
	}

	/**
	 * Atualiza a instância de dao com o valor de dao.
	 * 
	 * @param dao
	 *            Uma instância de IBuscaDAO contendo o valor a ser atualizado.
	 */
	public void setDao(IBuscaDAO dao) {
		this.dao = dao;
	}

	/**
	 * Atualiza a instância de estudoServ com o valor de estudoServ.
	 * 
	 * @param estudoServ
	 *            Uma instância de IEstudoServico contendo o valor a ser
	 *            atualizado.
	 */
	public void setEstudoServ(IEstudoServico estudoServ) {
		this.estudoServ = estudoServ;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IBuscaServico#recuperarEstudosArquivoBib(br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean)
	 */
	@Override
	public void tratarEstudosContidosArquivoBib(BuscaBean busca) {
		List<EstudoBean> estudos = new ArrayList<EstudoBean>();

		try {
			Collection<BibTeXEntry> registros = this
					.recuperarRegistrosArquivoBibTex(busca);

			if (registros != null && !registros.isEmpty()) {

				EstudoBean estudo = null;
				Value valor;
				String titulo = null;
				int cod = 1;

				for (BibTeXEntry registro : registros) {
					estudo = new EstudoBean();

					estudo.setCodigo(busca.getPrefixo() + "_" + cod);

					valor = registro
							.getField(org.jbibtex.BibTeXEntry.KEY_TITLE);
					if (valor != null) {
						titulo = StringUtils.replace(valor.toUserString(), "}",
								"");
						titulo = StringUtils.replace(titulo, "{", "");
						estudo.setTitulo(titulo);
					}

					valor = registro
							.getField(org.jbibtex.BibTeXEntry.KEY_AUTHOR);
					if (valor != null) {
						estudo.setAutores(valor.toUserString());
					}

					valor = registro.getField(EstudoBean.TYPE_ABSTRACT);
					if (valor != null) {
						estudo.setResumo(valor.toUserString());
					}

					valor = registro.getField(org.jbibtex.BibTeXEntry.KEY_YEAR);
					if (valor != null) {
						estudo.setAno(Integer.valueOf(valor.toUserString()));
					}

					valor = registro.getField(EstudoBean.TYPE_URL);
					if (valor != null) {
						estudo.setArquivo(valor.toUserString());
					} else {
						valor = registro.getField(EstudoBean.TYPE_FILE);
						if (valor != null) {
							estudo.setArquivo(valor.toUserString());
						}
					}

					estudos.add(estudo);
					cod++;
				}

				busca.setEstudos(estudos);
			}

		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao obter os estudos do arquivo "
							+ busca.getArquivoBib(), e);

			throw new MapeamentoException(new Erro(
					BuscaErroEnum.ERRO_EXTRAIR_ESTUDOS_ARQUIVO_BIB,
					TipoErro.INESPERADO, busca.getArquivoBib()), e);
		}
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IBuscaServico#tratarEstudosContidosArquivoExcel(br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean)
	 */
	@Override
	public void tratarEstudosContidosArquivoExcel(BuscaBean busca) {
		List<EstudoBean> estudos = new ArrayList<EstudoBean>();

		try {

			final InputStream template = new FileInputStream(
					busca.getDiretorio() + "/" + busca.getArquivoExcel());

			Workbook workbook = WorkbookFactory.create(template);
			final Sheet planilha = workbook.getSheetAt(0);

			if (planilha == null) {
				throw new MapeamentoException(
						new Erro(
								BuscaErroEnum.ERRO_EXTRAIR_ESTUDOS_ARQUIVO_EXCEL_PLANILHA,
								TipoErro.INESPERADO, busca.getArquivoExcel()));
			}

			Row linhaTabela = null;
			boolean existeProximo = true;
			Cell celula = null;
			EstudoBean estudo = null;
			int linha = 1;
			do {
				linhaTabela = planilha.getRow(linha);

				if (linhaTabela == null
						|| MapeamentoUtil.isEmpty(linhaTabela.getCell(0)
								.getStringCellValue())) {
					existeProximo = false;
				} else {
					estudo = new EstudoBean();

					celula = linhaTabela.getCell(0);
					estudo.setCodigo(celula.getStringCellValue());

					celula = linhaTabela.getCell(3);
					estudo.setTitulo(celula.getStringCellValue());

					celula = linhaTabela.getCell(4);
					estudo.setAutores(celula.getStringCellValue());

					celula = linhaTabela.getCell(5);
					if (celula != null) {
						estudo.setResumo(celula.getStringCellValue());
					}

					celula = linhaTabela.getCell(6);
					estudo.setArquivo(celula.getStringCellValue());

					estudos.add(estudo);
				}

				linha++;
			} while (existeProximo);

			LOG.debug(estudos.size() + " Estudos Encontrados");

			busca.setEstudos(estudos);

		} catch (Exception e) {
			LOG.error("Ocorreu um erro ao obter os estudos do arquivo excel:["
					+ busca.getArquivoExcel() + "]", e);

			throw new MapeamentoException(new Erro(
					BuscaErroEnum.ERRO_EXTRAIR_ESTUDOS_ARQUIVO_EXCEL,
					TipoErro.INESPERADO, busca.getArquivoExcel()), e);

		}

	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IBuscaServico#gerarPlanilhaEstudosContidosArquivoBib(br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean)
	 */
	@Override
	public void gerarPlanilhaEstudosContidosArquivoBib(BuscaBean busca) {
		try {
			this.tratarEstudosContidosArquivoBib(busca);
			this.preencherPlanilha(busca);
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao gerar planilha:["
							+ busca.getArquivoExcel()
							+ "] dos estudos contidos no arquivo bib:["
							+ busca.getArquivoBib() + "]", e);

			throw new MapeamentoException(new Erro(
					BuscaErroEnum.ERRO_GERAR_PLANILHA_ESTUDOS_ARQUIVO_BIB,
					TipoErro.INESPERADO, busca.getArquivoExcel(),
					busca.getArquivoBib()), e);
		}
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IBuscaServico#executarDiagnosticoArquivoBib(br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean)
	 */
	@Override
	public void executarDiagnosticoArquivoBib(BuscaBean busca) {
		try {
			this.tratarEstudosContidosArquivoBib(busca);

			int qtdEstudosSemArquivo = 0;
			int qtdEstudos = 0;
			List<String> arquivosNaoEncontrados = new ArrayList<String>();

			if (busca.getEstudos() != null && !busca.getEstudos().isEmpty()) {
				for (EstudoBean estudo : busca.getEstudos()) {
					if (estudo != null) {
						if (MapeamentoUtil.isEmpty(estudo.getArquivo())) {
							qtdEstudosSemArquivo++;
						}
					}
				}

				qtdEstudos = busca.getEstudos().size();
			}

			LOG.info("Estudos:" + qtdEstudos);
			LOG.info("Estudos sem arquivos:" + qtdEstudosSemArquivo);

			for (String arquivo : arquivosNaoEncontrados) {
				LOG.info(arquivo);
			}
		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar executar o diagnóstico do arquivo bib:["
							+ busca.getArquivoBib() + "]", e);
		}

	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IBuscaServico#importarEstudosParaBaseDados(br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean)
	 */
	@Override
	@Transactional(readOnly = false)
	public void importarEstudosParaBaseDados(BuscaBean busca) {
		try {

			if (busca.getEstudos() == null || busca.getEstudos().isEmpty()) {
				LOG.info("Não foram encontrados estudos para importação na busca:["
						+ busca + "]");
			} else {

				this.estudoServ.incluir(busca, busca.getEstudos());
			}

		} catch (Exception e) {
			LOG.error(
					"Ocorreu um erro ao tentar importar os estudos da busca:["
							+ busca + "]", e);

			throw new MapeamentoException(new Erro(
					BuscaErroEnum.ERRO_INCLUIR_ESTUDOS_BUSCA,
					TipoErro.VALIDACAO));
		}
	}

	/**
	 * Recupera o arquivo bibtex correspondente a coleção de artigos presentes
	 * no arquivo .bib informado.
	 * 
	 * @param busca
	 *            A instância que contém o caminho completo do arquivo bib a ser
	 *            tratado.
	 * @return Uma {@link Collection} contendo a lista de registros contidos no
	 *         bibtex.
	 * @throws MapeamentoException
	 *             Caso ocorra algum erro durante a obtenção dos dados do
	 *             arquivo;
	 */
	private Collection<BibTeXEntry> recuperarRegistrosArquivoBibTex(
			BuscaBean busca) {

		Collection<BibTeXEntry> registros = null;

		try {

			this.validarDiretorioDestino(busca);

			String nomeCompl = busca.getDiretorio() + "/"
					+ busca.getArquivoBib();

			Reader reader = new InputStreamReader(
					new FileInputStream(nomeCompl));

			BibTeXParser bibtexParser = new org.jbibtex.BibTeXParser();
			BibTeXDatabase database = bibtexParser.parse(reader);

			Map<org.jbibtex.Key, org.jbibtex.BibTeXEntry> mapaRegistros = database
					.getEntries();

			registros = mapaRegistros.values();
		} catch (Exception e) {
			LOG.error("Ocorre um erro ao tentar abrir o arquivo " + busca
					+ " na pasta " + busca.getDiretorio(), e);
			throw new MapeamentoException(new Erro(
					BuscaErroEnum.ERRO_ABRIR_ARQUIVO_BIB, TipoErro.INESPERADO,
					busca.getArquivoBib(), busca.getDiretorio()), e);
		}

		return registros;
	}

	/**
	 * Gera uma planilha contendo os dados da lista de estudos da busca.
	 * 
	 * @param busca
	 *            A instância que contém os dados da busca.
	 */
	private void preencherPlanilha(BuscaBean busca) {
		try {

			this.validarDiretorioDestino(busca);

			if (!busca.getEstudos().isEmpty()) {

				final InputStream template = Thread.currentThread()
						.getContextClassLoader()
						.getResourceAsStream("experimentos-cloud.xlsx");

				Workbook workbook = WorkbookFactory.create(template);
				final Sheet planilha = workbook.getSheet("experimentos-cloud");
				workbook.setSheetName(
						workbook.getSheetIndex("experimentos-cloud"),
						"experimentos-cloud-cn");

				Row linhaResult = null;
				int linha = 1;
				for (EstudoBean estudo : busca.getEstudos()) {

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
						celResumo.setCellValue(estudo.getResumo());

						// URL -> G2
						Cell cellUrl = linhaResult.createCell(6);
						String local = estudo.getArquivo();

						Hyperlink link = workbook.getCreationHelper()
								.createHyperlink(Hyperlink.LINK_URL);

						link.setAddress(local);
						cellUrl.setCellValue(local);

						cellUrl.setHyperlink(link);
						linha++;
					}

				}

				FileOutputStream fileOut = new FileOutputStream(
						busca.getDiretorio() + "/" + busca.getArquivoExcel()
								+ ".xlsx");

				workbook.write(fileOut);
				fileOut.close();
			}

		} catch (Exception e) {
			LOG.error(e);
		}
	}

	/**
	 * Valida as condições necessárias para leitura e escrita no diretório da
	 * busca no sistema.
	 * 
	 * @param busca
	 *            A instância que contém os dados da busca.
	 */
	private void validarDiretorioDestino(BuscaBean busca) {
		File diretorio = new File(busca.getDiretorio());

		if (diretorio == null || !diretorio.exists()) {
			LOG.debug("Criando o diretorio:[" + busca.getDiretorio() + "]");
			diretorio.mkdirs();
		} else if (!diretorio.isDirectory()) {
			LOG.error("O caminho:[" + diretorio.getPath()
					+ "] não representa um diretório.");

			throw new MapeamentoException(new Erro(
					BuscaErroEnum.ERRO_DESTINO_NAO_DIRETORIO,
					TipoErro.VALIDACAO, busca.getDiretorio()));

		} else if (!diretorio.canRead()) {
			LOG.error("Ocorreu um erro ao tentar unificar o resultado do tratamento dos arquivos ieee pois o diretorio:["
					+ diretorio.getPath() + "] não permite leitura.");

			throw new MapeamentoException(new Erro(
					BuscaErroEnum.ERRO_DESTINO_PERMISSAO_LEITURA,
					TipoErro.VALIDACAO, busca.getDiretorio()));
		}
	}
	
	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#copiarDadosParaAlterarEntidade(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean, br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	protected void copiarDadosParaAlterarEntidade(BuscaBean bean, Busca entidade) {
		entidade.setArquivoBib(bean.getArquivoBib());
		entidade.setArquivoExcel(bean.getArquivoExcel());
		entidade.setConferencia(bean.getConferencia());
		entidade.setData(bean.getData());
		entidade.setDataUltimaAlteracao(LocalDateTime.now());
		entidade.setDiretorio(bean.getDiretorio());
		entidade.setFim(bean.getFim());
		entidade.setInicio(bean.getInicio());
		entidade.setManual(bean.isManual());
		entidade.setPrefixo(bean.getPrefixo());
		entidade.setQtdEstudos(bean.getQtdEstudos());
		entidade.setString(bean.getString());
	}

}
