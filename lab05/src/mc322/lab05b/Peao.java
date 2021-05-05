package mc322.lab05;

import java.util.Locale;

public class Peao extends Peca {
    /* Métodos */
    public Peao(String tipo, int i, int j) {
        super(tipo, i, j);
    }

    public boolean validarMovimento(Peca[] caminho, int linha_destino, int coluna_destino) {
        /* Verificando se o movimento é simples, captura ou invalido */

        /* Movimento simples */
        if (caminho.length == 1) {
            /* Se o destino estiver vazio, o movimento é valido */
            if (caminho[0] == null) {
                /* Atualizando as cooredenadas do Peao e retornando true */
                linha = linha_destino;
                coluna = coluna_destino;
                return true;
            } else {
                return false;
            }
        }

        /* Captura */
        else if (caminho.length == 2) {
            /* Verificar se existe uma peça no meio de cor diferente da peça da origem e se o destino está vazio */
            if (caminho[0] != null && caminho[1] == null) {
                /* A peça do meio precisa ter cor diferente da peça da origem */
                if (!tipo.equals(caminho[0].tipo.toLowerCase(Locale.ROOT))) {
                    /* Atualizando as cooredenadas do Peao e retornando true */
                    linha = linha_destino;
                    coluna = coluna_destino;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        /* Invalido */
        else {
            return false;
        }
    }
}

