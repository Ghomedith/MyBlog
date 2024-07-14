package com.wildcodeschool.MyBlog.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wildcodeschool.MyBlog.model.Article;
import com.wildcodeschool.MyBlog.repository.ArticleRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/articles")
public class ArticleController {

    public final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

// Recuperer tous les articles sous forme de liste d'objets

    @GetMapping("")
    public ResponseEntity<List<Article>> getAllArticles() {

        List<Article> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);

    }

// Recuperer un article par son id

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id){

        Optional<Article> optionalArticle  = articleRepository.findById(id);
        if(optionalArticle.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Article article = optionalArticle.get();
        return ResponseEntity.ok(article);
    }

    //Création d'un article :

    @PostMapping("")

    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        article.setCreateAt(LocalDateTime.now());
        article.setUpdateAt(LocalDateTime.now());
        Article saveArticle = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveArticle);
    }
    
    //Mise à jour d'un article

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {

        Optional<Article> optionalArticle = articleRepository.findById(id);

        if(optionalArticle.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Article article = optionalArticle.get();
        article.setTitle(articleDetails.getTitle());
        article.setContent(articleDetails.getContent());
        article.setUpdateAt(LocalDateTime.now());

        Article updateArticle = articleRepository.save(article);
        return ResponseEntity.ok(updateArticle);
    }

    // Suppresion d'un  article

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Article article = optionalArticle.get();
        articleRepository.delete(article);
        return ResponseEntity.noContent().build();
    }
    //
    @GetMapping("/search-by-content")
    public List<Article> getArticlesByContent(@RequestParam String content) {
        return articleRepository.findByContentContaining(content);
    }

    //
    @GetMapping("/created-after")
    public List<Article> getArticlesCreatedAtAfter(@RequestParam LocalDate date) {
        return articleRepository.findByCreatedAtAfter(date);
    }
    
    //

    @GetMapping("/last-five")
    public List<Article> getFiveLastArticles() {
        return articleRepository.findTop5ByOrderByCreatedAtDesc();
    }
}
