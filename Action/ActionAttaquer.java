
package Action;

import Controleur.Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action déclenchant une attaque entre les deux territoires préalablement choisis par le joueur.
 * @author Karim
 */
public class ActionAttaquer implements ActionListener {

    private Controleur controleur;
    /**
     * Constructeur d'action de lancement d'attaque.
     * @param ctrl : Le controleur du jeu, permettant de d'attaquer un territoire ennemi.
     */
    public ActionAttaquer(Controleur ctrl) {
        this.controleur = ctrl;
    }

    /**
     * Déclenche l'action d'attaque en envoyant le nombre d'unités spécifiées contre le territoire ennemi.
     * @param e : L'evenement lié à l'action d'attaque
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.attaquer();
    }


}
