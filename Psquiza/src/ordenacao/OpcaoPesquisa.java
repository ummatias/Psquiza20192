package ordenacao;

import pesquisa.Pesquisa;

public class OpcaoPesquisa implements OrdenaPesquisa {

	@Override
	public int compare(Pesquisa ps1, Pesquisa ps2) {
		return ps1.getCodigo().compareTo(ps2.getCodigo()) * -1;
	}
	

}
