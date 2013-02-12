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
public class PanneauAjoutJoueur extends JPanel {

    private JLabel numeroJoueur;
    private JTextField nomJoueur;
    private JPanel carreCouleur;

    public PanneauAjoutJoueur(int num, String nom, Color couleur) {
        super();
        this.setPreferredSize(new Dimension(600, 60));
        this.setVisible(true);
        this.setOpaque(false);
        //this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));

        JLabel numJoueur = new JLabel();
        numJoueur.setText(num + ".");
        numJoueur.setFont(new Font("verdana", Font.PLAIN, 26));

        JTextField inputJoueur = new JTextField(nom);
        inputJoueur.setPreferredSize(new Dimension(400, 46));
        inputJoueur.setFont(new Font("verdana", Font.PLAIN, 26));

        JPanel caseCouleur = new JPanel();
        caseCouleur.setPreferredSize(new Dimension(45, 45));
        caseCouleur.setBackground(couleur);
        caseCouleur.setVisible(true);
        
        this.numeroJoueur = numJoueur;
        this.nomJoueur = inputJoueur;
        this.carreCouleur = caseCouleur;

        this.add(this.numeroJoueur);
        this.add(this.nomJoueur);
        this.add(this.carreCouleur);

    }

    public String rendNom() {
        return this.nomJoueur.getText();
    }

    public Color rendCouleur() {
        return this.carreCouleur.getBackground();
    }
}
