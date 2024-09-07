import java.util.Scanner;
import java.io.*;


public class Jogo {
    Palavra palavra;
    private int maximoTentativas;
    private Forca forca;
    int pontuacao;

    Jogo(Palavra palavra, Forca forca) {
        this.maximoTentativas = 10;
        this.palavra = new Palavra();
        this.forca = new Forca();
        this.pontuacao = 0;
        
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

        carregarEstado();
        StringBuilder letrasErradas = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        
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

        while (!mascara.equals(palavraSorteada) && mascara != null && tentativas < this.maximoTentativas) {

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
                this.pontuacao = this.pontuacao + 20;
            } else {
                System.out.println("Errou!");
                this.pontuacao = this.pontuacao - 5;
                letrasErradas.append(letra).append(" ");
                tentativas++;
                this.forca.adicionarErro();
            }

            System.out.println("-----------------------------------------");
            System.out.println("Estado: " + mascara);
            System.out.println("Letras erradas: " + letrasErradas);
            System.out.println("Tentativas restantes: " + (this.maximoTentativas - tentativas));
            System.out.println("Pontuação: " + this.pontuacao);
            System.out.println("Boneco: " + String.join(" ", this.forca.getBoneco()));
            System.out.println("-----------------------------------------");

            verificarVitoria(tentativas, mascara, palavraSorteada, this.pontuacao);
            
        }

        salvarEstado();
        scanner.close();
    }

    public void verificarVitoria(int tentativas, String mascara, String palavraSorteada, int pontuacao) {
        if (tentativas >= this.maximoTentativas) {
            System.out.println("Você perdeu! A palavra era: " + palavraSorteada);
            
        } else if (mascara.equals(palavraSorteada)) {
            System.out.println("Você venceu!");
            System.out.println("Pontuação Total: " + this.pontuacao);
        }
    }

    public void salvarEstado(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("estado.txt"))) {
            writer.write(this.pontuacao + "\n");
            writer.write(this.forca.getErros() + "\n");
            for (String parte : this.forca.getBoneco()) {
                writer.write(parte + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o estado do jogo: " + e.getMessage());
        }
    }

    public void carregarEstado(){
        try (BufferedReader reader = new BufferedReader(new FileReader("estado.txt"))) {
            this.pontuacao = Integer.parseInt(reader.readLine());
            this.forca.setErros(Integer.parseInt(reader.readLine()));
            String[] boneco = new String[10];
            for (int i = 0; i < 10; i++) {
                boneco[i] = reader.readLine();
            }
            this.forca.setBoneco(boneco);
        } catch (IOException e) {
            System.out.println("Erro ao carregar o estado do jogo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Palavra palavra = new Palavra();
        Forca forca = new Forca();
        Jogo jogo = new Jogo(palavra, forca);
        jogo.iniciarJogo();
    }
    
};
