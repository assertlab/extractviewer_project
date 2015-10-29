/**
 * SiVest - Sistema de Inscrição do Vestibular da UPE
 * 
 * Copyright (c) Universidade Federal de Pernambuco.
 * 
 * Este software é confidencial e propriedade da UPE. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do UPE. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.IEnumErro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;

/**
 * Representa a implementação genérica para criação de testes unitários da
 * camada de negócios do sistema.
 * 
 * @author helaine.lins
 * @created 08/05/2014 - 20:13:47
 */
public class AbstractServicoTestHelper extends AbstractDAOTestHelper {

	/**
	 * Loga em detalhe as exceções ocorridas durante a execução de um teste
	 * 
	 * @param e A instância do erro a ser detalhado.
	 */
	protected void logarDetalheExcecoes(Exception e) {
		if (e instanceof MapeamentoException) {
			MapeamentoException excecao = (MapeamentoException) e;

			for (int i = 0; i < excecao.getErros().size(); i++) {
				Erro erro = excecao.getErros().get(i);
				LOG.error("[Erro " + i + "]: " + erro.getErro(), e);
			}
		}
	}

	/**
	 * Realiza a asserção de uma exceção verificando se a mesma corresponde ao
	 * tipo e mensagem informados.
	 * 
	 * @param falha
	 *            A instância a ser validada.
	 * @param tipoErro
	 *            O tipo do erro esperado.
	 * @param erro
	 *            A mensagem de erro esperada.
	 */
	protected void assertMensagemNegocio(MapeamentoException falha,
			TipoErro tipoErro, IEnumErro... erro) {
		List<Erro> erros = falha.getErros();

		assertNotNull(erros);
		assertEquals(erros.size(), erro.length);

		Erro erroObj = null;

		for (int i = 0; i < erros.size(); i++) {
			erroObj = erros.get(i);

			assertNotNull(erroObj);
			assertEquals(erroObj.getTipoErro(), tipoErro);
			assertEquals(erroObj.getCodigo(), erro[i].getChave());
		}

	}

	/**
	 * Realiza a asserção de uma exceção verificando se a mesma corresponde ao
	 * tipo e mensagem informados.
	 * 
	 * @param falha
	 *            A instância a ser validada.
	 * @param tipoErro
	 *            O tipo do erro esperado.
	 * @param chave
	 *            A chave da mensagem de erro esperada.
	 */
	protected void assertMensagemNegocio(MapeamentoException falha,
			TipoErro tipoErro, String chave) {
		List<Erro> erros = falha.getErros();

		assertNotNull(erros);
		assertEquals(erros.size(), 1);

		Erro object = erros.get(0);

		assertNotNull(object);
		assertEquals(object.getTipoErro(), tipoErro);
		assertEquals(object.getCodigo(), chave);
	}

}
