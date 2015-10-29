/**
 * SiVest - Sistema de Inscrição do Vestibular da UPE
 * 
 * Copyright (c) Universidade Federal de Pernambuco.
 * 
 * Este software é confidencial e propriedade da UPE. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do UPE. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.dao.IUsuarioDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Perfil;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;

/**
 * Representa a implementação das regras de negócio do controle de acesso no sistema.
 * 
 * @author helaine.lins
 * @created 09/04/2014 - 00:03:21
 */
@Service("hibernateUserDetailsService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class ControleAcessoServico implements UserDetailsService {

    /**
     * Representa o mecanismo de log da classe.
     */
    private Log LOG = LogFactory.getLog(ControleAcessoServico.class);

    /**
     * Representa a instância da camada de acesso a dados da entidade {@link Perfil}.
     */
    @Autowired
    private IUsuarioDAO usuarioDAO;

    /**
     * Atualiza a instância de usuarioDAO com o valor de usuarioDAO.
     * 
     * @param usuarioDAO Uma instância de IUsuarioDAO contendo o valor a ser atualizado.
     */
    public void setUsuarioDAO(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException {
        Usuario usuario = null;

        if (StringUtils.isBlank(login)) {
            LOG.error("Nao e possivel carregar dados de usuario com login nao preenchido.");
            String msgErro =
                    MapeamentoUtil
                            .recuperarMensagemProperties(UsuarioErroEnum.FALHA_BUSCAR_CADASTRO_LOGIN_USUARIO_INVALIDO
                                    .getChave());
            
            throw new UsernameNotFoundException(msgErro);
        }

        try {
        	usuario = this.usuarioDAO.buscarUsuarioLoginSistema(login);
        } catch (Exception e) {
            LOG.error("Erro ao identificar usuario de login:" + login, e);
            throw new MapeamentoException(UsuarioErroEnum.FALHA_BUSCAR_CADASTRO_LOGIN_USUARIO, TipoErro.INESPERADO);
        }

        if (usuario == null) {
            LOG.error("Nao existe usuario cadastrado com o login:" + login);
            String msgErro =
 MapeamentoUtil
					.recuperarMensagemProperties(UsuarioErroEnum.FALHA_BUSCAR_LOGIN_USUARIO_INVALIDO
                            .getChave());
            
            throw new UsernameNotFoundException(msgErro);
        }
        
        return usuario.getBean();
    }

}
