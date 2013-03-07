/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import Controleur.Controleur;
import Vue.ExplorateurFichier;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Karim
 */
public class ActionCharger extends AbstractAction{

    private Controleur controleur;
    public ActionCharger(Controleur ctrl, String texte) {
        super(texte);
        this.controleur = ctrl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ExplorateurFichier explorateur = new ExplorateurFichier(this.controleur, "charger");
    }

}
