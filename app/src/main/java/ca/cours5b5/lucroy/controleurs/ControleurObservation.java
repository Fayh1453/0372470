package ca.cours5b5.lucroy.controleurs;

import java.util.Map;

import ca.cours5b5.lucroy.controleurs.interfaces.ListernerFournisseur;
import ca.cours5b5.lucroy.controleurs.interfaces.ListernerObservateur;
import ca.cours5b5.lucroy.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListernerObservateur> observations;

    static{

    }

    public static void observerModele(String nomModele, final ListernerObservateur listernerObservateur){

    }

    public static void lancerObservation(Modele modele){

    }
}
