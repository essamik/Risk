package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 * Panneau affichant le scores à la fin de la partie pour un Joueur.
 * S'imbrique avec les panneaux des autres Joueurs dans l'affichage final des scores de la partie.
 * Les éléments suivant sont affichés : 
 *  - Le rang du Joueur
 *  - Le nom du joueur
 *  - Sa couleur
 *  - Le nombre d'unité total déployé durant la partie par le Joueur.
 *  - Le nombre de tour avant élimination/victoire du Joueur.
 *  - Le temps de jeu effectif avant élimination/Victoire du Joueur.
 * @author Karim
 */
public class PanelScoreJoueur extends JPanel{

    /**
     * Construit un Panneau d'affichage du score d'un Joueur.
     * @param rang : Le rang du joueur.
     * @param nom : Le nom du Joueur.
     * @param color : La couleur du Joueur.
     * @param nbUniteEnJeu : Le nombre d'unité déployé en tout par le Joueur.
     * @param nbTour : Le nombre de tour de jeu du Joueur.
     * @param tempsJeu : Le temps de jeu du Joueur.
     */
    public PanelScoreJoueur(int rang, String nom, Color color, int nbUniteEnJeu, int nbTour, String tempsJeu) {
        super();
        this.setPreferredSize(new Dimension(800, 45));
        this.setVisible(true);
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
        Font police = new Font("verdana", Font.PLAIN, 20);

        if(rang == 1) {
            police = new Font("verdana", Font.BOLD, 20);
        }
        
        JLabel rangJoueur = new JLabel();
        rangJoueur.setText(rang + ".");
        rangJoueur.setPreferredSize(new Dimension(50, 45));
        rangJoueur.setFont(police);
        
        JPanel caseCouleur = new JPanel();
        caseCouleur.setPreferredSize(new Dimension(30, 30));
        caseCouleur.setBackground(color);
        caseCouleur.setVisible(true);
        caseCouleur.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));

        JLabel labelNom = new JLabel(nom);
        labelNom.setPreferredSize(new Dimension(200, 45));
        labelNom.setFont(police);

        JLabel labelNbUnite = new JLabel("" +nbUniteEnJeu);
        labelNbUnite.setPreferredSize(new Dimension(120, 45));
        labelNbUnite.setFont(police);
        
        JLabel labelNbTour = new JLabel("" + nbTour);
        labelNbTour.setPreferredSize(new Dimension(150, 45));
        labelNbTour.setFont(police);
  
        JLabel labelTemps = new JLabel(tempsJeu);
        labelTemps.setPreferredSize(new Dimension(100, 45));
        labelTemps.setFont(police);
        
        this.add(rangJoueur);
        this.add(caseCouleur);
        this.add(labelNom);
        this.add(labelNbUnite);
        this.add(labelNbTour);
        this.add(labelTemps);
    }

}
