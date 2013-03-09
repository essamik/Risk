package Modele;

import Observer.Observable;
import Observer.Observeur;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import modele.Chronometre;

/**
 * Modèle de base du jeu Risk. Gère toute la logique interne ainsi que les
 * mécaniques du jeu. Est responsable de stocker les données du jeu. Le modèle
 * comprends : - Une carte de jeu - Une liste des joueurs - Une liste des
 * joueurs éliminés - Un dé servant à simuler les combats - Une liste de
 * couleurs disponible pour les joueurs - Un chronomètre pour compter le temps
 * de jeu
 *
 * @author Karim
 */
public class JeuRisk implements Observable {

    private CarteTerre carte; //A remplacer par une interface commune pour toutes les cartes
    private ArrayList<Joueur> listeJoueurs;
    private ArrayList<Joueur> listeJoueursElimines;
    private De de;
    private ArrayList<Color> listeCouleursDispo;
    private Chronometre tempsJeu;
    private int nbTour;
    private final int NB_UNITES_A_DEPLOYER_MINIMUM = 3;
    private final int NB_UNITE_INITIALE_2_JOUEURS = 2; // 40
    private final int NB_UNITE_INITIALE_3_JOUEURS = 2; //35
    private final int NB_UNITE_INITIALE_4_JOUEURS = 30;
    private final int NB_UNITE_INITIALE_5_JOUEURS = 25;
    private final int NB_UNITE_INITIALE_6_JOUEURS = 2; //20
    private ArrayList<Observeur> listObserver;

    /**
     * Constructeur du modèle de jeu du risk. Doit être lancé dès le début pour
     * permettre de joueur. Lance automatiquement l'initialisation de la carte
     * du monde.
     */
    public JeuRisk() {
        this.listeJoueurs = new ArrayList<>();
        this.listeJoueursElimines = new ArrayList<>();
        this.listObserver = new ArrayList<>();
        this.listeCouleursDispo = new ArrayList<>();
        this.de = new De();
        this.nbTour = 1;
        this.tempsJeu = new Chronometre();
        this.tempsJeu.demarrer();
        this.initialiserCarteMonde();
        this.initialiserCouleursJoueur();
    }

    /**
     * Créer une carte simplifiée de la terre compatible avec le jeu Risk.
     */
    private void initialiserCarteMonde() {
        this.carte = new CarteTerre();
    }

    /**
     * Renvois la carte actuellement utilisée dans le jeu, avec ses continents
     * et ses territoires.
     *
     * @return : La carte du modèle du jeu.
     */
    public CarteTerre rendCarte() {
        return this.carte;
    }

    /**
     * Créer un nouveau joueur en fonction de son nom et de sa couleur et
     * l'ajoute dans la liste des joueurs.
     *
     * @param nom : Le nom unique du joueur en format String.
     * @param couleur : La couleur unique permettant d'identifier visuellement
     * le joueur
     */
    public void creerJoueur(String nom, Color couleur) {
        this.listeJoueurs.add(new Joueur(nom, couleur));
    }

    /**
     * Renvois la liste de tous les joueurs.
     *
     * @return : La liste des joueurs
     */
    public ArrayList<Joueur> rendListeJoueurs() {
        ArrayList<Joueur> joueurs = this.listeJoueurs;
        return joueurs;
    }

    /**
     * Renvois le dernier joueur de la liste (le dernier à jouer)
     *
     * @return : Le dernier joueur à jouer son tour.
     */
    public Joueur rendDernierJoueur() {
        return this.listeJoueurs.get(this.listeJoueurs.size() - 1);
    }

    /**
     * Ajoute une unité sur le territoire selectionné et ajoute ce territoire à
     * la liste des territoires capturés du joueur. Une fois le territoire
     * capturé, sa couleur prends celle du joueur l'ayant pris. La vue est
     * ensuite notifiée du changement afin de se mettre à jour.
     *
     * @param nomTerritoire : Le nom du territoire à capturer
     * @param joueurConquerant : Le joueur ayant annexé le territoire
     * @return : True si le territoire a été capturé, false sinon.
     */
    public boolean conquerirTerritoire(String nomTerritoire, Joueur joueurConquerant) {
        boolean aEteCapture = false;
        for (Continent monContinent : this.carte.rendListeContinents()) {
            for (Territoire monTerritoire : monContinent.rendTerritoires()) {
                if (monTerritoire.rendNom().equals(nomTerritoire)) {
                    int nbUnites;
                    nbUnites = monTerritoire.ajouterUnite();
                    monTerritoire.setCouleur(joueurConquerant.rendCouleur());
                    joueurConquerant.addTerritoire(monTerritoire);
                    joueurConquerant.retirerUnite();
                    this.notifyObserver(nomTerritoire, nbUnites, monTerritoire.rendNbUniteDeplacable(), joueurConquerant.rendCouleur());
                    aEteCapture = true;
                }
            }
        }
        return aEteCapture;
    }

    /**
     * Vide et recréer la liste des joueurs.
     */
    public void reinitialiserListeJoueur() {
        this.listeJoueurs = new ArrayList<Joueur>();
    }

    /**
     * Renvois le joueur suivant en fonction du joueur courrant et de l'ordre de
     * jeu définis. Si on envois le dernier joueur, on recevra le premier
     * joueur.
     *
     * @param joueurCourant : Le joueur finissant actuellement son tour de jeu.
     * @return : Le joueur suivant de la liste des joueurs.
     */
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

    /**
     * Définis au lancement de la phase d'initialisation, le nombre d'unités
     * disponbile par joueur en fonction du nombre de joueur, puis ajoutes les
     * unités aux joueurs.
     */
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

    /**
     * Renvois le nombre d'unités à déployer par joueur en fonction du nombre de
     * territoire contrôlé et des éventuels continents entier contrôlés. Le
     * minimum de troupes attribués est toujours de 3. L'attribution se fait en
     * comptant le nombre de territoire contrôlé et en divisant par 3.
     *
     * @param joueur : Le joueur qui doit déployer ses troupes.
     * @return : Le nombre d'unités à déployer.
     */
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
            //Ajout des unités bonus en cas de contrôle d'un continent entier
            unitesADeployer += this.rendUnitesBonusPourControleContinent(joueur);
        }
        return unitesADeployer;
    }

    /**
     * Renvois des unités supplémentaires pour le déployement du joueur si celui
     * ci contrôle tous les territoires d'un continent. Plus le continent est
     * grand, plus le nombre d'unités bonus est élevé.
     *
     * @param joueur : Le joueur qui doit déployer ses troupes.
     * @return : Le nombre d'unités bonus à déployer.
     */
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
                }
            }
        }
        return unitesBonus;
    }

    /**
     * Effectue un déplacement d'un territoire de départ à un territoire
     * d'arrivée avec un certain nombre d'unité à déplacer. Le déplacement ne
     * peut s'effectuer qu'entre un territoire controlé et un territoire
     * adjacents controlé ou libre.
     *
     * @param nomTerritoireDepart : Le nom du territoire duquel part les unités
     * @param nomTerritoireArrivee : Le nom du territoire où les unités vont se
     * rendre
     * @param nbUnitesADeplacer : Le nombre d'unité à déplacer entre les deux
     * territoires.
     * @param joueurDeplacement : Le joueur effectuant l'action de déplacement.
     * @return : True si le déplacement a réussis, false sinon.
     */
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
                            //Mise à jour de la vue
                            this.notifyObserver(territoireDestination.rendNom(), territoireDestination.rendNbUnites(), territoireDestination.rendNbUniteDeplacable(), joueurDeplacement.rendCouleur());
                            this.notifyObserver(monTerritoire.rendNom(), monTerritoire.rendNbUnites(), monTerritoire.rendNbUniteDeplacable(), joueurDeplacement.rendCouleur());
                            deplacementEffectue = true;
                        }
                    }
                }
            }
        }
        return deplacementEffectue;
    }

    /**
     * Effectue une invasion entre un territoire attaquant et un territoire
     * defenseur avec un nombre d'unités attaquant. L'attaque ne peut
     * s'effectuer que entre deux territoires adjacents contr'olés par des
     * joueurs différents. En cas de victoire, le territoire défenseur est
     * assimilé par le joueur attaquant. En cas de défaite, le territoire
     * défenseur est gardé par le joueur défenseur et les unités survivantes de
     * l'attaquant retournent dans le territoire attaquant.
     *
     * @param nomTerritoireAttaquant : Le territoire duquel part l'offensive.
     * @param nomTerritoireDefenseur : Le territoire vers lequel se dirige
     * l'offensive
     * @param nbUnitesAttaquant : Le nombre d'unité envoyé à l'attaque
     * @param joueurAttaquant : Le joueur lançant l'offensive contre le
     * territoire ennemi.
     * @return : Un Rapport de combat contenant les informations détaillés du
     * combat ayant eu lieu (résultat, nb d'unités perdues...etc)
     */
    public RapportCombat attaquer(String nomTerritoireAttaquant, String nomTerritoireDefenseur, int nbUnitesAttaquant, Joueur joueurAttaquant) {
        RapportCombat rapport = new RapportCombat(nbUnitesAttaquant);
        if (nomTerritoireAttaquant != null && nomTerritoireDefenseur != null && nbUnitesAttaquant > 0) {
            for (Continent monContinent : this.carte.rendListeContinents()) {
                for (Territoire monTerritoire : monContinent.rendTerritoires()) {
                    if (monTerritoire.rendNom().equals(nomTerritoireAttaquant)) {
                        for (Territoire territoireVoisin : monTerritoire.rendListeVoisins()) {
                            if (territoireVoisin.rendNom().equals(nomTerritoireDefenseur)) {
                                Territoire territoireDefenseur = territoireVoisin;
                                Territoire territoireAttaquant = monTerritoire;

                                rapport.setAttaquant(territoireAttaquant);
                                rapport.setDefenseur(territoireDefenseur);
                                //L'attaquant ne peut pas envoyer plus d'unités qu'il n'en possède
                                if (rapport.rendNbUniteAttaquant() < rapport.rendTerritoireAttaquant().rendNbUnites()) {
                                    //Les unités sont retirés du territoire lors de l'attaque.
                                    rapport.rendTerritoireAttaquant().retirerUnites(rapport.rendNbUniteAttaquant());
                                    //Jets de dés pour le combat
                                    rapport = this.resolutionCombat(rapport);

                                    if (rapport.rendResultatCombat()) { //En cas de victoire : 
                                        this.retirerTerritoireAJoueur(territoireDefenseur);
                                        joueurAttaquant.addTerritoire(territoireDefenseur);
                                        this.notifyObserver(territoireDefenseur.rendNom(), territoireDefenseur.rendNbUnites(),territoireDefenseur.rendNbUniteDeplacable(), joueurAttaquant.rendCouleur());
                                        this.notifyObserver(territoireAttaquant.rendNom(), territoireAttaquant.rendNbUnites(), territoireAttaquant.rendNbUniteDeplacable(), joueurAttaquant.rendCouleur());
                                    } else {
                                        //En cas de défaite, mise à jour du nombre d'unités par territoire, retour des unités survivantes
                                        territoireAttaquant.ajouterUnites(rapport.rendNbUnitesSurvivant());
                                        this.notifyObserver(territoireDefenseur.rendNom(), territoireDefenseur.rendNbUnites(),territoireDefenseur.rendNbUniteDeplacable(), territoireVoisin.rendCouleur());
                                        this.notifyObserver(territoireAttaquant.rendNom(), territoireAttaquant.rendNbUnites(),territoireAttaquant.rendNbUniteDeplacable(), joueurAttaquant.rendCouleur());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return rapport;
    }

    /**
     * Retire le territoire envoyé en paramètre au joueur le controlant.
     *
     * @param territoirePerdu : Le territoire à retirer au joueur.
     * @return : True si le territoire a été retiré de la liste des territoires
     * contrôlés par le joueur, false sinon.
     */
    private boolean retirerTerritoireAJoueur(Territoire territoirePerdu) {
        boolean aEteRetire = false;
        for (Joueur monJoueur : this.listeJoueurs) {
            if (monJoueur.rendListeTerritoire().contains(territoirePerdu)) {
                monJoueur.retirerTerritoire(territoirePerdu);
                aEteRetire = true;
            }
        }
        return aEteRetire;
    }

    /**
     * Renvois une couleur au hasard de la liste des 6 couleurs disponible pour
     * identifier les joueurs.
     *
     * @return : La couleur sélectionnée au hasard dans la liste des couleurs.
     */
    public Color genereCouleurRandom() {
        Collections.shuffle(this.listeCouleursDispo);
        return this.listeCouleursDispo.remove(0);
    }

    /**
     * Définis les couleurs 6 couleurs disponibles pour les joueurs du jeu.
     */
    private void initialiserCouleursJoueur() {
        this.listeCouleursDispo.add(Color.RED);
        this.listeCouleursDispo.add(Color.BLUE);
        this.listeCouleursDispo.add(new Color(255, 132, 9)); //Orange
        this.listeCouleursDispo.add(new Color(0, 174, 0)); //Vert
        this.listeCouleursDispo.add(new Color(255, 223, 0)); //Jaune
        this.listeCouleursDispo.add(new Color(29, 220, 201)); //turquoise
    }

    /**
     * Calcul le résultat d'un combat entre deux territoires en fonction du
     * rapport de combat envoyé en paramètre. Pour chaque unité confronté, un dé
     * est lancé pour chaqun, si le résultat du défenseur est plus grand ou égal
     * à celui de l'attaquant, l'attaquant perd une unité. Le combat continue
     * jusqu'a ce que toutes les unités engagées par l'attaquant aient attaqué
     * une fois ou que le défenseur n'aient plus d'unités pour se défendre.
     *
     * @param rapport : Le rapport de combat initial contenant le nombre
     * d'unités envoyé et les territoires cibles.
     * @return : Le rapport de combat remplis au fur et à mesure de la
     * résolution de la confrontation.
     */
    private RapportCombat resolutionCombat(RapportCombat rapport) {

        int nbAttaque = rapport.rendNbUniteAttaquant();
        //Condition de la boucle : Nb d'unités d'attaquant et aucun camps à 0 unités
        for (int i = 0; i < nbAttaque && rapport.rendTerritoireDefenseur().rendNbUnites() > 0 && rapport.rendNbUniteAttaquant() > 0; i++) {
            int resultatAttaquant = de.rendsValeurFaceVisible();
            int resultatDefenseur = de.rendsValeurFaceVisible();
            //Si le jet de dé du défenseur est supérieur ou égal à celui de l'attaquant, ce dernier perd une unité
            if (resultatDefenseur >= resultatAttaquant) {
                rapport.perteUnite();
            } else {
                rapport.rendTerritoireDefenseur().retirerUnite();
            }
        }
        if (rapport.rendTerritoireDefenseur().rendNbUnites() <= 0) {
            rapport.rendTerritoireDefenseur().ajouterUnites(rapport.rendNbUnitesSurvivant());
            rapport.attaquantGagne();
        }
        return rapport;
    }

    /**
     * Incrémente le nombre de tour de 1.
     */
    public void finTour() {
        this.nbTour++;
    }

    /**
     * Effectue une vérification du nombre de territoire possedé par chaque
     * joueur. Si le nombre de territoire d'un joueur tombe à 0, cela signifie
     * qu'il a été éliminé de la partie. Si l'avant dernier joueur a été
     * éliminé, cela signifie que la partie est terminée. Pour chaque joueur
     * éliminé, on stock le numero du tour actuel et le temps de jeu.
     *
     * @return : True si un joueur a été éliminé, false sinon.
     */
    public boolean checkJoueurElimine() {
        boolean joueurAEteElimine = false;
        Joueur joueurElimine = null;
        for (Joueur monJoueur : this.listeJoueurs) {
            if (monJoueur.rendListeTerritoire().isEmpty()) {
                //Si un joueur n'a plus de territoire, il a perdu
                joueurElimine = monJoueur;
            }
        }
        if (joueurElimine != null) {
            if (this.listeJoueurs.size() > 2) { //Si c'est l'avant dernier joueur, fin de la partie
                joueurElimine.setNbTourJoue(this.nbTour);
                joueurElimine.setDureeJeu(this.tempsJeu.retourneTempsEcoule());
                this.listeJoueurs.remove(joueurElimine);
                this.listeJoueursElimines.add(joueurElimine);
                joueurAEteElimine = true;
            } else { //Sinon, on élimine le joueur et la partie continue normalement
                this.listeJoueurs.remove(joueurElimine);
                this.listeJoueursElimines.add(joueurElimine);
                joueurElimine.setNbTourJoue(this.nbTour);
                joueurElimine.setDureeJeu(this.tempsJeu.retourneTempsEcoule());
                joueurAEteElimine = true;
                Joueur joueurGagnant = this.listeJoueurs.remove(0);
                joueurGagnant.setNbTourJoue(this.nbTour);
                joueurGagnant.setDureeJeu(this.tempsJeu.retourneTempsEcoule());
                this.listeJoueursElimines.add(joueurGagnant);
            }
        }
        return joueurAEteElimine;
    }

    /**
     * Renvois la liste des joueurs éliminés.
     *
     * @return : La liste des joueurs éliminés
     */
    public ArrayList<Joueur> rendListeJoueursElimines() {
        ArrayList<Joueur> joueursElimines = this.listeJoueursElimines;
        return joueursElimines;
    }

    /**
     * Renvois le Chronomètre avec le temps en seconde déroulé depuis le début
     * de la partie.
     *
     * @return : Le Chronomètre du jeu.
     */
    public Chronometre rendChronometre() {
        return this.tempsJeu;
    }

    /**
     * Renvois Le nombre de tour depuis le début de la partie.
     *
     * @return : le numéro actuel du tour.
     */
    public int rendNbTour() {
        return this.nbTour;
    }

    /**
     * Fixe le numéro actuel du tour en fonction d'une partie sauvegardé.
     *
     * @param numeroTour : Le numéro de tour de la partie sauvegardée.
     */
    public void setNbTour(int numeroTour) {
        this.nbTour = numeroTour;
    }

    /**
     * Fixe le le temps de jeu en fonction d'une partie sauvegardé.
     *
     * @param chronometre : Le Chronomètre comptant le temps de jeu de la partie
     * sauvegardée.
     */
    public void setTempsJeu(Chronometre chronometre) {
        this.tempsJeu = chronometre;
    }

    /**
     * Ajoute les joueur dans la liste des joueurs.
     *
     * @param mesJoueurs : La liste de joueurs résultant d'une sauvegarde.
     */
    public void chargerJoueurs(ArrayList<Joueur> mesJoueurs) {
        if (mesJoueurs != null) {
            this.listeJoueurs = mesJoueurs;
        }
    }

    /**
     * Ajoute les joueur éliminés dans la liste des joueurs éliminés.
     *
     * @param mesJoueursElimine : La liste de joueurs éliminés résultant d'une
     * sauvegarde.
     */
    public void chargerJoueursElimines(ArrayList<Joueur> mesJoueursElimine) {
        if (mesJoueursElimine != null) {
            this.listeJoueursElimines = mesJoueursElimine;
        }
    }
    
    /**
     * Remet à 0 les différentes liste du modèle en vue de recommencer une partie.
     */
   public void restaurerModele() {
        this.carte = new CarteTerre();
        this.listeJoueurs = new ArrayList<>();
        this.listeCouleursDispo = new ArrayList<>();
        this.initialiserCouleursJoueur();
        this.creerJoueur("Joueur 1", this.genereCouleurRandom());
        this.creerJoueur("Joueur 2", this.genereCouleurRandom());

    }
    /**
     * Ajoute un observer dans la liste des observers du modèle.
     *
     * @param obs : L'Observer à ajouter.
     */
    @Override
    public void addObserver(Observeur obs) {
        this.listObserver.add(obs);
    }

    /**
     * Retire les observeur de la liste des observeurs du modèle et la
     * réinitialise.
     */
    @Override
    public void removeObserver() {
        this.listObserver = new ArrayList<>();
    }

    /**
     * Notifie l'observeur d'un changement dans le modèle du territoire. La vue
     * est ainsi mise à jour une fois seulement que le modèle a été changé.
     *
     * @param nomTerritoire : Le nom du territoire ayant subi un changement.
     * @param nbUnites : Le nouveau nombre d'unités du territoire ayant subi un
     * changement.
     * @param nbUnitesDeplacable : Le nombre d'unités actives que l'on peut
     * déplacer.
     * @param couleur : La nouvelle couleur du territoire ayant subi un
     * changement.
     */
    @Override
    public void notifyObserver(String nomTerritoire, int nbUnites, int nbUnitesDeplacable, Color couleur) {
        for (Observeur obs : this.listObserver) {
            obs.update(nomTerritoire, nbUnites, nbUnitesDeplacable, couleur);
        }
    }

 
}
