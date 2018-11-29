package ca.cours5b5.lucroy.activites;

import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.controleurs.ControleurAction;
import ca.cours5b5.lucroy.controleurs.ControleurModeles;
import ca.cours5b5.lucroy.controleurs.interfaces.Fournisseur;
import ca.cours5b5.lucroy.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.lucroy.donnees.Serveur;
import ca.cours5b5.lucroy.global.GCommande;
import ca.cours5b5.lucroy.modeles.MPartie;

public class APartie extends Activite implements Fournisseur {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie);

        fournirActionTerminerPartie();
        Log.d("Examen", "Resume Create ");

    }


    private void fournirActionTerminerPartie() {

        ControleurAction.fournirAction(this,
                GCommande.TERMINER_PARTIE,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        quitterCetteActivite();

                    }
                });
    }


    @Override
    protected void onPause() {
        super.onPause();
        sauvegarderPartie();
        Log.d("Examen", "Pause Apartie ");
    }
    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Examen", "Resume Apartie ");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("Examen", "Destroy Apartie ");
    }




    protected void sauvegarderPartie(){
        ControleurModeles.sauvegarderModele(MPartie.class.getSimpleName());
    }



}