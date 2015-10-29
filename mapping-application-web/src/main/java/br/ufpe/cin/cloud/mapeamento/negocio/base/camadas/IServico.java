/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.camadas;

import java.io.Serializable;
import java.util.List;

/**
 * Representa as operações padrões da camada de negócios da aplicação.
 * 
 * @author helaine.lins
 * @created 08/04/2014 - 21:43:03
 */
public interface IServico<E extends Entidade, B extends BaseBean> extends Serializable{

    /**
     * Realiza a inclusão da instância da entidade no sistema.
     * 
     * @param entidade A instância a ser inserida.
     * @return Um {@link Long} contendo o identificador da entidade gerado no sistema.
     */
    Long incluir(B entidade);

    /**
     * Realiza a alteração dos dados da instãncia no sistema.
     * 
     * @param entidade A instância a ser alterada.
     */
    void alterar(B entidade);

    /**
     * Remove a instância da entidade do sistema.
     * 
     * @param entidade A instância a ser excluída.
     */
    void excluir(B entidade);
    
    /**
     * Recupera a instância da entidade que contém o identificador informado.
     * 
     * @param identificador Representa o identificador da entidade.
     * @return B a instância que foi encontrada.
     */
    B recuperar(Long identificador);
    
    /**
     * Recupera todas as intâncias da entidade cadastradas no sistema.
     * 
     * @param em Representa a instância do mecanismo de persistência.
     * @return Um List<B> as instâncias que foram localizadas.
     */
    List<B> listar();
    
}
