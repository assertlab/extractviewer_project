/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.web.revisor;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.bean.AnaliseEstudoBean;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.AnaliseEstudoServico;
import br.ufpe.cin.cloud.mapeamento.negocio.extracaodados.servico.IAnaliseEstudoServico;

/**
 * Representa a implementação do paginador sob demanda das listagens de
 * AnaliseEstudos incompletos.
 * 
 * @author helaine.lins
 * @created 01/09/2014 - 19:22:55
 */
public class AnaliseEstudoIncompletoLazyList extends
		LazyDataModel<AnaliseEstudoBean> {

	/**
	 * Representa o serial version da classe.
	 */
	private static final long serialVersionUID = 463928728685634645L;

	/**
	 * Representa a instância da camada de servico {@link AnaliseEstudoServico}.
	 */
	private IAnaliseEstudoServico analiseEstudoServico;

	private Long idEtapaRevisao;
	private List<AnaliseEstudoBean> estudos;
	private Long idUsuarioLogado;

	/**
	 * 
	 */
	public AnaliseEstudoIncompletoLazyList(
			IAnaliseEstudoServico analiseEstudoServico, Long idEtapaRevisao, Long idUsuarioLogado) {
		this.idEtapaRevisao = idEtapaRevisao;
		this.analiseEstudoServico = analiseEstudoServico;
		this.idUsuarioLogado = idUsuarioLogado;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.util.List,
	 *      java.util.Map)
	 */
	@Override
	public List<AnaliseEstudoBean> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {

		this.estudos = this.analiseEstudoServico.listarNaoConcluidas(
				this.idEtapaRevisao, this.idUsuarioLogado,first, pageSize);

		this.setRowCount(this.analiseEstudoServico
				.qtdTotalNaoConcluidas(this.idEtapaRevisao, this.idUsuarioLogado));

		this.setPageSize(pageSize);

		return estudos;

	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 *      org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<AnaliseEstudoBean> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, String> filters) {

		this.estudos = this.analiseEstudoServico.listarNaoConcluidas(
				this.idEtapaRevisao, this.idUsuarioLogado, first, pageSize);

		this.setRowCount(this.analiseEstudoServico
				.qtdTotalNaoConcluidas(this.idEtapaRevisao, this.idUsuarioLogado));

		this.setPageSize(pageSize);

		return estudos;

	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
	 */
	@Override
	public Object getRowKey(AnaliseEstudoBean analise) {
		Long id = null;

		if (analise != null) {
			id = analise.getId();
		}

		return id;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.primefaces.model.LazyDataModel#getRowData()
	 */
	@Override
	public AnaliseEstudoBean getRowData(String analiseId) {
		AnaliseEstudoBean bean = null;

		if (StringUtils.isNotEmpty(analiseId) && this.estudos != null
				&& !this.estudos.isEmpty()) {
			for (AnaliseEstudoBean elemBean : this.estudos) {
				if (Long.valueOf(analiseId).equals(elemBean.getId())) {
					bean = elemBean;
					break;
				}
			}
		}

		return bean;
	}

}
