/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.Joueur;
import Vue.Zone;

/**
 * Implémentation du design pattern State permettant de gérer les différentes
 * phase de jeu du Risk. Différentes infos relatives au jeu sont stockée : 
 *  - Le joueur courant qui joue actuellement son tour de jeu.
 *  - La zone de départ (première zone a être cliquée) en cas de transfert
 *  - La zone d'arrivée (seconde clic) en cas de transfert
 *  - Le controleur, afin d'avoir accès aux méthodes d'actions du jeu.
 *
 * @author Karim
 */
public abstract class EtatJeu {

    private Joueur joueurcourant;
    private Zone zoneDepart;
    private Zone zoneArrivee;
    private Controleur controleur;

    /**
     * Constructeur d'etat de jeu.
     * Renvois une erreur si le controleur n'est pas envoyé en paramètre.
     * @param monControleur : Le controleur du jeu.
     */
    public EtatJeu(Controleur monControleur) {
        if (monControleur == null) {
            throw new RuntimeException("Paramètres manquant : Impossible de lancer la machine d'état !");
        }
        this.controleur = monControleur;
        this.joueurcourant = null;
        this.zoneDepart = null;
        this.zoneArrivee = null;
    }

    /**
     * Effectue l'action correspondant à l'état actuel du jeu par rapport à la zone cliquée par le joueur.
     * @param maZone : La zone sur lequel le Joueur a cliqué.
     */
    public abstract void interactionUtilisateur(Zone maZone);

    public Joueur rendJoueurCourant() {
        return this.joueurcourant;
    }
    
    /**
     * Renvois le controleur pour effectuer les actions du jeu.
     * @return : Le controleur du jeu.
     */
    public Controleur rendControleur() {
        return this.controleur;
    }


    /**
     * Définis le Joueur auquel c'est le tour de jouer.
     * @param monJoueur : Le Joueur auquel on donne son tour de jeu.
     * @return : True si le joueur a bien été définis comme étant le joueur courant, false sinon.
     */
    public boolean setJoueurCourant(Joueur monJoueur) {
        boolean aEteAjoute = false;
        if (monJoueur != null) {
            this.joueurcourant = monJoueur;
            aEteAjoute = true;
        }
        return aEteAjoute;
    }

    /**
     * Définis la zone de départ en cas de transfert.
     * @param maZoneDepart : La zone duquel partent les unités à transferer.
     */
    public void setZoneDepart(Zone maZoneDepart) {
        this.zoneDepart = maZoneDepart;
    }

    /**
     * Renvois la zone de départ en cas de transfert.
     * @return : La zone d'où partent les unités.
     */
    public Zone rendZoneDepart() {
        return this.zoneDepart;
    }

    /**
     * Définis la zone d'arrivée en cas de transfert.
     * @param maZoneArrivee : La zone de destination des unités à transférer.
     */
    public void setZoneArrivee(Zone maZoneArrivee) {
        this.zoneArrivee = maZoneArrivee;
    }

    /**
     * Renvois la zone de destination en cas de transfert.
     * @return : La zone de destination des unités.
     */
    public Zone rendZoneArrivee() {
        return this.zoneArrivee;
    }
}
