package Action;

import Controleur.Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action lançant l'affichage de la deuxième interface du jeu, le choix des joueurs.
 * @author Karim
 */
public class ActionStart implements ActionListener {

    private Controleur controleur;
    
    /**
     * Constructeur d'action de lancement du choix des joueurs.
     * @param ctrl : Le controleur du jeu.
     */
    public ActionStart(Controleur ctrl) {
        if(ctrl == null) throw new RuntimeException("Paramètre manquant : Impossible de construire l'action de lancement du choix des joueurs !");
        this.controleur = ctrl;
    }
    /**
     * Déclenche l'affichage de l'interface de choix des joueurs.
     * @param e : L'evenement lié à l'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.initialiserChoixJoueur();
    }

}
