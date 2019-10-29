package entidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
	@Test
	void testCodigoComLetraMinuscula() {
		assertThrows(
				IllegalArgumentException.class,() -> {new Objetivo("o7","Geral","Ser forte o bastante para ninguem se preocupar comigo", 2, 3);}
		);
	}
	
	@Test
	void testTipo() {
		assertThrows(IllegalArgumentException.class, () -> 
		{new Objetivo("O2","Super", "Virar number one hero", 5, 5);}
		);
	}
	@Test
	void testDescriçãoObjetivoVazia() {
		assertThrows(
				IllegalArgumentException.class,() -> {new Objetivo("O4","Geral","", 2, 3);}
		);
	}
	
	@Test
	void testDescriçãoObjetivoNula() {
		assertThrows(
				NullPointerException.class, () -> {new Objetivo("O5","Geral",null, 2, 3);}
		);
	}
	
	@Test
	void testAderenciaZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Objetivo("O3","Especifico", "Cuidar do meio ambiente e nao votar em embuste", 0, 5);
		});
	}
	
	@Test
	void testAderenciaAcimaDe5() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Objetivo("O7","Geral", "Exibir a camisa no varal", 6, 4);
		});
	}
	@Test
	void testIdComZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Objetivo("O0", "Geral", "Terminar os testes", 5, 5);
		});
	}

}
