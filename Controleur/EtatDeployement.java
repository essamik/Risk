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
                //super.affecterJoueurSuivant(super.rendControleur().rendJoueurSuivant(super.rendJoueurCourant()));
                super.rendControleur().updateUnitesRestantADeployer();
            } else {
                System.err.println("Ce territoire ne vous appartient pas. Déployement impossible");
            }
        } else {
            System.err.println("Vous n'avez plus d'unités à déployer !");
        }
    }
}
