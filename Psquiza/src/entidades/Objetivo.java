package entidades;

/**
 * Classe que representa um Objetivo de uma pesquisa. Este objetivo pode ser do
 * tipo GERAL ou ESPECIFICO. Os objetivos de uma pesquisa costumam trabalhar para a resolução do problema.
 * 
 * Um objetivo GERAL é mais abrangente e responde diretamente ao problema da pesquisa.
 * Um objetivo ESPECIFICO delimita alvos específicos para atingir o objetivo geral.
 * 
 * @author José Igor de Farias Gomes - 119110692
 *
 */
public class Objetivo {

	/**
	 * O código que identifica unicamente um objetivo.
	 */
	private String codigo;
	
	/**
	 * O tipo do objetivo que pode ser GERAL ou ESPECIFICO.
	 */
	private String tipo;
	
	/**
	 * A descrição do objetivo da pesquisa.
	 */
	private String descricao;
	
	/**
	 * O quão aderente um objetivo está ao problema da pesquisa. Inteiro de 1 a 5.
	 */
	private int aderencia;
	
	/**
	 * O quão viável é a conclusão de um objetivo. Inteiro de 1 a 5.
	 */
	private int viabilidade;

	/**
	 * Constrói o objeto do tipo Objetivo inicializando seus atributos.
	 * 
	 * @param codigo o código indentificador único de um Objetivo
	 * @param tipo o tipo de um Objetivo que pode ser GERAL ou ESPECIFICO.
	 * @param descricao a descrição do objetivo de pesquisa.
	 * @param aderencia inteiro de 1 a 5 que representa o nível de aderência de um objetivo ao problema de pesquisa.
	 * @param viabilidade inteiro de 1 a 5 que representa o quão viável um objetivo é de ser concluído.
	 */
	public Objetivo(String codigo, String tipo, String descricao, int aderencia, int viabilidade) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;
	}

}
