package com.example.pc_gaming.libreriabbdd;

import java.io.Serializable;

/**
 * Created by PC_Gaming on 17/02/2018.
 */

public class Libro implements Serializable{

    private String titulo, sinopsis, autor;
    private int numPaginas;

    public Libro(String titulo, String autor, String sinopsis, int numPaginas){
        this.titulo = titulo;
        this.autor = autor;
        this.sinopsis = sinopsis;
        this.numPaginas = numPaginas;
    }

    // METODO GET Y SET
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }
}
