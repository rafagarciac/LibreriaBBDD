package com.example.pc_gaming.libreriabbdd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultarBBDD extends AppCompatActivity {

    private ListView lvLibros;
    private ArrayList<Libro> libros;

    // BBDD
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_bbdd);

        lvLibros = findViewById(R.id.lvLibros);
        libros = new ArrayList<Libro>();

        // BBDD
        admin = new AdminSQLiteOpenHelper(getApplicationContext(), "administrador", null, 1);
        bd = admin.getWritableDatabase();


        Cursor cursor = bd.rawQuery("SELECT titulo, autor, sinopsis, numPaginas FROM Libro", null);

        while (cursor.moveToNext()){
            // Meto los libros en un ArrayList
            libros.add(new Libro(cursor.getString(0).toString(), cursor.getString(1).toString(), cursor.getString(2).toString(), cursor.getInt(3)));
        }

        AdaptadorLibros adaptador = new AdaptadorLibros(this, libros);
        lvLibros.setAdapter(adaptador);


        //Toast.makeText(this, "CARGADOS!!", Toast.LENGTH_SHORT).show();


    }
}
