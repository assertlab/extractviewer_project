/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Criterio;

/**
 * Representa a definição da camada de acesso à dados da entidade {@link Criterio}.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 14:41:26
 */
public interface ICriterioDAO extends IDao<Criterio>{

	/**
	 * Recupera um critério pela sua descrição.
	 * 
	 * @param descricao A descrição do critério.
	 * @return Uma instância de {@link Criterio} ou <code>null</code> caso não seja encontrado.
	 */
	Criterio buscarCriterio(String descricao);
	
}
