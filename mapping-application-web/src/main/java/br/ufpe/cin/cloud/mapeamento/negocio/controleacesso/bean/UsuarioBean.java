/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Perfil;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;

/**
 * Representa o encapsulamento dos dados de um usuário no sistema.
 * 
 * @author helaine.lins
 * @created 08/04/2014 - 23:28:14
 */
public class UsuarioBean extends BaseBean implements UserDetails {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -8248029444494645886L;

	/**
	 * Representa o identificador do usuário.
	 */
	private Long id;

	/**
	 * Representa o login do usuário no sistema.
	 */
	private String login;

	/**
	 * Representa a senha de autenticação do usuário no sistema.
	 */
	private String senha;

	/**
	 * Representa o nome do usuário cadastrado no sistema.
	 */
	private String nome;

	/**
	 * Representa a nacionalidade do usuário.
	 */
	private String nacionalidade;

	/**
	 * Representa o email do usuário.
	 */
	private String email;

	/**
	 * Representa o email do usuário.
	 */
	private String emailConfirmacao;

	/**
	 * Identifica se o usuário inseriu o token recebido na conclusão do
	 * cadastro.
	 */
	private Boolean emailConfirmado;

	/**
	 * Representa o sexo do usuário.
	 */
	private SexoEnumBean sexo;

	/**
	 * Indica se o usuário está ativo no sistema.
	 */
	private Boolean ativo;

	/**
	 * Representa o código de ativação enviado por email pelo sistema.
	 */
	private String codigoAtivacaoEmail;

	/**
	 * Representa a lista de perfis do usuário no sistema.
	 */
	private List<PerfilBean> perfis;

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
	 * @return Uma instância de {@link List<PerfilBean>} contendo o valor do
	 *         atributo perfis.
	 */
	public List<PerfilBean> getPerfis() {
		return this.perfis;
	}

	/**
	 * Atualiza a instância de perfis com o valor de perfis.
	 * 
	 * @param perfis
	 *            Uma instância de List<PerfilBean> contendo o valor a ser
	 *            atualizado.
	 */
	public void setPerfis(List<PerfilBean> perfis) {
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
	 * @return Uma instância de {@link SexoEnumBean} contendo o valor do
	 *         atributo sexo.
	 */
	public SexoEnumBean getSexo() {
		return this.sexo;
	}

	/**
	 * Atualiza a instância de sexo com o valor de sexo.
	 * 
	 * @param sexo
	 *            Uma instância de SexoEnumBean contendo o valor a ser
	 *            atualizado.
	 */
	public void setSexo(SexoEnumBean sexo) {
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
	 * {@inheritDoc}.
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> lista = new ArrayList<GrantedAuthority>();

		if (this.perfis != null) {
			for (PerfilBean perfil : getPerfis()) {
				lista.add(new SimpleGrantedAuthority(perfil.getAuthority()));
			}
		}

		return lista;

	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return this.senha;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return this.login;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return this.ativo;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return this.ativo;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return this.ativo;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return this.ativo;
	}

	/**
	 * Obtém o valor do atributo emailConfirmacao.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         emailConfirmacao.
	 */
	public String getEmailConfirmacao() {
		return this.emailConfirmacao;
	}

	/**
	 * Atualiza a instância de emailConfirmacao com o valor de emailConfirmacao.
	 * 
	 * @param emailConfirmacao
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setEmailConfirmacao(String emailConfirmacao) {
		this.emailConfirmacao = emailConfirmacao;
	}

	/**
	 * {@inheritdoc}
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.infra.bean.BaseBean#getBean()
	 */
	@SuppressWarnings("unchecked")
	public Usuario getEntidade() {
		Usuario entidade = new Usuario();

		entidade.setId(this.getId());
		entidade.setDataInclusao(this.getDataInclusao());
		entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

		entidade.setAtivo(this.getAtivo());
		entidade.setCodigoAtivacaoEmail(this.getCodigoAtivacaoEmail());
		entidade.setLogin(this.getLogin());
		entidade.setEmail(this.getEmail());
		entidade.setEmailConfirmado(this.getEmailConfirmado());

		entidade.setNacionalidade(this.getNacionalidade());
		entidade.setNome(this.getNome());
		entidade.setSenha(this.getSenha());

		if (this.perfis != null) {
			List<Perfil> entPerfis = new ArrayList<Perfil>();

			for (PerfilBean perfil : this.perfis) {
				if (perfil != null) {
					entPerfis.add(perfil.getEntidade());
				}
			}

			entidade.setPerfis(entPerfis);
		}

		if (this.getSexo() != null) {
			entidade.setSexo(this.getSexo().getEnumeracao());
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
		} else if (obj instanceof UsuarioBean) {
			UsuarioBean objeto = (UsuarioBean) obj;
			iguais = new EqualsBuilder().append(login, objeto.login).isEquals();
		}

		return iguais;
	}

	/**
	 * Identifica se o usuário tem o perfil de administrador do sistema.
	 * 
	 * @return <code>true</code> se o usuário possuir o perfil de administrador,
	 *         caso contrário retorna <code>false</code>.
	 */
	public boolean isAdministrador() {
		Boolean admin = false;
		for (PerfilBean perfil : getPerfis()) {
			if (perfil.getAuthority().equalsIgnoreCase("ROLE_ADMINISTRADOR")) {
				admin = true;
				break;
			}
		}
		return admin;
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
