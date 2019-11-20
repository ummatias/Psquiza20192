package busca;

import atividade.AtividadeController;
import pesquisa.PesquisaController;
import pesquisador.PesquisadorController;
import problema.ProblemaObjetivoController;
import validadores.ValidadorEntradas;

/**
 * Cria a classe que gerencia a busca no sistema
 * 
 * @author Natalia Salvino
 *
 */
public class BuscaController {
	private AtividadeController atividadeController;
	private PesquisaController pesquisaController;
	private PesquisadorController pesquisadorController;
	private ProblemaObjetivoController problemaObjetivoController;

	/**
	 * Constrói a busca a partir de outros controllers do sistema
	 * 
	 * @param atividadeController        o controller de atividades
	 * @param pesquisaController         o controller de pesquisa
	 * @param pesquisadorController      o controller de pesquisadores
	 * @param problemaObjetivoController o controller de problemas e objetivos
	 */
	public BuscaController(AtividadeController atividadeController, PesquisaController pesquisaController,
			PesquisadorController pesquisadorController, ProblemaObjetivoController problemaObjetivoController) {
		this.atividadeController = atividadeController;
		this.pesquisaController = pesquisaController;
		this.pesquisadorController = pesquisadorController;
		this.problemaObjetivoController = problemaObjetivoController;
	}

	/**
	 * busca por um termo no sistema
	 * 
	 * @param termo o termo procurado
	 * @return retorna a representação textual dos respectivos objetos cadastrados
	 *         que possuem o termo procurado
	 */

	private String verificaBuscaVazia(String busca) {
		if (busca.equals("")) {
			return busca;
		}
		return busca + " | ";
	}

	/** Método que pesquisa determinado termo dentro de todo o sistema.
	 * @param termo - termo sendo pesquisado
	 * @return o resultado com o termo
	 */
	public String busca(String termo) {
		ValidadorEntradas.validarString(termo, "Campo termo nao pode ser nulo ou vazio.");

		String saida = verificaBuscaVazia(this.pesquisaController.buscaDescricaoCampoDeInteresse(termo))
				+ verificaBuscaVazia(this.pesquisadorController.buscaBiografia(termo))
				+ verificaBuscaVazia(this.problemaObjetivoController.buscaDescricaoProblema(termo))
				+ verificaBuscaVazia(this.problemaObjetivoController.buscaDescricaoObjetivo(termo))
				+ verificaBuscaVazia(this.atividadeController.buscaDescricao(termo));

		if (saida.length() > 0) {
			return saida.substring(0, saida.length() - 3);
		}
		return saida;
	}

	/**
	 * retorna o resultado na posição escolhida
	 * 
	 * @param termo o termo procurado
	 * @param num   o numero da posição procurada
	 * @return retorna o resultado da busca na posição procurada
	 */
	public String busca(String termo, int num) {
		if (num <= 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}
		String busca = this.busca(termo);

		String[] resultado = busca.split("\\|");
		if (num - 1 > resultado.length) {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		}
		return resultado[num - 1].trim();
	}

	/**
	 * Conta quantos resultados foram encontrados
	 * 
	 * @param termo o termo procurado
	 * @return retorna a quantidade total de resultados encontrados na busca
	 */
	public int contaResultadosBusca(String termo) {
		ValidadorEntradas.validarString(termo, "Campo termo nao pode ser nulo ou vazio.");

		String busca = this.busca(termo);
		if (busca.equals("")) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		}
		String[] resultado = busca.split("\\|");

		return resultado.length;
	}
}
