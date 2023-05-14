public class View {
    public void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Adicionar um novo artigo");
        System.out.println("2. Remover um artigo");
        System.out.println("3. Adicionar um novo utilizador");
        System.out.println("4. Remover um utilizador");
        System.out.println("5. Adicionar uma nova transportadora");
        System.out.println("6. Remover uma transportadora");
        System.out.println("7. Sair");
        System.out.println("Digite o número da opção desejada:");
    }
    
    public void displayArtigo(Artigo artigo) {
        System.out.println("Artigo:");
        System.out.println(artigo.toString());
    }

    public void removerArtigoDisplay(String codigo) {
        System.out.println("O Artigo com código " + codigo + " foi removido.");
    }

    public void displayUtilizador(Utilizador utilizador) {
        System.out.println("Utilizador:");
        System.out.println(utilizador.toString());
    }

    public void removerUtilizadorDisplay(String email) {
        System.out.println("O Utilizador com email " + email + " foi removido.");
    }

    public void displayTransportadora(Transportadora transportadora) {
        System.out.println("Transportadora:");
        System.out.println(transportadora.toString());
    }

    public void removerTransportadoraDisplay(String nome) {
        System.out.println("A Transportadora " + nome + " foi removida.");
    }
}
