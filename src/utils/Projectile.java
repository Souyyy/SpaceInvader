package utils;

public class Projectile {
    private float x, y;
    private static final float VITESSE = 0.2f;

    // Constructeur de la classe Projectile
    public Projectile(float x, float y) {
        this.x = x;
        this.y = y;
    }

    //Deplacement du projectile sur l'axe Y
    public void deplacement() {
        y += VITESSE;
    }
    // Verifie si le projectile est hors de l'ecran
    public boolean HorsEcran() {
        return y > 1.0f; 
    }

    // Getter
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
