import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class Malas extends Artigo {
    private String dimensao;
    private String material;
    private int ano_coleção;
    private String tipo_acessorio;
    private boolean tipo_mala;

    public enum material 
    {
        couro,
        nylon,
        poliester,
        microfibra,
        outro,
    }
    
    public enum dimensao
    {
        mão, //1
        média, //2
        grande, //3
        extra_grande, //4
    }

    public Malas(){

        this.dimensao= "";
        this.material="";
        this.ano_coleção= 0;
        this.tipo_acessorio= "";
        this.tipo_mala= false;


    }

    public Malas(String descriçao, String marca, double preço_base, double valorVenda, String dimensao, String material, int ano_coleção,String tipo_acessorio,boolean utilizada,boolean tipo_mala) {
        super(descriçao, marca, preço_base, valorVenda, utilizada);
        this.dimensao = dimensao;
        this.material = material;
        this.ano_coleção = ano_coleção;
        this.tipo_acessorio = tipo_acessorio;
        this.tipo_mala = tipo_mala;
    }

    public Malas(Malas outro) {
        this.dimensao = outro.dimensao;
        this.material = outro.material;
        this.ano_coleção = outro.ano_coleção;
        this.tipo_acessorio = outro.tipo_acessorio;
        this.tipo_mala = outro.tipo_mala;
    }    

    public String getDimensao() {
        return this.dimensao;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getAnoColeção() {
        return this.ano_coleção;
    }

    public void setAnoColeção(int ano_coleção) {
        this.ano_coleção = ano_coleção;
    }

    public String getTipoAcessorio() {
        return this.tipo_acessorio;
    }

    public void setTipoAcessorio(String tipo_acessorio) {
        this.tipo_acessorio = tipo_acessorio;
    }

    public boolean getTipoMala() {
        return this.tipo_mala;
    }

    public void setTipoMala(boolean tipo_mala) {
        this.tipo_mala = tipo_mala;
    }

    public Malas clone() {
        Malas copia = new Malas(this.getDescriçao(), this.getMarca(), this.getPreço_Base(), this.getValorVenda(), this.dimensao, this.material, this.ano_coleção, this.tipo_acessorio, this.getUtilizada() ,this.tipo_mala);
        return copia;
    }
    

    public static double numero_dimensao(String dimensao){
        double valor = 0.0;

        if(dimensao.equals("mão")){
            valor = 1;
        }else if(dimensao.equals("média"))
        {
            valor = 1.3;
        }else if (dimensao.equals("grande"))
        {
            valor = 1.8;
        }else if(dimensao.equals("extra_grande"))
        {
            valor = 2.1;
        }else {
            return valor;
        }
        return valor;
    }
    
    public double numeroAno(String material)
    {
        double valor = 0;
        if (material.equals("couro"))
        {
            valor = 1 + 0.20;// 10% de acrescimo todos os anos 
        }
        else if (material.equals("nylon"))
        {
            valor = 1 + 0.11;// 10% de acrescimo todos os anos 
            
        }
        else if (material.equals("poliester"))
        {
            valor = 1 + 0.07;// 10% de acrescimo todos os anos 
        }
        else if (material.equals("microfibra"))
        {
            valor = 1+0.14;

        }
        else
        {
            valor = 1+0.05;
        }
        return valor;
    }



    public double calcularPrecoBaseMalas(Malas mala) {
        double precoFinal = mala.getPreço_Base(); 
        int numAnos = (LocalDate.now().getYear() - ano_coleção);
        double desconto = 0;
        if (tipo_mala == false) 
        {
            if(material.toLowerCase().equals("couro")){
                desconto = mala.getPreço_Base() * (0.05 + (numAnos * 0.05))/numero_dimensao(dimensao);
            }
            else if(material.toLowerCase().equals("nylon")){
                desconto = mala.getPreço_Base() * (0.20 + (numAnos * 0.05))/numero_dimensao(dimensao);
            }
            else if(material.toLowerCase().equals("poliester")){
                desconto = mala.getPreço_Base() * (0.25 + (numAnos * 0.05))/numero_dimensao(dimensao);
            }
            else if(material.toLowerCase().equals("microfibra")){
                desconto = mala.getPreço_Base() * (0.15 + (numAnos * 0.05))/numero_dimensao(dimensao);
            }
            else{
                desconto = mala.getPreço_Base() * (0.10 + (numAnos * 0.05))/numero_dimensao(dimensao);
            }
            precoFinal = mala.getPreço_Base() - desconto;
        }    
        else
        {   
        precoFinal = precoFinal * Math.pow(numeroAno(material.toLowerCase()), numAnos);
        }
        return precoFinal;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dimensão: ").append(this.dimensao).append(", ");
        sb.append("Material: ").append(this.material).append(", ");
        sb.append("Ano da Coleção: ").append(this.ano_coleção).append(", ");
        sb.append("Tipo de Acessório: ").append(this.tipo_acessorio).append(", ");
        sb.append("Tipo de Mala: ").append(this.tipo_mala).append("}");
        return sb.toString();
    }
  
}

