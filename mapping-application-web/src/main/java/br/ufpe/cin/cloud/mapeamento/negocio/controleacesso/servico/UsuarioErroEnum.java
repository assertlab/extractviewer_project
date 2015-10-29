/**
 * SiVest - Sistema de Inscrição do Vestibular da UPE
 * 
 * Copyright (c) Universidade Federal de Pernambuco.
 * 
 * Este software é confidencial e propriedade da UPE. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do UPE. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico;

import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.IEnumErro;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;

/**
 * Representa as chaves de mensagens de erro nas realizações de casos de uso da entidade {@link UsuarioBean}.
 * 
 * @author helaine.lins
 * @created 05/05/2014 - 18:18:23
 */
public enum UsuarioErroEnum implements IEnumErro {
	
	FALHA_ALTERAR_EMAIL("Usuario.erro.falhaAoAlterarEmail"),
	
	CODIGO_ATIVACAO_INVALIDO("Usuario.erro.codigoAtivacaoInvalido"),
	
	FALHA_ATIVAR_CADASTRO_USUARIO_INEXISTENTE("Usuario.erro.falhaAoAtivarCadastroUsuarioInexistente"),

	FALHA_ATIVAR_CADASTRO("Usuario.erro.falhaAoAtivarCadastro"),
	
	FALHA_INCLUIR("Usuario.erro.falhaAoIncluir"),
	
    INCLUSAO_LOGIN_JA_EXISTENTE("Usuario.erro.inclusaoLoginExistente"),
    
    FALHA_ENCRIPTACAO("Usuario.erro.encriptacao"),
    
    PERFIL_USUARIO_NAO_ENCONTRADO("Usuario.erro.perfilNaoEncontrado"),

    FALHA_ALTERAR("Usuario.erro.falhaAoAlterar"),

    ALTERACAO_LOGIN_JA_EXISTENTE("Usuario.erro.alteracaoLoginExistente"),
    
    FALHA_BUSCAR_CADASTRO_LOGIN_USUARIO_INVALIDO("Usuario.erro.usuarioNaoCadastradoLoginInvalido"),

    FALHA_BUSCAR_CADASTRO_LOGIN_USUARIO("Usuario.erro.buscarUsuarioLogin"),
    
    FALHA_BUSCAR_LOGIN_USUARIO_INVALIDO("Usuario.erro.usuarioNaoCadastradoLogin"),
    
    
//    ALTERACAO_CPF_JA_EXISTENTE("UsuarioBean.erro.alteracaoCpfExistente"),
//
//
//    FORA_PERIODO_MODIFICACAO_CADASTRO("UsuarioBean.erro.foraPeriodoModificacaoCadastro"),
//
//    CODIGO_ATIVACAO_INVALIDO("UsuarioBean.erro.codigoAtivacaoInvalido"),
//
//
//
//
//
//    FALHA_ALTERAR_EMAIL("UsuarioBean.erro.falhaAoAlterarEmail"),
//
//    FALHA_ATIVAR_CADASTRO("UsuarioBean.erro.falhaAoAtivarCadastro"),
//
//    FALHA_ATIVAR_CADASTRO_USUARIO_INEXISTENTE("UsuarioBean.erro.falhaAoAtivarCadastroUsuarioInexistente"),
//
//    FALHA_BUSCAR_CADASTRO_USUARIO("UsuarioBean.erro.buscarUsuarioCadastrado"),
//
//
//
//
//    FALHA_RESPOSTA_PERGUNTA_SEGURANCA_INCORRETA("UsuarioBean.erro.recuperarSenhaRespostaIncorreta"),
//
//    FALHA_RESPOSTA_PERGUNTA_SEGURANCA_INVALIDA("UsuarioBean.erro.recuperarSenhaRespostaObrigatoria"),
//    
//    FALHA_EMAIL_INFORMADO_INVALIDO("UsuarioBean.erro.recuperarSenhaEmailObrigatorio"),
//    
//    FALHA_EMAIL_INFORMADO_INCORRETO("UsuarioBean.erro.recuperarSenhaEmailIncorreto"),
//
//    FALHA_RESPOSTA_PERGUNTA_SEGURANCA("UsuarioBean.erro.recuperarSenhaErro"),
//
//    CPF_INVALIDO("UsuarioBean.erro.cpfInvalido"),
//    
//    NOME_INVALIDO("UsuarioBean.erro.nomeInvalido"),
//    
//    RG_INVALIDO("UsuarioBean.erro.rgInvalido"),
//    
//    SENHA_NAO_CONFERE("UsuarioBean.erro.senhaNaoConfere"),
//    
//    NENHUM_DADO_FORNECIDO("UsuarioBean.erro.nenhumDado"),
//    
//    TAMANHO_SENHA_INVALIDO("UsuarioBean.erro.senhaCurta"),
//    
//    CADASTRO_JA_ALTERADO("UsuarioBean.erro.cadastroJaAlterado"),
    
    CADASTRO_PERIODO_INVALIDO("UsuarioBean.erro.periodoInvalido");

    /**
     * Representa o código da enumeração.
     */
    private String codigo;

    /**
     * Cria uma nova instância da enumeração inicializando o seu código e descrição correspondente.
     * 
     * @param codigo A instância que representa o código da enumeração.
     */
    private UsuarioErroEnum(String codigo) {
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
