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
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.AnaliseEstudoServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico;

/**
 * Representa a implementação do paginador sob demanda das listagens de AnaliseEstudos.
 * 
 * @author helaine.lins
 * @created 01/09/2014 - 19:22:55
 */
public class AnaliseEstudoLazyList extends LazyDataModel<AnaliseEstudoBean> {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 463928728685634645L;

    /**
     * Representa a instância da camada de servico {@link AnaliseEstudoServico}.
     */
    private IAnaliseEstudoServico analiseEstudoServico;
    private AnaliseEstudoBean containerDadosBusca;
    private List<AnaliseEstudoBean> estudos;
    private Long idUsuarioLogado;

    /**
     * Cria uma nova instância da classe inicializando os dados da busca.
     */
    public AnaliseEstudoLazyList(IAnaliseEstudoServico analiseEstudoServico, Long idEtapaRevisao, Long idUsuarioLogado,
            AnaliseEstudoBean containerDadosBusca) {
        this.analiseEstudoServico = analiseEstudoServico;
        this.containerDadosBusca = containerDadosBusca;
        this.idUsuarioLogado = idUsuarioLogado;
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
        } else {
            ordenacoes = new ArrayList<OrdenacaoPropriedade>();
            ordenacoes.add(new OrdenacaoPropriedade("analise.id", false));
        }

        List<FiltroPropriedade> filtros = this.tratarFiltros(filters);

        this.estudos =
                this.analiseEstudoServico.listar(this.containerDadosBusca.getEtapa().getId(), this.idUsuarioLogado, filtros, ordenacoes,
                        first, first + pageSize);

        this.setRowCount(this.analiseEstudoServico.qtdTotal(this.containerDadosBusca.getEtapa().getId(), this.idUsuarioLogado, filtros));

        this.setPageSize(pageSize);

        return estudos;
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
        } else {
            ordenacoes = new ArrayList<OrdenacaoPropriedade>();
            ordenacoes.add(new OrdenacaoPropriedade("analise.id", false));
        }

        List<FiltroPropriedade> filtros = this.tratarFiltros(filters);

        this.estudos =
                this.analiseEstudoServico.listar(this.containerDadosBusca.getEtapa().getId(), this.idUsuarioLogado, filtros, ordenacoes,
                        first, first + pageSize);

        this.setRowCount(this.analiseEstudoServico.qtdTotal(this.containerDadosBusca.getEtapa().getId(), this.idUsuarioLogado, filtros));

        this.setPageSize(pageSize);

        return estudos;

    }

    /**
     * Realiza o tratamento dos valores escolhidos como filtro.
     * 
     * @param filters Os filtros selecionados na interface com o usuário.
     * @return Um {@link List} contendo os valores a serem filtrados.
     */
    private List<FiltroPropriedade> tratarFiltros(Map<String, String> filters) {

        List<FiltroPropriedade> filtros = new ArrayList<FiltroPropriedade>();

        if (this.containerDadosBusca.getEstudo().getId() != null && this.containerDadosBusca.getEstudo().getId() > 0) {

            filtros.add(new FiltroPropriedade("estudo.id", this.containerDadosBusca.getEstudo().getId(), Long.class,
                    TipoFiltroEnum.EQUALS));
        }

        if (!MapeamentoUtil.isEmpty(this.containerDadosBusca.getEstudo().getTitulo())) {

            filtros.add(new FiltroPropriedade("estudo.titulo", this.containerDadosBusca.getEstudo().getTitulo(),
                    String.class, TipoFiltroEnum.LIKE));
        }

        if (this.containerDadosBusca.getEstudo().getAno() != null && this.containerDadosBusca.getEstudo().getAno() > 0) {
            filtros.add(new FiltroPropriedade("estudo.ano", this.containerDadosBusca.getEstudo().getAno(),
                    Integer.class, TipoFiltroEnum.EQUALS));
        }

        if (!MapeamentoUtil.isEmpty(this.containerDadosBusca.getEstudo().getAutores())) {
            filtros.add(new FiltroPropriedade("estudo.autores", this.containerDadosBusca.getEstudo().getAutores(),
                    String.class, TipoFiltroEnum.LIKE));
        }

        if (!MapeamentoUtil.isEmpty(this.containerDadosBusca.getEstudo().getBusca().getComunidade())) {
            filtros.add(new FiltroPropriedade("busca.comunidade", this.containerDadosBusca.getEstudo().getBusca()
                    .getComunidade(), String.class, TipoFiltroEnum.LIKE));
        }

        if (!MapeamentoUtil.isEmpty(this.containerDadosBusca.getEstudo().getBusca().getTipoConferencia())) {
            filtros.add(new FiltroPropriedade("busca.tipoConferencia", this.containerDadosBusca.getEstudo().getBusca()
                    .getTipoConferencia(), String.class, TipoFiltroEnum.LIKE));
        }

        if (!MapeamentoUtil.isEmpty(this.containerDadosBusca.getEstudo().getBusca().getConferencia())) {
            filtros.add(new FiltroPropriedade("busca.conferencia", this.containerDadosBusca.getEstudo().getBusca()
                    .getConferencia(), String.class, TipoFiltroEnum.LIKE));
        }

        if (!MapeamentoUtil.isEmpty(this.containerDadosBusca.getEstudo().getBusca().getBase().getNome())) {
            filtros.add(new FiltroPropriedade("base.nome", this.containerDadosBusca.getEstudo().getBusca().getBase()
                    .getNome(), String.class, TipoFiltroEnum.LIKE));
        }

        if (this.containerDadosBusca.getIncluido() != null) {
            filtros.add(new FiltroPropriedade("analise.incluido", this.containerDadosBusca.getIncluido(),
                    Boolean.class, TipoFiltroEnum.EQUALS));
        }

        if (this.containerDadosBusca.getConcluido() != null) {
            filtros.add(new FiltroPropriedade("analise.concluido", this.containerDadosBusca.getConcluido(),
                    Boolean.class, TipoFiltroEnum.EQUALS));
        }

        if (this.containerDadosBusca.getDuplicado() != null) {
            //aqui a lógica é invertida pelo fato de compara com is null.
            filtros.add(new FiltroPropriedade("analise.matriz", !this.containerDadosBusca.getDuplicado(),
                    Boolean.class, TipoFiltroEnum.NULL));
        }

        if (this.containerDadosBusca.getCriterio() != null && this.containerDadosBusca.getCriterio().getId() != null
                && this.containerDadosBusca.getCriterio().getId() > 0) {
            filtros.add(new FiltroPropriedade("criterio.id", this.containerDadosBusca.getCriterio().getId(),
                    Long.class, TipoFiltroEnum.EQUALS));
        }

        return filtros;
    }

    /**
     * Obtém o valor do atributo containerDadosBusca.
     * 
     * @return Uma instância de {@link AnaliseEstudoBean} contendo o valor do atributo containerDadosBusca.
     */
    public AnaliseEstudoBean getContainerDadosBusca() {
        return this.containerDadosBusca;
    }

    /**
     * Atualiza a instância de containerDadosBusca com o valor de containerDadosBusca.
     * 
     * @param containerDadosBusca Uma instância de AnaliseEstudoBean contendo o valor a ser atualizado.
     */
    public void setContainerDadosBusca(AnaliseEstudoBean containerDadosBusca) {
        this.containerDadosBusca = containerDadosBusca;
    }

    /**
     * Atualiza a instância de idUsuarioLogado com o valor de idUsuarioLogado.
     * 
     * @param idUsuarioLogado Uma instância de Long contendo o valor a ser atualizado.
     */
    public void setIdUsuarioLogado(Long idUsuarioLogado) {
        this.idUsuarioLogado = idUsuarioLogado;
    }

    /**
     * Obtém o valor do atributo idUsuarioLogado.
     * 
     * @return Uma instância de {@link Long} contendo o valor do atributo idUsuarioLogado.
     */
    public Long getIdUsuarioLogado() {
        return this.idUsuarioLogado;
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

        if (StringUtils.isNotEmpty(analiseId) && this.estudos != null && !this.estudos.isEmpty()) {
            for (AnaliseEstudoBean elemBean : this.estudos) {
                if (Long.valueOf(analiseId).equals(elemBean.getId())) {
                    bean = elemBean;
                    break;
                }
            }
        }

        return bean;
    }

}
