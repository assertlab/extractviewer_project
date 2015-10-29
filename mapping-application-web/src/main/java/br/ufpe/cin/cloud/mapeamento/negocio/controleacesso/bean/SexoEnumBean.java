/**
 * SiVest - Sistema de Inscrição do Vestibular da UPE
 * 
 * Copyright (c) Universidade Federal de Pernambuco.
 * 
 * Este software é confidencial e propriedade da UPE. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do UPE. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean;

import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.SexoEnum;
import br.ufpe.cin.cloud.mapeamento.web.base.camadas.IEnumBean;

/**
 * Representa o bean da enumeração de sexo no sistema.
 * 
 * @author helaine.lins
 * @created 17/04/2014 - 18:30:25
 */
public enum SexoEnumBean implements IEnumBean {

	/**
	 * Representa o sexo do tipo feminino.
	 */
	FEMININO("F", "enum_sexo_feminino"),

	/**
	 * Representa o sexo do tipo masculino.
	 */
	MASCULINO("M", "enum_sexo_masculino");

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
	private SexoEnumBean(String codigo, String descricao) {
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
	public SexoEnum getEnumeracao() {
		SexoEnum enumTipoErr = null;

		if (FEMININO.getCodigo().equalsIgnoreCase(this.getCodigo())) {
			enumTipoErr = SexoEnum.FEMININO;
		} else if (MASCULINO.getCodigo().equalsIgnoreCase(this.getCodigo())) {
			enumTipoErr = SexoEnum.MASCULINO;
		}

		return enumTipoErr;
	}
}
