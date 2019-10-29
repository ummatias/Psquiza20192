package entidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProblemaTest {
	private Problema problema;
	private Problema p;
	
	@BeforeEach
	void setUp() {
		this.problema = new Problema("P1", "Fazer CC sem ir regulamente no psicologo", 3);
	}
	@Test
	void testConstrutor() {
		assertNull(p);
		p = new Problema("P2", "Noites sem dormir pra estudar", 5);
		assertNotNull(p);
	}
	
	@Test
	void testEquals() {
		p = new Problema("P1", "Acumulo de materia", 4);
		assertTrue(this.problema.equals(p));
	}
	
	@Test
	void testNotEquals() {
		p = new Problema("P3", "Discurso machista entre os estudantes", 4);
		assertFalse(this.problema.equals(p));
	}
	
	@Test
	void testHashCodeIguais() {
		p = new Problema("P1", "Demanda muito alta de estudantes para a clinica de psicologia", 3);
		assertEquals(this.problema.hashCode(), p.hashCode());
	}
	
	@Test
	void testHashCodeDiferente() {
		p = new Problema("P5", "Muita nota baixa para conseguir passar", 3);
		assertEquals(this.problema.hashCode(), p.hashCode());
	}
	
	@Test
	void testToString() {
		assertEquals("P1 - Fazer CC sem ir regulamente no psicologo - 3", this.problema.toString());
	}
	
	@Test
	void testDescriçãoProblemaVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Problema("P4","", 2);
		});
	}
	
	@Test
	void testDescriçãoProblemaNull() {
		assertThrows(NullPointerException.class, () -> {
			new Problema("P3",null, 3);
		});
	}
	
	@Test
	void testViabilidadeZeroEmProblema() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Problema("P5","Presidente é um machista escroto", 0);
		});
	}
	
	@Test
	void testIdProblemaComZero() {
		assertThrows(IllegalArgumentException.class, () -> {
		new Problema("P0", "Calculo ta me matando", 4);
		});
	}
}
