package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.Item;

class ItemTest {

	private Item item;

	@BeforeEach
	void inicializaItem() {
		this.item = new Item("Preparar insumos do laboratório de biologia.");
	}
	
	@Test
	void testToString() {
		assertEquals("PENDENTE - Preparar insumos do laboratório de biologia.", item.toString());
	}
	
	@Test
	void testGetStatus() {
		assertFalse(item.getStatus());
	}

}
