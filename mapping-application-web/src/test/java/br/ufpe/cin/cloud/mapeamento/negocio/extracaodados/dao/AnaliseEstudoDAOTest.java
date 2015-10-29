/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpe.cin.cloud.mapeamento.base.AbstractDAOTestHelper;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.FiltroPropriedade;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.OrdenacaoPropriedade;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.TipoFiltroEnum;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.AnaliseEstudo;

/**
 * Representa a implementação de testes unitários da classe
 * {@link AnaliseEstudoDAO}.
 * 
 * @author helaine.lins
 * @created 03/09/2014 - 18:44:31
 */
public class AnaliseEstudoDAOTest extends AbstractDAOTestHelper {

	/**
	 * Representa a instância da camada de acesso à dados que está sendo
	 * testada.
	 */
	@Autowired
	private IAnaliseEstudoDAO analiseEstudoDAO;

	/**
	 * Realiza os testes da consulta de critérios aplicados.
	 */
	@Test
	public void testeColetarCriteriosAplicados() {

		try {
			this.analiseEstudoDAO.coletarCriteriosAplicados(2, 1l);
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeColetarCriteriosAplicados",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeColetarCriteriosAplicados.");
		}

	}

	/**
	 * Realiza os testes da consulta de listar com filtro e ordenação.
	 */
	@Test
	public void testeListar() {
		try {
			Long idAnalise = 2l;

			List<FiltroPropriedade> filtros = new ArrayList<FiltroPropriedade>();
			filtros.add(new FiltroPropriedade("a.estudo.titulo",
					"[Copyright notice]", String.class, TipoFiltroEnum.LIKE));

			List<OrdenacaoPropriedade> ordenacoes = new ArrayList<OrdenacaoPropriedade>();
			ordenacoes.add(new OrdenacaoPropriedade("a.id", true));

			int inicio = 0;
			int qtd = 10;

			List<AnaliseEstudo> resultado = this.analiseEstudoDAO.listar(
					idAnalise, 1l, filtros, ordenacoes, inicio, qtd);

			assertNotNull("deveria ter encontrado resultados", resultado);
			assertEquals("deveria ter encontrado dois resultados", 2,
					resultado.size());

		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeColetarCriteriosAplicados",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeColetarCriteriosAplicados.");
		}
	}

}
