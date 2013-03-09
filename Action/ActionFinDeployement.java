/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import Controleur.Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action déclenchant la fin du tour de déployement du joueur courant
 * @author Karim
 */
public class ActionFinDeployement implements ActionListener {

    private Controleur controleur;
    /**
     * Constructeur d'action de fin du tour de déployement.
     * @param ctrl : Le controleur du jeu.
     */
    public ActionFinDeployement(Controleur ctrl) {
        this.controleur = ctrl;
    }

    /**
     * Déclenche la fin du tour du joueur et lance le tour du joueur suivant.
     * @param e : L'evenement lié à l'action de fin du tour de déployement.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.finDeployement();
    }

}
