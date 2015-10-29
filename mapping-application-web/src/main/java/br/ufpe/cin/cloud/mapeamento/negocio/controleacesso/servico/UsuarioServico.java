/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico;
import br.ufpe.cin.cloud.mapeamento.negocio.base.email.IEmailServico;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.MapeamentoException;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.dao.IPerfilDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.dao.IUsuarioDAO;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Perfil;
import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;

/**
 * Representa a implementação de negócio de usuários no sistema.
 * 
 * @author helaine.lins
 * @created 22/04/2014 - 19:53:49
 */
@Service(value = "usuarioServico")
public class UsuarioServico extends AbstractServico<Usuario, UsuarioBean>
		implements IUsuarioServico {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -3456050323996450354L;

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private Log LOG = LogFactory.getLog(UsuarioServico.class);

	/**
	 * Instancia da camada de acesso a dados da entidade {@link UsuarioBean}.
	 */
	@Autowired
	private IUsuarioDAO dao;

	/**
	 * Camada de acesso a dados da entidade {@link Perfil}.
	 */
	@Autowired
	private IPerfilDAO perfilDAO;

	/**
	 * Camada de acesso aos dados do serviço de email do sistema.
	 */
	@Autowired
	private IEmailServico emailServico;

	/**
	 * Atualiza a instância de dao com o valor de dao.
	 * 
	 * @param dao
	 *            Uma instância de IUsuarioDAO contendo o valor a ser
	 *            atualizado.
	 */
	public void setDao(IUsuarioDAO dao) {
		this.dao = dao;
	}

	/**
	 * Atualiza a instância de perfilDAO com o valor de perfilDAO.
	 * 
	 * @param perfilDAO
	 *            Uma instância de IPerfilDAO contendo o valor a ser atualizado.
	 */
	public void setPerfilDAO(IPerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getDao()
	 */
	@Override
	public IUsuarioDAO getDao() {
		return this.dao;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#getLog()
	 */
	@Override
	public Log getLog() {
		return LOG;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.IServico#incluir(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	@Transactional(readOnly = false)
	public Long incluir(UsuarioBean bean) {
		this.validarInclusaoUsuario(bean);

		Usuario entidade = bean.getEntidade();

		String senha = null;
		try {
			senha = MapeamentoUtil.encriptarSenha(entidade.getSenha());
		} catch (Exception e) {
			LOG.error(
					"Erro ao tentar encriptar senha de usuario:"
							+ entidade.getLogin() + " na inclusao.", e);
			throw new MapeamentoException(UsuarioErroEnum.FALHA_ENCRIPTACAO,
					TipoErro.INESPERADO);
		}

		entidade.setSenha(senha);

		entidade.setEmailConfirmado(false);
		entidade.setAtivo(true);

		String codigoAtivacao = String.valueOf(
				System.currentTimeMillis() + Math.random()).substring(8);
		entidade.setCodigoAtivacaoEmail(codigoAtivacao);

		Perfil perfilUsuario = this.perfilDAO.obterPerfilUsuario();

		if (perfilUsuario == null) {
			LOG.error("Nao foi possivel recuperar perfil do tipo usuario");
			throw new MapeamentoException(
					UsuarioErroEnum.PERFIL_USUARIO_NAO_ENCONTRADO,
					TipoErro.NEGOCIO);
		}

		List<Perfil> perfis = new ArrayList<Perfil>(1);
		perfis.add(perfilUsuario);
		entidade.setPerfis(perfis);

		Long id = null;
		try {
			id = getDao().incluir(entidade);
			entidade.setId(id);
		} catch (Exception e) {
			LOG.error("Nao foi possivel incluir usuario:" + entidade.getLogin());
			throw new MapeamentoException(UsuarioErroEnum.FALHA_INCLUIR,
					TipoErro.INESPERADO);
		}

		LOG.info("Incluido usuario:" + entidade.getLogin() + " id:"
				+ entidade.getId());

		this.emailServico.enviarEmailConfirmacaoCadastro(entidade);

		return id;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.IUsuarioServico#validarInclusaoUsuario(br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean)
	 */
	@Override
	public void validarInclusaoUsuario(UsuarioBean bean) {
		Usuario entidade = bean.getEntidade();

		super.validar(entidade, "emailConfirmado", "ativo", "perfis");

		boolean existeLogin = false;
		try {

			existeLogin = dao.existeUsuarioLogin(entidade.getLogin(), null);
		} catch (Exception e) {
			LOG.error("Nao foi possivel identificar existencia de login:"
					+ entidade.getLogin(), e);
			throw new MapeamentoException(UsuarioErroEnum.FALHA_INCLUIR,
					TipoErro.NEGOCIO);
		}

		if (existeLogin) {
			LOG.error("Nao foi possivel incluir usuario, ja existe cadastro para o login:"
					+ entidade.getLogin());
			throw new MapeamentoException(
					UsuarioErroEnum.INCLUSAO_LOGIN_JA_EXISTENTE,
					TipoErro.NEGOCIO);
		}
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.IUsuarioServico#alterar(br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean,
	 *      boolean)
	 */
	@Override
	@Transactional(readOnly = false)
	public void alterar(UsuarioBean bean, boolean admin) {
		this.validarAlteracaoUsuario(bean);

		Usuario dadosAnteriores = null;

		try {
			dadosAnteriores = dao.recuperar(bean.getId());
		} catch (Exception e) {
			LOG.error(
					"Nao foi possivel alterar pois ocorreu falha ao recuperar os dados do usuário:"
							+ bean.getLogin(), e);

			throw new MapeamentoException(UsuarioErroEnum.FALHA_ALTERAR,
					TipoErro.INESPERADO);
		}

		// Identifica se houve alteração de email
		boolean emailAlterado = !dadosAnteriores.getEmail().equals(
				bean.getEmail());
		if (emailAlterado) {
			dadosAnteriores.setEmail(bean.getEmail());

			if (admin) {
				dadosAnteriores.setEmailConfirmado(false);
				bean.setEmailConfirmado(false);
			}
		}

		dadosAnteriores.setNacionalidade(bean.getNacionalidade());
		dadosAnteriores.setNome(bean.getNome());
		dadosAnteriores.setSexo(bean.getSexo().getEnumeracao());
		dadosAnteriores.setLogin(bean.getLogin());

		try {
			dao.alterar(dadosAnteriores);
		} catch (Exception e) {
			LOG.error(
					"Nao foi possivel alterar dados do usuario:"
							+ bean.getLogin() + " por falha de acesso a base",
					e);

			throw new MapeamentoException(UsuarioErroEnum.FALHA_ALTERAR,
					TipoErro.INESPERADO);
		}

		if (emailAlterado && !admin) {
			emailServico.enviarEmailConfirmacaoCadastro(dadosAnteriores);
		}
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.IUsuarioServico#validarAlteracaoUsuario(br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.bean.UsuarioBean)
	 */
	@Override
	public void validarAlteracaoUsuario(UsuarioBean bean) {
		Usuario entidade = bean.getEntidade();

		super.validar(entidade, "perfis");

		boolean existeLogin = false;
		try {
			existeLogin = dao.existeUsuarioLogin(entidade.getLogin(),
					entidade.getId());
		} catch (Exception e) {
			LOG.error("Nao foi possivel identificar existencia de login:"
					+ entidade.getLogin() + " para outro identificador", e);

			throw new MapeamentoException(UsuarioErroEnum.FALHA_ALTERAR,
					TipoErro.NEGOCIO);
		}

		if (existeLogin) {
			LOG.error("Nao foi possivel alterar usuario, já existe cadastro para o login:"
					+ entidade.getLogin());
			throw new MapeamentoException(
					UsuarioErroEnum.ALTERACAO_LOGIN_JA_EXISTENTE,
					TipoErro.NEGOCIO);
		}

	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.IUsuarioServico#buscarUsuarioLogin(java.lang.String)
	 */
	@Override
	public UsuarioBean buscarUsuarioLogin(String login) {
		UsuarioBean bean = null;
		
		try {
			final Usuario usuario = dao.buscarUsuarioLoginSistema(login);
			
			if (usuario != null) {
				bean = usuario.getBean();
			}
			
		} catch (Exception e) {
			LOG.error("Nao foi possivel identificar usuário de login:"
					+ login, e);

			throw new MapeamentoException(UsuarioErroEnum.FALHA_BUSCAR_CADASTRO_LOGIN_USUARIO,
					TipoErro.NEGOCIO);
		}

		return bean;
	}

	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.IUsuarioServico#ativarCadastro(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false)
	public void ativarCadastro(String login, String codigo) {
		Usuario usuario = null;

		try {
			usuario = dao.buscarUsuarioLoginSistema(login);
		} catch (Exception e) {
			LOG.error(
					"Nao foi possivel ativar cadastro pois ocorreu falha ao identificar existencia de login:"
							+ login, e);
			throw new MapeamentoException(UsuarioErroEnum.FALHA_ATIVAR_CADASTRO,
					TipoErro.INESPERADO);
		}

		if (usuario == null) {
			LOG.error("Nao foi possivel ativar cadastro pois nao foi identificado usuario com login:"
					+ login);
			throw new MapeamentoException(
					UsuarioErroEnum.FALHA_ATIVAR_CADASTRO_USUARIO_INEXISTENTE,
					TipoErro.NEGOCIO);
		}

		if (!usuario.getCodigoAtivacaoEmail().equalsIgnoreCase(codigo)) {
			LOG.error("Nao foi possivel ativar cadastro pois o codigo de ativacao do login:"
					+ login
					+ " é invalido - informado:"
					+ codigo
					+ " esperado:"
					+ usuario.getCodigoAtivacaoEmail());

			throw new MapeamentoException(UsuarioErroEnum.CODIGO_ATIVACAO_INVALIDO,
					TipoErro.NEGOCIO);
		}

		usuario.setEmailConfirmado(true);

		try {
			dao.alterar(usuario);
		} catch (Exception e) {
			LOG.error("Erro inesperado ao ativar cadastro usuario com login:"
					+ login, e);
			throw new MapeamentoException(UsuarioErroEnum.FALHA_ATIVAR_CADASTRO,
					TipoErro.INESPERADO);
		}
	}
	
	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.IUsuarioServico#enviarEmailConfirmacao(java.lang.String)
	 */
	@Override
	public void enviarEmailConfirmacao(String login) {
		Usuario usuario = null;

		try {
			usuario = dao.buscarUsuarioLoginSistema(login);
		} catch (Exception e) {
			LOG.error(
					"Nao foi possivel ativar cadastro pois ocorreu falha ao identificar existencia de cpf:"
							+ login, e);
			throw new MapeamentoException(UsuarioErroEnum.FALHA_ATIVAR_CADASTRO,
					TipoErro.INESPERADO);
		}

		if (usuario == null) {
			LOG.error("Nao foi possivel ativar cadastro pois nao foi identificado usuario com cpf:"
					+ login);
			throw new MapeamentoException(
					UsuarioErroEnum.FALHA_ATIVAR_CADASTRO_USUARIO_INEXISTENTE,
					TipoErro.NEGOCIO);
		}

		this.emailServico.enviarEmailConfirmacaoCadastro(usuario);
	}
	
	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.servico.IUsuarioServico#alterarEmail(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false)
	public void alterarEmail(String login, String novoEmail) {

		if (StringUtils.isBlank(login)) {
			LOG.error("Nao foi possivel alterar email pois o login nao foi informado");
			throw new MapeamentoException(UsuarioErroEnum.FALHA_ALTERAR_EMAIL,
					TipoErro.VALIDACAO);
		}

		Usuario usuario = null;

		try {
			usuario = dao.buscarUsuarioLoginSistema(login);
		} catch (Exception e) {
			LOG.error(
					"Nao foi possivel alterar pois ocorreu falha ao identificar existencia de login:"
							+ login, e);

			throw new MapeamentoException(UsuarioErroEnum.FALHA_ALTERAR_EMAIL,
					TipoErro.INESPERADO);
		}

		if (usuario == null) {
			LOG.error("Nao foi possivel alterar email pois nao existe cadastro para login:"
					+ login);
			throw new MapeamentoException(UsuarioErroEnum.FALHA_ALTERAR_EMAIL,
					TipoErro.NEGOCIO);
		}

		usuario.setEmail(novoEmail);
		usuario.setEmailConfirmado(false);

		String codigoAtivacao = String.valueOf(
				System.currentTimeMillis() + Math.random()).substring(8);
		usuario.setCodigoAtivacaoEmail(codigoAtivacao);

		try {
			this.dao.alterar(usuario);
		} catch (Exception e) {
			LOG.error("Nao foi possivel alterar email para login:" + login);
			throw new MapeamentoException(UsuarioErroEnum.FALHA_ALTERAR_EMAIL,
					TipoErro.INESPERADO);
		}

		this.emailServico.enviarEmailConfirmacaoCadastro(usuario);
	}
	
	/**
	 * {@inheritDoc}.
	 *
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.AbstractServico#copiarDadosParaAlterarEntidade(br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean, br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade)
	 */
	@Override
	protected void copiarDadosParaAlterarEntidade(UsuarioBean bean, Usuario entidade) {
		entidade.setCodigoAtivacaoEmail(bean.getCodigoAtivacaoEmail());
		entidade.setDataUltimaAlteracao(LocalDateTime.now());
		entidade.setEmail(bean.getEmail());
		entidade.setEmailConfirmado(bean.getEmailConfirmado());
		entidade.setLogin(bean.getLogin());
		entidade.setNacionalidade(bean.getNacionalidade());
		entidade.setNome(bean.getNome());
		entidade.setSenha(MapeamentoUtil.encriptarSenha(bean.getSenha()));
		
		if (bean.getSexo() != null) {
			entidade.setSexo(bean.getSexo().getEnumeracao());
		}
		
	}
}
