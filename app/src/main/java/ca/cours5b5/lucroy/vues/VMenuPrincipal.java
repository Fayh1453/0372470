package ca.cours5b5.lucroy.vues;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.activites.AMenuPrincipal;
import ca.cours5b5.lucroy.activites.AParametres;

public class VMenuPrincipal extends Vue{
    public VMenuPrincipal(Context context) {
        super(context);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    static{
        Log.d("Atelier04",VMenuPrincipal.class.getSimpleName()+"::static");
        Log.d("Atelier04",VMenuPrincipal.class.getSimpleName()+"::onFinishInState");
    }
}
