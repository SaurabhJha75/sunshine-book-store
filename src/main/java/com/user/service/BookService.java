package com.user.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.user.model.Book;
import com.user.repository.BookRepository;
 
 
@Service
public class BookService {
 
	@Autowired
	private BookRepository bookRepo;
	public void save(Book b) {
		bookRepo.save(b);
	}
	public List<Book> getAllBooks(){
		return bookRepo.findAll();
	}
	public Book getBookById(int bId) {
		return bookRepo.findById(bId).get();
	}
	public void deleteById(int bId) {
		bookRepo.deleteById(bId);
	}
}