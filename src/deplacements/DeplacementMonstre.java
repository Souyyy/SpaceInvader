package deplacements;

import utils.Monstre;

public class DeplacementMonstre {
    // Variable pour le deplacment des monstres
    private static final long INTERVAL_DEPLACEMENT = 300;
    private static final float DEPLACEMENT_DISTANCE = 0.1f;
    private static final float DISTANCE_DE_DESCENTE = 0.1f;

    public static void deplacerMonstres(Monstre[][] monstres, boolean deplacementDroite, boolean changementDirection) {
        // Si on descend il y a un changement de direction
        if (changementDirection) {
            for (int i = 0; i < monstres.length; i++) {
                for (int j = 0; j < monstres[i].length; j++) {
                    Monstre monstre = monstres[i][j];
                    if (monstre != null && monstre.Vivant()) {
                        // deplacement Y et X
                        monstre.setY(monstre.getY() - DISTANCE_DE_DESCENTE);
                        monstre.setX(monstre.getX() + (deplacementDroite ? -DEPLACEMENT_DISTANCE : DEPLACEMENT_DISTANCE));
                    }
                }
            }

        // sinon on deplace normalement
        } else {
            for (int i = 0; i < monstres.length; i++) {
                for (int j = 0; j < monstres[i].length; j++) {
                    Monstre monstre = monstres[i][j];
                    if (monstre != null && monstre.Vivant()) {
                        // deplacement des monstres vivants sur l'axe X
                        monstre.setX(monstre.getX() + (deplacementDroite ? DEPLACEMENT_DISTANCE : -DEPLACEMENT_DISTANCE));
                    }
                }
            }
        }
    }

    // Verifie si on doit effectuer un changement de direction des monstres
    public static boolean verifierChangementDirection(Monstre[][] monstres) {
        for (int i = 0; i < monstres.length; i++) {
            for (int j = 0; j < monstres[i].length; j++) {
                Monstre monstre = monstres[i][j];
                if (monstre != null && monstre.Vivant()) {
                    if (monstre.getX() >= DeplacementJoueur.getJoueurMaxX() || 
                        monstre.getX() <= DeplacementJoueur.getJoueurMinX()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // Verifie si il y a une collision d'un monstre sur l'etage du joueur
    public static boolean verifierCollisionJoueur(Monstre[][] monstres) {
        for (int i = 0; i < monstres.length; i++) {
            for (int j = 0; j < monstres[i].length; j++) {
                Monstre monstre = monstres[i][j];
                // Si il y a un monstre on renvoie true
                if (monstre != null && monstre.Vivant() && monstre.getY() <= -1.5f) {
                    return true;
                }
            }
        }
        return false;
    }


    // compte le nombre de monstre n'etant pas = a null
    public static int compterMonstresVivant(Monstre[][] monstres) {
        int count = 0;
        for (int i = 0; i < monstres.length; i++) {
            for (int j = 0; j < monstres[i].length; j++) {
                Monstre monstre = monstres[i][j];
                if (monstre != null && monstre.Vivant()) {
                    count++;
                }
            }
        }
        return count;
    }

    //Fait en sorte de changé la vitesse de de deplacement en fonction du noimbre de monstre restant moins il y'en a plus c'est rapide
    public static long calculerIntervalDeplacement(Monstre[][] monstres, int totalMonstres) {
        int monstresVivants = compterMonstresVivant(monstres);
        //renvoie renvoie l'interval de deplacement
        return INTERVAL_DEPLACEMENT - (long) ((INTERVAL_DEPLACEMENT - 50) * 
               (1 - (float) monstresVivants / totalMonstres));
    }

  
    // Renvoie le temps de déplacement des monstres en ms
    public static long getMoveInterval() {
        return INTERVAL_DEPLACEMENT;
    }
}
