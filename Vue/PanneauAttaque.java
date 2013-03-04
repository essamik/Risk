/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Action.ActionAnnulerTransfert;
import Action.ActionAttaquer;
import Action.ActionDeplacer;
import Controleur.Controleur;
import Vue.Zone;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author Karim
 */
public class PanneauAttaque extends JPanel {
    
    private SpinnerNumberModel modeleSpinner;
    
    public PanneauAttaque(Controleur ctrl, Zone zoneDepart, Zone zoneDestination) {
        super();
        this.setPreferredSize(new Dimension(1280, 100));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        
        Font police = new Font("verdana", Font.PLAIN, 20);
        
        JPanel conteneurTerritoire = new JPanel();
        conteneurTerritoire.setPreferredSize(new Dimension(640, 100));
        conteneurTerritoire.setBackground(Color.WHITE);
        conteneurTerritoire.setVisible(true);
        
        JLabel territoireDepart = new JLabel(zoneDepart.rendNom());
        territoireDepart.setFont(police);
        JLabel territoireDestination = new JLabel(zoneDestination.rendNom());
        territoireDestination.setFont(police);
        
        conteneurTerritoire.add(territoireDepart, BorderLayout.WEST);
        conteneurTerritoire.add(new JLabel(" => "), BorderLayout.CENTER);
        conteneurTerritoire.add(territoireDestination, BorderLayout.EAST);
        
        this.add(conteneurTerritoire, BorderLayout.WEST);
        
        JPanel panneauUnites = new JPanel();
        panneauUnites.setPreferredSize(new Dimension(320, 100));
        panneauUnites.setBackground(Color.WHITE);
        panneauUnites.setVisible(true);
        
        JLabel labelUnites = new JLabel("Nombre d'unités : ");
        labelUnites.setFont(police);
        
        this.modeleSpinner = new SpinnerNumberModel(zoneDepart.rendNbUnite()-1, 1 , zoneDepart.rendNbUnite()-1, 1);
        
        JSpinner spinnerNbUnite = new JSpinner(modeleSpinner);
        spinnerNbUnite.setFont(police);
        
        panneauUnites.add(labelUnites, BorderLayout.WEST);
        panneauUnites.add(spinnerNbUnite, BorderLayout.EAST);
        this.add(panneauUnites, BorderLayout.CENTER);
        
        JButton boutonValider = new JButton("Attaquer");
        boutonValider.addActionListener(new ActionAttaquer(ctrl));
        boutonValider.setPreferredSize(new Dimension(320, 45));

        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.addActionListener(new ActionAnnulerTransfert(ctrl));
        boutonAnnuler.setPreferredSize(new Dimension(320, 45));
        
        JPanel conteneurBoutons = new JPanel();
        conteneurBoutons.setPreferredSize(new Dimension(320, 100));
        conteneurBoutons.setBackground(Color.WHITE);
        conteneurBoutons.setVisible(true);
        conteneurBoutons.setLayout(new BorderLayout());
        conteneurBoutons.add(boutonValider, BorderLayout.NORTH);
        conteneurBoutons.add(boutonAnnuler, BorderLayout.SOUTH);
        
        this.add(conteneurBoutons, BorderLayout.EAST);
    }
    
    public int rendNbUniteDeplacement() {
        return this.modeleSpinner.getNumber().intValue();
    }    

}
