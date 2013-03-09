package Vue;

import Controleur.Controleur;
import Modele.Continent;
import Modele.Territoire;
import Observer.Observeur;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;

/**
 * Vue du jeu Risk. Recense tous les éléments visuels permettant de jouer au jeu risk.
 * 
 * @author Karim
 */
public class FenetreRisk extends JFrame implements Observeur {

    private PlateauJeu plateauJeu;
    private JLabel panneauPhaseJeu;
    private JPanel conteneurHaut;
    private JPanel conteneurBas;
    private JPanel panneauFactions;
    private JPanel listeOrdre;
    private PanneauDeployement panneauActionPhase;
    private JPanel barreForceArmees;
    private JLabel infoBarre;
    private JMenuItem menuSauvegarder;
    private Font titre;
    private Controleur controleur;

    /**
     * Constructeur de fenêtre de jeu Risk.
     * Initialise les éléments de la vue indispensable et qui ne changeront pas lors du jeu.
     * Définis le Look & Fell de type Nimbus afin d'améliorer le visuel des composants swing.
     */
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
        this.titre = new Font("verdana", Font.PLAIN, 40);

        this.setMinimumSize(new Dimension(1280, 720));
        this.setVisible(true);
        this.pack();
    }

    /**
     * Construit la structure du plateau de jeu interactif ainsi que les différents panneaux de contrôle du jeu.
     * Tous les panneaux créer sont vide , il ne s'agit que de créer les conteneurs pour ces différents éléments.
     */
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

/////////Carte du risk FIXE/////////////////////////////////////
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
        this.listeOrdre.setLayout(new WrapLayout());

        conteneurPanneauOrdres.add(conteneurListeOrdres, BorderLayout.CENTER);
        
        ////Barre de scroll//////////////////////////////////////////////////////////////
        JScrollPane scrollPane = new JScrollPane(this.listeOrdre);
        JScrollBar scrollBarV = new JScrollBar(JScrollBar.VERTICAL);
        scrollBarV.setPreferredSize(new Dimension(20, 260));
        scrollPane.setVerticalScrollBar(scrollBarV);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(210, 200));

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

        ////Barre de la phase de jeu fixe ///////////////////////////////////////////////////
        JPanel conteneurpanneauPhaseJeu = new JPanel();
        conteneurpanneauPhaseJeu.setPreferredSize(new Dimension(1280, 40));
        conteneurpanneauPhaseJeu.setBackground(Color.WHITE);
        conteneurpanneauPhaseJeu.setVisible(true);
        conteneurpanneauPhaseJeu.setLayout(new FlowLayout());
        this.conteneurBas.add(conteneurpanneauPhaseJeu, BorderLayout.NORTH);


        this.panneauPhaseJeu = new JLabel("Déployer armées +");
        Dimension dimensionPannneau = new Dimension(1260, 30);
        this.panneauPhaseJeu.setHorizontalAlignment(JLabel.CENTER);

        Font policePanneauEtat = new Font("Verdana", Font.BOLD, 20);
        this.panneauPhaseJeu.setFont(policePanneauEtat);
        this.panneauPhaseJeu.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.DARK_GRAY));

        this.panneauPhaseJeu.setPreferredSize(dimensionPannneau);

        conteneurpanneauPhaseJeu.add(this.panneauPhaseJeu);

        ////Panneau des actions correspondant à la phase de jeu en cours - Amovible ///////////////////////////////////////////////////
        this.panneauActionPhase = new PanneauDeployement();
        this.conteneurBas.add(panneauActionPhase, BorderLayout.CENTER);

/////////Conteneur des des informations de jeu fixe/////////////////////////////////////
        JPanel conteneurInfos = new JPanel();
        conteneurInfos.setPreferredSize(new Dimension(1280, 60));
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

    }

    /**
     * Permet d'ajouter le controleur à la vue afin de permettre la vue d'informer le controleur d'un clic sur un territoire.
     */
    public void ajouterControleur(Controleur monControleur) {
        this.controleur = monControleur;
    }

    /**
     * Renvois le plateau de jeu avec sa liste de zones.
     * @return Le plateau de jeu de la vue.
     */
    public PlateauJeu rendPlateauJeu() {
        return this.plateauJeu;
    }
    /**
     * Renvois le panneau recensant une interface de la liste des joueurs.
     * @return Le JPanel contenant la liste des joueurs.
     */
    public JPanel rendPanneauFactions() {
        return this.panneauFactions;
    }

    /**
     * Renvois le panneau d'action dépendant de l'état de jeu en cours. 
     * @return Le JPanel contenant les actions et informations relatives a la phase de jeu actuelle.
     */
    public PanneauDeployement rendPanneauActionPhase() {
        return this.panneauActionPhase;
    }

    /**
     * Avertis le controleur d'une interaction sur une zone du plateau de jeu.
     * @param maZone : La zone avec laquelle le joueur a intéragis.
     */
    public void interactionZone(Zone maZone) {
        this.controleur.actionEtat(maZone);
    }

    /**
     * Recrée un nouveau panneau de déployement.
     */
    public void reinitialiserPanneauDeployement() {
        this.panneauActionPhase = new PanneauDeployement();
        this.conteneurBas.add(panneauActionPhase, BorderLayout.CENTER);
    }

    /**
     * Initialise la structure de la vue de démarrage du jeu.
     * @return Le JPanel central de l'interface de démarrage.
     */
    public JPanel genereFondDemarrage() {
        JPanel fondAccueil = new FondDemarrage();
        fondAccueil.setPreferredSize(new Dimension(1280, 720));
        this.add(fondAccueil);
        
        JPanel espaceHaut = new JPanel();
        espaceHaut.setPreferredSize(new Dimension(1280, 78));
        espaceHaut.setOpaque(false);
        fondAccueil.add(espaceHaut, BorderLayout.NORTH);

        JPanel ecranAccueil = new JPanel();
//      JPanel ecranAccueil = new PanelDemarrage();
        ecranAccueil.setPreferredSize(new Dimension(1000, 485));
        ecranAccueil.setOpaque(true);
        ecranAccueil.setVisible(true);
        ecranAccueil.setLayout(new BorderLayout());
        ecranAccueil.setBackground(Color.WHITE);
        ecranAccueil.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
        fondAccueil.add(ecranAccueil, BorderLayout.CENTER);
        
        return ecranAccueil;
    }

    /**
     * Met à jour la barre des forces du jeu en fonction du nombre d'unité par joueur.
     * @param nbTotalUniteEnJeu : Le nombre d'unité total actuellement sur le plateau de jeu
     * @param nbUnitesParJoueur : Tableau contenant le nombre d'unités possedé par joueur.
     * @param couleurs : Tableau contenant les couleurs correspondante de chaque joueur.
     */
    public void updateBarrePourcentageForces(int nbTotalUniteEnJeu, int[] nbUnitesParJoueur, Color[] couleurs) {
        this.barreForceArmees.removeAll();
        this.barreForceArmees.add(new BarrePourcentageForces(nbTotalUniteEnJeu, nbUnitesParJoueur, couleurs));
    }

    /**
     * Définis le titre de l'état de jeu comme étant sous "Déployement" et encadre le texte de la couleur du joueur courant.
     * @param couleurJoueurCourant : La couleur du joueur à qui c'est actuellement le tour de jouer.
     */
    public void setEtatDeployementJoueur(Color couleurJoueurCourant) {
        this.panneauPhaseJeu.setText("Déployer armées +");
        this.panneauPhaseJeu.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, couleurJoueurCourant, couleurJoueurCourant));


    }
    /**
     * Définis le titre de l'état de jeu comme étant sous "Déplacement / Attaque" et encadre le texte de la couleur du joueur courant.
     * @param couleurJoueurCourant : Le joueur à qui c'est actuellement le tour de jouer.
     */
    public void setEtatDeplacementJoueur(Color couleurJoueurCourant) {
        this.panneauPhaseJeu.setText("Déplacement / Attaque =>");
        this.panneauPhaseJeu.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, couleurJoueurCourant, couleurJoueurCourant));

    }

    /**
     * Met à jour le texte de la barre d'information afin de guider le joueur.
     * @param texteInformation : Le texte à écrire dans la barre d'information.
     */
    public void setTexteInfo(String texteInformation, Color couleurTxt) {
        this.infoBarre.setForeground(couleurTxt);
        this.infoBarre.setText(texteInformation);
    }

    /**
     * Ajoute un ordre en fonction des actions du joueur courant.
     * @param ordreTextuel : Le texte résumant l'action effectué par le joueur.
     */
    public void ajouterOrdre(String ordreTextuel) {
        this.listeOrdre.add(new PanelOrdre(ordreTextuel));
    }

    public void reinitialiserPanneauOrdres() {
        this.listeOrdre.removeAll();
        this.listeOrdre.repaint();
        //this.pack();
    }
    
    /**
     * Définis l'élément de menu "Sauvegarder Partie"
     * @param menuSvg : L'objet de menu sauvegarder
     */
    public void setMenuSauvegarder(JMenuItem menuSvg) {
        this.menuSauvegarder = menuSvg;
    }
    
    /**
     * Rend l'élément de menu "Sauvegarder" clicable afin d'autoriser les sauvegardes.
     */
    public void autoriserSauvegardes() {
        this.menuSauvegarder.setEnabled(true);
    }
    
    /**
     * Rend l'élément de menu "Sauvegarder" non clicable afin d'interdire les sauvegardes.
     */
    public void interdireSauvegardes() {
        this.menuSauvegarder.setEnabled(false);
    }

    /**
     * Met à jour la Zone correspondante sur le plateau de jeu en fonction du nom donné.
     * Permet au modèle d'informer la vue d'un changement d'un des territoire.
     * Ceci est une implémentation du design pattern Observer.
     * @param nomTerritoire : Le nom de la Zone à mettre à jour.
     * @param nbUnites : Le nouveau nombre d'unité de la Zone.
     * @param nbUnitesDeplacable  : Le nouveau nombre d'unité actif de la zone.
     * @param couleur : La nouvelle couleur de la Zone.
     */
    @Override
    public void update(String nomTerritoire, int nbUnites, int nbUnitesDeplacable, Color couleur) {
        for (GroupeZone mesContinents : this.plateauJeu.rendListeGroupeZone()) {
            for (Zone maZone : mesContinents.rendListeZones()) {
                if (maZone.rendNom().equals(nomTerritoire)) {
                    maZone.setNbUnite(nbUnites);
                    maZone.setNbUniteDeplacable(nbUnitesDeplacable);
                    if (couleur != null) {
                        maZone.setCouleur(couleur);
                    }
                }
            }
        }
        this.plateauJeu.repaint();
    }
 
}
