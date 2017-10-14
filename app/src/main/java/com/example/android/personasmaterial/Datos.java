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

    public static boolean eliminarPersonas(Persona p){
        for (int i = 0; i <personas.size() ; i++){
            if (p.getCedula().equals((personas.get(i).getCedula()))){
                personas.remove(i);
                return true;
            }
        }
        return  false;
    }

    public static void actualizarPersona(Persona personaVieja, Persona personaNueva) {
        int position = getPositionPersona(personaVieja.getCedula());

        if(position != -1) {
            Persona aux = personas.get(position);
            aux.setCedula(personaNueva.getCedula());
            aux.setNombre(personaNueva.getNombre());
            aux.setApellido(personaNueva.getApellido());
            aux.setFoto(personaNueva.getFoto());
            aux.setSexo(personaNueva.getSexo());
        }
    }

    private static int getPositionPersona(String cedula) {
        for (int i = 0; i < personas.size(); i++) {
            if(personas.get(i).getCedula().equals(cedula)) return i;
        }
        return -1;
    }

}
