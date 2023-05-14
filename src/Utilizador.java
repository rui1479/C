import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.math.BigInteger;
import java.util.Comparator;

public class Utilizador {
    private String nome;
    private String morada;
    private String email;
    private String codigo;
    private String numero_fiscal;
    private Map<String, Artigo> artigos; // artigos que o utilizador está a vender
    private Map<String, Artigo> compras; // artigos que o utilizador já comprou
    private Map<String, Artigo> vendas; // artigos que o utilizador já vendeu
    private double valor_total_vendas;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 10;

    public Utilizador() {
        this.nome = "";
        this.morada = "";
        this.email = "";
        this.codigo = "";
        this.numero_fiscal = "";
        this.artigos = new HashMap<>();
        this.compras = new HashMap<>();
        this.vendas = new HashMap<>();
        this.valor_total_vendas = 0.0;
    }

    public Utilizador(String nome, String morada, String email, String codigo, String numero_fiscal,
            Map<String, Artigo> artigos, Map<String, Artigo> compras,
            Map<String, Artigo> vendas, double valor_total_vendas) {
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.codigo = codigo;
        this.numero_fiscal = numero_fiscal;
        this.artigos = artigos;
        this.compras = compras;
        this.vendas = vendas;
        this.valor_total_vendas = valor_total_vendas;
    }

    public Utilizador(String nome, String morada, String email, String numero_fiscal) {
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.codigo = defineCodigoAN();
        this.numero_fiscal = numero_fiscal;
        this.artigos = new HashMap<>();
        this.compras = new HashMap<>();
        this.vendas = new HashMap<>();
        this.valor_total_vendas = 0;
    }

    public Utilizador(Utilizador outro) {
        this.nome = outro.nome;
        this.morada = outro.morada;
        this.email = outro.email;
        this.codigo = defineCodigoAN();
        this.numero_fiscal = outro.numero_fiscal;
        this.artigos = outro.artigos;
        this.compras = outro.compras;
        this.vendas = outro.vendas;
        this.valor_total_vendas = outro.valor_total_vendas;
    }

    // sets
    public void setArtigo(Map<String, Artigo> artigos) {
        this.artigos = artigos;
    }

    public void setVendas(Map<String, Artigo> vendas) {
        this.vendas = vendas;
    }

    public void setCompras(Map<String, Artigo> compras) {
        this.compras = compras;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String codigo) {
        this.codigo = codigo;
    }

    public void setNumeroFiscal(String numero_fiscal) {
        this.numero_fiscal = numero_fiscal;
    }

    public void setValorTotalVendas(double valor_total_vendas) {
        this.valor_total_vendas = valor_total_vendas;
    }

    // gets
    public Map<String, Artigo> getArtigo() {
        return this.artigos;
    }

    public Map<String, Artigo> getVendas() {
        return this.vendas;
    }

    public Map<String, Artigo> getCompras() {
        return this.compras;
    }

    public String getNome() {
        return this.nome;
    }

    public String getMorada() {
        return this.morada;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getPassword() {
        return this.codigo;
    }

    public String getNumeroFiscal() {
        return this.numero_fiscal;
    }

    public double getValorTotalVendas() {
        return this.valor_total_vendas;
    }

    public boolean matches(String query) {
        // Verifica se algum atributo do Utilizador corresponde à consulta
        if (nome.toLowerCase().contains(query)) {
            return true;
        }
        if (email.toLowerCase().contains(query)) {
            return true;
        }
        // Verificar outros atributos relevantes para a correspondência

        // Caso nenhum atributo corresponda à consulta, retorna falso
        return false;
    }

    public static String defineCodigoAN() {
        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            codeBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }

    public Artigo getArtigo(String cod) {
        if (!artigos.containsKey(cod)) {
            return null;
        }
        return this.artigos.get(cod).clone();
    }

    public void adicionarProdutoVendido(Artigo artigo, double valorVenda) {
        artigo.setValorVenda(valorVenda);
        vendas.put(artigo.getCodigo(), artigo);
        valor_total_vendas += valorVenda;
    }

    public void addValorTotaldasVendas(double valor_compra) {
        this.valor_total_vendas += valor_compra;
    }

    public void adicionarProdutoAVenda(Artigo produto, Transportadora transportadora) {
        produto.setTransportadora(transportadora);
        this.vendas.put(produto.getCodigo(), produto);
        produto.setVendedor(this);
    }

    public void removerProdutoAVenda(Artigo artigo) {
        if (this.vendas.containsKey(artigo.getCodigo())) {
            this.vendas.remove(artigo.getCodigo());
        }
    }

    public void adicionarProdutoAdquirido(Artigo artigo) {
        if (!this.compras.containsKey(artigo.getCodigo())) {
            this.compras.put(artigo.getCodigo(), artigo);
        }
    }

    public void adicionarVendaEfetuada(Artigo venda) {
        if (!this.vendas.containsKey(venda.getCodigo())) {
            this.vendas.put(venda.getCodigo(), venda);
        }
    }

    public List<Artigo> getArtigosVendidos() {
        return this.vendas.values().stream()
                .collect(Collectors.toList());
    }

    public List<Artigo> getArtigosComprados() {
        return this.compras.values().stream()
                .collect(Collectors.toList());
    }

    public LocalDate getDataUltimaVenda() {
        return this.vendas.values().stream()
                .max(Comparator.comparing(Artigo::getDataVenda))
                .map(Artigo::getDataVenda)
                .orElse(null);
    }

    public LocalDate getDataUltimaCompra() {
        return this.compras.values().stream()
                .max(Comparator.comparing(Artigo::getDataCompra))
                .map(Artigo::getDataCompra)
                .orElse(null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.nome).append(", ");
        sb.append("Morada: ").append(this.morada).append(", ");
        sb.append("Email: ").append(this.email).append(", ");
        sb.append("Código: ").append(this.codigo).append(", ");
        sb.append("Número Fiscal: ").append(this.numero_fiscal).append(", ");
        sb.append("Artigos à Venda: ").append(this.artigos).append(", ");
        sb.append("Compras Realizadas: ").append(this.compras).append(", ");
        sb.append("Vendas Realizadas: ").append(this.vendas).append(", ");
        sb.append("Valor Total de Vendas: ").append(this.valor_total_vendas).append("}");
        return sb.toString();
    }

}