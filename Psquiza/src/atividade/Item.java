package atividade;

public class Item {
	
	private boolean status;
	private String textItem;
	
	public Item(String textItem) {
		status = false;
		this.textItem = textItem;
	}
	
	public String toString() {
		
		String estado = "";
		
		if(status == true) {
			estado = "REALIZADO";
		}else {
			estado = "PENDENTE";		
		}
		
		return estado + " - " + textItem;
	}
	
	public boolean getStatus() {
		return status;
	}

}
