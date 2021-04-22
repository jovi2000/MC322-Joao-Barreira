package mc322.lab04;

public class Tabuleiro {
    /* Atributos */
    Peca[][] matriz_tabuleiro; /* Inicialmente o tabuleiro será representado por uma matriz de objetos da classe Peca */

    /* Métodos */
    Tabuleiro() {
        this.matriz_tabuleiro = new Peca[7][7];
        preencher_tabuleiro();
    }

    void preencher_tabuleiro() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                /* No ponto d4 ( que na matriz é [3][3] do tabuleiro temos o meio, que é um ponto vazio no começo) */
                if (i == 3 && j == 3) {
                    matriz_tabuleiro[i][j] = new Peca('-');
                }
                /* Como o tabuleiro tem forma de cruz e ele foi representado por uma matriz quadrada, os 4 pontos de cada
                ponta não pertencem ao tabuleiro. Por esse motivo o "if" abaixo preenche todos os pontos com peças menos
                os pontos das pontas da matriz que não representam o tabuleiro (que são 16 pontos) */
                else if ((i != 0 || j != 0) && (i != 0 || j != 1) && (i != 1 || j != 0) && (i != 1 || j != 1) &&
                (i != 0 || j != 5) && (i != 0 || j != 6) && (i != 1 || j != 5) && (i != 1 || j != 6) && (i != 5 || j != 0)
                && (i != 5 || j != 1) && (i != 6 || j != 0) && (i != 6 || j != 1) && (i != 5 || j != 5) &&
                (i != 5 || j != 6) && (i != 6 || j != 5) && (i != 6 || j != 6)) {
                    matriz_tabuleiro[i][j] = new Peca('P');
                }
            }
        }
    }

    void realizar_movimento(String movimento) {
        int linha_origem, coluna_origem;
        int linha_destino, coluna_destino;
        int linha_meio, coluna_meio;

        /* Encontrando a linha e a coluna da origem */
        coluna_origem = movimento.charAt(0) - 'a';
        int numero = Integer.parseInt(String.valueOf(movimento.charAt(1)));
        linha_origem = Math.abs(numero - 7);

        /* Encontrando a linha e a coluna  */
        coluna_destino = movimento.charAt(3) - 'a';
        numero = Integer.parseInt(String.valueOf(movimento.charAt(4)));
        linha_destino = Math.abs(numero - 7);

        /* Encontando a linha e a coluna do meio. Como a Peça se move sempre verticalmente ou horizontalmente, a linha
        ou a coluna da origem será igual a linha ou a coluna do destino */
        if (coluna_origem == coluna_destino) {
            coluna_meio = coluna_origem;
            linha_meio = (linha_origem + linha_destino) / 2; /* Como nesse movimento a peça se move verticalmente duas
            posições, a peça do meio tem a mesma coluna da origem e do destino e tem sua linha igual a média aritmética
            das linhas da origem e do destino */
        }
        else /* Caso a linha_origem seja igual a linha_destino */ {
            linha_meio = linha_origem;
            coluna_meio = (coluna_origem + coluna_destino) / 2; /* Como nesse movimento a peça se move horizontalmente
            duas posições, a peça do meio tem a mesma linha da origem e do destino e tem sua coluna igual a média
            aritmética das colunas da origem e do destino */
        }

        /* Para que o movimento possa ser realizado, a Peça origem precisa estar preenchida ("P"), a Peça destino precisa
        estar vazia ("-") e a Peça do meio precisa estar preenchida */
        if ((matriz_tabuleiro[linha_origem][coluna_origem].situacao == 'P') &&
           (matriz_tabuleiro[linha_destino][coluna_destino].situacao == '-') &&
           (matriz_tabuleiro[linha_meio][coluna_meio].situacao == 'P') &&
           (linha_origem == linha_destino || coluna_origem == coluna_destino)) /* Essa última condição impede que
            movimentos em diagonal sejam realizados */ {
            /* Após o movimento a origem fica vazia */
            matriz_tabuleiro[linha_origem][coluna_origem].situacao = '-';
            /* O destino fica preenchido */
            matriz_tabuleiro[linha_destino][coluna_destino].situacao = 'P';
            /* O meio fica vazio */
            matriz_tabuleiro[linha_meio][coluna_meio].situacao = '-';
        }
        else {
            System.out.println("Esse movimento não pode ser realizado");
        }
    }

    String converter_matriz_para_string() {
        String tabuleiro_string = "";
        int k = 0;
       /* Percorre todas as poições da matriz que representa o tabuleiro */
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                /* Se a posição da matiz for null, significa que não existe peça nessa posição */
                if (matriz_tabuleiro[i][j] == null) {
                    tabuleiro_string += " ";
                    k++;
                }
                else {
                    tabuleiro_string += matriz_tabuleiro[i][j].situacao;
                    k++;
                }
                /* O j == 6 represnta a última coluna, por isso o "\n" */
                if (j == 6) {
                    tabuleiro_string += "\n";
                    k++;
                }
            }
        }
        return tabuleiro_string;
    }

    void printar_tabuleiro() {
        for (int i = 0; i < 7; i++) {
            System.out.print(Math.abs(i - 7));
            for (int j = 0; j < 7; j++) {
                if (matriz_tabuleiro[i][j] == null) { /* Se a posição da matriz for null se trata de uma parte que está
                fora do tabuleiro e, por isso, não possui peça */
                    if (j == 6) { /* Caso seja j == 6, significa que será printada uma posição que está na última coluna
                    do tabuleiro e, por isso, é dado println para que a proxima posição printada esteja na linha seguinte */
                        System.out.println(" ");
                    }
                    else {
                        System.out.print(" ");
                    }
                }
                else {
                    if (j == 6) {
                        System.out.println(matriz_tabuleiro[i][j].situacao);
                    }
                    else {
                        System.out.print(matriz_tabuleiro[i][j].situacao);
                    }
                }
            }
        }
        /* Printando todas as letras que mostram a posição das colunas */
        System.out.print(" ");
        for (char letra = 'a'; letra <= 'g'; letra++) {
            System.out.print(letra);
        }
        System.out.println();
        System.out.println();
    }
}
