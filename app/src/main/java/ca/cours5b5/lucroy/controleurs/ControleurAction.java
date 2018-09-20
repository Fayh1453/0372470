package ca.cours5b5.lucroy.controleurs;

import java.util.Map;
import java.util.Set;

import ca.cours5b5.lucroy.controleurs.interfaces.Fournisseur;
import ca.cours5b5.lucroy.controleurs.interfaces.ListernerFournisseur;
import ca.cours5b5.lucroy.global.GCommande;

public class ControleurAction {

    private static Map<GCommande, Action> actions;

    private static Set<Action> fileAttenteExecution;


    static{





    }

    public static Action demanderAction(GCommande commande){

        return null;
    }

    public static void fournirAction(Fournisseur fournisseur, GCommande commande, ListernerFournisseur listernerFournisseur){}



    static void executerDesQuePossible(Action action){}


    private static void executerActionsExecutables(){}

    static boolean siActionExectuable(Action action){


        return false;
    }

    private static void lancerObservationSiApplicable(Action action){

    }

    private static synchronized void executerMaintenant (Action action){

    }

    private static void enregistrerFournisseur(Fournisseur fournisseur, GCommande commande, ListernerFournisseur listernerFournisseur){


    }

    private static void ajouterActionEnFileDAttente(Action action) {
    }

}
