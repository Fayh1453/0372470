package ca.cours5b5.lucroy.vues;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.controleurs.ControleurObservation;
import ca.cours5b5.lucroy.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.lucroy.exceptions.ErreurObservation;
import ca.cours5b5.lucroy.modeles.MParametresPartie;
import ca.cours5b5.lucroy.modeles.MPartie;
import ca.cours5b5.lucroy.modeles.MPartieReseau;
import ca.cours5b5.lucroy.modeles.Modele;


public class VPartieReseau extends VPartie {


    private VGrille grille;


    public VPartieReseau(Context context) {
        super(context);
    }

    public VPartieReseau(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartieReseau(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected String getNomModele(){
        return MPartieReseau.class.getSimpleName();
    }

}
