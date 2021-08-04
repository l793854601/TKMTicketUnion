package com.example.tkmticketunion.model.domain;

import com.example.tkmticketunion.base.BaseModel;

public class GetTicketParam extends BaseModel {

    private String title;

    private String url;

    public GetTicketParam(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
