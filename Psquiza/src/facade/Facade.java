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
				"testes_aceitacao/use_case_8.txt",
				"testes_aceitacao/use_case_9.txt",
				"testes_aceitacao/use_case_10.txt"
				};

		EasyAccept.main(args);
	}

	public String cadastraProblema(String descricao, int viabilidade) {
		return problemaObjetivoController.cadastraProblema(descricao, viabilidade);
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return problemaObjetivoController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarProblema(String codigo) {
		problemaObjetivoController.apagaProblema(codigo);
	}

	
	public void apagarObjetivo(String codigo) {
		problemaObjetivoController.apagaObjetivo(codigo);
	}

	
	public String exibeProblema(String codigo) {
		return problemaObjetivoController.exibeProblema(codigo);
	}

	
	public String exibeObjetivo(String codigo) {
		return problemaObjetivoController.exibeObjetivo(codigo);
	}

	
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		pesquisadorController.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}
	
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		pesquisadorController.alteraPesquisador(email, atributo, novoValor);
	}

	public void desativaPesquisador(String email) {
		pesquisadorController.desativaPesquisador(email);
	}
	
	public void ativaPesquisador(String email) {
		pesquisadorController.ativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return pesquisadorController.exibePesquisador(email);
	}

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return this.pesquisaController.cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		this.pesquisaController.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		this.pesquisaController.encerraPesquisa(codigo, motivo);
	}

	public void ativaPesquisa(String codigo) {
		this.pesquisaController.ativaPesquisa(codigo);
	}

	public String exibePesquisa(String codigo) {
		return this.pesquisaController.exibePesquisa(codigo);
	}

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
	
	public void defineProximaAtividade(String idPrecedente, String idSubsequente) {
		atividadeController.defineProximaAtividade(idPrecedente, idSubsequente);
	}
	
	public void tiraProximaAtividade(String idPrecedente) {
		atividadeController.tiraProximaAtividade(idPrecedente);
	}
	
	public int contaProximos(String idPrecedente) {
		return atividadeController.contaProximos(idPrecedente);
	}
	
	public void configuraEstrategia(String estrategia) {
		pesquisaController.configuraEstrategia(estrategia);
	}
	
	public String proximaAtividade(String codigoPesquisa) {
		return pesquisaController.proximaAtividade(codigoPesquisa);
	}

}
