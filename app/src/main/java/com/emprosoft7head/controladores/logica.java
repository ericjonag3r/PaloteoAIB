package com.emprosoft7head.controladores;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.emprosoft7head.manejoDatos.DatabasePaloteo;
import com.emprosoft7head.manejoDatos.paloteoVo;
import com.emprosoft7head.manejoExcel.SQLiteToExcel;
import com.emprosoft7head.paloteoaib.Formulario;

import java.io.File;
import java.util.ArrayList;

public class logica {

    private Formulario formulario;
    private paloteoVo paloteoVo;
    private Uri uri;


    public logica() {
        paloteoVo = new paloteoVo();

    }

    public logica(Formulario formulario) {
        this.formulario = formulario;
        paloteoVo = new paloteoVo();


    }

    public boolean validarCampos() {
        boolean resul = true;

        if (TextUtils.isEmpty(formulario.getEditTextNumeroCuenta().getText().toString())
                || formulario.getEditTextNumeroCuenta().getText().toString().length() < 5
                || formulario.getEditTextNumeroCuenta().getText().toString().length() > 8) {

            formulario.mostrarMensage("revise el campo " + formulario.getEditTextNumeroCuenta().getHint() + " ");
            formulario.getEditTextNumeroCuenta().requestFocus();
            resul = false;
        } else if (TextUtils.isEmpty(formulario.getEditTextNodo().getText().toString())
                || formulario.getEditTextNodo().getText().toString().length() > 6) {

            formulario.mostrarMensage("revise el campo " + formulario.getEditTextNodo().getHint());
            formulario.getEditTextNodo().requestFocus();
            resul = false;
        } else if (TextUtils.isEmpty(formulario.getEditTextCiudad().getText().toString())) {

            formulario.mostrarMensage("complete el campo " + formulario.getEditTextCiudad().getHint());
            formulario.getEditTextCiudad().requestFocus();
            resul = false;
        } else if (TextUtils.isEmpty(formulario.getEditTextCMTS().getText().toString())) {

            formulario.mostrarMensage("complete el campo " + formulario.getEditTextCMTS().getHint());
            formulario.getEditTextCMTS().requestFocus();
            resul = false;
        } else if (TextUtils.isEmpty(formulario.getEditTextMultiDescripcion().getText().toString())) {

            formulario.mostrarMensage("complete el campo " + formulario.getEditTextMultiDescripcion().getHint().subSequence(0, 11));
            formulario.getEditTextMultiDescripcion().requestFocus();
            resul = false;
        } else {

            resul = true;
        }


        return resul;
    }


    public void generarObjetoPaloteo(boolean validarcampos) {
        if (validarcampos) {

            paloteoVo.setCUENTA(formulario.getEditTextNumeroCuenta().getText().toString());
            paloteoVo.setNODO(formulario.getEditTextNodo().getText().toString());
            paloteoVo.setCIUDAD(formulario.getEditTextCiudad().getText().toString());
            paloteoVo.setCMTS(formulario.getEditTextCMTS().getText().toString());
            paloteoVo.setDESCRIPCION(formulario.getEditTextMultiDescripcion().getText().toString());
            paloteoVo.setOTROS1(formulario.getEditTextOtros1().getText().toString());
            paloteoVo.setOTROS2(formulario.getEditTextOtros2().getText().toString());
            paloteoVo.setOTROS3(formulario.getEditTextOtros3().getText().toString());
            paloteoVo.setOTROS4(formulario.getEditTextOtros4().getText().toString());

        }

    }


    public ArrayList<String> generarArryDatos(boolean validarcampos) {
        ArrayList<String> elementos = new ArrayList<String>();
        if (validarcampos) {


            elementos.add(formulario.getButtonContadorTotal().getText().toString());
            elementos.add(formulario.getEditTextNumeroCuenta().getText().toString());
            elementos.add(formulario.getEditTextNodo().getText().toString());
            elementos.add(formulario.getEditTextCiudad().getText().toString());
            elementos.add(formulario.getEditTextCMTS().getText().toString());
            elementos.add(formulario.getEditTextMultiDescripcion().getText().toString());
            elementos.add(formulario.getEditTextOtros1().getText().toString());
            elementos.add(formulario.getEditTextOtros2().getText().toString());
            elementos.add(formulario.getEditTextOtros3().getText().toString());
            elementos.add(formulario.getEditTextOtros4().getText().toString());

        }
        return elementos;
    }


    public void llenarTabla() {
        Cursor cursor = formulario.getCoordinador().getDatabasePaloteo().getAllData();
        while (cursor.moveToNext()) {
            ArrayList<String> elementos = new ArrayList<String>();
            elementos.add(cursor.getString(0));
            elementos.add(cursor.getString(1));
            elementos.add(cursor.getString(2));
            elementos.add(cursor.getString(3));
            elementos.add(cursor.getString(4));
            elementos.add(cursor.getString(5));
            elementos.add(cursor.getString(6));
            elementos.add(cursor.getString(7));
            elementos.add(cursor.getString(8));
            elementos.add(cursor.getString(9));
            formulario.getTabla().agregarFilaTabla(elementos);


        }


    }


    public void limpiarCampos() {
        formulario.getEditTextNumeroCuenta().setText("");
        formulario.getEditTextNodo().setText("");
        formulario.getEditTextCiudad().setText("");
        formulario.getEditTextCMTS().setText("");
        formulario.getEditTextMultiDescripcion().setText("");
        formulario.getEditTextOtros1().setText("");
        formulario.getEditTextOtros2().setText("");
        formulario.getEditTextOtros3().setText("");
        formulario.getEditTextOtros4().setText("");

    }


    public void MostarPantalla(Cursor res) {


        if (res.getCount() == 0) {
            // show message
            showMessage("Alert", "Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

            buffer.append("id :" + res.getString(0) + "\n");

            buffer.append("cuenta: " + res.getString(1) + "\n");

            buffer.append("nodo :" + res.getString(2) + "\n");

            buffer.append("ciudad :" + res.getString(3) + "\n");

            buffer.append("CMTS :" + res.getString(4) + "\n");

            buffer.append("descripcion :" + res.getString(5) + "\n");


        }
        res.close();
        // Show all data
        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(formulario);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public Uri generarDocumento() {
        try {


            SQLiteToExcel excel = new SQLiteToExcel(formulario.getApplicationContext(),
                    DatabasePaloteo.DATABASE_NAME, formulario.getExternalCacheDir().toString(), formulario);

            String nameArchivo = new String("Paloteo.xls");

            excel.exportAllTables(nameArchivo, new SQLiteToExcel.ExportListener() {
                @Override
                public void onStart() {
                    Toast.makeText(formulario.getApplicationContext(), "iniciado...", Toast.LENGTH_SHORT).show();
                    Log.i("logica/gDonStart", "inicia creacion de archivo Exel");
                }

                @Override
                public void onCompleted(String filePath) {
                    Toast.makeText(formulario.getApplicationContext(), "...finalizado", Toast.LENGTH_SHORT).show();
                    Log.i("logica/gDonCompleted", filePath);
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(formulario.getApplicationContext(), "erorr", Toast.LENGTH_SHORT).show();
                    Log.e("logica/gDonError", e.toString());
                }
            });


            uri = FileProvider.getUriForFile(formulario.getApplicationContext(),
                    formulario.getApplicationContext().getPackageName() + ".provider",
                    new File(formulario.getExternalCacheDir().toString() + "/" + "paloteo.xls"));
            //uri=Uri.parse("content://com.emprosoft7head.paloteoaib.provider/external_files/Android/data/com.emprosoft7head.paloteoaib/cache/paloteo");

        } catch (Exception e) {

            Toast.makeText(formulario.getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            Log.e("logica/generarDocumento", e.toString());
        }
        Log.i("logica/generarDocumento", uri.toString());
        return uri;
    }

    public void compartirDocumento(Uri uri) {

        String[] mailto = {"wtavera@aib.com.co"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reporte diaro de palteo");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hola wendy te envio el paloteo del dia , Gracias. ");
        emailIntent.setType("application/vnd.ms-excel");
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

        formulario.startActivity(Intent.createChooser(emailIntent, "Send email using:"));
    }

    private String[][] createArrayDatos(Cursor cursor) {


        String[][] datos = new String[cursor.getCount() + 1][cursor.getColumnCount()];


        for (int x = 0; x < cursor.getColumnCount(); x++) {
            datos[0][x] = cursor.getColumnName(x);
        }
        try {


            for (int j = 0; j < cursor.getCount(); j++) {
                cursor.moveToPosition(j);
                for (int x = 0; x < cursor.getColumnCount(); x++) {

                    datos[j + 1][x] = cursor.getString(x);

                }

            }


        } catch (Exception e) {
            Toast.makeText(formulario.getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

        cursor.close();

        return datos;
    }





    public com.emprosoft7head.manejoDatos.paloteoVo getPaloteoVo() {
        return paloteoVo;
    }

    public void setPaloteoVo(com.emprosoft7head.manejoDatos.paloteoVo paloteoVo) {
        this.paloteoVo = paloteoVo;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }


    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
