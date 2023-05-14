import java.util.HashMap;
import java.util.Map;

public class Model {
    private Map<String, Artigo> artigos;
    private Map<String, Utilizador> utilizadores;
    private Map<String, Transportadora> transportadoras;

    public Model() {
        this.artigos = new HashMap<>();
        this.utilizadores = new HashMap<>();
        this.transportadoras = new HashMap<>();
    }

    public void addArtigo(Artigo artigo) {
        this.artigos.put(artigo.getCodigo(), artigo);
    }

    public Artigo getArtigo(String codigo) {
        return this.artigos.get(codigo);
    }

    public void updateArtigo(Artigo artigo) {
        this.artigos.put(artigo.getCodigo(), artigo);
    }

    public void deleteArtigo(String codigo) {
        this.artigos.remove(codigo);
    }

    public void addUtilizador(Utilizador utilizador) {
        this.utilizadores.put(utilizador.getEmail(), utilizador);
    }

    public Utilizador getUtilizador(String email) {
        return this.utilizadores.get(email);
    }

    public void updateUtilizador(Utilizador utilizador) {
        this.utilizadores.put(utilizador.getEmail(), utilizador);
    }

    public void deleteUtilizador(String email) {
        this.utilizadores.remove(email);
    }

    public void addTransportadora(Transportadora transportadora) {
        this.transportadoras.put(transportadora.getNome(), transportadora);
    }

    public Transportadora getTransportadora(String nome) {
        return this.transportadoras.get(nome);
    }

    public void updateTransportadora(Transportadora transportadora) {
        this.transportadoras.put(transportadora.getNome(), transportadora);
    }

    public void deleteTransportadora(String nome) {
        this.transportadoras.remove(nome);
    }
}
