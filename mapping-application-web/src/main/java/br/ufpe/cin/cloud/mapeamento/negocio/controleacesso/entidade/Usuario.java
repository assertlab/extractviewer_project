/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.PerfilBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;

/**
 * Representa o encapsulamento dos dados de um usuário no sistema.
 * 
 * @author helaine.lins
 * @created 08/04/2014 - 23:28:14
 */
@Entity
@Table(name = "usuario", schema = "acesso")
@SequenceGenerator(name = "seq_usuario", sequenceName = "acesso.seq_usuario")
public class Usuario extends Entidade {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -8248029444494669796L;

	/**
	 * Representa o identificador do usuário.
	 */
	@Id
	@GeneratedValue(generator = "seq_usuario")
	@Column(name = "id_usuario")
	private Long id;

	/**
	 * Representa o login do usuário no sistema.
	 */
	@NotBlank
	@Length(min = 2, max = 20)
	@Column(name = "login", length = 20, nullable = false)
	private String login;

	/**
	 * Representa a senha de autenticação do usuário no sistema.
	 */
	@NotBlank
	@Length(min = 6)
	@Column(name = "senha", nullable = false)
	private String senha;

	/**
	 * Representa o nome do usuário cadastrado no sistema.
	 */
	@NotBlank
	@Length(min = 2, max = 150)
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;

	/**
	 * Representa a nacionalidade do usuário.
	 */
	@NotBlank
	@Length(min = 1, max = 50)
	@Column(name = "nacionalidade", length = 50, nullable = false)
	private String nacionalidade;

	/**
	 * Representa o email do usuário.
	 */
	@NotBlank
	@Email
	@Column(name = "email", length = 80, nullable = false)
	private String email;

	/**
	 * Identifica se o usuário inseriu o token recebido na conclusão do
	 * cadastro.
	 */
	@NotNull
	@Column(name = "email_confirmado", nullable = false)
	private Boolean emailConfirmado;

	/**
	 * Representa o sexo do usuário.
	 */
	@NotNull
	@Type(type = USER_TYPE_ENUMERACAO, parameters = { @Parameter(name = USER_TYPE_ENUMERACAO_PARAMETRO_CLASS, value = SexoEnum.USER_TYPE_ENUMERACAO_CLASS) })
	@Column(name = "sexo", nullable = false)
	private SexoEnum sexo;

	/**
	 * Indica se o usuário está ativo no sistema.
	 */
	@NotNull
	@Column(name = "ativo", nullable = false)
	private Boolean ativo;

	/**
	 * Representa o código de ativação enviado por email pelo sistema.
	 */
	@Length(max = 150)
	@Column(name = "codigo_ativacao_email", length = 150)
	private String codigoAtivacaoEmail;

	/**
	 * Representa a lista de perfis do usuário no sistema.
	 */
	@NotNull
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Perfil.class)
	@JoinTable(name = "acesso.usuario_perfil", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	private List<Perfil> perfis;

	/**
	 * Cria uma nova instância de usuário no sistema.
	 */
	public Usuario() {
		super();
	}
	
	/**
	 * Cria uma nova instância do usuário inicializando o identificador do sistema.
	 * 
	 * @param id A instância do identificador.
	 */
	public Usuario (Long id) {
		this();
		this.id = id;
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
	 * Obtém o valor do atributo senha.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         senha.
	 */
	public String getSenha() {
		return this.senha;
	}

	/**
	 * Atualiza a instância de senha com o valor de senha.
	 * 
	 * @param senha
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Obtém o valor do atributo perfis.
	 * 
	 * @return Uma instância de {@link List<Perfil>} contendo o valor do
	 *         atributo perfis.
	 */
	public List<Perfil> getPerfis() {
		return this.perfis;
	}

	/**
	 * Atualiza a instância de perfis com o valor de perfis.
	 * 
	 * @param perfis
	 *            Uma instância de List<Perfil> contendo o valor a ser
	 *            atualizado.
	 */
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	/**
	 * Obtém o valor do atributo ativo.
	 * 
	 * @return Uma instância de {@link Boolean} contendo o valor do atributo
	 *         ativo.
	 */
	public Boolean getAtivo() {
		return this.ativo;
	}

	/**
	 * Atualiza a instância de ativo com o valor de ativo.
	 * 
	 * @param ativo
	 *            Uma instância de Boolean contendo o valor a ser atualizado.
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
	 * Obtém o valor do atributo nome.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         nome.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Atualiza a instância de nome com o valor de nome.
	 * 
	 * @param nome
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Obtém o valor do atributo nacionalidade.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         nacionalidade.
	 */
	public String getNacionalidade() {
		return this.nacionalidade;
	}

	/**
	 * Atualiza a instância de nacionalidade com o valor de nacionalidade.
	 * 
	 * @param nacionalidade
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	/**
	 * Obtém o valor do atributo email.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         email.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Atualiza a instância de email com o valor de email.
	 * 
	 * @param email
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtém o valor do atributo emailConfirmado.
	 * 
	 * @return Uma instância de {@link Boolean} contendo o valor do atributo
	 *         emailConfirmado.
	 */
	public Boolean getEmailConfirmado() {
		return this.emailConfirmado;
	}

	/**
	 * Atualiza a instância de emailConfirmado com o valor de emailConfirmado.
	 * 
	 * @param emailConfirmado
	 *            Uma instância de Boolean contendo o valor a ser atualizado.
	 */
	public void setEmailConfirmado(Boolean emailConfirmado) {
		this.emailConfirmado = emailConfirmado;
	}

	/**
	 * Obtém o valor do atributo sexo.
	 * 
	 * @return Uma instância de {@link SexoEnum} contendo o valor do atributo
	 *         sexo.
	 */
	public SexoEnum getSexo() {
		return this.sexo;
	}

	/**
	 * Atualiza a instância de sexo com o valor de sexo.
	 * 
	 * @param sexo
	 *            Uma instância de SexoEnum contendo o valor a ser atualizado.
	 */
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	/**
	 * Obtém o valor do atributo codigoAtivacaoEmail.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         codigoAtivacaoEmail.
	 */
	public String getCodigoAtivacaoEmail() {
		return this.codigoAtivacaoEmail;
	}

	/**
	 * Atualiza a instância de codigoAtivacaoEmail com o valor de
	 * codigoAtivacaoEmail.
	 * 
	 * @param codigoAtivacaoEmail
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setCodigoAtivacaoEmail(String codigoAtivacaoEmail) {
		this.codigoAtivacaoEmail = codigoAtivacaoEmail;
	}

	/**
	 * Obtém o valor do atributo login.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         login.
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Atualiza a instância de login com o valor de login.
	 * 
	 * @param login
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * {@inheritdoc}
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.infra.bean.BaseBean#getBean()
	 */
	@SuppressWarnings("unchecked")
	public UsuarioBean getBean() {
		UsuarioBean bean = new UsuarioBean();

		bean.setId(this.getId());
		bean.setDataInclusao(this.getDataInclusao());
		bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

		bean.setAtivo(this.getAtivo());
		bean.setCodigoAtivacaoEmail(this.getCodigoAtivacaoEmail());
		bean.setLogin(this.getLogin());
		bean.setEmail(this.getEmail());
		bean.setEmailConfirmado(this.getEmailConfirmado());

		bean.setNacionalidade(this.getNacionalidade());
		bean.setNome(this.getNome());
		bean.setSenha(this.getSenha());

		if (this.perfis != null) {
			List<PerfilBean> entPerfis = new ArrayList<PerfilBean>();

			for (Perfil perfil : this.perfis) {
				if (perfil != null) {
					entPerfis.add(perfil.getBean());
				}
			}

			bean.setPerfis(entPerfis);
		}

		if (this.getSexo() != null) {
			bean.setSexo(this.getSexo().getBean());
		}

		return bean;
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
		} else if (obj instanceof Usuario) {
			Usuario objeto = (Usuario) obj;
			
			if (this.getId() != null && objeto.getId() != null) {
				iguais = new EqualsBuilder().append(id, objeto.id).isEquals();
			} else {
				iguais = new EqualsBuilder().append(login, objeto.login).isEquals();
			}
			
		}

		return iguais;
	}

	public boolean isAdministrador() {
		for (Perfil perfil : getPerfis()) {
			if (perfil.getAuthority().equalsIgnoreCase("ROLE_ADMINISTRADOR")) {
				return true;
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.login).toHashCode();
	}

	/**
	 * {@inheritdoc}
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(this.id);
	}
}
