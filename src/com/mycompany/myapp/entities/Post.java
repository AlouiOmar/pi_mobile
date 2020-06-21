/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author USER
 */
public class Post {
    private  int id;
    private String title;
    private String description;
    private String photo;
    private String postdate;
    private int creator;
    private int rating;
    private int nbrpost;
    private ImageView img;
    private fos_user user;
    public Post() {
     
    }
    
    
    

    public Post(String title, String description, String photo, String postdate, int creator, int rating, int nbrpost, ImageView img) {
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.postdate = postdate;
        this.creator = creator;
        this.rating = rating;
        this.nbrpost = nbrpost;
        this.img = img;
    }
    

    public Post(String title, String description, String photo, String postdate, int creator, int rating, int nbrpost) {
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.postdate = postdate;
        this.creator = creator;
        this.rating = rating;
        this.nbrpost = nbrpost;
    }
    
    
    
 public Post(String title, String description, int rating ) {
        this.title = title;
        this.description = description;
        this.rating = rating;
 
    }
 
 public Post(String title, String description, int rating , String photo) {
        this.title = title;
        this.description = description;
        this.rating = rating;
         this.photo = photo;
 
    }
  public Post(String title, String description, int rating ,int creator) {
        this.title = title;
        this.description = description;
        this.rating = rating;
         this.creator= creator;
 
    }
  
  
//    public static int getId() {
//        return id;
//    }
//
//    public static void setId(int id) {
//        Post.id = id;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
 

     public Post(int id ,String title, String description, int rating) {
      this.id =id;
        this.title = title;
        this.description = description;
        this.rating = rating;
 
    }
     
      public Post(String title, String description) {
   //   this.id =id;
        this.title = title;
        this.description = description;
        
 
    }
      
       public Post(int id ,String title, String description, int rating,String photo) {
      this.id =id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.photo=photo;
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNbrpost() {
        return nbrpost;
    }

    public void setNbrpost(int nbrpost) {
        this.nbrpost = nbrpost;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public fos_user getUser() {
        return user;
    }

    public void setUser(fos_user user) {
        this.user = user;
    }
    
    
    
    
    

    @Override
    public String toString() {
        return "Post{" + "title=" + title + ", description=" + description + ", photo=" + photo + ", postdate=" + postdate + ", creator=" + creator + ", rating=" + rating + ", nbrpost=" + nbrpost + '}';
    }  
    
}
