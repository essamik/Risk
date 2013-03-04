/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Action.ActionAnnulerDeployement;
import Controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Karim
 */
public class PanneauDeployement extends JPanel {

    private JLabel infoUnites;
    
    public PanneauDeployement() {
        super();
        this.setPreferredSize(new Dimension(960, 100));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        Font police = new Font("verdana", Font.PLAIN, 20);
        this.infoUnites = new JLabel();
        this.infoUnites.setFont(police);
        this.add(infoUnites, BorderLayout.WEST);
        

    }
    
    public void setNbUniteADeployer(int nbUnites) {
        this.infoUnites.setText("Nombre d'unités restant à déployer : " +nbUnites);
    }


}
