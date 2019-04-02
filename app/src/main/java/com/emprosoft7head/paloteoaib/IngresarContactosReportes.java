package com.emprosoft7head.paloteoaib;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.security.Permission;

public class IngresarContactosReportes extends AppCompatActivity {

    private static final int CODE_PERMISION_CONTACTOS =100;
    private static String[] PERMISOS ={Manifest.permission.READ_CONTACTS};
    private String id;

    private static String EMAILPATTERN = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
            "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";

    private TextView TextViewNameConctacto ;
    private TextView textViewMensaje ;
    private EditText editTextPhone ;
    private EditText editTextEmail ;

    private static String CORREO_KEY="guardar_correo";
    private static String TELEFONO_KEY="guardar_telefono";
    private static String NOMBRE_TELEFONO_KEY="guardar_nombre_telefono";


    static final int REQUEST_SELECT_CONTACT = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_contactos_reportes);

        TextViewNameConctacto = (TextView)findViewById(R.id.TextViewNameConctacto);
        textViewMensaje = (TextView)findViewById(R.id.textViewMensaje);
        editTextPhone = (EditText)findViewById(R.id.editTextPhone);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        TextViewNameConctacto.setText(obtenerNombreTelefono(getApplicationContext()));
        editTextPhone.setText(obtenerTelefono(getApplicationContext()));
        editTextEmail.setText(obtenerEmail(getApplicationContext()));


    }

    @Override
    protected void onStart() {
        super.onStart();


    }



    //metodos para guardar los datos
    public void guardarEmail(Context context, String email) {
        SharedPreferences settings = context.getSharedPreferences(CORREO_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString("email", email);
        editor.commit();
    }



    public String obtenerEmail(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(CORREO_KEY, MODE_PRIVATE);
        //Toast.makeText(this,preferences.getString("nombre", ""),Toast.LENGTH_SHORT).show();
        return  preferences.getString("email", "");
    }



    public void guardarTelefono(Context context, String telefono) {
        SharedPreferences settings = context.getSharedPreferences(TELEFONO_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString("telefono", telefono);
        editor.commit();
    }



    public String obtenerTelefono(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(TELEFONO_KEY, MODE_PRIVATE);
        //Toast.makeText(this,preferences.getString("nombre", ""),Toast.LENGTH_SHORT).show();
        return  preferences.getString("telefono", "");
    }


    public void guardarNombreTelefono(Context context, String nombreTelefono) {
        SharedPreferences settings = context.getSharedPreferences(NOMBRE_TELEFONO_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString("NombreTelefono", nombreTelefono);
        editor.commit();
    }



    public String obtenerNombreTelefono(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(NOMBRE_TELEFONO_KEY, MODE_PRIVATE);
        //Toast.makeText(this,preferences.getString("nombre", ""),Toast.LENGTH_SHORT).show();
        return  preferences.getString("NombreTelefono", "");
    }
    //--------------------------------------------------------------------------


    //permisos para contactos
    private boolean validarPermiso(String permiso){

       int result= checkSelfPermission(permiso);

        return  result == PackageManager.PERMISSION_GRANTED;
    }

    private boolean validarRechasoAnterior(String permiso){
        return shouldShowRequestPermissionRationale(permiso);
    }

    private void solicitarPermisos(String [] permiso,int requestCode,int numeroPermiso){

        requestPermissions(permiso,requestCode);

    }

    private boolean validarSolicitarPermiso(String [] permiso,int requestCode,int numeroPermiso){
        boolean result =false;
        if (validarPermiso(permiso[numeroPermiso])){
            result=true;
        }else {
            if (validarRechasoAnterior(permiso[numeroPermiso])){


                textViewMensaje.setText(R.string.explicacion_permiso_contactos);
                solicitarPermisos(permiso,requestCode,numeroPermiso);

            }else {

                solicitarPermisos(permiso,requestCode,numeroPermiso);
            }

        }


        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CODE_PERMISION_CONTACTOS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    selectContact();

                } else {

                    Toast.makeText(this,"denied",Toast.LENGTH_SHORT).show();
                    editTextPhone.setEnabled(true);

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }
    //---------------------------------------------------------


    //seleccionar contactos sin permisos
    public void selectContact() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_CONTACT);
        }
    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            if (requestCode == REQUEST_SELECT_CONTACT && resultCode == RESULT_OK) {


                Uri contactUri = data.getData();
                id = contactUri.getLastPathSegment();

                Cursor cursor = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                        new String[]{id}, null);


                String columns[] = cursor.getColumnNames();

                for (String column : columns) {
                    int index = cursor.getColumnIndex(column);

                }
                if (cursor.moveToFirst()) {
                    int PhoneIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                    String Phone = cursor.getString(PhoneIdx);

                    editTextPhone.setText(Phone);
                    if (Phone.length() == 0) {
                        Toast.makeText(this, "No phone found for contact.", Toast.LENGTH_LONG).show();
                    }
                }
                //------------------------------------------------------------------------------------------------

                Cursor c = getContentResolver().query(
                        ContactsContract.Data.CONTENT_URI, null,
                        ContactsContract.Data.CONTACT_ID + "=?",
                        new String[]{id}, null);


                String co[] = cursor.getColumnNames();

                for (String col : co) {
                    int index = cursor.getColumnIndex(col);

                }
                if (c.moveToFirst()) {
                    int nameIdx = cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME);
                    String name = cursor.getString(nameIdx);

                    TextViewNameConctacto.setText(name);
                    if (name.length() == 0) {
                        Toast.makeText(this, "No name found for contact.", Toast.LENGTH_LONG).show();
                    }
                }





            }

        }catch (Exception e){textViewMensaje.setText(e.toString());}



    }

    //----------------------------------------------------------------



    //movimiento entre activitys
    public void anteriorActivity(View view) {

            Intent ir = new Intent(IngresarContactosReportes.this,InicioDatosUbicacion.class);
            startActivity(ir);
    }

    public void siguienteActivity(View view) {

        guardarEmail(getApplicationContext(), editTextEmail.getText().toString());
        guardarNombreTelefono(getApplicationContext(),TextViewNameConctacto.getText().toString());
        guardarTelefono(getApplicationContext(),editTextPhone.getText().toString());


        if (!(obtenerTelefono(getApplicationContext()).length()<10)){

            if (comprovarFormatoEmail(obtenerEmail(getApplicationContext()))){

            Intent ir = new Intent(IngresarContactosReportes.this,Tutorial.class);
            startActivity(ir);}


        }else{
            PopUp(view);

        }

    }

    public void verPoweredBy(View view) {
        Intent ir = new Intent(IngresarContactosReportes.this,Emprosoft.class);
        startActivity(ir);
    }
    //------------------------------------------------------------------------





   //eventos de pantalla
    public void eventoBontonContacto(View view) {

        if(validarSolicitarPermiso(PERMISOS,CODE_PERMISION_CONTACTOS,0)){
        selectContact();}

    }

    public void editTextPhoneClickEvent(View view) {
    }

    //-----------------------------------------------------------------------------

        private boolean comprovarFormatoEmail(String email){
            Pattern pattern = Pattern.compile(EMAILPATTERN);
            boolean resultado=false;
            if (email != null) {
                Matcher matcher = pattern.matcher(email);
                if (matcher.matches()) {
                    resultado=true;
                }
                else {
                    PopUpEmail();
                    resultado=false;
                }
            }
            return resultado;
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
    public void PopUpEmail(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("EJEMPLO:enavarro@aib.com.co")
                .setTitle("FORMATO NO VALIDO")
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
