package Modele;

import java.util.Random;

/**
 * Modèle de dé permettant de simuler l'utilisation d'un vrai dé en rendant au hasard l'une des 6 faces visible.
 * @author Karim
 */
public class De  {

    /**
     * Constructeur de dé.
     */
    public De() {
        
    }
    
    /**
     * Renvois la valeur de la face visible d'un dé à 6 faces.
     * @return : Une valeure entre 1 et 6 au hasard.
     */
    public int rendsValeurFaceVisible() {
	int nombre = (new Random()).nextInt(6)+1;
        return nombre;
    }
}
