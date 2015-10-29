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
import java.sql.Types;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

/**
 * A classe EnumeracaoUserType representa o tipo extendido do Hibernate para persistir constantes de Enumerações
 * {@link EnumeracaoUserType}. <br>
 * 
 * Toda Enumeração que persistir deve implementar o método <code>public char getTipo()</code>.
 * 
 * @author helaine.lins
 * @created 15/04/2014 - 18:30:25
 * 
 * @param <E> Tipo de enumeração que será persistido pela classe
 */
public class EnumeracaoUserType<E extends Enum<?>> implements UserType, ParameterizedType {

    /**
     * Representa o mecanismo de log da classe.
     */
    private static final Log LOG = LogFactory.getLog(EnumeracaoUserType.class);

    /**
     * Representa a classe da enumeração.
     */
    private Class<E> classe;

    /**
     * {@inheritDoc}.
     *
     * @see org.hibernate.usertype.UserType#equals(java.lang.Object, java.lang.Object)
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(final Object tipo1, final Object tipo2) {
        boolean iguais = false;

        if (tipo1 == null || tipo2 == null) {
            iguais = false;
        } else {
            final E dty = (E) tipo2;
            final E dtx = (E) tipo1;

            iguais = dtx.equals(dty);
        }

        return iguais;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[],
     *      org.hibernate.engine.spi.SessionImplementor, java.lang.Object)
     */
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
            throws HibernateException, SQLException {
        
        Object retorno = null;
        
        String codigo = StringType.INSTANCE.nullSafeGet(rs, names[0], session);
        
        Class<? extends Enum<?>> enumClass = returnedClass();
        Enum<?>[] enums = enumClass.getEnumConstants();
        
        for (int i = 0; i < enums.length; i++) {
            if (((IEnumeracaoPersistente) enums[i]).getCodigo().equals(codigo)) {
                LOG.debug("retornando enumeracao - nullSafeGet:" + enumClass.getName() + ".");
                retorno = enumClass.cast(enums[i]);
            }
        }
        
        return retorno;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int,
     *      org.hibernate.engine.spi.SessionImplementor)
     */
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
            throws HibernateException, SQLException {

        if (value == null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Binding null to parameter: " + index);
            }

            StringType.INSTANCE.nullSafeSet(st, null, index, session);
            
        } else {
            String codigoEnum = ((IEnumeracaoPersistente) value).getCodigo();

            if (LOG.isDebugEnabled()) {
                LOG.debug("Binding '" + codigoEnum + "' ao parâmetro: " + index);
            }

            st.setString(index, codigoEnum);
        }

    }

    /**
     * {@inheritDoc}.
     *
     * @see org.hibernate.usertype.UserType#returnedClass()
     */
    @Override
    public Class<E> returnedClass() {
        return this.classe;
    }

    /**
     * {@inheritDoc}.
     *
     * @see org.hibernate.usertype.UserType#sqlTypes()
     */
    @Override
    public int[] sqlTypes() {
        return new int[] {Types.VARCHAR};
    }

    /**
     * {@inheritDoc}.
     *
     * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
     */
    @Override
    public Object deepCopy(final Object value) throws HibernateException {
        return value;
    }

    /**
     * {@inheritDoc}.
     *
     * @see org.hibernate.usertype.UserType#disassemble(java.lang.Object)
     */
    @Override
    public Serializable disassemble(final Object value) throws HibernateException {
        return (Serializable) value;
    }

    /**
     * {@inheritDoc}.
     *
     * @see org.hibernate.usertype.UserType#assemble(java.io.Serializable, java.lang.Object)
     */
    @Override
    public Object assemble(final Serializable cached, final Object value) throws HibernateException {
        return cached;
    }

    /**
     * {@inheritDoc}.
     *
     * @see org.hibernate.usertype.UserType#replace(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Object replace(final Object original, final Object target, final Object owner)
            throws HibernateException {
        return original;
    }

    /**
     * {@inheritDoc}.
     *
     * @see org.hibernate.usertype.UserType#isMutable()
     */
    @Override
    public boolean isMutable() {
        return false;
    }

    /**
     * {@inheritDoc}.
     *
     * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
     */
    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    /**
     * Obtém os parâmetros passados na anotação de configuração do mapeamento. Todas as enumerações devem informar
     * o parâmetro "classeEnum" que indicará o caminho para instânciar a enumeração que define os valores a serem
     * persistidos pelo hibernate.
     * 
     * @param parameters - os parâmetros informados na anotação do atributo.
     *
     * @see org.hibernate.usertype.ParameterizedType#setParameterValues(java.util.Properties)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void setParameterValues(Properties parameters) {
        String enumClassName = parameters.getProperty("classeEnum");

        if (enumClassName == null) {
            throw new MappingException("O parâmetro classeEnum não foi especificado.");
        }

        try {
            this.classe = (Class<E>) Class.forName(enumClassName);
        } catch (java.lang.ClassNotFoundException e) {
            throw new MappingException("A enumeração " + enumClassName + " não foi encontrada.", e);
        }
    }

}
