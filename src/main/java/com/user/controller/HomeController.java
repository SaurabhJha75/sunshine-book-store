package com.user.controller;
 
import java.security.Principal;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
import com.user.model.UserDtls;
import com.user.repository.UserRepository;
import com.user.service.UserService;
 
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
 
@Controller
public class HomeController {
 
	@Autowired
	private UserService userService;
 
	@Autowired
	private UserRepository userRepo;
 
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
 
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
 
		if (p != null) {
			String email = p.getName();
			UserDtls user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		}
 
	}
 
	@GetMapping("/")
	public String index() {
		return "index";
	}
 
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
 
	@GetMapping("/register")
	public String register() {
		return "register";
	}
 
	@PostMapping("/createuser")
	public String createUser(@ModelAttribute UserDtls user,HttpSession session) {
 
		// System.out.println(user);
 
		boolean f = userService.checkEmail(user.getEmail());
 
		if (f) {
 
			session.setAttribute("errorMsg", "Email Id already Exist");
 
		} else {
 
			UserDtls userDetails = userService.createUser(user);
 
			if (userDetails != null) {
				session.setAttribute("msg", "Register Successfully");
 
			} else {
				session.setAttribute("errorMsg", "Somwthing went Wrong!!!");
			}
 
		}
 
		return "redirect:/register";
	}
 
	@GetMapping("/loadForgetPassword")
	public String loadForgetPassword() {
		return "forget_password";
	}
 
	@GetMapping("/loadResetPassword/{id}")
	public String loadResetPassword(@PathVariable int id, Model m) {
		m.addAttribute("id", id);
		return "reset_password";
	}
 
	@PostMapping("/forgetpassword")
	public String forgetPassword(@RequestParam String email, @RequestParam String mobileNumber, HttpSession session) {
 
		UserDtls user = userRepo.findByEmailAndMobileNumber(email, mobileNumber);
 
		if (user != null) {
			return "redirect:/loadResetPassword/" + user.getId();
		} else {
			session.setAttribute("errorMsg", "Invalid email & mobile number");
			return "forget_password";
		}
 
	}
 
	@PostMapping("/changepassword")
	public String resetPassword(@RequestParam String psw, @RequestParam String id, HttpSession session) {
 
		UserDtls user = userRepo.findById(id);
 
		String encryptPass = passwordEncode.encode(psw);
		user.setPassword(encryptPass);
		UserDtls updateUser = userRepo.save(user);
 
		if (updateUser != null) {
			session.setAttribute("msg", "Password reset successfully");
		}
		return "redirect:/loadForgetPassowrd";
	}
}