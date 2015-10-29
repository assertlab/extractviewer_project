/**
 * SiVest - Sistema de Inscrição do Vestibular da UPE
 * 
 * Copyright (c) Universidade Federal de Pernambuco.
 * 
 * Este software é confidencial e propriedade da UPE. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do UPE. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.SexoEnum;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.TipoEstudoEnum;
import br.ufpe.cin.cloud.mapeamento.web.base.camadas.IEnumBean;

/**
 * Representa o bean da enumeração de sexo no sistema.
 * 
 * @author helaine.lins
 * @created 17/04/2014 - 18:30:25
 */
public enum TipoEstudoEnumBean implements IEnumBean {

	MAPEAMENTO_SISTEMATICO("M", "enum_tipo_estudo_mapeamento"),

	REVISAO_SISTEMATICA("R", "enum_tipo_estudo_revisao");

	/**
	 * Representa o código da enumeração.
	 */
	private String codigo;

	/**
	 * Representa a descrição da enumeração.
	 */
	private String descricao;

	/**
	 * Cria uma nova instância da enumeração inicializando o seu código e
	 * descrição correspondente.
	 * 
	 * @param codigo
	 *            A instância que representa o código da enumeração.
	 * @param descricao
	 *            A instância que representa a descrição da enumeração.
	 */
	private TipoEstudoEnumBean(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.upe.sivest.negocio.base.hibernate.IEnumeracaoPersistente#getCodigo()
	 */
	@Override
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.upe.sivest.negocio.base.hibernate.IEnumeracaoPersistente#getDescricao()
	 */
	@Override
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * {@inheritdoc}
	 * 
	 * @see br.upe.sivest.web.base.camadas.IEnumBean#getValores()
	 */
	@Override
	public IEnumBean[] getValores() {
		return values();
	}

	/**
	 * Transforma a instância de bean na entidade correspondete.
	 * 
	 * @return Uma instância de {@link SexoEnum} ou <code>null</code>
	 *         caso não exista valor correspondente.
	 */
	public TipoEstudoEnum getEnumeracao() {
	    TipoEstudoEnum tipoEnum = null;

		if (MAPEAMENTO_SISTEMATICO.getCodigo().equalsIgnoreCase(this.getCodigo())) {
			tipoEnum = TipoEstudoEnum.MAPEAMENTO_SISTEMATICO;
		} else if (REVISAO_SISTEMATICA.getCodigo().equalsIgnoreCase(this.getCodigo())) {
			tipoEnum = TipoEstudoEnum.REVISAO_SISTEMATICA;
		}

		return tipoEnum;
	}
}
