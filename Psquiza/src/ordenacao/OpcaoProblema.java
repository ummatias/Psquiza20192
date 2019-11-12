package ordenacao;

import pesquisa.Pesquisa;

public class OpcaoProblema implements OrdenaPesquisa{

	@Override
	public int compare(Pesquisa ps1, Pesquisa ps2) {
		if (ps1.getProblema() != null && ps2.getProblema() == null) {
			return -1;
		}
		
		if (ps1.getProblema() == null && ps2.getProblema() != null) {
			return 1;
		}
		
		return ps1.getCodigo().compareTo(ps2.getCodigo()) * -1;
	}

}
