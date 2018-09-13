package ca.cours5b5.lucroy.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;

import ca.cours5b5.lucroy.activites.AMenuPrincipal;

public abstract class Vue extends ConstraintLayout {
    public Vue(Context context) {
        super(context);
    }

    public Vue(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Vue(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    static{

    }
}

