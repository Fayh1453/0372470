package ca.cours5b5.lucroy.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.global.GConstantes;

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

        Spinner monSpinner = this.findViewById(R.id.spinHauteur);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        monSpinner.setAdapter(adapter);
        for(int i = GConstantes.HMIN; i < GConstantes.HMAX+1; i++ ){
            adapter.add(i);
            if (GConstantes.HDEF == i){
                monSpinner.setSelection(i - GConstantes.HMIN);
            }
        }


        monSpinner = this.findViewById(R.id.spinLargeur);
        adapter = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        monSpinner.setAdapter(adapter);
        for(int i = GConstantes.LMIN; i < GConstantes.LMAX+1; i++ ){
            adapter.add(i);
            if (GConstantes.LDEF == i){
                monSpinner.setSelection(i - GConstantes.LMIN);
            }
        }


        monSpinner = this.findViewById(R.id.spinGagner);
        adapter = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        monSpinner.setAdapter(adapter);
        for(int i = GConstantes.GMIN; i < GConstantes.GMAX+1; i++ ){
            adapter.add(i);
            if (GConstantes.GDEF == i){
                monSpinner.setSelection(i - GConstantes.GMIN);
            }
        }


    }
    static{
        Log.d("Atelier04",VParametres.class.getSimpleName()+"::static");
        Log.d("Atelier04",VParametres.class.getSimpleName()+"::onFinishInState");
    }
}
