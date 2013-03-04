/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Controleur.Controleur;
import Vue.PanneauAjoutJoueur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
        this.controleur.reinitialiserListeJoueur();
        ArrayList<String> listeNoms = new ArrayList<>();
        boolean nomEstRedondant = false;
        for (PanneauAjoutJoueur panneau : this.controleur.rendlistePanneauJoueur()) {
            this.controleur.ajouterInfosJoueur(panneau.rendNom(), panneau.rendCouleur());
            if (listeNoms.contains(panneau.rendNom())) {
                nomEstRedondant = true;
            }
            listeNoms.add(panneau.rendNom());
        }
        if (nomEstRedondant) {  //Si deux joueurs ont le même nom -> Erreur
            this.controleur.initialiserChoixJoueur();
            JOptionPane errorBox = new JOptionPane();
            errorBox.showMessageDialog(null, "2 joueurs ont le même nom", "Erreur", JOptionPane.ERROR_MESSAGE);

        } else {
            this.controleur.initialiserLancementJeu();
        }

    }
}
