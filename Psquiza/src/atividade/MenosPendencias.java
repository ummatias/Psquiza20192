package atividade;

import java.util.List;


public class MenosPendencias implements Estrategia {


	@Override
	public Estrategia getEstrategia(String estrategia) {
		return new MenosPendencias();
	}
	
	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		// TODO Auto-generated method stub
		return null;
	}

}
