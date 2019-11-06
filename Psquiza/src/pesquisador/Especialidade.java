package pesquisador;

/**
 * Interface que generaliza uma especialidade de um pesquisador, pode assumir os tipo de Aluno ou Professor.
 * 
 *
 */
public interface Especialidade {
	/**
	 * Altera um atributo do pesquisador
	 * 
	 * @param atributo o campo a ser alterado
	 * @param novoValor o novo valor do campo
	 */
	public void alteraPesquisador(String atributo, String novoValor);

	public String toString(String base);

}
