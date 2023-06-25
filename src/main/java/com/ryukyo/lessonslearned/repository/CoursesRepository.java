package com.ryukyo.lessonslearned.repository;

import com.ryukyo.lessonslearned.model.Courses;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "courses")
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

    List<Courses> findByOrderByNameDesc();

    List<Courses> findByOrderByName();
}
