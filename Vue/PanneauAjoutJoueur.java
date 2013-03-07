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
 * Panneau permettant d'ajouter un Joueur avec un nom et une couleur, lors de la phase de démarrage du jeu.
 * @author Karim
 */
public class PanneauAjoutJoueur extends JPanel {

    private JLabel numeroJoueur;
    private JTextField nomJoueur;
    private JPanel carreCouleur;

    /**
     * Constructeur de panneau d'ajout de joueur avec un numéro, un nom et une couleur.
     * @param num : Le numero du Joueur.
     * @param nom : Le nom personnalisable du Joueur.
     * @param couleur : La couleur imposée au Joueur.
     */
    public PanneauAjoutJoueur(int num, String nom, Color couleur) {
        super();
        this.setPreferredSize(new Dimension(600, 60));
        this.setVisible(true);
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
        
        Font police = new Font("verdana", Font.PLAIN, 20);

        JLabel numJoueur = new JLabel();
        numJoueur.setText(num + ".");
        numJoueur.setFont(police);

        JTextField inputJoueur = new JTextField(nom);
        inputJoueur.setPreferredSize(new Dimension(300, 46));
        inputJoueur.setFont(police);

        JPanel caseCouleur = new JPanel();
        caseCouleur.setPreferredSize(new Dimension(45, 45));
        caseCouleur.setBackground(couleur);
        caseCouleur.setVisible(true);
        caseCouleur.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));

        
        this.numeroJoueur = numJoueur;
        this.nomJoueur = inputJoueur;
        this.carreCouleur = caseCouleur;

        this.add(this.numeroJoueur);
        this.add(this.nomJoueur);
        this.add(this.carreCouleur);

    }

    /**
     * Retourne le nom choisis par le Joueur.
     * @return : Le nom du Joueur.
     */
    public String rendNom() {
        return this.nomJoueur.getText();
    }
    /**
     * Retourne la couleur attribuée au Joueur.
     * @return : La couleur du joueur.
     */
    public Color rendCouleur() {
        return this.carreCouleur.getBackground();
    }
}
