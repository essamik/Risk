package Action;

import Controleur.Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action déclenchant le déplacement entre deux territoires selon ne nb d'unités spécifié.
 * @author Karim
 */
public class ActionDeplacer implements ActionListener {

    private Controleur controleur;
    /**
     * Constructeur d'action de transfert d'unités.
     * @param ctrl : Le controleur du jeu, permettant de déplacer des unités d'un territoire à un autre.
     */
    public ActionDeplacer(Controleur ctrl) {
        this.controleur = ctrl;
        
    }

    /**
     * Déclenche l'action de déplacement en envoyant le nombre d'unités spécifiées au territoire de destination
     * @param e : L'evenement lié à l'action de transfert
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.deplacerUnites();
    }

}
