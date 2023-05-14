import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.Map;

public class Encomenda {
    private Utilizador utilizador;
    private Map<String, Artigo> carrinho;
    private double preço_final;
    private double satisfaçao_serviço;
    private double custo_expediçao;
    private String estado;
    private String data_criaçao;

    public Encomenda() {
        this.carrinho = new HashMap<>();
        this.preço_final = 0;
        this.satisfaçao_serviço = 0;
        this.custo_expediçao = 0;
        this.estado = "";
        this.data_criaçao = "";

    }

    public Encomenda(Map<String, Artigo> carrinho, double preço_final,
            double satisfaçao_serviço, double custo_expediçao, String estado, String data_criaçao) {
        this.carrinho = carrinho;
        this.preço_final = preço_final;
        this.satisfaçao_serviço = satisfaçao_serviço;
        this.custo_expediçao = custo_expediçao;
        this.estado = estado;
        this.data_criaçao = data_criaçao;
    }

    // Adicione esses métodos à sua classe Encomenda

    public void criarEncomenda() {
        this.carrinho = new HashMap<>();
        this.preço_final = 0;
        this.satisfaçao_serviço = 0;
        this.custo_expediçao = 0;
        this.estado = "pendente";
        this.data_criaçao = new SimpleDateFormat("yyyy.MM.dd.HH").format(new Date());
    }

    public void finalizarEncomenda() {
        // aqui você pode adicionar lógica para calcular o preço final, custo de
        // expedição, etc.
        this.estado = "finalizada";
    }

    public void expedirEncomenda() {
        this.estado = "expedida";
    }

    public Utilizador getUtilizador() {
        return this.utilizador;
    }    
    
    public void setCarrinho(Map<String, Artigo> carrinho) {
        this.carrinho = new HashMap<>(carrinho);
    }

    public Map<String, Artigo> getCarrinho() {
        return new HashMap<>(this.carrinho);
    }

    public Artigo getArtigoC(String cod) {
        if(!carrinho.containsKey(cod)){
            return null;
        }
        return carrinho.get(cod).clone();
    }

   public void setArtigoC(String cod, Artigo artigo) {
        carrinho.put(cod, artigo);
    }

    public double getPreçoFinal() {
        return preço_final;
    }

    public void setPreçoFinal(double preço_final) {
        this.preço_final = preço_final;
    }

    public double getSatisfaçaoServiço() {
        return satisfaçao_serviço;
    }

    public void setSatisfaçaoServiço(double satisfaçao_serviço) {
        this.satisfaçao_serviço = satisfaçao_serviço;
    }

    public double getCustoExpediçao() {
        return custo_expediçao;
    }

    public void setCustoExpediçao(double custo_expediçao) {
        this.custo_expediçao = custo_expediçao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDataCriaçao() {
        return data_criaçao;
    }

    public void setDataCriaçao(String data_criaçao) {
        this.data_criaçao = data_criaçao;
    }

    public void adicionarAoCarrinho(Artigo artigo) {
        String codigo = artigo.getCodigo();
        if (this.carrinho.containsKey(codigo)) {
            this.carrinho.put(codigo, this.carrinho.get(codigo));    
    }
    }
    public void removerDoCarrinho(Artigo artigo) {
        String codigo = artigo.getCodigo();
        if (this.carrinho.containsKey(codigo)) {
            this.carrinho.remove(codigo);
            }
            }
    
    public double valorcompra(Artigo artigo) {
        double total = 0;

        if (artigo instanceof Sapatilhas) {
            Sapatilhas sapatilha = (Sapatilhas) artigo;
            total = sapatilha.calcularPrecoBaseSapatilha(sapatilha);
        } else if (artigo instanceof Tshirts) {
            Tshirts tshirt = (Tshirts) artigo;
            total = tshirt.calcularPrecoBaseTshirts(tshirt);
        } else if (artigo instanceof Malas) {
            Malas mala = (Malas) artigo;
            total = mala.calcularPrecoBaseMalas(mala);
        }

        return total;
    }

    public double realizarCompra() {
        // Calcula o preço total do carrinho
        double total = 0;
        for (Map.Entry<String, Artigo> entry : this.carrinho.entrySet()) {
            Artigo artigo = getArtigoC(entry.getKey());
            Transportadora transportadora = artigo.getTransportadora(); // aqui estamos assumindo que o artigo possui um método getTransportadora()
            if(transportadora != null) {
                utilizador.adicionarProdutoAVenda(artigo, transportadora);
                total += valorcompra(artigo);
            } else {
                // Caso o artigo não tenha uma transportadora associada, pode-se tomar uma ação aqui
                // Por exemplo, você pode decidir não adicionar o produto à venda, ou lançar uma exceção, ou atribuir uma transportadora padrão, etc.
                System.out.println("Artigo " + artigo.getCodigo() + " não possui uma Transportadora associada!");
            }
        }
        // Esvazia o carrinho (assumindo que a compra foi bem-sucedida)
        this.carrinho.clear();
    
        // Retorna o preço total
        return total;
    }
    

public Map<String, Integer> contarProdutosNovosEUsados() {
    int novos = 0;
    int usados = 0;
    for (Map.Entry<String, Artigo> entry : this.carrinho.entrySet()) {
        Artigo artigo = getArtigoC(entry.getKey());
        if (artigo != null) {
            if (artigo.getUtilizada() == false) {
                novos ++;
            } else {
                usados ++;
            }
        }
    }
    Map<String, Integer> resultado = new HashMap<>();
    resultado.put("novos", novos);
    resultado.put("usados", usados);
    return resultado;
}


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Encomenda {");
        sb.append("Lista de Artigos: ").append(this.carrinho).append(", ");
        sb.append("Preço Final: ").append(this.preço_final).append(", ");
        sb.append("Satisfação do Serviço: ").append(this.satisfaçao_serviço).append(", ");
        sb.append("Custo de Expedição: ").append(this.custo_expediçao).append(", ");
        sb.append("Estado: ").append(this.estado).append(", ");
        sb.append("Data de Criação: ").append(this.data_criaçao).append("}");
        return sb.toString();
    }

    public double calcularPrecoFinal() {
        double total = 0;
        for (String codigo : this.carrinho.keySet()) {
            Artigo artigo = getArtigoC(codigo);
            if (artigo != null) {
                total += artigo.getPreço_Base();
            }
        }
        total += contarProdutosNovosEUsados().get("novos") * 0.5;
        total += contarProdutosNovosEUsados().get("usados") * 0.25;
        this.preço_final = total++;
        return total;
    }
}

