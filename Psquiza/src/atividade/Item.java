package atividade;

import java.io.Serializable;

/**
 * Classe que modela um item de uma ativade, podendo ser
 * descrito como "Realizado" ou "Pendente".
 * 
 * @author Mateus Matias Ribeiro - 119111153
 *
 */
public class Item implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 3482417186357857257L;

	/**
	 * Valor booleano que representa o status de um item
	 */
	private boolean status;
	
	/**
	 * String que descrição do item.
	 */
	private String textItem;
	
	
	
	/**
	 * Constrói um item através da sua descrição e inicializa seus atributos.
	 * 
	 * @param textItem
	 */
	public Item(String textItem) {
		status = false;
		this.textItem = textItem;
	}
	
	/**Cria uma representação em String de um item, no padrão
	 * "Estado_do_item - descrição_do_item"
	 */
	
	@Override
	public String toString() {
		
		String estado = "";
		
		if(status == true) {
			estado = "REALIZADO";
		}else {
			estado = "PENDENTE";		
		}
		
		return estado + " - " + textItem;
	}
	
	/**
	 * Retorna um booleano que referente ao status do item.
	 * 
	 * @return status booleano que representa o status do item
	 */
	public boolean getStatus() {
		return status;
	}

	/** Método que altera o status do item para realizado.
	 * @param status - status do item.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	


}
