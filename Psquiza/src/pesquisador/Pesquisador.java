package pesquisador;

import validadores.ValidadorEntradas;

/**
 * Classe que modela um pesquisador. O pesquisador é a pessoa que aplica uma 
 * método estruturado na resolução de um problema de grande relevancia, e que
 * desenvolve um conhecimento aplicável a outras instâncias do mesmo problema.
 * @author Emilly de Albuquerque Oliveira - 119111162
 *
 */
public class Pesquisador {

	/**
	 * Atributo com o nome do pesquisador
	 */
	private String nome;
	/**
	 * Atributo que contem a função do pesquisador. Seja ela: estudante, professor ou externo.
	 */
	private String funcao;
	/**
	 * Guarda a especialidade do professor caso esta seja cadastrada, caso não seja é setado como null
	 */
	private Especialidade especialidade;
	/**
	 * Atributo que tem a biografia do pesquisador.
	 */
	private String biografia;
	/**
	 * Atributo com o email do pesquisador. Também ultilizado como identificador único do mesmo.
	 */
	private String email;
	/**
	 * Atributo que contem a url para a foto do pesquisador.
	 */
	private String fotoURL;
	/**
	 * Atributo que guarda o status (ativo ou inativo) do pesquisador.
	 */
	private boolean status;

	/** Construtor que cria o objeto do tipo Pesquisador e inicializa seus atributos
	 * @param nome - nome do pesquisador
	 * @param funcao - função do pesquisador
	 * @param biografia - biografia do pesquisador
	 * @param email - email e identificador unico do pesquisador.
	 * @param fotoURL - url para a foto do pesquisador.
	 */
	public Pesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		ValidadorEntradas.validarString(nome, "Campo nome nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		ValidadorEntradas.validarString(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(email, "Formato de email invalido.");
		ValidadorEntradas.validaFoto(fotoURL, "Formato de foto invalido.");
		
		this.nome = nome;
		this.funcao = funcao;
		this.especialidade = null;
		this.biografia = biografia;
		this.email = email;
		this.fotoURL = fotoURL;
		this.status = true;

		}

	/**
	 * Método para comparar dois objetos do tipo Pesquisador pelo codigo.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	/**
	 * Método para comparar dois objetos do tipo Pesquisador pelo email.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisador other = (Pesquisador) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	/**
	 * Método com a representação textual de Pesquisador: 
	 * Nome ( função ) - biografia - email - url.
	 */
	@Override
	public String toString() {
		return nome + " (" + funcao + ") - " + biografia + " - " + email + " - " + fotoURL;
	}

	/** Método para alterar o nome do pesquisador
	 * @param novoValor - novo nome do pesquisador
	 */
	public void setNome(String novoValor) {
		ValidadorEntradas.validarString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
		this.nome = novoValor;
		
	}

	/** Método para alterar a função do pesquisador(estudante, professor, externo)
	 * @param novoValor - a nova função do pesquisador
	 */
	public void setFuncao(String novoValor) {
		ValidadorEntradas.validarString(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
		this.funcao = novoValor;
		
	}

	/** Método para alterar a biografia do pesquisador
	 * @param novoValor - nova biografia
	 */
	public void setBiografia(String novoValor) {
		ValidadorEntradas.validarString(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
		this.biografia = novoValor;
	}

	/** Método para alterar o email
	 * @param novoValor - novo endereço de email do pesquisador
	 */
	public void setEmail(String novoValor) {
		ValidadorEntradas.validarString(novoValor, "Campo email nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaEmail(novoValor, "Formato de email invalido.");
		this.email = novoValor;
	}

	/**Método para alterar a url da foto do pesquisador
	 * @param novoValor - nova url
	 */
	public void setFoto(String novoValor) {
		ValidadorEntradas.validarString(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
		ValidadorEntradas.validaFoto(novoValor, "Formato de foto invalido.");
		this.fotoURL = novoValor;
	}

	/** Método para poder acessar o status do pesquisador, sem poder altera-lo
	 * @return o status (true ou false)
	 */
	public boolean getStatus() {
		return this.status;
	}

	/**
	 * Método que desativa um pesquisador, o Objeto
	 * não poderá ser exibido ou alterado.
	 */
	public void desativa() {
		if (!status) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		this.status = false;
	}
	
	/**
	 * Método que ativa ou reativa um pesquisador,
	 * alterando seu status para true.
	 */
	public void ativa() {
		if(status) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}
		this.status = true;
	}

	/**
	 * Cadastra os atributos especiais de um professor
	 * 
	 * @param formacao o grau de formação do professor
	 * @param unidade a unidade academica do professor
	 * @param data a data de contratação do professor
	 */
	public void setEspecialidadeProfessor(String formacao, String unidade, String data) {
		if(this.funcao.equalsIgnoreCase("PROFESSOR")) {
			this.especialidade = new Professor(formacao, unidade, data);
		} else {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}
		
	}

	/**
	 * Cadastra os atributos especiais de um aluno
	 * 
	 * @param semestre o semestre do aluno
	 * @param iea o indice de eficiencia academica do aluno
	 */
	public void setEspecialidadeAluno(int semestre, double iea) {
		if(this.funcao.equalsIgnoreCase("ESTUDANTE")) {
			this.especialidade = new Aluno(semestre, iea);
		} else {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}
	}
}
