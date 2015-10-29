/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EstagioExecucao;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EstudoSecundario;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;

/**
 * Representa o encapsulamento de dados de um bean de estudo secundário.
 * 
 * @author helaine.lins
 * @created 27/04/2015 - 13:44:32
 */
public class EstudoSecundarioBean extends BaseBean {

    private static final long serialVersionUID = -1120720718476554864L;

    private Long id;
    private String titulo;
    private TipoEstudoEnumBean tipoEstudo;
    private List<RevisorBean> autores;
    private EstagioExecucaoBean execucao;

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
     * Obtém o valor do atributo titulo.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo titulo.
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Atualiza a instância de titulo com o valor de titulo.
     * 
     * @param titulo Uma instância de String contendo o valor a ser atualizado.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém o valor do atributo tipoEstudo.
     * 
     * @return Uma instância de {@link TipoEstudoEnumBean} contendo o valor do atributo tipoEstudo.
     */
    public TipoEstudoEnumBean getTipoEstudo() {
        return this.tipoEstudo;
    }

    /**
     * Atualiza a instância de tipoEstudo com o valor de tipoEstudo.
     * 
     * @param tipoEstudo Uma instância de TipoEstudoEnumBean contendo o valor a ser atualizado.
     */
    public void setTipoEstudo(TipoEstudoEnumBean tipoEstudo) {
        this.tipoEstudo = tipoEstudo;
    }

    /**
     * Obtém o valor do atributo autores.
     * 
     * @return Uma instância de {@link List<RevisorBean>} contendo o valor do atributo autores.
     */
    public List<RevisorBean> getAutores() {
        return this.autores;
    }

    /**
     * Atualiza a instância de autores com o valor de autores.
     * 
     * @param autores Uma instância de List<RevisorBean> contendo o valor a ser atualizado.
     */
    public void setAutores(List<RevisorBean> autores) {
        this.autores = autores;
    }

    /**
     * Obtém o valor do atributo execucao.
     * 
     * @return Uma instância de {@link EstagioExecucaoBean} contendo o valor do atributo execucao.
     */
    public EstagioExecucaoBean getExecucao() {
        return this.execucao;
    }

    /**
     * Atualiza a instância de execucao com o valor de execucao.
     * 
     * @param execucao Uma instância de EstagioExecucaoBean contendo o valor a ser atualizado.
     */
    public void setExecucao(EstagioExecucaoBean execucao) {
        this.execucao = execucao;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
     */
    @SuppressWarnings("unchecked")
    @Override
    public EstudoSecundario getEntidade() {
        EstudoSecundario entidade = new EstudoSecundario();

        entidade.setId(this.id);
        entidade.setDataInclusao(this.getDataInclusao());
        entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

        entidade.setTitulo(this.titulo);

        if (this.tipoEstudo != null) {
            entidade.setTipoEstudo(this.tipoEstudo.getEnumeracao());
        }

        if (this.autores != null && !this.autores.isEmpty()) {
            List<Revisor> autoresBean = new ArrayList<Revisor>();

            for (RevisorBean autor : this.autores) {
                if (autor != null) {
                    autoresBean.add(new Revisor(autor.getId()));
                }
            }

            entidade.setAutores(autoresBean);
        }

        if (this.execucao != null) {
            entidade.setExecucao(new EstagioExecucao(this.execucao.getId()));
        }
        
        return entidade;
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

}
