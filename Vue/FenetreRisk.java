/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Controleur;
import Observer.Observeur;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Karim
 */
public class FenetreRisk extends JFrame implements Observeur {

    private PlateauJeu plateauJeu;
    private JPanel panneauPhasesJeu;
    private ArrayList<PanelOrdre> listeOrdres;
    private EtatJeu etat;
    private Controleur controleur;

    public FenetreRisk(Controleur monControleur) {
        super("Risk - The Java Game");
        this.etat = new EtatDeployement();
        this.listeOrdres = new ArrayList<>();
        this.controleur = monControleur;


        Font titre = new Font("verdana", Font.PLAIN, 40);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(25, 25);
        //this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(1280, 720));

////////////Conteneur du haut amovible/////////////////////////////////////
        JPanel conteneurHaut = new JPanel();
        conteneurHaut.setPreferredSize(new Dimension(1280, 520));
        conteneurHaut.setVisible(true);
        conteneurHaut.setLayout(new BorderLayout());
        this.add(conteneurHaut, BorderLayout.CENTER);

        ////Carte du risk FIXE/////////////////////////////////////
        this.plateauJeu = new PlateauJeu(this);
        this.plateauJeu.setPreferredSize(new Dimension(1000, 520));
        this.plateauJeu.setBackground(Color.BLACK);
        this.plateauJeu.setVisible(true);
        conteneurHaut.add(this.plateauJeu, BorderLayout.CENTER);

/////////Conteneur resumé du jeu Amovible /////////////////////////////////////
        JPanel conteneurResumeJeu = new JPanel();
        conteneurResumeJeu.setPreferredSize(new Dimension(280, 520));
        conteneurResumeJeu.setBackground(Color.ORANGE);
        conteneurResumeJeu.setVisible(true);
        conteneurResumeJeu.setLayout(new BorderLayout());
        conteneurHaut.add(conteneurResumeJeu, BorderLayout.EAST);

        ////Panneau des ordres Amovible///////////////////////////////////////////////////
        JPanel panneauOrdres = new JPanel();
        panneauOrdres.setPreferredSize(new Dimension(280, 260));
        panneauOrdres.setBackground(Color.DARK_GRAY);
        panneauOrdres.setVisible(true);
        panneauOrdres.setLayout(new FlowLayout());

        conteneurResumeJeu.add(panneauOrdres, BorderLayout.CENTER);
        JLabel titreOrdre = new JLabel(" Ordres");
        titreOrdre.setHorizontalAlignment(JLabel.LEFT);
        titreOrdre.setForeground(Color.BLACK);
        titreOrdre.setFont(titre);
        panneauOrdres.add(titreOrdre, BorderLayout.NORTH);
        for (PanelOrdre monOrdre : this.listeOrdres) {
            panneauOrdres.add(monOrdre);
        }

        ////Panneau des factions fixe ///////////////////////////////////////////////////
        JPanel panneauFactions = new JPanel();
        panneauFactions.setPreferredSize(new Dimension(280, 260));
        panneauFactions.setBackground(Color.GRAY);
        panneauFactions.setVisible(true);

        panneauFactions.setLayout(new FlowLayout());

        JLabel titreFactions = new JLabel(" Factions");
        titreFactions.setHorizontalAlignment(JLabel.LEFT);
        titreFactions.setForeground(Color.BLACK);
        titreFactions.setFont(titre);
        panneauFactions.add(titreFactions, BorderLayout.NORTH);

        conteneurResumeJeu.add(panneauFactions, BorderLayout.SOUTH);


/////////Conteneur du bas fixe/////////////////////////////////////
        JPanel conteneurBas = new JPanel();
        conteneurBas.setPreferredSize(new Dimension(1280, 200));
        conteneurBas.setBackground(Color.blue);
        conteneurBas.setVisible(true);
        conteneurBas.setLayout(new BorderLayout());
        this.add(conteneurBas, BorderLayout.SOUTH);

        ////Barre des 3 phases de jeu fixe ///////////////////////////////////////////////////
        this.panneauPhasesJeu = new JPanel();
        this.panneauPhasesJeu.setPreferredSize(new Dimension(1280, 40));
        this.panneauPhasesJeu.setBackground(Color.DARK_GRAY);
        this.panneauPhasesJeu.setVisible(true);
        conteneurBas.add(this.panneauPhasesJeu, BorderLayout.NORTH);


        ////Panneau des actions correspondant à la phase de jeu en cours - Amovible ///////////////////////////////////////////////////
        JPanel panneauActionPhase = new JPanel();
        panneauActionPhase.setPreferredSize(new Dimension(1280, 100));
        panneauActionPhase.setBackground(Color.LIGHT_GRAY);
        panneauActionPhase.setVisible(true);
        conteneurBas.add(panneauActionPhase, BorderLayout.CENTER);

/////////Conteneur des des informations de jeu fixe/////////////////////////////////////
        JPanel conteneurInfos = new JPanel();
        conteneurInfos.setPreferredSize(new Dimension(1280, 60));
        conteneurInfos.setVisible(true);
        conteneurInfos.setLayout(new BorderLayout());
        conteneurBas.add(conteneurInfos, BorderLayout.SOUTH);

        ////Barre représentant les forces des joueurs - Fixe ///////////////////////////////////////////////////
        JPanel barreForceArmees = new JPanel();
        barreForceArmees.setPreferredSize(new Dimension(1280, 20));
        barreForceArmees.setBackground(Color.ORANGE);
        barreForceArmees.setVisible(true);
        conteneurInfos.add(barreForceArmees, BorderLayout.NORTH);


        ////Barre d'information pour les joueurs - Amovible ///////////////////////////////////////////////////
        JPanel infoBarre = new JPanel();
        infoBarre.setPreferredSize(new Dimension(1280, 40));
        infoBarre.setBackground(Color.BLACK);
        infoBarre.setVisible(true);
        conteneurInfos.add(infoBarre, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
        //Ajout de la vue au controleur une fois l'interface terminée
        this.controleur.ajouterVue(this);
    }

    public PlateauJeu rendPlateauJeu() {
        return this.plateauJeu;
    }

    public JPanel rendPanneauPhasesDeJeu() {
        return this.panneauPhasesJeu;
    }

    public void interactionZone(Zone maZone) {
        this.etat.phaseDeployement(this, maZone);
    }

    public void deployerUnites(String nomTerritoire) {
        this.controleur.controleAjoutUnite(nomTerritoire);
    }

    @Override
    public void update(String nomTerritoire, int nbUnites) {
        for (GroupeZone mesContinents : this.plateauJeu.rendListeContinents()) {
            for (Zone maZone : mesContinents.rendListeZones()) {
                if (maZone.rendNom().equals(nomTerritoire)) {
                    maZone.setNbUnite(nbUnites);
                    System.out.println(maZone.rendNom() + " : " + maZone.rendNbUnite()+ " units");
                }
            }
        }
    }
}
