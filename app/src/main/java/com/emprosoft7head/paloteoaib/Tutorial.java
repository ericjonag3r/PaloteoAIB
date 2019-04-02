package com.emprosoft7head.paloteoaib;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class Tutorial extends AppCompatActivity implements OnSeekBarChangeListener, View.OnScrollChangeListener{

   private SeekBar seekBar;
   private ImageButton imageButtonAdelante;
   private ScrollView scrollViewTutorial;
   private int contadorScroll=1;
   private int contadorSeeekBar=0;
   private static String TUTORIAL_KEY ="tutorial";

    protected final static int TUTORIAL_SUPERADO=100;
    protected final static int TUTORIAL_VISTO=50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        imageButtonAdelante=(ImageButton)findViewById(R.id.imageButtonAdelante);
        scrollViewTutorial =(ScrollView)findViewById(R.id.scrollViewTutorial);
        scrollViewTutorial.setOnScrollChangeListener(this);
        seekBar =(SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        guardarTutorial(getApplicationContext(), TUTORIAL_VISTO);



    }
        //----------------------metodos de SeekBarr--------------------------------------------
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
  try{

        switch( i ) {
            case 0:
                scrollViewTutorial.requestChildFocus(findViewById(R.id.imageButton1), findViewById(R.id.imageButton1));
                break;
            case 1:
                scrollViewTutorial.requestChildFocus(findViewById(R.id.imageButton2), findViewById(R.id.imageButton2));
                break;
            case 2:
                scrollViewTutorial.requestChildFocus(findViewById(R.id.imageButton3), findViewById(R.id.imageButton3));
                break;
            case 3:
                scrollViewTutorial.requestChildFocus(findViewById(R.id.imageButton4), findViewById(R.id.imageButton4));
                break;
            case 4:
                scrollViewTutorial.requestChildFocus(findViewById(R.id.imageButton5), findViewById(R.id.imageButton5));

                imageButtonAdelante.setVisibility(View.VISIBLE);
                break;
                default:

}

  }catch(Exception e){
    Toast.makeText(this,"debes ver el tutorial completo",Toast.LENGTH_SHORT).show();

  }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
//-----------------------------------------------------------------------------------------
//-------------eventos de Scroll----------------------------------------------------------
    @Override
    public void onScrollChange(View view, int i, int i1, int i2, int i3) {



        if (i1>0 && i1<100) {
            seekBar.setProgress(0);
        }

        if (i1>477 && i1<577) {
            seekBar.setProgress(1);
        }

        if (i1>1109 && i1<1209) {
            seekBar.setProgress(2);
        }

        if (i1>1741 && i1<1841) {
            seekBar.setProgress(3);
        }

        if (i1>2337 && i1<2437) {
            seekBar.setProgress(4);
        }


    }


//--------------------------------------------------------------------------------------------------------


    public void siguienteActivity(View view) {
        guardarTutorial(getApplicationContext(),TUTORIAL_SUPERADO);
        Intent ir = new Intent(Tutorial.this,Formulario.class);
        startActivity(ir);
    }

    public void anteriorActivity(View view) {
        Intent i =new Intent(Tutorial.this,IngresarContactosReportes.class);
        startActivity(i);
    }


    public void verPoweredBy(View view) {
        Intent ir = new Intent(Tutorial.this,Emprosoft.class);
        startActivity(ir);
    }

    public void guardarTutorial(Context context, int valor) {
        SharedPreferences settings = context.getSharedPreferences(TUTORIAL_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putInt(TUTORIAL_KEY, valor);
        editor.commit();
    }


}


