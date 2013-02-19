package Modele;

import java.util.Random;

/**
 *
 * @author Karim
 */
public class De {


    public De() {
        
    }

    public int rendsValeurFaceVisible() {
	int nombre = (new Random()).nextInt(6)+1;
        return nombre;
    }
}
