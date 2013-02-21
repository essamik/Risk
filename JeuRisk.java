import Controleur.Controleur;
import Vue.FenetreRisk;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



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
                FenetreRisk fenetreRisk = new FenetreRisk();
                Controleur controleur = new Controleur(modeleRisk, fenetreRisk);

//                CreateurMap extracteur = new CreateurMap();
            }
        });
    }
}