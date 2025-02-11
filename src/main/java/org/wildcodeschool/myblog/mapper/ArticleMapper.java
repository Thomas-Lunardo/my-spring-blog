package org.wildcodeschool.myblog.mapper;

import org.springframework.stereotype.Component;
import org.wildcodeschool.myblog.dto.ArticleAuthorDTO;
import org.wildcodeschool.myblog.dto.ArticleDTO;
import org.wildcodeschool.myblog.dto.AuthorDTO;
import org.wildcodeschool.myblog.model.Article;
import org.wildcodeschool.myblog.model.Image;

import java.util.stream.Collectors;

@Component
public class ArticleMapper {

    public ArticleDTO convertToDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setUpdatedAt(article.getUpdatedAt());
        if (article.getCategory() != null) {
            articleDTO.setCategoryName(article.getCategory().getName());
        }
        if (article.getImages() != null) {
            articleDTO.setImageUrls(article.getImages().stream().map(Image::getUrl).collect(Collectors.toList()));
        }
        if (article.getArticleAuthors() != null) {
            articleDTO.setArticleAuthorDTOS(article.getArticleAuthors().stream()
                    .filter(articleAuthor -> articleAuthor.getArticle() != null)
                    .map(articleAuthor -> {
                        ArticleAuthorDTO articleAuthorDTO = new ArticleAuthorDTO();
                        articleAuthorDTO.setId(articleAuthor.getArticle().getId());
                        articleAuthorDTO.setAuthorId(articleAuthor.getAuthor().getId());
                        articleAuthorDTO.setArticleId(articleAuthor.getArticle().getId());
                        articleAuthorDTO.setContribution(articleAuthor.getContribution());
                        return articleAuthorDTO;
                    })
                    .collect(Collectors.toList()));
        }
        return articleDTO;
    }
}
