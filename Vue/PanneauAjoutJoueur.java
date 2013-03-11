package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Panneau permettant d'ajouter un Joueur avec un nom et une couleur, lors de la
 * phase de démarrage du jeu.
 *
 * @author Karim
 */
public class PanneauAjoutJoueur extends JPanel implements MouseListener {

    private JLabel numeroJoueur;
    private JTextField nomJoueur;
    private JPanel carreCouleur;

    /**
     * Constructeur de panneau d'ajout de joueur avec un numéro, un nom et une
     * couleur.
     *
     * @param num : Le numero du Joueur.
     * @param nom : Le nom personnalisable du Joueur.
     * @param couleur : La couleur imposée au Joueur.
     */
    public PanneauAjoutJoueur(int num, String nom, Color couleur) {
        super();
        this.setPreferredSize(new Dimension(600, 60));
        this.setVisible(true);
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));

        //this.colorPicker = selecteurCouleur;

        Font police = new Font("verdana", Font.PLAIN, 20);

        JLabel numJoueur = new JLabel();
        numJoueur.setText(num + ".");
        numJoueur.setFont(police);

        JTextField inputJoueur = new JTextField(nom);
        inputJoueur.setPreferredSize(new Dimension(300, 46));
        inputJoueur.setFont(police);

        JPanel caseCouleur = new JPanel();
        caseCouleur.setPreferredSize(new Dimension(45, 45));
        caseCouleur.setBackground(couleur);
        caseCouleur.setVisible(true);
        caseCouleur.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));

        caseCouleur.addMouseListener(this);

        this.numeroJoueur = numJoueur;
        this.nomJoueur = inputJoueur;
        this.carreCouleur = caseCouleur;

        this.add(this.numeroJoueur);
        this.add(this.nomJoueur);
        this.add(this.carreCouleur);

    }


    /**
     * Retourne le nom choisis par le Joueur.
     *
     * @return : Le nom du Joueur.
     */
    public String rendNom() {
        return this.nomJoueur.getText();
    }

    /**
     * Retourne la couleur attribuée au Joueur.
     *
     * @return : La couleur du joueur.
     */
    public Color rendCouleur() {
        return this.carreCouleur.getBackground();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        SelecteurCouleur monSelecteurCouluer = new SelecteurCouleur();
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

    void setColor(Color colorClicked) {
        this.carreCouleur.setBackground(colorClicked);
    }

    /**
     * Classe interne permettant au 
     */
    public class SelecteurCouleur extends JFrame implements ChangeListener {

        private JColorChooser colorChooser;

        public SelecteurCouleur() {
            super();
            
            this.colorChooser = new JColorChooser(PanneauAjoutJoueur.this.rendCouleur());
            this.colorChooser.getSelectionModel().addChangeListener(this);

            AbstractColorChooserPanel[] panelStyleChooser = this.colorChooser.getChooserPanels();
            this.colorChooser.removeChooserPanel(panelStyleChooser[0]);
            this.setLocation(850, 10);
            //Cache le preview
            this.colorChooser.setPreviewPanel(new JComponent() {
            }); 
            this.getContentPane().add(this.colorChooser);
            this.setVisible(true);
            this.pack();
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            Color newColor = this.colorChooser.getColor();
            PanneauAjoutJoueur.this.setColor(newColor);
        }
    }
}
