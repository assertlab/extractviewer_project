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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;

/**
 * Representa a implementação da camada de acesso à dados da entidade
 * {@link UsuarioBean}.
 * 
 * @author helaine.lins
 * @created 15/04/2014 - 17:10:14
 */
@Repository(value = "usuarioDAO")
public class UsuarioDAO extends AbstractDAO<Usuario> implements IUsuarioDAO {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private Log LOG = LogFactory.getLog(UsuarioDAO.class);

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
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.dao.IUsuarioDAO#existeUsuarioLogin(java.lang.String,
	 *      java.lang.Long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean existeUsuarioLogin(String login, Long id) {
		boolean existe = false;

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(
					Usuario.class, "usuario");
			criteria.add(Restrictions.eq("usuario.login", login));

			if (id != null && id > 0) {
				criteria.add(Restrictions.ne("usuario.id", id));
			}

			Criteria executableCriteria = criteria.getExecutableCriteria(this
					.getSession());

			List<Usuario> resultado = executableCriteria.list();

			if (resultado != null && !resultado.isEmpty()) {
				existe = true;
			}

		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "existe usuario login");
		}

		return existe;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.dao.IUsuarioDAO#buscarUsuarioLoginSistema(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Usuario buscarUsuarioLoginSistema(String login) {
		Usuario usuario = null;

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(
					Usuario.class, "usuario");
			criteria.add(Restrictions.eq("usuario.login", login));

			Criteria executableCriteria = criteria.getExecutableCriteria(this
					.getSession());
			executableCriteria.setMaxResults(1);
			List<Usuario> resultado = executableCriteria.list();

			if (resultado != null && !resultado.isEmpty()) {
				if (resultado.get(0) != null) {
					usuario = resultado.get(0);
				}
			}

		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "buscar usuario login sistema");
		}

		return usuario;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.dao.IUsuarioDAO#buscarUsuarioNome(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuarioNome(String nome) {
		List<Usuario> resultado = null;

		try {
			Query query = this.getSession().createQuery(
					"FROM UsuarioBean u WHERE lower(u.nome) LIKE lower(:nome)");
			
			query.setParameter("nome", '%' + StringUtils.trim(nome) + '%');
			
			resultado = query.list();
		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "buscar usuario nome");
		}

		return resultado;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO#incluir(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	public Long incluir(Usuario entidade) {
		Long id = null;

		try {
			id = super.incluir(entidade);
		} catch (Throwable e) {
			this.tratarErroPersistencia(e, "incluir");
		}

		return id;
	}

}
