package ca.cours5b5.lucroy.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.lucroy.R;

public class AParametres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        Log.d("MonEtiquette",this.getResources().getString(R.string.identifiant));
        if (this.getResources().getBoolean(R.bool.est_paysage))
            Log.d("MonEtiquette","Bonjour paysage!");
        else
            Log.d("MonEtiquette","Bonjour portrait!");
    }
}
