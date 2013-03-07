/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Controleur;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Karim
 */
public class ExplorateurFichier extends JFrame {

    private JFileChooser explorateur;
    private Controleur controleur;
    private String typeExploration;

    public ExplorateurFichier(Controleur ctrl, String typeExplo) {
        super();
        this.controleur = ctrl;
        this.typeExploration = typeExplo;
        this.explorateur = new JFileChooser(new File("."));
        this.explorateur.setMultiSelectionEnabled(false);
        this.explorateur.setDialogTitle("Explorateur de fichier");



        if (typeExploration.equals("sauvegarder")) {
            this.explorateur.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int actionSauvegarder = this.explorateur.showSaveDialog(this);
            if (actionSauvegarder == JFileChooser.APPROVE_OPTION) {
                this.controleur.sauvegarderPartie(this.explorateur.getSelectedFile().getAbsolutePath()+".data");
            }

        } else if (typeExploration.equals("charger")) {
            this.explorateur.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int actionCharger = this.explorateur.showOpenDialog(this);
            if (actionCharger == JFileChooser.APPROVE_OPTION) {
                this.controleur.chargerPartie(ExplorateurFichier.this.explorateur.getSelectedFile().getAbsolutePath());
            }
        }
    }
}
