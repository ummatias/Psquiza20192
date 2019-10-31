package atividade;

import java.util.LinkedHashSet;
import java.util.Set;

import validadores.ValidadorEntradas;

public class Atividade {
	private String desc;
	private String nvlRisco;
	private String descRisco;
	private String code;
	private Set<Item> items;
	
	
	
	public Atividade(String code, String desc, String nvlRisco, String descRisco) {
		ValidadorEntradas.validarString(code, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(desc, "Campo Descricao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(nvlRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(descRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		
		this.desc = desc;
		this.nvlRisco = nvlRisco;
		this.descRisco = descRisco;
		this.items = new LinkedHashSet<Item>();
	}
	
	public void	addItem(String itemText) {
		Item item = new Item(itemText);
		items.add(item);
	}
	
	public int contaItensPendentes() {
		int cont;
		cont = 0;
		for(Item it : items) {
			if(!it.getStatus()) {
				cont += 1;
			}
		}
		return cont;
	}
	
	public int contaItensRealizados() {
		int cont = 0;
		
		for(Item item : items) {
			if(item.getStatus()) {
				cont += 1;
			}
		}
		return cont;
	}
	
	public String toString() {
		String retorno = desc + " (" + nvlRisco + " - " + descRisco + ")";
		for(Item it : items) {
			retorno += " | " + it.toString();
		}
		retorno = retorno.substring(0, retorno.length());
		return retorno;
	}

	private String getCode() {
		return code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

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
