package ordenacao;

import java.util.Comparator;
import pesquisa.Pesquisa;

public interface OrdenaPesquisa extends Comparator<Pesquisa>{
	@Override
	public int compare(Pesquisa ps1, Pesquisa ps2);

}
