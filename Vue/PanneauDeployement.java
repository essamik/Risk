package Vue;

import Action.ActionAnnulerDeployement;
import Controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panneau se mettant en place lors de la phase de déployement, informant le Joueur courant sur le nombre d'unités lui restant à déployer.
 * @author Karim
 */
public class PanneauDeployement extends JPanel {

    private JLabel infoUnites;
    
    /**
     * Constructeur de Panneau de Déployement avec un label informant sur le nb d'unités réstant à déployer.
     */
    public PanneauDeployement() {
        super();
        this.setPreferredSize(new Dimension(960, 100));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        Font police = new Font("verdana", Font.PLAIN, 20);
        this.infoUnites = new JLabel();
        this.infoUnites.setFont(police);
        this.add(infoUnites, BorderLayout.WEST);
        

    }
    /**
     * Fixe le nombre d'unité restant au joueur à déployer.
     * @param nbUnites : Le nombre d'unités réstant à déployer.
     */
    public void setNbUniteADeployer(int nbUnites) {
        this.infoUnites.setText("Nombre d'unités restant à déployer : " +nbUnites);
    }


}
