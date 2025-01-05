package deplacements;

public class DeplacementJoueur {

    // Variable constante
    private static final float JOUEUR_VITESSE = 0.1f;
    private static final float JOUEUR_MIN_X = -1.5f;
    private static final float JOUEUR_MAX_X = 1.5f;


    // Fonction qui permet de deplacer le joueur de gauche a droite (seulement sur l'axe X)
    public static float deplacerJoueur(float joueurX, int direction) {
        float axeX = joueurX;
        
        //  Si la direction est vers la gauche ou la droite et que le joueur depasse pas la limite minimal ou maximal
        if (direction < 0 && joueurX > JOUEUR_MIN_X) {
            axeX -= JOUEUR_VITESSE;
        } else if (direction > 0 && joueurX < JOUEUR_MAX_X) {
            axeX += JOUEUR_VITESSE;
        }
        
        return axeX;
    }

    // getter concernant les limites de l'axe X
    public static float getJoueurMinX() {
        return JOUEUR_MIN_X;
    }

    public static float getJoueurMaxX() {
        return JOUEUR_MAX_X;
    }
}