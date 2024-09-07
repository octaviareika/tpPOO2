import java.util.*;
/**
 Descrição do Jogo
O jogo da forca é um jogo de adivinhação onde o jogador precisa descobrir a palavra
correta escolhendo letras uma a uma. Cada erro adiciona uma parte ao "boneco" na forca, e
o objetivo é descobrir a palavra antes que o boneco seja completado.
Funcionalidades
1. Interface Gráfica:
○ Tela inicial com o título do jogo e um botão para começar.
○ Tela principal do jogo com a exibição das letras adivinhadas (outra linha para
letras ainda não descobertas), letras já escolhidas, e uma representação
visual da forca que se completa a cada erro.
○ Área para o jogador selecionar a letra e um botão para confirmar a escolha.
○ Exibição do número de tentativas restantes.
○ Tela final que mostra se o jogador venceu ou perdeu, com opção de jogar
novamente ou sair do jogo.
2. Lógica do Jogo:
○ O sistema deve selecionar aleatoriamente uma palavra de uma lista
predefinida.
○ Controlar o estado do jogo, como as letras adivinhadas, tentativas restantes
e progresso na construção da forca.
○ Validar a entrada do jogador (uma letra por vez, sem repetição).
3. Contagem de Pontuação:
○ Implementar um sistema de pontuação simples que registra o número de
vitórias e derrotas em diferentes rodadas.
○ Mostrar a pontuação acumulada ao final de cada rodada.
4. Persistência de Dados:
○ Salvar as palavras e o histórico de pontuação em arquivos de texto.
○ Permitir que o jogador possa continuar de onde parou na próxima vez que o
jogo for aberto

Classes:
○ Jogo (controla a lógica do jogo, como escolha de palavra, controle de
tentativas, etc.)
○ Palavra (responsável por gerenciar a palavra a ser adivinhada, verificando
acertos e erros)
○ Forca (controla a construção da imagem da forca com base nos erros)
○ InterfaceGrafica (gerencia toda a interface Swing)
 */



public class Palavra {
    private String[] palavrasFaceis = {"casa", "carro", "moto", "bicicleta", "aviao", "navio", "trem", "onibus", "caminhao", "moto"};
    private String[] palavrasMedias = {"computador", "celular", "tablet", "notebook", "teclado", "mouse", "monitor", "impressora", "roteador", "modem"};
    private String[] palavrasDificeis = {"inextricavel", "paralelepipedo", "hipotenusa", "idiossincrasia", "esdruxulo", "onomatopeia", "procrastinacao", "hemeroteca", "inocuo", "cacofonia"};
    
    Palavra() {
        
    }

    public String sortearPalavraFacil() {
        Random random = new Random();
        int index = random.nextInt(this.palavrasFaceis.length);
        return this.palavrasFaceis[index];
    }

    public String sortearPalavraMedia() {
        Random random = new Random();
        int index = random.nextInt(this.palavrasMedias.length);
        return this.palavrasMedias[index];
    }

    public String sortearPalavraDificil() {
        Random random = new Random();
        int index = random.nextInt(this.palavrasDificeis.length);
        return this.palavrasDificeis[index];
    }


    // getter e setter
    public String[] getPalavrasFaceis() {
        return this.palavrasFaceis;
    }


    public void setPalavrasFaceis(String[] palavrasFaceis) {
        this.palavrasFaceis = palavrasFaceis;
    }

    public String[] getPalavrasMedias() {
        return this.palavrasMedias;
    }

    public void setPalavrasMedias(String[] palavrasMedias) {
        this.palavrasMedias = palavrasMedias;
    }

    public String[] getPalavrasDificeis() {
        return this.palavrasDificeis;
    }

    public void setPalavrasDificeis(String[] palavrasDificeis) {
        this.palavrasDificeis = palavrasDificeis;
    }
    

    public String criarMascara(String palavra) {
        StringBuilder mascara = new StringBuilder();
        for (int i = 0; i < palavra.length(); i++) {
            mascara.append("_");
        }
        return mascara.toString();
    }

    

}

