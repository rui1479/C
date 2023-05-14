public class Main {
    public static void main(String[] args) {

        // Cria um novo objeto Controller
        Controller controller = new Controller();

        // Cria um novo objeto Menu com o objeto Controller
        Menu menu = new Menu(controller);

        // Cria alguns artigos
        Malas mala1 = new Malas("Mala Louis Vuitton", "Louis Vuitton", 1000.0, 1200.0, "média", "couro", 2021, "alça ajustável", false, true);
        Sapatilhas sapatilha1 = new Sapatilhas("Nike Air Force", "Nike", 100.0, 120.0, false, 42, "branco", 0.1, true, "2022-01-01", 1, true, false);
        Tshirts tshirt1 = new Tshirts("Camiseta Polo", "Polo", 50.0, 60.0, false, 40, "listrado");

        // Adiciona os artigos ao modelo através do controlador
        controller.adicionarArtigo(mala1);
        controller.adicionarArtigo(sapatilha1);
        controller.adicionarArtigo(tshirt1);

        // Inicia o menu
        menu.start();
    }
}
