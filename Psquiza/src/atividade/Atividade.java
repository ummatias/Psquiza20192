package atividade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import validadores.ValidadorEntradas;

/**
 * Classe que modela uma atividade para atingir um objetivo da pesquisa. Uma
 * atividade contém uma descrição, um risco associado e uma descrição para esse
 * risco.
 * 
 * @author Mateus Matias Ribeiro - 119111153
 * @author Emilly de Albuquerque Oliveira - 119111162
 * @author José Igor de Farias Gomes -119110692
 * @author Natalia Salvino André - 119110051
 */

public class Atividade implements Comparable<Atividade>, Serializable {

	/**
	 * numero que identifica que versao da classe foi usada na serializacao
	 */
	private static final long serialVersionUID = 5370296329294947316L;

	/**
	 * String referente a descrição da atividade.
	 */
	private String desc;

	/**
	 * String referente ao nível de risco da atividade.
	 */
	private String nvlRisco;

	/**
	 * String referente a descrição desse risco.
	 */
	private String descRisco;

	/**
	 * String referente ao codigo da atividade.
	 */
	private String code;

	/**
	 * Set que contém os itens da atividade.
	 */
	private List<Item> itens;

	/**
	 * Duração total da atividade
	 */
	private int duracao;

	/**
	 * Sugestão de próxima atividade a ser realizada.
	 */
	private Atividade proximaAtividade;

	/**
	 * Resultados da atividade, identificados pela sua ordem no cadastro.
	 */
	private Map<Integer, String> resultados;

	/**
	 * Constrói uma atividade a partir de seu codigo, descrição nível de risco e
	 * descrição desse risco, e inicializa seus atributos.
	 * 
	 * @param code      String contendo o código da atividade.
	 * @param desc      String contendo a descrição da atividade.
	 * @param nvlRisco  String contendo o nível de risco da atividade.
	 * @param descRisco String contendo a descrição do risco da atividade.
	 */
	public Atividade(String code, String desc, String nvlRisco, String descRisco) {
		ValidadorEntradas.validarString(code, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(desc, "Campo Descricao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(nvlRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(descRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");

		this.desc = desc;
		this.nvlRisco = nvlRisco;
		this.descRisco = descRisco;
		this.itens = new ArrayList<Item>();
		this.duracao = 0;
		this.resultados = new HashMap<>();
		this.code = code;
		this.proximaAtividade = null;
	}

	/**
	 * Adiciona um item na atividade.
	 * 
	 * @param itemText String referente ao item.
	 */
	public void addItem(String itemText) {
		Item item = new Item(itemText);
		itens.add(item);
	}

	/**
	 * Conta quantidade de itens ainda pendente da atividade.
	 * 
	 * @return Inteiro referente a quantidade de itens pedentes.
	 */
	public int contaItensPendentes() {
		int cont;
		cont = 0;
		for (Item it : itens) {
			if (!it.getStatus()) {
				cont += 1;
			}
		}
		return cont;
	}

	/**
	 * Conta quantidade de itens realizados da atividade.
	 * 
	 * @return Inteiro referente a quantidade de itens realizados.
	 */
	public int contaItensRealizados() {
		int cont = 0;

		for (Item item : itens) {
			if (item.getStatus()) {
				cont += 1;
			}
		}
		return cont;
	}

	// Gera a representação em String de uma atividade
	@Override
	public String toString() {
		String retorno = desc + " (" + nvlRisco + " - " + descRisco + ")";
		for (Item it : itens) {
			retorno += " | " + it.toString();
		}
		retorno = retorno.substring(0, retorno.length());
		return retorno;
	}

	// Gera um inteiro que serve como identificador único para a atividade.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	/**
	 * Compara duas atividades e retorna true se forem iguais e False se forem
	 * difrentes, utiliza o código da atividade como critério.
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	/**
	 * Método que retorna a duração da ativida.
	 * 
	 * @return duração da atividade inteira.
	 */
	public int getDuracao() {
		return duracao;
	}

	/**
	 * Método para executar a atividade.
	 * 
	 * @param item  - item usado para realizar a atividade, será alterado o status
	 *              como executado;
	 * @param horas - horas gastas com a atividade
	 */
	public void executaAtividade(int item, int horas) {
		validaItemExiste(itens, item);

		if (itens.get(item - 1).getStatus()) {
			throw new IllegalArgumentException("Item ja executado.");
		}

		this.duracao += horas;
		itens.get(item - 1).setStatus(true);
	}

	/**
	 * Valida se item existe
	 * 
	 * @param itens - todos os itens da atividade
	 * @param item  - item a ser validado
	 */
	private void validaItemExiste(List<Item> itens, int item) {
		if (itens.size() < item) {
			throw new IllegalArgumentException("Item nao encontrado.");
		}
	}

	/**
	 * Método para adicionar resultados a atividade.
	 * 
	 * @param resultado - resultado a ser cadastrado
	 * @return código do resultado
	 */
	public int addResultados(String resultado) {
		resultados.put(resultados.size() + 1, resultado);
		return resultados.size();
	}

	/**
	 * Método que remove um resultado da atividade.
	 * 
	 * @param numeroResultado - numero do indice do resultado
	 * @return true se o resultado foi removido com sucesso.
	 */
	public boolean removeResultado(int numeroResultado) {
		validaResultadoExiste(resultados, numeroResultado);

		if (resultados.get(numeroResultado) == null) {
			return false;
		}
		resultados.remove(numeroResultado);
		if (resultados.get(numeroResultado) == null) {
			return true;
		}
		return false;
	}

	/**
	 * Método que valida se determinado resultado existe na atividade
	 * 
	 * @param resultados      - todos os resultados da atividade
	 * @param numeroResultado - codigo do resultado a ser validado.
	 */
	private void validaResultadoExiste(Map<Integer, String> resultados, int numeroResultado) {
		if (numeroResultado > resultados.size()) {
			throw new IllegalArgumentException("Resultado nao encontrado.");
		}
	}

	/**
	 * Método que retorna a lista com os resultados
	 * 
	 * @return representação textual com todos os resultados dessa atividade
	 */
	public String listaResultados() {
		String retorno = "";
		for (String resultado : resultados.values()) {
			retorno += resultado + " | ";
		}
		return retorno.substring(0, retorno.length() - 3);
	}

	/**
	 * Método que retorna o codigo da atividade.
	 * 
	 * @return o codigo.
	 */
	public String getCodigo() {
		return this.code;
	}

	/*
	 * Método que indica como a atividade será comparada
	 */
	@Override
	public int compareTo(Atividade o) {
		return (this.getCodigo().compareTo(o.getCodigo())) * -1;
	}

	/**
	 * Método que procura um termo especifico no objeto atividade
	 * 
	 * @param termo - termo sendo pesquisado
	 * @return o codigo da atividade e a frase onde o termo se encontra.
	 */
	public String buscaTermo(String termo) {
		String saida = "";

		if (this.desc.toLowerCase().contains(termo.toLowerCase())) {
			saida += this.code + ": " + this.desc + " | ";
		}
		if (this.descRisco.toLowerCase().contains(termo.toLowerCase())) {
			saida += this.code + ": " + this.descRisco + " | ";
		}
		if (saida.length() > 0) {
			return saida.substring(0, saida.length() - 3);
		}
		return saida;
	}

	/**
	 * Define a atividade cuja execução sugere-se que seja após a execução desta
	 * atividade.
	 * 
	 * @param atividadeSubsequente a atividade a ser definida como proxima
	 */
	public void defineProximaAtividade(Atividade atividadeSubsequente) {
		if (this.proximaAtividade != null) {
			throw new IllegalArgumentException("Atividade ja possui uma subsequente.");
		} else if (atividadeSubsequente.fazLoopCom(this)) {
			throw new IllegalArgumentException("Criacao de loops negada.");
		} else {
			this.proximaAtividade = atividadeSubsequente;
		}
	}

	/**
	 * Metodo que verifica se uma atividade está ligada a outra direta ou
	 * indiretamente.
	 * 
	 * @param atividade a atividade que deseja-se verificar se está ligada a outra
	 *                  direta ou indiretamente
	 * @return true se estiver ligada, false caso contrário
	 */
	private boolean fazLoopCom(Atividade atividade) {
		if (this.equals(atividade)) {
			return true;
		}
		if (this.proximaAtividade == null) {
			return false;
		}

		return this.proximaAtividade.fazLoopCom(atividade);

	}

	/**
	 * Remove a referência à próxima atividade
	 */
	public void tiraProximaAtividade() {
		this.proximaAtividade = null;

	}

	/**
	 * Conta quantas atividades estão encadeadas após esta
	 * 
	 * @return a quantidade de atividades encadeadas
	 */
	public int contaProximos() {
		if (this.proximaAtividade == null) {
			return 0;
		}

		return 1 + this.proximaAtividade.contaProximos();
	}

	/**
	 * De forma recursiva encontra a enesima atividade de uma cadeia
	 * 
	 * @param enesimaAtividade a distancia da atividade atual até a atividade
	 *                         desejada
	 * @return o id da atividade desejada
	 */
	public String pegaProximo(int enesimaAtividade) {
		if (enesimaAtividade == 0) {
			return this.code;
		}
		if (this.proximaAtividade == null) {
			throw new IllegalArgumentException("Atividade inexistente.");
		}

		return this.proximaAtividade.pegaProximo(enesimaAtividade - 1);
	}

	/**
	 * Pega a atividade de maior risco em uma cadeia. O critério de desempate é a
	 * última a ser adicionada na cadeia.
	 * 
	 * @return o id da atividade de maior risco na cadeia.
	 */
	public String pegaMaiorRiscoAtividades() {
		if (this.proximaAtividade == null) {
			throw new IllegalArgumentException("Nao existe proxima atividade.");
		}
		return this.proximaAtividade.pegaMaiorRiscoAtividades(this.proximaAtividade.nvlRisco,
				this.proximaAtividade.code);
	}

	/**
	 * Pega a atividade de maior risco em uma cadeia de forma recursiva. O critério
	 * de desempate é a última a ser adicionada na cadeia.
	 * 
	 * @param nivelRiscoMaior o maior risco encontrado até agora.
	 * @param codeMaiorRisco  o codigo da atividade de maior risco até o momento.
	 * @return o codigo da atividade de maior risco na cadeia.
	 */
	private String pegaMaiorRiscoAtividades(String nivelRiscoMaior, String codeMaiorRisco) {
		String codeMaior = codeMaiorRisco;
		String nivelMaior = nivelRiscoMaior;

		if (nivelRiscoMaior.equals("BAIXO")) {
			codeMaior = this.code;
			nivelMaior = this.nvlRisco;
		}

		if (nivelRiscoMaior.equals("MEDIO")) {
			if (this.nvlRisco.equals("MEDIO") || this.nvlRisco.equals("ALTO")) {
				codeMaior = this.code;
				nivelMaior = this.nvlRisco;
			}

		}

		if (nivelRiscoMaior.equals("ALTO")) {
			if (this.nvlRisco.equals("ALTO")) {
				codeMaior = this.code;
				nivelMaior = this.nvlRisco;
			}

		}

		if (this.proximaAtividade == null) {
			return codeMaior;
		}

		return this.proximaAtividade.pegaMaiorRiscoAtividades(nivelMaior, codeMaior);

	}

	/**
	 * Cria uma representação em String de uma atividade de forma resumida
	 * 
	 * @return String contendo o resumo da atividade
	 */
	public String resumeAtividade() {
		String saida = "";
		String estado = "";
		saida += "        - " + this.desc + " (" + this.nvlRisco + " - " + descRisco + ")" + System.lineSeparator();
		for (Item it : itens) {
			if (it.getStatus()) {
				estado = "REALIZADO";
			} else {
				estado = "PENDENTE";
			}
			saida += "            - " + estado + " - " + "ITEM" + (itens.indexOf(it) + 1) + System.lineSeparator();
		}
		return saida;
	}

	/**
	 * Cria uma representação em String de uma atividade de forma resumida formatada
	 * para gravação dos resultados
	 * 
	 * @return String contendo o resumo da atividade
	 */
	public String resumeAtividadeParaResultado() {
		String saida = "";
		int duracao = 1;
		if (contaItensRealizados() != 0) {
			duracao = this.duracao / this.contaItensRealizados();
		}
		saida += "        - " + this.desc + System.lineSeparator();

		for (Item it : itens) {
			if (it.getStatus()) {
				saida += "            - " + "ITEM" + (itens.indexOf(it) + 1) + " - " + duracao + System.lineSeparator();
			}
		}
		for (String result : resultados.values()) {
			saida += "            - " + result + System.lineSeparator();
		}
		return saida;
	}

	/**
	 * Metodo que retorna o risco da atividade.
	 * 
	 * @return o nivel do risco da atividade.
	 */
	public String getRisco() {
		return this.nvlRisco;
	}
}
