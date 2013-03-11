/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Vue.Zone;

/**
 * Phase de déplacement/attaque intervenant cycliquement après chaque phase de
 * déployement. Permet au joueur d'effectuer ses déplacements ou ses attaques
 * d'une zone à l'autre.
 *
 * @author Karim
 */
public class EtatTransfert extends EtatJeu {

    /**
     * Constructeur d'Etat de Transfert permettant au joueur d'effectuer les
     * actions de mouvement.
     *
     * @param controleur : Le controleur du jeu.
     */
    public EtatTransfert(Controleur controleur) {
        super(controleur);
    }

    /**
     * L'interaction se joue en 2 phases, le premier clic définis le territoire
     * de départ et le deuxième clic définis le territoire d'arrivée. Permet de
     * lancer le panneau de choix du nombre d'unités à déplacer entre les deux
     * territoires.
     *
     * @param maZone : La Zone de départ ou d'arrivée sur lequel le joueur à
     * cliqué
     */
    @Override
    public void interactionUtilisateur(Zone maZone) {
        if (maZone != null) {
            //Premier clic 
            if (super.rendZoneDepart() == null) {
                if (super.rendControleur().controleAppartenanceTerritoire(maZone.rendNom())) {
                    if (!super.rendControleur().controleTerritoireEnnemi(maZone.rendNom())) {
                        if (maZone.rendNbUnite() > 1) {
                            if(maZone.rendNbUniteDeplacable() > 1) {
                                super.setZoneDepart(maZone);
                                maZone.setClicked();
                            } else {
                                this.rendControleur().messageErreur("Vous ne pouvez plus déplacer d'unité de ce territoire, les unités ont déjà subi un déplacement");
                            }
                        } else {
                            this.rendControleur().messageErreur("Ce territoire ne possède qu'une seule unitée, il en faut minimum deux pour pour effectuer un déplacement");
                        }
                    } else {
                        this.rendControleur().messageErreur("Ce territoire est contrôlé par l'ennemi");

                    }
                } else {
                    this.rendControleur().messageErreur("Ce territoire ne vous appartient pas");
                }
                //Second clic
            } else {
                //Si l'utilisateur reclic sur la même zone (annule)
                if (super.rendZoneDepart() == maZone) {  
                    if(super.rendZoneArrivee()==null) {
                        maZone.setNotClicked();
                        super.setZoneDepart(null);
                    } else {
                        //Si l'utilisateur reclic sur la zone de départ après avoir cliqué sur une zone adjaçente, on annule tout
                        //Il faut faire disparaitre le panneau et remettre le message d'info de base
                        super.rendControleur().annulerTransfert();
                    }
                    //Si c'est une autre zone
                } else {
                    if (maZone.estVoisinDe(super.rendZoneDepart())) {
                        super.setZoneArrivee(maZone);
                        //Si c'est une zone alliée -> Déplacement
                        if (!super.rendControleur().controleTerritoireEnnemi(super.rendZoneArrivee().rendNom())) {
                            super.rendControleur().ajoutPanneauDeplacement(super.rendZoneDepart(), super.rendZoneArrivee());
                        } else { //Si c'est une zone ennemie -> Attaque
                            super.rendControleur().ajoutPanneauAttaque(super.rendZoneDepart(), super.rendZoneArrivee());
                        }
                    } else {
                        this.rendControleur().messageErreur("Ce territoire n'est pas un territoire voisin");
                    }
                }
            }
        }
    }
}
