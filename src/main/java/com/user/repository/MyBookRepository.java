package com.user.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.user.model.MyBookList;
 
@Repository
public interface MyBookRepository extends JpaRepository<MyBookList, Integer> {

 
}