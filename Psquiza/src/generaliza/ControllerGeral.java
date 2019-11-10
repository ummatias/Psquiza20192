package generaliza;

import java.util.Arrays;

import atividade.Atividade;
import atividade.AtividadeController;
import pesquisa.PesquisaController;
import pesquisador.Pesquisador;
import pesquisador.PesquisadorController;
import problema.Objetivo;
import problema.Problema;
import problema.ProblemaObjetivoController;
import validadores.ValidadorEntradas;

public class ControllerGeral {
	private PesquisaController pesquisaController;
	
	/**
	 * Atributo que contêm o controller responsável pelas operações com Pesquisador.
	 */
	private PesquisadorController psqzadorController;
	
	/**
	 * Atributo que guarda o controller responsável por operações com Problema e
	 * Objetivo.
	 */
	private ProblemaObjetivoController probObjController;
	private AtividadeController ativController;
	
	public ControllerGeral() {
		pesquisaController = new PesquisaController();
		psqzadorController = new PesquisadorController();
		probObjController = new ProblemaObjetivoController();
		ativController = new AtividadeController();
	}
	
	/**
	 * Cadastra um problema no sistema.
	 * 
	 * @param descricao   a descrição do problema.
	 * @param viabilidade o quão viável é a resolução do problema.
	 * @return o código do novo problema cadastrado.
	 */
	public String cadastraProblema(String descricao, int viabilidade) {
		return probObjController.cadastraProblema(descricao, viabilidade);
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
		return probObjController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	/**
	 * Apaga um problema do sistema
	 * 
	 * @param codigo o codigo do pesquisador a ser apagado
	 */
	public void apagarProblema(String codigo) {
		probObjController.apagaProblema(codigo);
	}

	/**
	 * Apaga um objetivo cadastrado no sistema
	 * 
	 * @param codigo o codigo do objetivo a ser apagado
	 */
	public void apagarObjetivo(String codigo) {
		probObjController.apagaObjetivo(codigo);
	}

	/**
	 * Exibe a representação String de um Problema
	 * 
	 * @param codigo o codigo do problema a ser visualizado
	 * @return a representação String do Problema
	 */
	public String exibeProblema(String codigo) {
		return probObjController.exibeProblema(codigo);
	}

	/**
	 * Exibe a um Objetivo
	 * 
	 * @param codigo o codigo do objetivo
	 * @return a representação em String do um objetivo.
	 */
	public String exibeObjetivo(String codigo) {
		return probObjController.exibeObjetivo(codigo);
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
		psqzadorController.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	/**
	 * Altera algum atributo do Pesquisador.
	 * 
	 * @param email     - identificador do pesquisador.
	 * @param atributo  - atributo a ser alterado.
	 * @param novoValor - novo valor que o atributo vai assumir.
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		psqzadorController.alteraPesquisador(email, atributo, novoValor);
	}

	/**
	 * Desativa o pesquisador, impedindo de exibir, alterar e desativar.
	 * 
	 * @param email - email e identificador unico do pesquisador.
	 */
	public void desativaPesquisador(String email) {
		psqzadorController.desativaPesquisador(email);
	}

	/**
	 * Ativa o pesquisador.
	 * 
	 * @param email - email e identificador unico do pesquisador.
	 */
	public void ativaPesquisador(String email) {
		psqzadorController.ativaPesquisador(email);
	}

	/**
	 * Exibe o pesquisador se estiver ativo no sistema.
	 * 
	 * @param email - email e identificador unico do pesquisador.
	 * @return a representação textual do pesquisador.
	 */
	public String exibePesquisador(String email) {
		return psqzadorController.exibePesquisador(email);
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
		return psqzadorController.ehAtivo(email);
	}
  
	public String cadastraAtividade(String Descricao, String nivelRisco, String descricaoRisco) {
		return ativController.cadastraAtividade(Descricao, nivelRisco, descricaoRisco);
	}
	
	public void apagaAtividade(String codigo) {
		ativController.apagaAtividade(codigo);
	}
	public void cadastraItem(String codigo, String item) {
		ativController.cadastraItem(codigo, item);
		
	}
	public String exibeAtividade(String codigo) {
		return ativController.exibeAtividade(codigo);

	}
	public int contaItensPendentes(String codigo) {
		return ativController.contaItensPendentes(codigo);
	}
	public int contaItensRealizados(String codigo) {
		return ativController.contaItensRealizados(codigo);

	}

	
	
	
	
	
	/**
	 * Associa um problma a uma pesquisa, apartir de seus respectivos ID's e
	 * retorna um valor booleano dizendo se a associação foi bem sucedida (true)
	 * ou se o problema já estava associado (false)
	 * 
	 * @param idPesquisa ID da pesquisa que será associado o problema
	 * @param idProblema ID do problema a ser associado a pesquisa
	 * @return valor boolean contendo o resultado da operação
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		
		Problema problema = probObjController.getProblema(idProblema);
		return pesquisaController.associaProblema(idPesquisa, problema);
	}
	
	public boolean desassociaProblema(String idPesquisa) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		
		return pesquisaController.desassociaProblema(idPesquisa);
	}
	
	
	/**
	 * Associa um objetivo a uma pesquisa, apartir de seus respectivos ID's e
	 * retorna um valor booleano dizendo se a associação foi bem sucedida (true)
	 * ou se o objetivo já estava associado (false)
	 * 
	 * @param idPesquisa ID da pesquisa que será associado o objetivo
	 * @param idObjetivo ID do objetivo a ser associado a pesquisa
	 * @return valor boolean que representa o resultado da operação
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		
		Objetivo objetivo = probObjController.getObjetivo(idObjetivo);
		return pesquisaController.associaObjetivo(idPesquisa, objetivo);
	}
	
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if(pesquisaController.desassociaObjetivo(idPesquisa, idObjetivo)) {
			Objetivo objetivo = probObjController.getObjetivo(idObjetivo);
			objetivo.setStatus(false);
			return true;
		}return false;
	}
	
	
	
	
	
	
	/** Método que associa uma atividade a pesquisa.
	 * @param codigoPesquisa - código de pesquisa
	 * @param codigoAtividade - código de atividade
	 * @return true se associar com sucesso.
	 */
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade){
		Atividade atividade = ativController.getAtividade(codigoAtividade);
		
		ValidadorEntradas.validarString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaPesquisaAtiva(pesquisaController.pesquisaEhAtiva(codigoPesquisa));

		return pesquisaController.associaAtividade(codigoPesquisa, atividade);
	}
	
	/** Método que desassocia uma atividade a pesquisa.
	 * @param codigoPesquisa - código de pesquisa
	 * @param codigoAtividade - código de atividade
	 * @return true se desassociar com sucesso.
	 */
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		ValidadorEntradas.validarString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaPesquisaAtiva(pesquisaEhAtiva(codigoPesquisa));
		ValidadorEntradas.validaAtividadeExiste(ativController.getMapa(), codigoAtividade);
		
		Atividade atividade = ativController.getAtividade(codigoAtividade);
		
		return pesquisaController.desassociaAtividade(codigoPesquisa, atividade);
	}
	
	/** Método que executa uma atividade
	 * @param codigoAtividade - código da atividade
	 * @param item - item usado durante a execução
	 * @param duracao - duração da execução
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		
		ValidadorEntradas.validaAtividadeEstaAssociada(pesquisaController.getAtividades(), ativController.getAtividade(codigoAtividade));
		
		ativController.executaAtividade(codigoAtividade, item, duracao);
	}
	
	/** Método que cadastra um resultado à atividade
	 * @param codigoAtividade - código de atividade
	 * @param resultado - resultado que vai ser cadastrado
	 * @return indice do resultado e ordem do cadastro
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		return ativController.cadastraResultado(codigoAtividade, resultado);
	}
	
	/** Método que remove um resultado do sistema
	 * @param codigoAtividade - código da atividade
	 * @param numeroResultado - indice do resultado.
	 * @return true se foi removido com sucesso
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return ativController.removeResultado(codigoAtividade, numeroResultado);
	}
	
	/** Método que retorna uma lista dos resultados de determinada atividade
	 * @param codigoAtividade - código da atividade
	 * @return a lista dos resultados.
	 */
	public String listaResultados(String codigoAtividade) {
		return ativController.listaResultados(codigoAtividade);
	}
	/** Método que retorna a duração total da atividade
	 * @param codigoAtividade - código da atividade
	 * @return a duração total da atividade.
	 */
	public int getDuracao(String codigoAtividade) {
		return ativController.getDuracao(codigoAtividade);
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
		psqzadorController.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
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
		psqzadorController.cadastraEspecialidadeAluno(email, semestre, iea);
		
	}

	/**
	 * Associa um pesquisador a uma pesquisa desde que esta esteja ainda ativa.
	 * 
	 * @param idPesquisa o id da pesquisa
	 * @param emailPesquisador o email do pesquisador
	 * @return true se a associação aconteceu com sucesso, false - caso a associação já exista
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		Pesquisador pesquisador = psqzadorController.getPesquisador(emailPesquisador);
		
		return pesquisaController.associaPesquisador(idPesquisa, pesquisador);
	}

	/**
	 * Desassocia um pesquisador de uma pesquisa desde que esta ainda esteja ativa.
	 * 
	 * @param idPesquisa o id da pesquisa
	 * @param emailPesquisador o email do pesquisador
	 * @return true - se a desassociação aconteceu com sucesso, false - caso a associação não exista
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return pesquisaController.desassociaPesquisador(idPesquisa, emailPesquisador);
	}
	public String busca(String termo) {
		ValidadorEntradas.validarString(termo, "Campo termo nao pode ser nulo ou vazio.");

		String saida = ValidadorEntradas.validaBuscaVazia(this.pesquisaController.buscaDescricaoCampoDeInteresse(termo))
				+ ValidadorEntradas.validaBuscaVazia(this.psqzadorController.buscaBiografia(termo))
				+ ValidadorEntradas.validaBuscaVazia(this.probObjController.buscaDescricaoProblema(termo))
				+ ValidadorEntradas.validaBuscaVazia(this.probObjController.buscaDescricaoObjetivo(termo))
				+ ValidadorEntradas.validaBuscaVazia(this.ativController.buscaDescricao(termo));
		
		if (saida.length() > 0) {
			return saida.substring(0, saida.length() - 3);
		}
		return saida;
	}
	public String busca(String termo, int num) {
		if (num < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}
		String busca = this.busca(termo);
		
		String[] resultado = busca.split("\\|");
		if (num - 1 > resultado.length) {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		}
		return resultado[num - 1].trim();
	}
	public int contaResultadosBusca(String termo) {
		ValidadorEntradas.validarString(termo, "Campo termo nao pode ser nulo ou vazio.");

		String busca = this.busca(termo);
		if (busca.equals("")) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		}
		System.out.println(busca);
		String[] resultado = busca.split("\\|");

		return resultado.length;
	}

	public String listaPesquisadores(String tipo) {
		return this.psqzadorController.listaPesquisadores(tipo);
	}

}
