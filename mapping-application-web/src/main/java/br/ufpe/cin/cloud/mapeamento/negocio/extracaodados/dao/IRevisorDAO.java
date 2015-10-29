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
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;

/**
 * Representa a definição da camada de acesso à dados da entidade {@link Revisor}.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 14:41:26
 */
public interface IRevisorDAO extends IDao<Revisor>{

	/**
	 * Busca um revisor pelo seu login no sistema.
	 * 
	 * @param login O login de usuário do revisor.
	 * @return A instância de {@link Revisor} correspondente ou <code>null</code> caso não encontre.
	 */
	Revisor buscarRevisor(String login);
	
	boolean existeRevisor(Long idRevisor);
	
}
