package controladores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PesquisadorControllerTest {

	private PesquisadorController controller;
	
	@BeforeEach
	public void CriaController() {
		this.controller = new PesquisadorController();
		this.controller.cadastraPesquisador("Maria", "estudante", "tem interesse em inteligencia artificial","maria@gmail.com","http://www.minhafoto.com");
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
	void testCadastraPesquisadorEmailInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos", "@gmail.com",
					"http://www.foto.com");
		});
	}

	@Test
	void testCadastraPesquisadorEmailInvalido2() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.controller.cadastraPesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos", "1@",
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
			this.controller.alteraPesquisador("pedro@gmail.com", "nome", "Pedro Silva");
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
			this.controller.exibePesquisador("maria@gmail.com");
		});
	}

}
