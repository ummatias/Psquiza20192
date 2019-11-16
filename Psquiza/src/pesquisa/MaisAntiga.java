package pesquisa;

import java.util.List;

import atividade.Atividade;

/**Classe criada usando a interface Estrategia que retorna a atividade mais antiga
 * @author Emilly de Albuquerque Oliveira - 119111162
 *
 */
public class MaisAntiga implements Estrategia{

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
