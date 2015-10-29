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
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.RevisorBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;

/**
 * Representa a definição da camada de acesso a dados da entidade {@link Estudo}.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 18:32:58
 */
public interface IEstudoServico extends IServico<Estudo, EstudoBean> {

	/**
     * Realiza a inclusão em lote de instâncias de {@link EstudoBean}.
     * 
     * @param busca A instância da busca na qual os estudos serão incluídos.
     * @param beans A instância a ser inserida.
     * @return Um {@link List} contendo as instâncias de {@link Estudo} inseridas no banco.
     */
	List<Estudo> incluir(BuscaBean busca, List<EstudoBean> beans);
    
	/**
     * Realiza a alteração dos dados de um estudo no sistema.
     * 
     * @param revisor O revisor que está alterando os dados.
     * @param estudo A instância que contém os dados a serem alterados.
     */
    void alterar(RevisorBean revisor, EstudoBean estudo);
	
    /**
	 * Identifica a quantidade de estudos em um determinado ano.
	 * 
	 * @return Um {@link List} de {@link EstatisticaEstudoBean} contendo a quantidade de estudos identificados por ano.
	 */
	List<EstatisticasEstudoBean> estatisticaQuantidadeEstudosPorAno();
}
