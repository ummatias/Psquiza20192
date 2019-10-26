package testes;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProblemaObjetivoControllerTest {
	
	POController controller;
	
	@BeforeEach
	public void setUp() {
		controller = new POController();
		Problema prob = new Problema("A Flora não é a protagonista das Winx", 4);
		Objetivo objGeral = new Objetivo("GERAL", "Promover uma revolução", 4, 5);
		Objetivo objEsp = new Objetivo("ESPECIFICO", "Reclamar no twitter e causar comoção", 3, 4);
	}

	@Test
	void testExibeProblema() {
		assertEquals( prob.toString(), "P1 - A Flora não é a protagonista das Winx - 4");
	}
	
	@Test
	void testExibeObjetivos() {
		assertEquals( obGeral.toString(), "O1 - Promover uma revolução - 9");
		assertEquals( obEsp.toString(), "O2 - Reclamar no twitter e causar comoção - 7");
	}
	
	@Test
	void testProblemaEObjetivos() {}

}
