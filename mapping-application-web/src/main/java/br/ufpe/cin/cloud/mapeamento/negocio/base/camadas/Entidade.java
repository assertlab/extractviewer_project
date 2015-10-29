/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.camadas;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.base.validacao.ValidadorEntidade;

/**
 * A classe representa todas as entidades persistentes do sistema.
 * 
 * @author helaine.lins
 * @version 1.0
 * @created 18:28:58
 */
@MappedSuperclass
public abstract class Entidade implements Serializable {

    /**
     * Hibernate type do Joda Time usado para persistir LocalDates.
     */
    public static final String USER_TYPE_JODA_LOCAL_DATE =
            "br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.LocalDateUserType";

    /**
     * Hibernate type do Joda Time usado para persistir LocalTimes.
     */
    public static final String USER_TYPE_JODA_LOCAL_TIME =
            "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime";

    /**
     * Hibernate type do Joda Time usado para persistir LocaldATETime.
     */
    public static final String USER_TYPE_JODA_LOCAL_DATE_TIME =
            "org.jadira.usertype.dateandtime.joda.PersistentLocalDate";

    /**
     * Representa o caminho da classe que implementa o UserType para persistência de enumerações no Hibernate.
     */
    public static final String USER_TYPE_ENUMERACAO = "br.ufpe.cin.cloud.mapeamento.negocio.base.hibernate.EnumeracaoUserType";

    /**
     * Representa o parâmetro de mapeamento de enumerações persistidas pelo Hibernate.
     */
    public static final String USER_TYPE_ENUMERACAO_PARAMETRO_CLASS = "classeEnum";

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 5927173238699971598L;

    /**
     * Guarda a data da inclusão da entidade.
     */
    @Column(name = "data_inclusao", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dataInclusao;

    /**
     * Guarda a data da última alteração da entidade.
     */
    @Column(name = "data_ultima_alteracao", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dataUltimaAlteracao;

    /**
     * Recupera o identificador da entidade.
     */
    public abstract Long getEntidadeID();

    /**
     * Realiza a conversão de ume entidade em seu bean correspondente.
     * 
     * @return Uma instância de {@link BaseBean} contendo os dados copiados, caso contrário retorna
     *         <code>null</code>.
     */
    public abstract <E extends BaseBean> E getBean();
    
    /**
     * Obtém o valor do atributo dataUltimaAlteracao.
     * 
     * @return Uma instância de {@link LocalDate} contendo o valor do atributo dataUltimaAlteracao.
     */
    public LocalDateTime getDataUltimaAlteracao() {
        return this.dataUltimaAlteracao;
    }

    /**
     * Atualiza a instância de dataUltimaAlteracao com o valor de dataUltimaAlteracao.
     * 
     * @param dataUltimaAlteracao Uma instância de LocalDate contendo o valor a ser atualizado.
     */
    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    /**
     * Obtém o valor do atributo dataInclusao.
     * 
     * @return Uma instância de {@link LocalDateTime} contendo o valor do atributo dataInclusao.
     */
    public LocalDateTime getDataInclusao() {
        return this.dataInclusao;
    }

    /**
     * Atualiza a instância de dataInclusao com o valor de dataInclusao.
     * 
     * @param dataInclusao Uma instância de LocalDateTime contendo o valor a ser atualizado.
     */
    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    /**
     * Aplica a validação do Hibernate Validator para identificar se alguma regra configurada foi violada.
     * 
     * @param propIgnorar Representam as propriedades que devem ter a validação ignorada.
     * @return Uma {@link List} de {@link Erro} contendo as violações encontradas.
     */
    public List<Erro> validar(String... propIgnorar) {
        @SuppressWarnings("unchecked")
        ValidadorEntidade<Entidade> validator = new ValidadorEntidade<Entidade>((Class<Entidade>) this.getClass());
        return validator.validate(this, propIgnorar);
    }

    /**
     * Lista o nome da classe e os valores de seus atributos.
     * 
     * Exemplo: br.upe.entidade.Usuario[nome:fulano sobrenome:de tal]
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "[" + MapeamentoUtil.listarValoresPropriedades(this) + "]";
    }

}
