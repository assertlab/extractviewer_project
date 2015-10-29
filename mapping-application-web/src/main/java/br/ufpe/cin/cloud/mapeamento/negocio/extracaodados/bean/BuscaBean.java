/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.joda.time.LocalDate;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Busca;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;

/**
 * Representa os dados de uma busca no sistema.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 13:14:12
 */
public class BuscaBean extends BaseBean implements Comparable<BuscaBean> {

    /**
     * Representa o serial version da classe.
     */
    private static final long serialVersionUID = -5600348329931134964L;

    private Long id;
    private String string;
    private String conferencia;
    private Integer inicio;
    private Integer fim;
    private String prefixo;
    private LocalDate data;
    private boolean manual;
    private String arquivoBib;
    private String arquivoExcel;
    private String diretorio;
    private BaseDeDadosBean base;
    private Integer qtdEstudos;
    private String comunidade;
    private String tipoConferencia;
    private List<EstudoBean> estudos;
    private EstagioExecucaoBean estagioExecucao;

    /**
     * Cria uma nova instância da classe.
     */
    public BuscaBean() {
        this.base = new BaseDeDadosBean();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getBeanID()
     */
    @Override
    public Long getBeanID() {
        return this.id;
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
     * Obtém o valor do atributo base.
     * 
     * @return Uma instância de {@link BaseDeDadosBean} contendo o valor do atributo base.
     */
    public BaseDeDadosBean getBase() {
        return this.base;
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
     * Atualiza a instância de base com o valor de base.
     * 
     * @param base Uma instância de BaseDeDadosBean contendo o valor a ser atualizado.
     */
    public void setBase(BaseDeDadosBean base) {
        this.base = base;
    }

    /**
     * Obtém o valor do atributo estudos.
     * 
     * @return Uma instância de {@link List<EstudoBean>} contendo o valor do atributo estudos.
     */
    public List<EstudoBean> getEstudos() {
        return this.estudos;
    }

    /**
     * Atualiza a instância de estudos com o valor de estudos.
     * 
     * @param estudos Uma instância de List<EstudoBean> contendo o valor a ser atualizado.
     */
    public void setEstudos(List<EstudoBean> estudos) {
        this.estudos = estudos;
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
     * @return Uma instância de {@link EstagioExecucaoBean} contendo o valor do atributo estagioExecucao.
     */
    public EstagioExecucaoBean getEstagioExecucao() {
        return this.estagioExecucao;
    }

    /**
     * Atualiza a instância de estagioExecucao com o valor de estagioExecucao.
     * 
     * @param estagioExecucao Uma instância de EstagioExecucaoBean contendo o valor a ser atualizado.
     */
    public void setEstagioExecucao(EstagioExecucaoBean estagioExecucao) {
        this.estagioExecucao = estagioExecucao;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean iguais = false;

        if (obj == this) {
            iguais = true;
        } else if (obj instanceof BuscaBean) {
            BuscaBean objeto = (BuscaBean) obj;
            iguais =
                    new EqualsBuilder().append(string, objeto.string).append(inicio, objeto.inicio)
                            .append(fim, objeto.fim).append(base, objeto.base)
                            .append(estagioExecucao, objeto.estagioExecucao).isEquals();
        }

        return iguais;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
     */
    @Override
    @SuppressWarnings("unchecked")
    public Busca getEntidade() {
        Busca entidade = new Busca();

        entidade.setId(id);
        entidade.setDataInclusao(this.getDataInclusao());
        entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

        entidade.setFim(this.getFim());
        entidade.setInicio(this.getInicio());
        entidade.setString(this.getString());
        entidade.setManual(this.isManual());
        entidade.setArquivoBib(this.getArquivoBib());
        entidade.setArquivoExcel(this.getArquivoExcel());
        entidade.setDiretorio(this.getDiretorio());
        entidade.setPrefixo(this.getPrefixo());
        entidade.setData(this.getData());
        entidade.setConferencia(this.getConferencia());
        entidade.setQtdEstudos(this.getQtdEstudos());
        entidade.setComunidade(this.getComunidade());
        entidade.setTipoConferencia(this.getTipoConferencia());

        if (this.base != null) {
            entidade.setBase(this.base.getEntidade());
        }

        if (this.estudos != null) {
            List<Estudo> estudosLista = new ArrayList<Estudo>();

            for (EstudoBean estudo : estudos) {
                if (estudo != null) {
                    estudosLista.add(estudo.getEntidade());
                }
            }

            entidade.setEstudos(estudosLista);
        }

        return entidade;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(BuscaBean busca) {
        int retorno = 0;

        if (this != null && busca == null) {
            retorno = -1;
        } else if (this == null && busca != null) {
            retorno = 1;
        } else if (this != null && busca != null) {
            if (this.getBase() != null && busca.getBase() == null) {
                retorno = -1;
            } else if (this.getBase() == null && busca.getBase() != null) {
                retorno = 1;
            } else if (this.getBase() != null && busca.getBase() != null) {

                if (StringUtils.isNotBlank(this.getBase().getNome()) && StringUtils.isBlank(busca.getBase().getNome())) {
                    retorno = -1;
                } else if (StringUtils.isBlank(this.getBase().getNome())
                        && StringUtils.isNotBlank(busca.getBase().getNome())) {
                    retorno = 1;
                }

                retorno = this.base.getNome().compareToIgnoreCase(busca.getBase().getNome());

                if (retorno == 0) {

                    if (StringUtils.isNotBlank(this.getString()) && StringUtils.isBlank(busca.getString())) {
                        retorno = -1;
                    } else if (StringUtils.isBlank(this.getString()) && StringUtils.isNotBlank(busca.getString())) {
                        retorno = 1;
                    }

                    retorno = this.getString().compareToIgnoreCase(busca.getString());

                    if (retorno == 0) {
                        if (this.getInicio() != null && busca.getInicio() == null) {
                            retorno = -1;
                        } else if (this.getInicio() == null && busca.getInicio() != null) {
                            retorno = 1;
                        }

                        retorno = this.getInicio().compareTo(busca.getInicio());

                        if (retorno == 0) {
                            if (this.getFim() != null && busca.getFim() == null) {
                                retorno = -1;
                            } else if (this.getFim() == null && busca.getFim() != null) {
                                retorno = 1;
                            }

                            retorno = this.getFim().compareTo(busca.getFim());
                        }

                    }
                }
            }
        }

        return retorno;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "base:[" + this.base.getNome() + "] inicio:[" + inicio + "] fim:[" + fim + "] string:[" + string + "]";
    }

}
