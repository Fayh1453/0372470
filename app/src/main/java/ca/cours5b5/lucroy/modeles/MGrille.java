package ca.cours5b5.lucroy.modeles;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import ca.cours5b5.lucroy.global.GCouleur;

public class MGrille extends Modele {
    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {

    }

    @Override
    public Map<String, Object> enObjetJson() {
        return null;
    }

    private List<MColonne> colonnes;

    public MGrille(int largeur) {
        initialiserColonnes(largeur);
    }


    public List<MColonne> getColonnes() {
        return colonnes;
    }

    public void placerJeton(int colonne, GCouleur couleur) {
        this.colonnes.get(colonne).placerJeton(couleur);
    }

    private void initialiserColonnes(int largeur) {
        colonnes = new ArrayList<>();
        for (int i = 0; i < largeur; ++i) {
            colonnes.add(new MColonne());
        }
    }
}
