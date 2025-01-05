<h3 align="center">Space Invader Java</h3>
<p align="center">Création du Jeu SpaceInvader avec le langage Java et OpenGL</p>

## Fonctionnalités
Voici les principales fonctionnalités du SpaceInvader :

- Lancer une partie.
- Jouer une partie.
- Deplacement de plus en plus rapide des monstres en fonction du nombres restant de monstre vivant.
- Recreer des monstres quand ils sont mort.
- Déplacement en X du joueur.
- Compte le score une fois la partie perdu.

## Lancement de l'application

Au lancement du SpaceInvader une fenêtre apparait et il faut lancer la partie :

Vous pouvez maintenant jouer !

## Prérequis
Avant de commencer, assurez-vous d'avoir une connexion haut débit et installé les outils suivants sur votre machine :

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Java SDK** : [Lien du site de Java](https://www.oracle.com/java/technologies/downloads/)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Git** : [Lien du site de Git](https://git-scm.com/downloads)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**jogamp** : [Lien du site de Jogamp](https://jogamp.org/jogl/www/)

Vous pouvez vérifier leurs versions installées avec les commandes suivantes depuis votre terminal de commande :

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`javac --version` -> Affiche votre version de Node.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`git --version` -> Affiche votre version de Git.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Pour **jogamp**  vous devez impérativement associé `gluegen-rt.jar` et `jogl-all.jar` au projet java !!

## Installation

### 1. Cloner le projet
   
Ouvrez votre terminal et exécutez la commande suivante pour cloner le dépôt :

```git clone https://github.com/Souyyy/SpaceInvader/```

### 2. Accéder au répertoire
Naviguez dans le dossier du projet :

```cd SpaceInvader```

### 4. Lancer l'application depuis votre terminal

Démarrez le jeu :

```java Main.java```


## Technologies
Ce projet utilise une technologie:

<table align="center">
  <tbody>
    <tr>
      <td  border="0">
        <img width="70" src="https://upload.wikimedia.org/wikipedia/en/thumb/3/30/Java_programming_language_logo.svg/121px-Java_programming_language_logo.svg.png" alt="Java">
        <p align="center">Java</p>
      </td>
      <td  border="0">
        <img width="70" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/OpenGL_logo.svg/220px-OpenGL_logo.svg.png" alt="Java">
        <p align="center">OpenGL</p>
      </td>
    </tr>
  </tbody>
</table>

## Structure du projet

Le projet est structuré en plusieurs packages, chacun gérant des fonctionnalités spécifiques :

**deplacements** : Contient les classes pour les déplacements, DeplacementJoueur, DeplacementMonstre et DeplacementProjectile.

**utils** : Regroupe les utilitaire tels que le Joueur, Monstre, Score et Projectile.

**Game** : Est la classe du Jeu, celle ci défini les règles et permet le bon fonctionnement de celui ci.

**Main** : Main, quant à lui permet de lancer la fêntre qui va ensuite rediriger sur le jeu 

## Bug & Amélioration
- J'ai eu un bug pour l'ajout du score, j'aurais pu ajouter le score directement dans la fenetre de jeu
- J'ai eu un peu de mal à implémenter la vitesse de deplacement en fonction du nombre de monstre restant (d'ailleurs bug un peu)
- Je peux faire en sorte d'améliorer le rendu 3D du jeu, il est très simpliste

## Licence
Ce programme est sous licence MIT.
