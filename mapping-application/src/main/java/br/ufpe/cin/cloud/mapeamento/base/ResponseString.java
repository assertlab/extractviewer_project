/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.base;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * Representa uma implementação que captura e loga o resultado de uma requisição
 * web e a retorna como {@link String}.
 * 
 * @author helaine.lins
 * @created 01/08/2014 - 10:18:11
 */
public class ResponseString implements ResponseHandler<String> {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger
			.getLogger(ResponseString.class.getName());

	public String handleResponse(final HttpResponse response)
			throws ClientProtocolException, IOException {

		String resultado = null;

		int status = response.getStatusLine().getStatusCode();

		if (status >= 200 && status < 300) {
			LOG.info("Status de Response: " + status);

			HttpEntity entity = response.getEntity();
			resultado = EntityUtils.toString(entity);

			// ControladorIEEEXplorer.LOG
			// .debug("*******************************************************************************************************************************");

			// ControladorIEEEXplorer.LOG.debug(resultado);

			// ControladorIEEEXplorer.LOG
			// .debug("*******************************************************************************************************************************");
		} else {
			HttpEntity entity = response.getEntity();
			resultado = EntityUtils.toString(entity);
			LOG.error("Status de Response Inesperado: " + status);
		}

		return resultado;
	}

}
