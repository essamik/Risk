/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Karim
 */
class PanelOrdre extends JLabel{

    public PanelOrdre(String texte) {
        super(texte);
        this.setForeground(Color.black);
        this.setFont(new Font("verdana", Font.PLAIN, 12));
        //this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));

    }


}
