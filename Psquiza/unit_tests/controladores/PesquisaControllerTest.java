package controladores;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import atividade.AtividadeController;
import pesquisa.PesquisaController;
import pesquisador.PesquisadorController;
import problema.ProblemaObjetivoController;


class PesquisaControllerTest {

	private PesquisaController pesquisaController;
	private ProblemaObjetivoController problemaObjetivoController;
	private AtividadeController atividadeController;
	private PesquisadorController pesquisadorController;


	@BeforeEach
	void instanciarController() {
		this.problemaObjetivoController = new ProblemaObjetivoController();
		this.atividadeController = new AtividadeController();
		this.pesquisadorController = new PesquisadorController();
		this.pesquisaController = new PesquisaController(
				atividadeController, 
				problemaObjetivoController, 
				pesquisadorController
			);
		
		pesquisaController.cadastraPesquisa(
				"Pesquisa sobre as implicacoes psicologicas da pressao gerada na universidade.",
				"Universidade, Problema, Psico");
		
		this.problemaObjetivoController.cadastraProblema("Falta de acessibilidade no ambiente academico", 5);
		this.problemaObjetivoController.cadastraObjetivo("ESPECIFICO", "contruir rampas em todos os predios", 5, 5);
		this.pesquisadorController.cadastraPesquisador("Flora", "Estudante", "Winx mais perfeita das Winx", "flora@winx.com", "http://fotosdaswinx.com/flora");
		this.pesquisadorController.cadastraPesquisador("Tecna", "Professor", "Winx que conserta impressora", "tecna@winx.com", "http://fotosdaswinx.com/tecna");
	}

	// Cadastra Pesquisa - testando cadastros e campos vazios ou nulos.
	@Test
	void testCadastraPesquisaComSucesso() {

		assertEquals("COM1", pesquisaController.cadastraPesquisa("Pesquisa sobre a supremacia quantica do Google",
				"Computacao, Google , Quantico"));
	}

	@Test
	void testCadastraMaisPesquisa() {
		assertEquals("POL1", pesquisaController
				.cadastraPesquisa("Incompetencia presidencial e como isso impacta globalmente", "Politica, Bozo"));
		assertEquals("POL2", pesquisaController.cadastraPesquisa("Pesquisa relaciona a participação feminina no senado",
				"Politica, Direitos"));

	}

	@Test
	void testCadastraPesquisaDescricaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.cadastraPesquisa("", "Economia, Oleo");
		});
	}

	@Test
	void testCadastraPesquisaDescricaoNula() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.cadastraPesquisa(null, "Economia, Oleo");
		});
	}

	@Test
	void testCadastraPesquisaCamposIntVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.cadastraPesquisa("Pesquisa sobre como o cerebro percebe a dor", "");
		});
	}

	@Test
	void testCadastraPesquisaCamposIntNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.cadastraPesquisa("Pesquisa sobre como o cerebro percebe a dor", null);
		});
	}

	// Altera Pesquisa - testando pesquisa inexistente e campos vazios e nulos.

	@Test
	void testAlteraPesquisaComSucesso() {
		pesquisaController.cadastraPesquisa("Pesquisa sobre a dependencia gerada pela internet",
				"Internet, Dependencia");
		pesquisaController.alteraPesquisa("INT1", "DESCRICAO", "Pesquisa sobre a dependencia causada pela internet");

		assertNotEquals("INT1 - Pesquisa sobre a dependencia gerada pela internet - Internet, Dependencia",
				pesquisaController.exibePesquisa("INT1"));
	}

	@Test
	void testAlteraPesquisaOutroCampo() {
		pesquisaController.cadastraPesquisa("Pesquisa sobre as diferenças do 5G em relação ao Wi-fi da biblioteca",
				"Nenhum, Zero");
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.alteraPesquisa("NEN1", "CODIGO", "ZER1");
		});
	}

	@Test
	void testAlteraPesquisaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.alteraPesquisa("INT2", "DESCRICAO",
					"Pesquisa sobre a dependencia causada pela internet");
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
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.alteraPesquisa("UNI1", "CAMPO", null);
		});
	}

	// Encerra Pesquisa - Testando encerrar com sucesso, encerrar pesquisa ja
	// encerrada e com cod vazio ou nulo.

	@Test
	void testEncerraPesquisaComSucesso() {
		pesquisaController.cadastraPesquisa(
				"Pesquisa sobre os impactos do incendio na amazonia para sobrevivencia na terra",
				"Natureza, Amazonia, Sobrevivencia");
		pesquisaController.encerraPesquisa("NAT1", "O governo não se importa com isso");

		assertEquals(pesquisaController.pesquisaEhAtiva("NAT1"), false);

	}

	@Test
	void testEncerraPesquisaJaEncerrada() {
		pesquisaController.cadastraPesquisa(
				"Pesquisa sobre os impactos da presenca de oleo nas praias para sobrevivencia na terra",
				"Praia, Incompetencia, Sobrevivencia");
		pesquisaController.encerraPesquisa("PRA1", "O governo não se importa com isso");

		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.encerraPesquisa("PRA1", "ja foram obtidos os reaultados");
		});
	}

	@Test
	void testEncerraPesquisaCodigoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.encerraPesquisa("", "");
		});
	}

	@Test
	void testEncerraPesquisaCodigoNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.encerraPesquisa(null, "");
		});
	}

	@Test
	void testEncerraPesquisaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.encerraPesquisa("LOL1", "o tema nao agradou o publico");
		});
	}

	// Ativa Pesquisa - Testando ativar com sucesso, ativar pesquisa ja ativa e com
	// cod vazio ou nulo.

	// @Test
	// void testAtivaPesquisaComSucesso() {
	// pesquisaController.cadastraPesquisa("Pesquisa relacionada ao impacto dos
	// jogos digitais na escolha de CC", "Jogos, Computação");
	// pesquisaController.encerraPesquisa("JOG1","os resultados ja foram obtidos");
	//
	// assertTrue(pesquisaController.pesquisaEhAtiva("JOG1"));
	// }

	@Test
	void testAtivaPesquisaJaAtiva() {
		pesquisaController.cadastraPesquisa("Ergonomia de cadeiras afeta diretamente a saude do individuo",
				"Saude, Cadeiras");

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

		assertEquals("LIV1 - Impacto da leitura na formação do ser humano - Livros, Crescimento",
				pesquisaController.exibePesquisa("LIV1"));
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
		pesquisaController.cadastraPesquisa("Pesquisa com brasileiros para definir se e biscoito ou bolacha",
				"Bolacha, Biscoito, Verdade");
		assertTrue(pesquisaController.pesquisaEhAtiva("BOL1"));
	}

	@Test
	void testPesquisaEhAtivaFalse() {
		pesquisaController.cadastraPesquisa("melhores bolsas de pesquisa", "bolsas, pesquisa");
		pesquisaController.encerraPesquisa("BOL1", "nao houverao resultados conclusivos");
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


	// parte 2 - cdu 7

	@Test
	void testAssociaAtividadeComSucesso() {
		atividadeController.cadastraAtividade("Questionario", "BAIXO", "n tem risco");

		assertTrue(pesquisaController.associaAtividade("UNI1", "A1"));
	}

	@Test
	void testAssociaAtividadeCodVazioNulo() {
		atividadeController.cadastraAtividade("Questionario", "BAIXO", "n tem risco");

		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaAtividade("", "A1");
		});

		assertThrows(NullPointerException.class, () -> {
			pesquisaController.associaAtividade(null, "");
		});
	}

	@Test
	void testAssociaAtividadeNula() {

		assertThrows(NullPointerException.class, () -> {
			pesquisaController.associaAtividade("UNI1", null);
		});
	}

	@Test
	void testAssociaAtividadeJaAssociada() {
		atividadeController.cadastraAtividade("Questionario", "BAIXO", "n tem risco");
		pesquisaController.associaAtividade("UNI1", "A1");

		assertFalse(pesquisaController.associaAtividade("UNI1", "A1"));
	}

	@Test
	void testAssociaAtividadePesquisaDesativada() {
		atividadeController.cadastraAtividade("Questionario", "BAIXO", "n tem risco");
		pesquisaController.encerraPesquisa("UNI1", "to só testando hehe");

		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaAtividade("UNI1", "A1");
		});
	}
	
	@Test
	void testAssociaAtividadePesquisaInexistente() {
		atividadeController.cadastraAtividade("Questionario", "BAIXO", "n tem risco");
		
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaAtividade("UNI2", "A1");
		});
	}	

	// desassocia atividade

	@Test
	void testDesassociaAtividadeComSucesso() {
		atividadeController.cadastraAtividade("Questionario", "BAIXO", "n tem risco");
		pesquisaController.associaAtividade("UNI1", "A1");

		assertTrue(pesquisaController.desassociaAtividade("UNI1", "A1"));
	}

	@Test
	void testDesassociaAtividadeCodVazioNulo() {
		atividadeController.cadastraAtividade("Questionario", "BAIXO", "n tem risco");
		pesquisaController.associaAtividade("UNI1", "A1");

		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.desassociaAtividade("", "A1");
		});

		assertThrows(NullPointerException.class, () -> {
			pesquisaController.desassociaAtividade(null, "A1");
		});
	}

	@Test
	void testDesassociaAtividadeJaDesassociada() {
		atividadeController.cadastraAtividade("Questionario", "BAIXO", "n tem risco");
		
		pesquisaController.associaAtividade("UNI1", "A1");
		pesquisaController.desassociaAtividade("UNI1", "A1");

		assertFalse(pesquisaController.desassociaAtividade("UNI1", "A1"));
	}
	
	@Test
	void testDesassociaAtividadePesquisaDesativada() {
		atividadeController.cadastraAtividade("Questionario", "BAIXO", "n tem risco");
		pesquisaController.associaAtividade("UNI1", "A1");
		pesquisaController.encerraPesquisa("UNI1", "to só testando hehe");

		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.desassociaAtividade("UNI1", "A1");
		});
	}
	
	@Test
	void testDesassociaAtividadePesquisaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.desassociaAtividade("UNI2", "A1");
		});
	}
	
	@Test
	void testDesassociaAtividadeInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.desassociaAtividade("UNI2", "A1");
		});
	}
//testes cdu 5
	@Test
	void testAssociaPesquisaDesativada() {
		this.pesquisaController.encerraPesquisa("UNI1", "Ja foi realizada");
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaObjetivo("UNI1", "O1");
		});
	}
	@Test
	void testAssociaSegundoProblemaAPesquisa() {
		this.pesquisaController.associaProblema("UNI1", "P1");
		this.problemaObjetivoController.cadastraProblema("falta de rampas na universidade", 5);
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaProblema("UNI1", "P2");
		});
	}
	@Test
	void testAssociaProblemaJaAssociado() {
		this.pesquisaController.associaProblema("UNI1", "P1");
		assertFalse(this.pesquisaController.associaProblema("UNI1", "P1"));
	}
	@Test
	void testDesassociaProblemaNaoAssociado() {
		assertFalse(this.pesquisaController.desassociaProblema("UNI1"));
	}
	@Test
	void testAssociaObjetivoJaAssociado() {
		this.pesquisaController.associaObjetivo("UNI1", "O1");
		assertFalse(this.pesquisaController.associaObjetivo("UNI1", "O1"));
	}
	@Test
	void testDesassociaObjetivoNaoAssociado() {
		assertFalse(this.pesquisaController.desassociaObjetivo("UNI1", "O1"));
	}
	
	@Test
	void testAssociaProblemaComSucesso() {
		pesquisaController.cadastraPesquisa("Erro nos testes da US5", "Erro, Debug");
		problemaObjetivoController.cadastraProblema("Programa bugadasso", 3);
		assertTrue(this.pesquisaController.associaProblema("ERR1", "P2"));
	}
	
	

//	@Test
//	void testAssociaProblemaComSucesso() {
//		assertTrue(this.pesquisaController.associaProblema("UNI1", "P1"));
//	}
  
	@Test
	void testAssociaObjetivoComSucesso() {
		this.pesquisaController.cadastraPesquisa("falta de agua", "agua,geografia");
		
		assertTrue(this.pesquisaController.associaObjetivo("AGU1", "O1"));
	}
	@Test
	void testAssociaObjetivoAsegundaPesquisa() {
		this.pesquisaController.associaObjetivo("UNI1", "O1");
		this.pesquisaController.cadastraPesquisa("falta de palestras sobre saude mental", "saude, universidade");
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaObjetivo("SAU1", "O1");
		});
	}
	@Test
	void testAssociaObjetivoPesquisaVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaObjetivo("", "O1");
		});
	}
	@Test
	void testAssociaObjetivoIdObjetivoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaObjetivo("UNI1", "");
		});
	}
	@Test
	void testAssociaObjetivoPesquisaNula() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.associaObjetivo(null, "O1");
		});
	}
	@Test
	void testAssociaObjetivoIdObjetivoNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.associaObjetivo("UNI1", null);
		});
	}
	@Test
	void testAssociaProblemaPesquisaNula() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.associaProblema(null, "P1");
		});
	}
	@Test
	void testAssociaProblemaIdProblemaNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.associaProblema("UNI1", null);
		});
	}
	@Test
	void testAssociaProblemaPesquisaVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaProblema("", "P1");
		});
	}
	@Test
	void testAssociaProblemaIdProblemaVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaProblema("UNI1", "");
		});
	}
	
	//Testes - US6
	
	@Test
	void testAssociaPesquisadorComSucesso() {
		assertTrue(pesquisaController.associaPesquisador("UNI1", "flora@winx.com"));
	}
	
	@Test
	void testAssociaPesquisadorCodigoPesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaPesquisador("", "flora@winx.com");
		});
	}
	
	@Test
	void testAssociaPesquisadorCodigoPesquisaNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.associaPesquisador(null, "flora@winx.com");
		});
	}
	
	@Test
	void testAssociaPesquisadorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaPesquisador("UNI1", "");
		});
	}
	
	@Test
	void testAssociaPesquisadorEmailPesquisaNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.associaPesquisador("UNI1", null);
		});
	}
	
	@Test
	void testAssociaPesquisadorPesquisaNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaPesquisador("LOL666", "flora@winx.com");
		});
	}
	
	@Test
	void testAssociaPesquisdorPesquisaDesativada() {
		pesquisaController.encerraPesquisa("UNI1", "Falta de verba governamental");
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaPesquisador("UNI1", "flora@winx.com");
		});
	}
	
	@Test
	void testAssociaPesquisadorJaAssociado() {
		pesquisaController.associaPesquisador("UNI1", "flora@winx.com");
		assertFalse(pesquisaController.associaPesquisador("UNI1", "flora@winx.com"));
	}
	
	@Test
	void testAssociaPesquisadorNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.associaPesquisador("UNI1", "bloom@winx.com");
		});
	}
	
	
	@Test
	void testDesassociaPesquisadorComSucesso() {
		pesquisaController.associaPesquisador("UNI1", "flora@winx.com");
		assertTrue(pesquisaController.desassociaPesquisador("UNI1", "flora@winx.com"));
	}
	
	@Test
	void testDesassociaPesquisadorCodigoPesquisaVazio() {
		pesquisaController.desassociaPesquisador("UNI1", "flora@winx.com");
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.desassociaPesquisador("", "flora@winx.com");
		});
	}
	
	@Test
	void testDesassociaPesquisadorCodigoPesquisaNulo() {
		pesquisaController.desassociaPesquisador("UNI1", "flora@winx.com");
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.desassociaPesquisador(null , "flora@winx.com");
		});
	}
	
	@Test
	void testDesassociaPesquisadorEmailVazio() {
		pesquisaController.desassociaPesquisador("UNI1", "flora@winx.com");
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.desassociaPesquisador("UNI1", "");
		});
	}
	
	@Test
	void testDesassociaPesquisadorEmailNulo() {
		pesquisaController.desassociaPesquisador("UNI1", "flora@winx.com");
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.desassociaPesquisador("UNI1" , null);
		});
	}
	
	@Test
	void testDesassociaPesquisadorPesquisaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.desassociaPesquisador("LOL666", "flora@winx.com");
		});
	}
	
	@Test
	void testDesassociaPesquisadorPesquisaDesativada() {
		pesquisaController.encerraPesquisa("UNI1", "Necessidade de Teste");
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.desassociaPesquisador("UNI1", "flora@winx.com");
		});
	}
	
	@Test
	void testDesassociaPesquisadorJaDesassociado() {
		assertFalse(pesquisaController.desassociaPesquisador("UNI1", "flora@winx.com"));
	}
	
	@Test
	void testDesassociaPesquisadorNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.desassociaPesquisador("UNI1", "musa@winx.com");
		});
	}
	
	@Test
	void testCadastraEspecialidadeAlunoComSucesso() {
		pesquisadorController.cadastraEspecialidadeAluno("flora@winx.com", 9, 10);
		assertEquals(
				"Flora (Estudante) - Winx mais perfeita das Winx - flora@winx.com - http://fotosdaswinx.com/flora - 9o SEMESTRE - 10.0",
				pesquisadorController.exibePesquisador("flora@winx.com")
		);
	}
	
	@Test
	void testCadastraEspecialidadeAlunoEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.cadastraEspecialidadeAluno("", 9, 10);
		});
	}
	
	@Test
	void testCadastraEspecialidadeAlunoEmailNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisadorController.cadastraEspecialidadeAluno(null, 9, 10);
		});
	}
	
	@Test
	void testCadastraEspecialidadeAlunoPesquisadorNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.cadastraEspecialidadeAluno("musa@winx.com", 9, 10);
		});
	}
	
	@Test
	void testCadastraEspecialidadeAlunoEmProfessor() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.cadastraEspecialidadeAluno("tecna@winx.com", 9, 10);
		});
	}
	
	@Test
	void testCadastraEspecialidadeAlunoSemestreNegativo() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.cadastraEspecialidadeAluno("flora@winx.com", -9, 10);
		});
	}
	
	@Test
	void testCadastraEspecialidadeAlunoIEANegativo() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.cadastraEspecialidadeAluno("flora@winx.com", 9, -10);
		});
	}
	
	@Test
	void tesCadastraEspecialidadeAlunoLimiteSemestreAbaixo() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.cadastraEspecialidadeAluno("flora@winx.com", 0, 10);
		});
	}
	
	@Test
	void testCadastraEspecialidadeProfessorComSucesso() {
		pesquisadorController.cadastraEspecialidadeProfessor("tecna@winx.com", "Doutorado", "Alfea", "28/01/2004");
		assertEquals(
				"Tecna (Professor) - Winx que conserta impressora - tecna@winx.com - http://fotosdaswinx.com/tecna - Doutorado - Alfea - 28/01/2004",
				pesquisadorController.exibePesquisador("tecna@winx.com"));
	}
	
	@Test
	void testCadastraEspecialidadeProfessorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.cadastraEspecialidadeProfessor("", "Doutorado", "Alfea", "28/01/2004");
		});
	}
	
	@Test
	void testCadastraEspecialidadeProfessorEmailNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisadorController.cadastraEspecialidadeProfessor(null, "Doutorado", "Alfea", "28/01/2004");
		});
	}
	
	@Test
	void testCadastraEspecialidadeProfessorFormacaoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.cadastraEspecialidadeProfessor("tecna@winx.com", "", "Alfea", "28/01/2004");
		});
	}
	
	@Test
	void testCadastraEspecialidadeProfessorFormacaoNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisadorController.cadastraEspecialidadeProfessor("tecna@winx.com", null, "Alfea", "28/01/2004");
		});
	}
	
	@Test
	void testCadastraEspecialidadeProfessorUnidadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.cadastraEspecialidadeProfessor("tecna@winx.com", "Doutorado", "", "28/01/2004");
		});
	}
	
	@Test
	void testCadastraEspecialidadeProfessorUnidadeNula() {
		assertThrows(NullPointerException.class, () -> {
			pesquisadorController.cadastraEspecialidadeProfessor("tecna@winx.com", "Doutorado", null, "28/01/2004");
		});
	}
	
	@Test
	void testCadastraEspecialidadeProfessorDataVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.cadastraEspecialidadeProfessor("tecna@winx.com", "Doutorado", "Alfea", "");
		});
	}
	
	@Test
	void testCadastraEspecialidadeProfessorPesquisadorNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.cadastraEspecialidadeProfessor("musa@winx.com", "Doutorado", "Alfea", "28/01/2004");
		});
	}
	
	@Test
	void testCadastraEspecialidadeProfessorDataNula() {
		assertThrows(NullPointerException.class, () -> {
			pesquisadorController.cadastraEspecialidadeProfessor("tecna@winx.com", "Doutorado", "Alfea", null);
		});
	}
	
	@Test
	void testCadastraEspecialidadeProfessorEmEstudante() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.cadastraEspecialidadeProfessor("flora@winx.com", "Doutorado", "Alfea", "28/01/2004");
		});
	}
	
	@Test
	void testlistaPesquisadoresProfessorComSucesso() {
		pesquisadorController.cadastraPesquisador("Stella", "Professor", "Winx contra protetor solar", "stella@winx.com", "http://fotosdaswinx.com/stella");
		pesquisadorController.cadastraPesquisador("Musa", "Professor", "Winx artista", "musa@winx.com", "http://fotosdaswinx.com/musa");
		
		pesquisadorController.cadastraEspecialidadeProfessor("tecna@winx.com", "Doutorado", "Alfea", "28/01/2004");
		pesquisadorController.cadastraEspecialidadeProfessor("stella@winx.com", "Mestrado", "Alfea", "28/01/2004");
		pesquisadorController.cadastraEspecialidadeProfessor("musa@winx.com", "Graduação", "Alfea", "28/01/2004");
		
		assertEquals("Tecna (Professor) - Winx que conserta impressora - tecna@winx.com - http://fotosdaswinx.com/tecna - Doutorado - Alfea - 28/01/2004 | "
				+ "Stella (Professor) - Winx contra protetor solar - stella@winx.com - http://fotosdaswinx.com/stella - Mestrado - Alfea - 28/01/2004 | "
				+ "Musa (Professor) - Winx artista - musa@winx.com - http://fotosdaswinx.com/musa - Graduação - Alfea - 28/01/2004",
				pesquisadorController.listaPesquisadores("PROFESSOR"));
	}
	
	@Test
	void testListaPesquisadoresTipoInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.listaPesquisadores("EXPULSOS");
		});
	}
	
	@Test
	void testListaPesquisadoresTipoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.listaPesquisadores("");
		});
	}
	
	@Test
	void testListaPesquisadoresTipoNulo() {
		assertThrows(NullPointerException.class, () -> {
			pesquisadorController.listaPesquisadores(null);
		});
	}
	
	@Test
	void testListaPesquisadoresExternosComSucesso() {
		pesquisadorController.cadastraPesquisador("Stella", "Externo", "Winx contra protetor solar", "stella@winx.com", "http://fotosdaswinx.com/stella");
		pesquisadorController.cadastraPesquisador("Musa", "Externo", "Winx artista", "musa@winx.com", "http://fotosdaswinx.com/musa");
		
		assertEquals("Stella (Externo) - Winx contra protetor solar - stella@winx.com - http://fotosdaswinx.com/stella | "
				+ "Musa (Externo) - Winx artista - musa@winx.com - http://fotosdaswinx.com/musa",
				pesquisadorController.listaPesquisadores("EXTERNO"));
		
	}
	
	@Test
	void testListaPesquisadoresEstudanteComSucesso() {
		pesquisadorController.cadastraPesquisador("Stella", "Estudante", "Winx contra protetor solar", "stella@winx.com", "http://fotosdaswinx.com/stella");
		pesquisadorController.cadastraPesquisador("Musa", "Estudante", "Winx artista", "musa@winx.com", "http://fotosdaswinx.com/musa");
		
		pesquisadorController.cadastraEspecialidadeAluno("flora@winx.com", 8, 5);
		pesquisadorController.cadastraEspecialidadeAluno("stella@winx.com", 7, 9);
		pesquisadorController.cadastraEspecialidadeAluno("musa@winx.com", 6, 10);
		
		assertEquals("Flora (Estudante) - Winx mais perfeita das Winx - flora@winx.com - http://fotosdaswinx.com/flora - 8o SEMESTRE - 5.0 | "
				+ "Stella (Estudante) - Winx contra protetor solar - stella@winx.com - http://fotosdaswinx.com/stella - 7o SEMESTRE - 9.0 | "
				+ "Musa (Estudante) - Winx artista - musa@winx.com - http://fotosdaswinx.com/musa - 6o SEMESTRE - 10.0",
				pesquisadorController.listaPesquisadores("ESTUDANTE"));
		
	}
	
	@Test
	void testConfiguraEstrategiaComSucesso() {
		pesquisaController.configuraEstrategia("MAIOR_RISCO");
		pesquisaController.configuraEstrategia("MENOS_PENDENCIAS");
		pesquisaController.configuraEstrategia("MAIS_ANTIGA");
		pesquisaController.configuraEstrategia("MAIOR_DURACAO");
	}
	
	@Test
	void testConfiguraEstrategiaValorNuloVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.configuraEstrategia("");
		});
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.configuraEstrategia(null);
		});
	}
	
	@Test
	void testConfiguraEstrategiaInvalida() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.configuraEstrategia("MAIS_ITENS");
		});
	}
	
	@Test
	void testAlteraPesquisadorComSucesso() {
		pesquisadorController.cadastraEspecialidadeAluno("flora@winx.com", 5, 7);
		pesquisadorController.alteraPesquisador("flora@winx.com", "SEMESTRE", "6");
		assertEquals("Flora (Estudante) - Winx mais perfeita das Winx - flora@winx.com - http://fotosdaswinx.com/flora - 6o SEMESTRE - 7.0",
				pesquisadorController.exibePesquisador("flora@winx.com"));
	}
	
	@Test
	void testAlteraPesquisadorAtributoInvalido() {
		pesquisadorController.cadastraEspecialidadeAluno("flora@winx.com", 5, 7);
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.alteraPesquisador("flora@winx.com", "IDADE", "winxperfeita@winx.com");
		});
	}
	
	@Test
	void testAlteraPesquisadorAtributoVazio() {
		pesquisadorController.cadastraEspecialidadeAluno("flora@winx.com", 5, 7);
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorController.alteraPesquisador("flora@winx.com", "", "6");
		});
	}
	
	@Test
	void testAlteraPesquisadorAtributoNulo() {
		pesquisadorController.cadastraEspecialidadeAluno("flora@winx.com", 5, 7);
		assertThrows(NullPointerException.class, () -> {
			pesquisadorController.alteraPesquisador("flora@winx.com", null, "6");
		});
	}

	
	@Test
	void testProximaAtividadeMaisAntiga() {
		atividadeController.cadastraAtividade("Fincar piquetes em frente ao LCC3", "ALTO", "Perigo de encontrar estudantes de cc no caminho");
		atividadeController.cadastraAtividade("Fazer algo com os piquetes", "BAIXO", "Não há risco pq eu n sei pra que serve um piquete");
		atividadeController.cadastraAtividade("Reclamar dos estudantes de cc", "ALTO", "Tem que ter coragem pq quando um estudante de cc pega ranço de alguem...");
		
		atividadeController.cadastraItem("A1", "Teste1");
		atividadeController.cadastraItem("A2", "Teste1");
		atividadeController.cadastraItem("A3", "Teste1");
		
		pesquisaController.associaAtividade("UNI1", "A1");
		pesquisaController.associaAtividade("UNI1", "A2");
		pesquisaController.associaAtividade("UNI1", "A3");
		
		assertEquals("A1", pesquisaController.proximaAtividade("UNI1"));
		
	}
	
	@Test
	void testProximaAtividadeMaiorRisco() {
		atividadeController.cadastraAtividade("Fazer algo com os piquetes", "BAIXO", "Não há risco pq eu n sei pra que serve um piquete");
		atividadeController.cadastraAtividade("Fincar piquetes em frente ao LCC3", "ALTO", "Perigo de encontrar estudantes de cc no caminho");
		atividadeController.cadastraAtividade("Reclamar dos estudantes de cc", "ALTO", "Tem que ter coragem pq quando um estudante de cc pega ranço de alguem...");
		
		atividadeController.cadastraItem("A1", "Teste1");
		atividadeController.cadastraItem("A2", "Teste1");
		atividadeController.cadastraItem("A3", "Teste1");
		
		pesquisaController.associaAtividade("UNI1", "A1");
		pesquisaController.associaAtividade("UNI1", "A2");
		pesquisaController.associaAtividade("UNI1", "A3");
		
		pesquisaController.configuraEstrategia("MAIOR_RISCO");
		
		assertEquals("A2", pesquisaController.proximaAtividade("UNI1"));
		
	}
	
	@Test
	void testProximaAtividadeMenosPendencias() {
		atividadeController.cadastraAtividade("Fazer algo com os piquetes", "BAIXO", "Não há risco pq eu n sei pra que serve um piquete");
		atividadeController.cadastraAtividade("Fincar piquetes em frente ao LCC3", "ALTO", "Perigo de encontrar estudantes de cc no caminho");
		atividadeController.cadastraAtividade("Reclamar dos estudantes de cc", "ALTO", "Tem que ter coragem pq quando um estudante de cc pega ranço de alguem...");
		
		atividadeController.cadastraItem("A1", "Teste1");
		atividadeController.cadastraItem("A1", "Teste1");
		atividadeController.cadastraItem("A2", "Teste1");
		atividadeController.cadastraItem("A2", "Teste1");
		atividadeController.cadastraItem("A2", "Teste1");
		atividadeController.cadastraItem("A3", "Teste1");
		
		pesquisaController.associaAtividade("UNI1", "A1");
		pesquisaController.associaAtividade("UNI1", "A2");
		pesquisaController.associaAtividade("UNI1", "A3");
		
		pesquisaController.configuraEstrategia("MENOS_PENDENCIAS");
		
		assertEquals("A3", pesquisaController.proximaAtividade("UNI1"));
		
	}
	
	@Test
	void testProximaAtividadeMaiorDuracao() {
		atividadeController.cadastraAtividade("Fazer algo com os piquetes", "BAIXO", "Não há risco pq eu n sei pra que serve um piquete");
		atividadeController.cadastraAtividade("Fincar piquetes em frente ao LCC3", "ALTO", "Perigo de encontrar estudantes de cc no caminho");
		atividadeController.cadastraAtividade("Reclamar dos estudantes de cc", "ALTO", "Tem que ter coragem pq quando um estudante de cc pega ranço de alguem...");
		
		atividadeController.cadastraItem("A1", "Teste1");
		atividadeController.cadastraItem("A1", "Teste1");
		atividadeController.executaAtividade("A1", 1, 20);
		
		atividadeController.cadastraItem("A2", "Teste1");
		atividadeController.cadastraItem("A2", "Teste1");
		atividadeController.cadastraItem("A2", "Teste1");
		atividadeController.executaAtividade("A2", 1, 20);
		atividadeController.executaAtividade("A2", 2, 20);
		
		atividadeController.cadastraItem("A3", "Teste1");
		atividadeController.cadastraItem("A3", "Teste1");
		atividadeController.executaAtividade("A3", 1, 60);
		
		
		pesquisaController.associaAtividade("UNI1", "A1");
		pesquisaController.associaAtividade("UNI1", "A2");
		pesquisaController.associaAtividade("UNI1", "A3");
		
		pesquisaController.configuraEstrategia("MAIOR_DURACAO");
		
		assertEquals("A3", pesquisaController.proximaAtividade("UNI1"));
		
	}
	
	@Test
	void testProximaAtividadeCodigoVazioNulo() {
		atividadeController.cadastraAtividade("Fazer algo com os piquetes", "BAIXO", "Não há risco pq eu n sei pra que serve um piquete");
		atividadeController.cadastraAtividade("Fincar piquetes em frente ao LCC3", "ALTO", "Perigo de encontrar estudantes de cc no caminho");
		atividadeController.cadastraAtividade("Reclamar dos estudantes de cc", "ALTO", "Tem que ter coragem pq quando um estudante de cc pega ranço de alguem...");
		
		atividadeController.cadastraItem("A1", "Teste1");
		atividadeController.cadastraItem("A1", "Teste1");
		atividadeController.executaAtividade("A1", 1, 20);
		
		atividadeController.cadastraItem("A2", "Teste1");
		atividadeController.cadastraItem("A2", "Teste1");
		atividadeController.cadastraItem("A2", "Teste1");
		atividadeController.executaAtividade("A2", 1, 20);
		atividadeController.executaAtividade("A2", 2, 20);
		
		atividadeController.cadastraItem("A3", "Teste1");
		atividadeController.cadastraItem("A3", "Teste1");
		atividadeController.executaAtividade("A3", 1, 60);
		
		
		pesquisaController.associaAtividade("UNI1", "A1");
		pesquisaController.associaAtividade("UNI1", "A2");
		pesquisaController.associaAtividade("UNI1", "A3");
		
		pesquisaController.configuraEstrategia("MAIOR_DURACAO");
		
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.proximaAtividade("");
		});
		assertThrows(NullPointerException.class, () -> {
			pesquisaController.proximaAtividade(null);
		});
		
	}
	
	@Test
	void testProximaAtividadeSemPendencias() {
		atividadeController.cadastraAtividade("Fincar piquetes em frente ao LCC3", "ALTO", "Perigo de encontrar estudantes de cc no caminho");
		atividadeController.cadastraAtividade("Fazer algo com os piquetes", "BAIXO", "Não há risco pq eu n sei pra que serve um piquete");
		atividadeController.cadastraAtividade("Reclamar dos estudantes de cc", "ALTO", "Tem que ter coragem pq quando um estudante de cc pega ranço de alguem...");
		
		pesquisaController.associaAtividade("UNI1", "A1");
		pesquisaController.associaAtividade("UNI1", "A2");
		pesquisaController.associaAtividade("UNI1", "A3");
		
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.proximaAtividade("UNI1");
		});
		
	}
	
	@Test
	void testProximaAtividadePesquisaDesativada() {
		atividadeController.cadastraAtividade("Fincar piquetes em frente ao LCC3", "ALTO", "Perigo de encontrar estudantes de cc no caminho");
		atividadeController.cadastraAtividade("Fazer algo com os piquetes", "BAIXO", "Não há risco pq eu n sei pra que serve um piquete");
		atividadeController.cadastraAtividade("Reclamar dos estudantes de cc", "ALTO", "Tem que ter coragem pq quando um estudante de cc pega ranço de alguem...");
		
		atividadeController.cadastraItem("A1", "Teste1");
		atividadeController.cadastraItem("A2", "Teste1");
		atividadeController.cadastraItem("A3", "Teste1");
		
		pesquisaController.associaAtividade("UNI1", "A1");
		pesquisaController.associaAtividade("UNI1", "A2");
		pesquisaController.associaAtividade("UNI1", "A3");
		
		pesquisaController.encerraPesquisa("UNI1", "porque eu terminei ue");
		
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.proximaAtividade("UNI1");
		});
	}
	
	@Test
	void testProximaAtividadePesquisaInexistente() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaController.proximaAtividade("UNI2");
		});
	}
	
	

}
