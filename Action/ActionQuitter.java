package Action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * Action déclenchant la fermeture de la fenêtre du jeu.
 * @author Karim
 */
public class ActionQuitter extends AbstractAction {
    /**
     * Constructeur d'action de fermeture du jeu.
     * @param texte : Le texte à afficher dans le menu.
     */
    public ActionQuitter(String texte) {
        super(texte);
    }
    /**
     * Déclenche l'action de fermeture du jeu, en ouvrant une boite de dialogue pour confirmer l'action.
     * @param e : L'evenemnt lié à l'action de fermeture du jeu.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane jop = new JOptionPane();
        int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter le jeu sans sauvegarder ?", "Fermeture de la fenêtre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }
}
