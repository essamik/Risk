/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.Territoire;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Implémentation simplifiée du design pattern Memento permettant de garder en
 * mémoire les territoires sur lequel le Joueur à déployer ses troupes durant
 * son tour de jeu
 *
 * @author Karim
 */
public class MementoDeployement {

    private ArrayList<Territoire> territoiresDeployement;

    /**
     * Constructeur de memento de déployement.
     */
    public MementoDeployement() {
        this.territoiresDeployement = new ArrayList<>();
    }

    /**
     * Ajoute le territoire envoyé en paramètre à la liste des territoires sur
     * lequel le joueur a déployé ses unités
     *
     * @param monTerritoire : Le territoire cible du déployement
     * @return : True si le territoire à été ajouté, false sinon.
     */
    public boolean ajouterTerritoire(Territoire monTerritoire) {
        boolean aEteAjoute = false;
        if (monTerritoire != null) {
            territoiresDeployement.add(monTerritoire);
            aEteAjoute = true;
        }
        return aEteAjoute;

    }

    /**
     * Retire le nb d'unités déployé sur chaque territoire. Si le territoire
     * était libre, remet le territoire à 0 et en gris.
     *
     * @return : Le nombre d'unités déployée pour redéployement.
     */
    public int restaurerEtatJeu() {
        int nbUnitesRetirees = 0;
        for (Territoire monTerritoire : this.territoiresDeployement) {
            monTerritoire.retirerUnite();
            nbUnitesRetirees++;
            if (monTerritoire.rendNbUnites() < 1) {
                monTerritoire.setCouleur(Color.GRAY);
            }
        }
        return nbUnitesRetirees;
    }

    /**
     * Renvois la liste des territoires sur lequel le Joueur à déployé ses
     * troupes.
     *
     * @return : La liste des territoires ayant reçu des unités du joueur.
     */
    public ArrayList<Territoire> rendListeTerritoiresDeployes() {
        ArrayList<Territoire> mesTerritoires = this.territoiresDeployement;
        return mesTerritoires;
    }
}
