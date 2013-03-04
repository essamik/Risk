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
    }

    @Override
    public void interactionUtilisateur(Zone maZone) {
        if (super.rendJoueurCourant().rendUnitesADeployer() > 0) {
            if (!super.rendControleur().controleTerritoireEnnemi(maZone.rendNom())) {
                super.rendControleur().annexerTerritoire(maZone, super.rendJoueurCourant());
                super.rendControleur().updateMemento(maZone.rendNom());
                //super.affecterJoueurSuivant(super.rendControleur().rendJoueurSuivant(super.rendJoueurCourant()));
                super.rendControleur().updateUnitesRestantADeployer();
            } else {
                super.rendControleur().messageErreur("Ce territoire ne vous appartient pas. Déployement impossible");

            }
        } else {
            super.rendControleur().messageErreur("Vous n'avez plus d'unités à déployer. Cliquez sur le bouton 'Terminer déployement' pour mettre fin à votre tour");
        }
    }
}
