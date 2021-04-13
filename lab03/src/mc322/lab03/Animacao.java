public class Animacao {
    /* Atributos */
    String sequencia; // Guarda a String que representa a sequência
    AquarioLombriga aquario_lombriga; // Representa o objeto da classe AquarioLombriga da sequencia
    int leitura = 0; // Indica qual a posição atual(qual caracter está) da sequência

    /* Métodos */
    Animacao(String sequencia) {
        this.sequencia = sequencia;

        /* Passando as informações do AquarioLombriga da sequencia para o objeto aquario_lombriga */

        /* Guardando o tamanho do aquário */
        String t_aquario = ""; // t_aquario = tamanho do aquário
        while (leitura < 2) { /* Leitura so vái até dois, pois os dois primeiros números da sequência representam o
        tamanho do aquário */
            char numero = sequencia.charAt(leitura);
            t_aquario += numero;
            leitura += 1;
        }
        int int_aquario = Integer.parseInt(t_aquario); /* Convertendo a string para um inteiro (para usar como parametro
        do construtor do aquario_lombriga) */

        /* Guardando o tamanho da lombriga */
        String t_lombriga = ""; // t_lombriga = tamanho da lombriga
        while (leitura < 4) { /* Leitura só vai até 4, pois o terceiro e o quarto número da sequência representam o
        tamanho da lombriga */
            char numero = sequencia.charAt(leitura);
            t_lombriga += numero;
            leitura += 1;
        }
        int int_t_lombriga = Integer.parseInt(t_lombriga); /* Convertendo a string para um inteiro */

        /* Guardando a posição da lombriga */
        String p_lombriga = ""; // p_lombriga = posição da lombriga no aquário
        while (leitura < 6) {
            char numero = sequencia.charAt(leitura);
            p_lombriga += numero;
            leitura += 1;
        }
        int int_p_lombriga = Integer.parseInt(p_lombriga); /* Convertendo a string para um inteiro */

        /* Colocando os atributos do AquarioLombriga no objeto */
        aquario_lombriga = new AquarioLombriga(int_aquario, int_t_lombriga, int_p_lombriga);
    }

    String apresenta() {
        return aquario_lombriga.apresenta();
    }

    void passo() {
        char acao = sequencia.charAt(leitura);
        leitura += 1;
        /* Verificando e realizando a ação correspondente */
        if (acao == 'C') {
            aquario_lombriga.crescer();
        }
        else if (acao == 'M') {
            aquario_lombriga.mover();
        }
        else {
            aquario_lombriga.virar();
        }
    }


}
