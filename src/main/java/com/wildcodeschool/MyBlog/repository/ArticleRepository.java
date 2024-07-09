package com.wildcodeschool.MyBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wildcodeschool.MyBlog.model.Article;

//un repository est une interface qui permet de définir les méthodes d'accès aux données pour une entité.

public interface ArticleRepository extends JpaRepository<Article, Long> {}
