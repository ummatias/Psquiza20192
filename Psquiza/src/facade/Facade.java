package facade;

import atividade.AtividadeController;
import busca.BuscaController;
import easyaccept.EasyAccept;
import pesquisa.PesquisaController;
import pesquisador.PesquisadorController;
import problema.ProblemaObjetivoController;

/**
 * Interface de comunicação entre o back-end e o front-end do Sistema.
 * Responsável por delegar as operações aos diferentes controllers do sistema.
 * 
 * @author José Igor de Farias Gomes -119110692
 * @author Emilly de Albuquerque Oliveira - 119111162
 * @author Natalia Salvino André - 119110051
 * @author Mateus Matias Ribeiro - 119111153
 */
public class Facade {

	private BuscaController buscaController;
	private AtividadeController atividadeController;
	private PesquisaController pesquisaController;
	private PesquisadorController pesquisadorController;
	private ProblemaObjetivoController problemaObjetivoController;

	/**
	 * Constrói o objeto do tipo Facade inicializando seus atributos.
	 */
	public Facade() {
		this.atividadeController = new AtividadeController();
		this.pesquisadorController = new PesquisadorController();
		this.problemaObjetivoController = new ProblemaObjetivoController();
		this.pesquisaController = new PesquisaController(atividadeController, problemaObjetivoController, pesquisadorController);
		this.buscaController = new BuscaController(this.atividadeController,this.pesquisaController, this.pesquisadorController, this.problemaObjetivoController);
	}

	public static void main(String[] args) {
		args = new String[] { "facade.Facade",

				"testes_aceitacao/use_case_1.txt",
				"testes_aceitacao/use_case_2.txt",
				"testes_aceitacao/use_case_3.txt",
				"testes_aceitacao/use_case_4.txt",
				"testes_aceitacao/use_case_5.txt",
				"testes_aceitacao/use_case_6.txt",
				"testes_aceitacao/use_case_7.txt",
				"testes_aceitacao/use_case_8.txt"
				};

		EasyAccept.main(args);
	}

	/**
	 * Cadastra um problema no sistema.
	 * 
	 * @param descricao   a descrição do problema.
	 * @param viabilidade o quão viável é a resolução do problema.
	 * @return o código do novo problema cadastrado.
	 */
	public String cadastraProblema(String descricao, int viabilidade) {
		return problemaObjetivoController.cadastraProblema(descricao, viabilidade);
	}

	/**
	 * Cadastra um novo objetivo no sistema.
	 * 
	 * @param tipo        o tipo do objetivo que deve ser 'GERAL' ou 'ESPECIFICO'
	 * @param descricao   a descrição do objetivo.
	 * @param aderencia   o nível de aderência do objetivo ao problema que deve ser
	 *                    um valor inteiro entre 1 e 5.
	 * @param viabilidade o nível de viabilidade para a conclusão do objetivo.
	 * @return o código do novo objetivo.
	 */
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return problemaObjetivoController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	/**
	 * Apaga um problema do sistema
	 * 
	 * @param codigo o codigo do problema a ser apagado
	 */
	public void apagarProblema(String codigo) {
		problemaObjetivoController.apagaProblema(codigo);
	}

	/**
	 * Apaga um objetivo cadastrado no sistema
	 * 
	 * @param codigo o codigo do objetivo a ser apagado
	 */
	public void apagarObjetivo(String codigo) {
		problemaObjetivoController.apagaObjetivo(codigo);
	}

	/**
	 * Exibe a representação String de um Problema
	 * 
	 * @param codigo o codigo do problema a ser visualizado
	 * @return a representação String do Problema
	 */
	public String exibeProblema(String codigo) {
		return problemaObjetivoController.exibeProblema(codigo);
	}

	/**
	 * Exibe a um Objetivo
	 * 
	 * @param codigo o codigo do objetivo
	 * @return a representação em String do um objetivo.
	 */
	public String exibeObjetivo(String codigo) {
		return problemaObjetivoController.exibeObjetivo(codigo);
	}

	/**
	 * Cadastra um novo pesquisador ao sistema.
	 * 
	 * @param nome      - nome do pesquisador
	 * @param funcao    - funçao do pesquisador (externo, estudante ou professor)
	 * @param biografia - biografia do pesquisador.
	 * @param email     - email e identificador do usuario.
	 * @param fotoURL   - url da foto do pesquisador.
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		pesquisadorController.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	/**
	 * Altera algum atributo do Pesquisador.
	 * 
	 * @param email     - identificador do pesquisador.
	 * @param atributo  - atributo a ser alterado.
	 * @param novoValor - novo valor que o atributo vai assumir.
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		pesquisadorController.alteraPesquisador(email, atributo, novoValor);
	}

	/**
	 * Desativa o pesquisador, impedindo de exibir, alterar e desativar.
	 * 
	 * @param email - email e identificador unico do pesquisador.
	 */
	public void desativaPesquisador(String email) {
		pesquisadorController.desativaPesquisador(email);
	}

	/**
	 * Ativa o pesquisador.
	 * 
	 * @param email - email e identificador unico do pesquisador.
	 */
	public void ativaPesquisador(String email) {
		pesquisadorController.ativaPesquisador(email);
	}

	/**
	 * Exibe o pesquisador se estiver ativo no sistema.
	 * 
	 * @param email - email e identificador unico do pesquisador.
	 * @return a representação textual do pesquisador.
	 */
	public String exibePesquisador(String email) {
		return pesquisadorController.exibePesquisador(email);
	}

	/**
	 * Cadastra uma pesquisa a partir do campo de interesse e descricao
	 * 
	 * @param descricao        a descricao da pesquisa
	 * @param campoDeInteresse o campo de interesse da pesquisa
	 * @return retorna o codigo da pesquisa
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return this.pesquisaController.cadastraPesquisa(descricao, campoDeInteresse);
	}

	/**
	 * Altera uma pesquisa
	 * 
	 * @param codigo               o codigo da pesquisa
	 * @param conteudoASerAlterado o conteudo a ser alterado
	 * @param novoConteudo         o novo conteudo
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		this.pesquisaController.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	/**
	 * Encerra uma pesquisa
	 * 
	 * @param codigo o codigo da pesquisa
	 * @param motivo o motivo do encerramento
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		this.pesquisaController.encerraPesquisa(codigo, motivo);
	}

	/**
	 * Ativa uma pesquisa desativada
	 * 
	 * @param codigo o codigo da pesquisa
	 */
	public void ativaPesquisa(String codigo) {
		this.pesquisaController.ativaPesquisa(codigo);
	}

	/**
	 * exibe uma pesquisa
	 * 
	 * @param codigo o codigo da pesquisa
	 * @return retorna a representacao textual da pesquisa
	 */
	public String exibePesquisa(String codigo) {
		return this.pesquisaController.exibePesquisa(codigo);
	}

	/**
	 * verifica se uma pesquisa e ativa
	 * 
	 * @param codigo o codigo da pesquisa
	 * @return retorna o boolean se a pesquisa e ativa ou nao
	 */
	public boolean pesquisaEhAtiva(String codigo) {
		return this.pesquisaController.pesquisaEhAtiva(codigo);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return pesquisadorController.ehAtivo(email);
	}

	public String cadastraAtividade(String Descricao, String nivelRisco, String descricaoRisco) {
		return atividadeController.cadastraAtividade(Descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		atividadeController.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		atividadeController.cadastraItem(codigo, item);

	}

	public String exibeAtividade(String codigo) {
		return atividadeController.exibeAtividade(codigo);

	}

	public int contaItensPendentes(String codigo) {
		return atividadeController.contaItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return atividadeController.contaItensRealizados(codigo);

	}

	public boolean associaProblema(String idPesquisa, String idProblema) {
		return pesquisaController.associaProblema(idPesquisa, idProblema);
	}
	
	public boolean desassociaProblema(String idPesquisa) {
		return pesquisaController.desassociaProblema(idPesquisa);
	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return pesquisaController.associaObjetivo(idPesquisa, idObjetivo);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return pesquisaController.desassociaObjetivo(idPesquisa, idObjetivo);
	}
	
	public String listaPesquisas(String ordem) {
		return pesquisaController.listaPesquisas(ordem);
	}
	
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return pesquisaController.associaAtividade(codigoPesquisa, codigoAtividade);
	}


	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return pesquisaController.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		pesquisaController.executaAtividade(codigoAtividade, item, duracao);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return atividadeController.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return atividadeController.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {
		return atividadeController.listaResultados(codigoAtividade);
	}

	public int getDuracao(String codigoAtividade) {
		return atividadeController.getDuracao(codigoAtividade);
	}
  
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		pesquisadorController.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}
	
	
	public void cadastraEspecialidadeAluno(String email, int semestre, double iea) {
		pesquisadorController.cadastraEspecialidadeAluno(email, semestre, iea);
	}
	

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return pesquisaController.associaPesquisador(idPesquisa, emailPesquisador);
	}
	

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return pesquisaController.desassociaPesquisador(idPesquisa, emailPesquisador);
	}

	public String listaPesquisadores(String tipo) {
		return pesquisadorController.listaPesquisadores(tipo);
	}
	
	public String busca(String termo) {
    	return buscaController.busca(termo);
   }

	public int contaResultadosBusca(String termo) {
		return this.buscaController.contaResultadosBusca(termo);
	}
	
	public String busca(String termo, int numero) {
		return this.buscaController.busca(termo, numero);
	}
	
	

}
