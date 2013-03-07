package Vue;

import java.util.ArrayList;

/**
 * Représentation des Continents dans la vue. Contient une ensemble de Zone (Territoire).
 * @author Karim
 */
public class GroupeZone {
    private String nom;
    private ArrayList<Zone> listeZones;
    
    /**
     * Constructeur de Groupe de Zone en fonction du nom donné.
     * @param name : Le nom de la Groupe de Zone correspondant à un Continent.
     */
    public GroupeZone(String name) {
        this.nom = name;
        this.listeZones = new ArrayList<>();
    }
    
    /**
     * Ajoute une Zone dans le la liste des Zones du Groupe de Zone.
     * @param maZone : La zone à ajouter.
     * @return : True si la zone a correctement été ajoutée, false sinon.
     */
    public boolean ajouterZone(Zone maZone) {
        boolean aEteAjoute = false;
        if(maZone != null) {
            this.listeZones.add(maZone);
            aEteAjoute = true;
        }
        return aEteAjoute;
    }
    
    /**
     * Renvois la liste des Zones du Groupe de Zone.
     * @return : Une ArrayList des Zones du Groupe de Zone.
     */
    public ArrayList<Zone> rendListeZones() {
        ArrayList<Zone> listeZonesRisk = this.listeZones;
        return listeZonesRisk;
    }
    
    /**
     * Renvois le nom du Groupe de Zone.
     * @return : Le nom du Groupe de Zone.
     */
    public String rendNom() {
        return this.nom;
    }


}
