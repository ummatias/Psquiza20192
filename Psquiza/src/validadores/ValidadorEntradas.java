package validadores;

public class ValidadorEntradas {
	
	/**
	 * Verifica se uma string passada como parametro é válida e lança uma exceção caso esta seja nula.
	 * 
	 * @param entrada a string a ser verificada.
	 * @param msg a mensagem de erro a ser lançada na exceção.
	 */
	private static void validarStringNaoNula(String entrada, String msg) {
		if (entrada == null) {
			throw new NullPointerException(msg);
		}
	}
	
	/**
	 * Verifica se uma string passada como parametro é válida e lança uma exceção caso esta seja vazia.
	 * 
	 * @param entrada a string a ser verificada.
	 * @param msg a mensagem de erro a ser lançada na exceção.
	 */
	private static void validarStringNaoVazia(String entrada, String msg){
		if (entrada.trim().equals("")) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	/**
	 * Método chamado por outras classes para validar uma string como não vazia e não nula.
	 * 
	 * @param entrada a string a ser validada
	 * @param msg a mensagem de erro a ser exibida na exceção.
	 */
	public static void validarString(String entrada, String msg) {
		validarStringNaoNula(entrada, msg);
		validarStringNaoVazia(entrada, msg);
	}
	
	/**
	 * Método que valida a viabilidade de um Problema ou Objetivo.
	 * Verifica se o valor da viabilidade é um inteiro entre 1 e 5 e lança exceção caso não seja.
	 * 
	 * @param viabilidade a viabilidade a ser validada.
	 * @param msg a mensagem de erro a ser exibida na possível exceção.
	 */
	public static void validarViabilidade(int viabilidade, String msg) {
		if(viabilidade > 5 || viabilidade < 1) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * Método que valida a aderencia de um Objetivo.
	 * Verifica se o valor da aderencia é um inteiro entre 1 e 5 e lança uma exceção caso não seja.
	 * 
	 * @param aderencia a aderência a ser validada.
	 * @param msg a mensagem de erro a ser exibida na possível exceção.
	 */
	public static void validarAderencia(int aderencia, String msg) {
		if(aderencia > 5 || aderencia < 1) {
			throw new IllegalArgumentException(msg);
		}
		
	}

	/**
	 * Método que valida o tipo de um objetivo.
	 * Verifica se o tipo é uma String igual a 'GERAL' ou 'ESPECIFICO'
	 * 
	 * @param tipo o tipo do objetivo a ser validado
	 * @param msg a mensagem de erro a ser exibida na possível exceção.
	 */
	public static void validarTipo(String tipo, String msg) {
		if(!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) {
			throw new IllegalArgumentException(msg);
		}
		
	}

	/**Método que valida o email do pesquisador.
	 * Para ser valido deve ter um @, e uma letra ou numero antes e depois do @
	 * @param email - email do pesquisador a ser validado.
	 * @param msg - mensagem de erro a ser exibidada caso seja invalido
	 */
	public static void validaEmail(String email, String msg) {
		String[] parts = email.split("@");
		if (parts.length !=2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
			throw new IllegalArgumentException(msg);
		}
	}

	/** Método que valida a URL da foto.
	 * Para ser valida, deve começar com http:// ou https://
	 * @param fotoURL - URL da foto a ser validada
	 * @param msg - mensagem de erro a ser exibidada caso seja invalido
	 */
	public static void validaFoto(String fotoURL, String msg) {
		String[] parts = fotoURL.split("//");
		if (parts.length != 2 || !(parts[0].equals("http:")) || !(parts[0].equals("https:") || parts[1].trim().isEmpty())) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void validaAtributosPesquisador(String atributo, String msg) {
		if(!atributo.equals("NOME") || !atributo.equals("EMAIL") || !atributo.equals("FOTO") || !atributo.equals("BIOGRAFIA") || !atributo.equals("FUNCAO")) {
			throw new IllegalArgumentException(msg);
		}
	}

}
