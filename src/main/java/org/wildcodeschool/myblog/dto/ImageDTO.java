package org.wildcodeschool.myblog.dto;

import org.wildcodeschool.myblog.model.Image;

import java.util.List;

public class ImageDTO {

    private Long id;
    private String url;
    private List<Long> articlesIds;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Long> getArticlesIds() {
        return articlesIds;
    }

    public void setArticlesIds(List<Long> articlesIds) {
        this.articlesIds = articlesIds;
    }
}