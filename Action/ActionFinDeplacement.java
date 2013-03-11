package Action;

import Controleur.Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action déclenchant la fin du tour de déplacement du joueur courant
 * @author Karim
 */
public class ActionFinDeplacement implements ActionListener {


    private Controleur controleur;
    /**
     * Constructeur d'action de fin du tour de transfert.
     * @param ctrl : Le controleur du jeu.
     */
    public ActionFinDeplacement(Controleur ctrl) {
        if(ctrl==null) throw new RuntimeException("Paramètre manquant : Impossible de construire l'action de fin de déplacement !");
        this.controleur = ctrl;
    }

    /**
     * Déclenche la fin du tour du joueur et lance le tour du joueur suivant.
     * @param e : L'evenement lié à l'action de fin du tour de déplacement.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.finDeplacement();
    }

}
