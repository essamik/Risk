package Action;

import Controleur.Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action déclenchant l'annulation de toutes les unités déployée lors de la phase courante de déployement.
 * @author Karim
 */
public class ActionAnnulerDeployement implements ActionListener {

    private Controleur controleur;
    /**
     * Constructeur d'action d'annulation du déployement.
     * @param ctrl : Le controleur du jeu, permettant de restaurer l'état de déployement.
     */
    public ActionAnnulerDeployement(Controleur ctrl) {
        this.controleur = ctrl;
    }
    /**
     * Déclenche l'action, enlevant les unités déployées et les restaurant au joueur.
     * @param e : L'evenement lié à l'action d'annulation du déployement.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.restaurerEtatJeu();
    }

}
