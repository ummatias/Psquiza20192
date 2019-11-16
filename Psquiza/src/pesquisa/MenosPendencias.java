package pesquisa;

import java.util.List;

import atividade.Atividade;


/**
 * Classe criada usando a interface Estrategia que retorna a atividade com
 * menos itens pendentes.
 * @author Emilly de Albuquerque Oliveira - 119111162
 *
 */
public class MenosPendencias implements Estrategia {
	
	/**
	 * Método que sugere a proxima atividade com menos pendencias
	 */
	@Override
	public String proximaAtividade(List<Atividade> atividades) {
		int cont = 0;
		String codeAtividade = "";

		for (Atividade atividade:atividades) {
			if(atividade.contaItensPendentes() != 0) {
				if(cont == 0) {
					cont = atividade.contaItensPendentes();
					codeAtividade = atividade.getCodigo();}
				if ( atividade.contaItensPendentes() < 0) {
					cont = atividade.contaItensPendentes();
					codeAtividade = atividade.getCodigo();
				}
			}
		}
		
		if (cont == 0) {
			throw new IllegalArgumentException("Pesquisa sem atividades com pendencias");
		}
		
		return codeAtividade;
	}

}
