package ca.cours5b5.lucroy.modeles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.lucroy.controleurs.ControleurAction;
import ca.cours5b5.lucroy.controleurs.interfaces.Fournisseur;
import ca.cours5b5.lucroy.controleurs.interfaces.ListernerFournisseur;
import ca.cours5b5.lucroy.controleurs.interfaces.ListernerObservateur;
import ca.cours5b5.lucroy.global.GCommande;
import ca.cours5b5.lucroy.global.GCouleur;
import ca.cours5b5.lucroy.serialisation.AttributSerialisable;

public class MPartie extends Modele implements Fournisseur{

    @AttributSerialisable
    public MParametresPartie parametres;
    private final String __parametres = "parametres";

    public List<Integer> coups;
    private final String __coups = "coups";
    private MGrille grille;

    private GCouleur couleurCourante;

    public MPartie(MParametresPartie parametres){
        this.parametres = parametres;
        this.coups = new ArrayList<>();

        this.grille = new MGrille(parametres.getLargeur());

        fournirActionPlacerJeton();
        initialiserCouleurCourante();
        this.parametres = parametres;
    }

    public MParametresPartie getParametres() {
        return this.parametres;
    }

    public MGrille getGrille() { return this.grille; }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {
        List<String> coupsJson = new ArrayList<>();

        for(Map.Entry<String, Object> entry : objetJson.entrySet()) {

            if (entry.getKey().equals(__parametres)) {

                this.parametres.aPartirObjetJson((Map<String, Object>) entry.getValue());

            } else if(entry.getKey().equals(__coups)) {

                coupsJson = (List<String>) entry.getValue();

            }

        }

        this.grille = new MGrille(parametres.getLargeur());

        this.initialiserCouleurCourante();

        rejouerLesCoups(listeCoupsAPartirJson(coupsJson));
    }

    @Override
    public Map<String, Object> enObjetJson() {
        Map<String, Object> map = new HashMap<>();

        map.put(__parametres, parametres.enObjetJson());
        map.put(__coups, listeCoupsEnObjetJson(coups));

        return map;
    }

    private void initialiserCouleurCourante() {

        couleurCourante = GCouleur.ROUGE;
    }


    private void prochaineCouleurCourante() {
        if (couleurCourante == GCouleur.JAUNE) { couleurCourante = GCouleur.ROUGE;}
        else if (couleurCourante == GCouleur.ROUGE) { couleurCourante = GCouleur.JAUNE;}
    }

    private void fournirActionPlacerJeton() {
        ControleurAction.fournirAction(this, GCommande.PLAY, new ListernerFournisseur() {
            @Override
            public void executer(Object... args) {
                if (peutExecute(args)) {
                    jouerCoup((int) args[0]);
                }
            }

            private boolean peutExecute(Object... args) {

                int nombreJetons = getGrille().getColonnes().get((int) args[0]).getJetons().size();

                return nombreJetons < parametres.getHauteur();
            }
        });
    }
    protected void jouerCoup(int colonne) {
        this.coups.add(colonne);
        this.grille.placerJeton(colonne, couleurCourante);
        prochaineCouleurCourante();
    }


    private void rejouerLesCoups(List<Integer> coupsARejouer) {
        coups.clear();
        for (int coup : coupsARejouer) {
            jouerCoup(coup);
        }


    }


    private List<Integer> listeCoupsAPartirJson(List<String> listeCoupsObjetJson) {
        final List<Integer> listInt = new ArrayList<>();
        for (String coup : listeCoupsObjetJson) {
            listInt.add(Integer.parseInt(coup));
        }

        return listInt;
    }

    private List<String> listeCoupsEnObjetJson(List<Integer> listeCoups) {
        final List<String> listJson = new ArrayList<>();

        for (int coup : listeCoups) {
            listJson.add(Integer.toString(coup));
        }

        return listJson;
    }

}
