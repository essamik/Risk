/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Action.ActionFinDeplacement;
import Controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Karim
 */
public class PanneauEtatDeplacement extends JPanel{


    public PanneauEtatDeplacement(Controleur ctrl) {
        super();
        this.setPreferredSize(new Dimension(1280, 100));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        
        JButton boutonValider = new JButton("Terminer déplacements");
        boutonValider.addActionListener(new ActionFinDeplacement(ctrl));
        
        JPanel conteneurBoutons = new JPanel();
        conteneurBoutons.setPreferredSize(new Dimension(320, 100));
        conteneurBoutons.setBackground(Color.WHITE);
        conteneurBoutons.setVisible(true);
        conteneurBoutons.setLayout(new BorderLayout());
        conteneurBoutons.add(boutonValider, BorderLayout.CENTER);
        
        this.add(conteneurBoutons, BorderLayout.EAST);
        
    }

}
