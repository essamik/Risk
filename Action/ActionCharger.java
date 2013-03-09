package Action;

import Controleur.Controleur;
import Vue.ExplorateurFichier;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Action déclenchant le lancement de l'explorateur de fichier permettant a l'utilisateur de choisir son fichier de sauvegarde.
 * @author Karim
 */
public class ActionCharger extends AbstractAction{

    private Controleur controleur;
    /**
     * Constructeur d'action affichant le tutorial.
     * @param ctrl : Le controleur du jeu.
     * @param texte Le texte à afficher dans le menu du jeu.
     */
    public ActionCharger(Controleur ctrl, String texte) {
        super(texte);
        this.controleur = ctrl;
    }

    /**
     * Déclenche l'action lançant l'explorateur de fichier.
     * @param e : L'evenement lié à l'action de chargement.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ExplorateurFichier explorateur = new ExplorateurFichier(this.controleur, "charger");
    }

}
