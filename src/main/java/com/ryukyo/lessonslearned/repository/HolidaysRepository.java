package com.ryukyo.lessonslearned.repository;

import com.ryukyo.lessonslearned.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidaysRepository extends CrudRepository<Holiday, String> {

}
