package com.example.android.personasmaterial;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallePersona extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Persona p;
    private String cedula, nombre, apellido;
    private int fot, sexo;
    private Bundle bundle;
    private Intent i;
    private ImageView foto;
    private Resources res;
    private TextView ced,nomb,app,sex;
    private String [] opc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
       // setSupportActionBar(toolbar);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        ced = (TextView)findViewById(R.id.lblCedulaD);
        nomb = (TextView)findViewById(R.id.lblNombreD);
        app = (TextView)findViewById(R.id.lblApellidoD);
        sex = (TextView)findViewById(R.id.lblSexoD) ;

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collaspsing_toolbar);
        foto = (ImageView) findViewById(R.id.fotoPersona);

        res = this.getResources();
        i = getIntent();
        bundle = i.getBundleExtra("datos");

        cedula = bundle.getString("cedula");
        nombre = bundle.getString("nombre");
        apellido = bundle.getString("apellido");
        fot = bundle.getInt("foto");
        sexo = bundle.getInt("sexo");

        opc = res.getStringArray(R.array.sexo);

        collapsingToolbarLayout.setTitle(nombre+" "+apellido);
       foto.setImageDrawable(ResourcesCompat.getDrawable(res,fot,null));
        ced.setText(cedula);
        nomb.setText(nombre);
        app.setText(apellido);
        sex.setText(opc[sexo]);

    }

   public  void eliminar(View v){
       String positivo, negativo;

       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle(res.getString(R.string.titulo_eliminar_mensaje));
       builder.setMessage(res.getString(R.string.eliminar_mensaje));
       positivo = res.getString(R.string.si_eliminar_mensaje);
       negativo = res.getString(R.string.no_eliminar_mensaje);


      builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
              Persona p = new Persona(cedula);
              Datos.eliminarPersonas(p);
              onBackPressed();
          }
      });

       builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {

           }
       });

       AlertDialog dialog = builder.create();
       dialog.show();

   }

    public void onBackPressed(){
        finish();
        Intent i=new Intent(DetallePersona.this,Principal.class);
        startActivity(i);
    }


    public void editar (View v){
        Bundle b = new Bundle();
        b.putString("cedula", cedula);
        b.putString("nombre", nombre);
        b.putString("apellido", apellido);
        b.putInt("foto", fot);
        b.putInt("sexo", sexo);

        startActivity(new Intent(this, EditarPersona.class).putExtras(b));
    }


}



