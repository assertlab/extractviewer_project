/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.HistoricoEstudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;

/**
 * Representa o encapsulamento dos dados de histórico de alteração de estudo.
 * 
 * @author helaine.lins
 * @created 21/10/2014 - 15:31:04
 */
public class HistoricoEstudoBean extends BaseBean {

	private static final long serialVersionUID = 3338888808667153334L;

	private Long id;
	private RevisorBean revisor;
	private EstudoBean estudo;
	private String dadosAnteriores;
	private String dadosAtuais;
	private String diferenca;

	/**
	 * Obtém o valor do atributo id.
	 * 
	 * @return Uma instância de {@link Long} contendo o valor do atributo id.
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Atualiza a instância de id com o valor de id.
	 * 
	 * @param id
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtém o valor do atributo revisor.
	 * 
	 * @return Uma instância de {@link RevisorBean} contendo o valor do atributo
	 *         revisor.
	 */
	public RevisorBean getRevisor() {
		return this.revisor;
	}

	/**
	 * Atualiza a instância de revisor com o valor de revisor.
	 * 
	 * @param revisor
	 *            Uma instância de RevisorBean contendo o valor a ser
	 *            atualizado.
	 */
	public void setRevisor(RevisorBean revisor) {
		this.revisor = revisor;
	}

	/**
	 * Obtém o valor do atributo estudo.
	 * 
	 * @return Uma instância de {@link EstudoBean} contendo o valor do atributo
	 *         estudo.
	 */
	public EstudoBean getEstudo() {
		return this.estudo;
	}

	/**
	 * Atualiza a instância de estudo com o valor de estudo.
	 * 
	 * @param estudo
	 *            Uma instância de EstudoBean contendo o valor a ser atualizado.
	 */
	public void setEstudo(EstudoBean estudo) {
		this.estudo = estudo;
	}

	/**
	 * Obtém o valor do atributo dadosAnteriores.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         dadosAnteriores.
	 */
	public String getDadosAnteriores() {
		return this.dadosAnteriores;
	}

	/**
	 * Atualiza a instância de dadosAnteriores com o valor de dadosAnteriores.
	 * 
	 * @param dadosAnteriores
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setDadosAnteriores(String dadosAnteriores) {
		this.dadosAnteriores = dadosAnteriores;
	}

	/**
	 * Obtém o valor do atributo dadosAtuais.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         dadosAtuais.
	 */
	public String getDadosAtuais() {
		return this.dadosAtuais;
	}

	/**
	 * Atualiza a instância de dadosAtuais com o valor de dadosAtuais.
	 * 
	 * @param dadosAtuais
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setDadosAtuais(String dadosAtuais) {
		this.dadosAtuais = dadosAtuais;
	}

	/**
	 * Obtém o valor do atributo diferenca.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         diferenca.
	 */
	public String getDiferenca() {
		return this.diferenca;
	}

	/**
	 * Atualiza a instância de diferenca com o valor de diferenca.
	 * 
	 * @param diferenca
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setDiferenca(String diferenca) {
		this.diferenca = diferenca;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HistoricoEstudo getEntidade() {
		HistoricoEstudo entidade = new HistoricoEstudo();

		entidade.setId(this.getId());
		entidade.setDataInclusao(this.getDataInclusao());
		entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

		entidade.setDadosAnteriores(this.getDadosAnteriores());
		entidade.setDadosAtuais(this.getDadosAtuais());
		entidade.setDiferenca(this.getDiferenca());

		Estudo estudo = new Estudo();
		estudo.setId(this.estudo.getId());
		entidade.setEstudo(estudo);

		Revisor revisor = new Revisor();
		revisor.setId(this.revisor.getId());
		entidade.setRevisor(revisor);

		return entidade;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getBeanID()
	 */
	@Override
	public Long getBeanID() {
		return this.id;
	}

}
