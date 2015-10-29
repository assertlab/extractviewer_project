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
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.HistoricoAnaliseEstudo;

/**
 * Representa a implementação da camada de dados da entidade
 * {@link HistoricoAnaliseEstudo}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:14:32
 */
@Repository(value = "historioAnaliseEstudoDAO")
public class HistoricoAnaliseEstudoDAO extends AbstractDAO<HistoricoAnaliseEstudo> implements
		IHistoricoAnaliseEstudoDAO {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	protected static Log LOG = LogFactory.getLog(HistoricoAnaliseEstudoDAO.class);

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
