package controladores;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import facade.Facade;

public class BuscaControllerTest {
	
	private Facade facade;
	
	@BeforeEach
	void setUp() {
		this.facade = new Facade();
		facade.cadastraPesquisa("Adesão crescente ao movimento Calculo 2020.1", "adeus calculo, 2019");
		facade.cadastraPesquisador("Maria","externo", "grande interesse em filmes de comedia e computação", "maria@gmail.com", "https://foto.com");
		facade.cadastraProblema("Falta de bom senso dos estudantes", 3);
		facade.cadastraObjetivo("Especifico", "Estudar história e ver menos filme de ação", 5, 5);
		facade.cadastraAtividade("Sentar e conversar", "ALTO", "Algumas pessoas são bem intolerantes a bom senso");
	}
	
	@Test
	void testBuscaVazia() {
		assertThrows( IllegalArgumentException.class, () -> {
			facade.busca("");});
	}
	
	@Test
	void testBuscaNula() {
		assertThrows( NullPointerException.class, () -> {
			facade.busca(null, 2);});
	}
	@Test
	void testBuscaVaziaComNumero() {
		assertThrows( IllegalArgumentException.class, () -> {
			facade.busca("", 2);});
	}
	
	@Test
	void testBuscaNulaComNumero() {
		assertThrows( NullPointerException.class, () -> {
			facade.busca(null);});
	}
	@Test
	void  testContaResultadosBuscaVazia() {
		assertThrows( IllegalArgumentException.class, () -> {
			facade.contaResultadosBusca("");
		}); 
	}
	@Test
	void  testContaResultadosBuscaNula() {
		assertThrows( NullPointerException.class, () -> {
			facade.contaResultadosBusca(null);
		});
	}
	@Test
	void testBuscaNumeroNegativo() {
		assertThrows(IllegalArgumentException.class, ()  -> {
			facade.busca("ente", -1);
		});
	}
	
	@Test
	void testBuscaNumeroZero () {
		assertThrows(IllegalArgumentException.class, () -> {
			facade.busca("ente", 0);
		});
	}
	
	@Test
	void testBuscaTermoInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			facade.busca("azul", 1);
		});
	}
	
	@Test
	void testBuscaTermoComNumeroInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			facade.busca("mas", 4);
		});
	}
	
	@Test
	void testBuscaTermoSemCaseSensive() {
		assertEquals(facade.busca("Sen"), "P1: Falta de bom senso dos estudantes | A1: Sentar e conversar | A1: Algumas pessoas são bem intolerantes a bom senso");
	}
	
	@Test
	void testBuscaTermoCaracteresEspeciais() {
		assertEquals(facade.busca("ão"),
				"ADE1: Adesão crescente ao movimento Calculo 2020.1 | "
				+ "maria@gmail.com: grande interesse em filmes de comedia e computação | "
				+ "O1: Estudar história e ver menos filme de ação | "
				+ "A1: Algumas pessoas são bem intolerantes a bom senso");
	}
	
	@Test
	void testBuscaTermoCaracteresEspeciaisEmCaixaAlta() {
		
		facade.cadastraPesquisa("nada", "novo burgão vegetariano na uni");
		
		assertEquals(facade.busca("Ão"), "NOV1: novo burgão vegetariano na uni | "
				+ "ADE1: Adesão crescente ao movimento Calculo 2020.1 | "
				+ "maria@gmail.com: grande interesse em filmes de comedia e computação | "
				+ "O1: Estudar história e ver menos filme de ação | "
				+ "A1: Algumas pessoas são bem intolerantes a bom senso");
	}
	
	@Test
	void testResultadoCampoDeInteresseAntesDeDescricao() {
		assertNotEquals(facade.busca("Calc"), "ADE1: adeus calculo, 2019 | ADE1: Adesão crescente ao movimento Calculo 2020.1");
	}
	
	@Test
	void testResultadoDescricaoDeRiscoAntesDeDescricao() {
		assertNotEquals(facade.busca("sen"), "P1: Falta de bom senso dos estudantes | A1: Algumas pessoas são bem intolerantes a bom senso | A1: Sentar e conversar");
	}
	
	@Test
	void testOrdemLexicografica() {
		assertNotEquals(facade.busca("ão"), "A1: Sentar e conversar | "
				+ "A1: Algumas pessoas são bem intolerantes a bom senso | "
				+ "ADE1 : Adesão crescente ao movimento Calculo 2020.1 | "
				+ "maria@gmail.com : grande interesse em filmes de comedia e computação | "
				+ "O1: Estudar história e ver menos filme de ação | "
				+ "P1: Falta de bom senso dos estudantes");
	}
	
	@Test
	void testContaResultadosComeçaComUm() {
		assertNotEquals(facade.contaResultadosBusca("ão"), 5);
	}
	
	@Test
	void testContaResultadoComSucesso() {
		assertEquals(facade.contaResultadosBusca("ão"), 4);
	}
	
	@Test
	void testNumeroNaOrdemErrada() {
		facade.cadastraAtividade("Solzão", "ALTO", "Pode desenvolver cancer de pele");
		assertNotEquals(facade.busca("ão"), "A2: Solzão | "
				+ "A1: Sentar e conversar | "
				+ "A1: Algumas pessoas são bem intolerantes a bom senso | "
				+ "ADE1 : Adesão crescente ao movimento Calculo 2020.1 | "
				+ "maria@gmail.com : grande interesse em filmes de comedia e computação | "
				+ "O1: Estudar história e ver menos filme de ação | "
				+ "P1: Falta de bom senso dos estudantes");
	}
	
	@Test
	void testExibeResultadosPulandoLinha() {
		assertNotEquals(facade.busca("Calc"), "ADE1: Adesão crescente ao movimento Calculo 2020.1 \n"
				+ "ADE1: adeus calculo, 2019");
	}
	
	
//	@Test
//	void testAssociaProblemaComSucesso() {
//		assertEquals(true,facade.associaProblema("CAL1", "P1"));
//	}
//	@Test
//	void testDesassociaProblemaComSucesso() {
//		assertEquals(true,facade.desassociaProblema("CAL1"));
//	}
//	@Test
//	void testAssociaObjetivoComSucesso() {
//		assertEquals(true, facade.associaObjetivo("CAL1", "O1"));
//	}
//	@Test
//	void testDesassociaObjetivoComSucesso() {
//		assertEquals(true,facade.desassociaObjetivo("CAL1", "O1"));
//	}
//	@Test
//	void testAssociaPesquisaJaDesativada() {
//		this.facade.encerraPesquisa("ADE1", "falta de verba");
//		assertThrows(IllegalArgumentException.class, () -> {
//			facade.associaObjetivo("ADE1", "O1");
//		});
//		
//	}
//	@Test
//	void testAssociaSegundoProblemaAPesquisa() {
//		this.facade.associaProblema("ADE1", "P1");
//		this.facade.cadastraProblema("Falta de ativiades pela saude mental de alunos", 5);
//		
//		assertThrows(IllegalArgumentException.class, () -> {
//			facade.associaProblema("ADE1", "P2");
//		});
//		
//	}
//	@Test
//	void testAssociaProblemaJaAssociado() {
//		this.facade.associaProblema("ADE1", "P1");
//		assertFalse(facade.associaProblema("ADE1", "P1"));
//
//	}
//	@Test
//	void testAssociaObjetivoJaAssociado() {
//		this.facade.associaObjetivo("ADE1", "O1");
//		assertFalse(facade.associaObjetivo("ADE1", "O1"));
//
//	}
	
}