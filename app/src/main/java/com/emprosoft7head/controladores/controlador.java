package com.emprosoft7head.controladores;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.emprosoft7head.manejoDatos.DatabasePaloteo;
import com.emprosoft7head.paloteoaib.Emprosoft;
import com.emprosoft7head.paloteoaib.Formulario;
import com.emprosoft7head.paloteoaib.R;


public class controlador implements View.OnClickListener , NavigationView.OnNavigationItemSelectedListener {

    private Formulario formulario;
    private logica logica;
    private DatabasePaloteo databasePaloteo;

    public controlador(Formulario formulario) {
        this.formulario = formulario;
        logica =new logica(formulario);


    }

    public controlador(Formulario formulario, com.emprosoft7head.controladores.logica logica) {
        this.formulario = formulario;
        this.logica = logica;
        logica =new logica(formulario);
    }

    public controlador() {
        logica =new logica(formulario);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.imageButtonDeshacer:
                try {
                    databasePaloteo.deleteDataForID(databasePaloteo.getIDultimaFila());
                    formulario.getTabla().eliminarFila(formulario.getTabla().getFilas()-1);
                    formulario.setContadorGeneral(formulario.getApplicationContext(),Integer.toString(formulario.getTabla().getFilas()-1) );
                    formulario.getButtonContadorTotal().setText(formulario.getContadorGeneral(formulario.getApplicationContext()).toString());
                }catch (Exception e){
                    Toast.makeText(formulario.getApplicationContext(),"no hay datos en la tabla", Toast.LENGTH_SHORT).show();
                    Log.e("controlador/onClick/iBD",e.toString());
                }
                break;

            case R.id.imageButtonGuardar:
                if (logica.validarCampos()) {
                    logica.generarObjetoPaloteo(true);

                    try {

                        boolean b = databasePaloteo.insertData(logica.getPaloteoVo());
                        if (b){
                            int count = databasePaloteo.getCantidadFilas();
                            formulario.getButtonContadorTotal().setText(count+"");
                            formulario.setContadorGeneral(formulario.getApplicationContext(),count+"");
                            formulario.getTabla().agregarFilaTabla(logica.generarArryDatos(true));
                            Log.i("controlador/onClick/iBG","se ha guardado un dato");
                        }
                    } catch (Exception e) {
                        Toast.makeText(formulario.getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();

                        Log.e("controlador/onClick/iBG",e.toString());

                    }

                    formulario.getEditTextNumeroCuenta().requestFocus();
                    logica.limpiarCampos();

                }

                break;


            case R.id.buttonContadorTotal:
                Cursor c=formulario.getCoordinador().getDatabasePaloteo().getAllData();

                try {
                    logica.MostarPantalla(c);
                }catch (Exception e){
                    Toast.makeText(formulario.getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                    Log.e("controlador/onClick/bCT",e.toString());
                }

                c.close();
                break;

            case R.id.buttonContadorCorte:
                    formulario.recreate();
                break;

            case R.id.imageButtonEmprosoft:
                formulario.startActivity(new Intent(formulario.getApplicationContext(), Emprosoft.class));
                break;

            case R.id.toolbar:
                Log.e("controlador/onClic/Toll","pulsado tollbarr");
                break;

        }
} //fin del on clik listener

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

         switch (item.getItemId()) {

             case R.id.nav_manage:

                 break;

             case R.id.nav_infomacion:
                 formulario.startActivity(new Intent(formulario.getApplicationContext(), Emprosoft.class));
                 break;

             case R.id.nav_share:
                 formulario.comparteView(formulario.getLienzo());
                 break;

             case R.id.nav_borrarBase:
                 logica.generarDocumento();
                 formulario.confirmacionMensage();
                 if (databasePaloteo.deleteData()) {
                     formulario.setContadorGeneral(formulario.getApplicationContext(), "0");
                     formulario.recreate();
                 }
                     break;

              case R.id.nav_send:
                   logica.generarDocumento();
                   logica.compartirDocumento(logica.getUri());
               break;

             case R.id.toolbar:
                 Log.e("controlador/onClic/Toll","pulsado tollbarr");
                 break;



         }

        DrawerLayout drawer = (DrawerLayout) formulario.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;



    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public com.emprosoft7head.controladores.logica getLogica() {
        return logica;
    }

    public void setLogica(com.emprosoft7head.controladores.logica logica) {
        this.logica = logica;
    }

    public DatabasePaloteo getDatabasePaloteo() {
        return databasePaloteo;
    }

    public void setDatabasePaloteo(DatabasePaloteo databasePaloteo) {
        this.databasePaloteo = databasePaloteo;
    }
}
