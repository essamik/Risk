package Action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Action déclenchant l'affichage du tutorial du jeu risk.
 * @author Karim
 */
public class ActionAfficherTutorial extends AbstractAction{

    /**
     * Constructeur d'action affichant le tutorial.
     * @param texte : Le texte à afficher dans le menu du jeu.
     */
    public ActionAfficherTutorial(String texte) {
        super(texte);
    }

    /**
     * Déclenche l'action d'affichage du tutorial au clic.
     * @param e : L'evenement lié à l'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
