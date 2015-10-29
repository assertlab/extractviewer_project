/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.FiltroPropriedade;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.OrdenacaoPropriedade;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AnaliseEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AutorBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaEsforcoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasConclusaoEtapaRevisaoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasEsforcoEtapaRevisaoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticasEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.PalavraChaveBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.ICriterioDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEtapaAnaliseDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IHistoricoAnaliseEstudoDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IRevisorDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.AnaliseEstudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Criterio;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EtapaAnalise;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.HistoricoAnaliseEstudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;

/**
 * Representa à implementação da camada de negócios da entidade {@link AnaliseEstudo}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:34:09
 */
@Service(value = "analiseEstudoServico")
public class AnaliseEstudoServico extends AbstractServico<AnaliseEstudo, AnaliseEstudoBean> implements
        IAnaliseEstudoServico {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 3548498707666989474L;

    /**
     * Representa o mecanismo de log da classe.
     */
    protected static Log LOG = LogFactory.getLog(AnaliseEstudoServico.class);

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link AnaliseEstudo}.
     */
    @Autowired
    private IAnaliseEstudoDAO dao;

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link Criterio}.
     */
    @Autowired
    private ICriterioDAO criterioDAO;

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link Revisor}.
     */
    @Autowired
    private IRevisorDAO revisorDAO;

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link EtapaAnalise}.
     */
    @Autowired
    private IEtapaAnaliseDAO etapaDAO;

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link HistoricoAnaliseEstudo}.
     */
    @Autowired
    private IHistoricoAnaliseEstudoDAO historicoAnaliseDAO;

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getDao()
     */
    @Override
    public IAnaliseEstudoDAO getDao() {
        return dao;
    }

    /**
     * Atualiza a instância de dao com o valor de dao.
     * 
     * @param dao Uma instância de IAnaliseEstudoDAO contendo o valor a ser atualizado.
     */
    public void setDao(IAnaliseEstudoDAO dao) {
        this.dao = dao;
    }

    /**
     * Atualiza a instância de criterioDAO com o valor de criterioDAO.
     * 
     * @param criterioDAO Uma instância de ICriterioDAO contendo o valor a ser atualizado.
     */
    public void setCriterioDAO(ICriterioDAO criterioDAO) {
        this.criterioDAO = criterioDAO;
    }

    /**
     * Atualiza a instância de revisorDAO com o valor de revisorDAO.
     * 
     * @param revisorDAO Uma instância de IRevisorDAO contendo o valor a ser atualizado.
     */
    public void setRevisorDAO(IRevisorDAO revisorDAO) {
        this.revisorDAO = revisorDAO;
    }

    /**
     * Atualiza a instância de historicoAnaliseDAO com o valor de historicoAnaliseDAO.
     * 
     * @param historicoAnaliseDAO Uma instância de IHistoricoAnaliseEstudoDAO contendo o valor a ser atualizado.
     */
    public void setHistoricoAnaliseDAO(IHistoricoAnaliseEstudoDAO historicoAnaliseDAO) {
        this.historicoAnaliseDAO = historicoAnaliseDAO;
    }

    /**
     * Atualiza a instância de etapaDAO com o valor de etapaDAO.
     * 
     * @param etapaDAO Uma instância de IEtapaAnaliseDAO contendo o valor a ser atualizado.
     */
    public void setEtapaDAO(IEtapaAnaliseDAO etapaDAO) {
        this.etapaDAO = etapaDAO;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getLog()
     */
    @Override
    public Log getLog() {
        return LOG;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#listar(java.lang.Long)
     */
    @Override
    public List<AnaliseEstudoBean> listar(Long idEtapaRevisao, Long idRevisor) {
        List<AnaliseEstudoBean> analisesBean = null;

        if (idEtapaRevisao == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ID_REVISAO_OBRIGATORIO,
                    TipoErro.VALIDACAO));
        }

        List<AnaliseEstudo> analises = this.dao.listar(idEtapaRevisao, idRevisor);

        if (analises != null && !analises.isEmpty()) {
            analisesBean = new ArrayList<AnaliseEstudoBean>();

            for (AnaliseEstudo analiseEstudo : analises) {
                if (analiseEstudo != null) {
                    analisesBean.add(analiseEstudo.getBean());
                }
            }
        }

        return analisesBean;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#qtdTotal(java.lang.Long)
     */
    @Override
    public Integer qtdTotal(Long idAnalise, Long idRevisor, List<FiltroPropriedade> filtros) {
        Integer qtd = 0;

        if (idAnalise == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ID_REVISAO_OBRIGATORIO,
                    TipoErro.VALIDACAO));
        }

        qtd = this.dao.qtdTotal(idAnalise, idRevisor, filtros);

        return qtd;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#listar(java.lang.Long,
     *      java.util.List, java.util.List, int, int)
     */
    @Override
    public List<AnaliseEstudoBean> listar(Long idAnalise, Long idRevisor, List<FiltroPropriedade> filtros,
            List<OrdenacaoPropriedade> ordenacoes, int inicio, int qtd) {
        List<AnaliseEstudoBean> analisesBean = null;

        if (idAnalise == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ID_REVISAO_OBRIGATORIO,
                    TipoErro.VALIDACAO));
        }

        List<AnaliseEstudo> analises = this.dao.listar(idAnalise, idRevisor, filtros, ordenacoes, inicio, qtd);

        if (analises != null && !analises.isEmpty()) {
            analisesBean = new ArrayList<AnaliseEstudoBean>();

            for (AnaliseEstudo analiseEstudo : analises) {
                if (analiseEstudo != null) {
                    analisesBean.add(analiseEstudo.getBean());
                }
            }
        }

        return analisesBean;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#listarNaoConcluidas(java.lang.Long)
     */
    @Override
    public List<AnaliseEstudoBean> listarNaoConcluidas(Long idAnalise, Long idRevisor) {
        List<AnaliseEstudoBean> analisesBean = null;

        if (idAnalise == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ID_REVISAO_OBRIGATORIO,
                    TipoErro.VALIDACAO));
        }

        List<AnaliseEstudo> analises = this.dao.listarNaoConcluidos(idAnalise, idRevisor);

        if (analises != null && !analises.isEmpty()) {
            analisesBean = new ArrayList<AnaliseEstudoBean>();

            for (AnaliseEstudo analiseEstudo : analises) {
                if (analiseEstudo != null) {
                    analisesBean.add(analiseEstudo.getBean());
                }
            }
        }

        return analisesBean;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#qtdTotalNaoConcluidas(java.lang.Long)
     */
    @Override
    public Integer qtdTotalNaoConcluidas(Long idAnalise, Long idRevisor) {
        Integer qtd = 0;

        if (idAnalise == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ID_REVISAO_OBRIGATORIO,
                    TipoErro.VALIDACAO));
        }

        qtd = this.dao.qtdTotalNaoConcluidos(idAnalise, idRevisor);

        return qtd;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#listarNaoConcluidas(java.lang.Long,
     *      int, int)
     */
    @Override
    public List<AnaliseEstudoBean> listarNaoConcluidas(Long idAnalise, Long idRevisor, int inicio, int qtd) {
        List<AnaliseEstudoBean> analisesBean = null;

        if (idAnalise == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ID_REVISAO_OBRIGATORIO,
                    TipoErro.VALIDACAO));
        }

        List<AnaliseEstudo> analises = this.dao.listarNaoConcluidos(idAnalise, idRevisor, inicio, qtd);

        if (analises != null && !analises.isEmpty()) {
            analisesBean = new ArrayList<AnaliseEstudoBean>();

            for (AnaliseEstudo analiseEstudo : analises) {
                if (analiseEstudo != null) {
                    analisesBean.add(analiseEstudo.getBean());
                }
            }
        }

        return analisesBean;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#listarConcluidas(java.lang.Long)
     */
    @Override
    public List<AnaliseEstudoBean> listarConcluidas(Long idAnalise, Long idRevisor) {
        List<AnaliseEstudoBean> analisesBean = null;

        if (idAnalise == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ID_REVISAO_OBRIGATORIO,
                    TipoErro.VALIDACAO));
        }

        List<AnaliseEstudo> analises = this.dao.listarConcluidos(idAnalise, idRevisor);

        if (analises != null && !analises.isEmpty()) {
            analisesBean = new ArrayList<AnaliseEstudoBean>();

            for (AnaliseEstudo analiseEstudo : analises) {
                if (analiseEstudo != null) {
                    analisesBean.add(analiseEstudo.getBean());
                }
            }
        }

        return analisesBean;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#qtdTotalConcluidas(java.lang.Long)
     */
    @Override
    public Integer qtdTotalConcluidas(Long idAnalise, Long idRevisor) {
        Integer qtd = 0;

        if (idAnalise == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ID_REVISAO_OBRIGATORIO,
                    TipoErro.VALIDACAO));
        }

        qtd = this.dao.qtdTotalConcluidos(idAnalise, idRevisor);

        return qtd;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#listarConcluidas(java.lang.Long,
     *      int, int)
     */
    @Override
    public List<AnaliseEstudoBean> listarConcluidas(Long idAnalise, Long idRevisor, int inicio, int qtd) {
        List<AnaliseEstudoBean> analisesBean = null;

        if (idAnalise == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ID_REVISAO_OBRIGATORIO,
                    TipoErro.VALIDACAO));
        }

        List<AnaliseEstudo> analises = this.dao.listarConcluidos(idAnalise, idRevisor, inicio, qtd);

        if (analises != null && !analises.isEmpty()) {
            analisesBean = new ArrayList<AnaliseEstudoBean>();

            for (AnaliseEstudo analiseEstudo : analises) {
                if (analiseEstudo != null) {
                    analisesBean.add(analiseEstudo.getBean());
                }
            }
        }

        return analisesBean;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#copiarDadosParaAlterarEntidade(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean,
     *      br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
     */
    @Override
    protected void copiarDadosParaAlterarEntidade(AnaliseEstudoBean bean, AnaliseEstudo entidade) {

        entidade.setComentario(bean.getComentario());
        entidade.setConcluido(bean.getConcluido());
        entidade.setDataUltimaAlteracao(LocalDateTime.now());
        entidade.setIncluido(bean.getIncluido());

        if (bean.getCriterio() != null && bean.getCriterio().getId() != null && bean.getCriterio().getId() > 0) {
            entidade.setCriterio(this.criterioDAO.recuperar(bean.getCriterio().getId()));
        } else {
            entidade.setCriterio(null);
        }

        if (bean.getMatriz() != null) {
            if (entidade.getMatriz() == null
                    || (entidade.getMatriz() != null && entidade.getMatriz().getId() != null && !entidade.getMatriz()
                            .getId().equals(bean.getMatriz().getId()))) {
                // troca de matriz
                entidade.setMatriz(this.dao.recuperar(bean.getMatriz().getId()));
            }
        }
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#coletarEstatisticasDeConclusao(java.lang.Long)
     */
    @Override
    public EstatisticasConclusaoEtapaRevisaoBean coletarEstatisticasDeConclusao(Long idAnalise, Long idRevisor) {
        EstatisticasConclusaoEtapaRevisaoBean estatisticas = new EstatisticasConclusaoEtapaRevisaoBean();

        if (idAnalise == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ESTATISTICAS_ID_REVISAO_OBRIGATORIO,
                    TipoErro.VALIDACAO));
        }

        estatisticas.setQtdTotal(this.dao.qtdTotal(idAnalise, idRevisor, null));

        estatisticas.setQtdConcluidos(this.dao.qtdTotalConcluidos(idAnalise, idRevisor));

        estatisticas.setQtdNaoConcluidos(this.dao.qtdTotalNaoConcluidos(idAnalise, idRevisor));

        estatisticas.setQtdIncluidos(this.dao.qtdTotalIncluidos(idAnalise, idRevisor));
        estatisticas.setQtdExcluidos(this.dao.qtdTotalExcluidos(idAnalise, idRevisor));

        estatisticas.setDetalheCriterios(this.dao.coletarCriteriosAplicados(idAnalise, idRevisor));

        return estatisticas;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#coletarEstatisticasDeEsforco(java.lang.Long)
     */
    @Override
    public EstatisticasEsforcoEtapaRevisaoBean coletarEstatisticasDeEsforco(Long idEtapa, Long idRevisor) {

        EstatisticasEsforcoEtapaRevisaoBean estatisticas = new EstatisticasEsforcoEtapaRevisaoBean();

        if (idEtapa == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ESTATISTICAS_ID_REVISAO_OBRIGATORIO,
                    TipoErro.VALIDACAO));
        }

        EtapaAnalise etapa = this.etapaDAO.recuperar(idEtapa);

        if (etapa == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ESTATISTICAS_REVISAO_NAO_IDENTIFICADA,
                    TipoErro.VALIDACAO));
        }

        // montar a lista de dias de trabalho disponíveis na execução da etapa
        // de revisão
        List<EstatisticaEsforcoBean> detalheEsforcos = new ArrayList<EstatisticaEsforcoBean>();
        int qtdDias =
                Days.daysBetween(etapa.getDataInicio().toLocalDate(), etapa.getDataConclusao().toLocalDate()).getDays() + 1;
        int qtdDiasUteis = 0;

        for (int i = 0; i < qtdDias; i++) {
            LocalDate dia = etapa.getDataInicio().toLocalDate().withFieldAdded(DurationFieldType.days(), i);

            // conta os dias de trabalho ignorando finais de semana.
            // FIXME: ignorar feriados
            // if (dia.getDayOfWeek() != DateTimeConstants.SATURDAY &&
            // dia.getDayOfWeek() != DateTimeConstants.SUNDAY) {
            qtdDiasUteis++;
            // }

            detalheEsforcos.add(new EstatisticaEsforcoBean(dia, 0l, 0l));
        }

        // extremos da lista (0.0) último dia onde deveria n ter nada que nem no
        // scrum!
        detalheEsforcos.add(new EstatisticaEsforcoBean(null, 0l, 0l));

        EstatisticaEsforcoBean ultimoDia =
                new EstatisticaEsforcoBean(etapa.getDataInicio().toLocalDate()
                        .withFieldAdded(DurationFieldType.days(), qtdDias), 0l, 0l);

        ultimoDia.setMarcador(true);
        detalheEsforcos.add(ultimoDia);

        // recuperar os esforços realizados
        List<EstatisticaEsforcoBean> esforcoRealizado = this.dao.coletarEstatisticasEsforco(idEtapa, idRevisor);

        if (esforcoRealizado != null && !esforcoRealizado.isEmpty()) {

            /*
             * verificar se os dias de trabalho existem na lista de esforços realizados. Atende ao caso da pessoa
             * ter comecado a trabalhar antes da data de início ou depois.
             */
            for (EstatisticaEsforcoBean realizadoBean : esforcoRealizado) {
                if (realizadoBean != null) {
                    if (detalheEsforcos != null && !detalheEsforcos.contains(realizadoBean)) {
                        realizadoBean.setQtdPlanejado(null);
                        realizadoBean.setMarcador(true);
                        detalheEsforcos.add(realizadoBean);
                    }
                }
            }

        }

        // ordena a lista
        Collections.sort(detalheEsforcos);

        // descobrir a quantidade de estudos por dia
        Integer qtdAnalises = this.dao.qtdTotal(idEtapa, idRevisor, null);
        Integer media = qtdAnalises / qtdDiasUteis;
        Long valorDia = qtdAnalises.longValue();
        Long acumuladoDia = 0l;
        // aplicar o esforco planejado e realizado no dia.
        for (EstatisticaEsforcoBean detalhe : detalheEsforcos) {
            if (detalhe != null && detalhe.getData() != null) {

                // if (detalhe.getData().getDayOfWeek() !=
                // DateTimeConstants.SATURDAY &&
                // detalhe.getData().getDayOfWeek() != DateTimeConstants.SUNDAY)
                // {
                if (!detalhe.isMarcador()) {
                    valorDia -= media;
                    detalhe.setQtdPlanejado(valorDia);
                }
                // }

                if (esforcoRealizado != null && !esforcoRealizado.isEmpty()) {
                    for (EstatisticaEsforcoBean realizadoBean : esforcoRealizado) {
                        if (realizadoBean != null && detalhe.getData().equals(realizadoBean.getData())) {

                            acumuladoDia += realizadoBean.getQtdRealizado();
                            detalhe.setQtdRealizado(acumuladoDia);
                            break;
                        }
                    }
                }
            }
        }

        estatisticas.setDetalheEsforcos(detalheEsforcos);

        return estatisticas;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#alterar(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean)
     */
    @Override
    @Transactional(readOnly = false)
    public void alterar(AnaliseEstudoBean analiseBean) {

        if (analiseBean == null) {
            throw new MapeamentoException("Os dados do bean encontram-se nulos", TipoErro.VALIDACAO);
        }

        AnaliseEstudo analise = this.getDao().recuperar(analiseBean.getBeanID());

        if (analise == null) {
            throw new MapeamentoException("Os dados da análise não foram encontrados", TipoErro.VALIDACAO);
        }

        // gerar o historico de alteracao
        Revisor revisor = this.revisorDAO.recuperar(analiseBean.getRevisor().getId());

        if (revisor == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ANALISE_REVISOR_NAO_ENCONTRADO,
                    TipoErro.VALIDACAO));
        }

        LocalDateTime now = LocalDateTime.now();
        HistoricoAnaliseEstudo alteracao = new HistoricoAnaliseEstudo();

        alteracao.setDataInclusao(now);
        alteracao.setDataUltimaAlteracao(now);
        alteracao.setAnalise(analise);

        alteracao.setRevisor(revisor);
        alteracao.setDadosAnteriores(analise.toString());
        alteracao.setDiferenca(analise.getDiferencas(analiseBean.getEntidade(), "alteracoes", "estudo", "etapa"));

        // altera os dados
        this.copiarDadosParaAlterarEntidade(analiseBean, analise);

        this.validar(analise);
        this.getDao().alterar(analise);

        // finaliza o histórico de alteracao - desconsidera os campos abaixo
        // pois os mesmos não são alterados por essência.
        alteracao.setDadosAtuais(analise.toString());

        this.historicoAnaliseDAO.incluir(alteracao);

        LOG.info("Usuário: [" + analiseBean.getRevisor().getUsuario() + "] alterou analise: [" + analiseBean.getId()
                + " - " + analiseBean.getEstudo().getTitulo() + "] status:[" + analiseBean.getConcluido()
                + "] Incluido:[" + analiseBean.getIncluido() + "]");
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#inverterOrdemMatrizEstudo(br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AnaliseEstudoBean,
     *      br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AnaliseEstudoBean)
     */
    @Override
    @Transactional(readOnly = false)
    public AnaliseEstudoBean inverterOrdemMatrizEstudo(AnaliseEstudoBean analiseBean, AnaliseEstudoBean matrizAtualBean) {

        List<Erro> erros = new ArrayList<Erro>();

        if (analiseBean == null) {
            erros.add(new Erro(AnaliseEstudoErroEnum.ERRO_ANALISE_MATRIZ_OBRIGATORIA, TipoErro.VALIDACAO));
        }

        if (matrizAtualBean == null) {
            erros.add(new Erro(AnaliseEstudoErroEnum.ERRO_ANALISE_MATRIZ_OBRIGATORIA, TipoErro.VALIDACAO));
        }

        if (!erros.isEmpty()) {
            throw new MapeamentoException(erros);
        }

        AnaliseEstudo matrizAtual = this.dao.recuperar(matrizAtualBean.getId());
        AnaliseEstudo analise = this.dao.recuperar(analiseBean.getId());

        if (matrizAtual == null) {
            erros.add(new Erro(AnaliseEstudoErroEnum.ERRO_ANALISE_MATRIZ_INEXISTENTE, TipoErro.VALIDACAO));
        }

        if (analise == null) {
            erros.add(new Erro(AnaliseEstudoErroEnum.ERRO_ANALISE_INEXISTENTE, TipoErro.VALIDACAO));
        }

        // inicia a geracao do histórico de alteracao
        Revisor revisor = this.revisorDAO.recuperar(analiseBean.getRevisor().getId());

        if (revisor == null) {
            erros.add(new Erro(AnaliseEstudoErroEnum.ERRO_ANALISE_REVISOR_NAO_ENCONTRADO, TipoErro.VALIDACAO));
        }

        if (!erros.isEmpty()) {
            throw new MapeamentoException(erros);
        }

        LocalDateTime now = LocalDateTime.now();
        HistoricoAnaliseEstudo alteracao = new HistoricoAnaliseEstudo();

        alteracao.setDataInclusao(now);
        alteracao.setDataUltimaAlteracao(now);
        alteracao.setAnalise(analise);

        alteracao.setRevisor(revisor);
        alteracao.setDadosAnteriores(analise.toString());
        alteracao.setDiferenca("Matriz: null; Criterio:null Concluido:false");

        // Altera os dados da análise
        analise.setMatriz(null);
        analise.setCriterio(null);
        analise.setConcluido(false);

        // conclui a geração do histórico
        alteracao.setDadosAtuais(analise.toString());

        if (analise.getAlteracoes() == null) {
            analise.setAlteracoes(new ArrayList<HistoricoAnaliseEstudo>());
        }

        analise.getAlteracoes().add(alteracao);
        this.dao.alterar(analise);

        // atualiza todos os estudos da qual a análise anterior era pai para
        // manter a consistência dos dados em cascata!
        List<AnaliseEstudo> analisesFilhas = this.dao.recuperarAnalisesFilhas(matrizAtual.getId());

        Criterio critDuplicado = criterioDAO.buscarCriterio("Estudo duplicado.");

        if (analisesFilhas == null) {
            analisesFilhas = new ArrayList<AnaliseEstudo>();
        } else {
            // altera os dados das filhas da atual matriz.
            for (AnaliseEstudo analiseEstudo : analisesFilhas) {
                // não inclui referências cíclicas.
                if (analiseEstudo != null && analiseEstudo.getId() != null && analiseEstudo.getId() != analise.getId()) {
                    analiseEstudo.setMatriz(analise);
                    analise.setCriterio(critDuplicado);
                    analise.setComentario("Duplicação manual com inversão de matriz.");
                    analise.setConcluido(false);
                }
            }
        }

        // altera os dados da matriz atual
        matrizAtual.setMatriz(analise);
        matrizAtual.setCriterio(critDuplicado);
        matrizAtual.setComentario("Duplicação manual com inversão de matriz.");
        matrizAtual.setConcluido(false);

        analisesFilhas.add(matrizAtual);

        this.dao.alterar(analisesFilhas);

        return analise.getBean();

    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#procurarPotenciaisDuplicados(java.util.List)
     */
    @Override
    public List<AnaliseEstudoBean> procurarPotenciaisDuplicados(List<FiltroPropriedade> filtros,
            List<OrdenacaoPropriedade> ordenacoes, int inicio, int qtd) {

        List<AnaliseEstudoBean> analises = null;

        if (filtros == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ANALISE_DUPLICADAS_FILTRO_NAO_INFORMADO,
                    TipoErro.VALIDACAO));
        }

        List<AnaliseEstudo> entAnalises = this.dao.procurarPotenciaisDuplicados(filtros, ordenacoes, inicio, qtd);

        if (entAnalises != null) {
            analises = new ArrayList<AnaliseEstudoBean>();

            for (AnaliseEstudo entAnalise : entAnalises) {
                if (entAnalise != null) {
                    analises.add(entAnalise.getBean());
                }
            }
        }

        return analises;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#qtdPotenciaisDuplicados(java.lang.Long,
     *      java.util.List)
     */
    @Override
    public Integer qtdPotenciaisDuplicados(List<FiltroPropriedade> filtros) {
        Integer qtd = 0;

        if (filtros == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_ANALISE_DUPLICADAS_FILTRO_NAO_INFORMADO,
                    TipoErro.VALIDACAO));
        }

        qtd = this.dao.qtdTotalPotenciaisDuplicados(filtros);

        return qtd;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#validarAssociacaoDuplicado(br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AnaliseEstudoBean,
     *      br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AnaliseEstudoBean)
     */
    @Override
    public void validarAssociacaoDuplicado(AnaliseEstudoBean analiseBean, AnaliseEstudoBean novaMatrizBean) {

        List<Erro> erros = new ArrayList<Erro>();

        if (analiseBean == null || analiseBean.getId() == null || analiseBean.getId() <= 0) {
            erros.add(new Erro(AnaliseEstudoErroEnum.ERRO_VALIDACAO_DUPLICADAS_MATRIZ_NAO_INFORMADA, TipoErro.VALIDACAO));
        }

        if (novaMatrizBean == null || novaMatrizBean.getId() == null || novaMatrizBean.getId() <= 0) {
            erros.add(new Erro(AnaliseEstudoErroEnum.ERRO_VALIDACAO_DUPLICADAS_DUPLICATA_NAO_INFORMADA,
                    TipoErro.VALIDACAO));
        }

        if (!erros.isEmpty()) {
            throw new MapeamentoException(erros);
        }

        AnaliseEstudo analise = this.dao.recuperar(analiseBean.getId());

        if (analise == null) {
            erros.add(new Erro(AnaliseEstudoErroEnum.ERRO_VALIDACAO_DUPLICADAS_DUPLICATA_NAO_INDENTIFICADA,
                    TipoErro.VALIDACAO));
        }

        AnaliseEstudo novaMatriz = this.dao.recuperar(novaMatrizBean.getId());

        if (novaMatriz == null) {
            throw new MapeamentoException(new Erro(
                    AnaliseEstudoErroEnum.ERRO_VALIDACAO_DUPLICADAS_MATRIZ_NAO_IDENTIFICADA, TipoErro.VALIDACAO));
        }

        if (novaMatriz.getMatriz() != null && novaMatriz.getMatriz().getId() != null && novaMatriz.getId() > 0) {
            /*
             * Não permite que uma análise que já filha de uma análise matriz seja apontada como matriz de um outro
             * estudo. O objetivo é evitar duplicações cíclicas. Neste caso o usuário deve identificar a matriz
             * desta análise e apontar para ela e não para as suas filhas.
             */
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_VALIDACAO_DUPLICADAS_NAO_PERMITIDA,
                    TipoErro.VALIDACAO, analiseBean.getId(), novaMatriz.getId(), novaMatriz.getMatriz().getId()));

        }

    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#listarPalavrasChaveEstudos(java.lang.Long,
     *      java.lang.Boolean)
     */
    @Override
    public HashMap<String, PalavraChaveBean> listarPalavrasChaveEstudos(Long idEtapaRevisao, Long idRevisor,
            Boolean incluidos) {

        HashMap<String, PalavraChaveBean> palavrasChaves = null;

        List<String> palavrasChavesBrutas = this.dao.listarPalavrasChaveEstudos(idEtapaRevisao, idRevisor, incluidos);

        if (palavrasChavesBrutas != null && !palavrasChavesBrutas.isEmpty()) {

            palavrasChaves = new HashMap<String, PalavraChaveBean>();
            PalavraChaveBean palavraChave = null;
            String[] tokens = null;

            for (String palavraBruta : palavrasChavesBrutas) {
                if (!MapeamentoUtil.isEmpty(palavraBruta)) {
                    palavraBruta = WordUtils.capitalize(palavraBruta);
                    palavraBruta = StringUtils.replace(palavraBruta, " ", "_");
                    palavraBruta = StringUtils.replace(palavraBruta, ";_", ";");

                    tokens = StringUtils.split(palavraBruta, ";");

                    for (String palavrinha : tokens) {
                        palavrinha = tratarStringComPontoEVirgula(palavrinha, true);
                        if (!MapeamentoUtil.isEmpty(palavrinha)) {
                            palavraChave = palavrasChaves.get(palavrinha);
                            if (palavraChave == null) {
                                palavraChave = new PalavraChaveBean();
                                palavraChave.setQuantidade(0);
                                palavraChave.setPalavra(palavrinha);

                                palavrasChaves.put(palavraChave.getPalavra(), palavraChave);
                            }

                            palavraChave.setQuantidade(palavraChave.getQuantidade() + 1);
                        }
                    }
                }
            }

        }

        return palavrasChaves;
    }

    private String tratarStringComPontoEVirgula(String palavraBruta, boolean strip) {
        String palavraTratada = "";

        if (strip) {
            palavraTratada = StringUtils.strip(palavraBruta);
            palavraTratada = StringUtils.lowerCase(palavraTratada);
            palavraTratada = WordUtils.capitalize(palavraTratada);
        } else {
            palavraTratada = StringUtils.trim(palavraBruta);
        }

        return palavraTratada;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#estatisticaQuantidadeEstudosAnalisadosPorAno(java.lang.Boolean)
     */
    @Override
    public List<EstatisticasEstudoBean> estatisticaQuantidadeEstudosAnalisadosPorAno(Boolean incluidos,
            Long idEtapaRevisao, Long idRevisor) {

        if (incluidos == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_VALIDACAO_QUANTITATIVO_ESTUDOS_NULO,
                    TipoErro.VALIDACAO));
        }

        return this.dao.estatisticaQuantidadeEstudosAnalisadosPorAno(idEtapaRevisao, idRevisor, incluidos);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#listarAutoresEstudos(java.lang.Long,
     *      java.lang.Boolean)
     */
    @Override
    public HashMap<String, AutorBean> listarAutoresEstudos(Long idEtapaRevisao, Long idRevisor, Boolean incluidos) {

        HashMap<String, AutorBean> autores = null;

        List<String> autoresBrutos = this.dao.listarAutoresEstudos(idEtapaRevisao, idRevisor, incluidos);

        if (autoresBrutos != null && !autoresBrutos.isEmpty()) {

            autores = new HashMap<String, AutorBean>();
            AutorBean autor = null;
            String[] tokens = null;

            for (String palavraBruta : autoresBrutos) {
                if (!MapeamentoUtil.isEmpty(palavraBruta)) {
                    tokens = StringUtils.split(palavraBruta, ",");

                    for (String autorzinho : tokens) {
                        autorzinho = this.tratarStringComPontoEVirgula(autorzinho, false);
                        if (!MapeamentoUtil.isEmpty(autorzinho)) {
                            autor = autores.get(autorzinho);
                            if (autor == null) {
                                autor = new AutorBean();
                                autor.setQuantidade(0);
                                autor.setAutor(autorzinho);

                                autores.put(autor.getAutor(), autor);
                            }

                            autor.setQuantidade(autor.getQuantidade() + 1);
                        }
                    }
                }
            }

        }

        return autores;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#gerarAnalisesEstudos(br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EtapaAnalise,
     *      java.util.List, java.util.List)
     */
    @Override
    public void gerarAnalisesEstudosParaDupla(EtapaAnalise etapa, List<Estudo> estudos) {

        List<AnaliseEstudo> analises = new ArrayList<AnaliseEstudo>();

        for (int i = 0; i < estudos.size(); i++) {
            Estudo estudo = estudos.get(i);

            if (estudo != null) {
                //insere de dois em dois por causa da dupla
                analises.add(this.gerarAnalise(etapa, estudo));
                analises.add(this.gerarAnalise(etapa, estudo));
            }
        }

        this.dao.incluir(analises);
    }

    private AnaliseEstudo gerarAnalise(EtapaAnalise etapa, Estudo estudo) {
        List<Erro> validacoes = null;

        AnaliseEstudo analise = new AnaliseEstudo();

        analise.setEstudo(estudo);
        analise.setIncluido(false);
        analise.setEtapa(etapa);
        analise.setConcluido(false);

        validacoes = analise.validar();

        if (validacoes.size() > 0) {
            for (Erro erro : validacoes) {
                LOG.error("Erro de validação:" + analise.getEstudo().getTitulo() + " Erro:" + erro.getCodigo() + " | "
                        + erro.getErro());
            }
        }

        return analise;
    }
    
    /**
     * {@inheritDoc}.
     *
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico#identificarConflitosAnalisesDuplas(java.lang.Long, java.lang.Long, java.lang.Long)
     */
    @Override
    public void identificarConflitosAnalisesDuplas(Long idEtapaAnalise, Long idRevisor1, Long idRevisor2) {
        if (idEtapaAnalise == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_VALIDACAO_CONFLITOS_ETAPA_ANALISE_OBRIGATORIA,
                    TipoErro.VALIDACAO));
        }
        
        if (idRevisor1 == null || idRevisor2 == null) {
            throw new MapeamentoException(new Erro(AnaliseEstudoErroEnum.ERRO_VALIDACAO_CONFLITOS_REVISORES_OBRIGATORIOS,
                    TipoErro.VALIDACAO));
        }
        
        //verificar se os participantes concluiram as análises
        
        
        List<AnaliseEstudo> analises = this.dao.buscarConflitosAnalises(idEtapaAnalise, idRevisor1, idRevisor2);
        HashMap<String, AnaliseEstudo> mapaAnalises = new HashMap<String, AnaliseEstudo>();
        
        for (AnaliseEstudo analiseEstudo : analises) {
            //colocar no mapa => chave = idAutor_idEstudo
            if (analiseEstudo != null) {
                mapaAnalises.put(analiseEstudo.getRevisor().getId().toString() + "_" + analiseEstudo.getEstudo().getId().toString(), analiseEstudo);
            }
        }
        
    }
}
