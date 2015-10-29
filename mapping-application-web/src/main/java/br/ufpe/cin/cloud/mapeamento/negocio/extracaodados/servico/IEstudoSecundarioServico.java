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
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstudoSecundarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Criterio;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.EstudoSecundario;

/**
 * Representa a definição da camada de negócios da entidade {@link Criterio}.
 * 
 * @author helaine.lins
 * @created 28/08/2014 - 15:24:22
 */
public interface IEstudoSecundarioServico extends IServico<EstudoSecundario, EstudoSecundarioBean> {

    /**
     * Gera a etapa de duplas a partir do resultado do passo de seleção inicial dos estudos.
     * 
     * @param idPassoRevisao O identificador do passo de revisão inicial.
     */
    public void gerarPassoDuplas(Long idPassoRevisao);
    
}
