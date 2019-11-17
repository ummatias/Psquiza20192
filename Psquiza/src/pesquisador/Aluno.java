package pesquisador;

import java.io.Serializable;

import validadores.ValidadorEntradas;

/**
 * Especialidade que o Pesquisador pode assumir.
 * Possui semestre e IEA
 *
 */
public class Aluno implements Especialidade, Serializable{

	/**
	 * Semestre de ingresso do aluno
	 */
	private int semestre;
	
	/**
	 * Indice de Eficiencia Academica do aluno
	 */
	private double iea;
	
	/**
	 * Constroi o objeto do tipo aluno e inicializa seus atributos.
	 * 
	 * @param semestre o semestre do aluno
	 * @param iea o indice de eficiencia do aluno
	 */
	public Aluno(int semestre, double iea) {
		this.semestre = semestre;
		this.iea = iea;
	}

	/**
	 * Acessa o semestre do aluno
	 * @return o semestre do aluno
	 */
	public int getSemestre() {
		return semestre;
	}

	/**
	 * Acessa o IEA do aluno
	 * @return o IEA do aluno
	 */
	public double getIea() {
		return iea;
	}

	/**
	 * Altera um atributo do aluno
	 * 
	 * @param atributo o campo a ser alterado
	 * @param novoValor o novo valor do campo
	 */
	@Override
	public void alteraPesquisador(String atributo, String novoValor) {
		switch (atributo) {
		case "SEMESTRE":
			int semestre = Integer.parseInt(novoValor);
			ValidadorEntradas.validaSemestreAluno(semestre, "Formato de semestre invalido.");
			
			this.semestre = semestre;
			break;
		case "IEA":
			double iea = Double.parseDouble(novoValor);
			ValidadorEntradas.validaIEA(iea, "Formato de IEA invalido.");
			
			this.iea = iea;
			break;
		default:
			throw new IllegalArgumentException("Atributo invalido.");
		}
		
	}
	
	public String toString(String base) {
		return base + " - " + this.semestre + "o SEMESTRE - " + this.iea;
	}
	
}
