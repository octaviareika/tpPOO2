import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Jogo {
    Palavra palavra;
   // private int maximoTentativas;
    private Forca forca;
    int pontuacao;
    private int tentativas;
    private String palavraSorteada; // Adicionar variável de instância para armazenar a palavra sorteada
    private Set<Character> letrasDigitadas; // Set para armazenar letras digitadas

    // Componentes da interface Swing
    private InterfaceSwing interfaceSwing; // Adicionar referência à InterfaceSwing

    private JPanel panel;
    private JLabel lblPalavra, lblTentativas, lblPontuacao, lblMensagem, lblLetrasErradas, lblBoneco;
    private JTextField txtLetra;
    private JButton btnEnviar;
    private JButton btnSalvar;
    private JLabel lbldicas;

    Jogo(InterfaceSwing interfaceSwing) throws IOException {
      // this.maximoTentativas = 10;
        this.palavra = new Palavra();
        this.forca = new Forca();
        this.pontuacao = 0;
        this.tentativas = 10;
        this.letrasDigitadas = new HashSet<>();
        this.interfaceSwing = interfaceSwing; // Inicializar a referência
        //carregarDados();
        initSwingComponents(); // Inicializar a interface Swing
    }

public void initSwingComponents() {
    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Define o layout como BoxLayout

    // Sortear uma palavra
    if (palavraSorteada == null) {
        palavraSorteada = escolherDificuldade(); // Armazenar a palavra sorteada
    }

    // Rótulo para exibir a palavra mascarada
    lblPalavra = new JLabel("Palavra: " + palavra.criarMascara(palavraSorteada), SwingConstants.CENTER);
    lblPalavra.setFont(new Font("Arial", Font.BOLD, 14)); // Aumentar a fonte do rótulo
    lblTentativas = new JLabel("Tentativas restantes: " + tentativas, SwingConstants.CENTER);
    lblTentativas.setFont(new Font("Arial", Font.BOLD, 14)); 
    lblPontuacao = new JLabel("Pontuação: " + pontuacao, SwingConstants.CENTER);
    lblPontuacao.setFont(new Font("Arial", Font.BOLD, 14));
    lblMensagem = new JLabel("", SwingConstants.CENTER);
    lblMensagem.setFont(new Font("Arial", Font.BOLD, 14));
    lblLetrasErradas = new JLabel("Letras erradas: ", SwingConstants.CENTER);
    lblLetrasErradas.setFont(new Font("Arial", Font.BOLD, 14));
    
    // Rótulo para exibir a imagem do boneco
    lblBoneco = new JLabel();
    lblBoneco.setHorizontalAlignment(SwingConstants.CENTER);
    atualizarImagemBoneco(); // Atualizar a imagem inicial do boneco

    lbldicas = new JLabel("DICA: " + palavra.adicionarDicasParaCadaPalavra(palavraSorteada), SwingConstants.CENTER);
    lbldicas.setFont(new Font("Arial", Font.BOLD, 14));
    lbldicas.setForeground(Color.BLUE);
    

    // Campo de texto para inserir a letra
    txtLetra = new JTextField(2);
    txtLetra.setPreferredSize(new Dimension(50, 30)); // Definir tamanho preferencial
    txtLetra.setHorizontalAlignment(JTextField.CENTER); // Centralizar o texto

    // Botão para enviar a letra
    btnEnviar = new JButton("Enviar Letra");
    btnEnviar.setPreferredSize(new Dimension(150, 30)); // Definir tamanho preferencial
    btnEnviar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String letra = txtLetra.getText().trim(); // trim remove espaços em branco
            txtLetra.setText("");   // Limpar o campo de texto

            // Processar a letra enviada
            try {
                processarLetra(letra);
            } catch (IOException e1) {
               
                e1.printStackTrace();
            }
        }
    });


    // Botão para salvar o jogo
    btnSalvar = new JButton("Salvar Jogo");
    // colocar o botão salvar no painel
    btnSalvar.setPreferredSize(new Dimension(150, 30)); // Definir tamanho preferencial
    
    btnSalvar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                salvarDados();
                JOptionPane.showMessageDialog(panel, "Progresso salvo com sucesso!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao salvar progresso: " + ex.getMessage());
            }
        }
    });

    // Painel para o campo de texto e o botão
    JPanel inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(txtLetra);
    inputPanel.add(btnEnviar);
    inputPanel.add(btnSalvar);

    // Adicionar os componentes ao painel principal
    panel.add(lblPalavra);
    panel.add(lblTentativas);
    panel.add(lblPontuacao);
    panel.add(lblMensagem);
    panel.add(lbldicas);
    panel.add(lblLetrasErradas);
    panel.add(inputPanel); // Adicionar o painel de entrada
    
    panel.add(lblBoneco);
}

    public JPanel getPanel() {
        return panel;
    }

    public String escolherDificuldade() {
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
                JOptionPane.showMessageDialog(panel, "Jogo fechado");
                System.exit(1);
                return null;
        }
    }

    public static void playSound(String filePath){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip(); // cria um clip de audio, o que significa que ele pode ser tocado
            clip.open(audioInputStream); // abre o arquivo de audio
            clip.start();//começar
        } catch (Exception e) {
            System.out.println("Erro ao tocar o som: " + e.getMessage());
        }
    }

    // Processar a letra inserida pelo jogador
    public void processarLetra(String letra) throws IOException {
        if (letra.length() != 1) {
            JOptionPane.showMessageDialog(panel, "Digite apenas uma letra por vez!");
            return;
        }

        if (Character.isDigit(letra.charAt(0))) {
            JOptionPane.showMessageDialog(panel, "Digite apenas letras!");
            return;
        }

        char letraChar = letra.charAt(0); // converte string pra char
        // Verficar se ja tem a letra na mascara
        if (letrasDigitadas.contains(letraChar)){
            JOptionPane.showMessageDialog(panel, "Você ja digitou essa letra!");
            return;
        }
        
        letrasDigitadas.add(letraChar);
        StringBuilder mascara = new StringBuilder(lblPalavra.getText().replace("Palavra: ", ""));


        // Verificar se a letra está correta
        if (palavraSorteada.contains(letra)) {
            // Atualiza a palavra mascarada com a letra correta
            for (int i = 0; i < palavraSorteada.length(); i++) {
                if (palavraSorteada.charAt(i) == letra.charAt(0)) {
                    mascara.setCharAt(i, letra.charAt(0));
                }
            }
            playSound("TPPOO2/musicas/acertaPalavra.wav");
            lblMensagem.setText("Acertou a letra!");
            lblMensagem.setForeground(Color.GREEN);
            
            pontuacao += 20;
            lblPontuacao.setForeground(Color.GREEN);
        } else {
            playSound("TPPOO2/musicas/letraErrada.wav");
            lblMensagem.setText("Errou a letra!");
            lblMensagem.setForeground(Color.RED);
            pontuacao -= 5;
            lblPontuacao.setForeground(Color.RED);
            tentativas--;
            forca.adicionarErro();
            lblLetrasErradas.setText(lblLetrasErradas.getText() + letra + " ");
            atualizarImagemBoneco(); // Atualizar a imagem do boneco

        }

        // Atualizar os rótulos na interface
        lblPalavra.setText("Palavra: " + mascara.toString());
        lblTentativas.setText("Tentativas restantes: " + (tentativas));
        lblPontuacao.setText("Pontuação: " + pontuacao);

        // Verificar vitória ou derrota
        if (tentativas <= 0) {
            JOptionPane.showMessageDialog(panel, "Você perdeu! A palavra era: " + palavraSorteada);
            playSound("TPPOO2/musicas/jogoPerdido.wav");
            perguntarReiniciarOuSair(false);
        } else if (mascara.toString().equals(palavraSorteada)) {
            JOptionPane.showMessageDialog(panel, "Você venceu!");
            playSound("TPPOO2/musicas/vitoria.wav");
           perguntarReiniciarOuSair(true);
        }
    }

    // Método para atualizar a imagem do boneco
    public void atualizarImagemBoneco() {
        ImageIcon imagemErro = forca.getImagemErro();
        if (imagemErro != null) {
            lblBoneco.setIcon(imagemErro);
        }
    }

    // Perguntar ao usuário se deseja reiniciar ou sair
    public void perguntarReiniciarOuSair(boolean venceu) throws IOException {
        String mensagemFinal = venceu ? "Você venceu! Deseja reiniciar o jogo?" : "Você perdeu! Deseja reiniciar o jogo?";
        interfaceSwing.mostrarTelaFinal(mensagemFinal);
    }

    // Reiniciar o jogo após vitória ou derrota
    public void reiniciarJogo() {
        this.pontuacao = 0;
        this.tentativas = 10;
        this.letrasDigitadas.clear();
        palavraSorteada = escolherDificuldade(); // Sortear nova palavra
        lblPalavra.setText("Palavra: " + palavra.criarMascara(palavraSorteada));
        lblTentativas.setText("Tentativas restantes: " +  tentativas);
        lblPontuacao.setText("Pontuação: " + pontuacao);
        lblPontuacao.setForeground(Color.BLACK);
        lblLetrasErradas.setText("Letras erradas: ");
        lblMensagem.setText("");
        forca.resetarBoneco(); // Resetar o boneco
        forca.resetarErros();
        atualizarImagemBoneco();
    }

    // Métodos de salvar e carregar estado, getters e setters, e main

    public void salvarDados() throws IOException {
        File arquivo = new File("dadoJogo.txt");
        System.out.println("Salvando dados no arquivo: " + arquivo.getAbsolutePath());
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write(palavraSorteada + "\n");
            writer.write(lblPalavra.getText() + "\n");
            writer.write("Tentativas restantes: " + (tentativas) + "\n");
            writer.write("Pontuação: " + pontuacao + "\n"); // Adicionar o prefixo "Pontuação: "
            writer.write("DICA: " + palavra.adicionarDicasParaCadaPalavra(palavraSorteada) + "\n");
    
            for (char letra : letrasDigitadas) {
                writer.write(letra + "\n");
            }
            
        }
    
        System.out.println("Arquivo salvo");
    }

    public void carregarDados() throws IOException {
        File arquivo = new File("dadoJogo.txt");
        System.out.println("Carregando dados do arquivo: " + arquivo.getAbsolutePath());
    
        if (!arquivo.exists()) {
            throw new FileNotFoundException("Arquivo de dados não encontrado: " + arquivo.getAbsolutePath());
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            palavraSorteada = reader.readLine();
            lblPalavra.setText(reader.readLine());
    
            // Tentativas 
            String tentativasStr = reader.readLine();
            if (tentativasStr.startsWith("Tentativas restantes: ")) {
                tentativasStr = tentativasStr.substring(22);
            }
    
            tentativas = Integer.parseInt(tentativasStr);
            lblTentativas.setText("Tentativas restantes: " + tentativas);
    
            // Remover o prefixo "Pontuação: " antes de converter para inteiro
            String pontuacaoStr = reader.readLine();
            if (pontuacaoStr.startsWith("Pontuação: ")) {
                pontuacaoStr = pontuacaoStr.substring(11); // Remover "Pontuação: "
            }
            pontuacao = Integer.parseInt(pontuacaoStr);
            lblPontuacao.setText("Pontuação: " + pontuacao);

            // DICAS AQUI
            lbldicas.setText(reader.readLine());
            if (lbldicas.getText().startsWith("DICA: ")) {
                lbldicas.setText(lbldicas.getText().substring(6));
                lbldicas.setText("DICA: " + palavra.adicionarDicasParaCadaPalavra(palavraSorteada));
            }
    
            StringBuilder letrasBuilder = new StringBuilder(); // Para construir a string das letras digitadas
            String linha;
            while ((linha = reader.readLine()) != null) {
                char letra = linha.charAt(0);
                letrasDigitadas.add(letra);
                letrasBuilder.append(letra).append(" "); // Adicionar a letra à string
            }
    
            // Atualizar o componente que exibe as letras digitadas
            lblLetrasErradas.setText("Letras digitadas: " + letrasBuilder.toString().trim());
        }
    }
    
    public static void main(String[] args) throws IOException {
        // Inicializar o jogo com a interface Swing
        InterfaceSwing interfaceSwing = new InterfaceSwing();
        Jogo jogo = new Jogo(interfaceSwing);
        
    }
}