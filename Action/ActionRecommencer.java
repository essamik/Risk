/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import Controleur.Controleur;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Karim
 */
public class ActionRecommencer extends AbstractAction{

    private Controleur controleur;
    public ActionRecommencer(Controleur ctrl, String texte) {
        super(texte);
        this.controleur = ctrl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.effacerVue();
        this.controleur.lancerEcranDemarrage();
    }


}