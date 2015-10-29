/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstudoSecundarioBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.RevisorBean;

/**
 * Representa um estudo sistemático no sistema.
 * 
 * @author helaine.lins
 * @created 25/04/2015 - 17:01:12
 */
@Entity
@Table(name = "estudo_secundario", schema = "mapeamento")
@SequenceGenerator(name = "seq_estudo_secundario", sequenceName = "mapeamento.seq_estudo_secundario")
public class EstudoSecundario extends Entidade {

    private static final long serialVersionUID = 2381027298217696636L;

    @Id
    @GeneratedValue(generator = "seq_estudo_secundario")
    @Column(name = "id_estudo_secundario")
    private Long id;

    @NotEmpty
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotNull
    @Type(type = USER_TYPE_ENUMERACAO, parameters = {@Parameter(name = USER_TYPE_ENUMERACAO_PARAMETRO_CLASS, value = TipoEstudoEnum.USER_TYPE_ENUMERACAO_CLASS)})
    @Column(name = "tipo_estudo", nullable = false)
    private TipoEstudoEnum tipoEstudo;

    @NotNull
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Revisor.class)
    @JoinTable(name = "mapeamento.autor_estudo_secundario", joinColumns = @JoinColumn(name = "id_revisor"), inverseJoinColumns = @JoinColumn(name = "id_estudo_secundario"))
    private List<Revisor> autores;

    //TODO: implementar
    //private EstagioPlanejamento planejamento;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estagio_execucao")
    private EstagioExecucao execucao;

    //TODO: implementar
    //private EstagioReport relatorio;

    /**
     * Cria uma nova instância da classe.
     */
    public EstudoSecundario() {
        super();
    }

    /**
     * Cria uma nova instância da classe inicializando o identificador.
     * 
     * @param id O identificador da classe.
     */
    public EstudoSecundario(Long id) {
        super();
        this.id = id;
    }

    /**
     * Obtém o valor do atributo id.
     * 
     * @return Uma instância de {@link Long} contendo o valor do atributo id.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Atualiza a instância de id com o valor de id.
     * 
     * @param id Uma instância de Long contendo o valor a ser atualizado.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o valor do atributo titulo.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo titulo.
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Atualiza a instância de titulo com o valor de titulo.
     * 
     * @param titulo Uma instância de String contendo o valor a ser atualizado.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém o valor do atributo tipoEstudo.
     * 
     * @return Uma instância de {@link TipoEstudoEnum} contendo o valor do atributo tipoEstudo.
     */
    public TipoEstudoEnum getTipoEstudo() {
        return this.tipoEstudo;
    }

    /**
     * Atualiza a instância de tipoEstudo com o valor de tipoEstudo.
     * 
     * @param tipoEstudo Uma instância de TipoEstudoEnum contendo o valor a ser atualizado.
     */
    public void setTipoEstudo(TipoEstudoEnum tipoEstudo) {
        this.tipoEstudo = tipoEstudo;
    }

    /**
     * Obtém o valor do atributo autores.
     * 
     * @return Uma instância de {@link List<Revisor>} contendo o valor do atributo autores.
     */
    public List<Revisor> getAutores() {
        return this.autores;
    }

    /**
     * Atualiza a instância de autores com o valor de autores.
     * 
     * @param autores Uma instância de List<Revisor> contendo o valor a ser atualizado.
     */
    public void setAutores(List<Revisor> autores) {
        this.autores = autores;
    }

    /**
     * Obtém o valor do atributo execucao.
     * 
     * @return Uma instância de {@link EstagioExecucao} contendo o valor do atributo execucao.
     */
    public EstagioExecucao getExecucao() {
        return this.execucao;
    }

    /**
     * Atualiza a instância de execucao com o valor de execucao.
     * 
     * @param execucao Uma instância de EstagioExecucao contendo o valor a ser atualizado.
     */
    public void setExecucao(EstagioExecucao execucao) {
        this.execucao = execucao;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#getEntidadeID()
     */
    @Override
    public Long getEntidadeID() {
        return this.id;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#getBean()
     */
    @SuppressWarnings("unchecked")
    @Override
    public EstudoSecundarioBean getBean() {

        EstudoSecundarioBean bean = new EstudoSecundarioBean();

        bean.setId(this.id);
        bean.setTitulo(this.titulo);
        bean.setDataInclusao(this.getDataInclusao());
        bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());
        
        
        if (this.getTipoEstudo() != null) {
            bean.setTipoEstudo(this.getTipoEstudo().getBean());
        }

        if (this.getAutores() != null && !this.getAutores().isEmpty()) {
            List<RevisorBean> autores = new ArrayList<RevisorBean>();
            
            for (Revisor entAutor : this.getAutores()) {
                autores.add(entAutor.getBean());
            }
            
            bean.setAutores(autores);
        }

        if (this.execucao != null) {
            bean.setExecucao(this.execucao.getBean());
        }

        return bean;

    }

}
