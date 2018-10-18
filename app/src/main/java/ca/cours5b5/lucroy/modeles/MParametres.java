package ca.cours5b5.lucroy.modeles;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.activites.AParametres;
import ca.cours5b5.lucroy.global.GConstantes;
import ca.cours5b5.lucroy.serialisation.AttributSerialisable;
import ca.cours5b5.lucroy.vues.VParametres;

public class MParametres extends Modele{


    public  static MParametres instance = new MParametres();

    @AttributSerialisable
    public MParametresPartie parametresPartie;
    private String __parametresPartie = "parametresPartie";

    @AttributSerialisable
    public Integer hauteur = GConstantes.HDEF;
    private final String __hauteur ="hauteur";

    @AttributSerialisable
    public Integer largeur = GConstantes.LDEF;
    private final String __largeur ="largeur";

    @AttributSerialisable
    public Integer pourGagner = GConstantes.GDEF;
    private final String __pourGagner ="pourGagner";

    private List<Integer> choixH;
    private List<Integer> choixL;
    private List<Integer> choixG;

    public MParametres(){
        listeSpinner();


        this.parametresPartie = new MParametresPartie(GConstantes.HDEF, GConstantes.LDEF, GConstantes.GDEF);
    }

    public List<Integer> getChoixHauteur(){


        return choixH;
    }

    public List<Integer> getChoixLargeur()
    {

        return choixL;
    }

    public List<Integer> getChoixPourGagner(){

       return choixG;
    }

    public MParametresPartie getParametresPartie() {
        return this.parametresPartie;
    }

    public Integer getHauteur(){
        return this.hauteur;
    }

    public Integer getLargeur(){
        return this.largeur;
    }

    public Integer getPourGagner(){
        return this.pourGagner;
    }

    public void setHauteur(int hauteur){
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur){
        this.largeur = largeur;
    }

    public void setPourGagner(int pourGagner){
        this.pourGagner = pourGagner;
    }

    public void setParametresPartie(MParametresPartie parametresPartie) { this.parametresPartie = parametresPartie;}



    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson){

        for(Map.Entry<String, Object> entry : objetJson.entrySet()){
            String cle = entry.getKey();
            Object valeur = entry.getValue();

            if(cle.equals( __hauteur)){
                setHauteur(Integer.valueOf((String)valeur));
            } else if (cle.equals( __largeur)){
                setLargeur(Integer.valueOf((String)valeur));
            } else if (cle.equals(__pourGagner)){
                setPourGagner(Integer.valueOf((String)valeur));
            }
        }

    }

    @Override
    public Map<String, Object> enObjetJson(){

        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__hauteur, hauteur.toString());
        objetJson.put(__largeur, largeur.toString());
        objetJson.put(__pourGagner, pourGagner.toString());

        return  objetJson;

    }
    public static MParametres getInstance() {

        return instance;
    }

    private void listeSpinner(){
        hauteur = GConstantes.HDEF;
        largeur = GConstantes.LDEF;
        pourGagner = GConstantes.GDEF;
        genererListeChoixHauteur();
        genererListeChoixLargeur();
        genererListeChoixPourGagner();
    }

    private List<Integer> listeSpinner(int min, int max){

        List<Integer> liste = new ArrayList<>();
        for(int indice = min; indice <= max; ++indice){
            liste.add(indice);
        }
        return liste;
    }

    private void genererListeChoixHauteur(){
        choixH = listeSpinner(GConstantes.HMIN, GConstantes.HMAX);
    }

    private void genererListeChoixLargeur(){
        choixL = listeSpinner(GConstantes.LMIN, GConstantes.LMAX);
    }

    private void genererListeChoixPourGagner(){
        choixG = listeSpinner(GConstantes.GMIN, GConstantes.GMAX);
    }
}
