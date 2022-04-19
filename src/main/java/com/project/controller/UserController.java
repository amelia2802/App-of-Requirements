package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.User;
import com.project.repo.UserRepository;

@Controller
public class UserController 
{
	@Autowired
	UserRepository urepo;
	
	/*@RequestMapping("/")
	public String home()
	{
		return "home";
	}*/
	
	@RequestMapping("/home")
	public String getSignup()
	{
		return "home";
	}
	
	@RequestMapping("/login")
	public String getLogin()
	{
		return "login";
	}
	
	@PostMapping("/addUser")
	public ModelAndView addUser(@RequestParam("user_email") String user_email, User user)
	{
		ModelAndView mv=new ModelAndView("login");
		List<User> list=urepo.findByEMAIL(user_email);
		
		if(list.size()!=0)
		{
		mv.addObject("message", "Oops!  There is already a user registered with the email provided.");
		
		}
		else
		{
		urepo.save(user);
		mv.addObject("message","User has been successfully registered.");
		}
		
		return mv;
	}
	
	@PostMapping("/login")
	public String login_user(@RequestParam("user_email") String user_email,@RequestParam("user_pass") String user_pass,
			HttpSession session,ModelMap modelMap)
	{
		
	User auser=urepo.findByUsernamePassword(user_email, user_pass);
	
	if(auser!=null)
	{
		String uname=auser.getUser_email();
		String upass=auser.getUser_pass();
	
		if(user_email.equalsIgnoreCase(uname) && user_pass.equalsIgnoreCase(upass)) 
		{
			session.setAttribute("user_email",user_email);
			return "home";
		}
		else 
		{
			modelMap.put("error", "Invalid Account");
			return "redirect:/login";
		}
	}
	else
	{
		modelMap.put("error", "Invalid Account");
		return "redirect:/login";
	}
	
	}
	
	@GetMapping(value = "/logout")
	public String logout_user(HttpSession session)
	{
		session.removeAttribute("username");
		session.invalidate();
		return "redirect:/login";
	}

	//library
	@RequestMapping("/lib-index")
	public String libIndex() {
    	return "lib-index";
	}

	
	
	
}
