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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufpe.cin.cloud.mapeamento.base.AbstractServicoTestHelper;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.IRevisorDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.dao.ISelecaoEstudoDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Revisor;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.SelecaoEstudo;

/**
 * Representa a implementação de testes unitários da classe {@link EtapaAnaliseServico}.
 * 
 * @author helaine.lins
 * @created 29/08/2014 - 10:44:40
 */
public class EtapaAnaliseServicoTest extends AbstractServicoTestHelper {

    /**
     * Representa a instância da camada de acesso à dados que está sendo testada.
     */
    @Autowired
    private IEtapaAnaliseServico eRevServico;

    /**
     * Representa a instância da camada de acesso à dados que está sendo testada.
     */
    @Autowired
    private ISelecaoEstudoDAO selecaoDAO;

    /**
     * Representa a instância da camada de acesso à dados que está sendo testada.
     */
    @Autowired
    private IRevisorDAO revisorDAO;

    /**
     * Realiza os testes de criação de análise inicial dos estudos.
     */
    @Test
    //@Ignore
    public void testeMontarAnaliseInicialEstudosDuplicados() {

        try {
            this.eRevServico.montarEtapaAnaliseInicialEstudosDuplicados("helainelins");
        } catch (Exception e) {
            LOG.error("falhou ao executar realizar o teste testeMontarAnaliseInicialEstudosDuplicados", e);
            fail("Nao deveria ter levantado excecao ao executar testeMontarAnaliseInicialEstudosDuplicados.");
        }

    }

    /**
     * Realiza os testes de criação de etapa de analise de critério de inclusão e exclusão.
     */
    @Test
    //@Ignore
    public void testeMontarEtapaAnaliseCriterioInclusaoExclusao() {
        try {
            SelecaoEstudo selecao = selecaoDAO.recuperar(2l);
            Revisor revisor = revisorDAO.recuperar(1l);

            this.eRevServico.montarEtapaAnaliseCriterioInclusaoExclusao(selecao, revisor);
        } catch (Exception e) {
            LOG.error("falhou ao executar realizar o teste testeMontarAnaliseInicialEstudosDuplicados", e);
            fail("Nao deveria ter levantado excecao ao executar testeMontarAnaliseInicialEstudosDuplicados.");
        }
    }

    /**
     * 
     */
    @Test
    //@Ignore
    public void testeTratarDadosAplicacaoCriterio() {
        //List<InstrumentoColeta> estudos = new ArrayList<InstrumentoColeta>();   

        try {

            final InputStream template =
                    new FileInputStream(
                            "C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/cloudmapping/instrumento de avaliacao/coleta.xls");

            Workbook workbook = WorkbookFactory.create(template);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

//            Sheet planilha = null;
//            HashMap<String, Double> somatorioNotasItens = new HashMap<String, Double>();
//            HashMap<String, Double> maioresNotasItens = new HashMap<String, Double>();
            HashMap<Integer, InstrumentoColeta> estudos = new HashMap<Integer, InstrumentoColeta>();
            HashMap<Double, Integer> pontuacao = new HashMap<Double, Integer>();
//            InstrumentoColeta estudo = null;

            //this.calcularSomatorioNotas(workbook, evaluator, somatorioNotasItens, maioresNotasItens);

            this.preencherNotasObjeto(workbook, evaluator, estudos);

            //contabilizar notas por secao
            Object[] chavesMedia = estudos.keySet().toArray();
            Arrays.sort(chavesMedia);
            
            InstrumentoColeta estudo;
//            Integer qtdAcertosItem11=0;
//            Integer qtdAcertosItem12=0;
//            Integer qtdAcertosItem13=0;
            Integer qtd=0;
            Double nota = 0d;
            
            for (Object codigo : chavesMedia) {
                estudo = estudos.get(codigo);
                
                System.out.println(estudo.getResumoConclusoes361());
                
//                if (pontuacao.get(estudo.getConclusoes()) == null) {
//                    pontuacao.put(estudo.getConclusoes(), 0);
//                }
//              
//                qtd = pontuacao.get(estudo.getConclusoes());
//                pontuacao.put(estudo.getConclusoes(), ++qtd);
//                
//                if (estudo.getConclusoes() == 8.0) {
//                    System.out.println("*" +estudo.getTituloEstudo() + " == == " + estudo.getIdEstudo() + " +++ " + estudo.getIdPlanilha() + " **** " + estudo.getConclusoes());
//                }
                
            }
            
//            Object[] chavesPontuacao = pontuacao.keySet().toArray();
//            Arrays.sort(chavesPontuacao);
//            
//            for (Object notaConclusoes : chavesPontuacao) {
//                System.out.println(notaConclusoes);
//            }
            
            System.out.println("*");
            System.out.println("*");
            System.out.println("*");
            System.out.println("*");
            
//            for (Object notaResumo : chavesPontuacao) {
//                System.out.println(pontuacao.get(notaResumo));
//            }
            
            //System.out.println("[QTD ACERTOS TÍTULO 1.1]: " + qtdAcertosItem11 + " % de acerto: " + ((double)qtdAcertosItem11/59));
            //System.out.println("[QTD ACERTOS TÍTULO 1.2]: " + qtdAcertosItem12 + " % de acerto: " + ((double)qtdAcertosItem12/59));
            //System.out.println("[QTD ACERTOS TÍTULO 1.3]: " + qtdAcertosItem13 + " % de acerto: " + ((double)qtdAcertosItem13/59));
            
            //preenchendo a nota dos estudos por secao.
            //this.preencherNotasEstudosPlanilhaResumida(workbook, estudos);

            //this.listarMaioresNotas(maioresNotasItens);
            //this.listarMediaNotasPorSecoes(somatorioNotasItens, maioresNotasItens);

        } catch (Exception e) {
            LOG.error(e);
        }

    }

    /**
     * @param workbook
     * @param estudos
     */
    protected void preencherNotasEstudosPlanilhaResumida(Workbook workbook, HashMap<Integer, InstrumentoColeta> estudos) {
        Sheet planilha;
        InstrumentoColeta estudo;
        Row linhaEstudo = null;
        Row linhaCabecalho = null;
        
        Cell celulaTitulo = null;
        Cell celulaAutoria = null;
        Cell celulaResumo = null;
        Cell celulaIntroducao = null;
        Cell celulaFundamentacao = null;
        Cell celulaPlanejamento = null;
        Cell celulaAnalises = null;
        Cell celulaDiscussao = null;
        Cell celulaConclusoes = null;
        Cell celulaApendices = null;

        planilha = workbook.getSheet("notas");

        
        for (int j = 1; j < 60; j++) {
            
            linhaEstudo = planilha.getRow(j);
            
            linhaCabecalho = planilha.getRow(0);
            
            celulaTitulo = linhaEstudo.getCell(2);
            celulaAutoria = linhaEstudo.getCell(3);
            celulaResumo = linhaEstudo.getCell(4);
            celulaIntroducao = linhaEstudo.getCell(5);
            celulaFundamentacao = linhaEstudo.getCell(6);
            celulaPlanejamento = linhaEstudo.getCell(7);
            celulaAnalises = linhaEstudo.getCell(8);
            celulaDiscussao = linhaEstudo.getCell(9);
            celulaConclusoes = linhaEstudo.getCell(10);
            celulaApendices = linhaEstudo.getCell(11);

            estudo =
                    estudos.get(Integer.valueOf(((Double) linhaEstudo.getCell(0).getNumericCellValue())
                            .intValue()));

            System.out.println("[Salvando notas do Estudo [" + estudo.getIdPlanilha() + "] " + estudo.getTituloEstudo() + "] ultima celula:" + linhaCabecalho.getLastCellNum());
            
            System.out.println("[ITEM TÍTULO]");
            celulaTitulo.setCellValue(estudo.getTitulo());

            System.out.println("[ITEM AUTORIA]");
            celulaAutoria.setCellValue(estudo.getAutoria());

            System.out.println("[ITEM RESUMO]");
            celulaResumo.setCellValue(estudo.getResumo());

            System.out.println("[ITEM INTRODUCAO]");
            celulaIntroducao.setCellValue(estudo.getIntroducao());

            System.out.println("[ITEM FUNDAMENTACAO]");
            celulaFundamentacao.setCellValue(estudo.getFundamentacao());

            System.out.println("[ITEM PLANEJAMENTO]");
            celulaPlanejamento.setCellValue(estudo.getPlanejamento());

            System.out.println("[ITEM ANALISES]");
            celulaAnalises.setCellValue(estudo.getAnalises());

            System.out.println("[ITEM DISCUSSOES]");
            celulaDiscussao.setCellValue(estudo.getDiscussao());

            System.out.println("[ITEM CONCLUSOES]");
            celulaConclusoes.setCellValue(estudo.getConclusoes());
            
            System.out.println("[ITEM APENDICES]");
            celulaApendices.setCellValue(estudo.getApendices());
        }
        
        try {
            FileOutputStream fileOut = new FileOutputStream("C:/Users/helainelins/Dropbox/Acadêmico/Mestrado/dissertação/04-mapeamento/cloudmapping/instrumento de avaliacao/coleta.xls");
            
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            System.out.println("Falha ao salvar notas.");
        }
        
    }

    /**
     * @param workbook
     * @param evaluator
     * @param somatorioNotasItens
     * @param maioresNotasItens
     */
    protected void calcularSomatorioNotas(Workbook workbook, FormulaEvaluator evaluator,
            HashMap<String, Double> somatorioNotasItens, HashMap<String, Double> maioresNotasItens) {
        Sheet planilha;
        Row linhaTabela;
        Cell cell;
        Cell item;
        Double nota;
        Double notaAcumulada;
        Double maiorNota;
        String labelItem;
        for (int i = 1; i < 59; i++) {
            planilha = workbook.getSheetAt(i);

            if (planilha == null) {
                throw new MapeamentoException(new Erro(BuscaErroEnum.ERRO_EXTRAIR_ESTUDOS_ARQUIVO_EXCEL_PLANILHA,
                        TipoErro.INESPERADO, "planilha_" + i));
            }

            System.out.println("[Analisando itens da Planilha " + planilha.getSheetName() + "]");

            //as informacoes vao da linha 3 até a 52
            for (int j = 3; j < 54; j++) {
                //colunas comecam de 1
                //linhas comecam de 0
                linhaTabela = planilha.getRow(j);
                cell = linhaTabela.getCell(9);
                item = linhaTabela.getCell(4);

                if (cell != null) {
                    evaluator.evaluateInCell(cell);

                    nota = cell.getNumericCellValue();
                    labelItem = item.getStringCellValue();
                    notaAcumulada = somatorioNotasItens.get(labelItem);

                    if (notaAcumulada == null) {
                        notaAcumulada = 0d;
                    }

                    notaAcumulada += nota;

                    somatorioNotasItens.put(labelItem, notaAcumulada);

                    maiorNota = maioresNotasItens.get(labelItem);
                    if (maiorNota == null) {
                        maiorNota = 0d;
                    }

                    if (nota > maiorNota) {
                        maiorNota = nota;
                    }

                    maioresNotasItens.put(labelItem, maiorNota);

                    System.out.println("[linha: " + j + " item: " + item.getStringCellValue() + " valor: "
                            + cell.getNumericCellValue() + "]");
                }
            }

        }
    }

    /**
     * @param workbook
     * @param evaluator
     * @param estudos
     */
    protected void preencherNotasObjeto(Workbook workbook, FormulaEvaluator evaluator,
            HashMap<Integer, InstrumentoColeta> estudos) {
        Sheet planilha;
        Row linhaTabela;
        Cell cell;
        InstrumentoColeta estudo;

        //planilhas começam de 0
        //colunas comecam de 1
        //linhas comecam de 0

        for (int i = 0; i < 59; i++) {
            planilha = workbook.getSheetAt(i);

            if (planilha == null) {
                throw new MapeamentoException(new Erro(BuscaErroEnum.ERRO_EXTRAIR_ESTUDOS_ARQUIVO_EXCEL_PLANILHA,
                        TipoErro.INESPERADO, "planilha_" + i));
            }

            System.out.println("[Preenchendo notas da Planilha " + planilha.getSheetName() + "]:" + planilha.getRow(1).getCell(2).getStringCellValue() + " ID:" + ((Double) (planilha.getRow(1).getCell(1).getNumericCellValue())).intValue());
            estudo = new InstrumentoColeta();

            estudo.setTituloEstudo(planilha.getRow(1).getCell(2).getStringCellValue());
            estudo.setIdEstudo(((Double) (planilha.getRow(1).getCell(1).getNumericCellValue())).intValue());
            evaluator.evaluateInCell(planilha.getRow(1).getCell(8));
            estudo.setMediaEstudo(planilha.getRow(1).getCell(8).getNumericCellValue());
            estudo.setIdPlanilha(i);

            //as informacoes vao da linha 3 até a 52

            //secao titulo
            estudo.setTitulo(0);

            linhaTabela = planilha.getRow(3);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setTitulo11(cell.getNumericCellValue());
            estudo.setTitulo(estudo.getTitulo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(4);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setTitulo12(cell.getNumericCellValue());
            estudo.setTitulo(estudo.getTitulo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(5);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setTitulo13(cell.getNumericCellValue());
            estudo.setTitulo(estudo.getTitulo() + cell.getNumericCellValue());

            //secao autoria
            estudo.setAutoria(0);

            linhaTabela = planilha.getRow(6);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setAutoria(cell.getNumericCellValue());
            estudo.setAutoria21(cell.getNumericCellValue());

            //secao resumo
            estudo.setResumo(0);

            linhaTabela = planilha.getRow(7);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoFundamentacao(cell.getNumericCellValue());
            estudo.setResumoFundamentacao311(cell.getNumericCellValue());
            estudo.setResumo(cell.getNumericCellValue());

            linhaTabela = planilha.getRow(8);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoObjetivo(cell.getNumericCellValue());
            estudo.setResumoObjetivo321(cell.getNumericCellValue());
            estudo.setResumo(estudo.getResumo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(9);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoObjetivo(estudo.getResumoObjetivo() + cell.getNumericCellValue());
            estudo.setResumoObjetivo322(cell.getNumericCellValue());
            estudo.setResumo(estudo.getResumo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(10);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoObjetivo(estudo.getResumoObjetivo() + cell.getNumericCellValue());
            estudo.setResumoObjetivo323(cell.getNumericCellValue());
            estudo.setResumo(estudo.getResumo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(11);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoObjetivo(estudo.getResumoObjetivo() + cell.getNumericCellValue());
            estudo.setResumoObjetivo324(cell.getNumericCellValue());
            estudo.setResumo(estudo.getResumo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(12);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoMetodo(cell.getNumericCellValue());
            estudo.setResumoMetodo331(cell.getNumericCellValue());
            estudo.setResumo(estudo.getResumo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(13);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoMetodo(estudo.getResumoMetodo() + cell.getNumericCellValue());
            estudo.setResumoMetodo332(cell.getNumericCellValue());
            estudo.setResumo(estudo.getResumo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(14);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoMetodo(estudo.getResumoMetodo() + cell.getNumericCellValue());
            estudo.setResumoMetodo333(cell.getNumericCellValue());
            estudo.setResumo(estudo.getResumo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(15);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoMetodo(estudo.getResumoMetodo() + cell.getNumericCellValue());
            estudo.setResumoMetodo334(cell.getNumericCellValue());
            estudo.setResumo(estudo.getResumo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(16);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoMetodo(estudo.getResumoMetodo() + cell.getNumericCellValue());
            estudo.setResumoMetodo335(cell.getNumericCellValue());
            estudo.setResumo(estudo.getResumo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(17);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoResultados(cell.getNumericCellValue());
            estudo.setResumoResultados341(cell.getNumericCellValue());
            estudo.setResumo(estudo.getResumo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(18);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoLimitacoes(cell.getNumericCellValue());
            estudo.setResumoLimitacoes351(cell.getNumericCellValue());
            estudo.setResumo(estudo.getResumo() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(19);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setResumoConclusoes(cell.getNumericCellValue());
            estudo.setResumoConclusoes361(cell.getNumericCellValue());
            estudo.setResumo(estudo.getResumo() + cell.getNumericCellValue());

            //secao introducao
            estudo.setIntroducao(0);

            linhaTabela = planilha.getRow(20);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setIntroducaoFormulacaoProblema411(cell.getNumericCellValue());
            estudo.setIntroducao(estudo.getIntroducao() + cell.getNumericCellValue());
            
            linhaTabela = planilha.getRow(21);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setIntroducaoObjetivo(estudo.getIntroducaoObjetivo() + cell.getNumericCellValue());
            estudo.setIntroducaoObjetivo421(cell.getNumericCellValue());
            estudo.setIntroducao(estudo.getIntroducao() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(22);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setIntroducaoContexto(cell.getNumericCellValue());
            estudo.setIntroducaoContexto431(cell.getNumericCellValue());
            estudo.setIntroducao(estudo.getIntroducao() + cell.getNumericCellValue());

            //secao fundamentacao
            estudo.setFundamentacao(0);

            linhaTabela = planilha.getRow(23);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setFundamentacao511(cell.getNumericCellValue());
            estudo.setFundamentacao(estudo.getFundamentacao() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(24);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setFundamentacao512(cell.getNumericCellValue());
            estudo.setFundamentacao(estudo.getFundamentacao() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(25);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setFundamentacao513(cell.getNumericCellValue());
            estudo.setFundamentacao(estudo.getFundamentacao() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(26);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setFundamentacao514(cell.getNumericCellValue());
            estudo.setFundamentacao(estudo.getFundamentacao() + cell.getNumericCellValue());

            //secao planejamento
            estudo.setPlanejamento(0);

            linhaTabela = planilha.getRow(27);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoObjetivos(cell.getNumericCellValue());
            estudo.setPlanejamentoObjetivos611(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(28);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoUnidades(cell.getNumericCellValue());
            estudo.setPlanejamentoUnidades621(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(29);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoMateriais(cell.getNumericCellValue());
            estudo.setPlanejamentoMateriais631(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(30);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoAtividades(cell.getNumericCellValue());
            estudo.setPlanejamentoAtividades641(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(31);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoHipoteses(cell.getNumericCellValue());
            estudo.setPlanejamentoHipoteses651(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(32);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoHipoteses(estudo.getPlanejamentoHipoteses() + cell.getNumericCellValue());
            estudo.setPlanejamentoHipoteses652(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(33);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoHipoteses(estudo.getPlanejamentoHipoteses() + cell.getNumericCellValue());
            estudo.setPlanejamentoHipoteses653(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(34);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoHipoteses(estudo.getPlanejamentoHipoteses() + cell.getNumericCellValue());
            estudo.setPlanejamentoHipoteses654(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(35);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoDesenho(cell.getNumericCellValue());
            estudo.setPlanejamentoDesenho661(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(36);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoProcedimentos(cell.getNumericCellValue());
            estudo.setPlanejamentoProcedimentos671(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(37);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoProcedimentos(estudo.getPlanejamentoProcedimentos() + cell.getNumericCellValue());
            estudo.setPlanejamentoProcedimentos672(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(38);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoProcedimentos(estudo.getPlanejamentoProcedimentos() + cell.getNumericCellValue());
            estudo.setPlanejamentoProcedimentos673(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(39);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setPlanejamentoProcedimentosAnalise(cell.getNumericCellValue());
            estudo.setPlanejamentoProcedimentosAnalise681(cell.getNumericCellValue());
            estudo.setPlanejamento(estudo.getPlanejamento() + cell.getNumericCellValue());

            //secao analises
            estudo.setAnalises(0);

            linhaTabela = planilha.getRow(40);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setAnalisesEstatisticaDescritiva(cell.getNumericCellValue());
            estudo.setAnalisesEstatisticaDescritiva811(cell.getNumericCellValue());
            estudo.setAnalises(estudo.getAnalises() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(41);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setAnalisesEstatisticaDescritiva(estudo.getAnalisesEstatisticaDescritiva()
                    + cell.getNumericCellValue());
            estudo.setAnalisesEstatisticaDescritiva812(cell.getNumericCellValue());
            estudo.setAnalises(estudo.getAnalises() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(42);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setAnalisesPreparacaoDados(cell.getNumericCellValue());
            estudo.setAnalisesPreparacaoDados821(cell.getNumericCellValue());
            estudo.setAnalises(estudo.getAnalises() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(43);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setAnalisesTesteHipotese(cell.getNumericCellValue());
            estudo.setAnalisesTesteHipotese831(cell.getNumericCellValue());
            estudo.setAnalises(estudo.getAnalises() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(44);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setAnalisesTesteHipotese(estudo.getAnalisesTesteHipotese() + cell.getNumericCellValue());
            estudo.setAnalisesTesteHipotese832(cell.getNumericCellValue());
            estudo.setAnalises(estudo.getAnalises() + cell.getNumericCellValue());

            //secao discussao
            estudo.setDiscussao(0);

            linhaTabela = planilha.getRow(45);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setDiscussaoResultados(cell.getNumericCellValue());
            estudo.setDiscussaoResultados911(cell.getNumericCellValue());
            estudo.setDiscussao(estudo.getDiscussao() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(46);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setDiscussaoResultados(estudo.getDiscussaoResultados() + cell.getNumericCellValue());
            estudo.setDiscussaoResultados912(cell.getNumericCellValue());
            estudo.setDiscussao(estudo.getDiscussao() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(47);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setDiscussaoAmeacas(cell.getNumericCellValue());
            estudo.setDiscussaoAmeacas921(cell.getNumericCellValue());
            estudo.setDiscussao(estudo.getDiscussao() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(48);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setDiscussaoInferencias(cell.getNumericCellValue());
            estudo.setDiscussaoInferencias931(cell.getNumericCellValue());
            estudo.setDiscussao(estudo.getDiscussao() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(49);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setDiscussaoLicoesAprendidas(cell.getNumericCellValue());
            estudo.setDiscussaoLicoesAprendidas941(cell.getNumericCellValue());
            estudo.setDiscussao(estudo.getDiscussao() + cell.getNumericCellValue());

            //secao conclusoes
            estudo.setConclusoes(0);

            linhaTabela = planilha.getRow(50);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setConclusoesResumo(cell.getNumericCellValue());
            estudo.setConclusoesResumo1011(cell.getNumericCellValue());
            estudo.setConclusoes(estudo.getConclusoes() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(51);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setConclusoesImpacto(cell.getNumericCellValue());
            estudo.setConclusoesImpacto1021(cell.getNumericCellValue());
            estudo.setConclusoes(estudo.getConclusoes() + cell.getNumericCellValue());

            linhaTabela = planilha.getRow(52);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setConclusoesTrabalhosFuturos(cell.getNumericCellValue());
            estudo.setConclusoesTrabalhosFuturos1031(cell.getNumericCellValue());
            estudo.setConclusoes(estudo.getConclusoes() + cell.getNumericCellValue());

            //secao conclusoes
            estudo.setApendices(0);

            linhaTabela = planilha.getRow(53);
            cell = linhaTabela.getCell(9);
            evaluator.evaluateInCell(cell);
            estudo.setApendices(cell.getNumericCellValue());

            estudos.put(estudo.getIdEstudo(), estudo);

        }
    }

    /**
     * @param somatorioNotasItens
     * @param maioresNotasItens
     */
    protected void listarMediaNotasPorSecoes(HashMap<String, Double> somatorioNotasItens,
            HashMap<String, Double> maioresNotasItens) {
        System.out.println("======================================================================================");

        System.out.println("RESULTADO DAS MÉDIAS DAS NOTAS POR SECAO");

        //ordena os resultados
        //            Object[] chavesMedia = somatorioNotasItens.keySet().toArray();
        //            Arrays.sort(chavesMedia);

        //            for (Object chave : chavesMedia) {
        //                System.out.println("[ITEM " + chave + "]: " + somatorioNotasItens.get(chave) / 59);
        //            }
        System.out.println("======================================================================================");

        System.out.println("[ITEM TÍTULO]: "
                + (maioresNotasItens.get("1.1") + maioresNotasItens.get("1.2") + maioresNotasItens.get("1.3") / 59)
                + " DE 3");

        System.out.println("======================================================================================");

        System.out.println("[ITEM AUTORIA]: " + (maioresNotasItens.get("2.1") / 59) + " DE 1");

        System.out.println("======================================================================================");

        System.out.println("[ITEM RESUMO]: "
                + (maioresNotasItens.get("3.1.1") + maioresNotasItens.get("3.2.1") + maioresNotasItens.get("3.2.2")
                        + maioresNotasItens.get("3.2.3") + maioresNotasItens.get("3.2.4")
                        + maioresNotasItens.get("3.3.1") + maioresNotasItens.get("3.3.2")
                        + maioresNotasItens.get("3.3.3") + maioresNotasItens.get("3.3.4")
                        + maioresNotasItens.get("3.3.5") + maioresNotasItens.get("3.4.1")
                        + maioresNotasItens.get("3.5.1") + maioresNotasItens.get("3.6.1") / 59) + " DE 13");

        System.out.println("======================================================================================");

        System.out
                .println("[ITEM INTRODUCAO]: "
                        + (maioresNotasItens.get("4.1.1") + maioresNotasItens.get("4.2.1") + maioresNotasItens
                                .get("4.3.1") / 59) + " DE 3");
        System.out.println("======================================================================================");

        System.out
                .println("[ITEM FUNDAMENTACAO]: "
                        + (maioresNotasItens.get("5.1.1") + maioresNotasItens.get("5.1.2")
                                + maioresNotasItens.get("5.1.3") + maioresNotasItens.get("5.1.4") / 59) + " DE 4");

        System.out.println("======================================================================================");

        System.out.println("[ITEM PLANEJAMENTO]: "
                + (maioresNotasItens.get("6.1.1") + maioresNotasItens.get("6.2.1") + maioresNotasItens.get("6.3.1")
                        + maioresNotasItens.get("6.4.1") + maioresNotasItens.get("6.5.1")
                        + maioresNotasItens.get("6.5.2") + maioresNotasItens.get("6.5.3")
                        + maioresNotasItens.get("6.5.4") + maioresNotasItens.get("6.6.1")
                        + maioresNotasItens.get("6.7.1") + maioresNotasItens.get("6.7.2")
                        + maioresNotasItens.get("6.7.3") + maioresNotasItens.get("6.8.1") / 59) + " DE 13");

        System.out.println("======================================================================================");

        System.out.println("[ITEM ANALISES]: "
                + (maioresNotasItens.get("8.1.1") + maioresNotasItens.get("8.1.2") + maioresNotasItens.get("8.2.1")
                        + maioresNotasItens.get("8.3.1") + maioresNotasItens.get("8.3.2") / 59) + " DE 5");

        System.out.println("======================================================================================");

        System.out.println("[ITEM DISCUSSAO]: "
                + (maioresNotasItens.get("9.1.1") + maioresNotasItens.get("9.1.2") + maioresNotasItens.get("9.2.1")
                        + maioresNotasItens.get("9.3.1") + maioresNotasItens.get("9.4.1") / 59) + " DE 5");

        System.out.println("======================================================================================");

        System.out
                .println("[ITEM CONCLUSOES]: "
                        + (maioresNotasItens.get("10.1.1") + maioresNotasItens.get("10.2.1") + maioresNotasItens
                                .get("10.3.1") / 59) + " DE 3");

        System.out.println("======================================================================================");

        System.out.println("[ITEM APENDICES]: " + (somatorioNotasItens.get("11.1.1") / 59) + " DE 1");

        System.out.println("======================================================================================");
    }

    /**
     * @param maioresNotasItens
     */
    protected void listarMaioresNotas(HashMap<String, Double> maioresNotasItens) {
        System.out.println("======================================================================================");
        System.out.println("RESULTADO DAS MAIORES NOTAS POR SEÇÃO");

        System.out.println("======================================================================================");

        System.out.println("[ITEM TÍTULO]: "
                + (maioresNotasItens.get("1.1") + maioresNotasItens.get("1.2") + maioresNotasItens.get("1.3"))
                + " DE 3");

        System.out.println("======================================================================================");

        System.out.println("[ITEM AUTORIA]: " + (maioresNotasItens.get("2.1")) + " DE 1");

        System.out.println("======================================================================================");

        System.out.println("[ITEM RESUMO]: "
                + (maioresNotasItens.get("3.1.1") + maioresNotasItens.get("3.2.1") + maioresNotasItens.get("3.2.2")
                        + maioresNotasItens.get("3.2.3") + maioresNotasItens.get("3.2.4")
                        + maioresNotasItens.get("3.3.1") + maioresNotasItens.get("3.3.2")
                        + maioresNotasItens.get("3.3.3") + maioresNotasItens.get("3.3.4")
                        + maioresNotasItens.get("3.3.5") + maioresNotasItens.get("3.4.1")
                        + maioresNotasItens.get("3.5.1") + maioresNotasItens.get("3.6.1")) + " DE 13");

        System.out.println("[ITEM RESUMO - FUNDAMENTACAO]: " + (maioresNotasItens.get("3.1.1")) + " DE 1");

        System.out
                .println("[ITEM RESUMO - OBJETIVO]: "
                        + (maioresNotasItens.get("3.2.1") + maioresNotasItens.get("3.2.2")
                                + maioresNotasItens.get("3.2.3") + maioresNotasItens.get("3.2.4")) + " DE 4");

        System.out.println("[ITEM RESUMO - METODO]: "
                + (maioresNotasItens.get("3.3.1") + maioresNotasItens.get("3.3.2") + maioresNotasItens.get("3.3.3")
                        + maioresNotasItens.get("3.3.4") + maioresNotasItens.get("3.3.5")) + " DE 5");

        System.out.println("[ITEM RESUMO - RESULTADOS]: " + (maioresNotasItens.get("3.4.1")) + " DE 1");

        System.out.println("[ITEM RESUMO - LIMITACOES]: " + (maioresNotasItens.get("3.5.1")) + " DE 1");

        System.out.println("[ITEM RESUMO - CONCLUSAO]: " + (maioresNotasItens.get("3.6.1")) + " DE 1");

        System.out.println("======================================================================================");

        System.out.println("[ITEM INTRODUCAO]: "
                + (maioresNotasItens.get("4.1.1") + maioresNotasItens.get("4.2.1") + maioresNotasItens.get("4.3.1"))
                + " DE 3");

        System.out.println("[ITEM INTRODUCAO - FORMULACAO DO PROBLEMA]: " + (maioresNotasItens.get("4.1.1")) + " DE 1");

        System.out.println("[ITEM INTRODUCAO - OBJETIVO DA PESQUISA]: " + (maioresNotasItens.get("4.2.1")) + " DE 1");

        System.out.println("[ITEM INTRODUCAO - CONTEXTO DA PESQUISA]: " + (maioresNotasItens.get("4.3.1")) + " DE 1");

        System.out.println("======================================================================================");

        System.out
                .println("[ITEM FUNDAMENTACAO]: "
                        + (maioresNotasItens.get("5.1.1") + maioresNotasItens.get("5.1.2")
                                + maioresNotasItens.get("5.1.3") + maioresNotasItens.get("5.1.4")) + " DE 4");

        System.out.println("======================================================================================");

        System.out.println("[ITEM PLANEJAMENTO]: "
                + (maioresNotasItens.get("6.1.1") + maioresNotasItens.get("6.2.1") + maioresNotasItens.get("6.3.1")
                        + maioresNotasItens.get("6.4.1") + maioresNotasItens.get("6.5.1")
                        + maioresNotasItens.get("6.5.2") + maioresNotasItens.get("6.5.3")
                        + maioresNotasItens.get("6.5.4") + maioresNotasItens.get("6.6.1")
                        + maioresNotasItens.get("6.7.1") + maioresNotasItens.get("6.7.2")
                        + maioresNotasItens.get("6.7.3") + maioresNotasItens.get("6.8.1")) + " DE 13");

        System.out.println("[ITEM PLANEJAMENTO - OBJETIVOS]: " + (maioresNotasItens.get("6.1.1")) + " DE 1");

        System.out.println("[ITEM PLANEJAMENTO - UNIDADES EXPERIMENTAIS]: " + (maioresNotasItens.get("6.2.1"))
                + " DE 1");

        System.out.println("[ITEM PLANEJAMENTO - MATERIAIS EXPERIMENTAIS]: " + (maioresNotasItens.get("6.3.1"))
                + " DE 1");

        System.out.println("[ITEM PLANEJAMENTO - ATIVIDADES]: " + (maioresNotasItens.get("6.4.1")) + " DE 1");

        System.out
                .println("[ITEM PLANEJAMENTO - HIPOTESES]: "
                        + (maioresNotasItens.get("6.5.1") + maioresNotasItens.get("6.5.2")
                                + maioresNotasItens.get("6.5.3") + maioresNotasItens.get("6.5.4")) + " DE 4");

        System.out.println("[ITEM PLANEJAMENTO - DESENHO EXPERIMENTAL]: " + (maioresNotasItens.get("6.6.1")) + " DE 1");

        System.out.println("[ITEM PLANEJAMENTO - PROCEDIMENTOS]: "
                + (maioresNotasItens.get("6.7.1") + maioresNotasItens.get("6.7.2") + maioresNotasItens.get("6.7.3"))
                + " DE 3");

        System.out.println("[ITEM PLANEJAMENTO - PROCEDIMENTOS DE ANALISE]: " + (maioresNotasItens.get("6.8.1"))
                + " DE 1");

        System.out.println("======================================================================================");

        System.out.println("[ITEM ANALISES]: "
                + (maioresNotasItens.get("8.1.1") + maioresNotasItens.get("8.1.2") + maioresNotasItens.get("8.2.1")
                        + maioresNotasItens.get("8.3.1") + maioresNotasItens.get("8.3.2")) + " DE 5");

        System.out.println("[ITEM ANALISES - ESTATISTICA DESCRITIVA]: "
                + (maioresNotasItens.get("8.1.1") + maioresNotasItens.get("8.1.2")) + " DE 2");

        System.out.println("[ITEM ANALISES - PREPARACAO DOS DADOS]: " + (maioresNotasItens.get("8.2.1")) + " DE 1");

        System.out.println("[ITEM ANALISES - TESTE DE HIPÓTESE]: "
                + (maioresNotasItens.get("8.3.1") + maioresNotasItens.get("8.3.2")) + " DE 2");

        System.out.println("======================================================================================");

        System.out.println("[ITEM DISCUSSAO]: "
                + (maioresNotasItens.get("9.1.1") + maioresNotasItens.get("9.1.2") + maioresNotasItens.get("9.2.1")
                        + maioresNotasItens.get("9.3.1") + maioresNotasItens.get("9.4.1")) + " DE 5");

        System.out.println("[ITEM DISCUSSAO - AVALIACAO RESULTADOS]: "
                + (maioresNotasItens.get("9.1.1") + maioresNotasItens.get("9.1.2")) + " DE 2");

        System.out.println("[ITEM DISCUSSAO - AMEACAS A VALIDADE]: " + (maioresNotasItens.get("9.2.1")) + " DE 1");

        System.out.println("[ITEM DISCUSSAO - INFERENCIAS]: " + (maioresNotasItens.get("9.3.1")) + " DE 1");

        System.out.println("[ITEM DISCUSSAO - INFERENCIAS]: " + (maioresNotasItens.get("9.4.1")) + " DE 1");

        System.out.println("======================================================================================");

        System.out.println("[ITEM CONCLUSOES]: "
                + (maioresNotasItens.get("10.1.1") + maioresNotasItens.get("10.2.1") + maioresNotasItens.get("10.3.1"))
                + " DE 3");

        System.out.println("[ITEM CONCLUSOES - RESUMO]: " + (maioresNotasItens.get("10.1.1")) + " DE 1");

        System.out.println("[ITEM CONCLUSOES - IMPACTO]: " + (maioresNotasItens.get("10.2.1")) + " DE 1");

        System.out.println("[ITEM CONCLUSOES - TRABALHOS FUTUROS]: " + (maioresNotasItens.get("10.3.1")) + " DE 1");

        System.out.println("======================================================================================");

        System.out.println("[ITEM APENDICES]: " + (maioresNotasItens.get("11.1.1")) + " DE 1");
    }
}
