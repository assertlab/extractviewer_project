/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Perfil;

/**
 * Representa a implementação da camada de acesso à dados da entidade {@link Perfil}.
 * 
 * @author helaine.lins
 * @created 15/04/2014 - 15:54:57
 */
@Repository(value = "perfilDAO")
public class PerfilDAO extends AbstractDAO<Perfil> implements IPerfilDAO {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private Log LOG = LogFactory.getLog(PerfilDAO.class);

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
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.dao.IPerfilDAO#obterPerfilUsuario()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Perfil obterPerfilUsuario() {
		Perfil perfil = null;

        try {
            DetachedCriteria criteria = DetachedCriteria.forClass(Perfil.class, "perfil");

		criteria.add(Restrictions.eq("perfil.descricao", "ROLE_USUARIO"));

            Criteria executableCriteria = criteria.getExecutableCriteria(this.getSession());

		List<Perfil> resultado = executableCriteria.list();

		if (resultado != null && !resultado.isEmpty()) {
			if (resultado.get(0) != null) {
				perfil = resultado.get(0);
			}
		}

        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "obter perfil usuario");
        }

		return perfil;
	}

}
