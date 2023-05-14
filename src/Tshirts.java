import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class Tshirts extends Artigo{
    private int tamanho;
    private String padrao;


    public Tshirts(){

        this.tamanho= 0;
        this.padrao= "";

    }

    public Tshirts(String descriçao, String marca, double preço_base, double valorVenda, boolean utilizada, int tamanho, String padrao) {
        super(descriçao, marca, preço_base, valorVenda,utilizada);
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    public Tshirts(Tshirts outro) {
        this.tamanho = outro.tamanho;
        this.padrao = outro.padrao;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String getPadrao() {
        return this.padrao;
    }

    public void setPadrao(String padrao) {
        this.padrao = padrao;
    }
    public Tshirts clone() {
        Tshirts copia = new Tshirts();
        copia.setCodigo(this.getCodigo());
        // copie os outros atributos aqui
        return copia;
    }

    public static String padrao_minusculas(String padrao) { // passa todas as letras das string material para minusculas
        String minusculas = padrao.toLowerCase();
        return minusculas;
    }

    public double calcularPrecoBaseTshirts(Tshirts tshirt) {
        double precoFinal = tshirt.getPreço_Base(); 

        
        if ((tshirt.getUtilizada()==true) && !padrao.equals("liso")) {
            precoFinal = tshirt.getPreço_Base() * 0.5;
        }

        return precoFinal;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tamanho: ").append(this.tamanho).append(", ");
        sb.append("Padrão: ").append(this.padrao).append("}");
        return sb.toString();
}
	}


