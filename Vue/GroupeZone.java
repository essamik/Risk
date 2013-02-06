/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import java.util.ArrayList;

/**
 *
 * @author Karim
 */
public class GroupeZone {
    private String nom;
    private ArrayList<Zone> listeZones;
    
    public GroupeZone(String name) {
        this.nom = name;
        this.listeZones = new ArrayList<>();
    }
    
    public boolean ajouterZone(Zone maZone) {
        boolean aEteAjoute = false;
        if(maZone != null) {
            this.listeZones.add(maZone);
            aEteAjoute = true;
        }
        return aEteAjoute;
    }
    
    public ArrayList<Zone> rendListeZones() {
        ArrayList<Zone> listeZonesRisk = this.listeZones;
        return listeZonesRisk;
    }
    
    public String rendNom() {
        return this.nom;
    }


}
