/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import Controleur.Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Karim
 */
public class ActionStart implements ActionListener {

    private Controleur controleur;
    public ActionStart(Controleur ctrl) {
        this.controleur = ctrl;
    }
     /**
     * Permet d'afficher ActionStart
     * @return Retourne "ActionStart"
     */
    @Override
    public String toString() {
        return "ActionStart";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.initialiserChoixJoueur();
    }

}
