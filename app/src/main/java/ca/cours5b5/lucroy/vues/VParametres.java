package ca.cours5b5.lucroy.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.global.GConstantes;
import ca.cours5b5.lucroy.modeles.MParametres;

public class VParametres extends Vue{



    public VParametres(Context context){
        super(context);
    }

    public VParametres(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public VParametres(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("Atelier04",VParametres.class.getSimpleName()+"::onFinishInState");
try {

    MParametres modele = MParametres.instance;

    Spinner spinnerHauteur = this.findViewById(R.id.spinHauteur);
    TextView lblHauteur = this.findViewById(R.id.lblHauteur);

    Spinner spinnerLargeur = this.findViewById(R.id.spinLargeur);
    TextView lblLargeur = this.findViewById(R.id.lblLargeur);

    Spinner spinnerGagner = this.findViewById(R.id.spinGagner);
    TextView lblGagner = this.findViewById(R.id.lblGagner);

    List<Integer> listeChoixHauteur = modele.getChoixHauteur();
    ArrayAdapter<Integer> adapthauteur = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, listeChoixHauteur);
    spinnerHauteur.setAdapter(adapthauteur);
    spinnerHauteur.setSelection(adapthauteur.getPosition(MParametres.instance.getHauteur()));

    List<Integer> listeChoixLargeur = modele.getChoixLargeur();
    ArrayAdapter<Integer> adapLargeur = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, listeChoixLargeur);
    spinnerLargeur.setAdapter(adapLargeur);//TODO Erreur
    spinnerLargeur.setSelection(adapLargeur.getPosition(MParametres.instance.getLargeur()));

    List<Integer> listeChoixPourGagner = modele.getChoixPourGagner();
    ArrayAdapter<Integer> adapPourGagner = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, listeChoixPourGagner);
    spinnerGagner.setAdapter(adapPourGagner);
    spinnerGagner.setSelection(adapPourGagner.getPosition(MParametres.instance.getPourGagner()));
}catch (Exception e) {Log.e("error05",e.getMessage()); e.printStackTrace();}
    }
    static{
        Log.d("Atelier04",VParametres.class.getSimpleName()+"::static");
        Class metaDonnees = VParametres.class;

    }
}
