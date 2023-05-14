import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class Sapatilhas extends Artigo {
    private int tamanho;
    private String cor;
    private double estado_utilização; // percentagem de utilização
    private boolean possui_atacadores;
    private String data_lançamento;
    private int numero_donos;

    public Sapatilhas(){

        this.tamanho= 0;
        this.cor="";
        this.estado_utilização= 0.0;
        this.possui_atacadores= false;
        this.data_lançamento= "";
        this.numero_donos= 1; //contando com o vendedor como 1 dono
    }

    public Sapatilhas(String descriçao, String marca, double preço_base, double valorVenda,boolean premium, int tamanho, String cor, double estado_utilização, boolean possui_atacadores,String data_lançamento, int numero_donos, boolean tipo_sapatilha, boolean utilizada) {
        super(descriçao, marca, preço_base, valorVenda, utilizada);
        this.tamanho = tamanho;
        this.cor = cor;
        this.estado_utilização = estado_utilização;
        this.possui_atacadores = possui_atacadores;
        this.data_lançamento = data_lançamento;
        this.numero_donos = numero_donos;
    }

    public Sapatilhas(Sapatilhas outro) {
        this.tamanho = outro.tamanho;
        this.cor = outro.cor;
        this.estado_utilização = outro.estado_utilização;
        this.possui_atacadores = outro.possui_atacadores;
        this.data_lançamento = outro.data_lançamento;
        this.numero_donos = outro.numero_donos;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getEstadoUtilização() {
        return this.estado_utilização;
    }

    public void setEstadoUtilização(double estado_utilização) {
        this.estado_utilização = estado_utilização;
    }

    public boolean getPossuiAtacadores() {
        return this.possui_atacadores;
    }

    public void setPossuiAtacadores(boolean possui_atacadores) {
        this.possui_atacadores = possui_atacadores;
    }

    public String getDataLançamento() {
        return this.data_lançamento;
    }

    public void setDataLançamento(String data_lançamento) {
        this.data_lançamento = data_lançamento;
    }

    public int getNumeroDonos() {
        return this.numero_donos;
    }

    public void setNumeroDonos(int numero_donos) {
        this.numero_donos = numero_donos;
    }

    public Sapatilhas clone() {
        Sapatilhas copia = new Sapatilhas();
        copia.setCodigo(this.getCodigo());
        return copia;
    }

    
    public double calcularPrecoBaseSapatilha(Sapatilhas sapatilha) {
    double desconto_atual = 0;
    double precoFinal = sapatilha.getPreço_Base();
    if (sapatilha.getPremium()) { 
        double percentualAumento = (LocalDate.now().getYear() - Integer.parseInt(sapatilha.data_lançamento.substring(0, 4))) * 0.1 * sapatilha.getPreço_Base();
        
        precoFinal = sapatilha.getPreço_Base() + percentualAumento;
        
        return precoFinal;
    } else {
        if (sapatilha.getUtilizada()==true || Integer.parseInt(sapatilha.data_lançamento.substring(0, 4)) != LocalDate.now().getYear()) {
            desconto_atual = (sapatilha.getPreço_Base() / sapatilha.numero_donos) * sapatilha.estado_utilização; 
        } else if (sapatilha.getUtilizada()==false && sapatilha.tamanho > 45) {
            desconto_atual = 0.1 * sapatilha.getPreço_Base(); // 10% de desconto em sapatilhas novas acima do tamanho 45
        }
        precoFinal = sapatilha.getPreço_Base() - desconto_atual;

        return precoFinal;
    }
}

    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Tamanho: ").append(this.tamanho).append(", ");
    sb.append("Cor: ").append(this.cor).append(", ");
    sb.append("Estado de Utilização: ").append(this.estado_utilização).append(", ");
    sb.append("Possui Atacadores: ").append(this.possui_atacadores).append(", ");
    sb.append("Data de Lançamento: ").append(this.data_lançamento).append(", ");
    sb.append("Número de Donos: ").append(this.numero_donos).append("}");
    return sb.toString();
}


}

