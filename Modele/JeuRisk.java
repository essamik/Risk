/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Observer.Observeur;
import Vue.Zone;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Karim
 */
public class JeuRisk implements Observer.Observable {

    private CarteTerre carte; //A remplacer par une interface commune pour toutes les cartes
    private ArrayList<Joueur> listeJoueurs;
    private ArrayList<Observeur> listObserver;
    private De de;
    private final int NB_UNITES_A_DEPLOYER_MINIMUM = 3;
    private final int NB_UNITE_INITIALE_2_JOUEURS = 2; // 40
    private final int NB_UNITE_INITIALE_3_JOUEURS = 2; //35
    private final int NB_UNITE_INITIALE_4_JOUEURS = 30;
    private final int NB_UNITE_INITIALE_5_JOUEURS = 25;
    private final int NB_UNITE_INITIALE_6_JOUEURS = 20;

    //private EventListenerList listeners;
    public JeuRisk() {
        this.listeJoueurs = new ArrayList<>();
        this.listObserver = new ArrayList<>();
        this.de = new De();
        this.initialiserCarteMonde();
        //Test avec 1 joueur//
        //this.listeJoueurs.add(new Joueur("Joueur1", Color.BLUE));
    }

    /**
     * Créer une carte simplifiée de la terre.
     */
    private void initialiserCarteMonde() {
        this.carte = new CarteTerre();
    }

    public CarteTerre rendCarte() {
        return this.carte;
    }

    public void ajouterJoueurs(String nom, Color couleur) {
        this.listeJoueurs.add(new Joueur(nom, couleur));
    }

    public ArrayList<Joueur> rendListeJoueurs() {
        ArrayList<Joueur> joueurs = this.listeJoueurs;
        return joueurs;
    }

    public Joueur rendDernierJoueur() {
        return this.listeJoueurs.get(this.listeJoueurs.size() - 1);
    }

    /**
     * Ajoute une unité sur le territoire sélectionné.
     *
     * @param nomTerritoire : Le nom du territoire
     * @param joueurConquerant : Le joueur ayant annexé le territoire
     */
    public void conquerirTerritoire(String nomTerritoire, Joueur joueurConquerant) {
        for (Continent monContinent : this.carte.rendListeContinents()) {
            for (Territoire monTerritoire : monContinent.rendTerritoires()) {
                if (monTerritoire.rendNom().equals(nomTerritoire)) {
                    int nbUnites;
                    nbUnites = monTerritoire.ajouterUnite();
                    monTerritoire.setCouleur(joueurConquerant.rendCouleur());
                    joueurConquerant.addTerritoire(monTerritoire);
                    joueurConquerant.deployerUnite();
                    this.notifyObserver(nomTerritoire, nbUnites, joueurConquerant.rendCouleur());
                }
            }
        }
    }

    public void reinitialiserListeJoueur() {
        this.listeJoueurs = new ArrayList<Joueur>();
    }

    public Joueur rendJoueurSuivant(Joueur joueurCourant) {
        Joueur joueurSuivant = null;
        if (joueurCourant != null && this.listeJoueurs.contains(joueurCourant)) {
            int ordreJoueurActuel = this.listeJoueurs.indexOf(joueurCourant);
            if (this.listeJoueurs.size() - 1 > ordreJoueurActuel) {
                joueurSuivant = this.listeJoueurs.get(ordreJoueurActuel + 1);
            } else { //Sinon, on renvois le premier joueur de la liste
                joueurSuivant = this.listeJoueurs.get(0);
            }
        }
        return joueurSuivant;
    }

    public void initialiserNbUnitesDepart() {
        int nbUnitesInitiale = 0;
        switch (this.listeJoueurs.size()) {
            case 2:
                nbUnitesInitiale = this.NB_UNITE_INITIALE_2_JOUEURS;
                break;
            case 3:
                nbUnitesInitiale = this.NB_UNITE_INITIALE_3_JOUEURS;
                break;
            case 4:
                nbUnitesInitiale = this.NB_UNITE_INITIALE_4_JOUEURS;
                break;
            case 5:
                nbUnitesInitiale = this.NB_UNITE_INITIALE_5_JOUEURS;
                break;
            case 6:
                nbUnitesInitiale = this.NB_UNITE_INITIALE_6_JOUEURS;
                break;
        }

        for (Joueur monJoueur : this.listeJoueurs) {
            monJoueur.ajouteUnitesADeployer(nbUnitesInitiale);
        }
    }

    public int rendNbUnitesADeployer(Joueur joueur) {
        int unitesADeployer = 0;
        if (joueur != null) {
            int nbTerritoiresControles = joueur.rendListeTerritoire().size();
            int quotaTroupes = nbTerritoiresControles / 3;

            if (quotaTroupes <= 3) {
                unitesADeployer = this.NB_UNITES_A_DEPLOYER_MINIMUM;
            } else if (quotaTroupes > 3) {
                unitesADeployer = quotaTroupes;
            }
            System.out.println(unitesADeployer + " unités à déployer");
            //Ajout des unités bonus
            unitesADeployer += this.rendUnitesBonusPourControleContinent(joueur);
        }
        return unitesADeployer;
    }

    private int rendUnitesBonusPourControleContinent(Joueur joueur) {
        int unitesBonus = 0;
        if (joueur != null) {
            //Calcul le nombre de fois qu'un territoire est recensé dans le continent actuellement parcourus
            int checkTerritoire;
            for (Continent monContinent : this.carte.rendListeContinents()) {
                checkTerritoire = 0;
                for (Territoire monTerritoire : monContinent.rendTerritoires()) {
                    //Si le territoire appartient au joueur
                    if (joueur.rendListeTerritoire().contains(monTerritoire)) {
                        checkTerritoire++;
                    }
                }
                //Si le nombre de territoire correspondant correspond au nombre total de territoires de ce continent
                if (monContinent.rendTerritoires().size() == checkTerritoire) {
                    unitesBonus = monContinent.rendNbUnitesBonusPourControleContinent();
                    System.out.println(unitesBonus + " unités supplémentaires reçus pour controle de" + monContinent.rendNom());
                }
            }
        }

        return unitesBonus;
    }

    @Override
    public void addObserver(Observeur obs) {
        this.listObserver.add(obs);
    }

    @Override
    public void removeObserver() {
        this.listObserver = new ArrayList<Observeur>();
    }

    @Override
    public void notifyObserver(String nomTerritoire, int nbUnites, Color couleur) {
        for (Observeur obs : this.listObserver) {
            obs.update(nomTerritoire, nbUnites, couleur);
        }
    }

    public boolean deplacerUnites(String nomTerritoireDepart, String nomTerritoireArrivee, int nbUnitesADeplacer, Joueur joueurDeplacement) {
        boolean deplacementEffectue = false;
        if (nomTerritoireDepart != null && nomTerritoireArrivee != null && nbUnitesADeplacer > 0) {
            for (Continent monContinent : this.carte.rendListeContinents()) {
                for (Territoire monTerritoire : monContinent.rendTerritoires()) {
                    if (monTerritoire.rendNom().equals(nomTerritoireDepart)) {
                        Territoire territoireDestination = monTerritoire.deplacerUnites(nbUnitesADeplacer, nomTerritoireArrivee);
                        if (territoireDestination != null) {
                            //Le joueur annexe le territoire
                            joueurDeplacement.addTerritoire(territoireDestination);
                            this.notifyObserver(territoireDestination.rendNom(), territoireDestination.rendNbUnites(), joueurDeplacement.rendCouleur());
                            this.notifyObserver(monTerritoire.rendNom(), monTerritoire.rendNbUnites(), joueurDeplacement.rendCouleur());
                            deplacementEffectue = true;
                        }
                    }
                }
            }
        }
        return deplacementEffectue;
    }

    public boolean attaquer(String nomTerritoireAttaquant, String nomTerritoireDefenseur, int nbUnitesADeplacer, Joueur joueurAttaquant) {
        boolean attaqueEffectuee = false;
        if (nomTerritoireAttaquant != null && nomTerritoireDefenseur != null && nbUnitesADeplacer > 0) {
            for (Continent monContinent : this.carte.rendListeContinents()) {
                for (Territoire monTerritoire : monContinent.rendTerritoires()) {
                    if (monTerritoire.rendNom().equals(nomTerritoireAttaquant)) {
                        Territoire territoireDefenseur = monTerritoire.lancerAttaque(nbUnitesADeplacer, nomTerritoireDefenseur, this.de);
                        if (territoireDefenseur != null) {
                            //Le joueur annexe le territoire
                            this.retirerTerritoireAJoueur(territoireDefenseur);
                            joueurAttaquant.addTerritoire(territoireDefenseur);
                            this.notifyObserver(territoireDefenseur.rendNom(), territoireDefenseur.rendNbUnites(), joueurAttaquant.rendCouleur());
                            this.notifyObserver(monTerritoire.rendNom(), monTerritoire.rendNbUnites(), joueurAttaquant.rendCouleur());
                            attaqueEffectuee = true;
                        } else { //En cas de défaite, mise à jour du nombre d'unités par territoire (et il faut chercher manuellement le territoire défenseur
                            for (Territoire territoireVoisin : monTerritoire.rendListeVoisins()) {
                                if (monTerritoire.rendNom().equals(nomTerritoireAttaquant)) {
                                    this.notifyObserver(territoireVoisin.rendNom(), territoireVoisin.rendNbUnites(), territoireVoisin.rendCouleur());
                                    this.notifyObserver(monTerritoire.rendNom(), monTerritoire.rendNbUnites(), joueurAttaquant.rendCouleur());
                                }
                            }
                        }
                    }
                }
            }
        }
        return attaqueEffectuee;
    }

    private void retirerTerritoireAJoueur(Territoire territoirePerdu) {
        for (Joueur monJoueur : this.listeJoueurs) {
            for (Territoire monTerritoire : monJoueur.rendListeTerritoire()) {
                if (territoirePerdu == monTerritoire) {
                    monJoueur.retirerTerritoire(territoirePerdu);
                }
            }
        }
    }
}
