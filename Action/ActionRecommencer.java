package Action;

import Controleur.Controleur;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Action déclenchant le renouvellement du lancement du jeu.
 * @author Karim
 */
public class ActionRecommencer extends AbstractAction{

    private Controleur controleur;
    /**
     * Constructeur d'Action de relancement du jeu.
     * @param ctrl : Le controleur du jeu.
     * @param texte : Le texte à afficher dans le menu.
     */
    public ActionRecommencer(Controleur ctrl, String texte) {
        super(texte);
        this.controleur = ctrl;
    }
    /**
     * Déclenche l'action de relancement du jeu en restaurant le plateau de jeu et en redémarrant le jeu à l'accueil.
     * @param e : L'evenement lié à l'action de redémarrage du jeu.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.effacerVue();
        this.controleur.restaurerPlateauJeu();
        this.controleur.lancerEcranDemarrage();
    }
}
