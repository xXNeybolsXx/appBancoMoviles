package com.example.appbancobr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqlBanco extends SQLiteOpenHelper {

    //Definicion De Las Tablas De La BDs
    String tblcustomer = "CREATE TABLE customer(email text primary key, name text, password text, rol text)";
    String tblaccount = "CREATE TABLE account(accountnumber integer primary key autoincrement, email text,date text, balance int)";
    String tbltranstype = "CREATE TABLE transtype(idtranstype text primary key, description text)";
    String tbltransactionc = "CREATE TABLE transactionc(accountnumber int, idtranstype text, datetrans text, amount int)";


    public sqlBanco(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Generar Las Instrucciones Para Crear Las Tablas
        db.execSQL(tblcustomer);
        db.execSQL(tblaccount);
        db.execSQL(tbltranstype);
        db.execSQL(tbltransactionc);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Actualizar Las Tablas En SQLite
        db.execSQL("DROP TABLE customer");
        db.execSQL(tblcustomer);
        db.execSQL("DROP TABLE account");
        db.execSQL(tbltranstype);
        db.execSQL("DROP TABLE transtype");
        db.execSQL(tblcustomer);
        db.execSQL("DROP TABLE transactionc");
        db.execSQL(tbltransactionc);

    }
}
