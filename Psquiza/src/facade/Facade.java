package facade;

import atividade.AtividadeController;
import busca.BuscaController;
import easyaccept.EasyAccept;
import generaliza.ControllerGeral;
import pesquisa.PesquisaController;
import pesquisador.PesquisadorController;
import problema.ProblemaObjetivoController;

/**
 * Interface de comunicação entre o back-end e o front-end do Sistema.
 * Responsável por delegar as operações aos diferentes controllers do sistema.
 * 
 * @author José Igor de Farias Gomes -119110692
 *
 */
public class Facade {

	private ControllerGeral controllerGeral;
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
		this.pesquisaController = new PesquisaController();
		this.pesquisadorController = new PesquisadorController();
		this.problemaObjetivoController = new ProblemaObjetivoController();
		this.controllerGeral = new ControllerGeral(this.atividadeController,this.pesquisaController, this.pesquisadorController, this.problemaObjetivoController);
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
		return controllerGeral.cadastraProblema(descricao, viabilidade);
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
		return controllerGeral.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	/**
	 * Apaga um problema do sistema
	 * 
	 * @param codigo o codigo do problema a ser apagado
	 */
	public void apagarProblema(String codigo) {
		controllerGeral.apagarProblema(codigo);
	}

	/**
	 * Apaga um objetivo cadastrado no sistema
	 * 
	 * @param codigo o codigo do objetivo a ser apagado
	 */
	public void apagarObjetivo(String codigo) {
		controllerGeral.apagarObjetivo(codigo);
	}

	/**
	 * Exibe a representação String de um Problema
	 * 
	 * @param codigo o codigo do problema a ser visualizado
	 * @return a representação String do Problema
	 */
	public String exibeProblema(String codigo) {
		return controllerGeral.exibeProblema(codigo);
	}

	/**
	 * Exibe a um Objetivo
	 * 
	 * @param codigo o codigo do objetivo
	 * @return a representação em String do um objetivo.
	 */
	public String exibeObjetivo(String codigo) {
		return controllerGeral.exibeObjetivo(codigo);
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
		controllerGeral.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	/**
	 * Altera algum atributo do Pesquisador.
	 * 
	 * @param email     - identificador do pesquisador.
	 * @param atributo  - atributo a ser alterado.
	 * @param novoValor - novo valor que o atributo vai assumir.
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		controllerGeral.alteraPesquisador(email, atributo, novoValor);
	}

	/**
	 * Desativa o pesquisador, impedindo de exibir, alterar e desativar.
	 * 
	 * @param email - email e identificador unico do pesquisador.
	 */
	public void desativaPesquisador(String email) {
		controllerGeral.desativaPesquisador(email);
	}

	/**
	 * Ativa o pesquisador.
	 * 
	 * @param email - email e identificador unico do pesquisador.
	 */
	public void ativaPesquisador(String email) {
		controllerGeral.ativaPesquisador(email);
	}

	/**
	 * Exibe o pesquisador se estiver ativo no sistema.
	 * 
	 * @param email - email e identificador unico do pesquisador.
	 * @return a representação textual do pesquisador.
	 */
	public String exibePesquisador(String email) {
		return controllerGeral.exibePesquisador(email);
	}

	/**
	 * Cadastra uma pesquisa a partir do campo de interesse e descricao
	 * 
	 * @param descricao        a descricao da pesquisa
	 * @param campoDeInteresse o campo de interesse da pesquisa
	 * @return retorna o codigo da pesquisa
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return this.controllerGeral.cadastraPesquisa(descricao, campoDeInteresse);
	}

	/**
	 * Altera uma pesquisa
	 * 
	 * @param codigo               o codigo da pesquisa
	 * @param conteudoASerAlterado o conteudo a ser alterado
	 * @param novoConteudo         o novo conteudo
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		this.controllerGeral.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	/**
	 * Encerra uma pesquisa
	 * 
	 * @param codigo o codigo da pesquisa
	 * @param motivo o motivo do encerramento
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		this.controllerGeral.encerraPesquisa(codigo, motivo);
	}

	/**
	 * Ativa uma pesquisa desativada
	 * 
	 * @param codigo o codigo da pesquisa
	 */
	public void ativaPesquisa(String codigo) {
		this.controllerGeral.ativaPesquisa(codigo);
	}

	/**
	 * exibe uma pesquisa
	 * 
	 * @param codigo o codigo da pesquisa
	 * @return retorna a representacao textual da pesquisa
	 */
	public String exibePesquisa(String codigo) {
		return this.controllerGeral.exibePesquisa(codigo);
	}

	/**
	 * verifica se uma pesquisa e ativa
	 * 
	 * @param codigo o codigo da pesquisa
	 * @return retorna o boolean se a pesquisa e ativa ou nao
	 */
	public boolean pesquisaEhAtiva(String codigo) {
		return this.controllerGeral.pesquisaEhAtiva(codigo);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return controllerGeral.pesquisadorEhAtivo(email);
	}

	public String cadastraAtividade(String Descricao, String nivelRisco, String descricaoRisco) {
		return controllerGeral.cadastraAtividade(Descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		controllerGeral.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		controllerGeral.cadastraItem(codigo, item);

	}

	public String exibeAtividade(String codigo) {
		return controllerGeral.exibeAtividade(codigo);

	}

	public int contaItensPendentes(String codigo) {
		return controllerGeral.contaItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return controllerGeral.contaItensRealizados(codigo);

	}

	
	
	/**
	 * {@link ControllerGeral}
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) {
		return controllerGeral.associaProblema(idPesquisa, idProblema);
	}
	
	/**
	 * {@link ControllerGeral}
	 */
	public boolean desassociaProblema(String idPesquisa) {
		return controllerGeral.desassociaProblema(idPesquisa);
	}

	
	/**
	 * {@link ControllerGeral}
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return controllerGeral.associaObjetivo(idPesquisa, idObjetivo);
	}
	
	/**
	 * {@link ControllerGeral}
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return controllerGeral.desassociaObjetivo(idPesquisa, idObjetivo);
	}
	
	/**
	 * {@link ControllerGeral}
	 */
	public String listaPesquisas(String ordem) {
		return controllerGeral.listaPesquisas(ordem);
	}
	
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return controllerGeral.associaAtividade(codigoPesquisa, codigoAtividade);
	}


	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return controllerGeral.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		controllerGeral.executaAtividade(codigoAtividade, item, duracao);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return controllerGeral.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return controllerGeral.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {
		return controllerGeral.listaResultados(codigoAtividade);
	}

	public int getDuracao(String codigoAtividade) {
		return controllerGeral.getDuracao(codigoAtividade);
	}
  
  public String busca(String termo) {
    	return buscaController.busca(termo);
   }

	/**
	 * Especializa um pesquisador como sendo do tipo Professor, cadastrando seus
	 * atributos especiais: formação, unidade e data de contratação
	 * 
	 * @param email o email do pesquisador
	 * @param formacao o grau de formação do professor
	 * @param unidade a unidade academica do professor
	 * @param data a data de contratação do professor
	 */
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		controllerGeral.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}
	
	/**
	 * Especializa um pesquisador como sendo do tipo Aluno, cadastrando seus
	 * atributos especiais: semestre e IEA.
	 * 
	 * @param email o email do pesquisador
	 * @param semestre o semestre do aluno
	 * @param iea o indice de eficiencia academica do aluno
	 */
	public void cadastraEspecialidadeAluno(String email, int semestre, double iea) {
		controllerGeral.cadastraEspecialidadeAluno(email, semestre, iea);
	}
	
	/**
	 * Associa um pesquisador a uma pesquisa desde que esta ainda esteja ativa.
	 * 
	 * @param idPesquisa o id da pesquisa
	 * @param emailPesquisador o email do pesquisador
	 * @return true - se a associação aconteceu com sucesso, false - caso a associação já exista
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return controllerGeral.associaPesquisador(idPesquisa, emailPesquisador);
	}
	
	/**
	 * Desassocia um pesquisador de uma pesquisa desde que esta ainda esteja ativa.
	 * 
	 * @param idPesquisa o id da pesquisa
	 * @param emailPesquisador o email do pesquisador
	 * @return true - se a desassociação aconteceu com sucesso, false - caso a associação não exista
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return controllerGeral.desassociaPesquisador(idPesquisa, emailPesquisador);
	}
	public int contaResultadosBusca(String termo) {
		return this.buscaController.contaResultadosBusca(termo);
	}
	public String busca(String termo, int numero) {
		return this.buscaController.busca(termo, numero);
	}
	
	/**
	 * Lista os pesquisadores de um determinado tipo/funcao.
	 * 
	 * @param tipo o tipo do pesquisador
	 * @return a listagem dos pesquisadores que pertencem a uma função.
	 */
	public String listaPesquisadores(String tipo) {
		return controllerGeral.listaPesquisadores(tipo);
	}
	

}
