/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Continent représenté par un ensemble de Territoire.
 * Un Continent est identifié par un nom.
 * Le contrôle de tous les territoires d'un continent donne un bonu de déployement de troupes au joueur.
 * @author Karim
 */
public class Continent implements Serializable{

    private String nom;
    private ArrayList<Territoire> listeTerritoire;
    private int unitesBonusDeployement;
    
    /**
     * Constructeur de Continent avec un nom est un nombre d'unité bonus accordé en cas de contrôle de tous ses territoires.
     * @param nomContinent : Le nom du continent.
     * @param unitesSupp  : Le nombre d'unité bonus accordé au joueur contrôlant l'entier du continent.
     */
    public Continent(String nomContinent, int unitesSupp) {
        if(nomContinent == null || unitesSupp == 0)  throw new RuntimeException("Paramètres manquants : Impossible de lancer le programme !");
        this.nom = nomContinent;
        this.unitesBonusDeployement = unitesSupp;
        this.listeTerritoire = new ArrayList<>();
    }

    /**
     * Ajoute un Territoire dans le Continent.
     * @param monTerritoire : Le territoire à ajouter au continent
     * @return : True si le territoire a bien été ajouté, false sinon.
     */
    public boolean addTerritoire(Territoire monTerritoire) {
        boolean aEteAjoute = false;
        if(monTerritoire != null) {
            this.listeTerritoire.add(monTerritoire);
            aEteAjoute = true;
        }
        return aEteAjoute;
    }
    
    /**
     * Renvois la liste des territoires attribués pour ce Continent.
     * @return : Une ArrayList des territoires du continent.
     */
    public ArrayList<Territoire> rendTerritoires() {
        ArrayList<Territoire> territoiresRisk = this.listeTerritoire;
        return territoiresRisk;
    }

    /**
     * Renvois le nom du continent.
     * @return : Le nom du continenet sous forme de String.
     */
    public String rendNom() {
        return this.nom;
    }
    
    /**
     * Renvois le nombre d'unités bonus accordé pour le contrôle de tous les territoires du continent.
     * @return : Le nombre d'unités bonus.
     */
    public int rendNbUnitesBonusPourControleContinent() {
        return this.unitesBonusDeployement;
    }
}
