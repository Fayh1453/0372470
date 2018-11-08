package ca.cours5b5.lucroy.controleurs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.lucroy.controleurs.interfaces.Fournisseur;
import ca.cours5b5.lucroy.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.lucroy.donnees.Serveur;
import ca.cours5b5.lucroy.donnees.SourceDeDonnees;
import ca.cours5b5.lucroy.exceptions.ErreurModele;
import ca.cours5b5.lucroy.modeles.MParametres;
import ca.cours5b5.lucroy.modeles.MParametresPartie;
import ca.cours5b5.lucroy.modeles.MPartie;
import ca.cours5b5.lucroy.modeles.Modele;
import ca.cours5b5.lucroy.donnees.Disque;
import ca.cours5b5.lucroy.usagers.UsagerCourant;

public final class ControleurModeles {

    private ControleurModeles(){}

    private static Map<String, Modele> modelesEnMemoire;

    private static SourceDeDonnees[] sequenceDeChargement;

    private static List<SourceDeDonnees> listeDeSauvegardes;

    static void getModele(String nomModele, ListenerGetModele listerGetModele){


        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null){

            creerModeleEtChargerDonnees(nomModele, listerGetModele);

        }else{
            reagirAuModele();
        }





    }

    private static void creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele {

    }

    private static void creerModeleEtChargerDonnees(final String nomModele, final ListenerGetModele listernerGetModele) {

    }

    private static void chargerDonnees(Modele modele, String nomModele, ListenerGetModele listenerGetModele) {

    }

    private static void chargementViaSequence(Modele modele, String cheminDeSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante) {

    }

    private static void chargementViaSourceCouranteOuSuivante(final Modele modele, final String cheminDeSauvegarde, final ListenerGetModele listenerGetModele, final int indiceSourceCourante) {

    }

    private static void termineChargementAvecDonnees(Map<String, Object> objetJson, Modele modele,ListenerGetModele listenerGetModele) {

    }

    private static void terminerChargement(Modele modele, ListenerGetModele listernerGetModele) {

    }

    private static void chargementViaSourceSuivante(Modele modele, String cheminDeSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante ) {

    }



































    static {

        modelesEnMemoire = new HashMap<>();

        listeDeSauvegardes = new ArrayList<>();
        listeDeSauvegardes.add(Disque.getInstance());
        listeDeSauvegardes.add(Serveur.getInstance());


    }

    public static void setSequenceDeChargement(SourceDeDonnees... sequenceDeChargement){

        ControleurModeles.sequenceDeChargement = sequenceDeChargement;

    }

    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            Map<String, Object> objetJson = modele.enObjetJson();

            if (sourceDeDonnees == Serveur.getInstance()){
                Serveur.getInstance().sauvegarderModele(getCheminSauvegarde(nomModele), objetJson);
            }else{

                sourceDeDonnees.sauvegarderModele(nomModele, objetJson);
            }



        }
    }

    static Modele getModele(final String nomModele){

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null){

            modele =  chargerViaSequenceDeChargement(nomModele);

        }

        return modele;
    }





    private static Modele chargerViaSequenceDeChargement(final String nomModele){

        Modele modele = creerModeleSelonNom(nomModele);

        modelesEnMemoire.put(nomModele, modele);

        for(SourceDeDonnees sourceDeDonnees : sequenceDeChargement){

            Map<String, Object> objetJson = sourceDeDonnees.chargerModele(nomModele);

            if(objetJson != null){

                modele.aPartirObjetJson(objetJson);
                break;

            }

        }

        return modele;
    }

    public static void sauvegarderModele(String nomModele) throws ErreurModele {

        for(SourceDeDonnees source : listeDeSauvegardes){

            sauvegarderModeleDansCetteSource(nomModele, source);

        }

    }


    private static Modele creerModeleSelonNom(String nomModele) throws ErreurModele {

        if(nomModele.equals(MParametres.class.getSimpleName())){

            return new MParametres();

        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            MParametres mParametres = (MParametres) getModele(MParametres.class.getSimpleName());

            return new MPartie(mParametres.getParametresPartie().cloner());

        }else{

            throw new ErreurModele("Modèle inconnu: " + nomModele);

        }
    }

    public static void detruireModele(String nomModele) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            modelesEnMemoire.remove(nomModele);

            ControleurObservation.detruireObservation(modele);

            if(modele instanceof Fournisseur){

                ControleurAction.oublierFournisseur((Fournisseur) modele);

            }
        }
    }

    private static String getCheminSauvegarde(String nomModele) {

        return nomModele + "/"+ UsagerCourant.getId();

    }

}
