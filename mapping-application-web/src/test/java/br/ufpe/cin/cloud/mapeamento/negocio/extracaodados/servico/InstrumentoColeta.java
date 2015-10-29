/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;

/**
 * Representa os dados do instrumento de coleta dos dados.
 * 
 * @author helaine.lins
 * @created 11/08/2015 - 14:47:53
 */
public class InstrumentoColeta extends BaseBean {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 2147644032057644973L;

    private Integer idEstudo;
    private String tituloEstudo;
    private int idPlanilha;

    private double mediaEstudo;

    //    private double mediaTitulo;
    //    private double mediaAutoria;
    //    private double mediaResumo;
    //    private double mediaIntroducao;
    //    private double mediaFundamentacao;
    //    private double mediaPlanejamento;
    //    private double mediaExecucao;
    //    private double mediaAnalise;
    //    private double mediaDiscusao;
    //    private double mediaConclusao;
    //    private double mediaApendice;

    private double titulo;
    private double titulo11;
    private double titulo12;
    private double titulo13;

    private double autoria;
    private double autoria21;

    private double resumo;

    private double resumoFundamentacao;
    private double resumoFundamentacao311;

    private double resumoObjetivo;
    private double resumoObjetivo321;
    private double resumoObjetivo322;
    private double resumoObjetivo323;
    private double resumoObjetivo324;

    private double resumoMetodo;
    private double resumoMetodo331;
    private double resumoMetodo332;
    private double resumoMetodo333;
    private double resumoMetodo334;
    private double resumoMetodo335;

    private double resumoResultados;
    private double resumoResultados341;

    private double resumoLimitacoes;
    private double resumoLimitacoes351;

    private double resumoConclusoes;
    private double resumoConclusoes361;

    private double introducao;

    private double introducaoFormulacaoProblema;
    private double introducaoFormulacaoProblema411;

    private double introducaoObjetivo;
    private double introducaoObjetivo421;

    private double introducaoContexto;
    private double introducaoContexto431;

    private double fundamentacao;
    private double fundamentacao511;
    private double fundamentacao512;
    private double fundamentacao513;
    private double fundamentacao514;

    private double planejamento;

    private double planejamentoObjetivos;
    private double planejamentoObjetivos611;

    private double planejamentoUnidades;
    private double planejamentoUnidades621;

    private double planejamentoMateriais;
    private double planejamentoMateriais631;

    private double planejamentoAtividades;
    private double planejamentoAtividades641;

    private double planejamentoHipoteses;
    private double planejamentoHipoteses651;
    private double planejamentoHipoteses652;
    private double planejamentoHipoteses653;
    private double planejamentoHipoteses654;

    private double planejamentoDesenho;
    private double planejamentoDesenho661;

    private double planejamentoProcedimentos;
    private double planejamentoProcedimentos671;
    private double planejamentoProcedimentos672;
    private double planejamentoProcedimentos673;

    private double planejamentoProcedimentosAnalise;
    private double planejamentoProcedimentosAnalise681;

    private double execucao;

    private double execucaoPreparacao;
    private double execucaoPreparacao711;

    private double analises;

    private double analisesEstatisticaDescritiva;
    private double analisesEstatisticaDescritiva811;
    private double analisesEstatisticaDescritiva812;

    private double analisesPreparacaoDados;
    private double analisesPreparacaoDados821;

    private double analisesTesteHipotese;
    private double analisesTesteHipotese831;
    private double analisesTesteHipotese832;

    private double discussao;

    private double discussaoResultados;
    private double discussaoResultados911;
    private double discussaoResultados912;

    private double discussaoAmeacas;
    private double discussaoAmeacas921;

    private double discussaoInferencias;
    private double discussaoInferencias931;

    private double discussaoLicoesAprendidas;
    private double discussaoLicoesAprendidas941;

    private double conclusoes;

    private double conclusoesResumo;
    private double conclusoesResumo1011;

    private double conclusoesImpacto;
    private double conclusoesImpacto1021;

    private double conclusoesTrabalhosFuturos;
    private double conclusoesTrabalhosFuturos1031;

    private double apendices;

    /**
     * Obtém o valor do atributo idEstudo.
     * 
     * @return Uma instância de {@link Integer} contendo o valor do atributo idEstudo.
     */
    public Integer getIdEstudo() {
        return this.idEstudo;
    }

    /**
     * Atualiza a instância de idEstudo com o valor de idEstudo.
     * 
     * @param idEstudo Uma instância de Integer contendo o valor a ser atualizado.
     */
    public void setIdEstudo(Integer idEstudo) {
        this.idEstudo = idEstudo;
    }

    /**
     * Obtém o valor do atributo tituloEstudo.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo tituloEstudo.
     */
    public String getTituloEstudo() {
        return this.tituloEstudo;
    }

    /**
     * Atualiza a instância de tituloEstudo com o valor de tituloEstudo.
     * 
     * @param tituloEstudo Uma instância de String contendo o valor a ser atualizado.
     */
    public void setTituloEstudo(String tituloEstudo) {
        this.tituloEstudo = tituloEstudo;
    }

    /**
     * Obtém o valor do atributo mediaEstudo.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo mediaEstudo.
     */
    public double getMediaEstudo() {
        return this.mediaEstudo;
    }

    /**
     * Atualiza a instância de mediaEstudo com o valor de mediaEstudo.
     * 
     * @param mediaEstudo Uma instância de double contendo o valor a ser atualizado.
     */
    public void setMediaEstudo(double mediaEstudo) {
        this.mediaEstudo = mediaEstudo;
    }

    /**
     * Obtém o valor do atributo titulo.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo titulo.
     */
    public double getTitulo() {
        return this.titulo;
    }

    /**
     * Atualiza a instância de titulo com o valor de titulo.
     * 
     * @param titulo Uma instância de double contendo o valor a ser atualizado.
     */
    public void setTitulo(double titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém o valor do atributo titulo11.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo titulo11.
     */
    public double getTitulo11() {
        return this.titulo11;
    }

    /**
     * Atualiza a instância de titulo11 com o valor de titulo11.
     * 
     * @param titulo11 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setTitulo11(double titulo11) {
        this.titulo11 = titulo11;
    }

    /**
     * Obtém o valor do atributo titulo12.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo titulo12.
     */
    public double getTitulo12() {
        return this.titulo12;
    }

    /**
     * Atualiza a instância de titulo12 com o valor de titulo12.
     * 
     * @param titulo12 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setTitulo12(double titulo12) {
        this.titulo12 = titulo12;
    }

    /**
     * Obtém o valor do atributo titulo13.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo titulo13.
     */
    public double getTitulo13() {
        return this.titulo13;
    }

    /**
     * Atualiza a instância de titulo13 com o valor de titulo13.
     * 
     * @param titulo13 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setTitulo13(double titulo13) {
        this.titulo13 = titulo13;
    }

    /**
     * Obtém o valor do atributo autoria.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo autoria.
     */
    public double getAutoria() {
        return this.autoria;
    }

    /**
     * Atualiza a instância de autoria com o valor de autoria.
     * 
     * @param autoria Uma instância de double contendo o valor a ser atualizado.
     */
    public void setAutoria(double autoria) {
        this.autoria = autoria;
    }

    /**
     * Obtém o valor do atributo autoria21.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo autoria21.
     */
    public double getAutoria21() {
        return this.autoria21;
    }

    /**
     * Atualiza a instância de autoria21 com o valor de autoria21.
     * 
     * @param autoria21 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setAutoria21(double autoria21) {
        this.autoria21 = autoria21;
    }

    /**
     * Obtém o valor do atributo resumo.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumo.
     */
    public double getResumo() {
        return this.resumo;
    }

    /**
     * Atualiza a instância de resumo com o valor de resumo.
     * 
     * @param resumo Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumo(double resumo) {
        this.resumo = resumo;
    }

    /**
     * Obtém o valor do atributo resumoFundamentacao.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoFundamentacao.
     */
    public double getResumoFundamentacao() {
        return this.resumoFundamentacao;
    }

    /**
     * Atualiza a instância de resumoFundamentacao com o valor de resumoFundamentacao.
     * 
     * @param resumoFundamentacao Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoFundamentacao(double resumoFundamentacao) {
        this.resumoFundamentacao = resumoFundamentacao;
    }

    /**
     * Obtém o valor do atributo resumoFundamentacao311.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoFundamentacao311.
     */
    public double getResumoFundamentacao311() {
        return this.resumoFundamentacao311;
    }

    /**
     * Atualiza a instância de resumoFundamentacao311 com o valor de resumoFundamentacao311.
     * 
     * @param resumoFundamentacao311 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoFundamentacao311(double resumoFundamentacao311) {
        this.resumoFundamentacao311 = resumoFundamentacao311;
    }

    /**
     * Obtém o valor do atributo resumoObjetivo.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoObjetivo.
     */
    public double getResumoObjetivo() {
        return this.resumoObjetivo;
    }

    /**
     * Atualiza a instância de resumoObjetivo com o valor de resumoObjetivo.
     * 
     * @param resumoObjetivo Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoObjetivo(double resumoObjetivo) {
        this.resumoObjetivo = resumoObjetivo;
    }

    /**
     * Obtém o valor do atributo resumoObjetivo321.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoObjetivo321.
     */
    public double getResumoObjetivo321() {
        return this.resumoObjetivo321;
    }

    /**
     * Atualiza a instância de resumoObjetivo321 com o valor de resumoObjetivo321.
     * 
     * @param resumoObjetivo321 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoObjetivo321(double resumoObjetivo321) {
        this.resumoObjetivo321 = resumoObjetivo321;
    }

    /**
     * Obtém o valor do atributo resumoObjetivo322.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoObjetivo322.
     */
    public double getResumoObjetivo322() {
        return this.resumoObjetivo322;
    }

    /**
     * Atualiza a instância de resumoObjetivo322 com o valor de resumoObjetivo322.
     * 
     * @param resumoObjetivo322 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoObjetivo322(double resumoObjetivo322) {
        this.resumoObjetivo322 = resumoObjetivo322;
    }

    /**
     * Obtém o valor do atributo resumoObjetivo323.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoObjetivo323.
     */
    public double getResumoObjetivo323() {
        return this.resumoObjetivo323;
    }

    /**
     * Atualiza a instância de resumoObjetivo323 com o valor de resumoObjetivo323.
     * 
     * @param resumoObjetivo323 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoObjetivo323(double resumoObjetivo323) {
        this.resumoObjetivo323 = resumoObjetivo323;
    }

    /**
     * Obtém o valor do atributo resumoObjetivo324.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoObjetivo324.
     */
    public double getResumoObjetivo324() {
        return this.resumoObjetivo324;
    }

    /**
     * Atualiza a instância de resumoObjetivo324 com o valor de resumoObjetivo324.
     * 
     * @param resumoObjetivo324 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoObjetivo324(double resumoObjetivo324) {
        this.resumoObjetivo324 = resumoObjetivo324;
    }

    /**
     * Obtém o valor do atributo resumoMetodo.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoMetodo.
     */
    public double getResumoMetodo() {
        return this.resumoMetodo;
    }

    /**
     * Atualiza a instância de resumoMetodo com o valor de resumoMetodo.
     * 
     * @param resumoMetodo Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoMetodo(double resumoMetodo) {
        this.resumoMetodo = resumoMetodo;
    }

    /**
     * Obtém o valor do atributo resumoMetodo331.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoMetodo331.
     */
    public double getResumoMetodo331() {
        return this.resumoMetodo331;
    }

    /**
     * Atualiza a instância de resumoMetodo331 com o valor de resumoMetodo331.
     * 
     * @param resumoMetodo331 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoMetodo331(double resumoMetodo331) {
        this.resumoMetodo331 = resumoMetodo331;
    }

    /**
     * Obtém o valor do atributo resumoMetodo332.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoMetodo332.
     */
    public double getResumoMetodo332() {
        return this.resumoMetodo332;
    }

    /**
     * Atualiza a instância de resumoMetodo332 com o valor de resumoMetodo332.
     * 
     * @param resumoMetodo332 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoMetodo332(double resumoMetodo332) {
        this.resumoMetodo332 = resumoMetodo332;
    }

    /**
     * Obtém o valor do atributo resumoMetodo333.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoMetodo333.
     */
    public double getResumoMetodo333() {
        return this.resumoMetodo333;
    }

    /**
     * Atualiza a instância de resumoMetodo333 com o valor de resumoMetodo333.
     * 
     * @param resumoMetodo333 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoMetodo333(double resumoMetodo333) {
        this.resumoMetodo333 = resumoMetodo333;
    }

    /**
     * Obtém o valor do atributo resumoMetodo334.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoMetodo334.
     */
    public double getResumoMetodo334() {
        return this.resumoMetodo334;
    }

    /**
     * Atualiza a instância de resumoMetodo334 com o valor de resumoMetodo334.
     * 
     * @param resumoMetodo334 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoMetodo334(double resumoMetodo334) {
        this.resumoMetodo334 = resumoMetodo334;
    }

    /**
     * Obtém o valor do atributo resumoMetodo335.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoMetodo335.
     */
    public double getResumoMetodo335() {
        return this.resumoMetodo335;
    }

    /**
     * Atualiza a instância de resumoMetodo335 com o valor de resumoMetodo335.
     * 
     * @param resumoMetodo335 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoMetodo335(double resumoMetodo335) {
        this.resumoMetodo335 = resumoMetodo335;
    }

    /**
     * Obtém o valor do atributo resumoResultados.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoResultados.
     */
    public double getResumoResultados() {
        return this.resumoResultados;
    }

    /**
     * Atualiza a instância de resumoResultados com o valor de resumoResultados.
     * 
     * @param resumoResultados Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoResultados(double resumoResultados) {
        this.resumoResultados = resumoResultados;
    }

    /**
     * Obtém o valor do atributo resumoResultados341.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoResultados341.
     */
    public double getResumoResultados341() {
        return this.resumoResultados341;
    }

    /**
     * Atualiza a instância de resumoResultados341 com o valor de resumoResultados341.
     * 
     * @param resumoResultados341 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoResultados341(double resumoResultados341) {
        this.resumoResultados341 = resumoResultados341;
    }

    /**
     * Obtém o valor do atributo resumoLimitacoes.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoLimitacoes.
     */
    public double getResumoLimitacoes() {
        return this.resumoLimitacoes;
    }

    /**
     * Atualiza a instância de resumoLimitacoes com o valor de resumoLimitacoes.
     * 
     * @param resumoLimitacoes Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoLimitacoes(double resumoLimitacoes) {
        this.resumoLimitacoes = resumoLimitacoes;
    }

    /**
     * Obtém o valor do atributo resumoLimitacoes351.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoLimitacoes351.
     */
    public double getResumoLimitacoes351() {
        return this.resumoLimitacoes351;
    }

    /**
     * Atualiza a instância de resumoLimitacoes351 com o valor de resumoLimitacoes351.
     * 
     * @param resumoLimitacoes351 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoLimitacoes351(double resumoLimitacoes351) {
        this.resumoLimitacoes351 = resumoLimitacoes351;
    }

    /**
     * Obtém o valor do atributo resumoConclusoes.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoConclusoes.
     */
    public double getResumoConclusoes() {
        return this.resumoConclusoes;
    }

    /**
     * Atualiza a instância de resumoConclusoes com o valor de resumoConclusoes.
     * 
     * @param resumoConclusoes Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoConclusoes(double resumoConclusoes) {
        this.resumoConclusoes = resumoConclusoes;
    }

    /**
     * Obtém o valor do atributo resumoConclusoes361.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo resumoConclusoes361.
     */
    public double getResumoConclusoes361() {
        return this.resumoConclusoes361;
    }

    /**
     * Atualiza a instância de resumoConclusoes361 com o valor de resumoConclusoes361.
     * 
     * @param resumoConclusoes361 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setResumoConclusoes361(double resumoConclusoes361) {
        this.resumoConclusoes361 = resumoConclusoes361;
    }

    /**
     * Obtém o valor do atributo introducao.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo introducao.
     */
    public double getIntroducao() {
        return this.introducao;
    }

    /**
     * Atualiza a instância de introducao com o valor de introducao.
     * 
     * @param introducao Uma instância de double contendo o valor a ser atualizado.
     */
    public void setIntroducao(double introducao) {
        this.introducao = introducao;
    }

    /**
     * Obtém o valor do atributo introducaoFormulacaoProblema.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo introducaoFormulacaoProblema.
     */
    public double getIntroducaoFormulacaoProblema() {
        return this.introducaoFormulacaoProblema;
    }

    /**
     * Atualiza a instância de introducaoFormulacaoProblema com o valor de introducaoFormulacaoProblema.
     * 
     * @param introducaoFormulacaoProblema Uma instância de double contendo o valor a ser atualizado.
     */
    public void setIntroducaoFormulacaoProblema(double introducaoFormulacaoProblema) {
        this.introducaoFormulacaoProblema = introducaoFormulacaoProblema;
    }

    /**
     * Obtém o valor do atributo introducaoFormulacaoProblema411.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo introducaoFormulacaoProblema411.
     */
    public double getIntroducaoFormulacaoProblema411() {
        return this.introducaoFormulacaoProblema411;
    }

    /**
     * Atualiza a instância de introducaoFormulacaoProblema411 com o valor de introducaoFormulacaoProblema411.
     * 
     * @param introducaoFormulacaoProblema411 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setIntroducaoFormulacaoProblema411(double introducaoFormulacaoProblema411) {
        this.introducaoFormulacaoProblema411 = introducaoFormulacaoProblema411;
    }

    /**
     * Obtém o valor do atributo introducaoObjetivo.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo introducaoObjetivo.
     */
    public double getIntroducaoObjetivo() {
        return this.introducaoObjetivo;
    }

    /**
     * Atualiza a instância de introducaoObjetivo com o valor de introducaoObjetivo.
     * 
     * @param introducaoObjetivo Uma instância de double contendo o valor a ser atualizado.
     */
    public void setIntroducaoObjetivo(double introducaoObjetivo) {
        this.introducaoObjetivo = introducaoObjetivo;
    }

    /**
     * Obtém o valor do atributo introducaoObjetivo421.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo introducaoObjetivo421.
     */
    public double getIntroducaoObjetivo421() {
        return this.introducaoObjetivo421;
    }

    /**
     * Atualiza a instância de introducaoObjetivo421 com o valor de introducaoObjetivo421.
     * 
     * @param introducaoObjetivo421 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setIntroducaoObjetivo421(double introducaoObjetivo421) {
        this.introducaoObjetivo421 = introducaoObjetivo421;
    }

    /**
     * Obtém o valor do atributo introducaoContexto.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo introducaoContexto.
     */
    public double getIntroducaoContexto() {
        return this.introducaoContexto;
    }

    /**
     * Atualiza a instância de introducaoContexto com o valor de introducaoContexto.
     * 
     * @param introducaoContexto Uma instância de double contendo o valor a ser atualizado.
     */
    public void setIntroducaoContexto(double introducaoContexto) {
        this.introducaoContexto = introducaoContexto;
    }

    /**
     * Obtém o valor do atributo introducaoContexto431.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo introducaoContexto431.
     */
    public double getIntroducaoContexto431() {
        return this.introducaoContexto431;
    }

    /**
     * Atualiza a instância de introducaoContexto431 com o valor de introducaoContexto431.
     * 
     * @param introducaoContexto431 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setIntroducaoContexto431(double introducaoContexto431) {
        this.introducaoContexto431 = introducaoContexto431;
    }

    /**
     * Obtém o valor do atributo fundamentacao.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo fundamentacao.
     */
    public double getFundamentacao() {
        return this.fundamentacao;
    }

    /**
     * Atualiza a instância de fundamentacao com o valor de fundamentacao.
     * 
     * @param fundamentacao Uma instância de double contendo o valor a ser atualizado.
     */
    public void setFundamentacao(double fundamentacao) {
        this.fundamentacao = fundamentacao;
    }

    /**
     * Obtém o valor do atributo fundamentacao511.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo fundamentacao511.
     */
    public double getFundamentacao511() {
        return this.fundamentacao511;
    }

    /**
     * Atualiza a instância de fundamentacao511 com o valor de fundamentacao511.
     * 
     * @param fundamentacao511 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setFundamentacao511(double fundamentacao511) {
        this.fundamentacao511 = fundamentacao511;
    }

    /**
     * Obtém o valor do atributo fundamentacao512.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo fundamentacao512.
     */
    public double getFundamentacao512() {
        return this.fundamentacao512;
    }

    /**
     * Atualiza a instância de fundamentacao512 com o valor de fundamentacao512.
     * 
     * @param fundamentacao512 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setFundamentacao512(double fundamentacao512) {
        this.fundamentacao512 = fundamentacao512;
    }

    /**
     * Obtém o valor do atributo fundamentacao513.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo fundamentacao513.
     */
    public double getFundamentacao513() {
        return this.fundamentacao513;
    }

    /**
     * Atualiza a instância de fundamentacao513 com o valor de fundamentacao513.
     * 
     * @param fundamentacao513 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setFundamentacao513(double fundamentacao513) {
        this.fundamentacao513 = fundamentacao513;
    }

    /**
     * Obtém o valor do atributo fundamentacao514.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo fundamentacao514.
     */
    public double getFundamentacao514() {
        return this.fundamentacao514;
    }

    /**
     * Atualiza a instância de fundamentacao514 com o valor de fundamentacao514.
     * 
     * @param fundamentacao514 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setFundamentacao514(double fundamentacao514) {
        this.fundamentacao514 = fundamentacao514;
    }

    /**
     * Obtém o valor do atributo planejamento.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamento.
     */
    public double getPlanejamento() {
        return this.planejamento;
    }

    /**
     * Atualiza a instância de planejamento com o valor de planejamento.
     * 
     * @param planejamento Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamento(double planejamento) {
        this.planejamento = planejamento;
    }

    /**
     * Obtém o valor do atributo planejamentoObjetivos.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoObjetivos.
     */
    public double getPlanejamentoObjetivos() {
        return this.planejamentoObjetivos;
    }

    /**
     * Atualiza a instância de planejamentoObjetivos com o valor de planejamentoObjetivos.
     * 
     * @param planejamentoObjetivos Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoObjetivos(double planejamentoObjetivos) {
        this.planejamentoObjetivos = planejamentoObjetivos;
    }

    /**
     * Obtém o valor do atributo planejamentoObjetivos611.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoObjetivos611.
     */
    public double getPlanejamentoObjetivos611() {
        return this.planejamentoObjetivos611;
    }

    /**
     * Atualiza a instância de planejamentoObjetivos611 com o valor de planejamentoObjetivos611.
     * 
     * @param planejamentoObjetivos611 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoObjetivos611(double planejamentoObjetivos611) {
        this.planejamentoObjetivos611 = planejamentoObjetivos611;
    }

    /**
     * Obtém o valor do atributo planejamentoUnidades.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoUnidades.
     */
    public double getPlanejamentoUnidades() {
        return this.planejamentoUnidades;
    }

    /**
     * Atualiza a instância de planejamentoUnidades com o valor de planejamentoUnidades.
     * 
     * @param planejamentoUnidades Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoUnidades(double planejamentoUnidades) {
        this.planejamentoUnidades = planejamentoUnidades;
    }

    /**
     * Obtém o valor do atributo planejamentoUnidades621.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoUnidades621.
     */
    public double getPlanejamentoUnidades621() {
        return this.planejamentoUnidades621;
    }

    /**
     * Atualiza a instância de planejamentoUnidades621 com o valor de planejamentoUnidades621.
     * 
     * @param planejamentoUnidades621 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoUnidades621(double planejamentoUnidades621) {
        this.planejamentoUnidades621 = planejamentoUnidades621;
    }

    /**
     * Obtém o valor do atributo planejamentoMateriais.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoMateriais.
     */
    public double getPlanejamentoMateriais() {
        return this.planejamentoMateriais;
    }

    /**
     * Atualiza a instância de planejamentoMateriais com o valor de planejamentoMateriais.
     * 
     * @param planejamentoMateriais Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoMateriais(double planejamentoMateriais) {
        this.planejamentoMateriais = planejamentoMateriais;
    }

    /**
     * Obtém o valor do atributo planejamentoMateriais631.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoMateriais631.
     */
    public double getPlanejamentoMateriais631() {
        return this.planejamentoMateriais631;
    }

    /**
     * Atualiza a instância de planejamentoMateriais631 com o valor de planejamentoMateriais631.
     * 
     * @param planejamentoMateriais631 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoMateriais631(double planejamentoMateriais631) {
        this.planejamentoMateriais631 = planejamentoMateriais631;
    }

    /**
     * Obtém o valor do atributo planejamentoAtividades.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoAtividades.
     */
    public double getPlanejamentoAtividades() {
        return this.planejamentoAtividades;
    }

    /**
     * Atualiza a instância de planejamentoAtividades com o valor de planejamentoAtividades.
     * 
     * @param planejamentoAtividades Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoAtividades(double planejamentoAtividades) {
        this.planejamentoAtividades = planejamentoAtividades;
    }

    /**
     * Obtém o valor do atributo planejamentoAtividades641.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoAtividades641.
     */
    public double getPlanejamentoAtividades641() {
        return this.planejamentoAtividades641;
    }

    /**
     * Atualiza a instância de planejamentoAtividades641 com o valor de planejamentoAtividades641.
     * 
     * @param planejamentoAtividades641 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoAtividades641(double planejamentoAtividades641) {
        this.planejamentoAtividades641 = planejamentoAtividades641;
    }

    /**
     * Obtém o valor do atributo planejamentoHipoteses.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoHipoteses.
     */
    public double getPlanejamentoHipoteses() {
        return this.planejamentoHipoteses;
    }

    /**
     * Atualiza a instância de planejamentoHipoteses com o valor de planejamentoHipoteses.
     * 
     * @param planejamentoHipoteses Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoHipoteses(double planejamentoHipoteses) {
        this.planejamentoHipoteses = planejamentoHipoteses;
    }

    /**
     * Obtém o valor do atributo planejamentoHipoteses651.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoHipoteses651.
     */
    public double getPlanejamentoHipoteses651() {
        return this.planejamentoHipoteses651;
    }

    /**
     * Atualiza a instância de planejamentoHipoteses651 com o valor de planejamentoHipoteses651.
     * 
     * @param planejamentoHipoteses651 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoHipoteses651(double planejamentoHipoteses651) {
        this.planejamentoHipoteses651 = planejamentoHipoteses651;
    }

    /**
     * Obtém o valor do atributo planejamentoHipoteses652.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoHipoteses652.
     */
    public double getPlanejamentoHipoteses652() {
        return this.planejamentoHipoteses652;
    }

    /**
     * Atualiza a instância de planejamentoHipoteses652 com o valor de planejamentoHipoteses652.
     * 
     * @param planejamentoHipoteses652 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoHipoteses652(double planejamentoHipoteses652) {
        this.planejamentoHipoteses652 = planejamentoHipoteses652;
    }

    /**
     * Obtém o valor do atributo planejamentoHipoteses653.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoHipoteses653.
     */
    public double getPlanejamentoHipoteses653() {
        return this.planejamentoHipoteses653;
    }

    /**
     * Atualiza a instância de planejamentoHipoteses653 com o valor de planejamentoHipoteses653.
     * 
     * @param planejamentoHipoteses653 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoHipoteses653(double planejamentoHipoteses653) {
        this.planejamentoHipoteses653 = planejamentoHipoteses653;
    }

    /**
     * Obtém o valor do atributo planejamentoHipoteses654.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoHipoteses654.
     */
    public double getPlanejamentoHipoteses654() {
        return this.planejamentoHipoteses654;
    }

    /**
     * Atualiza a instância de planejamentoHipoteses654 com o valor de planejamentoHipoteses654.
     * 
     * @param planejamentoHipoteses654 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoHipoteses654(double planejamentoHipoteses654) {
        this.planejamentoHipoteses654 = planejamentoHipoteses654;
    }

    /**
     * Obtém o valor do atributo planejamentoDesenho.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoDesenho.
     */
    public double getPlanejamentoDesenho() {
        return this.planejamentoDesenho;
    }

    /**
     * Atualiza a instância de planejamentoDesenho com o valor de planejamentoDesenho.
     * 
     * @param planejamentoDesenho Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoDesenho(double planejamentoDesenho) {
        this.planejamentoDesenho = planejamentoDesenho;
    }

    /**
     * Obtém o valor do atributo planejamentoDesenho661.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoDesenho661.
     */
    public double getPlanejamentoDesenho661() {
        return this.planejamentoDesenho661;
    }

    /**
     * Atualiza a instância de planejamentoDesenho661 com o valor de planejamentoDesenho661.
     * 
     * @param planejamentoDesenho661 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoDesenho661(double planejamentoDesenho661) {
        this.planejamentoDesenho661 = planejamentoDesenho661;
    }

    /**
     * Obtém o valor do atributo planejamentoProcedimentos.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoProcedimentos.
     */
    public double getPlanejamentoProcedimentos() {
        return this.planejamentoProcedimentos;
    }

    /**
     * Atualiza a instância de planejamentoProcedimentos com o valor de planejamentoProcedimentos.
     * 
     * @param planejamentoProcedimentos Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoProcedimentos(double planejamentoProcedimentos) {
        this.planejamentoProcedimentos = planejamentoProcedimentos;
    }

    /**
     * Obtém o valor do atributo planejamentoProcedimentos671.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoProcedimentos671.
     */
    public double getPlanejamentoProcedimentos671() {
        return this.planejamentoProcedimentos671;
    }

    /**
     * Atualiza a instância de planejamentoProcedimentos671 com o valor de planejamentoProcedimentos671.
     * 
     * @param planejamentoProcedimentos671 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoProcedimentos671(double planejamentoProcedimentos671) {
        this.planejamentoProcedimentos671 = planejamentoProcedimentos671;
    }

    /**
     * Obtém o valor do atributo planejamentoProcedimentos672.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoProcedimentos672.
     */
    public double getPlanejamentoProcedimentos672() {
        return this.planejamentoProcedimentos672;
    }

    /**
     * Atualiza a instância de planejamentoProcedimentos672 com o valor de planejamentoProcedimentos672.
     * 
     * @param planejamentoProcedimentos672 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoProcedimentos672(double planejamentoProcedimentos672) {
        this.planejamentoProcedimentos672 = planejamentoProcedimentos672;
    }

    /**
     * Obtém o valor do atributo planejamentoProcedimentos673.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoProcedimentos673.
     */
    public double getPlanejamentoProcedimentos673() {
        return this.planejamentoProcedimentos673;
    }

    /**
     * Atualiza a instância de planejamentoProcedimentos673 com o valor de planejamentoProcedimentos673.
     * 
     * @param planejamentoProcedimentos673 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoProcedimentos673(double planejamentoProcedimentos673) {
        this.planejamentoProcedimentos673 = planejamentoProcedimentos673;
    }

    /**
     * Obtém o valor do atributo planejamentoProcedimentosAnalise.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoProcedimentosAnalise.
     */
    public double getPlanejamentoProcedimentosAnalise() {
        return this.planejamentoProcedimentosAnalise;
    }

    /**
     * Atualiza a instância de planejamentoProcedimentosAnalise com o valor de planejamentoProcedimentosAnalise.
     * 
     * @param planejamentoProcedimentosAnalise Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoProcedimentosAnalise(double planejamentoProcedimentosAnalise) {
        this.planejamentoProcedimentosAnalise = planejamentoProcedimentosAnalise;
    }

    /**
     * Obtém o valor do atributo planejamentoProcedimentosAnalise681.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo planejamentoProcedimentosAnalise681.
     */
    public double getPlanejamentoProcedimentosAnalise681() {
        return this.planejamentoProcedimentosAnalise681;
    }

    /**
     * Atualiza a instância de planejamentoProcedimentosAnalise681 com o valor de
     * planejamentoProcedimentosAnalise681.
     * 
     * @param planejamentoProcedimentosAnalise681 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setPlanejamentoProcedimentosAnalise681(double planejamentoProcedimentosAnalise681) {
        this.planejamentoProcedimentosAnalise681 = planejamentoProcedimentosAnalise681;
    }

    /**
     * Obtém o valor do atributo execucao.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo execucao.
     */
    public double getExecucao() {
        return this.execucao;
    }

    /**
     * Atualiza a instância de execucao com o valor de execucao.
     * 
     * @param execucao Uma instância de double contendo o valor a ser atualizado.
     */
    public void setExecucao(double execucao) {
        this.execucao = execucao;
    }

    /**
     * Obtém o valor do atributo execucaoPreparacao.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo execucaoPreparacao.
     */
    public double getExecucaoPreparacao() {
        return this.execucaoPreparacao;
    }

    /**
     * Atualiza a instância de execucaoPreparacao com o valor de execucaoPreparacao.
     * 
     * @param execucaoPreparacao Uma instância de double contendo o valor a ser atualizado.
     */
    public void setExecucaoPreparacao(double execucaoPreparacao) {
        this.execucaoPreparacao = execucaoPreparacao;
    }

    /**
     * Obtém o valor do atributo execucaoPreparacao711.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo execucaoPreparacao711.
     */
    public double getExecucaoPreparacao711() {
        return this.execucaoPreparacao711;
    }

    /**
     * Atualiza a instância de execucaoPreparacao711 com o valor de execucaoPreparacao711.
     * 
     * @param execucaoPreparacao711 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setExecucaoPreparacao711(double execucaoPreparacao711) {
        this.execucaoPreparacao711 = execucaoPreparacao711;
    }

    /**
     * Obtém o valor do atributo analises.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo analises.
     */
    public double getAnalises() {
        return this.analises;
    }

    /**
     * Atualiza a instância de analises com o valor de analises.
     * 
     * @param analises Uma instância de double contendo o valor a ser atualizado.
     */
    public void setAnalises(double analises) {
        this.analises = analises;
    }

    /**
     * Obtém o valor do atributo analisesEstatisticaDescritiva.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo analisesEstatisticaDescritiva.
     */
    public double getAnalisesEstatisticaDescritiva() {
        return this.analisesEstatisticaDescritiva;
    }

    /**
     * Atualiza a instância de analisesEstatisticaDescritiva com o valor de analisesEstatisticaDescritiva.
     * 
     * @param analisesEstatisticaDescritiva Uma instância de double contendo o valor a ser atualizado.
     */
    public void setAnalisesEstatisticaDescritiva(double analisesEstatisticaDescritiva) {
        this.analisesEstatisticaDescritiva = analisesEstatisticaDescritiva;
    }

    /**
     * Obtém o valor do atributo analisesEstatisticaDescritiva811.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo analisesEstatisticaDescritiva811.
     */
    public double getAnalisesEstatisticaDescritiva811() {
        return this.analisesEstatisticaDescritiva811;
    }

    /**
     * Atualiza a instância de analisesEstatisticaDescritiva811 com o valor de analisesEstatisticaDescritiva811.
     * 
     * @param analisesEstatisticaDescritiva811 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setAnalisesEstatisticaDescritiva811(double analisesEstatisticaDescritiva811) {
        this.analisesEstatisticaDescritiva811 = analisesEstatisticaDescritiva811;
    }

    /**
     * Obtém o valor do atributo analisesEstatisticaDescritiva812.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo analisesEstatisticaDescritiva812.
     */
    public double getAnalisesEstatisticaDescritiva812() {
        return this.analisesEstatisticaDescritiva812;
    }

    /**
     * Atualiza a instância de analisesEstatisticaDescritiva812 com o valor de analisesEstatisticaDescritiva812.
     * 
     * @param analisesEstatisticaDescritiva812 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setAnalisesEstatisticaDescritiva812(double analisesEstatisticaDescritiva812) {
        this.analisesEstatisticaDescritiva812 = analisesEstatisticaDescritiva812;
    }

    /**
     * Obtém o valor do atributo analisesPreparacaoDados.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo analisesPreparacaoDados.
     */
    public double getAnalisesPreparacaoDados() {
        return this.analisesPreparacaoDados;
    }

    /**
     * Atualiza a instância de analisesPreparacaoDados com o valor de analisesPreparacaoDados.
     * 
     * @param analisesPreparacaoDados Uma instância de double contendo o valor a ser atualizado.
     */
    public void setAnalisesPreparacaoDados(double analisesPreparacaoDados) {
        this.analisesPreparacaoDados = analisesPreparacaoDados;
    }

    /**
     * Obtém o valor do atributo analisesPreparacaoDados821.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo analisesPreparacaoDados821.
     */
    public double getAnalisesPreparacaoDados821() {
        return this.analisesPreparacaoDados821;
    }

    /**
     * Atualiza a instância de analisesPreparacaoDados821 com o valor de analisesPreparacaoDados821.
     * 
     * @param analisesPreparacaoDados821 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setAnalisesPreparacaoDados821(double analisesPreparacaoDados821) {
        this.analisesPreparacaoDados821 = analisesPreparacaoDados821;
    }

    /**
     * Obtém o valor do atributo analisesTesteHipotese.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo analisesTesteHipotese.
     */
    public double getAnalisesTesteHipotese() {
        return this.analisesTesteHipotese;
    }

    /**
     * Atualiza a instância de analisesTesteHipotese com o valor de analisesTesteHipotese.
     * 
     * @param analisesTesteHipotese Uma instância de double contendo o valor a ser atualizado.
     */
    public void setAnalisesTesteHipotese(double analisesTesteHipotese) {
        this.analisesTesteHipotese = analisesTesteHipotese;
    }

    /**
     * Obtém o valor do atributo analisesTesteHipotese831.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo analisesTesteHipotese831.
     */
    public double getAnalisesTesteHipotese831() {
        return this.analisesTesteHipotese831;
    }

    /**
     * Atualiza a instância de analisesTesteHipotese831 com o valor de analisesTesteHipotese831.
     * 
     * @param analisesTesteHipotese831 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setAnalisesTesteHipotese831(double analisesTesteHipotese831) {
        this.analisesTesteHipotese831 = analisesTesteHipotese831;
    }

    /**
     * Obtém o valor do atributo analisesTesteHipotese832.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo analisesTesteHipotese832.
     */
    public double getAnalisesTesteHipotese832() {
        return this.analisesTesteHipotese832;
    }

    /**
     * Atualiza a instância de analisesTesteHipotese832 com o valor de analisesTesteHipotese832.
     * 
     * @param analisesTesteHipotese832 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setAnalisesTesteHipotese832(double analisesTesteHipotese832) {
        this.analisesTesteHipotese832 = analisesTesteHipotese832;
    }

    /**
     * Obtém o valor do atributo discussao.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo discussao.
     */
    public double getDiscussao() {
        return this.discussao;
    }

    /**
     * Atualiza a instância de discussao com o valor de discussao.
     * 
     * @param discussao Uma instância de double contendo o valor a ser atualizado.
     */
    public void setDiscussao(double discussao) {
        this.discussao = discussao;
    }

    /**
     * Obtém o valor do atributo discussaoResultados.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo discussaoResultados.
     */
    public double getDiscussaoResultados() {
        return this.discussaoResultados;
    }

    /**
     * Atualiza a instância de discussaoResultados com o valor de discussaoResultados.
     * 
     * @param discussaoResultados Uma instância de double contendo o valor a ser atualizado.
     */
    public void setDiscussaoResultados(double discussaoResultados) {
        this.discussaoResultados = discussaoResultados;
    }

    /**
     * Obtém o valor do atributo discussaoResultados911.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo discussaoResultados911.
     */
    public double getDiscussaoResultados911() {
        return this.discussaoResultados911;
    }

    /**
     * Atualiza a instância de discussaoResultados911 com o valor de discussaoResultados911.
     * 
     * @param discussaoResultados911 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setDiscussaoResultados911(double discussaoResultados911) {
        this.discussaoResultados911 = discussaoResultados911;
    }

    /**
     * Obtém o valor do atributo discussaoResultados912.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo discussaoResultados912.
     */
    public double getDiscussaoResultados912() {
        return this.discussaoResultados912;
    }

    /**
     * Atualiza a instância de discussaoResultados912 com o valor de discussaoResultados912.
     * 
     * @param discussaoResultados912 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setDiscussaoResultados912(double discussaoResultados912) {
        this.discussaoResultados912 = discussaoResultados912;
    }

    /**
     * Obtém o valor do atributo discussaoAmeacas.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo discussaoAmeacas.
     */
    public double getDiscussaoAmeacas() {
        return this.discussaoAmeacas;
    }

    /**
     * Atualiza a instância de discussaoAmeacas com o valor de discussaoAmeacas.
     * 
     * @param discussaoAmeacas Uma instância de double contendo o valor a ser atualizado.
     */
    public void setDiscussaoAmeacas(double discussaoAmeacas) {
        this.discussaoAmeacas = discussaoAmeacas;
    }

    /**
     * Obtém o valor do atributo discussaoAmeacas921.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo discussaoAmeacas921.
     */
    public double getDiscussaoAmeacas921() {
        return this.discussaoAmeacas921;
    }

    /**
     * Atualiza a instância de discussaoAmeacas921 com o valor de discussaoAmeacas921.
     * 
     * @param discussaoAmeacas921 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setDiscussaoAmeacas921(double discussaoAmeacas921) {
        this.discussaoAmeacas921 = discussaoAmeacas921;
    }

    /**
     * Obtém o valor do atributo discussaoInferencias.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo discussaoInferencias.
     */
    public double getDiscussaoInferencias() {
        return this.discussaoInferencias;
    }

    /**
     * Atualiza a instância de discussaoInferencias com o valor de discussaoInferencias.
     * 
     * @param discussaoInferencias Uma instância de double contendo o valor a ser atualizado.
     */
    public void setDiscussaoInferencias(double discussaoInferencias) {
        this.discussaoInferencias = discussaoInferencias;
    }

    /**
     * Obtém o valor do atributo discussaoInferencias931.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo discussaoInferencias931.
     */
    public double getDiscussaoInferencias931() {
        return this.discussaoInferencias931;
    }

    /**
     * Atualiza a instância de discussaoInferencias931 com o valor de discussaoInferencias931.
     * 
     * @param discussaoInferencias931 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setDiscussaoInferencias931(double discussaoInferencias931) {
        this.discussaoInferencias931 = discussaoInferencias931;
    }

    /**
     * Obtém o valor do atributo discussaoLicoesAprendidas.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo discussaoLicoesAprendidas.
     */
    public double getDiscussaoLicoesAprendidas() {
        return this.discussaoLicoesAprendidas;
    }

    /**
     * Atualiza a instância de discussaoLicoesAprendidas com o valor de discussaoLicoesAprendidas.
     * 
     * @param discussaoLicoesAprendidas Uma instância de double contendo o valor a ser atualizado.
     */
    public void setDiscussaoLicoesAprendidas(double discussaoLicoesAprendidas) {
        this.discussaoLicoesAprendidas = discussaoLicoesAprendidas;
    }

    /**
     * Obtém o valor do atributo discussaoLicoesAprendidas941.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo discussaoLicoesAprendidas941.
     */
    public double getDiscussaoLicoesAprendidas941() {
        return this.discussaoLicoesAprendidas941;
    }

    /**
     * Atualiza a instância de discussaoLicoesAprendidas941 com o valor de discussaoLicoesAprendidas941.
     * 
     * @param discussaoLicoesAprendidas941 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setDiscussaoLicoesAprendidas941(double discussaoLicoesAprendidas941) {
        this.discussaoLicoesAprendidas941 = discussaoLicoesAprendidas941;
    }

    /**
     * Obtém o valor do atributo conclusoes.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo conclusoes.
     */
    public double getConclusoes() {
        return this.conclusoes;
    }

    /**
     * Atualiza a instância de conclusoes com o valor de conclusoes.
     * 
     * @param conclusoes Uma instância de double contendo o valor a ser atualizado.
     */
    public void setConclusoes(double conclusoes) {
        this.conclusoes = conclusoes;
    }

    /**
     * Obtém o valor do atributo conclusoesResumo.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo conclusoesResumo.
     */
    public double getConclusoesResumo() {
        return this.conclusoesResumo;
    }

    /**
     * Atualiza a instância de conclusoesResumo com o valor de conclusoesResumo.
     * 
     * @param conclusoesResumo Uma instância de double contendo o valor a ser atualizado.
     */
    public void setConclusoesResumo(double conclusoesResumo) {
        this.conclusoesResumo = conclusoesResumo;
    }

    /**
     * Obtém o valor do atributo conclusoesResumo1011.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo conclusoesResumo1011.
     */
    public double getConclusoesResumo1011() {
        return this.conclusoesResumo1011;
    }

    /**
     * Atualiza a instância de conclusoesResumo1011 com o valor de conclusoesResumo1011.
     * 
     * @param conclusoesResumo1011 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setConclusoesResumo1011(double conclusoesResumo1011) {
        this.conclusoesResumo1011 = conclusoesResumo1011;
    }

    /**
     * Obtém o valor do atributo conclusoesImpacto.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo conclusoesImpacto.
     */
    public double getConclusoesImpacto() {
        return this.conclusoesImpacto;
    }

    /**
     * Atualiza a instância de conclusoesImpacto com o valor de conclusoesImpacto.
     * 
     * @param conclusoesImpacto Uma instância de double contendo o valor a ser atualizado.
     */
    public void setConclusoesImpacto(double conclusoesImpacto) {
        this.conclusoesImpacto = conclusoesImpacto;
    }

    /**
     * Obtém o valor do atributo conclusoesImpacto1021.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo conclusoesImpacto1021.
     */
    public double getConclusoesImpacto1021() {
        return this.conclusoesImpacto1021;
    }

    /**
     * Atualiza a instância de conclusoesImpacto1021 com o valor de conclusoesImpacto1021.
     * 
     * @param conclusoesImpacto1021 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setConclusoesImpacto1021(double conclusoesImpacto1021) {
        this.conclusoesImpacto1021 = conclusoesImpacto1021;
    }

    /**
     * Obtém o valor do atributo conclusoesTrabalhosFuturos.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo conclusoesTrabalhosFuturos.
     */
    public double getConclusoesTrabalhosFuturos() {
        return this.conclusoesTrabalhosFuturos;
    }

    /**
     * Atualiza a instância de conclusoesTrabalhosFuturos com o valor de conclusoesTrabalhosFuturos.
     * 
     * @param conclusoesTrabalhosFuturos Uma instância de double contendo o valor a ser atualizado.
     */
    public void setConclusoesTrabalhosFuturos(double conclusoesTrabalhosFuturos) {
        this.conclusoesTrabalhosFuturos = conclusoesTrabalhosFuturos;
    }

    /**
     * Obtém o valor do atributo conclusoesTrabalhosFuturos1031.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo conclusoesTrabalhosFuturos1031.
     */
    public double getConclusoesTrabalhosFuturos1031() {
        return this.conclusoesTrabalhosFuturos1031;
    }

    /**
     * Atualiza a instância de conclusoesTrabalhosFuturos1031 com o valor de conclusoesTrabalhosFuturos1031.
     * 
     * @param conclusoesTrabalhosFuturos1031 Uma instância de double contendo o valor a ser atualizado.
     */
    public void setConclusoesTrabalhosFuturos1031(double conclusoesTrabalhosFuturos1031) {
        this.conclusoesTrabalhosFuturos1031 = conclusoesTrabalhosFuturos1031;
    }

    /**
     * Obtém o valor do atributo apendices.
     * 
     * @return Uma instância de {@link double} contendo o valor do atributo apendices.
     */
    public double getApendices() {
        return this.apendices;
    }

    /**
     * Atualiza a instância de apendices com o valor de apendices.
     * 
     * @param apendices Uma instância de double contendo o valor a ser atualizado.
     */
    public void setApendices(double apendices) {
        this.apendices = apendices;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
     */
    @Override
    public <E extends Entidade> E getEntidade() {
        return null;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getBeanID()
     */
    @Override
    public Long getBeanID() {
        return null;
    }

    /**
     * Obtém o valor do atributo idPlanilha.
     * 
     * @return Uma instância de {@link int} contendo o valor do atributo idPlanilha.
     */
    public int getIdPlanilha() {
        return this.idPlanilha;
    }

    /**
     * Atualiza a instância de idPlanilha com o valor de idPlanilha.
     * 
     * @param idPlanilha Uma instância de int contendo o valor a ser atualizado.
     */
    public void setIdPlanilha(int idPlanilha) {
        this.idPlanilha = idPlanilha;
    }

}
