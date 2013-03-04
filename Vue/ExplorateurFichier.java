/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Controleur;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Karim
 */
public class ExplorateurFichier extends JFrame {

    private JTree arbre;
    private DefaultMutableTreeNode racine;
    protected Controleur controleur;
    protected String typeExploration;

    public ExplorateurFichier(Controleur ctrl, String typeExplo) {
        super();
        //this.setPreferredSize(new Dimension(1000, 520));
        this.setSize(1000, 520);
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Explorateur de fichiers");
        //On invoque la méthode de construction de l'arbre
        this.controleur = ctrl;
        this.typeExploration = typeExplo;
        listRoot();

        this.setVisible(true);
    }

    private void listRoot() {
        this.racine = new DefaultMutableTreeNode();
        int count = 0;
        for (File file : File.listRoots()) {
            DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.getAbsolutePath());
            try {
                for (File nom : file.listFiles()) {
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName() + "\\");
                    lecteur.add(this.listFile(nom, node));
                }
            } catch (NullPointerException e) {
            }

            this.racine.add(lecteur);
        }
        //Nous créons, avec notre hiérarchie, un arbre
        arbre = new JTree(this.racine);
        arbre.setRootVisible(false);
        arbre.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent event) {
                if (arbre.getLastSelectedPathComponent() != null) {
                    //La méthode getPath retourne un objet TreePath
                    if(typeExploration.equals("sauvegarder"))
                    ExplorateurFichier.this.controleur.sauvegarderPartie(getAbsolutePath(event.getPath()));
                    else if(typeExploration.equals("charger"))
                    ExplorateurFichier.this.controleur.chargerPartie(getAbsolutePath(event.getPath()));
                }
            }

            private String getAbsolutePath(TreePath treePath) {
                String str = "";
                //On balaie le contenu de l'objet TreePath
                for (Object name : treePath.getPath()) {
                    //Si l'objet a un nom, on l'ajoute au chemin
                    if (name.toString() != null) {
                        str += name.toString();
                    }
                }
                return str;
            }
        });
        //Que nous plaçons sur le ContentPane de notre JFrame à l'aide d'un scroll 
        this.getContentPane().add(new JScrollPane(arbre));
    }

    private DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node) {
        int count = 0;
        if (file.isFile()) {
            return new DefaultMutableTreeNode(file.getName());
        } else {
            File[] list = file.listFiles();
            if (list == null) {
                return new DefaultMutableTreeNode(file.getName());
            }

            for (File nom : list) {
                count++;
                //Pas plus de 5 enfants par noeud
                if (count < 5) {
                    DefaultMutableTreeNode subNode;
                    if (nom.isDirectory()) {
                        subNode = new DefaultMutableTreeNode(nom.getName() + "\\");
                        node.add(this.listFile(nom, subNode));
                    } else {
                        subNode = new DefaultMutableTreeNode(nom.getName());
                    }
                    node.add(subNode);
                }
            }
            return node;
        }
    }
}
