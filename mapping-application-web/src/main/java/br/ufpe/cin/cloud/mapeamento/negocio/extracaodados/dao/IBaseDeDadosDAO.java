/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.BaseDeDados;

/**
 * Representa a definição da camada de acesso à dados da entidade {@link BaseDeDados}
 * 
 * @author helaine.lins
 * @created 19/08/2014 - 10:44:54
 */
public interface IBaseDeDadosDAO extends IDao<BaseDeDados>{

	/**
	 * Recupera uma base de dados pelo nome.
	 * 
	 * @param nome O nome da base de dados.
	 * @return Uma instância da {@link BaseDeDados} ou <code>null</code> caso não encontre resultados.
	 */
	BaseDeDados buscar(String nome);
}
