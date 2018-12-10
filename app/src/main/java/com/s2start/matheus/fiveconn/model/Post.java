package com.s2start.matheus.fiveconn.model;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {

    private int id_post;
    private int id_usuario;
    private String titulo;
    private String descricao;
    private String youtube;
    private String data_post;
    private int status;
    private int tipo_post;
    private Usuario user;



    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getData_post() {
        return data_post;
    }

    public void setData_post(String data_post) {
        this.data_post = data_post;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTipo_post() {
        return tipo_post;
    }

    public void setTipo_post(int tipo_post) {
        this.tipo_post = tipo_post;
    }
}
