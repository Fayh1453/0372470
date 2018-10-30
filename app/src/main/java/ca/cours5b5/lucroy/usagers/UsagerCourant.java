package ca.cours5b5.lucroy.usagers;

import com.google.firebase.auth.FirebaseAuth;

public class UsagerCourant {


    public static boolean siUsagerConnecte(){
        boolean connecter = false;

        if(FirebaseAuth.getInstance().getCurrentUser() != null){

            connecter = true;

        }
        return connecter;

    }

    public static String getId(){

        return FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

}
