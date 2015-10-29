/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpe.cin.cloud.mapeamento.base.AbstractDAOTestHelper;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;

/**
 * Representa a implementação de testes unitários da classe {@link EstudoDAO}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 17:52:19
 */
public class EstudoDAOTest extends AbstractDAOTestHelper {

	/**
	 * Representa a instância da camada de acesso à dados que está sendo
	 * testada.
	 */
	@Autowired
	private IEstudoDAO estudoDAO;

	/**
	 * Realiza os testes de inclusão da base de dados Mista
	 */
	@Test
	// @Ignore
	public void testeIdentificarEstudosBrutosDuplicados() {

		try {
			Collection<Estudo> duplicados = this.estudoDAO
					.listarEstudosAgrupadosPorTitulo();
			assertNotNull("Deveria ter encontrado estudos duplicados.",
					duplicados);

		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeIdentificarEstudosBrutosDuplicados",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeIdentificarEstudosBrutosDuplicados.");
		}

	}

}
