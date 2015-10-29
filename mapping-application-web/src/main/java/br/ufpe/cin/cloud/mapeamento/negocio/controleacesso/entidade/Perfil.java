/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.PerfilBean;

/**
 * Representa um perfil de acesso ao sistema.
 * 
 * @author helaine.lins
 * @created 08/04/2014 - 23:37:30
 */
@Entity
@Table(name = "perfil", schema = "acesso")
@SequenceGenerator(name = "seq_perfil", sequenceName = "acesso.seq_perfil")
public class Perfil extends Entidade implements GrantedAuthority, Comparable<Perfil> {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 5763674239068928942L;

    /**
     * Representa o identificador do perfil de acesso.
     */
    @Id
    @GeneratedValue(generator = "seq_perfil")
    @Column(name = "id_perfil")
    private Long id;

    /**
     * Representa a descrição do pergil de acesso.
     */
    @Length(max = 150)
    @Column(name = "descricao", length = 150, nullable = false)
    private String descricao;

    /**
     * Cria uma nova instância da entidade.
     */
    public Perfil() {

    }

    /**
     * Cria uma nova instância da entidade inicializando o seu identificador.
     * 
     * @param id O identificador.
     */
    public Perfil(Long id) {
        this();
        this.id = id;
    }

    /**
     * Cria uma nova instância da entidade inicializando a sua descricao.
     * 
     * @param descricao A descricao.
     */
    public Perfil(String descricao) {
        this();
        this.descricao = descricao;
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
     * Obtém o valor do atributo descricao.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo descricao.
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Atualiza a instância de descricao com o valor de descricao.
     * 
     * @param descricao Uma instância de String contendo o valor a ser atualizado.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    @Override
    @Transient
    public String getAuthority() {
        return this.descricao;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Perfil objeto) {
        int comp = -1;

        if (this.descricao != null) {
            comp = this.descricao.compareToIgnoreCase(objeto.getDescricao());
        }

        return comp;
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
        } else if (obj instanceof Perfil) {
            Perfil objeto = (Perfil) obj;
            iguais = new EqualsBuilder().append(descricao, objeto.descricao).isEquals();
        }

        return iguais;
    }
    
    /**
     * Cria um bean a partir dos dados da entidade.
     * 
     * @return Uma instância de {@link PerfilBean} a partir dos dados da entidade.
     */
    @SuppressWarnings("unchecked")
	public PerfilBean getBean() {
        PerfilBean bean = new PerfilBean();
        
        bean.setId(this.getId());
        bean.setDataInclusao(this.getDataInclusao());
        bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());
        
        bean.setDescricao(this.getDescricao());
        
        return bean;
    }
}
