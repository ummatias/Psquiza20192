package gravacaoArquivos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pesquisa.PesquisaController;
import pesquisador.PesquisadorController;

public class Gravacao {
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

	public static Object restaurar(String caminho) {

		Object objeto = null;

		try {

			FileInputStream restFile = new FileInputStream(caminho);

			ObjectInputStream stream = new ObjectInputStream(restFile);

			if( caminho.equals("pesquisa.txt")) {
			objeto = (PesquisaController)stream.readObject();
			}
			else if(caminho.equals("pesquisador.txt")) {
				objeto = (PesquisadorController)stream.readObject();
			}

			stream.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return  objeto;

	}
	
}
