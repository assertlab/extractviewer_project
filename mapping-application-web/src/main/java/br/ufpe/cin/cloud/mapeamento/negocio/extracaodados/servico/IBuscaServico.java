/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Busca;

/**
 * Representa a definição da camada de serviço de uma {@link Busca} no Sistema.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 14:45:30
 */
public interface IBuscaServico extends IServico<Busca, BuscaBean> {

	/**
	 * Identifica os estudos contidos no arquivo.bib e os importa para a coleção
	 * dentro do objeto.
	 * 
	 * @param busca
	 *            A instância que contém os dados da busca da qual se deseja
	 *            recuperar os estudos.
	 */
	void tratarEstudosContidosArquivoBib(BuscaBean busca);
	
	/**
	 * Identifica os estudos contidos no arquivo.xls e os importa para a coleção
	 * dentro do objeto.
	 * 
	 * @param busca
	 *            A instância que contém os dados da busca da qual se deseja
	 *            recuperar os estudos.
	 */
	void tratarEstudosContidosArquivoExcel(BuscaBean busca);

	/**
	 * Identifica os estudos contidos no arquivo.bib e gera o arquivo excel
	 * correspondente contendo os dados encontrados.
	 * 
	 * @param busca
	 *            A instância que contém os dados da busca da qual se deseja
	 *            recuperar os estudos.
	 */
	void gerarPlanilhaEstudosContidosArquivoBib(BuscaBean busca);

	/**
	 * Executa o diagnóstico do conteúdo do arquivo bib de uma determinada
	 * busca. O diagnóstico consiste na realização da leitura e validação do
	 * link dos artigos.
	 * 
	 * @param busca
	 *            A instância que contém os dados da busca a serem validados.
	 */
	void executarDiagnosticoArquivoBib(BuscaBean busca);

	/**
	 * Insere os dados dos estudos encontrados na busca para a base de dados do sistema.
	 * 
	 * @param busca A instância que contém os dados da busca.
	 */
	void importarEstudosParaBaseDados(BuscaBean busca);
	
}
