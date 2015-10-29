/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.base;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jbibtex.Key;

/**
 * Representa a implementação de metodos utilitários do sistema.
 * 
 * @author helaine.lins
 * @created 29/07/2014 - 09:18:47
 */
public class MapeamentoUtil {

	/**
	 * Representa a chave do diretório de destino dos arquivos selecionados da
	 * ese no arquivo de propriedades.
	 */
	public static final String CHAVE_DIRETORIO_DESTINO_ESE = "config.diretorio.destino.ese";
	
	/**
	 * Representa a chave do diretório de destino dos arquivos selecionados da
	 * computação em nuvem no arquivo de propriedades.
	 */
	public static final String CHAVE_DIRETORIO_DESTINO_CN = "config.diretorio.destino.cn";
	
	/**
	 * Representa a chave do diretório de destino dos arquivos selecionados da
	 * Science Direct no arquivo de propriedades.
	 */
	public static final String CHAVE_DIRETORIO_DESTINO_SCIENCE_DIRECT = "config.diretorio.destino.sciencedirect";
	
	/**
	 * Representa a chave do diretório de destino dos arquivos selecionados da
	 * Science Direct no arquivo de propriedades.
	 */
	public static final String CHAVE_DIRETORIO_DESTINO_BIB = "config.diretorio.destino";
	
	/**
	 * Representa a chave do diretório do IEEEXplorer no arquivo de propriedades.
	 */
	public static final String CHAVE_DIRETORIO_EEE = "config.diretorio.ieee";
	
	/**
	 * Representa a chave do diretório do ACM Digital Library no arquivo de propriedades.
	 */
	public static final String CHAVE_DIRETORIO_ACM = "config.diretorio.acm";
	
	/**
	 * Representa a chave do diretório do Science Direct no arquivo de propriedades.
	 */
	public static final String CHAVE_DIRETORIO_SCIENCE_DIRECT = "config.diretorio.sciencedirect";
	
	/**
	 * Representa a chave do diretório dos arquivos bib de resultados de busca.
	 */
	public static final String CHAVE_DIRETORIO_BIB = "config.diretorio.bib";
	
	/**
	 * Representa a chave do diretório de destino dos arquivos selecionados da
	 * IEEEXplorer no arquivo de propriedades.
	 */
	public static final String CHAVE_DIRETORIO_DESTINO_EEE = "config.diretorio.destino.ieee";
	
	/**
	 * Representa a chave do diretório de destino dos arquivos selecionados da
	 * ACM Digital Library no arquivo de propriedades.
	 */
	public static final String CHAVE_DIRETORIO_DESTINO_ACM = "config.diretorio.destino.acm";
	
	/**
	 * Representa a chave do diretório de origem dos arquivos bib do mapeamento manual da computação em nuvem.
	 */
	public static final String CHAVE_DIRETORIO_ORIGEM_CN = "config.diretorio.origem.manualcloud";

	/**
	 * Representa a propriedade file no arquivo .bib
	 */
	public static final Key TYPE_FILE = new Key("file");
	
	/**
	 * Representa a propriedade abstract no arquivo .bib
	 */
	public static final Key TYPE_ABSTRACT = new Key("abstract");
	
	/**
	 * Representa a propriedade url no arquivo .bib
	 */
	public static final Key TYPE_URL = new Key("url");
	
	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger
			.getLogger(MapeamentoUtil.class.getName());

	/**
	 * Representa o arquivo de propriedades das mensagens do sistema.
	 */
	private static PropertiesConfiguration arqProperties = null;

	/**
	 * Cria uma nova instância da classe.
	 */
	private MapeamentoUtil() {
		// força que os métodos sejam estáticos.
	}

	/**
	 * Recupera uma mensagem do arquivo de propriedades através da chave da
	 * mesma.
	 * 
	 * @param chave
	 *            A chave que representa a mensagem no arquivo de propriedades.
	 * @param argumentos
	 *            A lista de argumentos a serem substituidos na mensagem
	 *            recuperada.
	 * @return Uma {@link String} contendo a mensagem encontrada ou uma string
	 *         vazia se não for possível recuperar a mensagem.
	 */
	public static String recuperarMensagemProperties(String chave,
			Object... argumentos) {
		String mensagem = "";

		if (chave == null) {
			throw new IllegalArgumentException(
					"A chave da mensagem deve ser informada");
		}

		try {
			if (arqProperties == null) {
				arqProperties = new PropertiesConfiguration(
						"MessageBundle.properties");
			}

			mensagem = arqProperties.getString(chave);

			if (mensagem != null && argumentos != null) {
				mensagem = MessageFormat.format(mensagem, argumentos);
			}
		} catch (Exception e) {
			LOG.error("Erro ao recuperar mensagem de properties com a chave:"
					+ chave, e);
		}

		return mensagem;
	}

	/**
	 * Identifica se uma {@link Collection} esta nula ou vazia.
	 * 
	 * @param colecao
	 *            A instância a ser validada.
	 * 
	 * @return Retorna <code>true</code> caso a coleção esteja nula ou vazia,
	 *         caso contrário retorna <code>false</code>.
	 */
	public static boolean isEmpty(Collection<?> colecao) {
		return colecao == null || colecao.isEmpty();
	}

	/**
	 * Identifica se uma {@link Collection} não esta nula ou vazia.
	 * 
	 * @param colecao
	 *            A instância a ser validada.
	 * 
	 * @return Retorna <code>false</code> caso a coleção esteja nula ou vazia,
	 *         caso contrário retorna <code>true</code>.
	 */
	public static boolean isNotEmpty(Collection<?> colecao) {
		return colecao != null && !colecao.isEmpty();
	}

	/**
	 * Identifica se o objeto preenchido é nulo.
	 * 
	 * @param objeto
	 *            a instância a ser validada.
	 * @return Retorna <code>true</code> caso o objeto seja nulo, caso contrário
	 *         retorna <code>false</code>.
	 */
	public static boolean isNull(Object objeto) {
		return objeto == null;
	}

	/**
	 * Identifica se o objeto preenchido não é nulo.
	 * 
	 * @param objeto
	 *            a instância a ser validada.
	 * @return Retorna <code>false</code> caso o objeto seja nulo, caso
	 *         contrário retorna <code>true</code>.
	 */
	public static boolean isNotNull(Object objeto) {
		return objeto != null;
	}

	/**
	 * Identifica se o objeto preenchido é nulo.
	 * 
	 * @param objeto
	 *            a instância a ser validada.
	 * @return Retorna <code>false</code> caso o objeto seja nulo, caso
	 *         contrário retorna <code>true</code>.
	 */
	public static boolean isEmpty(String string) {
		return StringUtils.isEmpty(string);
	}
	
	/**
	 * Identifica se o objeto preenchido não é nulo.
	 * 
	 * @param objeto
	 *            a instância a ser validada.
	 * @return Retorna <code>true</code> caso o objetonão  seja nulo, caso
	 *         contrário retorna <code>false</code>.
	 */
	public static boolean isNotEmpty(String string) {
		return StringUtils.isNotEmpty(string);
	}
	
	/**
	 * Realiza a cópia dos atributos em comum dos objetos.
	 * 
	 * @param origem
	 *            a instância que contém os dados a serem copiados.
	 * @param destino
	 *            a instância que irá receber a cópia dos dados.
	 */
	public static void copiarAtributos(Object origem, Object destino) {
		try {
			java.util.Date defaultValue = null;
			DateConverter converter = new DateConverter(defaultValue);
			ConvertUtils.register(new LongConverter(null), Long.class);
			ConvertUtils.register(new IntegerConverter(null), Integer.class);
			ConvertUtils.register(converter, java.util.Date.class);
			ConvertUtils.register(new BigDecimalConverter(null),
					java.math.BigDecimal.class);
			BeanUtils.copyProperties(destino, origem);
		} catch (IllegalAccessException e) {
			LOG.trace(
					"Ocorreu um IllegalAccessException ao realizar a copia dos dados em copiarAtributos.",
					e);
		} catch (InvocationTargetException e) {
			LOG.trace(
					"Ocorreu um InvocationTargetException ao realizar a copia dos dados em copiarAtributos.",
					e);
		}
	}

	/**
	 * Realiza a cópia de um arquivo para um determinado diretório e sem alterar
	 * o nome do mesmo.
	 * 
	 * @param diagnostico
	 *            Indica se é necessário logar a existência do diretório de
	 *            destino e se o mesmo permite leitura a escrita.
	 * @param arqOrigem
	 *            O arquivo a ser copiado
	 * @param dirDest
	 *            O dirtório de destino
	 * @return Retorna <code>true</code> se o arquivo foi copiado, caso
	 *         contrário retorna <code>false</code>
	 */
	public static boolean copiarArquivo(boolean diagnostico, File arqOrigem,
			File dirDest) {
		boolean dadosValidos = true;

		try {
			if (diagnostico) {
				if (!dirDest.exists()) {
					LOG.error("O diretorio de destino ["
							+ dirDest.getAbsolutePath() + "] não existe!");

					dadosValidos = false;
				}

				if (!dirDest.canRead() || !dirDest.canWrite()) {
					LOG.error("A aplicação não tem permissão de leitura e escrita no diretorio de destino ["
							+ dirDest.getAbsolutePath() + "] !");

					dadosValidos = false;
				}

				if (!arqOrigem.exists()) {
					LOG.error("O arquivo: [" + arqOrigem.getAbsolutePath()
							+ "/" + arqOrigem.getName() + "] não existe!");

					dadosValidos = false;
				}
			}
			if (!diagnostico && dadosValidos) {
				FileUtils.copyFileToDirectory(arqOrigem, dirDest);

				LOG.debug("Arquivo: [" + arqOrigem.getName()
						+ "] copiado para:[" + dirDest.getAbsolutePath() + "]!");
			}
		} catch (Exception e) {
			LOG.error(
					"Ocorreu uma falha ao copiar o arquivo:["
							+ arqOrigem.getName() + "] para o diretorio:"
							+ dirDest.getAbsolutePath(), e);
			dadosValidos = false;
		}

		return dadosValidos;
	}

	/**
	 * Renomeia o arquivo.
	 * 
	 * @param diagnostico
	 *            Indica se é necessário logar a existência do arquivo.
	 * @param arqOrigem
	 *            O arquivo a ser renomeado.
	 * @param nome
	 *            O novo nome do arquivo
	 * @return Retorna <code>true</code> se o arquivo foi renomeado, caso
	 *         contrário retorna <code>false</code>
	 */
	public static boolean renomearArquivo(boolean diagnostico, File arqOrigem,
			String nome) {
		boolean dadosValidos = true;

		try {

			if (diagnostico) {
				if (!arqOrigem.exists()) {
					LOG.error("Não é possível renomear o arquivo ["
							+ arqOrigem.getAbsolutePath() + "/"
							+ arqOrigem.getName() + "] não existe!");

					dadosValidos = false;
				}

				if (MapeamentoUtil.isEmpty(nome)) {
					LOG.error("Não é possível renomear o arquivo:[ "
							+ arqOrigem.getName()
							+ "] pois o novo nome não foi informado!");
					dadosValidos = false;
				}
			}

			if (!diagnostico && dadosValidos) {

				FileUtils.moveFile(
						FileUtils.getFile(arqOrigem.getPath()),
						FileUtils.getFile(StringUtils.replace(
								arqOrigem.getParent(), "\\", "/")
								+ "/" + nome));

				LOG.debug("Arquivo renomeado para:" + arqOrigem.getName());
			}
		} catch (Exception e) {
			LOG.error(
					"Ocorreu uma falha ao renomear o arquivo:["
							+ arqOrigem.getName() + "] para:" + nome, e);
			dadosValidos = false;
		}

		return dadosValidos;
	}

	/**
	 * Verifica se um determinado arquivo existe.
	 * 
	 * @param nome
	 *            O nome completo do arquivo.
	 * @return Retorna <code>true</code> se o arquivo exista, caso não exista ou
	 *         ocorra algum erro durante a identificação será retornado
	 *         <code>false</code>.
	 */
	public static boolean existeArquivo(String nome) {
		boolean existe = false;

		try {
			existe = new File(nome).exists();
		} catch (Exception e) {
			LOG.error("Ocorreu uma falha ao verificar se o arquivo:[" + nome
					+ "] existe!", e);
		}

		return existe;
	}

//	public savePDF(def url, def path) throws IOException {
//
//
//		println "savePDF="+url
//
//
//		//sleep(TIME_PER_PAPER + Math.abs(new Random().nextInt() % TIME_RANDOM_PER_PAPER + 1))
//
//		HttpGet get = new HttpGet(url)
//
//		//objeto singleton -> HttpClient client = HttpClientBuilder.create().build()
//
//		HttpResponse response = client.execute(get)
//
//
//
//		InputStream input = null
//
//		OutputStream output = null
//
//		byte[] buffer = new byte[1024]
//
//
//
//		def indexname = url.lastIndexOf("/")+1
//
//		String name = url.substring(indexname)
//
//
//
//		try {
//
//		println "Downloading file..."
//
//		input = response.getEntity().getContent()
//
//		File file = createFile(path, name)
//
//
//		output = new FileOutputStream(file, false)
//
//		for (int length; (length = input.read(buffer)) > 0;) {
//
//		output.write(buffer, 0, length)
//
//		}
//
//		println "File "+name+" successfully downloaded!"
//
//		}  catch (Exception e){
//
//		e.printStackTrace()
//
//		File file = createFile(path, errorFileName)
//
//		file << url+"- on ERROR Download FILE \n" 
//
//		} finally {
//
//		if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
//
//		if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
//
//		}
//
//		}
}
