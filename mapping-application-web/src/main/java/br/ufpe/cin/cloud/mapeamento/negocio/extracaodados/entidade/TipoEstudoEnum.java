/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade;

import br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.IEnumeracaoPersistente;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.TipoEstudoEnumBean;


/**
 * Representa as opções de escolha do atributo sexo de um {@link UsuarioBean}.
 * 
 * @author helaine.lins
 * @created 15/04/2014 - 18:30:25
 */
public enum TipoEstudoEnum implements IEnumeracaoPersistente {

    REVISAO_SISTEMATICA("R", "Revisão Sistemática"),
    MAPEAMENTO_SISTEMATICO("M", "Mapeamento Sistemático");
    
    /**
     * Retorna o nome completo da enumeração para persistência no hibernate.
     */
    public static final String USER_TYPE_ENUMERACAO_CLASS = "br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.TipoEstudoEnum";
    
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
    private TipoEstudoEnum(String codigo, String descricao) {
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
     * @return Uma instância da enumeração {@link TipoEstudoEnumBean} correspondente ou <code>null</code> caso não encontre.
     */
    public TipoEstudoEnumBean getBean() {
        TipoEstudoEnumBean enumSexo = null;
        
        if (REVISAO_SISTEMATICA.getCodigo().equalsIgnoreCase(this.getCodigo())) {
            enumSexo = TipoEstudoEnumBean.REVISAO_SISTEMATICA;
        } else if (MAPEAMENTO_SISTEMATICO.getCodigo().equalsIgnoreCase(this.getCodigo())) {
            enumSexo = TipoEstudoEnumBean.MAPEAMENTO_SISTEMATICO;
        }
        
        return enumSexo;
    }
    
}
