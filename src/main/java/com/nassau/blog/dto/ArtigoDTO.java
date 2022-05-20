package com.nassau.blog.dto;

import java.util.Date;

import net.bytebuddy.asm.Advice.Return;

public class ArtigoDTO {

    private String titulo;
    private String autor;
    private Date date;
    private String texto;
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
}


