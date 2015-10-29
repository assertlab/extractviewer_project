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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BaseDeDadosBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstudoBean;

/**
 * Representa o encapsulamento dos dados de uma busca no sistema.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 13:18:58
 */
/**
 * Representa FIXME: completar javadoc
 * 
 * @author helaine.lins
 * @created 27/04/2015 - 21:10:55
 */
@Entity
@Table(name = "busca", schema = "mapeamento")
@SequenceGenerator(name = "seq_busca", sequenceName = "mapeamento.seq_busca")
public class Busca extends Entidade {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = 5442009710837336485L;

    @Id
    @GeneratedValue(generator = "seq_busca")
    private Long id;

    @NotNull
    @Column(name = "string", columnDefinition = "TEXT", nullable = false)
    private String string;

    @NotNull
    @Column(name = "conferencia", columnDefinition = "TEXT", nullable = true)
    private String conferencia;

    @NotNull
    @Column(name = "inicio", nullable = false)
    private Integer inicio;

    @NotNull
    @Column(name = "fim", nullable = false)
    private Integer fim;

    @NotNull
    @Column(name = "prefixo", nullable = false)
    private String prefixo;

    @NotNull
    @Column(name = "data", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate data;

    @NotNull
    @Column(name = "manual", nullable = false)
    private boolean manual;

    @Column(name = "arquivo_bib", nullable = true)
    private String arquivoBib;

    @Column(name = "arquivo_excel", nullable = true)
    private String arquivoExcel;

    @NotNull
    @Column(name = "diretorio", nullable = true)
    private String diretorio;

    @ManyToOne
    @JoinColumn(name = "id_base", nullable = true)
    private BaseDeDados base;

    @Column(name = "qtd_estudos", nullable = true)
    private Integer qtdEstudos;

    @NotNull
    @Column(name = "comunidade", nullable = true)
    private String comunidade;

    @NotNull
    @Column(name = "tipo_conferencia", nullable = true)
    private String tipoConferencia;

    @OneToMany(mappedBy = "busca")
    private List<Estudo> estudos;

    @ManyToOne
    @JoinColumn(name = "id_estagio_execucao", nullable = false)
    private EstagioExecucao estagioExecucao;

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
     * Obtém o valor do atributo string.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo string.
     */
    public String getString() {
        return this.string;
    }

    /**
     * Atualiza a instância de string com o valor de string.
     * 
     * @param string Uma instância de String contendo o valor a ser atualizado.
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * Obtém o valor do atributo inicio.
     * 
     * @return Uma instância de {@link Integer} contendo o valor do atributo inicio.
     */
    public Integer getInicio() {
        return this.inicio;
    }

    /**
     * Atualiza a instância de inicio com o valor de inicio.
     * 
     * @param inicio Uma instância de Integer contendo o valor a ser atualizado.
     */
    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    /**
     * Obtém o valor do atributo fim.
     * 
     * @return Uma instância de {@link Integer} contendo o valor do atributo fim.
     */
    public Integer getFim() {
        return this.fim;
    }

    /**
     * Atualiza a instância de fim com o valor de fim.
     * 
     * @param fim Uma instância de Integer contendo o valor a ser atualizado.
     */
    public void setFim(Integer fim) {
        this.fim = fim;
    }

    /**
     * Obtém o valor do atributo data.
     * 
     * @return Uma instância de {@link LocalDate} contendo o valor do atributo data.
     */
    public LocalDate getData() {
        return this.data;
    }

    /**
     * Atualiza a instância de data com o valor de data.
     * 
     * @param data Uma instância de LocalDate contendo o valor a ser atualizado.
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Obtém o valor do atributo prefixo.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo prefixo.
     */
    public String getPrefixo() {
        return this.prefixo;
    }

    /**
     * Atualiza a instância de prefixo com o valor de prefixo.
     * 
     * @param prefixo Uma instância de String contendo o valor a ser atualizado.
     */
    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    /**
     * Obtém o valor do atributo base.
     * 
     * @return Uma instância de {@link BaseDeDados} contendo o valor do atributo base.
     */
    public BaseDeDados getBase() {
        return this.base;
    }

    /**
     * Atualiza a instância de base com o valor de base.
     * 
     * @param base Uma instância de BaseDeDados contendo o valor a ser atualizado.
     */
    public void setBase(BaseDeDados base) {
        this.base = base;
    }

    /**
     * Obtém o valor do atributo manual.
     * 
     * @return Uma instância de {@link boolean} contendo o valor do atributo manual.
     */
    public boolean isManual() {
        return this.manual;
    }

    /**
     * Atualiza a instância de manual com o valor de manual.
     * 
     * @param manual Uma instância de boolean contendo o valor a ser atualizado.
     */
    public void setManual(boolean manual) {
        this.manual = manual;
    }

    /**
     * Obtém o valor do atributo arquivoBib.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo arquivoBib.
     */
    public String getArquivoBib() {
        return this.arquivoBib;
    }

    /**
     * Atualiza a instância de arquivoBib com o valor de arquivoBib.
     * 
     * @param arquivoBib Uma instância de String contendo o valor a ser atualizado.
     */
    public void setArquivoBib(String arquivoBib) {
        this.arquivoBib = arquivoBib;
    }

    /**
     * Obtém o valor do atributo arquivoExcel.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo arquivoExcel.
     */
    public String getArquivoExcel() {
        return this.arquivoExcel;
    }

    /**
     * Atualiza a instância de arquivoExcel com o valor de arquivoExcel.
     * 
     * @param arquivoExcel Uma instância de String contendo o valor a ser atualizado.
     */
    public void setArquivoExcel(String arquivoExcel) {
        this.arquivoExcel = arquivoExcel;
    }

    /**
     * Obtém o valor do atributo diretorio.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo diretorio.
     */
    public String getDiretorio() {
        return this.diretorio;
    }

    /**
     * Atualiza a instância de diretorio com o valor de diretorio.
     * 
     * @param diretorio Uma instância de String contendo o valor a ser atualizado.
     */
    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    /**
     * Obtém o valor do atributo conferencia.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo conferencia.
     */
    public String getConferencia() {
        return this.conferencia;
    }

    /**
     * Atualiza a instância de conferencia com o valor de conferencia.
     * 
     * @param conferencia Uma instância de String contendo o valor a ser atualizado.
     */
    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    /**
     * Obtém o valor do atributo qtdEstudos.
     * 
     * @return Uma instância de {@link Integer} contendo o valor do atributo qtdEstudos.
     */
    public Integer getQtdEstudos() {
        return this.qtdEstudos;
    }

    /**
     * Atualiza a instância de qtdEstudos com o valor de qtdEstudos.
     * 
     * @param qtdEstudos Uma instância de Integer contendo o valor a ser atualizado.
     */
    public void setQtdEstudos(Integer qtdEstudos) {
        this.qtdEstudos = qtdEstudos;
    }

    /**
     * Obtém o valor do atributo estudos.
     * 
     * @return Uma instância de {@link List<Estudo>} contendo o valor do atributo estudos.
     */
    public List<Estudo> getEstudos() {
        return this.estudos;
    }

    /**
     * Atualiza a instância de estudos com o valor de estudos.
     * 
     * @param estudos Uma instância de List<Estudo> contendo o valor a ser atualizado.
     */
    public void setEstudos(List<Estudo> estudos) {
        this.estudos = estudos;
    }

    /**
     * Obtém o valor do atributo comunidade.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo comunidade.
     */
    public String getComunidade() {
        return this.comunidade;
    }

    /**
     * Atualiza a instância de comunidade com o valor de comunidade.
     * 
     * @param comunidade Uma instância de String contendo o valor a ser atualizado.
     */
    public void setComunidade(String comunidade) {
        this.comunidade = comunidade;
    }

    /**
     * Obtém o valor do atributo tipoConferencia.
     * 
     * @return Uma instância de {@link String} contendo o valor do atributo tipoConferencia.
     */
    public String getTipoConferencia() {
        return this.tipoConferencia;
    }

    /**
     * Atualiza a instância de tipoConferencia com o valor de tipoConferencia.
     * 
     * @param tipoConferencia Uma instância de String contendo o valor a ser atualizado.
     */
    public void setTipoConferencia(String tipoConferencia) {
        this.tipoConferencia = tipoConferencia;
    }

    /**
     * Obtém o valor do atributo estagioExecucao.
     * 
     * @return Uma instância de {@link EstagioExecucao} contendo o valor do atributo estagioExecucao.
     */
    public EstagioExecucao getEstagioExecucao() {
        return this.estagioExecucao;
    }

    /**
     * Atualiza a instância de estagioExecucao com o valor de estagioExecucao.
     * 
     * @param estagioExecucao Uma instância de EstagioExecucao contendo o valor a ser atualizado.
     */
    public void setEstagioExecucao(EstagioExecucao estagioExecucao) {
        this.estagioExecucao = estagioExecucao;
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
    @Override
    @SuppressWarnings("unchecked")
    public BuscaBean getBean() {
        BuscaBean bean = new BuscaBean();

        bean.setId(id);
        bean.setDataInclusao(this.getDataInclusao());
        bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

        bean.setFim(this.getFim());
        bean.setInicio(this.getInicio());
        bean.setString(this.getString());
        bean.setManual(this.isManual());
        bean.setArquivoBib(this.getArquivoBib());
        bean.setArquivoExcel(this.getArquivoExcel());
        bean.setDiretorio(this.getDiretorio());
        bean.setPrefixo(this.getPrefixo());
        bean.setData(this.getData());
        bean.setConferencia(this.getConferencia());
        bean.setQtdEstudos(this.getQtdEstudos());
        bean.setComunidade(this.getComunidade());
        bean.setTipoConferencia(this.getTipoConferencia());

        if (this.base != null) {
            BaseDeDadosBean base = new BaseDeDadosBean();
            base.setId(this.base.getId());
            base.setNome(this.base.getNome());
            bean.setBase(base);
        }

        if (this.estudos != null) {
            List<EstudoBean> estudosLista = new ArrayList<EstudoBean>();

            for (Estudo estudo : estudos) {
                if (estudo != null) {
                    estudosLista.add(estudo.getBean());
                }
            }

            bean.setEstudos(estudosLista);
        }

//        if (this.estagioExecucao != null) {
//            bean.setEstagioExecucao(this.getEstagioExecucao().getBean());
//        }

        return bean;
    }

}
