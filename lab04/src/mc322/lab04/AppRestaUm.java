package mc322.lab04;

import java.io.BufferedReader;
import java.util.Scanner;

public class AppRestaUm {
    /* Método */
    static String[] executaJogo(String caminho) {
        /* Criando o tabuleiro e o vetor com os estados do tabuleiro após cada movimento */
        String[] vetor_estado_tabuleiro = new String[100];
        Tabuleiro tabuleiro_main = new Tabuleiro();
        /* Relizando os processos com a Classe CSVReader que resulta em um vetor com cada movimento que será realizado */
        CSVReader csv = new CSVReader();
        csv.setDataSource(caminho);
        String commands[] = csv.requestCommands();

        /* Printando o estado inicial do tabuleiro */
        System.out.println("Tabuleiro inicial:");
        tabuleiro_main.printar_tabuleiro();

        String source = "", target = "";
        for (int i = 0; i < commands.length; i++) {
            source = ""; target = "";
            /* Obtendo a posição da origem */
            source += commands[i].charAt(0); source += commands[i].charAt(1);
            /* Obtendo a posição do destino */
            target += commands[i].charAt(3); target += commands[i].charAt(4);
            /* Realizando o movimento */
            tabuleiro_main.realizar_movimento(commands[i]);
            /* Printando a origem e o destino */
            System.out.println("Source: " + source);
            System.out.println("Target: " + target);
            /* Printando o tabuleiro após o movimento */
            tabuleiro_main.printar_tabuleiro();
            /* Preenchendo vetor de estados do tabuleiro */
            vetor_estado_tabuleiro[i] = tabuleiro_main.converter_matriz_para_string();
        }

        return vetor_estado_tabuleiro;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Escreva o caminho do arquivo CSV: ");
        String caminho = sc.nextLine();
        AppRestaUm.executaJogo(caminho);
    }
}
