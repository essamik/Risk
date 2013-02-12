/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;

import Controleur.EtatJeu;
import Controleur.Controleur;
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
//        super.setZoneDepart(maZone);
//        super.setZoneArrivee(maZone);
        
        super.rendControleur().controleAjoutUnite(maZone.rendNom());
    }

}
