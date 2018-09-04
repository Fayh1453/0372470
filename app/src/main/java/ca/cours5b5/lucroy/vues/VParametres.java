package ca.cours5b5.lucroy.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.global.GConstantes;

public class VParametres extends ConstraintLayout{



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

        int compteur =0;
        Spinner monSpinner = this.findViewById(R.id.spinHauteur);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        monSpinner.setAdapter(adapter);
        for(int i = GConstantes.HMIN; i < GConstantes.HMAX+1; i++ ){
            adapter.add(i);
            if (GConstantes.HDEF == i){
                monSpinner.setSelection(compteur);
            }else{
                compteur++;
            }
        }


        compteur =0;
        monSpinner = this.findViewById(R.id.spinLargeur);
        adapter = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        monSpinner.setAdapter(adapter);
        for(int i = GConstantes.LMIN; i < GConstantes.LMAX+1; i++ ){
            adapter.add(i);
            if (GConstantes.LDEF == i){
                monSpinner.setSelection(compteur);
            }else{
                compteur++;
            }
        }


        compteur =0;
        monSpinner = this.findViewById(R.id.spinGagner);
        adapter = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        monSpinner.setAdapter(adapter);
        for(int i = GConstantes.GMIN; i < GConstantes.GMAX+1; i++ ){
            adapter.add(i);
            if (GConstantes.GDEF == i){
                monSpinner.setSelection(compteur);
            }else{
                compteur++;
            }
        }


    }

}
