package com.example.android.personasmaterial;

import java.util.ArrayList;

/**
 * Created by android on 07/10/2017.
 */

public class Datos {
    public static ArrayList<Persona>personas = new ArrayList();
    public static void guardarPersona (Persona p){
        personas.add(p);
    }

    public static ArrayList<Persona> obteberPersonas(){
        return personas;
    }

}
