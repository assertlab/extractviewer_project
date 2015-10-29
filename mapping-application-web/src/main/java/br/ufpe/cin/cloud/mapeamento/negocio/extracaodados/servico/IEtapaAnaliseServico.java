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
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaEtapaRevisao;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EtapaAnaliseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EtapaAnalise;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.SelecaoEstudo;

/**
 * Representa a definição da camada de negócios da entidade {@link EtapaAnalise}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:26:28
 */
public interface IEtapaAnaliseServico extends
		IServico<EtapaAnalise, EtapaAnaliseBean> {

	
	void montarEtapaAnaliseInicialEstudosDuplicados(String loginRevisor);
	
	void montarEtapaAnaliseCriterioInclusaoExclusao(SelecaoEstudo selecao, Revisor revisor);
	
	/**
	 * Lista todas as etapas de revisão alocadas para um revisor.
	 * 
	 * @param loginRevisor O login do revisor.
	 * @return Um {@link List} contendo as revisoes alocadas para o revisor ou <code>null</code> caso não exista.
	 */
	List<EtapaAnaliseBean> listarEtapasAnalise(String loginRevisor);
	
	/**
	 * Lista todas as etapas de revisão alocadas para um revisor.
	 * 
	 * @param idEtapaRevisao O identificador da Etapa de Revisão.
	 * @return Uma instância de {@link EstatisticaEtapaRevisao} contendo as estatísticas da Etapa de Revisão.
	 */
	EstatisticaEtapaRevisao coletarEstatisticasEtapaRevisao(Long idEtapaRevisao, Long idRevisor);

}
