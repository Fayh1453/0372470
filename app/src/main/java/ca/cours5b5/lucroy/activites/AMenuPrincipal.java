package ca.cours5b5.lucroy.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;


import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.controleurs.ControleurAction;
import ca.cours5b5.lucroy.controleurs.interfaces.Fournisseur;
import ca.cours5b5.lucroy.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.lucroy.global.GCommande;
import ca.cours5b5.lucroy.global.GConstantes;

public class AMenuPrincipal extends Activite implements Fournisseur {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        fournirActions();

    }

    private void fournirActions() {

        fournirActionOuvrirMenuParametres();

        fournirActionDemarrerPartie();

        fournirActionConnexion();





    }

    private void fournirActionOuvrirMenuParametres() {

        ControleurAction.fournirAction(this,
                GCommande.OUVRIR_MENU_PARAMETRES,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionParametres();

                    }
                });
    }

    private void fournirActionDemarrerPartie() {

        ControleurAction.fournirAction(this,
                GCommande.DEMARRER_PARTIE,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionPartie();

                    }
                });
    }

    private void fournirActionConnexion() {
        Log.d("Atelier11", "Fournir Action");
        ControleurAction.fournirAction(this,
                GCommande.CONNEXION,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionConnexion();

                    }
                });
    }

    private void transitionParametres(){

        Intent intentionParametres = new Intent(this, AParametres.class);
        startActivity(intentionParametres);

    }

    private void transitionPartie(){
        Intent intentionParametres = new Intent(this, APartie.class);
        startActivity(intentionParametres);

    }

    private void transitionConnexion(){
        Log.d("Atelier11", "Click!");

        List<AuthUI.IdpConfig> fournisseursDeConnexion = new ArrayList<>();

        fournisseursDeConnexion.add(new AuthUI.IdpConfig.GoogleBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.EmailBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.PhoneBuilder().build());

        Intent intentionConnexion = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(fournisseursDeConnexion)
                .build();
        Log.d("Atelier11", "Click!");
        this.startActivityForResult(intentionConnexion, GConstantes.CODE_CONNEXION);

    }

    @Override
    protected void onActivityResult(int resquestCode, int resultCode, Intent data) {

        if (resquestCode == GConstantes.CODE_CONNEXION){

            if (resultCode == RESULT_OK){

                Log.d("Atelier11", "OK!");


            }else{

                Log.d("Atelier11", "PAS OK!");


            }

        }

    }

}
