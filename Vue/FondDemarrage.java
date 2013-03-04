/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Karim
 */
public class FondDemarrage extends JPanel {

    public FondDemarrage() {
        super();
        
    }

    public void paintComponent(Graphics g) {
        try {
            Image img = ImageIO.read(new File("fond.jpg"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            //Pour une image de fond
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
