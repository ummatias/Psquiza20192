package atividade;

import java.util.List;

public interface Estrategia {
	public abstract Estrategia getEstrategia(String estrategia);
	public String proximaAtividade(List<Atividade> atividades);
}
