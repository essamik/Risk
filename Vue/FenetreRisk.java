/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Controleur;
import Observer.Observeur;
import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Karim
 */
public class FenetreRisk extends JFrame implements Observeur {

    private PlateauJeu plateauJeu;
    private JPanel panneauPhasesJeu;
    private JPanel conteneurHaut;
    private JPanel conteneurBas;
    private JPanel panneauFactions;
    private JPanel listeOrdre;
    private PanneauActionPhase panneauActionPhase;
    private JPanel barreForceArmees;
    private JLabel infoBarre;
    private Font titre;
    private Controleur controleur;

    public FenetreRisk() {
        super("Risk - The Java Game");
        //Mise en place du design pour les éléments SWING
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(200, 25);
        //this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(1280, 720));

        this.initialiserFenetre();
    }

    public void creerPlateauJeu() {

        this.getContentPane().setLayout(new BorderLayout());

////////////Conteneur du haut amovible/////////////////////////////////////
        this.conteneurHaut = new JPanel();
        this.conteneurHaut.setPreferredSize(new Dimension(1280, 520));
        this.conteneurHaut.setVisible(true);
        this.conteneurHaut.setLayout(new BorderLayout());
        this.add(this.conteneurHaut, BorderLayout.CENTER);



/////////Conteneur du bas fixe/////////////////////////////////////
        this.conteneurBas = new JPanel();
        this.conteneurBas.setPreferredSize(new Dimension(1280, 200));
        this.conteneurBas.setBackground(Color.blue);
        this.conteneurBas.setVisible(true);
        this.conteneurBas.setLayout(new BorderLayout());
        this.add(this.conteneurBas, BorderLayout.SOUTH);

        //Ajout de la vue au controleur une fois l'interface terminée
        //Le fait d'ajouter le controleur après le pack() détruit le redimensionnement de la fenêtre -> A corriger
//        this.controleur.ajouterVue(this);

        ////Carte du risk FIXE/////////////////////////////////////
        this.plateauJeu = new PlateauJeu(this);
        this.plateauJeu.setPreferredSize(new Dimension(1000, 520));
        this.plateauJeu.setBackground(Color.BLACK);
        this.plateauJeu.setVisible(true);
        this.conteneurHaut.add(this.plateauJeu, BorderLayout.CENTER);

/////////Conteneur resumé du jeu Amovible /////////////////////////////////////
        JPanel conteneurResumeJeu = new JPanel();
        conteneurResumeJeu.setPreferredSize(new Dimension(280, 520));
        conteneurResumeJeu.setBackground(Color.ORANGE);
        conteneurResumeJeu.setVisible(true);
        conteneurResumeJeu.setLayout(new BorderLayout());
        this.conteneurHaut.add(conteneurResumeJeu, BorderLayout.EAST);

        ////Panneau des ordres Amovible///////////////////////////////////////////////////
        JPanel conteneurPanneauOrdres = new JPanel();
        conteneurPanneauOrdres.setPreferredSize(new Dimension(280, 260));
        conteneurPanneauOrdres.setVisible(true);
        conteneurPanneauOrdres.setLayout(new BorderLayout());
        conteneurPanneauOrdres.setBackground(Color.WHITE);


        conteneurResumeJeu.add(conteneurPanneauOrdres, BorderLayout.CENTER);
        JLabel titreOrdre = new JLabel(" Ordres");
        titreOrdre.setHorizontalAlignment(JLabel.LEFT);

        titreOrdre.setForeground(Color.BLACK);
        titreOrdre.setFont(this.titre);
        conteneurPanneauOrdres.add(titreOrdre, BorderLayout.NORTH);


        JPanel conteneurListeOrdres = new JPanel();
        conteneurListeOrdres.setVisible(true);
        conteneurListeOrdres.setBackground(Color.WHITE);
        conteneurListeOrdres.setLayout(new BorderLayout());
        conteneurListeOrdres.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));

        //conteneurPanneauOrdres.add(conteneurListeOrdres, BorderLayout.CENTER);

        JPanel espaceOrdreGauche = new JPanel();
        espaceOrdreGauche.setPreferredSize(new Dimension(15, 260));
        espaceOrdreGauche.setOpaque(false);
        JPanel espaceOrdreDroite = new JPanel();
        espaceOrdreDroite.setPreferredSize(new Dimension(15, 260));
        espaceOrdreDroite.setOpaque(false);

        conteneurPanneauOrdres.add(espaceOrdreGauche, BorderLayout.WEST);
        conteneurPanneauOrdres.add(espaceOrdreDroite, BorderLayout.EAST);

        ////Panneau contenant la liste de tous les ordres///////////////////////////////////////////////////
        this.listeOrdre = new JPanel();
        this.listeOrdre.setVisible(true);
        this.listeOrdre.setBackground(Color.WHITE);
        this.listeOrdre.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        this.listeOrdre.setPreferredSize(new Dimension(210, 200));

        conteneurPanneauOrdres.add(conteneurListeOrdres, BorderLayout.CENTER);

//        PanelOrdre ordre1 = new PanelOrdre("Attaque de Egypte à AfriqueNord");
//        PanelOrdre ordre2 = new PanelOrdre("Pertes :  2 unités");
//        PanelOrdre ordre3 = new PanelOrdre("Afrique du nord capturé !");
//        PanelOrdre ordre4 = new PanelOrdre("Déployement à Europe Est");
//        PanelOrdre ordre5 = new PanelOrdre("Bla");
//        PanelOrdre ordre6 = new PanelOrdre("Truc");
//
//        this.listeOrdre.add(ordre1);
//        this.listeOrdre.add(ordre2);
//        this.listeOrdre.add(ordre3);
//        this.listeOrdre.add(ordre4);
//        this.listeOrdre.add(ordre5);
//        this.listeOrdre.add(ordre6);
//        this.listeOrdre.add(new PanelOrdre("machine"));
//        this.listeOrdre.add(new PanelOrdre("descend"));

        ////Barre de scroll//////////////////////////////////////////////////////////////
        JScrollPane scrollPane = new JScrollPane(this.listeOrdre);
        JScrollBar scrollBarV = new JScrollBar(JScrollBar.VERTICAL);
        scrollBarV.setPreferredSize(new Dimension(20, 260));
        scrollPane.setVerticalScrollBar(scrollBarV);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        conteneurListeOrdres.add(scrollPane, BorderLayout.CENTER);

        ////Conteneur des factions fixe ///////////////////////////////////////////////////
        JPanel conteneurFactions = new JPanel();
        conteneurFactions.setPreferredSize(new Dimension(280, 260));
        conteneurFactions.setBackground(Color.WHITE);
        conteneurFactions.setVisible(true);
        conteneurFactions.setLayout(new BorderLayout());

        conteneurResumeJeu.add(conteneurFactions, BorderLayout.SOUTH);

        ////Titre "Factions" ///////////////////////////////////////////////////
        JLabel titreFactions = new JLabel(" Factions");
        titreFactions.setHorizontalAlignment(JLabel.LEFT);
        titreFactions.setForeground(Color.BLACK);
        titreFactions.setFont(this.titre);
        titreFactions.setPreferredSize(new Dimension(280, 60));


        conteneurFactions.add(titreFactions, BorderLayout.NORTH);

        ////Panneau des factions fixe ///////////////////////////////////////////////////
        this.panneauFactions = new JPanel();
        //this.panneauFactions.setPreferredSize(new Dimension(280, 200));
        this.panneauFactions.setBackground(Color.WHITE);
        this.panneauFactions.setForeground(Color.BLACK);
        this.panneauFactions.setVisible(true);
        this.panneauFactions.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
        this.panneauFactions.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));

        conteneurFactions.add(this.panneauFactions, BorderLayout.CENTER);

        ////JPanels servant d'espace afin de centrer le panneau des factions ///////////////////////////////////////////////////
        JPanel espaceFactionDroite = new JPanel();
        espaceFactionDroite.setPreferredSize(new Dimension(15, 260));
        espaceFactionDroite.setOpaque(false);
        JPanel espaceFactionBas = new JPanel();
        espaceFactionBas.setPreferredSize(new Dimension(200, 25));
        espaceFactionBas.setOpaque(false);
        JPanel espaceFactionGauche = new JPanel();
        espaceFactionGauche.setPreferredSize(new Dimension(15, 260));
        espaceFactionGauche.setOpaque(false);

        conteneurFactions.add(espaceFactionBas, BorderLayout.SOUTH);
        conteneurFactions.add(espaceFactionDroite, BorderLayout.EAST);
        conteneurFactions.add(espaceFactionGauche, BorderLayout.WEST);

        ////Barre des 3 phases de jeu fixe ///////////////////////////////////////////////////
        this.panneauPhasesJeu = new JPanel();
        this.panneauPhasesJeu.setPreferredSize(new Dimension(1280, 40));
        this.panneauPhasesJeu.setBackground(Color.WHITE);
        this.panneauPhasesJeu.setVisible(true);
        this.panneauPhasesJeu.setLayout(new FlowLayout());
        this.conteneurBas.add(this.panneauPhasesJeu, BorderLayout.NORTH);


        ////Panneau des actions correspondant à la phase de jeu en cours - Amovible ///////////////////////////////////////////////////
        this.panneauActionPhase = new PanneauActionPhase();
        this.conteneurBas.add(panneauActionPhase, BorderLayout.CENTER);

/////////Conteneur des des informations de jeu fixe/////////////////////////////////////
        JPanel conteneurInfos = new JPanel();
        conteneurInfos.setPreferredSize(new Dimension(1280, 60));
        //conteneurInfos.setVisible(true);
        conteneurInfos.setLayout(new BorderLayout());
        this.conteneurBas.add(conteneurInfos, BorderLayout.SOUTH);

        ////Barre représentant les forces des joueurs - Fixe ///////////////////////////////////////////////////
        this.barreForceArmees = new JPanel();
        barreForceArmees.setPreferredSize(new Dimension(1280, 20));
        barreForceArmees.setBackground(Color.WHITE);
        barreForceArmees.setVisible(true);

        ////Barre de texte d'information pour guider le joueur - Fixe ///////////////////////////////////////////////////
        this.infoBarre = new JLabel("Barre d'information");
        this.infoBarre.setPreferredSize(new Dimension(1280, 30));
        this.infoBarre.setBackground(Color.WHITE);
        this.infoBarre.setForeground(Color.BLACK);
        this.infoBarre.setHorizontalAlignment(JLabel.CENTER);
        this.infoBarre.setFont(new Font("Verdana", Font.PLAIN, 14));

        conteneurInfos.add(this.infoBarre, BorderLayout.SOUTH);
        conteneurInfos.add(barreForceArmees, BorderLayout.CENTER);

        this.creerBarrePhasesJeu();
    }

    private void creerBarrePhasesJeu() {

        JLabel labelDeployer = new JLabel("Déployer armées +");
        JLabel labelDeplacer = new JLabel("Attaquer / Transfert =>");
        Dimension dimensionPannneau = new Dimension(630, 30);
        labelDeployer.setHorizontalAlignment(JLabel.CENTER);
        labelDeplacer.setHorizontalAlignment(JLabel.CENTER);

        Font policePanneauEtat = new Font("Verdana", Font.BOLD, 20);
        labelDeployer.setFont(policePanneauEtat);
        labelDeplacer.setFont(policePanneauEtat);
        labelDeployer.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.DARK_GRAY));
        labelDeplacer.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.DARK_GRAY));

        labelDeployer.setPreferredSize(dimensionPannneau);
        labelDeplacer.setPreferredSize(dimensionPannneau);
//        labelDeployer.setFont(titre);
//        labelDeplacer.setFont(titre);
        this.panneauPhasesJeu.add(labelDeployer);
        this.panneauPhasesJeu.add(labelDeplacer);
    }

    public void ajouterControleur(Controleur monControleur) {
        this.controleur = monControleur;
    }

    public PlateauJeu rendPlateauJeu() {
        return this.plateauJeu;
    }

    public JPanel rendPanneauPhasesDeJeu() {
        return this.panneauPhasesJeu;
    }

    public JPanel rendPanneauFactions() {
        return this.panneauFactions;
    }

    public PanneauActionPhase rendPanneauActionPhase() {
        return this.panneauActionPhase;
    }

    public void interactionZone(Zone maZone) {
        this.controleur.actionEtat(maZone);
    }

    @Override
    public void update(String nomTerritoire, int nbUnites, Color couleur) {
        for (GroupeZone mesContinents : this.plateauJeu.rendListeContinents()) {
            for (Zone maZone : mesContinents.rendListeZones()) {
                if (maZone.rendNom().equals(nomTerritoire)) {
                    maZone.setNbUnite(nbUnites);
                    if (couleur != null) {
                        maZone.setCouleur(couleur);
                    }

                    //System.out.println(maZone.rendNom() + " : " + nbUnites+ " units");
                }
            }
        }
        this.plateauJeu.repaint();
    }

    public JPanel rendConteneurHaut() {
        return this.conteneurHaut;
    }

    public JPanel rendConteneurBas() {
        return this.conteneurBas;
    }

    public void initialiserFenetre() {
        this.titre = new Font("verdana", Font.PLAIN, 40);

        this.pack();
        this.setVisible(true);
    }

    public void reinitialiserPanneauAction() {
        this.panneauActionPhase = new PanneauActionPhase();
        this.conteneurBas.add(panneauActionPhase, BorderLayout.CENTER);
    }

    public JPanel genereFondDemarrage() {
        JPanel fondAccueil = new JPanel();
        fondAccueil.setPreferredSize(new Dimension(1280, 720));
        fondAccueil.setBackground(Color.BLACK);
        this.add(fondAccueil);

        JPanel espaceHaut = new JPanel();
        espaceHaut.setPreferredSize(new Dimension(1280, 78));
        espaceHaut.setOpaque(false);
        fondAccueil.add(espaceHaut, BorderLayout.NORTH);

        JPanel ecranAccueil = new JPanel();
        ecranAccueil.setPreferredSize(new Dimension(1000, 485));
        ecranAccueil.setOpaque(false);
        ecranAccueil.setLayout(new BorderLayout());
        fondAccueil.add(ecranAccueil, BorderLayout.CENTER);
        return ecranAccueil;
    }

    public void updateBarrePourcentageForces(int nbTotalUniteEnJeu, int[] nbUnitesParJoueur, Color[] couleurs) {
        this.barreForceArmees.removeAll();
        this.barreForceArmees.add(new JBarrePourcentageForces(nbTotalUniteEnJeu, nbUnitesParJoueur, couleurs));
    }

    public void setEtatDeployementJoueur(Color couleurJoueurCourant) {
        Component componentDeployer = this.rendPanneauPhasesDeJeu().getComponents()[0];
        if (componentDeployer instanceof JLabel) {
            JLabel panneauDeployer = (JLabel) componentDeployer;
            panneauDeployer.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, couleurJoueurCourant, couleurJoueurCourant));
        }

        Component componentTransfert = this.rendPanneauPhasesDeJeu().getComponents()[1];
        if (componentTransfert instanceof JLabel) {
            JLabel panneauTransfert = (JLabel) componentTransfert;
            panneauTransfert.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.BLACK));
        }
    }

    public void setEtatDeplacementJoueur(Color couleurJoueurCourant) {
        Component component = this.rendPanneauPhasesDeJeu().getComponents()[1];
        if (component instanceof JLabel) {
            JLabel panneauTransfert = (JLabel) component;
            panneauTransfert.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, couleurJoueurCourant, couleurJoueurCourant));
        }

        Component componentDeployer = this.rendPanneauPhasesDeJeu().getComponents()[0];
        if (componentDeployer instanceof JLabel) {
            JLabel panneauDeployer = (JLabel) componentDeployer;
            panneauDeployer.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.BLACK));
        }
    }

    public void setTexteInfo(String texteInformation) {
        this.infoBarre.setText(texteInformation);
    }
    
    public void ajouterOrdre(String ordreTextuel) {
        this.listeOrdre.add(new PanelOrdre(ordreTextuel));
    }

    public void reinitialiserPanneauOrdres() {
        this.listeOrdre.removeAll();
        //this.pack();
        
    }
}
