package facade;

import controladores.PesquisadorController;
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
	 * Atributo que contêm o controller responsável pelas operações com Pesquisador.
	 */
	private PesquisadorController psqzadorController;

	/**
	 * Constrói o objeto do tipo Facade inicializando seus atributos.
	 */
	public Facade() {
		this.probObjController = new ProblemaObjetivoController();
		this.psqzadorController = new PesquisadorController();
	}

	public static void main(String[] args) {
		args = new String[] { "facade.Facade",
				"testes_aceitacao/use_case_2.txt",
				"testes_aceitacao/use_case_3.txt" };
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
	
	/** 
	 * Cadastra um novo pesquisador ao sistema.
	 * @param nome - nome do pesquisador
	 * @param funcao - funçao do pesquisador (externo, estudante ou professor)
	 * @param biografia - biografia do pesquisador.
	 * @param email - email e identificador do usuario.
	 * @param fotoURL - url da foto do pesquisador.
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		psqzadorController.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}
	
	/** 
	 * Altera algum atributo do Pesquisador.
	 * @param email - identificador do pesquisador.
	 * @param atributo - atributo a ser alterado.
	 * @param novoValor - novo valor que o atributo vai assumir.
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		psqzadorController.alteraPesquisador(email, atributo, novoValor);
	}
	
	/**
	 * Desativa o pesquisador, impedindo de exibir, alterar e desativar.
	 * @param email - email e identificador  unico do pesquisador.
	 */
	public void desativaPesquisador(String email) {
		psqzadorController.desativaPesquisador(email);
	}
	
	/**
	 * Ativa o pesquisador.
	 * @param email - email e identificador  unico do pesquisador.
	 */
	public void ativaPesquisador(String email) {
		psqzadorController.ativaPesquisador(email);
	}
	
	/**
	 * Exibe o pesquisador se estiver ativo no sistema.
	 * @param email - email e identificador  unico do pesquisador.
	 * @return a representação textual do pesquisador.
	 */
	public String exibePesquisador(String email) {
		return psqzadorController.exibePesquisador(email);
	}
}