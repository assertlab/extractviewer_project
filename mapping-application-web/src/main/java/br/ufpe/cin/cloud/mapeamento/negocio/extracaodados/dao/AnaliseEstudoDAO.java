/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.FiltroPropriedade;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.OrdenacaoPropriedade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaConclusaoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaEsforcoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.AnaliseEstudo;

/**
 * Representa a implementação da camada da acesso à dados da entidade {@link AnaliseEstudo}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:18:49
 */
@Repository(value = "analiseEstudoDAO")
public class AnaliseEstudoDAO extends AbstractDAO<AnaliseEstudo> implements IAnaliseEstudoDAO {

    /**
     * Representa o mecanismo de log da classe.
     */
    protected static Log LOG = LogFactory.getLog(AnaliseEstudoDAO.class);

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO#getLog()
     */
    @Override
    public Log getLog() {
        return LOG;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#listar(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AnaliseEstudo> listar(Long idEtapaRevisao, Long idRevisor) {
        this.getLog().trace("iniciando listagem de analises de uma revisao");

        List<AnaliseEstudo> analises = null;

        try {
            Query query =
                    super.getSession()
                            .createQuery(
                                    "SELECT a FROM AnaliseEstudo a INNER JOIN a.estudo LEFT JOIN a.criterio WHERE a.etapa.id = :idEtapaRevisao AND a.revisor.id = :idRevisor ORDER by a.estudo.titulo");

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);

            analises = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar analises de uma revisao");
        }

        this.getLog().trace("finalizando listagem de analises de uma revisao");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#qtdTotal(java.lang.Long,
     *      java.util.List)
     */
    @Override
    public Integer qtdTotal(Long idEtapaRevisao, Long idRevisor, List<FiltroPropriedade> filtros) {
        this.getLog().trace("iniciando identificação de quantidade de analises de uma revisao");

        Integer analises = null;

        try {

            String hql =
                    "SELECT COUNT(analise.id) FROM AnaliseEstudo as analise " + "INNER JOIN analise.estudo as estudo "
                            + "INNER JOIN analise.etapa as etapa " + "INNER JOIN analise.revisor as revisor "
                            + "LEFT JOIN analise.criterio as criterio " + "INNER JOIN estudo.busca as busca "
                            + "INNER JOIN busca.base as base " + "WHERE etapa.id = :idEtapaRevisao "
                            + "AND revisor.id = :idRevisor";

            hql = super.aplicarFiltros(hql, filtros, true);

            Query query = super.getSession().createQuery(hql);

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);

            super.aplicarValoresFiltros(query, filtros);

            query.setMaxResults(1);

            analises = ((Long) query.uniqueResult()).intValue();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificação de quantidade de analises de uma revisao");
        }

        this.getLog().trace("finalizando identificação de quantidade de analises de uma revisao");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#listar(java.lang.Long,
     *      java.util.List, java.util.List, int, int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AnaliseEstudo> listar(Long idEtapaRevisao, Long idRevisor, List<FiltroPropriedade> filtros,
            List<OrdenacaoPropriedade> ordenacoes, int inicio, int qtd) {
        this.getLog().trace("iniciando listagem paginada de analises de uma revisao");

        List<AnaliseEstudo> analises = null;

        try {

            String hql =
                    "SELECT analise FROM AnaliseEstudo as analise " + "INNER JOIN analise.estudo as estudo "
                            + "INNER JOIN analise.etapa as etapa " + "LEFT JOIN analise.criterio as criterio "
                            + "INNER JOIN analise.revisor as revisor " + "INNER JOIN estudo.busca as busca "
                            + "INNER JOIN busca.base as base " + "WHERE etapa.id = :idEtapaRevisao "
                            + "AND revisor.id = :idRevisor";

            hql = super.aplicarFiltros(hql, filtros, true);
            hql = super.aplicarOrdenacoes(hql, ordenacoes, "estudo.titulo");

            Query query = super.getSession().createQuery(hql);

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);

            super.aplicarValoresFiltros(query, filtros);

            query.setFirstResult(inicio);
            query.setMaxResults(qtd);

            analises = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar analises de uma revisao com paginação");
        }

        this.getLog().trace("finalizando listagem paginada de analises de uma revisao");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#listarConcluidos(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AnaliseEstudo> listarConcluidos(Long idEtapaRevisao, Long idRevisor) {
        this.getLog().trace("iniciando listagem de analises concluidas de uma revisao");

        List<AnaliseEstudo> analises = null;

        try {
            Query query =
                    super.getSession()
                            .createQuery(
                                    "SELECT a FROM AnaliseEstudo a INNER JOIN a.estudo LEFT JOIN a.criterio INNER JOIN a.revisor WHERE a.etapa.id = :idEtapaRevisao "
                                            + "AND a.concluido = true AND a.revisor.id = :idRevisor ORDER by a.estudo.titulo");

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);

            analises = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar analises concluidas de uma revisao");
        }

        this.getLog().trace("finalizando listagem de analises concluidas de uma revisao");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#qtdTotalConcluidos(java.lang.Long)
     */
    @Override
    public Integer qtdTotalConcluidos(Long idEtapaRevisao, Long idRevisor) {
        this.getLog().trace("iniciando identificação de quantidade de analises concluidas de uma revisao");

        Integer analises = null;

        try {
            Query query =
                    super.getSession().createQuery(
                            "SELECT COUNT(a.id) FROM AnaliseEstudo a WHERE a.etapa.id = :idEtapaRevisao AND a.revisor.id = :idRevisor "
                                    + "AND a.concluido = TRUE");

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);
            query.setMaxResults(1);

            analises = ((Long) query.uniqueResult()).intValue();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificação de quantidade de analises concluidas de uma revisao");
        }

        this.getLog().trace("finalizando identificação de quantidade de analises concluidas de uma revisao");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#listarConcluidos(java.lang.Long,
     *      int, int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AnaliseEstudo> listarConcluidos(Long idEtapaRevisao, Long idRevisor, int inicio, int qtd) {
        this.getLog().trace("iniciando listagem de analises concluidas de uma revisao");

        List<AnaliseEstudo> analises = null;

        try {
            Query query =
                    super.getSession()
                            .createQuery(
                                    "SELECT a FROM AnaliseEstudo a INNER JOIN a.estudo LEFT JOIN a.criterio INNER JOIN a.revisor WHERE a.etapa.id = :idEtapaRevisao "
                                            + "AND a.concluido = true AND a.revisor.id = :idRevisor ORDER by a.estudo.titulo");

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);
            query.setFirstResult(inicio);
            query.setMaxResults(qtd);

            analises = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar analises concluidas de uma revisao");
        }

        this.getLog().trace("finalizando listagem de analises concluidas de uma revisao");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#listarNaoConcluidos(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AnaliseEstudo> listarNaoConcluidos(Long idEtapaRevisao, Long idRevisor) {
        this.getLog().trace("iniciando listagem de analises não concluidas de uma revisao");

        List<AnaliseEstudo> analises = null;

        try {
            Query query =
                    super.getSession()
                            .createQuery(
                                    "SELECT a FROM AnaliseEstudo a INNER JOIN a.estudo LEFT JOIN a.criterio INNER JOIN a.revisor WHERE a.etapa.id = :idEtapaRevisao "
                                            + "AND a.concluido = false AND a.revisor.id = :idRevisor ORDER by a.estudo.titulo");

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);

            analises = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar analises nao concluidas de uma revisao");
        }

        this.getLog().trace("finalizando listagem de analises nao concluidas de uma revisao");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#qtdTotalNaoConcluidos(java.lang.Long)
     */
    @Override
    public Integer qtdTotalNaoConcluidos(Long idEtapaRevisao, Long idRevisor) {
        this.getLog().trace("iniciando identificação de quantidade de analises concluidas de uma revisao");

        Integer analises = null;

        try {
            Query query =
                    super.getSession().createQuery(
                            "SELECT COUNT(a.id) FROM AnaliseEstudo a WHERE a.etapa.id = :idEtapaRevisao "
                                    + "AND a.concluido = false AND a.revisor.id = :idRevisor");

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);
            query.setMaxResults(1);

            analises = ((Long) query.uniqueResult()).intValue();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificação de quantidade de analises concluidas de uma revisao");
        }

        this.getLog().trace("finalizando identificação de quantidade de analises concluidas de uma revisao");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#listarNaoConcluidos(java.lang.Long,
     *      int, int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AnaliseEstudo> listarNaoConcluidos(Long idEtapaRevisao, Long idRevisor, int inicio, int qtd) {
        this.getLog().trace("iniciando listagem de analises não concluidas de uma revisao");

        List<AnaliseEstudo> analises = null;

        try {
            Query query =
                    super.getSession()
                            .createQuery(
                                    "SELECT a FROM AnaliseEstudo a INNER JOIN a.estudo  LEFT JOIN a.criterio INNER JOIN a.revisor WHERE a.etapa.id = :idEtapaRevisao "
                                            + "AND a.concluido = false AND a.revisor.id = :idRevisor ORDER by a.estudo.titulo");

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);

            query.setFirstResult(inicio);
            query.setMaxResults(qtd);

            analises = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar analises nao concluidas de uma revisao");
        }

        this.getLog().trace("finalizando listagem de analises nao concluidas de uma revisao");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#coletarCriteriosAplicados(long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EstatisticaConclusaoBean> coletarCriteriosAplicados(long idEtapaRevisao, Long idRevisor) {

        this.getLog().trace("iniciando listagem de critérios aplicados");

        List<EstatisticaConclusaoBean> criterios = null;

        try {
            Query query =
                    super.getSession().createQuery(
                            "SELECT " + "a.criterio.descricao as descricao, " + "COUNT(a.criterio.descricao) as qtd "
                                    + "FROM AnaliseEstudo a " + "LEFT JOIN a.criterio " + " INNER JOIN a.revisor "
                                    + "WHERE a.etapa.id = :idEtapaRevisao " + "AND a.concluido = true "
                                    + " AND a.revisor.id = :idRevisor " + "GROUP BY a.criterio.descricao "
                                    + "ORDER by a.criterio.descricao");

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);

            query.setResultTransformer(new AliasToBeanResultTransformer(EstatisticaConclusaoBean.class));

            criterios = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar criterios aplicados");
        }

        this.getLog().trace("finalizando listagem de criterios aplicados");

        return criterios;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#coletarEstatisticasEsforco(long)
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<EstatisticaEsforcoBean> coletarEstatisticasEsforco(long idEtapaRevisao, Long idRevisor) {

        this.getLog().trace("iniciando listagem de análises concluídas por dia");

        List<EstatisticaEsforcoBean> criterios = null;

        try {
            Query query =
                    super.getSession().createQuery(
                            "SELECT " + "cast(a.dataUltimaAlteracao as date) as data, "
                                    + "COUNT(a.id) as qtdRealizado " + "FROM AnaliseEstudo a "
                                    + "WHERE a.etapa.id = :idEtapaRevisao "
                                    + "AND a.concluido = true AND a.revisor.id = :idRevisor "
                                    + "GROUP BY cast(a.dataUltimaAlteracao as date) "
                                    + "ORDER by cast(a.dataUltimaAlteracao as date) ");

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

            final List<Map> lista = query.list();

            if (lista != null && !lista.isEmpty()) {

                criterios = new ArrayList<EstatisticaEsforcoBean>();
                EstatisticaEsforcoBean estatistica = null;

                for (Map valores : lista) {
                    if (valores != null) {
                        estatistica = new EstatisticaEsforcoBean();

                        estatistica.setData(new LocalDate((Date) valores.get("data")));
                        estatistica.setQtdRealizado((Long) valores.get("qtdRealizado"));

                        criterios.add(estatistica);
                    }
                }
            }

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar de análises concluídas por dia");
        }

        this.getLog().trace("finalizando listagem de análises concluídas por dia");

        return criterios;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#qtdTotalIncluidos(java.lang.Long)
     */
    @Override
    public Integer qtdTotalIncluidos(Long idEtapaRevisao, Long idRevisor) {
        this.getLog().trace("iniciando identificação de quantidade de estudos incluídos de uma revisao");

        Integer analises = null;

        try {
            Query query =
                    super.getSession()
                            .createQuery(
                                    "SELECT COUNT(a.id) FROM AnaliseEstudo a WHERE a.etapa.id = :idEtapaRevisao AND a.incluido = true AND a.concluido = true AND a.revisor.id = :idRevisor");

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);
            query.setMaxResults(1);

            analises = ((Long) query.uniqueResult()).intValue();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificação de quantidade de estudos incluídos de uma revisao");
        }

        this.getLog().trace("finalizando identificação de quantidade de estudos incluídos de uma revisao");

        return analises;
    }

    @Override
    public Integer qtdTotalDuplicados(Long idEtapaRevisao, Long idRevisor) {
        this.getLog().trace("iniciando identificação de quantidade de estudos duplicados de uma revisao");

        Integer analises = null;

        try {
            Query query =
                    super.getSession()
                            .createQuery(
                                    "SELECT COUNT(a.id) FROM AnaliseEstudo a WHERE a.etapa.id = :idEtapaRevisao AND a.incluido = true AND a.matriz is not null AND a.revisor.id = :idRevisor");

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);
            query.setMaxResults(1);

            analises = ((Long) query.uniqueResult()).intValue();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificação de quantidade de estudos duplicados de uma revisao");
        }

        this.getLog().trace("finalizando identificação de quantidade de estudos duplicados de uma revisao");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#qtdTotalExcluidos(java.lang.Long)
     */
    @Override
    public Integer qtdTotalExcluidos(Long idEtapaRevisao, Long idRevisor) {
        this.getLog().trace("iniciando identificação de quantidade de estudos excluídos de uma revisao");

        Integer analises = null;

        try {
            Query query =
                    super.getSession()
                            .createQuery(
                                    "SELECT COUNT(a.id) FROM AnaliseEstudo a WHERE a.etapa.id = :idEtapaRevisao AND a.incluido = false AND a.concluido = true AND a.revisor.id = :idRevisor");

            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);
            query.setMaxResults(1);

            analises = ((Long) query.uniqueResult()).intValue();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificação de quantidade de estudos excluídos de uma revisao");
        }

        this.getLog().trace("finalizando identificação de quantidade de estudos excluídos de uma revisao");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#recuperarAnalisesFilhas(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AnaliseEstudo> recuperarAnalisesFilhas(Long idAnalise) {
        this.getLog().trace("iniciando identificação de análises filhas de uma determinada análise pai.");

        List<AnaliseEstudo> analises = null;

        try {
            Query query = super.getSession().createQuery("SELECT a FROM AnaliseEstudo a WHERE a.matriz.id = :idMatriz");

            query.setParameter("idMatriz", idAnalise);

            analises = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificação de análises filhas de uma determinada análise pai.");
        }

        this.getLog().trace("finalizando identificação de análises filhas de uma determinada análise pai.");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#procurarPotenciaisDuplicados(java.util.List,
     *      java.util.List, int, int)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<AnaliseEstudo> procurarPotenciaisDuplicados(List<FiltroPropriedade> filtros,
            List<OrdenacaoPropriedade> ordenacoes, int inicio, int qtd) {

        this.getLog().trace("iniciando identificação de análises duplicadas.");

        List<AnaliseEstudo> analises = null;

        try {

            String hql =
                    "SELECT analise FROM AnaliseEstudo as analise " + "INNER JOIN analise.estudo as estudo "
                            + "INNER JOIN analise.etapa as etapa " + "LEFT JOIN analise.criterio as criterio "
                            + "INNER JOIN estudo.busca as busca " + "INNER JOIN busca.base as base ";

            hql = super.aplicarFiltros(hql, filtros, false);

            hql = super.aplicarOrdenacoes(hql, ordenacoes, "analise.id");

            Query query = super.getSession().createQuery(hql);

            super.aplicarValoresFiltros(query, filtros);

            analises = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificação de análises duplicadas.");
        }

        this.getLog().trace("finalizando identificação de análises duplicadas.");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#qtdTotalPotenciaisDuplicados(java.util.List)
     */
    @Override
    public Integer qtdTotalPotenciaisDuplicados(List<FiltroPropriedade> filtros) {
        this.getLog().trace("iniciando identificação de quantidade de analises duplicadas");

        Integer analises = null;

        try {

            String hql =
                    "SELECT COUNT(analise.id) FROM AnaliseEstudo as analise " + "INNER JOIN analise.estudo as estudo "
                            + "INNER JOIN analise.etapa as etapa " + "LEFT JOIN analise.criterio as criterio "
                            + "INNER JOIN estudo.busca as busca " + "INNER JOIN busca.base as base ";

            hql = super.aplicarFiltros(hql, filtros, false);

            Query query = super.getSession().createQuery(hql);

            super.aplicarValoresFiltros(query, filtros);

            query.setMaxResults(1);

            analises = ((Long) query.uniqueResult()).intValue();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificação de quantidade de analises duplicadas");
        }

        this.getLog().trace("finalizando identificação de quantidade de analises duplicadas");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#listarPalavrasChaveEstudos(java.lang.Long,
     *      java.lang.Boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> listarPalavrasChaveEstudos(Long idEtapaRevisao, Long idRevisor, Boolean incluidos) {
        this.getLog().trace("iniciando identificação de palavras chaves dos estudos");

        List<String> palavras = null;

        try {

            String hql =
                    "SELECT estudo.palavrasChave FROM AnaliseEstudo as analise "
                            + "INNER JOIN analise.estudo as estudo INNER JOIN analise.revisor WHERE analise.revisor.id = :idRevisor AND analise.etapa.id = :idEtapa ";

            if (Boolean.TRUE.equals(incluidos)) {
                hql += " AND analise.concluido=true AND analise.incluido = true ";
            } else if (Boolean.FALSE.equals(incluidos)) {
                hql += " AND analise.concluido=true AND analise.incluido = false ";
            }

            Query query = super.getSession().createQuery(hql);

            query.setParameter("idRevisor", idRevisor);
            query.setParameter("idEtapa", idEtapaRevisao);

            palavras = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificação de palavras chaves dos estudos");
        }

        this.getLog().trace("finalizando identificação de palavras chaves dos estudos");

        return palavras;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#estatisticaQuantidadeEstudosAnalisadosPorAno(java.lang.Boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EstatisticasEstudoBean> estatisticaQuantidadeEstudosAnalisadosPorAno(Long idEtapa, Long idRevisor,
            Boolean incluidos) {
        List<EstatisticasEstudoBean> estatisticas = null;

        this.getLog().trace("iniciando identificação de quantidade de estudos");

        try {
            Query query =
                    super.getSession()
                            .createQuery(
                                    "SELECT e.ano as ano, COUNT(e.id) as qtd FROM AnaliseEstudo a "
                                            + "INNER JOIN a.estudo e "
                                            + "WHERE a.concluido = true AND a.incluido= :incluido  AND a.revisor.id = :idRevisor AND a.etapa.id = :idEtapa "
                                            + "GROUP BY e.ano ORDER BY e.ano DESC");

            query.setParameter("incluido", incluidos);
            query.setParameter("idRevisor", idRevisor);
            query.setParameter("idEtapa", idEtapa);

            query.setResultTransformer(new AliasToBeanResultTransformer(EstatisticasEstudoBean.class));

            estatisticas = query.list();
        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificar quantidade de estudos");
        }

        this.getLog().trace("finalizando identificação de quantidade de estudos");

        return estatisticas;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> listarAutoresEstudos(Long idEtapaRevisao, Long idRevisor, Boolean incluidos) {
        this.getLog().trace("iniciando identificação de autores dos estudos");

        List<String> palavras = null;

        try {

            String hql =
                    "SELECT estudo.autores FROM AnaliseEstudo as analise "
                            + "INNER JOIN analise.estudo as estudo INNER JOIN analise.revisor ";

            if (Boolean.TRUE.equals(incluidos)) {
                hql +=
                        " WHERE analise.concluido=true AND analise.incluido = true AND analise.revisor.id = :idRevisor AND analise.etapa.id = :idEtapaRevisao";
            } else if (Boolean.FALSE.equals(incluidos)) {
                hql +=
                        " WHERE analise.concluido=true AND analise.incluido = false AND analise.revisor.id = :idRevisor AND analise.etapa.id = :idEtapaRevisao";
            }

            Query query = super.getSession().createQuery(hql);
            query.setParameter("idEtapaRevisao", idEtapaRevisao);
            query.setParameter("idRevisor", idRevisor);

            palavras = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificação de autores dos estudos");
        }

        this.getLog().trace("finalizando identificação de autores dos estudos");

        return palavras;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#buscarConflitosAnalises(java.lang.Long,
     *      java.lang.Long, java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AnaliseEstudo> buscarConflitosAnalises(Long idEtapaAnalise, Long revisor1, Long revisor2) {
        List<AnaliseEstudo> analises = null;

        this.getLog().trace("iniciando identificação de conflitos entre revisores de análises.");

        try {

            String hql =
                    "SELECT a FROM AnaliseEstudo WHERE a.id IN (SELECT analise.id FROM AnaliseEstudo as analise "
                            + "INNER JOIN analise.revisor as revisor "
                            + "INNER JOIN analise.estudo as estudo "
                            + "INNER JOIN analise.criterio as criterio "
                            + "WHERE (revisor.id = :idRevisor1 OR revisor.id = :idRevisor2) AND "
                            + "analise.etapa.id = :idEtapa "
                            + "GROUP BY analise.estudo.id, analise.estudo.titulo, analise.revisor.id, analise.concluido)";

            Query query = super.getSession().createQuery(hql);
            query.setParameter("idRevisor1", revisor1);
            query.setParameter("idRevisor2", revisor2);
            query.setParameter("idEtapa", idEtapaAnalise);

            analises = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "busca de conflitos entre revisores de duplas de análises");
        }

        this.getLog().trace("finalizando identificação de conflitos entre revisores de análises.");

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO#validarConclusaoAnalises(java.lang.Long,
     *      java.lang.Long, java.lang.Long)
     */
    @Override
    public boolean validarConclusaoAnalises(Long idEtapaAnalise, Long revisor1, Long revisor2) {
        boolean concluido = true;

        this.getLog().trace("iniciando identificação de conflitos entre revisores de análises.");

        try {

            String hql =
                    "SELECT count(a.id) FROM AnaliseEstudo a WHERE a.etapa.id = :idEtapa AND (a.revisor.id = :idRevisor1 OR a.revisor.id = :idRevisor2) AND a.concluido = false";

            Query query = super.getSession().createQuery(hql);
            query.setParameter("idRevisor1", revisor1);
            query.setParameter("idRevisor2", revisor2);
            query.setParameter("idEtapa", idEtapaAnalise);

            Long qtd = (Long) query.uniqueResult();
            
            if (qtd != null && qtd > 0) {
                concluido = false;
            }

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "busca de conflitos entre revisores de duplas de análises");
        }

        this.getLog().trace("finalizando identificação de conflitos entre revisores de análises.");

        return concluido;
    }
}
