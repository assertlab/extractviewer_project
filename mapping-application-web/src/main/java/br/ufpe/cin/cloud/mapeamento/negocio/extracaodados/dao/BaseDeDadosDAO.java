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
import org.springframework.stereotype.Repository;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.BaseDeDados;

/**
 * Representa a implementação da camada de acesso à dados da entidade {@link BaseDeDados}
 * 
 * @author helaine.lins
 * @created 19/08/2014 - 10:46:27
 */
@Repository(value = "baseDeDadosDAO")
public class BaseDeDadosDAO extends AbstractDAO<BaseDeDados> implements
		IBaseDeDadosDAO {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	protected static Log LOG = LogFactory.getLog(BaseDeDadosDAO.class);
	
	
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
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IBaseDeDadosDAO#buscar(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BaseDeDados buscar(String nome) {
		this.getLog().trace("iniciando busca pelo nome");

        BaseDeDados base = null;

        try {
            Query query = super.getSession().createQuery("FROM BaseDeDados b WHERE upper(b.nome) like upper(:nome)");
            query.setParameter("nome", nome);
            query.setMaxResults(1);
            
            List<BaseDeDados> resultados = query.list();
            
            if (resultados != null && !resultados.isEmpty()) {
            	base = resultados.get(0);
            }
        } catch (Throwable e) {
            this.tratarErroPersistencia(e, "procurar por exemplo");
        }

        this.getLog().trace("finalizando busca pelo nome");

        return base;
	}
	
}
