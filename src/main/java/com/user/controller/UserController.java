package com.user.controller;
 
import java.security.Principal;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
import com.user.model.Book;
import com.user.model.MyBookList;
import com.user.model.UserDtls;
import com.user.repository.UserRepository;
import com.user.service.BookService;
import com.user.service.MyBookListService;
 
import jakarta.servlet.http.HttpSession;
 
@Controller
@RequestMapping("/user")
public class UserController {
 
	@Autowired
	private BookService bookService;
	@Autowired
	private MyBookListService mybooklistService;
	@Autowired
	private MyBookListService myBookService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
 
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		String email = p.getName();
		UserDtls user = userRepo.findByEmail(email);
 
		m.addAttribute("user", user);
 
	}
 
	@GetMapping("/")
	public String home() {
		return "user/home";
	}
	//Book controller handler
	@GetMapping("/book_register")
	public String bookRegister() {
		return "user/bookRegister";
	}
 
	@GetMapping("/available_books")
	public ModelAndView getAllBooks() {
		List<Book> list = bookService.getAllBooks();
		/*
		 * ModelAndView m = new ModelAndView(); m.setViewName("bookList");
		 * m.addObject("book", list);
		 */
		return new ModelAndView("user/bookList", "book", list);
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
		return "user/myBooks";
	}
 
	@RequestMapping("/mylist/{id}/")
	public String getMyList(@PathVariable("id") int id) {
		Book b = bookService.getBookById(id);
		MyBookList mb = new MyBookList(b.getBookId(), b.getBookName(), b.getBookAuthor(), b.getBookPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/user/my_books";
	}
 
	//handler for editing the book
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Book b = bookService.getBookById(id);
		model.addAttribute("book", b);
		return "user/bookEdit";
	}
 
	//deleting the book from the MyCart
	@RequestMapping("/deleteMylist/{id}")
	public String deleteMyList(@PathVariable("id") int id) {
		mybooklistService.deleteById(id);
		return "redirect:/user/my_books";
	}
	//deleting the book from available books
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		bookService.deleteById(id);
		return "redirect:/user/available_books";
	}

	//handler for view profile
	@GetMapping("/yourprofile")
	public String viewProfile(Model model, Principal p) {
		//fetching the details of loggedIn user
		String email = p.getName();
		UserDtls user = this.userRepo.findByEmail(email);
		model.addAttribute("user", user);
		model.addAttribute("title", user.getFullname() );
		return "user/view_profile";
	}
 
	//handler for change password
	@GetMapping("/changepassword")
	public String loadChangePassword() {
		return "user/change_password";
	}
 
	@PostMapping("/updatepassword")
	public String changePassword(Principal p, @RequestParam("oldPass") String oldPass,
			@RequestParam("newPass") String newPass,HttpSession session) {
		String email = p.getName();
		UserDtls loginUser = userRepo.findByEmail(email);
		boolean f = passwordEncode.matches(oldPass, loginUser.getPassword());
		if(f) {
			loginUser.setPassword(passwordEncode.encode(newPass));
			UserDtls updatePassUser = userRepo.save(loginUser);
			if(updatePassUser != null) {
				session.setAttribute("msg", "Password changed successfully");
			}else {
				session.setAttribute("errorMsg", "Something went wrong");
			}
		}else {
			session.setAttribute("errorMsg", "Password doesn't matched");
		}
		return "redirect:/user/changepassword";
	}
	@DeleteMapping("/deleteprofile")
	public String deleteProfile(Model model, Principal p) {
		String email = p.getName();
		UserDtls loginUser = userRepo.findByEmail(email);
		this.userRepo.delete(loginUser);
		return "redirect:/logout";
	}
 
}