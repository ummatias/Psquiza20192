package atividade;

import java.util.List;

public class MaisAntiga implements Estrategia{

	@Override
	public Estrategia getEstrategia(String estrategia) {
		return new MaisAntiga();
	}

	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		return null;
	}

}
