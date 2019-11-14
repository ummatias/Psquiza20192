package pesquisa;

import java.util.List;

import atividade.Atividade;

public class MaiorRisco implements Estrategia{

	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		return "oi";
	}
}
