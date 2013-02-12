/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Karim
 */
public class PanneauFaction extends JPanel {

    private JPanel carreCouleur;
    private JLabel nomFaction;

    public PanneauFaction(Color color, String name, boolean joueurCourant) {
        super();
        this.setVisible(true);
        this.setOpaque(false);
        //this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        JPanel caseCouleur = new JPanel();
        caseCouleur.setPreferredSize(new Dimension(20, 20));
        caseCouleur.setBackground(color);
        caseCouleur.setVisible(true);

        JLabel labelNom = new JLabel(name);
        if (joueurCourant) {
            labelNom.setFont(new Font("verdana", Font.BOLD, 14));
        } else {
            labelNom.setFont(new Font("verdana", Font.PLAIN, 14));
        }

        this.carreCouleur = caseCouleur;
        this.nomFaction = labelNom;

        this.add(this.carreCouleur);
        this.add(this.nomFaction);

    }
}
