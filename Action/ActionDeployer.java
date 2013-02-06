/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import Controleur.Controleur;
import Vue.FenetreRisk;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Karim
 */
public class ActionDeployer implements ActionListener{

    private FenetreRisk vue;
    private Controleur controleur;
    public ActionDeployer(FenetreRisk vueRisk, Controleur controller) {
        this.vue = vueRisk;
        this.controleur = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Lancer l'état "Deployer"
        
    }

}
