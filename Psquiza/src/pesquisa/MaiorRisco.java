package pesquisa;

import java.util.List;

import atividade.Atividade;

/**Classe criada usando a interface Estrategia que retorna a atividade com
 * maior risco.
 * @author Emilly de Albuquerque Oliveira - 119111162
 *
 */
public class MaiorRisco implements Estrategia{

	/**
	 * Método que sugere a proxima atividade com maior risco
	 */
	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		String retorno = "";
//		for (int i = 0; i < atividades.size(); i++) {
//			Atividade atividade = atividades.get(i);
//			Atividade prox = atividades.get(i++);
//			atividade.defineProximaAtividade(prox);
//			retorno = atividade.pegaMaiorRiscoAtividades();
//			atividade.tiraProximaAtividade();
//		}
		return retorno;
	}
}
