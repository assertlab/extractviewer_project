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

import org.joda.time.LocalDateTime;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.DatasExtremasAnalisesBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaBuscaEstudosPorConferencia;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EtapaAnalise;

/**
 * Representa a definição da camada de acesso à dados da entidade {@link EtapaAnalise}.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 14:41:26
 */
public interface IEtapaAnaliseDAO extends IDao<EtapaAnalise>{

	boolean existeEtapaRevisaoInicial();

	/**
	 * Lista toda as revisões alocadas para o revisor de login informado.
	 * 
	 * @param loginRevisor O login do revisor.
	 * @return Um {@link List} contendo as revisões alocadas para o revisor ou <code>null</code> caso não exista.
	 */
	List<EtapaAnalise> listarEtapasAnalise(String loginRevisor);
	
	/**
	 * Lista o quantitativo de estudos por conferência e categoria.
	 * 
	 * @param idEtapa O identificador da etapa de revisão.
	 * @return Um {@link List} contendo as estatísticas.
	 */
	List<EstatisticaBuscaEstudosPorConferencia> listarBuscasPorOrigemECategoria(Long idEtapa);
	
	/**
	 * Lista o quantitativo de estudos por categoria.
	 * 
	 * @param idEtapa O identificador da etapa de revisão.
	 * @return Um {@link List} contendo as estatísticas.
	 */
	List<EstatisticaBuscaEstudosPorConferencia> listarBuscasPorCategoria(Long idEtapa);
	
	/**
	 * Lista o quantitativo de estudos por engenho de busca.
	 * 
	 * @param idEtapa O identificador da etapa de revisão.
	 * @return Um {@link List} contendo as estatísticas.
	 */
	List<EstatisticaBuscaEstudosPorConferencia> listarBuscasPorEngenho(Long idEtapa);
	
	/**
	 * Preenche as datas extremas do dia de uma determinada etapa de revisão
	 * 
	 * @param idEtapa O identificador da etapa de revisão.
	 */
	void recuperarDatasExtremas(DatasExtremasAnalisesBean datas);
	
	/**
	 * Recupera a quantidade de estudos concluídos durante o dia.
	 * 
	 * @param data A data para qual o valor será coletado.
	 * @param idEtapa O identificador da etapa.
	 * @return Um {@link Long} contendo a quantidade de estudos concluídos no dia.
	 */
	Long recuperarConcluidosPorDia(LocalDateTime data, Long idEtapa, Long idRevisor);
}
