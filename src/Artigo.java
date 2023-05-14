import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.security.SecureRandom;



public class Artigo {
    private String descriçao;
    private String marca;
    private String codigo;
    private double preço_base;
    private double valorVenda;
    private boolean utilizada; // false se for novo
    private Utilizador vendedor;
    private Utilizador comprador;
    private LocalDate dataVenda;
    private LocalDate dataCompra;
    private boolean premium;
    private Transportadora transportadora;



    private static final String NUMBER = "0123456789";
    private static final int CODE_LENGTH = 10;

    public enum descriçao 
        {
            Sapatilha,
            Mala,
            Tshirt,
        }

    public Artigo(){

        this.descriçao= "";
        this.marca= "";
        this.codigo= "";
        this.preço_base= 0;
        this.valorVenda= 0;
        this.utilizada= false;
        this.premium= false;
        this.transportadora= null;
        this.vendedor= null;
        this.comprador= null;
    }

    public Artigo(String descriçao, String marca, double preço_base, double valorVenda, boolean utilizada) {
        this.descriçao = descriçao;
        this.marca = marca;
        this.codigo=defineCodigoN();
        this.preço_base = preço_base;
        this.valorVenda= valorVenda;
        this.utilizada = utilizada;
    }

    public Artigo(Artigo outro) {
        this.descriçao = outro.descriçao;
        this.marca = outro.marca;
        this.codigo = defineCodigoN ();
        this.preço_base = outro.preço_base;
        this.valorVenda= outro.valorVenda;
        this.utilizada = outro.utilizada;
    }

    public static String defineCodigoN() {
        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(NUMBER.length());
            codeBuilder.append(NUMBER.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }
    
    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }


    public String getDescriçao() {
        return descriçao;
    }

    public void setDescriçao(String descriçao) {
        this.descriçao = descriçao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPreço_Base() {
        return preço_base;
    }

    public void setPreçoBase(double preço_base) {
        this.preço_base = preço_base;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public boolean getUtilizada() {
        return this.utilizada;
    }

    public void setUtilizada(boolean utilizada) {
        this.utilizada = utilizada;
    }

    public Artigo clone() {
        try {
            return (Artigo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Isso nunca deve acontecer
        }
    }

    public void setComprador(Utilizador comprador) {
        this.comprador = comprador;
    }

    public Utilizador getComprador() {
        return this.comprador;
    }

    public Utilizador getVendedor() {
        return this.vendedor;
    }

    public void setVendedor(Utilizador vendedor) {
        this.vendedor = vendedor;
    }

    public LocalDate getDataVenda() {
        return this.dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public LocalDate getDataCompra() {
        return this.dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public boolean getPremium() {
        return this.premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }
    /*public abstract double calcularPrecoBase();*/
    

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Artigo{");
        sb.append("Descrição: ").append(this.descriçao).append(", ");
        sb.append("Marca: ").append(this.marca).append(", ");
        sb.append("Código: ").append(this.codigo).append(", ");
        sb.append("Preço base: ").append(this.preço_base).append(", ");
        sb.append("Valor da Venda:").append(this.valorVenda).append(", ");
        sb.append("Utilizada: ").append(this.utilizada).append(", ");
        sb.append("Vendedor: ").append(this.vendedor).append(", ");
        sb.append("Comprador: ").append(this.comprador).append(", ");
        sb.append("DataVenda: ").append(this.dataVenda).append(", ");
        sb.append("Data da Compra: ").append(this.dataCompra).append(", ");
        sb.append("Premium: ").append(this.premium).append(", ");
        sb.append("Transportadora: ").append(this.transportadora).append(", ");
        sb.append('}');
        return sb.toString();
    }


}   
 
