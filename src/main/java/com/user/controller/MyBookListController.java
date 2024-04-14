package com.user.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.user.service.MyBookListService;
 
 
@Controller
public class MyBookListController {
 
	@Autowired
	private MyBookListService mybooklistService;
	@RequestMapping("/deleteMylist/{id}")
	public String deleteMyList(@PathVariable("id") int id) {
		mybooklistService.deleteById(id);
		return "redirect:/my_books";
	}
}