package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 * Bloc textuel résumant un ordre ou un action effectué durant une des phases de jeu.
 * @author Karim
 */
public class PanelOrdre extends JLabel{

    /**
     * Construit un bloc de texte avec l'ordre envoyé en paramètre.
     * @param texte : L'ordre sous forme textuelle à afficher.
     */
    public PanelOrdre(String texte) {
        super(texte);
        this.setForeground(Color.BLACK);
        this.setBackground(Color.WHITE);
        this.setFont(new Font("verdana", Font.PLAIN, 10));
        this.setPreferredSize(new Dimension(210, 30));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));

    }


}
