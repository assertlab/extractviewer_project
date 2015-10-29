/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.base.application;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.web.base.util.MapeamentoWebUtil;

/**
 * Representa o mecanismo de captura e tratamento de exceções da aplicação.
 * 
 * @author Cleber Alberto
 * @created 21/04/2014 - 21:10:06
 */
public class MapeamentoExceptionHandler extends ExceptionHandlerWrapper {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static final Log LOG = LogFactory
			.getLog(MapeamentoExceptionHandler.class);

	private static final String MSG_ERRO_LOG = "OCORREU UM ERRO DURANTE O PROCESSAMENTO DA REQUISICAO.";

	// atributos utilizados para marcar o tratamento de exceções no request
	private static final String ATTRIBUTE_ERROR_EXCEPTION = "javax.servlet.error.exception";
	private static final String ATTRIBUTE_ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
	private static final String ATTRIBUTE_ERROR_MESSAGE = "javax.servlet.error.message";
	private static final String ATTRIBUTE_ERROR_REQUEST_URI = "javax.servlet.error.request_uri";
	private static final String ATTRIBUTE_ERROR_STATUS_CODE = "javax.servlet.error.status_code";

	/**
	 * Representa o container do exception handler.
	 */
	private ExceptionHandler container;

	/**
	 * Representa a instância do serviço de registro de erros.
	 */
	// @ManagedProperty(value = "#{erroSistemaServico}")
	// private IErroSistemaServico servico;

	/**
	 * Cria uma nova instância da classe inicializando o tratador de exceções.
	 * 
	 * @param containerException
	 *            A instância do tratador de exceções.
	 */
	public MapeamentoExceptionHandler(ExceptionHandler containerException) {
		this.container = containerException;
//		ApplicationContext ctx = ContextLoader
//				.getCurrentWebApplicationContext();
		// TODO: hsl - Acrescentar a implementação da classe.
		// servico = (IErroSistemaServico) ctx.getBean("erroSistemaServico");
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see javax.faces.context.ExceptionHandlerWrapper#getWrapped()
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return this.container;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see javax.faces.context.ExceptionHandlerWrapper#handle()
	 */
	@Override
	public void handle() throws FacesException {

		// 0. Verifica se houveram exceções.
		if (this.isBlocoExcecao()) {

			// 1. primeiro identificar se a exceção foi tratada ou não;
			MapeamentoException excecaoTratada = this.isExcecaoTratada();

			// 2. caso a exceção seja tratada deve se manter na mesma página em
			// que o erro gerado
			if (excecaoTratada != null) {
				this.tratarSiVestException(excecaoTratada);
			} else {
				// 3. caso a exceção não seja tratada deve redirecionar o
				// usuário para a tela de erro 500;
				this.tratarErroNaoEsperado();
			}

		} else {
			this.container.handle();
		}
	}

	/**
	 * Identifica se o handler contém exceções a serem tratadas.
	 * 
	 * @return Retorna <code>true</code> caso existam exceções a serem tratadas.
	 */
	private boolean isBlocoExcecao() {
		return this.container != null
				&& this.container.getUnhandledExceptionQueuedEvents() != null
				&& this.container.getUnhandledExceptionQueuedEvents()
						.iterator() != null
				&& this.container.getUnhandledExceptionQueuedEvents()
						.iterator().hasNext();
	}

	/**
	 * Identifica na pilha de erros se existe alguma exceção tratada pela
	 * aplicação. Caso exista a mesma será retornada.
	 * 
	 * @return Uma instância de {@link MapeamentoException} caso a exceção tenha
	 *         sido tratada em negócio, caso contrário retornará
	 *         <code>null</code>.
	 */
	private MapeamentoException isExcecaoTratada() {
		MapeamentoException excecaoTratada = null;

		if (this.container != null
				&& this.container.getUnhandledExceptionQueuedEvents() != null) {

			Iterable<ExceptionQueuedEvent> events = this.container
					.getUnhandledExceptionQueuedEvents();
			for (Iterator<ExceptionQueuedEvent> it = events.iterator(); it
					.hasNext();) {

				ExceptionQueuedEvent event = it.next();
				ExceptionQueuedEventContext eqec = event.getContext();
				Throwable causa = eqec.getException();

				while (causa != null) {

					if (causa instanceof MapeamentoException) {
						excecaoTratada = (MapeamentoException) causa;
						break;
					}
					causa = causa.getCause();
				}
			}
		}

		return excecaoTratada;
	}

	/**
	 * Trata a exceção {@link MapeamentoException} levantada durante o processamento
	 * da requisição. Traduz a mensagem de erro, loga e apresenta a mesma no
	 * console.
	 * 
	 * @param falha
	 *            A falha levantada durante o processamento da requisição.
	 */
	private void tratarSiVestException(MapeamentoException falha) {

		// todas as exeções tratadas possuem a lista de erros incializada mesmo
		// que somente por um elemento.
		if (falha != null && falha.getErros() != null
				&& !falha.getErros().isEmpty()) {
			FacesMessage message = null;

			for (Erro erro : falha.getErros()) {
				if (erro != null) {

					String mensagem = "";

					if (erro.getTipoErro().equals(TipoErro.HIBERNATE_VALIDATOR)) {

						mensagem = MapeamentoWebUtil.getStringBundle(
								erro.getCodigo(), erro.getParametros())
								+ erro.getErro();

					} else if (StringUtils.isNotBlank(erro.getCodigo())) {
						mensagem = MapeamentoWebUtil.getStringBundle(
								erro.getCodigo(), erro.getParametros());
					} else {
						mensagem = MapeamentoWebUtil.getStringBundle(
								erro.getErro(), erro.getParametros());
					}

					if (StringUtils.isBlank(mensagem)) {
						mensagem = MapeamentoWebUtil
								.getStringBundle("mapping.erro.geralNaoLocalizado");
					}

					message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							mensagem, mensagem);
					FacesContext.getCurrentInstance().addMessage(null, message);
				}

			}
		} else {
			// indica que houve algum erro de programação na montagem do erro.

			LOG.fatal(
					"NAO FOI POSSIVEL TRATAR EXCECAO NO EXCEPTION HANDLER POIS A MESMA NAO FOI MONTADA CORRETAMENTE",
					falha);
			String mensagem = MapeamentoWebUtil
					.getStringBundle("mapping.erro.geralNaoLocalizado");

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		this.limparPilhaErros();
		this.container.handle();
	}

	/**
	 * Realiza o tratamento de erros não esperados na aplicação.
	 */
	private void tratarErroNaoEsperado() {
		if (getUnhandledExceptionQueuedEvents() != null
				&& getUnhandledExceptionQueuedEvents().iterator() != null) {
			Iterator<ExceptionQueuedEvent> pilhaExcecoesEvents = getUnhandledExceptionQueuedEvents()
					.iterator();

			if (pilhaExcecoesEvents.hasNext()) {

				// tratamento diferenciado para as requisições ajax
				FacesContext contexto = FacesContext.getCurrentInstance();
				String paginaErro = null;

				Throwable exception = pilhaExcecoesEvents.next().getContext()
						.getException();

				if (exception instanceof ViewExpiredException) {
					LOG.error(
							"O USUARIO TENTOU RESTAURAR UMA PAGINA APOS A SESSAO TER EXPIRADO",
							exception);

					paginaErro = "/paginas/erros/sessao_expirada.xhtml";

				} else {
					LOG.error(
							"OCORREU UMA FALHA NAO TRATADA NA CAMADA DE NEGOCIOS",
							exception);
					Long idErro = null;
					try {
						// idErro =
						// this.servico.registrarErroSistema(MapeamentoWebUtil.getAnoBase(),
						// MapeamentoWebUtil.obterUsuarioLogado(),
						// TipoErroSistemaEnum.ERRO_INESPERADO, exception);
					} catch (Exception e) {
						LOG.error(
								"OCORREU PROBLEMA AO REGISTRAR A FALHA NAO TRATADA NA BASE DE DADOS",
								exception);
					}

					final HttpServletRequest request = (HttpServletRequest) contexto
							.getExternalContext().getRequest();

					request.setAttribute(ATTRIBUTE_ERROR_EXCEPTION, exception);
					request.setAttribute(ATTRIBUTE_ERROR_EXCEPTION_TYPE,
							exception.getClass());
					request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,
							exception.getMessage());
					request.setAttribute(ATTRIBUTE_ERROR_REQUEST_URI,
							request.getRequestURI());
					request.setAttribute(ATTRIBUTE_ERROR_STATUS_CODE,
							HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					request.setAttribute("chaveErro500", "EID " + idErro);
					contexto.getExternalContext().log(MSG_ERRO_LOG, exception);

					paginaErro = "/paginas/erros/500.xhtml";
				}

				// Força o JSF a renderizar a página de erro em sua totalidade
				// no response da requisição ajax.
				String viewId = this.normalizarViewId(paginaErro);
				contexto.setViewRoot(contexto.getApplication().getViewHandler()
						.createView(contexto, viewId));
				if (contexto.getPartialViewContext() != null
						&& contexto.getPartialViewContext().isAjaxRequest()) {
					contexto.getPartialViewContext().setRenderAll(true);
				}
				contexto.renderResponse();

				// Tratamento para que não corre exceções IllegalStateException
				// "response alread commited"
				contexto.getViewRoot().addPhaseListener(
						new TratamentoErroAjaxPhaseListener());

			}

		}

		this.limparPilhaErros();
		this.container.handle();
	}

	/**
	 * Limpa a pilha de erros que foi tratada para que a requisição possa ser
	 * finalizada e a página renderizada para o usuário.
	 */
	private void limparPilhaErros() {
		Iterator<ExceptionQueuedEvent> pilhaExcecoesEvents = getUnhandledExceptionQueuedEvents()
				.iterator();
		if (pilhaExcecoesEvents != null) {
			while (pilhaExcecoesEvents.hasNext()) {
				pilhaExcecoesEvents.next();
				pilhaExcecoesEvents.remove();
			}
		}
	}

	/**
	 * @param path
	 * @return
	 */
	private String normalizarViewId(String path) {
		String mapping = getMapping();

		if ((mapping.charAt(0) == '/')) {
			if (path.startsWith(mapping)) {
				return path.substring(mapping.length());
			}
		} else if (!path.endsWith(mapping)) {
			return path.substring(0, path.lastIndexOf('.')) + mapping;
		}

		return path;
	}

	private String getMapping() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();

		if (externalContext.getRequestPathInfo() == null) {
			String path = externalContext.getRequestServletPath();
			return path.substring(path.lastIndexOf('.'));
		} else {
			return externalContext.getRequestServletPath();
		}
	}

	// /**
	// * Obtém o valor do atributo servico.
	// *
	// * @return Uma instância de {@link IErroSistemaServico} contendo o valor
	// do
	// * atributo servico.
	// */
	// public IErroSistemaServico getServico() {
	// return this.servico;
	// }
	//
	// /**
	// * Atualiza a instância de servico com o valor de servico.
	// *
	// * @param servico
	// * Uma instância de IErroSistemaServico contendo o valor a ser
	// * atualizado.
	// */
	// public void setServico(IErroSistemaServico servico) {
	// this.servico = servico;
	// }

}
