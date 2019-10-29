package controladores;

import java.util.HashMap;
import java.util.Map;

import validadores.ValidadorEntradas;
import entidades.Pesquisador;

public class PesquisadorController {
	private Map<String, Pesquisador> pesquisadores;
	
	public PesquisadorController() {
		pesquisadores = new HashMap<>();
	}

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		ValidadorEntradas.validarString(nome, "Campo nome nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		ValidadorEntradas.validaFoto(fotoURL,"Formato de foto invalido.");
		if(pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("");
		}
		pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, fotoURL));
		
	}

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
		
		switch (atributo.toLowerCase()) {
		case "nome":
			pesquisadores.get(email).setNome(novoValor);
			break;
		case "funcao":
			pesquisadores.get(email).setFuncao(novoValor);
			break;
		case "biografia":
			pesquisadores.get(email).setBiografia(novoValor);
			break;
		case "email":
			pesquisadores.get(email).setEmail(novoValor);
			pesquisadores.put(novoValor, pesquisadores.get(email));
			pesquisadores.remove(email);
			break;
		case "foto":
			pesquisadores.get(email).setFoto(novoValor);
			break;

		}
	}

	public void desativaPesquisador(String email) {
		ValidadorEntradas.validarString(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
		
		Pesquisador pesquisador = pesquisadores.get(email);
		if(pesquisador.getStatus() == true) {
			pesquisador.desativa();
		} 
			throw new IllegalArgumentException("Pesquisador inativo");
	}

	public void ativaPesquisador(String email) {
		ValidadorEntradas.validarString(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
		
		Pesquisador pesquisador = pesquisadores.get(email);
		if(pesquisador.getStatus() == false) {
			pesquisador.ativa();
		} 
			throw new IllegalArgumentException("Pesquisador ja ativado.");
	}

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
}
