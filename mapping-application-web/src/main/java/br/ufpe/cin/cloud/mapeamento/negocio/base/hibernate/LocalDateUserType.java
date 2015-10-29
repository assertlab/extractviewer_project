/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.DateType;
import org.jadira.usertype.dateandtime.joda.PersistentLocalDate;
import org.joda.time.LocalDate;

/**
 * A classe LocalDateUserType é a implementação utilizada para persistir tipos {@link org.joda.time.LocalDate}.
 * Estende {@link PersistentLocalDate} para corrigir BUG no uso de {@link org.joda.time.LocalDate#toDateMidnight()}
 * .
 * 
 * @author helaine.lins
 * @created 15/04/2014 - 18:30:25
 */
public class LocalDateUserType extends PersistentLocalDate {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 6780635839985262411L;

    /**
     * implementação de {@link org.hibernate.usertype.UserType#nullSafeSet(PreparedStatement, Object, int)}.
     * 
     * Este método foi sobrescrito para corrigir um BUG no original que usa
     * {@link org.joda.time.LocalDate#toDateMidnight()} ao invés de
     * {@link org.joda.time.LocalDate#toDateTimeAtStartOfDay()}.
     * 
     * @param preparedStatement uma prepared statement
     * @param value o objeto a ser escrito
     * @param index o índice do parâmetro da statement
     * 
     * @throws HibernateException exceção do hibernate.
     * @throws SQLException exceção JDBC.
     * 
     * @see org.jadira.usertype.dateandtime.shared.spi.AbstractSingleColumnUserType#nullSafeSet(java.sql.PreparedStatement,
     *      java.lang.Object, int, org.hibernate.engine.spi.SessionImplementor)
     */
    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index,
            SessionImplementor session) throws SQLException {
        if (value == null) {
            DateType.INSTANCE.nullSafeSet(preparedStatement, null, index, session);
        } else {
            DateType.INSTANCE.nullSafeSet(preparedStatement,
                    ((LocalDate) value).toDateTimeAtStartOfDay().toDate(), index, session);
        }
    }

}
