package ca.cours5b5.lucroy.donnees;

import com.google.android.gms.flags.impl.DataUtils;

import java.util.Map;


public abstract class SourceDeDonnees {

    public abstract Map<String, Object> chargerModele(final String cheminSauvegarde);

    public abstract void sauvegarderModele(final String cheminSauvegarde, final Map<String, Object> objetJson);

    public abstract void chargerModele(final String cheminSauvegarde, final ListenerChargement listenerChargement);


    protected String getNomModele(String cheminSauvegarde){

       String[] parts = cheminSauvegarde.split("/");
       return parts[0];
    }

}
