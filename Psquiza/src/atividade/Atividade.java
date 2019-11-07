package atividade;

import java.util.ArrayList;
import java.util.List;

import validadores.ValidadorEntradas;

/**
 * Classe que modela uma atividade para atingir um objetivo da pesquisa.
 * Uma atividade contém uma descrição, um risco associado e uma 
 * descrição para esse risco.
 * 
 * @author Mateus Matias Ribeiro - 119111153
 * @author Emilly de Albuquerque Oliveira - 119111162
 */
public class Atividade implements Comparable<Atividade>{
	
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
	private List<Item> itens;
	
	private int duracao;
	
	private List<String> resultados;
	
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
		this.itens = new ArrayList<Item>();
		this.duracao = 0;
		this.resultados = new ArrayList<String>();
		this.code = code;
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

	/** Método que retorna a duração da ativida.
	 * @return duração da atividade inteira.
	 */
	public int getDuracao() {
	 return duracao;
	}

	/** Método para executar a atividade.
	 * @param item - item usado para realizar a atividade, será alterado o status como executado;
	 * @param horas - horas gastas com a atividade
	 */
	public void executaAtividade(int item, int horas) {
		this.duracao += horas;
		itens.get(item - 1).setStatus(true);
	}

	/**Método para adicionar resultados a atividade.
	 * @param resultado - resultado a ser cadastrado
	 * @return código do resultado
	 */
	public int addResultados(String resultado) {
		resultados.add(resultado);
		return resultados.size();
	}

	/** Método que remove um resultado da atividade.
	 * @param numeroResultado - numero do indice do resultado
	 * @return true se o resultado foi removido com sucesso.
	 */
	public boolean removeResultado(int numeroResultado) {
		resultados.remove(numeroResultado);
		if (resultados.get(numeroResultado) == null) {
			return true;
		}  return false;
	}

	/** Método que retorna a lista com os resultados
	 * @return representação textual com todos os
	 * resultados dessa atividade
	 */
	public String listaResultados() {
		String retorno = "";
		for (String resultado:resultados) {
			retorno += resultado + " | ";
		}
		return retorno.substring(0, retorno.length() - 3);
	}
	public String getCodigo() {
		return this.code;
	}

	@Override
	public int compareTo(Atividade o) {
		return (this.getCodigo().compareTo(o.getCodigo())) * -1;
	}
	public String buscaTermo(String termo) {
		String saida = "";
		
		if(this.desc.toLowerCase().contains(termo.toLowerCase())) {
			saida += this.code + ": " + this.desc + " | ";
		}
		if(this.descRisco.toLowerCase().contains(termo.toLowerCase())) {
			saida += this.code + ": " + this.descRisco + " | ";
		}
		if(saida.length() > 0) {
			return saida.substring(0,saida.length() - 3);
		}
		return saida;
	}
}
