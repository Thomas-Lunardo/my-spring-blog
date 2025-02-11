package org.wildcodeschool.myblog.dto;

import org.wildcodeschool.myblog.model.Article;
import org.wildcodeschool.myblog.model.Author;

import java.util.List;

public class ArticleAuthorDTO {

    private Long id;
    private String contribution;
    private Long articleId;
    private Long authorId;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getContribution() {

        return contribution;
    }

    public void setContribution(String contribution) {

        this.contribution = contribution;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}