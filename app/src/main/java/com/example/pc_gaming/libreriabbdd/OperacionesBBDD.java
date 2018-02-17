package com.example.pc_gaming.libreriabbdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class OperacionesBBDD extends AppCompatActivity {


    private TextView tvLibroId, tvNumPags;
    private EditText etTitulo, etAutor, etSinopsis, etIdLibro;
    private ImageButton ibtnAdd, ibtnFind, ibtnDelete, ibtnSave;
    private SeekBar seekBarNumPags;

    // BBDD
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operaciones_bbdd);


        tvLibroId = findViewById(R.id.tvLibroId);
        tvNumPags = findViewById(R.id.tvNumPags);

        etTitulo = findViewById(R.id.etTitulo);
        etAutor = findViewById(R.id.etAutor);
        etSinopsis = findViewById(R.id.etSinopsis);
        etIdLibro = findViewById(R.id.etIdLibro);

        ibtnAdd = findViewById(R.id.ibtnAdd);
        ibtnFind = findViewById(R.id.ibtnFind);
        ibtnDelete = findViewById(R.id.ibtnDelete);
        ibtnSave = findViewById(R.id.ibtnSave);

        seekBarNumPags = findViewById(R.id.seekBarNumPags);


        // BBDD
        admin = new AdminSQLiteOpenHelper(getApplicationContext(), "administrador", null, 1);
        bd = admin.getWritableDatabase();

        tvNumPags.setText("" + 0);
        tvLibroId.setText(String.valueOf(sacarMaxIdLibro()));


        seekBarNumPags.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvNumPags.setText("" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ibtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alta();
            }
        });

        ibtnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar(etIdLibro.getText().toString());
            }
        });

        ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrar(etIdLibro.getText().toString());
            }
        });

        ibtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarLibro(etIdLibro.getText().toString());
            }
        });

    }

// AÑADIR
    public void alta() {

        String titulo = etTitulo.getText().toString();
        String autor = etAutor.getText().toString();
        String sinopsis = etSinopsis.getText().toString();
        int numPaginas = Integer.parseInt(tvNumPags.getText().toString());
        int idLibro = sacarMaxIdLibro();

        // COntenedor de Values
        ContentValues registro = new ContentValues();

        //Meto los valores en la BBDD
        registro.put("idLibro", idLibro);
        registro.put("titulo", titulo);
        registro.put("autor", autor);
        registro.put("sinopsis", sinopsis);
        registro.put("numPaginas", numPaginas);

        //Inserto

        bd.insert("libro", null, registro);

        Toast.makeText(this, "Libro insertado correctamente!", Toast.LENGTH_SHORT).show();
        prepararSiguiente();

    }

// ACTUALIZAR
    public void modificarLibro(String idLibro) {

        String titulo = etTitulo.getText().toString();
        String autor = etAutor.getText().toString();
        String sinopsis = etSinopsis.getText().toString();
        int numPaginas = Integer.parseInt(tvNumPags.getText().toString());

        // COntenedor de Values
        ContentValues registro = new ContentValues();

        //Meto los valores en la BBDD
        registro.put("titulo", titulo);
        registro.put("autor", autor);
        registro.put("sinopsis", sinopsis);
        registro.put("numPaginas", numPaginas);

        int libroActualizado = bd.update("libro", registro, "idLibro = " + idLibro, null);
        if (libroActualizado == 1)
            Toast.makeText(this, "El libro con id " + idLibro + "se ha modificado correctamente", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Algo ha salido mal...", Toast.LENGTH_SHORT).show();

    }

// BORRAR
    public void borrar(String idLibro) {

        int elemBorrado = bd.delete("libro", "idLibro = " + idLibro, null);

        if (elemBorrado == 1) {
            Toast.makeText(this, "El libro con id " + idLibro + " ha sido borrado.", Toast.LENGTH_SHORT).show();
            prepararSiguiente();
        }

    }

// BUSCAR LIBRO
    public void buscar(String idLibro) {

        Cursor cursor = bd.rawQuery("SELECT titulo, autor, sinopsis, numPaginas FROM Libro WHERE idLibro = " + idLibro, null);


        if (cursor.moveToFirst()) {

            tvLibroId.setText(idLibro);
            etTitulo.setText(cursor.getString(0));
            etAutor.setText(cursor.getString(1));
            etSinopsis.setText(cursor.getString(2));
            tvNumPags.setText(String.valueOf(cursor.getInt(3)));
            seekBarNumPags.setProgress(cursor.getInt(3));
        }

    }

// METODOS NECCESARIOS
    public void prepararSiguiente() {
        etSinopsis.setText("");
        etAutor.setText("");
        etTitulo.setText("");
        tvLibroId.setText("");
        tvNumPags.setText("0");
        seekBarNumPags.setProgress(0);
        tvLibroId.setText(String.valueOf(sacarMaxIdLibro()));
    }

    public int sacarMaxIdLibro() {

        int maxIdLibro = 0;
        Cursor cursor = bd.rawQuery("SELECT MAX(idLibro) FROM Libro", null);

        while (cursor.moveToNext())
            maxIdLibro = cursor.getInt(0);

        maxIdLibro++;
        return maxIdLibro;

    }


}
