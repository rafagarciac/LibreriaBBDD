package com.example.pc_gaming.libreriabbdd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by PC_Gaming on 17/02/2018.
 */

public class AdaptadorLibros extends BaseAdapter{

    Context context;
    ArrayList<Libro> libros;

    public AdaptadorLibros(Context context, ArrayList<Libro> libros) {
        this.context = context;
        this.libros = libros;
    }

    @Override
    public int getCount() {
        return libros.size();
    }

    @Override
    public Object getItem(int i) {
        return libros.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View vista = view;
        LayoutInflater inflate = LayoutInflater.from(context);
        vista = inflate.inflate(R.layout.libro_item, null);

        TextView tvTitulo, tvAutor, tvSinopsis, tvNumPaginas;
        tvTitulo = vista.findViewById(R.id.tvTituloItem);
        tvAutor = vista.findViewById(R.id.tvAutorItem);
        tvSinopsis= vista.findViewById(R.id.tvSinopsisItem);
        tvNumPaginas= vista.findViewById(R.id.tvNumPaginasItem);

        //Toast.makeText(context, libros.get(i).getTitulo().toString() + ", " + libros.get(i).getAutor().toString() + ", " + libros.get(i).getSinopsis().toString() + ", " + libros.get(i).getNumPaginas(), Toast.LENGTH_SHORT).show();

        tvTitulo.setText(libros.get(i).getTitulo().toString());
        tvAutor.setText(libros.get(i).getAutor().toString());
        tvSinopsis.setText(libros.get(i).getSinopsis().toString());
        tvNumPaginas.setText(String.valueOf(libros.get(i).getNumPaginas()));

        return vista;
    }
}
