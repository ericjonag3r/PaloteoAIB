package com.emprosoft7head.paloteoaib;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InicioDatosNombre extends AppCompatActivity {

    private static String NOMBRE_KEY="guardar_nombre";
    private EditText editTextNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_datos_nombre);

        editTextNombre =(EditText)findViewById(R.id.editTextNombre);
        editTextNombre.setText(obtenerNombre(getApplicationContext()));

    }

    public void siguienteActivity(View view) {

        guardarNombre(getApplicationContext(), editTextNombre.getText().toString());


        if (!(obtenerNombre(getApplicationContext()).length()<8)){
        Intent ir = new Intent(InicioDatosNombre.this,InicioDatosUbicacion.class);
        startActivity(ir);
        }else{
            PopUp(view);

        }
    }

    public void anteriorActivity(View view) {

        Intent ir = new Intent(InicioDatosNombre.this,Lanzador.class);
        startActivity(ir);

    }

    public void verPoweredBy(View view) {
        Intent ir = new Intent(InicioDatosNombre.this,Emprosoft.class);
        startActivity(ir);
    }


    public void guardarNombre(Context context, String nombre) {
        SharedPreferences settings = context.getSharedPreferences(NOMBRE_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString("nombre", nombre);
        editor.commit();
    }



    public String obtenerNombre(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(NOMBRE_KEY, MODE_PRIVATE);
        //Toast.makeText(this,preferences.getString("nombre", ""),Toast.LENGTH_SHORT).show();
        return  preferences.getString("nombre", "");
    }

    public void PopUp(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("no ha escrito nungun nombre o el mismo no esta completo")
                .setTitle("CAMPO REQUERIDO")
                .setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }



}
