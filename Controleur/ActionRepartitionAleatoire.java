/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;

/**
 * Action déclenchant le déploiement aléatoire des troupes
 * @author Karim
 */
public class ActionRepartitionAleatoire extends AbstractAction {

    private Controleur contoleur;
    /**
     * Constructeur d'action de répartition aléatoire
     * @param ctrl : Le controleur du jeu
     * @param texteMenu : Le texte à afficher dans l'onglet menu
     */
    public ActionRepartitionAleatoire(Controleur ctrl, String texteMenu) {
        super(texteMenu);
        if(ctrl == null) throw new RuntimeException("Paramètre manquant : Impossible de déclencher l'action de lancement de la partie automatique");
        this.contoleur = ctrl;
    }

    /**
     * Déclenche l'action de répartition automatique
     * @param e : L'evenement lié à l'action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.contoleur.lancerRepartitionUnitesAuto();
               
    }


}
