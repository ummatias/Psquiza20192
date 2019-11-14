package pesquisa;

import java.util.List;

import atividade.Atividade;

public class MaisAntiga implements Estrategia{

	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		return atividades.get(0).getCodigo();
	}

}
