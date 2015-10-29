/**
 * SiVest - Sistema de Inscrição do Vestibular da UPE
 * 
 * Copyright (c) Universidade Federal de Pernambuco.
 * 
 * Este software é confidencial e propriedade da UPE. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do UPE. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Representa a implementação genérica para criação de testes unitários da
 * camada de acesso a dados do projeto.
 * 
 * @author emanoel.barreiros
 * @created 01/04/2012 - 16:47:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class AbstractDAOTestHelper {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	protected static Log LOG = LogFactory.getLog(AbstractDAOTestHelper.class);

	/**
	 * Representa a sessão de persistencia.
	 */
	protected Session sess;

	/**
	 * Representa a fábrica de sessões de persistência.
	 */
	protected SessionFactory sessionFactory;

	/**
	 * Atualiza a instância de sessionFactory com o valor de sessionFactory.
	 * 
	 * @param sessionFactory
	 *            Uma instância de SessionFactory contendo o valor a ser
	 *            atualizado.
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Realiza a configuração inicial de acesso a dados da aplicação para a
	 * realização dos testes unitários com os datasources. O controle
	 * transacional está sendo feito manualmente a fim de garantir que o
	 * contexto do script de carga esteja totalmente inicializado antes da
	 * execução de cada um dos testes unitários.
	 */
	@Before
	public void setup() {
		LOG.info("Inicializando contexto do teste");
	}

	/**
	 * Realiza a configuração final desalocando os recursos de acesso a dados
	 * utilizados na realização dos testes unitários. Realiza o roolback das
	 * operações realizadas nos testes unitários para manter o contexto do
	 * script de carga.
	 */
	@After
	public void shutdown() {
		LOG.info("Finalizando contexto do teste");
	}

	/**
	 * Recupera o caminho de acesso ao arquivo de carga insert no banco de
	 * dados.
	 * 
	 * @return uam {@link String} inficando o caminho do arquivo de script de
	 *         carga insert.
	 */
	public String getScriptCargaInsert() {
		return "scriptCarga.xml";
	}

}
