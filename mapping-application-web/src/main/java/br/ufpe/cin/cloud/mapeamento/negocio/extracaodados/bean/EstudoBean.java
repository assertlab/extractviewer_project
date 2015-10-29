/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.jbibtex.Key;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.entidade.Estudo;

/**
 * Representa os dados do bean de um {@link EstudoBean} no sistema.
 * 
 * @author helaine.lins
 * @created 24/07/2014 - 17:39:59
 */
public class EstudoBean extends BaseBean implements Comparable<EstudoBean> {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = -6682882217367661509L;

	/**
	 * Representa a propriedade file no arquivo .bib
	 */
	public static final Key TYPE_FILE = new Key("file");

	/**
	 * Representa a propriedade abstract no arquivo .bib
	 */
	public static final Key TYPE_ABSTRACT = new Key("abstract");

	/**
	 * Representa a propriedade url no arquivo .bib
	 */
	public static final Key TYPE_URL = new Key("url");

	private Long id;
	private String codigo;
	private String titulo;
	private Integer ano;
	private String autores;
	private String resumo;
	private String palavrasChave;
	private String arquivo;
	private String tituloPublicacao;
	private boolean urlNaoTratada;
	private BuscaBean busca;
	private List<EstudoBean> duplicados;

	/**
	 * Cria uma nova instância da classe inicializando as dependências.
	 */
	public EstudoBean() {
		this.busca = new BuscaBean();
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
	 * Obtém o valor do atributo busca.
	 * 
	 * @return Uma instância de {@link BuscaBean} contendo o valor do atributo
	 *         busca.
	 */
	public BuscaBean getBusca() {
		return this.busca;
	}

	/**
	 * Atualiza a instância de busca com o valor de busca.
	 * 
	 * @param busca
	 *            Uma instância de BuscaBean contendo o valor a ser atualizado.
	 */
	public void setBusca(BuscaBean busca) {
		this.busca = busca;
	}

	/**
	 * Obtém o valor do atributo duplicados.
	 * 
	 * @return Uma instância de {@link List<EstudoBean>} contendo o valor do
	 *         atributo duplicados.
	 */
	public List<EstudoBean> getDuplicados() {
		return this.duplicados;
	}

	/**
	 * Atualiza a instância de duplicados com o valor de duplicados.
	 * 
	 * @param duplicados
	 *            Uma instância de List<EstudoBean> contendo o valor a ser
	 *            atualizado.
	 */
	public void setDuplicados(List<EstudoBean> duplicados) {
		this.duplicados = duplicados;
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
	 * @return Uma instância de {@link String} contendo o valor do atributo tituloPublicacao.
	 */
	public String getTituloPublicacao() {
		return this.tituloPublicacao;
	}

	/**
	 * Atualiza a instância de tituloPublicacao com o valor de tituloPublicacao.
	 *
	 * @param tituloPublicacao Uma instância de String contendo o valor a ser atualizado.
	 */
	public void setTituloPublicacao(String tituloPublicacao) {
		this.tituloPublicacao = tituloPublicacao;
	}

	/**
	 * Obtém o valor do atributo arquivo.
	 * 
	 * @return Uma instância de {@link String} contendo o valor do atributo
	 *         arquivo.
	 */
	public String getArquivoReduzido() {
		String arquivo = null;
		
		if (!MapeamentoUtil.isEmpty(this.arquivo) && this.arquivo.length() > 100) {
			arquivo = this.arquivo.substring(0, 100) + "...";
		} else {
			arquivo = this.arquivo;
		}
		
		return arquivo;
	}
	
	/**
	 * {@inheritDoc}.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "titulo:" + this.titulo + " ano:" + this.ano + " autores:"
				+ this.autores + " arquivo:" + this.arquivo + " palavras-chave:" + this.palavrasChave;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.BaseBean#getEntidade()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Estudo getEntidade() {
		Estudo entidade = new Estudo();

		entidade.setId(this.getId());
		entidade.setDataInclusao(this.getDataInclusao());
		entidade.setDataUltimaAlteracao(this.getDataUltimaAlteracao());

		entidade.setAno(this.getAno());
		entidade.setArquivo(this.getArquivo());
		entidade.setAutores(this.getAutores());
		entidade.setCodigo(this.getCodigo());
		entidade.setResumo(this.getResumo());
		entidade.setTitulo(this.getTitulo());
		entidade.setTituloPublicacao(this.getTituloPublicacao());
		entidade.setUrlNaoTratada(this.isUrlNaoTratada());
		entidade.setPalavrasChave(this.getPalavrasChave());

		return entidade;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(EstudoBean estudo2) {
		int retorno = 0;

		if (this != null && estudo2 == null) {
			retorno = -1;
		} else if (this == null && estudo2 != null) {
			retorno = 1;
		} else if (this != null && estudo2 != null) {
			if (StringUtils.isNotBlank(this.getCodigo())
					&& StringUtils.isBlank(estudo2.getCodigo())) {
				retorno = -1;
			} else if (StringUtils.isBlank(this.getCodigo())
					&& StringUtils.isNotBlank(estudo2.getCodigo())) {
				retorno = 1;
			}

			// recupera a posição em que o caracter _ se encontra
			int indexOfthis = this.getCodigo().indexOf("_");
			int indexOfEstudo2 = estudo2.getCodigo().indexOf("_");

			// parte inteira do codigo
			String numeroThis = this.getCodigo().substring(indexOfthis + 1,
					this.getCodigo().length());

			String numeroEstudo2 = estudo2.getCodigo().substring(
					indexOfEstudo2 + 1, estudo2.getCodigo().length());

			// parte textual do codigo
			String codigoThis = this.getCodigo().substring(0, indexOfthis);
			String codigoEstudo2 = estudo2.getCodigo().substring(0,
					indexOfEstudo2);

			// compara a parte textual
			retorno = codigoThis.compareToIgnoreCase(codigoEstudo2);

			// se a parte string for igual compara a parte numerica
			if (retorno == 0) {
				retorno = Integer.valueOf(numeroThis).compareTo(
						Integer.valueOf(numeroEstudo2));
			}
		}

		return retorno;
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
		} else if (obj instanceof EstudoBean) {
			EstudoBean objeto = (EstudoBean) obj;
			iguais = new EqualsBuilder().append(titulo, objeto.titulo)
					.isEquals();
		}

		return iguais;
	}
}
