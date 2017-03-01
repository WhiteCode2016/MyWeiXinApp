package com.weixin.dto.cookery;

import java.util.List;

/**
 * Created by White on 2017/3/1.
 */
public class CookeryData {
    private int id;
    private String title;
    private String tags;
    private String imtro;
    private String ingredients;
    private List<String> albums;
    List<CookerySteps> steps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImtro() {
        return imtro;
    }

    public void setImtro(String imtro) {
        this.imtro = imtro;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }

    public List<CookerySteps> getSteps() {
        return steps;
    }

    public void setSteps(List<CookerySteps> steps) {
        this.steps = steps;
    }
}
