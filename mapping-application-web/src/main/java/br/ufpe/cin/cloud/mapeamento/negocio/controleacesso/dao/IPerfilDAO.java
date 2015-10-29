/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.dao;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IDao;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Perfil;

/**
 * Representa a definição dos serviços da camada de acesso à dados da entidade
 * {@link Perfil} .
 * 
 * @author helaine.lins
 * @created 15/04/2014 - 15:51:06
 */
public interface IPerfilDAO extends IDao<Perfil> {

	/**
	 * Obtém o perfil de usuários do sistema.
	 * 
	 * @return a instância do {@link Perfil} do {@link UsuarioBean}.
	 */
	Perfil obterPerfilUsuario();

}
