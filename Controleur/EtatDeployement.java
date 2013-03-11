/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Vue.Zone;

/**
 * Phase de jeu de déployement du risk. Intervient cycliquement après chaque phase de déplacement.
 * Permet au Joueur de déployer les unités reçu à chaque nouveau tour de jeu.
 * @author Karim
 */
public class EtatDeployement extends EtatJeu {

    /**
     * Constructeur d'Etat de Deployement permettant au joueur de déposer ses unités.
     * @param controleur : Le controleur du jeu.
     */
    public EtatDeployement(Controleur controleur) {
        super(controleur);
    }

    /**
     * Déplois une unité sur la zone cible si cette dernière est libre ou déjà sous le contrôle du joueur.
     * Il est impossible de déployer plus de 99 unités sur un territoire.
     * La zone est gardée en mémoire afin de pouvoir annuler la phase de déployement du joueur.
     * @param maZone : La zone sur laquel le joueur souhaite déployer une unité
     */
    @Override
    public void interactionUtilisateur(Zone maZone) {
        if (super.rendJoueurCourant().rendUnitesADeployer() > 0) {
            if (!super.rendControleur().controleTerritoireEnnemi(maZone.rendNom())) {
                if (maZone.rendNbUnite() < 99) {
                    super.rendControleur().annexerTerritoire(maZone, super.rendJoueurCourant());
                    super.rendControleur().updateMemento(maZone.rendNom());
                    super.rendControleur().updateUnitesRestantADeployer();
                } else {
                    super.rendControleur().messageErreur("Impossible de déployer plus de 99 unités sur un territoire");
                }
            } else {
                super.rendControleur().messageErreur("Ce territoire ne vous appartient pas. Déployement impossible");

            }
        } else {
            super.rendControleur().messageErreur("Vous n'avez plus d'unités à déployer. Cliquez sur le bouton 'Terminer déployement' pour mettre fin à votre tour");
        }
    }
}
