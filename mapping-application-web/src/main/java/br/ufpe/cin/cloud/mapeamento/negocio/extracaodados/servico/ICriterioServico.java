/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import java.util.List;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.CriterioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Criterio;

/**
 * Representa a definição da camada de negócios da entidade {@link Criterio}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:24:22
 */
public interface ICriterioServico extends IServico<Criterio, CriterioBean> {

	/**
	 * Lista os critérios ordenados pela descrição.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico#listar()
	 */
	List<CriterioBean> listar();

	/**
	 * Realiza a busca de um determinado critério de acordo com sua descrição.
	 * 
	 * @param descricao
	 *            A instância que contém o filtro do campo descrição.
	 * @return Uma instância de {@link CriterioBean} contendo a instância
	 *         encontrada ou <code>null</code> caso não seja possível encontrar.
	 */
	CriterioBean buscarCriterio(String descricao);
}
