package atividade;

import java.util.List;

public class MaiorDuracao implements Estrategia {

	@Override
	public Estrategia getEstrategia(String estrategia) {
		return new MaiorDuracao();
	}

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
