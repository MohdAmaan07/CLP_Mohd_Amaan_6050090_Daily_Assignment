package com.cg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepo extends JpaRepository<com.cg.entities.Movie, Long> {
    List<com.cg.entities.Movie> findByGenre(String genre);
}