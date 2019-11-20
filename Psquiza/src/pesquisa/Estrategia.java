package pesquisa;

import java.util.List;
import atividade.Atividade;

/** Interface que abstrai a forma como será sugerida a proxima atividade de pesquisa
 * @author Emilly de Albuquerque Oliveira - 119111162
 *
 */
public interface Estrategia {

	public String proximaAtividade(List<Atividade> atividades);
}
