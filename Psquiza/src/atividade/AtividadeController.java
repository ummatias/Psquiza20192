package atividade;

import java.util.HashMap;
import java.util.Map;
import validadores.ValidadorEntradas;

/**
 * Controlador responsável pelo gerenciamento das classes "Atividade"
 * e "Item". Classe que armazena as atividades no sistema
 * 
 * @author Mateus Matias Ribeiro - 119111153
 *
 */
public class AtividadeController {
	
	/**
	 * Mapa que contém as atividades e as relaciona com seus devidos
	 * codigos.
	 */
	private Map<String, Atividade> atividades;
	
	/**
	 * Inteiro que representa um contador, usado para gerar o
	 * codigo referente as atividades.
	 */
	int contAtiv;
	
	
	/**
	 * Constrói um Controller e inicializa seus atributos. 
	 */
	public AtividadeController() {
		atividades = new HashMap<>();
		contAtiv = 1;
	}
	
	/**
	 * Cadastra uma nova atividade no sistema a partir de sua descrição, nivél de
	 * risco e descrição desse risco.
	 * 
	 * @param desc String referente a descrição da atividade
	 * @param nvlRisco String referente ao nível de risco da atividade
	 * @param descRisco desc String referente a descrição do risco da atividade
	 * @return String contendo o código da atividade
	 */
	public String cadastraAtividade(String desc, String nvlRisco, String descRisco) {
		
		ValidadorEntradas.validarString(desc, "Campo Descricao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(nvlRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(descRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaValorRisco(nvlRisco);
		
		String code = generateCodeAtividade();
		Atividade atividade = new Atividade(code, desc, nvlRisco, descRisco);
		atividades.put(code, atividade);
		return code;
		
	}

	/**
	 * Apaga uma atividade do sistema a partir de seu código.
	 * 
	 * @param codigo código da atividade
	 */
	public void apagaAtividade(String codigo) {
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaAtividadeExiste(atividades, codigo);
		atividades.remove(codigo);
	}
	
	/**
	 * Gera um código para a atividade à ser cadastrada.
	 * 
	 * @return String contendo o código da atividade
	 */
	private String generateCodeAtividade() {
		String code = "A"+ contAtiv;
		contAtiv += 1;
		return code;
	}
	
	/**
	 * Cadastra um item a uma atividade.
	 * 
	 * @param codigo String contendo o código referente a atividade
	 * @param item String que representa o item a ser adicionado
	 */
	public void cadastraItem(String codigo, String item) {
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(item, "Item nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaAtividadeExiste(atividades, codigo);
		
		atividades.get(codigo).addItem(item);
	}
	
	/**
	 * Conta os itens pendentes de uma ativade através do código da mesma.
	 * 
	 * @param codigo String contendo o código referente a atividade
	 * @return Inteiro contendo o número de itens pendentes
	 */
	public int contaItensPendentes(String codigo) {
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaAtividadeExiste(atividades, codigo);
		return atividades.get(codigo).contaItensPendentes();
	}
	
	/**
	 * Conta os itens realizados de uma ativade através do código da mesma.
	 * 
	 * @param codigo String contendo o código referente a atividade
	 * @return Inteiro contendo o número de itens realizados
	 */
	public int contaItensRealizados(String codigo) {
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaAtividadeExiste(atividades, codigo);
		
		return atividades.get(codigo).contaItensRealizados();
	}
	
	/**
	 * Exibe a representação String de uma atividade no formato
	 * "Descrição (Nível de Risco - Desc. do Risco) | Items (Se houver)".
	 * 
	 * @param codigo String contendo o código referente a atividade
	 * @return String referente a representação textual da atividade
	 */
	public String exibeAtividade(String codigo) {
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaAtividadeExiste(atividades, codigo);
		
		return atividades.get(codigo).toString();
	}
}
