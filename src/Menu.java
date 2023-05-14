import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Controller controller;
    private Scanner scanner;

    public Menu(Controller controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public Controller getController() {
        return this.controller;
    }

    public Menu() {
        scanner = new Scanner(System.in);
    }
    
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("1. Adicionar Artigo");
            System.out.println("2. Remover Artigo");
            System.out.println("3. Adicionar Utilizador");
            System.out.println("4. Remover Utilizador");
            System.out.println("5. Adicionar Transportadora");
            System.out.println("6. Remover Transportadora");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha

            switch (option) {
                case 1:
                    // Adicionar um novo artigo
                    Artigo artigo = adicionarArtigo();
                    controller.adicionarArtigo(artigo);
                    break;
                case 2:
                    // Remover um artigo
                    System.out.print("Digite o código do artigo que deseja remover: ");
                    String codigoArtigo = scanner.nextLine();
                    controller.deletarArtigo(codigoArtigo);
                    break;
                case 3:
                    // Adicionar um novo utilizador
                    Utilizador utilizador = adicionarUtilizador();
                    controller.adicionarUtilizador(utilizador);
                    break;
                case 4:
                    // Remover um utilizador
                    System.out.print("Digite o email do utilizador que deseja remover: ");
                    String emailUtilizador = scanner.nextLine();
                    controller.deletarUtilizador(emailUtilizador);
                    break;
                case 5:
                    // Adicionar uma nova transportadora
                    Transportadora transportadora = adicionarTransportadora();
                    controller.adicionarTransportadora(transportadora);
                    break;
                case 6:
                    // Remover uma transportadora
                    System.out.print("Digite o nome da transportadora que deseja remover: ");
                    String nomeTransportadora = scanner.nextLine();
                    controller.deletarTransportadora(nomeTransportadora);
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        }
        scanner.close();
    }

    private Artigo adicionarArtigo() {
        System.out.println("Digite a descrição do artigo: ");
        String descriçao = scanner.nextLine();
        System.out.println("Digite a marca do artigo: ");
        String marca = scanner.nextLine();
        System.out.println("Digite o preço base do artigo: ");
        double preco_base = scanner.nextDouble();
        System.out.println("Digite o valor de venda do artigo: ");
        double valorVenda = scanner.nextDouble();
        System.out.println("O artigo foi utilizado? (true/false) ");
        boolean utilizada = scanner.nextBoolean();
        scanner.nextLine(); // Consome a nova linha

        Artigo artigo = new Artigo(descriçao, marca, preco_base, valorVenda, utilizada);

        return artigo;
    }

    private Utilizador adicionarUtilizador() {
        System.out.println("Digite o nome do utilizador: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a morada do utilizador: ");
        String morada = scanner.nextLine();
        System.out.println("Digite o email do utilizador: ");
        String email = scanner.nextLine();
        System.out.println("Digite o código do utilizador: ");
        String codigo = scanner.nextLine();
        System.out.println("Digite o numero fiscal do utilizador: ");
        String numero_fiscal = scanner.nextLine();
        System.out.println("Digite o valor total de vendas do utilizador: ");
        double valor_total_vendas = scanner.nextDouble();
        scanner.nextLine(); // Consome a nova linha

        // As coleções de artigos são inicializadas como vazias
        Map<String, Artigo> artigos = new HashMap<>();
        Map<String, Artigo> compras = new HashMap<>();
        Map<String, Artigo> vendas = new HashMap<>();

        Utilizador utilizador = new Utilizador(nome, morada, email, codigo, numero_fiscal, artigos, compras, vendas, valor_total_vendas);
        return utilizador;
    }

    private Transportadora adicionarTransportadora() {
        System.out.println("Digite o nome da transportadora: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a margem de lucro da transportadora: ");
        double margemLucro = scanner.nextDouble();
        scanner.nextLine(); // Consome a nova linha
        System.out.println("A transportadora é especializada em artigos premium? (true/false) ");
        boolean especializadaArtigosPremium = scanner.nextBoolean();
        scanner.nextLine(); // Consome a nova linha

        // A encomenda é inicializada como nula
        Encomenda encomenda = null;

        Transportadora transportadora = new Transportadora(nome, margemLucro, especializadaArtigosPremium, encomenda);
        return transportadora;
    }
}
