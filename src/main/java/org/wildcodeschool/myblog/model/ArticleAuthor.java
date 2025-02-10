package org.wildcodeschool.myblog.model;

import jakarta.persistence.*;
import org.wildcodeschool.myblog.model.Article;

@Entity
public class ArticleAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String contribution;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

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

    public Article getArticle() {

        return article;
    }

    public void setArticle(Article article) {

        this.article = article;
    }

    public Author getAuthor() {

        return author;
    }

    public void setAuthor(Author author) {

        this.author = author;
    }
}