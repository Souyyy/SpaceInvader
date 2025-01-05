import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private static final int WIDTH = 650;
    private static final int HEIGHT = 800;

    public Main() {
        // Configuration de la fenetre
        setTitle("Space Invaders");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuration du contenu 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Space Invaders 3D");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title1 = new JLabel("Space Invaders 3D créé par THEO DISY");
        title1.setFont(new Font("Arial", Font.BOLD, 14));
        title1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Démarrer");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> startGame());

        JButton quitButton = new JButton("Quitter");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.addActionListener(e -> System.exit(0));

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(title);
        mainPanel.add(title1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(startButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(quitButton);
        mainPanel.add(Box.createVerticalGlue());
        add(mainPanel);
        setVisible(true);
    }

    // Démarrer le jeu
    private void startGame() {
        getContentPane().removeAll();
        Game game = new Game();
        game.setFocusable(true);
        game.requestFocusInWindow();
        add(game, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Méthode main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
