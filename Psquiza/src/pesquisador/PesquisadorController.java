package pesquisador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import validadores.ValidadorEntradas;

/**
 * Classe controller responsável pelo gerenciamento da classe Pesquisador.
 * Armazena todos os pesquisadores cadastrados no sistema e realiza operações
 * de exibir, ativar, desativar e alterar sobre eles, além do cadastramento.
 * @author Emilly de Albuquerque Oliveira - 119111162
 *
 */
public class PesquisadorController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2358028280285631748L;
	/**
	 * Atributo mapa, que guarda todos os pesquisadores do sistema pelo email.
	 */
	private Map<String, Pesquisador> pesquisadores;
	
	/**
	 * Construtor que cria controller que guarda as operações
	 * com o objeto do tipo Pesquisador e inicializa atributos.
	 */
	public PesquisadorController() {
		pesquisadores = new HashMap<>();
	}

	/** Método para cadastrar um novo pesquisador ao sistema.
	 * @param nome - nome do pesquisador
	 * @param funcao - função do pesquisador (externo, estudante ou professor)
	 * @param biografia - biografia do pesquisador
	 * @param email - email e identificador único do pesquisador.
	 * @param fotoURL - url com a foto do pesquisador.
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		ValidadorEntradas.validarString(nome, "Campo nome nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		ValidadorEntradas.validaFoto(fotoURL, "Formato de foto invalido.");

		pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, fotoURL));
		
	}

	/** Método para alterar algum atributo de pesquisador,
	 * seja ele: nome, email, função, url ou biografia.
	 * @param email - email do pesquisador
	 * @param atributo - atributo que será alterado
	 * @param novoValor - valor que o atributo vai assumir.
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		ValidadorEntradas.validarString(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(atributo, "Atributo nao pode ser vazio ou nulo.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");

		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
		if(pesquisadores.get(email).getStatus() == false) {
			throw new IllegalArgumentException("Pesquisador inativo");
		}
		
		pesquisadores.get(email).alteraPesquisador(atributo, novoValor);
		
		if(atributo.equals("EMAIL")) {
			pesquisadores.put(novoValor, pesquisadores.get(email));
			pesquisadores.remove(email);
		}
	}

	/**Método para que o Pesquisador seja desativado,
	 * não pode ser alterado, desativado ou exibido.
	 * @param email - email e ID único do pesquisador
	 */
	public void desativaPesquisador(String email) {
		ValidadorEntradas.validarString(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
		
		Pesquisador pesquisador = pesquisadores.get(email);
		if(pesquisador.getStatus() == false) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		} pesquisador.desativa();
			
	}

	/** Método para que o Pesquisador seja ativado ou reativado.
	 * @param email - email e ID único do pesquisador
	 */
	public void ativaPesquisador(String email) {
		ValidadorEntradas.validarString(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
		if(pesquisadores.get(email).getStatus() == true) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}
		
		pesquisadores.get(email).ativa();
			
	}

	/** Método para exibir o pesquisador a partir do seu email.
	 * @param email - email do pesquisador
	 * @return a representação textual do pesquisador.
	 */
	public String exibePesquisador(String email) {
		ValidadorEntradas.validarString(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
		if (pesquisadores.get(email).getStatus() == false) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		
		return pesquisadores.get(email).toString();
		
	}

	/** Método que verifica se o pesquisador está ativo ou não
	 * @param email - email do pesquisador
	 * @return true se estiver ativo, false se não estiver.
	 */
	public boolean ehAtivo(String email) {
		ValidadorEntradas.validarString(email, "Email nao pode ser vazio ou nulo.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
		if (pesquisadores.get(email).getStatus() == false) {
			return false;
		}   return true;
	}

	public String buscaBiografia(String termo) {
		ValidadorEntradas.validarString(termo, "Campo termo nao pode ser nulo ou vazio.");
		
		List<Pesquisador> listPesquisador = new ArrayList<>(this.pesquisadores.values());
		Collections.sort(listPesquisador);
		String saida = "";
		for (Pesquisador pesquisador : listPesquisador) {
			String pesquisadorSaida = pesquisador.buscaTermo(termo);
			
			if (pesquisadorSaida.length() > 0) {
				saida += pesquisadorSaida + " | ";
				
			}
			
		}
		if (saida.length() > 0) {
			
			return saida.substring(0, saida.length() - 3);
		}
		
		return saida;
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
		ValidadorEntradas.validarString(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		ValidadorEntradas.validarString(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(data, "Campo data nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaFormatoData(data, "Atributo data com formato invalido.");
		
		if(pesquisadores.containsKey(email)) {
			Pesquisador pesquisador = pesquisadores.get(email);
			pesquisador.setEspecialidadeProfessor(formacao, unidade, data);
			
		} else {
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		}
		
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
		ValidadorEntradas.validarString(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		ValidadorEntradas.validaSemestreAluno(semestre, "Atributo semestre com formato invalido.");
		ValidadorEntradas.validaIEA(iea, "Atributo IEA com formato invalido.");
		
		if(pesquisadores.containsKey(email)) {
			Pesquisador pesquisador = pesquisadores.get(email);
			pesquisador.setEspecialidadeAluno(semestre, iea);
			
		} else {
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		}
	}

	/**
	 * Retorna um objeto do tipo Pesquisador para ser usado em outras entidades.
	 * 
	 * @param email o email do pesquisador
	 * @return o objeto Pesquisador desejado
	 */
	public Pesquisador getPesquisador(String email) {
		ValidadorEntradas.validarString(email, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		
		if(pesquisadores.containsKey(email)) {
			return pesquisadores.get(email);
			
		} else {
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		}
	}

	/**
	 * Lista os pesquisadores de um determinado tipo/funcao.
	 * 
	 * @param tipo o tipo do pesquisador
	 * @return a listagem dos pesquisadores que pertencem a uma função.
	 */
	public String listaPesquisadores(String tipo) {
		ValidadorEntradas.validarString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarFuncaoPesquisador(tipo, "Tipo " + tipo + " inexistente.");
		
		String retorno = "";
		for (Pesquisador pesquisador : pesquisadores.values()) {
			if(pesquisador.getFuncao().equalsIgnoreCase(tipo)) {
				retorno += pesquisador.toString() + " | ";
			}
			
		}
		if (retorno.length() > 0) {
			return retorno.substring(0, retorno.length() - 3);
		}
		
		return retorno;
	}
}
