package Modele;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Joueur participant à la session d'un jeu Risk. Un joueur est identifié par un
 * nom unique et une couleur unique. Chaque joueur possède un certain nombre
 * d'unités à déployer attribués automatiquement à chaque nouveau tour.
 *
 * @author Karim
 */
public class Joueur implements Serializable {

    private String nom;
    private Color couleur;
    private ArrayList<Territoire> mesTerritoires;
    private int nbUniteADeployer;
    private int nbUniteTotalEnJeu;
    private int nbTourJoue;
    private String dureeJeu;

    /**
     * Constructeur de Joueur en fonction d'un nom donné et une d'une couleur
     * donné.
     *
     * @param nomJoueur : Le nom identifiant le joueur.
     * @param couleurJoueur : La couleur identifiant le joueur et ses unités sur
     * la carte.
     */
    public Joueur(String nomJoueur, Color couleurJoueur) {
        this.nom = nomJoueur;
        this.couleur = couleurJoueur;
        this.mesTerritoires = new ArrayList<>();
        this.nbUniteTotalEnJeu = 0;
        this.nbTourJoue = 1;
        this.dureeJeu = "00:00";
    }

    /**
     * Retourne le nom identifiant le Joueur.
     *
     * @return : Le nom du joueur
     */
    public String rendNom() {
        return this.nom;
    }

    /**
     * Renvois la couleur identifiant le Joueur.
     *
     * @return : La couleur du joueur.
     */
    public Color rendCouleur() {
        return this.couleur;
    }

    /**
     * Ajoute le nombre d'unités envoyés en paramètre aux unités à déployer du
     * Joueur.
     *
     * @param unites : Le nombre d'unité reçu pour le déploiement.
     */
    public void ajouteUnitesADeployer(int unites) {
        this.nbUniteADeployer += unites;
    }

    /**
     * Renvois le nombre d'unité que le Joueur peut déployer.
     *
     * @return : Le nombre d'unité possedé par le joueur en réserve.
     */
    public int rendUnitesADeployer() {
        return this.nbUniteADeployer;
    }

    /**
     * Retire une unité du nombre total d'unité que le joueur possède en
     * réserve.
     */
    public void retirerUnite() {
        this.nbUniteADeployer--;
        this.nbUniteTotalEnJeu++;
    }

    /**
     * Ajoute le territoire annexé à la liste des territoires contrôlé par le
     * Joueur.
     *
     * @param territoireCapture
     * @return : True si le territoire à été ajouté, false sinon.
     */
    public boolean addTerritoire(Territoire territoireCapture) {
        boolean aEteAJoute = false;
        if (territoireCapture != null && !this.mesTerritoires.contains(territoireCapture)) {
            territoireCapture.setCouleur(this.couleur);
            this.mesTerritoires.add(territoireCapture);
            aEteAJoute = true;
        }
        return aEteAJoute;
    }

    /**
     * Renvois la liste des territoires contrôlé par le Joueur.
     * @return : La liste des territoires du Joueur.
     */
    public ArrayList<Territoire> rendListeTerritoire() {
        ArrayList<Territoire> listeTerritoires = this.mesTerritoires;
        return listeTerritoires;
    }

    /**
     * Retire le territoire envoyé en paramètre au Joueur.
     * @param territoirePerdu : Le territoire capturé par l'ennemi à retirer.
     * @return : True si le territoire à été retiré, false sinon.
     */
    public boolean retirerTerritoire(Territoire territoirePerdu) {
        boolean aEteRetire = false;
        if (territoirePerdu != null) {
            if (this.mesTerritoires.contains(territoirePerdu)) {
                this.mesTerritoires.remove(territoirePerdu);
                aEteRetire = true;
            }
        }
        return aEteRetire;
    }

    /**
     * Renvois le nombre d'unités que le joueur possède sur le plateau de jeu.
     * @return : Le nombre d'unités actuellement déployée sur le plateau de jeu
     * par le joueur.
     */
    public int rendNbUniteEnJeu() {
        int nbUnitesPossede = 0;
        for (Territoire monTerritoire : this.mesTerritoires) {
            nbUnitesPossede += monTerritoire.rendNbUnites();
        }
        return nbUnitesPossede;
    }

    /**
     * Renvois le nombre total d'unités que le Joueur a déployé tout au long du jeu.
     * @return : Le nombre d'unités déployés en tout par le Joueur.
     */
    public int rendNbUniteTotalEnJeu() {
        return this.nbUniteTotalEnJeu;
    }

    /**
     * Fixe le nombre de tour que le Joueur a joué jusqu'a son élimination ou sa victoire.
     * @param tourElimination : Le nombre de tour auquel le Joueur a participé.
     */
    public void setNbTourJoue(int tourElimination) {
        this.nbTourJoue = tourElimination;
    }

    /**
     * Fixe le temps de jeu du Joueur jusqu'a son élimination ou sa victoire
     * @param tempsAvantElimination : Le temps effectif de jeu du Joueur.
     */
    public void setDureeJeu(String tempsAvantElimination) {
        this.dureeJeu = tempsAvantElimination;
    }

    /**
     * Renvois le temps du Joueur jusqu'a son élimination ou sa victoire
     * @return : Le temps effectif de jeu du Joueur.
     */
    public String rendTempsJeu() {
        return this.dureeJeu;
    }

    /**
     * Renvois le nombre de tour que le Joueur à joué jusqu'a son élimination ou sa victoire.
     * @return : Le nombre de tour auquel le Joueur a participé
     */
    public int rendNbTourJoue() {
        return this.nbTourJoue;
    }
}
