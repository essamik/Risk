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
        if(super.rendControleur().controleTerritoireLibre(maZone.rendNom())) {
        super.affecterJoueurSuivant(super.rendControleur().rendJoueurSuivant(super.rendJoueurCourant()));
        super.rendControleur().creerPanneauFaction();
        }
        //Si le territoire est libre ou appartient au joueur
        
    }

}
