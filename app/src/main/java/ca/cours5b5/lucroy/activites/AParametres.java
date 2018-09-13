package ca.cours5b5.lucroy.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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





        Spinner hauteur = this.findViewById(R.id.spinHauteur);
        hauteur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String choixHauteur = (adapterView.getAdapter().getItem(i)).toString();
                MParametres.instance.setHauteur(Integer.parseInt(choixHauteur));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Spinner largeur = this.findViewById(R.id.spinLargeur);
        largeur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String choixLargeur = (adapterView.getAdapter().getItem(i)).toString();
                MParametres.instance.setLargeur(Integer.parseInt(choixLargeur));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Spinner pourGagner = this.findViewById(R.id.spinGagner);
        pourGagner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String choixGagner = (adapterView.getAdapter().getItem(i)).toString();
                MParametres.instance.setPourGagner(Integer.parseInt(choixGagner));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        if(savedInstanceState != null){

            String json = savedInstanceState.getString(this.getClass().getSimpleName());
            Map<String, Object> objetJson = Jsonification.enObjetJson(json);
            MParametres.instance.aPartirObjetJson(objetJson);
            Log.d("Atelier05",  AParametres.class.getSimpleName() + "::restaurerParametres, clé:" + MParametres.instance.getClass().getSimpleName());
            Log.d("Atelier05",  AParametres.class.getSimpleName() + "::restaurerParametres, json " + json);

        }


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
        Log.d("Atelier04", AParametres.class.getSimpleName() + "::onSaveInstanceState");
        super.onSaveInstanceState(outState);
        Map<String, Object> objetJson = MParametres.instance.enObjetJson();
        String json = Jsonification.enChaine(objetJson);
        outState.putString(this.getClass().getSimpleName(), json);
        Log.d("Atelier05",  AParametres.class.getSimpleName() + "::sauvegarderParametres, clé:" + MParametres.instance.getClass().getSimpleName());
        Log.d("Atelier05",  AParametres.class.getSimpleName() + "::sauvegarderParametres, json " + json);


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
