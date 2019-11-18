package pesquisa;

import java.util.List;

import atividade.Atividade;

/**Classe criada usando a interface Estrategia que retorna a atividade com
 * maior risco.
 * @author Emilly de Albuquerque Oliveira - 119111162
 *
 */
public class MaiorRisco implements Estrategia{

	/**
	 * Método que sugere a proxima atividade com maior risco
	 */
	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		String maiorCode = "";
		for (Atividade atividade:atividades) {
			if(maiorCode.equals("")) {
			 maiorCode = atividade.getRisco();
			} 
			
			
			if(maiorCode.equals("BAIXO")) {
				if(atividade.getRisco().equals("MEDIO") || atividade.getRisco().equals("ALTO")) {
					maiorCode = atividade.getCodigo();
				}
			}else if(atividade.getRisco().equals("MEDIO")) {
				if(atividade.getRisco().equals("ALTO")) {
					maiorCode = atividade.getRisco();
				}
			} else if(maiorCode.equals("ALTO")) {
				break;
			}
		}
		return maiorCode;
	}
}
