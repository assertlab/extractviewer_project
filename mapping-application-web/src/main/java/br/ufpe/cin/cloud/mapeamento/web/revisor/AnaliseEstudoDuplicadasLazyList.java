/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.revisor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.FiltroPropriedade;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.OrdenacaoPropriedade;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.TipoFiltroEnum;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AnaliseEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.FiltroBuscaEstudoDuplicadoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico;

/**
 * Representa a implementação do paginador sob demanda das listagens de AnaliseEstudos duplicadas.
 * 
 * @author helaine.lins
 * @created 01/09/2014 - 19:22:55
 */
public class AnaliseEstudoDuplicadasLazyList extends LazyDataModel<AnaliseEstudoBean> {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 463928728685655555L;

    private IAnaliseEstudoServico analiseEstudoServico;
    private FiltroBuscaEstudoDuplicadoBean filtroDuplicado;
    private List<AnaliseEstudoBean> analises;

    /**
     * Cria uma nova instância da classe inicializando os dados da busca.
     */
    public AnaliseEstudoDuplicadasLazyList(IAnaliseEstudoServico analiseEstudoServico,
            FiltroBuscaEstudoDuplicadoBean filtroDuplicado) {

        this.analiseEstudoServico = analiseEstudoServico;
        this.filtroDuplicado = filtroDuplicado;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see org.primefaces.model.LazyDataModel#load(int, int, java.util.List, java.util.Map)
     */
    @Override
    public List<AnaliseEstudoBean> load(int first, int pageSize, List<SortMeta> multiSortMeta,
            Map<String, String> filters) {

        List<OrdenacaoPropriedade> ordenacoes = null;
        if (multiSortMeta != null && !multiSortMeta.isEmpty()) {
            ordenacoes = new ArrayList<OrdenacaoPropriedade>();

            if (multiSortMeta != null) {
                for (SortMeta sortMeta : multiSortMeta) {
                    if (sortMeta != null) {
                        if (SortOrder.DESCENDING.equals(sortMeta.getSortOrder())) {
                            ordenacoes.add(new OrdenacaoPropriedade(sortMeta.getSortField(), true));
                        } else {
                            ordenacoes.add(new OrdenacaoPropriedade(sortMeta.getSortField(), false));
                        }
                    }
                }
            }
        }

        List<FiltroPropriedade> filtros = this.tratarFiltros(filters);

        this.analises =
                this.analiseEstudoServico.procurarPotenciaisDuplicados(filtros, ordenacoes, first, first + pageSize);

        this.setRowCount(this.analiseEstudoServico.qtdPotenciaisDuplicados(filtros));

        this.setPageSize(pageSize);

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder,
     *      java.util.Map)
     */
    @Override
    public List<AnaliseEstudoBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, String> filters) {

        List<OrdenacaoPropriedade> ordenacoes = null;
        if (StringUtils.isNotEmpty(sortField)) {
            ordenacoes = new ArrayList<OrdenacaoPropriedade>();

            if (SortOrder.DESCENDING.equals(sortOrder)) {
                ordenacoes.add(new OrdenacaoPropriedade(sortField, true));
            } else {
                ordenacoes.add(new OrdenacaoPropriedade(sortField, false));
            }
        }

        List<FiltroPropriedade> filtros = this.tratarFiltros(filters);

        this.analises =
                this.analiseEstudoServico.procurarPotenciaisDuplicados(filtros, ordenacoes, first, first + pageSize);

        this.setRowCount(this.analiseEstudoServico.qtdPotenciaisDuplicados(filtros));

        this.setPageSize(pageSize);

        return analises;

    }

    /**
     * {@inheritDoc}.
     * 
     * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
     */
    @Override
    public Object getRowKey(AnaliseEstudoBean analise) {
        Long id = null;

        if (analise != null) {
            id = analise.getId();
        }

        return id;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see org.primefaces.model.LazyDataModel#getRowData()
     */
    @Override
    public AnaliseEstudoBean getRowData(String analiseId) {
        AnaliseEstudoBean bean = null;

        if (StringUtils.isNotEmpty(analiseId) && this.analises != null && !this.analises.isEmpty()) {
            for (AnaliseEstudoBean elemBean : this.analises) {
                if (Long.valueOf(analiseId).equals(elemBean.getId())) {
                    bean = elemBean;
                    break;
                }
            }
        }

        return bean;
    }

    /**
     * Realiza o tratamento dos valores escolhidos como filtro.
     * 
     * @param filters Os filtros selecionados na interface com o usuário.
     * @return Um {@link List} contendo os valores a serem filtrados.
     */
    private List<FiltroPropriedade> tratarFiltros(Map<String, String> filters) {

        List<FiltroPropriedade> filtros = null;

        filtros = new ArrayList<FiltroPropriedade>();

        filtros.add(new FiltroPropriedade("etapa.id", this.filtroDuplicado.getIdEtapaRevisao(), Long.class,
                TipoFiltroEnum.EQUALS));

        filtros.add(new FiltroPropriedade("analise.id", this.filtroDuplicado.getIdAnaliseEstudoEmEdicao(), Long.class,
                TipoFiltroEnum.DIFFERENT));

        if (!MapeamentoUtil.isEmpty(this.filtroDuplicado.getCodEstudo())) {
            filtros.add(new FiltroPropriedade("estudo.codigo", this.filtroDuplicado.getCodEstudo(), String.class,
                    TipoFiltroEnum.EQUALS));
        }

        if (!MapeamentoUtil.isEmpty(this.filtroDuplicado.getTituloEstudo())) {
            filtros.add(new FiltroPropriedade("estudo.titulo", this.filtroDuplicado.getTituloEstudo(), Long.class,
                    TipoFiltroEnum.LIKE));
        }

        if (this.filtroDuplicado.getIdAnaliseEstudoDuplicada() > 0) {
            filtros.add(new FiltroPropriedade("analise.id", this.filtroDuplicado.getIdAnaliseEstudoDuplicada(),
                    Long.class, TipoFiltroEnum.EQUALS));
        }

        return filtros;
    }

    /**
     * Obtém o valor do atributo filtroDuplicado.
     * 
     * @return Uma instância de {@link FiltroBuscaEstudoDuplicadoBean} contendo o valor do atributo
     *         filtroDuplicado.
     */
    public FiltroBuscaEstudoDuplicadoBean getFiltroDuplicado() {
        return this.filtroDuplicado;
    }

    /**
     * Atualiza a instância de filtroDuplicado com o valor de filtroDuplicado.
     * 
     * @param filtroDuplicado Uma instância de FiltroBuscaEstudoDuplicadoBean contendo o valor a ser atualizado.
     */
    public void setFiltroDuplicado(FiltroBuscaEstudoDuplicadoBean filtroDuplicado) {
        this.filtroDuplicado = filtroDuplicado;
    }

    /**
     * Obtém o valor do atributo analises.
     * 
     * @return Uma instância de {@link List<AnaliseEstudoBean>} contendo o valor do atributo analises.
     */
    public List<AnaliseEstudoBean> getAnalises() {
        return this.analises;
    }

    /**
     * Atualiza a instância de analises com o valor de analises.
     * 
     * @param analises Uma instância de List<AnaliseEstudoBean> contendo o valor a ser atualizado.
     */
    public void setAnalises(List<AnaliseEstudoBean> analises) {
        this.analises = analises;
    }

}
