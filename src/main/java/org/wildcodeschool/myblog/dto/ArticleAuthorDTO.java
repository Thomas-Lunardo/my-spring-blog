package org.wildcodeschool.myblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.wildcodeschool.myblog.model.Article;
import org.wildcodeschool.myblog.model.Author;

import java.util.List;

public class ArticleAuthorDTO {

    private Long id;

    @NotBlank(message = "La contribution de l'auteur ne doit pas être vide")
    private String contribution;
    private Long articleId;

    @NotNull(message = "L'ID de l'auteur ne doit pas être nul")
    @Positive(message = "L'ID de l'auteur doit être un nombre positif")
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