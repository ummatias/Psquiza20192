package entidades;

import validadores.ValidadorEntradas;

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
		ValidadorEntradas.validarCodigoProblema(codigo, "Codigo invalido.");
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarViabilidade(viabilidade, "Valor invalido de viabilidade.");
		
		this.codigo = codigo;
		this.descricao = descricao;
		this.viabilidade = viabilidade;
	}
	
	/**
	 * Calcula um hashcode único para o Objeto Problema tendo como base o código, que é seu identificador único.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Compara um Problema com outro objeto e retorna se os objetos são iguais ou não.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Problema other = (Problema) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Gera a representação em String de um Problema
	 */
	@Override
	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.viabilidade;
	}

}
