package controladores;

import java.util.HashMap;
import java.util.Map;

import entidades.Objetivo;
import entidades.Problema;
import validadores.ValidadorEntradas;

/**
 * Controller responsável pelo gerenciamento das classes Problema e Objetivo.
 * Classe que armazena os problemas e objetivos cadastrados no sistema.
 * 
 * @author José Igor de Farias Gomes - 119110692
 *
 */
public class ProblemaObjetivoController {

	/**
	 * Mapa que relaciona os problemas cadastrados no sistema aos seus códigos.
	 */
	private Map<String, Problema> problemas;
	
	/**
	 * Mapa que relaciona os objetivos cadastrados no sistema aos seus códigos.
	 */
	private Map<String, Objetivo> objetivos;

	/**
	 * Último ID dos problemas cadastrado no sistema que serve como controle para criação
	 * automática dos códigos identificadores dos proximos problemas a serem cadastrados no sistema.
	 */
	private int ultimoIDProblemas;
	
	/**
	 * Último ID dos ojetivos cadastrado no sistema que serve como controle para criação
	 * automática dos códigos identificadores dos proximos objetivos a serem cadastrados no sistema.
	 */
	private int ultimoIDObjetivos;

	/**
	 * Constroi o Controller inicializando seus atributos.
	 */
	public ProblemaObjetivoController() {
		this.problemas = new HashMap<>();
		this.objetivos = new HashMap<>();
		this.ultimoIDProblemas = 0;
		this.ultimoIDObjetivos = 0;
	}

	/**
	 * Cadastra um novo problema no sistema.
	 * 
	 * @param descricao a descrição do problema
	 * @param viabilidade valor entre 1 e 5 que determina a viabilidade de resolução do problema.
	 * @return o código do novo problema.
	 */
	public String cadastraProblema(String descricao, int viabilidade) {
		ValidadorEntradas.validarString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarViabilidade(viabilidade, "Valor invalido de viabilidade.");

		String codigo = "O" + (this.ultimoIDProblemas++);
		Problema novoProb = new Problema(codigo, descricao, viabilidade);

		this.problemas.put(codigo, novoProb);

		return codigo;

	}

	/**
	 * Cadastra um novo objetivo no sistema.
	 * 
	 * @param tipo o tipo do objetivo que deve ser 'GERAL' ou 'ESPECIFICO'
	 * @param descricao a descrição do objetivo.
	 * @param aderencia o nível de aderência do objetivo ao problema que deve ser um valor inteiro entre 1 e 5.
	 * @param viabilidade o nível de viabilidade para a conclusão do objetivo.
	 * @return o código do novo objetivo.
	 */
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		ValidadorEntradas.validarString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarTipo(tipo, "Valor invalido de tipo.");
		ValidadorEntradas.validarString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarAderencia(aderencia, "Valor invalido de aderencia");
		ValidadorEntradas.validarViabilidade(viabilidade, "Valor invalido de viabilidade.");
		
		String codigo = "P" + (this.ultimoIDProblemas++);
		Objetivo novoObj = new Objetivo(codigo, tipo, descricao, aderencia, viabilidade);
		
		this.objetivos.put(codigo, novoObj);
		
		return codigo;
		
	}


}
