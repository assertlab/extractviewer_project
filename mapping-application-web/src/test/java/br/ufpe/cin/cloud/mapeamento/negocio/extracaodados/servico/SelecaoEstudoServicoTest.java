/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpe.cin.cloud.mapeamento.base.AbstractServicoTestHelper;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstagioExecucaoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.RevisorBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.SelecaoEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.TipoAnaliseEstudoEnumBean;

/**
 * Representa a implementação dos testes unitários da classe {@link BuscaServico}.
 * 
 * @author helaine.lins
 * @created 19/08/2014 - 10:35:55
 */
// @TransactionConfiguration(defaultRollback = true)
public class SelecaoEstudoServicoTest extends AbstractServicoTestHelper {

    /**
     * Representa a instância da camada de serviços que está sendo testada.
     */
    @Autowired
    private ISelecaoEstudoServico servSelecao;

    /**
     * Realiza os testes de impotação dos dados da busca manual do EASE do ano de 2006.
     */
    @Test
    //@Ignore
    public void testeGerarSelecaoCriteriosInclusaoExclusao() {

        try {
            // Passo 1 - criar a instância de SelecaoEstudo
            SelecaoEstudoBean bean = new SelecaoEstudoBean();
            bean.setConcluida(false);
            bean.setDataConclusao(new LocalDateTime(2015, 05, 19, 23, 59, 59));
            bean.setDataInicio(new LocalDateTime(2015, 05, 1, 0, 0, 0));
            bean.setDescricao("Etapa de Aplicação dos Critérios de Inclusão e Exclusão");
            bean.setEstagioExecucao(new EstagioExecucaoBean(1l));
            bean.setMatriz(new SelecaoEstudoBean(1l));
            bean.setOrdem(2);
            bean.setRevisor(new RevisorBean(1l));
            bean.setSelecaoFinal(false);
            bean.setTipoAnalise(TipoAnaliseEstudoEnumBean.CRITERIOS_INCLUSAO_EXCLUSAO);

            List<RevisorBean> participantes = new ArrayList<RevisorBean>();
            
            participantes.add(new RevisorBean(1l));
            participantes.add(new RevisorBean(2l));
            participantes.add(new RevisorBean(3l));
            participantes.add(new RevisorBean(4l));
            participantes.add(new RevisorBean(5l));
            participantes.add(new RevisorBean(6l));
            participantes.add(new RevisorBean(7l));
            participantes.add(new RevisorBean(8l));
            participantes.add(new RevisorBean(9l));
            participantes.add(new RevisorBean(10l));
            participantes.add(new RevisorBean(11l));
            participantes.add(new RevisorBean(12l));
            participantes.add(new RevisorBean(13l));
            participantes.add(new RevisorBean(14l));
            participantes.add(new RevisorBean(15l));
            participantes.add(new RevisorBean(16l));
            participantes.add(new RevisorBean(17l));
            participantes.add(new RevisorBean(18l));
            participantes.add(new RevisorBean(19l));
            participantes.add(new RevisorBean(20l));
            
            bean.setParticipantes(participantes);

            // Passo 2 - realizar a 
            this.servSelecao.montarSelecaoCriterioInclusaoExclusao(bean);
            
            // Passo 3 - 

        } catch (Exception e) {
            LOG.error("falhou ao executar realizar o teste testeMontarSelecaoCriterioInclusaoExclusao", e);

            super.logarDetalheExcecoes(e);

            fail("Nao deveria ter levantado excecao ao executar testeMontarSelecaoCriterioInclusaoExclusao.");
        }

    }

    /**
     * Realiza os testes de impotação dos dados da busca manual do EASE do ano de 2006.
     */
    @Test
    //@Ignore
    public void testeGerarSenhas() {

//        System.out.println("Marco -> " + MapeamentoUtil.encriptarSenha("d1_marco"));
//        System.out.println("Andreza -> " + MapeamentoUtil.encriptarSenha("d1_andreza"));
//        System.out.println("Michael -> " + MapeamentoUtil.encriptarSenha("d2_michael"));
//        System.out.println("Juliana Saraiva -> " + MapeamentoUtil.encriptarSenha("d2_juliana"));
//        System.out.println("Eudis -> " + MapeamentoUtil.encriptarSenha("d3_eudis"));
//
//        System.out.println("Alex -> " + MapeamentoUtil.encriptarSenha("d4_alex"));
//        System.out.println("Josino -> " + MapeamentoUtil.encriptarSenha("d4_josino"));
//        System.out.println("Gert -> " + MapeamentoUtil.encriptarSenha("d5_gert"));
//        System.out.println("Fish -> " + MapeamentoUtil.encriptarSenha("d5_fish"));
//        System.out.println("Diogo -> " + MapeamentoUtil.encriptarSenha("d6_diogo"));
//        System.out.println("Kid -> " + MapeamentoUtil.encriptarSenha("d6_kid"));
//        System.out.println("Vinicius -> " + MapeamentoUtil.encriptarSenha("d7_vini"));
        System.out.println("Vanilson -> " + MapeamentoUtil.encriptarSenha("d7_vanilson"));
//        System.out.println("Emanoel -> " + MapeamentoUtil.encriptarSenha("d8_emanoel"));
//        System.out.println("Jamir -> " + MapeamentoUtil.encriptarSenha("d8_jamir"));
//        System.out.println("Liliane -> " + MapeamentoUtil.encriptarSenha("d9_lili"));
//        System.out.println("Samuel -> " + MapeamentoUtil.encriptarSenha("d9_samuel"));
//        System.out.println("Vilmar -> " + MapeamentoUtil.encriptarSenha("d10_vilmar"));
//        System.out.println("Adauto -> " + MapeamentoUtil.encriptarSenha("d10_adauto"));

    }

}
