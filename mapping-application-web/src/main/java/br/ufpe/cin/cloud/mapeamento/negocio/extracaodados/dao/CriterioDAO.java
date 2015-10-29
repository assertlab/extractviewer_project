/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Criterio;

/**
 * Representa a implementação da camada de dados da entidade {@link Criterio}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:14:32
 */
@Repository(value = "criterioDAO")
public class CriterioDAO extends AbstractDAO<Criterio> implements ICriterioDAO {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	protected static Log LOG = LogFactory.getLog(CriterioDAO.class);
	
	
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
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.ICriterioDAO#buscarCriterio(java.lang.String)
	 */
	@Override
	public Criterio buscarCriterio(String descricao) {
		this.getLog().trace("iniciando busca de criterio por descricao");

		Criterio criterio = null;

		try {
			Query query = super
					.getSession()
					.createQuery(
							"FROM Criterio c WHERE c.descricao like :descricao");
			
			query.setParameter("descricao", "%" + descricao + "%");
			query.setMaxResults(1);

			Object resultado = query.uniqueResult();

			if (resultado != null) {
				criterio = (Criterio) resultado;
			}
		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "buscar de criterio por descricao");
		}

		this.getLog().trace("finalizando busca de criterio por descricao");

		return criterio;
	}
	
}
