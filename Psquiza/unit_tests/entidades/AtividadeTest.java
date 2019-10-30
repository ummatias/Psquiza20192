package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.Atividade;

class AtividadeTest {

	private Atividade atividade;

	@BeforeEach
	void instanciarAtividade() {
		this.atividade = new Atividade("A1", "Aplicação de questionários para estudantes", "BAIXO",
				"Sem riscos ao aplicar questionários.");
	}

	@Test
	void testConstrutorCodigoVazioNulo() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Atividade(" ", "Aplicação de questionários para estudantes", "BAIXO",
					"Sem riscos ao aplicar questionários.");
		});
		
		assertThrows(NullPointerException.class, () ->{
			new Atividade(null, "Aplicação de questionários para estudantes", "BAIXO",
					"Sem riscos ao aplicar questionários.");
		});
	}
	
	@Test
	void testEqualsTrue() {
		Atividade atv2 = new Atividade("A1", "Questionários", "BAIXO", "Sem riscos.");

		assertTrue(this.atividade.equals(atv2));
	}

	@Test
	void testEqualsFalse() {
		Atividade atv2 = new Atividade("A2", "Questionários", "BAIXO", "Sem riscos.");

		assertFalse(this.atividade.equals(atv2));
	}

	@Test
	void testHashCodeIgual() {
		Atividade atv2 = new Atividade("A1", "Questionários", "BAIXO", "Sem riscos.");

		assertEquals(this.atividade.hashCode(), atv2.hashCode());
	}

	@Test
	void testHashCodeDiferente() {
		Atividade atv2 = new Atividade("A2", "Questionários", "BAIXO", "Sem riscos.");

		assertFalse(this.atividade.hashCode() == atv2.hashCode());
	}

	@Test
	void testToStringSemItens() {
		assertEquals("Aplicação de questionários para estudantes (BAIXO - Sem riscos ao aplicar questionários.)",
				this.atividade.toString());
	}

	@Test
	void testToStringComItens() {
		atividade.addItem("Pedir permissão aos alunos");

		assertEquals(
				"Aplicação de questionários para estudantes (BAIXO - Sem riscos ao aplicar questionários.) | PENDENTE - Pedir permissão aos alunos",
				atividade.toString());
	}

	@Test
	void testAddItemSucesso() {
		atividade.addItem("Pedir permissão aos alunos");

		assertEquals(
				"Aplicação de questionários para estudantes (BAIXO - Sem riscos ao aplicar questionários.) | PENDENTE - Pedir permissão aos alunos",
				atividade.toString());
	}

	@Test
	void testContaItensPendentes() {
		atividade.addItem("Pedir permissão aos alunos");

		assertEquals(1, atividade.contaItensPendentes());
	}
	
	@Test
	void testContaItensPendentesSemItens() {
		assertEquals(0, atividade.contaItensPendentes());
	}
	
	@Test
	void testContaItensRealizados() {
		atividade.addItem("Pedir permissão aos alunos");

		assertEquals(0, atividade.contaItensRealizados());
	}

}