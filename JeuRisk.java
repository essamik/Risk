import Controleur.Controleur;
import Vue.FenetreRisk;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Karim
 */
public class JeuRisk {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Modele.JeuRisk modeleRisk = new Modele.JeuRisk();
                Controleur controleur = new Controleur(modeleRisk);
                FenetreRisk fenetreRisk = new FenetreRisk(controleur);
//                CreateurMap extracteur = new CreateurMap();
            }
        });
    }
}