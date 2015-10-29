/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.TimeZone;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.TimeType;
import org.joda.time.LocalTime;

/**
 * A Classe LocalTimeUserType permite a persistência pelo Hibernate de campos do tipo {@link LocalTime} do joda,
 * resolvendo o problema na diferença de horas no timezone.
 * 
 * @author helaine.lins
 * @created 15/04/2014 - 18:30:25
 */
public class LocalTimeUserType extends TipoAbstratoPersistencia {

    /**
     * Representa a instância do calendar utilizado para manipular as datas.
     */
    private static Calendar sUTCCalendar = Calendar.getInstance();

    /**
     * Cria uma nova instância da classe.
     */
    public LocalTimeUserType() {
        super();
        sUTCCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /**
     * {@inheritDoc}.
     *
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.TipoAbstratoPersistencia#equals(java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean equals(final Object data1, final Object data2) throws HibernateException {
        boolean iguais = false;

        if (data1 == null || data2 == null) {
            iguais = false;
        } else {
            final LocalTime dty = (LocalTime) data2;
            final LocalTime dtx = (LocalTime) data1;

            iguais = dtx.equals(dty);
        }

        return iguais;
    }
    
    /**
     * {@inheritDoc}.
     *
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.TipoAbstratoPersistencia#nullSafeGet(java.sql.ResultSet, java.lang.String[], org.hibernate.engine.spi.SessionImplementor, java.lang.Object)
     */
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
            throws HibernateException, SQLException {

        Object timestamp = TimeType.INSTANCE.nullSafeGet(rs, names[0], session);
        
        if (timestamp != null) {
            timestamp = new LocalTime(timestamp);
        }
        
        return timestamp;
    }

    /**
     * {@inheritDoc}.
     *
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.TipoAbstratoPersistencia#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int, org.hibernate.engine.spi.SessionImplementor)
     */
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
            throws HibernateException, SQLException {
        
        if (value == null) {
            TimeType.INSTANCE.nullSafeSet(st, null, index, session);
        } else {
            final Timestamp timestamp = new Timestamp(((LocalTime) value).toDateTimeToday().getMillis());
            st.setTimestamp(index, timestamp, sUTCCalendar);
        }
        
    }
    
    /**
     * {@inheritDoc}.
     *
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.TipoAbstratoPersistencia#returnedClass()
     */
    @Override
    public Class<LocalTime> returnedClass() {
        return LocalTime.class;
    }

    /**
     * {@inheritDoc}.
     *
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.TipoAbstratoPersistencia#sqlTypes()
     */
    @Override
    public int[] sqlTypes() {
        return new int[] {Types.TIME};
    }

    /**
     * {@inheritDoc}.
     *
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.TipoAbstratoPersistencia#deepCopy(java.lang.Object)
     */
    @Override
    public Object deepCopy(final Object value) throws HibernateException {
        Object copy = null;

        if (value != null) {
            copy = new LocalTime(value);
        }

        return copy;
    }

    /**
     * {@inheritDoc}.
     *
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.TipoAbstratoPersistencia#disassemble(java.lang.Object)
     */
    @Override
    public Serializable disassemble(final Object value) throws HibernateException {
        return (Serializable) value;
    }

    /**
     * {@inheritDoc}.
     *
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.TipoAbstratoPersistencia#assemble(java.io.Serializable, java.lang.Object)
     */
    @Override
    public Object assemble(final Serializable cached, final Object value) throws HibernateException {
        return cached;
    }

    /**
     * {@inheritDoc}.
     *
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.TipoAbstratoPersistencia#replace(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Object replace(final Object original, final Object target, final Object owner)
            throws HibernateException {
        return original;
    }

}
