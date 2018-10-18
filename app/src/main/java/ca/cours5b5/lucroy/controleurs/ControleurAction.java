package ca.cours5b5.lucroy.controleurs;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import ca.cours5b5.lucroy.controleurs.interfaces.Fournisseur;
import ca.cours5b5.lucroy.controleurs.interfaces.ListernerFournisseur;
import ca.cours5b5.lucroy.global.GCommande;
import ca.cours5b5.lucroy.modeles.Modele;

public class ControleurAction {

    private static Map<GCommande, Action> actions;

    private static Set<Action> fileAttenteExecution;


    static{


        actions = new HashMap<>();
        for (GCommande commande : GCommande.values()) {
            actions.put(commande, new Action());
        }

        fileAttenteExecution = new LinkedHashSet<>();


    }

    public static Action demanderAction(GCommande commande){

        return actions.get(commande);
    }

    public static void fournirAction(Fournisseur fournisseur, GCommande commande, ListernerFournisseur listernerFournisseur){

        enregistrerFournisseur(fournisseur, commande, listernerFournisseur);

        executerActionsExecutables();
    }



    static void executerDesQuePossible(Action action){
        ajouterActionEnFileDAttente(action);
        executerActionsExecutables();
    }
    static boolean siActionExecutable(Action action) {
        return action.listernerFournisseur != null;
    }

    private static void executerActionsExecutables(){
        for (Action action : fileAttenteExecution) {
            if(siActionExecutable(action)) {
                fileAttenteExecution.remove(action);
                executerMaintenant(action);
               // lancerObservationSiApplicable(action);
            }
        }
    }

    static boolean siActionExectuable(Action action){


        return action.listernerFournisseur != null;
    }

   /* private static void lancerObservationSiApplicable(Action action){
        if (action.fournisseur instanceof Modele) {
                  ControleurObservation.lancerObservation((Modele) action.fournisseur);
            ControleurObservation.reagirObservation((Modele) action.fournisseur);
        }
    }*/

    private static synchronized void executerMaintenant (Action action){
        action.listernerFournisseur.executer(action.args);

    }

    private static void enregistrerFournisseur(Fournisseur fournisseur, GCommande commande, ListernerFournisseur listernerFournisseur){

        Action action = actions.get(commande);
        action.fournisseur = fournisseur;
        action.listernerFournisseur = listernerFournisseur;
    }

    private static void ajouterActionEnFileDAttente(Action action) {
        fileAttenteExecution.add(action.cloner());
    }

}
