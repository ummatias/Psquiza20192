package pesquisa;


import java.io.Serializable;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import atividade.Atividade;
import pesquisador.Pesquisador;
import problema.Objetivo;
import problema.Problema;
import validadores.ValidadorEntradas;

/**
 * Classe que representa a pesquisa, contendo uma descricao, campo de interesse
 * um codigo unico e um status
 * 
 * @author José Igor de Farias Gomes -119110692
 * @author Emilly de Albuquerque Oliveira - 119111162
 * @author Natalia Salvino André - 119110051
 * @author Mateus Matias Ribeiro - 119111153
 *
 */
public class Pesquisa implements Comparable<Pesquisa>, Serializable{
	/**
	 * Versão serial padrão da pesquisa
	 */
	private static final long serialVersionUID = 8009147095361493964L;
	/**
	 * Descricao da pesquisa
	 */
	private String descricao;
	/**
	 * Campo de interesse da pesquisa
	 */
	private String campoDeInteresse;
	/**
	 * O codigo que identifica a pesquisa
	 */
	private String codigo;
	/**
	 * O status da pesquisa, que pode ser ativo ou inativo
	 */
	private boolean status;
	
	/**
	 * Atividades associadas a pesquisa.
	 */
	private List<Atividade> atividades;

	/**
	 * O problema que é abordado na pesquisa.
	 */
	private Problema problema;

	/**
	 * Objetivos da pesquisa.
	 */
	private Map<String, Objetivo> objetivos;

	/**
	 * Pesquisadores desenvolvendo a pesquisa.
	 */
	private Map<String, Pesquisador> pesquisadores;

	/**
	 * Contrutor da pesquisa
	 * 
	 * @param codigo           o codigo da pesquisa
	 * @param descricao        a descricao da pesquisa
	 * @param campoDeInteresse
	 */
	public Pesquisa(String codigo, String descricao, String campoDeInteresse) {
		ValidadorEntradas.validarString(descricao, "Descricao nao pode ser nula ou vazia.");
		ValidadorEntradas.validarString(campoDeInteresse, "Formato do campo de interesse invalido.");

		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.codigo = codigo;
		this.status = true;
		this.atividades = new ArrayList<>();
		this.problema = null;
		this.objetivos = new HashMap<>();
		this.pesquisadores = new LinkedHashMap<String, Pesquisador>();
	}

	/**
	 * Altera a descricao da pesquisa
	 * 
	 * @param descricao a nova descricao da pesquisa
	 */
	public void setDescricao(String descricao) {
		ValidadorEntradas.validarString(descricao, "Descricao nao pode ser nula ou vazia.");

		this.descricao = descricao;
	}

	/**
	 * Altera o campo de interesse
	 * 
	 * @param campoDeInteresse o novo campo de interesse
	 */
	public void setCampoDeInteresse(String campoDeInteresse) {
		ValidadorEntradas.validarString(campoDeInteresse, "Formato do campo de interesse invalido.");

		this.campoDeInteresse = campoDeInteresse;
	}

	/**
	 * Ativa uma pesquisa desativada
	 */
	public void ativaPesquisa() {
		this.status = true;
	}

	/**
	 * verifica se uma pesquisa e ativa ou nao
	 * 
	 * @return Retorna o boolean se a pesquisa e ativa ou nao
	 */
	public boolean ehAtiva() {
		return this.status;
	}

	/**
	 * Desativa uma pesquisa
	 */
	public void desativaPesquisa() {
		this.status = false;
	}

	/**
	 * retorna o codigo da pesquisa
	 * 
	 * @param
	 * @returno codigo da pesquisa
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Altera a descricao ou o campo de interesse da pesquisa
	 * 
	 * @param atributo  o atributo que sera modificado
	 * @param novoValor o novo valor
	 */
	public void alteraPesquisa(String atributo, String novoValor) {
		if (atributo.equals("DESCRICAO")) {
			this.setDescricao(novoValor);
		} else if (atributo.equals("CAMPO")) {
			this.setCampoDeInteresse(novoValor);
		} else {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
	}

	/**
	 * encerra uma pesquisa
	 */
	public void encerraPesquisa() {
		this.status = false;
	}

	/**
	 * cria a representacao textual da pesquisa
	 */
	@Override
	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
	}

	/**
	 * cria a representacao numerica da pesquisa
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * verifica se pesquisas sao iguais
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisa other = (Pesquisa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Método que associa uma Atividade a pesquisa.
	 * 
	 * @param atividade a ser associdada
	 */
	public boolean associaAtividade(Atividade atividade) {
		if (!this.atividades.contains(atividade)) {
			this.atividades.add(atividade);
			return true;
		}
		return false;
	}

	/**
	 * Método que desassocia a atividade a pesquisa.
	 * 
	 * @return true se conseguir remover com sucesso, false se não conseguir
	 */
	public boolean desassociaAtividade(Atividade atividade) {

		if (this.atividades.contains(atividade)) {
			this.atividades.remove(atividade);
			return true;
		} 	return false;}
	
	/** Método que retorna uma atividade especifica da pesquisa
	 * @param codigo - codigo da pesquisa
	 * @return a atividade.
	 */
	public Atividade getAtividade(String codigo) {
		for (Atividade atividade: atividades) {
			if (codigo.equals(atividade.getCodigo())) {
				return atividade;
			}
		}
		
		throw new IllegalArgumentException("Atividade sem associacoes com pesquisas.");
	}

	
	/**
	 * Associa um problma a uma pesquisa e retorna um valor booleano dizendo se a
	 * associação foi bem sucedida (true) ou se o problema já estava associado (false)
	 * 
	 * @param problema Problema a ser associado a pesquisa
	 * @return valor boolean contendo o resultado da operação
	 */
	public boolean associaProblema(Problema problema) {
		if(this.problema != null && !this.problema.equals(problema)) {
			throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
		}
		
		if (this.problema == null) {
			this.problema = problema;
			return true;
		}
		return false;
	}

	/**
	 * Desassocia o problema que estiver ligado a pesquisa
	 * 
	 * @return Valor booleano contendo o resultado da operação
	 */
	public boolean desassociaProblema() {
		if (this.problema != null) {
			this.problema = null;
			return true;
		}
		return false;
	}
	
	/**
	 * Associa um objetivo a uma pesquisa e retorna um valor booleano dizendo se a
	 * associação foi bem sucedida (true) ou se o problema já estava associado (false)
	 * 
	 * @param objetivo Objetivo a ser associado a pesquisa
	 * @return valor boolean contendo o resultado da operação
	 */
	public boolean associaObjetivo(Objetivo objetivo) {
		if(!objetivo.getStatus()) {
			objetivo.setStatus(true);
			objetivos.put(objetivo.getCodigo(), objetivo);
			return true;
		}else if(objetivos.containsKey(objetivo.getCodigo())) {
			return false;
		}
		throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
	}

	/**
	 * Desassocia um objetivo que estiver cadastrado na pesquisa através de seu ID
	 * 
	 * @param idObjetivo ID do objetivo a ser desassociado
	 * @return Valor booleano contendo o resultado da operação
	 */
	public boolean desassociaObjetivo(String idObjetivo) {
		if (objetivos.containsKey(idObjetivo)) {
			objetivos.get(idObjetivo).desativa();
			
			objetivos.remove(idObjetivo);
			return true;
		}
		return false;
	}

	/** Método que retorna o problema da pesquisa
	 * @return o problema da pesquisa.
	 */
	public Problema getProblema() {
		return this.problema;
	}
	
	/**
	 * Retorna um inteiro que representa a quantidade de objetivos
	 * cadastrados em uma pesquisa
	 * 
	 * @return Inteiro contendo a quantidade de objetivos da pesquisa
	 */
	public int quntObjetivos() {
		return this.objetivos.size();
	}
	
	/**
	 * Gera uma lista de objetivos e retorna o mais recente 
	 * 
	 * @return ultimo objetivo cadastrado na pesquisa
	 */
	public Objetivo ultimoObjetivo() {
		ArrayList<Objetivo> objetivosOrdenados = new ArrayList<Objetivo>();
		objetivosOrdenados.addAll(objetivos.values());
		return objetivosOrdenados.get(objetivosOrdenados.size());
	}


	/**
	 * Adiciona um pesquisador ao mapa de pesquisadores associados à Pesquisa.
	 * 
	 * @param pesquisador o pesquisador a ser adiconado
	 * @return true - caso o pesquisador seja adicionado com sucesso, false - caso o
	 *         pesquisador já exista no mapa.
	 */
	public boolean associaPesquisador(Pesquisador pesquisador) {
		if (this.status) {
			String email = pesquisador.getEmail();

			if (pesquisadores.containsKey(email)) {
				return false;
			}
			pesquisadores.put(email, pesquisador);
			return true;
		} else {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

	}

	/**
	 * Remove um pesquisador do mapa de pesquisadores.
	 * 
	 * @param emailPesquisador o email do pesquisador
	 * @return true - se a desassociação ocorrer com sucesso, false - caso o
	 *         pesquisador não esteja no mapa de pesquisadores.
	 */
	public boolean desassociaPesquisador(String emailPesquisador) {
		if (this.status) {
			if (!pesquisadores.containsKey(emailPesquisador)) {
				return false;
			}

			pesquisadores.remove(emailPesquisador);
			return true;
		} else {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}

	/**
	 * Método que define como a pesquisa será comparada.
	 */
	@Override
	public int compareTo(Pesquisa o) {
		return (this.getCodigo().compareTo(o.getCodigo())) * -1;
	}

	/** Método que busca determinado termo dentro da pesquisa
	 * @param termo - termo a ser procurado
	 * @return o codigo da pesquisa e onde o termo está presente
	 */
	public String buscaTermo(String termo) {
		String saida = "";
				
		if(this.descricao.toLowerCase().contains(termo.toLowerCase())) {
			saida += this.codigo + ": " + this.descricao + " | ";
		}
		if(this.campoDeInteresse.toLowerCase().contains(termo.toLowerCase())) {
			saida += this.codigo + ": " + this.campoDeInteresse + " | ";
		}
		if(saida.length() > 0) {
			saida = saida.substring(0, saida.length() - 3);
		}
		
		return saida;

	}

	/** Método que retorna as atividades associadas a essa pesquisa
	 * @return as atividades associadas a essa pesquisa.
	 */
	public List<Atividade> getAtividades() {
		return atividades;
	}
	
	/**
	 * Grava em um arquivo de texto um resumo da pesquisa
	 * 
	 * @throws IOException
	 */
	public void gravarResumo() throws IOException {
		FileWriter resumo = new FileWriter("./" + this.codigo+ ".txt");
		PrintWriter grava = new PrintWriter(resumo);
		String resumoAtividades = listaAtividades();
		
		grava.println("\"" + "- Pesquisa: " + this.toString() );
		
		grava.print("    - Pesquisadores:" + System.lineSeparator() + 
				 listaPesquisadores());
		
		grava.print("    - Problema: " + System.lineSeparator() + 
				"        - " + this.problema.toString() + System.lineSeparator());
		
		grava.print("    - Objetivos: " + System.lineSeparator() +
				listaObjetivos());

		grava.print("    - Atividades: " + System.lineSeparator() +
				resumoAtividades.substring(0, resumoAtividades.length() - 1) + "\"");
		
		resumo.close();
	}
	
	/**
	 * Grava em um arquivo de texto os resultados da pesquisa
	 * 
	 * @throws IOException
	 */
	public void	gravarResultados() throws IOException {
		FileWriter result = new FileWriter("./" + this.codigo+ "-Resultados.txt");
		PrintWriter grava = new PrintWriter(result);
		String resultados = listaAtividadeParaResultado();
		
		grava.println("\"" + "- Pesquisa: " + this.toString());
		grava.print("    - Resultados:" + System.lineSeparator() +
				resultados.substring(0, resultados.length() -1) + "\"" );
		grava.close();
	}
	
	/**
	 * Gera um representação em String dos pesquisadores formatada de modo
	 * a ser salvo no resumo
	 * 
	 * @return Representação em String dos pesquisadores
	 */
	private String listaPesquisadores() {
		String saida = "";
		for(Pesquisador pesq : pesquisadores.values()) {
			saida += "        - " + pesq.toString() + System.lineSeparator();
		}
		return saida;
	}
	
	/**
	 * Gera um representação em String dos objetivos formatada de modo
	 * a ser salvo no resumo
	 * 
	 * @return Representação em String dos objetivos
	 */
	private String listaObjetivos() {
		String saida = "";
		for(Objetivo obj : objetivos.values()) {
			saida += "        - " + obj.toString() + System.lineSeparator();
		}
		return saida;
	}
	
	/**
	 * Gera um representação em String das atividades formatada de modo
	 * a ser salvo no resumo
	 * 
	 * @return Representação em String das atividades
	 */
	private String listaAtividades() {
		String saida = "";
		for(Atividade atividade : atividades) {
			saida += atividade.resumeAtividade();
		}
		return saida;
	}
	
	/**
	 * Gera um representação em String das atividades formatada de modo
	 * a ser salvo nos resultados
	 * 
	 * @return Representação em String das atividades
	 */
	private String listaAtividadeParaResultado() {
		String saida = "";
		for(Atividade atividade : atividades) {
			saida += atividade.resumeAtividadeParaResultado();
		}
		return saida;
	}
	
}
