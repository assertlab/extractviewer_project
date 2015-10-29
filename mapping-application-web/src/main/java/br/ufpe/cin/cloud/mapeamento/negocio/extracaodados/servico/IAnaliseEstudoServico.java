/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import java.util.HashMap;
import java.util.List;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.FiltroPropriedade;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.OrdenacaoPropriedade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AnaliseEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AutorBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasConclusaoEtapaRevisaoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasEsforcoEtapaRevisaoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.PalavraChaveBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.AnaliseEstudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EtapaAnalise;

/**
 * Representa a definição da camada de negócios da entidade {@link AnaliseEstudo}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:25:39
 */
public interface IAnaliseEstudoServico extends IServico<AnaliseEstudo, AnaliseEstudoBean> {

    /**
     * Lista as análises de estudo de uma determinada etapa de análise.
     * 
     * @param id O identificador da revisão;
     * @return Um {@link List} de analises desta revisão.
     */
    List<AnaliseEstudoBean> listar(Long id, Long idRevisor);

    /**
     * Recupera a quantidade de análises de estudo de uma determinada etapa de análise para um determinado revisor.
     * 
     * @param id O identificador da revisão;
     * @param filtros A lista de filtros a serem aplicados. Caso não deseje, preencha com o valor <code>null</code>
     *        ;
     * @return Um {@link Integer} contendo a quantidade de analises desta revisão.
     */
    Integer qtdTotal(Long id, Long idRevisor, List<FiltroPropriedade> filtros);

    /**
     * Lista as análises de estudo de uma determinada etapa de revisão.
     * 
     * @param idAnalise O identificador da revisão;
     * @param filtros A lista de filtros a serem aplicados. Caso não deseje, preencha com o valor <code>null</code>
     *        ;
     * @param ordenacoes A lista de ordenações a serem aplicadas. Caso não deseje, preencha com o valor
     *        <code>null</code>;
     * @param inicio O registro do qual a seleção inicia.
     * @param qtd A quantidade de registros a serem recuperados.
     * @return Um {@link List} de analises desta revisão.
     */
    List<AnaliseEstudoBean> listar(Long idAnalise, Long idRevisor, List<FiltroPropriedade> filtros, List<OrdenacaoPropriedade> ordenacoes,
            int inicio, int qtd);

    /**
     * Recupera a quantidade de análises de estudo concluídas de uma determinada etapa de revisão.
     * 
     * @param id O identificador da revisão;
     * @return Um {@link Integer} contendo a quantidade de analises concluídas desta revisão.
     */
    Integer qtdTotalConcluidas(Long id, Long idRevisor);

    /**
     * Lista as análises de estudo concluídas de uma determinada etapa de revisão.
     * 
     * @param id O identificador da revisão;
     * @return Um {@link List} de analises desta revisão.
     */
    List<AnaliseEstudoBean> listarConcluidas(Long id, Long idRevisor);

    /**
     * Lista com paginação as análises de estudo concluídas de uma determinada etapa de revisão.
     * 
     * @param id O identificador da revisão;
     * @param inicio O registro do qual a seleção inicia.
     * @param qtd A quantidade de registros a serem recuperados.
     * @return Um {@link List} de analises desta revisão.
     */
    List<AnaliseEstudoBean> listarConcluidas(Long id, Long idRevisor, int inicio, int qtd);

    /**
     * Lista as análises de estudo não concluídas de uma determinada etapa de revisão.
     * 
     * @param id O identificador da revisão;
     * @return Um {@link List} de analises desta revisão.
     */
    List<AnaliseEstudoBean> listarNaoConcluidas(Long id, Long idRevisor);

    /**
     * Recupera a quantidade de análises de estudo não concluídas de uma determinada etapa de revisão.
     * 
     * @param id O identificador da revisão;
     * @return Um {@link Integer} contendo a quantidade de analises não concluídas desta revisão.
     */
    Integer qtdTotalNaoConcluidas(Long id, Long idRevisor);

    /**
     * Lista com paginação as análises de estudo não concluídas de uma determinada etapa de revisão.
     * 
     * @param id O identificador da revisão;
     * @return Um {@link List} de analises desta revisão.
     */
    List<AnaliseEstudoBean> listarNaoConcluidas(Long id, Long idRevisor, int inicio, int qtd);

    /**
     * Coleta as estatísticas da aplicação dos critérios de inclusão/exclusão dos estudos concluídos.
     * 
     * @param idEtapa O identificador da etapa.
     * @return Uma instância de {@link EstatisticasConclusaoEtapaRevisaoBean} contendo os dados coletados.
     */
    EstatisticasConclusaoEtapaRevisaoBean coletarEstatisticasDeConclusao(Long idEtapa, Long idRevisor);

    /**
     * Coleta as estatísticas da esforço na execução da análise.
     * 
     * @param idEtapa O identificador da etapa.
     * @return Uma instância de {@link EstatisticasEsforcoEtapaRevisaoBean} contendo os dados coletados.
     */
    EstatisticasEsforcoEtapaRevisaoBean coletarEstatisticasDeEsforco(Long idEtapa, Long idRevisor);

    /**
     * Transforma a análise atual na matriz das demais análises das quais a matriz atual era pai.
     * 
     * @param estudo A instância que será a nova matriz.
     * @param matrizAtual A instância que é a atual matriz.
     * @return A instância com os dados alterados.
     */
    AnaliseEstudoBean inverterOrdemMatrizEstudo(AnaliseEstudoBean estudo, AnaliseEstudoBean matrizAtual);

    /**
     * Identifica potnciais análises duplicadas de acordo com os valores informados no filtro de busca.
     * 
     * @param filtros A instância que contém os valores a sere aplicados como filtro.
     * @param ordenacoes A lista de ordenações a serem aplicadas. Caso não deseje, preencha com o valor
     *        <code>null</code>;
     * @param inicio O registro do qual a seleção inicia.
     * @param qtd A quantidade de registros a serem recuperados.
     * @return Um {@link List} de {@link AnaliseEstudo} contendo os dados encontrados ou <code>null</code> caso
     *         nada seja encontrado.
     */
    List<AnaliseEstudoBean> procurarPotenciaisDuplicados(List<FiltroPropriedade> filtros,
            List<OrdenacaoPropriedade> ordenacoes, int inicio, int qtd);

    /**
     * Recupera a quantidade de análises identificadas na busca por análises duplicadas.
     * 
     * @param filtros A lista de filtros a serem aplicados. Caso não deseje, preencha com o valor <code>null</code>
     *        ;
     * @return Um {@link Integer} contendo a quantidade de analises desta revisão.
     */
    Integer qtdPotenciaisDuplicados(List<FiltroPropriedade> filtros);

    /**
     * Valida se a análise de um estudo pode ser relacionada como duplicada de outra.
     * 
     * @param analise A instância da análise que será a matriz.
     * @param novaMatriz A instância da análise que será a duplicata.
     */
    void validarAssociacaoDuplicado(AnaliseEstudoBean analise, AnaliseEstudoBean novaMatriz);

    /**
     * Lista os valores dos campos palavrasChave da entidade {@link Estudo} que foram analisadas na etapa de
     * revisão informada.
     * 
     * @param idEtapaRevisao O identificador da etapa de revisão.
     * @param incluidos Informe <code>true</code> caso deseje as palavras apenas dos estudos incluídos, informe
     *        <code>false</code> se deseja apenas os dos estudos não incluídos ou informe <code>null</code> se
     *        desejar as palavras chaves de todos os estudos.
     * @return Um {@link HashMap} contendo as palavras chaves identificadas.
     */
    HashMap<String, PalavraChaveBean> listarPalavrasChaveEstudos(Long idEtapaRevisao, Long idRevisor, Boolean incluidos);

    /**
     * Identifica a quantidade de estudos analisados em um determinado ano.
     * 
     * @param incluidos Indica se o quantitativo é de estudos incluídos.
     * @return Um {@link List} de {@link EstatisticaEstudoBean} contendo a quantidade de estudos identificados por
     *         ano.
     */
    List<EstatisticasEstudoBean> estatisticaQuantidadeEstudosAnalisadosPorAno(Boolean incluidos, Long idEtapaRevisao, Long idRevisor);

    /**
     * Lista os valores dos campos autores da entidade {@link Estudo} que foram analisadas na etapa de revisão
     * informada.
     * 
     * @param idEtapaRevisao O identificador da etapa de revisão.
     * @param incluidos Informe <code>true</code> caso deseje os autores apenas dos estudos incluídos, informe
     *        <code>false</code> se deseja apenas os dos estudos não incluídos ou informe <code>null</code> se
     *        desejar os autores de todos os estudos.
     * @return Um {@link HashMap} contendo os autores identificados.
     */
    HashMap<String, AutorBean> listarAutoresEstudos(Long idEtapaRevisao, Long idRevisor, Boolean incluidos);

    void gerarAnalisesEstudosParaDupla(EtapaAnalise etapa, List<Estudo> estudos);
    
    void identificarConflitosAnalisesDuplas(Long idEtapaAnalise, Long idRevisor1, Long idRevisor2);
}
