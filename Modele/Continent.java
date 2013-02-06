/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import java.util.ArrayList;

/**
 *
 * @author Karim
 */
public class Continent {

    private String nom;
    private ArrayList<Territoire> listeTerritoire;
    
    public Continent(String nomContinent) {
        this.nom = nomContinent;
        this.listeTerritoire = new ArrayList<>();
    }

    public boolean addTerritoire(Territoire monTerritoire) {
        boolean aEteAjoute = false;
        if(monTerritoire != null) {
            this.listeTerritoire.add(monTerritoire);
            aEteAjoute = true;
        }
        return aEteAjoute;
    }
    
    public ArrayList<Territoire> rendTerritoires() {
        ArrayList<Territoire> territoiresRisk = this.listeTerritoire;
        return territoiresRisk;
    }

    public String rendNom() {
        String name = this.nom;
        return name;
    }
}
