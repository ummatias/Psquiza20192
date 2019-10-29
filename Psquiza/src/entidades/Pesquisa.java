package entidades;

import validadores.ValidadorEntradas;

public class Pesquisa {
	private String descricao;
	private String campoDeInteresse;
	private String codigo;
	private boolean status;
	
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
	

}
