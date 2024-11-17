package com.example.restaurant.Domain;

public class CategoryDomain {
    private String title;
    private String pic;

    // Construtor
    public CategoryDomain(String title, String pic) {
        this.title = title;
        this.pic = pic;
    }

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
