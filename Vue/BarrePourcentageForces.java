package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Représentation graphique de l'équilibre des unités présente sur le plateau de jeu.
 * Chaque joueur est représenté par sa couleur et occupe le pourcentage de ses unités par rapport au nombre d'unités total sur le plateau de jeu.
 * Si un joueur est éliminé, il n'apparaît plus dans la barre.
 * @author Karim
 */
public class BarrePourcentageForces extends JPanel {

    private final int LONGUEUR_BARRE_EN_PX = 1270;
    private final int HAUTEUR_BARRE_EN_PX = 20;

    /**
     * Constructeur de Barre de Pourcentage des Forces en jeu sur le plateau.
     * Définis la taille de la barre individuellement pour chaque Joueur, en fonction du nombre d'unité total.
     * @param nbTotalUniteEnJeu : Le nombre d'unité total de tous les joueurs présent sur le plateau de jeu (pas les unités en réserve)
     * @param nbUniteParJoueur : Tableau de nombre d'unités présente sur le plateau par joueur.
     * @param couleurs : Tableau de couleurs de joueur.
     */
    public BarrePourcentageForces(int nbTotalUniteEnJeu, int[] nbUniteParJoueur, Color[] couleurs) {
        super();
        this.setPreferredSize(new Dimension(LONGUEUR_BARRE_EN_PX, HAUTEUR_BARRE_EN_PX));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        int i = 0;
        double[] pourcentageUniteParJoueur = new double[nbUniteParJoueur.length];
        double longueurTotalBarre=0;
        for (double nbUnite : nbUniteParJoueur) {
            //Cast de la valeur pour ne pas perdre les arrondis.
            pourcentageUniteParJoueur[i] = ((double) nbUniteParJoueur[i] / (double) nbTotalUniteEnJeu * 100);
            double longueurBarreJoueurEnPx = (pourcentageUniteParJoueur[i]*this.LONGUEUR_BARRE_EN_PX)/100;
            
            JPanel elementBarre = new JPanel();
            elementBarre.setVisible(true);
            elementBarre.setBackground(couleurs[i]);
            elementBarre.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
            //La barre est un JPanel dont la longueur est fixée par le calcul du pourcentage d'unité du joueur
            elementBarre.setPreferredSize(new Dimension((int)longueurBarreJoueurEnPx, this.HAUTEUR_BARRE_EN_PX));
            
            this.add(elementBarre);
            
            i++;
        }


    }
}
