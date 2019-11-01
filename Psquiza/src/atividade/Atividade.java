package atividade;

import java.util.LinkedHashSet;
import java.util.Set;

import validadores.ValidadorEntradas;

/**
 * Classe que modela uma atividade para atingir um objetivo da pesquisa.
 * Uma atividade contém uma descrição, um risco associado e uma 
 * descrição para esse risco.
 * 
 * @author Mateus Matias Ribeiro - 119111153
 *
 */
public class Atividade {
	
	/**
	 * String referente a descrição da atividade.
	 */
	private String desc;
	
	/**
	 * String referente ao nível de risco da atividade.
	 */
	private String nvlRisco;
	
	/**
	 * String referente a descrição desse risco.
	 */
	private String descRisco;
	
	/**
	 * String referente ao codigo da atividade.
	 */
	private String code;
	
	/**
	 * Set que contém os itens da atividade.
	 */
	private Set<Item> itens;
	
	
	
	/**
	 * Constrói uma atividade a partir de seu codigo, descrição
	 * nível de risco e descrição desse risco, e inicializa seus atributos.
	 * 
	 * @param code String contendo o código da atividade.
	 * @param desc String contendo a descrição da atividade.
	 * @param nvlRisco String contendo o nível de risco da atividade.
	 * @param descRisco String contendo a descrição do risco da atividade.
	 */
	public Atividade(String code, String desc, String nvlRisco, String descRisco) {
		ValidadorEntradas.validarString(code, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(desc, "Campo Descricao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(nvlRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(descRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		
		this.desc = desc;
		this.nvlRisco = nvlRisco;
		this.descRisco = descRisco;
		this.itens = new LinkedHashSet<Item>();
	}
	
	/**
	 * Adiciona um item na atividade.
	 * 
	 * @param itemText String referente ao item.
	 */
	public void	addItem(String itemText) {
		Item item = new Item(itemText);
		itens.add(item);
	}
	
	/**
	 * Conta quantidade de itens ainda pendente da atividade.
	 * 
	 * @return Inteiro referente a quantidade de itens pedentes.
	 */
	public int contaItensPendentes() {
		int cont;
		cont = 0;
		for(Item it : itens) {
			if(!it.getStatus()) {
				cont += 1;
			}
		}
		return cont;
	}
	
	/**
	 * Conta quantidade de itens realizados da atividade.
	 * 
	 * @return Inteiro referente a quantidade de itens realizados.
	 */
	public int contaItensRealizados() {
		int cont = 0;
		
		for(Item item : itens) {
			if(item.getStatus()) {
				cont += 1;
			}
		}
		return cont;
	}
	
	//Gera a representação em String de uma atividade
	@Override
	public String toString() {
		String retorno = desc + " (" + nvlRisco + " - " + descRisco + ")";
		for(Item it : itens) {
			retorno += " | " + it.toString();
		}
		retorno = retorno.substring(0, retorno.length());
		return retorno;
	}
	
	
	/**
	 * Retorna o código da atividade.
	 * 
	 * @return String referente ao código da atividade
	 */
	private String getCode() {
		return code;
	}

	//Gera um inteiro que serve como identificador único para a atividade.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	/**Compara duas atividades e retorna true se forem iguais e
	 * False se forem difrentes, utiliza o código da atividade como
	 * critério.
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}




	
}
