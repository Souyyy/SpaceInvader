package utils;

import com.jogamp.opengl.GL2;

public class Joueur {

    // Crée la forme du joueur (pyramidale)
    public static void Pyramide(GL2 gl, float taille) {

        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glBegin(GL2.GL_TRIANGLES);

        // Face AV
        gl.glVertex3f(0.0f, taille, 0.0f);
        gl.glVertex3f(-taille, -taille, taille);
        gl.glVertex3f(taille, -taille, taille);

        // Face DR
        gl.glVertex3f(0.0f, taille, 0.0f);
        gl.glVertex3f(taille, -taille, taille);
        gl.glVertex3f(taille, -taille, -taille);

        // Face AR
        gl.glVertex3f(0.0f, taille, 0.0f);
        gl.glVertex3f(taille, -taille, -taille);
        gl.glVertex3f(-taille, -taille, -taille);

        // Face GA
        gl.glVertex3f(0.0f, taille, 0.0f);
        gl.glVertex3f(-taille, -taille, -taille);
        gl.glVertex3f(-taille, -taille, taille);

        gl.glEnd();

        // Face BAS
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3f(-taille, -taille, taille);
        gl.glVertex3f(taille, -taille, taille);
        gl.glVertex3f(taille, -taille, -taille);
        gl.glVertex3f(-taille, -taille, -taille);
        gl.glEnd();
    }
}
