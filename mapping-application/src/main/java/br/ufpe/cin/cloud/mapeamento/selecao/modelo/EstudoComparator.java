/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.selecao.modelo;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

/**
 * Representa a implementação de um comparator para ordenação dos {@link Estudo}
 * de acordo com o seu código.
 * 
 * @author helaine.lins
 * @created 04/08/2014 - 09:13:06
 */
public class EstudoComparator implements Comparator<Estudo> {

	/**
	 * {@inheritDoc}.
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Estudo estudo1, Estudo estudo2) {

		int retorno = 0;

		if (estudo1 != null && estudo2 == null) {
			retorno = -1;
		} else if (estudo1 == null && estudo2 != null) {
			retorno = 1;
		} else if (estudo1 != null && estudo2 != null) {
			if (StringUtils.isNotBlank(estudo1.getCodigo())
					&& StringUtils.isBlank(estudo2.getCodigo())) {
				retorno = -1;
			} else if (StringUtils.isBlank(estudo1.getCodigo())
					&& StringUtils.isNotBlank(estudo2.getCodigo())) {
				retorno = 1;
			}

			// recupera a posição em que o caracter _ se encontra
			int indexOfEstudo1 = estudo1.getCodigo().indexOf("_");
			int indexOfEstudo2 = estudo2.getCodigo().indexOf("_");

			// parte inteira do codigo
			String numeroEstudo1 = estudo1.getCodigo().substring(
					indexOfEstudo1 + 1, estudo1.getCodigo().length());
			
			String numeroEstudo2 = estudo2.getCodigo().substring(
					indexOfEstudo2 + 1, estudo2.getCodigo().length());
			
			//parte textual do codigo
			String codigoEstudo1 = estudo1.getCodigo().substring(0,
					indexOfEstudo1);
			String codigoEstudo2 = estudo2.getCodigo().substring(0,
					indexOfEstudo2);

			//compara a parte textual
			retorno = codigoEstudo1.compareToIgnoreCase(codigoEstudo2);
			
			//se a parte string for igual compara a parte numerica
			if (retorno == 0) {
				retorno = Integer.valueOf(numeroEstudo1).compareTo(Integer.valueOf(numeroEstudo2));
			}
		}

		return retorno;
	}

}
