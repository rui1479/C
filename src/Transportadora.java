import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class Transportadora {
    private String nome;
    private double margemLucro;
    private boolean especializadaArtigosPremium;
    private Encomenda encomenda;

    private static final double preçoEP = 0.5;
    private static final double preçoEM = 1.0;
    private static final double preçoEG = 2;
    private static final double imposto = 0.23;

    public static final String CTT = "CTT";
    public static final String DHL = "DHL";
    public static final String UPS = "UPS";
    public static final String FedEX = "FedEX";
    public static final String Nacex = "Nacex";

    public enum nome {
        CTT,
        DHL,
        UPS,
        FedEX,
        Nacex,
    }

    public Transportadora() {
        this.nome = "";
        this.margemLucro = 0;
        this.especializadaArtigosPremium = false;
        this.encomenda = null;
    }

    public Transportadora(String nome, double margemLucro, boolean especializadaArtigosPremium, Encomenda encomenda) {
        this.nome = nome;
        this.margemLucro = margemLucro;
        this.especializadaArtigosPremium = especializadaArtigosPremium;
        this.encomenda = encomenda;
    }

    public double getMargemLucro() {
        return this.margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public boolean getEspecializadaArtigosPremium() {
        return this.especializadaArtigosPremium;
    }

    public void setEspecializadaArtigosPremium(boolean especializadaArtigosPremium) {
        this.especializadaArtigosPremium = especializadaArtigosPremium;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static double calcularCustoExpedicao(double valorBase, double imposto) {

        double precoExpedicao = (valorBase * (1 + imposto)) * 0.9;

        return precoExpedicao;
    }

    public static double calcularCustoExpedicao(double valorBase, double margemLucro, double imposto) {
        double precoExpedicao = (valorBase * margemLucro * (1 + imposto)) * 0.9;
        return precoExpedicao;
    }

    public static double calcularCustoExpedicaoPremium(double valorBase, double margemLucro, double imposto) {
        // Suponha que para artigos Premium, o custo de expedição seja 1.5 vezes o
        // normal
        double precoExpedicao = 1.5 * calcularCustoExpedicao(valorBase, margemLucro, imposto);
        return precoExpedicao;
    }

    public void contarArtigosPorTransportadora(Encomenda encomenda) {
        int ctt = 0;Artigo[] cttA = new Artigo[100];
        int dhl = 0; Artigo [] dhlA = new Artigo [100];
        int ups = 0; Artigo [] upsA = new Artigo [100];
        int fedex = 0;Artigo [] fedexA = new Artigo [100];
        int nacex = 0;Artigo[] nacexA = new Artigo [100];
        for (Map.Entry<String, Artigo> entry : encomenda.getCarrinho().entrySet()) {
            Artigo artigo = entry.getValue();
            Transportadora transportadora = artigo.getTransportadora();
            if (transportadora != null) {
                switch (transportadora.getNome()) {
                    case CTT:
                        {ctt++;cttA[ctt] = artigo;}
                        break;
                    case DHL:
                        {dhl++;dhlA[dhl] = artigo;}
                        break;
                    case UPS:
                        {ups++;upsA[ups] = artigo;}
                        break;
                    case FedEX:
                        {fedex++;fedexA[fedex] = artigo;}
                        break;
                    case Nacex:
                        {nacex++;nacexA[nacex] = artigo;}
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public double calcularPrecoCTT(Artigo[] cttA) {
        double total = 0;
        this.margemLucro = 1.2;
        int tamanho = cttA.length;
        Artigo a;
        for (int i = 0; i < tamanho; i++) {
            a = cttA[i];
            double e = encomenda.valorcompra(a);
            if (a.getPremium() == true) {
                total += e * 1.2;
            }
        }
        if (tamanho < 2) {
            total = total * preçoEP;
        } else if (tamanho < 5) {
            total = total * preçoEM;
        } else {
            total = total * preçoEG;
        }
        return total*imposto*this.margemLucro;
    }
    public double calcularPrecoCTT(Artigo[] cttA) {
        double total = 0;
        this.margemLucro = 1.2;
        int tamanho = cttA.length;
        Artigo a;
        for (int i = 0; i < tamanho; i++) {
            a = cttA[i];
            double e = encomenda.valorcompra(a);
            if (a.getPremium() == true) {
                total += e * 1.2;
            }
        }
        if (tamanho < 2) {
            total = total * preçoEP;
        } else if (tamanho < 5) {
            total = total * preçoEM;
        } else {
            total = total * preçoEG;
        }
        return total*imposto*this.margemLucro;
    }
    public double calcularPrecoCTT(Artigo[] cttA) {
        double total = 0;
        this.margemLucro = 1.2;
        int tamanho = cttA.length;
        Artigo a;
        for (int i = 0; i < tamanho; i++) {
            a = cttA[i];
            double e = encomenda.valorcompra(a);
            if (a.getPremium() == true) {
                total += e * 1.2;
            }
        }
        if (tamanho < 2) {
            total = total * preçoEP;
        } else if (tamanho < 5) {
            total = total * preçoEM;
        } else {
            total = total * preçoEG;
        }
        return total*imposto*this.margemLucro;
    }
    public double calcularPrecofedex(Artigo[] fedexA) {
        double total = 0;
        this.margemLucro = 1.1;
        int tamanho = fedexA.length;
        Artigo a;
        for (int i = 0; i < tamanho; i++) {
            a = fedexA[i];
            double e = encomenda.valorcompra(a);
            if (a.getPremium() == true) {
                total += e * 1.2;
            }
        }
        if (tamanho < 2) {
            total = total * preçoEP;
        } else if (tamanho < 5) {
            total = total * preçoEM;
        } else {
            total = total * preçoEG;
        }
        return total*imposto*this.margemLucro;
    }
    public double calcularPreconacexT(Artigo[] nacexA) {
        double total = 0;
        this.margemLucro = 1.05;
        int tamanho = nacexA.length;
        Artigo a;
        for (int i = 0; i < tamanho; i++) {
            a = nacexA[i];
            double e = encomenda.valorcompra(a);
            if (a.getPremium() == true) {
                total += e * 1.2;
            }
        }
        if (tamanho < 2) {
            total = total * preçoEP;
        } else if (tamanho < 5) {
            total = total * preçoEM;
        } else {
            total = total * preçoEG;
        }
        return total*imposto*this.margemLucro;
    }
}