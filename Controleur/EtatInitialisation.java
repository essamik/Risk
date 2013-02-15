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
public class EtatInitialisation extends EtatJeu {


    public EtatInitialisation(Controleur controleur) {
        super(controleur);
        super.rendControleur().lancerEcranDemarrage();
    }
    
    

    @Override
    public void interactionUtilisateur(Zone maZone) {
        //Si le territoire est libre ou appartient au joueur
        if(!super.rendControleur().controleTerritoireEnnemi(maZone.rendNom())) {
            super.rendControleur().annexerTerritoire(maZone, super.rendJoueurCourant());
        super.affecterJoueurSuivant(super.rendControleur().rendJoueurSuivant(super.rendJoueurCourant()));
        super.rendControleur().updatePanneauFaction();
        super.rendControleur().updateUnitesRestantADeployer();
        super.rendControleur().checkFinPhaseInitialisation();
        
        }
        
        
    }

}
