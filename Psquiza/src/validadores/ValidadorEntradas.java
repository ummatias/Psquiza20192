package validadores;

import java.util.List;
import java.util.Map;

import atividade.Atividade;
import atividade.Item;
import problema.Objetivo;
import problema.Problema;

public class ValidadorEntradas {

	/**
	 * Verifica se uma string passada como parametro é válida e lança uma exceção
	 * caso esta seja nula.
	 * 
	 * @param entrada
	 *            a string a ser verificada.
	 * @param msg
	 *            a mensagem de erro a ser lançada na exceção.
	 */
	private static void validarStringNaoNula(String entrada, String msg) {
		if (entrada == null) {
			throw new NullPointerException(msg);
		}
	}

	/**
	 * Verifica se uma string passada como parametro é válida e lança uma exceção
	 * caso esta seja vazia.
	 * 
	 * @param entrada
	 *            a string a ser verificada.
	 * @param msg
	 *            a mensagem de erro a ser lançada na exceção.
	 */
	private static void validarStringNaoVazia(String entrada, String msg) {
		if (entrada.trim().equals("")) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * Método chamado por outras classes para validar uma string como não vazia e
	 * não nula.
	 * 
	 * @param entrada
	 *            a string a ser validada
	 * @param msg
	 *            a mensagem de erro a ser exibida na exceção.
	 */
	public static void validarString(String entrada, String msg) {
		validarStringNaoNula(entrada, msg);
		validarStringNaoVazia(entrada, msg);
	}

	/**
	 * Método que valida a viabilidade de um Problema ou Objetivo. Verifica se o
	 * valor da viabilidade é um inteiro entre 1 e 5 e lança exceção caso não seja.
	 * 
	 * @param viabilidade
	 *            a viabilidade a ser validada.
	 * @param msg
	 *            a mensagem de erro a ser exibida na possível exceção.
	 */
	public static void validarViabilidade(int viabilidade, String msg) {
		if (viabilidade > 5 || viabilidade < 1) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * Método que valida a aderencia de um Objetivo. Verifica se o valor da
	 * aderencia é um inteiro entre 1 e 5 e lança uma exceção caso não seja.
	 * 
	 * @param aderencia
	 *            a aderência a ser validada.
	 * @param msg
	 *            a mensagem de erro a ser exibida na possível exceção.
	 */
	public static void validarAderencia(int aderencia, String msg) {
		if (aderencia > 5 || aderencia < 1) {
			throw new IllegalArgumentException(msg);
		}

	}

	/**
	 * Método que valida o tipo de um objetivo. Verifica se o tipo é uma String
	 * igual a 'GERAL' ou 'ESPECIFICO'
	 * 
	 * @param tipo
	 *            o tipo do objetivo a ser validado
	 * @param msg
	 *            a mensagem de erro a ser exibida na possível exceção.
	 */
	public static void validarTipo(String tipo, String msg) {
		tipo = tipo.toUpperCase();

		if (!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * Valida o código de um problema para garantir que comece com P e que tenha
	 * após isso um número inteiro maior que zero
	 * 
	 * @param codigo
	 *            o codigo do problema
	 * @param msg
	 *            a possivel mensagem de erro
	 */
	public static void validarCodigoProblema(String codigo, String msg) {
		if (!codigo.substring(0, 1).equals("P")) {
			throw new IllegalArgumentException(msg);
		}

		int id = Integer.parseInt(codigo.substring(1));
		if (id <= 0) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * Valida o código de um objetivo para garantir que comece com O e que tenha
	 * após isso um número inteiro maior que zero
	 * 
	 * @param codigo
	 *            o codigo do objetivo
	 * @param msg
	 *            a possivel mensagem de erro
	 */
	public static void validarCodigoObjetivo(String codigo, String msg) {
		if (!codigo.substring(0, 1).equals("O")) {
			throw new IllegalArgumentException(msg);
		}

		int id = Integer.parseInt(codigo.substring(1));
		if (id <= 0) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * ======= if (!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) { throw new
	 * IllegalArgumentException(msg); }
	 * 
	 * }
	 * 
	 * /** Método que valida o email do pesquisador. Para ser valido deve ter um @,
	 * e uma letra ou numero antes e depois do @
	 * 
	 * @param email
	 *            - email do pesquisador a ser validado.
	 * @param msg
	 *            - mensagem de erro a ser exibidada caso seja invalido
	 */
	public static void validaEmail(String email, String msg) {

		if (email.startsWith("@") || email.endsWith("@") || email.indexOf("@") == -1) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * Método que valida a URL da foto. Para ser valida, deve começar com http:// ou
	 * https://
	 * 
	 * @param fotoURL
	 *            - URL da foto a ser validada
	 * @param msg
	 *            - mensagem de erro a ser exibidada caso seja invalido
	 */
	public static void validaFoto(String fotoURL, String msg) {
		if (fotoURL.length() < 7) {
			throw new IllegalArgumentException(msg);
		}

		if (fotoURL.length() >= 7 && !(fotoURL.substring(0, 7).equals("http://"))) {
			if (!(fotoURL.substring(0, 8).equals("https://"))) {
				throw new IllegalArgumentException(msg);
			}
		}
	}

	/**
	 * Método que valida se os atributos passados no parametro de alteraPesquisador
	 * são validos, ou seja, são nome, email, foto, biografia e funcao em caps lock.
	 * 
	 * @param atributo
	 *            - o atributo que vai ser testado.
	 * @param msg
	 *            - mensagem de erro a ser exibidada caso seja invalido
	 */
	public static void validaAtributosPesquisador(String atributo, String msg) {
		if (!atributo.equals("NOME")) {
			if (!atributo.equals("EMAIL")) {
				if (!atributo.equals("FOTO")) {
					if (!atributo.equals("BIOGRAFIA")) {
						if (!atributo.equals("FUNCAO")) {
							throw new IllegalArgumentException(msg);
						}
					}
				}
			}
		}
	}

	/**
	 * Valida o campo de interesse da pesquisa, que deve conter ate 255 caracteres e
	 * ate quatro campos que nao podem ser vazios
	 * 
	 * @param campo
	 *            o campo de interesse
	 */
	public static void validaCampoDeInteresse(String campo) {
		String[] topicos = campo.split(",");
		for (int i = 0; i < topicos.length; i++) {
			if (topicos[i].equals("") || topicos[i].length() < 3) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}

		}
		if (topicos.length > 4 || campo.length() > 255) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}
	}

	/**
	 * Valida o nível de risco da atividade que deve ser: Alto, Médio ou Baixo.
	 * 
	 * @param risco
	 *            String contento o risco a ser validado
	 */
	public static void validaValorRisco(String risco) {
		if (!(risco.equals("MEDIO") || risco.equals("ALTO") || risco.equals("BAIXO"))) {
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}
	}

	/**
	 * Valida se uma atividade existe dentro de um map de atividades.
	 * 
	 * @param atividades
	 *            Map de atividades para a validação.
	 * @param codigo
	 *            String contendo o código da atividade a ser validada.
	 */
	public static void validaAtividadeExiste(Map<String, Atividade> atividades, String codigo) {
		if (!atividades.containsKey(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
	}

	public static void validaProblemaExiste(Map<String, Problema> problemas, String codigo) {
		if (!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
	}

	public static void validaObjetivoExiste(Map<String, Objetivo> objetivos, String codigo) {
		if (!objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
	}
  
	/**
	 * Valida se uma string correspondente à uma data tem o formato "dd-mm-aaaa"
	 * 
	 * @param data
	 *            a data a ser validada
	 * @param msg
	 *            a mensagem de erro que possivelmente será exibida
	 */
	public static void validaFormatoData(String data, String msg) {
		String[] dataQuebrada = data.split("/");

		if (dataQuebrada.length != 3) {
			throw new IllegalArgumentException(msg);
		} else if (dataQuebrada[0].length() != 2 || dataQuebrada[1].length() != 2 || dataQuebrada[2].length() != 4) {
			throw new IllegalArgumentException(msg);
		} else {
			int dia = Integer.parseInt(dataQuebrada[0]);
			int mes = Integer.parseInt(dataQuebrada[1]);

			if (dia > 31 || dia < 1) {
				throw new IllegalArgumentException(msg);
			} else if (mes > 12 || mes < 1) {
				throw new IllegalArgumentException(msg);
			}
		}

	}

	/**
	 * Valida se um semestre é maior que zero.
	 * 
	 * @param semestre o semestre a ser validado
	 * @param msg a mensagem de erro que possivelmente será exibida
	 */
	public static void validaSemestreAluno(int semestre, String msg) {
		if (semestre <= 0) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * Valida se um IEA está entre 0 e 10
	 * 
	 * @param iea o IEA a ser validado
	 * @param msg a mensagem de erro que possivelmente será exibida
	 */
	public static void validaIEA(double iea, String msg) {
		if (iea < 0 || iea > 10) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void validaDuracao(int duracao) {
		if (duracao < 1) {
			throw new IllegalArgumentException("Duracao nao pode ser nula ou negativa.");
		}
	}

	public static void validaPesquisaAtiva(boolean pesquisaEhAtiva) {
		if (!pesquisaEhAtiva) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}

	public static void validaItem(int item) {
		if ((Integer)(item) == null || item < 1) {
			throw new IllegalArgumentException("Item nao pode ser nulo ou negativo.");
		}
	}

	public static void validaResultado(int numeroResultado) {
		if ((Integer)(numeroResultado) == null || numeroResultado < 1)
	throw new IllegalArgumentException("numeroResultado nao pode ser nulo ou negativo.");
	}
}
