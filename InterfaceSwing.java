import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InterfaceSwing {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Jogo jogo;

    public InterfaceSwing() throws IOException {
        jogo = new Jogo(this); // Passar a instância de InterfaceSwing para Jogo
        initUI();
    }

    private void initUI() {
        JFrame frame = new JFrame("Jogo da Forca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
    
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
    
        // Tela Inicial
        JPanel telaInicial = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes
        gbc.gridx = 0; // Coluna 0
        gbc.gridy = 0; // Linha 0
        gbc.anchor = GridBagConstraints.CENTER; // Alinhamento central
    
        // Adicionar a descrição
        JLabel lblDescricao = new JLabel("Bem-vindo ao Jogo da Forca!");
        lblDescricao.setForeground(Color.PINK);
        lblDescricao.setFont(new Font("Arial", Font.BOLD, 24)); // Aumentar a fonte da descrição
        telaInicial.add(lblDescricao, gbc);
    
        // Incrementar a linha para adicionar a imagem abaixo da descrição
        gbc.gridy++;
    
        // Carregar e adicionar a imagem
        ImageIcon imagemForcaIcon = new ImageIcon("TPPOO2/ImagensBoneco2/jogoForca.png");
        Image imagemForca = imagemForcaIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel lblImagemForca = new JLabel(new ImageIcon(imagemForca));
        telaInicial.add(lblImagemForca, gbc);
    
        // Incrementar a linha para adicionar os botões abaixo da imagem
        gbc.gridy++;
    
        JButton btnIniciar = new JButton("Iniciar Jogo");
        btnIniciar.setForeground(Color.BLUE);
        btnIniciar.setPreferredSize(new Dimension(200, 50)); // Definir tamanho preferencial
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 18)); // Aumentar a fonte do botão
        telaInicial.add(btnIniciar, gbc);
    
        gbc.gridy++; // incrementar a linha para adicionar o Jogo Salvo abaixo do botão Iniciar
        JButton btnAbrir = new JButton("Abrir Jogo Salvo");
        btnAbrir.setForeground(Color.ORANGE);
        btnAbrir.setPreferredSize(new Dimension(200, 50)); // Definir tamanho preferencial
        btnAbrir.setFont(new Font("Arial", Font.BOLD, 18)); // Aumentar a fonte do botão
        telaInicial.add(btnAbrir, gbc);
    
        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Tela Jogo");
            }
        });
    
        btnAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    jogo.carregarDados();
                    cardLayout.show(mainPanel, "Tela Jogo");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Erro ao carregar progresso: " + ex.getMessage());
                }
            }
        });
    
        JPanel telaJogo = jogo.getPanel();
    
        // mudar aqui
        JPanel telaFinal = new JPanel(new BorderLayout());
       
        JLabel lblMensagemFinal = new JLabel("Fim de Jogo!", JLabel.CENTER);
        
        JButton btnReiniciar = new JButton("Reiniciar Jogo");
    
        JButton btnSalvar = new JButton("Salvar");

        String venceuOuPerdeu = "";
        
        mostrarTelaFinalJpanel(telaFinal, lblMensagemFinal, btnReiniciar, btnSalvar, venceuOuPerdeu);
    
        mainPanel.add(telaInicial, "Tela Inicial");
        mainPanel.add(telaJogo, "Tela Jogo");
        mainPanel.add(telaFinal, "Tela Final");
    
        frame.add(mainPanel);
        frame.setVisible(true);
    }

        /*...*/
        public void mostrarTelaFinal(String mensagemFinal) throws IOException {
            JPanel telaFinal = new JPanel(new BorderLayout());
            JLabel lblMensagemFinal = new JLabel(mensagemFinal, JLabel.CENTER);
            JButton btnReiniciar = new JButton("Reiniciar Jogo");
            JButton btnSalvar = new JButton("Salvar");
    
            mostrarTelaFinalJpanel(telaFinal, lblMensagemFinal, btnReiniciar, btnSalvar, mensagemFinal);
    
            mainPanel.add(telaFinal, "Tela Final");
            cardLayout.show(mainPanel, "Tela Final");
        }
    
        public void mostrarTelaFinalJpanel(JPanel telaFinal, JLabel lblMensagemFinal, JButton btnReiniciar, JButton btnSalvar, String venceuOuPerdeu) {
            lblMensagemFinal.setText(venceuOuPerdeu); // Definir a mensagem de vitória ou derrota
            lblMensagemFinal.setFont(new Font("Arial", Font.BOLD, 24)); // Aumentar a fonte da mensagem final
                
            btnReiniciar.setPreferredSize(new Dimension(200, 100)); // Definir tamanho preferencial
            btnReiniciar.setFont(new Font("Arial", Font.BOLD, 24)); // Aumentar a fonte do botão
        
            btnSalvar.setPreferredSize(new Dimension(200, 100)); // Definir tamanho preferencial
            btnSalvar.setFont(new Font("Arial", Font.BOLD, 24)); // Aumentar a fonte do botão
    
            btnReiniciar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(mainPanel, "Tela Inicial");
                    jogo.reiniciarJogo();
                }
            });
        
            btnSalvar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        jogo.salvarDados();
                        JOptionPane.showMessageDialog(mainPanel, "Progresso salvo com sucesso!");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "Erro ao salvar progresso: " + ex.getMessage());
                    }
                }
            });
    
            JPanel panelBtnsFinal = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelBtnsFinal.add(btnSalvar);
            panelBtnsFinal.add(btnReiniciar);
        
            telaFinal.add(lblMensagemFinal, BorderLayout.CENTER);
            telaFinal.add(panelBtnsFinal, BorderLayout.SOUTH);
        }
        /*...*/
    
    public static void main(String[] args) {
        try {
            // Tenta aplicar o tema Nimbus
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { 
                // Se encontrar o tema Nimbus
                if ("Nimbus".equals(info.getName())) { 
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Não foi possível aplicar o tema Nimbus");
        }
        try {
            new InterfaceSwing();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}