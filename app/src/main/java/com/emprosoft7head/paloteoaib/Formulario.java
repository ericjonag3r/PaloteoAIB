package com.emprosoft7head.paloteoaib;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.emprosoft7head.controladores.controlador;
import com.emprosoft7head.controladores.coordinador;
import com.emprosoft7head.manejoDatos.DatabasePaloteo;
import com.emprosoft7head.manejoDatos.lienzo;
import com.emprosoft7head.manejoDatos.Tabla;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Formulario extends AppCompatActivity {
    Tabla tabla;
    private coordinador coordinador;
    private controlador controlador;
    private static final String COUNT_KEY="100";
    private boolean confirmaEliminacion;

    private String[] datosReportes = {"eric jose navarro german ", "aib", "barranquilla", "22/03/2019", "13:32 pm"};
    private String[] datosCabeceras = {
            DatabasePaloteo.getCol1(),
            DatabasePaloteo.getCol2(),
            DatabasePaloteo.getCol3(),
            DatabasePaloteo.getCol4(),
            DatabasePaloteo.getCol5(),
            DatabasePaloteo.getCol6(),
            DatabasePaloteo.getCol7(),
            DatabasePaloteo.getCol8(),
            DatabasePaloteo.getCol9(),
            DatabasePaloteo.getCol10(),
    };
    String[][] datos;

    Toolbar toolbar;
    DrawerLayout drawerLayout;

    private lienzo lienzo;
    private  ImageButton imageButtonDeshacer;
    private  ImageButton imageButtonGuardar;
    private  Button buttonContadorTotal;
    private  Button buttonContadorCorte;
    private  ImageButton imageButtonEmprosoft;


    private  EditText editTextNumeroCuenta;
    private  EditText editTextNodo;
    private  EditText editTextCiudad;
    private  EditText editTextCMTS;
    private  MultiAutoCompleteTextView editTextMultiDescripcion;
    private  EditText editTextOtros1;
    private  EditText editTextOtros2;
    private  EditText editTextOtros3;
    private  EditText editTextOtros4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        coordinador =new coordinador(this);
        controlador= coordinador.getControlador();

        tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla),this);
        tabla.agregarCabecera(datosCabeceras);
        controlador.getLogica().llenarTabla();
        this.obtenerPreferences();
        this.traerDatos();
        this.addImage();


        instancedElementosToolBar();
        instancedElementsFormulario();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void addImage(){

        lienzo =findViewById(R.id.tablaPaloteo);
        lienzo.setDatos(datos);
        lienzo.setDatosReportes(datosReportes);
    }




    private void instancedElementsFormulario(){
        toolbar = findViewById(R.id.toolbar);
        drawerLayout =findViewById(R.id.drawer_layout);


        imageButtonDeshacer =(ImageButton)findViewById(R.id.imageButtonDeshacer);
        imageButtonGuardar =(ImageButton)findViewById(R.id.imageButtonGuardar) ;
        buttonContadorTotal =(Button)findViewById(R.id.buttonContadorTotal) ;
        buttonContadorCorte =(Button)findViewById(R.id.buttonContadorCorte) ;
        imageButtonEmprosoft =(ImageButton)findViewById(R.id.imageButtonEmprosoft) ;

        imageButtonDeshacer.setOnClickListener(controlador);
        imageButtonGuardar.setOnClickListener(controlador);
        buttonContadorTotal.setOnClickListener(controlador);
        buttonContadorTotal.setText(getContadorGeneral(this.getApplicationContext()));
        buttonContadorCorte.setOnClickListener(controlador);
        imageButtonEmprosoft.setOnClickListener(controlador);




        editTextNumeroCuenta     = (EditText)findViewById(R.id.editTextNumeroCuenta);
        editTextNumeroCuenta.requestFocus();
        editTextNodo             = (EditText)findViewById(R.id.editTextNodo);

        editTextCiudad           = (EditText)findViewById(R.id.editTextCiudad);

        editTextCMTS             = (EditText)findViewById(R.id.editTextCMTS);

        editTextMultiDescripcion = (MultiAutoCompleteTextView)findViewById(R.id.editTextMultiDescripcion);


        editTextOtros1 = (EditText)findViewById(R.id.editTextOtros1);

        editTextOtros2 = (EditText)findViewById(R.id.editTextOtros2);

        editTextOtros3 = (EditText)findViewById(R.id.editTextOtros3);

        editTextOtros4 = (EditText)findViewById(R.id.editTextOtros4);






    }

    private void instancedElementosToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(controlador);

    }


    public String[] obtenerPreferences(){


        SharedPreferences preferences = getApplicationContext().getSharedPreferences("guardar_nombre", MODE_PRIVATE);
        datosReportes[0]=preferences.getString("nombre","");
        preferences = getApplicationContext().getSharedPreferences("guardar_aliado", MODE_PRIVATE);

        datosReportes[1]=preferences.getString("aliado","");
        preferences = getApplicationContext().getSharedPreferences("guardar_ciudad", MODE_PRIVATE);

        datosReportes[2]=preferences.getString("ciudad","");

        datosReportes[3]=new Date().toString();
        datosReportes[4]="";
        return datosReportes;

    }


    public void comparteView(lienzo viewShare) {
        Uri uriF=Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.debugger);

        try {

            Bitmap bitmap = null;

                bitmap = Bitmap.createBitmap(viewShare.getCoordenadaX(), viewShare.getCoordenadaY(), Bitmap.Config.ARGB_8888);

            // Creamos el canvas para pintar en el bitmap
            Canvas canvas = new Canvas(bitmap);
            // Pintamos el contenido de la vista en el canvas y así en el bitmap
            viewShare.draw(canvas);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);


            try {

                File f = new File(this.getExternalCacheDir(),"Paloteo.jpg");
                f.deleteOnExit();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(stream.toByteArray());
                fo.close();

                uriF = FileProvider.getUriForFile(this,getApplicationContext ().getPackageName ()+ ".provider",f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i("formulario/comparteView",uriF.toString());
            Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
            shareIntent.putExtra(Intent.EXTRA_STREAM,uriF);
            shareIntent.setPackage("com.whatsapp");
            startActivity(Intent.createChooser(shareIntent, "Share via"));

        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
            Log.e("formulario",e.toString());
        }

    }



    public void traerDatos() {

       Cursor cursor = coordinador.getDatabasePaloteo().getUltimaData(10);


        datos = new String[cursor.getCount()+1][cursor.getColumnCount()];

        for (int x = 0; x < cursor.getColumnCount(); x++) {
            datos[0][x] = cursor.getColumnName(x);
        }
        try {



            for (int j = 0; j < cursor.getCount(); j++) {
                cursor.moveToPosition(j);
                for (int x = 0; x < cursor.getColumnCount(); x++) {

                    datos[j+1][x] = cursor.getString(x);
                }

            }


        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }




    }



    public void mostrarMensage(String mensage){

        Toast mensageT =Toast.makeText(this,mensage,Toast.LENGTH_SHORT);
        mensageT.setGravity(Gravity.TOP,0,12);
        mensageT.show();
    }


    //conservar en memoria el valor del contador total
    public void setContadorGeneral(Context context, String cant) {
        SharedPreferences settings = context.getSharedPreferences(COUNT_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString("can", cant);
        editor.commit();
    }

    public String getContadorCorte(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(COUNT_KEY, MODE_PRIVATE);
        return  preferences.getString("can", "");
    }

    public void setContadorCorte(Context context, String cant) {
        SharedPreferences settings = context.getSharedPreferences(COUNT_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString("can", cant);
        editor.commit();
    }

    public String getContadorGeneral(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(COUNT_KEY, MODE_PRIVATE);
        return  preferences.getString("can", "");
    }

    //GRETERS AN SETERS DE CONTROLADOR Y COORDINADOR
    public com.emprosoft7head.controladores.coordinador getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(com.emprosoft7head.controladores.coordinador coordinador) {
        this.coordinador = coordinador;
    }


    public com.emprosoft7head.controladores.controlador getControlador() {
        return controlador;
    }

    public void setControlador(com.emprosoft7head.controladores.controlador controlador) {
        this.controlador = controlador;
    }

    public boolean confirmacionMensage() {

        try {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Formulario.this);
            // Setting Alert Dialog Title
            alertDialogBuilder.setTitle("Confirm Delete..!!!");
            // Icon Of Alert Dialog
            alertDialogBuilder.setIcon(R.drawable.closebutom);
            // Setting Alert Dialog Message
            alertDialogBuilder.setMessage("desea eliminar los datos?");
            alertDialogBuilder.setCancelable(false);


            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    confirmaEliminacion=true;
                    Log.e("logica/confiMensa"," usuario seleciona yes");


                }
            });

            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    confirmaEliminacion=false;
                    Log.e("logica/confiMensa"," usuario seleciona no");

                }
            });
            alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    confirmaEliminacion =false;
                    Log.e("logica/confiMensa"," usuario cancela");
                }

            });


            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();


        }catch (Exception e){
            confirmaEliminacion=false;
            Log.e("logica/confiMensa",e.toString());
        }

        return confirmaEliminacion;
    }







    //---------------------getter ans setter vistas ---------------------------------------

    public ImageButton getImageButtonDeshacer() {
        return imageButtonDeshacer;
    }

    public void setImageButtonDeshacer(ImageButton imageButtonDeshacer) {
        this.imageButtonDeshacer = imageButtonDeshacer;
    }

    public ImageButton getImageButtonGuardar() {
        return imageButtonGuardar;
    }

    public void setImageButtonGuardar(ImageButton imageButtonGuardar) {
        this.imageButtonGuardar = imageButtonGuardar;
    }

    public Button getButtonContadorTotal() {
        return buttonContadorTotal;
    }

    public void setButtonContadorTotal(Button buttonContadorTotal) {
        this.buttonContadorTotal = buttonContadorTotal;
    }

    public Button getButtonContadorCorte() {
        return buttonContadorCorte;
    }

    public void setButtonContadorCorte(Button buttonContadorCorte) {
        this.buttonContadorCorte = buttonContadorCorte;
    }

    public ImageButton getImageButtonEmprosoft() {
        return imageButtonEmprosoft;
    }

    public void setImageButtonEmprosoft(ImageButton imageButtonEmprosoft) {
        this.imageButtonEmprosoft = imageButtonEmprosoft;
    }

    public EditText getEditTextNumeroCuenta() {
        return editTextNumeroCuenta;
    }

    public void setEditTextNumeroCuenta(EditText editTextNumeroCuenta) {
        this.editTextNumeroCuenta = editTextNumeroCuenta;
    }

    public EditText getEditTextNodo() {
        return editTextNodo;
    }

    public void setEditTextNodo(EditText editTextNodo) {
        this.editTextNodo = editTextNodo;
    }

    public EditText getEditTextCiudad() {
        return editTextCiudad;
    }

    public void setEditTextCiudad(EditText editTextCiudad) {
        this.editTextCiudad = editTextCiudad;
    }

    public EditText getEditTextCMTS() {
        return editTextCMTS;
    }

    public void setEditTextCMTS(EditText editTextCMTS) {
        this.editTextCMTS = editTextCMTS;
    }

    public MultiAutoCompleteTextView getEditTextMultiDescripcion() {
        return editTextMultiDescripcion;
    }

    public void setEditTextMultiDescripcion(MultiAutoCompleteTextView editTextMultiDescripcion) {
        this.editTextMultiDescripcion = editTextMultiDescripcion;
    }

    public EditText getEditTextOtros1() {
        return editTextOtros1;
    }

    public void setEditTextOtros1(EditText editTextOtrosA) {
        this.editTextOtros1 = editTextOtrosA;
    }

    public EditText getEditTextOtros2() {
        return editTextOtros2;
    }

    public void setEditTextOtros2(EditText editTextOtrosB) {
        this.editTextOtros2 = editTextOtrosB;
    }

    public EditText getEditTextOtros3() {
        return editTextOtros3;
    }

    public void setEditTextOtros3(EditText editTextOtrosC) {
        this.editTextOtros3 = editTextOtrosC;
    }

    public EditText getEditTextOtros4() {
        return editTextOtros4;
    }

    public void setEditTextOtros4(EditText editTextOtrosD) {
        this.editTextOtros4 = editTextOtrosD;
    }

    public String[] getDatosReportes() {
        return datosReportes;
    }

    public void setDatosReportes(String[] datosReportes) {
        this.datosReportes = datosReportes;
    }

    public String[][] getDatos() {
        return datos;
    }

    public void setDatos(String[][] datos) {
        this.datos = datos;
    }

    public Tabla getTabla() {
        return tabla;
    }

    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }

    public String[] getDatosCabeceras() {
        return datosCabeceras;
    }

    public void setDatosCabeceras(String[] datosCabeceras) {
        this.datosCabeceras = datosCabeceras;
    }

    public static String getCountKey() {
        return COUNT_KEY;
    }

    public lienzo getLienzo() {
        return lienzo;
    }

    public void setLienzo(lienzo lienzo) {
        this.lienzo = lienzo;
    }

    //---------------------getter ans setter vistas ---------------------------------------
}
