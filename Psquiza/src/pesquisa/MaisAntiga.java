package pesquisa;

import java.io.Serializable;
import java.util.List;

import atividade.Atividade;

/**Classe criada usando a interface Estrategia que retorna a atividade mais antiga
 * @author Emilly de Albuquerque Oliveira - 119111162
 *
 */
public class MaisAntiga implements Estrategia, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1366024318295793050L;

	/**
	 * Método que sugere a proxima atividade mais antiga
	 */
	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		String codeAtividade = "";
		for (Atividade atividade:atividades) {
			if (atividade.contaItensPendentes() > 0) {
				codeAtividade = atividade.getCodigo();
				break;
			}
		} return codeAtividade;
	}
}
