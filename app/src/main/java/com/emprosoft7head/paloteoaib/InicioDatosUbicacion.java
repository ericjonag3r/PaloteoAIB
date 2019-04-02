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

public class InicioDatosUbicacion extends AppCompatActivity {

    private EditText editTextAliado;
    private EditText editTextCiudad;
    private static String ALIADO_KEY="guardar_aliado";
    private static String CIUDAD_KEY="guardar_ciudad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_datos_ubicacion);

        editTextAliado=(EditText) findViewById(R.id.editTextAliado);
        editTextCiudad=(EditText) findViewById(R.id.editTextCiudad);
        editTextAliado.setText(obtenerAliado(getApplicationContext()));
        editTextCiudad.setText(obtenerCiudad(getApplicationContext()));

    }

    public void anteriorActivity(View view) {
            Intent ir = new Intent(InicioDatosUbicacion.this,InicioDatosNombre.class);
            startActivity(ir);

    }

    public void siguienteActivity(View view) {
        guardarAliado(getApplicationContext(),editTextAliado.getText().toString());
        guardarCiudad(getApplicationContext(),editTextCiudad.getText().toString());

        if (!(obtenerAliado(getApplicationContext()).length()<3)&&!(obtenerCiudad(getApplicationContext()).length()<4)){
            Intent ir = new Intent(InicioDatosUbicacion.this,IngresarContactosReportes.class);
            startActivity(ir);
        }else{
            PopUp(view);

        }

    }

    public void verPoweredBy(View view) {
        Intent ir = new Intent(InicioDatosUbicacion.this,Emprosoft.class);
        startActivity(ir);
    }

    public void guardarAliado(Context context, String aliado) {
        SharedPreferences settings = context.getSharedPreferences(ALIADO_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString("aliado", aliado);
        editor.commit();
    }



    public String obtenerAliado(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ALIADO_KEY, MODE_PRIVATE);
        //Toast.makeText(this,preferences.getString("nombre", ""),Toast.LENGTH_SHORT).show();
        return  preferences.getString("aliado", "");
    }

    public void guardarCiudad(Context context, String ciudad) {
        SharedPreferences settings = context.getSharedPreferences(CIUDAD_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString("ciudad", ciudad);
        editor.commit();
    }



    public String obtenerCiudad(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(CIUDAD_KEY, MODE_PRIVATE);
        //Toast.makeText(this,preferences.getString("nombre", ""),Toast.LENGTH_SHORT).show();
        return  preferences.getString("ciudad", "");
    }




    public void PopUp(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("todos los campos son obligatorios")
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
