/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import org.apache.commons.lang.builder.EqualsBuilder;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;

/**
 * Representa o encapsulamento de dados de um Revisor no sistema.
 * 
 * @author helaine.lins
 * @created 27/08/2014 - 19:01:00
 */
public class RevisorBean extends BaseBean {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 3775872160661175278L;

	private Long id;
	private UsuarioBean usuario;
	
	/**
     * Cria uma nova instância da classe.
     */
    public RevisorBean() {
        super();
    }
    
    /**
     * Cria uma nova instância da classe inicializando seu identificador.
     * 
     * @param id O identificador da classe.
     */
    public RevisorBean(Long id) {
        this();
        this.id = id;
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
	 * Obtém o valor do atributo usuario.
	 * 
	 * @return Uma instância de {@link UsuarioBean} contendo o valor do atributo
	 *         usuario.
	 */
	public UsuarioBean getUsuario() {
		return this.usuario;
	}

	/**
	 * Atualiza a instância de usuario com o valor de usuario.
	 * 
	 * @param usuario
	 *            Uma instância de UsuarioBean contendo o valor a ser
	 *            atualizado.
	 */
	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Revisor getEntidade() {
		Revisor entidade = new Revisor();

		entidade.setId(this.id);
		entidade.setDataInclusao(this.getDataInclusao());
		entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());
		
		if (this.usuario != null) {
			Usuario usuario = new Usuario();
			usuario.setId(this.usuario.getId());
			entidade.setUsuario(usuario);
		}
		
		return entidade;
	}

	/**
	 * {@inheritDoc}.
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean iguais = false;

		if (obj == this) {
			iguais = true;
		} else if (obj instanceof RevisorBean) {
			RevisorBean objeto = (RevisorBean) obj;
			iguais = new EqualsBuilder().append(usuario, objeto.usuario)
					.isEquals();
		}

		return iguais;
	}
}
