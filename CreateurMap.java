
import Vue.ExtractPoint;
import java.awt.Dimension;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Karim
 */
public class CreateurMap extends JFrame{


    public CreateurMap() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Risk - The Java Game");
        this.setLocation(25, 25);
        //this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(1000, 520));
        this.getContentPane().add(new ExtractPoint());

        this.pack();
        this.setVisible(true);

    }
     /**
     * Permet d'afficher CreateurMap
     * @return Retourne "CreateurMap"
     */
    @Override
    public String toString() {
        return "CreateurMap";
    }

}
