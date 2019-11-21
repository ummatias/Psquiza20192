package controladores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pesquisador.PesquisadorController;

class PesquisadorControllerTest {

	private PesquisadorController controller;
	
	@BeforeEach
	public void CriaController() {
		this.controller = new PesquisadorController();
		this.controller.cadastraPesquisador("Maria", "estudante", "tem interesse em inteligencia artificial","maria@gmail.com","http://www.minhafoto.com");
		this.controller.cadastraPesquisador("Midoriya", "estudante", "Não tinha poder, agr é apelão", "deku@hero.com", "http://bokunohero.com/midoriya");
		this.controller.cadastraPesquisador("Aizawa", "professor", "Quando a luz dos olhos teus e a luz dos olhos meus...", "aizawa@hero.com", "http://bokunohero.com/aizawa");
		this.controller.cadastraPesquisador("Flora", "Estudante", "Winx mais perfeita das Winx", "flora@winx.com", "http://fotosdaswinx.com/flora");
		this.controller.cadastraPesquisador("Tecna", "Professor", "Winx que conserta impressora", "tecna@winx.com", "http://fotosdaswinx.com/tecna");
	
	}

	@Test
	void testCadastraPesquisador() {
		assertEquals(controller.exibePesquisador("maria@gmail.com"),
				"Maria (estudante) - tem interesse em inteligencia artificial - maria@gmail.com - http://www.minhafoto.com");
	}

	@Test
	void testCadastraPesquisadorNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraPesquisador("", "estudante", "pesquisa eficiencia de algoritmos", "pedro@gmail.com",
					"http://www.foto.com");
		});
	}

	@Test
	void testCadastraPesquisadorNomeNulo() {
		assertThrows(NullPointerException.class, () -> {
			this.controller.cadastraPesquisador(null, "estudante", "pesquisa eficiencia de algoritmos",
					"pedro@gmail.com", "http://www.foto.com");
		});
	}

	@Test
	void testCadastraPesquisadorFuncaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "", "pesquisa eficiencia de algoritmos", "pedro@gmail.com",
					"http://www.foto.com");
		});
	}

	@Test
	void testCadastraPesquisadorFuncaoNula() {
		assertThrows(NullPointerException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", null, "pesquisa eficiencia de algoritmos", "pedro@gmail.com",
					"http://www.foto.com");
		});
	}

	@Test
	void testCadastraPesquisadorBiografiaVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "estudante", "", "pedro@gmail.com", "http://www.foto.com");
		});
	}

	@Test
	void testCadastraPesquisadorBiografiaNula() {
		assertThrows(NullPointerException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "estudante", null, "pedro@gmail.com", "http://www.foto.com");
		});
	}

	@Test
	void testCadastraPesquisadorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos", "",
					"http://www.foto.com");
		});
	}

	@Test
	void testCadastraPesquisadorEmailNulo() {
		assertThrows(NullPointerException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos", null,
					"http://www.foto.com");
		});
	}

	@Test
	void testCadastraPesquisadorFotoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos", "pedro@gmail.com", "");
		});
	}

	@Test
	void testCadastraPesquisadorFotoNula() {
		assertThrows(NullPointerException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos",
					"pedro@gmail.com", null);
		});
	}

	@Test
	void testCadastraPesquisadorEmailInicioInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos", "@gmail.com",
					"http://www.foto.com");
		});
	}

	@Test
	void testCadastraPesquisadorEmailFiminvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos", "1@",
					"http://www.foto.com");
		});
	}
	@Test
	void testCadastraPesquidadorEmailSemArroba() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos", "pedro.123",
					"http://www.foto.com");
		});
	
	}

	@Test
	void testCadastraPesquisadorFotoInvalida() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos",
					"pedro@gmail.com", "www.foto.com");
		});
	}

	@Test
	void testAlteraPesquisadorNaoCadastrado() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.alteraPesquisador("pedro@gmail.com", "NOME", "Pedro Silva");
		});

	}

	@Test
	void testAtivaPesquisadorNaoCadastrado() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.ativaPesquisador("pedro@gmail.com");
		});
	}

	@Test
	void testDesativaPesquisadorNaoCadastrado() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.desativaPesquisador("pedro@gmail.com");
		});
	}

	@Test
	void testExibePesquisadorNaoCadastrado() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.exibePesquisador("pedro@gmail.com");
		});
	}

	@Test
	void testAlteraPesquisadorInativo() {
		this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa de algoritmos", "pedro@gmail.com",
				"http://fotominha.com.br");
		this.controller.desativaPesquisador("pedro@gmail.com");
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.alteraPesquisador("pedro@gmail.com", "nome", "Pedro Silva");
		});
	}

	@Test
	void testDesativaPesquisadorInativo() {
		this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa de algoritmos", "pedro@gmail.com",
				"http://fotominha.com.br");
		this.controller.desativaPesquisador("pedro@gmail.com");
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.desativaPesquisador("pedro@gmail.com");
		});
	}

	@Test
	void testExibePesquisadorInativo() {
		this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa de algoritmos", "pedro@gmail.com",
				"http://fotominha.com.br");
		this.controller.desativaPesquisador("pedro@gmail.com");
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.exibePesquisador("pedro@gmail.com");
		});
	}

	@Test
	void testAtivaPesquisadorAtivo() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.ativaPesquisador("maria@gmail.com");
		});
	}
	
	@Test
	void testCadastraEspecialidadeProfessorComSucesso() {
		controller.cadastraEspecialidadeProfessor("aizawa@hero.com", "Doutorado", "UA", "19/05/2016");
		
		assertEquals("Aizawa (professor) - Quando a luz dos olhos teus e a luz dos olhos meus... - aizawa@hero.com - http://bokunohero.com/aizawa - Doutorado - UA - 19/05/2016",
				controller.exibePesquisador("aizawa@hero.com"));
	}
	
	@Test
	void testCadastraEspecialidadeProfessorInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraEspecialidadeProfessor("allmight@hero.com", "Phd", "UA", "19/05/2016");
		});
	}
	
	@Test
	void testCadastraEspecialidadeProfessorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraEspecialidadeProfessor("", "Phd", "UA", "19/05/2016");
		});
	}
	
	@Test
	void testCadastraProfessorEmailNulo() {
		assertThrows(NullPointerException.class, () -> {
			controller.cadastraEspecialidadeProfessor(null, "Phd", "UA", "19/05/2016");
		});
	}
	
	@Test
	void testCadastraEspecialidadeAlunoComSucesso() {
		controller.cadastraEspecialidadeAluno("deku@hero.com", 1, 9);
		
		assertEquals("Midoriya (estudante) - Não tinha poder, agr é apelão - deku@hero.com - http://bokunohero.com/midoriya - 1o SEMESTRE - 9.0", 
				controller.exibePesquisador("deku@hero.com"));
	}
	
	@Test
	void testCadastraEspecialidadeAlunoInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraEspecialidadeAluno("todoroki@hero.com", 1, 10);
		});
	}
	
	@Test
	void testCadastraEspecialidadeAlunoEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraEspecialidadeAluno("", 6, 9);
		});
	}
	
	@Test
	void testCadastraEspecialidadeAlunoEmailNulo() {
		assertThrows(NullPointerException.class, () -> {
			controller.cadastraEspecialidadeAluno(null, 6, 8);
		});
	}
	
	@Test
	void testCadastraEspecialidadeAlunoSemestreLimiteAbaixo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraEspecialidadeAluno("deku@hero.com", 0, 9);
		});
	}
	
	@Test
	void testCadastraEspecialidadeAlunoSemestreNegativo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraEspecialidadeAluno("deku@hero.com", -2, 9);
		});
	}
	
	@Test
	void testCadastraEspecialidadeAlunoIDANegativo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraEspecialidadeAluno("deku@hero.com", 6, -9);
		});
	}
	
	
	@Test
	void testlistaPesquisadoresProfessorComSucesso() {
		controller.cadastraPesquisador("Stella", "Professor", "Winx contra protetor solar", "stella@winx.com", "http://fotosdaswinx.com/stella");
		controller.cadastraPesquisador("Musa", "Professor", "Winx artista", "musa@winx.com", "http://fotosdaswinx.com/musa");
		
		controller.cadastraEspecialidadeProfessor("stella@winx.com", "Mestrado", "Alfea", "28/01/2004");
		controller.cadastraEspecialidadeProfessor("musa@winx.com", "Graduação", "Alfea", "28/01/2004");
		
		assertEquals("Aizawa (professor) - Quando a luz dos olhos teus e a luz dos olhos meus... - aizawa@hero.com - http://bokunohero.com/aizawa | "
				+ "Tecna (Professor) - Winx que conserta impressora - tecna@winx.com - http://fotosdaswinx.com/tecna | "
				+ "Stella (Professor) - Winx contra protetor solar - stella@winx.com - http://fotosdaswinx.com/stella - Mestrado - Alfea - 28/01/2004 | "
				+ "Musa (Professor) - Winx artista - musa@winx.com - http://fotosdaswinx.com/musa - Graduação - Alfea - 28/01/2004",
				controller.listaPesquisadores("PROFESSOR"));
	}
	
	@Test
	void testListaPesquisadoresTipoInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.listaPesquisadores("EXPULSOS");
		});
	}
	
	@Test
	void testListaPesquisadoresTipoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.listaPesquisadores("");
		});
	}
	
	
	@Test
	void testListaPesquisadoresTipoNulo() {
		assertThrows(NullPointerException.class, () -> {
			controller.listaPesquisadores(null);
		});
	}
	
	@Test
	void testListaPesquisadoresExternosComSucesso() {
		controller.cadastraPesquisador("Stella", "Externo", "Winx contra protetor solar", "stella@winx.com", "http://fotosdaswinx.com/stella");
		controller.cadastraPesquisador("Musa", "Externo", "Winx artista", "musa@winx.com", "http://fotosdaswinx.com/musa");
		
		assertEquals("Stella (Externo) - Winx contra protetor solar - stella@winx.com - http://fotosdaswinx.com/stella | "
				+ "Musa (Externo) - Winx artista - musa@winx.com - http://fotosdaswinx.com/musa",
				controller.listaPesquisadores("EXTERNO"));
		
	}
	
	@Test
	void testListaPesquisadoresEstudanteComSucesso() {
		controller.cadastraPesquisador("Stella", "Estudante", "Winx contra protetor solar", "stella@winx.com", "http://fotosdaswinx.com/stella");
		controller.cadastraPesquisador("Musa", "Estudante", "Winx artista", "musa@winx.com", "http://fotosdaswinx.com/musa");
		
		controller.cadastraEspecialidadeAluno("flora@winx.com", 8, 5);
		controller.cadastraEspecialidadeAluno("stella@winx.com", 7, 9);
		controller.cadastraEspecialidadeAluno("musa@winx.com", 6, 10);
		
		assertEquals("Midoriya (estudante) - Não tinha poder, agr é apelão - deku@hero.com - http://bokunohero.com/midoriya | "
				+ "Flora (Estudante) - Winx mais perfeita das Winx - flora@winx.com - http://fotosdaswinx.com/flora - 8o SEMESTRE - 5.0 | "
				+ "Stella (Estudante) - Winx contra protetor solar - stella@winx.com - http://fotosdaswinx.com/stella - 7o SEMESTRE - 9.0 | "
				+ "Maria (estudante) - tem interesse em inteligencia artificial - maria@gmail.com - http://www.minhafoto.com | "
				+ "Musa (Estudante) - Winx artista - musa@winx.com - http://fotosdaswinx.com/musa - 6o SEMESTRE - 10.0",
				controller.listaPesquisadores("ESTUDANTE"));
	}
	
}
