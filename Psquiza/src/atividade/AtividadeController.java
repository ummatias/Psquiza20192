package atividade;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import validadores.ValidadorEntradas;

public class AtividadeController {
	
	private Map<String, Atividade> atividades;
	int contAtiv;
	
	public AtividadeController() {
		atividades = new HashMap<>();
		contAtiv = 1;
	}
	
	public String cadastraAtividade(String desc, String nvlRisco, String descRisco) {
		
		ValidadorEntradas.validarString(desc, "Campo Descricao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(nvlRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(descRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaValorRisco(nvlRisco);
		
		String code = generateCodeAtividade();
		Atividade atividade = new Atividade(code, desc, nvlRisco, descRisco);
		atividades.put(code, atividade);
		return code;
		
	}

	public void apagaAtividade(String codigo) {
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaAtividadeExiste(atividades, codigo);
		atividades.remove(codigo);
	}
	
	private String generateCodeAtividade() {
		String code = "A"+ contAtiv;
		contAtiv += 1;
		return code;
	}
	
	public void cadastraItem(String codigo, String item) {
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(item, "Item nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaAtividadeExiste(atividades, codigo);
		
		atividades.get(codigo).addItem(item);
	}
	
	public int contaItensPendentes(String codigo) {
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaAtividadeExiste(atividades, codigo);
		return atividades.get(codigo).contaItensPendentes();
	}
	
	public int contaItensRealizados(String codigo) {
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaAtividadeExiste(atividades, codigo);
		
		return atividades.get(codigo).contaItensRealizados();
	}
	
	public String exibeAtividade(String codigo) {
		ValidadorEntradas.validarString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaAtividadeExiste(atividades, codigo);
		
		return atividades.get(codigo).toString();
	}
}
