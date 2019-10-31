package pesquisador;

import java.util.HashMap;
import java.util.Map;

import validadores.ValidadorEntradas;

/**
 * Classe controller responsável pelo gerenciamento da classe Pesquisador.
 * Armazena todos os pesquisadores cadastrados no sistema e realiza operações
 * de exibir, ativar, desativar e alterar sobre eles, além do cadastramento.
 * @author Emilly de Albuquerque Oliveira - 119111162
 *
 */
public class PesquisadorController {
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

		pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, fotoURL));
		
	}

	/** Método para alterar algum atributo de pesquisador,
	 * seja ele: nome, email, função, url ou biografia.
	 * @param email
	 * @param atributo
	 * @param novoValor
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		ValidadorEntradas.validarString(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(atributo, "Atributo nao pode ser vazio ou nulo.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");

		ValidadorEntradas.validaAtributosPesquisador(atributo, "Atributo invalido.");
		
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
		if(pesquisadores.get(email).getStatus() == false) {
			throw new IllegalArgumentException("Pesquisador inativo");
		}
		
		switch (atributo) {
		case "NOME":
			pesquisadores.get(email).setNome(novoValor);
			break;
		case "FUNCAO":
			pesquisadores.get(email).setFuncao(novoValor);
			break;
		case "BIOGRAFIA":
			pesquisadores.get(email).setBiografia(novoValor);
			break;
		case "EMAIL":
			pesquisadores.get(email).setEmail(novoValor);
			pesquisadores.put(novoValor, pesquisadores.get(email));
			pesquisadores.remove(email);
			break;
		case "FOTO":
			pesquisadores.get(email).setFoto(novoValor);
			break;

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
			throw new IllegalArgumentException("Pesquisador inativo");
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
			throw new IllegalArgumentException("Pesquisador inativo");
		}
		
		return pesquisadores.get(email).toString();
		
	}

	public boolean ehAtivo(String email) {
		ValidadorEntradas.validarString(email, "Email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
		if (pesquisadores.get(email).getStatus() == false) {
			return false;
		}
		return true;
	}
}
