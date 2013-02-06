/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Modele.Joueur;

/**
 *
 * @author Karim
 */
public abstract class EtatJeu {
    
     protected Joueur joueur;
     
     public EtatJeu() {
         this.joueur = null;
     }
     
     public Joueur rendJoueur() {
         return this.joueur;
     }
     
     public boolean tourJoueur(Joueur monJoueur) {
         boolean aEteAjoute = false;
         if(monJoueur!=null) {
             this.joueur = monJoueur;
             aEteAjoute = true;
         }
         return aEteAjoute;
     }

     public abstract boolean phaseInitialisation(FenetreRisk vue); //Phase de lancement du jeu (nb joueur, choix couleurs...)
     public abstract boolean phaseDeployement(FenetreRisk vue, Zone maZone);
     public abstract boolean phaseDeplacement(FenetreRisk vue, Zone maZone);
     public abstract boolean phaseConfirmation(FenetreRisk vue);

}
