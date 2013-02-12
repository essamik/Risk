/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Karim
 */
public class Territoire {

    private String nom;
    private ArrayList<Territoire> listeVoisins;
    private int nbUnite;
    private int[] xpoints;
    private int[] ypoints;
    private Color couleur;
    private Point pointCentral;

    public Territoire(String nomTerritoire, int[] coordonneesX, int[] coordonneesY, Point pointCentre) {
        this.nom = nomTerritoire;
        this.nbUnite = 0;
        this.listeVoisins = new ArrayList<>();
        this.xpoints = coordonneesX;
        this.ypoints = coordonneesY;
        this.couleur = Color.GRAY;
        this.pointCentral = pointCentre;

    }

    public boolean addVoisin(Territoire territoireVoisin) {
        boolean aEteAjoute = false;
        if (territoireVoisin != null && !this.listeVoisins.contains(territoireVoisin)) {
            this.listeVoisins.add(territoireVoisin);
            territoireVoisin.addVoisin(this); //Le pays voisin ajoute automatiquement ce pays aussi
        }
        return aEteAjoute;
    }

    public String rendNom() {
        return this.nom;
    }

    public ArrayList<Territoire> rendListeVoisins() {
        ArrayList<Territoire> territoiresAdjacents = this.listeVoisins;
        return territoiresAdjacents;
    }

    public int rendNbUnites() {
        int unites = this.nbUnite;
        return unites;
    }

    public int[] rendCoordonnesX() {
        return this.xpoints;
    }

    public int[] rendCoordonnesY() {
        return this.ypoints;
    }

    public Color rendCouleur() {
        return this.couleur;
    }

    public void setCouleur(Color nouvelleCouleur) {
        this.couleur = nouvelleCouleur;
    }
    public Point rendPointCentral() {
        return this.pointCentral;
    }

    public int ajouterUnite() {
        this.nbUnite++;
        return this.nbUnite;
        
    }
}
