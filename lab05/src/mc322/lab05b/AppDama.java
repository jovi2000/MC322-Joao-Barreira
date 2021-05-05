package mc322.lab05;

public class AppDama {
    static String[] executaJogo(String entrada, String saida) {

        /* Relizando os processos com a Classe CSVReader que resulta em um vetor com cada movimento que será realizado */
        CSVHandling csv = new CSVHandling();
        csv.setDataSource(entrada);
        String[] commands = csv.requestCommands();

        /* Criando o tabuleiro e o vetor com os estados do tabuleiro após cada movimento */
        Tabuleiro tabuleiro_main = new Tabuleiro();
        String[] vetor_estado_tabuleiro = new String[500];

        /* Printando o estado inicial do tabuleiro */
        System.out.println("Tabuleiro inicial:");
        tabuleiro_main.imprimirTabuleiro();

        String source = "", target = "";
        for (int i = 0; i < commands.length; i++) {
            source = ""; target = "";
            /* Obtendo a posição da origem */
            source += commands[i].charAt(0); source += commands[i].charAt(1);
            /* Obtendo a posição do destino */
            target += commands[i].charAt(3); target += commands[i].charAt(4);
            /* Printando a origem e o destino */
            System.out.println("Source: " + source);
            System.out.println("Target: " + target);
            /* Realizando, ou não, o movimento */
            tabuleiro_main.solicitaMovimento(commands[i]);
            /* Preenchendo vetor de estados do tabuleiro */
            vetor_estado_tabuleiro[i] = tabuleiro_main.converterMatrizParaString();
        }
        /* Escrevendo o estado final do tabuleiro no arquivo de saída */
        tabuleiro_main.exportarArquivo(saida);

        return vetor_estado_tabuleiro;
    }

    public static void main(String[] args) {
        AppDama.executaJogo(args[0], args[1]);
    }
}
