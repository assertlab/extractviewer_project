/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao;

import java.util.List;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;

/**
 * Representa a definição da camada de acesso a dados da entidade {@link Estudo}
 * .
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 18:28:46
 */
public interface IEstudoDAO extends IDao<Estudo> {

	/**
	 * Identifica a quantidade de estudos cadastrados na base.
	 * 
	 * @return Um {@link Integer} contendo a quantidade de estudos totais na
	 *         base de dados.
	 */
	Integer identificarQuantidadeEstudos();

	/**
	 * Identifica na base de dados inicial de estudos cadastrados agrupados peo
	 * título.
	 * 
	 * @return Um {@link List} de {@link Estudo} contendo as instâncias
	 *         potencialmente duplicadas.
	 */
	List<Estudo> listarEstudosAgrupadosPorTitulo();
	
	/**
	 * Identifica a quantidade de estudos em um determinado ano.
	 * 
	 * @return Um {@link List} de {@link EstatisticaEstudoBean} contendo a quantidade de estudos identificados por ano.
	 */
	List<EstatisticasEstudoBean> estatisticaQuantidadeEstudosPorAno();

	/**
	 * Recupera os estudos que foram incluídos em uma determinada etapa de análise.
	 * 
	 * @param idEtapaAnalise O identificador da etapa de análise.
	 * @return Um {@link List} de {@link Estudo} encontrados.
	 */
	List<Estudo> recuperarEstudosIncluidosSelecao(Long idEtapaAnalise);
}
