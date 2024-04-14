package com.user.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
import com.user.model.Book;
import com.user.model.MyBookList;
import com.user.service.BookService;
import com.user.service.MyBookListService;
 
 
@Controller
public class BookController {
 
	@Autowired
	private BookService bookService;
 
	@Autowired
	private MyBookListService myBookService;
 
	/*
	 * @GetMapping("/") public String Home() { return "home1"; }
	 */
 
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}
 
	@GetMapping("/available_books")
	public ModelAndView getAllBooks() {
		List<Book> list = bookService.getAllBooks();
 
		ModelAndView m = new ModelAndView();
		m.setViewName("bookList");
		m.addObject("book", list);
 
		return new ModelAndView("bookList", "book", list);
	}
 
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		this.bookService.save(b);
		return "redirect:/user/available_books";
	}
 
	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		List<MyBookList> list = myBookService.getAllMyBooks();
		model.addAttribute("book", list);
		return "myBooks";
	}
 
	@RequestMapping("/mylist/{id}/")
	public String getMyList(@PathVariable("id") int id) {
		Book b = bookService.getBookById(id);
		MyBookList mb = new MyBookList(b.getBookId(), b.getBookName(), b.getBookAuthor(), b.getBookPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}
 
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Book b = bookService.getBookById(id);
		model.addAttribute("book", b);
		return "bookEdit";
	}
 
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		bookService.deleteById(id);
		return "redirect:/available_books";
	}
 
}