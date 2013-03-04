/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.JeuRisk;
import Modele.Territoire;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Karim
 */
public class MementoDeployement {

    private ArrayList<Territoire> territoiresDeployement;

    public MementoDeployement() {
        this.territoiresDeployement = new ArrayList<>();
    }

    public boolean ajouterTerritoire(Territoire monTerritoire) {
        boolean aEteAjoute = false;
        if (monTerritoire != null) {
            territoiresDeployement.add(monTerritoire);
            aEteAjoute = true;
        }
        return aEteAjoute;

    }

    public int restaurerEtatJeu() {
        int nbUnitesRetirees = 0;
        for(Territoire monTerritoire : this.territoiresDeployement) {
             monTerritoire.retirerUnite();
             nbUnitesRetirees++;
             if(monTerritoire.rendNbUnites() < 1) {
                 monTerritoire.setCouleur(Color.GRAY);
             }
        }
        System.out.println(nbUnitesRetirees);
        return nbUnitesRetirees;
    }
    
    public ArrayList<Territoire> rendListeTerritoiresDeployes() {
        ArrayList<Territoire> mesTerritoires = this.territoiresDeployement;
        return mesTerritoires;
    } 
}
