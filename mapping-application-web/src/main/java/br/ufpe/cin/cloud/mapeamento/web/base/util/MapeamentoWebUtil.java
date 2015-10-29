/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.base.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.IEnumeracaoPersistente;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.web.base.camadas.IEnumBean;

/**
 * Representa a implementação padrão para métodos utilitários da camada web da
 * aplicação.
 * 
 * @author helaine.lins
 * @created 09/04/2014 - 00:13:53
 */
public class MapeamentoWebUtil {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static final Log LOG = LogFactory.getLog(MapeamentoWebUtil.class);

	/**
	 * Recupera um parâmetro do request.
	 * 
	 * @param nome
	 *            O nome do parâmetro que encontra-se no request.
	 * @return Uma {@link String} contendo o valor do atributo no request.
	 */
	public static String getRequestParameter(String nome) {
		String parametro = null;

		ExternalContext contextoExterno = getExternalContext();

		if (contextoExterno != null
				&& contextoExterno.getRequestParameterMap() != null) {
			parametro = contextoExterno.getRequestParameterMap().get(nome);
		}

		return parametro;
	}

	/**
	 * Recupera um parâmetro do request.
	 * 
	 * @param nome
	 *            O nome do parâmetro que encontra-se no request.
	 * @return Uma {@link String} contendo o valor do atributo no request.
	 */
	public static <E> E getRequestParameter(String nome, Class<E> tipo) {
		E parametro = null;

		ExternalContext contextoExterno = getExternalContext();
		if (contextoExterno != null
				&& contextoExterno.getRequestParameterMap() != null) {
			parametro = tipo.cast(contextoExterno.getRequestParameterMap().get(
					nome));
		}

		return parametro;
	}

	/**
	 * Coloca um parâmetro no request.
	 * 
	 * @param nome
	 *            O nome do parâmetro que encontra-se no request.
	 */
	public static void setRequestParameter(String nome, Object valor) {

		ExternalContext contextoExterno = getExternalContext();
		if (contextoExterno != null
				&& contextoExterno.getRequestParameterMap() != null) {
			contextoExterno.getRequestMap().put(nome, valor);
		}
	}

	/**
	 * Exibe uma mensagem de sucesso na aplicação.
	 * 
	 * @param mensagem
	 *            A mensagem a ser exibida.
	 * @param parametros
	 *            Os parâmetros a serem substituidos na mensagem.
	 */
	public static void exibirMensagemSucesso(String mensagem,
			Object... parametros) {
		exibirMensagem(FacesMessage.SEVERITY_INFO, mensagem, parametros);
	}
	
	/**
	 * Exibe uma mensagem de sucesso na aplicação.
	 * 
	 * @param mensagem
	 *            A mensagem a ser exibida.
	 * @param parametros
	 *            Os parâmetros a serem substituidos na mensagem.
	 */
	public static void exibirMensagemSucesso(String mensagem,String objeto,
	        Object... parametros) {
	    exibirMensagem(FacesMessage.SEVERITY_INFO, mensagem,objeto, parametros);
	}

	/**
	 * Exibe uma mensagem de alerta na aplicação.
	 * 
	 * @param mensagem
	 *            A mensagem a ser exibida.
	 * @param parametros
	 *            Os parâmetros a serem substituidos na mensagem.
	 */
	public static void exibirMensagemAlerta(String mensagem,
			Object... parametros) {
		exibirMensagem(FacesMessage.SEVERITY_WARN, mensagem, parametros);
	}
	
	/**
	 * Exibe uma mensagem de alerta na aplicação.
	 * 
	 * @param mensagem
	 *            A mensagem a ser exibida.
	 * @param parametros
	 *            Os parâmetros a serem substituidos na mensagem.
	 */
	public static void exibirMensagemAlerta(String mensagem, String objeto,
	        Object... parametros) {
	    exibirMensagem(FacesMessage.SEVERITY_WARN, mensagem, objeto, parametros);
	}

	/**
	 * Exibe uma mensagem de erro na aplicação.
	 * 
	 * @param mensagem
	 *            A mensagem a ser exibida.
	 * @param parametros
	 *            Os parâmetros a serem substituidos na mensagem.
	 */
	public static void exibirMensagemErro(String mensagem, Object... parametros) {
		exibirMensagem(FacesMessage.SEVERITY_ERROR, mensagem, parametros);
	}
	
	/**
	 * Exibe uma mensagem de erro na aplicação.
	 * 
	 * @param mensagem
	 *            A mensagem a ser exibida.
	 * @param parametros
	 *            Os parâmetros a serem substituidos na mensagem.
	 */
	public static void exibirMensagemErro(String mensagem, String objeto, Object... parametros) {
	    exibirMensagem(FacesMessage.SEVERITY_ERROR, mensagem, objeto, parametros);
	}

	/**
	 * Recupera o contexto externo da aplicação.
	 * 
	 * @return Uma instância de {@link ExternalContext} ou null caso o
	 *         {@link FacesContext} retorne uma instância nula.
	 */
	public static ExternalContext getExternalContext() {
		ExternalContext contextoExterno = null;
		FacesContext contexto = FacesContext.getCurrentInstance();

		if (contexto != null) {
			contextoExterno = contexto.getExternalContext();
		}

		return contextoExterno;
	}

	/**
	 * Recupera o mapa de sessão da aplicação.
	 * 
	 * @return Uma instância de {@link Map} contendo o mapa da aplicação ou
	 *         <code>null</code> caso o mesmo não exista.
	 */
	public static Map<String, Object> getSessionMap() {
		Map<String, Object> mapa = null;

		if (getExternalContext() != null) {
			mapa = getExternalContext().getSessionMap();
		}

		return mapa;
	}

	/**
	 * Recupera o contexto de servlets da aplicação.
	 * 
	 * @return Uma instância de {@link ServletContext} contendo o contexto da
	 *         aplicação ou <code>null</code> caso o contexto externo esteja
	 *         nulo.
	 */
	public static ServletContext getServletContext() {
		ServletContext contexto = null;

		if (getExternalContext() != null
				&& getExternalContext().getContext() != null) {
			contexto = (ServletContext) getExternalContext().getContext();
		}

		return (ServletContext) contexto;
	}

	/**
	 * Recupera o request da requisição.
	 * 
	 * @return Uma instância de {@link HttpServletRequest} contendo o request da
	 *         requisição ou <code>null</code> caso o contexto externo esteja
	 *         nulo.
	 */
	public static HttpServletRequest getServletRequest() {
		HttpServletRequest contexto = null;

		if (getExternalContext() != null
				&& getExternalContext().getRequest() != null) {
			contexto = (HttpServletRequest) getExternalContext().getRequest();
		}

		return contexto;
	}

	/**
	 * Recupera o response da requisição.
	 * 
	 * @return Uma instância de {@link HttpServletResponse} contendo o response
	 *         da requisição ou <code>null</code> caso o contexto externo esteja
	 *         nulo.
	 */
	public static HttpServletResponse getServletResponse() {
		HttpServletResponse contexto = null;

		if (getExternalContext() != null
				&& getExternalContext().getResponse() != null) {
			contexto = (HttpServletResponse) getExternalContext().getResponse();
		}

		return contexto;
	}

	/**
	 * Recupera uma determinada {@link String} que esteja inserida no
	 * {@link ResourceBundle} correspondente ao {@link Locale} atual da
	 * aplicação.
	 * 
	 * @param chave
	 *            {@link String} correspondente à chave do valor pesquisado.
	 * @param argumentos
	 *            A lista de argumentos a serem substituidos na mensagem
	 *            recuperada.
	 * @return {@link String} correspondente à chave passada para o locale
	 *         atual, do contrário <code>null</code>.
	 */
	public static String getStringBundle(String chave, Object... argumentos) {

		String encontrada = null;

		ResourceBundle bundle = getMessageBundle();

		if (bundle != null) {
			encontrada = bundle.getString(chave);

			if (argumentos != null) {
				encontrada = MessageFormat.format(encontrada, argumentos);
			}

		}

		return encontrada;
	}

	/**
	 * Recupera um objeto {@link ResourceBundle}, que é o arquivo de
	 * internacionalização correspondente ao {@link Locale} atual da aplicação,
	 * configurado no <code>faces-config.xml</code>
	 * 
	 * @return {@link ResourceBundle} correspondente ao arquivo de
	 *         internacionalização atual;
	 */
	public static ResourceBundle getMessageBundle() {

		ResourceBundle bundle = null;

		FacesContext contexto = FacesContext.getCurrentInstance();

		if (contexto != null) {

			String messageBundleName = contexto.getApplication()
					.getMessageBundle();

			Locale locale = contexto.getViewRoot().getLocale();

			bundle = ResourceBundle.getBundle(messageBundleName, locale);
		}

		return bundle;
	}

	/**
	 * Monta uma coleção de {@link SelectItem} a partir de um qualquer
	 * enumeração que implemente a interface {@link IEnumBean}.
	 * 
	 * @param objetoEnum
	 *            {@link IEnumBean} representando a instância da enumeração que
	 *            será convertiva numa lista de {@link SelectItem}.
	 * @return {@link List} de {@link SelectItem} contendo os valore da
	 *         enumeração convertido.
	 */
	public static List<SelectItem> montarSelectItems(IEnumBean objetoEnum) {

		List<SelectItem> itensIdades = new ArrayList<SelectItem>();

		ResourceBundle bundle = MapeamentoWebUtil.getMessageBundle();

		if (bundle != null) {

			for (IEnumBean itemEnum : objetoEnum.getValores()) {

				SelectItem item = new SelectItem();
				item.setValue(itemEnum);
				item.setLabel(bundle.getString(itemEnum.getDescricao()));

				itensIdades.add(item);
			}
		}

		return itensIdades;
	}

	/**
	 * Retorna um booleano informando se a requisição atual é um postback
	 * 
	 * @return <code>true</code> caso seja um postback, do contrário
	 *         <code>false</code>.
	 */
	public static boolean isPostBack() {

		boolean isPostBack = false;

		FacesContext contexto = FacesContext.getCurrentInstance();

		if (contexto != null) {
			isPostBack = contexto.isPostback();
		}

		return isPostBack;
	}

	/**
	 * Retorna um determinado valor que pode está contido no escopo flash da
	 * aplicação. Caso o valor seja encontrado, o mesmo é removido do escopo
	 * flash.
	 * 
	 * @param <E>
	 * @param chave
	 *            Chave do valor no mapa do escopo {@link Flash}
	 * @param tipo
	 *            Tipo parametrizado do valor.
	 * @return Valor encontrado de acordo com o tipo parametrizado passado.
	 */
	public static <E> E getAtributoFlash(String chave, Class<E> tipo) {

		E encontrado = null;

		ExternalContext contextoExterno = getExternalContext();

		if (contextoExterno != null && contextoExterno.getFlash() != null) {

			Flash flash = contextoExterno.getFlash();

			Object valor = flash.get(chave);

			if (valor != null && tipo.isAssignableFrom(valor.getClass())) {
				encontrado = tipo.cast(valor);
			}

			flash.remove(chave);
		}

		return encontrado;
	}

	/**
	 * Adiciona um valor no escopo flash da aplicação.
	 * 
	 * @param chave
	 *            Chave do valor que serpa inserido.
	 * @param valor
	 *            Valor que será inserido.
	 */
	public static void addAtributoFlash(String chave, Object valor) {

		ExternalContext contextoExterno = getExternalContext();

		if (contextoExterno != null && contextoExterno.getFlash() != null) {

			Flash flash = contextoExterno.getFlash();

			flash.put(chave, valor);
			flash.setKeepMessages(true);
		}
	}

	/**
	 * Adiciona um valor no escopo da sessão.
	 * 
	 * @param chave
	 *            a chave do valor adicionado
	 * @param valor
	 *            o valor a ser adicionado
	 */
	public static void adicionarAtributoSessao(String chave, Object valor) {
		getSessionMap().put(chave, valor);
	}

	/**
	 * Adiciona um valor no escopo da sessão.
	 * 
	 * @param chave
	 *            a chave do valor adicionado
	 * @param valor
	 *            o valor a ser adicionado
	 */
	public static void removerAtributoSessao(String chave) {
		HttpSession session = (HttpSession) getExternalContext().getSession(
				false);
		session.removeAttribute(chave);
	}

	/**
	 * Obtém um valor previamente adicionado à sessão.
	 * 
	 * @param chave
	 *            a chave associada ao valor a ser recuperado.
	 * @param tipo
	 *            o tipo do valor a ser recuperado.
	 * @return a instância ou null caso não existe valor associado à chave
	 *         informada.
	 */
	@SuppressWarnings("unchecked")
	public static <E> E obterAtributoSessao(String chave, Class<E> tipo) {
		return (E) getSessionMap().get(chave);
	}

	/**
	 * Recupera os dados do usuário logado da sessão.
	 * 
	 * @return A instância do {@link UsuarioBean} logado no sistema.
	 */
	public static UsuarioBean obterUsuarioLogado() {
		UsuarioBean usuario = null;

		if (SecurityContextHolder.getContext() != null
				&& SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal() != null) {

			Object principal = SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();

			if (principal instanceof UsuarioBean) {
				usuario = (UsuarioBean) principal;
			}
		}

		return usuario;
	}

	/**
	 * Recupera os dados do usuário logado.
	 * 
	 * @return Uma instância de {@link UsuarioTO} caso exista, do contrário
	 *         retorna <code>null</code>.
	 */
	public static void obterUsuarioLogadoTO() {
//		UsuarioTO usuario = null;
//
//		UsuarioBean usuarioLogado = obterUsuarioLogado();
//
//		if (usuarioLogado != null) {
//			usuario = new UsuarioTO();
//
//			usuario.setCpf(usuarioLogado.getLogin());
//			usuario.setEmail(usuarioLogado.getEmail());
//			usuario.setId(usuarioLogado.getId());
//			usuario.setNomeCompleto(usuarioLogado.getNome());
//		}
//
//		return usuario;
		
	}

	/**
	 * Exibe uma mensagem no sistema.
	 * 
	 * @param severity
	 *            A severidade da mensagem a ser exibida.
	 * @param mensagem
	 *            A mensagem a ser exibida.
	 */
	private static void exibirMensagem(FacesMessage.Severity severity,
			String mensagem, Object... parametros) {
		FacesMessage facesMessage = new FacesMessage(severity, "",
				getStringBundle(mensagem, parametros));
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	/**
	 * Exibe uma mensagem no sistema.
	 * 
	 * @param severity
	 *            A severidade da mensagem a ser exibida.
	 * @param mensagem
	 *            A mensagem a ser exibida.
	 */
	private static void exibirMensagem(FacesMessage.Severity severity,
	        String mensagem, String objeto, Object... parametros) {
	    
	    FacesMessage facesMessage = new FacesMessage(severity, "",
	            getStringBundle(mensagem, parametros));
	    
	    final UIComponent componente = encontrarComponente(FacesContext.getCurrentInstance().getViewRoot(), objeto);
	    FacesContext.getCurrentInstance().addMessage(componente.getClientId(), facesMessage);
	}

	/**
	 * Realiza a conversão de um valor de uma enumeração do tipo to para uma
	 * enumeração persistente.
	 * 
	 * @param enumTO
	 *            A instância que contém o valor a ser convertido.
	 * @param enumEntity
	 *            A classe da instância para qual a enumeração será convertida.
	 * @return Uma instância de <E> representando o valor correpondente.
	 */
	@SuppressWarnings("unchecked")
	public static <E extends IEnumeracaoPersistente, H extends IEnumBean> E converterEnum(
			H enumTO, Class<E> enumEntity) {

		E retorno = null;

		if (enumTO != null) {
			Class<? extends Enum<?>> enumClass = (Class<? extends Enum<?>>) enumEntity;
			Enum<?>[] enums = enumClass.getEnumConstants();

			for (int i = 0; i < enums.length; i++) {
				if (((IEnumeracaoPersistente) enums[i]).getCodigo().equals(
						enumTO.getCodigo())) {
					LOG.debug("retornando enumeracao - nullSafeGet:"
							+ enumClass.getName() + ".");
					retorno = enumEntity.cast(enums[i]);
					break;
				}
			}

		}

		return retorno;
	}

	/**
	 * Realiza a conversão de um valor de uma enumeração do tipo persistente
	 * para o tipo TO.
	 * 
	 * @param enumPersistente
	 *            A instância que contém o valor a ser convertido.
	 * @param enumTO
	 *            A classe da instância para qual a enumeração será convertida.
	 * @return Uma instância de <E> representando o valor correpondente.
	 */
	@SuppressWarnings("unchecked")
	public static <H extends IEnumeracaoPersistente, E extends IEnumBean> E converterEnum(
			H enumPersistente, Class<E> enumTO) {

		E retorno = null;

		if (enumPersistente != null) {
			Class<? extends Enum<?>> enumClass = (Class<? extends Enum<?>>) enumTO;
			Enum<?>[] enums = enumClass.getEnumConstants();

			for (int i = 0; i < enums.length; i++) {
				if (((IEnumBean) enums[i]).getCodigo().equals(
						enumPersistente.getCodigo())) {
					LOG.debug("retornando enumeracao - nullSafeGet:"
							+ enumClass.getName() + ".");
					retorno = enumTO.cast(enums[i]);
					break;
				}
			}

		}

		return retorno;
	}

	/**
	 * Realiza o upper de um campo string.
	 * 
	 * @param valor
	 *            A string a ser convertida.
	 * @return Uma nova {@link String} contendo o valor em caixa alta.
	 */
	public static String converteMaiuscula(String valor) {
		String retorno = null;

		if (valor != null) {
			retorno = valor.toUpperCase();
		}

		return retorno;
	}

	/**
	 * Realiza o upper de um campo string.
	 * 
	 * @param valor
	 *            A string a ser convertida.
	 * @return Uma nova {@link String} contendo o valor em caixa alta.
	 */
	public static String converteMinuscula(String valor) {
		String retorno = null;

		if (valor != null) {
			retorno = valor.toLowerCase();
		}

		return retorno;
	}
	
	@SuppressWarnings("rawtypes")
    public static UIComponent encontrarComponente(UIComponent base, String id) {
	    if (id.equals(base.getId()))
	      return base;
	  
	    UIComponent kid = null;
	    UIComponent result = null;
	    Iterator kids = base.getFacetsAndChildren();
	    while (kids.hasNext() && (result == null)) {
	      kid = (UIComponent) kids.next();
	      if (id.equals(kid.getId())) {
	        result = kid;
	        break;
	      }
	      result = encontrarComponente(kid, id);
	      if (result != null) {
	        break;
	      }
	    }
	    return result;
	}

}
