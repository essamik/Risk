/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Observer.Observeur;
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

    //private EventListenerList listeners;
    public JeuRisk() {
        this.listeJoueurs = new ArrayList<>();
        this.listObserver = new ArrayList<>();
        this.initialiserCarteMonde();
        //Test avec 1 joueur//
        this.listeJoueurs.add(new Joueur("Joueur1", Color.BLUE));

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

    public ArrayList<Joueur> rendJoueurs() {
        ArrayList<Joueur> joueurs = this.listeJoueurs;
        return joueurs;
    }

    /**
     * Ajoute une unité sur le territoire sélectionné.
     * @param nomTerritoire : Le nom du territoire
     */
    public void ajouterUnite(String nomTerritoire) {
        for (Continent monContinent : this.carte.rendListeContinents()) {
            for (Territoire monTerritoire : monContinent.rendTerritoires()) {
                if (monTerritoire.rendNom().equals(nomTerritoire)) {
                    int nbUnites;
                    nbUnites = monTerritoire.ajouterUnite();
                    this.notifyObserver(nomTerritoire, nbUnites);
                }
            }
        }
        
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
    public void notifyObserver(String nomTerritoire, int nbUnites) {
        for(Observeur obs : this.listObserver) {
            obs.update(nomTerritoire, nbUnites);
        }
    }
}
