package com.example.demo.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.*;

@Repository
public class MovieRepository {
    private List<Movie> movies;
    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private EntityTransaction transaction;

    @Autowired
    public MovieRepository(EntityManagerFactory entityManagerFactory) {
        this.factory = entityManagerFactory;
        this.entityManager = factory.createEntityManager();
        this.transaction = entityManager.getTransaction();
    }

    public MovieRepository(List<Movie> movies) {
        this.movies = movies;
    }

    public void save(Movie movie) {
        transaction.begin();
        entityManager.persist(movie);
        transaction.commit();
    }

    public List<Movie> findAll() {
        transaction.begin();
        Query query = entityManager.createQuery("select m from Movie m");
        List resultList = query.getResultList();
        transaction.commit();
        return resultList;
    }

    public Movie findByTitle(String title) {
        transaction.begin();
        Query query = entityManager.createQuery("select m from Movie m where m.title='" + title + "'");
        Movie movie = null;
        for (Object m : query.getResultList()) {
            movie = (Movie) m;
        }
        transaction.commit();
        return movie;
    }

    public List<Movie> findByCategory(Category category) {
        transaction.begin();
        Query query = entityManager.createQuery("select m from Movie m where m.category='" + category + "'");
        List resultList = query.getResultList();
        transaction.commit();
        return resultList;
    }
}
