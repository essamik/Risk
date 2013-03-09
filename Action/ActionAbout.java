package Action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * Action déclenchant les informations "A propos" du jeu.
 * @author Karim
 */
public class ActionAbout extends AbstractAction{

    /**
     * Constructeur d'actions about.
     * @param texte : Le texte à afficher dans le menu.
     */
    public ActionAbout(String texte) {
        super(texte);
    }

    /**
     * Déclenche l'action affichant l'a propos du jeu dans une boite de dialogue.
     * @param e : L'evenement lié à l'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "RISK - The Java Game v.1.0 \n Développé par Karim Es-sami", "About", JOptionPane.INFORMATION_MESSAGE);
    }


}
