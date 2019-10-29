package entidades;

import validadores.ValidadorEntradas;
/**
 * Classe que representa a pesquisa, contendo uma descricao, campo de interesse
 * um codigo unico e um status
 * @author Natalia salvino 
 *
 */
public class Pesquisa {
/**
 * Descricao da pesquisa
 */
	private String descricao;
/**
 * Campo de interesse da pesquisa
 */
	private String campoDeInteresse;
/**
 * O codigo que identifica a pesquisa
 */
	private String codigo;
/**
 * O status da pesquisa, que pode ser ativo ou inativo
 */
	private boolean status;
/**
 * Contrutor da pesquisa
 * @param codigo o codigo da pesquisa
 * @param descricao a descricao da pesquisa
 * @param campoDeInteresse
 */
	public Pesquisa(String codigo,String descricao, String campoDeInteresse){
		ValidadorEntradas.validarString(descricao,"Descricao nao pode ser nula ou vazia.");
		ValidadorEntradas.validarString(campoDeInteresse,"Formato do campo de interesse invalido.");
		
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.codigo = codigo;
		this.status = true;
		
	}

	public void setDescricao(String descricao) {
		ValidadorEntradas.validarString(descricao,"Descricao nao pode ser nula ou vazia.");
		
		this.descricao = descricao;
	}

	public void setCampoDeInteresse(String campoDeInteresse) {
		ValidadorEntradas.validarString(campoDeInteresse,"Formato do campo de interesse invalido.");
		
		this.campoDeInteresse = campoDeInteresse;
	}
	
	public void ativaPesquisa() {
		this.status = true;
	}
	public void desativaPesquisa() {
		this.status = false;
	}
	public boolean getStatus() {
		return this.status;
	}
	

}
