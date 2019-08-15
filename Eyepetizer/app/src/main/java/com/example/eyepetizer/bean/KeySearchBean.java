package com.example.eyepetizer.bean;

public class KeySearchBean {
    String name;
    String title;
    String head;
    String titleImage;
    String description;
    String url;
    String nextpage;
    int total;


    public String getHead() {
        return head;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getNextpage() {
        return nextpage;
    }

    public int getTotal() {
        return total;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNextpage(String nextpage) {
        this.nextpage = nextpage;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
