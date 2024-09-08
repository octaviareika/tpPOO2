import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceSwing {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel telaInicial, telaFinal;

    public InterfaceSwing() {
        initUI();
    }

    private void initUI() {
        frame = new JFrame("Jogo da Forca");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        initTelaInicial();
        initTelaJogo();
        initTelaFinal();

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void initTelaInicial() {
        telaInicial = new JPanel(new BorderLayout());

        JLabel lblTitulo = new JLabel("Bem-vindo ao Jogo da Forca!", JLabel.CENTER);
        JButton btnIniciar = new JButton("Iniciar Jogo");

        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Tela Jogo");
            }
        });

        telaInicial.add(lblTitulo, BorderLayout.CENTER);
        telaInicial.add(btnIniciar, BorderLayout.SOUTH);

        mainPanel.add(telaInicial, "Tela Inicial");
    }

    private void initTelaJogo() {
        Jogo jogo = new Jogo();
        mainPanel.add(jogo.getPanel(), "Tela Jogo"); // Usar o método getPanel
    }

    private void initTelaFinal() {
        telaFinal = new JPanel(new BorderLayout());

        JLabel lblMensagemFinal = new JLabel("Fim de Jogo!", JLabel.CENTER);
        JButton btnReiniciar = new JButton("Reiniciar Jogo");

        btnReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Tela Inicial");
            }
        });

        telaFinal.add(lblMensagemFinal, BorderLayout.CENTER);
        telaFinal.add(btnReiniciar, BorderLayout.SOUTH);

        mainPanel.add(telaFinal, "Tela Final");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfaceSwing();
            }
        });
    }
}