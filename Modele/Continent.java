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
    private int unitesBonusDeployement;
    
    public Continent(String nomContinent, int unitesSupp) {
        this.nom = nomContinent;
        this.unitesBonusDeployement = unitesSupp;
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
    
    public int rendNbUnitesBonusPourControleContinent() {
        return this.unitesBonusDeployement;
    }
}
