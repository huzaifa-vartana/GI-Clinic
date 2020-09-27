package com.example.adminpanel;

import com.google.firebase.database.PropertyName;

public class ModelClassNewDrama {
    private String Title, Image, Episodes, Drama;

    public ModelClassNewDrama() {
    }

    public ModelClassNewDrama(String title, String image, String episodes, String drama) {
        Title = title;
        Image = image;
        Episodes = episodes;
        Drama = drama;
    }

    @PropertyName("Title")
    public String getTitle() {
        return Title;
    }

    @PropertyName("Title")
    public void setTitle(String title) {
        Title = title;
    }

    @PropertyName("Image")
    public String getImage() {
        return Image;
    }

    @PropertyName("Image")
    public void setImage(String image) {
        Image = image;
    }

    @PropertyName("Episodes")
    public String getEpisodes() {
        return Episodes;
    }

    @PropertyName("Episodes")
    public void setEpisodes(String episodes) {
        Episodes = episodes;
    }

    @PropertyName("Drama")
    public String getDrama() {
        return Drama;
    }

    @PropertyName("Drama")
    public void setDrama(String drama) {
        Drama = drama;
    }
}
