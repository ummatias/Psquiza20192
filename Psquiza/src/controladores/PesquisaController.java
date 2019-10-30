package controladores;

import java.util.HashMap;
import java.util.Map;

import entidades.Pesquisa;
import validadores.ValidadorEntradas;

public class PesquisaController {
	private Map<String, Pesquisa> pesquisasCadastradas;
	private Map<String, Integer> chavesGeradas;

	public PesquisaController() {
		this.pesquisasCadastradas = new HashMap<>();
		this.chavesGeradas = new HashMap<>();
	}

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		ValidadorEntradas.validarString(campoDeInteresse, "Formato do campo de interesse invalido.");
		ValidadorEntradas.validarString(descricao, "Descricao nao pode ser nula ou vazia.");

		String codigo = this.geraCodigo(campoDeInteresse);
		Pesquisa pesquisa = new Pesquisa(codigo, descricao, campoDeInteresse);
		this.pesquisasCadastradas.put(codigo, pesquisa);
		return this.pesquisasCadastradas.get(codigo).getCodigo(codigo);
	}

	private String geraCodigo(String campoDeInteresse) {
		ValidadorEntradas.validarString(campoDeInteresse, "Formato do campo de interesse invalido.");
		String codigo = campoDeInteresse.substring(0, 3).toUpperCase();

		if (!this.chavesGeradas.containsKey(codigo)) {
			this.chavesGeradas.put(codigo, 1);
			String codigoGerado = codigo + this.chavesGeradas.get(codigo);
			return codigoGerado;

		}
		String codigoGerado = codigo + (this.chavesGeradas.get(codigo) + 1);
		
		this.chavesGeradas.put(codigo,chavesGeradas.get(codigo) + 1);
		return codigoGerado;
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		ValidadorEntradas.validarString(conteudoASerAlterado, "Conteudo nao pode ser vazio ou nulo");
		ValidadorEntradas.validarString(codigo, "Codigo nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(novoConteudo, "Novo valor nao pode ser nulo ou vazio.");
		if (this.pesquisasCadastradas.containsKey(codigo)) {
			this.pesquisasCadastradas.get(codigo).alteraPesquisa(conteudoASerAlterado, novoConteudo);
		}
		throw new IllegalArgumentException("Pesquisa nao encontrada");
	}

	public void encerraPesquisa(String codigo, String motivo) {
		ValidadorEntradas.validarString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if (this.pesquisasCadastradas.containsKey(codigo)) {
			if(this.pesquisasCadastradas.get(codigo).ehAtiva() == true) {
				this.pesquisasCadastradas.get(codigo).encerraPesquisa();
			}else {
				throw new IllegalArgumentException("Pesquisa desativada.");
			}
		}else{
			throw new IllegalArgumentException("Pesquisa nao encontrada");
		}
		
	}

	public void ativaPesquisa(String codigo) {
		if (this.pesquisasCadastradas.containsKey(codigo)) {
			if(this.pesquisasCadastradas.get(codigo).ehAtiva() == false) {
				this.pesquisasCadastradas.get(codigo).ativaPesquisa();
			}else{
				throw new IllegalArgumentException("Pesquisa ja ativada.");
			}
		}else {
			throw new IllegalArgumentException("Pesquisa nao encontrada");
		}
	}

	public String exibePesquisa(String codigo) {
		ValidadorEntradas.validarString(codigo, "Codigo nao pode ser nulo ou vazio.");
		
		if (this.pesquisasCadastradas.containsKey(codigo)) {
			return this.pesquisasCadastradas.get(codigo).toString();
		}
		throw new IllegalArgumentException("Pesquisa nao encontrada");
	}

	public boolean pesquisaEhAtiva(String codigo) {
		
		ValidadorEntradas.validarString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if (this.pesquisasCadastradas.containsKey(codigo)) {
			return this.pesquisasCadastradas.get(codigo).ehAtiva();
		}
		throw new IllegalArgumentException("Pesquisa nao encontrada");
	}
}
