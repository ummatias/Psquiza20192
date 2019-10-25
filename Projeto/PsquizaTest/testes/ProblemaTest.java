package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProblemaTest {
	@BeforeEach
	public void criaProblema() {
		String desc;
		int viabilidade = 1;
		Problema prob = new Problema("Falta de apoio emocional para estudantes de cc", 4);
		Objetivo obGeral = new Objetivo("GERAL", "Promover debates e comprar lenços", 4, 5);
		Objetivo obEsp = new Objetivo("ESPECIFICO", "Vaquinha pros lenços", 3, 4);
	}
	@Test
	void testHashcode() {
		
	}
	// O + id(1-5) e P + id(1-5)
	@Test
	void testEquals() {
		
	}
	
	
	@Test
	void testToString() {
		assertEquals( prob.toString(), "cod - Falta de apoio emocional para estudantes de cc - 4");
		assertEquals( obGeral.toString(), "cod - Geral - Promover debates e comprar lenços - 9");
		assertEquals( obEsp.toString(), "") 
	}
	
	@Test
	void testProblema() {
		fail("Not yet implemented");
	}

}
