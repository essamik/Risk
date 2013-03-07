/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Action.ActionAnnulerTransfert;
import Action.ActionDeplacer;
import Controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

/**
 * Panneau permettant au Joueur de régler le déplacement de ses unités.
 * Le Joueur peut définir le nombre d'unités à envoyé d'un territoire à l'autre.
 * @author Karim
 */
public class PanneauTransfertUnites extends JPanel{

    private SpinnerNumberModel modeleSpinner;
    
    /**
     * Constructeur de Panneau de Transfert d'Unités, à partir d'une zone de départ et d'une zone de destination.
     * @param zoneDepart : La zone à partir de laquelle le Joueur souhaite déplacer ses troupes.
     * @param zoneDestination : La zone vers laquelle doivent arriver les troupes du Joueur.
     * @param actDeplacer : L'action déclenchant le déplacement des unités.
     * @param actAnnuler : L'action déclenchant l'annulation du déplacement.
     */
    public PanneauTransfertUnites(Zone zoneDepart, Zone zoneDestination, ActionDeplacer actDeplacer, ActionAnnulerTransfert actAnnuler) {
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
        
        JButton boutonValider = new JButton("Déplacer");
        boutonValider.addActionListener(actDeplacer);
        boutonValider.setPreferredSize(new Dimension(320, 45));
        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.addActionListener(actAnnuler);
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
    
    /**
     * Renvois le nombre d'unités à déplacer.
     * @return : Le nombre d'unité que le Joueur à choisi dans le Spinner.
     */
    public int rendNbUniteDeplacement() {
        return this.modeleSpinner.getNumber().intValue();
    }
}
