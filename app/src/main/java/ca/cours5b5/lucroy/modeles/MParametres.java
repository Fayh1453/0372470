package ca.cours5b5.lucroy.modeles;

import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.global.GConstantes;
import ca.cours5b5.lucroy.serialisation.AttributSerialisable;
import ca.cours5b5.lucroy.vues.VParametres;

public class MParametres extends Modele{


    public  static MParametres instance;
    @AttributSerialisable
    public Integer hauteur;
    private final String _hauteur ="hauteur";

    @AttributSerialisable
    public Integer largeur;
    private final String _largeur ="largeur";

    @AttributSerialisable
    public Integer pourGagner;
    private final String _pourGagner ="pourGagner";

    public List<Integer> getChoixHauteur(){
        /*Spinner monSpinner = VParametres.findViewById(R.id.spinHauteur);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(VParametres.getContext(), R.layout.support_simple_spinner_dropdown_item);
        monSpinner.setAdapter(adapter);
        for(int i = GConstantes.HMIN; i < GConstantes.HMAX+1; i++ ){
            adapter.add(i);
            if (GConstantes.HDEF == i){
                monSpinner.setSelection(i - GConstantes.HMIN);
            }
        }*/
        return null;
    }

    public List<Integer> getChoixLargeur()
    {
        /*Spinner monSpinner = VParametres.findViewById(R.id.spinLargeur);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(VParametres.getContext(), R.layout.support_simple_spinner_dropdown_item);
        monSpinner.setAdapter(adapter);
        for(int i = GConstantes.LMIN; i < GConstantes.LMAX+1; i++ ){
            adapter.add(i);
            if (GConstantes.LDEF == i){
                monSpinner.setSelection(i - GConstantes.LMIN);
            }
        }*/
        return null;
    }

    public List<Integer> getChoixPourGagner(){
       /* Spinner monSpinner = VParametres.findViewById(R.id.spinGagner);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(VParametres.getContext(), R.layout.support_simple_spinner_dropdown_item);
        monSpinner.setAdapter(adapter);
        for(int i = GConstantes.GMIN; i < GConstantes.GMAX+1; i++ ){
            adapter.add(i);
            if (GConstantes.GDEF == i){
                monSpinner.setSelection(i - GConstantes.GMIN);
            }
        }*/
       return null;
    }





    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {
        Gson gson = new GsonBuilder().create();
        String chaineJson = gson.toJson(objetJson);
    }

    @Override
    public Map<String, Object> enObjetJson() {


        return null;

    }
}
