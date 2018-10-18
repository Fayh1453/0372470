package ca.cours5b5.lucroy.modeles;

import java.util.Map;

import ca.cours5b5.lucroy.exceptions.ErreurDeSerialisation;
import ca.cours5b5.lucroy.serialisation.AttributSerialisable;

public class MParametresPartie extends Modele {


    @AttributSerialisable
    public Integer hauteur;
    protected final String __hauteur = "hauteur";


    @AttributSerialisable
    public Integer largeur;
    protected final String __largeur = "largeur";


    @AttributSerialisable
    public Integer pourGagner;
    protected final String __pourGagner = "pourGagner";

    public MParametresPartie(int hauteur, int largeur, int pourGagner) {
        this.setHauteur(hauteur);
        this.setLargeur(largeur);
        this.setPourGagner(pourGagner);
    }
    public static MParametresPartie aPartirMParametres (MParametres mParametres){


        return new MParametresPartie(mParametres.getParametresPartie().getHauteur(),
                mParametres.getParametresPartie().getLargeur(),
                mParametres.getParametresPartie().getPourGagner());
    }

    public MParametresPartie(){}

    public Integer getHauteur() {
        return hauteur;
    }

    public Integer getLargeur() {
        return largeur;
    }

    public Integer getPourGagner() {
        return pourGagner;
    }

    public void setHauteur(Integer hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(Integer largeur) {
        this.largeur = largeur;
    }

    public void setPourGagner(Integer pourGagner) {
        this.pourGagner = pourGagner;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurDeSerialisation{

    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurDeSerialisation {
        return null;
    }
}
