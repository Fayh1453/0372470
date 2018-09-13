package ca.cours5b5.lucroy.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.lucroy.R;

public abstract class Activite extends AppCompatActivity {


@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);


}

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }




static{


}


}
