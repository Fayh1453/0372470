package ca.cours5b5.lucroy.controleurs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.lucroy.controleurs.interfaces.Fournisseur;
import ca.cours5b5.lucroy.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.lucroy.donnees.ListenerChargement;
import ca.cours5b5.lucroy.donnees.Serveur;
import ca.cours5b5.lucroy.donnees.SourceDeDonnees;
import ca.cours5b5.lucroy.exceptions.ErreurModele;
import ca.cours5b5.lucroy.modeles.Identifiable;
import ca.cours5b5.lucroy.modeles.MParametres;
import ca.cours5b5.lucroy.modeles.MParametresPartie;
import ca.cours5b5.lucroy.modeles.MPartie;
import ca.cours5b5.lucroy.modeles.MPartieReseau;
import ca.cours5b5.lucroy.modeles.Modele;
import ca.cours5b5.lucroy.donnees.Disque;
import ca.cours5b5.lucroy.serialisation.Jsonification;
import ca.cours5b5.lucroy.usagers.UsagerCourant;


public final class ControleurModeles {

    private ControleurModeles(){}

    private static Map<String, Modele> modelesEnMemoire;

    private static SourceDeDonnees[] sequenceDeChargement;

    private static List<SourceDeDonnees> listeDeSauvegardes;

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

            sourceDeDonnees.sauvegarderModele(getCheminSauvegarde(nomModele), objetJson);

        }
    }

    static void getModele(String nomModele, ListenerGetModele listenerGetModele){

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null){

            creerModeleEtChargerDonnees(nomModele, listenerGetModele);

        } else {

            listenerGetModele.reagirAuModele(modele);

        }

    }


    public static void sauvegarderModele(String nomModele) throws ErreurModele {

        for(SourceDeDonnees source : listeDeSauvegardes){

            sauvegarderModeleDansCetteSource(nomModele, source);

        }

    }

    private static void creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele {

        if(nomModele.equals(MParametres.class.getSimpleName())){

            listenerGetModele.reagirAuModele(new MParametres());

        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres modeleParametre = (MParametres) modele;

                    MPartie modelePartie = new MPartie(modeleParametre.parametresPartie.cloner());

                    listenerGetModele.reagirAuModele(modelePartie);

                }

            });

        } else if(nomModele.equals(MPartieReseau.class.getSimpleName())){

            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres modeleParametre = (MParametres) modele;

                    MPartieReseau modelePartieReseau = new MPartieReseau(modeleParametre.parametresPartie.cloner());

                    listenerGetModele.reagirAuModele(modelePartieReseau);

                }

            });

        } else {


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

    static String getCheminSauvegarde(String nomModele){

        /*
         * Si le modèle est Identifiable, alors le chemin est nomModele/idModele
         * sinon, le chemin est nomModele/idUsager
         *
         */

        String cheminSauvegarde;

        Modele modele = modelesEnMemoire.get(nomModele);


        if(modele != null && modele instanceof Identifiable){

            cheminSauvegarde =  nomModele + "/" + ((Identifiable) modele).getId();

        } else {

            cheminSauvegarde =  nomModele + "/" + UsagerCourant.getId();

        }

        return cheminSauvegarde;

    }

    private static void creerModeleEtChargerDonnees(final String nomModele, final ListenerGetModele listenerGetModele){

        /*
         * Aussi: mémoriser le modèle dans modelesEnMemoire
         */

        creerModeleSelonNom(nomModele, new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {

                modelesEnMemoire.put(nomModele, modele);

                chargerDonnees(modele,nomModele,listenerGetModele);

            }
        });

    }

    private static void chargerDonnees(Modele modele, String nomModele, ListenerGetModele listenerGetModele){

        chargementViaSequence(modele, getCheminSauvegarde(nomModele),listenerGetModele,0 );

    }

    private static void chargementViaSequence(Modele modele, String cheminDeSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante){

        if(indiceSourceCourante >= sequenceDeChargement.length){

            terminerChargement(modele, listenerGetModele);

        } else {

            chargementViaSourceCouranteOuSuivante(modele,cheminDeSauvegarde,listenerGetModele,indiceSourceCourante);

        }

    }

    private static void chargementViaSourceCouranteOuSuivante(final Modele modele, final String cheminDeSauvegarde, final ListenerGetModele listenerGetModele, final int indiceSourceCourante){

        final SourceDeDonnees sourceCourante = sequenceDeChargement[indiceSourceCourante];

        sourceCourante.chargerModele(cheminDeSauvegarde, new ListenerChargement() {
            @Override
            public void reagirSucces(Map<String, Object> objetJson) {

                terminerChargementAvecDonnees(objetJson, modele,listenerGetModele);

            }

            @Override
            public void reagirErreur(Exception e) {

                chargementViaSourceSuivante(modele, cheminDeSauvegarde,listenerGetModele, indiceSourceCourante);

            }
        });

    }

    private static void terminerChargementAvecDonnees(Map<String, Object> objetJson, Modele modele, ListenerGetModele listenerGetModele){

        modele.aPartirObjetJson(objetJson);

        terminerChargement(modele, listenerGetModele);

    }

    private static void terminerChargement(Modele modele, ListenerGetModele listenerGetModele){

        listenerGetModele.reagirAuModele(modele);

    }

    private static void chargementViaSourceSuivante(Modele modele, String cheminDeSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante){

        chargementViaSequence(modele, cheminDeSauvegarde, listenerGetModele,indiceSourceCourante + 1);

    }

}