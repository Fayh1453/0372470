package ca.cours5b5.lucroy.controleurs;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.lucroy.controleurs.interfaces.ListernerFournisseur;
import ca.cours5b5.lucroy.controleurs.interfaces.ListernerObservateur;
import ca.cours5b5.lucroy.modeles.MParametres;
import ca.cours5b5.lucroy.modeles.MParametresPartie;
import ca.cours5b5.lucroy.modeles.MPartie;
import ca.cours5b5.lucroy.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListernerObservateur> observations;

    public static MPartie partie;

    static{
        observations = new HashMap<>();
    }

    public static void observerModele(String nomModele, final ListernerObservateur listernerObservateur){
        Log.d("BUG", "TeST(START)");

        if (nomModele.equals(MParametres.class.getSimpleName())) {
            Log.d("BUG", "IFONE)");

            MParametres mParametres = MParametres.getInstance();

            observations.put(mParametres, listernerObservateur);

            ControleurObservation.lancerObservation(mParametres);

        } else if (nomModele.equals(MPartie.class.getSimpleName())) {

            Log.d("BUG", "IFTWO)");

            partie = new MPartie(MParametresPartie.aPartirMParametres(MParametres.getInstance()));
            Log.d("BUG", "IF3)");

            observations.put(partie, listernerObservateur);
            Log.d("BUG", "IF4)");

            ControleurObservation.lancerObservation(partie);
            Log.d("BUG", "IF5)");

        }
        Log.d("BUG", "TeST(LAST)");
    }

    public static void lancerObservation(Modele modele) {

        ListernerObservateur observateur = observations.get(modele);

        if (observateur != null) {

            observateur.reagirNouveauModele(modele);

        }
    }
    public static void reagirObservation(Modele modele) {

        ListernerObservateur observateur = observations.get(modele);

        if (observateur != null) {

            observateur.reagirChangementAuModele(modele);

        }
    }
}
