package pesquisador;

import java.io.Serializable;

import validadores.ValidadorEntradas;

/**
 * Especialidade que o Pesquisador pode assumir.
 * Possui formacao, unidade e data de contratação.
 *
 */
public class Professor implements Especialidade, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4132657058200189351L;

	/**
	 * Formação do professor.
	 */
	private String formacao;
	
	/**
	 * Unidade academica a qual o professor está vinculado
	 */
	private String unidade;
	
	/**
	 * Data da contratação do professor
	 */
	private String dataContrato;
	
	/**
	 * COnstroi o objeto do tipo Professor inicializando seus atributos
	 * 
	 * @param formacao a formação do professor
	 * @param unidade a unidade a qual o professor está vinculado
	 * @param dataContrato a data de contratação do professor
	 */
	public Professor(String formacao, String unidade, String dataContrato) {
		this.formacao = formacao;
		this.unidade = unidade;
		this.dataContrato = dataContrato;
	}

	/**
	 * Acessa a formação do professor
	 * @return a formação do professor
	 */
	public String getFormacao() {
		return formacao;
	}

	/**
	 * Acessa a unidade academica do professor
	 * @return a unidade academica do professor
	 */
	public String getUnidade() {
		return unidade;
	}

	/**
	 * Acessa a data de contratação do professor
	 * @return a data de contratação do professor
	 */
	public String getDataContrato() {
		return dataContrato;
	}

	/**
	 * Altera um atributo do professor
	 * 
	 * @param atributo o campo a ser alterado
	 * @param novoValor o novo valor do campo
	 */
	@Override
	public void alteraPesquisador(String atributo, String novoValor) {
		switch (atributo) {
		case "FORMACAO":
			ValidadorEntradas.validarString(novoValor, "Campo formacao nao pode ser nulo ou vazio.");
			this.formacao = novoValor;
			break;
		case "UNIDADE":
			ValidadorEntradas.validarString(novoValor, "Campo unidade nao pode ser nulo ou vazio.");
			this.unidade = novoValor;
			break;
		case "DATA":
			ValidadorEntradas.validarString(novoValor, "Campo data nao pode ser nulo ou vazio.");
			ValidadorEntradas.validaFormatoData(novoValor, "Formato de data invalido.");
			this.dataContrato = novoValor;
			break;
		default:
			throw new IllegalArgumentException("Atributo invalido.");
		}

	}

	@Override
	public String toString(String base) {
		return base + " - " + this.formacao + " - " + this.unidade + " - " + this.dataContrato;
	}

}
