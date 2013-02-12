/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Karim
 */
public class Joueur {

    private String nom;
    private Color couleur;
    private ArrayList<Territoire> mesTerritoires;
    
    public Joueur(String nomJoueur, Color couleurJoueur) {
        this.nom = nomJoueur;
        this.couleur = couleurJoueur;
        this.mesTerritoires = new ArrayList<>();
    }
    
    public String rendNom() {
        return this.nom;
    }
    public Color rendCouleur(){
        return this.couleur;
    }
    
//    public void setOrdre(int ordreJoueur) {
//        this.ordreJeu = ordreJoueur;
//    }
//    public int rendOrdre() {
//        return this.ordreJeu;
//    }
    
    public boolean addTerritoire(Territoire territoryCaptured) {
        boolean aEteAJoute = false;
        if(territoryCaptured != null && !this.mesTerritoires.contains(territoryCaptured)) {
            this.mesTerritoires.add(territoryCaptured);
            aEteAJoute = true;
        }
        return aEteAJoute;
    }
    
    public ArrayList<Territoire> rendListeTerritoire() {
        ArrayList<Territoire> listeTerritoires = this.mesTerritoires;
        return listeTerritoires;
    }
}
