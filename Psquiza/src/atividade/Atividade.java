package atividade;

import java.util.LinkedHashSet;
import java.util.Set;

public class Atividade {
	private String desc;
	private String nvlRisco;
	private String descRisco;
	private String code;
	private Set<Item> items;
	
	
	
	public Atividade(String code, String desc, String nvlRisco, String descRisco) {
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
		int cont = 0;
		
		for(Item item : items) {
			if(item.getStatus() == false) {
				cont += 1;
			}
		}
		return cont;
	}
	
	public int contaItensRealizados() {
		int cont = 0;
		
		for(Item item : items) {
			if(item.getStatus() == true) {
				cont += 1;
			}
		}
		return cont;
	}
	
	public String toString() {
		String retorno = retorno = desc + " (" + nvlRisco + " - " + descRisco + ")";
		if(!items.isEmpty()) {
			for(Item item : items) {
				retorno += item.ToString();
			}	
		}
		
		return retorno;
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
