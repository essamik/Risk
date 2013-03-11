package Modele;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Représentation d'un territoire du jeu Risk dans le modèle. Chaque territorie
 * est définis par : - Un nom unique représentant un pays ou une région du monde
 * - Une liste de territoires voisins, qui sont les territoires adjaçents. - Un
 * nombre d'unité occupant le territoire - Une série de points en 2 dimensions
 * formant le contour du territoire. - Une couleur, dépendant du Joueur
 * contrôlant le territoire. - Un point central.
 *
 * @author Karim
 */
public class Territoire implements Serializable {

    private String nom;
    private ArrayList<Territoire> listeVoisins;
    private int nbUnite;
    private int nbUniteBloquee;
    private int[] xpoints;
    private int[] ypoints;
    private Color couleur;
    private Point pointCentral;

    /**
     * Constructeur de territoire en fonction de son nom, d'une série de
     * coordonnées x et y et d'un point central.
     *
     * @param nomTerritoire : Le nom identifiant le territoire
     * @param coordonneesX : Un tableau des coordonnées X du territoire
     * @param coordonneesY : Un tableau des coordonnées Y du territoire
     * @param pointCentre : Le point central du pays.
     */
    public Territoire(String nomTerritoire, int[] coordonneesX, int[] coordonneesY, Point pointCentre) {
        if(nomTerritoire==null || coordonneesX.length<0 || coordonneesY.length<0 || pointCentre==null)  throw new RuntimeException("Paramètres manquants : Impossible de lancer le programme !");

        this.nom = nomTerritoire;
        this.nbUnite = 0;
        this.nbUniteBloquee = 0;
        this.listeVoisins = new ArrayList<>();
        this.xpoints = coordonneesX;
        this.ypoints = coordonneesY;
        this.couleur = Color.GRAY;
        this.pointCentral = pointCentre;

    }

    /**
     * Ajoute un territoire à la liste des territoires voisins du territoire.
     *
     * @param territoireVoisin : Le territoire adjaçent.
     * @return : true si le territoire a été ajouté, fale sinon.
     */
    public boolean addVoisin(Territoire territoireVoisin) {
        boolean aEteAjoute = false;
        if (territoireVoisin != null && !this.listeVoisins.contains(territoireVoisin)) {
            this.listeVoisins.add(territoireVoisin);
            territoireVoisin.addVoisin(this); //Le pays voisin ajoute automatiquement ce pays aussi
        }
        return aEteAjoute;
    }

    /**
     * Renvois le nom identifiant le Territoire.
     *
     * @return : Le nom du territoire.
     */
    public String rendNom() {
        return this.nom;
    }

    /**
     * Renvois la liste des territoires voisins.
     *
     * @return : La liste des territoires adjaçent.
     */
    public ArrayList<Territoire> rendListeVoisins() {
        ArrayList<Territoire> territoiresAdjacents = this.listeVoisins;
        return territoiresAdjacents;
    }

    /**
     * Renvois le nombre d'unité occupant le territoire.
     *
     * @return : Le nombre d'unités sur le territoire.
     */
    public int rendNbUnites() {
        return this.nbUnite;
    }

    /**
     * Renvois le nombre d'unité pouvant être déplaçable du territoire. Des
     * unités ne sont pas déplaçables si elles viennent d'effectuer un mouvement
     * vers ce territoire
     *
     * @return : Le nombre d'unités déplaçable
     */
    public int rendNbUniteDeplacable() {
        return this.nbUnite - nbUniteBloquee;
    }

    /**
     * Renvois les coordonnées X formant le territoire.
     *
     * @return : Un tableau de points X.
     */
    public int[] rendCoordonnesX() {
        return this.xpoints;
    }

    /**
     * Renvois les coordonnées Y formant le territoire.
     *
     * @return : Un tableau de points Y.
     */
    public int[] rendCoordonnesY() {
        return this.ypoints;
    }
    
    /**
     * Renvois la couleur actuelle du territoire. Dépend du Joueur contrôlant le
     * territoire.
     *
     * @return : La couleur du territoire.
     */
    public Color rendCouleur() {
        return this.couleur;
    }

    /**
     * Définis la nouvelle couleur du territoire.
     *
     * @param nouvelleCouleur : La couleur à attribuer au territoire.
     */
    public void setCouleur(Color nouvelleCouleur) {
        this.couleur = nouvelleCouleur;
    }

    /**
     * Renvois le point central du territoire.
     *
     * @return : Le Point central du territoire.
     */
    public Point rendPointCentral() {
        return this.pointCentral;
    }

    /**
     * Ajoute une unité sur le territoire.
     *
     * @return : Le nombre d'unités présent sur le territoire.
     */
    public int ajouterUnite() {
        this.nbUnite++;
        return this.nbUnite;

    }

    /**
     * Retire une unité du territoire.
     */
    public void retirerUnite() {
        if (this.nbUnite > 0) {
            this.nbUnite--;
        }
    }

    /**
     * Retire le nombre d'unité envoyé en paramètre du territoire.
     *
     * @param nbUnitesARetirer : Le nombre d'unité à retirer du territoire.
     * @return : True si les unités ont été retirées, false sinon.
     */
    public boolean retirerUnites(int nbUnitesARetirer) {
        boolean uniteAEteRetiree = false;;
        if (nbUnitesARetirer < this.nbUnite) {
            this.nbUnite = this.nbUnite - nbUnitesARetirer;
            uniteAEteRetiree = true;
        }
        return uniteAEteRetiree;
    }

    /**
     * Ajoute le nombre d'unités envoyé en paramètre du territoire. Attention,
     * le nombre d'unité par territoire ne peut pas dépasser 99.
     * Dans le même tour de jeu, les unités déplacé sur ce territoire ne pourront plus se déplacer.
     * @param nbUnitesAAjouter : Le nombre d'unités à ajouter sur le territoire.
     * @return : True si l'ajout a réussis, false sinon.
     */
    public boolean ajouterUnites(int nbUnitesAAjouter) {
        boolean unitesAjoute = false;
        if (this.nbUnite < 100) {
            this.nbUnite = this.nbUnite + nbUnitesAAjouter;
            this.nbUniteBloquee = nbUnitesAAjouter; //Ces unités ne peuvent plus se déplacer
            unitesAjoute = true;
        }
        return unitesAjoute;
    }

    /**
     * Effectue le transfert d'unités en retirant le nombre d'unités envoyé du
     * territoire ciblé et en les envoyant au territoire voisin designé
     *
     * @param nbUnitesADeplacer : Le nombre d'unité que le joueur souhaite
     * transférer d'un territoire à l'autre
     * @param nomTerritoireDestination : Le territoire auquel sera ajouté les
     * unités retiré du territoire ciblé
     * @return : Le territoire de destination ou null si le déplacement à échoué
     */
    public Territoire deplacerUnites(int nbUnitesADeplacer, String nomTerritoireDestination) {
        Territoire territoireDestination = null;
        if (nbUnitesADeplacer < this.nbUnite) {
            this.nbUnite = this.nbUnite - nbUnitesADeplacer;
            for (Territoire territoireVoisin : this.listeVoisins) {
                if (nomTerritoireDestination.equals(territoireVoisin.rendNom())) {
                    if (territoireVoisin.ajouterUnites(nbUnitesADeplacer)) {
                        territoireDestination = territoireVoisin;
                    }
                }
            }
        }
        return territoireDestination;
    }

    /**
     * Fixe le nombre d'unités sur le territoire. Utilisé lors du chargement
     * d'une sauvegarde.
     *
     * @param unites : Le nombre d'unités à mettre sur le territoire.
     */
    public void setNbUnites(int unites) {
        this.nbUnite = unites;
    }
    
    /**
     * Libère les unités s'étant déplacée durant ce tour.
     */
    public void libererUnitesBloquees() {
        this.nbUniteBloquee = 0;
    }
}
