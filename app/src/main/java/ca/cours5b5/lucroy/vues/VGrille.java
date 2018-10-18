package ca.cours5b5.lucroy.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.lucroy.controleurs.Action;
import ca.cours5b5.lucroy.controleurs.ControleurAction;
import ca.cours5b5.lucroy.global.GCommande;
import ca.cours5b5.lucroy.global.GConstantes;
import ca.cours5b5.lucroy.global.GCouleur;
import ca.cours5b5.lucroy.modeles.MColonne;
import ca.cours5b5.lucroy.modeles.MGrille;
import ca.cours5b5.lucroy.modeles.MPartie;
import ca.cours5b5.lucroy.modeles.Modele;

public class VGrille extends GridLayout{
    public VGrille(Context context) {
        super(context);
    }

    public VGrille(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VGrille(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VGrille(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private int nombreRangees;

    private class Colonne extends ArrayList<VCase>{}

    private List<Colonne> colonnesDeCases;

    private List<VEntete> entetes;

    private VCase[][] cases;

    private Action action;


    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        this.initialiser();
    }

    private void initialiser(){

    }

    void creerGrille(int hauteur, int largeur){
        
        this.initialiserColonnes(largeur);

        this.ajouterEnTetes(largeur);

        this.initialiserTableauDeCases(hauteur, largeur);

        this.ajouterCases(hauteur, largeur);


    }

    private void initialiserTableauDeCases(int hauteur, int largeur) {
        Log.d("BUG", "TeST4");
        this.cases = new VCase[hauteur][largeur];
    }

    private void initialiserColonnes(int largeur){
        Log.d("BUG", "TeST5");

        this.colonnesDeCases = new ArrayList<>();
    }
//TODO A MODIFIER
    private void ajouterEnTetes(int largeur){
        Log.d("BUG", "TeST6");
        this.entetes = new ArrayList<>();
        VEntete entete;
        for (int colonne = 0; colonne < largeur; colonne++) {
            entete = new VEntete(this.getContext(), colonne);
            installerListernerEntete(entete, colonne);
            this.entetes.add(entete);
            this.addView(entete, this.getMiseEnPageEntete(colonne));
        }
    }

    private LayoutParams getMiseEnPageEntete(int colonne){
        Log.d("BUG", "TeST7");

        Spec specRangee = GridLayout.spec(GConstantes.ENTETE, GConstantes.ENTETE_RANGE);
        Spec specColonne = GridLayout.spec(colonne, GConstantes.ENTETE_COLONNE);
        LayoutParams params = new LayoutParams(specRangee, specColonne);
        params.width = 0;
        params.height = 0;
        params.setGravity(Gravity.FILL);

        params.rightMargin = GConstantes.MARGE;
        params.leftMargin = GConstantes.MARGE;

        return params;
    }

    private void ajouterCases (int hauteur, int largeur){

        for (int i = 0; i < hauteur; i++) {
            this.colonnesDeCases.add(new Colonne());

        }

        VCase vCase;

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                vCase = new VCase(this.getContext(), i, j);
                this.colonnesDeCases.get(j).add(vCase);
                this.addView(vCase, this.getMiseEnPageCase((hauteur - i) - 1, j));
            }
        }
        Log.d("BUG", "Buf");
    }

    private LayoutParams getMiseEnPageCase(int rangee, int colonne){

        Spec specRangee = GridLayout.spec(GConstantes.ENTETE, (float) GConstantes.ENTETE_RANGE);
        Spec specColonne = GridLayout.spec(colonne, (float) GConstantes.ENTETE_COLONNE);
        LayoutParams params = new LayoutParams(specRangee, specColonne);
        params.width = 0;
        params.height = 0;
        params.setGravity(Gravity.FILL);

        params.rightMargin = GConstantes.MARGE;
        params.leftMargin = GConstantes.MARGE;
        return params;
    }

    void afficherJetons(MGrille grille) {
        Log.d("BUG", "TeST10");
        List<MColonne> colonnes = grille.getColonnes();

        for (int i = 0; i < colonnes.size(); i++) {
            for (int j = 0; j < colonnes.get(i).getJetons().size(); j++) {
                afficherJeton(i, j, colonnes.get(i).getJetons().get(j));
            }
        }

    }

    private void afficherJeton(int colonne, int rangee, GCouleur jeton) {
        Log.d("BUG", "TeST11");
        colonnesDeCases.get(colonne).get(rangee).afficherJeton(jeton);
    }


    private void installerListernerEntete(VEntete entete, final int colonne) {

        entete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                demanderActionEntete();

                action.setArguments(colonne);

                action.executerDesQuePossible();
            }
        });

    }



    private void demanderActionEntete() {

        action =  ControleurAction.demanderAction(GCommande.PLAY);

    }

}
