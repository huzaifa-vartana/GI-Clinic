package com.example.adminpanel;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;

public class treningVideos implements Serializable {
    private String Title, Description, Link, Image;

    public treningVideos() {

    }

    public treningVideos(String title, String desp, String vUrl, String iUrl) {
        this.Title = title;
        this.Description = desp;
        this.Link = vUrl;
        this.Image = iUrl;
    }

    @PropertyName("Title")
    public String getTitle() {
        return Title;
    }

    @PropertyName("Title")
    public void setTitle(String title) {
        Title = title;
    }

    @PropertyName("Description")

    public String getDescription() {
        return Description;
    }

    @PropertyName("Description")
    public void setDescription(String description) {
        Description = description;
    }

    @PropertyName("Link")
    public String getLink() {
        return Link;
    }

    @PropertyName("Link")
    public void setLink(String link) {
        Link = link;
    }

    @PropertyName("Image")
    public String getImage() {
        return Image;
    }

    @PropertyName("Image")
    public void setImage(String image) {
        Image = image;
    }
}
