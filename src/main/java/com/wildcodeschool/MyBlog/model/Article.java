package com.wildcodeschool.MyBlog.model;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// permet de generer automatiquement les getters et setters
@Data 
@Entity

public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition= "TEXT" )
    private String content;

    @Column(nullable = false, updatable= false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime updateAt;


}
