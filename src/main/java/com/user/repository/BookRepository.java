package com.user.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.user.model.Book;
 
 
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
 
}