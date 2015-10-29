/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade;

import javax.persistence.MappedSuperclass;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;

/**
 * Representa os estágios de um estudo sistemático da literatura: Planejamento, Condução e Report
 * 
 * @author helaine.lins
 * @created 28/04/2015 - 13:35:54
 */
@MappedSuperclass
public abstract class EstagioRevisao extends Entidade {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 6324944239599326621L;

}
