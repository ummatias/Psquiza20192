package pesquisa;

import java.util.List;

import atividade.Atividade;


public class MenosPendencias implements Estrategia {
	
	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		int cont = 0;
		String codeAtividade = "";
		for (Atividade atividade:atividades) {
			if(atividade.getContaItensPendentes() > cont) {
				cont = atividade.getContaItensPendentes();
				codeAtividade = atividade.getCodigo();
			}
		}
		
		if (cont == 0) {
			throw new IllegalArgumentException("Pesquisa sem atividades com pendencias");
		}
		
		return codeAtividade;
	}

}
