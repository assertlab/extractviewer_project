/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.HistoricoEstudoBean;

/**
 * Representa os dados de histórico de uma análise.
 * 
 * @author helaine.lins
 * @created 21/10/2014 - 15:31:04
 */
@Entity
@Table(name = "historico_estudo", schema = "mapeamento")
@SequenceGenerator(name = "seq_historico_estudo", sequenceName = "mapeamento.seq_historico_estudo")
public class HistoricoEstudo extends Entidade {

	private static final long serialVersionUID = 3755468584399473841L;

	@Id
	@GeneratedValue(generator = "seq_historico_estudo")
	@Column(name = "id_historico_estudo")
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_revisor")
	private Revisor revisor;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_estudo")
	private Estudo estudo;

	@NotNull
	@Column(name = "dados_anteriores", nullable = false)
	private String dadosAnteriores;

	@NotNull
	@Column(name = "dados_atuais", nullable = false)
	private String dadosAtuais;

	@NotNull
	@Column(name = "diferenca", nullable = false)
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
	 * @return Uma instância de {@link Revisor} contendo o valor do atributo
	 *         revisor.
	 */
	public Revisor getRevisor() {
		return this.revisor;
	}

	/**
	 * Atualiza a instância de revisor com o valor de revisor.
	 * 
	 * @param revisor
	 *            Uma instância de Revisor contendo o valor a ser atualizado.
	 */
	public void setRevisor(Revisor revisor) {
		this.revisor = revisor;
	}

	/**
	 * Obtém o valor do atributo estudo.
	 * 
	 * @return Uma instância de {@link Estudo} contendo o valor do atributo
	 *         estudo.
	 */
	public Estudo getEstudo() {
		return this.estudo;
	}

	/**
	 * Atualiza a instância de estudo com o valor de estudo.
	 * 
	 * @param estudo
	 *            Uma instância de Estudo contendo o valor a ser atualizado.
	 */
	public void setEstudo(Estudo estudo) {
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
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#getEntidadeID()
	 */
	@Override
	public Long getEntidadeID() {
		return this.id;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#getBean()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public HistoricoEstudoBean getBean() {
		HistoricoEstudoBean bean = new HistoricoEstudoBean();

		bean.setId(this.getId());
		bean.setDataInclusao(this.getDataInclusao());
		bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

		bean.setEstudo(this.estudo.getBean());
		bean.setRevisor(this.revisor.getBean());

		bean.setDadosAnteriores(this.getDadosAnteriores());
		bean.setDadosAtuais(this.getDadosAtuais());
		bean.setDiferenca(this.getDiferenca());

		return bean;
	}

}
