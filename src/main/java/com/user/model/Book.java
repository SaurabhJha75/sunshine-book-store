package com.user.model;
 
import org.springframework.boot.autoconfigure.domain.EntityScan;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "book")
@EntityScan(basePackages = { "com.user.model" })
public class Book {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;
 
	private String bookName;
	private String bookAuthor;
	private double bookPrice;

 
	public int getBookId() {
		return bookId;
	}
 
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
 
	public String getBookName() {
		return bookName;
	}
 
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
 
	public String getBookAuthor() {
		return bookAuthor;
	}
 
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
 
	public double getBookPrice() {
		return bookPrice;
	}
 
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
 
	public Book(int bookId, String bookName, String bookAuthor, double bookPrice) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPrice = bookPrice;
	}
 
	public Book() {
		super();
	}
 
}