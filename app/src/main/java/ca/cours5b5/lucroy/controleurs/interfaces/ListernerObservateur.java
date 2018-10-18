package ca.cours5b5.lucroy.controleurs.interfaces;

import ca.cours5b5.lucroy.modeles.Modele;

public abstract class ListernerObservateur {




       public void reagirNouveauModele(Modele modele) {

           this.reagirChangementAuModele(modele);

       }




   public abstract void reagirChangementAuModele(Modele modele);



}
