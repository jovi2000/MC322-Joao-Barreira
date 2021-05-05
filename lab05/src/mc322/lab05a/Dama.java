package mc322.lab05;

import java.util.Locale;

public class Dama extends Peca {
    /* Métodos */
    public Dama(String tipo, int i, int j) {
        super(tipo, i, j);
    }

    public boolean validarMovimento(Peca[] caminho, int linha_destino, int coluna_destino) {
        /* Verificando se o movimento é simples, longo, captura ou invalido */

        /* Movimento simples */
        if (caminho.length == 1) {
            /* Se o destino estiver vazio, o movimento é valido */
            if (caminho[0] == null) {
                /* Atualizando as cooredenadas do Peao e retornando true */
                linha = linha_destino;
                coluna = coluna_destino;
                return true;
            }
            else {
                return false;
            }
        }

        /* Captura ou movimento longo */
        else if (caminho.length > 1 && caminho.length < 8) {
            /* Verificar se existe uma peça no meio de cor diferente da peça da origem e se o destino está vazio */
            if (caminho[caminho.length - 1] == null) {
                /* Verificando todas as posições do caminho (com exceção do destino) e verificando quantas peças tem no
                caminho */
                int contador = 0;
                for (int i = 0; i < caminho.length - 1; i++) {
                    if (caminho[i] != null ) {
                        contador++;
                        /* Caso uma Dama esteja tentando capturar uma outra peça da mesma cor ou caso tenha mais de uma
                        peça no caminho, o movimento é invalido */
                        if (tipo.equals(caminho[i].tipo.toUpperCase(Locale.ROOT)) || contador > 1) {
                            return false;
                        }
                    }
                }
                /* Atualizando as cooredenadas do Peao e retornando true */
                linha = linha_destino;
                coluna = coluna_destino;
                return true;
            }
            else {
                return false;
            }
        }

        /* Movimento invalido */
        else {
            return false;
        }
    }
}
