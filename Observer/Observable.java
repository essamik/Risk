package Observer;

import java.awt.Color;

/**
 * Interface du Design Pattern Observer pour le modèle. Permet de gérer une
 * liste d'observeurs à notifier lors d'un changement d'un territoire du modèle.
 *
 * @author Karim
 */
public interface Observable {

    /**
     * Ajoute un Observeur (le modèle) à la liste des observeurs.
     *
     * @param obs : L'observeur que l'on désire ajouter à la liste des
     * observeurs.
     */
    public void addObserver(Observeur obs);

    /**
     * Remet la liste des observeurs à 0 en retirant tous les observeurs.
     */
    public void removeObserver();
    /**
     * Envois une notification aux observeurs en cas de changement d'un élément
     * du modèle.
     *
     * @param nomTerritoire : Le nom du territoire ayant subi un changement.
     * @param nbUnites : Le nouveau nombre d'unités du territoire.
     * @param nbUnitesDeplacable : Le nombre d'unités actives que l'on peut
     * déplacer.
     * @param couleur : La nouvelle couleur du territoire.
     */
    
      public void notifyObserver(String nomTerritoire, int nbUnites, int nbUnitesDeplacable, Color couleur); }
     
