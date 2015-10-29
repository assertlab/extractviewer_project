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
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.TipoAnaliseEstudoEnumBean;


/**
 * Representa as opções de tipo de análise de seleção de estudo.
 * 
 * @author helaine.lins
 * @created 15/04/2014 - 18:30:25
 */
public enum TipoAnaliseEstudoEnum implements IEnumeracaoPersistente {

    TITULO_RESUMO("TR", "Título e Resumo"),
    CRITERIOS_INCLUSAO_EXCLUSAO("CIE", "Critérios de Inclusão e Exclusão");
    
    /**
     * Retorna o nome completo da enumeração para persistência no hibernate.
     */
    public static final String USER_TYPE_ENUMERACAO_CLASS = "br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.TipoAnaliseEstudoEnum";
    
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
    private TipoAnaliseEstudoEnum(String codigo, String descricao) {
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
     * @return Uma instância da enumeração {@link CRITERIOS_INCLUSAO_EXCLUSAO} correspondente ou <code>null</code> caso não encontre.
     */
    public TipoAnaliseEstudoEnumBean getBean() {
        TipoAnaliseEstudoEnumBean enumSexo = null;
        
        if (TITULO_RESUMO.getCodigo().equalsIgnoreCase(this.getCodigo())) {
            enumSexo = TipoAnaliseEstudoEnumBean.TITULO_RESUMO;
        } else if (CRITERIOS_INCLUSAO_EXCLUSAO.getCodigo().equalsIgnoreCase(this.getCodigo())) {
            enumSexo = TipoAnaliseEstudoEnumBean.CRITERIOS_INCLUSAO_EXCLUSAO;
        }
        
        return enumSexo;
    }
    
}
