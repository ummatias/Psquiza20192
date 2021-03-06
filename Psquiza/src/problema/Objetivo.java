package problema;

import java.io.Serializable;

import validadores.ValidadorEntradas;

/**
 * Classe que representa um Objetivo de uma pesquisa. Este objetivo pode ser do
 * tipo GERAL ou ESPECIFICO. Os objetivos de uma pesquisa costumam trabalhar
 * para a resolução do problema.
 * 
 * Um objetivo GERAL é mais abrangente e responde diretamente ao problema da
 * pesquisa. Um objetivo ESPECIFICO delimita alvos específicos para atingir o
 * objetivo geral.
 * 
 * @author José Igor de Farias Gomes - 119110692
 *
 */
public class Objetivo implements Comparable<Objetivo>, Serializable {

	/**
	 * numero que identifica que versao da classe foi usada na serializacao
	 */
	private static final long serialVersionUID = 1894889608615390697L;

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
	 * Status do objetivo, se está concluido ou não.
	 */
	private boolean status;

	/**
	 * Constrói o objeto do tipo Objetivo inicializando seus atributos.
	 * 
	 * @param codigo      o código indentificador único de um Objetivo
	 * @param tipo        o tipo de um Objetivo que pode ser GERAL ou ESPECIFICO.
	 * @param descricao   a descrição do objetivo de pesquisa.
	 * @param aderencia   inteiro de 1 a 5 que representa o nível de aderência de um
	 *                    objetivo ao problema de pesquisa.
	 * @param viabilidade inteiro de 1 a 5 que representa o quão viável um objetivo
	 *                    é de ser concluído.
	 */
	public Objetivo(String codigo, String tipo, String descricao, int aderencia, int viabilidade) {
		ValidadorEntradas.validarCodigoObjetivo(codigo, "Codigo invalido");
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarTipoObjetivo(tipo, "Valor invalido de tipo.");
		ValidadorEntradas.validarString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarAderencia(aderencia, "Valor invalido de aderencia");
		ValidadorEntradas.validarViabilidade(viabilidade, "Valor invalido de viabilidade.");

		this.codigo = codigo;
		this.tipo = tipo;
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;
		this.status = false;
	}

	/**
	 * Calcula um hashcode único para o Objeto Objetivo tendo como base o código,
	 * que é seu identificador único.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Compara um Objetivo com outro objeto e retorna se os objetos são iguais ou
	 * não.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Objetivo other = (Objetivo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Retorna a representação em String de um Objetivo
	 */
	@Override
	public String toString() {
		return this.codigo + " - " + this.tipo + " - " + this.descricao + " - " + this.getValor();
	}

	/**
	 * Calcula o valor de um Objetivo, que é a soma de seus atributos aderencia e
	 * viabilidade.
	 * 
	 * @return a soma aderencia+viabilidade
	 */
	private int getValor() {
		return this.aderencia + this.viabilidade;
	}

	/**
	 * Método que retorna o codigo do objetivo.
	 * 
	 * @return o codigo
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Método que retorna o status do objetivo.
	 * 
	 * @return o status do objetivo.
	 */
	public boolean getStatus() {
		return this.status;
	}

	/**
	 * Método que altera o status do objetivo
	 * 
	 * @param status - o status que o objetivo vai assumir.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Método que indica como o objeto do tipo Objetivo irá ser comparado.
	 */
	@Override
	public int compareTo(Objetivo o) {
		return (this.getCodigo().compareTo(o.getCodigo())) * -1;
	}

	/**
	 * Método que busca determinado termo dentro do objetivo
	 * 
	 * @param termo - termo sendo procurado
	 * @return o codido do objetivo e a descrição em que se encontra.
	 */
	public String buscaTermo(String termo) {
		String saida = "";

		if (this.descricao.toLowerCase().contains(termo.toLowerCase())) {
			saida += this.codigo + ": " + this.descricao + " | ";
		}

		if (saida.length() > 0) {
			return saida.substring(0, saida.length() - 3);
		}
		return saida;
	}

	/**
	 * Método que desativa o objetivo
	 */
	public void desativa() {
		this.status = false;

	}

}
