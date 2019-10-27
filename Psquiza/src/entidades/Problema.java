package entidades;

/**
 * Classe que modela um problema de pesquisa. O problema a ser abordado na
 * pesquisa deve ser fonte de incômodo a algo ou alguém. Ao mesmo tempo, para
 * que seja um problema objeto de pesquisa, é necessário que seja também viável.
 * 
 * @author José Igor de Farias Gomes - 119110692
 *
 */
public class Problema {

	/**
	 * Código e identificador único de um problema
	 */
	private String codigo;
	
	/**
	 * Descrição do problema
	 */
	private String descricao;
	
	/**
	 * Inteiro de 1 a 5 que define o quão viável é a solução de um problema.
	 */
	private int viabilidade;

	/**
	 * Constrói o objeto do tipo Problema inicializando seus atributos.
	 * 
	 * @param codigo o código (identificador único) do problema.
	 * @param descricao a descrição do problema.
	 * @param viabilidade o nível de viabilidade da resolução do problema
	 */
	public Problema(String codigo, String descricao, int viabilidade) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.viabilidade = viabilidade;
	}

}
