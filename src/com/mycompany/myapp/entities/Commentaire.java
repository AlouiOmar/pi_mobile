/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author USER
 */
public class Commentaire {
    
    
    int id;
    int post_id;
    int user_id; 
    String content;

    public Commentaire(int id, int post_id, int user_id, String content) {
        this.id = id;
        this.post_id = post_id;
        this.user_id = user_id;
        this.content = content;
    }

    public Commentaire(int post_id, int user_id, String content) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.content = content;
    }

    public Commentaire(String content) {
        this.content = content;
    }
    
    public Commentaire(int id, String content) {
        this.id = id;
       
        this.content = content;
    }

    public Commentaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "content=" + content + '}';
    }

    
    
    
}
    

