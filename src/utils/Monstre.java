package utils;

import com.jogamp.opengl.GL2;

public class Monstre {
    private float x, y;
    private boolean Vivant; 

    // Constructeur de la classe Monstre
    public Monstre(float x, float y) {
        // Init des positons et  de son statut
        this.x = x;
        this.y = y;
        this.Vivant = true; 
    }

    //Methode pour changer la vie d'un monstre
    public void detruire() {
        this.Vivant = false;
    }

    // Crée la forme du monstre (cubique)
    public static void Cube(GL2 gl, float taille) {
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBegin(GL2.GL_QUADS);

        // Face AV
        gl.glVertex3f(-taille, -taille, taille);
        gl.glVertex3f(taille, -taille, taille);
        gl.glVertex3f(taille, taille, taille);
        gl.glVertex3f(-taille, taille, taille);

        // Face AR
        gl.glVertex3f(-taille, -taille, -taille);
        gl.glVertex3f(-taille, taille, -taille);
        gl.glVertex3f(taille, taille, -taille);
        gl.glVertex3f(taille, -taille, -taille);

        // Face HAUT
        gl.glVertex3f(-taille, taille, -taille);
        gl.glVertex3f(-taille, taille, taille);
        gl.glVertex3f(taille, taille, taille);
        gl.glVertex3f(taille, taille, -taille);

        // Face BAS
        gl.glVertex3f(-taille, -taille, -taille);
        gl.glVertex3f(taille, -taille, -taille);
        gl.glVertex3f(taille, -taille, taille);
        gl.glVertex3f(-taille, -taille, taille);

        // Face DR
        gl.glVertex3f(taille, -taille, -taille);
        gl.glVertex3f(taille, taille, -taille);
        gl.glVertex3f(taille, taille, taille);
        gl.glVertex3f(taille, -taille, taille);

        // Face GA
        gl.glVertex3f(-taille, -taille, -taille);
        gl.glVertex3f(-taille, -taille, taille);
        gl.glVertex3f(-taille, taille, taille);
        gl.glVertex3f(-taille, taille, -taille);
        gl.glEnd();
    }

    //Getter 
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public boolean Vivant() {
        return Vivant;
    }

    // Setter
    public void setX(float x) {
        this.x = x;
    }
    
    public void setY(float y) {
        this.y = y;
    }
}


