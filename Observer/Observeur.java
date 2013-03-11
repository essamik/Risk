package Observer;

import java.awt.Color;

/**
 * Interface du Design Pattern Observer pour la vue. Permet de mettre à jour les
 * données de les Zones subissant un changement en fonction du modèle.
 *
 * @author Karim
 */
public interface Observeur {

    /**
     * Met à jour les données de la Zone correspondant au nom du Territoire
     * envoyé en paramètre.
     *
     * @param nomTerritoire : L'identifiant commun entre la zone et le
     * territoire permettant de mettre à jour la bonne Zone.
     * @param nbUnites : Le nouveau nombre d'unités a fixer pour la zone.
     * @param nbUnitesDeplacable : Le nombre d'unités actives que l'on peut
     * déplacer.
     * @param couleur : La nouvelle couleur à fixer pour la zone.
     */
    public void update(String nomTerritoire, int nbUnites, int nbUnitesDeplacable, Color couleur);
}