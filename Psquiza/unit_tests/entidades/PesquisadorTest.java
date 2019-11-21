package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pesquisador.Pesquisador;

class PesquisadorTest {
	private Pesquisador pesquisador1;
	private Pesquisador pesquisadorAllMight;
	
	@BeforeEach
	public void criaPesquisador() {
		this.pesquisador1 = new Pesquisador("Maria", "estudante", "tem interesse em inteligencia artificial",
				"maria@gmail.com", "http://www.minhafoto.com");
		this.pesquisadorAllMight = new Pesquisador("All Might", "professor", "Salva as pessoas com um sorriso",
				"allmight@hero.com", "http://bokunohero.com/allmight");
		
	}

	@Test
	void testEquals() {
		Pesquisador pesquisador2 = new Pesquisador("Ana Maria", "estudante", "tem interesse em inteligencia artificial",
				"maria@gmail.com", "http://www.minhafoto.com");
		assertEquals(pesquisador1, pesquisador2);

	}

	@Test
	void testHashcode() {
		Pesquisador pesquisador2 = new Pesquisador("Maria Clara", "Estudante", "tem interesse em analise de dados",
				"maria@gmail.com", "http://www.pinterest1.com");
		assertEquals(pesquisador1, pesquisador2);
	}

	@Test
	void testToString() {
		assertEquals(this.pesquisador1.toString(),
				"Maria (estudante) - tem interesse em inteligencia artificial - maria@gmail.com - http://www.minhafoto.com");
	}

	@Test
	void testNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("", "estudante", "pesquisa eficiencia de algoritmos",
					"pedro@gmail.com", "http://www.foto.com");
		});
	}

	@Test
	void testNomeNulo() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador(null, "estudante", "pesquisa eficiencia de algoritmos",
					"pedro@gmail.com", "http://www.foto.com");
		});
	}

	@Test
	void testFuncaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Pedro", "", "pesquisa eficiencia de algoritmos", "pedro@gmail.com",
					"http://www.foto.com");
		});
	}

	@Test
	void testFuncaoNula() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador("Pedro", null, "pesquisa eficiencia de algoritmos",
					"pedro@gmail.com", "http://www.foto.com");
		});
	}

	@Test
	void testBiografiaVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Pedro", "estudante", "", "pedro@gmail.com", "http://www.foto.com");
		});
	}

	@Test
	void testBiografiaNula() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador("Pedro", "estudante", null, "pedro@gmail.com",
					"http://www.foto.com");
		});
	}

	@Test
	void testEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos", "",
					"http://www.foto.com");
		});
	}

	@Test
	void testEmailNulo() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos", null,
					"http://www.foto.com");
		});
	}

	@Test
	void testFotoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos", "pedro@gmail.com",
					"");
		});
	}

	@Test
	void testFotoNula() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador("Pedro", "estudante", "pesquisa eficiencia de algoritmos",
					"pedro@gmail.com", null);
		});
	}

	@Test
	void testDesativa() {
		this.pesquisador1.desativa();
		assertEquals(this.pesquisador1.getStatus(), false);
	}
	@Test
	void testDesativaPesquisadorInativo() {
		this.pesquisador1.desativa();
		assertThrows(IllegalArgumentException.class, () -> {
			this.pesquisador1.desativa();
		});
		
	}

	@Test
	void testGetStatus() {
		assertEquals(this.pesquisador1.getStatus(), true);
	}
	
	@Test
	void testSetEspecialidadeProfessorSucesso() {
		pesquisadorAllMight.setEspecialidadeProfessor("Phd", "UA", "19/05/2016");
		assertEquals("All Might (professor) - Salva as pessoas com um sorriso - allmight@hero.com - http://bokunohero.com/allmight - Phd - UA - 19/05/2016", 
				pesquisadorAllMight.toString());
	}
	
	@Test
	void testSetEspecialidadeProfessorEmAluno() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador1.setEspecialidadeProfessor("Doutorado", "UFA", "19/08/2002");
		});
	}
	
	@Test
	void testSetEspecialidadeAlunorSucesso() {
		pesquisador1.setEspecialidadeAluno(6, 9);
		assertEquals("Maria (estudante) - tem interesse em inteligencia artificial - maria@gmail.com - http://www.minhafoto.com - 6o SEMESTRE - 9.0",
				pesquisador1.toString());
	}
	
	@Test
	void testSetEspecialidadeAlunoEmProfessor() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisadorAllMight.setEspecialidadeAluno(9, 2);
		});
	}


}
