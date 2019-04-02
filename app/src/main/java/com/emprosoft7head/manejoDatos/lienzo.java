package com.emprosoft7head.manejoDatos;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.emprosoft7head.paloteoaib.R;

/**
 * TODO: document your custom
 */
public class lienzo extends  View {
    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable Drawable;


    private String[][]datos=null;
    private String[]datosReportes=null;
    private Canvas canvas ;
    private int x;
    private  int y;



    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    public lienzo(Context context) {
        super(context);

        init(null, 0);
    }

    public lienzo(Context context, String [][] datos, String []datosReportes) {
        super(context);
        this.datos=datos;
        this.datosReportes=datosReportes;
        init(null, 0);
    }

    public lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public lienzo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);




    }

    public void setDatos(String [][]datos){
        this.datos=datos;


    }

    public String [][] getDatos() {

        return datos;
    }

    public void setDatosReportes(String[] datosReportes){

        this.datosReportes=datosReportes;
    }
    public String[] getDatosReportes(){
        return datosReportes;
    }



    private void init(AttributeSet attrs, int defStyle) {


        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(


                attrs, R.styleable.lienzo, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.lienzo_exampleString);
        mExampleColor = a.getColor(
                R.styleable.lienzo_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.lienzo_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.lienzo_exampleDrawable)) {
            Drawable = a.getDrawable(
                    R.styleable.lienzo_exampleDrawable);
            Drawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();

    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas=canvas;

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;



        x =1;
        y=1; //altura

        try {


            int relativoAnchoTamLetra25=13;
            int relativoAnchoTamLetra40=70;

            canvas.drawColor(Color.WHITE);
            Paint pincel1 = new Paint();
            pincel1.setTextSize(10);
            y=30;
            canvas.drawLine(0,0,10000,0,pincel1);
            canvas.drawLine(0,1,10000,1,pincel1);
            for (int contDatosReportes=0;contDatosReportes<datosReportes.length;contDatosReportes++ ) {
                canvas.drawText(datosReportes[contDatosReportes],x, pincel1.getTextSize(), pincel1);
                x=x+datosReportes[contDatosReportes].length()*relativoAnchoTamLetra25;

            }
            canvas.drawLine(0,pincel1.getTextSize()+1,10000,pincel1.getTextSize()+1,pincel1);
            canvas.drawLine(0,pincel1.getTextSize()+2,10000,pincel1.getTextSize()+2,pincel1);

            x=0;
            for(int contFilas=0;contFilas<datos.length;contFilas++){

                y =y+ (int) pincel1.getTextSize();
                x=0;
                for(int contColum=0;contColum<datos[contFilas].length;contColum++){

                    if(!TextUtils.isEmpty(datos[contFilas][contColum])) {
                        if (contFilas == 0) {
                            pincel1.setColor(Color.RED);
                            pincel1.setTextSize(25);
                            pincel1.setLinearText(true);
                            pincel1.setTypeface(Typeface.SANS_SERIF);

                            canvas.drawText(datos[contFilas][contColum], x, y+6, pincel1);
                            pincel1.setColor(Color.BLUE);


                            canvas.drawLine(0, y+5, 10000, y + 12, pincel1);


                            switch (contColum) {
                                case 0:
                                    x = x + 30;

                                    break;

                                case 1:
                                    x = x + 100;
                                    break;

                                case 2:
                                    x = x + 80;
                                    break;
                                case 3:
                                    x = x + 180;
                                    break;

                                case 4:
                                    x = x + 180;
                                    break;

                                case 5:
                                    x = x + 430;

                                    break;
                                case 6:
                                    x = x + 100;

                                    break;
                                case 7:
                                    x = x + 100;

                                    break;
                                case 8:
                                    x = x + 100;

                                    break;
                                case 9:
                                    x = x + 100;

                                    break;
                            }

                        } else {

                            pincel1.setColor(Color.BLACK);
                            pincel1.setTextSize(20);
                            pincel1.setLinearText(true);
                            pincel1.setTypeface(Typeface.SANS_SERIF);
                           if (contColum == 3 && datos[contFilas][contColum].length() > 19) {
                                //ciudad
                                pincel1.setTextSize(15);
                                canvas.drawText(datos[contFilas][contColum].substring(0, 20), x, y, pincel1);

                            }else if (contColum == 4 && datos[contFilas][contColum].length() > 18) {
                                //cmts
                                pincel1.setTextSize(15);
                                canvas.drawText(datos[contFilas][contColum].substring(0, 19), x, y, pincel1);

                            } else if (contColum == 5 && datos[contFilas][contColum].length() > 60) {
                                //descripcion

                                pincel1.setTextSize(15);
                                canvas.drawText(datos[contFilas][contColum].substring(0, 61), x, y, pincel1);

                            } else {
                                canvas.drawText(datos[contFilas][contColum], x, y, pincel1);
                            }

                            pincel1.setColor(Color.BLUE);
                            canvas.drawLine(0, y + 1, 10000, y + 1, pincel1);
                            canvas.drawLine(0, y + 2, 10000, y + 2, pincel1);

                            switch (contColum) {
                                case 0:
                                    x = x + 30;

                                    break;

                                case 1:
                                    x = x + 100;
                                    break;

                                case 2:
                                    x = x + 80;
                                    break;
                                case 3:
                                    x = x + 180;
                                    break;

                                case 4:
                                    x = x + 180;
                                    break;

                                case 5:
                                    x = x + 430;

                                    break;
                                case 6:
                                    x = x + 100;

                                    break;
                                case 7:
                                    x = x + 100;

                                    break;
                                case 8:
                                    x = x + 100;

                                    break;
                                case 9:
                                    x = x + 100;

                                    break;
                            }
                        }
                    }

                }

            }
            pincel1.setColor(Color.BLUE);
            canvas.drawLine(0,y+1,10000,y+1,pincel1);
            canvas.drawLine(0,y+2,10000,y+2,pincel1);
            setCoordenadaY(y+4);

        }catch (Exception e){
            Toast.makeText(getContext(),"no hay datos",Toast.LENGTH_SHORT).show();
            Log.i("lienzo/onDraw/for","no se han pasado datos al view");
        }



        if (Drawable != null) {
            Drawable.setBounds(paddingLeft, paddingTop, paddingLeft + contentWidth, paddingTop + contentHeight);
            Drawable.draw(canvas);
        }


    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return Drawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        Drawable = exampleDrawable;
    }


    public int getCoordenadaX() {
        return x;
    }

    public void setCoordenadaX(int x) {
        this.x = x;
    }


    public int getCoordenadaY() {
        return y;
    }

    public void setCoordenadaY(int y) {
        this.y = y;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
