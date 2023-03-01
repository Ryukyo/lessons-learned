package com.ryukyo.lessonslearned.repository;


import com.ryukyo.lessonslearned.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}