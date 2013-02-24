/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Karim
 */
public class Territoire {

    private String nom;
    private ArrayList<Territoire> listeVoisins;
    private int nbUnite;
    private int[] xpoints;
    private int[] ypoints;
    private Color couleur;
    private Point pointCentral;

    public Territoire(String nomTerritoire, int[] coordonneesX, int[] coordonneesY, Point pointCentre) {
        this.nom = nomTerritoire;
        this.nbUnite = 0;
        this.listeVoisins = new ArrayList<>();
        this.xpoints = coordonneesX;
        this.ypoints = coordonneesY;
        this.couleur = Color.GRAY;
        this.pointCentral = pointCentre;

    }

    public boolean addVoisin(Territoire territoireVoisin) {
        boolean aEteAjoute = false;
        if (territoireVoisin != null && !this.listeVoisins.contains(territoireVoisin)) {
            this.listeVoisins.add(territoireVoisin);
            territoireVoisin.addVoisin(this); //Le pays voisin ajoute automatiquement ce pays aussi
        }
        return aEteAjoute;
    }

    public String rendNom() {
        return this.nom;
    }

    public ArrayList<Territoire> rendListeVoisins() {
        ArrayList<Territoire> territoiresAdjacents = this.listeVoisins;
        return territoiresAdjacents;
    }

    public int rendNbUnites() {
        return this.nbUnite;
    }

    public int[] rendCoordonnesX() {
        return this.xpoints;
    }

    public int[] rendCoordonnesY() {
        return this.ypoints;
    }

    public Color rendCouleur() {
        return this.couleur;
    }

    public void setCouleur(Color nouvelleCouleur) {
        this.couleur = nouvelleCouleur;
    }

    public Point rendPointCentral() {
        return this.pointCentral;
    }

    public int ajouterUnite() {
        this.nbUnite++;
        return this.nbUnite;

    }
    
    public void retirerUnite() {
        if(this.nbUnite > 0) {
            this.nbUnite--;
        }
    }

    public boolean retirerUnites(int nbUnitesARetirer) {
        boolean uniteAEteRetiree = false;;
        if (nbUnitesARetirer < this.nbUnite) {
            this.nbUnite = this.nbUnite - nbUnitesARetirer;
            uniteAEteRetiree = true;
        }
        return uniteAEteRetiree;
    }

    public boolean ajouterUnites(int nbUnitesADeplacee) {
        boolean unitesAjoute = false;
        if (this.nbUnite < 100) {
            this.nbUnite = this.nbUnite + nbUnitesADeplacee;
            unitesAjoute = true;
        } else {
            System.err.println("Le nombre maximum d'unités autorisé par territoire est de 100");
        }
        return unitesAjoute;
    }

    /**
     * Effectue le transfert d'unités en retirant le nombre d'unités envoyé du
     * territoire ciblé et en les envoyant au territoire voisin designé
     *
     * @param nbUnitesADeplacer : Le nombre d'unité que le joueur souhaite
     * transférer d'un territoire à l'autre
     * @param nomTerritoireDestination : Le territoire auquel sera ajouté les
     * unités retiré du territoire ciblé
     * @return : Le territoire de destination ou null si le déplacement à échoué
     */
    public Territoire deplacerUnites(int nbUnitesADeplacer, String nomTerritoireDestination) {
        Territoire territoireDestination = null;
        if (nbUnitesADeplacer < this.nbUnite) {
            this.nbUnite = this.nbUnite - nbUnitesADeplacer;
            for (Territoire territoireVoisin : this.listeVoisins) {
                if (nomTerritoireDestination.equals(territoireVoisin.rendNom())) {
                    if (territoireVoisin.ajouterUnites(nbUnitesADeplacer)) {
                        territoireDestination = territoireVoisin;
                    }
                }
            }
        }
        return territoireDestination;
    }

    public Territoire lancerAttaque(int nbUnitesAttaquant, String nomTerritoirDefenseur, De de) {
        Territoire territoireConquis = null;
        if (nbUnitesAttaquant < this.nbUnite) {
            this.nbUnite = this.nbUnite - nbUnitesAttaquant;
            for (Territoire territoireVoisin : this.listeVoisins) {
                if (nomTerritoirDefenseur.equals(territoireVoisin.rendNom())) {
                    //Jets de dés pour le combat
                    if(this.resolutionCombat(nbUnitesAttaquant, territoireVoisin, de)) {
                    //En cas de victoire, on renvois le territoire conquis
                    territoireConquis = territoireVoisin;
                    }
                }
            }
        }
        return territoireConquis;
    }

    private boolean resolutionCombat(int nbUnitesAttaquant, Territoire territoireDefenseur, De de) {
        boolean attaquantEstGagant = false;
        
        RapportCombat rapport = new RapportCombat(this, territoireDefenseur, nbUnitesAttaquant);
        this.retirerUnites(nbUnitesAttaquant);
        int nbAttaque = nbUnitesAttaquant;
//        System.out.println("nombre d'unités attaquant : " + nbUnitesAttaquant);
//        System.out.println("nombre d'unités defendant : " +territoireDefenseur.rendNbUnites());
        //Condition de la boucle : Nb d'unités d'attaquant et aucun camps à 0 unités
        for (int i = 0; i < nbAttaque && territoireDefenseur.rendNbUnites() > 0 && nbUnitesAttaquant > 0; i++) {
            int resultatAttaquant = de.rendsValeurFaceVisible();
            int resultatDefenseur = de.rendsValeurFaceVisible();
//            System.out.println("Attaquant : " + resultatAttaquant + " | defenseur : " + resultatDefenseur);
            //Si le jet de dé du défenseur est supérieur ou égal à celui de l'attaquant, ce dernier perd une unité
            if (resultatDefenseur >= resultatAttaquant) {
                nbUnitesAttaquant--;//
                rapport.perteUnite();
                
            } else {
                territoireDefenseur.retirerUnite();//
                rapport.rendTerritoireDefenseur().retirerUnite();
            }
        }
        if (territoireDefenseur.rendNbUnites() <= 0) {
            territoireDefenseur.ajouterUnites(nbUnitesAttaquant);
            attaquantEstGagant = true;//
            rapport.attaquantGagne();
//            System.out.println("l'attaquant gagne");
        }
        
//        System.out.println("nombre d'unités attaquant APRES : " + nbUnitesAttaquant);
//        System.out.println("nombre d'unités defendant APRES: " +territoireDefenseur.rendNbUnites());
        return attaquantEstGagant;
    }
}
