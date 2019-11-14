package pesquisa;

import java.util.List;

import atividade.Atividade;

public class MaisAntiga implements Estrategia{

	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		String codeAtividade = "";
		for (Atividade atividade:atividades) {
			if (atividade.getContaItensPendentes() > 0) {
				codeAtividade = atividade.getCodigo();
			}
		} return codeAtividade;
	}
}
