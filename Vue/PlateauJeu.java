package Vue;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Surface interactive de jeu contenant l'interface de carte du risk avec ses
 * zones. Les zones sont automatiquement générées en fonction de leur
 * coordonnées X et Y.
 *
 * @author Karim
 */
public class PlateauJeu extends JPanel implements MouseListener {

    private ArrayList<GroupeZone> listeContinents;
    private FenetreRisk vue;

    /**
     * Constructeur de plateau de jeu interactif permettant de joueur au Risk.
     *
     * @param maVue : La vue principale du jeu.
     */
    public PlateauJeu(FenetreRisk maVue) {
        super();
        this.vue = maVue;
        this.addMouseListener(this);
        this.listeContinents = new ArrayList<>();
    }

    /**
     * Dessine un par un les zones fournies afin de créer une carte en 2D du jeu
     * Risk. Si une zone est définies comme étant cliquée, elle sera mise en
     * évidence ainsi que ses territoires voisins.
     *
     * @param g : L'objet Graphics servant à dessiner la carte.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; //Utilisation de Graphics2D afin d'utiliser la transparence, qui n'est pas dispo sous Graphics
        
        
        this.ajouterPonts(g2d);
        for (GroupeZone monGroupeZone : this.listeContinents) {
            for (Zone maZone : monGroupeZone.rendListeZones()) {
                g2d.setColor(maZone.getCouleur());
                g2d.fillPolygon(maZone);
                g2d.setColor(Color.BLACK);
                g2d.drawPolygon(maZone);

                g2d.setFont(new Font("arial", Font.BOLD, 16));
                if (maZone.rendNbUnite() > 0) {
                    g2d.setStroke(new BasicStroke(3));
                    g2d.setColor(maZone.getCouleur());
                    g2d.fillOval(maZone.rendPointCentral().x - 10, maZone.rendPointCentral().y - 15, 30, 25);
                    g2d.setColor(Color.BLACK);
                    g2d.drawOval(maZone.rendPointCentral().x - 10, maZone.rendPointCentral().y - 15, 30, 25);
                    g2d.setColor(Color.WHITE);

                }
                if (maZone.rendNbUnite() > 9) {
                    g2d.drawString("" + maZone.rendNbUnite(), maZone.rendPointCentral().x - 4, maZone.rendPointCentral().y + 4);
                } else if (maZone.rendNbUnite() > 0) {
                    g2d.drawString("" + maZone.rendNbUnite(), maZone.rendPointCentral().x + 1, maZone.rendPointCentral().y + 4);
                }
                g2d.setStroke(new BasicStroke(1));
            }
        }
        this.dessinerTerritoiresVoisins(g2d);
    }

    /**
     * Affiche les interactions possible entre le territoire sur lequel le
     * joueur à cliqué et ses territoires adjaçents Si aucun territoire n'a
     * préalablement été cliqué, cette méthode n'affiche rien.
     *
     * @param g : Graphics g, l'objet permettant de dessiner
     */
    private void dessinerTerritoiresVoisins(Graphics2D g2d) {
        for (GroupeZone monGroupeZone : this.listeContinents) {
            for (Zone maZone : monGroupeZone.rendListeZones()) {
                if (maZone.estClique()) {
                    //Changement de couleur de la zone cible ? 
                    for (Zone territoireVoisin : maZone.rendListeVoisins()) {
                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                        g2d.setColor(Color.WHITE);
                        g2d.fillPolygon(territoireVoisin); //On recréer une couche transparante par dessus le territoire
                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    }
                }
            }
        }
    }

    /**
     * Dessine les traits faisant office de pont maritime entre les zones.
     *
     * @param g : L'objet Graphics permettant de dessiner.
     */
    private void ajouterPonts(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.drawLine(285, 93, 328, 72); //Amerique-Groenland
        g.drawLine(332, 321, 408, 269); //AmeriqueSud-Afrique
        g.drawLine(584, 235, 591, 230); //Afrique-MoyenOrient
        g.drawLine(523, 176, 525, 162); //Egypte-Europe
        g.drawLine(486, 162, 504, 152); //Afrique-Italie
        g.drawLine(436, 167, 436, 164); //Maroc-Espagne
        g.drawLine(452, 117, 451, 109); //EuropeOuest - Angleterre
        g.drawLine(469, 106, 461, 102); //EuropeNord - Angleterre      
        g.drawLine(436, 79, 424, 72); //Angleterre-Islande
        g.drawLine(393, 64, 387, 51); //Islande - Groenland
        g.drawLine(434, 65, 486, 65); //Islande-Scandinavie
        g.drawLine(879, 355, 873, 340); //AustralieOuest-Indonesie
        g.drawLine(906, 346, 921, 331); //AustralieEst-NouvelleGuinee
        g.drawLine(898, 305, 875, 297); //NouvelleGuinee - Indonesie
        g.drawLine(837, 282, 816, 257); //Indonesie-Siam
        g.drawLine(185, 214, 186, 205); //Cuba - EastCoast
        g.drawLine(170, 243, 188, 224); //Mexique - Cuba
        g.drawLine(215, 256, 208, 232); //Venezuela - Cuba
        g.drawLine(873, 171, 866, 168); //Japon - Mongolie
        g.drawLine(886, 133, 870, 130); //Japon - Kamchatka
        g.drawLine(20, 74, 0, 74); //Alaska - BordGauche
        g.drawLine(923, 74, this.getWidth(), 74); //Kamchatka - BordDroite (extensible avec la fenetre)
    }

    /**
     * Lance le dessin de la carte en fonction des Zones fournies.
     *
     * @param mesGroupesZones : La liste de Groupe de Zone à dessiner.
     */
    public void creerCarte(ArrayList<GroupeZone> mesGroupesZones) {
        this.listeContinents = mesGroupesZones;
        this.repaint();
    }

    /**
     * Renvois la liste des Groupes de Zones.
     *
     * @return : La liste des Groupes de Zone.
     */
    public ArrayList<GroupeZone> rendListeGroupeZone() {
        ArrayList<GroupeZone> mesContinents = this.listeContinents;
        return mesContinents;
    }
    
    /**
     * Remet à 0 la liste des continents.
     */
    public void reinitialiserListeContinents() {
        this.listeContinents = new ArrayList<>();
    }
    
    /**
     * Gère les interactions du joueur sur la carte. Cherche si le point cliqué
     * correspondant aux coordonnées d'une zone et lance l'action correspondant.
     *
     * @param e : L'évênement lié au clic de la souris.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        for (GroupeZone monJContinent : this.listeContinents) {
            for (Zone maZone : monJContinent.rendListeZones()) {
                if (maZone.contains(e.getX(), e.getY())) {
                    this.vue.interactionZone(maZone);
                }
            }
        }
        this.repaint();
    }



    /**
     * Interaction de la souris non utilisé
     *
     * @param e : L'évênement lié à la souris
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Interaction de la souris non utilisé
     *
     * @param e : L'évênement lié à la souris
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Interaction de la souris non utilisé
     *
     * @param e : L'évênement lié à la souris
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    /**
     * Interaction de la souris non utilisé
     *
     * @param e : L'évênement lié à la souris
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
