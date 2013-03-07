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
 * Panneau s'affichant par défaut lorsque le Joueur entre en phase de déplacement/attaque.
 * Permet de mettre fin au tour de jeu en cliquant sur le bouton "Termienr déplacements".
 * @author Karim
 */
public class PanneauEtatDeplacement extends JPanel{


    /**
     * Constructeur de Panneau pour l'état Deplacement.
     * @param actFinDeplacement : L'action déclenchant la fin de la phase de jeu du Joueur.
     */
    public PanneauEtatDeplacement(ActionFinDeplacement actFinDeplacement) {
        super();
        this.setPreferredSize(new Dimension(1280, 100));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        
        JButton boutonValider = new JButton("Terminer déplacements");
        boutonValider.addActionListener(actFinDeplacement);
        
        JPanel conteneurBoutons = new JPanel();
        conteneurBoutons.setPreferredSize(new Dimension(320, 100));
        conteneurBoutons.setBackground(Color.WHITE);
        conteneurBoutons.setVisible(true);
        conteneurBoutons.setLayout(new BorderLayout());
        conteneurBoutons.add(boutonValider, BorderLayout.CENTER);
        
        this.add(conteneurBoutons, BorderLayout.EAST);
        
    }

}
