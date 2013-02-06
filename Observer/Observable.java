/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Observer;

/**
 *
 * @author Karim
 */
public interface Observable {
  public void addObserver(Observeur obs);
  public void removeObserver();
  public void notifyObserver(String nomTerritoire, int nbUnites);
}

