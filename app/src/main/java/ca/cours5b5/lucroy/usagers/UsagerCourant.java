package ca.cours5b5.lucroy.usagers;

import com.google.firebase.auth.FirebaseAuth;

public class UsagerCourant {


    public static boolean siUsagerConnecte(){
        boolean reussi = false;

        if(FirebaseAuth.getInstance().getCurrentUser() != null){

            reussi = true;

        }
        return reussi;

    }

    public static String getId(){

            return FirebaseAuth.getInstance().getCurrentUser().getUid();


    }

}
