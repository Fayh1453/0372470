package ca.cours5b5.lucroy.donnees;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

import ca.cours5b5.lucroy.serialisation.Jsonification;

public class Serveur  extends SourceDeDonnees{


    private Serveur(){}

    private static final Serveur instance = new Serveur();


    public static Serveur getInstance(){

        return instance;

    }


    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson){

        Log.d("AAAAAAAAAAAAAAAAAAAAA", "BBBBBBBBBBBBBBBBBBB");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);

        reference.setValue(objetJson);
    }

    @Override
    public void chargerModele(String cheminSauvegarde, ListenerChargement listenerChargement) {

    }

    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde) {


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);

        return Jsonification.aPartirChaineJson(reference.toString());
    }

    //@Override
    public void detruireSauvegarde(String cheminSauvegarde){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);
        reference.removeValue();
    }


}