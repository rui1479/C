import java.util.Scanner;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }
    
    public Controller() {
        model = new Model();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
    
        while (!quit) {
            view.displayMenu();
            String choice = scanner.nextLine();
    
            switch (choice) {
                case "1":
                    // Adicionar um novo artigo
                    Artigo artigo = new Artigo();
                    adicionarArtigo(artigo);
                    break;
                case "2":
                    // Remover um artigo
                    System.out.print("Digite o código do artigo que deseja remover: ");
                    String codigo = scanner.nextLine();
                    deletarArtigo(codigo);
                    break;
                case "3":
                    // Adicionar um novo utilizador
                    Utilizador utilizador = new Utilizador();
                    adicionarUtilizador(utilizador);
                    break;
                case "4":
                    // Remover um utilizador
                    System.out.print("Digite o email do utilizador que deseja remover: ");
                    String email = scanner.nextLine();
                    deletarUtilizador(email);
                    break;
                case "5":
                    // Adicionar uma nova transportadora
                    Transportadora transportadora = new Transportadora();
                    adicionarTransportadora(transportadora);
                    break;
                case "6":
                    // Remover uma transportadora
                    System.out.print("Digite o nome da transportadora que deseja remover: ");
                    String nome = scanner.nextLine();
                    deletarTransportadora(nome);
                    break;
                case "7":
                    quit = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
        scanner.close();
    }
    

    public void adicionarArtigo(Artigo artigo) {
        model.addArtigo(artigo);
        view.displayArtigo(artigo);
    }

    public void atualizarArtigo(Artigo artigo) {
        model.updateArtigo(artigo);
        view.displayArtigo(artigo);
    }

    public void deletarArtigo(String codigo) {
        model.deleteArtigo(codigo);
        view.removerArtigoDisplay(codigo);
    }

    public void adicionarUtilizador(Utilizador utilizador) {
        model.addUtilizador(utilizador);
        view.displayUtilizador(utilizador);
    }

    public void atualizarUtilizador(Utilizador utilizador) {
        model.updateUtilizador(utilizador);
        view.displayUtilizador(utilizador);
    }

    public void deletarUtilizador(String email) {
        model.deleteUtilizador(email);
        view.removerUtilizadorDisplay(email);
    }

    public void adicionarTransportadora(Transportadora transportadora) {
        model.addTransportadora(transportadora);
        view.displayTransportadora(transportadora);
    }

    public void atualizarTransportadora(Transportadora transportadora) {
        model.updateTransportadora(transportadora);
        view.displayTransportadora(transportadora);
    }

    public void deletarTransportadora(String nome) {
        model.deleteTransportadora(nome);
        view.removerTransportadoraDisplay(nome);
    }
}
