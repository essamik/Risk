package Controleur;

import Action.*;
import Modele.*;
import Vue.*;
import com.sun.corba.se.spi.monitoring.MonitoredObject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import modele.Chronometre;

/**
 * Permet de faire le lien entre le modèle et la vue. S'occupe de : -
 * Intercèpter les interactions de l'utilisateur, contrôler l'intégrité des
 * données et les envoyer au modèle. - Mettre à jour les panneaux d'informations
 * de la vue. - Gèrer le passage entre les différentes étapes du jeu. - Gèrer la
 * charge/sauvegarde des parties de jeu.
 *
 * @author Karim
 */
public class Controleur {

    private JeuRisk modele;
    private FenetreRisk vue;
    private EtatJeu etat;
    private ArrayList<PanneauAjoutJoueur> listePanneauJoueurs;
    private final int NB_JOUEUR_MAX = 6;
    private MementoDeployement mementoDeployement;

    /**
     * Constructeur de Controleur, demande le modèle et la vue afin de
     * fonctionner.
     *
     * @param modeleRisk : Le modèle contenant les informations et les
     * mécaniques du jeu risk.
     * @param vueRisk : La vue contenant l'interface du jeu Risk.
     */
    public Controleur(JeuRisk modeleRisk, FenetreRisk vueRisk) {
        if (modeleRisk == null || vueRisk == null) {
            throw new RuntimeException("Paramètres manquants : Impossible de lancer le programme !");
        }
        this.modele = modeleRisk;
        this.vue = vueRisk;
        this.modele.addObserver(this.vue);
        this.listePanneauJoueurs = new ArrayList<>();
        this.etat = new EtatInitialisation(this);

        //Ajout des 2 premiers joueurs requis minimum
        this.modele.creerJoueur("Joueur 1", this.rendCouleurRandom());
        this.modele.creerJoueur("Joueur 2", this.rendCouleurRandom());
    }

    /**
     * Lance l'interface de démarrage du jeu permettant au joueur de lancer une
     * partie ou de charger une sauvegarde.
     */
    public void lancerEcranDemarrage() {
        JPanel ecranAccueil = this.vue.genereFondDemarrage();
        JLabel titreIntroduction = new JLabel("RISK");
        titreIntroduction.setFont(new Font("verdana", Font.BOLD, 100));
        titreIntroduction.setPreferredSize(new Dimension(1000, 150));
        titreIntroduction.setHorizontalAlignment(JLabel.CENTER);

        JLabel sousTitreIntro = new JLabel("The Java Game");
        sousTitreIntro.setFont(new Font("verdana", Font.PLAIN, 60));
        sousTitreIntro.setPreferredSize(new Dimension(1000, 100));

        sousTitreIntro.setHorizontalAlignment(JLabel.CENTER);

        JPanel conteneurBas = new JPanel();
        conteneurBas.setPreferredSize(new Dimension(1000, 225));
        conteneurBas.setOpaque(false);
        conteneurBas.setLayout(new BorderLayout());

        JPanel espaceBoutonHaut = new JPanel();
        espaceBoutonHaut.setPreferredSize(new Dimension(1000, 70));
        espaceBoutonHaut.setOpaque(false);

        JPanel espaceBoutonBas = new JPanel();
        espaceBoutonBas.setPreferredSize(new Dimension(1000, 10));
        espaceBoutonBas.setOpaque(false);

        JPanel espaceBoutonGauche = new JPanel();
        espaceBoutonGauche.setPreferredSize(new Dimension(250, 225));
        espaceBoutonGauche.setOpaque(false);

        JPanel espaceBoutonDroite = new JPanel();
        espaceBoutonDroite.setPreferredSize(new Dimension(250, 200));
        espaceBoutonDroite.setOpaque(false);

        JButton startButton = new JButton("START");
        startButton.setFont(new Font("verdana", Font.PLAIN, 40));
        startButton.addActionListener(new ActionStart(this));
        startButton.setPreferredSize(new Dimension(250, 90));

        JButton loadButton = new JButton("Charger partie");
        loadButton.setFont(new Font("verdana", Font.PLAIN, 20));
        loadButton.addActionListener(new ActionCharger(this, "Charger partie"));
        loadButton.setPreferredSize(new Dimension(250, 40));

        JPanel conteneurBoutons = new JPanel();
        conteneurBoutons.setPreferredSize(new Dimension(250, 155));
        conteneurBoutons.setLayout(new BorderLayout());
        conteneurBoutons.setVisible(true);
        conteneurBoutons.setOpaque(false);
        conteneurBoutons.add(startButton, BorderLayout.NORTH);
        conteneurBoutons.add(loadButton, BorderLayout.SOUTH);

        conteneurBas.add(conteneurBoutons, BorderLayout.CENTER);
        conteneurBas.add(espaceBoutonHaut, BorderLayout.NORTH);
        conteneurBas.add(espaceBoutonBas, BorderLayout.SOUTH);
        conteneurBas.add(espaceBoutonGauche, BorderLayout.WEST);
        conteneurBas.add(espaceBoutonDroite, BorderLayout.EAST);

        ecranAccueil.add(titreIntroduction, BorderLayout.NORTH);
        ecranAccueil.add(sousTitreIntro, BorderLayout.CENTER);
        ecranAccueil.add(conteneurBas, BorderLayout.SOUTH);

        this.vue.pack();
    }

    /**
     * Lance l'interface de choix des joueurs après l'écran de démarrage. Permet
     * de changer de nom, ajouter des joueurs(jusqu'a un maximum de 6) et de
     * lancer le jeu Risk.
     */
    public void initialiserChoixJoueur() {
        this.vue.getContentPane().removeAll();

        //Réinitialiser de la liste des joueurs
        this.listePanneauJoueurs = new ArrayList<>();
        this.vue.genereFondDemarrage().removeAll();
        JPanel ecranJoueurs = this.vue.genereFondDemarrage();

        JPanel colonneJoueur = new JPanel();
        colonneJoueur.setPreferredSize(new Dimension(600, 485));
        colonneJoueur.setBackground(Color.WHITE);
        colonneJoueur.setVisible(true);
        colonneJoueur.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        JPanel colonneBouton = new JPanel();
        colonneBouton.setPreferredSize(new Dimension(400, 485));
        colonneBouton.setBackground(Color.WHITE);
        colonneBouton.setVisible(true);
        colonneBouton.setLayout(new BorderLayout());

        JPanel espaceColonneBoutonHaut = new JPanel();
        espaceColonneBoutonHaut.setPreferredSize(new Dimension(400, 200));
        espaceColonneBoutonHaut.setOpaque(false);
        JPanel espaceColonneBoutonBas = new JPanel();
        espaceColonneBoutonBas.setPreferredSize(new Dimension(400, 200));
        espaceColonneBoutonBas.setOpaque(false);
        JPanel espaceColonneBoutonDroite = new JPanel();
        espaceColonneBoutonDroite.setPreferredSize(new Dimension(10, 485));
        espaceColonneBoutonDroite.setOpaque(false);

        JPanel espaceColonneBoutonGauche = new JPanel();
        espaceColonneBoutonGauche.setPreferredSize(new Dimension(10, 485));
        espaceColonneBoutonGauche.setOpaque(false);

        colonneBouton.add(espaceColonneBoutonHaut, BorderLayout.NORTH);
        colonneBouton.add(espaceColonneBoutonBas, BorderLayout.SOUTH);
        colonneBouton.add(espaceColonneBoutonDroite, BorderLayout.EAST);
        colonneBouton.add(espaceColonneBoutonGauche, BorderLayout.WEST);
        ecranJoueurs.add(colonneJoueur, BorderLayout.WEST);
        ecranJoueurs.add(colonneBouton, BorderLayout.EAST);



        int numeroJoueur = 1;
        for (Joueur monJoueur : this.modele.rendListeJoueurs()) {
            PanneauAjoutJoueur panneauJoueur = new PanneauAjoutJoueur(numeroJoueur, monJoueur.rendNom(), monJoueur.rendCouleur());
            this.listePanneauJoueurs.add(panneauJoueur);
            colonneJoueur.add(panneauJoueur);
            numeroJoueur++;
        }

        if (this.modele.rendListeJoueurs().size() < this.NB_JOUEUR_MAX) {
            //Bouton + pour ajouter un joueur
            JButton boutonAjouterJoueur = new JButton("+");
            boutonAjouterJoueur.setPreferredSize(new Dimension(450, 45));
            boutonAjouterJoueur.setFont(new Font("verdana", Font.BOLD, 20));
            boutonAjouterJoueur.addActionListener(new ActionAjouterJoueur(this));
            JPanel panneauJoueurSupp = new JPanel();
            panneauJoueurSupp.setVisible(true);
            panneauJoueurSupp.setOpaque(false);
            panneauJoueurSupp.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));

            colonneJoueur.add(panneauJoueurSupp);
            panneauJoueurSupp.add(boutonAjouterJoueur);

        }
        JLabel infoNbJoueur = new JLabel("6 joueurs max");
        infoNbJoueur.setPreferredSize(new Dimension(500, 45));
        infoNbJoueur.setHorizontalAlignment(JLabel.CENTER);

        colonneJoueur.add(infoNbJoueur);
        JButton launchGameButton = new JButton("Lancer la partie");
        launchGameButton.setPreferredSize(new Dimension(300, 100));
        launchGameButton.setFont(new Font("verdana", Font.PLAIN, 26));
        launchGameButton.addActionListener(new ActionLancerPartie(this));

        colonneBouton.add(launchGameButton, BorderLayout.CENTER);

        this.vue.pack();
    }

    /**
     * Renvois la liste des panneaux de joueurs visible à l'écran de choix des
     * joueurs.
     *
     * @return : Le panneau de tous les joueurs ajoutés en phase de démarrage du
     * jeu.
     */
    public ArrayList<PanneauAjoutJoueur> rendlistePanneauJoueur() {
        return this.listePanneauJoueurs;
    }

    /**
     * Renvois la liste des joueurs du modèle.
     *
     * @return : La liste des Joueurs.
     */
    public ArrayList<Joueur> rendlisteJoueur() {
        return this.modele.rendListeJoueurs();
    }

    /**
     * Ajoute les informations relatives au Joueur dans le modèle dans le but de
     * créer un objet Joueur.
     *
     * @param nom : Le nom du joueur à créer.
     * @param couleur : La couleur du jouer à créer.
     */
    public void ajouterInfosJoueur(String nom, Color couleur) {
        if (nom != null && couleur != null) {
            this.modele.creerJoueur(nom, couleur);
        }
    }

    /**
     * Lance le jeu Risk avec sa carte et son interface de jeu.
     */
    public void initialiserLancementJeu() {
        this.vue.getContentPane().removeAll();
        this.vue.creerPlateauJeu();
        this.creerMenu();
        this.creerMap();
        this.vue.ajouterControleur(this);
        //Ordre de jeu random : 
        Collections.shuffle(this.modele.rendListeJoueurs());
        this.modele.initialiserNbUnitesDepart();
        this.etat.setJoueurCourant(this.modele.rendListeJoueurs().get(0));
        this.updateUnitesRestantADeployer();
        this.updateVueJoueur();
        this.vue.setEtatDeployementJoueur(this.etat.rendJoueurCourant().rendCouleur());
        this.vue.setTexteInfo("Chaque joueur clic à tour de rôle sur un pays afin de se l'approprier jusqu'a ce que toutes les unités ont été déployées", Color.BLACK);
        this.vue.pack();
    }

    /**
     * Ajoute le menu supérieur dans l'interface du jeu. Permet de
     * sauvegarder/charger une partie, recommencer, quitter, afficher le tuto du
     * jeu et les infos "à propos".
     */
    private void creerMenu() {
        JMenuBar barreMenu = new JMenuBar();
        this.vue.setJMenuBar(barreMenu);

        JMenu menuFile = new JMenu("Fichier");
        barreMenu.add(menuFile);

        JMenuItem menuSauvegarder = new JMenuItem(new ActionSauvegarder(this, "Sauvegarder  partie"));
        menuSauvegarder.setEnabled(false);
        this.vue.setMenuSauvegarder(menuSauvegarder); //On garde la référence vers l'élément de menu sauvegarder afin de le rendre cliquable plus tard
        menuFile.add(menuSauvegarder);
        menuFile.add(new JMenuItem(new ActionCharger(this, "Charger partie")));
        menuFile.add(new JMenuItem(new ActionRecommencer(this, "Recommencer partie")));
        menuFile.add(new JMenuItem(new ActionQuitter("Quitter la partie")));

        JMenu menuEdition = new JMenu("Edition");
        barreMenu.add(menuEdition);
        JMenuItem menuRepartitionAuto = new JMenuItem(new ActionRepartitionAleatoire(this, "Répartition aléatoire des unités"));
        this.vue.setMenuRepartitionAuto(menuRepartitionAuto);
        menuEdition.add(menuRepartitionAuto);

        JMenu menuAide = new JMenu("Aide");
        barreMenu.add(menuAide);
        menuAide.add(new JMenuItem(new ActionAfficherTutorial("Tutorial du jeu")));
        menuAide.add(new JMenuItem(new ActionAbout("A propos")));
    }

    /**
     * Met à jour le panneau d'information du jeu avec le nombre d'untités
     * restant à déployer au Joueur.
     */
    public void updateUnitesRestantADeployer() {
        this.vue.rendPanneauActionPhase().setNbUniteADeployer(this.etat.rendJoueurCourant().rendUnitesADeployer());
    }

    /**
     * Lance la création de la carte dans le plateau de jeu de la vue, en
     * fonction des information des Territoires du modèle. Chaque territoire est
     * dessiné point par point.
     */
    private void creerMap() {
        ArrayList<GroupeZone> listeVueContinents = new ArrayList<>();

        for (Continent monContinent : this.modele.rendCarte().rendListeContinents()) {
            GroupeZone groupeZone = new GroupeZone(monContinent.rendNom());
            for (Territoire monTerritoire : monContinent.rendTerritoires()) {
                //Création de la zone en fonction du modèle
                Zone maZone = new Zone(monTerritoire.rendNom(), monTerritoire.rendCouleur(),
                        monTerritoire.rendCoordonnesX(), monTerritoire.rendCoordonnesY(), monTerritoire.rendPointCentral());
                //Ajout des voisins
                for (Territoire voisin : monTerritoire.rendListeVoisins()) {
                    maZone.ajouterVoisin(new Zone(voisin.rendNom(), voisin.rendCouleur(),
                            voisin.rendCoordonnesX(), voisin.rendCoordonnesY(), monTerritoire.rendPointCentral()));
                }
                //Ajout de la zone dans le groupe
                groupeZone.ajouterZone(maZone);
            }
            listeVueContinents.add(groupeZone);
        }
        this.vue.rendPlateauJeu().creerCarte(listeVueContinents);
    }

    /**
     * Définis le comportement adéquat du clic de l'utilisateur en fonction de
     * l'état de jeu actuel.
     *
     * @param maZone : La Zoen dans laquelle l'utilisateur à cliqué.
     */
    public void actionEtat(Zone maZone) {
        if (maZone != null) {
            this.etat.interactionUtilisateur(maZone);
        }
    }

    /**
     * Ajoute un panneau dans la partie inférieur de l'interface, permettant au
     * joueur de définir le nombre d'unités à déplacer et de valider/annuler
     * l'action.
     *
     * @param zoneDepart : La zone duquel partent les unités.
     * @param zoneArrivee : La zone de destination des unités
     */
    public void ajoutPanneauDeplacement(Zone zoneDepart, Zone zoneArrivee) {
        if (zoneDepart != null && zoneArrivee != null) {
            this.vue.rendPanneauActionPhase().removeAll();
            this.vue.rendPanneauActionPhase().add(new PanneauTransfertUnites(zoneDepart, zoneArrivee, new ActionDeplacer(this), new ActionAnnulerTransfert(this)));
            this.vue.setTexteInfo("Sélectionnez le nombre d'unité que vous souhaitez déplacer, puis cliquez sur le bouton 'Déplacer' pour lancer l'action", Color.BLACK);
            this.vue.pack();
        }
    }

    /**
     *
     * Ajoute un panneau dans la partie inférieur de l'interface, permettant au
     * joueur de définir le nombre d'unités à envoyer à l'attaque et de
     * valider/annuler l'action.
     *
     * @param zoneDepart : La zone duquel partent les unités.
     * @param zoneArrivee : La zone ennemie à attaquer.
     */
    public void ajoutPanneauAttaque(Zone zoneDepart, Zone zoneArrivee) {
        if (zoneDepart != null && zoneArrivee != null) {
            this.vue.rendPanneauActionPhase().removeAll();
            this.vue.rendPanneauActionPhase().add(new PanneauAttaque(zoneDepart, zoneArrivee, new ActionAttaquer(this), new ActionAnnulerTransfert(this)));
            this.updateBarreForces();
            this.vue.setTexteInfo("Sélectionnez le nombre d'unité que vous souhaitez envoyer à l'attaque du territoire ennemi, puis cliquez sur le bouton 'Attaquer' pour lancer l'action", Color.BLACK);
            this.vue.pack();
        }
    }

    /**
     * Réinitialise entièrement la vue.
     */
    public void effacerVue() {
        this.vue.getContentPane().removeAll();
        this.vue.pack();
    }

    /**
     * Remet à 0 la liste des Joueurs.
     */
    public void reinitialiserListeJoueur() {
        this.modele.reinitialiserListeJoueur();
    }

    /**
     * Renvois le Joueur suivant en fonction du Joueur actuel en suivant l'ordre
     * de jeu.
     *
     * @param joueurCourant : Le joueur terimant actuellement son tour.
     * @return : Le Joueur suivant selon l'ordre de jeu définis.
     */
    public Joueur rendJoueurSuivant(Joueur joueurCourant) {
        return this.modele.rendJoueurSuivant(joueurCourant);

    }

    /**
     * Détermine si le territoire appartient à un autre joueur où s'il est
     * libre. La recherche est effectué en fonction des territoires controlés
     * par les joueurs.
     *
     * @param nomTerritoire : Le nom du territoire dont il faut vérifier
     * l'occupation.
     * @return : True si le territoire est occupé par l'ennemi, false s'il est
     * libre où s'il nous appartient.
     */
    public boolean controleTerritoireEnnemi(String nomTerritoire) {
        boolean territoireEstOccupe = false;
        for (Joueur monJoueur : this.modele.rendListeJoueurs()) {
            //On exclu le joueur courant
            if (!monJoueur.rendNom().equals(this.etat.rendJoueurCourant().rendNom())) {
                for (Territoire monTerritoire : monJoueur.rendListeTerritoire()) {
                    //Si on trouve le territoire dans la liste des territoires d'un joueur adverse -> false
                    if (nomTerritoire.equals(monTerritoire.rendNom())) {
                        territoireEstOccupe = true;
                    }
                }
            }
        }
        return territoireEstOccupe;
    }

    /**
     * Vérifie sur le territoire envoyé en paramètre appartient au joueur
     * courant.
     *
     * @param nomTerritoire : Le territoire dont on souhaite contrôler
     * l'apprtenance au Joueur courant.
     * @return : True si le territoire appartient au Joueur, false sinon.
     */
    public boolean controleAppartenanceTerritoire(String nomTerritoire) {
        boolean appartientAuJoueur = false;
        if (nomTerritoire != null) {
            for (Territoire territoireControle : this.etat.rendJoueurCourant().rendListeTerritoire()) {
                if (nomTerritoire.equals(territoireControle.rendNom())) {
                    appartientAuJoueur = true;
                }

            }
        }
        return appartientAuJoueur;
    }

    /**
     * Donne au Joueur le contrôle du territoire envoyé en paramètre, ou y
     * ajoute une unité si le territoire est déjà sous le controle du joueur.
     *
     * @param territoireAConquerir : Le territoire à ajouter à la liste des
     * territoires du joueur.
     * @param joueurConquerant : Le Joueur prenant le contrôle du territoire.
     */
    public void annexerTerritoire(Zone territoireAConquerir, Joueur joueurConquerant) {
        this.modele.conquerirTerritoire(territoireAConquerir.rendNom(), joueurConquerant);
        this.updateBarreForces();
        if (territoireAConquerir.rendNbUnite() > 1) {
            this.vue.ajouterOrdre("Ajout d'une unité à " + territoireAConquerir.rendNom());
        } else {
            this.vue.ajouterOrdre(territoireAConquerir.rendNom() + " capturé");
        }
        if (this.etat instanceof EtatDeployement) {
            this.vue.setTexteInfo("Déployez vos unités en cliquant sur un territoire libre ou un de vos territoire. Cliquez sur le bouton 'Terminer déploiement' pour mettre fin à votre tour", Color.BLACK);
        } else if (this.etat instanceof EtatInitialisation) {
            this.vue.setTexteInfo("Chaque joueur clic à tour de rôle sur un pays afin de se l'approprier jusqu'a ce que toutes les unités ont été déployées", Color.BLACK);
        }
        this.vue.pack();

    }

    /**
     * Lance l'état de déployement du jeu, en attribuant un nombre d'unités a
     * chaque joueur et leur permet de déployer leurs unités sur la carte.
     */
    public void lancerPhaseDeployement() {
        //Affectation des nouvelles unités à déployer
        this.etat.rendJoueurCourant().ajouteUnitesADeployer(this.modele.rendNbUnitesADeployer(this.etat.rendJoueurCourant()));

        this.updateUnitesRestantADeployer();
        this.updateVueJoueur();

        JPanel conteneurBoutons = new JPanel();
        conteneurBoutons.setVisible(true);
        conteneurBoutons.setLayout(new BorderLayout());
        conteneurBoutons.setBackground(Color.WHITE);
        conteneurBoutons.setPreferredSize(new Dimension(320, 100));
        JButton boutonFinDeployement = new JButton("Terminer déploiement");
        boutonFinDeployement.addActionListener(new ActionFinDeployement(this));
        boutonFinDeployement.setPreferredSize(new Dimension(320, 45));
        JButton boutonAnnulerDeployement = new JButton("Annuler déploiement");
        boutonAnnulerDeployement.addActionListener(new ActionAnnulerDeployement(this));
        boutonAnnulerDeployement.setPreferredSize(new Dimension(320, 45));
        conteneurBoutons.add(boutonFinDeployement, BorderLayout.NORTH);
        conteneurBoutons.add(boutonAnnulerDeployement, BorderLayout.SOUTH);
        this.vue.rendPanneauActionPhase().add(conteneurBoutons, BorderLayout.EAST);

        this.mementoDeployement = new MementoDeployement();

        this.etat.setJoueurCourant(this.modele.rendListeJoueurs().get(0)); //A optimiser
        //On autorise les sauvegardes en phase de déployement : 
        this.vue.autoriserSauvegardes();
        this.vue.setTexteInfo("Déployez vos unités en cliquant sur un territoire libre ou un de vos territoire. Cliquez sur le bouton 'Terminer déploiement' pour mettre fin à votre tour", Color.BLACK);
        this.vue.pack();

    }

    /**
     * Vérifie si la phase de jeu d'initialisation est terminée et lance la
     * phase suivante si besoin est.
     */
    public boolean checkFinPhaseInitialisation() {
        //Si le dernier joueur à déployer n'as plus rien à déployer -> phase suivante
        boolean phaseInitialisationFini = false;
        int nbJoueur = this.modele.rendListeJoueurs().size();
        if (this.modele.rendDernierJoueur().rendUnitesADeployer() <= 0) {
            this.etat = new EtatDeployement(this);
            this.etat.setJoueurCourant(this.modele.rendListeJoueurs().get(0));
            this.vue.interdireRepartitionAuto(); //On bloque l'onglet du menu car la répartition ne peut se faire qu'au début du jeu

            this.lancerPhaseDeployement();
            phaseInitialisationFini = true;
        }

        return phaseInitialisationFini;
    }

    /**
     * Met fin au tour de déployement du joueur et donne son tour au Joueur
     * suivant. Si le dernier joueur met fin à son tour, on lance la phase de
     * jeu suivante.
     */
    public void finDeployement() {
        if (this.etat.rendJoueurCourant() != this.modele.rendDernierJoueur()) {
            this.etat.setJoueurCourant(this.modele.rendJoueurSuivant(this.etat.rendJoueurCourant()));
            this.etat.rendJoueurCourant().ajouteUnitesADeployer(this.modele.rendNbUnitesADeployer(this.etat.rendJoueurCourant()));
            this.mementoDeployement = new MementoDeployement();
            this.updateUnitesRestantADeployer();
            this.updateVueJoueur();
            this.vue.setTexteInfo("Déployez vos unités en cliquant sur un territoire libre ou un de vos territoire. Cliquez sur le bouton 'Terminer déploiement' pour mettre fin à votre tour", Color.BLACK);


        } else { //Si le dernier joueur vient de mettre fin à son tour de déployement
            this.etat = new EtatTransfert(this);
            this.vue.setTexteInfo("Effectuez vos déplacements ou attaques. Cliquez sur un de vos territoire ayant au moins 2 unités, puis cliquez sur un territoire adjaçent pour effectuer un déplacement", Color.BLACK);
            this.etat.setJoueurCourant(this.modele.rendListeJoueurs().get(0)); //A optimiser
            this.updateVueJoueur();
            this.vue.rendPanneauActionPhase().removeAll();
            this.vue.rendPanneauActionPhase().add(new PanneauEtatDeplacement(new ActionFinDeplacement((this))));
            this.vue.setEtatDeplacementJoueur(this.etat.rendJoueurCourant().rendCouleur());
            //On bloque les sauvegardes en phase de Transfert : 
            this.vue.interdireSauvegardes();
            this.vue.pack();
        }
    }

    /**
     * Met fin au tour de déplacement/attaque du joueur et donne son tour au
     * Joueur suivant. Si le dernier joueur met fin à son tour, on lance la
     * phase de jeu suivante.
     */
    public void finDeplacement() {
        //Normalement, on passe au joueur suivant
        if (this.etat.rendJoueurCourant() != this.modele.rendDernierJoueur()) {
            this.libererUnitesBloquees();
            this.etat.setJoueurCourant(this.modele.rendJoueurSuivant(this.etat.rendJoueurCourant()));
            this.updateVueJoueur();
            this.vue.rendPanneauActionPhase().add(new PanneauEtatDeplacement(new ActionFinDeplacement((this))));
            this.vue.setEtatDeplacementJoueur(this.etat.rendJoueurCourant().rendCouleur());
            this.vue.setTexteInfo("Effectuez vos déplacements ou attaques. Cliquez sur un de vos territoire ayant au moins 2 unités, puis cliquez sur un territoire adjaçent pour effectuer un déplacement", Color.BLACK);

        } else { //Si le dernier joueur vient de mettre fin à son tour de déplacement
            this.libererUnitesBloquees();
            this.modele.finTour();
            this.etat = new EtatDeployement(this);
            this.etat.setJoueurCourant(this.modele.rendListeJoueurs().get(0)); //A optimiser
            this.vue.rendPanneauActionPhase().removeAll();
            this.vue.reinitialiserPanneauDeployement();
            this.lancerPhaseDeployement();
        }
    }

    /**
     * Lance l'action de déplacement du nombre d'unité choisis entre les deux
     * territoires définis.
     */
    public void deplacerUnites() {
        Component component = this.vue.rendPanneauActionPhase().getComponent(0);
        if (component instanceof PanneauTransfertUnites) {
            int nbUnitesADeplacer = ((PanneauTransfertUnites) component).rendNbUniteDeplacement();
            if (this.modele.deplacerUnites(this.etat.rendZoneDepart().rendNom(), this.etat.rendZoneArrivee().rendNom(), nbUnitesADeplacer, this.etat.rendJoueurCourant())) {
                this.vue.ajouterOrdre("Déplacement de " + this.etat.rendZoneDepart().rendNom() + " à " + this.etat.rendZoneArrivee().rendNom());
                this.etat.rendZoneDepart().setNotClicked();
                this.etat.setZoneDepart(null);
                this.etat.setZoneArrivee(null);
                this.vue.rendPanneauActionPhase().removeAll();
                this.vue.rendPanneauActionPhase().add(new PanneauEtatDeplacement(new ActionFinDeplacement((this))));
                this.vue.setTexteInfo("Effectuez vos déplacements ou attaques. Cliquez sur un de vos territoire ayant au moins 2 unités, puis cliquez sur un territoire adjaçent pour effectuer un déplacement", Color.BLACK);
                this.vue.pack();
            }
        }
    }

    /**
     * Lance l'action d'attaque entre le territoire contrôlé et le territoire
     * ennemi avec le nombre d'unité choisis. Si l'attaque est concluante, le
     * territoire ennemi passe sous le contrôle du joueur attaquant, sinon, les
     * éventuels unités survivantes retournent au territoire de base. Après
     * chaque attaque, contrôle si le joueur attaqué a été éliminé, et lancement
     * du tableau des scores en cas de fin du jeu.
     */
    public void attaquer() {
        Component component = this.vue.rendPanneauActionPhase().getComponent(0);
        if (component instanceof PanneauAttaque) {
            int nbUnitesADeplacer = ((PanneauAttaque) component).rendNbUniteDeplacement();
            RapportCombat rapport = this.modele.attaquer(this.etat.rendZoneDepart().rendNom(), this.etat.rendZoneArrivee().rendNom(), nbUnitesADeplacer, this.etat.rendJoueurCourant());
            this.vue.ajouterOrdre("Attaque de " + rapport.rendTerritoireAttaquant().rendNom() + " à " + rapport.rendTerritoireDefenseur().rendNom());
            this.vue.ajouterOrdre("Nb unités envoyées : " + rapport.rendNbUniteAttaquant());
            this.vue.ajouterOrdre("Perte(s) : " + rapport.rendNbUnitesPerdues());
            if (rapport.rendResultatCombat()) {
                this.vue.ajouterOrdre(rapport.rendTerritoireDefenseur().rendNom() + " capturé");

                if (this.modele.checkJoueurElimine()) {
                    if (!this.modele.rendListeJoueurs().isEmpty()) {
                        this.vue.ajouterOrdre("Joueur éliminé");
                        this.updateVueJoueur();
                        this.updateBarreForces();
                        this.etat.rendZoneDepart().setNotClicked();
                        JOptionPane.showMessageDialog(null, "Joueur éliminé", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        this.finJeu();
                    }
                }

            } else {
                this.vue.ajouterOrdre("Défaite");
                this.vue.ajouterOrdre(+rapport.rendNbUnitesSurvivant() + " survivant(s) de retour à " + rapport.rendTerritoireAttaquant().rendNom());
            }

            this.etat.rendZoneDepart().setNotClicked();
            this.etat.setZoneDepart(null);
            this.etat.setZoneArrivee(null);
            this.vue.rendPanneauActionPhase().removeAll();
            this.vue.rendPanneauActionPhase().add(new PanneauEtatDeplacement(new ActionFinDeplacement((this))));
            this.vue.setTexteInfo("Effectuez vos déplacements ou attaques. Cliquez sur un de vos territoire ayant au moins 2 unités, puis cliquez sur un territoire adjaçent pour effectuer un déplacement", Color.BLACK);
            this.vue.pack();
        }
    }

    /**
     * Renvois une couleur au hasard parmi la liste des couleurs disponible.
     *
     * @return : Une couleur prédéfinie au hasard.
     */
    public final Color rendCouleurRandom() {
        return this.modele.genereCouleurRandom();
    }

    /**
     * Met à jour la barre des forces dans la vue, en fonction du nombre
     * d'unités par joueur sur la carte.
     */
    public void updateBarreForces() {
        int[] nbUniteParJoueur = new int[this.modele.rendListeJoueurs().size()];
        Color[] couleurParJoueur = new Color[this.modele.rendListeJoueurs().size()];
        int nbTotalUnite = 0;

        for (int i = 0; i < this.modele.rendListeJoueurs().size(); i++) {
            int nbUniteEnJeu = this.modele.rendListeJoueurs().get(i).rendNbUniteEnJeu();
            nbUniteParJoueur[i] = nbUniteEnJeu;
            couleurParJoueur[i] = this.modele.rendListeJoueurs().get(i).rendCouleur();
            nbTotalUnite += nbUniteEnJeu;
        }

        this.vue.updateBarrePourcentageForces(nbTotalUnite, nbUniteParJoueur, couleurParJoueur);
    }

    /**
     * Met à jour les panneaux d'informations de l'interface de jeu.
     */
    public void updateVueJoueur() {
        boolean estLeJoueurCourant;
        this.vue.rendPanneauFactions().removeAll();
        for (Joueur monJoueur : this.modele.rendListeJoueurs()) {
            estLeJoueurCourant = false;
            if (monJoueur == this.etat.rendJoueurCourant()) {
                estLeJoueurCourant = true;
            }
            PanneauFaction panneauJoueur = new PanneauFaction(monJoueur.rendCouleur(), monJoueur.rendNom(), estLeJoueurCourant);
            this.vue.rendPanneauFactions().add(panneauJoueur);
        }

        if (this.etat instanceof EtatDeployement || this.etat instanceof EtatInitialisation) {
            this.vue.setEtatDeployementJoueur(this.etat.rendJoueurCourant().rendCouleur());

        } else if (this.etat instanceof EtatTransfert) {
            this.vue.setEtatDeplacementJoueur(this.etat.rendJoueurCourant().rendCouleur());

        }
        this.vue.reinitialiserPanneauOrdres();
        this.vue.rendPanneauFactions().repaint();
        this.vue.pack(); // Peut foutre la merde -> A déplacer
    }

    /**
     * Retourne une alerte sonore etun message d'erreur dans la barre latéral
     * d'information lorsque le joueur effectue une action interdite
     *
     * @param mssgErreur : Le message expliquant au joueur la raison de
     * l'incapacité du jeu à traiter son action.
     */
    public void messageErreur(String mssgErreur) {
        Toolkit.getDefaultToolkit().beep();
        this.vue.setTexteInfo(mssgErreur, Color.RED);
    }

    /**
     * Annuler un déplacement/attaque et permet au joueur de continuer sa phase
     * de jeu.
     */
    public void annulerTransfert() {
        this.vue.rendPanneauActionPhase().removeAll();
        this.etat.rendZoneDepart().setNotClicked();
        this.etat.setZoneDepart(null);
        this.etat.setZoneArrivee(null);
        this.vue.rendPanneauActionPhase().add(new PanneauEtatDeplacement(new ActionFinDeplacement((this))));
        this.vue.setEtatDeplacementJoueur(this.etat.rendJoueurCourant().rendCouleur());
        this.vue.setTexteInfo("Effectuez vos déplacements ou attaques. Cliquez sur un de vos territoire ayant au moins 2 unités, puis cliquez sur un territoire adjaçent pour effectuer un déplacement", Color.BLACK);
        this.vue.rendPlateauJeu().repaint();
        this.vue.pack();
    }

    /**
     * Lance l'interface de fin de jeu affichant les scores de chaque joueur.
     */
    private void finJeu() {
        this.vue.getContentPane().removeAll();
        JPanel panel = this.vue.genereFondDemarrage();
        JPanel conteneurEnTete = new JPanel();
        conteneurEnTete.setPreferredSize(new Dimension(1000, 40));
        conteneurEnTete.setVisible(true);
        conteneurEnTete.setLayout(new BorderLayout());

        JPanel espaceGaucheEnTete = new JPanel();
        espaceGaucheEnTete.setPreferredSize(new Dimension(340, 40));
        espaceGaucheEnTete.setVisible(true);

        JPanel panelEnTetes = new JPanel();
        //panelEnTetes.setPreferredSize(new Dimension (700, 100));
        panelEnTetes.setVisible(true);
        panelEnTetes.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));

        conteneurEnTete.add(espaceGaucheEnTete, BorderLayout.WEST);
        conteneurEnTete.add(panelEnTetes, BorderLayout.CENTER);

        Font policeEnTete = new Font("verdana", Font.BOLD, 14);

        JLabel labelUnite = new JLabel("Nb d'unités total");
        JLabel labelTour = new JLabel("Nb de tour");
        JLabel espace = new JLabel("   ");
        JLabel labelTemps = new JLabel("Temps");

        labelUnite.setFont(policeEnTete);
        labelTour.setFont(policeEnTete);
        labelTemps.setFont(policeEnTete);

        panelEnTetes.add(labelUnite);
        panelEnTetes.add(labelTour);
        panelEnTetes.add(espace);
        panelEnTetes.add(labelTemps);


        JPanel panneauListeJoueurs = new JPanel();
        panneauListeJoueurs.setPreferredSize(new Dimension(800, 300));
        panneauListeJoueurs.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));
        panneauListeJoueurs.setVisible(true);

        panel.add(conteneurEnTete, BorderLayout.NORTH);
        panel.add(panneauListeJoueurs, BorderLayout.CENTER);

        int rang = 1;
        Collections.reverse(this.modele.rendListeJoueursElimines());
        for (Joueur monJoueur : this.modele.rendListeJoueursElimines()) {
            panneauListeJoueurs.add(new PanelScoreJoueur(rang, monJoueur.rendNom(), monJoueur.rendCouleur(), monJoueur.rendNbUniteTotalEnJeu(), monJoueur.rendNbTourJoue(), monJoueur.rendTempsJeu()));
            rang++;
        }
        this.vue.pack();
    }

    /**
     * Effectue une sauvegarde binaire de la partie en cours. L'utilisateur peut
     * choisir où enregistrer sur son ordinateur le fichier binaire. La
     * sauvegarde n'est autorisée qu'en phase de déployement.
     *
     * @param chemin : Le chemin vers le dossier où L'utilisateur décide
     * d'enregistrer le fichier.
     */
    public void sauvegarderPartie(String chemin) {

        try {
            ObjectOutputStream flotTraitementOut = null;
            // permet d'écrire le flot de 11000110101001111000010 dans un fichier
            FileOutputStream flotCommunicationOut = new FileOutputStream(chemin);
            // permet de transformer les objets en un flot de 1001100110010010010
            flotTraitementOut = new ObjectOutputStream(flotCommunicationOut);
            //Temps de jeu
            flotTraitementOut.writeObject(this.modele.rendChronometre());
            //N° tour
            flotTraitementOut.writeInt(this.modele.rendNbTour());

            //Enregistrment de l'état actuel de tous les territoires
            flotTraitementOut.writeObject(this.modele.rendCarte().rendListeContinents());

            //Joueurs
            flotTraitementOut.writeObject(this.modele.rendListeJoueurs());
            flotTraitementOut.writeObject(this.modele.rendListeJoueursElimines());


            //Joueur courant
            flotTraitementOut.writeObject(this.etat.rendJoueurCourant());


            // Le fait de fermer le flot de traitement, ferme automatiquement
            // le flot de communication associé.
            flotTraitementOut.close();
            JOptionPane.showMessageDialog(null, "Partie sauvegardée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Echec de la sauvegarde de la partie", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Charge le fichier de sauvegarde binaire de jeu fournis par l'utilisateur
     * et réinitialise la partie en fonction de la sauvegarde.
     *
     * @param chemin : Le chemin sur l'ordinateur où est enregistré le fichier
     * de sauvegarde.
     */
    public void chargerPartie(String chemin) {
        if (this.vue.rendPlateauJeu() == null) {
            this.initialiserLancementJeu();
        }
        try {
            // Permet transformer le fichier en un flot de 1100100010010010011001
            FileInputStream flotCommunicationIn = new FileInputStream(chemin);
            Joueur joueurCourant;
            try (ObjectInputStream flotTraitementIn = new ObjectInputStream(flotCommunicationIn)) {
                this.modele.setTempsJeu((Chronometre) flotTraitementIn.readObject());
                this.modele.setNbTour(flotTraitementIn.readInt());

                this.modele.rendCarte().chargerListeContinents( (ArrayList<Continent>) flotTraitementIn.readObject());
                ArrayList<Joueur> mesJoueurs = (ArrayList<Joueur>) flotTraitementIn.readObject();
                this.modele.chargerJoueurs(mesJoueurs);
                ArrayList<Joueur> mesJoueursElimine = (ArrayList<Joueur>) flotTraitementIn.readObject();
                this.modele.chargerJoueursElimines(mesJoueursElimine);
                joueurCourant = (Joueur) flotTraitementIn.readObject();
            }



            this.vue.getContentPane().removeAll();
            this.vue.rendPlateauJeu().reinitialiserListeContinents();
            this.vue.pack();
            this.vue.creerPlateauJeu();
            this.creerMenu();
            this.creerMap();
            for (Continent monContinent : this.modele.rendCarte().rendListeContinents()) {
                for (Territoire monTerritoire : monContinent.rendTerritoires()) {
                    this.modele.notifyObserver(monTerritoire.rendNom(), monTerritoire.rendNbUnites(), monTerritoire.rendNbUniteDeplacable(), monTerritoire.rendCouleur());
                }
            }
            this.vue.rendPanneauActionPhase().removeAll();
            this.vue.reinitialiserPanneauDeployement();
            this.etat = new EtatDeployement(this);
            this.etat.setJoueurCourant(joueurCourant);

            this.updateUnitesRestantADeployer();
            this.updateVueJoueur();
            this.vue.setEtatDeployementJoueur(this.etat.rendJoueurCourant().rendCouleur());
            JPanel conteneurBoutons = new JPanel();
            conteneurBoutons.setVisible(true);
            conteneurBoutons.setLayout(new BorderLayout());
            conteneurBoutons.setBackground(Color.WHITE);
            conteneurBoutons.setPreferredSize(new Dimension(320, 100));
            JButton boutonFinDeployement = new JButton("Terminer déploiement");
            boutonFinDeployement.addActionListener(new ActionFinDeployement(this));
            boutonFinDeployement.setPreferredSize(new Dimension(320, 45));
            JButton boutonAnnulerDeployement = new JButton("Annuler déploiement");
            boutonAnnulerDeployement.addActionListener(new ActionAnnulerDeployement(this));
            boutonAnnulerDeployement.setPreferredSize(new Dimension(320, 45));
            conteneurBoutons.add(boutonFinDeployement, BorderLayout.NORTH);
            conteneurBoutons.add(boutonAnnulerDeployement, BorderLayout.SOUTH);
            this.vue.rendPanneauActionPhase().add(conteneurBoutons, BorderLayout.EAST);

            this.mementoDeployement = new MementoDeployement();

            //On autorise les sauvegardes en phase de déployement : 
            this.vue.autoriserSauvegardes();
            this.vue.interdireRepartitionAuto();
            this.vue.setTexteInfo("Déployez vos unités en cliquant sur un territoire libre ou un de vos territoire. Cliquez sur le bouton 'Terminer déploiement' pour mettre fin à votre tour", Color.BLACK);

            this.vue.pack();
            JOptionPane.showMessageDialog(null, "Partie chargée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Echec du chargement de la partie", "Erreur", JOptionPane.ERROR_MESSAGE);
        }


    }

    /**
     * Annule le déployement du Joueur courant en retirant les territoires
     * capturés de sa liste des territoires ou en soustrayant le nombre de
     * troupes déployés dessus.
     */
    public void restaurerEtatJeu() {
        this.etat.rendJoueurCourant().ajouteUnitesADeployer(this.mementoDeployement.restaurerEtatJeu());
        for (Territoire monTerritoireARestaurer : this.mementoDeployement.rendListeTerritoiresDeployes()) {
            if (monTerritoireARestaurer.rendNbUnites() < 1) {
                this.etat.rendJoueurCourant().retirerTerritoire(monTerritoireARestaurer);
            }
            this.modele.notifyObserver(monTerritoireARestaurer.rendNom(), monTerritoireARestaurer.rendNbUnites(), monTerritoireARestaurer.rendNbUniteDeplacable(), monTerritoireARestaurer.rendCouleur());
        }
        this.mementoDeployement = new MementoDeployement();
        this.updateUnitesRestantADeployer();
        this.updateVueJoueur();
        this.updateBarreForces();
        this.vue.pack();
    }

    /**
     * Met à jour le memento conservant une trace des territoires sur lesquels
     * le joueur à déposé ses troupes, afin de lui permettre d'annuler tous ses
     * déployements.
     */
    public void updateMemento(String nomTerritoire) {
        for (Continent monContinent : this.modele.rendCarte().rendListeContinents()) {
            for (Territoire monTerritoire : monContinent.rendTerritoires()) {
                if (monTerritoire.rendNom().equals(nomTerritoire)) {
                    this.mementoDeployement.ajouterTerritoire(monTerritoire);
                }
            }
        }
    }

    /**
     * Remet à jour entièrement toutes les zones du plateau de jeu.
     */
    public void restaurerPlateauJeu() {
        this.modele.restaurerModele();
        for (Continent monContinent : this.modele.rendCarte().rendListeContinents()) {
            for (Territoire monTerritoire : monContinent.rendTerritoires()) {
                this.modele.notifyObserver(monTerritoire.rendNom(), monTerritoire.rendNbUnites(), monTerritoire.rendNbUniteDeplacable(), monTerritoire.rendCouleur());
            }
        }
    }

    /**
     * Libère toutes les unités dont le mouvement est restreint du joueur
     * courant.
     */
    private void libererUnitesBloquees() {
        for (Territoire monTerritoire : this.etat.rendJoueurCourant().rendListeTerritoire()) {
            monTerritoire.libererUnitesBloquees();
            this.modele.notifyObserver(monTerritoire.rendNom(), monTerritoire.rendNbUnites(), monTerritoire.rendNbUniteDeplacable(), monTerritoire.rendCouleur());
        }
    }

    /**
     * Affiche une info box d'information qui se ferme toute seule au bout d'une
     * seconde.
     *
     * @param texte : Le texte d'information à afficher dans l'info box
     */
    public void afficherInfoBox(String texte) {
        JOptionPane pane = new JOptionPane(texte);
        final JDialog dialog = pane.createDialog("Information");
        Timer timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                // or maybe you'll need dialog.dispose() instead?
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    /**
     * Remplis la carte de manière aléatoire jusqu'a ce que toutes les unités de
     * la phase d'initialisation aient été déployées
     */
    public void lancerRepartitionUnitesAuto() {
        if (this.etat instanceof EtatInitialisation) {
            ArrayList<Territoire> listeTerritoiresLibre = this.modele.rendTerritoiresLibre();
            Collections.shuffle(listeTerritoiresLibre);
            if (listeTerritoiresLibre.size() > 0) {
                for (Territoire monTerritoire : listeTerritoiresLibre) {
                    if(this.etat.rendJoueurCourant().rendUnitesADeployer() > 0) {
                        this.modele.conquerirTerritoire(monTerritoire.rendNom(), this.etat.rendJoueurCourant());
                    }
                    this.etat.setJoueurCourant(this.modele.rendJoueurSuivant(this.etat.rendJoueurCourant()));
                }

            }
            //Une fois que tous les territoires libres ont été distribués
            for (Joueur monJoueur : this.modele.rendListeJoueurs()) {
                while (monJoueur.rendUnitesADeployer() > 0) {
                    Collections.shuffle(monJoueur.rendListeTerritoire());
                    this.modele.conquerirTerritoire(monJoueur.rendListeTerritoire().get(0).rendNom(), monJoueur);
                }
            }
            this.updateVueJoueur();
            this.updateBarreForces();
            this.updateUnitesRestantADeployer();
            this.checkFinPhaseInitialisation();
        }
    }
}
