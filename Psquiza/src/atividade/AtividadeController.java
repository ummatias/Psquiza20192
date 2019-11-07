package atividade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pesquisa.Pesquisa;
import validadores.ValidadorEntradas;

/**
 * Controlador responsável pelo gerenciamento das classes "Atividade"
 * e "Item". Classe que armazena as atividades no sistema
 * 
 * @author Mateus Matias Ribeiro - 119111153
 * @author Emilly de Albuquerque Oliveira - 119111162
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
		if (!atividades.containsKey(code)) {
			atividades.put(code, atividade);}
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
	
	/** Método para retornar uma atividade do sistema pelo codigo.
	 * @param codigo - codigo da atividade
	 * @return o objeto Atividade
	 */
	public Atividade getAtividade(String codigo) {
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaAtividadeExiste(atividades, codigo);
		return atividades.get(codigo);
	}
	
	/**Método para indicar a execução de determinada atividade.
	 * @param codigoAtividade - codigo da atividade
	 * @param item - item que será executado
	 * @param duracao - duraçao da execução
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		atividades.get(codigoAtividade).executaAtividade(item, duracao);
	}
	
	/**Método que retorna a duração de determinada atividade.
	 * @param codigoAtividade - codigo da atividade.
	 * @return a duração total da atividade.
	 */
	public int getDuracao(String codigoAtividade) {
		return atividades.get(codigoAtividade).getDuracao();
	}
	
	/** Método que cadastra resultados a uma determinada atividade.
	 * @param codigoAtividade - código da atividade que vai cadastrar o resultado
	 * @param resultado - resultado que vai ser adicionado
	 * @return código do resultado
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		return atividades.get(codigoAtividade).addResultados(resultado);
	}
	
	/**Método para remover determinado resultado.
	 * @param codigoAtividade - código da atividade que terá o resultado removido
	 * @param numeroResultado - codigo do resultado 
	 * @return true se foi removido com sucesso
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return atividades.get(codigoAtividade).removeResultado(numeroResultado);
	}
	
	/** Método que retorna a listagem de todos os resultados de determinada atividade.
	 * @param codigoAtividade - código da atividade
	 * @return a lista de resultados
	 */
	public String listaResultados(String codigoAtividade) {
		return atividades.get(codigoAtividade).listaResultados();
	}
	public String buscaDescricao(String termo) {
		ValidadorEntradas.validarString(termo, "Campo termo nao pode ser nulo ou vazio.");
		
		List<Atividade> listAtividades = new ArrayList<>(this.atividades.values());
		Collections.sort(listAtividades);
		
		String saida = "";
		for (Atividade atividade : listAtividades) {
			String atividadeSaida = atividade.buscaTermo(termo);
			
			if (atividadeSaida.length() > 0) {
				saida += atividadeSaida + " | ";
				
			}
		}
		
		if (saida.length() > 0) {
			return saida.substring(0, saida.length() - 3);
		}
		return saida;
	}
}
