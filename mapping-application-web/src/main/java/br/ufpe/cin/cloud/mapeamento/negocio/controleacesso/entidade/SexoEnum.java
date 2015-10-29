/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade;

import br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.IEnumeracaoPersistente;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.SexoEnumBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;


/**
 * Representa as opções de escolha do atributo sexo de um {@link UsuarioBean}.
 * 
 * @author helaine.lins
 * @created 15/04/2014 - 18:30:25
 */
public enum SexoEnum implements IEnumeracaoPersistente {

    /**
     * Representa o sexo do tipo feminino.
     */
    FEMININO("F", "Feminino"),
    
    /**
     * Representa o sexo do tipo masculino.
     */
    MASCULINO("M", "Masculino");
    
    /**
     * Retorna o nome completo da enumeração para persistência no hibernate.
     */
    public static final String USER_TYPE_ENUMERACAO_CLASS = "br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.SexoEnum";
    
    /**
     * Representa o código da enumeração.
     */
    private String codigo;
    
    /**
     * Representa a descrição da enumeração. 
     */
    private String descricao;
    
    /**
     * Cria uma nova instância da enumeração inicializando o seu código e descrição correspondente.
     * 
     * @param codigo A instância que representa o código da enumeração.
     * @param descricao A instância que representa a descrição da enumeração.
     */
    private SexoEnum(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    /**
     * {@inheritDoc}.
     *
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.IEnumeracaoPersistente#getCodigo()
     */
    @Override
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * {@inheritDoc}.
     *
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.IEnumeracaoPersistente#getDescricao()
     */
    @Override
    public String getDescricao() {
        return this.descricao;
    }
    
    /**
     * Transforma o bean em enumeração correspondente.
     * 
     * @return Uma instância da enumeração {@link SexoEnumBean} correspondente ou <code>null</code> caso não encontre.
     */
    public SexoEnumBean getBean() {
        SexoEnumBean enumSexo = null;
        
        if (FEMININO.getCodigo().equalsIgnoreCase(this.getCodigo())) {
            enumSexo = SexoEnumBean.FEMININO;
        } else if (MASCULINO.getCodigo().equalsIgnoreCase(this.getCodigo())) {
            enumSexo = SexoEnumBean.MASCULINO;
        }
        
        return enumSexo;
    }
    
}
