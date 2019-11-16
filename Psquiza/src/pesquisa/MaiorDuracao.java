package pesquisa;

import java.util.List;

import atividade.Atividade;

/**
 * Classe criada usando a interface Estrategia que retorna a atividade com
 * maior duração.
 * @author Emilly de Albuquerque Oliveira - 119111162
 *
 */
public class MaiorDuracao implements Estrategia {
	
	/**
	 * Método que sugere a proxima atividade com maior duração
	 */
	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		int maiorDuracao = 0;
		String codeAtividade = "";
		for (Atividade atividade:atividades) {
			if(atividade.getDuracao() > maiorDuracao) {
				codeAtividade = atividade.getCodigo();
			}
		} return codeAtividade;
	}

}
