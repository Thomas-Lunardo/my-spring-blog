package org.wildcodeschool.myblog.dto;

import org.hibernate.validator.constraints.URL;
import org.wildcodeschool.myblog.model.Image;

import java.util.List;

public class ImageDTO {

    private Long id;

    @URL(message = "L'URL de l'image doit être valide")
    private String url;

    private List<Long> articlesIds;

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