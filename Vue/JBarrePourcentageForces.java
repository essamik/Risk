/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Karim
 */
public class JBarrePourcentageForces extends JPanel {

    private final int LONGUEUR_BARRE_EN_PX = 1270;
    private final int HAUTEUR_BARRE_EN_PX = 20;

    public JBarrePourcentageForces(int nbTotalUniteEnJeu, int[] nbUniteParJoueur, Color[] couleurs) {
        super();
        this.setPreferredSize(new Dimension(LONGUEUR_BARRE_EN_PX, HAUTEUR_BARRE_EN_PX));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        int i = 0;
        double[] pourcentageUniteParJoueur = new double[nbUniteParJoueur.length];
        double longueurTotalBarre=0;
        for (double nbUnite : nbUniteParJoueur) {
            pourcentageUniteParJoueur[i] = ((double) nbUniteParJoueur[i] / (double) nbTotalUniteEnJeu * 100);
            double longueurBarreJoueurEnPx = (pourcentageUniteParJoueur[i]*this.LONGUEUR_BARRE_EN_PX)/100;
//            longueurTotalBarre+=longueurBarreJoueurEnPx;
            JPanel elementBarre = new JPanel();
            elementBarre.setVisible(true);
            elementBarre.setBackground(couleurs[i]);
            elementBarre.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));

//            if(i == nbUniteParJoueur.length-1) {
                //Pour le dernier joueur, on remplis l'espace pouvant être vide
//
//                if(longueurTotalBarre < 1280) {
//                    double espaceVide = 1280 - longueurTotalBarre;
//                    //System.out.println("Espace vide : " +espaceVide);
//                    double longueurCorrigee = (longueurBarreJoueurEnPx+espaceVide);
//                    System.out.println(longueurCorrigee);
//                    elementBarre.setPreferredSize(new Dimension((int)(longueurCorrigee), this.HAUTEUR_BARRE_EN_PX));
//                }
//                
//            } else {
            elementBarre.setPreferredSize(new Dimension((int)longueurBarreJoueurEnPx, this.HAUTEUR_BARRE_EN_PX));
//            }

            
            this.add(elementBarre);
            
            i++;
        }


    }
}
