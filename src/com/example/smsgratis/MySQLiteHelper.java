package com.example.smsgratis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_NUMERO = "tblnumeros";
    public static final String COLUMN_NUMERO = "numero";
    

    public static final String TABLE_MENSAJES= "tblmensajes";    
    public static final String COLUMN_ID = "id";    
    public static final String COLUMN_NUMEROMENSAJE = "numero";    
    public static final String COLUMN_MENSAJE = "mensaje";

    private static final String DATABASE_NAME = "mensajes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NUMERO + COLUMN_NUMERO
            + " integer primary key);";
    

    private static final String DATABASE_CREATE2 = "create table "
            + TABLE_MENSAJES + COLUMN_ID 
            + " integer primary key autoincrement"+""+COLUMN_NUMEROMENSAJE  +"integer"+"COLUMN_MENSAJE"+"text"+"Foreing Key references  "+");";

    private static final String ALTER1="ALTER TABLE tblmensajes add foreing key (numero) references tblnumeros(numero)";
        
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(DATABASE_CREATE2);
        database.execSQL(ALTER1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NUMERO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENSAJES);
        onCreate(db);
    }
}