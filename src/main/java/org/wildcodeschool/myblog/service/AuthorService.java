package org.wildcodeschool.myblog.service;

import org.springframework.stereotype.Service;
import org.wildcodeschool.myblog.dto.AuthorDTO;
import org.wildcodeschool.myblog.mapper.AuthorMapper;
import org.wildcodeschool.myblog.model.Article;
import org.wildcodeschool.myblog.model.ArticleAuthor;
import org.wildcodeschool.myblog.model.Author;
import org.wildcodeschool.myblog.repository.ArticleAuthorRepository;
import org.wildcodeschool.myblog.repository.ArticleRepository;
import org.wildcodeschool.myblog.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final ArticleRepository articleRepository;
    private final ArticleAuthorRepository articleAuthorRepository;

    public AuthorService(
            AuthorRepository authorRepository,
            AuthorMapper authorMapper,
            ArticleRepository articleRepository,
            ArticleAuthorRepository articleAuthorRepository
    ) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.articleRepository = articleRepository;
        this.articleAuthorRepository = articleAuthorRepository;
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(authorMapper::convertToDTO).collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElse(null);

        if (author == null) {
            return null;
        }

        return authorMapper.convertToDTO(author);
    }

    public AuthorDTO createAuthor(Author author) {

        author.setFirstname(author.getFirstname());
        author.setLastname(author.getLastname());

        Author savedAuthor = authorRepository.save(author);

        if (author.getArticleAuthors() != null) {
            for (ArticleAuthor articleAuthor : author.getArticleAuthors()) {
                Article article = articleAuthor.getArticle();
                article = articleRepository.findById(article.getId()).orElse(null);
                if (article == null) {
                    return null;
                }

                articleAuthor.setArticle(article);
                articleAuthor.setAuthor(savedAuthor);
                articleAuthor.setContribution(articleAuthor.getContribution());

                articleAuthorRepository.save(articleAuthor);
            }
        }

        return authorMapper.convertToDTO(savedAuthor);
    }

    public AuthorDTO updateAuthor(Long id, Author authorDetails) {

        Author author = authorRepository.findById(id).orElse(null);

        if (author == null) {
            return null;
        }

        author.setFirstname(authorDetails.getFirstname());
        author.setLastname(authorDetails.getLastname());

        if (authorDetails.getArticleAuthors() != null) {
            for (ArticleAuthor oldArticleAuthor : author.getArticleAuthors()) {
                articleAuthorRepository.delete(oldArticleAuthor);
            }

            List<ArticleAuthor> updatedArticleAuthors = new ArrayList<>();

            for (ArticleAuthor articleAuthorDetails : authorDetails.getArticleAuthors()) {
                Article article = articleAuthorDetails.getArticle();
                article = articleRepository.findById(author.getId()).orElse(null);
                if (article == null) {
                    return null;
                }

                ArticleAuthor newArticleAuthor = new ArticleAuthor();
                newArticleAuthor.setAuthor(author);
                newArticleAuthor.setArticle(article);
                newArticleAuthor.setContribution(articleAuthorDetails.getContribution());

                updatedArticleAuthors.add(newArticleAuthor);
            }

            for (ArticleAuthor articleAuthor : updatedArticleAuthors) {
                articleAuthorRepository.save(articleAuthor);
            }

            author.setArticleAuthors(updatedArticleAuthors);
        }

        Author updatedAuthor = authorRepository.save(author);

        return authorMapper.convertToDTO(updatedAuthor);
    }

    public boolean deleteAuthor(Long id) {

        Author author = authorRepository.findById(id).orElse(null);

        if (author == null) {
            return false;
        }

        articleAuthorRepository.deleteAll(author.getArticleAuthors());
        authorRepository.delete(author);

        return true;
    }
}
