package Main;

import Controleur.Controleur;
import Vue.FenetreRisk;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main du jeu Risk. Lance le modèle, la vue et le controleur.
 * @author Karim
 */
public class MainRisk {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Modele.JeuRisk modeleRisk = new Modele.JeuRisk();
                FenetreRisk fenetreRisk = new FenetreRisk();
                Controleur controleur = new Controleur(modeleRisk, fenetreRisk);
            }
        });
    }
}