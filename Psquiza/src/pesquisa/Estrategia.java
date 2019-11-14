package pesquisa;

import java.util.List;
import atividade.Atividade;

public interface Estrategia {

	public String proximaAtividade(List<Atividade> atividades);
}
