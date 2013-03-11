package Action;

import Controleur.Controleur;
import Vue.ExplorateurFichier;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Action déclenchement le lancement de l'explorateur de fichier permettant d'enregistrer un fichier de sauvegarde du jeu.
 * @author Karim
 */
public class ActionSauvegarder extends AbstractAction{

    private Controleur controleur;
    /**
     * Constructeur d'action de sauvegarde.
     * @param ctrl : Le controleur du jeu.
     * @param texte : Le texte à afficher dans le menu.
     */
    public ActionSauvegarder(Controleur ctrl, String texte) {
        super(texte);
        if(ctrl==null) throw new RuntimeException("Paramètre manquant : Impossible de construire l'action de sauvegarde du jeu !");
        this.controleur = ctrl;
        
    }
    
    /**
     * Déclenche l'action en lançant l'explorateur de fichier et en permettant
     * à l'utilisateur de pointer un dossier ou enregistrer la sauvegarde.
     * @param e : L'evenemnt lié à l'action de sauvegarde. 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ExplorateurFichier explorateur = new ExplorateurFichier(this.controleur, "sauvegarder");
        
    }

}
