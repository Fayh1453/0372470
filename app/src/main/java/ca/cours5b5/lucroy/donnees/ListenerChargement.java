package ca.cours5b5.lucroy.donnees;

import java.util.Map;

public interface ListenerChargement {

   void reagirSucces(Map<String, Object> objectJson);

   void reagirErreur(Exception e);




}
