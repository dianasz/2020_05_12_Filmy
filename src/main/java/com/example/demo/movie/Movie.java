package com.example.demo.movie;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiereDate;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Movie() {
    }

    public Movie(Long id, String title, LocalDate premiereDate, String description, Category category) {
        this.id = id;
        this.title = title;
        this.premiereDate = premiereDate;
        this.description = description;
        this.category = category;
    }

    public Movie(String title, LocalDate premiereDate, String description, Category category) {
        this.title = title;
        this.premiereDate = premiereDate;
        this.description = description;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(LocalDate premiereDate) {
        this.premiereDate = premiereDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", title='" + title + '\'' +
                ", premiereDate=" + premiereDate +
                ", description='" + description + '\'' +
                ", category=" + category;
    }

}
