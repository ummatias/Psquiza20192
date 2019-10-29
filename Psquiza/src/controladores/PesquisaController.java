package controladores;

import java.util.HashMap;
import java.util.Map;

import entidades.Pesquisa;
import validadores.ValidadorEntradas;

public class PesquisaController {
    private Map<String, Pesquisa> pesquisasCadastradas;
    private Map<String,Integer> chavesGeradas;

    public PesquisaController() {
        this.pesquisasCadastradas = new HashMap<>();
        this.chavesGeradas = new HashMap<>();
    }

    public String cadastraPesquisa(String descricao, String campoDeInteresse){
        ValidadorEntradas.validarString(campoDeInteresse,"Formato do campo de interesse invalido.");
        ValidadorEntradas.validarString(descricao,"Descricao nao pode ser nula ou vazia.");

        String codigo = this.geraCodigo(campoDeInteresse);
        Pesquisa pesquisa = new Pesquisa(codigo, descricao, campoDeInteresse);
        this.pesquisasCadastradas.put(codigo, pesquisa);
        return this.pesquisasCadastradas.get(codigo).getCodigo(codigo);
    }
    private String geraCodigo(String campoDeInteresse) {
        ValidadorEntradas.validarString(campoDeInteresse,"Formato do campo de interesse invalido.");
        String codigo = campoDeInteresse.substring(0,3).toUpperCase();

        if(!this.chavesGeradas.containsKey(codigo)) {
            this.chavesGeradas.put(codigo,0);

        }
        String codigoGerado = codigo + this.chavesGeradas.get(codigo) + 1;
        this.chavesGeradas.put(codigo,chavesGeradas.get(codigo) + 1);
        return codigoGerado;
    }
    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo){
        if(this.pesquisasCadastradas.containsKey(codigo)){
            this.pesquisasCadastradas.get(codigo).alteraPesquisa(conteudoASerAlterado,novoConteudo);
        }throw new IllegalArgumentException("Pesquisa nao encontrada");
    }
    public void encerraPesquisa(String codigo, String motivo){
        if(this.pesquisasCadastradas.containsKey(codigo)){
            this.pesquisasCadastradas.get(codigo).encerraPesquisa(motivo);
        }
    }
        public void ativaPesquisa(String codigo){
            if(this.pesquisasCadastradas.containsKey(codigo)){
                this.pesquisasCadastradas.get(codigo).ativaPesquisa();
            }throw new IllegalArgumentException("Pesquisa nao encontrada");
        }
}
