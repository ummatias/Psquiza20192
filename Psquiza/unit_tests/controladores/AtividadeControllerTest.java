package controladores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.AtividadeController;

class AtividadeControllerTest {

	private AtividadeController atividadeController;

	@BeforeEach
	void instanciarController() {
		this.atividadeController = new AtividadeController();
		atividadeController.cadastraAtividade("Coletar dados de análises fisico quimicas com polpas de fruta de manga",
				"MEDIO", "O manuseio incorreto de ferramentas de laboratório pode causar riscos.");

	}

	@Test
	void testCadastraAtividadeComSucesso() {
		atividadeController.cadastraAtividade("Aplicação de Questionários para estudantes", "BAIXO",
				"Não há riscos em se aplicar um questionario.");

		assertEquals(
				"Aplicação de Questionários para estudantes (BAIXO - Não há riscos em se aplicar um questionario.)",
				atividadeController.exibeAtividade("A2"));
	}

	@Test
	void testCadastraAtividadeDescricaoVaziaNula() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.cadastraAtividade("  ", "BAIXO", "Não há riscos em se aplicar um questionario.");
		});

		assertThrows(NullPointerException.class, () -> {
			atividadeController.cadastraAtividade(null, "BAIXO", "Não há riscos em se aplicar um questionario.");
		});

	}

	@Test
	void testCadastraAtividadeRiscoVazioNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.cadastraAtividade("Aplicação de Questionários para estudantes", "  ",
					"Não há riscos em se aplicar um questionario.");
		});

		assertThrows(NullPointerException.class, () -> {
			atividadeController.cadastraAtividade("Aplicação de Questionários para estudantes", null,
					"Não há riscos em se aplicar um questionario.");
		});

	}

	@Test
	void testCadastraAtividadeDescRiscoVaziaNula() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.cadastraAtividade("Aplicação de Questionários para estudantes", "BAIXO", "");
		});

		assertThrows(NullPointerException.class, () -> {
			atividadeController.cadastraAtividade("Aplicação de Questionários para estudantes", "BAIXO", null);
		});

	}

	@Test
	void testApagaAtividadeComSucesso() {
		atividadeController.cadastraAtividade("Aplicação de Questionários para estudantes", "BAIXO",
				"Não há riscos em se aplicar um questionario.");

		atividadeController.apagaAtividade("A2");

		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.exibeAtividade("A2");
		});
	}

	@Test
	void testApagaAtividadeInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.apagaAtividade("A2");
		});
	}

	@Test
	void testApagaAtividadeCodigoVazioNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.apagaAtividade("");
		});

		assertThrows(NullPointerException.class, () -> {
			atividadeController.apagaAtividade(null);
		});
	}

	@Test
	void testCadastraItemComSucesso() {
		atividadeController.cadastraItem("A1", "Comprar as polpas");

		assertEquals(
				"Coletar dados de análises fisico quimicas com polpas de fruta de manga (MEDIO - O manuseio incorreto de ferramentas de laboratório pode causar riscos.)"
						+ " | PENDENTE - Comprar as polpas",
				atividadeController.exibeAtividade("A1"));

	}

	@Test
	void testCadastraItemCodigoInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.cadastraItem("A2", "Comprar as polpas");
		});

	}

	@Test
	void testCadastraItemCodigoVazioNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.cadastraItem("", "Comprar as polpas");
		});

		assertThrows(NullPointerException.class, () -> {
			atividadeController.cadastraItem(null, "Comprar as polpas");
		});

	}

	@Test
	void testCadastraItemVazioNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.cadastraItem("A1", " ");
		});

		assertThrows(NullPointerException.class, () -> {
			atividadeController.cadastraItem("A1", null);
		});

	}

	@Test
	void testExibeAtividadeComSucesso() {
		atividadeController.cadastraItem("A1", "Comprar as polpas");
		atividadeController.cadastraItem("A1", "Preparar laboratório");

		assertEquals(
				"Coletar dados de análises fisico quimicas com polpas de fruta de manga (MEDIO - O manuseio incorreto de ferramentas de laboratório pode causar riscos.)"
						+ " | PENDENTE - Comprar as polpas | PENDENTE - Preparar laboratório",
				atividadeController.exibeAtividade("A1"));

	}

	@Test
	void testExibeAtividadeInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.exibeAtividade("A2");
		});

	}

	@Test
	void testExibeAtividadeCodigoVazioNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.exibeAtividade("");
		});

		assertThrows(NullPointerException.class, () -> {
			atividadeController.exibeAtividade(null);
		});

	}

	@Test
	void testExibeAtividadeSemItens() {
		assertEquals(
				"Coletar dados de análises fisico quimicas com polpas de fruta de manga (MEDIO - O manuseio incorreto de ferramentas de laboratório pode causar riscos.)",
				atividadeController.exibeAtividade("A1"));
	}

	@Test
	void testContaItensPendentes() {
		atividadeController.cadastraItem("A1", "Comprar as polpas");
		atividadeController.cadastraItem("A1", "Preparar laboratório");

		assertEquals(2, atividadeController.contaItensPendentes("A1"));
	}

	@Test
	void testContaItensPendentesSemItens() {
		assertEquals(0, atividadeController.contaItensPendentes("A1"));
	}

	@Test
	void testContaItensPendentesCodigoVazioNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.contaItensPendentes("");
		});

		assertThrows(NullPointerException.class, () -> {
			atividadeController.contaItensPendentes(null);
		});
	}

	@Test
	void testContaItensRealizados() {
		atividadeController.cadastraItem("A1", "Comprar as polpas");
		atividadeController.cadastraItem("A1", "Preparar laboratório");

		assertEquals(0, atividadeController.contaItensRealizados("A1"));
	}

	// cdu 7 - executa atividade

	@Test
	void testExecutaAtividadeCodigoVazioNulo() {

		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.executaAtividade("", 1, 100);
		});

		assertThrows(NullPointerException.class, () -> {
			atividadeController.executaAtividade(null, 1, 100);
		});

	}

	// testes cdu 9

	@Test
	void testDefineProximaAtividadeNula() {
		assertThrows(NullPointerException.class, () -> {
			atividadeController.defineProximaAtividade(null, "A1");
			;
		});

	}

	@Test
	void testDefineProximaAtividadeSegundaAtividadeNula() {
		assertThrows(NullPointerException.class, () -> {
			atividadeController.defineProximaAtividade("A1", null);
			;
		});
	}

	@Test
	void testDefineProximaAtividadeVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.defineProximaAtividade("", "A1");
			;
		});
	}

	@Test
	void testDefineProximaAtividadeSegundaAtividadeVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.defineProximaAtividade("A1", "");
			;
		});
	}

	@Test
	void testDefineProximaAtividadeNaoEncontrada() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.defineProximaAtividade("A2", "A1");
			;
		});
	}

	@Test
	void testDefineProximaAtividadeSegundoSubsequente() {
		this.atividadeController.cadastraAtividade("aplicacao de provas", "ALTO", "provas sao arriscadas");
		this.atividadeController.cadastraAtividade("fazer eleicoes", "ALTO", "grande bipolarizacao");
		this.atividadeController.cadastraAtividade("fazer questionario", "BAIXO", "nao ha risco");
		this.atividadeController.defineProximaAtividade("A2", "A1");
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.defineProximaAtividade("A2", "A3");
			;
		});

	}

	@Test
	void testContaProximosComSucesso() {
		this.atividadeController.cadastraAtividade("aplicacao de provas", "ALTO", "provas sao arriscadas");
		this.atividadeController.cadastraAtividade("fazer eleicoes", "ALTO", "grande bipolarizacao");
		this.atividadeController.defineProximaAtividade("A2", "A1");
		assertEquals(1, this.atividadeController.contaProximos("A2"));
	}

	@Test
	void testContaProximosNUlo() {
		assertThrows(NullPointerException.class, () -> {
			atividadeController.contaProximos(null);
		});
	}

	@Test
	void testContaProximosVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.contaProximos("");
		});
	}

	@Test
	void testContaProximosAtividadeNaoEncontrada() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.contaProximos("A5");
		});
	}

	@Test
	void testRetiraProximoComSucesso() {
		this.atividadeController.cadastraAtividade("aplicacao de provas", "ALTO", "provas sao arriscadas");
		this.atividadeController.cadastraAtividade("fazer eleicoes", "ALTO", "grande bipolarizacao");
		this.atividadeController.defineProximaAtividade("A2", "A1");
		this.atividadeController.tiraProximaAtividade("A2");
		assertEquals(0, this.atividadeController.contaProximos("A2"));
	}

	@Test
	void testRetiraProximoNulo() {
		assertThrows(NullPointerException.class, () -> {
			atividadeController.tiraProximaAtividade(null);
		});
	}

	@Test
	void testRetiraProximoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.tiraProximaAtividade("");
		});
	}

	@Test
	void testPegaProximoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.pegaProximo("", 1);
		});
	}

	@Test
	void testPegaProximoNulo() {
		assertThrows(NullPointerException.class, () -> {
			atividadeController.pegaProximo(null, 1);
		});
	}

	@Test
	void testPegaProximoZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.pegaProximo("A2", 0);
		});
	}

	@Test
	void testPegaProxioNegativo() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.pegaProximo("A2", -1);
		});
	}

	@Test
	void testPegaMaiorRiscoNulo() {
		assertThrows(NullPointerException.class, () -> {
			atividadeController.pegaMaiorRiscoAtividades(null);
		});
	}

	@Test
	void testPegaMaiorRiscoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.pegaMaiorRiscoAtividades("");
		});
	}

	@Test
	void testPegaMaiorRiscoAtividadeInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.pegaMaiorRiscoAtividades("A3");
		});
	}

	@Test
	void testPegaMaiorRiscoComSucesso() {
		this.atividadeController.cadastraAtividade("aplicacao de provas", "ALTO", "provas sao arriscadas");
		this.atividadeController.cadastraAtividade("fazer eleicoes", "BAIXO", "grande bipolarizacao");
		this.atividadeController.cadastraAtividade("vender comida", "BAIXO", "nao ha risoc");
		this.atividadeController.defineProximaAtividade("A2", "A1");
		this.atividadeController.defineProximaAtividade("A1", "A3");
		assertEquals("A1", this.atividadeController.pegaMaiorRiscoAtividades("A2"));
	}

	@Test
	void testPegaMaiorRiscoSemProximaAtividade() {
		this.atividadeController.cadastraAtividade("aplicacao de provas", "ALTO", "provas sao arriscadas");
		this.atividadeController.cadastraAtividade("fazer eleicoes", "BAIXO", "grande bipolarizacao");
		this.atividadeController.defineProximaAtividade("A2", "A1");
		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.pegaMaiorRiscoAtividades("A1");
		});

	}

}
