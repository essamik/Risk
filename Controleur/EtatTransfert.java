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
public class EtatTransfert extends EtatJeu {

    public EtatTransfert(Controleur controleur) {
        super(controleur);
    }

    @Override
    public void interactionUtilisateur(Zone maZone) {
//        if (maZone != null) {
//            //Premier clic 
//            if (super.rendZoneDepart() == null) {
//                super.setZoneDepart(maZone);
//                if (super.rendControleur().controleAppartenanceTerritoire(maZone.rendNom())) {
//                    maZone.setClicked();
//                    System.out.println("setclick");
//                }
//                //Second clic
//            } else {
//                //Si l'utilisateur reclic sur la même zone
//                if (super.rendZoneDepart() == maZone) {
//                    maZone.setNotClicked();
//                //Si c'est une autre zone
//                } else {
//                    super.setZoneArrivee(maZone);
//                    //Si c'est une zone alliée
//                    if (super.rendControleur().controleAppartenanceTerritoire(super.rendZoneArrivee().rendNom())) {
//                        super.rendControleur().actionDeplacement(super.rendZoneDepart(), super.rendZoneArrivee());
//                    } else {
//                        super.rendControleur().actionAttaque(super.rendZoneDepart(), super.rendZoneArrivee());
//                    }
//
//                }
//            }
//        }
    }
}
