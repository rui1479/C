import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaVintage {
    private Map<String, Utilizador> utilizadores;
    private List<Artigo> artigos;
    private List<Transportadora> transportadores;
    private List<Encomenda> encomendas;

    // Inicialização, getters e setters

    public void adicionarUtilizador(Utilizador user) {
         // Verifica se o mapa de utilizadores já foi inicializado
    if (utilizadores == null) {
        utilizadores = new HashMap<>();
    }
    
    // Adiciona o utilizador ao mapa utilizando o código como chave
    utilizadores.put(user.getCodigo(), user);
}

    public void adicionarArtigo(Artigo artigo) {
        // Verifica se a lista de sapatilhas já foi inicializada
        if (artigos == null) {
            artigos = new ArrayList<>();
        }

        
        // Adiciona a sapatilha à lista
        artigos.add(artigo);
}


    public void adicionarTransportador(Transportadora transportador) {
        if (transportadores == null) {
            transportadores = new ArrayList<>();
        }
        
        // Adiciona a transportadora à lista
        transportadores.add(transportador);
}

    public void criarEncomenda(Encomenda encomenda) {
        if (encomendas == null) {
            encomendas = new ArrayList<>();
        }
        encomendas.add(encomenda);
        
        // Outras operações relevantes, como atualizar o estado da encomenda, registrar a data de criação, etc.
}

   // public void avancarTempo(String data) {
     //   this.data = data;
   // }

    // Métodos adicionais conforme necessário

    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Utilizadores: ").append(this.utilizadores).append(", ");
    sb.append("Artigos: ").append(this.artigos).append(", ");
    sb.append("Transportadores: ").append(this.transportadores).append(", ");
    sb.append("Encomendas: ").append(this.encomendas).append("}");
    return sb.toString();
}
}
