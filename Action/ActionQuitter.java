/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author Karim
 */
public class ActionQuitter extends AbstractAction {


    public ActionQuitter(String texte) {
        super(texte);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane jop = new JOptionPane();
        int option = jop.showConfirmDialog(null, "Voulez-vous vraiment quitter le jeu sans sauvegarder ?", "Fermeture de la fenêtre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option != JOptionPane.NO_OPTION
                && option != JOptionPane.CANCEL_OPTION
                && option != JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }
    }

}
