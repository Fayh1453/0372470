package ca.cours5b5.lucroy.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.lucroy.R;
import ca.cours5b5.lucroy.controleurs.Action;
import ca.cours5b5.lucroy.controleurs.ControleurAction;
import ca.cours5b5.lucroy.global.GCommande;


public class VMenuPrincipal extends Vue {

    private Button boutonParametres;
    private Action actionParametres;

    private Button boutonPartie;
    private Action actionPartie;

    private Button boutonConnexion;
    private Action actionConnexion;

    private Button boutonPartieEnLigne;
    private Action actionPartieEnLigne;



    public VMenuPrincipal(Context context) {
        super(context);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        recupererControles();

        demanderActions();

        installerListeners();

    }


    private void recupererControles() {

        boutonParametres = findViewById(R.id.bouton_parametres);

        boutonPartie = findViewById(R.id.bouton_partie);

        boutonConnexion = findViewById(R.id.bouton_connexion);

        boutonPartieEnLigne = findViewById(R.id.bouton_jouer_en_ligne);

    }

    private void demanderActions() {

        actionParametres = ControleurAction.demanderAction(GCommande.OUVRIR_MENU_PARAMETRES);

        actionPartie = ControleurAction.demanderAction(GCommande.DEMARRER_PARTIE);

        actionConnexion = ControleurAction.demanderAction(GCommande.CONNEXION);

        actionPartieEnLigne = ControleurAction.demanderAction(GCommande.JOINDRE_OU_CREER_PARTIE_RESEAU);

    }


    private void installerListeners() {

        installerListenerParametres();

        installerListenerPartie();

        installerListenerConnexion();

        installerListenerPartieEnLigne();

    }

    private void installerListenerPartie() {

        boutonPartie.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPartie.executerDesQuePossible();
            }
        });

    }

    private void installerListenerParametres() {

        boutonParametres.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionParametres.executerDesQuePossible();
            }
        });

    }

    private void installerListenerConnexion() {

        boutonConnexion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionConnexion.executerDesQuePossible();
            }
        });

    }

    private void installerListenerPartieEnLigne() {

        boutonPartieEnLigne.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPartieEnLigne.executerDesQuePossible();
            }
        });

    }

}
