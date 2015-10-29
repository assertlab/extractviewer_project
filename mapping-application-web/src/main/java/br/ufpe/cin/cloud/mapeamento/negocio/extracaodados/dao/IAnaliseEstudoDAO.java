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

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.FiltroPropriedade;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.OrdenacaoPropriedade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaConclusaoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaEsforcoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.AnaliseEstudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;

/**
 * Representa a definição da camada de acesso à dados da entidade {@link AnaliseEstudo}.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 14:41:26
 */
public interface IAnaliseEstudoDAO extends IDao<AnaliseEstudo> {

    /**
     * Lista as análises de uma determinada revisão.
     * 
     * @param idAnalise O identificador da análise.
     * @return Um {@link List} contendo as análises encontradas.
     */
    List<AnaliseEstudo> listar(Long idAnalise, Long idRevisor);

    /**
     * Recupera a quantidade total de análises de uma determinada revisão.
     * 
     * @param idAnalise O identificador da análise.
     * @param filtros A lista de filtros a serem aplicados. Caso não deseje, preencha com o valor <code>null</code>
     *        ;
     * @return Um {@link Integer} contendo a quantidade de análises encontradas.
     */
    Integer qtdTotal(Long idAnalise, Long idRevisor, List<FiltroPropriedade> filtros);

    /**
     * Lista com paginação as análises de uma determinada revisão.
     * 
     * @param idAnalise O identificador da análise.
     * @param idRevisor O identificador do revisor.
     * @param filtros A lista de filtros a serem aplicados. Caso não deseje, preencha com o valor <code>null</code>
     *        ;
     * @param ordenacoes A lista de ordenações a serem aplicadas. Caso não deseje, preencha com o valor
     *        <code>null</code>;
     * @param inicio O registro do qual a seleção inicia.
     * @param qtd A quantidade de registros a serem recuperados.
     * @return Um {@link List} contendo as análises encontradas.
     */
    List<AnaliseEstudo> listar(Long idAnalise, Long idRevisor, List<FiltroPropriedade> filtros,
            List<OrdenacaoPropriedade> ordenacoes, int inicio, int qtd);

    /**
     * Lista as análises concluidas de uma determinada revisão.
     * 
     * @param idAnalise O identificador da análise.
     * @return Um {@link List} contendo as análises encontradas.
     */
    List<AnaliseEstudo> listarConcluidos(Long idAnalise, Long idRevisor);

    /**
     * Recupera a quantidade total de análises concluídas de uma determinada revisão.
     * 
     * @param idAnalise O identificador da análise.
     * @return Um {@link Integer} contendo a quantidade de análises concluídas encontradas.
     */
    Integer qtdTotalConcluidos(Long idAnalise, Long idRevisor);

    /**
     * Lista com paginação as análises concluidas de uma determinada revisão.
     * 
     * @param idAnalise O identificador da análise.
     * @param inicio O registro do qual a seleção inicia.
     * @param qtd A quantidade de registros a serem recuperados.
     * @return Um {@link List} contendo as análises encontradas.
     */
    List<AnaliseEstudo> listarConcluidos(Long idAnalise, Long idRevisor, int inicio, int qtd);

    /**
     * Lista as análises não concluidas de uma determinada revisão.
     * 
     * @param idAnalise O identificador da análise.
     * @return Um {@link List} contendo as análises encontradas.
     */
    List<AnaliseEstudo> listarNaoConcluidos(Long idAnalise, Long idRevisor);

    /**
     * Recupera a quantidade total de análises não concluídas de uma determinada revisão.
     * 
     * @param idAnalise O identificador da análise.
     * @return Um {@link Integer} contendo a quantidade de análises não concluídas encontradas.
     */
    Integer qtdTotalNaoConcluidos(Long idAnalise, Long idRevisor);

    /**
     * Lista com paginação as análises não concluidas de uma determinada revisão.
     * 
     * @param idAnalise O identificador da análise.
     * @param inicio O registro do qual a seleção inicia.
     * @param qtd A quantidade de registros a serem recuperados.
     * @return Um {@link List} contendo as análises encontradas.
     */
    List<AnaliseEstudo> listarNaoConcluidos(Long idAnalise, Long idRevisor, int inicio, int qtd);

    /**
     * Cole a quantidade de análises de estudos concluídas por dia.
     * 
     * @param idAnalise O identificador da análise.
     * @return Um {@link List} de {@link EstatisticaEsforcoBean} contendo o detalhamento dos critérios aplicados.
     */
    List<EstatisticaEsforcoBean> coletarEstatisticasEsforco(long idEtapaRevisao, Long idRevisor);

    /**
     * Lista os critérios e os quantitativos aplicados na análise.
     * 
     * @param idAnalise O identificador da análise.
     * @return Um {@link List} de {@link EstatisticaConclusaoBean} contendo o detalhamento dos critérios aplicados.
     */
    List<EstatisticaConclusaoBean> coletarCriteriosAplicados(long idEtapaRevisao, Long idRevisor);

    /**
     * Recupera a quantidade total de análises em que os estudos foram incluídos em uma determinada revisão.
     * 
     * @param idAnalise O identificador da análise.
     * @return Um {@link Integer} contendo a quantidade de análises incluídas encontradas.
     */
    Integer qtdTotalIncluidos(Long idAnalise, Long idRevisor);

    /**
     * Recupera a quantidade total de análises em que os estudos não foram incluídos uma determinada revisão.
     * 
     * @param idAnalise O identificador da análise.
     * @return Um {@link Integer} contendo a quantidade de análises não incluidas encontradas.
     */
    Integer qtdTotalExcluidos(Long idAnalise, Long idRevisor);

    /**
     * Recupera a quantidade total de análises marcadas como duplicadas.
     * 
     * @param idAnalise O identificador da análise.
     * @return Um {@link Integer} contendo a quantidade de análises não incluidas encontradas.
     */
    Integer qtdTotalDuplicados(Long idAnalise, Long idRevisor);

    /**
     * Recupera todas as análises que identificaram o estudo informado como matriz.
     * 
     * @param idAnalise O identficador da análise.
     * @return Um {@link List} de {@link AnaliseEstudo} filhas.
     */
    List<AnaliseEstudo> recuperarAnalisesFilhas(Long idAnalise);

    /**
     * Identifica potnciais análises duplicadas de acordo com os valores informados no filtro de busca.
     * 
     * @param filtros A instância que contém os valores a sere aplicados como filtro.
     * @param ordenacoes A instância que contém as preferências de ordenação da lista de resultados.
     * @param inicio O registro do qual a seleção inicia.
     * @param qtd A quantidade de registros a serem recuperados.
     * @return Um {@link List} de {@link AnaliseEstudo} contendo os dados encontrados ou <code>null</code> caso
     *         nada seja encontrado.
     */
    List<AnaliseEstudo> procurarPotenciaisDuplicados(List<FiltroPropriedade> filtros,
            List<OrdenacaoPropriedade> ordenacoes, int inicio, int qtd);

    /**
     * Recupera a quantidade total de análises duplicadas conforme o filtro selecionado pelo usuário.
     * 
     * @param filtros A lista de filtros a serem aplicados. Caso não deseje, preencha com o valor <code>null</code>
     *        ;
     * @return Um {@link Integer} contendo a quantidade de análises encontradas.
     */
    Integer qtdTotalPotenciaisDuplicados(List<FiltroPropriedade> filtros);

    /**
     * Lista os valores dos campos palavrasChave da entidade {@link Estudo} que foram analisadas na etapa de
     * revisão informada.
     * 
     * @param idEtapaRevisao O identificador da etapa de revisão.
     * @param incluidos Informe <code>true</code> caso deseje as palavras apenas dos estudos incluídos, informe
     *        <code>false</code> se deseja apenas os dos estudos não incluídos ou informe <code>null</code> se
     *        desejar as palavras chaves de todos os estudos.
     * @return Um {@link List} contendo as palavras chaves identificadas.
     */
    List<String> listarPalavrasChaveEstudos(Long idEtapaRevisao, Long idRevisor, Boolean incluidos);

    /**
     * Identifica a quantidade de estudos analisados que foram analisados por ano.
     * 
     * @param incluidos Indica se devem ser retornados apenas os estudos incluídos.
     * @return Um {@link List} de {@link EstatisticaEstudoBean} contendo a quantidade de estudos identificados por
     *         ano.
     */
    List<EstatisticasEstudoBean> estatisticaQuantidadeEstudosAnalisadosPorAno(Long idEtapaRevisao, Long idRevisor,Boolean incluidos);

    /**
     * Lista os autores dos {@link Estudo} que foram analisados na etapa de revisão informada.
     * 
     * @param idEtapaRevisao O identificador da etapa de revisão.
     * @param incluidos Informe <code>true</code> caso deseje os autores apenas dos estudos incluídos, informe
     *        <code>false</code> se deseja apenas os dos estudos não incluídos ou informe <code>null</code> se
     *        desejar os autores de todos os estudos.
     * @return Um {@link List} contendo os autores identificados.
     */
    List<String> listarAutoresEstudos(Long idEtapaRevisao, Long idRevisor, Boolean incluidos);
    
    /**
     * Verifica se os revisores concluiram as análises dos estudos.
     * 
     * @param idEtapaAnalise O identificador da etapa de análise.
     * @param revisor1 O identificador do revisor 1.
     * @param revisor2 O identificador do revisor 2.
     * @return Um boolean indicando se os revisores concluiram as análises dos estudos.
     */
    boolean validarConclusaoAnalises(Long idEtapaAnalise, Long revisor1, Long revisor2);
    
    /**
     * Realiza a identificação de conflitos entre os revisores de uma determinada dupla.
     * 
     * @param idEtapaAnalise O identificador da etapa de análise.
     * @param revisor1 O identificador do primeiro revisor.
     * @param revisor2 O identificador do segundo revisor.
     */
    List<AnaliseEstudo> buscarConflitosAnalises(Long idEtapaAnalise, Long revisor1, Long revisor2);
    
    
    
}
