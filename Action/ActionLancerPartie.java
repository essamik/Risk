/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Controleur.Controleur;
import Vue.PanneauAjoutJoueur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Karim
 */
public class ActionLancerPartie implements ActionListener {

    private Controleur controleur;

    public ActionLancerPartie(Controleur ctrl) {
        this.controleur = ctrl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("launch");
        this.controleur.reinitialiserListeJoueur();
        for (PanneauAjoutJoueur panneau : this.controleur.rendlistePanneauJoueur()) {
            this.controleur.ajouterInfosJoueur(panneau.rendNom(), panneau.rendCouleur());
        }
        
        this.controleur.initialiserLancementJeu();

    }
}
