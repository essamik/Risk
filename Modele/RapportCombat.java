/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.Serializable;

/**
 * Compte rendu d'une offensive à l'encontre d'un territoire. Recense : - les
 * territoires engagés dans une confrontation - Le nombre d'unités attaquant -
 * Le nombre d'unités perdu par l'attaquant - Le résultat de la bataille
 * (victoire ou défaite)
 *
 * @author Karim
 */
public class RapportCombat implements Serializable {

    private int nbUnitesAttaquant;
    private Territoire territoireAttaquant;
    private Territoire territoireDefenseur;
    private int unitesPerdues;
    private boolean estGagne;

    /**
     * Constructeur de rapport de combat, fixe le nombre d'unités engagés par
     * l'attaquant.
     *
     * @param nbUnites
     */
    public RapportCombat(int nbUnites) {
        this.estGagne = false;
        this.unitesPerdues = 0;
        this.territoireAttaquant = null;
        this.territoireDefenseur = null;
        this.nbUnitesAttaquant = nbUnites;
    }

    /**
     * Définis le territoire duquel provient l'attaque.
     *
     * @param attaquant : Le territoire offensif.
     */
    public void setAttaquant(Territoire attaquant) {
        if (attaquant != null) {
            this.territoireAttaquant = attaquant;
        }
    }

    /**
     * Définis le territoire attaqué.
     *
     * @param defenseur : Le territoire défenseur.
     */
    public void setDefenseur(Territoire defenseur) {
        if (defenseur != null) {
            this.territoireDefenseur = defenseur;
        }
    }

    /**
     * Définis l'attaquant comme victorieux du combat.
     */
    public void attaquantGagne() {
        this.estGagne = true;
    }

    /**
     * Augmente le nombre d'unités perdu de 1.
     */
    public void perteUnite() {
        this.unitesPerdues++;
    }

    /**
     * Renvois le résultat du combat.
     * @return : True en cas de victoire, false en cas de défaite.
     */
    public boolean rendResultatCombat() {
        return this.estGagne;
    }

    /**
     * Renvois le nombre d'unités perdu lors de l'offensive par l'attaquant.
     * @return : Le nombre d'unités perdu par l'attaquant.
     */
    public int rendNbUnitesPerdues() {
        return this.unitesPerdues;
    }
    /**
     * Renvois le nombre d'unités de l'attaquant ayant survécu à l'offensive.
     * @return : La différence entre les pertes et le nombre d'unités engagées.
     */
    public int rendNbUnitesSurvivant() {
        return this.nbUnitesAttaquant - this.unitesPerdues;
    }

    /**
     * Renvois le Territoire défenseur.
     * @return : Le territoire attaqué.
     */
    public Territoire rendTerritoireDefenseur() {
        return this.territoireDefenseur;
    }

    /**
     * Renvois le territoire ayant lancé l'attaque.
     * @return : Le territoire attaquant
     */
    public Territoire rendTerritoireAttaquant() {
        return this.territoireAttaquant;
    }

    /**
     * Renvois le nombre d'unités envoyé au combat par l'attaquant.
     * @return : Le nombre d'unités impliqué dans le combat par l'attaquant.
     */
    public int rendNbUniteAttaquant() {
        return this.nbUnitesAttaquant;
    }
}
