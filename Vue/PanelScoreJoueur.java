/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 *
 * @author Karim
 */
public class PanelScoreJoueur extends JPanel{

    public PanelScoreJoueur(int rang, String nom, Color color, int nbUniteEnJeu, int nbTour, String tempsJeu) {
        super();
        this.setPreferredSize(new Dimension(800, 60));
        this.setVisible(true);
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
        
        Font police = new Font("verdana", Font.PLAIN, 20);
        
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
        labelNbUnite.setPreferredSize(new Dimension(100, 45));
        labelNbUnite.setFont(police);
        
        JLabel labelNbTour = new JLabel("" + nbTour);
        labelNbTour.setPreferredSize(new Dimension(100, 45));
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
