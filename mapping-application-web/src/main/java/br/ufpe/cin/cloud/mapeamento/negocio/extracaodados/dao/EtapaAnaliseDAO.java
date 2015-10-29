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
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.DatasExtremasAnalisesBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaBuscaEstudosPorConferencia;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EtapaAnalise;

/**
 * Representa a implementação da camada de acesso à dados da entidade {@link EtapaAnalise}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:17:31
 */
@Repository(value = "etapaRevisaoDAO")
public class EtapaAnaliseDAO extends AbstractDAO<EtapaAnalise> implements IEtapaAnaliseDAO {

    /**
     * Representa o mecanismo de log da classe.
     */
    protected static Log LOG = LogFactory.getLog(EtapaAnaliseDAO.class);

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
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEtapaAnaliseDAO#existeEtapaRevisaoInicial()
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean existeEtapaRevisaoInicial() {
        this.getLog().trace("iniciando busca por etapa de revisao inicial");

        Boolean existe = false;

        try {
            Query query = super.getSession().createQuery("FROM EtapaAnalise e WHERE e.inicial = true");

            List<EtapaAnalise> resultados = query.list();

            if (resultados != null && !resultados.isEmpty()) {
                existe = resultados.size() > 0;
            }
        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "buscar etapa de revisao inicial");
        }

        this.getLog().trace("finalizando busca por etapa de revisao inicial");

        return existe;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEtapaAnaliseDAO#listarRevisoes(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EtapaAnalise> listarEtapasAnalise(String loginRevisor) {
        this.getLog().trace("iniciando busca de revisões para um revisor");

        List<EtapaAnalise> resultados = null;

        try {
            Query query =
                    super.getSession()
                            .createQuery(

                            //"select e FROM EtapaAnalise e INNER JOIN e.revisor INNER JOIN e.revisor.usuario "
                            //	+ "WHERE e.revisor.usuario.login = :loginRevisor ORDER BY e.dataInclusao DESC");
                                    "SELECT DISTINCT(etapa) " +
                                    "FROM AnaliseEstudo ae " +
                                    "INNER JOIN ae.revisor revisor " +
                                    "INNER JOIN ae.etapa etapa " +
                                    "LEFT JOIN ae.revisor.usuario usuarioRev " +
                                    "WHERE usuarioRev.login = :loginRevisor");
            
            query.setParameter("loginRevisor", loginRevisor);

            resultados = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "buscar revisões para um revisor");
        }

        this.getLog().trace("finalizando busca de revisões para um revisor");

        return resultados;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEtapaAnaliseDAO#listarBuscasPorOrigemECategoria(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EstatisticaBuscaEstudosPorConferencia> listarBuscasPorOrigemECategoria(Long idEtapa) {

        this.getLog().trace("iniciando listagem de buscas por origem e categoria");

        List<EstatisticaBuscaEstudosPorConferencia> resultados = null;

        try {
            Query query =
                    super.getSession()
                            .createQuery(
                                    "SELECT a.estudo.busca.conferencia as nome, "
                                            + "a.estudo.busca.comunidade as comunidade, a.estudo.busca.tipoConferencia as tipo, "
                                            + "a.estudo.busca.prefixo as sigla, "
                                            + "count(a.estudo.id) as qtdEstudos FROM AnaliseEstudo a INNER JOIN a.estudo "
                                            + "INNER JOIN a.estudo.busca "
                                            + "WHERE a.etapa.id = :idEtapa GROUP BY a.estudo.busca.comunidade, a.estudo.busca.tipoConferencia, "
                                            + "a.estudo.busca.conferencia, a.estudo.busca.prefixo "
                                            + "ORDER BY a.estudo.busca.comunidade, a.estudo.busca.tipoConferencia, a.estudo.busca.conferencia, "
                                            + "a.estudo.busca.prefixo");

            query.setParameter("idEtapa", idEtapa);
            query.setResultTransformer(new AliasToBeanResultTransformer(EstatisticaBuscaEstudosPorConferencia.class));

            resultados = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar buscas por origem e categoria");
        }

        this.getLog().trace("finalizando listagem de buscas por origem e categoria");

        return resultados;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEtapaAnaliseDAO#listarBuscasPorCategoria(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EstatisticaBuscaEstudosPorConferencia> listarBuscasPorCategoria(Long idEtapa) {

        this.getLog().trace("iniciando listagem de buscas por categoria");

        List<EstatisticaBuscaEstudosPorConferencia> resultados = null;

        try {
            Query query =
                    super.getSession().createQuery(
                            "SELECT a.estudo.busca.tipoConferencia as tipo, "
                                    + "count(a.estudo.id) as qtdEstudos FROM AnaliseEstudo a INNER JOIN a.estudo "
                                    + "INNER JOIN a.estudo.busca "
                                    + "WHERE a.etapa.id = :idEtapa GROUP BY a.estudo.busca.tipoConferencia "
                                    + "ORDER BY a.estudo.busca.tipoConferencia");

            query.setParameter("idEtapa", idEtapa);
            query.setResultTransformer(new AliasToBeanResultTransformer(EstatisticaBuscaEstudosPorConferencia.class));

            resultados = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar buscas por categoria");
        }

        this.getLog().trace("finalizando listagem de buscas por categoria");

        return resultados;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EstatisticaBuscaEstudosPorConferencia> listarBuscasPorEngenho(Long idEtapa) {

        this.getLog().trace("iniciando listagem de buscas por origem");

        List<EstatisticaBuscaEstudosPorConferencia> resultados = null;

        try {
            Query query =
                    super.getSession().createQuery(
                            "SELECT a.estudo.busca.base.nome as nome, "
                                    + "count(a.estudo.id) as qtdEstudos FROM AnaliseEstudo a INNER JOIN a.estudo "
                                    + "INNER JOIN a.estudo.busca INNER JOIN a.estudo.busca.base "
                                    + "WHERE a.etapa.id = :idEtapa GROUP BY a.estudo.busca.base.nome "
                                    + "ORDER BY a.estudo.busca.base.nome");

            query.setParameter("idEtapa", idEtapa);
            query.setResultTransformer(new AliasToBeanResultTransformer(EstatisticaBuscaEstudosPorConferencia.class));

            resultados = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar buscas por origem");
        }

        this.getLog().trace("finalizando listagem de buscas por origem");

        return resultados;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEtapaAnaliseDAO#recuperarDatasExtremas(br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.DatasExtremasAnalisesBean)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void recuperarDatasExtremas(DatasExtremasAnalisesBean datas) {
        this.getLog().trace("iniciando listagem de buscas por origem");

        try {
            Query query =
                    super.getSession().createQuery(
                            "SELECT MAX(a.dataUltimaAlteracao) as maior, " + "MIN(a.dataUltimaAlteracao) as menor "
                                    + "FROM AnaliseEstudo a " + "WHERE a.etapa.id = :idEtapa "
                                    + "AND a.concluido = true "
                                    + "AND a.dataUltimaAlteracao BETWEEN :dataInicio AND :dataFim");

            query.setParameter("idEtapa", datas.getIdEtapa());

            // montar a menor e a maior do dia
            LocalDateTime dataMenor =
                    new LocalDateTime(datas.getDia().getYear(), datas.getDia().getMonthOfYear(), datas.getDia()
                            .getDayOfMonth(), 0, 0, 0, 0);
            LocalDateTime dataMaior =
                    new LocalDateTime(datas.getDia().getYear(), datas.getDia().getMonthOfYear(), datas.getDia()
                            .getDayOfMonth(), 23, 59, 59, 999);

            query.setParameter("dataInicio", dataMenor);
            query.setParameter("dataFim", dataMaior);

            query.setMaxResults(1);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

            final Map mapa = (Map) query.uniqueResult();

            if (mapa != null) {
                datas.setMenor((LocalDateTime) mapa.get("menor"));
                datas.setMaior((LocalDateTime) mapa.get("maior"));
            }

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar buscas por origem");
        }

        this.getLog().trace("finalizando listagem de buscas por origem");
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEtapaAnaliseDAO#recuperarConcluidosPorDia(org.joda.time.LocalDateTime,
     *      java.lang.Long)
     */
    @Override
    public Long recuperarConcluidosPorDia(LocalDateTime data, Long idEtapa, Long idRevisor) {
        Long resultado = 0l;

        this.getLog().trace("iniciando listagem de buscas por origem");

        try {
            Query query =
                    super.getSession().createQuery(
                            "SELECT COUNT(a.id) " + "FROM AnaliseEstudo a "
                                    + "WHERE a.etapa.id = :idEtapa AND a.revisor.id = :idRevisor "
                                    + "AND a.concluido = true "
                                    + "AND a.dataUltimaAlteracao BETWEEN :dataInicio AND :dataFim");

            query.setParameter("idEtapa", idEtapa);
            query.setParameter("idRevisor", idRevisor);

            // montar a menor e a maior do dia
            LocalDateTime dataMenor =
                    new LocalDateTime(data.getYear(), data.getMonthOfYear(), data.getDayOfMonth(), 0, 0, 0, 0);
            LocalDateTime dataMaior =
                    new LocalDateTime(data.getYear(), data.getMonthOfYear(), data.getDayOfMonth(), 23, 59, 59, 999);

            query.setParameter("dataInicio", dataMenor);
            query.setParameter("dataFim", dataMaior);

            query.setMaxResults(1);

            resultado = (Long) query.uniqueResult();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar buscas por origem");
        }

        this.getLog().trace("finalizando listagem de buscas por origem");

        return resultado;
    }

}
