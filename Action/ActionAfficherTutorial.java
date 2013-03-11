package Action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;

/**
 * Action déclenchant l'affichage du tutorial du jeu risk au format pdf.
 *
 * @author Karim
 */
public class ActionAfficherTutorial extends AbstractAction {

    /**
     * Constructeur d'action affichant le tutorial.
     *
     * @param texte : Le texte à afficher dans le menu du jeu.
     */
    public ActionAfficherTutorial(String texte) {
        super(texte);
    }

    /**
     * Déclenche l'action d'affichage du tutorial au clic.
     *
     * @param e : L'evenement lié à l'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            java.awt.Desktop.getDesktop().open(new File("ressources/TutoRisk.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(ActionAfficherTutorial.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Impossible d'ouvrir le tutoriel du jeu");
        }

    }
}
