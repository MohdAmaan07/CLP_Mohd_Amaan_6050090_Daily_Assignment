package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Trainee;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

}
