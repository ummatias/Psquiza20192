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

		assertThrows(IllegalArgumentException.class, () -> {
			atividadeController.apagaAtividade(null);
		});
	}

	@Test
	void testCadastraItemComSucesso() {
		atividadeController.cadastraItem("A1", "Comprar as polpas");

		assertEquals(
				"Coletar dados de análises fisico quimicas com polpas de fruta de manga (MEDIO - O manuseio incorreto de ferramentas de laboratório pode causar riscos.):"
						+ "\n- PENDENTE - Comprar as polpas",
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
		assertThrows(IllegalArgumentException.class, () ->{
			atividadeController.contaItensPendentes("");
		});
		
		assertThrows(NullPointerException.class, () ->{
			atividadeController.contaItensPendentes(null);
		});
	}
	
	@Test
	void testContaItensRealizados() {
		atividadeController.cadastraItem("A1", "Comprar as polpas");
		atividadeController.cadastraItem("A1", "Preparar laboratório");

		assertEquals(0, atividadeController.contaItensRealizados("A1"));
	}

}
