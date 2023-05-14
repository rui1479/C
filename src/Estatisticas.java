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

public class Estatisticas {
    private Marketplace marketplace;
    private List<Encomenda> encomendas;
    private double taxaComissao;


    public Estatisticas() {
        this.marketplace = null;
        this.taxaComissao = 0;
        this.encomendas = new ArrayList<>();

    }

    public Estatisticas(Marketplace marketplace, double taxaComissao, int numero) {
        this.marketplace = marketplace;
        this.taxaComissao = taxaComissao;
        this.encomendas = new ArrayList<>();

    }

    public void addEncomenda(Encomenda encomenda) {
        this.encomendas.add(encomenda);
    }

    public double getTaxaComissao() {
        return this.taxaComissao;
    }

    public void setTaxaComissao(double taxaComissao) {
        this.taxaComissao = taxaComissao;
    }

    public int getTotalArtigosVendidos() {
        int total = 0;
        for (Utilizador utilizador : marketplace.getUtilizadores()) {
            total += utilizador.getVendas().size();
        }
        return total;
    }

    public int getTotalArtigosComprados() {
        int total = 0;
        for (Utilizador utilizador : marketplace.getUtilizadores()) {
            total += utilizador.getCompras().size();
        }
        return total;
    }

    public double getValorTotalVendas() {
        double total = 0.0;
        for (Utilizador utilizador : marketplace.getUtilizadores()) {
            total += utilizador.getValorTotalVendas();
        }
        return total;
    }

    public List<Encomenda> getEncomendasPorVendedor(Utilizador vendedor) {
        List<Encomenda> encomendasPorVendedor = new ArrayList<>();
        for (Encomenda encomenda : this.encomendas) {
            if (encomenda.getUtilizador().equals(vendedor)) {
                encomendasPorVendedor.add(encomenda);
            }
        }
        return encomendasPorVendedor;
    }

    public List<Utilizador> getMaioresVendedores(LocalDate inicio, LocalDate fim) {
        return marketplace.getUtilizadores().stream()
                .filter(u -> u.getDataUltimaVenda() != null && !u.getDataUltimaVenda().isBefore(inicio) && !u.getDataUltimaVenda().isAfter(fim))
                .sorted((u1, u2) -> Integer.compare(u2.getVendas().size(), u1.getVendas().size()))
                .collect(Collectors.toList());
    }

    public List<Utilizador> getMaioresCompradores(LocalDate inicio, LocalDate fim) {
        return marketplace.getUtilizadores().stream()
                .filter(u -> u.getDataUltimaCompra() != null && !u.getDataUltimaCompra().isBefore(inicio) && !u.getDataUltimaCompra().isAfter(fim))
                .sorted((u1, u2) -> Integer.compare(u2.getCompras().size(), u1.getCompras().size()))
                .collect(Collectors.toList());
    }

    public double getGanhosVintage() {
        double totalGanhos = 0.0;
        for (Utilizador utilizador : marketplace.getUtilizadores()) {
            totalGanhos += utilizador.getValorTotalVendas() * taxaComissao;
        }
        return totalGanhos;
    }

    public Transportadora getTransportadoraComMaiorVolumeDeFaturacao() {
        Map<Transportadora, Double> faturacaoPorTransportadora = new HashMap<>();
    
        for (Encomenda encomenda : encomendas) {
            for (Artigo artigo : encomenda.getCarrinho().values()) {
                Transportadora transportadora = artigo.getTransportadora();
                if (transportadora != null) {
                    double valorExpedicao = encomenda.getCustoExpediçao();
                    faturacaoPorTransportadora.put(transportadora, faturacaoPorTransportadora.getOrDefault(transportadora, 0.0) + valorExpedicao);
                }
            }
        }
    
        return faturacaoPorTransportadora.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    } 


public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Marketplace: ").append(this.marketplace).append(", ");
    sb.append("Encomendas: ").append(this.encomendas).append(", ");
    sb.append("Taxa de Comissão: ").append(this.taxaComissao).append("}");
    return sb.toString();
}
 
}  
