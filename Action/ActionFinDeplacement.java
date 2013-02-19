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
public class ActionFinDeplacement implements ActionListener {


    private Controleur controleur;
    public ActionFinDeplacement(Controleur ctrl) {
        this.controleur = ctrl;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.finDeplacement();
    }

}
