import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;

public class MainGL extends GLCanvas implements GLEventListener {

    // Configuration des ennemis
    private final int rows = 5; // 5 lignes d'ennemis
    private final int cols = 10; // 10 ennemis par ligne
    private float[][] ennemis = new float[rows][cols]; // Position des ennemis
    private float vitesseEnnemi = 0.01f; // Vitesse de déplacement des ennemis
    private boolean allerDroite = true; // Direction des ennemis

    // Configuration du vaisseau
    private float vaisseauX = 0.0f; // Position initiale du vaisseau
    private final float vaisseauVitesse = 0.05f; // Vitesse du vaisseau
    private final float vaisseauLargeur = 0.2f; // Largeur du vaisseau
    private final float vaisseauHauteur = 0.1f; // Hauteur du vaisseau

    public MainGL() {
        this.addGLEventListener(this);

        // Initialisation des positions des ennemis
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ennemis[i][j] = -3.5f + j * 0.4f; // Espacement des ennemis
            }
        }

        // Gestion du déplacement du vaisseau avec les touches fléchées
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    if (vaisseauX > -3.8f) { // Limite gauche
                        vaisseauX -= vaisseauVitesse;
                    }
                } else if (key == KeyEvent.VK_RIGHT) {
                    if (vaisseauX < 3.8f - vaisseauLargeur) { // Limite droite
                        vaisseauX += vaisseauVitesse;
                    }
                }
            }
        });
        this.setFocusable(true);
    }

    // Méthode d'initialisation (appelée au démarrage)
    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Fond noir
        gl.glEnable(GL2.GL_DEPTH_TEST); // Activer le test de profondeur
    }

    // Méthode pour ajuster la vue lors du redimensionnement de la fenêtre
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        float aspect = (float) width / height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustum(-aspect, aspect, -1.0f, 1.0f, 1.0f, 100.0f);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    // Méthode pour dessiner chaque frame
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        // Dessiner les bordures grises à gauche et à droite
        gl.glColor3f(0.5f, 0.5f, 0.5f); // Gris
        gl.glBegin(GL2.GL_QUADS); // Dessiner la bordure gauche
        gl.glVertex3f(-4.5f, -2.0f, -5.0f); // Coin inférieur gauche
        gl.glVertex3f(-4.0f, -2.0f, -5.0f); // Coin inférieur droit
        gl.glVertex3f(-4.0f, 2.0f, -5.0f);  // Coin supérieur droit
        gl.glVertex3f(-4.5f, 2.0f, -5.0f);  // Coin supérieur gauche
        gl.glEnd();
        
        gl.glBegin(GL2.GL_QUADS); // Dessiner la bordure droite
        gl.glVertex3f(4.0f, -2.0f, -5.0f);  // Coin inférieur gauche
        gl.glVertex3f(4.5f, -2.0f, -5.0f);  // Coin inférieur droit
        gl.glVertex3f(4.5f, 2.0f, -5.0f);   // Coin supérieur droit
        gl.glVertex3f(4.0f, 2.0f, -5.0f);   // Coin supérieur gauche
        gl.glEnd();

        // Dessiner la zone de jeu au centre
        gl.glColor3f(0.0f, 0.0f, 0.0f); // Noir (fond de la zone de jeu)
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3f(-4.0f, -2.0f, -5.0f); // Coin inférieur gauche
        gl.glVertex3f(4.0f, -2.0f, -5.0f);  // Coin inférieur droit
        gl.glVertex3f(4.0f, 2.0f, -5.0f);   // Coin supérieur droit
        gl.glVertex3f(-4.0f, 2.0f, -5.0f);  // Coin supérieur gauche
        gl.glEnd();

        // Dessiner les ennemis
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Rouge
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                float xPos = ennemis[i][j]; // Position X de l'ennemi
                float yPos = 1.5f - i * 0.3f; // Position Y de l'ennemi
                gl.glBegin(GL2.GL_QUADS);
                gl.glVertex3f(xPos - 0.1f, yPos - 0.1f, -5.0f); // Coin inférieur gauche
                gl.glVertex3f(xPos + 0.1f, yPos - 0.1f, -5.0f); // Coin inférieur droit
                gl.glVertex3f(xPos + 0.1f, yPos + 0.1f, -5.0f); // Coin supérieur droit
                gl.glVertex3f(xPos - 0.1f, yPos + 0.1f, -5.0f); // Coin supérieur gauche
                gl.glEnd();
            }
        }

        // Dessiner le vaisseau
        gl.glColor3f(0.0f, 1.0f, 0.0f); // Vert
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3f(vaisseauX - vaisseauLargeur / 2, -1.8f, -5.0f); // Coin inférieur gauche
        gl.glVertex3f(vaisseauX + vaisseauLargeur / 2, -1.8f, -5.0f); // Coin inférieur droit
        gl.glVertex3f(vaisseauX + vaisseauLargeur / 2, -1.6f, -5.0f); // Coin supérieur droit
        gl.glVertex3f(vaisseauX - vaisseauLargeur / 2, -1.6f, -5.0f); // Coin supérieur gauche
        gl.glEnd();

        // Déplacer les ennemis
        deplacerEnnemis();
    }

    // Méthode pour déplacer les ennemis
    private void deplacerEnnemis() {
        // Vérifier si les ennemis doivent changer de direction
        if (allerDroite) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    ennemis[i][j] += vitesseEnnemi; // Déplacement vers la droite
                    if (ennemis[i][j] > 3.5f) { // Si un ennemi atteint le bord droit
                        allerDroite = false;
                    }
                }
            }
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    ennemis[i][j] -= vitesseEnnemi; // Déplacement vers la gauche
                    if (ennemis[i][j] < -3.5f) { // Si un ennemi atteint le bord gauche
                        allerDroite = true;
                    }
                }
            }
        }
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}

    public static void main(String[] args) {
        GLCanvas canvas = new MainGL();
        canvas.setPreferredSize(new Dimension(800, 600));
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setTitle("Space Invaders");
        frame.pack();
        frame.setVisible(true);
    }
}
