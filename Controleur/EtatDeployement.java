/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;

import Vue.Zone;

/**
 *
 * @author Karim
 */
public class EtatDeployement extends EtatJeu {


    public EtatDeployement(Controleur controleur) {
        super(controleur);
        super.rendControleur().lancerPhaseDeployement();
    }

    @Override
    public void interactionUtilisateur(Zone maZone) {
        if(!super.rendControleur().controleTerritoireEnnemi(maZone.rendNom())) {
            super.rendControleur().annexerTerritoire(maZone, super.rendJoueurCourant());
        //super.affecterJoueurSuivant(super.rendControleur().rendJoueurSuivant(super.rendJoueurCourant()));
        super.rendControleur().updateUnitesRestantADeployer();
        }
    }
}
