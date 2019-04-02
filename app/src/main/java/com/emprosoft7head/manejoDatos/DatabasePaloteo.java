package com.emprosoft7head.manejoDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabasePaloteo extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "paloteo.db";
    public static final String TABLE_NAME = "PALOTEO_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Cuenta";
    public static final String COL_3 = "Nodo";
    public static final String COL_4 = "Ciudad";
    public static final String COL_5 = "CMTS";
    public static final String COL_6 = "Descripcion";
    public static final String COL_7 = "Otros1";
    public static final String COL_8 = "Otros2";
    public static final String COL_9 = "Otros3";
    public static final String COL_10 = "Otros4";

    private int cantidadFilas;
    private int cantidadColumnas;

    public DatabasePaloteo(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CUENTA TEXT,NODO TEXT,CIUDAD TEXT,CMTS TEXT,DESCRIPCION TEXT,OTROS1 TEXT,OTROS2 TEXT,OTROS3 TEXT,OTROS4 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(paloteoVo paloteoVo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,paloteoVo.getCUENTA()+"");
        contentValues.put(COL_3,paloteoVo.getNODO()+"");
        contentValues.put(COL_4,paloteoVo.getCIUDAD()+"");
        contentValues.put(COL_5,paloteoVo.getCMTS()+"");
        contentValues.put(COL_6,paloteoVo.getDESCRIPCION()+"");
        contentValues.put(COL_7,paloteoVo.getOTROS1()+"");
        contentValues.put(COL_8,paloteoVo.getOTROS2()+"");
        contentValues.put(COL_9,paloteoVo.getOTROS3()+"");
        contentValues.put(COL_10,paloteoVo.getOTROS4()+"");
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getUltimaData(int cantidad) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =new String("select * from "+TABLE_NAME+" ORDER BY " +COL_1+" DESC LIMIT "+cantidad);

        Cursor res = db.rawQuery(sql,null);
        return res;
    }

    public boolean updateData(String id,String cuenta,String nodo,String ciudad,String cmts,String descripcion,
                              String otros1,String otros2,String otros3,String otros4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,cuenta);
        contentValues.put(COL_3,nodo);
        contentValues.put(COL_4,ciudad);
        contentValues.put(COL_5,cmts);
        contentValues.put(COL_6,descripcion);
        contentValues.put(COL_7,cmts);
        contentValues.put(COL_8,cmts);
        contentValues.put(COL_9,cmts);
        contentValues.put(COL_10,cmts);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteDataForID (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public boolean deleteData () {
        boolean b=true;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE "+TABLE_NAME);

            db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "CUENTA TEXT,NODO TEXT,CIUDAD TEXT,CMTS TEXT,DESCRIPCION TEXT,OTROS1 TEXT,OTROS2 TEXT,OTROS3 TEXT,OTROS4 TEXT)");
        }catch (Exception e){
            b =false;
        }
        return b;

    }


    public int getCantidadFilas() {
        Cursor cursor =this.getAllData();
        cantidadFilas= cursor.getCount();
        cursor.close();
        return cantidadFilas;
    }

    public String getIDultimaFila() {
        Cursor cursor =this.getAllData();
        cantidadFilas= cursor.getCount();
        cursor.moveToLast();
        String id = cursor.getString(0);
        cursor.close();
        return id;
    }


    public int getCantidadColumnas() {
        Cursor cursor =this.getAllData();
        cantidadColumnas= cursor.getColumnCount();
        cursor.close();
        return cantidadColumnas;
    }


    public static String getCol1() {
        return COL_1;
    }

    public static String getCol2() {
        return COL_2;
    }

    public static String getCol3() {
        return COL_3;
    }

    public static String getCol4() {
        return COL_4;
    }

    public static String getCol5() {
        return COL_5;
    }

    public static String getCol6() {
        return COL_6;
    }

    public static String getCol7() {
        return COL_7;
    }

    public static String getCol8() {
        return COL_8;
    }

    public static String getCol9() {
        return COL_9;
    }

    public static String getCol10() {
        return COL_10;
    }
}



