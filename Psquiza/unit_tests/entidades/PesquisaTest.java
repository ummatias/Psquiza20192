package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pesquisa.Pesquisa;

class PesquisaTest {

	private Pesquisa pesquisa;

	@BeforeEach
	void inicializaPesquisa() {
		this.pesquisa = new Pesquisa("COM1", "Desigualdade de genero presente na computacao", "Computacao, Genero");
		
	}
	
	@Test
	void testEqualsTrue() {
		Pesquisa pesquisaEX1 = new Pesquisa("ENE1", "Fontes de energia renovaveis na revolucao energetica", "Energia, Eletrica");
		Pesquisa pesquisaEX2 = new Pesquisa("ENE1", "Impactos ambientais das fontes fosseis", "Energia, Eletrica");
		
		assertEquals(pesquisaEX1, pesquisaEX2);
	}
	
	@Test
	void testEqualsFalse() {
		Pesquisa pesquisaEX3 = new Pesquisa("ENE3", "Impactos da falta", "Energia, Eletrica");
		Pesquisa pesquisaEX4 = new Pesquisa("ENE4", "Impactos ambientais das fontes fosseis", "Energia, Eletrica");
		
		assertNotEquals(pesquisaEX3, pesquisaEX4);
	}
	
	@Test
	void testHashFalse() {
		
		Pesquisa pesquisaOutra = new Pesquisa("CAN1", "Posibilidade de cura para o cancer atraves de impressao 3D", "Cancer, Saude");
		
		assertNotEquals(pesquisaOutra.hashCode(), pesquisa.hashCode());
	}
	
	@Test
	void testHashTrue() {
		
		Pesquisa pesquisaMaisUma = new Pesquisa("COMP1", "Impressao 3D e a tulizalçao da computacao a saude", "Computacao, Saude");
		
		assertNotEquals(pesquisaMaisUma.hashCode(), pesquisa.hashCode());
	}
	
	@Test
	void testToString(){
		assertEquals("COM1 - Desigualdade de genero presente na computacao - Computacao, Genero", pesquisa.toString());
	}
	
	@Test
	void testSetDesc() {
		pesquisa.setDescricao("Desigualdade de genero na computacao");
		assertEquals("COM1 - Desigualdade de genero na computacao - Computacao, Genero" , pesquisa.toString());
	}
	
	@Test
	void testSetCampo() {
		pesquisa.setCampoDeInteresse("Genero, Computacao");
		assertEquals("COM1 - Desigualdade de genero na computacao - Genero, Computacao" , pesquisa.toString());
	}
	
	@Test
	void testEncerraCorreto() {
		pesquisa.encerraPesquisa();
		assertFalse(pesquisa.ehAtiva());
	}
	
	@Test
	void testEncerraJaEcerrado() {
		pesquisa.encerraPesquisa();
		
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisa.encerraPesquisa();
		});
	}
	
	
	@Test
	void testAtivaCorreto() {
		pesquisa.encerraPesquisa();
		pesquisa.ativaPesquisa();
		assertTrue(pesquisa.ehAtiva());
	}
	
	@Test
	void testAtivaPesquisaAtiva() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisa.ativaPesquisa();
	});
	}


}
