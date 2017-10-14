package com.example.android.personasmaterial;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EditarPersona extends AppCompatActivity {
    private EditText txtCedula;
    private EditText txtNombre;
    private EditText txtApellido;
    private TextInputLayout cajaCedula;
    private TextInputLayout cajaNombre;
    private TextInputLayout cajaApellido;

    private Resources res;
    private Spinner sexo;
    private ArrayAdapter<String> adapter;
    private String[] opc;
    private Bundle b;
    private Persona p;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_persona);

        txtCedula = (EditText) findViewById(R.id.txtCedulaeEdit);
        txtNombre = (EditText) findViewById(R.id.txtNombreEdit);
        txtApellido = (EditText) findViewById(R.id.txtApellidoEdit);
        res = this.getResources();

        cajaNombre = (TextInputLayout) findViewById(R.id.cajaNombreEdit);
        cajaApellido = (TextInputLayout) findViewById(R.id.cajaApellidoEdit);
        cajaCedula = (TextInputLayout) findViewById(R.id.cajaCedulaEdit);

        sexo = (Spinner) findViewById(R.id.spnSexoEdit);

        opc = res.getStringArray(R.array.sexo);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc);
        sexo.setAdapter(adapter);

        b = this.getIntent().getExtras();

        p = new Persona(b.getInt("foto"), b.getString("cedula"), b.getString("nombre"), b.getString("apellido"), b.getInt("sexo"));
        txtCedula.setText(p.getCedula());
        txtNombre.setText(p.getNombre());
        txtApellido.setText(p.getApellido());
        sexo.setSelection(p.getSexo());

    }



    public void guadarEdit(View v) {
        final View v2 = v;
        if (validar()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.modificar));
            builder.setMessage(getResources().getString(R.string.editar_mensaje));
            builder.setPositiveButton(getResources().getString(R.string.si_eliminar_mensaje), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Persona p2 = new Persona(p.getFoto(), txtCedula.getText().toString(),
                            txtNombre.getText().toString(), txtApellido.getText().toString(), sexo.getSelectedItemPosition());
                    p.actualizar(p2);
                    p = p2;
                    Snackbar.make(v2, res.getString(R.string.mensaje_guardado), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    limpiar();

                    Bundle b = new Bundle();
                    b.putString("cedula",p2.getCedula());
                    b.putString("nombre",p2.getNombre());
                    b.putString("apellido",p2.getApellido());
                    b.putInt("sexo",p2.getSexo());
                    b.putInt("foto",p2.getFoto());
                    startActivity(new Intent(EditarPersona.this, DetallePersona.class).putExtra("datos",b));
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.no_eliminar_mensaje), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create().show();
        }
    }

    public void limpiar(View v) {
        limpiar();
    }

    public void limpiar() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        sexo.setSelection(0);
        txtCedula.requestFocus();

    }

    public boolean validar() {
        if (validar_aux(txtCedula, cajaCedula)) return false;
        else if (validar_aux(txtNombre, cajaNombre)) return false;
        else if (validar_aux(txtApellido, cajaApellido)) return false;
        else if (Metodos.exitencia_persona_editar(Datos.obteberPersonas(), txtCedula.getText().toString(), p.getCedula())) {
            txtCedula.setError(res.getString(R.string.persona_existente_error));
            txtCedula.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validar_aux(TextView t, TextInputLayout ct) {
        if (t.getText().toString().isEmpty()) {
            t.requestFocus();
            t.setError(res.getString(R.string.no_vacio_error));
            return true;
        }
        return false;
    }

}
