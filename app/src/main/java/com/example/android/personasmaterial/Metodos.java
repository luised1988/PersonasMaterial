package com.example.android.personasmaterial;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by android on 07/10/2017.
 */

public class Metodos {

    public static int fotoAleatoria(ArrayList<Integer> fotos){
        int fotoSeleccionada;
        Random r = new Random();
        fotoSeleccionada = r.nextInt(fotos.size());
        return fotos.get(fotoSeleccionada);
    }

    public static boolean existencia_persona(ArrayList<Persona>personas, String cedula){
        for (int i = 0; i <personas.size() ; i++) {
            if(personas.get(i).getCedula().equals(cedula)){
                return true;
            }
        }return false;
    }


    public static boolean exitencia_persona_editar(ArrayList<Persona> personas, String cedulaN, String cedulaA){
        int cN = 0, cV = 0;
        for (int i = 0; i <personas.size() ; i++) {
            if(personas.get(i).getCedula().equals(cedulaN)) cN++;
            if(personas.get(i).getCedula().equals(cedulaA)) cV++;
        }
        if(cV == cN && cN == 1 && cV == 1) {
            return false;
        } else if((!(cV == cN)) && cN == 0) {
            return false;
        }
        return true;
    }


}
