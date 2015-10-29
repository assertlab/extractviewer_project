/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean;

import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.springframework.security.core.GrantedAuthority;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Perfil;

/**
 * Representa o bean de um {@link Perfil} de acesso ao sistema.
 * 
 * @author helaine.lins
 * @created 08/04/2014 - 23:37:30
 */
public class PerfilBean extends BaseBean implements GrantedAuthority, Comparable<PerfilBean> {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 6359320253846011219L;

    /**
     * Representa o identificador do perfil de acesso.
     */
    private Long id;

    /**
     * Representa a descrição do pergil de acesso.
     */
    private String descricao;

    /**
     * Cria uma nova instância da entidade.
     */
    public PerfilBean() {

    }

    /**
     * Cria uma nova instância da entidade inicializando o seu identificador.
     * 
     * @param id O identificador.
     */
    public PerfilBean(Long id) {
        this();
        this.id = id;
    }

    /**
     * Cria uma nova instância da entidade inicializando a sua descricao.
     * 
     * @param descricao A descricao.
     */
    public PerfilBean(String descricao) {
        this();
        this.descricao = descricao;
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
    public int compareTo(PerfilBean objeto) {
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
    public boolean equals(Object obj) {
        boolean iguais = false;

        if (obj == this) {
            iguais = true;
        } else if (obj instanceof PerfilBean) {
            PerfilBean objeto = (PerfilBean) obj;
            iguais = new EqualsBuilder().append(descricao, objeto.descricao).isEquals();
        }

        return iguais;
    }

    /** 
     * {@inheritdoc}
     *
     * @see br.ufpe.cin.cloud.mapeamento.infra.bean.BaseBean#getEntidade()
     */
    @Override
    @SuppressWarnings("unchecked")
    public Perfil getEntidade() {
        Perfil entidade = new Perfil();
        
        entidade.setId(this.getId());
        entidade.setDataInclusao(this.getDataInclusao());
        entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());
        
        entidade.setDescricao(this.getDescricao());
        
        return entidade;
    }
}
