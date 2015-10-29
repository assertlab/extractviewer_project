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
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.RevisorBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;

/**
 * Representa a definição da camada de negócios da entidade {@link Revisor}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:21:13
 */
public interface IRevisorServico extends IServico<Revisor, RevisorBean>{

	/**
	 * Recupera o revisor a partir dos dados de usuário.
	 * 
	 * @param idUsuario O login do Usuário.
	 * @return A instância do {@link Revisor} encontrado ou <code>null</code> caso o mesmo não seja localizado.
	 */
	RevisorBean recuperarRevisor(String login);
	
}
