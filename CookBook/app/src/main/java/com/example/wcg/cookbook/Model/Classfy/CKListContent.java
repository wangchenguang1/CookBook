package com.example.wcg.cookbook.Model.Classfy;

import com.example.wcg.cookbook.Http.HttpClient;

/**
 * Created by wcg on 16/3/17.
 */
public class CKListContent {

    private String id;   // 菜谱id
    private String keywords;
    private String count;
    private String food;
    private String description;
    private String name;
    private String img;
    private String fcount;
    private String images;
    private String rcount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFcount() {
        return fcount;
    }

    public void setFcount(String fcount) {
        this.fcount = fcount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {

        this.img = img;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getRcount() {
        return rcount;
    }

    public void setRcount(String rcount) {
        this.rcount = rcount;
    }

    @Override
    public String toString() {
        return "CKListContent{" +
                "id='" + id + '\'' +
                ", keywords='" + keywords + '\'' +
                ", count='" + count + '\'' +
                ", food='" + food + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", fcount='" + fcount + '\'' +
                ", images='" + images + '\'' +
                ", rcount='" + rcount + '\'' +
                '}';
    }
}
