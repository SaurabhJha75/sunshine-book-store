package com.user.model;
 
import jakarta.persistence.Entity;

import jakarta.persistence.Id;

import jakarta.persistence.Table;
 
@Entity

@Table(name="MyBooks")

public class MyBookList {
 
	@Id

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

	public MyBookList(int bookId, String bookName, String bookAuthor, double bookPrice) {

		super();

		this.bookId = bookId;

		this.bookName = bookName;

		this.bookAuthor = bookAuthor;

		this.bookPrice = bookPrice;

	}

	public MyBookList() {

		super();

	}


}
