package pesquisa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import problema.Objetivo;
import problema.Problema;
import problema.ProblemaObjetivoController;
import atividade.Atividade;
import atividade.AtividadeController;
import pesquisador.Pesquisador;
import pesquisador.PesquisadorController;
import validadores.ValidadorEntradas;

/**
 * Classe que representa o controlador de pesquisas.
 * 
 * @author José Igor de Farias Gomes -119110692
 * @author Emilly de Albuquerque Oliveira - 119111162
 * @author Natalia Salvino André - 119110051
 * @author Mateus Matias Ribeiro - 119111153
 *
 */
public class PesquisaController {
	/**
	 * Mapa de pesquisas cadastradas no sistema.
	 */
	private Map<String, Pesquisa> pesquisasCadastradas;
	/**
	 * Mapa de chaves geradas pelo sistema
	 */
	private Map<String, Integer> chavesGeradas;

	/**
	 * Lista com todas as atividades associadas as pesquisas do sistema.
	 */
	private List<Atividade> atividadesAssociadas;
	
	private AtividadeController atividadeController;
	
	private ProblemaObjetivoController problemaObjetivoController;
	
	private PesquisadorController pesquisadorController;
	
	private Estrategia estrategia;

	public PesquisaController(AtividadeController atvController, ProblemaObjetivoController poController, PesquisadorController pesquisadorController) {
		this.pesquisasCadastradas = new HashMap<>();
		this.chavesGeradas = new HashMap<>();
		this.atividadesAssociadas = new ArrayList<>();
		this.atividadeController = atvController;
		this.problemaObjetivoController = poController;
		this.pesquisadorController = pesquisadorController;
		this.estrategia = new MaisAntiga();
	}

	/**
	 * Cadastra uma pesquisa no sistema a partir de uma descricao e um campo de
	 * interesse
	 * 
	 * @param descricao        descricao da pesquisa
	 * @param campoDeInteresse campo de interesse da pesquisa
	 * @return Retorna o codigo gerado quando a pesquisa e cadastrada com sucesso
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		ValidadorEntradas.validarString(campoDeInteresse, "Formato do campo de interesse invalido.");
		ValidadorEntradas.validarString(descricao, "Descricao nao pode ser nula ou vazia.");
		ValidadorEntradas.validaCampoDeInteresse(campoDeInteresse);

		String codigo = this.geraCodigo(campoDeInteresse);
		Pesquisa pesquisa = new Pesquisa(codigo, descricao, campoDeInteresse);
		this.pesquisasCadastradas.put(codigo, pesquisa);
		return this.pesquisasCadastradas.get(codigo).getCodigo();
	}

	/**
	 * Gera um codigo para a pesquisa a partir do campo de interesse de pesquisa.
	 * 
	 * @param campoDeInteresse o campo de interesse da pesquisa
	 * @return retorna o codigo gerado
	 */
	private String geraCodigo(String campoDeInteresse) {
		ValidadorEntradas.validarString(campoDeInteresse, "Formato do campo de interesse invalido.");
		String codigo = campoDeInteresse.substring(0, 3).toUpperCase();

		if (!this.chavesGeradas.containsKey(codigo)) {
			this.chavesGeradas.put(codigo, 1);
			String codigoGerado = codigo + this.chavesGeradas.get(codigo);
			return codigoGerado;

		}

		String codigoGerado = codigo + (this.chavesGeradas.get(codigo) + 1);
		this.chavesGeradas.put(codigo, chavesGeradas.get(codigo) + 1);
		return codigoGerado;
	}

	/**
	 * Altera uma pesqusia cadastrada
	 * 
	 * @param codigo               o codigo da pesquisa
	 * @param conteudoASerAlterado o conteudo a ser alterado
	 * @param novoConteudo         o novo conteudo
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		ValidadorEntradas.validarString(conteudoASerAlterado, "Conteudo nao pode ser vazio ou nulo");
		ValidadorEntradas.validarString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if (novoConteudo == null || novoConteudo.equals("")) {
			if (conteudoASerAlterado.equals("DESCRICAO")) {
				throw new IllegalArgumentException("Descricao nao pode ser nula ou vazia.");
			} else if (conteudoASerAlterado.equals("CAMPO")) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}
		}
		if (this.pesquisasCadastradas.containsKey(codigo)) {
			if (this.pesquisasCadastradas.get(codigo).ehAtiva()) {
				this.pesquisasCadastradas.get(codigo).alteraPesquisa(conteudoASerAlterado, novoConteudo);
			} else {
				throw new IllegalArgumentException("Pesquisa desativada.");
			}
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Encerra uma pesquisa
	 * 
	 * @param codigo o codigo da pesquisa
	 * @param motivo o motivo do encerramento.
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		ValidadorEntradas.validarString(codigo, "Codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(motivo, "Motivo nao pode ser nulo ou vazio.");

		if (this.pesquisasCadastradas.containsKey(codigo)) {
			if (this.pesquisasCadastradas.get(codigo).ehAtiva()) {
				this.pesquisasCadastradas.get(codigo).encerraPesquisa();
			} else {
				throw new IllegalArgumentException("Pesquisa desativada.");
			}
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

	}

	/**
	 * Ativa uma pesquisa
	 * 
	 * @param codigo o codigo da pesquisa
	 */
	public void ativaPesquisa(String codigo) {
		ValidadorEntradas.validarString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if (this.pesquisasCadastradas.containsKey(codigo)) {
			if (this.pesquisasCadastradas.get(codigo).ehAtiva() == false) {
				this.pesquisasCadastradas.get(codigo).ativaPesquisa();
			} else {
				throw new IllegalArgumentException("Pesquisa ja ativada.");
			}
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * exibe uma pesquisa cadastrada
	 * 
	 * @param codigo o codigo da pesquisa
	 * @return retorna a representação textual da pesquisa
	 */
	public String exibePesquisa(String codigo) {
		ValidadorEntradas.validarString(codigo, "Codigo nao pode ser nulo ou vazio.");

		if (this.pesquisasCadastradas.containsKey(codigo)) {
			return this.pesquisasCadastradas.get(codigo).toString();
		}
		throw new IllegalArgumentException("Pesquisa nao encontrada.");
	}

	/**
	 * Retorna se uma pesquisa e ativa
	 * 
	 * @param codigo o codigo da pesquisa
	 * @return retorna um boolean depois de verificar se a pesquisa e ativa
	 */
	public boolean pesquisaEhAtiva(String codigo) {
		ValidadorEntradas.validarString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if (this.pesquisasCadastradas.containsKey(codigo)) {
			return this.pesquisasCadastradas.get(codigo).ehAtiva();
		}
		throw new IllegalArgumentException("Pesquisa nao encontrada.");
	}

	
	
	
	/**
	 * Associa um problma a uma pesquisa e retorna um valor 
	 * booleano dizendo se a associação foi bem sucedida (true)
	 * ou se o problema já estava associado (false)
	 * 
	 * @param idPesquisa ID da pesquisa que será associado o problema
	 * @param problema Problema a ser associado a pesquisa
	 * @return valor boolean contendo o resultado da operação
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		validaPesquisaExiste(idPesquisa);
		validaPesquisaAtiva(idPesquisa);
		
		Problema problema = problemaObjetivoController.getProblema(idProblema);
		return pesquisasCadastradas.get(idPesquisa).associaProblema(problema);
	}
	
	/**
	 * Desassocia o problema que estiver ligado a pesquisa
	 * 
	 * @param idPesquisa ID da pesquisa que terá o problema desassociado
	 * @return Valor booleano contendo o resultado da operação
	 */
	public boolean desassociaProblema(String idPesquisa) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validaPesquisaExiste(idPesquisa);
		validaPesquisaAtiva(idPesquisa);
		return pesquisasCadastradas.get(idPesquisa).desassociaProblema();
	}
	
	/**
	 * Associa um objetivo a uma pesquisa e retorna um valor 
	 * booleano dizendo se a associação foi bem sucedida (true)
	 * ou se o problema já estava associado (false)
	 * 
	 * @param idPesquisa ID da pesquisa que será associado o objetivo
	 * @param objetivo objetivo a ser associado a pesquisa
	 * @return valor boolean contendo o resultado da operação
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		
		Objetivo objetivo = problemaObjetivoController.getObjetivo(idObjetivo);
		validaPesquisaExiste(idPesquisa);
		validaPesquisaAtiva(idPesquisa);

		return pesquisasCadastradas.get(idPesquisa).associaObjetivo(objetivo);
		
	}
	
	/**
	 * Desassocia um objetivo que estiver cadastrado na pesquisa através de seus
	 * respectivos ID's
	 * 
	 * @param idPesquisa ID da pesquisa que será desassociado o objetivo
	 * @param idObjetivo ID do objetivo a ser desassociado
	 * @return Valor booleano contendo o resultado da operação
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		validaPesquisaExiste(idPesquisa);
		validaPesquisaAtiva(idPesquisa);

		return pesquisasCadastradas.get(idPesquisa).desassociaObjetivo(idObjetivo);
	}
	
	/**
	 * Lista as pesquisas baseando-se em um critério passado como parametro
	 * 
	 * @param ordem Critério que definira a forma de ordenação
	 * @return String que representa as pesquisas ordenadas de acordo com o critério especificado
	 */
	public String listaPesquisas(String ordem) {
		ArrayList<Pesquisa> pesquisasOrdenadas = ordenaPesquisas(ordem);
		String retorno = "";
		
		for(Pesquisa pesquisa : pesquisasOrdenadas) {
			retorno += pesquisa.toString() + " | ";
		}
		
		return retorno.substring(0, retorno.length() - 3);
		
		
		
	}
	
	/**
	 * Cria uma lista de objetivos ordenada de acordo com um critério
	 * especificado por parametro.
	 * 
	 * @param Tipoordem Critério a ser usado na ordenação
	 * @return Lista ordenada de objetivos
	 */
	private ArrayList<Pesquisa> ordenaPesquisas(String Tipoordem) {
		OrdenaPesquisa ordem;
		
		switch (Tipoordem) {
		case "PROBLEMA":
			ordem = new OpcaoProblema();
			
			break;
		case "OBJETIVOS":
			ordem = new OpcaoObjetivo();
			break;
		case "PESQUISA":
			ordem = new OpcaoPesquisa();
			
			break;

		default:
			throw new IllegalArgumentException("Valor invalido da ordem");
		}
		ArrayList<Pesquisa> pesquisasOrdenadas = new ArrayList<Pesquisa>();
		pesquisasOrdenadas.addAll(pesquisasCadastradas.values());
		
		Collections.sort(pesquisasOrdenadas, ordem);
		return pesquisasOrdenadas;
		
	}

	
	/**
	 * Método que associa uma atividade a pesquisa.
	 * 
	 * @param codigoPesquisa - código da pesquisa
	 * @param atividade      - atividade a ser associada
	 * @return true se conseguir associar com sucesso. False se já tiver uma
	 *         atividade associada.
	 */
	public boolean associaAtividade(String codigoPesquisa,String codigoAtividade) {
		ValidadorEntradas.validarString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		atividadeController.validaAtividadeExiste(codigoAtividade);
		validaPesquisaExiste(codigoPesquisa);
		validaPesquisaAtiva(codigoPesquisa);
		
		Atividade atividade = atividadeController.getAtividade(codigoAtividade);
		atividadesAssociadas.add(atividade);
		return pesquisasCadastradas.get(codigoPesquisa).associaAtividade(atividade);
	}

	/**
	 * Método que desassocia uma atividade a pesquisa.
	 * 
	 * @param codigoPesquisa  - código da pesquisa
	 * @param codigoAtividade
	 * @return true se for desassociada com sucesso, false se já não tiver uma
	 *         atividade.
	 */
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		ValidadorEntradas.validarString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validaPesquisaExiste(codigoPesquisa);
		validaPesquisaAtiva(codigoPesquisa);
		atividadeController.validaAtividadeExiste(codigoAtividade);
		
		Atividade atividade = atividadeController.getAtividade(codigoAtividade);
		
		atividadesAssociadas.remove(atividade);
		return pesquisasCadastradas.get(codigoPesquisa).desassociaAtividade(atividade);

	}

	/**
	 * Associa um pesquisador a uma pesquisa desde que esta esteja ainda ativa.
	 * 
	 * @param idPesquisa  o id da pesquisa
	 * @param pesquisador o objeto pesquisador que se deseja associar
	 * @return true - se a associação aconteceu com sucesso, false - caso a
	 *         associação já exista
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Pesquisador pesquisador = pesquisadorController.getPesquisador(emailPesquisador);

		if (pesquisasCadastradas.containsKey(idPesquisa)) {
			Pesquisa pesquisa = pesquisasCadastradas.get(idPesquisa);

			return pesquisa.associaPesquisador(pesquisador);
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Desassocia um pesquisador de uma pesquisa desde que esta ainda esteja ativa.
	 * 
	 * @param idPesquisa       o id da pesquisa
	 * @param emailPesquisador o email do pesquisador
	 * @return true - se a desassociação aconteceu com sucesso, false - caso a
	 *         associação não exista
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		if (pesquisasCadastradas.containsKey(idPesquisa)) {
			Pesquisa pesquisa = pesquisasCadastradas.get(idPesquisa);

			return pesquisa.desassociaPesquisador(emailPesquisador);
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
    
  public String buscaDescricaoCampoDeInteresse(String termo) {
	
		ValidadorEntradas.validarString(termo, "Campo termo nao pode ser nulo ou vazio.");
		List<Pesquisa> listPesquisa = new ArrayList<>(this.pesquisasCadastradas.values());
		Collections.sort(listPesquisa);
		String saida = "";
		for (Pesquisa pesquisa : listPesquisa) {
			String pesquisaSaida = pesquisa.buscaTermo(termo);

			if (pesquisaSaida.length() > 0) {
				saida += pesquisaSaida + " | ";
			}

		}
		if (saida.length() > 0) {
			return saida.substring(0, saida.length() - 3);
		}
		return saida;
	}
	/**
	 * Método que retorna todas as atividades associadas a pesquisas.
	 * 
	 * @return a lista de atividades.
	 */
	private List<Atividade> getAtividades() {
		return atividadesAssociadas;
	}
	
	
	/** Método que executa uma atividade
	 * @param codigoAtividade - código da atividade
	 * @param item - item usado durante a execução
	 * @param duracao - duração da execução
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		
		validaAtividadeEstaAssociada(getAtividades(), atividadeController.getAtividade(codigoAtividade));
		
		atividadeController.executaAtividade(codigoAtividade, item, duracao);
	}
	
	
	/** Método que valida se uma atividade está associada a alguma pesquisa do sistema
	 * @param atividades - atividades do sistema associadas a pesquisas.
	 * @param atividade - atividade a ser validada.
	 */
	private void validaAtividadeEstaAssociada(List<Atividade> atividades, Atividade atividade) {
		if (!atividades.contains(atividade)) {
			throw new IllegalArgumentException("Atividade sem associacoes com pesquisas.");
		}
	}

	/**
	 * Valida se determinada pesquisa está cadastrada no sistema e
	 * lança uma exceção caso não esteja
	 * 
	 * @param idPesquisa ID da pesquisa a ser validada
	 */
	private void validaPesquisaExiste(String idPesquisa) {
		if(!pesquisasCadastradas.containsKey(idPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	/**
	 * Valida se determinada pesquisa está ativa no sistema e
	 * lança uma exceção caso não esteja
	 * 
	 * @param idPesquisa ID da pesquisa a ser validada
	 */
	private void validaPesquisaAtiva(String idPesquisa) {
		if(!pesquisasCadastradas.get(idPesquisa).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}

	/** Método que configura a estrategia para sugerir a proxima
	 * atividade a ser executada.
	 * @param estrategia - estrategia que será usada.
	 */
	public void configuraEstrategia(String estrategia) {
		ValidadorEntradas.validarString(estrategia, "Estrategia nao pode ser nula ou vazia.");
		ValidadorEntradas.validaEntradaEstrategia(estrategia);
		if("MENOS_PENDENCIAS".equals(estrategia)) {
			this.estrategia = new MenosPendencias();
		}
		if("MAIOR_RISCO".equals(estrategia)) {
			this.estrategia = new MaiorRisco();
		}
		 
		if ("MAIOR_DURACAO".equals(estrategia)) {
			this.estrategia = new MaiorDuracao();
		}
		if ("MAIS_ANTIGA".equals(estrategia)) {
			this.estrategia = new MaisAntiga();
		}
	}
	
	/**Método para indicar qual a proxima atividade a ser executada.
	 * @param codigoPesquisa - codigo da pesquisa.
	 * @return a proxima atividade.
	 */
	public String proximaAtividade(String codigoPesquisa) {
		ValidadorEntradas.validarString(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		validaPesquisaExiste(codigoPesquisa);
		validaPesquisaAtiva(codigoPesquisa);
		validaPesquisaSemPendencias(codigoPesquisa);
		return this.estrategia.proximaAtividade(pesquisasCadastradas.get(codigoPesquisa).getAtividades());
		
	}
	

	public void gravarResumo(String codigoPesquisa) throws IOException {
		ValidadorEntradas.validarString(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		validaPesquisaExiste(codigoPesquisa);
		validaPesquisaAtiva(codigoPesquisa);
		pesquisasCadastradas.get(codigoPesquisa).gravarResumo();
	
	}

	/**Método que verifica se a pesquisa não contem nenhuma atividade com item pendente
	 * @param codigoPesquisa - código da pesquisa a ser validada.
	 */
	private void validaPesquisaSemPendencias(String codigoPesquisa) {
		List<Atividade> atividades = pesquisasCadastradas.get(codigoPesquisa).getAtividades();
		for (Atividade atividade:atividades) {
			if (atividade.contaItensPendentes() != 0) {
				return;
			}
		} throw new IllegalArgumentException("Pesquisa sem atividades com pendencias.");
	}
	
}
