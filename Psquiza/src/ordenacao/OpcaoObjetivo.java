package ordenacao;

import pesquisa.Pesquisa;

public class OpcaoObjetivo implements OrdenaPesquisa {

	@Override
	public int compare(Pesquisa ps1, Pesquisa ps2) {
		if (ps1.quntObjetivos() > ps2.quntObjetivos()) {
			return -1;
		}
		
		if (ps1.quntObjetivos() < ps2.quntObjetivos()) {
			return 1;
		}
		
		if (ps1.quntObjetivos() == ps2.quntObjetivos() && ps1.quntObjetivos() != 0 || ps2.quntObjetivos() != 0) {
			return ps1.ultimoObjetivo().getCodigo().compareTo(ps2.ultimoObjetivo().getCodigo());
		}
		
		return ps1.getCodigo().compareTo(ps2.getCodigo()) * -1;

	}

}
