package ca.cours5b5.lucroy.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.controleurs.Action;
import ca.cours5b5.lucroy.controleurs.ControleurAction;
import ca.cours5b5.lucroy.controleurs.ControleurObservation;
import ca.cours5b5.lucroy.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.lucroy.exceptions.ErreurObservation;
import ca.cours5b5.lucroy.global.GCommande;
import ca.cours5b5.lucroy.modeles.MParametres;
import ca.cours5b5.lucroy.modeles.Modele;


public class VParametres extends Vue {



    private Spinner spinnerHauteur;
    private Spinner spinnerLargeur;
    private Spinner spinnerPourGagner;

    private Button boutonEffacerPartieCourante;


    public VParametres(Context context) {
        super(context);
    }

    public VParametres(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VParametres(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        initialiser();

        installerListeners();

        installerObservateur();

    }

    private void initialiser(){
        spinnerHauteur = findViewById(R.id.spinner_hauteur);
        spinnerLargeur = findViewById(R.id.spinner_largeur);
        spinnerPourGagner = findViewById(R.id.spinner_pour_gagner);

        boutonEffacerPartieCourante = findViewById(R.id.bouton_effacer_partie);

        initialiserSpinner(spinnerHauteur);
        initialiserSpinner(spinnerLargeur);
        initialiserSpinner(spinnerPourGagner);

    }

    private void initialiserSpinner(Spinner spinner){
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void installerListeners() {
        installerListenerHauteur();
        installerListenerLargeur();
        installerListenerPourGagner();
        installerListenerEffacer();
    }


    private void installerListenerHauteur(){

        final Action actionHauteur = ControleurAction.demanderAction(GCommande.CHOISIR_HAUTEUR);

        spinnerHauteur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int leChoix = (int) parent.getAdapter().getItem(position);

                actionHauteur.setArguments(leChoix);
                actionHauteur.executerDesQuePossible();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void installerListenerLargeur(){

        final Action actionLargeur = ControleurAction.demanderAction(GCommande.CHOISIR_LARGEUR);

        spinnerLargeur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int leChoix = (int) parent.getAdapter().getItem(position);

                actionLargeur.setArguments(leChoix);
                actionLargeur.executerDesQuePossible();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void installerListenerPourGagner(){

        final Action actionPourGagner = ControleurAction.demanderAction(GCommande.CHOISIR_POUR_GAGNER);


        spinnerPourGagner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int leChoix = (int) parent.getAdapter().getItem(position);

                actionPourGagner.setArguments(leChoix);
                actionPourGagner.executerDesQuePossible();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void installerListenerEffacer() {

        final Action actionEffacer = ControleurAction.demanderAction(GCommande.EFFACER);


        boutonEffacerPartieCourante.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                actionEffacer.executerDesQuePossible();

            }
        });

    }

    private void installerObservateur() {

        ControleurObservation.observerModele(MParametres.class.getSimpleName(),
                new ListenerObservateur() {

                    @Override
                    public void reagirChangementAuModele(Modele modele) {
                        observerParametres(modele);
                    }
                });

    }

    private void observerParametres(Modele modele){
        try{

            MParametres mParametres = (MParametres) modele;

            afficherLesChoix(mParametres);


        }catch (ClassCastException e){

            throw new ErreurObservation(e);

        }
    }

    private void afficherLesChoix(MParametres mParametres){
        afficherChoixHauteur(mParametres);
        afficherChoixLargeur(mParametres);
        afficherChoixPourGagner(mParametres);
    }

    private void afficherChoixHauteur(MParametres mParametres){
        mettreAJourSpinner(spinnerHauteur,
                mParametres.getChoixHauteur(),
                mParametres.getParametresPartie().getHauteur());
    }

    private void afficherChoixLargeur(MParametres mParametres){
        mettreAJourSpinner(spinnerLargeur,
                mParametres.getChoixLargeur(),
                mParametres.getParametresPartie().getLargeur());
    }

    private void afficherChoixPourGagner(MParametres mParametres){
        mettreAJourSpinner(spinnerPourGagner,
                mParametres.getChoixPourGagner(),
                mParametres.getParametresPartie().getPourGagner());
    }

    private void mettreAJourSpinner(Spinner spinner, List<Integer> choix, int selectionCourante){
        ArrayAdapter<Integer> adapter = (ArrayAdapter<Integer>) spinner.getAdapter();
        adapter.clear();

        for(int i=0; i < choix.size(); i++){
            int leChoix = choix.get(i);
            adapter.add(leChoix);

            if(leChoix == selectionCourante){
                spinner.setSelection(i);
            }
        }
    }
}
