/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

/**
 *
 * @author Karim
 */
public class RapportCombat {

    private int nbUnitesAttaquant;
    private Territoire territoireAttaquant;
    private Territoire territoireDefenseur;
    private int unitesPerdues;
    private boolean estGagne;
    
    public RapportCombat(Territoire attaquant, Territoire defenseur, int nbUnites) {
        this.estGagne = false;
        this.unitesPerdues = 0;
        this.territoireAttaquant = attaquant;
        this.territoireDefenseur = defenseur;
        this.nbUnitesAttaquant = nbUnites;
    }
    
    public void attaquantGagne() {
        this.estGagne = true;
    }
    
    public void perteUnite() {
        this.unitesPerdues++;
    }
    
    public boolean rendResultatCombat() {
        return this.estGagne;
    }

    public int rendNbUnitesPerdues() {
        return this.unitesPerdues;
    }
    
    public int rendNbUnitesSurvivant() {
        return this.nbUnitesAttaquant-this.unitesPerdues;
    }

    public Territoire rendTerritoireDefenseur() {
        return this.territoireDefenseur;
    }
    public Territoire rendTerritoireAttaquant() {
        return this.territoireAttaquant;
    }
}
