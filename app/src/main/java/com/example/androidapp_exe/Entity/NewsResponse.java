package com.example.androidapp_exe.Entity;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import lombok.AllArgsConstructor;
import lombok.ToString;


public class NewsResponse {
    private String status;
    private String totalResult;
    private ArrayList<News> articles;

    public NewsResponse(String status, String totalResult, ArrayList<News> articles) {
        this.status = status;
        this.totalResult = totalResult;
        this.articles = articles;
    }

    public void setArticles(ArrayList<News> articles) {
        this.articles = articles;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }

    public String getStatus() {
        return status;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public ArrayList<News> getArticles() {
        return articles;
    }
}
