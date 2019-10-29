package controladores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PesquisaControllerTest {

	private PesquisaController pesquisaController;

	@BeforeEach
	void instanciarController() {
		this.pesquisaController = new PesquisaController();
		pesquisaController.cadastraPesquisa("Pesquisa sobre as implicacoes psicologicas da pressao gerada na universidade.",
				"Universidade, Problema, Psico");
	}
	
	// Cadastra Pesquisa - testando cadastros e campos vazios ou nulos.
	@Test
	void testCadastraPesquisaComSucesso() {
		
		assertEquals(
				"COM1",
				pesquisaController.cadastraPesquisa("Pesquisa sobre a supremacia quantica do Google", "Computacao, Google , Quantico"));
	}
	
	@Test
	void testCadastraMaisPesquisa() {
		assertEquals("POL1", pesquisaController.cadastraPesquisa("Incompetencia presidencial e como isso impacta globalmente", "Politica, Bozo"));
		assertEquals("POL2", pesquisaController.cadastraPesquisa("Pesquisa relaciona a participação feminina no senado", "Politica, Direitos"));

	}
	
	@Test
	void testCadastraPesquisaDescricaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.cadastraPesquisa("", "Economia, Oleo" );
		});
	}
	
	@Test
	void testCadastraPesquisaDescricaoNula() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.cadastraPesquisa(null, "Economia, Oleo" );
		});
	}
	
	@Test
	void testCadastraPesquisaCamposIntVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.cadastraPesquisa("Pesquisa sobre como o cerebro percebe a dor", "" );
		});
	}
	
	@Test
	void testCadastraPesquisaCamposIntNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.cadastraPesquisa("Pesquisa sobre como o cerebro percebe a dor", null );
		});
	}
	
	
	// Altera Pesquisa - testando pesquisa inexistente e campos vazios e nulos.
	
	@Test
	void testAlteraPesquisaComSucesso() {
		pesquisaController.cadastraPesquisa("Pesquisa sobre a dependencia gerada pela internet", "Internet, Dependencia");
		pesquisaController.alteraPesquisa("INT1", "DESCRICAO", "Pesquisa sobre a dependencia causada pela internet");
		
		assertNotEquals(
				"INT1 - Pesquisa sobre a dependencia gerada pela internet - Internet, Dependencia",
				pesquisaController.exibePesquisa("INT1")
				);
	}
	
	@Test
	void testAlteraPesquisaOutroCampo() {
		pesquisaController.cadastraPesquisa("Pesquisa sobre as diferenças do 5G em relação ao Wi-fi da biblioteca", "Nenhum, Zero");
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.alteraPesquisa("NEN1", "CODIGO", "ZER1");
		});
	}
	
	@Test
	void testAlteraPesquisaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.alteraPesquisa("INT2", "DESCRICAO", "Pesquisa sobre a dependencia causada pela internet");
		});
	}
	
	@Test
	void testAlteraPesquisaCodigoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.alteraPesquisa("", "DESCRICAO", "Pesquisa sobre a dependencia causada pela internet");
		});
	}
	
	@Test
	void testAlteraPesquisaCodigoNula() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.alteraPesquisa(null, "DESCRICAO", "Pesquisa sobre a dependencia causada pela internet");
		});
	}
	
	@Test
	void testAlteraPesquisaCampoAltVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.alteraPesquisa("INT1", "", "Pesquisa sobre a dependencia causada pela internet");
		});
	}
	
	@Test
	void testAlteraPesquisaCampoAltNula() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.alteraPesquisa("INT1", null, "Pesquisa sobre a dependencia causada pela internet");
		});
	}
	
	
	@Test
	void testAlteraPesquisaCampoNovoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.alteraPesquisa("INT1", "CAMPO", "");
		});
	}
	
	@Test
	void testAlteraPesquisaCampoNovoNula() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.alteraPesquisa("INT1", "CAMPO", null);
		});	
	}

	
	//Encerra Pesquisa - Testando encerrar com sucesso, encerrar pesquisa ja encerrada e com cod vazio ou nulo.
	
	@Test
	void testEncerraPesquisaComSucesso() {
		pesquisaController.cadastraPesquisa("Pesquisa sobre os impactos do incendio na amazonia para sobrevivencia na terra", "Natureza, Amazonia, Sobrevivencia");
		pesquisaController.encerraPesquisa("NAT1", "O governo não se importa com isso");
		
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.exibePesquisa("NAT1");
		});
	}
	
	@Test
	void testEncerraPesquisaJaEncerrada() {
		pesquisaController.cadastraPesquisa("Pesquisa sobre os impactos da presenca de oleo nas praias para sobrevivencia na terra", "Praia, Incompetencia, Sobrevivencia");
		pesquisaController.encerraPesquisa("PRA1", "O governo não se importa com isso");
		
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.encerraPesquisa("PRA1");
		});
	}
	
	@Test
	void testEncerraPesquisaCodigoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.encerraPesquisa("");
		});
	}
	
	@Test
	void testEncerraPesquisaCodigoNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.encerraPesquisa(null);
		});
	}
	
	@Test
	void testEncerraPesquisaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.encerraPesquisa("LOL1");
		});
	}
	
	
	// Ativa Pesquisa - Testando ativar com sucesso, ativar pesquisa ja ativa e com cod vazio ou nulo.
	
	@Test
	void testAtivaPesquisaComSucesso() {
		pesquisaController.cadastraPesquisa("Pesquisa relacionada ao impacto dos jogos digitais na escolha de CC", "Jogos, Computação");
		pesquisaController.encerraPesquisa("JOG1");
		
		assertTrue(pesquisaController.pesquisaEhAtiva("JOG1"));
	}
	
	@Test
	void testAtivaPesquisaJaAtiva() {
		pesquisaController.cadastraPesquisa("Ergonomia de cadeiras afeta diretamente a saude do individuo", "Saude, Cadeiras");
		
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.ativaPesquisa("SAU1");
		});
	}
	
	@Test
	void testAtivaPesquisaCodigoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.ativaPesquisa("");
		});
	}
	
	@Test
	void testAtivaPesquisaCodigoNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.ativaPesquisa(null);
		});
	}
		
		void testAtivaPesquisaInexistente() {
			assertThrows(IllegalArgumentException.class, () -> {
				pesquisaController.ativaPesquisa("LOL1");
			});
		
	}
	
	// Exibe Pesquisa
	
	@Test
	void testExibePesquisaComSucesso() {
		pesquisaController.cadastraPesquisa("Impacto da leitura na formação do ser humano", "Livros, Crescimento");
		
		assertEquals("LIV1 - Impacto da leitura na formação do ser humano - Livros, Crescimento", pesquisaController.exibePesquisa("LIV1"));
	}
	
	@Test
	void testExibePesquisaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.exibePesquisa("LOL1");
		});
	}
	
	@Test
	void testExibePesquisaCodigoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.exibePesquisa("");
		});
	}
	
	@Test
	void testExibePesquisaCodigoNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.exibePesquisa(null);
		});
	}
	
	
	// Pesquisa Eh Ativa
	
	@Test
	void testPesquisaEhAtivaTrue() {
		pesquisaController.cadastraPesquisa("Pesquisa com brasileiros para definir se e biscoito ou bolacha", "Bolacha, Biscoito, Verdade");
		assertTrue(pesquisaController.pesquisaEhAtiva("BOL1"));
	}
	
	@Test
	void testPesquisaEhAtivaFalse() {
		pesquisaController.encerraPesquisa("BOL!");
		assertFalse(pesquisaController.pesquisaEhAtiva("BOL1"));
	}
	
	@Test
	void testPesquisaEhAtivaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.pesquisaEhAtiva("LOL1");
		});
	}
	
	@Test
	void testPesquisaEhAtivaCodVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.pesquisaEhAtiva("");
		});
	}	
	
	@Test
	void testPesquisaEhAtivaCodNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.pesquisaEhAtiva(null);
		});
	}
	

}
