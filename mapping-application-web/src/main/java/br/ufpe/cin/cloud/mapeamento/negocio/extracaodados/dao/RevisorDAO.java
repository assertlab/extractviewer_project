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
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;

/**
 * Representa a implementação da camada de dados da entidade {@link Revisor}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:16:04
 */
@Repository(value = "revisorDAO")
public class RevisorDAO extends AbstractDAO<Revisor> implements IRevisorDAO {

    /**
     * Representa o mecanismo de log da classe.
     */
    protected static Log LOG = LogFactory.getLog(RevisorDAO.class);

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
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IRevisorDAO#buscarRevisor(java.lang.String)
     */
    @Override
    public Revisor buscarRevisor(String login) {
        this.getLog().trace("iniciando busca de revisor por login");

        Revisor revisor = null;

        try {
            Query query =
                    super.getSession().createQuery(
                            "SELECT r FROM Revisor r INNER JOIN r.usuario WHERE r.usuario.login = :login");

            query.setParameter("login", login);
            query.setMaxResults(1);

            Object resultado = query.uniqueResult();

            if (resultado != null) {
                revisor = (Revisor) resultado;
            }
        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "buscar de revisor por login");
        }

        this.getLog().trace("finalizando busca de revisor por login");

        return revisor;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IRevisorDAO#existeRevisor(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean existeRevisor(Long idRevisor) {
        boolean existe = false;

        try {
            DetachedCriteria criteria = DetachedCriteria.forClass(Revisor.class, "revisor");
            criteria.add(Restrictions.eq("revisor.id", idRevisor));

            Criteria executableCriteria = criteria.getExecutableCriteria(this.getSession());

            List<Revisor> resultado = executableCriteria.list();

            if (resultado != null && !resultado.isEmpty()) {
                existe = true;
            }

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "existe revisor");
        }

        return existe;
    }

}
