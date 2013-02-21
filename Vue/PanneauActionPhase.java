/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Karim
 */
public class PanneauActionPhase extends JPanel {

    private JLabel infoUnites;
    
    public PanneauActionPhase() {
        super();
        this.setPreferredSize(new Dimension(1280, 100));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.infoUnites = new JLabel();
        JLabel unitesRestant = new JLabel();
        this.add(infoUnites, BorderLayout.WEST);
    }
    
    public void setNbUniteADeployer(int nbUnites) {
        this.infoUnites.setText("Nombre d'unités restant à déployer : " +nbUnites);
    }


}
