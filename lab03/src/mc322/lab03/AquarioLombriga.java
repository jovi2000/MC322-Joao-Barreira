public class AquarioLombriga {
    /* Atributos */
    int tamanho_aquario;
    int tamanho_lombriga;
    int posicao;
    char lado = 'd'; // se o lado for "d" a cabeça está para direita e se for "e" a cabeça está para esquerda

    /* Métodos */
    AquarioLombriga(int tamanho_aquario, int tamanho_lombriga, int posicao) {
        /* Caso a lombriga seja maior que o aquário, o aquário tem seu tamanho aumentado até a última posição da lombriga */
        if (tamanho_lombriga > tamanho_aquario) {
            this.tamanho_aquario = (posicao + tamanho_lombriga) - 1;
            this.tamanho_lombriga = tamanho_lombriga;
            this.posicao = posicao;
        }
        /* Caso a lombriga nao caiba na posição específicada, a posição passa a ter valor 1 */
        else if ((posicao + tamanho_lombriga) - 1 > tamanho_aquario) {
            this.posicao = 1;
            this.tamanho_aquario = tamanho_aquario;
            this.tamanho_lombriga = tamanho_lombriga;
        }
        else {
            this.tamanho_aquario = tamanho_aquario;
            this.tamanho_lombriga = tamanho_lombriga;
            this.posicao = posicao;
        }
    }

    void crescer() {
        if (lado == 'd') {
            if (posicao > 1) {
                tamanho_lombriga += 1;
                posicao -= 1;
            }
        }
        else {
            if (lado == 'e') {
                if ((posicao + tamanho_lombriga) - 1 < tamanho_aquario) { /* (posicao + tamanho_lombriga) - 1 é igual a
                última posição da lombriga no aquário */
                    tamanho_lombriga += 1;
                }
            }
        }
    }

    void virar() {
        if (lado == 'd') {
            lado = 'e';
        }
        else {
            lado = 'd';
        }
    }

    void mover() {
        if (lado == 'd') {
            if ((posicao + tamanho_lombriga) - 1 == tamanho_aquario) { /* Caso a cabeça da lombriga esteja no final do
             aquário, ela muda seu lado */
                virar();
            }
            else { /* Se ela não estiver no aquário, ela se move 1 posição para a direita */
                posicao += 1;
            }
        }
        else {
            if (posicao == 1) { /* Caso a cabeça da lombriga esteja no começo doaquário, ela muda seu lado */
                virar();
            }
            else {
                posicao -= 1; /* Se ela não estiver no aquário, ela se move 1 posição para a esquerda */
            }
        }
    }

    String apresenta() {
        String estado_atual = "";
        /* Do começo do aquário até a primeira posição da lombriga */
        for (int i = 1; i < posicao; i++) {
            estado_atual += '#';
        }
        /* Da primeira posição da lombriga até a última posição da lombriga */
        if (lado == 'e') {
            /* Caso a lombriga esteja virada para a esquerda, colocamos antes a cabeça e depois corpo */
            estado_atual += '0';
            for (int i = posicao + 1; i <= (posicao + tamanho_lombriga) - 1; i++) {
                estado_atual += '@';
            }
        }
        else {
            for (int i = posicao; i < (posicao + tamanho_lombriga) - 1; i++) {
                estado_atual += '@';
            }
            /* Caso a lombriga esteja virada para a direita, colocamos o corpo antes e depois a cabeça */
            estado_atual += '0';
        }
        /* Da últiaa posição da lombriga até o final do aquário */
        if ((posicao + tamanho_lombriga) - 1 < tamanho_aquario) {
            for (int i = (posicao + tamanho_lombriga); i <= tamanho_aquario; i++) {
                estado_atual += '#';
            }
        }
        return estado_atual;
    }
}
