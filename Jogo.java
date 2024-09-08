import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jogo {
    Palavra palavra;
    private int maximoTentativas;
    private Forca forca;
    int pontuacao;
    private int tentativas;
    private String palavraSorteada; // Adicionar variável de instância para armazenar a palavra sorteada

    // Componentes da interface Swing
    private JPanel panel;
    private JLabel lblPalavra, lblTentativas, lblPontuacao, lblMensagem, lblLetrasErradas, lblBoneco;
    private JTextField txtLetra;
    private JButton btnEnviar;

    Jogo(Palavra palavra, Forca forca) {
        this.maximoTentativas = 10;
        this.palavra = new Palavra();
        this.forca = new Forca();
        this.pontuacao = 0;
        this.tentativas = 0;
    }

    Jogo() {
        this.maximoTentativas = 10;
        this.palavra = new Palavra();
        this.forca = new Forca();
        this.pontuacao = 0;
        this.tentativas = 0;

        initSwingComponents(); // Inicializar a interface Swing
    }

    // Inicializar componentes da interface Swing
    private void initSwingComponents() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));  // Define o layout como Grid

        // Sortear uma palavra
        palavraSorteada = escolherDificuldade(); // Armazenar a palavra sorteada

        // Rótulo para exibir a palavra mascarada
        lblPalavra = new JLabel("Palavra: " + palavra.criarMascara(palavraSorteada), JLabel.CENTER);
        lblTentativas = new JLabel("Tentativas restantes: " + maximoTentativas, JLabel.CENTER);
        lblPontuacao = new JLabel("Pontuação: " + pontuacao, JLabel.CENTER);
        lblMensagem = new JLabel("", JLabel.CENTER);
        lblLetrasErradas = new JLabel("Letras erradas: ", JLabel.CENTER);
        lblBoneco = new JLabel("Boneco: " + String.join(" ", forca.getBoneco()), JLabel.CENTER);

        // Campo de texto para inserir a letra
        txtLetra = new JTextField(1);

        // Botão para enviar a letra
        btnEnviar = new JButton("Enviar Letra");
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String letra = txtLetra.getText().trim(); // trim remove espaços em branco
                txtLetra.setText("");   // Limpar o campo de texto

                // Processar a letra enviada
                processarLetra(letra);
            }
        });

        // Adicionar os componentes ao painel
        panel.add(lblPalavra);
        panel.add(lblTentativas);
        panel.add(lblPontuacao);
        panel.add(lblMensagem);
        panel.add(txtLetra);
        panel.add(btnEnviar);
        panel.add(lblLetrasErradas);
        panel.add(lblBoneco);
    }

    public JPanel getPanel() {
        return panel;
    }

    private String escolherDificuldade() {
        String[] opcoes = {"Fácil", "Médio", "Difícil"};
        int opcao = JOptionPane.showOptionDialog(panel, "Escolha o nível de dificuldade:", "Nível de Dificuldade",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        switch (opcao) {
            case 0:
                return palavra.sortearPalavraFacil();
            case 1:
                return palavra.sortearPalavraMedia();
            case 2:
                return palavra.sortearPalavraDificil();
            default:
                JOptionPane.showMessageDialog(panel, "Opção inválida!");
                return "";
        }
    }

    // Processar a letra inserida pelo jogador
    private void processarLetra(String letra) {
        if (letra.length() != 1) {
            JOptionPane.showMessageDialog(panel, "Digite apenas uma letra por vez!");
            return;
        }

        if (Character.isDigit(letra.charAt(0))) {
            JOptionPane.showMessageDialog(panel, "Digite apenas letras!");
            return;
        }

        StringBuilder mascara = new StringBuilder(lblPalavra.getText().replace("Palavra: ", ""));

        // Verificar se a letra está correta
        if (palavraSorteada.contains(letra)) {
            // Atualiza a palavra mascarada com a letra correta
            for (int i = 0; i < palavraSorteada.length(); i++) {
                if (palavraSorteada.charAt(i) == letra.charAt(0)) {
                    mascara.setCharAt(i, letra.charAt(0));
                }
            }
            lblMensagem.setText("Acertou!");
            pontuacao += 20;
        } else {
            lblMensagem.setText("Errou!");
            pontuacao -= 5;
            tentativas++;
            forca.adicionarErro();
            lblLetrasErradas.setText(lblLetrasErradas.getText() + letra + " ");
        }

        // Atualizar os rótulos na interface
        lblPalavra.setText("Palavra: " + mascara.toString());
        lblTentativas.setText("Tentativas restantes: " + (maximoTentativas - tentativas));
        lblPontuacao.setText("Pontuação: " + pontuacao);
        lblBoneco.setText("Boneco: " + String.join(" ", forca.getBoneco()));

        // Verificar vitória ou derrota
        if (tentativas >= maximoTentativas) {
            JOptionPane.showMessageDialog(panel, "Você perdeu! A palavra era: " + palavraSorteada);
            perguntarReiniciarOuSair();
        } else if (mascara.toString().equals(palavraSorteada)) {
            JOptionPane.showMessageDialog(panel, "Você venceu!");
            perguntarReiniciarOuSair();
        }
    }

    // Perguntar ao usuário se deseja reiniciar ou sair
    private void perguntarReiniciarOuSair() {
        int resposta = JOptionPane.showOptionDialog(panel, "Deseja jogar novamente?", "Fim de Jogo",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (resposta == JOptionPane.YES_OPTION) {
            reiniciarJogo();
        } else {
            System.exit(0);
        }
    }

    // Reiniciar o jogo após vitória ou derrota
    private void reiniciarJogo() {
        this.maximoTentativas = 10;
        this.pontuacao = 0;
        this.tentativas = 0;
        palavraSorteada = escolherDificuldade(); // Sortear nova palavra
        lblPalavra.setText("Palavra: " + palavra.criarMascara(palavraSorteada));
        lblTentativas.setText("Tentativas restantes: " + maximoTentativas);
        lblPontuacao.setText("Pontuação: " + pontuacao);
        lblLetrasErradas.setText("Letras erradas: ");
        lblBoneco.setText("Boneco: " + String.join(" ", forca.getBoneco()));
    }

    // Métodos de salvar e carregar estado, getters e setters, e main
    // ...

    public static void main(String[] args) {
        // Inicializar o jogo com a interface Swing
        new Jogo();
    }
}