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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;

/**
 * Representa a implementação da camada de acesso a dados da entidade {@link Estudo}
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 18:31:31
 */
@Repository(value = "estudoDAO")
public class EstudoDAO extends AbstractDAO<Estudo> implements IEstudoDAO {

    /**
     * Representa o mecanismo de log da classe.
     */
    protected static Log LOG = LogFactory.getLog(EstudoDAO.class);

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
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEstudoDAO#listarEstudosAgrupadosPorTitulo()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Estudo> listarEstudosAgrupadosPorTitulo() {
        this.getLog().trace("iniciando a listagem de estudos agrupados por titulo");

        List<Estudo> resultados = null;

        try {
            Query query =
                    super.getSession()
                            .createQuery(
                                    "SELECT e.id as id, e.codigo as codigo, e.titulo as titulo, e.tituloLimpo as tituloLimpo, "
                                            + "e.ano as ano, e.autores as autores, e.resumo as resumo, "
                                            + "e.arquivo as arquivo, e.urlNaoTratada as urlNaoTratada FROM Estudo e INNER JOIN e.busca INNER JOIN e.busca.base "
                                            + "GROUP BY e.tituloLimpo, e.busca.id, e.titulo, e.ano, e.autores, e.resumo, e.arquivo, "
                                            + "e.urlNaoTratada, e.codigo, e.id ORDER BY e.tituloLimpo, e.busca.id");

            query.setResultTransformer(new AliasToBeanResultTransformer(Estudo.class));

            resultados = query.list();

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "listar agrupado por titulo");
        }

        this.getLog().trace("finalizando listagem de estudos agrupados por titulo");

        return resultados;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEstudoDAO#identificarQuantidadeEstudos()
     */
    @Override
    public Integer identificarQuantidadeEstudos() {
        this.getLog().trace("iniciando identificação de quantidade de estudos");

        Integer qtd = 0;

        try {
            Query query = super.getSession().createQuery("SELECT COUNT(e.id) FROM Estudo e ");
            qtd = ((Long) query.uniqueResult()).intValue();
        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificar quantidade de estudos");
        }

        this.getLog().trace("finalizando identificação de quantidade de estudos");

        return qtd;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEstudoDAO#estatisticaQuantidadeEstudosPorAno()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EstatisticasEstudoBean> estatisticaQuantidadeEstudosPorAno() {
        List<EstatisticasEstudoBean> estatisticas = null;

        this.getLog().trace("iniciando identificação de quantidade de estudos");

        try {
            Query query =
                    super.getSession().createQuery(
                            "SELECT e.ano as ano, COUNT(e.id) as qtd FROM Estudo e GROUP BY e.ano ORDER BY e.ano DESC");
            query.setResultTransformer(new AliasToBeanResultTransformer(EstatisticasEstudoBean.class));

            estatisticas = query.list();
        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificar quantidade de estudos");
        }

        this.getLog().trace("finalizando identificação de quantidade de estudos");

        return estatisticas;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEstudoDAO#recuperarEstudosIncluidosSelecao(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Estudo> recuperarEstudosIncluidosSelecao(Long idSelecao) {
        List<Estudo> estIncluidos = null;

        this.getLog().trace("iniciando identificação de estudos incluídos em uma seleção de estudos.");

        try {
            Query query =
                    super.getSession()
                            .createQuery(
                                    "SELECT a.estudo FROM AnaliseEstudo a "
                                            + "INNER JOIN a.etapa "
                                            + "INNER JOIN a.etapa.selecaoEstudo "
                                            + "INNER JOIN a.estudo "
                                            + "WHERE a.etapa.selecaoEstudo.id= :idSelecao AND a.incluido=true AND a.concluido=true");
            
            //query.setResultTransformer(new AliasToBeanResultTransformer(Estudo.class));

            query.setParameter("idSelecao", idSelecao);
            estIncluidos = query.list();
        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "identificar estudos incluídos em uma seleção de estudos");
        }

        this.getLog().trace("finalizando identificação de estudos incluídos em uma seleção de estudos");

        return estIncluidos;
    }
}
