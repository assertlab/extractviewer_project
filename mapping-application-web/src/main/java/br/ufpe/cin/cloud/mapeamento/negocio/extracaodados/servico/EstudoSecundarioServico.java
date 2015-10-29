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
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstudoSecundarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.RevisorBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IEstudoSecundarioDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.AnaliseEstudo;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EstudoSecundario;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;

/**
 * Representa à implementação da camada de negócios da entidade {@link AnaliseEstudo}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:34:09
 */
@Service(value = "estudoSecundarioServico")
public class EstudoSecundarioServico extends AbstractServico<EstudoSecundario, EstudoSecundarioBean> implements
        IEstudoSecundarioServico {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 3547777707666989474L;

    /**
     * Representa o mecanismo de log da classe.
     */
    protected static Log LOG = LogFactory.getLog(EstudoSecundarioServico.class);

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link EstudoSecundario}.
     */
    @Autowired
    private IEstudoSecundarioDAO dao;

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
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getDao()
     */
    @Override
    public IEstudoSecundarioDAO getDao() {
        return this.dao;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#copiarDadosParaAlterarEntidade(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean,
     *      br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
     */
    @Override
    protected void copiarDadosParaAlterarEntidade(EstudoSecundarioBean bean, EstudoSecundario entidade) {
        
        entidade.setTitulo(bean.getTitulo());

        if (bean.getTipoEstudo() != null) {
            entidade.setTipoEstudo(bean.getTipoEstudo().getEnumeracao());
        }
        
        if (bean.getAutores() != null && !bean.getAutores().isEmpty()) {
            List<Revisor> autoresBean = new ArrayList<Revisor>();
            
            for (RevisorBean autor : bean.getAutores()) {
                if (autor != null) {
                    autoresBean.add(new Revisor(autor.getId()));
                }
            }
            
            entidade.setAutores(autoresBean);
        }
        
        entidade.setDataInclusao(bean.getDataInclusao());
        entidade.setDataUltimaAlteracao(bean.getDataUltimaAlteracao());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IEstudoSecundarioServico#gerarPassoDuplas(java.lang.Long)
     */
    @Override
    public void gerarPassoDuplas(Long idPassoRevisao) {
        // TODO: Acrescentar implementação do método

    }

}
