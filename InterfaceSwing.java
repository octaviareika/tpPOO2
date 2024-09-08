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

        JPanel telaInicial = new JPanel(new BorderLayout());

        // Criar um painel intermediário com BoxLayout para alinhar verticalmente
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Colocar uma descrição na tela escrito bem vindo ao jogo da forca
        JLabel lblDescricao = new JLabel("Bem vindo ao jogo da forca", JLabel.CENTER);
        lblDescricao.setFont(new Font("Arial", Font.BOLD, 24)); // Aumentar a fonte da descrição
        lblDescricao.setForeground(Color.BLUE); // Alterar a cor da fonte para azul qqaqq
        lblDescricao.setAlignmentX(Component.CENTER_ALIGNMENT); // Centralizar o texto

        // botao iniciar
        JButton btnIniciar = new JButton("Iniciar Jogo");
        btnIniciar.setPreferredSize(new Dimension(200, 100)); // Definir tamanho preferencial
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 24)); // Aumentar a fonte do botão
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT); // Centralizar o botão

        // Adicionar a descrição e o botão ao painel intermediário
        panelCentral.add(lblDescricao);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20))); // Espaço entre a descrição e o botão
        panelCentral.add(btnIniciar);

        // Adicionar o painel intermediário ao centro do painel principal
        telaInicial.add(panelCentral, BorderLayout.CENTER);

        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Tela Jogo");
            }
        });

        JPanel telaJogo = jogo.getPanel();

        JPanel telaFinal = new JPanel(new BorderLayout());
        JLabel lblMensagemFinal = new JLabel("Fim de Jogo!", JLabel.CENTER);
        lblMensagemFinal.setFont(new Font("Arial", Font.BOLD, 24)); // Aumentar a fonte da mensagem final

        JButton btnReiniciar = new JButton("Reiniciar Jogo");
        btnReiniciar.setPreferredSize(new Dimension(200, 100)); // Definir tamanho preferencial
        btnReiniciar.setFont(new Font("Arial", Font.BOLD, 24)); // Aumentar a fonte do botão

        JButton btnSalvar = new JButton("Salvar");
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

        // Adicionar os botões a um painel intermediário com FlowLayout
        JPanel panelBtnsFinal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBtnsFinal.add(btnSalvar);
        panelBtnsFinal.add(btnReiniciar);

        telaFinal.add(lblMensagemFinal, BorderLayout.CENTER);
        telaFinal.add(panelBtnsFinal, BorderLayout.SOUTH);

        mainPanel.add(telaInicial, "Tela Inicial");
        mainPanel.add(telaJogo, "Tela Jogo");
        mainPanel.add(telaFinal, "Tela Final");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void mostrarTelaFinal() throws IOException {
        cardLayout.show(mainPanel, "Tela Final");
    }

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