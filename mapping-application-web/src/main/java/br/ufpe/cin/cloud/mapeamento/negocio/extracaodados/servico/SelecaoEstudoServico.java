/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.SelecaoEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEstudoDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IRevisorDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.ISelecaoEstudoDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Busca;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.SelecaoEstudo;

/**
 * Representa a implementação da camada de serviços da entidade {@link Busca} no sistema.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 14:46:35
 */
@Service(value = "selecaoEstudoServico")
public class SelecaoEstudoServico extends AbstractServico<SelecaoEstudo, SelecaoEstudoBean> implements
        ISelecaoEstudoServico {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = -2481491711110234912L;

    /**
     * Representa o mecanismo de log da classe.
     */
    protected static Log LOG = LogFactory.getLog(SelecaoEstudoServico.class);

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link SelecaoEstudo}.
     */
    @Autowired
    private ISelecaoEstudoDAO dao;

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link Revisor}.
     */
    @Autowired
    private IRevisorDAO revisorDAO;

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link Estudo}.
     */
    @Autowired
    private IEstudoDAO estudoDAO;

    /**
     * Representa a instância da camada de serviço
     */
    @Autowired
    private IEtapaAnaliseServico servEtapa;

    /**
     * Cria uma nova instância do serviço inicializando os mecanismos do crawler de navegação.
     */
    public SelecaoEstudoServico() {
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getDao()
     */
    @Override
    public ISelecaoEstudoDAO getDao() {
        return this.dao;
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
     * Atualiza a instância de dao com o valor de dao.
     * 
     * @param dao Uma instância de IBuscaDAO contendo o valor a ser atualizado.
     */
    public void setDao(ISelecaoEstudoDAO dao) {
        this.dao = dao;
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
     * Atualiza a instância de estudoDAO com o valor de estudoDAO.
     * 
     * @param estudoDAO Uma instância de IEstudoDAO contendo o valor a ser atualizado.
     */
    public void setEstudoDAO(IEstudoDAO estudoDAO) {
        this.estudoDAO = estudoDAO;
    }

    /**
     * Atualiza a instância de servEtapa com o valor de servEtapa.
     * 
     * @param servEtapa Uma instância de IEtapaAnaliseServico contendo o valor a ser atualizado.
     */
    public void setServEtapa(IEtapaAnaliseServico servEtapa) {
        this.servEtapa = servEtapa;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#copiarDadosParaAlterarEntidade(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean,
     *      br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
     */
    @Override
    protected void copiarDadosParaAlterarEntidade(SelecaoEstudoBean bean, SelecaoEstudo entidade) {
        //TODO:implementar
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.ISelecaoEstudoServico#montarSelecaoCriterioInclusaoExclusao(br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.SelecaoEstudoBean)
     */
    @Override
    public void montarSelecaoCriterioInclusaoExclusao(SelecaoEstudoBean selecao) {
        if (selecao == null) {
            throw new MapeamentoException(new Erro(SelecaoEstudoErroEnum.ERRO_CONFIGURACAO_SELECAO_OBRIGATORIA,
                    TipoErro.VALIDACAO));
        }

        if (selecao.getRevisor() == null || selecao.getRevisor().getId() == null || selecao.getRevisor().getId() <= 0) {
            throw new MapeamentoException(new Erro(SelecaoEstudoErroEnum.ERRO_REVISOR_OBRIGATORIO, TipoErro.VALIDACAO));
        }

        if (selecao.getMatriz() == null || selecao.getMatriz().getId() == null || selecao.getMatriz().getId() <= 0) {
            throw new MapeamentoException(new Erro(SelecaoEstudoErroEnum.ERRO_SELECAO_ANTERIOR_OBRIGATORIA,
                    TipoErro.VALIDACAO));
        }

        // verificar se existe analise inicial cadastrada
        if (!this.dao.existeSelecaoEstudo(selecao.getMatriz().getId())) {
            throw new MapeamentoException(new Erro(SelecaoEstudoErroEnum.ERRO_SELECAO_ANTERIOR_INEXISTENTE,
                    TipoErro.NEGOCIO));
        }


        if (!this.revisorDAO.existeRevisor(selecao.getRevisor().getId())) {
            throw new MapeamentoException(new Erro(EtapaAnaliseErroEnum.ETAPA_INCLUSAO_EXCLUSAO_REVISOR_INEXISTENTE,
                    TipoErro.VALIDACAO));
        }

        SelecaoEstudo entSelecao = selecao.getEntidade();

        this.dao.incluir(entSelecao);

        //chamar o serviço de etapa analise e passar a selecaoEstudo
        this.servEtapa.montarEtapaAnaliseCriterioInclusaoExclusao(entSelecao, entSelecao.getRevisor());
    }

}
