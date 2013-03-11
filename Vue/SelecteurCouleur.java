/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Karim
 */
public class SelecteurCouleur extends JFrame implements ChangeListener {

    private JColorChooser colorChooser;
    private PanneauAjoutJoueur monJoueur;

    public SelecteurCouleur(PanneauAjoutJoueur panelJoueur) {
        super();
        if (panelJoueur != null) {
            this.monJoueur = panelJoueur;
        }
        this.colorChooser = new JColorChooser(this.monJoueur.rendCouleur());
        this.colorChooser.getSelectionModel().addChangeListener(this);

        AbstractColorChooserPanel[] panelStyleChooser = this.colorChooser.getChooserPanels();
        this.colorChooser.removeChooserPanel(panelStyleChooser[0]);
        this.setLocation(850, 100);
        this.colorChooser.setPreviewPanel(new JComponent() {
        }); //Cache le preview
        this.getContentPane().add(this.colorChooser);
        this.setVisible(true);
        this.pack();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Color newColor = this.colorChooser.getColor();
        this.monJoueur.setColor(newColor);
    }
}
