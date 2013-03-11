package Action;

import Controleur.Controleur;
import Vue.PanneauAjoutJoueur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action permettant l'ajout d'un joueur supplémentaire.
 * @author Karim
 */
public class ActionAjouterJoueur implements ActionListener {

    private Controleur controleur;
    
    /**
     * Constructeur d'action d'ajout de joueur.
     * @param ctrl : Controleur du jeu, utilisé pour lancer les différentes méthodes nécessaire à l'ajout d'un joueur.
     */
    public ActionAjouterJoueur(Controleur ctrl) {
        if(ctrl==null) throw new RuntimeException("Paramètre manquant : Impossible de construire l'action d'ajout de joueurs !");

        this.controleur = ctrl;
    }

    /**
     * Le lancement de l'action initialise la liste des joueurs dans le modèle en fonction des informations saisi par l'utilisateur.
     * Un nouveau joueur est ajouté à la liste.
     * @param e : L'evenement lié à l'action d'ajout de joueur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.controleur.reinitialiserListeJoueur();
        for(PanneauAjoutJoueur panneau : this.controleur.rendlistePanneauJoueur()) {
            this.controleur.ajouterInfosJoueur(panneau.rendNom(), panneau.rendCouleur());
        }
        int numeroJoueurSuivant = this.controleur.rendlistePanneauJoueur().size()+1;
        this.controleur.ajouterInfosJoueur("Joueur " + numeroJoueurSuivant, this.controleur.rendCouleurRandom());
        this.controleur.initialiserChoixJoueur();
    }

}
