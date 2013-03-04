/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import Controleur.Controleur;
import Modele.Joueur;
import Vue.PanneauAjoutJoueur;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Action permettant l'ajout d'un joueur supplémentaire.
 * @author Karim
 */
public class ActionAjouterJoueur implements ActionListener {

    private Controleur controleur;
    
    /**
     * Constructeur d'action d'ajout de joueur.
     * @param ctrl : Controleur du jeu, utilisé pour lancer les différentes méthodes nécessaire à l'ajout d'un joueur.
     */
    public ActionAjouterJoueur(Controleur ctrl) {
        this.controleur = ctrl;
    }

    /**
     * Le lancement de l'action initialise la liste des joueurs dans le modèle en fonction des informations saisi par l'utilisateur.
     * Un nouveau joueur est ajouté à la liste.
     * @param e : L'evenement lié à l'action d'ajout de joueur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.reinitialiserListeJoueur();
        for(PanneauAjoutJoueur panneau : this.controleur.rendlistePanneauJoueur()) {
            this.controleur.ajouterInfosJoueur(panneau.rendNom(), panneau.rendCouleur());
        }
        int numeroJoueurSuivant = this.controleur.rendlistePanneauJoueur().size()+1;
        this.controleur.ajouterInfosJoueur("Joueur " + numeroJoueurSuivant, this.controleur.rendCouleurRandom());
        this.controleur.initialiserChoixJoueur();
    }

}
