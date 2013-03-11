/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import Controleur.Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action déclenchant l'annulation du déplacement/attaque initialement prévu par le Joueur.
 * @author Karim
 */
public class ActionAnnulerTransfert implements ActionListener {

    private Controleur controleur;
    /**
     * Constructeur d'action d'annulation de transfert.
     * @param ctrl : Le controleur du jeu, permettant de restaurer l'état de déployement.
     */
    public ActionAnnulerTransfert(Controleur ctrl) {
        if(ctrl==null) throw new RuntimeException("Paramètre manquant : Impossible de construire l'action d'annulation du transfert !");
        controleur = ctrl;
        
    }
    
    /**
     * Déclenche l'action annulant le transfert initialisé par le Joueur.
     * @param e : L'evenement lié à l'action d'annulation du transfert.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.annulerTransfert();
    }


}
