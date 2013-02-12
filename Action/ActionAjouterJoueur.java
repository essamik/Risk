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
 *
 * @author Karim
 */
public class ActionAjouterJoueur implements ActionListener {

    private Controleur controleur;
    
    public ActionAjouterJoueur(Controleur ctrl) {
        this.controleur = ctrl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.reinitialiserListeJoueur();
        for(PanneauAjoutJoueur panneau : this.controleur.rendlistePanneauJoueur()) {
            this.controleur.ajouterInfosJoueur(panneau.rendNom(), panneau.rendCouleur());
        }
        int numeroJoueurSuivant = this.controleur.rendlistePanneauJoueur().size()+1;
        this.controleur.ajouterInfosJoueur("Joueur " + numeroJoueurSuivant, Color.GREEN);
        this.controleur.initialiserChoixJoueur();
    }

}
