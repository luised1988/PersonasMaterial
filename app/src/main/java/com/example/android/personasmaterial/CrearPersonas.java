package com.example.android.personasmaterial;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CrearPersonas extends AppCompatActivity {

    private EditText cajaNombre;
    private EditText cajaApellido;
    private TextInputLayout icajaNombre;
    private TextInputLayout icajaApellido;
    private ArrayList<Integer> fotos;
    private Resources res;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_personas);

        cajaNombre = (EditText)findViewById(R.id.txtNombre);
        cajaApellido = (EditText)findViewById(R.id.txtApellido);

        icajaNombre = (TextInputLayout) findViewById(R.id.cajaNombre);
        icajaApellido = (TextInputLayout) findViewById(R.id.cajaApellido);

        res = this.getResources();
        fotos = new ArrayList<>();
        fotos.add(R.drawable.images);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);

    }

    public void guardar (View v){
         Persona p = new Persona(Metodos.fotoAleatoria(fotos),
                 cajaNombre.getText().toString(),cajaApellido.getText().toString());


        p.guardar();


        Snackbar.make(v, res.getString(R.string.mensaje_guardado), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void limpiar(){
        cajaNombre.setText("");
        cajaApellido.setText("");
        cajaNombre.requestFocus();
    }

    public void onBackPressed(){
        finish();
        Intent i=new Intent(CrearPersonas.this,Principal.class);
        startActivity(i);
    }
}
