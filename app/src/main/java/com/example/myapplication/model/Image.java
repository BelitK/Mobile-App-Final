package com.example.myapplication.model;


public class Image {

    private int id;

    private String name;

    private String path;

    private String comment;

    public Image(){}

    public Image(int id, String name, String path, String comment) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
