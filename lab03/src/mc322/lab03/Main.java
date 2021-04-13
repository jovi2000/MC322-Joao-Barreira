public class Main {
    public static void main(String[] args) {
        /* Criando o objeto da classe Animacao */
        Animacao teste = new Animacao("080403MCMVM");

        /* Apresentando o a primeira configuração do aquario_lombriga do objeto teste */
        System.out.println(teste.apresenta());

        /* Realizando todos os passos e printando cada um */
        for (int i = 6; i < teste.sequencia.length(); i++) {
            teste.passo();
            System.out.println(teste.apresenta());
        }
    }
}
