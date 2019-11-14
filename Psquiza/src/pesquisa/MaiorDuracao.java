package pesquisa;

import java.util.List;

import atividade.Atividade;

public class MaiorDuracao implements Estrategia {
	
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
