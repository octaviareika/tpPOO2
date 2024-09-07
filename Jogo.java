import java.util.Scanner;



public class Jogo {
    Palavra palavra;
    private int maximoTentativas;
    private Forca forca;

    Jogo(Palavra palavra, Forca forca) {
        this.maximoTentativas = 10;
        this.palavra = new Palavra();
        this.forca = new Forca();
        
    }

    // getter e setter

    int getMaximoTentativas() {
        return this.maximoTentativas;
    }

    void setMaximoTentativas(int maximoTentativas) {
        this.maximoTentativas = maximoTentativas;
    }

    Palavra getPalavra() {
        return this.palavra;
    }

    void setPalavra(Palavra palavra) {
        this.palavra = palavra;
    }

    public String sortearPalavraFacil() {
        return this.palavra.sortearPalavraFacil();
    }

    public String sortearPalavraMedia() {
        return this.palavra.sortearPalavraMedia();
    }

    public String sortearPalavraDificil() {
        return this.palavra.sortearPalavraDificil();
    }

    public void iniciarJogo() {
        // lógica para iniciar o jogo
        StringBuilder letrasErradas = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        int pontuacao = 0;
        System.out.println("Escolha o nível de dificuldade: ");

        System.out.println("1 - Fácil");
        System.out.println("2 - Médio");
        System.out.println("3 - Difícil");

        int opcao = scanner.nextInt();

        String palavraSorteada = "";

        switch (opcao) {
            case 1:
                palavraSorteada = this.sortearPalavraFacil();
                break;
            case 2:
                palavraSorteada = this.sortearPalavraMedia();
                break;
            case 3:
                palavraSorteada = this.sortearPalavraDificil();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }

        // mascara
        String mascara = this.palavra.criarMascara(palavraSorteada);
        int tentativas = 0;

        while (mascara != null && tentativas < this.maximoTentativas) {

            System.out.println("Digite uma letra: ");
            String letra = scanner.next();
            if (letra.length() > 1) {
                System.out.println("Digite apenas uma letra por vez!");
                continue;
            }

            // se a letra digitada for um número

            if (Character.isDigit(letra.charAt(0))) {
                System.out.println("Digite apenas letras!");
                continue;
            }

            if (mascara.contains(letra)) {
                System.out.println("Letra já escolhida!");

                continue;
            }

            if (palavraSorteada.contains(letra)) {
                for (int i = 0; i < palavraSorteada.length(); i++) {
                    if (palavraSorteada.charAt(i) == letra.charAt(0)) {
                        mascara = mascara.substring(0, i) + letra + mascara.substring(i + 1);
                    }
                }
                System.out.println("Acertou!");
                pontuacao = pontuacao + 20;
            } else {
                System.out.println("Errou!");
                pontuacao = pontuacao - 5;
                letrasErradas.append(letra).append(" ");
                tentativas++;
                this.forca.adicionarErro();
            }

            System.out.println("-----------------------------------------");
            System.out.println("Estado: " + mascara);
            System.out.println("Letras erradas: " + letrasErradas);
            System.out.println("Tentativas restantes: " + (this.maximoTentativas - tentativas));
            System.out.println("Pontuação: " + pontuacao);
            System.out.println("Boneco: " + String.join(" ", this.forca.getBoneco()));
            System.out.println("-----------------------------------------");

            verificarVitoria(tentativas, mascara, palavraSorteada);
            
        }


        scanner.close();
    }

    public void verificarVitoria(int tentativas, String mascara, String palavraSorteada) {
        if (tentativas >= this.maximoTentativas) {
            System.out.println("Você perdeu! A palavra era: " + palavraSorteada);
        } else if (mascara.equals(palavraSorteada)) {
            System.out.println("Você venceu!");
        }
    }

    public static void main(String[] args) {
        Palavra palavra = new Palavra();
        Forca forca = new Forca();
        Jogo jogo = new Jogo(palavra, forca);
        jogo.iniciarJogo();
    }
    
};
