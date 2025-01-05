
//Import des biblio OpenGL
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

//Import des Classes dans les packages
import deplacements.DeplacementJoueur;
import deplacements.DeplacementMonstre;
import deplacements.DeplacementProjectile;
import utils.Joueur;
import utils.Monstre;
import utils.Projectile;
import utils.Score;

import javax.swing.JOptionPane;
//Import des biblio Java
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game extends GLCanvas implements GLEventListener, KeyListener {

    // Variable d'initilisation du je u.
    private static final int NBR_LIGNES = 4;
    private static final int NBR_MONSTRES_LIGNES = 8;
    private boolean deplacementDroite = true;
    private boolean jeuLancer;
    private float joueurX = 0.0f;
    private ArrayList<Projectile> projectiles;
    private Timer DeplacementMonstreMs;
    private Score score;
    private Monstre[][] monstres;

    public Game() {
        // Init de GLCanvas, de la fenetre et l'ecoute d'evenement
        addGLEventListener(this);
        setFocusable(true);
        addKeyListener(this);

        // Initialisation des besoins du jeu
        monstres = new Monstre[NBR_LIGNES][NBR_MONSTRES_LIGNES];
        projectiles = new ArrayList<>();
        score = new Score();
        jeuLancer = true;

        // Reset des monstres et init du timer
        resetMonstres();
        DeplacementMonstreMs = new Timer((int) DeplacementMonstre.getMoveInterval(), e -> {
            if (jeuLancer) {
                DeplacementMonstres();
                repaint();
            }
        });
        DeplacementMonstreMs.start();
    }

    // Reset la position des monstres en recentrant les monstres dans la fenetre
    public void resetMonstres() {
        float startX = -((NBR_MONSTRES_LIGNES - 1) * 0.3f) / 2;
        for (int ligne = 0; ligne < NBR_LIGNES; ligne++) {
            for (int colonne = 0; colonne < NBR_MONSTRES_LIGNES; colonne++) {
                float x = startX + colonne * 0.3f;
                float y = 1.0f - ligne * 0.3f;
                // Creation du monstre
                monstres[ligne][colonne] = new Monstre(x, y);
            }
        }
        deplacementDroite = true;
    }

    public void DeplacementMonstres() {
        boolean changementDirection = DeplacementMonstre.verifierChangementDirection(monstres);

        // Vérifier collision avec la ligne du joueur
        if (DeplacementMonstre.verifierCollisionJoueur(monstres)) {
            endGame();
            return;
        }

        // si il reste des monstres
        int monstresVivants = DeplacementMonstre.compterMonstresVivant(monstres);
        // Si toiut les monstres sont mort ajouté 1000 points
        if (monstresVivants == 0) {
            score.addPoints(1000);
            resetMonstres();
            return;
        }

        // Mettre à jour l'intervalle de deplacement en fonctione du nombre de monstre
        // présent
        long ajustementVitesse = DeplacementMonstre.calculerIntervalDeplacement(
                monstres, NBR_LIGNES * NBR_MONSTRES_LIGNES);
        DeplacementMonstreMs.setDelay((int) ajustementVitesse);

        // Déplacer les monstres
        DeplacementMonstre.deplacerMonstres(monstres, deplacementDroite, changementDirection);

        if (changementDirection) {
            deplacementDroite = !deplacementDroite;
        }
    }

    public void deplacementProjectiles() {
        DeplacementProjectile.DeplacementDuProjectile(projectiles, monstres);
        // Ajouter des points pour chaque monstre tué
        score.addPoints(10 * MonstreTuer());
        repaint();
    }

    public int MonstreTuer() {
        int count = 0;
        // Cherche les lignes et colonne des monstres
        for (int ligne = 0; ligne < NBR_LIGNES; ligne++) {
            for (int colonne = 0; colonne < NBR_MONSTRES_LIGNES; colonne++) {
                // si le monstre existe et est tué
                if (monstres[ligne][colonne] != null && !monstres[ligne][colonne].Vivant()) {
                    count++;
                    // Supprime le monstre une fois mort
                    monstres[ligne][colonne] = null;
                }
            }
        }
        return count;
    }

    // Méthode fin du jeu
    public void endGame() {
        jeuLancer = false;
        DeplacementMonstreMs.stop();
        JOptionPane.showMessageDialog(null, "Game Over! Score final : " + score.getScore());
    }

    // Méthode pour restart le jeu
    public void restartGame() {
        score = new Score();
        resetMonstres();
        joueurX = 0.0f;
        projectiles.clear();
        jeuLancer = true;
        DeplacementMonstreMs.restart();
    }

    // Retourne le score actuel
    public int getScore() {
        return score.getScore();
    }

    // Creation du projectile en les parcourant tous
    public void drawProjectiles(GL2 gl) {
        for (Projectile projectile : projectiles) {
            gl.glPushMatrix();
            gl.glTranslatef(projectile.getX(), projectile.getY(), -4.5f);
            gl.glColor3f(1.0f, 1.0f, 0.0f);
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3f(-0.02f, -0.1f, 0.0f);
            gl.glVertex3f(0.02f, -0.1f, 0.0f);
            gl.glVertex3f(0.02f, 0.1f, 0.0f);
            gl.glVertex3f(-0.02f, 0.1f, 0.0f);
            gl.glEnd();
            gl.glPopMatrix();
        }
    }

    // Parametre d'init pour OpenGL
    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        if (!jeuLancer)
            return;

        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        // Dessine le joueur
        gl.glPushMatrix();
        gl.glTranslatef(joueurX, -1.5f, -5.0f);
        Joueur.Pyramide(gl, 0.1f);
        gl.glPopMatrix();

        // Dessine les monstres
        for (int ligne = 0; ligne < NBR_LIGNES; ligne++) {
            for (int colonne = 0; colonne < NBR_MONSTRES_LIGNES; colonne++) {
                Monstre monstre = monstres[ligne][colonne];
                if (monstre != null && monstre.Vivant()) {
                    gl.glPushMatrix();
                    // Deplace le monstre
                    gl.glTranslatef(monstre.getX(), monstre.getY(), -5.0f);
                    // Dessine le monstre cubique
                    Monstre.Cube(gl, 0.1f);
                    gl.glPopMatrix();
                }
            }
        }

        // Déplace et dessine les projectiles
        deplacementProjectiles();
        drawProjectiles(gl);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // Definit les parametre pour l'affichage 3D
        GL2 gl = drawable.getGL().getGL2();
        if (height <= 0)
            height = 1;

        // Défini la vue
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // / Calcul du rapport de l'aspect
        float aspect = (float) width / height;
        new GLU().gluPerspective(45.0, aspect, 1.0, 20.0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // Nettoyage des ressources
        if (DeplacementMonstreMs != null) {
            DeplacementMonstreMs.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Si le jeu est pas lance on ne fait rien
        if (!jeuLancer)
            return;

        // Detecte la touche presse et effectue l'action associé
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> joueurX = DeplacementJoueur.deplacerJoueur(joueurX, -1);
            case KeyEvent.VK_RIGHT -> joueurX = DeplacementJoueur.deplacerJoueur(joueurX, 1);
            case KeyEvent.VK_SPACE -> projectiles.add(new Projectile(joueurX, -1.4f));
            case KeyEvent.VK_R -> {
                if (!jeuLancer) {
                    restartGame();
                }
            }
        }
        // rafraichir l'affichage
        repaint();
    }

    // Non utilisé
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}