package com.example.pc_gaming.libreriabbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PC_Gaming on 16/02/2018.
 */

class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // AQUI CREO LA BBDD

        sqLiteDatabase.execSQL("CREATE TABLE Libro( " +
                              "idLibro int PRIMARY KEY, " +
                              "titulo text, " +
                              "autor text, " +
                              "sinopsis text, " +
                              "numPaginas int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
