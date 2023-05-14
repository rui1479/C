import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Marketplace {
    private List<Utilizador> utilizadores;
    private List<Artigo> produtos;
    private List<Encomenda> encomendas;

    public Marketplace() {
        utilizadores = new ArrayList<>();
        produtos = new ArrayList<>();
        encomendas = new ArrayList<>();
    }

    // Métodos para gerir utilizadores
    public void adicionarUtilizador(Utilizador utilizador) {
        if (!utilizadores.contains(utilizador)) {
            utilizadores.add(utilizador);
        }
    }
    

    public void removerUtilizador(Utilizador utilizador) {
        utilizadores.remove(utilizador);
    }

    public List<Utilizador> getUtilizadores() {
        return this.utilizadores;
    }

    // Métodos para gerir produtos
    public void adicionarProduto(Artigo produto) {
        produtos.add(produto);
    }

    public void removerProduto(Artigo produto) {
        produtos.remove(produto);
    }

    public List<Artigo> getProdutos() {
        return this.produtos;
    }

    // Métodos para gerir encomendas
    public void adicionarEncomenda(Encomenda encomenda) {
        encomendas.add(encomenda);
    }

    public void removerEncomenda(Encomenda encomenda) {
        encomendas.remove(encomenda);
    }

    public List<Encomenda> getEncomendas() {
        return this.encomendas;
    }

    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Utilizadores: ").append(this.utilizadores).append(", ");
    sb.append("Produtos: ").append(this.produtos).append(", ");
    sb.append("Encomendas: ").append(this.encomendas).append("}");
    return sb.toString();
}
}

