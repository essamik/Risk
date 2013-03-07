/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.*;
import javax.swing.JPanel;

/**
 * Panel central lors du démarrage du jeu. Gère la transparance pour laisser apparaître l'image de fond.
 * @author Karim
 */
class PanelDemarrage extends JPanel {

    /**
     * Constructeur de pannel de démarrage opaque.
     */
    public PanelDemarrage() {
        super();
    }

    /**
     * Définis un fond blanc et transparent pour le panel.
     * @param g : L'objet Graphics permettant de dessiner dans le JPanel
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; //Utilisation de Graphics2D afin d'utiliser la transparence, qui n'est pas dispo sous Graphics
        g2d.setColor(Color.WHITE);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
    }
}
