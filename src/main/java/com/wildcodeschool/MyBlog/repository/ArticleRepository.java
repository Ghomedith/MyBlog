package com.wildcodeschool.MyBlog.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wildcodeschool.MyBlog.model.Article;

//un repository est une interface qui permet de définir les méthodes d'accès aux données pour une entité.

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByContentContaining(String content);
    List<Article> findByCreatedAtAfter(LocalDate date);
    List<Article> findTop5ByOrderByCreatedAtDesc();
}
