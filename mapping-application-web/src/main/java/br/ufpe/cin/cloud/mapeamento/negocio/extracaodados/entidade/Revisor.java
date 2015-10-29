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

import org.apache.commons.lang.builder.EqualsBuilder;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.RevisorBean;

/**
 * Representa os dados de um Revisor de {@link Estudo} no sistema.
 * 
 * @author helaine.lins
 * @created 27/08/2014 - 18:56:16
 */
@Entity
@Table(name = "revisor", schema = "mapeamento")
@SequenceGenerator(name = "seq_revisor", sequenceName = "mapeamento.seq_revisor")
public class Revisor extends Entidade {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -6812335179616894975L;

	@Id
	@GeneratedValue(generator = "seq_revisor")
	@Column(name = "id_revisor")
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = true)
	private Usuario usuario;
	
	
	/**
	 * Cria uma nova instância da classe.
	 */
	public Revisor() {
		super();
	}
	
	/**
	 * Cria uma nova instância da classe inicializando o valor do identificador.
	 * 
	 * @param id O valor do identificador.
	 */
	public Revisor(Long id) {
		this();
		this.id = id;
	}
	
	/**
	 * Cria uma nova instância de revisor inicializando o usuário.
	 * 
	 * @param usuario A instância de usuário a ser atribuída.
	 */
	public Revisor (Usuario usuario) {
		this();
		this.usuario = usuario;
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
	 * @param id Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Obtém o valor do atributo usuario.
	 * 
	 * @return Uma instância de {@link Usuario} contendo o valor do atributo usuario.
	 */
	public Usuario getUsuario() {
		return this.usuario;
	}
	/**
	 * Atualiza a instância de usuario com o valor de usuario.
	 *
	 * @param usuario Uma instância de Usuario contendo o valor a ser atualizado.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		} else if (obj instanceof Revisor) {
			Revisor objeto = (Revisor) obj;
			
			if (this.getId() != null && objeto.getId() != null) {
				iguais = new EqualsBuilder().append(id, objeto.id)
						.isEquals();
			} else {
				iguais = new EqualsBuilder().append(usuario, objeto.usuario)
						.isEquals();
			}
		}

		return iguais;
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
	@SuppressWarnings("unchecked")
	@Override
	public RevisorBean getBean() {
		RevisorBean bean = new RevisorBean();
		
		bean.setId(this.id);
		bean.setDataInclusao(this.getDataInclusao());
		bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());
		
		if (this.usuario != null) {
			UsuarioBean usuario = new UsuarioBean();
			usuario.setId(this.usuario.getId());
			usuario.setNome(this.usuario.getNome());
			bean.setUsuario(usuario);
		}
		
		return bean;
	}
	
	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#toString()
	 */
	@Override
	public String toString() {
		return "Revisor: " + this.getId() + "-" + this.getUsuario().getNome() + "(" + this.getUsuario().getLogin() + ")";
	}
}
