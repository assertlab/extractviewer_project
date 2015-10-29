/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.controleacesso;

import javax.faces.bean.ViewScoped;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import br.ufpe.cin.cloud.mapeamento.web.base.camadas.AbstractMB;

/**
 * Representa a implementação do MB da página inicial do sistema.
 * 
 * @author helaine.lins
 * @created 06/06/2014 - 13:02:10
 */
@ViewScoped
@Controller("indexMB")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class IndexMB extends AbstractMB {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -5392249264415575730L;

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.web.base.camadas.AbstractMB#carregarPagina()
	 */
	@Override
	public void carregarPagina() {

	}

	/**
	 * Redirecionar o usuário para a página de autenticação do sistema.
	 * 
	 * @return A chave da navegação para a página login.xhtml
	 */
	public String redirecionarPaginaLogin() {
		return "exibirLogin";
	}

}
