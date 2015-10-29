/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BaseDeDadosBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.BaseDeDados;

/**
 * Representa a definição da camada de negócios da entidade {@link BaseDeDados}.
 * 
 * @author helaine.lins
 * @created 19/08/2014 - 10:47:46
 */
public interface IBaseDeDadosServico extends IServico<BaseDeDados, BaseDeDadosBean>{

	/**
	 * Recupera uma base de dados pelo nome.
	 * 
	 * @param nome O nome da base de dados.
	 * @return Uma instância da {@link BaseDeDadosBean} ou <code>null</code> caso não encontre resultados.
	 */
	BaseDeDadosBean buscar(String nome);
	
}
