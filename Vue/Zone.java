package Vue;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 * Représentation graphique d'un Territoire pour le Jeu Risk.
 * Une Zone prends la forme d'un polygon en fonction des coordonnées X et Y envoyées.
 * @author Karim
 */
public class Zone extends Polygon {

    private boolean estClique;
    private String nom;
    private Color couleur;
    private ArrayList<Zone> listeVoisins;
    private int nbUnites;
    private Point pointCentral;
    private int nbUnitesDeplacable;

    /**
     * Constructeur de Zone, appartenant à un Groupe de Zone.
     * @param name : Le nom de la zone.
     * @param color : La couleur dont sera rempli la zone.
     * @param coordonneesX : La série de coordonnées X définissant la forme de la zone
     * @param coordonneesY: La série de coordonnées Y définissant la forme de la zone
     * @param ptCentral : Le point central de la zone, qui servira à afficher le nombre d'unités présent.
     */
    public Zone(String name, Color color, int[] coordonneesX, int[] coordonneesY, Point ptCentral) {
        super();
        this.nom = name;
        this.estClique = false;
        this.couleur = color;
        this.nbUnites = 0;
        this.listeVoisins = new ArrayList<>();
        for (int i = 0; i < coordonneesX.length; i++) {
            super.addPoint(coordonneesX[i], coordonneesY[i]);
        }
        this.pointCentral = ptCentral;
    }

    /**
     * Renvois la série de coordonnées X constituant la zone.
     * @return : Un tableau de points X.
     */
    public int[] rendCoordonnesX() {
        return super.xpoints;
    }
    
    /**
     * Renvois la série de coordonnées Y constituant la zone.
     * @return : Un tableau de points Y.
     */    public int[] rendCoordonnesY() {
        return super.ypoints;
    }

     /**
      * Définis la zone comme ayant été cliqué par le Joueur, en vue de la mettre en évidence.
      */
    public void setClicked() {
        this.estClique = true;
    }

    /**
     * Définis la zone comme étant non cliqué par le Joueur, de façon à la ramener dans son état standard.
     */
    public void setNotClicked() {
        this.estClique = false;
    }

    /**
     * Renvois l'état (est cliqué ou pas) de la Zone.
     * @return : True si le territoire a été cliqué par le joueur, false sinon.
     */
    public boolean estClique() {
        return this.estClique;
    }

    /**
     * Renvois le nom identifiant la Zone.
     * @return : Le nom de la Zone.
     */
    public String rendNom() {
        return this.nom;
    }

    /**
     * Ajoute la Zone envoyé en paramètre à la liste des zones adjaçentes.
     * @param zoneVoisine : La Zone adjaçente à cette Zone.
     * @return : True si la zone vosiine à bien été ajoutée, false sinon.
     */
    public boolean ajouterVoisin(Zone zoneVoisine) {
        boolean aEteAjoute = false;
        if (zoneVoisine != null && !this.listeVoisins.contains(zoneVoisine)) {
            this.listeVoisins.add(zoneVoisine);
            aEteAjoute = true;
        }
        return aEteAjoute;
    }

    /**
     * Renvois la liste des zone adjaçentes ou reliés par un pont à cette zone.
     * @return : La liste des zones voisines.
     */
    public ArrayList<Zone> rendListeVoisins() {
        ArrayList<Zone> listeVoisins = this.listeVoisins;
        return listeVoisins;
    }

    /**
     * Renvois la couleur de la Zone.
     * @return : La couleur de la Zone.
     */
    public Color getCouleur() {
        return this.couleur;
    }

    /**
     * Renvois le nombre d'unités présent sur la zone.
     * @return : Le nombre total d'unité sur la zone.
     */
    public int rendNbUnite() {
        return this.nbUnites;
    }

    /**
     * Définis un nombre d'unité sur la zone.
     * @param newNbUnite : Le nouveau nombre d'unité de la Zone.
     */
    public void setNbUnite(int newNbUnite) {
        this.nbUnites = newNbUnite;
    }

    /**
     * Renvois les coordonnées X et Y du point central de la Zone.
     * @return : Un point en 2 dimensions du point central de la zone.
     */
    public Point rendPointCentral() {
        Point pt = this.pointCentral;
        return pt;
    }

    /**
     * Définis la nouvelle couleur de la zone.
     * @param nouvelleCouleur : La nouvelle couleur de la Zone.
     */
    public void setCouleur(Color nouvelleCouleur) {
        this.couleur = nouvelleCouleur;
    }

    /**
     * Définis si la zone envoyé en paramètre est adjaçente à cette zone.
     * @param zoneVoisin : La zone dont il faut définir si elle est voisine.
     * @return  : True si la zone envoyé en paramètre est voisine avec cette zone, false sinon.
     */
    public boolean estVoisinDe(Zone zoneVoisin) {
        boolean estVoisin = false;
        if (zoneVoisin != null) {
            for(Zone zoneAdjacent : this.listeVoisins) {
                if(zoneAdjacent.rendNom().equals(zoneVoisin.rendNom())) {
                     estVoisin = true;
                }
            }
        }
        return estVoisin;
    }

    /**
     * Fixe le nombre d'unité actuellement déplaçable sur la zone.
     * @param nbUniteActive : Le nombre d'unités active prête à se déplacer.
     */
    public void setNbUniteDeplacable(int nbUniteActive) {
        this.nbUnitesDeplacable = nbUniteActive;
    }
    
    /**
     * Renvois le nombre d'unitées actuellement active sur la zone.
     * @return : Le nombre de territoires apte à se déplacer.
     */
    public int rendNbUniteDeplacable() {
        return this.nbUnitesDeplacable;
    }
}
