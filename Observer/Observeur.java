/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Observer;

import java.awt.Color;

/**
 *
 * @author Karim
 */
public interface Observeur {
  public void update(String nomTerritoire, int nbUnites, Color couleur);
}