package ca.cours5b5.lucroy.controleurs;

import ca.cours5b5.lucroy.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.lucroy.donnees.Serveur;
import ca.cours5b5.lucroy.global.GCommande;
import ca.cours5b5.lucroy.global.GConstantes;
import ca.cours5b5.lucroy.modeles.MPartieReseau;
import ca.cours5b5.lucroy.modeles.Modele;
import ca.cours5b5.lucroy.proxy.ProxyListe;
import ca.cours5b5.lucroy.usagers.UsagerCourant;

public final class ControleurPartieReseau {

    private static final ControleurPartieReseau instance = new ControleurPartieReseau();
    public static ControleurPartieReseau getInstance(){
        return instance;
    }

    private ProxyListe proxyEmettreCoups;
    private ProxyListe proxyRecevoirCoups;

    public void connecterAuServeur(){

        /*
         * Obtenir le modèle MPartieReseau
         * Obtenir le id du modèle (qui est l'id du joueur hôte)
         * Appeler connecterAuServeur(String idJouerHote)
         *
         */

        ControleurModeles.getModele(MPartieReseau.class.getSimpleName(), new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {

                MPartieReseau modelePartieReseau = (MPartieReseau) modele;

                connecterAuServeur(modelePartieReseau.idJoueurHote);

            }
        });

    }

    private void connecterAuServeur(String idJoueurHote) {

        /*
         * Connecter en tant que joueur hôte OU en tant qu'invité, selon le cas
         *
         * Connecter les deux proxy au serveur
         *
         * Ajouter l'action RECEVOIR_COUP_RESEAU au proxyRecevoirCoups
         *
         */

        if(UsagerCourant.getId().equals(idJoueurHote)){

            connecterEnTantQueJoueurHote(getCheminCoupsJoueurHote(idJoueurHote), getCheminCoupsJoueurInvite(idJoueurHote));

        } else {

            connecterEnTantQueJoueurInvite(getCheminCoupsJoueurHote(idJoueurHote), getCheminCoupsJoueurInvite(idJoueurHote));

        }

        proxyRecevoirCoups.setActionNouvelItem(GCommande.RECEVOIR_COUP_RESEAU);

        proxyRecevoirCoups.connecterAuServeur();

        proxyEmettreCoups.connecterAuServeur();


    }

    private void connecterEnTantQueJoueurHote(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {

        /*
         * Créer les proxy... avec les bons chemins
         *
         */

        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurHote);

        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurInvite);

    }

    private void connecterEnTantQueJoueurInvite(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {

        /*
         * Créer les proxy... avec les bons chemins
         *
         */

        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurHote);

        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurInvite);

    }

    public void deconnecterDuServeur(){

        /*
         * Détruire les valeurs du proxyEmettreCoups
         *
         * Déconnecter les deux proxy
         *
         */

        proxyEmettreCoups.deconnecterDuServeur();

        proxyRecevoirCoups.deconnecterDuServeur();

    }

    public void transmettreCoup(Integer idColonne){

        /*
         * Transmettre avec proxyEmettreCoups
         *
         */

        proxyEmettreCoups.ajouterValeur(idColonne);

    }

    private String getCheminCoupsJoueurInvite(String idJoueurHote){

        /*
         * Utiliser p.ex. la constante CLE_COUPS_JOUR_INVITE
         */

        return getCheminPartie(idJoueurHote) + '/' + GConstantes.CLE_COUPS_JOUEUR_INVITE;

    }

    private String getCheminCoupsJoueurHote(String idJoueurHote){

        /*
         * Utiliser p.ex. la constante CLE_COUPS_JOUR_HOTE
         */

        return getCheminPartie(idJoueurHote) + '/' + GConstantes.CLE_COUPS_JOUEUR_HOTE;

    }

    private String getCheminPartie(String idJoueurHote){

        /*
         * Le chemin contient l'id de la partie (id du joueur hote)
         */

        return MPartieReseau.class.getSimpleName() + '/' + idJoueurHote;

    }

    public void detruireSauvegardeServeur() {


        Serveur.getInstance().detruireSauvegarde(getCheminPartie(UsagerCourant.getId()));

    }

}
