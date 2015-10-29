/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * Representa a implementação base dos controladores de tratamento de artigos do
 * mapeamento.
 * 
 * @author helaine.lins
 * @created 15/08/2014 - 18:42:02
 */
public class ControladorBase {

	/**
	 * Representa o mecanismo de log da classe.
	 */
	private static Logger LOG = Logger
			.getLogger(ControladorBase.class.getName());
	
	
	/**
	 * Abre a conexão com o banco de dados.
	 */
	private void abrirConexao() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			LOG.error("Não foi possívem encontrar o Driver PostgreSQL JDBC no classpath", e);
			throw new MapeamentoException("Ocorreu um erro ao carregar o driver PostgreSQL JDBC", e);
		}

		LOG.debug("Driver PostgreSQL JDBC carregado!");

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/testdb", "mkyong",
					"123456");
		} catch (SQLException e) {
			LOG.error("Falha ao obter a conexão com a base de dados do sistema.", e);
		}

		if (connection == null) {
			throw new MapeamentoException("Ocorreu um erro ao carregar o driver PostgreSQL JDBC");
		}
	}

}
