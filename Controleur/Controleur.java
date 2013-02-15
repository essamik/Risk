/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Action.ActionAjouterJoueur;
import Action.ActionLancerPartie;
import Action.ActionStart;
import Modele.Continent;
import Modele.JeuRisk;
import Modele.Joueur;
import Modele.Territoire;
import Vue.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.*;

/**
 *
 * @author Karim
 */
public class Controleur {

    private JeuRisk modele;
    private FenetreRisk vue;
    private HashMap<GroupeZone, Continent> mapCarte;
    private EtatJeu etat;
    private ArrayList<PanneauAjoutJoueur> listePanneauJoueurs;
    private final int NB_JOUEUR_MAX = 3;

    public Controleur(JeuRisk modeleRisk, FenetreRisk vueRisk) {
        this.modele = modeleRisk;
        this.vue = vueRisk;
        this.modele.addObserver(this.vue);
        this.mapCarte = new HashMap();
        this.listePanneauJoueurs = new ArrayList<>();
        //test
        this.etat = new EtatInitialisation(this);

        //Ajout des 2 premiers joueurs requis
        this.modele.ajouterJoueurs("Joueur 1", Color.RED);
        this.modele.ajouterJoueurs("Joueur 2", Color.BLUE);
    }

//    public void ajouterVue(FenetreRisk maFenetre) {
//        this.vue = maFenetre;
//        this.modele.addObserver(vue);
//    }
    public void lancerEcranDemarrage() {
        JPanel fondAccueil = new JPanel();
        fondAccueil.setPreferredSize(new Dimension(1280, 720));
        fondAccueil.setBackground(Color.BLACK);
        fondAccueil.setVisible(true);
        this.vue.add(fondAccueil);

        JPanel espaceHaut = new JPanel();
        espaceHaut.setPreferredSize(new Dimension(1280, 78));
        espaceHaut.setVisible(true);
        espaceHaut.setOpaque(false);
        fondAccueil.add(espaceHaut, BorderLayout.NORTH);

        JPanel ecranAccueil = new JPanel();
        ecranAccueil.setPreferredSize(new Dimension(1000, 485));
        ecranAccueil.setBackground(Color.WHITE);
        ecranAccueil.setVisible(true);
        ecranAccueil.setLayout(new BorderLayout());
        fondAccueil.add(ecranAccueil, BorderLayout.CENTER);

        JLabel titreIntroduction = new JLabel("RISK");
        titreIntroduction.setFont(new Font("verdana", Font.BOLD, 100));
        titreIntroduction.setPreferredSize(new Dimension(1000, 150));
        titreIntroduction.setHorizontalAlignment(JLabel.CENTER);

        JLabel sousTitreIntro = new JLabel("The Java Game");
        sousTitreIntro.setFont(new Font("verdana", Font.PLAIN, 60));
        sousTitreIntro.setPreferredSize(new Dimension(1000, 100));

        sousTitreIntro.setHorizontalAlignment(JLabel.CENTER);

        JButton startButton = new JButton("START");
        startButton.setFont(new Font("verdana", Font.PLAIN, 40));
        startButton.setPreferredSize(new Dimension(400, 100));
        startButton.addActionListener(new ActionStart(this));


        ecranAccueil.add(titreIntroduction, BorderLayout.NORTH);
        ecranAccueil.add(sousTitreIntro, BorderLayout.CENTER);
        ecranAccueil.add(startButton, BorderLayout.SOUTH);

        this.vue.pack();
    }

    public void initialiserChoixJoueur() {
        this.vue.getContentPane().removeAll();
        this.vue.initialiserFenetre();

        //Réinitialiser de la liste des joueurs
        this.listePanneauJoueurs = new ArrayList<PanneauAjoutJoueur>();

        JPanel fondAccueil = new JPanel();
        fondAccueil.setPreferredSize(new Dimension(1280, 720));
        fondAccueil.setBackground(Color.BLACK);
        fondAccueil.setVisible(true);
        this.vue.add(fondAccueil);

        JPanel espaceHaut = new JPanel();
        espaceHaut.setPreferredSize(new Dimension(1280, 78));
        espaceHaut.setVisible(true);
        espaceHaut.setOpaque(false);
        fondAccueil.add(espaceHaut, BorderLayout.NORTH);

        JPanel ecranJoueurs = new JPanel();
        ecranJoueurs.setPreferredSize(new Dimension(1000, 485));
        ecranJoueurs.setBackground(Color.WHITE);
        ecranJoueurs.setVisible(true);
        ecranJoueurs.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        fondAccueil.add(ecranJoueurs, BorderLayout.CENTER);

        //Répétition de tout le code en haut... A centraliser qqpart
        int numeroJoueur = 1;
        for (Joueur monJoueur : this.modele.rendJoueurs()) {

            PanneauAjoutJoueur panneauJoueur = new PanneauAjoutJoueur(numeroJoueur, monJoueur.rendNom(), monJoueur.rendCouleur());

            this.listePanneauJoueurs.add(panneauJoueur);
            ecranJoueurs.add(panneauJoueur);

            numeroJoueur++;
        }

        if (this.modele.rendJoueurs().size() < this.NB_JOUEUR_MAX) {
            //Bouton + pour ajouter un joueur
            JButton boutonAjouterJoueur = new JButton("+");
            boutonAjouterJoueur.setPreferredSize(new Dimension(45, 45));
            boutonAjouterJoueur.addActionListener(new ActionAjouterJoueur(this));
            JPanel panneauJoueurSupp = new JPanel();
            panneauJoueurSupp.setPreferredSize(new Dimension(600, 60));
            panneauJoueurSupp.setVisible(true);
            panneauJoueurSupp.setOpaque(false);
            panneauJoueurSupp.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));

            ecranJoueurs.add(panneauJoueurSupp);
            panneauJoueurSupp.add(boutonAjouterJoueur);

        }

        JButton launchGameButton = new JButton("Lancer la partie");
        launchGameButton.setPreferredSize(new Dimension(300, 100));
        launchGameButton.addActionListener(new ActionLancerPartie(this));

        ecranJoueurs.add(launchGameButton);

        this.vue.pack();
    }

    public ArrayList<PanneauAjoutJoueur> rendlistePanneauJoueur() {
        return this.listePanneauJoueurs;
    }

    public ArrayList<Joueur> rendlisteJoueur() {
        return this.modele.rendJoueurs();
    }

    public void ajouterInfosJoueur(String nom, Color couleur) {
        this.modele.ajouterJoueurs(nom, couleur);
    }

    public void initialiserLancementJeu() {
        this.vue.getContentPane().removeAll();
        this.vue.initialiserFenetre();
        this.vue.creerPlateauJeu();
        this.creerMenu();
        this.creerMap();
        this.vue.ajouterControleur(this);
        //Ordre de jeu random : 
        Collections.shuffle(this.modele.rendJoueurs());
        this.modele.initialiserNbUnitesDepart();
        this.etat.affecterJoueurSuivant(this.modele.rendJoueurs().get(0));
        this.updateUnitesRestantADeployer();
        this.updatePanneauFaction();

        this.vue.pack();
    }

    public void updatePanneauFaction() {
        boolean estLeJoueurCourant;
        this.vue.rendPanneauFactions().removeAll();
        for (Joueur monJoueur : this.modele.rendJoueurs()) {
            estLeJoueurCourant = false;
            if (monJoueur == this.etat.rendJoueurCourant()) {
                estLeJoueurCourant = true;
            }
            PanneauFaction panneauJoueur = new PanneauFaction(monJoueur.rendCouleur(), monJoueur.rendNom(), estLeJoueurCourant);
            this.vue.rendPanneauFactions().add(panneauJoueur);
        }
        this.vue.pack(); // Peut foutre la merde -> A déplacer
    }

    public void updateUnitesRestantADeployer() {
        this.vue.rendPanneauActionPhase().setNbUniteADeployer(this.etat.rendJoueurCourant().rendUnitesADeployer());
    }

    private void creerMenu() {
        //MENU/////////////////////////////////////////////////////////////////
        JMenuBar barreMenu = new JMenuBar();
        this.vue.setJMenuBar(barreMenu);

        JMenu menuFile = new JMenu("Fichier");
        barreMenu.add(menuFile);

        menuFile.add(new JMenuItem("Sauvegarder  partie"));
        menuFile.add(new JMenuItem("Charger partie"));
        menuFile.add(new JMenuItem("Recommencer partie"));
        menuFile.add(new JMenuItem("Quitter sans sauvegarder"));

        JMenu menuEdition = new JMenu("Aide");
        barreMenu.add(menuEdition);
        menuEdition.add(new JMenuItem("Tutorial du jeu"));
        menuEdition.add(new JMenuItem("A propos"));

//        this.vue.getContentPane().setLayout(new BorderLayout());

    }

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
            this.mapCarte.put(groupeZone, monContinent);
        }
        this.vue.rendPlateauJeu().creerCarte(listeVueContinents);
    }

//    public boolean controleAjoutUnite(String nomTerritoire) {
//        boolean autorisationAjout = false;
//        if (nomTerritoire != null) {
//            //Verifie qu'il ne s'agit pas d'un territoire ennemi
//            if (!this.controleTerritoireEnnemi(nomTerritoire)) {
//                this.modele.conquerirTerritoire(nomTerritoire, this.etat.rendJoueurCourant());
//            }
//            autorisationAjout = true;
//        }
//        return autorisationAjout;
//    }

    public void actionEtat(Zone maZone) {
        this.etat.interactionUtilisateur(maZone);
    }

    public void actionDeplacement(Zone zoneDepart, Zone zoneArrivee) {
        System.out.println("Déplacement de " + zoneDepart.rendNom() + " à " + zoneArrivee.rendNom());
    }

    public void actionAttaque(Zone zoneDepart, Zone zoneArrivee) {
        System.out.println("Attaque de " + zoneDepart.rendNom() + " à " + zoneArrivee.rendNom());
    }

    public void effacerVue() {
        this.vue.getContentPane().removeAll();
        this.vue.initialiserFenetre();
    }

    public void reinitialiserListeJoueur() {
        this.modele.reinitialiserListeJoueur();
    }

    public Joueur rendJoueurSuivant(Joueur joueurCourant) {
        return this.modele.rendJoueurSuivant(joueurCourant);
    }

//    public boolean controleAppartenanceTerritoire(String nomTerritoire) {
//        boolean appartientAuJoueur = false;
//        if (nomTerritoire != null) {
//            if (this.controleTerritoireEnnemi(nomTerritoire)) {
//                appartientAuJoueur = true;
//            } else {
//                for (Territoire territoireControle : this.modele.rendJoueurActuel().rendListeTerritoire()) {
//                    if (nomTerritoire.equals(territoireControle.rendNom())) {
//                        appartientAuJoueur = true;
//                    }
//                }
//            }
//        }
//        return appartientAuJoueur;
//    }

    /**
     * Détermine si le territoire appartient à un autre joueur où s'il est
     * libre. La recherche est effectué en fonction des territoires controlés par les joueurs.
     * @param nomTerritoire : Le nom du territoire dont il faut vérifier l'occupation.
     * @return : True si le territoire est occupé par l'ennemi, false s'il est libre où s'il nous appartient.
     */
    public boolean controleTerritoireEnnemi(String nomTerritoire) {
        boolean territoireEstOccupe = false;
        for (Joueur monJoueur : this.modele.rendJoueurs()) {
            //On exclu le joueur courant
            if (!monJoueur.rendNom().equals(this.etat.rendJoueurCourant().rendNom())) {
                for (Territoire monTerritoire : monJoueur.rendListeTerritoire()) {
                    //Si on trouve le territoire dans la liste des territoires d'un joueur adverse -> false
                    if (nomTerritoire.equals(monTerritoire.rendNom())) {
                        territoireEstOccupe = true;
                        System.err.println("Ce territoire appartient à qqun d'autre !");
                    }
                }
            }
        }
        return territoireEstOccupe;
    }
    
    public void annexerTerritoire(Zone territoireAConquerir, Joueur joueurConquerant) {
        this.modele.conquerirTerritoire(territoireAConquerir.rendNom(), joueurConquerant);
    }

    /**
     * Vérifie si la phase de jeu actuelle est terminée et lance la phase
     * suivante si besoin est.
     */
    public boolean checkFinPhaseInitialisation() {
        //Si le dernier joueur à déployer n'as plus rien à déployer -> phase suivante
        boolean phaseInitialisationFini = false;
        int nbJoueur = this.modele.rendJoueurs().size();
        if (this.modele.rendJoueurs().get(nbJoueur - 1).rendUnitesADeployer() <= 0) {
            this.etat = new EtatDeployement(this);
            this.etat.setJoueurCourant(this.modele.rendJoueurs().get(0));
            phaseInitialisationFini = true;
            
            
        }

        return phaseInitialisationFini;
    }

    public void lancerPhaseDeployement() {
        this.modele.rendNbUnitesADeployer(this.etat.rendJoueurCourant());
        System.out.println("Lancement du déployement");
    }
}