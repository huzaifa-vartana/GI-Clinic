package com.example.adminpanel;

import com.google.firebase.database.PropertyName;

public class ModelClassSliders {

    private String Images, Link;

    public ModelClassSliders() {
    }

    public ModelClassSliders(String images, String link) {
        Images = images;
        Link = link;
    }

    @PropertyName("Images")
    public String getImages() {
        return Images;
    }

    @PropertyName("Images")
    public void setImages(String images) {
        Images = images;
    }

    @PropertyName("Link")
    public String getLink() {
        return Link;
    }

    @PropertyName("Link")
    public void setLink(String link) {
        Link = link;
    }
}
