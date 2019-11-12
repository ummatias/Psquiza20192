package controladores;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import generaliza.ControllerGeral;

public class ControllerGeralTest {
	private ControllerGeral controller;
	
	@BeforeEach
	void setUp() {
		this.controller = new ControllerGeral();
		controller.cadastraPesquisa("Adesão crescente ao movimento Calculo 2020.1", "adeus calculo, 2019");
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
			controller.contaResultadosBusca("");
		}); 
	}
	@Test
	void  testContaResultadosBuscaNula() {
		assertThrows( NullPointerException.class, () -> {
			controller.contaResultadosBusca(null);
		});
	}
	@Test
	void testBuscaNumeroNegativo() {
		assertThrows(IllegalArgumentException.class, ()  -> {
			controller.busca("ente", -1);
		});
	}
	
	@Test
	void testBuscaNumeroZero () {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.busca("ente", 0);
		});
	}
	
	@Test
	void testBuscaTermoInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.busca("azul", 1);
		});
	}
	
	@Test
	void testBuscaTermoComNumeroInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.busca("mas", 4);
		});
	}
	
	@Test
	void testBuscaTermoSemCaseSensive() {
		assertEquals(controller.busca("Sen"), "P1: Falta de bom senso dos estudantes | A1: Sentar e conversar | A1: Algumas pessoas são bem intolerantes a bom senso");
	}
	
	@Test
	void testBuscaTermoCaracteresEspeciais() {
		assertEquals(controller.busca("ão"), "P1: Falta de bom senso dos estudantes | "
				+ "O1: Estudar história e ver menos filme de ação | "
				+ "maria@gmail.com : grande interesse em filmes de comedia e computação | "
				+ "ADE1 :Adesão crescente ao movimento Calculo 2020.1 | "
				+ "A1: Sentar e conversar | "
				+ "A1: Algumas pessoas são bem intolerantes a bom senso");
	}
	
	@Test
	void testBuscaTermoCaracteresEspeciaisEmCaixaAlta() {
		
		controller.cadastraPesquisa("nada", "novo burgão vegetariano na uni");
		
		assertEquals(controller.busca("Ão"),"P1: Falta de bom senso dos estudantes | "
				+ "O1: Estudar história e ver menos filme de ação | "
				+ "NOV1: novo burgão vegetariano na uni | "
				+ "maria@gmail.com : grande interesse em filmes de comedia e computação | "
				+ "ADE1 : Adesão crescente ao movimento Calculo 2020.1 | "
				+ "A1: Sentar e conversar | "
				+ "A1: Algumas pessoas são bem intolerantes a bom senso");
	}
	
	@Test
	void testResultadoCampoDeInteresseAntesDeDescricao() {
		assertNotEquals(controller.busca("Calc"), "ADE1: adeus calculo, 2019 | ADE1: Adesão crescente ao movimento Calculo 2020.1");
	}
	
	@Test
	void testResultadoDescricaoDeRiscoAntesDeDescricao() {
		assertNotEquals(controller.busca("sen"), "P1: Falta de bom senso dos estudantes | A1: Algumas pessoas são bem intolerantes a bom senso | A1: Sentar e conversar");
	}
	
	@Test
	void testOrdemLexicografica() {
		assertNotEquals(controller.busca("ão"), "A1: Sentar e conversar | "
				+ "A1: Algumas pessoas são bem intolerantes a bom senso | "
				+ "ADE1 : Adesão crescente ao movimento Calculo 2020.1 | "
				+ "maria@gmail.com : grande interesse em filmes de comedia e computação | "
				+ "O1: Estudar história e ver menos filme de ação | "
				+ "P1: Falta de bom senso dos estudantes");
	}
	
	@Test
	void testContaResultadosComeçaComUm() {
		assertNotEquals(contaResultadosBusca("ão"), 7);
	}
	
	@Test
	void testContaResultadoComSucesso() {
		assertEquals(contaResultadosBusca("ão"), 6);
	}
	
	@Test
	void testNumeroNaOrdemErrada() {
		controller.cadastraAtividade("Solzão", "ALTO", "Pode desenvolver cancer de pele");
		assertNotEquals(controller.busca("ão"), "A2: Solzão | "
				+ "A1: Sentar e conversar | "
				+ "A1: Algumas pessoas são bem intolerantes a bom senso | "
				+ "ADE1 : Adesão crescente ao movimento Calculo 2020.1 | "
				+ "maria@gmail.com : grande interesse em filmes de comedia e computação | "
				+ "O1: Estudar história e ver menos filme de ação | "
				+ "P1: Falta de bom senso dos estudantes");
	}
	
	@Test
	void testExibeResultadosPulandoLinha() {
		assertNotEquals(controller.busca("Calc"), "ADE1: Adesão crescente ao movimento Calculo 2020.1 \n"
				+ "ADE1: adeus calculo, 2019");
	}
	
	@Test
	void testAssociaProblemaComSucesso() {
		assertEquals(true,controller.associaProblema("CAL1", "P1"));
	}
	@Test
	void testDesassociaProblemaComSucesso() {
		assertEquals(true,controller.desassociaProblema("CAL1", "P1"));
	}
	@Test
	void testAssociaObjetivoComSucesso() {
		assertEquals(true, controller.associaObjetivo("CAL1", "O1"));
	}
	@Test
	void testDesassociaObjetivoComSucesso() {
		assertEquals(true,controller.desassociaObjetivo("CAL1", "O1"));
	}
	@Test
	void testAssociaPesquisaJaDesativada() {
		this.controller.encerraPesquisa("ADE1", "falta de verba");
		assertThrows(IllegalArgumentException.class, () -> {
			controller.associaObjetivo("ADE1", "O1");
		});
		
	}
	@Test
	void testAssociaSegundoProblemaAPesquisa() {
		this.controller.associaProblema("ADE1", "P1");
		this.controller.cadastraProblema("Falta de ativiades pela saude mental de alunos", 5);
		
		assertThrows(IllegalArgumentException.class, () -> {
			controller.associaProblema("ADE1", "P2");
		});
		
	}
	@Test
	void testAssociaProblemaJaAssociado() {
		this.controller.associaProblema("ADE1", "P1");
		assertFalse(controller.associaProblema("ADE1", "P1"));

	}
	@Test
	void testAssociaObjetivoJaAssociado() {
		this.controller.associaObjetivo("ADE1", "O1");
		assertFalse(controller.associaObjetivo("ADE1", "O1"));

	}
	
}