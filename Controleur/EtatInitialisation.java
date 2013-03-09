/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Vue.Zone;

/**
 * Phase de jeu initiale du Risk. N'est lancée qu'une seule fois, au début du
 * jeu afin que les joueurs déposent leur unités de base sur les territoires.
 *
 * @author Karim
 */
public class EtatInitialisation extends EtatJeu {

    /**
     * Constructeur d'Etat d'Initialisation du jeu.
     * @param controleur : Le controleur du jeu.
     */
    public EtatInitialisation(Controleur controleur) {
        super(controleur);
        super.rendControleur().lancerEcranDemarrage();
    }

    /**
     * Déplois une unité sur la zone cible si cette dernière est libre ou déjà sous le contrôle du joueur.
     * @param maZone : La zone sur laquel le joueur souhaite déployer une unité
     */
    @Override
    public void interactionUtilisateur(Zone maZone) {
        //Si le territoire est libre ou appartient au joueur
        if (!super.rendControleur().controleTerritoireEnnemi(maZone.rendNom())) {
            super.rendControleur().annexerTerritoire(maZone, super.rendJoueurCourant());
            super.setJoueurCourant(super.rendControleur().rendJoueurSuivant(super.rendJoueurCourant()));
            super.rendControleur().updateVueJoueur();
            super.rendControleur().updateUnitesRestantADeployer();
            super.rendControleur().checkFinPhaseInitialisation();
        } else {
            super.rendControleur().messageErreur("Ce territoire appartient à l'ennemi. Déployement impossible");
        }



    }
}
