/**
 * SiVest - Sistema de Inscrição do Vestibular da UPE
 * 
 * Copyright (c) Universidade Federal de Pernambuco.
 * 
 * Este software é confidencial e propriedade da UPE. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do UPE. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.IEnumErro;

/**
 * Representa as chaves de mensagens de erro nas realizações de casos de uso da entidade {@link NuscaBean}.
 * 
 * @author helaine.lins
 * @created 05/05/2014 - 18:18:23
 */
public enum AnaliseEstudoErroEnum implements IEnumErro {

    ERRO_ID_REVISAO_OBRIGATORIO("AnaliseEstudo.erro.listarIdRevisaoObrigatorio"),
    ERRO_ID_REVISAO_INICIO_QTD_OBRIGATORIO("AnaliseEstudo.erro.listarIdRevisaoInicioQtdObrigatorio"),
    ERRO_ESTATISTICAS_ID_REVISAO_OBRIGATORIO("AnaliseEstudo.erro.estatisticasIdRevisaoObrigatorio"),
    ERRO_ESTATISTICAS_REVISAO_NAO_IDENTIFICADA("AnaliseEstudo.erro.estatisticasEtapaNaoEncontrada"),
    ERRO_ANALISE_MATRIZ_INEXISTENTE("AnaliseEstudo.erro.matrizInexsitente"),
    ERRO_ANALISE_INEXISTENTE("AnaliseEstudo.erro.analiseInexsitente"),
    ERRO_ANALISE_MATRIZ_OBRIGATORIA("AnaliseEstudo.erro.matrizObrigatorio"),
    ERRO_ANALISE_OBRIGATORIA("AnaliseEstudo.erro.analiseObrigatoria"),
    ERRO_ANALISE_REVISOR_NAO_ENCONTRADO("AnaliseEstudo.erro.revisorNaoEncontrado"),
    ERRO_ANALISE_DUPLICADAS_FILTRO_NAO_INFORMADO("AnaliseEstudo.erro.filtroNaoInformado"),
    ERRO_VALIDACAO_DUPLICADAS_MATRIZ_NAO_INFORMADA("AnaliseEstudo.erro.duplicataNaoInformada"),
    ERRO_VALIDACAO_DUPLICADAS_DUPLICATA_NAO_INFORMADA("AnaliseEstudo.erro.matrizNaoInformada"),
    ERRO_VALIDACAO_DUPLICADAS_MATRIZ_NAO_IDENTIFICADA("AnaliseEstudo.erro.analiseMatrizNaoIdentificada"),
    ERRO_VALIDACAO_DUPLICADAS_DUPLICATA_NAO_INDENTIFICADA("AnaliseEstudo.erro.analiseDuplicataNaoIdentificada"),
    ERRO_VALIDACAO_DUPLICADAS_NAO_PERMITIDA("AnaliseEstudo.erro.analiseDuplicataMatrizJaIdentificada"),
    ERRO_VALIDACAO_QUANTITATIVO_ESTUDOS_NULO("AnaliseEstudo.erro.quantitativoEstudosNulo"),
    ERRO_VALIDACAO_CONFLITOS_ETAPA_ANALISE_OBRIGATORIA("AnaliseEstudo.erro.conflitosAnaliseNula"),
    ERRO_VALIDACAO_CONFLITOS_REVISORES_OBRIGATORIOS("AnaliseEstudo.erro.conflitosRevisoresNulo");

    /**
     * Representa o código da enumeração.
     */
    private String codigo;

    /**
     * Cria uma nova instância da enumeração inicializando o seu código e descrição correspondente.
     * 
     * @param codigo A instância que representa o código da enumeração.
     */
    private AnaliseEstudoErroEnum(String codigo) {
        this.codigo = codigo;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.IEnumErro#getChave()
     */
    @Override
    public String getChave() {
        return this.codigo;
    }

}
