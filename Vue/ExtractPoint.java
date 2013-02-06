package Vue;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Cette classe sert à cliquer les points d'une image donnée.
 * Cette classe n'est pas utilisé dans le jeu.
 * @author Karim
 */
public class ExtractPoint extends JPanel implements MouseListener {

    private ArrayList<Integer> pointsX;
    private ArrayList<Integer> pointsY;
    private Polygon monPoly;

    public ExtractPoint() {
        super();
        this.setPreferredSize(new Dimension(1000,520));
        this.setBackground(Color.BLACK);
        this.setVisible(true);

        this.addMouseListener(this);
        this.pointsX = new ArrayList<>();
        this.pointsY = new ArrayList<>();
        this.monPoly = new Polygon();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
      try {
      File file = new File("MapRisk.png");
      Image img = ImageIO.read(file);
      g.drawImage(img, 0, 0, this);
      //Pour une image de fond
      //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    } catch (IOException e) {
      e.printStackTrace();
    }
        g.setColor(Color.ORANGE);
        g.drawPolygon(this.monPoly);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.pointsX.add(e.getX());
        this.pointsY.add(e.getY());
        for(int maValeur : this.pointsX) {
            System.out.print(maValeur+", ");
        }
        System.out.println("");
        for(int maValeur : this.pointsY) {
            System.out.print(maValeur+", ");
        }
        System.out.println("");
        
        
        this.monPoly.addPoint(e.getX(), e.getY());
                this.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
