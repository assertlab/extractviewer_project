/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Representa os dados de um autor.
 * 
 * @author helaine.lins
 * @created 28/04/2015 - 19:29:48
 */
public class AutorBean implements Serializable, Comparable<AutorBean> {

    private static final long serialVersionUID = 6770062339208599085L;

    private Integer quantidade;
    private String autor;

    /**
     * Obtém o valor do atributo quantidade.
     * 
     * @return Uma instância de {@link Integer} contendo o valor do atributo quantidade.
     */
    public Integer getQuantidade() {
        return this.quantidade;
    }

    /**
     * Atualiza a instância de quantidade com o valor de quantidade.
     * 
     * @param quantidade Uma instância de Integer contendo o valor a ser atualizado.
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Obtém o valor do atributo autor.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo autor.
     */
    public String getAutor() {
        return this.autor;
    }

    /**
     * Atualiza a instância de autor com o valor de autor.
     * 
     * @param autor Uma instância de String contendo o valor a ser atualizado.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(AutorBean objeto) {
        int comp = -1;

        if (this.autor == null && objeto.getAutor() != null) {
            comp = 1;
        } else if (this.autor != null && objeto.getAutor() != null) {
            comp = this.autor.compareTo(objeto.getAutor());
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
        } else if (obj instanceof AutorBean) {
            AutorBean objeto = (AutorBean) obj;

            if (this.autor == null && objeto.getAutor() == null) {
                iguais = true;
            } else if (this.autor != null && objeto.getAutor() != null) {
                iguais = new EqualsBuilder().append(autor, objeto.autor).isEquals();
            }
        }

        return iguais;
    }
}
