package ca.cours5b5.lucroy.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Map;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.modeles.MParametres;
import ca.cours5b5.lucroy.serialisation.Jsonification;

public class AParametres extends Activite {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        Log.d("Atelier04", AParametres.class.getSimpleName() + "::onCreate");
        if (this.getResources().getBoolean(R.bool.est_paysage))
            Log.d("MonEtiquette","Bonjour paysage!");
        else
            Log.d("MonEtiquette","Bonjour portrait!");

       /* if(savedInstanceState != null){

            String json = savedInstanceState.getString("MaCle");
            Map<String,Object> objetJson = Jsonification.enObjetJson(json);
            MParametres.aPartirObjetJson(objetJson);


        }*/


    }

    private void restaurerParametres(Bundle savedInstancesState){

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Atelier04", AParametres.class.getSimpleName() + "::onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Atelier04", AParametres.class.getSimpleName() + "::onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {




        /*<String,Object> objetJson = MParametres.enObjetJson();

        for(Map.Entry<String, Object> entry : objetJson.entrySet()){

            String cle = entry.getKey();
            Object valeur = entry.getValue();

        }

        String json = Jsonification.enChaine(objetJson);*/

        outState.putInt("MaCle",10);
        Log.d("Atelier04", AParametres.class.getSimpleName() + "::onSaveInstanceState");

    }

    private void sauvegarderParametres(Bundle outState){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Atelier04", AParametres.class.getSimpleName() + "::onDestroy");
    }


    static {


        Log.d("Atelier04", AParametres.class.getSimpleName() + "::static");



    }

}
