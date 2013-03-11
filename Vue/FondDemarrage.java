package Vue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Panel représentant le fond de du programme lors de la phase de lancement du jeu.
 * Affiche une image en fond.
 * @author Karim
 */
public class FondDemarrage extends JPanel {

    /**
     * Constructeur de fond de démarrage.
     */
    public FondDemarrage() {
        super();
        
    }

    /**
     * Dessine une image sur toute la longueur du panel.
     * @param g : Le composant Graphics permettant de dessiner.
     */
    public void paintComponent(Graphics g) {
        try {
            Image img = ImageIO.read(new File("ressources/fond.jpg"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            //Pour une image de fond
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
