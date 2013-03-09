package Action;

import Controleur.Controleur;
import Vue.PanneauAjoutJoueur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Action déclenchant le lancement de la phase d'initialisation du jeu.
 * @author Karim
 */
public class ActionLancerPartie implements ActionListener {

    private Controleur controleur;
    /**
     * Constructeur d'action de lancement de la partie.
     * @param ctrl : Le controleur du jeu.
     */
    public ActionLancerPartie(Controleur ctrl) {
        this.controleur = ctrl;
    }
    /**
     * Déclenche l'action de lancement de la partie. 
     * Génère la liste des joueurs tout en controlant que deux joueurs n'aient
     * pas le même nom ou que le nom ne soit pas trop long.
     * En cas de noms non conforme, retour au panneau de choix des joueurs.
     * @param e : L'evenement lié à l'action de lancement de la partie.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.reinitialiserListeJoueur();
        ArrayList<String> listeNoms = new ArrayList<>();
        boolean nomEstRedondant = false;
        boolean nomEstTropLong = false;
        boolean nomInexistant = false;
        for (PanneauAjoutJoueur panneau : this.controleur.rendlistePanneauJoueur()) {
            this.controleur.ajouterInfosJoueur(panneau.rendNom(), panneau.rendCouleur());
            if (listeNoms.contains(panneau.rendNom())) {
                nomEstRedondant = true;
            }
            if(panneau.rendNom().length() > 16) {
                nomEstTropLong = true;
            }
            if(panneau.rendNom().length() < 1) {
                nomInexistant = true;
            }
            listeNoms.add(panneau.rendNom());
        }
        
        JOptionPane errorBox = new JOptionPane();

        if (nomEstRedondant) {  //Si deux joueurs ont le même nom -> Erreur
            this.controleur.initialiserChoixJoueur();
            errorBox.showMessageDialog(null, "2 joueurs ont le même nom", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if(nomEstTropLong) {
            this.controleur.initialiserChoixJoueur();
            errorBox.showMessageDialog(null, "Le nom d'un joueur est trop long (16 caractères max.)", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if(nomInexistant) {
            this.controleur.initialiserChoixJoueur();
            errorBox.showMessageDialog(null, "Le nom d'un joueur est trop court", "Erreur", JOptionPane.ERROR_MESSAGE);
        }else {
            this.controleur.initialiserLancementJeu();
        }

    }
}
