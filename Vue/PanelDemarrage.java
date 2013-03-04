/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author Karim
 */
class PanelDemarrage extends JPanel {

    public PanelDemarrage() {
        super();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; //Utilisation de Graphics2D afin d'utiliser la transparence, qui n'est pas dispo sous Graphics
        g2d.setColor(Color.WHITE);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
    }
}
