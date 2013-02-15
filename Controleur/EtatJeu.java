/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;

import Controleur.Controleur;
import Modele.Joueur;
import Vue.Zone;

/**
 *
 * @author Karim
 */
public abstract class EtatJeu {
    
     private Joueur joueurcourant;
     private Zone zoneDepart; 
     private Zone zoneArrivee; 
     private Controleur controleur;
     
     public EtatJeu(Controleur monControleur) {
         this.controleur = monControleur;
         this.joueurcourant = null;
         this.zoneDepart = null;
         this.zoneArrivee = null;
     }
     
     public abstract void interactionUtilisateur(Zone maZone);
     
     
     public Joueur rendJoueurCourant() {
         return this.joueurcourant;
     }
     
     public boolean affecterJoueurSuivant(Joueur monJoueur) {
         boolean aEteAjoute = false;
         if(monJoueur!=null) {
             this.joueurcourant = monJoueur;
             aEteAjoute = true;
         }
         return aEteAjoute;
     }
     
     public void setJoueurCourant(Joueur monJoueur) {
         this.joueurcourant = monJoueur;
     }
     
     public void setZoneDepart(Zone maZoneDepart) {
         this.zoneDepart = maZoneDepart;
     }
     public Zone rendZoneDepart() {
         return this.zoneDepart;
     }

    public void setZoneArrivee(Zone maZoneArrivee) {
        this.zoneArrivee = maZoneArrivee;
    }

    public Controleur rendControleur() {
        return this.controleur;
    }

    public Zone rendZoneArrivee() {
        return this.zoneArrivee;
    }

}
