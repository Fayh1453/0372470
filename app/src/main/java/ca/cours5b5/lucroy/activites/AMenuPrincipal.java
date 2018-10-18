package ca.cours5b5.lucroy.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.lucroy.R;

public class AMenuPrincipal extends Activite {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);
        Log.d("Atelier04", AMenuPrincipal.class.getSimpleName() + "::onCreate");

        Button boutonParametres = this.findViewById(R.id.boutPara);

        boutonParametres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boutonPara();

            }
        });

        Button boutonJouer = this.findViewById(R.id.boutJouer);
        boutonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boutonJouer();

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Atelier04", AMenuPrincipal.class.getSimpleName() + "::onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Atelier04", AMenuPrincipal.class.getSimpleName() + "::onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("MaCle",10);
        Log.d("Atelier04", AMenuPrincipal.class.getSimpleName() + "::onSaveInstanceState");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Atelier04", AMenuPrincipal.class.getSimpleName() + "::onDestroy");
    }


    static {


        Log.d("Atelier04", AMenuPrincipal.class.getSimpleName() + "::static");
        Log.d("examen", AMenuPrincipal.class.getName() + "::static");


    }

    public void boutonPara() {

        Intent monIntention = new Intent(this, AParametres.class);

        this.startActivity(monIntention);

    }

    public void boutonJouer() {
        Log.d("Atelier06", "Bouton Jouer");
        Intent monIntention = new Intent(this, APartie.class);

        this.startActivity(monIntention);

    }


}
