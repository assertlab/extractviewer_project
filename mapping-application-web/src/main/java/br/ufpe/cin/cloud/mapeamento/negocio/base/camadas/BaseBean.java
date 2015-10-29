/**
 * SiVest - Sistema de Inscrição do Vestibular da UPE
 * 
 * Copyright (c) Universidade Federal de Pernambuco.
 * 
 * Este software é confidencial e propriedade da UPE. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do UPE. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.camadas;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDateTime;

/**
 * Representa a abstração das classes do tipo Bean no sistema.
 * 
 * @author Helaine Lins
 */
public abstract class BaseBean implements Serializable {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	protected final Log LOG = LogFactory.getLog(BaseBean.class);

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 5074265793197342348L;

	/**
	 * Guarda a data da inclusão da entidade.
	 */
	private LocalDateTime dataInclusao;

	/**
	 * Guarda a data da última alteração da entidade.
	 */
	private LocalDateTime dataUltimaAlteracao;

	/**
	 * Realiza a conversão de um bean em sua entidade correspondente.
	 * 
	 * @return Uma instância de {@link Entidade} contendo os dados copiados,
	 *         caso contrário retorna <code>null</code>.
	 */
	public abstract <E extends Entidade> E getEntidade();

	/**
     * Recupera o identificador do bean.
     */
    public abstract Long getBeanID();
	
	/**
	 * Obtém o valor do atributo dataInclusao.
	 * 
	 * @return Uma instância de {@link LocalDateTime} contendo o valor do
	 *         atributo dataInclusao.
	 */
	public LocalDateTime getDataInclusao() {
		return this.dataInclusao;
	}

	/**
	 * Atualiza a instância de dataInclusao com o valor de dataInclusao.
	 * 
	 * @param dataInclusao
	 *            Uma instância de LocalDateTime contendo o valor a ser
	 *            atualizado.
	 */
	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	/**
	 * Obtém o valor do atributo dataUltimaAlteracao.
	 * 
	 * @return Uma instância de {@link LocalDateTime} contendo o valor do
	 *         atributo dataUltimaAlteracao.
	 */
	public LocalDateTime getDataUltimaAlteracao() {
		return this.dataUltimaAlteracao;
	}

	/**
	 * Atualiza a instância de dataUltimaAlteracao com o valor de
	 * dataUltimaAlteracao.
	 * 
	 * @param dataUltimaAlteracao
	 *            Uma instância de LocalDateTime contendo o valor a ser
	 *            atualizado.
	 */
	public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

}
