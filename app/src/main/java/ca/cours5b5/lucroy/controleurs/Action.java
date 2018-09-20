package ca.cours5b5.lucroy.controleurs;

import java.util.Objects;

import ca.cours5b5.lucroy.controleurs.interfaces.Fournisseur;
import ca.cours5b5.lucroy.controleurs.interfaces.ListernerFournisseur;

public class Action {

    Fournisseur fournisseur;

    ListernerFournisseur listernerFournisseur;

    Object[] args;

    public void setArguments (Object... args){

            this.args = args;

    }
    public void setFournisseur (Fournisseur fournisseur){

        this.fournisseur = fournisseur;

    }
    public void setListernerFournisseur (Fournisseur fournisseur){

        this.fournisseur = fournisseur;

    }

    public void executerDesQuePossible(){



    }




}
