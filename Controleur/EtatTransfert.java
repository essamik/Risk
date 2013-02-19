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
        if (maZone != null) {
            //Premier clic 
            if (super.rendZoneDepart() == null) {
                if (super.rendControleur().controleAppartenanceTerritoire(maZone.rendNom())) {
                    if (!super.rendControleur().controleTerritoireEnnemi(maZone.rendNom())) {
                        if(maZone.rendNbUnite() > 1) {
                        super.setZoneDepart(maZone);
                        maZone.setClicked();
                        } else {
                            System.err.println("Ce territoire ne possède pas assez d'unités pour effectuer un déplacement");
                        }
                    } else {
                        System.err.println("Ce territoire est contrôlé par l'ennemi");
                    }
                } else {
                    System.err.println("Ce territoire ne vous appartient pas");
                }
                //Second clic
            } else {
                //Si l'utilisateur reclic sur la même zone (annule)
                if (super.rendZoneDepart() == maZone) {
                    maZone.setNotClicked();
                    super.setZoneDepart(null);
                    //Si c'est une autre zone
                } else {
                    if (maZone.estVoisinDe(super.rendZoneDepart())) {
                        super.setZoneArrivee(maZone);
                        //Si c'est une zone alliée -> Déplacement
                        if (!super.rendControleur().controleTerritoireEnnemi(super.rendZoneArrivee().rendNom())) {
                            super.rendControleur().actionDeplacement(super.rendZoneDepart(), super.rendZoneArrivee());
                        } else { //Si c'est une zone ennemie -> Attaque
                            super.rendControleur().actionAttaque(super.rendZoneDepart(), super.rendZoneArrivee());
                        }
                    } else {
                        System.err.println("Ce territoire n'est pas un territoire voisin");
                    }
                }
            }
        }
    }
}
