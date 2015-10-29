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
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.DatasExtremasAnalisesBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaEsforcoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstatisticaEtapaRevisao;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EtapaAnaliseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.VelocidadeDiaBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IAnaliseEstudoDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.ICriterioDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEstudoDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEtapaAnaliseDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IRevisorDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.AnaliseEstudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Criterio;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EtapaAnalise;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.SelecaoEstudo;

/**
 * Representa à implementação da camada de negócios da entidade {@link EtapaAnalise}
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:36:37
 */
@Service(value = "etapaAnaliseServico")
public class EtapaAnaliseServico extends AbstractServico<EtapaAnalise, EtapaAnaliseBean> implements
        IEtapaAnaliseServico {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 3548498707604989474L;

    /**
     * Representa o mecanismo de log da classe.
     */
    protected static Log LOG = LogFactory.getLog(EtapaAnaliseServico.class);

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link Criterio}.
     */
    @Autowired
    private IEtapaAnaliseDAO dao;

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link Estudo}.
     */
    @Autowired
    private IEstudoDAO estudoDAO;

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link Revisor}.
     */
    @Autowired
    private IRevisorDAO revisorDAO;

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link Criterio}.
     */
    @Autowired
    private ICriterioDAO criterioDAO;

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link AnaliseEstudo}.
     */
    @Autowired
    private IAnaliseEstudoDAO analiseEstudoDAO;

    /**
     * Representa a instância da camada de negócios da entidade {@link AnaliseEstudo}.
     */
    @Autowired
    private IAnaliseEstudoServico analiseEstudoServico;

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getDao()
     */
    @Override
    public IEtapaAnaliseDAO getDao() {
        return dao;
    }

    /**
     * Atualiza a instância de dao com o valor de dao.
     * 
     * @param dao Uma instância de IEtapaAnaliseDAO contendo o valor a ser atualizado.
     */
    public void setDao(IEtapaAnaliseDAO dao) {
        this.dao = dao;
    }

    /**
     * Atualiza a instância de estudoDAO com o valor de estudoDAO.
     * 
     * @param estudoDAO Uma instância de IEstudoDAO contendo o valor a ser atualizado.
     */
    public void setEstudoDAO(IEstudoDAO estudoDAO) {
        this.estudoDAO = estudoDAO;
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
     * Atualiza a instância de criterioDAO com o valor de criterioDAO.
     * 
     * @param criterioDAO Uma instância de ICriterioDAO contendo o valor a ser atualizado.
     */
    public void setCriterioDAO(ICriterioDAO criterioDAO) {
        this.criterioDAO = criterioDAO;
    }

    /**
     * Atualiza a instância de analiseEstudoDAO com o valor de analiseEstudoDAO.
     * 
     * @param analiseEstudoDAO Uma instância de IAnaliseEstudoDAO contendo o valor a ser atualizado.
     */
    public void setAnaliseEstudoDAO(IAnaliseEstudoDAO analiseEstudoDAO) {
        this.analiseEstudoDAO = analiseEstudoDAO;
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
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IEtapaAnaliseServico#montarAnaliseEstudosDuplicados()
     */
    @Override
    @Transactional(readOnly = false)
    public void montarEtapaAnaliseInicialEstudosDuplicados(String loginRevisor) {

        if (MapeamentoUtil.isEmpty(loginRevisor)) {
            throw new MapeamentoException(new Erro(EtapaAnaliseErroEnum.ERRO_LOGIN_OBRIGATORIO, TipoErro.VALIDACAO));
        }

        // verificar se existe analise inicial cadastrada
        if (this.dao.existeEtapaRevisaoInicial()) {
            throw new MapeamentoException(new Erro(EtapaAnaliseErroEnum.ETAPA_INICIAL_EXISTENTE, TipoErro.NEGOCIO));
        }

        Revisor entRevisor = this.revisorDAO.buscarRevisor(loginRevisor);

        if (entRevisor == null) {
            throw new MapeamentoException(new Erro(EtapaAnaliseErroEnum.ETAPA_INICIAL_REVISOR_INEXISTENTE,
                    TipoErro.VALIDACAO));
        }

        Criterio critRepetido = this.criterioDAO.buscarCriterio("Estudo duplicado.");

        if (critRepetido == null) {
            throw new MapeamentoException(new Erro(EtapaAnaliseErroEnum.ETAPA_INICIAL_CRITERIO_DUPLICADO_INEXISTENTE,
                    TipoErro.VALIDACAO));
        }

        // listar os estudos a serem analisados
        List<Estudo> estudos = this.estudoDAO.listarEstudosAgrupadosPorTitulo();
        Estudo estudo = null;

        EtapaAnalise etapaInicial = new EtapaAnalise();

        etapaInicial.setInicial(true);
        etapaInicial.setQtdEstudos(this.estudoDAO.identificarQuantidadeEstudos());
        etapaInicial.setRevisor(entRevisor);
        etapaInicial.setDataInclusao(new LocalDateTime(2014, 12, 4, 00, 00, 00));
        etapaInicial.setDataInicio(new LocalDateTime(2014, 12, 5, 00, 00, 00));
        etapaInicial.setDataConclusao(new LocalDateTime(2014, 12, 15, 23, 59, 59));
        etapaInicial.setConcluida(false);
        etapaInicial.setDescricao("Avaliação de Título e Abstract");
        etapaInicial.setQtdIncluidos(0);
        etapaInicial.setQtdExcluidos(0);

        this.dao.incluir(etapaInicial);

        // inserir os registros de AnaliseEstudo
        List<Erro> validacoes = null;
        List<AnaliseEstudo> analises = new ArrayList<AnaliseEstudo>();
        AnaliseEstudo analise = null;
        AnaliseEstudo analisePai = null;
        String tituloLimpo = null;

        if (estudos != null && !estudos.isEmpty()) {

            for (int i = 0; i < estudos.size(); i++) {
                estudo = estudos.get(i);

                if (estudo != null) {

                    analise = new AnaliseEstudo();
                    analise.setEstudo(estudo);
                    analise.setIncluido(false);
                    analise.setRevisor(entRevisor);
                    analise.setEtapa(etapaInicial);
                    analise.setConcluido(false);

                    if (i == 0 || (tituloLimpo != null && !tituloLimpo.equalsIgnoreCase(estudo.getTituloLimpo()))) {
                        tituloLimpo = estudo.getTituloLimpo();
                        analisePai = analise;
                    } else {
                        if (tituloLimpo.equalsIgnoreCase(tituloLimpo)) {
                            analise.setMatriz(analisePai);
                            analise.setCriterio(critRepetido);
                            analise.setComentario("Identificação automática.");
                        }
                    }

                    validacoes = analise.validar();

                    if (validacoes.size() > 0) {
                        for (Erro erro : validacoes) {
                            LOG.error("Erro de validação:" + analise.getEstudo().getTitulo() + " Erro:"
                                    + erro.getCodigo() + " | " + erro.getErro());
                        }
                    }

                    analises.add(analise);
                }
            }
        }

        this.analiseEstudoDAO.incluir(analises);

    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IEtapaAnaliseServico#montarAnaliseCriterioInclusaoExclusao(java.lang.String)
     */
    @Override
	public void montarEtapaAnaliseCriterioInclusaoExclusao(SelecaoEstudo selecao, Revisor revisor) {
	    
	    //recupera os estudos selecionados na etapa matriz.
        final List<Estudo> estudosIncluidos = this.estudoDAO.recuperarEstudosIncluidosSelecao(selecao.getMatriz().getId());
        
        if (estudosIncluidos == null || estudosIncluidos.isEmpty()) {
            throw new MapeamentoException(new Erro(
                    EtapaAnaliseErroEnum.ETAPA_CRITERIOS_ESTUDOS_INEXISTENTES,
                    TipoErro.VALIDACAO));
        }
        
        //mistura os estudos
        long seed = System.nanoTime();
        Collections.shuffle(estudosIncluidos, new Random(seed));
        
        EtapaAnalise etapaCritIncExc = new EtapaAnalise();
        
        etapaCritIncExc.setConcluida(Boolean.FALSE);
        etapaCritIncExc.setDescricao("Seleção de Estudos baseado em Critérios de Inclusão e Exclusão.");
        etapaCritIncExc.setInicial(false);
        etapaCritIncExc.setQtdEstudos(estudosIncluidos.size());
        etapaCritIncExc.setRevisor(revisor);
        etapaCritIncExc.setSelecaoEstudo(selecao);
     
        this.dao.incluir(etapaCritIncExc);
        
        //chama o serviço de análise de estudos para criar as análises.
        this.analiseEstudoServico.gerarAnalisesEstudosParaDupla(etapaCritIncExc, estudosIncluidos);
	}

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IEtapaAnaliseServico#listarEtapasAnalise(java.lang.String)
     */
    @Override
    public List<EtapaAnaliseBean> listarEtapasAnalise(String loginRevisor) {
        List<EtapaAnaliseBean> revisoes = new ArrayList<EtapaAnaliseBean>();

        if (MapeamentoUtil.isEmpty(loginRevisor)) {
            throw new MapeamentoException(new Erro(EtapaAnaliseErroEnum.LISTAR_REVISOES_LOGIN_REVISOR_OBRIGATORIO,
                    TipoErro.VALIDACAO));
        }

        List<EtapaAnalise> revEnt = this.dao.listarEtapasAnalise(loginRevisor);

        if (revEnt != null && !revEnt.isEmpty()) {
            for (EtapaAnalise etapaRevisao : revEnt) {
                revisoes.add(this.preencherBean(etapaRevisao));
            }
        }

        return revisoes;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IEtapaAnaliseServico#coletarEstatisticasEtapaRevisao(java.lang.Long)
     */
    @Override
    public EstatisticaEtapaRevisao coletarEstatisticasEtapaRevisao(Long idEtapaRevisao, Long idRevisor) {
        EstatisticaEtapaRevisao estatisticas = new EstatisticaEtapaRevisao();

        EtapaAnalise etapaRevisao = this.dao.recuperar(idEtapaRevisao);

        EtapaAnaliseBean beanRev = etapaRevisao.getBean();
        int qtd = 0;
        int qtdConcl = 0;

        qtd = this.analiseEstudoDAO.qtdTotal(beanRev.getId(), idRevisor, null);
        qtdConcl = this.analiseEstudoDAO.qtdTotalConcluidos(beanRev.getId(), idRevisor);

        estatisticas.setQtdIncluidos(this.analiseEstudoDAO.qtdTotalIncluidos(idEtapaRevisao, idRevisor));

        estatisticas.setQtdExcluidos(this.analiseEstudoDAO.qtdTotalExcluidos(idEtapaRevisao, idRevisor));

        estatisticas.setQtdConcluidos(qtdConcl);

        estatisticas.setQtdTotal(qtd);

        estatisticas.setPercConclusao((qtdConcl * 100d) / qtd);

        // coleta a velocidade do dia
        DatasExtremasAnalisesBean datas = new DatasExtremasAnalisesBean();
        datas.setDia(LocalDateTime.now());
        datas.setIdEtapa(idEtapaRevisao);

        this.dao.recuperarDatasExtremas(datas);

        VelocidadeDiaBean velocidadeDia = new VelocidadeDiaBean();
        velocidadeDia.setData(datas.getDia().toLocalDate());

        Long qtdDia = this.dao.recuperarConcluidosPorDia(datas.getDia(), idEtapaRevisao, idRevisor);

        velocidadeDia.setConcluidos(qtdDia);

        if ((datas.getMaior() == null && datas.getMenor() == null) || (datas.getMaior().equals(datas.getMenor()))) {

            velocidadeDia.setVelocidade(0d);

        } else {
            // descobrir a quantidade de minutos entre as duas horas
            Minutes minutes = Minutes.minutesBetween(datas.getMenor(), datas.getMaior());

            // descobrir a quantidade de estudos concluídos no dia
            if (minutes.getMinutes() > 0) {
                velocidadeDia.setVelocidade((Double.valueOf(qtdDia) / Double.valueOf(minutes.getMinutes())));
            }
        }

        estatisticas.setVelocidadeDia(velocidadeDia);

        // coleta o histórico de velocidades
        List<EstatisticaEsforcoBean> esforcoRealizado =
                this.analiseEstudoDAO.coletarEstatisticasEsforco(idEtapaRevisao, idRevisor);

        if (esforcoRealizado != null && !esforcoRealizado.isEmpty()) {

            List<VelocidadeDiaBean> historicoVelocidade = new ArrayList<VelocidadeDiaBean>();

            /*
             * verificar se os dias de trabalho existem na lista de esforços realizados. Atende ao caso da pessoa
             * ter comecado a trabalhar antes da data de início ou depois.
             */
            for (EstatisticaEsforcoBean realizadoBean : esforcoRealizado) {
                if (realizadoBean != null) {
                    datas.setDia(LocalDateTime.fromDateFields(realizadoBean.getData().toDate()));
                    datas.setMaior(null);
                    datas.setMenor(null);

                    this.dao.recuperarDatasExtremas(datas);

                    velocidadeDia = new VelocidadeDiaBean();
                    velocidadeDia.setData(realizadoBean.getData());
                    velocidadeDia.setConcluidos(realizadoBean.getQtdRealizado());

                    if ((datas.getMaior() == null && datas.getMenor() == null)
                            || (datas.getMaior().equals(datas.getMenor()))) {

                        velocidadeDia.setVelocidade(0d);

                    } else {
                        // descobrir a quantidade de minutos entre as duas horas
                        Minutes minutes = Minutes.minutesBetween(datas.getMenor(), datas.getMaior());

                        // descobrir a quantidade de estudos concluídos no dia
                        if (minutes.getMinutes() > 0) {
                            velocidadeDia.setVelocidade((Double.valueOf(realizadoBean.getQtdRealizado()) / Double
                                    .valueOf(minutes.getMinutes())));
                        }
                    }

                    historicoVelocidade.add(velocidadeDia);
                }
            }

            estatisticas.setHistoricoVelocidade(historicoVelocidade);

        }

        return estatisticas;
    }

    private EtapaAnaliseBean preencherBean(EtapaAnalise etapaRevisao) {
        EtapaAnaliseBean beanRev = null;
        int qtd = 0;
        int qtdConcl = 0;

        if (etapaRevisao != null) {
            beanRev = etapaRevisao.getBean();
            beanRev.setPercentualConclusao(0d);

            qtd = this.analiseEstudoDAO.qtdTotal(beanRev.getId(), etapaRevisao.getRevisor().getId(), null);
            beanRev.setQtdIncluidos(this.analiseEstudoDAO.qtdTotalIncluidos(beanRev.getId(), etapaRevisao.getRevisor().getId()));

            beanRev.setQtdExcluidos(this.analiseEstudoDAO.qtdTotalExcluidos(beanRev.getId(), etapaRevisao.getRevisor().getId()));

            qtdConcl = this.analiseEstudoDAO.qtdTotalConcluidos(beanRev.getId(), etapaRevisao.getRevisor().getId());

            beanRev.setPercentualConclusao((qtdConcl * 100d) / qtd);
        }

        return beanRev;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#copiarDadosParaAlterarEntidade(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean,
     *      br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
     */
    @Override
    protected void copiarDadosParaAlterarEntidade(EtapaAnaliseBean bean, EtapaAnalise entidade) {

        entidade.setConcluida(bean.getConcluida());
        entidade.setDataConclusao(bean.getDataConclusao());
        entidade.setDataUltimaAlteracao(LocalDateTime.now());
        entidade.setDescricao(bean.getDescricao());
        entidade.setInicial(bean.isInicial());
        entidade.setObservacoes(bean.getObservacoes());
        entidade.setQtdEstudos(bean.getQtdEstudos());
        entidade.setQtdExcluidos(bean.getQtdExcluidos());
        entidade.setQtdIncluidos(bean.getQtdIncluidos());

    }

}
