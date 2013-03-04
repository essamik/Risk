package modele;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Observable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

/**
 * Compteur de temps pouvant en tous temps retourner le nombre de secondes passé depuis son lancement.
 * @author Karim
 */
public class Chronometre implements Serializable {
    
    private Timer tempsEcoule;
    private int nbSecondes = 0;
    
    /**
     * Constructeur de Chronomètre permettant de mesurer le temps.
     */
    public Chronometre(){
        this.initialiseTimer();
    }
    
    /**
     * Lance le timer en partant de 0 seconde.
     */
    public void demarrer(){
        tempsEcoule.start();
    }
    
    /**
     * Actualise le Timer à chaque secondes afin de mettre à jour le temps.
     */
    private void initialiseTimer() {
        Action afficherTemps;
        afficherTemps = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nbSecondes++;
                //System.out.println(nbSecondes);
            }
        };
        tempsEcoule = new Timer(1000, afficherTemps);
        tempsEcoule.setRepeats(true);
    }
    
    /**
     * Retourne le nombre de secondes écoulées depuis le lancement du Timer.
     * @return : Le temps en secondes.
     */
    public String retourneTempsEcoule(){
        
        return ""+nbSecondes;
    }
    
    /**
     * Stop le Timer.
     * @return : Le temps écoulé en seconde.
     */
    public int arreter(){
        tempsEcoule.stop();
        return nbSecondes;
    }
}
