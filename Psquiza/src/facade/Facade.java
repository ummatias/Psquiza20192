package facade;

import controladores.ProblemaObjetivoController;
import easyaccept.EasyAccept;

/**
 * Interface de comunicação entre o back-end e o front-end do Sistema.
 * Responsável por delegar as operações aos diferentes controllers do sistema.
 * 
 * @author José Igor de Farias Gomes -119110692
 *
 */
public class Facade {

	/**
	 * Atributo que guarda o controller responsável por operações com Problema e Objetivo.
	 */
	private ProblemaObjetivoController probObjController;

	/**
	 * Constrói o objeto do tipo Facade inicializando seus atributos.
	 */
	public Facade() {
		this.probObjController = new ProblemaObjetivoController();
	}

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "testes_aceitacao/use_case_3.txt" };
		EasyAccept.main(args);
	}

	/**
	 * Cadastra um problema no sistema.
	 * 
	 * @param descricao
	 *            a descrição do problema.
	 * @param viabilidade
	 *            o quão viável é a resolução do problema.
	 * @return o código do novo problema cadastrado.
	 */
	public String cadastraProblema(String descricao, int viabilidade) {
		return probObjController.cadastraProblema(descricao, viabilidade);
	}
	
	/**
	 * Cadastra um novo objetivo no sistema.
	 * 
	 * @param tipo o tipo do objetivo que deve ser 'GERAL' ou 'ESPECIFICO'
	 * @param descricao a descrição do objetivo.
	 * @param aderencia o nível de aderência do objetivo ao problema que deve ser um valor inteiro entre 1 e 5.
	 * @param viabilidade o nível de viabilidade para a conclusão do objetivo.
	 * @return o código do novo objetivo.
	 */
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return probObjController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

}
