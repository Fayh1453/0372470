package ca.cours5b5.lucroy.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import ca.cours5b5.lucroy.controleurs.ControleurObservation;
import ca.cours5b5.lucroy.controleurs.interfaces.ListernerObservateur;
import ca.cours5b5.lucroy.exceptions.ErreurObservation;
import ca.cours5b5.lucroy.modeles.MPartie;
import ca.cours5b5.lucroy.modeles.Modele;
import ca.cours5b5.lucroy.R;

public class VPartie extends Vue {

    private VGrille grille;

    public VPartie(Context context) {
        super(context);
    }

    public VPartie(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VGrille getGrille() {
        return grille;
    }

    private MPartie getPartie(Modele modele) {

        Log.d("BUG", "TeSTA");
        try {
            return (MPartie) modele;
        } catch (ErreurObservation e) {
            throw new ErreurObservation("Modele A");
        }
    }

    @Override
    protected  void onFinishInflate(){
        super.onFinishInflate();
        this.initialiser();
    }

    private void initialiser(){
        this.grille = findViewById(R.id.GridLayout_partie);

        this.observerPartie();

        miseAjourGrille(ControleurObservation.partie);

    }

    private void observerPartie() {

        ControleurObservation.observerModele(MPartie.class.getSimpleName(), new ListernerObservateur() {

            @Override
            public void reagirNouveauModele(Modele modele) {

                MPartie mPartie = getPartie(modele);

                initialiserGrille(mPartie);

            }

            @Override
            public void reagirChangementAuModele(Modele modele) {
                miseAjourGrille(getPartie(modele));
            }
        });

    }

    private void initialiserGrille(MPartie partie) {
        Log.d("BUG", "TeSTE");

        this.grille.creerGrille(partie.getParametres().getHauteur(), partie.getParametres().getLargeur());

    }

    private void miseAjourGrille(MPartie partie) {
        Log.d("BUG", "TeSTF");

        this.grille.afficherJetons(partie.getGrille());

    }


}
