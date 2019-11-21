package gravacaoArquivos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import atividade.AtividadeController;
import pesquisa.PesquisaController;
import pesquisador.PesquisadorController;
import problema.ProblemaObjetivoController;

/**
 * classe responsavel por gravar e recuperar o atividade do sistema
 * 
 * @author Natalia Salvino
 *
 */
public class Gravacao {
	/**
	 * metodo que usa de arquivo txt para gravar atividade do sistema
	 * 
	 * @param objeto  o objeto que sera gravado
	 * @param caminho o caminho onde o objeto sera guardado
	 */
	public static void salvar(Object objeto, String caminho) {

		try {

			FileOutputStream saveFile = new FileOutputStream(caminho);

			ObjectOutputStream stream = new ObjectOutputStream(saveFile);

			stream.writeObject(objeto);

			stream.close();

		} catch (Exception exc) {

			exc.printStackTrace();

		}

	}

	/**
	 * metodo que restaura a atividade salva no sistema
	 * 
	 * @param caminho o caminho do arquivo que grava a atividade
	 * @return retorna o objeto restaurado
	 */
	public static Object restaurar(String caminho) {

		Object objeto = null;

		try {

			FileInputStream restFile = new FileInputStream(caminho);

			ObjectInputStream stream = new ObjectInputStream(restFile);

			if (caminho.equals("pesquisa.txt")) {
				objeto = (PesquisaController) stream.readObject();
			} else if (caminho.equals("pesquisador.txt")) {
				objeto = (PesquisadorController) stream.readObject();
			} else if (caminho.equals("problemaObjetivo.txt")) {
				objeto = (ProblemaObjetivoController) stream.readObject();
			} else if (caminho.equals("atividade.txt")) {
				objeto = (AtividadeController) stream.readObject();

			}
			stream.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return objeto;

	}

}