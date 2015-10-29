/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.validator.constraints.Length;
import org.jfree.util.Log;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BaseDeDadosBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.BuscaBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.EstudoBean;

/**
 * Representa o encapsulamento dos dados de um Estudo no sistema.
 * 
 * @author helaine.lins
 * @created 18/08/2014 - 13:00:24
 */
@Entity
@Table(name = "estudo", schema = "mapeamento")
@SequenceGenerator(name = "seq_estudo", sequenceName = "mapeamento.seq_estudo")
public class Estudo extends Entidade {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 2348207796079296395L;

	@Id
	@GeneratedValue(generator = "seq_estudo")
	@Column(name = "id_estudo")
	private Long id;

	@NotNull
	@Length(max = 20)
	@Column(name = "codigo", length = 20, nullable = false)
	private String codigo;

	@NotNull
	@Length(max = 250)
	@Column(name = "titulo_limpo", length = 250, nullable = false)
	private String tituloLimpo;

	@NotNull
	@Length(max = 300)
	@Column(name = "titulo", length = 300, nullable = false)
	private String titulo;

	@Column(name = "ano", nullable = true)
	private Integer ano;

	@NotNull
	@Column(name = "autores", columnDefinition = "TEXT", nullable = true)
	private String autores;

	@Length(max = 600)
	@Column(name = "palavras_chave", length = 600, nullable = true)
	private String palavrasChave;

	@Length(max = 400)
	@Column(name = "titulo_publicacao", length = 400, nullable = true)
	private String tituloPublicacao;

	@Column(name = "resumo", columnDefinition = "TEXT", nullable = true)
	private String resumo;

	@Length(max = 900)
	@Column(name = "arquivo", length = 900, nullable = true)
	private String arquivo;

	@Column(name = "url_nao_tratada", nullable = true)
	private boolean urlNaoTratada;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_busca", nullable = true)
	private Busca busca;

	/**
	 * Cria uma nova instância da entidade.
	 */
	public Estudo() {
		super();
	}

	/**
	 * Cria uma nova instância inicializando o valor do identificador.
	 */
	public Estudo(Long id) {
		this();
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
	 * @param id
	 *            Uma instância de Long contendo o valor a ser atualizado.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtém o valor do atributo codigo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         codigo.
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Atualiza a instância de codigo com o valor de codigo.
	 * 
	 * @param codigo
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtém o valor do atributo titulo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         titulo.
	 */
	public String getTitulo() {
		return this.titulo;
	}

	/**
	 * Atualiza a instância de titulo com o valor de titulo.
	 * 
	 * @param titulo
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtém o valor do atributo ano.
	 * 
	 * @return Uma instância de {@link Integer} contendo o valor do atributo
	 *         ano.
	 */
	public Integer getAno() {
		return this.ano;
	}

	/**
	 * Atualiza a instância de ano com o valor de ano.
	 * 
	 * @param ano
	 *            Uma instância de Integer contendo o valor a ser atualizado.
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
	}

	/**
	 * Obtém o valor do atributo autores.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         autores.
	 */
	public String getAutores() {
		return this.autores;
	}

	/**
	 * Atualiza a instância de autores com o valor de autores.
	 * 
	 * @param autores
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setAutores(String autores) {
		this.autores = autores;
	}

	/**
	 * Obtém o valor do atributo resumo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         resumo.
	 */
	public String getResumo() {
		return this.resumo;
	}

	/**
	 * Atualiza a instância de resumo com o valor de resumo.
	 * 
	 * @param resumo
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	/**
	 * Obtém o valor do atributo arquivo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         arquivo.
	 */
	public String getArquivo() {
		return this.arquivo;
	}

	/**
	 * Atualiza a instância de arquivo com o valor de arquivo.
	 * 
	 * @param arquivo
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	/**
	 * Obtém o valor do atributo urlNaoTratada.
	 * 
	 * @return Uma instância de {@link boolean} contendo o valor do atributo
	 *         urlNaoTratada.
	 */
	public boolean isUrlNaoTratada() {
		return this.urlNaoTratada;
	}

	/**
	 * Atualiza a instância de urlNaoTratada com o valor de urlNaoTratada.
	 * 
	 * @param urlNaoTratada
	 *            Uma instância de boolean contendo o valor a ser atualizado.
	 */
	public void setUrlNaoTratada(boolean urlNaoTratada) {
		this.urlNaoTratada = urlNaoTratada;
	}

	/**
	 * Obtém o valor do atributo busca.
	 * 
	 * @return Uma instância de {@link Busca} contendo o valor do atributo
	 *         busca.
	 */
	public Busca getBusca() {
		return this.busca;
	}

	/**
	 * Atualiza a instância de busca com o valor de busca.
	 * 
	 * @param busca
	 *            Uma instância de Busca contendo o valor a ser atualizado.
	 */
	public void setBusca(Busca busca) {
		this.busca = busca;
	}

	/**
	 * Obtém o valor do atributo tituloLimpo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         tituloLimpo.
	 */
	public String getTituloLimpo() {
		return this.tituloLimpo;
	}

	/**
	 * Atualiza a instância de tituloLimpo com o valor de tituloLimpo.
	 * 
	 * @param tituloLimpo
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setTituloLimpo(String tituloLimpo) {
		this.tituloLimpo = tituloLimpo;
	}

	/**
	 * Obtém o valor do atributo serialversionuid.
	 * 
	 * @return Uma instância de {@link long} contendo o valor do atributo
	 *         serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Obtém o valor do atributo palavrasChave.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         palavrasChave.
	 */
	public String getPalavrasChave() {
		return this.palavrasChave;
	}

	/**
	 * Atualiza a instância de palavrasChave com o valor de palavrasChave.
	 * 
	 * @param palavrasChave
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setPalavrasChave(String palavrasChave) {
		this.palavrasChave = palavrasChave;
	}

	/**
	 * Obtém o valor do atributo tituloPublicacao.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         tituloPublicacao.
	 */
	public String getTituloPublicacao() {
		return this.tituloPublicacao;
	}

	/**
	 * Atualiza a instância de tituloPublicacao com o valor de tituloPublicacao.
	 * 
	 * @param tituloPublicacao
	 *            Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setTituloPublicacao(String tituloPublicacao) {
		this.tituloPublicacao = tituloPublicacao;
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
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean iguais = false;

		if (obj == this) {
			iguais = true;
		} else if (obj instanceof Estudo) {
			Estudo objeto = (Estudo) obj;

			if (this.getId() != null && objeto.getId() != null) {
				iguais = new EqualsBuilder().append(id, objeto.id).isEquals();
			} else {
				iguais = new EqualsBuilder().append(titulo, objeto.titulo)
						.isEquals();
			}
		}

		return iguais;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#getBean()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public EstudoBean getBean() {
		EstudoBean bean = new EstudoBean();

		bean.setId(this.getId());
		bean.setDataInclusao(this.getDataInclusao());
		bean.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

		bean.setAno(this.getAno());
		bean.setArquivo(this.getArquivo());
		bean.setAutores(this.getAutores());
		bean.setCodigo(this.getCodigo());
		bean.setResumo(this.getResumo());
		bean.setTitulo(this.getTitulo());
		bean.setUrlNaoTratada(this.isUrlNaoTratada());
		bean.setPalavrasChave(this.getPalavrasChave());

		if (this.getBusca() != null) {
			BuscaBean busca = new BuscaBean();

			busca.setId(this.getBusca().getId());
			busca.setManual(this.getBusca().isManual());
			busca.setConferencia(this.getBusca().getConferencia());
			busca.setInicio(this.getBusca().getInicio());
			busca.setFim(this.getBusca().getFim());

			if (this.getBusca().getBase() != null) {
				BaseDeDadosBean base = new BaseDeDadosBean();

				base.setNome(this.getBusca().getBase().getNome());
				base.setId(this.getBusca().getBase().getId());

				busca.setBase(base);
			}

			bean.setBusca(busca);
		}

		return bean;
	}

	/**
	 * Recupera as diferenças de valores entre duas entidades.
	 * 
	 * @param inscricao
	 *            A instância que contém os novos valores
	 * @param propIgnorar
	 *            As propriedades a serem ignoradas.
	 * 
	 * @return Uma {@link String} contendo as alterações realizadas.
	 */
	public String getDiferencas(Estudo novoEstudo, String... propIgnorar) {

		StringBuilder diferencas = new StringBuilder();

		try {
			if (novoEstudo != null) {

				if (novoEstudo.getTitulo() == null && this.titulo != null) {
					diferencas.append(" Titulo:null");
				} else if (novoEstudo.getTitulo() != null
						&& !novoEstudo.getTitulo()
								.equalsIgnoreCase(this.titulo)) {
					diferencas.append(" Titulo:");
					diferencas.append(novoEstudo.getTitulo());
				}

				if (novoEstudo.getTituloLimpo() == null
						&& this.tituloLimpo != null) {
					diferencas.append(" TituloLimpo:null");
				} else if (novoEstudo.getTituloLimpo() != null
						&& !novoEstudo.getTituloLimpo().equalsIgnoreCase(
								this.tituloLimpo)) {
					diferencas.append(" TituloLimpo:");
					diferencas.append(novoEstudo.getTituloLimpo());
				}

				if (novoEstudo.getAutores() == null && this.autores != null) {
					diferencas.append(" Autores:null");
				} else if (novoEstudo.getAutores() != null
						&& !novoEstudo.getAutores().equalsIgnoreCase(
								this.autores)) {
					diferencas.append(" Autores:");
					diferencas.append(novoEstudo.getAutores());
				}

				if (novoEstudo.getAno() == null && this.ano != null) {
					diferencas.append(" Ano:null");
				} else if (novoEstudo.getAno() != null
						&& !novoEstudo.getAno().equals(this.ano)) {
					diferencas.append(" Ano:");
					diferencas.append(novoEstudo.getAno());
				}

				if (novoEstudo.getResumo() == null && this.resumo != null) {
					diferencas.append(" Resumo:null");
				} else if (novoEstudo.getResumo() != null
						&& !novoEstudo.getResumo()
								.equalsIgnoreCase(this.resumo)) {
					diferencas.append(" Resumo:");
					diferencas.append(novoEstudo.getResumo());
				}

				if (novoEstudo.getArquivo() == null && this.arquivo != null) {
					diferencas.append(" Arquivo:null");
				} else if (novoEstudo.getArquivo() != null
						&& !novoEstudo.getArquivo().equalsIgnoreCase(
								this.arquivo)) {
					diferencas.append(" Arquivo:");
					diferencas.append(novoEstudo.getArquivo());
				}

				if (novoEstudo.getPalavrasChave() == null
						&& this.palavrasChave != null) {
					diferencas.append(" PalavrasChave:null");
				} else if (novoEstudo.getPalavrasChave() != null
						&& !novoEstudo.getPalavrasChave().equalsIgnoreCase(
								this.palavrasChave)) {
					diferencas.append(" PalavrasChave:");
					diferencas.append(novoEstudo.getPalavrasChave());
				}

			}
		} catch (Exception e) {
			Log.error(
					"Ocorreu um erro ao gerar a diferença das alterações do estudo",
					e);
		}

		return diferencas.toString();
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade#toString()
	 */
	@Override
	public String toString() {
		return "Estudo:" + this.id + "-" + this.codigo + "-" + this.titulo
				+ "-" + this.ano + "-" + this.autores + "-"
				+ this.palavrasChave;
	}
}
