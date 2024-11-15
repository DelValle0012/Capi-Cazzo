package com.example.restaurant.Domain;

import com.example.restaurant.databinding.ActivityLoginBinding;
import com.example.restaurant.databinding.FragmentHomeBinding;

public class CategoryDomain  {
    private FragmentHomeBinding binding;

    private String title;
    private String pic;

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

    public CategoryDomain(String tittle, String pic)
    {
        this.title = tittle;
        this.pic = pic;
    }
}
