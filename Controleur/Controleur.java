/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Action.ActionDeployer;
import Modele.Continent;
import Modele.JeuRisk;
import Modele.Joueur;
import Modele.Territoire;
import Vue.FenetreRisk;
import Vue.GroupeZone;
import Vue.Zone;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Karim
 */
public class Controleur {

    private JeuRisk modele;
    private FenetreRisk vue;
    private HashMap<GroupeZone, Continent> mapCarte;

    public Controleur(JeuRisk modeleRisk) {
        this.modele = modeleRisk;
        this.mapCarte = new HashMap();

    }

    public void ajouterVue(FenetreRisk maFenetre) {
        this.vue = maFenetre;
        this.modele.addObserver(vue);
        this.creerMenu();
        this.creerBarrePhasesJeu();
        this.creerMap();
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
                for(Territoire voisin : monTerritoire.rendListeVoisins()) {
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
//        this.vue.rendPlateauJeu().creerCarte(this.modele.rendCarte().rendListeContinents());
        
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

        this.vue.getContentPane().setLayout(new BorderLayout());

    }

    private void creerBarrePhasesJeu() {

        JButton boutonDeployer = new JButton("Déployer armées +");
        JButton boutonDeplacer = new JButton("Attaquer / Transfert =>");
        JButton boutonConfirmation = new JButton("Confirmation / ");
        Dimension dimensionBouton = new Dimension(420, 30);
        boutonDeployer.setPreferredSize(dimensionBouton);
        boutonDeplacer.setPreferredSize(dimensionBouton);
        boutonConfirmation.setPreferredSize(dimensionBouton);
        boutonDeployer.addActionListener(new ActionDeployer(this.vue, this));
        this.vue.rendPanneauPhasesDeJeu().add(boutonDeployer);
        this.vue.rendPanneauPhasesDeJeu().add(boutonDeplacer);
        this.vue.rendPanneauPhasesDeJeu().add(boutonConfirmation);
    }

    public void ajouterJoueurs() {
        ArrayList<Joueur> listeJoueurs = this.modele.rendJoueurs();
    }

    public boolean controleAjoutUnite(String nomTerritoire) {
        boolean autorisationAjout = false;
        if(nomTerritoire!=null) {
            this.modele.ajouterUnite(nomTerritoire);
            autorisationAjout = true;
        }
        return autorisationAjout;
    }
    
//    private void addListenersToModel() {
//        this.modele.addListener(this.vue);
//    }
//    
//    public void notifyUnitesChanged(String nomTerritoire) {
//        this.modele.ajouterUnite(nomTerritoire);
//    }
}
