package pesquisa;

import java.util.List;

import atividade.Atividade;

public class MaiorRisco implements Estrategia{

	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		String retorno = "";
		for (Atividade atividade:atividades) {
			retorno = atividade.pegaMaiorRiscoAtividades();
		}
		return retorno;
	}
}
