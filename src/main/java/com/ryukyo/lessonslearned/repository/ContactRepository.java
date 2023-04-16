package com.ryukyo.lessonslearned.repository;

import com.ryukyo.lessonslearned.model.Contact;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer>,
    CrudRepository<Contact, Integer> {

  List<Contact> findByStatus(String status);

  Page<Contact> findByStatus(String status, Pageable pageable);

  @Transactional
  @Modifying
  @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
  int updateStatusById(String status, int id);

  Page<Contact> findOpenMsgs(@Param("status") String status, Pageable pageable);

  @Transactional
  @Modifying
  int updateMsgStatus(String status, int id);

  @Query(nativeQuery = true)
  Page<Contact> findOpenMsgsNative(@Param("status") String status, Pageable pageable);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int updateMsgStatusNative(String status, int id);

}
