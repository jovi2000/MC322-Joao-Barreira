package mc322.lab05;

public class Tabuleiro {
    /* Atributos */
    private Peca[][] matriz_tabuleiro;
    private boolean validar;

    /* Métodos */
    public Tabuleiro() {
        this.matriz_tabuleiro = new Peca[8][8];
        preencherTabuleiro();
    }

    public void preencherTabuleiro() {
        /* Preenchendo a matriz_tabuleiro */
        for (int i = 0; i < 8; i++) { /* Percorrendo toda a matriz */
            for (int j = 0; j < 8; j++) {
                if (i != 3 && i != 4) { /* As linhas 3 e 4 da matriz não possuem peça */
                    if ((i % 2) == 0) { /* Nas linhas pares, todas as colunas impares possuem peça */
                        if ((j % 2) == 1) {
                            if (i < 3) { /* As linhas menores que 3 possuem peças pretas */
                                matriz_tabuleiro[i][j] = new Peao("p", i, j);
                            }
                            else { /* As linhas maiores que 3 possuem peças brancas */
                                matriz_tabuleiro[i][j] = new Peao("b", i, j);
                            }
                        }
                    }
                    else { /* Nas linhas ímpares, todas as colunas pares possuem peça */
                        if ((j % 2) == 0) {
                            if (i < 3) { /* As linhas menores que 3 possuem peças pretas */
                                matriz_tabuleiro[i][j] = new Peao("p", i, j);
                            }
                            else { /* As linhas maiors que 3 possuem peças brancas */
                                matriz_tabuleiro[i][j] = new Peao("b", i, j);
                            }
                        }
                    }
                }
            }
        }
    }

    public void solicitaMovimento(String movimento) {
        char[] vetor_movimento = movimento.toCharArray();

        /* Encontrando a linha e a coluna da origem */
        int coluna_origem = vetor_movimento[0] - 'a';
        int linha_origem = '8' - vetor_movimento[1];
        /* Encontrando a linha e a coluna do destino */
        int coluna_destino = vetor_movimento[3] - 'a';
        int linha_destino = '8' - vetor_movimento[4];

        /* É necessário descobrir as peças do caminho do movimento, essas peças serão armazenadas em um vetor (o tamanho
        do vetor será igual a linha/coluna de origem - linha/coluna do destino, em módulo)*/
        Peca caminho[] = new Peca[Math.abs(linha_origem - linha_destino)];
        int contador = 0; // Representa a posição atual do vetor

        /* Caso o destino seja uma posição fora do tabuleiro, o movimento é invalido */
        if (linha_destino > 7 || linha_destino < 0 || coluna_destino > 7 || coluna_destino < 0) {
            validar = false;
        }
        /* Caso não tenha nenhuma peça na origem o movimento é invalido */
        else if (matriz_tabuleiro[linha_origem][coluna_origem] == null) {
            validar = false;
        }
        /* Caso o movimento seja em linha reta ele é invalido */
        else if (linha_origem == linha_destino || coluna_origem == coluna_destino) {
            validar = false;
        }
        /* Verificar a validade do movimento */
        else {
            /* Movimento para cima-direita (o movimento ocorre sendo [i-1][j+1]) */
            if (linha_destino < linha_origem && coluna_destino > coluna_origem) {
                int j = coluna_origem + 1;
                for (int i = linha_origem - 1; i >= linha_destino; i--) {
                    caminho[contador] = matriz_tabuleiro[i][j];
                    j++; contador++;
                    validar = matriz_tabuleiro[linha_origem][coluna_origem].validarMovimento(caminho, linha_destino, coluna_destino);
                }
            }
            /* Movimento para baixo-direita (o movimento ocorre sendo [i+1][j+1]) */
            else if (linha_destino > linha_origem && coluna_destino > coluna_origem) {
                int j = coluna_origem + 1;
                for (int i = linha_origem + 1; i <= linha_destino; i++) {
                    caminho[contador] = matriz_tabuleiro[i][j];
                    j++; contador++;
                    validar = matriz_tabuleiro[linha_origem][coluna_origem].validarMovimento(caminho, linha_destino, coluna_destino);
                }
            }
            /* Movimento para cima-esquerda (o movimento ocorre sendo [i-1][j-1]) */
            else if (linha_destino < linha_origem && coluna_destino < coluna_origem) {
                int j = coluna_origem - 1;
                for (int i = linha_origem - 1; i >= linha_destino; i--) {
                    caminho[contador] = matriz_tabuleiro[i][j];
                    j--; contador++;
                    validar = matriz_tabuleiro[linha_origem][coluna_origem].validarMovimento(caminho, linha_destino, coluna_destino);
                }
            }
            /* Movimento para baixo-esquerda (o movimento ocorre sendo [i+1][j-1]) */
            else {
                int j = coluna_origem - 1;
                for (int i = linha_origem + 1; i <= linha_destino; i++) {
                    caminho[contador] = matriz_tabuleiro[i][j];
                    j--; contador++;
                    validar = matriz_tabuleiro[linha_origem][coluna_origem].validarMovimento(caminho, linha_destino, coluna_destino);
                }
            }
        }
        /* Caso o movimento seja valido, o tabuleiro irá ajustar a peça da origem, do meio e do destino */
        if (validar) {
            /* Caso o Peao tenha atingido o lado oposto do tabuleiro, ele se transforma em uma Dama */
            if (matriz_tabuleiro[linha_origem][coluna_origem].tipo == "b" && linha_destino == 0) {
                matriz_tabuleiro[linha_destino][coluna_destino] = new Dama("B", linha_destino, coluna_destino);
            }
            else if (matriz_tabuleiro[linha_origem][coluna_origem].tipo == "p" && linha_destino == 7) {
                matriz_tabuleiro[linha_destino][coluna_destino] = new Dama("P", linha_destino, coluna_destino);
            }
            /* Peça do destino será a peça da origem (caso a peça não vire uma dama) */
            else {
                matriz_tabuleiro[linha_destino][coluna_destino] = matriz_tabuleiro[linha_origem][coluna_origem];
            }
            /* A posição da origem ficará vazia */
            matriz_tabuleiro[linha_origem][coluna_origem] = null;
            /* Caso o movimento não seja simples, é preciso verificar se existe uma peça capturada */
            if (caminho.length > 1) {
                for (int i = 0; i < caminho.length - 1; i++) {
                    if (caminho[i] != null) {
                        matriz_tabuleiro[caminho[i].linha][caminho[i].coluna] = null;
                    }
                }
            }
            imprimirTabuleiro();
        }
        else {
            System.out.println("Movimento invalido!");
            System.out.println();
        }
    }

    public void imprimirTabuleiro() {
        for (int i = 0; i < 8; i++) {
            if (i != 0) { /* Mudança de linha (com exceção da primeira, que não precisa) */
                System.out.println();
            }
            /* Printando todos os número no começo das linhas que indicam a posição das linhas */
            System.out.print((8 - i) + " ");
            for (int j = 0; j < 8; j++) {
                /* Se a posição da matriz for null se trata de uma posição vazia no tabuleiro */
                if (matriz_tabuleiro[i][j] == null) {
                    System.out.print("- ");
                }
                else {
                    System.out.print(matriz_tabuleiro[i][j].tipo + " ");
                }
            }
        }
        /* Printando todas as letras que mostram a posição das colunas */
        System.out.println(); System.out.print("  ");
        for (char letra = 'a'; letra <= 'h'; letra++) {
            System.out.print(letra + " ");
        }
        System.out.println();
        System.out.println();
    }

    public String converterMatrizParaString() {
        String tabuleiro_string = "";
        int k = 0;
        /* Percorre todas as poições da matriz que representa o tabuleiro */
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                /* Se a posição da matiz for null, significa que a posição está vazia */
                if (matriz_tabuleiro[i][j] == null) {
                    tabuleiro_string += "-";
                    k++;
                }
                else {
                    tabuleiro_string += matriz_tabuleiro[i][j].tipo;
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

    public void exportarArquivo(String caminho) {
        CSVHandling arquivo = new CSVHandling();
        arquivo.setDataExport(caminho);
        if (validar) {
            /* Criando o vetor que representa o estado final do tabuleiro */
            String[] estado_tabuleiro = new String[64];
            int posicao = 0; // Representa a posição atual do vetor
            String peca_atual;
            /* Percorrendo a matriz tabuleiro */
            for (int i = 'a'; i <= 'h'; i++) { // Coluna
                for (int j = 7; j >= 0; j--) { // Linha
                    peca_atual = "";
                    /* Montando a posição mais a peça da posição atual do tabuleiro */
                    peca_atual += (char)i; peca_atual += (8 - j);
                    /* Verificando o tipo da peça */
                    if (matriz_tabuleiro[j][i - 'a'] == null) {
                        peca_atual += "_";
                    }
                    else {
                        peca_atual += matriz_tabuleiro[j][i - 'a'].tipo;
                    }
                    estado_tabuleiro[posicao] = peca_atual; /* Adicionando o estado da posição no vetor */
                    posicao++;
                }
            }
            /* Escrevendo no arquivo */
            arquivo.exportState(estado_tabuleiro);
        }
        else {
            String[] estado_tabuleiro = new String[1];
            estado_tabuleiro[0] = "erro";
            /* Escrevendo no arquivo */
            arquivo.exportState(estado_tabuleiro);
        }
    }
}
