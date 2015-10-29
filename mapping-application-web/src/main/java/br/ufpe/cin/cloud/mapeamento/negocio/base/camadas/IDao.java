/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.camadas;

import java.util.List;

import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.DAOException;

/**
 * Representa as operações de acesso aos dados da aplicação.
 * 
 * @author helaine.lins
 * @created 08/04/2014 - 21:18:24
 */
public interface IDao<E extends Entidade> {

	/**
	 * Realiza a persistência da instância da entidade na base de dados.
	 * 
	 * @param entidade
	 *            A instância a ser inserida.
	 * @return Uma instância de {@link Long} contendo o identificador gerado.
	 * @throws DAOException
	 *             Caso ocorra algum erro durante a inclusão da instância.
	 */
	Long incluir(E entidade);
	
	/**
	 * Realiza a persistência da instância da entidade na base de dados.
	 * 
	 * @param entidades
	 *            A lista de instâncias a serem inseridas.
	 * @throws DAOException
	 *             Caso ocorra algum erro durante a inclusão das instâncias.
	 */
	void incluir(List<E> entidades);

	/**
	 * Realiza o merge da entidade persistente na base de dados.
	 * 
	 * @param entidade
	 *            A instância a ser alterada.
	 * @throws DAOException
	 *             caso ocorra algum erro durante a alteração da instância.
	 */
	void alterar(E entidade);

	/**
     * Atualiza as entidades em lote.
     * 
     * @param entidades A lista de entidades a serem atualizadas.
     */
    void alterar(List<E> entidades);
	
	/**
	 * Remove a instância da entidade da base de dados. Antes da remocao é
	 * realizado um merge.
	 * 
	 * @param entidade
	 *            A instância a ser excluída.
	 * @exception DAOException
	 *                caso ocorra algum erro durante a exclusão da instância.
	 */
	void excluir(E entidade);

	/**
	 * Recupera a instância da entidade que contém o identificador informado.
	 * 
	 * @param identificador
	 *            Representa o identificador da entidade.
	 * @return E a instância que foi encontrada.
	 * @exception DAOException
	 *                caso ocorra algum erro durante a localização da instância.
	 */
	E recuperar(Long identificador);

	/**
	 * Recupera todas as intâncias da entidade cadastradas na base de dados.
	 * 
	 * @param em
	 *            Representa a instância do mecanismo de persistência.
	 * @return Um List<E> as instâncias que foram localizadas.
	 * @exception DAOException
	 *                caso ocorra algum erro durante a localização de todas as
	 *                instâncias.
	 */
	List<E> listar();

	/**
	 * Carrega todos os objetos da entidade.
	 * 
	 * @param propOrdenacao
	 *            Indica a propriedade pela qual a lista será ordenada.
	 * @param ascendente
	 *            Indica se a ordenação será ascendente.
	 * 
	 * @return um {@link List} de {@link Entidade}.
	 */
	List<E> listar(String propOrdenacao, boolean ascendente);

	/**
	 * Carrega uma quantidade pré-definida de {@link Entidade} a partir de uma
	 * posição inicial.
	 * 
	 * @param inicio
	 *            A posição inicial
	 * @param tamanho
	 *            A quantidade a ser carregada
	 * 
	 * @return Um {@link List} de {@link Entidade}
	 */
	List<E> listar(int inicio, int tamanho);

	/**
	 * Atualiza a data de última atualização da instância de {@link Entidade}.
	 * 
	 * @param objeto
	 *            - o objeto a ser atualizado.
	 */
	void atualizarDataUltimaAlteracao(E objeto);

	/**
	 * Executa uma busca baseada em exemplos.
	 * 
	 * @param exemplo
	 *            A entidade preenchida com os dados a serem utilizados no
	 *            filtro de pesquisa.
	 * @param inicio
	 *            O registro de início da pesquisa.
	 * @param tamanho
	 *            A quantidade de registros a serem recuperados.
	 * 
	 * @return um {@link List} com as entidades encontradas.
	 */
	List<E> procurar(final E exemplo, final int inicio, final int tamanho);

	/**
	 * Executa uma busca baseada em exemplos.
	 * 
	 * @param exemplo
	 *            A entidade preenchida com os dados a serem utilizados no
	 *            filtro de pesquisa.
	 * 
	 * @return um {@link List} com as entidades encontradas.
	 */
	List<E> procurar(final E exemplo);

}
