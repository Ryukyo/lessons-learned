package com.ryukyo.lessonslearned.repository;

import com.ryukyo.lessonslearned.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {

}
