/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpe.cin.cloud.mapeamento.base.AbstractDAOTestHelper;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Criterio;

/**
 * Representa a implementação de testes unitários da classe {@link CriterioDAO}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 16:20:27
 */
public class CriterioDAOTest extends AbstractDAOTestHelper {

	/**
	 * Representa a instância da camada de acesso à dados que está sendo
	 * testada.
	 */
	@Autowired
	private ICriterioDAO criterioDAO;

	/**
	 * Realiza os testes de inclusão da base de dados Mista
	 */
	@Test
	@Ignore
	public void testeIncluirCriteriosInclusaoExclusao() {

		try {
			List<Criterio> criterios = new ArrayList<Criterio>();
			
			criterios.add(new Criterio("O arquivo é um Slideshow ou Resumo Expandido.", false));
			criterios.add(new Criterio("O Estudo não está disponível.", false));
			criterios.add(new Criterio("O arquivo não corresponde ao Estudo. (ex: índice)", false));
			criterios.add(new Criterio("O arquivo não é um Estudo. (ex: proceedings)", false));
			criterios.add(new Criterio("O Estudo duplicado.", false));
			criterios.add(new Criterio("O Estudo não está relacionado à Computação em Nuvem.", false));
			criterios.add(new Criterio("O Estudo não reporta um Experimento.", false));
			criterios.add(new Criterio("O Estudo não reporta um Experimento Technology Based.", false));
			criterios.add(new Criterio("O Estudo reporta um Experimento Technology Based.", true));
			
			this.criterioDAO.incluir(criterios);
			
		} catch (Exception e) {
			LOG.error(
					"falhou ao executar realizar o teste testeIncluirCriteriosExclusao",
					e);
			fail("Nao deveria ter levantado excecao ao executar testeIncluirCriteriosExclusao.");
		}

	}
	
}
