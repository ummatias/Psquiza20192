package atividade;

import java.util.List;

public class MaiorRisco implements Estrategia{
	
	@Override
	public Estrategia getEstrategia(String estrategia) {
		
		return new MaiorRisco();
	}

	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		String nvlRisco = "BAIXO";
		for (Atividade atividade: atividades) {
			if (nvlRisco != atividade.getNivelRisco().toUpperCase()) {
				if (nvlRisco.equals("BAIXO") && atividade.getNivelRisco().equals("ALTO") || atividade.getNivelRisco().equals("MEDIA")) {
					
				} if (nvlRisco.equals("MEDIO") && atividade.getNivelRisco().equals("ALTO")){
					nvlRisco = atividade.getNivelRisco();
				}
		}} return nvlRisco;}
}
