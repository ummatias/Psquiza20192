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

	@Test
	void testCadastraProblemaViabilidadeNull() {
		assertThrows(NullPointerException.class, () -> {
			POcontroller.cadastraProblema("Transformar crianças com beijo gay na novela", null);
		});
	}

	@Test
	void testApagaProblemaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> { POcontroller.apagaProblema("P4"); });
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

	@Test
	void testApagaObjetivoInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.apagaObjetivo("O5");});
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
	@Test
	void testCodigoProblemaComLetraMinuscula() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.apagaProblema("p2");
		});
	}
	
	@Test
	void testCodigoObjetivoComLetraMinuscula() {
		assertThrows(IllegalArgumentException.class, () -> {
		POcontroller.exibeObjetivo("o3");
		});
	}
	
	@Test
	void testCodigoProblemaComLetraApenasLetras() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.apagaProblema("PP");
		});
	}
	@Test
	void testCodigoObjetivoComLetraApenasLetras() {
		assertThrows(IllegalArgumentException.class, () -> {
		POcontroller.exibeObjetivo("OO");
		});
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

	@Test
	void testExibeProblemaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.exibeProblema(p);
		});
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

	@Test
	void testExibeObjetivoInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.exibeObjetivo(o);
		});	
	}

	@Test
	void testExibeObjetivoCodigoVazioNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.exibeObjetivo("");
		});

		assertThrows(NullPointerException.class, () -> {
			POcontroller.exibeObjetivo(null);
		});
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

	@Test
	void testExibeObjetivoCodigoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.exibeObjetivo("");
		});
	}

	@Test
	void testExibeObjetivoCodigoNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			POcontroller.exibeObjetivo(null);
		});
	}
}
