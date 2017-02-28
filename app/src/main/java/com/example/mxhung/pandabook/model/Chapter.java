package com.example.mxhung.pandabook.model;

/**
 * Created by MXHung on 2/20/2017.
 */

public class Chapter {
    private int id;
    private int id_book;
    private String name;
    private String detail;

    public Chapter() {
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
