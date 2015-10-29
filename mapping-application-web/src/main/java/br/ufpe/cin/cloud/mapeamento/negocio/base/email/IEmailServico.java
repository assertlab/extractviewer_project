/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.email;

import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;

/**
 * Representa a definição dos serviços de envio de email da aplicação.
 * 
 * @author helaine.lins
 * @created 28/06/2014 - 03:21:29
 */
public interface IEmailServico {

    /**
     * Envia um email para o usuário para confirmação do email cadastrado pelo usuário.
     * 
     * @param usuario A instância que contém os dados do usuário.
     */
    void enviarEmailConfirmacaoCadastro(final Usuario usuario);

    /**
     * Envia um email para o usuário informando a nova senha cadastrada.
     * 
     * @param usuario A instância que contém os dados do usuário.
     */
    void enviarEmailNovaSenha(final Usuario usuario);

}
