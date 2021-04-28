package com.example.prova02.model;

public class Filmes {
    private int id;
    private String titulo;
    private int duracao;
    private Categorias categoria;
    private int classificacao;

    public Filmes() {    }

    public Filmes(int id, String titulo, int duracao, Categorias categoria, int classificacao) {
        this.id = id;
        this.titulo = titulo;
        this.duracao = duracao;
        this.categoria = categoria;
        this.classificacao = classificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
}
