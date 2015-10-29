/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.email;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario;

/**
 * Representa a implementação do serviço de envio de emails pelo sistema.
 * 
 * @author helaine.lins
 * @created 28/06/2014 - 03:19:16
 */
@Service(value = "emailServico")
public class EmailServico implements IEmailServico {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private Log LOG = LogFactory.getLog(EmailServico.class);

	/**
	 * Representa o caminho do template de corpo de mensagem a ser enviado por
	 * email.
	 */
	private static final String TEMPLATE_CONFIRMACAO_EMAIL = "email.vm";

	/**
	 * Representa o email de origem a ser apresentado na mensagem.
	 */
	private static final String FROM = "helainelins@gmail.com";

	/**
	 * Representa o assunto do email.
	 */
	private static final String SUBJECT = "Mapping Application - Confirmação de Email";

	/**
	 * Representa o assunto do email enviado para requisição de nova senha pelo
	 * usuario.
	 */
	private static final String SUBJECT_NOVA_SENHA = "Mapping Application - Nova Senha";

	/**
	 * Representa o caminho do template de corpo de mensagem a ser enviado por
	 * email para recuperacao de senha.
	 */
	private static final String TEMPLATE_RECUPERACAO_SENHA = "emailSenha.vm";

	/**
	 * Representa o mecanismo de envio de emails.
	 */
	@Autowired
	private JavaMailSender mecanismoEnvio;

	/**
	 * Representa o engenho de velocity por onde vamos preencher o template de
	 * email a ser enviado.
	 */
	@Autowired
	private VelocityEngine engenhoVelocity;

	/**
	 * {@inheritdoc}
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.email.IEmailServico#enviarEmailConfirmacaoCadastro(br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario)
	 */
	@Async
	@Override
	public void enviarEmailConfirmacaoCadastro(final Usuario usuario) {
		int reenvio = 1;

		do {

			try {

				MimeMessagePreparator mailMessage = new MimeMessagePreparator() {

					public void prepare(MimeMessage mimeMessage)
							throws Exception {

						MimeMessageHelper message = new MimeMessageHelper(
								mimeMessage);

						message.setFrom(FROM);
						message.setTo(usuario.getEmail());
						message.setSubject(SUBJECT);

						Map<String, Object> model = new HashMap<String, Object>();
						model.put("usuario", usuario);

						String text = VelocityEngineUtils
								.mergeTemplateIntoString(engenhoVelocity,
										TEMPLATE_CONFIRMACAO_EMAIL, model);

						message.setText(text, true);
					}

				};

				this.mecanismoEnvio.send(mailMessage);

				LOG.info("Email enviado para usuario:" + usuario.getLogin()
						+ " tentativa:" + reenvio);
				break;

			} catch (Exception e) {
				LOG.info(
						"Erro ao tentar enviar email para usuario:"
								+ usuario.getLogin() + " tentativa:" + reenvio, e);
				reenvio++;
			}

		} while (reenvio < 4);
	}

	/**
	 * {@inheritdoc}
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.email.IEmailServico#enviarEmailNovaSenha(br.ufpe.cin.cloud.mapeamento.negocio.controleacesso.entidade.Usuario)
	 */
	@Async
	@Override
	public void enviarEmailNovaSenha(final Usuario usuario) {
		int reenvio = 1;
		do {
			try {

				MimeMessagePreparator mailMessage = new MimeMessagePreparator() {

					public void prepare(MimeMessage mimeMessage)
							throws Exception {

						MimeMessageHelper message = new MimeMessageHelper(
								mimeMessage);

						message.setFrom(FROM);
						message.setTo(usuario.getEmail());
						message.setSubject(SUBJECT_NOVA_SENHA);

						Map<String, Object> model = new HashMap<String, Object>();
						model.put("usuario", usuario);

						String text = VelocityEngineUtils
								.mergeTemplateIntoString(engenhoVelocity,
										TEMPLATE_RECUPERACAO_SENHA, model);

						message.setText(text, true);
					}

				};

				this.mecanismoEnvio.send(mailMessage);

				LOG.info("Email enviado para usuario:" + usuario.getLogin()
						+ " tentativa:" + reenvio);
				break;

			} catch (Exception e) {
				LOG.info(
						"Erro ao tentar enviar email para usuario:"
								+ usuario.getLogin() + " tentativa:" + reenvio, e);

				reenvio++;
			}

		} while (reenvio < 4);
	}

}
