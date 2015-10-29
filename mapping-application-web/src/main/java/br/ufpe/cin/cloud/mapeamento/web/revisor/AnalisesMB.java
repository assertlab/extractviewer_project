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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AnaliseEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AutorBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.CriterioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaConclusaoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaEsforcoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaEtapaRevisao;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasConclusaoEtapaRevisaoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasEsforcoEtapaRevisaoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EtapaAnaliseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.FiltroBuscaEstudoDuplicadoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.PalavraChaveBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.AnaliseEstudoServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.CriterioServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.EstudoServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.EtapaAnaliseServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.ICriterioServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IEstudoServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IEtapaAnaliseServico;
import br.ufpe.cin.cloud.mapeamento.web.base.camadas.AbstractMB;
import br.ufpe.cin.cloud.mapeamento.web.base.util.MapeamentoWebUtil;

/**
 * Representa a implementação do controlador da tela de análises.
 * 
 * @author helaine.lins
 * @created 01/09/2014 - 12:49:25
 */
@ViewScoped
@ManagedBean(name = "analisesMB")
public class AnalisesMB extends AbstractMB {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = -3047710952771984933L;

    /**
     * Representa a instância da camada de servico {@link EtapaAnaliseServico}.
     */
    @ManagedProperty(value = "#{etapaAnaliseServico}")
    private IEtapaAnaliseServico etapaRevisaoServico;

    /**
     * Representa a instância da camada de servico {@link AnaliseEstudoServico}.
     */
    @ManagedProperty(value = "#{analiseEstudoServico}")
    private IAnaliseEstudoServico analiseEstudoServico;

    /**
     * Representa a instância da camada de servico {@link CriterioServico}.
     */
    @ManagedProperty(value = "#{criterioServico}")
    private ICriterioServico criterioServico;

    /**
     * Representa a instância da camada de servico {@link EstudoServico}.
     */
    @ManagedProperty(value = "#{estudoServico}")
    private IEstudoServico estudoServico;

    private EtapaAnaliseBean containerRevisao;
    private AnaliseEstudoBean containerDados;
    private AnaliseEstudoBean containerDadosMatriz;
    private AnaliseEstudoBean containerEdicaoDados;
    private AnaliseEstudoBean containerDadosBusca;
    private AnaliseEstudoLazyList analises;
    private Boolean analiseDetalhe;
    private List<CriterioBean> criterios;
    private EstatisticasConclusaoEtapaRevisaoBean estatisticasConclusao;
    private EstatisticasEsforcoEtapaRevisaoBean estatisticasEsforco;
    private PieChartModel estatisticasCategorias;
    private PieChartModel estatisticasQuantitativos;
    private PieChartModel estatisticasEstudosAno;
    private PieChartModel estatisticasAnaliseEstudosIncluidosAno;
    private PieChartModel estatisticasAnaliseEstudosExcluidosAno;
    private PieChartModel estatisticasEstudosAnalisadosAno;
    private CartesianChartModel estatisticasDashBoard;
    private TagCloudModel tagcloudIncluidos;
    private TagCloudModel tagcloudExcluidos;
    private TagCloudModel tagcloudGeral;
    private EstatisticaEtapaRevisao estatisticaEtapaRevisao;
    private List<AutorBean> autoresIncluidos;
    private List<AutorBean> autoresExcluidos;
    private List<AutorBean> autores;
    private DataTable registrosDataTable;
    private Boolean forcarAlteracao;
    private Boolean emEdicao;
    private Boolean estudoEmEdicao;
    private Boolean informarMotivo;
    private EstudoBean containerEstudoEdicao;
    private FiltroBuscaEstudoDuplicadoBean filtroDuplicado;
    private CriterioBean criterioDuplicado;
    private Boolean exibeFiltroBuscaDuplicacao;
    private AnaliseEstudoDuplicadasLazyList potDuplicados;
    private AnaliseEstudoBean selDuplicado;
    private DataTable duplicadosDataTable;
    private Boolean matrizEmEdicao;

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.web.base.camadas.AbstractMB#carregarPagina()
     */
    @Override
    public void carregarPagina() {

        this.containerRevisao = MapeamentoWebUtil.obterAtributoSessao("revisao", EtapaAnaliseBean.class);

        if (!FacesContext.getCurrentInstance().isPostback()) {
            this.filtroDuplicado = new FiltroBuscaEstudoDuplicadoBean();

            this.containerEstudoEdicao = null;

            this.containerEdicaoDados = new AnaliseEstudoBean();
            this.containerDadosBusca = new AnaliseEstudoBean(this.containerRevisao);

            this.listarAnalises();
            this.criterios = this.criterioServico.listar();
            this.criterioDuplicado = this.criterioServico.buscarCriterio("O Estudo é duplicado.");

            this.analiseDetalhe = false;
            this.forcarAlteracao = false;
            this.emEdicao = false;
            this.informarMotivo = true;
            this.exibeFiltroBuscaDuplicacao = false;
            this.matrizEmEdicao = false;
        }

        this.atualizarInclusaoAnalise();
    }

    /**
     * Analisa os dados preenchidos pelo usuário para saber se o filtro de busca por estudos duplicados deve ser
     * exibido.
     */
    public void exibirFiltroBusca() {
        this.exibeFiltroBuscaDuplicacao = false;

        // candidato preencheu criterio
        if (this.containerEdicaoDados != null && this.containerEdicaoDados.getCriterio() != null
                && this.containerEdicaoDados.getCriterio().getId() != null
                && this.containerEdicaoDados.getCriterio().getId() > 0) {

            // o critério selecionado foi estudo duplicado
            if (this.criterioDuplicado.getId() == this.containerEdicaoDados.getCriterio().getId()
                    && this.containerEdicaoDados.getMatriz() == null) {

                /*
                 * habilita o filtro de busca de estudos duplicados se o estudo não estiver concluido ou se estiver
                 * concluido o filtro de forçar alteração tiver sido habilitado
                 */

                if (this.getContainerEdicaoDados().getConcluido() != null) {
                    if (this.getContainerEdicaoDados().getMatriz() == null
                            && this.getContainerEdicaoDados().getConcluido() == false
                            || (this.getContainerEdicaoDados().getConcluido() == true && this.forcarAlteracao != null && this.forcarAlteracao == true)) {

                        this.exibeFiltroBuscaDuplicacao = true;
                    }
                }
            }
        }
    }

    public void exibirBuscaDuplicados() {
        this.selDuplicado = null;
        this.filtroDuplicado = new FiltroBuscaEstudoDuplicadoBean();
    }

    /**
     * Realiza a busca por estudos duplicados conforme o filtro preenchido pelo usuário.
     */
    public void buscarAnalisesDuplicadas() {
        if (this.filtroDuplicado != null && this.containerEdicaoDados != null && this.containerEdicaoDados.getId() > 0) {

            if (!MapeamentoUtil.isEmpty(this.filtroDuplicado.getCodEstudo())
                    || !MapeamentoUtil.isEmpty(this.filtroDuplicado.getTituloEstudo())
                    || (this.filtroDuplicado.getIdAnaliseEstudoDuplicada() != null && this.filtroDuplicado
                            .getIdAnaliseEstudoDuplicada() > 0)) {

                this.filtroDuplicado.setIdAnaliseEstudoEmEdicao(this.containerEdicaoDados.getId());
                this.filtroDuplicado.setIdEtapaRevisao(this.containerRevisao.getId());

                if (this.potDuplicados == null) {
                    this.potDuplicados =
                            new AnaliseEstudoDuplicadasLazyList(this.analiseEstudoServico, this.filtroDuplicado);
                } else {
                    this.potDuplicados.setFiltroDuplicado(this.filtroDuplicado);
                    this.duplicadosDataTable.loadLazyData();
                }

            } else {
                MapeamentoWebUtil.exibirMensagemErro("msg_busca_duplicados_campos_obrigatorios");
            }

        }
    }

    public void cancelarBuscaDuplicados() {
        this.selDuplicado = null;
        this.filtroDuplicado = new FiltroBuscaEstudoDuplicadoBean();
        this.emEdicao = true;
    }

    public void finalizarBuscaDuplicados() {
        if (this.selDuplicado == null) {
            MapeamentoWebUtil.exibirMensagemErro("msg_selecao_duplicado_nao_informada");
        } else {
            this.analiseEstudoServico.validarAssociacaoDuplicado(this.containerEdicaoDados, this.selDuplicado);
            this.containerEdicaoDados.setMatriz(this.selDuplicado);
            this.filtroDuplicado = new FiltroBuscaEstudoDuplicadoBean();
            this.emEdicao = true;
        }
    }

    public void inserirTextoConfirmacaoDuplicado() {
        if (this.containerEdicaoDados != null) {
            this.containerEdicaoDados.setComentario("Duplicação confirmada.");
        }
    }

    /**
     * Atualiza a análise duplicada que foi selecionada pelo usuário na tabela de exibição dos dados.
     * 
     * @param event A instância que contém os dados do evento.
     */
    public void onRowDuplicadoSelect(SelectEvent event) {
        this.containerDados = (AnaliseEstudoBean) event.getObject();
    }

    /**
     * Atualiza a análise duplicada que deixou de ser selecionada pelo usuário na tabela de exibição dos dados.
     * 
     * @param event A instância que contém os dados do evento.
     */
    public void onRowDuplicadoUnselect(UnselectEvent event) {
        this.containerDados = null;
    }

    /**
     * Libera os campos do formulário de edição da análise.
     */
    public void liberarAlteracao() {
        this.forcarAlteracao = true;
        this.emEdicao = true;
        this.exibirFiltroBusca();
    }

    /**
     * Bloqueia os campos de alteração do formulário da edição da análise.
     */
    public void cancelarAlteracao() {
        this.forcarAlteracao = false;
        this.emEdicao = false;
        this.informarMotivo = true;
        this.containerDadosBusca = this.analises.getContainerDadosBusca();
    }

    /**
     * Bloqueia os campos de alteração do formulário da edição da análise.
     * 
     * @param event A instância do evento que disparou a chamada.
     */
    public void cancelarAlteracao(CloseEvent event) {
        this.cancelarAlteracao();
    }

    /**
     * Habilita os campos para edição da análise.
     */
    public void iniciarEdicao() {
        this.emEdicao = true;
        this.exibirFiltroBusca();
    }

    /**
     * Bloqueia os campos do formulário de edição quando o usuário cancela a eição.
     */
    public void finalizarEdicao() {
        this.emEdicao = false;
    }

    /**
     * Prepara o formulário para a edição de estudos.
     */
    public void iniciarEdicaoEstudo() {
        if (matrizEmEdicao != null && matrizEmEdicao == true) {
            this.containerEstudoEdicao = this.containerDadosMatriz.getEstudo();
        } else {
            this.containerEstudoEdicao = this.containerEdicaoDados.getEstudo();
        }

        this.estudoEmEdicao = true;
    }

    /**
     * Bloqueia os campos de edição de estudos.
     */
    public void cancelarEdicaoEstudo() {
        this.matrizEmEdicao = false;
        this.estudoEmEdicao = false;
        this.containerEstudoEdicao = null;
    }

    /**
     * Realiza a edição do estudo de uma determinada análise.
     */
    public void editarEstudo() {
        this.estudoServico.alterar(this.getRevisorLogado(), this.containerEstudoEdicao);

        if (this.matrizEmEdicao != null && this.matrizEmEdicao == true) {
            this.containerDadosMatriz.setEstudo(this.containerEstudoEdicao);
            this.containerEdicaoDados.setMatriz(this.containerDadosMatriz);

        } else {
            this.containerEdicaoDados.setEstudo(this.containerEstudoEdicao);
        }

        this.exibirFiltroBusca();

        this.registrosDataTable.loadLazyData();
        this.estudoEmEdicao = false;
        this.matrizEmEdicao = false;
    }

    /**
     * Aplica os filtros de busca selecionados pelo revisor.
     */
    public void filtrarEstudos() {
        this.analises.setContainerDadosBusca(this.containerDadosBusca);
        this.registrosDataTable.loadLazyData();
    }

    /**
     * Limpa os filtros de busca selecionados pelo revisor.
     */
    public void limparFiltroEstudos() {
        this.analises.setContainerDadosBusca(new AnaliseEstudoBean(this.containerRevisao));
        this.registrosDataTable.loadLazyData();
    }

    /**
     * Recupera o identificador do critério de busca utilizado no filtro de busca de análises.
     * 
     * return O identificador do critério selecionado ou <code>null</code> caso não exista um selecionado.
     */
    public Long getCriterioBusca() {
        Long id = null;

        if (this.containerDadosBusca != null && this.containerDadosBusca.getCriterio() != null) {
            id = this.containerDadosBusca.getCriterio().getId();
        }

        return id;
    }

    /**
     * Atualiza o valor do critério de busca utilizado pelo usuário durante a aplicação do filtro de busca;
     * 
     * @param id O identificador do filtro de busca.
     */
    public void setCriterioBusca(Long id) {
        if (id == null) {
            this.containerDadosBusca.setCriterio(null);
        } else {
            this.containerDadosBusca.setCriterio(new CriterioBean(id));
        }
    }

    /**
     * Recupera uma lista de {@link SelectItem} do tipo boleanos.
     * 
     * @return Um {@link List} de {@link SelectItem} do tipo boleano.
     */
    public List<SelectItem> getListaBoleanos() {
        List<SelectItem> selectItemBoleano = new ArrayList<SelectItem>();

        selectItemBoleano.add(new SelectItem(null, "Selecione"));
        selectItemBoleano.add(new SelectItem(Boolean.TRUE, "Sim"));
        selectItemBoleano.add(new SelectItem(Boolean.FALSE, "Não"));

        return selectItemBoleano;
    }

    /**
     * Lista todas as análises cadastradas para a etapa de revisão.
     */
    private void listarAnalises() {
        if (this.analises == null) {
            this.analises =
                    new AnaliseEstudoLazyList(this.analiseEstudoServico, this.containerRevisao.getId(),
                            super.getIdUsuarioLogado(), this.containerDadosBusca);
        }
    }

    /**
     * Realiza a inversão da análise matriz de uma determinada análise.
     */
    public void inverterAnaliseMatrizEstudo() {
        this.containerEdicaoDados.setRevisor(this.getRevisorLogado());

        this.containerEdicaoDados =
                this.analiseEstudoServico.inverterOrdemMatrizEstudo(this.containerEdicaoDados,
                        this.containerEdicaoDados.getMatriz());

        this.registrosDataTable.loadLazyData();

        MapeamentoWebUtil.exibirMensagemSucesso("msg_matriz_alterada_sucesso");
    }

    /**
     * Atualiza a análise selecionada pelo usuário na tabela de exibição dos dados.
     * 
     * @param event A instância que contém os dados do evento.
     */
    public void onRowAnaliseSelect(SelectEvent event) {
        this.containerDados = (AnaliseEstudoBean) event.getObject();
    }

    /**
     * Atualiza a análise que deixou de ser selecionada pelo usuário na tabela de exibição dos dados.
     * 
     * @param event A instância que contém os dados do evento.
     */
    public void onRowAnaliseUnselect(UnselectEvent event) {
        this.containerDados = null;
    }

    /**
     * Apresenta a tela inicial de estudos em analise.
     * 
     * @return Uma {@link String} do fluxo de navegação.
     */
    public String exibirEstudosAnalise() {
        this.listarAnalises();
        return "exibirEstudosAnalise";
    }

    /**
     * Salva os dados de uma análise que está sendo realizada no modo lista.
     */
    public void salvarEdicaoAnalise() {
        if (this.containerEdicaoDados != null) {

            if ((Boolean.FALSE.equals(this.containerEdicaoDados.getIncluido()) && (this.containerEdicaoDados
                    .getCriterio() == null || this.containerEdicaoDados.getCriterio().getId() == null || this.containerEdicaoDados
                    .getCriterio().getId() <= 0))) {

                MapeamentoWebUtil.exibirMensagemErro("msg_criterio_obrigatorio", "mensagensAlteracao");
            } else {
                this.containerEdicaoDados.setConcluido(true);
                this.containerEdicaoDados.setRevisor(this.getRevisorLogado());
                this.analiseEstudoServico.alterar(this.containerEdicaoDados);

                this.registrosDataTable.loadLazyData();

                this.forcarAlteracao = false;
                this.emEdicao = false;

                MapeamentoWebUtil.exibirMensagemSucesso("msg_analise_salva_sucesso", "mensagensAnalises");

                RequestContext.getCurrentInstance().execute("popupAlteraDadosAnalise.hide()");
            }
        }
    }

    public void atualizarInclusaoAnalise() {
        this.informarMotivo = true;

        if (this.containerEdicaoDados.getIncluido() != null && this.containerEdicaoDados.getIncluido()) {

            this.containerEdicaoDados.setCriterio(null);
            this.containerEdicaoDados.setConcluido(false);
            this.informarMotivo = false;
        }
    }

    /**
     * Monta a lista de critérios de análise.
     */
    public List<SelectItem> getListaCriterios() {
        List<SelectItem> selectItemCriterios = null;

        if (this.criterios != null && !this.criterios.isEmpty()) {
            selectItemCriterios = new ArrayList<SelectItem>();

            for (CriterioBean criterio : this.criterios) {
                if (criterio != null) {
                    selectItemCriterios.add(new SelectItem(criterio.getId(), criterio.getDescricao()));
                }
            }
        }

        return selectItemCriterios;
    }

    /**
     * Realiza a coleta de estatísticas de conclusao da etapa de análise.
     */
    public void coletarEstatisticasConclusao() {
        this.estatisticasConclusao =
                this.analiseEstudoServico.coletarEstatisticasDeConclusao(this.containerRevisao.getId(),
                        super.getIdUsuarioLogado());

        this.estatisticasCategorias = new PieChartModel();

        for (EstatisticaConclusaoBean estCriterios : this.estatisticasConclusao.getDetalheCriterios()) {
            estatisticasCategorias.set(estCriterios.getDescricao(), estCriterios.getQtd());
        }

        this.estatisticasQuantitativos = new PieChartModel();

        estatisticasQuantitativos.set("Incluídos", this.estatisticasConclusao.getQtdIncluidos());

        estatisticasQuantitativos.set("Excluídos", this.estatisticasConclusao.getQtdExcluidos());

        estatisticasQuantitativos.set("Não Analisados", this.estatisticasConclusao.getQtdNaoConcluidos());
    }

    /**
     * Realiza a coleta de estatísticas da esforço etapa de análise.
     */
    public void coletarEstatisticasEsforco() {

        this.estatisticasEsforco =
                this.analiseEstudoServico.coletarEstatisticasDeEsforco(this.containerRevisao.getId(),
                        super.getIdUsuarioLogado());

        if (this.estatisticasEsforco != null && this.estatisticasEsforco.getDetalheEsforcos() != null
                && !this.estatisticasEsforco.getDetalheEsforcos().isEmpty()) {

            LineChartSeries seriePlanejado = new LineChartSeries("Meta Diária");
            LineChartSeries serieRealizado = new LineChartSeries("Realizado");

            for (EstatisticaEsforcoBean estatistica : this.estatisticasEsforco.getDetalheEsforcos()) {

                if (estatistica != null) {
                    if (estatistica.getData() == null) {
                        seriePlanejado.set("", estatistica.getQtdPlanejado());
                        serieRealizado.set("", estatistica.getQtdRealizado());
                    } else {
                        if (estatistica.getQtdPlanejado() != null && estatistica.getQtdPlanejado() != 0) {
                            seriePlanejado.set(estatistica.getData().toString("dd/MM/yyyy"),
                                    estatistica.getQtdPlanejado());
                        }
                        serieRealizado.set(estatistica.getData().toString("dd/MM/yyyy"), estatistica.getQtdRealizado());
                    }
                }
            }

            this.estatisticasDashBoard = new CartesianChartModel();
            this.estatisticasDashBoard.addSeries(seriePlanejado);
            this.estatisticasDashBoard.addSeries(serieRealizado);
        }

    }

    /**
     * Realiza a coleta de estatísticas de estudos por ano.
     */
    public void coletarEstatisticasEstudosAno() {
        List<EstatisticasEstudoBean> quantitativoEstudosAno = this.estudoServico.estatisticaQuantidadeEstudosPorAno();

        if (quantitativoEstudosAno != null && !quantitativoEstudosAno.isEmpty()) {
            this.estatisticasEstudosAno = new PieChartModel();

            for (EstatisticasEstudoBean estatisticasEstudoBean : quantitativoEstudosAno) {
                if (estatisticasEstudoBean != null) {
                    this.estatisticasEstudosAno.set(estatisticasEstudoBean.getAno().toString(),
                            estatisticasEstudoBean.getQtd());
                }
            }
        }
    }

    /**
     * Realiza a coleta de estatísticas de estudos excluídos por ano.
     */
    public void coletarEstatisticasAnalisesEstudosExcluidosAno() {
        List<EstatisticasEstudoBean> quantitativoEstudosAno =
                this.analiseEstudoServico.estatisticaQuantidadeEstudosAnalisadosPorAno(false,
                        this.containerRevisao.getId(), this.getIdUsuarioLogado());

        this.estatisticasAnaliseEstudosExcluidosAno = new PieChartModel();
        if (quantitativoEstudosAno != null && !quantitativoEstudosAno.isEmpty()) {

            for (EstatisticasEstudoBean estatisticasEstudoBean : quantitativoEstudosAno) {
                if (estatisticasEstudoBean != null) {
                    this.estatisticasAnaliseEstudosExcluidosAno.set(estatisticasEstudoBean.getAno().toString(),
                            estatisticasEstudoBean.getQtd());
                }
            }
        }
    }

    /**
     * Realiza a coleta de estatísticas de estudos incluídos por ano.
     */
    public void coletarEstatisticasAnalisesEstudosIncluidosAno() {
        List<EstatisticasEstudoBean> quantitativoEstudosAno =
                this.analiseEstudoServico.estatisticaQuantidadeEstudosAnalisadosPorAno(true,
                        this.containerRevisao.getId(), this.getIdUsuarioLogado());

        this.estatisticasAnaliseEstudosIncluidosAno = new PieChartModel();

        if (quantitativoEstudosAno != null && !quantitativoEstudosAno.isEmpty()) {

            for (EstatisticasEstudoBean estatisticasEstudoBean : quantitativoEstudosAno) {
                if (estatisticasEstudoBean != null) {
                    this.estatisticasAnaliseEstudosIncluidosAno.set(estatisticasEstudoBean.getAno().toString(),
                            estatisticasEstudoBean.getQtd());
                }
            }
        }
    }

    /**
     * Realiza a coleta de estatísticas de estudos analisados por ano.
     */
    public void coletarEstatisticasEstudosAnalisados() {
        List<EstatisticasEstudoBean> quantitativoEstudosAno = this.estudoServico.estatisticaQuantidadeEstudosPorAno();

        this.estatisticasEstudosAno = new PieChartModel();

        if (quantitativoEstudosAno != null && !quantitativoEstudosAno.isEmpty()) {

            for (EstatisticasEstudoBean estatisticasEstudoBean : quantitativoEstudosAno) {
                if (estatisticasEstudoBean != null) {
                    this.estatisticasEstudosAno.set(estatisticasEstudoBean.getAno().toString(),
                            estatisticasEstudoBean.getQtd());
                }
            }
        }
    }

    public void coletarTagcloudsIncluidos() {

        HashMap<String, PalavraChaveBean> pcIncluidos =
                this.analiseEstudoServico.listarPalavrasChaveEstudos(this.containerRevisao.getId(),
                        super.getIdUsuarioLogado(), true);

        this.tagcloudIncluidos = new DefaultTagCloudModel();
        if (pcIncluidos != null) {
            for (PalavraChaveBean pc : pcIncluidos.values()) {
                tagcloudIncluidos.addTag(new DefaultTagCloudItem(pc.getPalavra(), pc.getQuantidade()));
            }
        }
    }

    public void coletarTagcloudsExcluidos() {

        HashMap<String, PalavraChaveBean> pcExcluidos =
                this.analiseEstudoServico.listarPalavrasChaveEstudos(this.containerRevisao.getId(),
                        super.getIdUsuarioLogado(), false);

        this.tagcloudExcluidos = new DefaultTagCloudModel();

        if (pcExcluidos != null) {
            for (PalavraChaveBean pc : pcExcluidos.values()) {
                if (pc.getQuantidade() > 3) {
                    tagcloudExcluidos.addTag(new DefaultTagCloudItem(pc.getPalavra(), pc.getQuantidade()));
                }
            }
        }
    }

    public void coletarTagcloudsGeral() {

        HashMap<String, PalavraChaveBean> pcGeral =
                this.analiseEstudoServico.listarPalavrasChaveEstudos(this.containerRevisao.getId(),
                        super.getIdUsuarioLogado(), null);

        this.tagcloudGeral = new DefaultTagCloudModel();

        if (pcGeral != null) {
            for (PalavraChaveBean pc : pcGeral.values()) {
                if (pc.getQuantidade() > 5) {
                    tagcloudGeral.addTag(new DefaultTagCloudItem(pc.getPalavra(), pc.getQuantidade()));
                }
            }
        }
    }

    /**
     * Realiza a coleta de autores dos estudos incluídos.
     */
    public void coletarEstatisticasAutoresIncluidos() {
        HashMap<String, AutorBean> autores =
                this.analiseEstudoServico.listarAutoresEstudos(this.containerRevisao.getId(),
                        super.getIdUsuarioLogado(), true);

        if (autores != null && !autores.isEmpty()) {
            this.autoresIncluidos = new ArrayList<AutorBean>(autores.values());
            Collections.sort(this.autoresIncluidos);
            Collections.reverse(this.autoresIncluidos);
        }
    }

    /**
     * Realiza a coleta de autores dos estudos excluídos.
     */
    public void coletarEstatisticasAutoresExcluidos() {
        HashMap<String, AutorBean> autores =
                this.analiseEstudoServico.listarAutoresEstudos(this.containerRevisao.getId(),
                        super.getIdUsuarioLogado(), false);

        if (autores != null && !autores.isEmpty()) {
            this.autoresExcluidos = new ArrayList<AutorBean>(autores.values());
            Collections.sort(this.autoresExcluidos);
            Collections.reverse(this.autoresExcluidos);
        }
    }

    /**
     * Realiza a coleta de autores dos estudos em geral.
     */
    public void coletarEstatisticasAutoresEmGeral() {
        HashMap<String, AutorBean> autores =
                this.analiseEstudoServico.listarAutoresEstudos(this.containerRevisao.getId(),
                        super.getIdUsuarioLogado(), null);

        if (autores != null && !autores.isEmpty()) {
            this.autores = new ArrayList<AutorBean>(autores.values());
            Collections.sort(this.autores);
            Collections.reverse(this.autores);
        }
    }

    /**
     * Realiza a coleta das estatísticas da etapa de revisão.
     */
    public void coletarEstatisticasEtapaRevisao() {
        this.estatisticaEtapaRevisao =
                this.etapaRevisaoServico.coletarEstatisticasEtapaRevisao(this.containerRevisao.getId(),
                        super.getIdUsuarioLogado());
    }

    /**
     * Atualiza o valor do identificador do criterio de inclusao na instância de edição da análise de estudo.
     * 
     * @param id A instância que contém o valor a ser identificado.
     */
    public void setEdicaoCriterioInclusao(Long id) {
        if (id != null) {
            for (CriterioBean criterio : this.criterios) {
                if (criterio != null && criterio.getId() != null && criterio.getId().equals(id)) {
                    this.containerEdicaoDados.setCriterio(criterio);
                    this.containerDados.setIncluido(criterio.getInclusao());
                    break;
                }
            }
        }
    }

    /**
     * Atualiza o valor do critério selecionado na tela de edição em lista.
     * 
     * @param id O valor do identificador do criterio escolhido.
     */
    public void setValorCriterioEdicao(Long id) {
        if (this.containerEdicaoDados != null) {
            this.containerEdicaoDados.setCriterio(new CriterioBean(id));
            this.exibirFiltroBusca();
        }
    }

    /**
     * Atualiza o valor do critério selecionado na tela de edição em lista.
     * 
     * @param id O valor do identificador do criterio escolhido.
     */
    public Long getValorCriterioEdicao() {
        Long id = null;

        if (this.containerEdicaoDados != null && this.containerEdicaoDados.getCriterio() != null) {
            id = this.containerEdicaoDados.getCriterio().getId();
        }

        return id;
    }

    /**
     * Recupera o valor do identificador o critério de inclusão da instância de edição da análise de estudo.
     * 
     * @return Um {@link Long} contendo o identificador do critério de inclusão da instância em edição.
     */
    public Long getEdicaoCriterioInclusao() {
        Long id = null;

        if (this.containerEdicaoDados != null && this.containerEdicaoDados.getCriterio() != null) {
            id = this.containerEdicaoDados.getCriterio().getId();
        }

        return id;
    }

    /**
     * Atualiza a instância de analiseEstudoServico com o valor de analiseEstudoServico.
     * 
     * @param analiseEstudoServico Uma instância de IAnaliseEstudoServico contendo o valor a ser atualizado.
     */
    public void setAnaliseEstudoServico(IAnaliseEstudoServico analiseEstudoServico) {
        this.analiseEstudoServico = analiseEstudoServico;
    }

    /**
     * Atualiza a instância de etapaRevisaoServico com o valor de etapaRevisaoServico.
     * 
     * @param etapaRevisaoServico Uma instância de IEtapaAnaliseServico contendo o valor a ser atualizado.
     */
    public void setEtapaRevisaoServico(IEtapaAnaliseServico etapaRevisaoServico) {
        this.etapaRevisaoServico = etapaRevisaoServico;
    }

    /**
     * Obtém o valor do atributo containerRevisao.
     * 
     * @return Uma instância de {@link EtapaAnaliseBean} contendo o valor do atributo containerRevisao.
     */
    public EtapaAnaliseBean getContainerRevisao() {
        return this.containerRevisao;
    }

    /**
     * Atualiza a instância de containerRevisao com o valor de containerRevisao.
     * 
     * @param containerRevisao Uma instância de EtapaAnaliseBean contendo o valor a ser atualizado.
     */
    public void setContainerRevisao(EtapaAnaliseBean containerRevisao) {
        this.containerRevisao = containerRevisao;
    }

    /**
     * Obtém o valor do atributo containerDados.
     * 
     * @return Uma instância de {@link AnaliseEstudoBean} contendo o valor do atributo containerDados.
     */
    public AnaliseEstudoBean getContainerDados() {
        return this.containerDados;
    }

    /**
     * Atualiza a instância de containerDados com o valor de containerDados.
     * 
     * @param containerDados Uma instância de AnaliseEstudoBean contendo o valor a ser atualizado.
     */
    public void setContainerDados(AnaliseEstudoBean containerDados) {
        this.containerDados = containerDados;
    }

    /**
     * Obtém o valor do atributo analises.
     * 
     * @return Uma instância de {@link AnaliseEstudoLazyList} contendo o valor do atributo analises.
     */
    public AnaliseEstudoLazyList getAnalises() {
        return this.analises;
    }

    /**
     * Atualiza a instância de analises com o valor de analises.
     * 
     * @param analises Uma instância de AnaliseEstudoLazyList contendo o valor a ser atualizado.
     */
    public void setAnalises(AnaliseEstudoLazyList analises) {
        this.analises = analises;
    }

    /**
     * Obtém o valor do atributo analiseDetalhe.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo analiseDetalhe.
     */
    public Boolean getAnaliseDetalhe() {
        return this.analiseDetalhe;
    }

    /**
     * Atualiza a instância de analiseDetalhe com o valor de analiseDetalhe.
     * 
     * @param analiseDetalhe Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setAnaliseDetalhe(Boolean analiseDetalhe) {
        this.analiseDetalhe = analiseDetalhe;
    }

    /**
     * Obtém o valor do atributo containerEdicaoDados.
     * 
     * @return Uma instância de {@link AnaliseEstudoBean} contendo o valor do atributo containerEdicaoDados.
     */
    public AnaliseEstudoBean getContainerEdicaoDados() {
        return this.containerEdicaoDados;
    }

    /**
     * Atualiza a instância de containerEdicaoDados com o valor de containerEdicaoDados.
     * 
     * @param containerEdicaoDados Uma instância de AnaliseEstudoBean contendo o valor a ser atualizado.
     */
    public void setContainerEdicaoDados(AnaliseEstudoBean containerEdicaoDados) {
        this.containerEdicaoDados = containerEdicaoDados;
    }

    /**
     * Atualiza a instância de criterioServico com o valor de criterioServico.
     * 
     * @param criterioServico Uma instância de ICriterioServico contendo o valor a ser atualizado.
     */
    public void setCriterioServico(ICriterioServico criterioServico) {
        this.criterioServico = criterioServico;
    }

    /**
     * Obtém o valor do atributo containerDadosMatriz.
     * 
     * @return Uma instância de {@link AnaliseEstudoBean} contendo o valor do atributo containerDadosMatriz.
     */
    public AnaliseEstudoBean getContainerDadosMatriz() {
        return this.containerDadosMatriz;
    }

    /**
     * Atualiza a instância de containerDadosMatriz com o valor de containerDadosMatriz.
     * 
     * @param containerDadosMatriz Uma instância de AnaliseEstudoBean contendo o valor a ser atualizado.
     */
    public void setContainerDadosMatriz(AnaliseEstudoBean containerDadosMatriz) {
        this.containerDadosMatriz = containerDadosMatriz;
    }

    /**
     * Obtém o valor do atributo estatisticasConclusao.
     * 
     * @return Uma instância de {@link EstatisticasConclusaoEtapaRevisaoBean} contendo o valor do atributo
     *         estatisticasConclusao.
     */
    public EstatisticasConclusaoEtapaRevisaoBean getEstatisticasConclusao() {
        return this.estatisticasConclusao;
    }

    /**
     * Atualiza a instância de estatisticasConclusao com o valor de estatisticasConclusao.
     * 
     * @param estatisticasConclusao Uma instância de EstatisticasConclusaoEtapaRevisaoBean contendo o valor a ser
     *        atualizado.
     */
    public void setEstatisticasConclusao(EstatisticasConclusaoEtapaRevisaoBean estatisticas) {
        this.estatisticasConclusao = estatisticas;
    }

    /**
     * Obtém o valor do atributo estatisticasCategorias.
     * 
     * @return Uma instância de {@link PieChartModel} contendo o valor do atributo estatisticasCategorias.
     */
    public PieChartModel getEstatisticasCategorias() {
        return this.estatisticasCategorias;
    }

    /**
     * Atualiza a instância de estatisticasCategorias com o valor de estatisticasCategorias.
     * 
     * @param estatisticasCategorias Uma instância de PieChartModel contendo o valor a ser atualizado.
     */
    public void setEstatisticasCategorias(PieChartModel estatisticasCategorias) {
        this.estatisticasCategorias = estatisticasCategorias;
    }

    /**
     * Obtém o valor do atributo estatisticasQuantitativos.
     * 
     * @return Uma instância de {@link PieChartModel} contendo o valor do atributo estatisticasQuantitativos.
     */
    public PieChartModel getEstatisticasQuantitativos() {
        return this.estatisticasQuantitativos;
    }

    /**
     * Atualiza a instância de estatisticasQuantitativos com o valor de estatisticasQuantitativos.
     * 
     * @param estatisticasQuantitativos Uma instância de PieChartModel contendo o valor a ser atualizado.
     */
    public void setEstatisticasQuantitativos(PieChartModel estatisticasQuantitativos) {
        this.estatisticasQuantitativos = estatisticasQuantitativos;
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
     * Obtém o valor do atributo registrosDataTable.
     * 
     * @return Uma instância de {@link DataTable} contendo o valor do atributo registrosDataTable.
     */
    public DataTable getRegistrosDataTable() {
        return this.registrosDataTable;
    }

    /**
     * Atualiza a instância de registrosDataTable com o valor de registrosDataTable.
     * 
     * @param registrosDataTable Uma instância de DataTable contendo o valor a ser atualizado.
     */
    public void setRegistrosDataTable(DataTable registrosDataTable) {
        this.registrosDataTable = registrosDataTable;
    }

    /**
     * Obtém o valor do atributo forcarAlteracao.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo forcarAlteracao.
     */
    public Boolean getForcarAlteracao() {
        return this.forcarAlteracao;
    }

    /**
     * Atualiza a instância de forcarAlteracao com o valor de forcarAlteracao.
     * 
     * @param forcarAlteracao Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setForcarAlteracao(Boolean forcarAlteracao) {
        this.forcarAlteracao = forcarAlteracao;
    }

    /**
     * Obtém o valor do atributo emEdicao.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo emEdicao.
     */
    public Boolean getEmEdicao() {
        return this.emEdicao;
    }

    /**
     * Atualiza a instância de emEdicao com o valor de emEdicao.
     * 
     * @param emEdicao Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setEmEdicao(Boolean emEdicao) {
        this.emEdicao = emEdicao;
    }

    /**
     * Obtém o valor do atributo estudoServico.
     * 
     * @return Uma instância de {@link IEstudoServico} contendo o valor do atributo estudoServico.
     */
    public IEstudoServico getEstudoServico() {
        return this.estudoServico;
    }

    /**
     * Atualiza a instância de estudoServico com o valor de estudoServico.
     * 
     * @param estudoServico Uma instância de IEstudoServico contendo o valor a ser atualizado.
     */
    public void setEstudoServico(IEstudoServico estudoServico) {
        this.estudoServico = estudoServico;
    }

    /**
     * Obtém o valor do atributo containerEstudoEdicao.
     * 
     * @return Uma instância de {@link EstudoBean} contendo o valor do atributo containerEstudoEdicao.
     */
    public EstudoBean getContainerEstudoEdicao() {
        return this.containerEstudoEdicao;
    }

    /**
     * Atualiza a instância de containerEstudoEdicao com o valor de containerEstudoEdicao.
     * 
     * @param containerEstudoEdicao Uma instância de EstudoBean contendo o valor a ser atualizado.
     */
    public void setContainerEstudoEdicao(EstudoBean containerEstudoEdicao) {
        this.containerEstudoEdicao = containerEstudoEdicao;
    }

    /**
     * Obtém o valor do atributo estudoEmEdicao.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo estudoEmEdicao.
     */
    public Boolean getEstudoEmEdicao() {
        return this.estudoEmEdicao;
    }

    /**
     * Atualiza a instância de estudoEmEdicao com o valor de estudoEmEdicao.
     * 
     * @param estudoEmEdicao Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setEstudoEmEdicao(Boolean estudoEmEdicao) {
        this.estudoEmEdicao = estudoEmEdicao;
    }

    /**
     * Obtém o valor do atributo informarMotivo.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo informarMotivo.
     */
    public Boolean getInformarMotivo() {
        return this.informarMotivo;
    }

    /**
     * Atualiza a instância de informarMotivo com o valor de informarMotivo.
     * 
     * @param informarMotivo Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setInformarMotivo(Boolean informarMotivo) {
        this.informarMotivo = informarMotivo;
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
     * Obtém o valor do atributo exibeFiltroBuscaDuplicacao.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo exibeFiltroBuscaDuplicacao.
     */
    public Boolean getExibeFiltroBuscaDuplicacao() {
        return this.exibeFiltroBuscaDuplicacao;
    }

    /**
     * Atualiza a instância de exibeFiltroBuscaDuplicacao com o valor de exibeFiltroBuscaDuplicacao.
     * 
     * @param exibeFiltroBuscaDuplicacao Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setExibeFiltroBuscaDuplicacao(Boolean exibeFiltroBuscaDuplicacao) {
        this.exibeFiltroBuscaDuplicacao = exibeFiltroBuscaDuplicacao;
    }

    /**
     * Obtém o valor do atributo potDuplicados.
     * 
     * @return Uma instância de {@link AnaliseEstudoDuplicadasLazyList} contendo o valor do atributo potDuplicados.
     */
    public AnaliseEstudoDuplicadasLazyList getPotDuplicados() {
        return this.potDuplicados;
    }

    /**
     * Atualiza a instância de potDuplicados com o valor de potDuplicados.
     * 
     * @param potDuplicados Uma instância de AnaliseEstudoDuplicadasLazyList contendo o valor a ser atualizado.
     */
    public void setPotDuplicados(AnaliseEstudoDuplicadasLazyList potDuplicados) {
        this.potDuplicados = potDuplicados;
    }

    /**
     * Obtém o valor do atributo selDuplicado.
     * 
     * @return Uma instância de {@link AnaliseEstudoBean} contendo o valor do atributo selDuplicado.
     */
    public AnaliseEstudoBean getSelDuplicado() {
        return this.selDuplicado;
    }

    /**
     * Atualiza a instância de selDuplicado com o valor de selDuplicado.
     * 
     * @param selDuplicado Uma instância de AnaliseEstudoBean contendo o valor a ser atualizado.
     */
    public void setSelDuplicado(AnaliseEstudoBean selDuplicado) {
        this.selDuplicado = selDuplicado;
    }

    /**
     * Obtém o valor do atributo duplicadosDataTable.
     * 
     * @return Uma instância de {@link DataTable} contendo o valor do atributo duplicadosDataTable.
     */
    public DataTable getDuplicadosDataTable() {
        return this.duplicadosDataTable;
    }

    /**
     * Atualiza a instância de duplicadosDataTable com o valor de duplicadosDataTable.
     * 
     * @param duplicadosDataTable Uma instância de DataTable contendo o valor a ser atualizado.
     */
    public void setDuplicadosDataTable(DataTable duplicadosDataTable) {
        this.duplicadosDataTable = duplicadosDataTable;
    }

    /**
     * Obtém o valor do atributo estatisticasDashBoard.
     * 
     * @return Uma instância de {@link CartesianChartModel} contendo o valor do atributo estatisticasDashBoard.
     */
    public CartesianChartModel getEstatisticasDashBoard() {
        return this.estatisticasDashBoard;
    }

    /**
     * Atualiza a instância de estatisticasDashBoard com o valor de estatisticasDashBoard.
     * 
     * @param estatisticasDashBoard Uma instância de CartesianChartModel contendo o valor a ser atualizado.
     */
    public void setEstatisticasDashBoard(CartesianChartModel estatisticasDashBoard) {
        this.estatisticasDashBoard = estatisticasDashBoard;
    }

    /**
     * Obtém o valor do atributo matrizEmEdicao.
     * 
     * @return Uma instância de {@link Boolean} contendo o valor do atributo matrizEmEdicao.
     */
    public Boolean getMatrizEmEdicao() {
        return this.matrizEmEdicao;
    }

    /**
     * Atualiza a instância de matrizEmEdicao com o valor de matrizEmEdicao.
     * 
     * @param matrizEmEdicao Uma instância de Boolean contendo o valor a ser atualizado.
     */
    public void setMatrizEmEdicao(Boolean matrizEmEdicao) {
        this.matrizEmEdicao = matrizEmEdicao;
    }

    /**
     * Obtém o valor do atributo tagcloudIncluidos.
     * 
     * @return Uma instância de {@link TagCloudModel} contendo o valor do atributo tagcloudIncluidos.
     */
    public TagCloudModel getTagcloudIncluidos() {
        return this.tagcloudIncluidos;
    }

    /**
     * Atualiza a instância de tagcloudIncluidos com o valor de tagcloudIncluidos.
     * 
     * @param tagcloudIncluidos Uma instância de TagCloudModel contendo o valor a ser atualizado.
     */
    public void setTagcloudIncluidos(TagCloudModel tagcloudIncluidos) {
        this.tagcloudIncluidos = tagcloudIncluidos;
    }

    /**
     * Obtém o valor do atributo tagcloudExcluidos.
     * 
     * @return Uma instância de {@link TagCloudModel} contendo o valor do atributo tagcloudExcluidos.
     */
    public TagCloudModel getTagcloudExcluidos() {
        return this.tagcloudExcluidos;
    }

    /**
     * Atualiza a instância de tagcloudExcluidos com o valor de tagcloudExcluidos.
     * 
     * @param tagcloudExcluidos Uma instância de TagCloudModel contendo o valor a ser atualizado.
     */
    public void setTagcloudExcluidos(TagCloudModel tagcloudExcluidos) {
        this.tagcloudExcluidos = tagcloudExcluidos;
    }

    /**
     * Obtém o valor do atributo tagcloudGeral.
     * 
     * @return Uma instância de {@link TagCloudModel} contendo o valor do atributo tagcloudGeral.
     */
    public TagCloudModel getTagcloudGeral() {
        return this.tagcloudGeral;
    }

    /**
     * Atualiza a instância de tagcloudGeral com o valor de tagcloudGeral.
     * 
     * @param tagcloudGeral Uma instância de TagCloudModel contendo o valor a ser atualizado.
     */
    public void setTagcloudGeral(TagCloudModel tagcloudGeral) {
        this.tagcloudGeral = tagcloudGeral;
    }

    /**
     * Obtém o valor do atributo estatisticaEtapaRevisao.
     * 
     * @return Uma instância de {@link EstatisticaEtapaRevisao} contendo o valor do atributo
     *         estatisticaEtapaRevisao.
     */
    public EstatisticaEtapaRevisao getEstatisticaEtapaRevisao() {
        return this.estatisticaEtapaRevisao;
    }

    /**
     * Atualiza a instância de estatisticaEtapaRevisao com o valor de estatisticaEtapaRevisao.
     * 
     * @param estatisticaEtapaRevisao Uma instância de EstatisticaEtapaRevisao contendo o valor a ser atualizado.
     */
    public void setEstatisticaEtapaRevisao(EstatisticaEtapaRevisao estatisticaEtapaRevisao) {
        this.estatisticaEtapaRevisao = estatisticaEtapaRevisao;
    }

    /**
     * Obtém o valor do atributo estatisticasEsforco.
     * 
     * @return Uma instância de {@link EstatisticasEsforcoEtapaRevisaoBean} contendo o valor do atributo
     *         estatisticasEsforco.
     */
    public EstatisticasEsforcoEtapaRevisaoBean getEstatisticasEsforco() {
        return this.estatisticasEsforco;
    }

    /**
     * Atualiza a instância de estatisticasEsforco com o valor de estatisticasEsforco.
     * 
     * @param estatisticasEsforco Uma instância de EstatisticasEsforcoEtapaRevisaoBean contendo o valor a ser
     *        atualizado.
     */
    public void setEstatisticasEsforco(EstatisticasEsforcoEtapaRevisaoBean estatisticasEsforco) {
        this.estatisticasEsforco = estatisticasEsforco;
    }

    /**
     * Obtém o valor do atributo estatisticasEstudosAno.
     * 
     * @return Uma instância de {@link PieChartModel} contendo o valor do atributo estatisticasEstudosAno.
     */
    public PieChartModel getEstatisticasEstudosAno() {
        return this.estatisticasEstudosAno;
    }

    /**
     * Atualiza a instância de estatisticasEstudosAno com o valor de estatisticasEstudosAno.
     * 
     * @param estatisticasEstudosAno Uma instância de PieChartModel contendo o valor a ser atualizado.
     */
    public void setEstatisticasEstudosAno(PieChartModel estatisticasEstudosAno) {
        this.estatisticasEstudosAno = estatisticasEstudosAno;
    }

    /**
     * Obtém o valor do atributo estatisticasEstudosAnalisadosAno.
     * 
     * @return Uma instância de {@link PieChartModel} contendo o valor do atributo
     *         estatisticasEstudosAnalisadosAno.
     */
    public PieChartModel getEstatisticasEstudosAnalisadosAno() {
        return this.estatisticasEstudosAnalisadosAno;
    }

    /**
     * Atualiza a instância de estatisticasEstudosAnalisadosAno com o valor de estatisticasEstudosAnalisadosAno.
     * 
     * @param estatisticasEstudosAnalisadosAno Uma instância de PieChartModel contendo o valor a ser atualizado.
     */
    public void setEstatisticasEstudosAnalisadosAno(PieChartModel estatisticasEstudosAnalisadosAno) {
        this.estatisticasEstudosAnalisadosAno = estatisticasEstudosAnalisadosAno;
    }

    /**
     * Obtém o valor do atributo estatisticasAnaliseEstudosIncluidosAno.
     * 
     * @return Uma instância de {@link PieChartModel} contendo o valor do atributo
     *         estatisticasAnaliseEstudosIncluidosAno.
     */
    public PieChartModel getEstatisticasAnaliseEstudosIncluidosAno() {
        return this.estatisticasAnaliseEstudosIncluidosAno;
    }

    /**
     * Atualiza a instância de estatisticasAnaliseEstudosIncluidosAno com o valor de
     * estatisticasAnaliseEstudosIncluidosAno.
     * 
     * @param estatisticasAnaliseEstudosIncluidosAno Uma instância de PieChartModel contendo o valor a ser
     *        atualizado.
     */
    public void setEstatisticasAnaliseEstudosIncluidosAno(PieChartModel estatisticasAnaliseEstudosIncluidosAno) {
        this.estatisticasAnaliseEstudosIncluidosAno = estatisticasAnaliseEstudosIncluidosAno;
    }

    /**
     * Obtém o valor do atributo estatisticasAnaliseEstudosExcluidosAno.
     * 
     * @return Uma instância de {@link PieChartModel} contendo o valor do atributo
     *         estatisticasAnaliseEstudosExcluidosAno.
     */
    public PieChartModel getEstatisticasAnaliseEstudosExcluidosAno() {
        return this.estatisticasAnaliseEstudosExcluidosAno;
    }

    /**
     * Atualiza a instância de estatisticasAnaliseEstudosExcluidosAno com o valor de
     * estatisticasAnaliseEstudosExcluidosAno.
     * 
     * @param estatisticasAnaliseEstudosExcluidosAno Uma instância de PieChartModel contendo o valor a ser
     *        atualizado.
     */
    public void setEstatisticasAnaliseEstudosExcluidosAno(PieChartModel estatisticasAnaliseEstudosExcluidosAno) {
        this.estatisticasAnaliseEstudosExcluidosAno = estatisticasAnaliseEstudosExcluidosAno;
    }

    /**
     * Obtém o valor do atributo autoresIncluidos.
     * 
     * @return Uma instância de {@link List<AutorBean>} contendo o valor do atributo autoresIncluidos.
     */
    public List<AutorBean> getAutoresIncluidos() {
        return this.autoresIncluidos;
    }

    /**
     * Atualiza a instância de autoresIncluidos com o valor de autoresIncluidos.
     * 
     * @param autoresIncluidos Uma instância de List<AutorBean> contendo o valor a ser atualizado.
     */
    public void setAutoresIncluidos(List<AutorBean> autoresIncluidos) {
        this.autoresIncluidos = autoresIncluidos;
    }

    /**
     * Obtém o valor do atributo autoresExcluidos.
     * 
     * @return Uma instância de {@link List<AutorBean>} contendo o valor do atributo autoresExcluidos.
     */
    public List<AutorBean> getAutoresExcluidos() {
        return this.autoresExcluidos;
    }

    /**
     * Atualiza a instância de autoresExcluidos com o valor de autoresExcluidos.
     * 
     * @param autoresExcluidos Uma instância de List<AutorBean> contendo o valor a ser atualizado.
     */
    public void setAutoresExcluidos(List<AutorBean> autoresExcluidos) {
        this.autoresExcluidos = autoresExcluidos;
    }

    /**
     * Obtém o valor do atributo autores.
     * 
     * @return Uma instância de {@link List<AutorBean>} contendo o valor do atributo autores.
     */
    public List<AutorBean> getAutores() {
        return this.autores;
    }

    /**
     * Atualiza a instância de autores com o valor de autores.
     * 
     * @param autores Uma instância de List<AutorBean> contendo o valor a ser atualizado.
     */
    public void setAutores(List<AutorBean> autores) {
        this.autores = autores;
    }

}
