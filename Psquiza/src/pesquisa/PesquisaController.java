package pesquisa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import problema.Objetivo;
import problema.Problema;
import atividade.Atividade;
import pesquisador.Pesquisador;
import validadores.ValidadorEntradas;

/**
 * Classe que representa o controlador de pesquisas.
 * 
 * @author Natalia Salvino
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

	public PesquisaController() {
		this.pesquisasCadastradas = new HashMap<>();
		this.chavesGeradas = new HashMap<>();
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

	public boolean associaProblema(String idPesquisa, Problema problema) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		if (pesquisasCadastradas.get(idPesquisa).getProblema() != null) {
			throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
		}
		return pesquisasCadastradas.get(idPesquisa).associaProblema(problema);
	}

	public boolean desassociaProblema(String idPesquisa) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		return pesquisasCadastradas.get(idPesquisa).desassociaProblema();
	}

	public void associaObjetivo(String idPesquisa, Objetivo objetivo) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		pesquisasCadastradas.get(idPesquisa).associaObjetivo(objetivo);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");

		return pesquisasCadastradas.get(idPesquisa).desassociaObjetivo(idObjetivo);
	}

	/**
	 * Método que associa uma atividade a pesquisa.
	 * 
	 * @param codigoPesquisa - código da pesquisa
	 * @param atividade      - atividade a ser associada
	 * @return true se conseguir associar com sucesso. False se já tiver uma
	 *         atividade associada.
	 */
	public boolean associaAtividade(String codigoPesquisa, Atividade atividade) {
		ValidadorEntradas.validarString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		return pesquisasCadastradas.get(codigoPesquisa).associaAtividade(atividade);
	}

	/**
	 * Método que desassocia uma atividade a pesquisa.
	 * 
	 * @param codigoPesquisa - código da pesquisa
	 * @return true se for desassociada com sucesso, false se já não tiver uma
	 *         atividade.
	 */
	public boolean desassociaAtividade(String codigoPesquisa) {
		ValidadorEntradas.validarString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		return pesquisasCadastradas.get(codigoPesquisa).desassociaAtividade();

	}

	/**
	 * Associa um pesquisador a uma pesquisa desde que esta esteja ainda ativa.
	 * 
	 * @param idPesquisa  o id da pesquisa
	 * @param pesquisador o objeto pesquisador que se deseja associar
	 * @return true - se a associação aconteceu com sucesso, false - caso a
	 *         associação já exista
	 */
	public boolean associaPesquisador(String idPesquisa, Pesquisador pesquisador) {
		ValidadorEntradas.validarString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");

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
	 * @param idPesquisa o id da pesquisa
	 * @param emailPesquisador o email do pesquisador
	 * @return true - se a desassociação aconteceu com sucesso, false - caso a associação não exista
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
    
  public String buscaDescricaoCampoDeInteresse(String termo) {
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
}
