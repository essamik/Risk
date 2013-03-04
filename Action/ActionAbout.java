/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author Karim
 */
public class ActionAbout extends AbstractAction{


    public ActionAbout(String texte) {
        super(texte);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "RISK - The Java Game v.1.0 \n Développé par Karim Es-sami", "About", JOptionPane.INFORMATION_MESSAGE);
    }


}
