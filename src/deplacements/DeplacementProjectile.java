package deplacements;

import java.util.ArrayList;
import utils.Monstre;
import utils.Projectile;

public class DeplacementProjectile {
    // 
    private static final float SEUIL_DE_COLLISION = 0.15f;

    public static void DeplacementDuProjectile(ArrayList<Projectile> projectiles, Monstre[][] monstres) {
        ArrayList<Projectile> SupprimerProjectiles = new ArrayList<>();

        // Parcours des projectiles
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);            
            projectile.deplacement(); 

            // verifie si il y a des collisions avec les monstres
            for (int row = 0; row < monstres.length; row++) {
                for (int col = 0; col < monstres[row].length; col++) {
                    Monstre monstre = monstres[row][col];
                    
                    // Ss le monstre est vivant et si le projectile entre en collision avec lui
                    if (monstre != null && monstre.Vivant() && enCollision(projectile, monstre)) {
                        // On tue le monstre et supprime le projectile et on sort de la boucle
                        monstre.detruire(); 
                        SupprimerProjectiles.add(projectile); 
                        break;
                    }
                }
            }

            // Si le projectile sort de l'ecran on le supprime
            if (projectile.HorsEcran()) {
                SupprimerProjectiles.add(projectile);
            }
        }

        // Retire les projectiles qui sont hors de l'ecran ou qui ont toucher un monstre
        projectiles.removeAll(SupprimerProjectiles);
    }

    // Verifie si un projectile et en collision avec un monstre
    private static boolean enCollision(Projectile projectile, Monstre monstre) {
        float x = Math.abs(projectile.getX() - monstre.getX());
        float y = Math.abs(projectile.getY() - monstre.getY());
        // Si la différence de position est < au seuil de collision alors on simule la colision
        return x < SEUIL_DE_COLLISION && y < SEUIL_DE_COLLISION;
    }
}
