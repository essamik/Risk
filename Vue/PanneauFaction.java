
package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Panneau contenant les informations relatifs à un Joueur.
 * Affiche un carré contenant la couleur du joueur, poursuivis par son nom.
 * Le joueur qui est en train de jouer est mis en évidence.
 * @author Karim
 */
public class PanneauFaction extends JPanel {

    private JPanel carreCouleur;
    private JLabel nomFaction;

    /**
     * Constructeur de Panneau de Faction en fonction d'une couleur et d'un nom.
     * @param color : La couleur associé au joueur.
     * @param name : Le nom choisis par le joueur.
     * @param estJoueurCourant : Définis si le Joueur est le Joueur courant.
     */
    public PanneauFaction(Color color, String name, boolean estJoueurCourant) {
        super();
        this.setVisible(true);
        this.setOpaque(false);
        //this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));

        JPanel caseCouleur = new JPanel();
        caseCouleur.setPreferredSize(new Dimension(20, 20));
        caseCouleur.setBackground(color);
        caseCouleur.setVisible(true);

        JLabel labelNom = new JLabel(name);
        labelNom.setPreferredSize(new Dimension(180, 20));
        if (estJoueurCourant) {
            labelNom.setFont(new Font("verdana", Font.BOLD, 14));
            caseCouleur.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
            caseCouleur.setPreferredSize(new Dimension(25, 25));


        } else {
            labelNom.setFont(new Font("verdana", Font.PLAIN, 14));
        }

        this.carreCouleur = caseCouleur;
        this.nomFaction = labelNom;

        this.add(this.carreCouleur);
        this.add(this.nomFaction);

    }
}
