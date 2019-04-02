package com.emprosoft7head.manejoDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseNodos extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "nodoscmts.db";
    public static final String TABLE_NAME = "nodos_table";
    
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Nodo";
    public static final String COL_3 = "Ciudad";
    public static final String COL_4 = "CMTS";


    private int cantidadFilas;
    private int cantidadColumnas;

    public DatabaseNodos(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NODO TEXT,CIUDAD TEXT,CMTS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(paloteoVo paloteoVo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,paloteoVo.getNODO()+"");
        contentValues.put(COL_3,paloteoVo.getCIUDAD()+"");
        contentValues.put(COL_4,paloteoVo.getCMTS()+"");

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
                    "NODO TEXT,CIUDAD TEXT,CMTS TEXT)");
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

}



