package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PesquisaTest {

	private Pesquisa pesquisa;

	@BeforeEach
	void inicializaPesquisa() {
		this.pesquisa = new Pesquisa("COM1", "Desigualdade de genero presente na computacao", "Computacao, Genero");
		
	}
	
	@Test
	void testEquals() {
		Pesquisa pesquisa1 = new Pesquisa("ENE1", "Fontes de energia renovaveis na revolucao energetica", "Energia, Eletrica");
		Pesquisa pesquisa2 = new Pesquisa("ENE1", "Impactos ambientais das fontes fosseis", "Energia, Eletrica");
		
		assertEquals(pesquisa1, pesquisa2);
	}
	
	@Test
	void testHash() {
		
		Pesquisa pesquisaOutra = new Pesquisa("CAN1", "Posibilidade de cura para o cancer atraves de impressao 3D", "Cancer, Saude");
		
		assertNotEquals(pesquisaOutra.hashCode(), pesquisa.hashCode());
	}
	
	@Test
	void testToString(){
		assertEquals("COM1 - Desigualdade de genero presente na computacao - Computacao, Genero", pesquisa.toString());
	}
	
	@Test
	void testSetDesc() {
		assertEquals("COM1 - Desigualdade de genero na computacao - Computacao, Genero" , pesquisa.setDesc("Desigualdade de genero na computacao"));
	}
	
	@Test
	void testSetCampo() {
		assertEquals("COM1 - Desigualdade de genero na computacao - Genero, Computacao" , pesquisa.setCampo("Genero, Computacao"));
	}
	
	@Test
	void testEncerra() {
		pesquisa.encerra();
		assertFalse(pesquisa.getStatus());
	}
	
	@Test
	void testAtiva() {
		pesquisa.ativa();
		assertTrue(pesquisa.getStatus());
	}


}
