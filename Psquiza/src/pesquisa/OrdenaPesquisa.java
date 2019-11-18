package pesquisa;

import java.util.Comparator;

public interface OrdenaPesquisa extends Comparator<Pesquisa>{
	@Override
	public int compare(Pesquisa ps1, Pesquisa ps2);

}
