package pesquisa;

import atividade.Atividade;
import validadores.ValidadorEntradas;

/**
 * Classe que representa a pesquisa, contendo uma descricao, campo de interesse
 * um codigo unico e um status
 * 
 * @author Natalia salvino
 *
 */
public class Pesquisa {
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
	 * Atividade associada a pesquisa.
	 */
	private Atividade atividade;

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
		this.atividade = null;

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
	
	/** Método que associa uma Atividade a pesquisa.
	 * @param atividade a ser associdada
	 */
	public boolean associaAtividade(Atividade atividade) {
		if (atividade == null) {
			this.atividade = atividade;
			return true;
		} return false;
	}
	
	/**
	 * Método que desassocia a atividade a pesquisa.
	 * @return 
	 */
	public boolean desassociaAtividade() {
		if (atividade == null) {
			return false;}
		this.atividade = null;
		return true;
	}
	
	public Atividade getAtividade() {
		return atividade;
	}
}
