package controladores;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BuscaControllerGeralTest {
	private ControllerGeral controller;
	
	@BeforeEach
	void setUp() {
		this.controller = new ControllerGeral();
		controller.cadastraPesquisa("Adesão crescente ao movimento Calculo 2020.1", 5);
		controller.cadastraPesquisador("Maria","externo", "grande interesse em filmes de comedia e computação", "maria@gmail.com", "https://foto.com");
		controller.cadastraProblema("Falta de bom senso dos estudantes", 3);
		controller.cadastraObjetivo("Especifico", "Estudar história e ver menos filme de ação", 5, 5);
		controller.cadastraAtividade("Sentar e conversar", "ALTO", "Algumas pessoas são bem intolerantes a bom senso");
	}
	
	@Test
	void testBuscaVazia() {
		assertThrows( IllegalArgumentException.class, () -> {
			controller.busca("");});
	}
	@Test
	void testBuscaNula() {
		assertThrows( NullPointerException.class, () -> {
			controller.busca(null);});
	}
	@Test
	void  testContaResultadosBuscaVazia() {
		assertThrows( IllegalArgumentException.class, () -> {
			controller. contaResultadosBusca("");
		});
	}
	@Test
	void  testContaResultadosBuscaNula() {
		assertThrows( NullPointerException.class, () -> {
			controller. contaResultadosBusca(null);
		});
	}
}