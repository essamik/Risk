/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Karim
 */
public class Zone extends Polygon {
    
    private boolean estClique;
    private String nom;
    private Color couleur;
    private ArrayList<Zone> listeVoisins;
    private int nbUnites;
    private Point pointCentral;
    
    public Zone(String name, Color color, int[] coordonneesX, int[] coordonneesY, Point ptCentral) {
        super();
        this.nom = name;
        this.estClique = false;
        this.couleur = color;
        this.nbUnites = 0;
        this.listeVoisins = new ArrayList<>();
        for(int i = 0; i<coordonneesX.length; i++) {
            super.addPoint(coordonneesX[i], coordonneesY[i]);
        }
        this.pointCentral = ptCentral;
    }
    
    public int[] rendCoordonnesX() {
        return super.xpoints;
    }
    public int[] rendCoordonnesY() {
        return super.ypoints;
    }
    
    public void setClicked() {
        this.estClique = true;
    }
    
    public void setNotClicked() {
        this.estClique = false;
    }
    
    public boolean estClique() {
        return this.estClique;
    }
    
    public String rendNom() {
        return this.nom;
    }
    
    public boolean ajouterVoisin(Zone zoneVoisine) {
        boolean aEteAjoute = false;
        if(zoneVoisine!=null && !this.listeVoisins.contains(zoneVoisine)) {
            this.listeVoisins.add(zoneVoisine);
            aEteAjoute = true;
        }
        return aEteAjoute;
    }
        
    public ArrayList<Zone> rendListeVoisins() {
        ArrayList<Zone> listeVoisins = this.listeVoisins;
        return listeVoisins;
    }
    
    public Color getCouleur(){
        return this.couleur;
    }
    
    public int rendNbUnite() {
        return this.nbUnites;
    }

    public void setNbUnite(int newNbUnite) {
        this.nbUnites = newNbUnite;
    }
    
    public Point rendPointCentral() {
        Point pt = this.pointCentral;
        return pt;
    }
    
    public void setCouleur(Color nouvelleCouleur) {
        this.couleur = nouvelleCouleur;
    }

}
