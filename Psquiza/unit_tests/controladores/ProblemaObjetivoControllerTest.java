package controladores;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProblemaObjetivoControllerTest {

	ProblemaObjetivoController POcontroller;

	@BeforeEach
	void setUp() {
		POcontroller = new ProblemaObjetivoController();
	}
	
	//Testes de viabilidade
	
	@Test
	void testViabilidadeZeroEmProblema() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.cadastraProblema("Presidente é um machista escroto", 0);
		});
	}

	@Test
	void testViabilidadeZeroEmObjetivo() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.cadastraObjetivo("Especifico", "impeachment", 5, 0);
		});
	}

	@Test
	void testViabilidadeAcimaDe5EmProblema() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.cadastraProblema("Policia que mata mais do que bandidos", 6);
		});
	}

	@Test
	void testViabilidadeAcimade5EmObjetivo() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.cadastraObjetivo("Geral", "Punição justa ao invés de dizer que foi engano", 5, 1000);
		});
	}

	@Test
	void testViabilidadeNegativaEmProblema() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.cadastraProblema("Oleo nas praias do Nordeste", -2);
		});
	}

	@Test
	void testViabilidadeNegativaEmObjetivo() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.cadastraObjetivo("Especifico", "Jogar a culpa do oleo na Venezuela", 3, -7);
		});
	}

	@Test(expected = IllegalArgumentException.class)
	void testCadastraProblemaViabilidadeNull() {
		POcontroller.cadastraProblema("Transformar crianças com beijo gay na novela", null);
	}

	@Test(expected = IllegalArgumentException.class)
	void testCadastraObjetivoViabilidadeDouble() {
		POcontroller.cadastraObjetivo("Geral", "Desfazer o estrago em Brumadinho", 5, 4.5);
	}
	
	//Testes de Aderencia

	@Test(expected = IllegalArgumentException.class)
	void testApagaProblemaInexistente() {
		POcontroller.apagaProblema("P4");
	}

	@Test
	void testApagaProblemaCodigoVazioNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.apagaProblema(" ");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.apagaProblema(null);
		});
	}

	@Test(expected = IllegalArgumentException.class)
	void testApagaObjetivoInexistente() {
		POcontroller.apagaObjetivo("O5");
	}

	@Test
	void testApagaObjetivoCodigoVazioNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.apagaObjetivo("");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.apagaObjetivo(null);
		});
	}
	@Test(expected = IllegalArgumentException.class)
	void testCodigoProblemaComLetraMinuscula() {
		POcontroller.apagaProblema("p2");
	}
	
	@Test(expected = IllegalArgumentException.class)
	void testCodigoObjetivoComLetraMinuscula() {
		POcontroller.exibeObjetivo("o3");
	}
	
	@Test(expected = IllegalArgumentException.class)
	void testCodigoProblemaComLetraApenasLetras() {
		POcontroller.apagaProblema("PP");
	}
	
	@Test(expected = IllegalArgumentException.class)
	void testCodigoObjetivoComLetraApenasLetras() {
		POcontroller.exibeObjetivo("OO");
	}
	
	@Test
	void testApagaProblemaComSucesso() {
		POcontroller.cadastraProblema("Fora Temer", 5);
		POcontroller.apagaProblema("P1");
	}

	@Test
	void testApagaObjetivoComSucesso() {
		POcontroller.cadastraObjetivo("Geral", "Tira o pete", 5, 5);
		POcontroller.apagaObjetivo("O1");
	}

	@Test(expected = IllegalArgumentException.class)
	void testExibeProblemaInexistente() {
		POcontroller.exibeProblema(p);
	}

	@Test
	void testExibeProblemaCodigoVazioNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.exibeProblema("");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.exibeProblema(null);
		});
	}

	@Test(expected = IllegalArgumentException.class)
	void testExibeObjetivoInexistente() {
		POcontroller.exibeObjetivo(o);
	}

	@Test
	void testExibeObjetivoCodigoVazioNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.exibeObjetivo("");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.exibeObjetivo(null);
		});
	}

	@Test(expected = IllegalArgumentException.class)
	void testIdProblemaComZero() {
		POcontroller.exibeProblema("P0");
	}

	@Test(expected = IllegalArgumentException.class)
	void testIdObjetivoComZero() {
		POcontroller.exibeObjetivo("O0");
	}

	@Test
	void testExibeProblemaComSucesso() {
		POcontroller.cadastraProblema("A Flora não eh a protagonista das Winx", 4);
		assertEquals(POcontroller.exibeProblema("P1"), "P1 - A Flora não eh a protagonista das Winx - 4");
	}

	@Test
	void testExibeObjetivosComSucesso() {
		POcontroller.cadastraObjetivo("Geral", "Promover uma revolucao", 4, 5);
		POcontroller.cadastraObjetivo("Especifico", "Reclamar no twitter", 4, 3);
		assertEquals(POcontroller.exibeObjetivo("O1"), "O1 - Promover uma revolucao - 9");
		assertEquals(POcontroller.exibeObjetivo("O2"), "O2 - Reclamar no twitter - 7");
	}

	@Test(expected = IllegalArgumentException.class)
	void testExibeObjetivoCodigoVazio() {
		POcontroller.exibeObjetivo("");
	}

	@Test(expected = IllegalArgumentException.class)
	void testExibeObjetivoCodigoNulo() {
		POcontroller.exibeObjetivo(null);
	}
}
