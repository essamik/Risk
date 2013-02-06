/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

/**
 *
 * @author Karim
 */
class EtatDeployement extends EtatJeu {


    public EtatDeployement() {
        
    }

    @Override
    public boolean phaseDeployement(FenetreRisk vue, Zone maZone) {
        boolean aEteDeploye = false;
        if(vue!=null) {
            //vue.deployerUnites();
            aEteDeploye = true;
        }
        return aEteDeploye;
    }

    @Override
    public boolean phaseDeplacement(FenetreRisk vue, Zone maZone) {
        return false;
        
    }

    @Override
    public boolean phaseConfirmation(FenetreRisk vue) {
        return false;
        
    }

    @Override
    public boolean phaseInitialisation(FenetreRisk vue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
