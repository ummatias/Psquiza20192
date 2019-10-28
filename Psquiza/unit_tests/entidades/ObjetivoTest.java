package entidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjetivoTest {
	private Objetivo objetivo;
	private Objetivo o;
	
	@BeforeEach
	void setUp() {
		this.objetivo = new Objetivo("O1", "Especifico", "Terminar o Projeto com nota alta", 5, 5);
	}
	@Test
	void testConstrutor() {
		assertNull(o);
		o = new Objetivo("O3", "Geral", "Pagar Calculo 1", 5, 1);
		assertNotNull(o);
	}
	
	@Test
	void testEquals() {
		o = new Objetivo("O1", "Geral", "Pagar LP2", 5, 2);
		assertTrue(this.objetivo.equals(o));
	}
	
	@Test
	void testNotEquals() {
		o = new Objetivo("O2", "Especifico", "Tirar nota boa na prova", 4, 5);
		assertFalse(this.objetivo.equals(o));
	}
	
	@Test
	void testHashCodeIguais() {
		o = new Objetivo("O1", "Geral", "", 2, 1);
		assertEquals(this.objetivo.hashCode(), o.hashCode());
	}
	
	@Test
	void testHashCodeDiferente() {
		o = new Objetivo("O2", "Geral", "Manter a saude mental em dia", 5, 5);
		assertEquals(this.objetivo.hashCode(), o.hashCode());
	}
	
	@Test
	void testToString() {
		assertEquals("O1 - Especifico - Terminar o Projeto com nota alta - 10", this.objetivo.toString());
	}
}
