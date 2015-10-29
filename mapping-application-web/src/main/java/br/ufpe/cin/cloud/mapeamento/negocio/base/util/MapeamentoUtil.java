/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.text.Normalizer;
import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;

/**
 * Representa a implementação de métodos utilitários do sistema.
 * 
 * @author helaine.lins
 * @created 01/04/2014 - 17:38:47
 */
public class MapeamentoUtil {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static final Log LOG = LogFactory.getLog(MapeamentoUtil.class);

	/**
	 * Representa o arquivo de propriedades das mensagens do sistema.
	 */
	private static PropertiesConfiguration arqProperties = null;

	/**
	 * Representa o encriptador de senhas utilizado pelo Spring Secutity
	 */
	private static Md5PasswordEncoder encriptador = new Md5PasswordEncoder();

	/**
	 * Expressão regular para remover caracteres acentuados de uma string.
	 */
	private static final String EXPRESSAO_REGULAR_CARACTERES_ACENTUADOS = "\\p{InCombiningDiacriticalMarks}+";

	/**
	 * Cria uma nova instância da classe.
	 */
	private MapeamentoUtil() {
		// força que os métodos sejam estáticos.
		try {
			arqProperties = new PropertiesConfiguration(
					"MessageBundle.properties");
		} catch (ConfigurationException e) {
			LOG.error("Erro ao abrir o arquivo de propriedades", e);
		}
	}

	/**
	 * Indica se o identificador da entidade está preenchido com o valor válido.
	 * 
	 * @param entidade
	 *            - a entidade a ser verificada.
	 * @return <code>true</code> se o identificador for diferente de
	 *         <code>null</code> e maior que zero.
	 */
	public static boolean isIdentificadorPreenchido(Entidade entidade) {
		boolean preenchido = false;
		if (entidade != null && entidade.getEntidadeID() != null
				&& entidade.getEntidadeID() > 0) {
			preenchido = true;
		}
		return preenchido;
	}

	/**
	 * Recupera o nome do método get correspondente ao atributo informado,
	 * seguindo as convenções do javabean.
	 * 
	 * @param atributo
	 *            o nome do atributo.
	 * @return uma {@link String} contendo o nome do método get segundo o
	 *         javabean getNomeAtributo.
	 */
	public static String recuperarNomeMetodoGet(Field atributo) {
		String prefixo = null;

		Class<?> type = atributo.getType();
		if (type.isPrimitive() && type.isAssignableFrom(Boolean.TYPE)) {
			prefixo = "is";
		} else {
			prefixo = "get";
		}

		return prefixo + WordUtils.capitalize(atributo.getName());
	}

	/**
	 * Identifica se uma {@link Collection} esta nula ou vazia.This method
	 * returns true if the collection is null or is empty.
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
	 * Identifica se uma {@link Collection} não esta nula ou vazia.This method
	 * returns true if the collection is null or is empty.
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
	 * Identifica se o objeto preenchido não é nulo.
	 * 
	 * @param objeto
	 *            a instância a ser validada.
	 * @return Retorna <code>false</code> caso o objeto seja nulo, caso
	 *         contrário retorna <code>true</code>.
	 */
	public static boolean isEmpty(String string) {
		return string == null || string.isEmpty();
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
	 * Valida se todos os objetos informados estão diferentes de nulo.
	 * 
	 * @param objetos
	 *            Representa a lista de objetos a serem validados.
	 * @return Retorna <code>true</code> caso um dos objetos informados na lista
	 *         estejam diferentes de nulo, caso contrário retorna
	 *         <code>false</code>.
	 */
	public static boolean possuiElementosNaoNulos(Object... objetos) {
		boolean preenchidos = true;

		for (Object objeto : objetos) {
			if (isNull(objeto)) {
				preenchidos = false;
				break;
			}
		}

		return preenchidos;
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
	 * Obtém uma string listando todos os valores preenchidos em um objeto.
	 * 
	 * @param objeto
	 *            A instância que contém os valores a serem listados.
	 * @return Uma {@link String} contendo a listagem de todos os atributos do
	 *         objeto.
	 */
	public static String listarValoresPropriedades(Object objeto) {
		StringBuilder valores = new StringBuilder();

		if (objeto != null) {
			Field[] atributos = objeto.getClass().getDeclaredFields();

			if (atributos != null && atributos.length > 0) {

				for (Field atributo : atributos) {

					if (atributo != null
							&& !Modifier.isStatic(atributo.getModifiers())) {

						valores.append(atributo.getName());
						valores.append("::");

						if (atributo.getType().isAssignableFrom(
								Collection.class)) {
							valores.append(obterValorArray(objeto,
									atributo.getName()));
						} else {
							valores.append(obterValorString(objeto,
									atributo.getName()));
						}

						valores.append(" ");
					}

				}
			}
		}

		return valores.toString();
	}

	/**
	 * Recupera o valor de uma propriedade em uma determinada instância.
	 * 
	 * @param objeto
	 *            A instância que contém o valor a ser recuperado.
	 * @param propriedade
	 *            O nome da propriedade a ser recuperada.
	 * @return Uma {@link String} contendo o valor recuperado ou
	 *         <code>null</code> caso o mesmo não possua valor.
	 */
	public static String obterValorString(Object objeto, String propriedade) {
		LOG.trace("iniciando obterValorString");

		String valor = null;

		if (objeto != null && StringUtils.isNotBlank(propriedade)) {
			try {
				valor = BeanUtils.getSimpleProperty(objeto, propriedade);
				LOG.debug("Recuperada propriedade " + propriedade + " valor:"
						+ valor + "no objeto "
						+ objeto.getClass().getSimpleName());
			} catch (Exception e) {
				LOG.info("Erro ao recuperar o valor da propriedade "
						+ propriedade + "no objeto "
						+ objeto.getClass().getSimpleName(), e);
			}
		}

		LOG.trace("finalizando obterValorString");
		return valor;
	}

	/**
	 * Recupera o valor de uma propriedade em uma determinada instância.
	 * 
	 * @param objeto
	 *            A instância que contém o valor a ser recuperado.
	 * @param propriedadeLista
	 *            O nome da propriedade a ser recuperada.
	 * @return Uma {@link String} contendo o valor recuperado ou
	 *         <code>null</code> caso o mesmo não possua valor.
	 */
	public static String obterValorArray(Object objeto, String propriedadeLista) {
		LOG.trace("iniciando obterValorString");

		StringBuilder valor = new StringBuilder();

		if (objeto != null && StringUtils.isNotBlank(propriedadeLista)) {
			try {
				String[] valores = BeanUtils.getArrayProperty(objeto,
						propriedadeLista);

				for (String valorString : valores) {
					valor.append(valorString);
					valor.append(" ");
				}

				LOG.debug("Recuperada propriedade " + propriedadeLista
						+ " valor:" + valor + "no objeto "
						+ objeto.getClass().getSimpleName());
			} catch (Exception e) {
				LOG.info("Erro ao recuperar o valor da propriedade "
						+ propriedadeLista + "no objeto "
						+ objeto.getClass().getSimpleName(), e);
			}
		}

		LOG.trace("finalizando obterValorString");
		return valor.toString();
	}

	/**
	 * Encripta a senha.
	 * 
	 * @param senha
	 *            a senha ser encriptada
	 * @return a senha encriptada
	 */
	public static String encriptarSenha(String senha) {
		return encriptador.encodePassword(senha, null);
	}

	/**
	 * Realiza a validação do CPF do {@link UsuarioBean}.
	 * 
	 * @param cpf
	 *            O CPF a ser validado.
	 * @return true se o CPF é válido, false caso contrário.
	 */
	public static boolean validarCpf(String cpf) {
		if (cpf.equals("11111111111") || cpf.equals("00000000000")
				|| cpf.equals("22222222222") || cpf.equals("33333333333")
				|| cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777")
				|| cpf.equals("88888888888") || cpf.equals("99999999999")) {
			return false;
		}

		char[] digitos = cpf.substring(0, 9).toCharArray();
		char dv1 = cpf.charAt(9);
		char dv2 = cpf.charAt(10);

		int soma = 0;
		for (int i = 10; i > 1; i--) {
			soma += Character.digit(digitos[10 - i], 10) * i;
		}

		int dv1Calculado = (soma % 11 < 2) ? 0 : (11 - (soma % 11));
		if (Character.digit(dv1, 10) != dv1Calculado) {
			return false;
		}

		soma = 0;
		for (int i = 11; i > 2; i--) {
			soma += Character.digit(digitos[11 - i], 10) * i;
		}
		soma += dv1Calculado * 2;

		int dv2Calculado = (soma % 11 < 2) ? 0 : (11 - (soma % 11));
		if (Character.digit(dv2, 10) != dv2Calculado) {
			return false;
		}

		return true;
	}

	/**
	 * Valida o PIS/PASEPE/NIT informado pelo suposto fiscal.
	 * 
	 * @param pis
	 * @return true caso o valor passado seja válido, false caso contrário.
	 */
	public static boolean validarPisNit(String pis) {
		if (pis.equals("11111111111") || pis.equals("00000000000")
				|| pis.equals("22222222222") || pis.equals("33333333333")
				|| pis.equals("44444444444") || pis.equals("55555555555")
				|| pis.equals("66666666666") || pis.equals("77777777777")
				|| pis.equals("88888888888") || pis.equals("99999999999")) {
			return false;
		}

		int[] multiplicador = { 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		char[] digitos = pis.substring(0, 10).toCharArray();

		int soma = 0;
		int resto = 0;
		for (int i = 0; i < digitos.length; i++) {
			soma += Character.digit(digitos[i], 10) * multiplicador[i];
		}

		resto = soma % 11;

		if (resto < 2) {
			resto = 0;
		} else {
			resto = 11 - resto;
		}

		return pis.endsWith(String.valueOf(resto));
	}

	/**
	 * Recupera os dados do usuário logado da sessão.
	 * 
	 * @return A instância do {@link UsuarioBean} logado no sistema.
	 */
	public static Usuario obterUsuarioLogado() {
		Usuario usuario = null;

		if (SecurityContextHolder.getContext() != null
				&& SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal() != null) {

			Object principal = SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();

			if (principal instanceof Usuario) {
				usuario = (Usuario) principal;
			}
		}

		return usuario;
	}

	/**
	 * Identifica se a propriedade informada na {@link String} encontra-se na
	 * lista de propriedades a serem ignoradas.
	 * 
	 * @param propriedade
	 *            A instância da string a ser procurada.
	 * @param propIgnoradas
	 *            A instância que contém a lista de strings onde a procura será
	 *            realizada.
	 * @return <code>true</code> se a "string" estiver contida na lista de
	 *         "strings", caso contrário <code>false</code>.
	 */
	public static boolean isPropriedadeEmLista(String propriedade,
			String... propIgnoradas) {
		boolean isContida = false;

		if (propIgnoradas != null) {
			for (String stringAtual : propIgnoradas) {
				if (stringAtual.equals(propriedade)) {
					isContida = true;
				}
			}
		}

		return isContida;
	}

	/**
	 * BuscaBean um método numa classe de acordo com seus nome e parâmetros, se
	 * necessário.
	 * 
	 * @param classObjeto
	 *            {@link Class} correspondente ao tipo do objeto que contem o
	 *            método especificado.
	 * @param nomeMetodo
	 *            Nome do método que será retornado.
	 * @param parametros
	 *            Vargs de {@link Class} contendo os tipos dos parâmetros dos
	 *            métodos passados.
	 * @return {@link RuntimeException} Caso ocorra algum problema ao tentar
	 *         buscar o método.
	 */
	public static Method buscarMetodo(Class<?> classObjeto, String nomeMetodo,
			Class<?>... parametros) {

		Method metodo = null;

		if (classObjeto == null) {
			throw new IllegalArgumentException(
					"O parametro classObjeto deve ser informado");
		}

		if (nomeMetodo == null) {
			throw new IllegalArgumentException(
					"O parametro nomeMetodo deve ser informado");
		}

		try {
			metodo = classObjeto.getDeclaredMethod(nomeMetodo, parametros);
		} catch (Exception e) {
			try {
				metodo = classObjeto.getSuperclass().getDeclaredMethod(
						nomeMetodo, parametros);
			} catch (Exception e1) {
				throw new RuntimeException("Erro ao procurar método"
						+ nomeMetodo, e);
			}
		}

		return metodo;
	}

	/**
	 * Remove todos os caracteres acentuados de uma string.
	 * 
	 * @param string
	 *            A string que terá acentos removidos.
	 * @return Uma {@link String} contendo o texto sem caracteres acentuados.
	 */
	public static final String getStringSemAcentos(String string) {
		String msg = "";

		if (StringUtils.isNotBlank(string)) {
			String marcada = Normalizer.normalize(string, Normalizer.Form.NFD);
			msg = marcada.replaceAll(EXPRESSAO_REGULAR_CARACTERES_ACENTUADOS,
					"").toUpperCase();
		}

		return StringUtils.trimToEmpty(msg);
	}

	/**
	 * Remove todos os caracteres acentuados de uma string.
	 * 
	 * @param string
	 *            A string que terá acentos removidos.
	 * @return Uma {@link String} contendo o texto sem caracteres acentuados.
	 */
	public static final String getStringLimpaUppercase(String string) {
		String msg = getStringSemAcentos(string);

		if (StringUtils.isNotBlank(string)) {
			msg = msg.replaceAll("/^[a-zA-Z0-9]$/", "").toUpperCase();
		}

		return StringUtils.trimToEmpty(msg);
	}

}
