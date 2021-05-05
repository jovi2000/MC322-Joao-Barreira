package mc322.lab05;

public class Peca {
    /* Atributos */
    protected String tipo; // A peça pode ser Peao ou Dama
    protected int linha;
    protected int coluna;

    /* Métodos */

    public Peca(String tipo, int i, int j) {
        this.tipo = tipo;
        this.linha = i;
        this.coluna = j;
    }

    public boolean validarMovimento(Peca[] caminho, int linha_destino, int coluna_destino) {
        return false;
    }
}
