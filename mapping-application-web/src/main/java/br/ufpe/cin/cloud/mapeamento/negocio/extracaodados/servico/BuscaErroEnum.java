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
public enum BuscaErroEnum implements IEnumErro {

    ERRO_ABRIR_ARQUIVO_BIB("Busca.erro.leituraArquivoBib"),
    ERRO_EXTRAIR_ESTUDOS_ARQUIVO_EXCEL_PLANILHA("Busca.erro.extracaoEstudosArquivoExcelPlanilha"),
    ERRO_EXTRAIR_ESTUDOS_ARQUIVO_EXCEL("Busca.erro.extracaoEstudosArquivoExcel"),
    ERRO_EXTRAIR_ESTUDOS_ARQUIVO_BIB("Busca.erro.extracaoEstudosArquivoBib"),
    ERRO_GERAR_PLANILHA_ESTUDOS_ARQUIVO_BIB("Busca.erro.geracaoPlanilhaEstudosArquivoBib"),
    ERRO_INCLUIR_ESTUDOS_ARQUIVO_BIB("Busca.erro.validacaoInclusaoEstudo"),
    ERRO_INCLUIR_ESTUDOS_BUSCA("Busca.erro.inclusaoEstudosBusca"),
    ERRO_DESTINO_NAO_DIRETORIO("Busca.erro.diretorio"),
    ERRO_DESTINO_PERMISSAO_LEITURA("Busca.erro.diretorioPermissaoLeitura");

    /**
     * Representa o código da enumeração.
     */
    private String codigo;

    /**
     * Cria uma nova instância da enumeração inicializando o seu código e descrição correspondente.
     * 
     * @param codigo A instância que representa o código da enumeração.
     */
    private BuscaErroEnum(String codigo) {
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
