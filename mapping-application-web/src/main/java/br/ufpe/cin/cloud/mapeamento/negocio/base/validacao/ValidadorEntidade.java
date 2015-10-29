/**
 * CloudMapping - Sistema de Extração de Dados de Mapeamento dos Experimentos em Computação em Nuvem
 * 
 * Copyright (c) AssertLab.
 * 
 * Este software é confidencial e propriedade da AssertLab. Não é permitida sua distribuição ou divulgação 
 * do seu conteúdo sem expressa autorização do AssertLab. Este arquivo contém informações proprietárias.
 */
package br.ufpe.cin.cloud.mapeamento.negocio.base.validacao;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.beanutils.PropertyUtils;

import br.ufpe.cin.cloud.mapeamento.negocio.base.camadas.Entidade;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.Erro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.excecao.TipoErro;
import br.ufpe.cin.cloud.mapeamento.negocio.base.util.MapeamentoUtil;

/**
 * A classe representa a extensão da implementação do hibernate validator para o projeto.
 * 
 * @author helaine.lins
 * @version
 * @created 18:33:15
 */
public class ValidadorEntidade<E extends Entidade> {

    /**
     * Representa o mecanismo validador da entidade.
     */
    private Validator validador;

    /**
     * Lista que matém todas as violações de uma propriedade.
     */
    private List<Violacao> violacoes;

    /**
     * Inicializa o mecanismo de validação de entidades.
     */
    public ValidadorEntidade(Class<E> classe) {
        this.violacoes = new ArrayList<Violacao>();
        this.validador = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * Aplica todas as validações nas propriedades mapeadas de uma {@link Entidade} com exceção das informadas como
     * parâmetro.
     * 
     * @param entidade A instância a ser validada.
     * @param propriedadesIgnoradas A lista das propriedades que terão a <strong>validação ignorada</strong>
     * @return Um {@link List} de {@link Erro} contendo as validações encontradas.
     */
    public List<Erro> validate(E entidade, String... propriedadesIgnoradas) {
        List<Erro> erros = new ArrayList<Erro>();

        // recupera todas as propriedades declaradas na entidade para que sejam
        // validadas.
        PropertyDescriptor[] descritores = PropertyUtils.getPropertyDescriptors(entidade);

        // aplica as validações respeitando as que devem ser ignoradas.
        for (PropertyDescriptor descritor : descritores) {
            String propriedade = descritor.getName();

            if (!isPropriedadeEmLista(propriedade, propriedadesIgnoradas)) {
                erros.addAll(validarPropriedade(entidade, propriedade));
            }
        }

        return erros;
    }

    /**
     * Identifica se a propriedade informada na {@link String} encontra-se na lista de propriedades a serem
     * ignoradas.
     * 
     * @param propriedade A instância da string a ser procurada.
     * @param propIgnoradas A instância que contém a lista de strings onde a procura será realizada.
     * @return <code>true</code> se a "string" estiver contida na lista de "strings", caso contrário
     *         <code>false</code>.
     */
    protected boolean isPropriedadeEmLista(String propriedade, String... propIgnoradas) {
        boolean isContida = false;

        for (String stringAtual : propIgnoradas) {
            if (stringAtual.equals(propriedade)) {
                isContida = true;
            }
        }

        return isContida;
    }

    /**
     * Valida todos os atributos de uma {@link Entidade} e retorna uma lista de {@link FaultService} contendo as
     * violações encontradas.
     * 
     * @param entidade A instância que contém a propriedade a ser validada.
     * @param propriedade O nome da propriedade.
     * @return um {@link List} de violações {@link FaultService} contendo as mensagens a serem apresentadas ao
     *         usuário.
     */
    public List<Erro> validarPropriedade(E entidade, String propriedade) {
        Erro msgViolacao = null;

        this.violacoes.clear();

        // realiza a validação da propriedade. Uma propriedade pode retornar
        // mais de uma violação.
        Set<ConstraintViolation<E>> violacoesEncontradas = this.validador.validateProperty(entidade, propriedade);
        List<Erro> violacoes = new ArrayList<Erro>();

        String chaveProp = entidade.getClass().getSimpleName() + "." + propriedade;
        StringBuilder desErro = new StringBuilder();

        for (ConstraintViolation<E> valorInvalido : violacoesEncontradas) {
            desErro.append(this.recuperarNomeExibicaoPropriedade(entidade, propriedade, chaveProp));
            desErro.append(" ");
            desErro.append(valorInvalido.getMessage());

            msgViolacao = new Erro(chaveProp, desErro.toString(), TipoErro.HIBERNATE_VALIDATOR);
            violacoes.add(msgViolacao);
        }

        this.violacoes.clear();

        return violacoes;
    }

    /**
     * Recupera o nome da propriedade validada para ser exibida ao usuário durante a exibição da exceção.
     * 
     * @param entidade A entidade que está sendo validada.
     * @param propriedade A propriedade que está sendo validada.
     * @param chaveProp A chave da propriedade a ser procurar no arquivo de propriedades.
     * @return Uma {@link String} contendo o nome da propriedade a ser apresentada para o usuário.
     */
    private String recuperarNomeExibicaoPropriedade(E entidade, String propriedade, String chaveProp) {
        String nomeProp = "O campo " + propriedade;

        /*
         * Foi montada uma convenção para recperar o nome da propriedade(amigável para o usuário) que está sendo
         * validada. A conveção adotada é acrescentar no arquivo de proprieades ValidationMessages.properties a
         * chave seguindo a conveção: NomeDaEntity.propriedade.
         * 
         * Ex: Entidade.codEntidade=código da entidade
         * 
         * Caso a conveção não seja satisfeita será retornada a mensagem padrão: 'O campo + propriedade'.
         */
        return MapeamentoUtil.recuperarMensagemProperties(chaveProp, nomeProp);
    }

}
