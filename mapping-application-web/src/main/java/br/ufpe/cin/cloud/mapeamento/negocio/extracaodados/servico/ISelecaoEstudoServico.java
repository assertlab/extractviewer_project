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
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.SelecaoEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.SelecaoEstudo;

/**
 * Representa a definição da camada de serviço de uma {@link SelecaoEstudo} no Sistema.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 14:45:30
 */
public interface ISelecaoEstudoServico extends IServico<SelecaoEstudo, SelecaoEstudoBean> {

    void montarSelecaoCriterioInclusaoExclusao(SelecaoEstudoBean selecao);
}
