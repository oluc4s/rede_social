package com.s2start.matheus.fiveconn.model;

import java.io.Serializable;
import java.util.List;

public class ListaPost implements Serializable {
    private List<Post> post;

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}

