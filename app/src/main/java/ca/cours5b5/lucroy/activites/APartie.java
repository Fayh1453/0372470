package ca.cours5b5.lucroy.activites;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.Map;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.controleurs.ControleurObservation;
import ca.cours5b5.lucroy.modeles.MParametres;
import ca.cours5b5.lucroy.modeles.MPartie;
import ca.cours5b5.lucroy.serialisation.Jsonification;

public class APartie extends Activite {

    private String cle = MPartie.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie);

        if (savedInstanceState != null) {
            this.restaurerPartie(savedInstanceState);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        this.sauvegarderPartie(outState);
    }

    private void restaurerPartie(Bundle savedInstanceState) {
        String json = savedInstanceState.getString(this.cle);
        Map<String, Object> objetJson = Jsonification.enObjetJson(json);
        ControleurObservation.partie.aPartirObjetJson(objetJson);
    }

    private void sauvegarderPartie(Bundle outState) {
        Map<String, Object> objetJson = ControleurObservation.partie.enObjetJson();

        String json = Jsonification.enChaine(objetJson);

        outState.putString(this.cle, json);

    }
}