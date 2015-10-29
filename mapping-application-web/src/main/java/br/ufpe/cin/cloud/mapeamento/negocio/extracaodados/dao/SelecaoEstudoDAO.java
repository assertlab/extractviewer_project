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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.SelecaoEstudo;

/**
 * Representa a implementação da camada de dados da entidade {@link SelecaoEstudo}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:16:04
 */
@Repository(value = "selecaoEstudoDAO")
public class SelecaoEstudoDAO extends AbstractDAO<SelecaoEstudo> implements ISelecaoEstudoDAO {

    /**
     * Representa o mecanismo de log da classe.
     */
    protected static Log LOG = LogFactory.getLog(SelecaoEstudoDAO.class);

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
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.ISelecaoEstudoDAO#existeSelecaoEstudo(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean existeSelecaoEstudo(Long idSelecao) {
        boolean existe = false;

        try {
            DetachedCriteria criteria = DetachedCriteria.forClass(SelecaoEstudo.class, "selecao");
            criteria.add(Restrictions.eq("selecao.id", idSelecao));

            Criteria executableCriteria = criteria.getExecutableCriteria(this.getSession());

            List<SelecaoEstudo> resultado = executableCriteria.list();

            if (resultado != null && !resultado.isEmpty()) {
                existe = true;
            }

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "existe selecao estudo");
        }

        return existe;
    }

}
