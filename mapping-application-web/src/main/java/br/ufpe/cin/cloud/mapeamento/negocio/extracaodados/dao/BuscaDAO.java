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
import org.springframework.stereotype.Repository;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Busca;

/**
 * Representa a implementação da camada de acesso a dados da entidade {@link Busca}.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 14:42:35
 */
@Repository(value = "buscaDAO")
public class BuscaDAO extends AbstractDAO<Busca> implements IBuscaDAO {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	protected static Log LOG = LogFactory.getLog(BuscaDAO.class);
	
	
	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractDAO#getLog()
	 */
	@Override
	public Log getLog() {
		return LOG;
	}

}
