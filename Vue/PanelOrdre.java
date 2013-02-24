/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Karim
 */
public class PanelOrdre extends JLabel{

    public PanelOrdre(String texte) {
        super(texte);
        this.setForeground(Color.BLACK);
        this.setBackground(Color.WHITE);
        this.setFont(new Font("verdana", Font.PLAIN, 10));
        this.setPreferredSize(new Dimension(210, 30));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));

    }


}
